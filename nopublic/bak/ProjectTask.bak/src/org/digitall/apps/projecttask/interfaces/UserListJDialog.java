package org.digitall.apps.projecttask.interfaces;

import java.awt.Dimension;

import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicDialog;

public class UserListJDialog extends BasicDialog {
    private UserList userList = new UserList(this); 
    private ComponentsManager compManager;
    
    public UserListJDialog() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(535, 150));
        this.getContentPane().add(userList);
	this.setTitle("Usuarios");
	compManager.centerWindow(this);
	
    }
}
