package org.digitall.apps.systemmanager;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.digitall.lib.components.basic.BasicDialog;

//

public class UserToGroup extends BasicDialog {

    private PanelUserToGroup panelUserToGroup;
    private String grupo = "";

    public UserToGroup(String _grupo) {
	try {
	    grupo = _grupo;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	panelUserToGroup = new PanelUserToGroup(this, grupo);
	this.setSize(new Dimension(354, 316));
	this.getContentPane().setLayout(null);
	this.setTitle("Add an user to a group");
	panelUserToGroup.setBounds(new Rectangle(5, 5, 340, 245));
	panelUserToGroup.setBounds(new Rectangle(10, 10, 325, 250));
	panelUserToGroup.setSize(new Dimension(326, 270));
	this.getContentPane().add(panelUserToGroup, null);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
