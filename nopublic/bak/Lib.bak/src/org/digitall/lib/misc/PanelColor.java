package org.digitall.lib.misc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.FindButton;

public class PanelColor extends BasicContainerPanel {

    private BasicPanel colorseleccionado = new BasicPanel();
    private FindButton pickcolor = new FindButton();
    private int red, green, blue;
    private BasicLabel labelblue1 = new BasicLabel();

    public PanelColor() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(170, 40));
	this.setBounds(new Rectangle(10, 10, 170, 35));
	colorseleccionado.setBounds(new Rectangle(5, 15, 60, 20));
	colorseleccionado.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	pickcolor.setText("Buscar");
	pickcolor.setBounds(new Rectangle(80, 10, 86, 24));
	pickcolor.setMargin(new Insets(2, 5, 2, 14));
	pickcolor.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			pickcolor_actionPerformed(e);
		    }

		});
	labelblue1.setText("Color:");
	labelblue1.setBounds(new Rectangle(5, 0, 37, 15));
	this.add(labelblue1, null);
	this.add(pickcolor, null);
	this.add(colorseleccionado, null);
    }

    private void pickcolor_actionPerformed(ActionEvent e) {
	/** Abre el Selector de Colores */
	Color initialColor = Color.WHITE;
	Color newColor = JColorChooser.showDialog(this, "Seleccione color", initialColor);
	try {
	    colorseleccionado.setBackground(newColor);
	    setColor(newColor);
	} catch (NullPointerException x) {
	}
    }

    public void setColor(Color c) {
	red = c.getRed();
	green = c.getGreen();
	blue = c.getBlue();
	colorseleccionado.setBackground(new Color(red, green, blue));
    }

    public Color getColor() {
	return new Color(red, green, blue);
    }

    public int getRed() {
	return red;
    }

    public int getGreen() {
	return green;
    }

    public int getBlue() {
	return blue;
    }

    private void delcolor_actionPerformed(ActionEvent e) {
	this.red = 0;
	this.green = 0;
	this.blue = 0;
    }

}
