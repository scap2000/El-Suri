package org.digitall.lib.components;

import java.awt.Color;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.text.DecimalFormat;

import java.text.DecimalFormatSymbols;

import java.util.Locale;

import javax.swing.text.NumberFormatter;

public class JPercentEntry extends JEntry {

    public JPercentEntry() {
	this("\'%\' #,##0.00");
    }

    public JPercentEntry(String _format) {
	super(new DecimalFormat(_format, new DecimalFormatSymbols(new Locale("es","AR"))));
	//super(new DecimalFormat(_format, new DecimalFormatSymbols(new Locale("es_AR"))));
	super.setValue(0.0);
	try {
	    NumberFormatter nfl = (NumberFormatter)getFormatter();
	    nfl.setOverwriteMode(true);
	    nfl.setAllowsInvalid(false);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	super.setHorizontalAlignment(RIGHT);
    }

    public void setTextColor(Color _color) {
	this.setForeground(_color);
    }

}
