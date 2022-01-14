/**
 * SSLRelayServer.java
 *
 * */
package org.digitall.lib.ssl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.Date;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**  ********************************************************************
Disclaimer
This example code is provided "AS IS" without warranties of any kind.
Use it at your Risk!

Our SSL secured Relay Server to handle all incoming data stream and forward them to the relevant port on the localmachine
It is a subclass of the SSLConnection

@author Chianglin Jan 2003

@version Modified by Santiago Cassina August 2007
 ********************************************************************** */
public class SSLRelayServer extends SSLConnection {

    private ServerSocket serverSocket;
    private int service = -1;
    private int destinationPort = -1;
    private boolean running = false;

    public SSLRelayServer(int _serverSecuredPort, int _serverUnsecuredPort, int _service) {
	super();
	running = true;
	service = _service;
	initSSLServerSocket(_serverSecuredPort);
	startListen(_serverSecuredPort, _serverUnsecuredPort);
    }

    public void initSSLServerSocket(int localport) {
	try {
	    //get the ssl socket factory
	    SSLServerSocketFactory ssf = (getMySSLContext()).getServerSocketFactory();
	    //create the ssl server socket
	    serverSocket = ssf.createServerSocket(localport);
	    ((SSLServerSocket)serverSocket).setNeedClientAuth(true);
	    running = true;
	} catch (Exception e) {
	    running = false;
	    e.printStackTrace();
	}
    }

    public void run() {
	while (running) {
	    try {
	        System.out.println("Waiting incoming connections");
		SSLSocket incomingSocket = (SSLSocket)serverSocket.accept();
		//set a ten minutes timeout
		//incoming.setSoTimeout(10 * 60 * 1000);
		//set a 60 seconds
		//int minutes = 1;
		//incoming.setSoTimeout(minutes * 60 * 1000);
		//System.out.println((new Date()) + " connection from " + incoming);
		//System.out.println("Timeout setting for socket is " + incoming.getSoTimeout());
		createHandlers(incomingSocket, destinationPort);
	    } catch (IOException e) {
		e.printStackTrace();
		continue;
	    }
	}
    }

    public void startListen(int _localPort, int _destinationPort) {
	destinationPort = _destinationPort;
	System.out.println("SSLRelayServer started at " + (new Date()) + " listening on port " + _localPort + " servicing SSLRelay!");
	start();
    }

    public void createHandlers(SSLSocket _incomingSocket, int _destinationPort) {
	//create a normal socket to connect to actual Server
	//System.out.println("Creating handlers");
	try {
	    Socket handlerSocket = new Socket("localhost", _destinationPort);
	    System.out.println(handlerSocket);
	    //get the input and output streams associated with the actual server
	    DataInputStream unsecuredInputStream = new DataInputStream(new BufferedInputStream(handlerSocket.getInputStream()));
	    DataOutputStream unsecuredOutputStream = new DataOutputStream(new BufferedOutputStream(handlerSocket.getOutputStream()));
	    //get our secured input and output streams of relay server
	    DataInputStream securedInputStream = new DataInputStream(new BufferedInputStream(_incomingSocket.getInputStream()));
	    DataOutputStream securedOutputStream = new DataOutputStream(new BufferedOutputStream(_incomingSocket.getOutputStream()));
	    //create the two handler threads
	    new Relay(securedInputStream, unsecuredOutputStream, _incomingSocket, service);
	    new Relay(unsecuredInputStream, securedOutputStream, false, service);
	    System.out.println("Connecting from: " + _incomingSocket.getInetAddress());
	    running = true;
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    //LOG TO ERRORS TO SEE IF THERE IS A SECURITY
	    System.out.println("Error trying to connect from: " + _incomingSocket.getInetAddress());
	}
    }

    public boolean isRunning() {
	return running;
    }

}
