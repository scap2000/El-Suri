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
 * ChWYLocalChatPanel.java
 *
 * */
package org.digitall.apps.chwy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.InetAddress;
import java.net.Socket;

import java.net.UnknownHostException;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

import org.digitall.common.digitalk.ChWYConfig;
import org.digitall.common.digitalk.SystemInformation;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.network.NetworkConfig;
import org.digitall.lib.ssl.SSLConfig;

//

public class ChWYLocalChatPanel extends JDialog {
//public class ChWYLocalChatPanel extends BasicDialog {

    //SE DEBE TRANSFORMAR EN BasicInternalFrame o BasicPanel
    private BasicLabel lblTitle = new BasicLabel();
    private BasicLabel lblWriteMessage = new BasicLabel();
    private BasicButton btnSendMessage = new BasicButton();
    private BasicButton btnCloseWindow = new BasicButton();
    private BasicTextField tfMessage = new BasicTextField();
    private JTextPane messagesArea = new JTextPane();
    private BasicScrollPane jspMessagesArea = new BasicScrollPane();
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;
    private Socket socket = null;
    private String localUserName = "";
    private String remoteUserName = "";
    //private String serverIP = "digitallsh.digitallsh.com.ar";
    private String serverIP = "localhost";
    private int port = SSLConfig.CHWY_SERVER_UNSECURED_PORT;
    private Vector userList = new Vector();
    private BasicScrollPane jspUserList = new BasicScrollPane();
    private JList lstUserList = new JList();
    private boolean connected = false;
    private boolean sendingMessage = true;
    private boolean firstLoad = true;
    private BasicLabel lblUserList = new BasicLabel();
    private BasicButton btnClearMessagesArea = new BasicButton();

