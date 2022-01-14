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
