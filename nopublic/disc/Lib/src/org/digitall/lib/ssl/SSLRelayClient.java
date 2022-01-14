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
 * SSLRelayClient.java
 *
 * */
package org.digitall.lib.ssl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.BindException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.net.SocketException;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.digitall.lib.network.NetworkConfig;

//

/**  ******************************************************
Disclaimer
This example code is provided "AS IS" without warranties of any kind.
Use it at your Risk!

Our relay client relay application that will accept the normal
socket connection from a client app and forward these to our secured SSL
connection

@author Chianglin Jan 2003

@version Modified by Santiago Cassina August 2007
 ******************************************************************* */
public class SSLRelayClient extends SSLConnection {

    private SSLSocket sslSocket;
    private ServerSocket serverSocket;
    private DataInputStream unsecuredInputStream, securedInputStream;
    private DataOutputStream unsecuredOutputStream, securedOutputStream;
    private String destinationIP;
    private int destinationPort;
    private int service = -1;
    private boolean running = false;
    private boolean socketOpened = false;
    //default constructor

    public SSLRelayClient(int _serverSecuredPort, int _clientPort, int _service) throws BindException {
	super();
	running = true;
	if (_service == SSLConfig.LOG_SERVICE) {
	    destinationIP = SSLConfig.LOG_SERVER_REMOTE_IP;
	} else {
	    destinationIP = (NetworkConfig.getServerIP());
	}
        destinationPort = _serverSecuredPort;
	initLocalConnection(_clientPort);
	service = _service;
	System.out.println("Starting relayapp ..." + _serverSecuredPort + "<->" + _clientPort + " to " + destinationIP + ":" + destinationPort);
	startListen();
    }
    //creates the secured SSL link

    public SSLRelayClient(int _serverSecuredPort, int _clientPort, String _serverURL) throws BindException {
	super();
	running = true;
	destinationIP = _serverURL;
	destinationPort = _serverSecuredPort;
	initLocalConnection(_clientPort);
	service = -1;
	System.out.println("Starting relayapp ..." + _serverSecuredPort + "<->" + _clientPort + " to " + destinationIP + ":" + destinationPort);
	startListen();
    }

    public void initSecuredConnection(String dest, int destport) throws BindException {
	try {
	    //get the Socketfactory from the SSLContext
	    SSLSocketFactory sf = getMySSLContext().getSocketFactory();
	    sslSocket = (SSLSocket)sf.createSocket(dest, destport);
	    sslSocket.startHandshake();
	    //begin handshake
	    SSLSession current = sslSocket.getSession();
	    //Debug.println("Cipher suite in use is " + current.getCipherSuite());
	    //Debug.println("Protocol is " + current.getProtocol());
	    //get the input and output streams from the SSL connection
	    securedInputStream = new DataInputStream(new BufferedInputStream(sslSocket.getInputStream()));
	    securedOutputStream = new DataOutputStream(new BufferedOutputStream(sslSocket.getOutputStream()));
	    //Debug.println("Got remote secured connection");
	    running = true;
	} catch (ConnectException e) {
	    e.printStackTrace();
	    System.out.println("Maybe the server isn't running");
	    running = false;
	} catch (IOException e) {
	    e.printStackTrace();
	    running = false;
	}
    }

    public void initLocalConnection(int _port) throws BindException {
	try {
	    if (!socketOpened) {
		serverSocket = new ServerSocket(_port, 0, InetAddress.getLocalHost());
		socketOpened = true;
	    }
	    running = true;
	} catch (BindException e) {
	    throw e;
	} catch (Exception e) {
	    e.printStackTrace();
	    running = false;
	}
    }

    public void run() {
	while (running) {
	    try {
		Socket clientSocket = serverSocket.accept();
		initSecuredConnection(destinationIP, destinationPort);
		unsecuredInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
		unsecuredOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
		beginRelay();
	    } catch (SocketException e) {
		//e.printStackTrace();
	        running = false;
	    } catch (IOException e) {
		e.printStackTrace();
		running = false;
	    }
	}
    }

    public void startListen() {
	start();
    }
    // start our relay threads to do the actual relaying

    public void beginRelay() {
	//System.out.println("Beginning relay");
	new Relay(unsecuredInputStream, securedOutputStream, false, service);
	new Relay(securedInputStream, unsecuredOutputStream, false, service);
	running = true;
    }

    public boolean isRunning() {
	return running;
    }
    
    public void close() {
	if (socketOpened) {
	    try {
		System.out.println("Closing socket: " + serverSocket.getInetAddress().getLocalHost() + ":" + serverSocket.getLocalPort());
	        socketOpened = false;
	        running = false;
	        serverSocket.close();
	        this.interrupt();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

}
