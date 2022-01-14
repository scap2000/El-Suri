package org.digitall.common.legalprocs.manager;

import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicDialog;

//

public class Contacts extends BasicDialog {

    private ContactsPanel contactsPanel;
    private int color = 0;
    private String appName = "";

    public Contacts(String _appName, int _color) {
	try {
	    appName = _appName;
	    color = _color;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(413, 358));
	contactsPanel = new ContactsPanel(this, appName, color);
	this.getContentPane().setLayout(null);
	this.getContentPane().add(contactsPanel);
	contactsPanel.setBounds(0, 0, 408, 334);
	this.setTitle("Contactos");
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
