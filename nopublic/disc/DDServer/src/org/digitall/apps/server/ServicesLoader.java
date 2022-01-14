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
 * ServicesLoader.java
 *
 * */
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
