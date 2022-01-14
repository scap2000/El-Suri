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
 * BasicTextInput.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.text.Format;
import java.text.ParseException;

import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class BasicTextInput extends JFormattedTextField {

    private Color backgroundColor = BasicConfig.TEXTFIELD_ENABLED_BACKGROUND_COLOR;
    private Color disabledBackgroundColor = BasicConfig.TEXTFIELD_DISABLED_BACKGROUND_COLOR;
    private Color foregroundColor = BasicConfig.TEXTFIELD_ENABLED_FOREGROUND_COLOR;
    private Color disabledforegroundColor = BasicConfig.TEXTFIELD_DISABLED_FOREGROUND_COLOR;
    private Color uneditableBackgroundColor = BasicConfig.TEXTFIELD_UNEDITABLE_BACKGROUND_COLOR;
    private Color uneditableForegroundColor = BasicConfig.TEXTFIELD_UNEDITABLE_FOREGROUND_COLOR;
	
    public BasicTextInput() {
	super();
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTextInput(int _int) {
	super(_int);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTextInput(String _format) throws ParseException {
	super(new MaskFormatter(_format));
	((MaskFormatter)getFormatter()).setPlaceholderCharacter('_');
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTextInput(AbstractFormatter _format) {
	super(_format);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTextInput(Format _format) {
	super(_format);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTextInput(Date _date) {
	super(_date);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setForegroundColor(foregroundColor);
	setDisabledTextColor(disabledforegroundColor);
	addFocusListener(new FocusListener() {

		public void focusGained(FocusEvent focusEvent) {
		    setBorder(BorderFactory.createLineBorder(new Color(199, 0, 0), 2));
		}

		public void focusLost(FocusEvent focusEvent) {
		    try {
		        if (isEnabled()) {
			    setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
			    commitEdit();
			}
		    } catch (ParseException e) {
			//e.printStackTrace();
			setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 1));
		    }
		}
	    });
	
	setEnabled(true);
    }

    public void setEnabled(boolean _enabled) {
	super.setEnabled(_enabled);
	updateColors();
    }
    
    public void setEditable(boolean _editable) {
	super.setEditable(_editable);
	updateColors();
    }
    
    public void updateColors() {
	if (isEnabled()) {
	    if (isEditable()) {
	        setBorder(BorderFactory.createLineBorder(BasicConfig.TEXTFIELD_ENABLED_BORDER_COLOR, 1));
	        setBackground(backgroundColor);
	        setForeground(foregroundColor);
	        setCaretColor(foregroundColor);
	    } else {
	        setBorder(BorderFactory.createLineBorder(BasicConfig.TEXTFIELD_UNEDITABLE_BORDER_COLOR, 1));
	        setBackground(uneditableBackgroundColor);
	        setForeground(uneditableForegroundColor);
	        setCaretColor(uneditableForegroundColor);
	    }
	} else {
	    setBorder(BorderFactory.createLineBorder(BasicConfig.TEXTFIELD_DISABLED_BORDER_COLOR, 1));
	    setBackground(disabledBackgroundColor);
	    setForeground(disabledforegroundColor);
	}
    }

    public void setFormatter(AbstractFormatter _formatter) {
	super.setFormatter(_formatter);
    }

    public void setBackgroundColor(Color backgroundColor) {
	this.backgroundColor = backgroundColor;
	updateColors();
    }

    public void setDisabledBackgroundColor(Color disabledBackgroundColor) {
	this.disabledBackgroundColor = disabledBackgroundColor;
	updateColors();
    }

    public void setForegroundColor(Color foregroundColor) {
	this.foregroundColor = foregroundColor;
	updateColors();
    }

    public void setDisabledforegroundColor(Color disabledforegroundColor) {
	this.disabledforegroundColor = disabledforegroundColor;
	updateColors();
    }

    public void setUneditableBackgroundColor(Color uneditableBackgroundColor) {
	this.uneditableBackgroundColor = uneditableBackgroundColor;
	updateColors();
    }

    public void setUneditableForegroundColor(Color uneditableForegroundColor) {
	this.uneditableForegroundColor = uneditableForegroundColor;
	updateColors();
    }
}
