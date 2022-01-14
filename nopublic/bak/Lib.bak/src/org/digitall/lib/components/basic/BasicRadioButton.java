package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.FocusEvent;

import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JRadioButton;

import org.digitall.lib.icons.IconTypes;

public class BasicRadioButton extends JRadioButton {

    private int idRadioButton = -1;

    public BasicRadioButton() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicRadioButton(String _string) {
	super(_string);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicRadioButton(String _string, boolean _boolean) {
	super(_string, _boolean);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicRadioButton(int _idRadioButton, String _string, boolean _boolean) {
	super(_string, _boolean);
	idRadioButton = _idRadioButton;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setOpaque(false);
	setContentAreaFilled(false);
	setBorderPainted(false);
	setFocusPainted(false);
	setForeground(BasicConfig.LABEL_FOREGROUND_COLOR);
	setSelectedIcon(IconTypes.radio_sel_16x16);
	setRolloverSelectedIcon(IconTypes.radio_sel_ro_16x16);
	setDisabledSelectedIcon(IconTypes.radio_sel_ne_16x16);
	setPressedIcon(IconTypes.radio_sel_pr_16x16);

	setIcon(IconTypes.radio_16x16);
	setRolloverIcon(IconTypes.radio_ro_16x16);
	setDisabledIcon(IconTypes.radio_ne_16x16);
	setFont(new Font("Lucida Sans", Font.PLAIN, 11));
	addFocusListener(new FocusListener() {

	    public void focusGained(FocusEvent focusEvent) {
	        setBorderPainted(true);
		setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 1));
	    }

	    public void focusLost(FocusEvent focusEvent) {
		setBorder(null);
	    }

	});
    }

    public int getIdRadioButton() {
	return idRadioButton;
    }

}
