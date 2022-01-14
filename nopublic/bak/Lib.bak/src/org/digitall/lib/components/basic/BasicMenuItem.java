package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JMenuItem;

public class BasicMenuItem extends JMenuItem {

    private int idMenuItem = -1;

    public BasicMenuItem() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicMenuItem(String _title) {
	super(_title);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicMenuItem(int _idMenuitem, String _title) {
	super(_title);
	idMenuItem = _idMenuitem;
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

    public int getIdMenuItem() {
	return idMenuItem;
    }

}
