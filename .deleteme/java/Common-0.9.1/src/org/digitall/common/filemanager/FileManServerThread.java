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
 * FileManServerThread.java
 *
 * */
package org.digitall.common.filemanager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

import java.util.Vector;

import org.digitall.common.filemanager.LocalFile;
import org.digitall.common.filemanager.RemoteFile;
import org.digitall.lib.sql.LibSQL;

//

public class FileManServerThread extends Thread {

    private Socket clientSocket = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    boolean connected = false;
    private byte[] buffer;
    public static final int BUFFER_SIZE = 1024 * 50;

    public FileManServerThread(Socket _clientSocket, Vector _userList) {
	buffer = new byte[BUFFER_SIZE];
	clientSocket = _clientSocket;
	//in.lista.addElement(this);
	//in.cargarlista();
	//System.out.println("cliente agregado: " + this);
    }

    public void run() {
	try {
	    objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
	    connected = true;
	} catch (IOException x) {
	    x.printStackTrace();
	    connected = false;
	}
	String command;
	int status = 0;
	//(0: inicial; 1: from ok; 2: to ok; 3: msg ok);
	//userName = tryToGetUserName();
	String origUser = "";
	String destUser = "";
	String message = "";
	while (connected) {
	    try {
		System.out.println("Waiting for OBJECT!:");
		Object object = objectInputStream.readObject();
		System.out.println("Object received, class: " + object.getClass().getName());
		if (File.class.isInstance(object)) {
		    //FILE SUBMITTED!;
		    LocalFile file = (LocalFile)object;
		    System.out.println("FILE SUBMITTED: " + file);
		    getFile(file);
		} else if (RemoteFile.class.isInstance(object)) {
		    //FILE RETRIEVED!;
		    RemoteFile file = (RemoteFile)object;
		    System.out.println("FILE RETRIEVED: " + file.getName());
		    putFile(file);
		}
	    } catch (ClassNotFoundException e) {
		e.printStackTrace();
		continue;
	    } catch (IOException e) {
		System.out.println("Connection closed");
		connected = false;
		break;
	    }
	}
	try {
	    clientSocket.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private boolean getFile(LocalFile _file) {
	try {
	    //SQL START TRANSFER!
	    System.out.println("SQL START GET TRANSFER");
	    String params = "" + _file.getIDTransfer();
	    if (LibSQL.getBoolean("admin.starttransfer", params)) {
		//Download from remote host
		BufferedInputStream in = new BufferedInputStream(clientSocket.getInputStream());
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(FileManConfig.REMOTE_PATH + _file.getName()));
		int len = 0;
		int total = 0;
		while ((len = in.read(buffer)) > 0) {
		    out.write(buffer, 0, len);
		    total += len;
		    System.out.println("Total bytes received: " + total);
		}
		in.close();
		out.close();
		System.out.println("Receiving Done!");
		//SQL END COMPLETE TRANSFER!
		params = _file.getIDTransfer() + ", " + _file.length();
		_file.setIdFile(LibSQL.getInt("admin.addlocalfile", params));
		return (_file.getIdFile() == -1 ? false : true);
	    } else {
		System.out.println("Transfer not started");
		return false;
	    }
	} catch (FileNotFoundException e) {
	    //SQL ERROR TRANSFER
	    e.printStackTrace();
	    return false;
	} catch (IOException e) {
	    //SQL ERROR TRANSFER
	    e.printStackTrace();
	    return false;
	}
    }

    private boolean putFile(RemoteFile _remoteFile) {
	try {
	    System.out.println("SQL START PUT TRANSFER");
	    String params = "" + _remoteFile.getIDTransfer();
	    System.out.println("admin.starttransfer(" + params + ")");
	    if (LibSQL.getBoolean("admin.starttransfer", params)) {
		//Upload to remote host
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(FileManConfig.REMOTE_PATH + _remoteFile.getName()));
		BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream());
		int len = 0;
		int total = 0;
		while ((len = in.read(buffer)) > 0) {
		    out.write(buffer, 0, len);
		    total += len;
		    System.out.println("Total bytes sent: " + total);
		}
		in.close();
		out.close();
		System.out.println("Uploading done!");
		return true;
	    } else {
		System.out.println("Transfer not started");
		return false;
	    }
	} catch (FileNotFoundException e) {
	    //e.printStackTrace();
	    return false;
	} catch (IOException e) {
	    //e.printStackTrace();
	    return false;
	}
    }

}
