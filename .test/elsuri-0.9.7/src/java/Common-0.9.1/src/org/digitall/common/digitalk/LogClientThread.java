/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * LogClientThread.java
 *
 * */
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
