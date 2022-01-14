package org.digitall.common.digitalk;

import java.io.IOException;

import java.net.BindException;
import java.net.Socket;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.sql.LibSQLMini;
import org.digitall.lib.ssl.SSLConfig;
import org.digitall.lib.ssl.SSLRelayClient;

public class LogClientThread {

    private boolean connected = false;
    private Socket socket = null;
    private int timerRecoleccion = 3600000; // se envia información cada 1 horas
    private int timerRevision = 10000; //observa si se puede conectar en caso de que no se conecte el servicio se cierra
    private int timeoutConexion = 0;

    static String ServerIPLocal = "";
    static String DBNameLocal = "";
    static String DBStringLocal = "";

    private String nombreUsuario = "";
    private String valor = "";

    static String ServerIPRemoto = "";
    static String DBNameRemota = "";
    static String DBStringRemota = "";

    private String servicioActivo = "no";

    private boolean validado = false;
    private static SSLRelayClient logClient = null;
    private ConfigFile configFile;
    private LogLocal logLocal;

    private Thread clientThread = null;
    private LibSQLMini libSQL = new LibSQLMini();

    public LogClientThread() {
	if (inicializarConexion()) {
	    startService();
	} else {
	    System.out.println("No se pudo inicializar el servicio de Logs");
	}
    }

    public LogClientThread(ConfigFile _configFile) {
	configFile = _configFile;
	cargarConfiguracion();
	if (servicioActivo.equalsIgnoreCase("SI")) {
	    if (inicializarConexion()) {
		startService();
	    } else {
		System.out.println("No se pudo inicializar servicio de Logs");
	    }
	} else {
	    System.out.println("El servicio de Logs está desactivado");
	}

    }

    private void cargarConfiguracion() {
	ServerIPLocal = configFile.getProperty("serverlocal");
	DBNameLocal = configFile.getProperty("bdlocal");
	DBStringLocal = "jdbc:postgresql://" + ServerIPLocal + "/" + DBNameLocal;

	ServerIPRemoto = configFile.getProperty("serverremoto");
	DBNameRemota = configFile.getProperty("bdremota");
	//jdbc:postgresql://host:port/database
	DBStringRemota = "jdbc:postgresql://" + ServerIPLocal + ":" + SSLConfig.LOG_CLIENT_PORT + "/" + DBNameRemota;

	SSLConfig.setLOG_SERVER_REMOTE_IP(ServerIPRemoto);

	timerRecoleccion = Integer.parseInt(configFile.getProperty("frecrecoleccion"));
	timerRevision = Integer.parseInt(configFile.getProperty("frecrevision"));

	nombreUsuario = configFile.getProperty("usuario");
	valor = configFile.getProperty("valor");

	servicioActivo = configFile.getProperty("activo");
    }

    private boolean inicializarConexion() {
	int tries = 0;
	boolean conectado = false;
	logClient = null;
	while (tries < 3 && logClient == null) {
	    try {
		logClient = new SSLRelayClient(SSLConfig.LOG_SERVER_SECURED_PORT, SSLConfig.LOG_CLIENT_PORT, SSLConfig.LOG_SERVICE);
		conectado = true;
	    } catch (BindException x) {
		SSLConfig.setLOG_CLIENT_PORT((int)(Math.random() * 10000));
		System.out.println("LOG Port already open, trying on port: " + SSLConfig.LOG_CLIENT_PORT);
		tries++;
		continue;
	    }
	}
	return conectado;
    }

