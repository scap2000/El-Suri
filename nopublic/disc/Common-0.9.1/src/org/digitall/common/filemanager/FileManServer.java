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
 * FileManServer.java
 *
 * */
package org.digitall.common.filemanager;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.Date;
import java.util.Vector;

import org.digitall.lib.sql.LibSQLMini;
import org.digitall.lib.ssl.SSLConfig;

//FileMan Server Class

public class FileManServer extends Thread {

    private static final int PORT = SSLConfig.FM_SERVER_UNSECURED_PORT;
    private static Vector userList = new Vector();
    //static String ServerIP = "digitallsh.digitallsh.com.ar";
    static String ServerIP = "localhost";
    static String DBName = "template1";
    static String DBString = "jdbc:postgresql://" + ServerIP + "/" + DBName;

    public FileManServer() {
	LibSQLMini libSQL = new LibSQLMini();
        libSQL.setDataBaseString(DBString);
        libSQL.setSQLUser("fileman");
        libSQL.setSQLPass("mortadelo");
	if (libSQL.isConnected()) {
	    start();
	} else {
	    System.exit(-1);
	}
    }

    public void run() {
	ServerSocket serverSocket = null;
	boolean listening = true;
	try {
	    serverSocket = new ServerSocket(PORT);
	    System.out.println("FileManServer started at " + (new Date()) + " listening on port " + PORT + " servicing FileManager!");
	    while (listening) {
		Socket clientSocket = null;
		try {
		    clientSocket = serverSocket.accept();
		    //AUTENTICAR CLIENTE!!!
		    if (true) {
			//autenticado OK
			FileManServerThread serverThread = new FileManServerThread(clientSocket, userList);
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
    /*public static void main(String[] args) throws IOException {
        try {
            initServer();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }*/

}
