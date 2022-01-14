package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.event.FocusEvent;

import java.awt.event.FocusListener;

import java.text.ParseException;

import javax.swing.BorderFactory;

import org.digitall.lib.components.JArea;

public class BasicTextArea extends JArea {

    public BasicTextArea() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setEnabled(true);
	addFocusListener(new FocusListener() {

		public void focusGained(FocusEvent focusEvent) {
		    setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 2));
		}

		public void focusLost(FocusEvent focusEvent) {
		    setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
		}
	    });
    }
    
    public void setEnabled(boolean _enabled) {
	super.setEnabled(_enabled);
	if (_enabled) {
	    setBorder(BorderFactory.createLineBorder(BasicConfig.TEXTFIELD_ENABLED_BORDER_COLOR, 1));
	    setBackground(BasicConfig.TEXTFIELD_ENABLED_BACKGROUND_COLOR);
	    setForeground(BasicConfig.TEXTFIELD_ENABLED_FOREGROUND_COLOR);
	} else {
	    setBorder(BorderFactory.createLineBorder(BasicConfig.TEXTFIELD_DISABLED_BORDER_COLOR, 1));
	    setBackground(BasicConfig.TEXTFIELD_DISABLED_BACKGROUND_COLOR);
	    setForeground(BasicConfig.TEXTFIELD_DISABLED_FOREGROUND_COLOR);
	}
    }

}
