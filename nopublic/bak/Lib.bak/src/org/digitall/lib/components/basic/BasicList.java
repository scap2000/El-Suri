package org.digitall.lib.components.basic;

import javax.swing.JList;
import javax.swing.ListModel;

public class BasicList extends JList {

    public BasicList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    public BasicList(ListModel _model) {
	super(_model);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }


    private void jbInit() {
    }

}
