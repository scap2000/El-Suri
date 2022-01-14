package org.digitall.apps.chwy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.InetAddress;
import java.net.Socket;

import java.net.UnknownHostException;

import java.util.Vector;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import org.digitall.common.digitalk.ChWYConfig;
import org.digitall.common.digitalk.SystemInformation;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.StatusBar;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.network.NetworkConfig;
import org.digitall.lib.ssl.SSLConfig;

//

public class ChWYChatPanel extends BasicPrimitivePanel {

    //SE DEBE TRANSFORMAR EN BasicInternalFrame o BasicPanel
    private BasicLabel lblTitle = new BasicLabel();
    private BasicLabel lblWriteMessage = new BasicLabel();
    private AssignButton btnSendMessage = new AssignButton();
    //private BasicButton btnCloseWindow = new BasicButton();
    private BasicTextField tfMessage = new BasicTextField();
    private JTextPane messagesArea = new JTextPane();
    private BasicScrollPane jspMessagesArea = new BasicScrollPane();
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;
    private Socket socket = null;
    private String localUserName = "";
    private String remoteUserName = "";
    private String serverIP = "";
    private int port = SSLConfig.CHWY_CLIENT_PORT;
    private Vector userList = new Vector();
    private BasicScrollPane jspUserList = new BasicScrollPane();
    private JList lstUserList = new JList();
    private boolean connected = false;
    private boolean sendingMessage = true;
    private StatusBar lblStatusBar;
    private boolean firstLoad = true;
    private BasicLabel lblUserList = new BasicLabel();
    private DeleteButton btnClearMessagesArea = new DeleteButton();
    private CloseButton btnClearMessage = new CloseButton();
    private BasicPanel jpChat = new BasicPanel();
    private static long lastTry = System.currentTimeMillis();
    private static Timer connectionChecker = null;
    private static Timer connectionQueue = null;
    public static int timeout = 10;

