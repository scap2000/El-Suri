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
 * StatusBar.java
 *
 * */
package org.digitall.lib.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;

//

public class StatusBar extends BasicContainerPanel {

    private String user = "";
    private int onlineUsers = 0;
    private boolean chwyServiceUp = false;
    private int unreadMessages = 0;
    private BasicLabel lblHelp = new BasicLabel();
    private BasicLabel lblUsers = new BasicLabel();
    private JProgressBar jProgress = new JProgressBar();
    private BasicLabel lblConnection = new BasicLabel();
    private BasicLabel lblAction = new BasicLabel();
    private BasicPanel jpHelp = new BasicPanel();
    private BasicPanel jpUsers = new BasicPanel();
    private BasicPanel jpProgressBar = new BasicPanel();
    private GridLayout gridLayout2 = new GridLayout();
    private BasicPanel jpConnection = new BasicPanel();
    private BasicPanel jpCurrent = new BasicPanel();
    private GridLayout gridLayout3 = new GridLayout();
    private GridLayout gridLayout4 = new GridLayout();
    private GridLayout gridLayout5 = new GridLayout();
    private GridLayout gridLayout6 = new GridLayout();
    private GridBagLayout gridBagLayout = new GridBagLayout();

    public StatusBar() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(gridBagLayout);
	this.setBounds(new Rectangle(10, 10, 400, 22));
	this.setMaximumSize(new Dimension(32767, 22));
	this.setMinimumSize(new Dimension(40, 22));
	this.setSize(new Dimension(502, 30));
	lblHelp.setText("Help: ");
	lblUsers.setText("Users: ");
	lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
	lblConnection.setHorizontalAlignment(SwingConstants.CENTER);
	lblAction.setText("Action");
	lblAction.setHorizontalAlignment(SwingConstants.RIGHT);
	jpHelp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jpHelp.setLayout(gridLayout3);
	jpUsers.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jpUsers.setLayout(gridLayout6);
	jpProgressBar.setLayout(gridLayout2);
	jpProgressBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	gridLayout2.setVgap(5);
	gridLayout2.setHgap(5);
	gridLayout2.setColumns(1);
	jpConnection.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jpConnection.setLayout(gridLayout5);
	jpCurrent.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jpCurrent.setLayout(gridLayout4);
	jpHelp.add(lblHelp, null);
	this.add(jpHelp, new GridBagConstraints(0, 0, 1, 1, 2.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 1), 4, 0));
	jpUsers.add(lblUsers, null);
	this.add(jpUsers, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0));
	jpConnection.add(lblConnection, null);
	this.add(jpConnection, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), 0, 0));
	jpProgressBar.add(jProgress, null);
	this.add(jpProgressBar, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 1, 0, 1), -60, 0));
	jpCurrent.add(lblAction, null);
	this.add(jpCurrent, new GridBagConstraints(4, 0, 1, 1, 1.5, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 1, 0, 0), 0, 0));
	setConnection();
    }

    public void setUser(String _user) {
	user = _user;
	setStatus();
    }

    public String getUser() {
	return user;
    }

    public void setOnlineUsers(int _onlineUsers) {
	onlineUsers = _onlineUsers;
	setStatus();
    }

    public int getOnlineUsers() {
	return onlineUsers;
    }

    public void setChwyServiceUp(boolean _chwyServiceUp) {
	chwyServiceUp = _chwyServiceUp;
	setStatus();
    }

    public boolean isChwyServiceUp() {
	return chwyServiceUp;
    }

    public void newUnreadMessage(String _origUser, String _message, BasicInternalFrame _panel) {
	Advisor.messagePopupWindowIFrame("<html>" + _origUser + " says:<br>" + _message + "</html>", _panel);
	unreadMessages++;
	setStatus();
    }

    public void setUnreadMessages(int _unreadMessages) {
	unreadMessages = _unreadMessages;
	setStatus();
    }

    public int getUnreadMessages() {
	return unreadMessages;
    }

    private void setStatus() {
	String status = "";
	if (chwyServiceUp) {
	    status = "User: " + user + " - Online user(s): " + onlineUsers + " - " + unreadMessages + " unread message(s)";
	    //setText("User: " + user + " - Online user(s): " + onlineUsers + " - " + unreadMessages + " unread message(s)");
	} else {
	    status = "User: " + user + " - ChatWithYou service not available";
	    //setText("User: " + user + " - ChatWithYou service not available");
	}
	lblUsers.setText(status);
	lblUsers.setToolTipText(status);
    }

    public void setConnection() {
	if (LibSQL.getConnectionLatency() > 2500) {
	    lblConnection.setIcon(IconTypes.slowConnection);
	    lblConnection.setToolTipText("Slow connection detected!");
	} else {
	    lblConnection.setIcon(IconTypes.fastConnection);
	    lblConnection.setToolTipText("Fast connection detected!");
	}
    }

    public void setHelp(String _help) {
	lblHelp.setText(_help);
    }

    public void setAction(String _action) {
	lblAction.setText(_action);
    }

    public void setIndeterminate(boolean _indeterminate) {
	jProgress.setIndeterminate(_indeterminate);
    }

    public void setValue(int _value) {
	jProgress.setValue(_value);
    }

}
