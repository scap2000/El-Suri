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
