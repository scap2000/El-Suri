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
 * GaiaClient.java
 *
 * */
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