    private boolean startService() {
	connected = tryToConnect();
	logLocal = new LogLocal(ServerIPLocal, DBNameLocal, nombreUsuario, valor);
	clientThread = new Thread() {
		public void run() {
		    while (true) {
			try {
			    enviarRegistros();
			    sleep(timerRecoleccion);
			    cargarConfiguracion();
			} catch (Exception e) {
			    connected = false;
			    continue;
			}
		        try {
		            Calendar lastUpdate = Calendar.getInstance();
		            Calendar now = Calendar.getInstance();
		            lastUpdate.setTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(configFile.getProperty("lastupdate")));
		            now.setTime(new Date());
		            int days = (int) ((now.getTimeInMillis() - lastUpdate.getTimeInMillis())/(1000*60*60*24));
		            if (days > timerRevision) {
		                System.out.println("Servicio cancelado");
		                System.exit(1);
		            }
		        } catch (ParseException e) {
		            configFile.setProperty("lastupdate", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		        }
		    }
		}
	    };
	clientThread.start();
	return connected;
    }

    //metodo que consulta en la base de datos de digitall el último log almacenado

    private String getUltimoRegistro() {
	ResultSet result = null;
	String fechaHora = "";
	libSQL.closeConnection();
	libSQL.setDataBaseString(DBStringRemota);
	validado = libSQL.tryToConnect(nombreUsuario, valor);
	//System.out.println("** ** ** libCliente = "+libSQL.getDataBase());
	result = libSQL.exFunction("auditorias.getultimoregistro", "'" + SystemInformation.getHostName() + "'");
	//////result = libSQL.exQuery("SELECT * FROM auditorias.logssystem WHERE nombreservidor = '" + SystemInformation.getHostName() + "' AND fechahora IN (SELECT MAX(fechahora) FROM auditorias.logssystem)");
	try {
	    if (result.next()) {
		fechaHora = result.getString("fechahora");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return fechaHora;
    }

    private void enviarRegistros() {
	String ultimaFechaHora = "";
	if (connected) {
	    System.out.println("Recolectando y enviando datos");
	    ultimaFechaHora = getUltimoRegistro(); //obtengo el ultimo registro enviado desde el servidor de digitall
	    String registros = getRegistrosEnviar(ultimaFechaHora); //obtengo los registros a enviar desde el servidor local
	    if (!(registros.equals(""))) {
		// envio los datos al servidor de digitall
		libSQL.closeConnection();
		libSQL.setDataBaseString(DBStringRemota);
		validado = libSQL.tryToConnect(nombreUsuario, valor);
		if (validado) {
		    timeoutConexion = 0;
		    //System.out.println(" datos guardados en = " + libSQL.getDataBase());
		    if (libSQL.exActualizar('a', registros)) {
			System.out.println("Se guardaron los datos enviados");
			configFile.setProperty("lastupdate", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		    } else {
			System.out.println("Error al conectarse con el servidor remoto");
		    }
		} else {
		    timeoutConexion += timerRecoleccion;
		}
	    } else {
		libSQL.closeConnection();
		libSQL.setDataBaseString(DBStringRemota);
		validado = libSQL.tryToConnect(nombreUsuario, valor);
		System.out.println("No hay datos para enviar");
	        configFile.setProperty("lastupdate", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
	    }
	} else {
	    connected = tryToConnect();
	    if (!connected) {
		System.out.println("No se pudo conectar con el servidor remoto, la última conexión exitosa fue " + configFile.getProperty("lastupdate"));
	    }
	}
    }

    private String getRegistrosEnviar(String _ultimaFechaHora) {
	String registros = "";
	registros = logLocal.getInformacionEnviar(_ultimaFechaHora);
	return registros;
    }

    public void disconnect() {
	try {
	    socket.close();
	    //socket = null;
	} catch (IOException e) {
	    // TODO
	    e.printStackTrace();
	}
    }

    public boolean tryToConnect() {
	try {
	    libSQL.setDataBaseString(DBStringRemota);
	    validado = libSQL.tryToConnect(nombreUsuario, valor);
	    if (validado) {
		//System.out.println("lib = "+libSQL.getDataBase());
		libSQL.setSQLUser(nombreUsuario);
		libSQL.setSQLPass(valor);
		if (libSQL.isConnected()) {
		    validado = true;
		} else {
		    validado = false;
		}
	    } else {

	    }
	} catch (Exception e) {
	    // TODO: Add catch code
	    e.printStackTrace();
	}
	return validado;
    }

}