    private void jbInit() {
	try {
	    serverIP = InetAddress.getLocalHost().getHostAddress().toString();
	} catch (UnknownHostException e) {
	    e.printStackTrace();   
	}
	setTitle("ChWYChatPanel");
	lblStatusBar = Environment.jpStatusBar;
	lblStatusBar.setUser(localUserName);
	lblUserList.setText("Usuarios conectados: ");
	lblUserList.setBounds(new Rectangle(300, 15, 215, 25));
	btnClearMessagesArea.setText("Limpiar lista de mensajes");
	btnClearMessage.setText("Cancelar mensaje");
	btnClearMessagesArea.setBounds(new Rectangle(400, 370, 120, 60));
	btnClearMessage.setBounds(new Rectangle(80, 370, 120, 60));
	btnClearMessage.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnClearMessage_actionPerformed(e);
				       }

				   }
	);
	btnClearMessagesArea.addActionListener(new ActionListener() {

					    public void actionPerformed(ActionEvent e) {
						btnClearMessagesArea_actionPerformed(e);
					    }

					}
	);
	jpChat.setLayout(null);
	add(jpChat, BorderLayout.CENTER);
	this.setSize(new Dimension(540, 440));
	this.setPreferredSize(new Dimension(540, 440));
	this.addFocusListener(new FocusAdapter() {

			   public void focusGained(FocusEvent e) {
			       this_focusGained(e);
			   }

		       }
	);
	lblTitle.setBounds(112, 10, 100, 25);
	lblTitle.setText("Usuario: " + localUserName);
	lblTitle.setBounds(new Rectangle(10, 15, 276, 25));
	jspUserList.getViewport().add(lstUserList, null);
	jpChat.add(btnClearMessagesArea, null);
	jpChat.add(btnClearMessage, null);
	jpChat.add(lblUserList, null);
	jpChat.add(jspUserList, null);
	jpChat.add(lblTitle);
	lblWriteMessage.setBounds(10, 328, 180, 25);
	lblWriteMessage.setText("Escriba su mensaje aquí:");
	jpChat.add(lblWriteMessage);
	tfMessage.setBounds(10, 348, 277, 20);
	jpChat.add(tfMessage);
	jpChat.add(btnSendMessage);
	btnSendMessage.setToolTipText("Enviar mensaje");
	btnSendMessage.setBounds(35, 370, 70, 25);
	btnSendMessage.setText("Enviar");
	btnSendMessage.setBounds(new Rectangle(10, 370, 70, 60));
	btnSendMessage.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnSendMessage_actionPerformed(e);
				      }

				  }
	);
	/*jpChat.add(btnCloseWindow);
	btnCloseWindow.setBounds(185, 370, 70, 25);
	btnCloseWindow.setText("Exit");
	btnCloseWindow.setBounds(new Rectangle(450, 375, 70, 25));
	btnCloseWindow.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnCloseWindow_actionPerformed(e);
		    }

		});
	*/
	messagesArea.setEditable(false);
	messagesArea.setBackground(new Color(0, 0, 0));
	messagesArea.setForeground(new Color(255, 255, 255));
	messagesArea.setBorder(new EtchedBorder());
	jspMessagesArea.setViewportView(messagesArea);
	jpChat.add(jspMessagesArea);
	jspMessagesArea.setBorder(new EtchedBorder());
	jspMessagesArea.setOpaque(true);
	jspMessagesArea.setBounds(new Rectangle(10, 130, 275, 195));
	jspUserList.setBounds(new Rectangle(300, 35, 215, 290));
	jspMessagesArea.setBounds(10, 35, 276, 290);
	/*addWindowListener(new WindowAdapter() {

                              public void windowClosing(WindowEvent evt) {
                                  exitForm(evt);
                              }
                          }
        );*/
	tfMessage.setBounds(new Rectangle(10, 350, 510, 20));
	tfMessage.addKeyListener(new KeyAdapter() {

			      public void keyReleased(KeyEvent e) {
				  if (e.getKeyCode() == e.VK_ENTER) {
				      enterPressed();
				  }
			      }

			  }
	);
	addButton(btnSendMessage);
	addButton(btnClearMessage);
	addButton(btnClearMessagesArea);
	startService();
    }

    public void addUser(String userName) {
	//userList.addElement(userName);
    }

    private void retrieveUserList(String _userList) {
	String[] list = _userList.split(ChWYConfig.USERLISTITEM);
	Vector _tempUserList = new Vector();
	for (int i = 1; i < list.length; i++) {
	    //System.out.println("User [" + i + "]: " + list[i]);
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
		    Advisor.messagePopupWindow("User " + userList.elementAt(i).toString() + " is offline", "");
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
		    Advisor.messagePopupWindow("User " + _tempUserList.elementAt(i).toString() + " is online", "");
		}
	    }
	} else {
	    firstLoad = false;
	}
	userList = _tempUserList;
	lstUserList.setListData(userList);
	lblStatusBar.setOnlineUsers(userList.size());
    }

    private void retrieveUserList() {
	try {
	    writeOut(ChWYConfig.RTRVUSERLIST);
	    String data = readIn();
	    if (data.equals(ChWYConfig.STARTTRANSFER)) {
		//System.out.println("Retrieving user list");
		//System.out.println(data);
		userList.removeAllElements();
		data = readIn();
		while (!data.equals(ChWYConfig.ENDTRANSFER)) {
		    userList.addElement(new String(data));
		    //System.out.println("Added user " + data + " to list");
		    data = readIn();
		}
		lstUserList.setListData(userList);
		//System.out.println("User list retrieved");
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

    /**2010-03-17(moraless)*/
    private boolean sendMessage(String _message) {
	String answer = "";
	int status = 0;
	//(0: inicial; 1: from ok; 2: to ok; 3: msg ok);
	int tries = 0;
	boolean messageSent = false;
	try {
	    ////writeOut(ChWYConfig.MESSAGE_ORIG + localUserName + ";" + ChWYConfig.MESSAGE_DEST + remoteUserName + ";" + ChWYConfig.MESSAGE_STRING + _message + ";"); //chwy codigo comentado 
	    writeOut(ChWYConfig.MESSAGE_ORIG + localUserName + ";" + ChWYConfig.ORGANIZATIONNAME + Environment.organization + ";" + ChWYConfig.MESSAGE_DEST + remoteUserName + ";" + ChWYConfig.MESSAGE_STRING + _message + ";");
	    newMessage("Message to ", remoteUserName, _message);
	    /*            System.out.println("Output Message:" + _message);
            while ((status == 0) && (tries < 10)) {
                writeOut(ChWYConfig.MESSAGE_ORIG + localUserName + "\n");
                System.out.println("Waiting for answer:" + _message);
                answer = readIn();
                System.out.println("Answer received:" + _message);
                if (answer.startsWith(ChWYConfig.MESSAGE_ORIG + ChWYConfig.DATA_ACCEPTED)) {
                    tries = 0;
                    status = 1;
                } else {
                    tries++;
                }
            }
            if (status != 1) {
                //ERROR!
                org.digitall.lib.components.Advisor.messageBox("Comando " + ChWYConfig.MESSAGE_ORIG + localUserName + " no aceptado", "Error");
            } else {
                while ((status == 1) && (tries < 10)) {
                    writeOut(ChWYConfig.MESSAGE_DEST + remoteUserName + "\n");
                    System.out.println("SNDMessage is waiting: ");
                    answer = inputStream.readUTF();
                    if (answer.startsWith(ChWYConfig.MESSAGE_DEST + ChWYConfig.DATA_ACCEPTED)) {
                        tries = 0;
                        status = 2;
                    } else {
                        tries++;
                    }
                }
                if (status != 2) {
                    //ERROR!
                    org.digitall.lib.components.Advisor.messageBox("Comando " + ChWYConfig.MESSAGE_DEST + remoteUserName + " no aceptado", "Error");
                } else {
                    while ((status == 2) && (tries < 10)) {
                        writeOut(ChWYConfig.MESSAGE_STRING + _message + "\n");
                        answer = inputStream.readUTF();
                        if (answer.startsWith(ChWYConfig.MESSAGE_DEST + ChWYConfig.DATA_ACCEPTED)) {
                            tries = 0;
                            status = 3;
                        } else {
                            tries++;
                        }
                    }
                    if (status != 3) {
                        //ERROR!
                        org.digitall.lib.components.Advisor.messageBox("Comando " + ChWYConfig.MESSAGE_STRING + " no aceptado", "Error");
                    } else {
                        messageSent = true;
                    }
                }
            }*/
	} catch (IOException e) {
	    Advisor.messageBox("Error al enviar mensaje", "Error");
	    e.printStackTrace();
	}
	return messageSent;
    }

    private Socket createConnection() throws IOException {
	return socket = new Socket(serverIP, port);
	//return socket = new Socket("www.digitallsh.com.ar", SSLConfig.CHWY_SERVER_SECURED_PORT);
	//return socket = new Socket("localhost", port);
    }

    public void disconnect() {
	try {
	    socket.close();
	    //socket = null;
	} catch (IOException e) {
	    // TODO
	    e.printStackTrace();
	}
    }

    public boolean tryToConnect() {
	int tries = 0;
	boolean _connected = false;
	while (tries < 10 && !_connected) {
	    if (socket == null) {
		try {
		    System.out.println("Connecting...");
		    socket = createConnection();
		    //System.out.println("Socket: " + socket);
		    System.out.println("Connected!");
		    _connected = true;
		} catch (IOException x) {
		    tries++;
		    System.out.println("Fallo en el Intento de conexión número: " + tries);
		    //x.printStackTrace();
		    socket = null;
		    _connected = false;
		}
	    } else {
		_connected = true;
	    }
	}
	setConnected(_connected);
	return _connected;
    }
    /*    public static void main(String[] args) throws IOException {
        ChWYChatPanel chatPanel = new ChWYChatPanel("prueba" + Math.random(), new StatusBar());
        //chatPanel.jbInit();
    }
*/

    public ChWYChatPanel(String _localUserName, StatusBar _statusBar) {
	lblStatusBar = _statusBar;
	localUserName = _localUserName + "@" + NetworkConfig.getHostname();
	jbInit();
    }

    public void newMessage(String _prefix, String _user, String _message) {
	messagesArea.setText(messagesArea.getText() + "\n" + _prefix + _user + ": " + _message);
	if (!getParentInternalFrame().isVisible() || getParentInternalFrame().getDesktop() != Environment.getActiveDesktop()) {
	    lblStatusBar.newUnreadMessage(_user, _message, getParentInternalFrame());
	}
	//setVisible(true);
	//show();
    }

    public void clearMessagesArea() {
	messagesArea.setText("");
	
    }
    /*private void btnCloseWindow_actionPerformed(ActionEvent e) {
	//System.exit(0);
	//Avisar al servidor del cierre del chat privado
	//dispose();
	setVisible(false);
	hide();
    }*/

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
	    Advisor.messageBox("<html><p align=center>Error: No users selected<br>You must select at least one user from the User list</p></html>", "Error");
	}
    }

    private void writeOut(String _command) throws IOException {
	outputStream.writeUTF(_command + "");
    }

    /**2010-03-17(moraless)*/
    private void sendUserName(String _userName) throws IOException {
	////writeOut(ChWYConfig.USERNAME + _userName); chwy codigo comentado 
	SystemInformation informacion = new SystemInformation();
        System.out.println("Información a enviar = " + informacion.getParametros());//codigo a borrar
	writeOut(ChWYConfig.USERNAME + _userName+ ";" + ChWYConfig.ORGANIZATIONNAME + Environment.organization + ";"+informacion.getParametros());
    }

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

    private void this_focusGained(FocusEvent e) {
	lblStatusBar.setUnreadMessages(0);
    }

    private void btnClearMessagesArea_actionPerformed(ActionEvent e) {
	messagesArea.setText("");
	tfMessage.setText("");
    }

    private void checkConnection() {
	//simulateDisconnection();
	if (connectionChecker == null) {
	    connectionChecker = new Timer(10000, new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   //retrieveUserList();
					   if (!connected) {
					       //disableAll and start queueconnect
					       setDisconnectedAppearance();
					       queueConnection();
					       connectionChecker.stop();
					   }
				       }

				   }
		);
	}
	if (timeout > 0 && !connectionChecker.isRunning()) {
	    connectionChecker.start();
	}
    }

    private void queueConnection() {
	lastTry = System.currentTimeMillis();
	if (connectionQueue == null) {
	    final int _timeout = timeout * 60 * 1000;
	    connectionQueue = new Timer(10000, new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 if ((System.currentTimeMillis() - lastTry) > _timeout - 550000) {
					     //System.out.println("Closing SQL connection due to timeout");
					     //connectionQueue.stop();
					     if (startService()) {
						 connectionQueue.stop();
					     }
					 } else {
					     System.out.println("Disconnected, ETA: " + (_timeout - (System.currentTimeMillis() - lastTry)) / 1000.0 + " seconds to retry");
					 }
				     }

				 }
		);
	}
	if (timeout > 0 && !connectionQueue.isRunning()) {
	    connectionQueue.start();
	}
    }

    private void simulateDisconnection() {
	Timer disconnectionQueue = new Timer(30000, new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      setConnected(false);
					      socket = null;
					  }

				      }
	    );
	disconnectionQueue.start();
    }

    private void setDisconnectedAppearance() {
	tfMessage.setEnabled(false);
	messagesArea.setEnabled(false);
	jspMessagesArea.setEnabled(false);
	jspUserList.setEnabled(false);
	lstUserList.setEnabled(false);
	lblUserList.setEnabled(false);
	btnClearMessagesArea.setEnabled(false);
	jpChat.setEnabled(false);
    }

    private void setConnectedAppearance() {
	tfMessage.setEnabled(true);
	messagesArea.setEnabled(true);
	jspMessagesArea.setEnabled(true);
	jspUserList.setEnabled(true);
	lstUserList.setEnabled(true);
	lblUserList.setEnabled(true);
	btnClearMessagesArea.setEnabled(true);
	jpChat.setEnabled(true);
    }

    private boolean startService() {
	connected = tryToConnect();
	if (isConnected()) {
	    try {
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		//System.out.println(inputStream.readUTF());
		Thread clientThread = new Thread() {

			public void run() {
			    String command;
			    int status = 0;
			    //(0: inicial; 1: from ok; 2: to ok; 3: msg ok);
			    //userName = tryToGetUserName();
			    String origUser = "";
			    String message = "";
			    //retrieveUserList();
			    while (isConnected()) {
				try {
				    //System.out.println("Client is waiting messages:");
				    command = inputStream.readUTF();
				    //System.out.print("Command received: " + command);
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
					newMessage("Message from ", origUser, message);
					//outputStream.writeUTF(ChWYConfig.MESSAGE_ORIG + ChWYConfig.DATA_ACCEPTED);
				    } else if (command.startsWith(ChWYConfig.USERLIST)) {
					retrieveUserList(command.replaceFirst(ChWYConfig.USERLIST, ""));
				    }
				} catch (IOException e) {
				    //System.out.println("Connection closed");
				    setConnected(false);
				    break;
				}
			    }
			}

		    }
		;
		clientThread.start();
		sendUserName(localUserName);
		setConnectedAppearance();
		checkConnection();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else {
	    queueConnection();
	}
	return connected;
    }

    private void btnClearMessage_actionPerformed(ActionEvent e) {
	tfMessage.setText("");
    }

    private void setConnected(boolean _connected) {
	connected = _connected;
	lblStatusBar.setChwyServiceUp(_connected);
    }

}
