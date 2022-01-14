package org.digitall.apps.projecttask.interfaces;

import java.awt.Dimension;
import java.awt.Frame;

import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicDialog;

public class ReportByUserJDialog extends BasicDialog {

    ReportByUser reportByUser;
    private ComponentsManager compManager;
    
    public ReportByUserJDialog() {
	this(null, "", false);
    }

    public ReportByUserJDialog(Frame parent, String title, boolean modal) {
	super(parent, title, modal);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	reportByUser =  new ReportByUser(this);
	this.setSize( new Dimension( 665, 385 ) );
	this.getContentPane().setLayout( null );
	this.getContentPane().add(reportByUser);
	this.setTitle("Reportes por Usuarios");
	compManager.centerWindow(this);
	
    }

}
