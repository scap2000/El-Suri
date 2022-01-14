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
 * BasicButton.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import org.digitall.lib.data.Format;

public class BasicButton extends JButton {

    private String text = "";
    private int horizontalInsets = 3;
    private boolean showText = true;
    private int idButton = -1;

    public BasicButton() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicButton(String _text) {
	text = _text;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicButton(ImageIcon _icon) {
	super(_icon);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicButton(Icon _icon) {
	super(_icon);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicButton(String _text, Icon _icon) {
	super(_icon);
	text = _text;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicButton(Action _action) {
	super(_action);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setOpaque(false);
	setFont(new Font("Dialog", 1, 11));
	setForeground(Color.BLUE.darker());
	setHorizontalTextPosition(SwingConstants.RIGHT);
	setIconTextGap(3);
	setAlignmentY(JButton.CENTER_ALIGNMENT);
	setMargin(new Insets(1, 1, 1, 1));
	setRolloverEnabled(true);
	setVerticalTextPosition(SwingConstants.BOTTOM);
	setHorizontalTextPosition(SwingConstants.CENTER);
	setForeground(BasicConfig.LABEL_FOREGROUND_COLOR);
	setText(text);
	setCursor(new Cursor(Cursor.HAND_CURSOR));
	addFocusListener(new FocusListener() {

	    public void focusGained(FocusEvent focusEvent) {
	        setFocusPainted(true);
	    }

	    public void focusLost(FocusEvent focusEvent) {
	        setFocusPainted(false);
	    }

	});
    }
    /*protected void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D)g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	int w = getWidth();
	int h = getHeight();
	GradientPaint gradient = new GradientPaint(20, 0, BasicConfig.BUTTON_GRADIENT_START_COLOR, 20, h, BasicConfig.BUTTON_GRADIENT_END_COLOR, false);
	g2.setPaint(gradient);
	g2.fillRect(0, 0, w, h);
	super.paintComponent(g);
    }*/

    public void setText(String _text) {
        if (_text != null) {
            if (showText) {
                super.setText(Format.toHtmlCentered(_text));
            } else {
                super.setText("");
            }
        }
	text = _text;
    }

    public void setShowText(boolean _showText) {
	showText = _showText;
	setText(text);
    }

    public void setIdButton(int _idButton) {
	idButton = _idButton;
    }

    public int getIdButton() {
	return idButton;
    }

    public void resizeToIconSize() {
	if (getIcon() != null) {
	    setMaximumSize(new Dimension(getIcon().getIconWidth()+horizontalInsets, getIcon().getIconHeight()));
	    setMinimumSize(new Dimension(getIcon().getIconWidth()+horizontalInsets, getIcon().getIconHeight()));
	}
    }

    @Override
    public void setOpaque(boolean _opaque) {
	this.setContentAreaFilled(_opaque);
	this.setBorderPainted(_opaque);
	this.setFocusPainted(_opaque);
	super.setOpaque(_opaque);
    }
}
