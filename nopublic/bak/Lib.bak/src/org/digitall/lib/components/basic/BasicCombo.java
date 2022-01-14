package org.digitall.lib.components.basic;

import java.awt.Color;

import java.awt.event.FocusEvent;

import java.awt.event.FocusListener;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.UIManager;

public class BasicCombo extends JComboBox {

    public BasicCombo() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicCombo(Vector _vector) {
	super(_vector);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicCombo(Object[] _object) {
	super(_object);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicCombo(String[] _string) {
	super(_string);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setRenderer(new BasicComboRenderer());
	setBackground(BasicConfig.LABEL_FOREGROUND_COLOR);
	addFocusListener(new FocusListener() {

	    public void focusGained(FocusEvent focusEvent) {
		setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 1));
	    }

	    public void focusLost(FocusEvent focusEvent) {
		setBorder(null);
	    }

	});
    }

}

