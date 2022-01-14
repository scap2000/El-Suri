package org.digitall.apps.systemmanager;

import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicDialog;

//

public class FindUser extends BasicDialog {

    private PanelFindUser panelUser;
    final String TEXTO = "Find User";

    public FindUser() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	panelUser = new PanelFindUser(this);
	this.setSize(new Dimension(457, 371));
	this.getContentPane().setLayout(null);
	this.setTitle("Find User");
	this.getContentPane().add(panelUser, null);
	panelUser.setBounds(0, 35, 453, 311);
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
    }

}
