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
 * ChWYServer.java
 *
 * */
package org.digitall.common.digitalk;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.Date;
import java.util.Vector;

import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.ssl.SSLConfig;

//ChatWithYou Server Class

public class ChWYServer extends Thread {

    private static final int PORT = SSLConfig.CHWY_SERVER_UNSECURED_PORT;
    private static Vector userList = new Vector();
    private ConfigFile configFile;
    
    public ChWYServer() {
	start();
    }
    
    public ChWYServer(ConfigFile _configFile) {
        configFile = _configFile;
        start();
    }

    public void run() {
	ServerSocket serverSocket = null;
	boolean listening = true;
	try {
	    serverSocket = new ServerSocket(PORT);
	    System.out.println("ChWYServer started at " + (new Date()) + " listening on port " + PORT + " servicing ChatWithYou!");
	    while (listening) {
		Socket clientSocket = null;
		try {
		    clientSocket = serverSocket.accept();
		    //AUTENTICAR CLIENTE!!!
		    if (true) {
			//autenticado OK
			ChWYServerThread serverThread = new ChWYServerThread(clientSocket, userList,configFile);
			System.out.println("Conectado: " + clientSocket);
			serverThread.start();
		    } else {
			//ERROR, NO AUTENTICADO
		    }
		} catch (IOException e) {
		    System.err.println("Accept failed: " + serverSocket + ", " + e.getMessage());
		    continue;
		}
	    }
	} catch (IOException e) {
	    System.out.println("Service already up or address/port error");
	    //e.printStackTrace();
	    System.exit(0);
	}
    }
}
