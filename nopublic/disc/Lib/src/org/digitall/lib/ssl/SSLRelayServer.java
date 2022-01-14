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
