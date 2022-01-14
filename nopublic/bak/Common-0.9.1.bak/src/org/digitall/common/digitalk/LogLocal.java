package org.digitall.common.digitalk;

import java.net.BindException;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.lib.sql.LibSQLMini;
import org.digitall.lib.ssl.SSLConfig;
import org.digitall.lib.ssl.SSLRelayClient;

public class LogLocal {
    
    private SSLRelayClient pgClient = null;
    private boolean validado = false;
    private String nombreUsuario = "";
    private String clave = "";
    static String ServerIPLocal = "";
    static String DBNameLocal = "";
    static String DBStringLocal = "jdbc:postgresql://" + ServerIPLocal + "/" + DBNameLocal;
    private String datosEnviar = "";
    private String ultimaFechaHora = "";
    private LibSQLMini libSQL = new LibSQLMini();
    
    public LogLocal() {
        iniciarConexionLocal();
        //conectar();
    }
    
    public LogLocal(String _serverLocal, String _dbNameLocal, String _nombreUsuario, String _clave) {
        ServerIPLocal = _serverLocal;
        DBNameLocal = _dbNameLocal;
        DBStringLocal = "jdbc:postgresql://" + ServerIPLocal + "/" + DBNameLocal;
        nombreUsuario = _nombreUsuario;
        clave = _clave;
        iniciarConexionLocal();
    }
    
    public void iniciarConexionLocal(){
        if (pgClient == null) {
            int tries = 0;
            while (tries < 3 && pgClient == null) {
                try {
                    pgClient = new SSLRelayClient(SSLConfig.PG_SERVER_SECURED_PORT, SSLConfig.PG_CLIENT_PORT, SSLConfig.PG_SERVICE);
                } catch (BindException x) {
                    SSLConfig.setPG_CLIENT_PORT((int)(Math.random() * 10000));
                    System.out.println("PG Port already open, trying on port: " + SSLConfig.PG_CLIENT_PORT);
                    tries++;
                    continue;
                }
            }
        }
    }
    
    public boolean tryToConnect() {
        libSQL.setDataBaseString(DBStringLocal);
        validado = libSQL.tryToConnect(nombreUsuario, clave);
        if (validado) {
            libSQL.setSQLUser(nombreUsuario);
            libSQL.setSQLPass(clave);
            if (libSQL.isConnected()) {
            
            } else {
                
            }
        } else {
            
        }
        return validado;
    }

    private void conectar() {
        libSQL.closeConnection();
        libSQL.setDataBaseString(DBStringLocal);
        validado = libSQL.tryToConnect(nombreUsuario, clave);
        validado = true;
        if (validado) {
            libSQL.setSQLUser(nombreUsuario);
            libSQL.setSQLPass(clave);
            if (libSQL.isConnected()) {
                System.out.println("Conexion Establecida");
            } else {
                System.out.println("No se pudo conectar");
            }
        } else {
            System.out.println("No se pudo conectar");
        }
    }
    
    public String getInformacionEnviar(String _ultimaFechaHora){
        ResultSet resultados = null;
        String result = "";
        String filtro = "";
        conectar();
        
            System.out.println("** ** ** base de datos local = " + libSQL.getDataBase());
            if(!_ultimaFechaHora.equals("")){
                filtro = " AND fechahora > '" + _ultimaFechaHora + "'";
            }
            resultados = libSQL.exQuery("SELECT 'INSERT INTO auditorias.logssystem VALUES(' || oidusuario ||','''||usuario ||''','''|| fechahora || ''',''' || nombresistema || ''',''' || nombrehost || ''',''' || basededatos || ''',''' || direccionip || ''',''' || direccionmac || ''',''' || versionjvm || ''',''' || nombreso || ''',''' || memoriaram || ''',''' || procesador || ''',''' || resolucionpantalla || ''',''' || estado || ''',''' || nombreservidor || ''')' AS registros FROM auditorias.logssystem WHERE estado <> '*'" + filtro + " AND nombreservidor = '" + SystemInformation.getHostName() + "';");
            try {
                if(!(resultados == null)){
                    while(resultados.next()){
                        result += resultados.getString("registros")+ ";\n";
                    }    
                } else{
                    System.out.println("Sin resultados");
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            libSQL.closeConnection();    
        //pgClient.close();
        return result;
    }
    
    public void desconectar(){
        libSQL.closeConnection();
    }

    public void setDatosEnviar(String datosEnviar) {
        this.datosEnviar = datosEnviar;
    }

    public String getDatosEnviar() {
        return datosEnviar;
    }

    public void setUltimaFechaHora(String ultimaFechaHora) {
        this.ultimaFechaHora = ultimaFechaHora;
    }

    public String getUltimaFechaHora() {
        return ultimaFechaHora;
    }
    
}
