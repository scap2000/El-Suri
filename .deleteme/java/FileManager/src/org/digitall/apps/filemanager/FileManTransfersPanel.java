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
 * FileManTransfersPanel.java
 *
 * */
package org.digitall.apps.filemanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

import org.digitall.common.filemanager.LocalFile;
import org.digitall.common.filemanager.RemoteFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.StatusBar;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.network.NetworkConfig;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.ssl.SSLConfig;

public class FileManTransfersPanel extends BasicInternalFrame {
    //SE DEBE TRANSFORMAR EN BasicInternalFrame o BasicPanel
    private JTextPane messagesArea = new JTextPane();
    private Socket serverSocket = null;
    private String localUserName = "";
    private String serverIP = "";
    private int port = SSLConfig.FM_CLIENT_PORT;
    private StatusBar lblStatusBar;
    private JProgressBar pbTransferStatus = new JProgressBar();
    private JSplitPane fileSplitPanel = new JSplitPane();
    private RemoteFileTreePanel remoteFileTree = new RemoteFileTreePanel(this);
    private LocalFileTreePanel localFileTree = new LocalFileTreePanel(this);
    private CloseButton btnCloseWindow = new CloseButton();
    private FileManToolBar fmToolBar = new FileManToolBar();

    private void jbInit() {
	serverIP = NetworkConfig.getServerIP();
	serverIP = "localhost";
	setTitle("FileManTransferSuite");
	lblStatusBar.setUser(localUserName);
	pbTransferStatus.setBounds(new Rectangle(85, 545, 365, 15));
	fileSplitPanel.setBounds(new Rectangle(10, 45, 515, 430));
	fileSplitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
	//fileSplitPanel.setDividerLocation(215);
	fileSplitPanel.setDividerLocation(0.5);
	fileSplitPanel.setOneTouchExpandable(true);
	fileSplitPanel.setDividerSize(12);
	fileSplitPanel.setResizeWeight(0.5);
	btnCloseWindow.setBounds(new Rectangle(485, 545, 40, 25));
	btnCloseWindow.setSize(new Dimension(40, 25));
	btnCloseWindow.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnCloseWindow_actionPerformed(e);
		    }

		});
	getContentPane().setLayout(null);
	this.setSize(new Dimension(537, 603));
	fileSplitPanel.add(remoteFileTree, JSplitPane.RIGHT);
	fileSplitPanel.add(localFileTree, JSplitPane.LEFT);
	this.getContentPane().add(btnCloseWindow, null);
	this.getContentPane().add(fileSplitPanel, null);
	this.getContentPane().add(pbTransferStatus, null);
	messagesArea.setEditable(false);
	messagesArea.setBackground(new Color(110, 110, 110));
	messagesArea.setForeground(new Color(70, 250, 50));
	messagesArea.setBorder(new EtchedBorder());
	this.getContentPane().add(fmToolBar, BorderLayout.NORTH);
    }

    private void enterPressed() {
	//TODO
	System.out.println("GO");
    }

    protected Socket createConnection() throws IOException {
	return new Socket(serverIP, port);
    }

    public void disconnect() {
	try {
	    serverSocket.close();
	    //socket = null;
	} catch (IOException e) {
	    // TODO
	    e.printStackTrace();
	}
    }

    public FileManTransfersPanel(String _localUserName, StatusBar _statusBar) {
	lblStatusBar = _statusBar;
	localUserName = _localUserName;
	jbInit();
    }

    private void btnCloseWindow_actionPerformed(ActionEvent e) {
	setVisible(false);
	hide();
    }

    protected void putFile(LocalFile _file) {
	try {
	    Socket fileSocket = createConnection();
	    //Upload to remote host
	    //SQL INITIATE TRANSFER
	    String params = FileManTransferFileThread.PUT + ",'" + _file.getName() + "',''," + _file.getDestinationDir();
	    _file.setIDTransfer(LibSQL.getInt("admin.initiatetransfer", params));
	    System.out.println("ID: " + _file.getIDTransfer());
	    if (_file.getIDTransfer() != -1) {
		ObjectOutputStream outputStream = new ObjectOutputStream(fileSocket.getOutputStream());
		outputStream.writeObject(_file);
		FileManTransferFileThread thread = new FileManTransferFileThread(fileSocket, _file, pbTransferStatus);
		if (thread.prepareTransfer()) {
		    thread.start();
		} else {
		    Advisor.messageBox("Error de:\n    - Lectura del archivo local o de\n    - Escritura en el servidor remoto", "Error");
		}
	    }
	} catch (IOException e) {
	    Advisor.messageBox("Excepciï¿½n!:\n    - Lectura del archivo local o de\n    - Escritura en el servidor remoto", "Error");
	    e.printStackTrace();
	}
    }

    protected void getFile(RemoteFile _file) {
	try {
	    Socket fileSocket = createConnection();
	    //Download from remote host
	    String params = FileManTransferFileThread.GET + ",'" + _file.getName() + "','" + _file.getDestinationDir() + "',-1";
	    System.out.println("admin.initiatetransfer(" + params + ")");
	    _file.setIDTransfer(LibSQL.getInt("admin.initiatetransfer", params));
	    ObjectOutputStream outputStream = new ObjectOutputStream(fileSocket.getOutputStream());
	    outputStream.writeObject(_file);
	    FileManTransferFileThread thread = new FileManTransferFileThread(fileSocket, _file, pbTransferStatus);
	    if (thread.prepareTransfer()) {
		thread.start();
	    } else {
		Advisor.messageBox("Error de:\n    - Lectura del archivo remoto o de\n    - Escritura en el disco local", "Error");
	    }
	} catch (IOException e) {
	    Advisor.messageBox("Excepciï¿½n!:\n    - Lectura del archivo remoto o de\n    - Escritura en el disco local", "Error");
	    e.printStackTrace();
	}
    }

    public File[] openFile() {
	JFileChooser selector = new JFileChooser();
	selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
	selector.setMultiSelectionEnabled(true);
	//selector.setFileFilter(new ClassFilter());
	int opcion = selector.showOpenDialog(null);
	if (opcion == JFileChooser.APPROVE_OPTION) {
	    return selector.getSelectedFiles();
	} else {
	    return null;
	}
    }

    private void btnSendMessage_actionPerformed(ActionEvent e) {
    }

    public void setDragSource(RemoteFileList _list) {
	localFileTree.setDragSource(_list);
	remoteFileTree.setDragSource(null);
    }

    public void setDragSource(LocalFileList _list) {
	remoteFileTree.setDragSource(_list);
	localFileTree.setDragSource(null);
    }

}
