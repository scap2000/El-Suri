/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * PanelColor.java
 *
 * */
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
