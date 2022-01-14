package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.FocusEvent;

import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;

import org.digitall.lib.icons.IconTypes;

public class BasicCheckBox extends JCheckBox {

    private int idCheckBox = -1;

    public BasicCheckBox() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicCheckBox(boolean _boolean) {
	this("", _boolean);
    }

    public BasicCheckBox(String _string) {
	this(_string, false);
    }

    public BasicCheckBox(String _string, boolean _boolean) {
	this(-1, _string, _boolean);
    }

    public BasicCheckBox(int _idCheckBox, String _string, boolean _boolean) {
	super(_string, _boolean);
	idCheckBox = _idCheckBox;
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
	
	setSelectedIcon(IconTypes.check_sel_16x16);
	setRolloverSelectedIcon(IconTypes.check_sel_ro_16x16);
	setDisabledSelectedIcon(IconTypes.check_sel_ne_16x16);
	setPressedIcon(IconTypes.check_sel_pr_16x16);
	setIcon(IconTypes.check_16x16);
	setRolloverIcon(IconTypes.check_ro_16x16);
	setDisabledIcon(IconTypes.check_ne_16x16);
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

    public int getIdCheckBox() {
	return idCheckBox;
    }

}
