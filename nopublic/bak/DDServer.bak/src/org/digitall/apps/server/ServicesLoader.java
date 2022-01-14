package org.digitall.apps.server;
import java.io.File;

import org.digitall.common.digitalk.ChWYServer;
import org.digitall.common.digitalk.LogClientThread;
//import org.digitall.common.filemanager.FileManServer;
import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.ssl.SSLConfig;
import org.digitall.lib.ssl.SSLRelayServer;

public class ServicesLoader {
    
    private ConfigFile confLogs;

    public ServicesLoader() {
	File _config = new File("/etc/ddesktop/logs.conf");
	System.out.println("Abriendo archivo de configuración: " + _config.getAbsolutePath());
	if (!_config.exists()) {
	    System.err.println("Error, no existe el archivo de configuración, se creará uno nuevo en la ubicación actual. Moverlo a \'/etc/ddesktop\'");
	    if (new File(System.getProperty("user.dir")).canWrite()) {
	        confLogs = new ConfigFile("logs.conf");
	        confLogs.setProperty("serverlocal","localhost");
	        confLogs.setProperty("bdlocal","digitall_devel");
	        confLogs.setProperty("usuario","logsuser");
	        confLogs.setProperty("valor","l0g5u5er");
	        confLogs.setProperty("serverremoto","digitallsh.com.ar");
	        confLogs.setProperty("bdremota","logsdatabase");
	        confLogs.setProperty("frecrecoleccion","10800000"); //recolectar datos para enviar
	        confLogs.setProperty("frecrevision","10000"); //tiempo sin conectarse al servidor remoto, luego del cual se dará de baja el servicio
	        confLogs.setProperty("activo","si");
	        System.err.println("Mueva o copie el archivo logs.conf a \'/etc/ddesktop\' e inicie la aplicación");
	        System.exit(0);
	    } else {
		System.err.println("Error, no se puede leer ni escribir el archivo de configuración");
	        System.exit(0);
	    }
	} else {
	    confLogs = new ConfigFile(_config.getAbsolutePath());
	}
	//FileManServer fmServer = new FileManServer();
	//SSLRelayServer fmSSLServer = new SSLRelayServer(SSLConfig.FM_SERVER_SECURED_PORT, SSLConfig.FM_SERVER_UNSECURED_PORT, SSLConfig.FM_SERVICE);

	new SSLRelayServer(SSLConfig.CHWY_SERVER_SECURED_PORT, SSLConfig.CHWY_SERVER_UNSECURED_PORT, SSLConfig.CHWY_SERVICE);
	new SSLRelayServer(SSLConfig.PG_SERVER_SECURED_PORT, SSLConfig.PG_SERVER_UNSECURED_PORT, SSLConfig.PG_SERVICE);
        
	new ChWYServer(confLogs);
	new LogClientThread(confLogs);
    }

    public static void main(String[] args) {
	new ServicesLoader();
    }
}
