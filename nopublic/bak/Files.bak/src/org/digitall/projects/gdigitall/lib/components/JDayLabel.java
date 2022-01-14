// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2005-01-11 06:14:32 p.m.
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   JDayLabel.java

package org.digitall.projects.gdigitall.lib.components;

import javax.swing.JLabel;

public class JDayLabel extends JLabel
{

    public JDayLabel(String text)
    {
        super(text, 0);
        setFont(getFont().deriveFont(0, 9F));
    }
}