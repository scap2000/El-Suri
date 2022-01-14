package org.digitall.apps.jpgadmin.sqleditor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import org.digitall.lib.sql.LibSQL;

public class MainFrame extends JFrame {

    public MainFrame() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setLayout(new BorderLayout());
	getContentPane().add(new FunctionsList(), BorderLayout.CENTER);
	setSize(new Dimension(1000, 800));
	setResizable(true);
	this.setTitle("JSQL Editor");
    }

}
