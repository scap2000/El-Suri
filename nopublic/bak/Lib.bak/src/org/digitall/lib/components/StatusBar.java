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
