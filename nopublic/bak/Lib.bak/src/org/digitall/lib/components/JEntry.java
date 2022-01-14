package org.digitall.lib.components;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.text.Format;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.MaskFormatter;

import org.digitall.lib.components.basic.BasicTextInput;

public class JEntry extends BasicTextInput {

    public JEntry() {
	this(new DefaultFormatter());
	setValue("");
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public JEntry(AbstractFormatter _format) {
	super(_format);
	if (_format instanceof DefaultFormatter) {
	    ((DefaultFormatter)_format).setOverwriteMode(false);
	}
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public JEntry(Format _format) {
	super(_format);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {

    }

    public String getText() {
	return super.getText().replaceAll("\'", "\\\\'").trim().replaceAll("  ", " ");
    }
    
}
