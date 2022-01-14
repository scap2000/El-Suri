package org.digitall.lib.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;

public class JPColorLabel extends BasicContainerPanel {

    private String date;
    private Color color;
    private BasicPanel jpColor = new BasicPanel();
    private GridLayout gridLayout3 = new GridLayout();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();

    public JPColorLabel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void setDate(String _date) {
	date = _date;
    }

    public String getDate() {
	return date;
    }

    public void setColor(Color _color) {
	color = _color;
	jpColor.setBackground(color);
    }

    public Color getColor() {
	return color;
    }

    private void jbInit() {
	this.setLayout(gridBagLayout1);
	jpColor.setLayout(gridLayout3);
	jpColor.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	jpColor.setSize(new Dimension(30, 247));
	//this.setToolTipText("hola");
	this.add(jpColor, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 30, 296));
	this.setBackground(Color.white);
    }

}
