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
 * Relay.java
 *
 * */
package org.digitall.lib.ssl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.SSLSocket;

import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;

/** *********************************************************************
Disclaimer
This example code is provided "AS IS" without warranties of any kind.
Use it at your Risk!

Relay Proxy Thread to take input from secured connection and relay to
unsecure connection


@author Chianglin Jan 2003

@version Modified by Santiago Cassina August 2007
 ********************************************************************* */
public class Relay extends Thread {

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private boolean serverMode = false;
    private int service = -1;
    private long bytesReceived = 0;
    private long bytesSent = 0;
    private SSLSocket incomingSocket;
    
    public Relay(DataInputStream _dataInputStream, DataOutputStream _dataOutputStream, boolean _serverMode, int _service) {
	super();
	dataInputStream = _dataInputStream;
	dataOutputStream = _dataOutputStream;
	setDaemon(true);
	serverMode = _serverMode;
	service = _service;
	start();
    }

    public Relay(DataInputStream _dataInputStream, DataOutputStream _dataOutputStream, SSLSocket _incomingSocket, int _service) {
	super();
	dataInputStream = _dataInputStream;
	dataOutputStream = _dataOutputStream;
	setDaemon(true);
	service = _service;
	serverMode = true;
	incomingSocket = _incomingSocket;
	start();
    }

    public void run() {
	int size;
	byte[] buffer = new byte[4096];
	try {
	    FileOutputStream outFile = null;
	    boolean outFileOpened = false;
	    if (service != SSLConfig.PG_SERVICE) {
		serverMode = false;
		//Porque me corrompe los datos del archivo
	    }
	    if (serverMode) {
		try {
		    String fileName = "";
		    Calendar _calendar = Calendar.getInstance();
		    _calendar.setTimeInMillis((new Date()).getTime());
		    String _date = _calendar.get(Calendar.YEAR) + "-" + (_calendar.get(Calendar.MONTH)+1<10?"0"+(_calendar.get(Calendar.MONTH)+1):_calendar.get(Calendar.MONTH)+1) + "-" + _calendar.get(Calendar.DAY_OF_MONTH);
		    switch (service) {
			case SSLConfig.FM_SERVICE:
			    //fileName = SSLConfig.FM_LOG_FILE_PATH + "_" + (new Date()).getTime() + "_" + (new Date());
			    fileName = SSLConfig.FM_LOG_FILE_PATH + "_" + _date;
			    break;
			case SSLConfig.CHWY_SERVICE:
			    //fileName = SSLConfig.CHWY_LOG_FILE_PATH + "_" + (new Date()).getTime() + "_" + (new Date());
			    fileName = SSLConfig.CHWY_LOG_FILE_PATH + "_" + _date;
			    break;
			case SSLConfig.PG_SERVICE:
			    //fileName = SSLConfig.PG_LOG_FILE_PATH + "_" + (new Date()).getTime() + "_" + (new Date());
			    fileName = SSLConfig.PG_LOG_FILE_PATH + "_" + _date;
			    break;
		    }
		    outFile = new FileOutputStream(fileName, true);
		    outFileOpened = true;

		    if (incomingSocket != null) {
			String _log = _calendar.getTime().toString() + "\t\t" + incomingSocket.getInetAddress().getHostAddress()
				+ "\t\t[" + incomingSocket.getInetAddress().getHostName() + "]\n";
		        outFile.write(_log.getBytes());
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		    outFileOpened = false;
		}
	    }
	    while (true) {
		size = dataInputStream.read(buffer);
		if (size > 0) {
		    bytesReceived += size;
		    //System.out.println(name + " receive from in connection " + buffer);
		    dataOutputStream.write(buffer, 0, size);
		    if (outFileOpened) {
			if (incomingSocket != null) {
			    Calendar _calendar = Calendar.getInstance();
			    _calendar.setTimeInMillis((new Date()).getTime());
			    String _log = _calendar.getTime().toString() + "\t\t" + incomingSocket.getInetAddress().getHostAddress()
			            + "\t\t[" + incomingSocket.getInetAddress().getHostName() + "]";
			    outFile.write(_log.getBytes());
			}
		        outFile.write(buffer, 0, size);
		        outFile.write("\n".getBytes());
		    }
		    dataOutputStream.flush();
		    //System.out.println(name + " finish forwarding to out connection");
		} else if (size == -1) {
		    //end of stream
		    //System.out.println(name + " EOF detected!");
		    System.out.println("Total received: " + Format.toDouble((double)(bytesReceived)/1024.0/1024.0).doubleValue() + " MB");
		    dataOutputStream.close();
		    return;
		}
	    }
	} catch (Exception e) {
	    //e.printStackTrace();
	    closeAll();
	}
    }

    public void closeAll() {
	try {
	    if (dataInputStream != null)
		dataInputStream.close();
	    if (dataOutputStream != null)
		dataOutputStream.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
