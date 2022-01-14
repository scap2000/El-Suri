package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JMenu;

public class BasicMenu extends JMenu {

    public BasicMenu() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicMenu(String _title) {
	super(_title);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//setBackground(Color.BLUE);
	this.setRolloverEnabled(true);
	setForeground(Color.WHITE);
	setFont(new Font("Lucida Sans", Font.BOLD, 11));
    }

}
