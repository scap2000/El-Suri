package org.digitall.lib.network;

import java.net.BindException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.digitall.lib.environment.Debug;
import org.digitall.lib.ssl.SSLConfig;
import org.digitall.lib.ssl.SSLRelayClient;

public abstract class NetworkConfig {

    private static String SQL_SERVER_IP = "localhost";
    private static int SQL_SERVER_PORT = SSLConfig.PG_CLIENT_PORT;
    private static SSLRelayClient fmClient = null;
    private static SSLRelayClient pgClient = null;
    private static SSLRelayClient chwyClient = null;

    public static String getServerIP() {
	return SQL_SERVER_IP;
    }

    public static int getServerPort() {
	return SQL_SERVER_PORT;
    }

    public static void setServerURL(String _serverIP) {
	SQL_SERVER_IP = _serverIP;
    }

    public static boolean restartClient() {
	if (fmClient != null) {
	    fmClient.close();
	    fmClient = null;
	}
	if (pgClient != null) {
	    pgClient.close();
	    pgClient = null;
	}
	if (chwyClient != null) {
	    chwyClient.close();
	    chwyClient = null;
	}
	return startClient();
    }

    public static boolean startClient() {
	try {
	    if (fmClient == null) {
		int tries = 0;
		while (tries < 3 && fmClient == null) {
		    try {
			fmClient = new SSLRelayClient(SSLConfig.FM_SERVER_SECURED_PORT, SSLConfig.FM_CLIENT_PORT, SSLConfig.FM_SERVICE);
		    } catch (BindException x) {
			SSLConfig.setFM_CLIENT_PORT((int)(Math.random() * 10000));
			Debug.println("FM Port already open, trying on port: " + SSLConfig.FM_CLIENT_PORT);
			tries++;
			continue;
		    }
		}
	    }
	    if (pgClient == null) {
		int tries = 0;
		while (tries < 3 && pgClient == null) {
		    try {
			pgClient = new SSLRelayClient(SSLConfig.PG_SERVER_SECURED_PORT, SSLConfig.PG_CLIENT_PORT, SSLConfig.PG_SERVICE);
		    } catch (BindException x) {
			SSLConfig.setPG_CLIENT_PORT((int)(Math.random() * 10000));
			Debug.println("PG Port already open, trying on port: " + SSLConfig.PG_CLIENT_PORT);
			tries++;
			continue;
		    }
		}
	    }
	    if (chwyClient == null) {
		int tries = 0;
		while (tries < 3 && chwyClient == null) {
		    try {
			chwyClient = new SSLRelayClient(SSLConfig.CHWY_SERVER_SECURED_PORT, SSLConfig.CHWY_CLIENT_PORT, SSLConfig.CHWY_SERVICE);
		    } catch (BindException x) {
			SSLConfig.setCHWY_CLIENT_PORT((int)(Math.random() * 10000));
			Debug.println("CHWY Port already open, trying on port: " + SSLConfig.CHWY_CLIENT_PORT);
			tries++;
			continue;
		    }
		}
	    }
	    return (pgClient != null && fmClient != null && chwyClient != null);
	} catch (Exception x) {
	    x.printStackTrace();
	    return false;
	}
    }

    public static String getHostname() {
	String _hostName = "";
	try {
	    InetAddress localIP = InetAddress.getLocalHost();
	    //String LOCALHOST_IP = localIP.getHostAddress();
	    _hostName = localIP.getHostName();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	}
	return _hostName;
    }

}
