package org.digitall.lib.components.basic;

import java.awt.Component;

import javax.swing.JScrollPane;

public class BasicScrollPane extends JScrollPane {

    public BasicScrollPane() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicScrollPane(Component _component) {
	super(_component);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.getViewport().setOpaque(false);
	this.setBackground(BasicConfig.SCROLLPANE_BACKGROUND_COLOR);
    }

}
