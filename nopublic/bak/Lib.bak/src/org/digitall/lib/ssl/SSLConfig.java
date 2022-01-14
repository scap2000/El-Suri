package org.digitall.lib.ssl;

public abstract class SSLConfig {
    //Common configuration
    public static final String KEY_FILE_PATH = "certs/serverStore.cer";
    public static final String TRUST_FILE_PATH = "certs/clientStore.cer";
    public static final char[] STORE_PASS = new String("digitallsh").toCharArray();
    public static final char[] KEY_PASS = new String("digitallsh").toCharArray();
    //public static final String SERVER_IP = "172.16.4.253";
    //End of common configuration
    //ChatWithYou Module configuration
    public static final int CHWY_SERVICE = 1;
    public static final String CHWY_LOG_FILE_PATH = "/tmp/chwy_log";
    public static int CHWY_CLIENT_PORT = 1502;
    public static final int CHWY_SERVER_SECURED_PORT = 2002;
    public static final int CHWY_SERVER_UNSECURED_PORT = 5431;
    //End of module configuration
    //FileManager Module configuration
    public static final int FM_SERVICE = 2;
    public static final String FM_LOG_FILE_PATH = "/tmp/fman_log";
    public static int FM_CLIENT_PORT = 1501;
    public static final int FM_SERVER_SECURED_PORT = 2001;
    public static final int FM_SERVER_UNSECURED_PORT = 5430;
    //End of module configuration
    //PostgreSQL SSL Connection configuration
    public static final int PG_SERVICE = 3;
    public static final String PG_LOG_FILE_PATH = "/tmp/sql_log";
    public static int PG_CLIENT_PORT = 1503;
    public static final int PG_SERVER_SECURED_PORT = 2003;
    public static final int PG_SERVER_UNSECURED_PORT = 5432;
    //End of module configuration
    //LogsSystem SSL Connection configuration
    public static final int LOG_SERVICE = 4; // el valor 1 le indica al cliente que se debe conectar a digitall en lugar de localhost
    public static final String LOGSYSTEM_FILE_PATH = "/tmp/logsystem_log";
    public static int LOG_CLIENT_PORT = 1504;
    public static final int LOG_SERVER_SECURED_PORT = 2003;
    public static final int LOG_SERVER_UNSECURED_PORT = 5432;
    public static String LOG_SERVER_REMOTE_IP = "localhost";
    //GaiaClient Configurations
    public static final int GAIA_SERVICE = 5;
    public static int GAIA_CLIENT_PORT = 1505;
    public static final int GAIA_SERVER_SECURED_PORT = 2003;
    public static final int GAIA_SERVER_UNSECURED_PORT = 5432;
    //LibSQLMini Configurations
    public static final int SQLMINI_SERVICE = 6;
    public static int SQLMINI_CLIENT_PORT = 1506;
    public static final int SQLMINI_SERVER_SECURED_PORT = 2003;
    public static final int SQLMINI_SERVER_UNSECURED_PORT = 5432;
    //End of module configuration

     public static void setFM_CLIENT_PORT(int _port) {
	 FM_CLIENT_PORT = _port;
     }

    public static void setPG_CLIENT_PORT(int _port) {
	PG_CLIENT_PORT = _port;
    }

    public static void setCHWY_CLIENT_PORT(int _port) {
	CHWY_CLIENT_PORT = _port;
    }
    
    public static void setLOG_CLIENT_PORT(int _port) {
        LOG_CLIENT_PORT = _port;
    }

    public static void setLOG_SERVER_REMOTE_IP(String _remoteIP) {
	LOG_SERVER_REMOTE_IP = _remoteIP;
    }

    public static void setGAIA_CLIENT_PORT(int _port) {
	GAIA_CLIENT_PORT = _port;
    }

    public static void setSQLMINI_CLIENT_PORT(int _port) {
	SQLMINI_CLIENT_PORT = _port;
    }
}
