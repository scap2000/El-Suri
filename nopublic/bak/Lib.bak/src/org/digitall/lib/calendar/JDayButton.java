// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2005-01-11 06:14:12 p.m.
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   JDayButton.java
//package org.gui;
package org.digitall.lib.calendar;

import java.awt.Insets;

import javax.swing.JToggleButton;

public class JDayButton extends JToggleButton {

    private String day = "";
    private String month = "";
    private String year = "";

    public JDayButton() {
	setEnabled(false);
    }

    public JDayButton(String text) {
	super(text);
	setMargin(new Insets(0, 0, 0, 0));
	setFont(getFont().deriveFont(0, 9F));
    }

    public JDayButton(String _day, String _month, String _year, String text) {
	super(text);
	setMargin(new Insets(0, 0, 0, 0));
	setFont(getFont().deriveFont(0, 9F));
	day = _day;
	month = _month;
	year = _year;
    }

    public String getDateDMY() {
	return day + "/" + month + "/" + year;
    }

}
