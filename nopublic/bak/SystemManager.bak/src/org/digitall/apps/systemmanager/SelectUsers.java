package org.digitall.apps.systemmanager;

import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicDialog;

//

public class SelectUsers extends BasicDialog {

    private PanelSelectUsers panelSelectUsers;
    String grupo = "";
    final String TEXTO = "Select Users";

    public SelectUsers(String _grupo) {
	try {
	    grupo = _grupo;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(330, 318));
	panelSelectUsers = new PanelSelectUsers(this, grupo);
	this.getContentPane().setLayout(null);
	this.setTitle("Select Users");
	this.getContentPane().add(panelSelectUsers, null);
	panelSelectUsers.setBounds(0, 35, 326, 258);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
