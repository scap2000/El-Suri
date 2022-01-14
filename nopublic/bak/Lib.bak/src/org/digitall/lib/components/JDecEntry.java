package org.digitall.lib.components;

import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.text.DecimalFormat;

import java.text.DecimalFormatSymbols;

import java.util.Locale;

import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

public class JDecEntry extends JEntry {

    public JDecEntry() {
	this("#0.00");
    }

    public JDecEntry(String _decimalFormat) {
	super(new DecimalFormat(_decimalFormat, new DecimalFormatSymbols(new Locale("en"))));
	/**
	 * No se utiliza Locale("es_AR") por problemas con la grilla
	 * en tableModel.getDataVector().elementAt(i);
	 * */
	//super(new DecimalFormat("#0.00", new DecimalFormatSymbols(new Locale("es_AR"))));
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
	try {
	    return new Double(super.getValue().toString());
	} catch (NullPointerException x) {
	    return 0.0;
	}
    }

    public Object getValue() {
	return getAmount();
    }
}
