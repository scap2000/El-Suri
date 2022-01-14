package org.digitall.lib.components.basic;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class BasicTextField extends JTextField {

    public BasicTextField() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTextField(int _int) {
	super(_int);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTextField(String _string) {
	super(_string);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setForeground(BasicConfig.TEXTFIELD_ENABLED_FOREGROUND_COLOR);
	setDisabledTextColor(BasicConfig.TEXTFIELD_DISABLED_FOREGROUND_COLOR);
	setEnabled(true);
    }
    
    public void setEnabled(boolean _enabled) {
	super.setEnabled(_enabled);
	if (_enabled) {
	    setBorder(BorderFactory.createLineBorder(BasicConfig.TEXTFIELD_ENABLED_BORDER_COLOR, 1));
	    setBackground(BasicConfig.TEXTFIELD_ENABLED_BACKGROUND_COLOR);
	} else {
	    setBorder(BorderFactory.createLineBorder(BasicConfig.TEXTFIELD_DISABLED_BORDER_COLOR, 1));
	    setBackground(BasicConfig.TEXTFIELD_DISABLED_BACKGROUND_COLOR);
	}
    }
    
}
