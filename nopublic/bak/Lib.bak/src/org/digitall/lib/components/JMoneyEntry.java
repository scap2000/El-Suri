package org.digitall.lib.components;

import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.text.DecimalFormat;

import java.text.DecimalFormatSymbols;

import java.util.Locale;

import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

public class JMoneyEntry extends JEntry {

    public JMoneyEntry() {
	this(false);
    }

    public JMoneyEntry(boolean _extended) {
	this(_extended?"$ #,##0.0000":"$ #,##0.00");
    }

    public JMoneyEntry(String _format) {
	super(new DecimalFormat(_format, new DecimalFormatSymbols(new Locale("es","AR"))));
	//super(new DecimalFormat(_format, new DecimalFormatSymbols(new Locale("es_AR"))));
	super.setValue(0.0);
	try {
	    NumberFormatter nfl = (NumberFormatter)getFormatter();
	    nfl.setOverwriteMode(true);
	    nfl.setAllowsInvalid(false);
	    addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent keyEvent) {
			    typeKey(keyEvent);
			}

			public void keyPressed(KeyEvent keyEvent) {
			    pressKey(keyEvent);
			}

			public void keyReleased(KeyEvent keyEvent) {
			    releaseKey(keyEvent);
			}

		    });
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void typeKey(KeyEvent _keyEvent) {
	if (_keyEvent.getKeyChar() == '.') {
	    _keyEvent.setKeyChar(',');
	}
    }

    private void pressKey(KeyEvent _keyEvent) {
	if (isEditable()) {
	    switch (_keyEvent.getKeyCode()) {
		case KeyEvent.VK_HOME:
		    _keyEvent.consume();
		    setCaretPosition(2);
		    break;
		case KeyEvent.VK_BACK_SPACE:
		case KeyEvent.VK_DELETE:
		    _keyEvent.consume();
		    setValue(0.0);
		    break;
	    }
	}
    }

    private void releaseKey(KeyEvent _keyEvent) {
	switch (_keyEvent.getKeyCode()) {
	    case KeyEvent.VK_HOME:
		_keyEvent.consume();
		setCaretPosition(2);
		break;
	}
    }

    private void jbInit() throws Exception {
	super.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public void setTextColor(Color _color) {
	this.setForeground(_color);
    }

    public double getAmount() {
	return new Double(getValue().toString());
    }

}
