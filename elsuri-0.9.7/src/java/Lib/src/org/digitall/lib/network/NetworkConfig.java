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
 * NetworkConfig.java
 *
 * */
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
	    /*
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
	    */
	    if (pgClient == null) {
		int tries = 0;
		while (tries < 3 && pgClient == null) {
		    try {
			pgClient = new SSLRelayClient(SSLConfig.PG_SERVER_SECURED_PORT, SSLConfig.PG_CLIENT_PORT, SSLConfig.PG_SERVICE);
		    } catch (BindException x) {
			x.printStackTrace();
			System.out.println(x.getMessage());
		        Debug.println("ERROR PGClient: No se puede abrir el puerto: " + SSLConfig.PG_CLIENT_PORT);
		        SSLConfig.setPG_CLIENT_PORT((int)(Math.random() * 10000));
		        Debug.println("AVISO PGClient: Intentando abrir el puerto: " + SSLConfig.PG_CLIENT_PORT);
			tries++;
			continue;
		    }
		}
	    }
	    /*
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
	    */
	    //return (pgClient != null && fmClient != null && chwyClient != null);
	    return (pgClient != null);
	} catch (Exception x) {
	    x.printStackTrace();
	    return false;
	}
    }

    public static String getHostname() {
	String _hostName = "";
	try {
	    //InetAddress localIP = InetAddress.getLocalHost();
	    //String LOCALHOST_IP = localIP.getHostAddress();
	    InetAddress localIP = InetAddress.getByName("localhost");
	    _hostName = localIP.getHostName();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	}
	return _hostName;
    }

}
