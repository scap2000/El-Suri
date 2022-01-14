package org.digitall.common.legalprocs.manager;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicDialog;

//

public class ApplicationConfig extends BasicDialog {

    private ApplicationConfigPanel appCfgPanel;

    public ApplicationConfig(BasicDialog _parent) {
	super(_parent);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ApplicationConfig(JFrame _parent) {
	super(_parent);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ApplicationConfig() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	appCfgPanel = new ApplicationConfigPanel(this);
	this.setSize(new Dimension(629, 486));
	this.setTitle("Configuracion de Trámites");
	this.getContentPane().add(appCfgPanel, null);
	this.getContentPane().setLayout(null);
	appCfgPanel.setBounds(new Rectangle(0, 35, 625, 427));
	ComponentsManager.centerWindow(this);
    }

}
