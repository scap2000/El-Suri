package org.digitall.apps.projecttask.interfaces;

import java.awt.Dimension;
import java.awt.Frame;

import org.digitall.lib.org.User;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicDialog;

public class TaskByEmployeeJDialog extends BasicDialog {
    
    private User user;
    private String namePerson;
    private ComponentsManager compManager;
    private TaskByEmployeeMgmt taskByEmployeeMgmtPanel;

    public TaskByEmployeeJDialog() {
	this(null, "", false);
    }

    public TaskByEmployeeJDialog(String _namePerson, User _user) {
	try {
	    namePerson = _namePerson;
	    user = _user;
	    
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public TaskByEmployeeJDialog(Frame parent, String title, boolean modal) {
	super(parent, title, modal);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	taskByEmployeeMgmtPanel = new TaskByEmployeeMgmt(this, namePerson, user);
	this.setSize(new Dimension(645, 455));
	this.getContentPane().setLayout( null );
	this.getContentPane().add(taskByEmployeeMgmtPanel);
	this.setTitle("AdministraciÃ³n Tareas");
	compManager.centerWindow(this);
    }

}
