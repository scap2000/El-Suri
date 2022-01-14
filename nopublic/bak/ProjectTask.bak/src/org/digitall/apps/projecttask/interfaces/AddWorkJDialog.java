package org.digitall.apps.projecttask.interfaces;

import java.awt.Dimension;

import org.digitall.apps.projecttask.classes.Task;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicDialog;

public class AddWorkJDialog extends BasicDialog {
    private int taskType;
    private Task taskClass;
    private TaskMgmt taskMgmtPanel;
    private ComponentsManager compManager;
    
    public AddWorkJDialog(int _taskType, Task _taskClass) {
	try {
	    taskType = _taskType;
	    taskClass = _taskClass;
	    
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	taskMgmtPanel = new TaskMgmt(this, taskType, taskClass);
	this.getContentPane().setLayout( null );
	this.setSize( new Dimension(310, 245));                   
	this.getContentPane().add(taskMgmtPanel);
	if ( taskType != 0  ) {
	    this.setTitle("Nueva Tarea");
	    
	}
	else {
	    this.setTitle("Nuevo Proyecto");
	    
	} 
	
	compManager.centerWindow(this);
	
	
    }

}
