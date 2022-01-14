package org.digitall.common.systemmanager.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;

import javax.swing.JEditorPane;
import javax.swing.JPanel;

import javax.swing.Timer;

import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.network.NetworkConfig;
import org.digitall.lib.sql.LibSQL;

public class SystemInfo extends BasicPrimitivePanel {

    private TFInput tfHost = new TFInput(DataTypes.STRING, "Server", false);
    private TFInput tfDatabase = new TFInput(DataTypes.STRING, "DBName", false);
    private TFInput tfUser = new TFInput(DataTypes.STRING, "User", false);
    private TFInput tfVersion = new TFInput(DataTypes.STRING, "Versión del Sistema", false);
    private JArea jaAbout = new JArea();
    private JPanel content = new JPanel();
    private GridLayout gridLayout1 = new GridLayout();

    public SystemInfo() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(524, 284));
	content.add(tfHost, null);
	content.add(tfDatabase, null);
	content.add(tfUser, null);
	content.add(tfVersion, null);
	jaAbout.setContentType("text/html");
	//jaAbout.setContentType("text/java");
	jaAbout.setPage(Environment.mainClass.getResource("license.txt"));
	jaAbout.setEditable(false);
	//jaAbout.setText("<html><p align=center><b><u>DIGITALL Desktop</u></b><br><p align=center>DIGITALL Desktop, los escritorios, el GIS y sus aplicaciones son propiedad de <b>DIGITALL S.H.</b><br>Salta, Argentina (2007-2010)</p></html>");
	jaAbout.setBounds(new Rectangle(0, 195, 410, 40));
	jaAbout.setPreferredSize(new Dimension(8, 75));
	content.setLayout(gridLayout1);
	content.setSize(new Dimension(410, 160));
	content.setMaximumSize(new Dimension(32767, 160));
	content.setMinimumSize(new Dimension(1, 160));
	content.setPreferredSize(new Dimension(1, 160));
	content.setBounds(new Rectangle(0, 0, 410, 160));
	gridLayout1.setColumns(1);
	gridLayout1.setRows(4);
	this.add(content, BorderLayout.NORTH);
	this.add(jaAbout, BorderLayout.CENTER);
	tfHost.setEditable(false);
	tfDatabase.setEditable(false);
	tfUser.setEditable(false);
	tfVersion.setEditable(false);
	refresh();
	Timer _refreshTimer = new Timer(10000, new ActionListener() {
	
					 public void actionPerformed(ActionEvent e) {
					    refresh();
					 }
					 
				     }
		);
	_refreshTimer.start();
    }

    public void refresh() {
	tfHost.setValue(NetworkConfig.getServerIP());
	tfDatabase.setValue(LibSQL.getDataBase());
	tfUser.setValue(Environment.sessionUser);
	tfVersion.setValue(Environment.SYSTEM_VERSION + ", corriendo en Java " + System.getProperty("java.runtime.version"));
    }

    
}
