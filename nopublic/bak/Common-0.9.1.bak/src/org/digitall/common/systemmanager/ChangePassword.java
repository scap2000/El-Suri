package org.digitall.common.systemmanager;

import java.awt.Dimension;

import java.awt.Rectangle;

import javax.swing.JDialog;

import org.digitall.common.systemmanager.PanelChangePassword;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicDialog;

//

public class ChangePassword extends BasicDialog {

    private PanelChangePassword panelPass;
    private String userName = "";
    final String TEXTO = "Systems";

    public ChangePassword(String _userName) {
	try {
	    userName = _userName;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setTitle("Cambio de clave");
	this.setBounds(new Rectangle(0, 0, 320, 140));
	panelPass = new PanelChangePassword(this, userName);
	this.getContentPane().add(panelPass, null);
	panelPass.setBounds(0, 0, 312, 124);
	ComponentsManager.centerWindow(this);
    }

}