    private void jbInit() {
	//serverIP = Proced.getServerIP();
	connected = tryToConnect();
	if (connected) {
	    localUserName = (new JOptionPane()).showInputDialog("Ingrese el nombre de usuario:") + "@" + NetworkConfig.getHostname();
	    lblUserList.setText("User list: ");
	    lblUserList.setBounds(new Rectangle(300, 15, 215, 25));
	    btnClearMessagesArea.setText("Clear");
	    btnClearMessagesArea.setBounds(new Rectangle(90, 375, 70, 25));
	    btnClearMessagesArea.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
						    btnClearMessagesArea_actionPerformed(e);
						}

					    }
	    );
	    try {
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		//System.out.println(inputStream.readUTF());
		clientThread.start();
		sendUserName(localUserName);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else {
	    org.digitall.lib.components.Advisor.messageBox("Can't connect to server", "Error");
	    System.exit(0);
	}
	setTitle("ChWYChatPanel");
	getContentPane().setLayout(null);
	this.setSize(new Dimension(537, 430));
	lblTitle.setBounds(112, 10, 100, 25);
	lblTitle.setText("User: " + localUserName);
	lblTitle.setBounds(new Rectangle(10, 15, 276, 25));
	jspUserList.getViewport().add(lstUserList, null);
	this.getContentPane().add(btnClearMessagesArea, null);
	this.getContentPane().add(lblUserList, null);
	this.getContentPane().add(jspUserList, null);
	getContentPane().add(lblTitle);
	lblWriteMessage.setBounds(10, 328, 180, 25);
	lblWriteMessage.setText("Type your message here:");
	getContentPane().add(lblWriteMessage);
	tfMessage.setBounds(10, 348, 277, 20);
	getContentPane().add(tfMessage);
	getContentPane().add(btnSendMessage);
	btnSendMessage.setBounds(35, 370, 70, 25);
	btnSendMessage.setText("Send");
	btnSendMessage.setBounds(new Rectangle(10, 375, 70, 25));
	btnSendMessage.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnSendMessage_actionPerformed(e);
				      }

				  }
	);
	getContentPane().add(btnCloseWindow);
	btnCloseWindow.setBounds(185, 370, 70, 25);
	btnCloseWindow.setText("Exit");
	btnCloseWindow.setBounds(new Rectangle(455, 375, 70, 25));
	btnCloseWindow.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnCloseWindow_actionPerformed(e);
				      }

				  }
	);
	messagesArea.setEditable(false);
	messagesArea.setBackground(new Color(110, 110, 110));
	messagesArea.setForeground(new Color(70, 250, 50));
	messagesArea.setBorder(new EtchedBorder());
	jspMessagesArea.setViewportView(messagesArea);
	getContentPane().add(jspMessagesArea);
	jspMessagesArea.setBorder(new EtchedBorder());
	jspMessagesArea.setOpaque(true);
	jspMessagesArea.setBounds(new Rectangle(10, 130, 275, 195));
	jspUserList.setBounds(new Rectangle(300, 35, 215, 290));
	jspMessagesArea.setBounds(10, 35, 276, 290);
	addWindowListener(new WindowAdapter() {

		       public void windowClosing(WindowEvent evt) {
			   System.exit(0);
		       }

		   }
	);
	tfMessage.setBounds(new Rectangle(10, 350, 510, 20));
	tfMessage.addKeyListener(new KeyAdapter() {

			      public void keyReleased(KeyEvent e) {
				  if (e.getKeyCode() == e.VK_ENTER) {
				      enterPressed();
				  }
			      }

			  }
	);
	//setSize(303, 430);
	setLocation(250, 90);
	show();
    }

    public void addUser(String userName) {
	//userList.addElement(userName);
    }

    private void retrieveUserList(String _userList) {
	String[] list = _userList.split(ChWYConfig.USERLISTITEM);
	Vector _tempUserList = new Vector();
	for (int i = 1; i < list.length; i++) {
	    System.out.println("User [" + i + "]: " + list[i]);
	    _tempUserList.addElement(list[i]);
	}
	if (!firstLoad) {
	    for (int i = 0; i < userList.size(); i++) {
		boolean found = false;
		for (int j = 0; j < _tempUserList.size(); j++) {
		    if (userList.elementAt(i).toString().equals(_tempUserList.elementAt(j).toString())) {
			found = true;
		    }
		}
		if (!found) {
		    //Advisor.messagePopupWindow("User " + userList.elementAt(i).toString() + " is offline", "");
		    this.show();
		}
	    }
	    for (int i = 0; i < _tempUserList.size(); i++) {
		boolean found = false;
		for (int j = 0; j < userList.size(); j++) {
		    if (userList.elementAt(j).toString().equals(_tempUserList.elementAt(i).toString())) {
			found = true;
		    }
		}
		if (!found) {
		    //Advisor.messagePopupWindow("User " + _tempUserList.elementAt(i).toString() + " is online", "");
		    this.show();
		}
	    }
	} else {
	    firstLoad = false;
	}
	userList = _tempUserList;
	lstUserList.setListData(userList);
    }

    private void retrieveUserList() {
	try {
	    //            writeOut(ChWYConfig.RTRVUSERLIST);
	    String data = readIn();
	    if (data.equals(ChWYConfig.STARTTRANSFER)) {
		System.out.println("Retrieving user list");
		System.out.println(data);
		userList.removeAllElements();
		data = readIn();
		while (!data.equals(ChWYConfig.ENDTRANSFER)) {
		    userList.addElement(new String(data));
		    System.out.println("Added user " + data + " to list");
		    data = readIn();
		}
		lstUserList.setListData(userList);
		System.out.println("User list retrieved");
	    }
	} catch (IOException e) {
	    // TODO
	    e.printStackTrace();
	}
    }

    private void exitForm(WindowEvent evt) {
	System.exit(0);
    }

    private void enterPressed() {
	sendMessage();
    }

    private boolean sendMessage(String _message) {
	String answer = "";
	int status = 0;
	//(0: inicial; 1: from ok; 2: to ok; 3: msg ok);
	int tries = 0;
	boolean messageSent = false;
	try {
	    //writeOut(ChWYConfig.MESSAGE_ORIG + localUserName + ";" + ChWYConfig.MESSAGE_DEST + remoteUserName + ";" + ChWYConfig.MESSAGE_STRING + _message + ";");
	    writeOut(ChWYConfig.MESSAGE_ORIG + localUserName + ";" + ChWYConfig.ORGANIZATIONNAME + ";" + ChWYConfig.MESSAGE_DEST + remoteUserName + ";" + ChWYConfig.MESSAGE_STRING + _message + ";");
            
	    newMessage("Message to ", remoteUserName, _message);
	} catch (IOException e) {
	    org.digitall.lib.components.Advisor.messageBox("Error al enviar mensaje", "Error");
	    e.printStackTrace();
	}
	return messageSent;
    }

    private Socket createConnection() throws IOException {
	try {
	    serverIP = InetAddress.getLocalHost().getHostAddress().toString();
	} catch (UnknownHostException e) {
	    e.printStackTrace();   
	}
	return socket = new Socket(serverIP, port);
	//return socket = new Socket("localhost", port);
	//SOLICITAR NICK?
	//System.out.print("Introducir Nick: ");
    }

    public boolean tryToConnect() {
	int tries = 0;
	boolean connected = false;
	while (tries < 10 && !connected) {
	    if (socket == null) {
		try {
		    System.out.println("Connecting...");
		    socket = createConnection();
		    System.out.println("Socket: " + socket);
		    System.out.println("Connected!");
		    connected = true;
		} catch (IOException x) {
		    tries++;
		    x.printStackTrace();
		    System.out.println("Fallo en el Intento de conexión número: " + tries);
		    socket = null;
		    connected = false;
		}
	    } else {
		connected = true;
	    }
	}
	return connected;
    }

    public static void main(String[] args) throws IOException {
	ChWYLocalChatPanel chatPanel = new ChWYLocalChatPanel("prueba" + Math.random());
	//chatPanel.jbInit();
    }

    public ChWYLocalChatPanel(String _localUserName) {
	localUserName = _localUserName;
	jbInit();
    }

    public void newMessage(String _prefix, String _user, String _message) {
	messagesArea.setText(messagesArea.getText() + "\n" + _prefix + _user + ": " + _message);
	/*if (!isVisible()) {
            lblStatusBar.newUnreadMessage(_user, _message, this);
        }*/
	//setVisible(true);
	//show();
    }

    public void clearMessagesArea() {
	messagesArea.setText("");
    }

    private void btnCloseWindow_actionPerformed(ActionEvent e) {
	System.exit(0);
    }

    private void btnSendMessage_actionPerformed(ActionEvent e) {
	sendMessage();
    }

    private void sendMessage() {
	if (lstUserList.getSelectedIndex() != -1 && tfMessage.getText().trim().length() > 0) {
	    for (int i = 0; i < lstUserList.getSelectedIndices().length; i++) {
		String mensaje = tfMessage.getText();
		remoteUserName = userList.elementAt(lstUserList.getSelectedIndices()[i]).toString();
		sendMessage(mensaje);
		sendingMessage = true;
	    }
	    tfMessage.setText("");
	} else {
	    org.digitall.lib.components.Advisor.messageBox("<html><p align=center>Error: No users selected<br>You must select at least one user from the User list</p></html>", "Error");
	}
    }

    private void writeOut(String _command) throws IOException {
	outputStream.writeUTF(_command + "");
    }

    /**2010-03-15(moraless)*/
    private void sendUserName(String _userName) throws IOException {
	////writeOut(ChWYConfig.USERNAME + _userName);
        SystemInformation informacion = new SystemInformation();
        writeOut(ChWYConfig.USERNAME + _userName+ ";" + ChWYConfig.ORGANIZATIONNAME + ";"+informacion.getParametros());
    }
    private Thread clientThread = new Thread() {

	    public void run() {
		String command;
		int status = 0;
		//(0: inicial; 1: from ok; 2: to ok; 3: msg ok);
		//userName = tryToGetUserName();
		String origUser = "";
		String message = "";
		//retrieveUserList();
		while (connected) {
		    try {
			System.out.println("Client is waiting messages:");
			command = inputStream.readUTF();
			System.out.print("Command received: " + command);
			/*                        switch (status) {
                        case 0 :
                            if (command.startsWith(ChWYConfig.MESSAGE_ORIG)) {
                                status = 2;
                                origUser = command.replaceFirst(ChWYConfig.MESSAGE_ORIG, "");
                                outputStream.writeUTF(ChWYConfig.MESSAGE_ORIG + ChWYConfig.DATA_ACCEPTED);
                            } else if (command.equals(ChWYConfig.RTRVUSERLIST + ChWYConfig.DATA_ACCEPTED)) {
                                retrieveUserList();
                            }
                            break;
                        case 2 :
                            if (command.startsWith(ChWYConfig.MESSAGE_STRING)) {
                                status = 3;
                                message = command.replaceAll(ChWYConfig.MESSAGE_STRING, "");
                                outputStream.writeUTF(ChWYConfig.MESSAGE_STRING + ChWYConfig.DATA_ACCEPTED);
                                System.out.println("New message received: " + message);
                                newMessage(message);
                                status = 0;
                            }
                            break;
                        default :
                            status = 0;
                            origUser = "";
                            message = "";
                            break;
                        }*/
			if (command.startsWith(ChWYConfig.MESSAGE_ORIG)) {
			    //NEW MESSAGE RECEIVED!!!
			    command = command.replaceFirst(ChWYConfig.MESSAGE_ORIG, "");
			    origUser = command.substring(0, command.indexOf(";" + ChWYConfig.MESSAGE_STRING));
			    command = command.substring(command.indexOf(ChWYConfig.MESSAGE_STRING));
			    message = command.replaceAll(ChWYConfig.MESSAGE_STRING, "");
			    //Advisor.messagePopupWindow("<html>" + origUser + " dice:<br>" + message + "</html>", "");
			    show();
			    newMessage("Message from ", origUser, message);
			    //outputStream.writeUTF(ChWYConfig.MESSAGE_ORIG + ChWYConfig.DATA_ACCEPTED);
			} else if (command.startsWith(ChWYConfig.USERLIST)) {
			    retrieveUserList(command.replaceFirst(ChWYConfig.USERLIST, ""));
			}
		    } catch (IOException e) {
			System.out.println("Connection closed");
			connected = false;
			break;
		    }
		}
	    }

	}
    ;

    private String readIn() throws IOException {
	return inputStream.readUTF();
    }
    /*    private Thread retrieveThread = new Thread() {
            public void run() {
                while (connected) {
                    try {
                        retrieveUserList();
                        sleep(15000);
                    } catch (InterruptedException e) {
                        // TODO
                        e.printStackTrace();
                    }
                }
            }
        }
    ;*/

    public boolean isConnected() {
	return connected;
    }

    public int getUsersOnline() {
	return userList.size();
    }

    private void btnClearMessagesArea_actionPerformed(ActionEvent e) {
	messagesArea.setText("");
	tfMessage.setText("");
    }

    public void show() {
	this.setModal(true);
	super.show();
	this.setModal(false);
    }

}
