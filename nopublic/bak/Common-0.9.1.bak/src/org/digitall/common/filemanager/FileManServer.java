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
