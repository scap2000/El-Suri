package org.digitall.apps.taskman;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

import org.digitall.apps.taskman.interfases.TaskManList;


public class TaskManMain extends JFrame {
    
    private TaskManList pContenedor = new TaskManList(this);
    
    public TaskManMain() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(699, 99));
        this.setTitle("Administrador de Tareas");
        this.setResizable(false);
        pContenedor.setBounds(new Rectangle(0, 0, 695, 70));
        pContenedor.setLayout(null);
        this.getContentPane().add(pContenedor, null);
    }
}
