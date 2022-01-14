package org.digitall.apps.projecttask.interfaces;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.digitall.lib.components.ComponentsManager;

public class ProjectFrame extends JFrame {
    
    private ProjectsTree jpTask;
    private ComponentsManager compManager;
    
    public ProjectFrame() {
        try {
                jbInit();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
	jpTask = new ProjectsTree(this);
        this.setSize(new Dimension(570, 480));
        this.getContentPane().add(jpTask);
	this.setTitle("Administraciï¿½n de tareas");
	compManager.centerWindow(this);
    }
}
