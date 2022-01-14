package org.digitall.lib.geo.gaia;

import java.net.BindException;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQLMini;
import org.digitall.lib.ssl.SSLConfig;
import org.digitall.lib.ssl.SSLRelayClient;

public class GaiaClient {

    private boolean connected = false;

    private String serverURL = "";
    private String database = "";
    private String user = "";
    private String valor = "";
    private SSLRelayClient logClient = null;
    private LibSQLMini libSQL = new LibSQLMini();

    private boolean validado = false;

    public GaiaClient(String _serverURL, String _database, String _user, String _password) {
	serverURL = _serverURL;
	String _localhost = "localhost";
	try {
	    _localhost = InetAddress.getLocalHost().getHostAddress().toString();
	} catch (UnknownHostException e) {
	    e.printStackTrace();   
	}
	database = "jdbc:postgresql://" + _localhost + ":" + SSLConfig.GAIA_CLIENT_PORT + "/" + _database;
	user = _user;
	valor = _password;
    }

    public boolean startClient() {
	int tries = 0;
	connected = false;
	while (tries < 3 && logClient == null) {
	    try {
		logClient = new SSLRelayClient(SSLConfig.GAIA_SERVER_SECURED_PORT, SSLConfig.GAIA_CLIENT_PORT, serverURL);
		connected = true;
	    } catch (BindException x) {
		SSLConfig.setGAIA_CLIENT_PORT((int)(Math.random() * 10000));
		System.out.println("GAIA Port already open, trying on port: " + SSLConfig.GAIA_CLIENT_PORT);
		tries++;
		continue;
	    }
	}
	return tryToConnect();
    }

    public boolean tryToConnect() {
	validado = false;
	if (connected) {
	    try {
		libSQL.setDataBaseString(database);
		validado = libSQL.tryToConnect(user, valor);
		if (validado) {
		    //System.out.println("lib = "+LibSQLMini.getDataBase());
		    libSQL.setSQLUser(user);
		    libSQL.setSQLPass(valor);
		    validado = libSQL.isConnected();
		} else {
		    System.out.println("Error al validar conexión SQL en GaiaClient.java");
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    if (!validado) {
		logClient.close();
	    }
	}
	return validado;
    }

    public boolean getBoolean(String _functionName, Object _params) {
	return libSQL.getBoolean(_functionName, _params);
    }

    public String getString(String _functionName, Object _params) {
	return libSQL.getString(_functionName, _params);
    }

    public ResultSet exQuery(String _sqlStat) {
	return libSQL.exQuery(_sqlStat);
    }

    public void closeConnection() {
	logClient.close();
    }


}
