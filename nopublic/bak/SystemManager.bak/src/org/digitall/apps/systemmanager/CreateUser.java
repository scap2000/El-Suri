package org.digitall.apps.systemmanager;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.digitall.lib.components.basic.BasicDialog;

//

public class CreateUser extends BasicDialog {

    private PanelCreateUser panelUser;
    private String group = "";
    final String TEXTO = "Create User";

    public CreateUser(String _group) {
	try {
	    group = _group;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	panelUser = new PanelCreateUser(this);
	this.setSize(new Dimension(399, 220));
	this.getContentPane().setLayout(null);
	this.setTitle("Create User");
	panelUser.setBounds(new Rectangle(1, 35, 392, 158));
	panelUser.setBounds(new Rectangle(1, 35, 392, 158));
	this.getContentPane().add(panelUser, null);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
	panelUser.setGroup(group);
    }

}
