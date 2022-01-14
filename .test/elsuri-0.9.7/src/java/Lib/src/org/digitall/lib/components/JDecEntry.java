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
 * JDecEntry.java
 *
 * */
package org.digitall.lib.components;

import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.text.DecimalFormat;

import java.text.DecimalFormatSymbols;

import java.util.Locale;

import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

public class JDecEntry extends JEntry {

    public JDecEntry() {
	this("#0.00");
    }

    public JDecEntry(String _decimalFormat) {
	super(new DecimalFormat(_decimalFormat, new DecimalFormatSymbols(new Locale("en"))));
	/**
	 * No se utiliza Locale("es_AR") por problemas con la grilla
	 * en tableModel.getDataVector().elementAt(i);
	 * */
	//super(new DecimalFormat("#0.00", new DecimalFormatSymbols(new Locale("es_AR"))));
	super.setValue(0.0);
	try {
	    NumberFormatter nfl = (NumberFormatter)getFormatter();
	    nfl.setOverwriteMode(true);
	    nfl.setAllowsInvalid(false);
	    addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent keyEvent) {
			    typeKey(keyEvent);
			}

			public void keyPressed(KeyEvent keyEvent) {
			    pressKey(keyEvent);
			}

			public void keyReleased(KeyEvent keyEvent) {
			    releaseKey(keyEvent);
			}

		    });
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void typeKey(KeyEvent _keyEvent) {
	if (_keyEvent.getKeyChar() == '.') {
	    _keyEvent.setKeyChar(',');
	}
    }

    private void pressKey(KeyEvent _keyEvent) {
	if (isEditable()) {
	    switch (_keyEvent.getKeyCode()) {
		case KeyEvent.VK_HOME:
		    _keyEvent.consume();
		    setCaretPosition(2);
		    break;
		case KeyEvent.VK_BACK_SPACE:
		case KeyEvent.VK_DELETE:
		    _keyEvent.consume();
		    setValue(0.0);
		    break;
	    }
	}
    }

    private void releaseKey(KeyEvent _keyEvent) {
	switch (_keyEvent.getKeyCode()) {
	    case KeyEvent.VK_HOME:
		_keyEvent.consume();
		setCaretPosition(2);
		break;
	}
    }

    private void jbInit() throws Exception {
	super.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public void setTextColor(Color _color) {
	this.setForeground(_color);
    }

    public double getAmount() {
	try {
	    return new Double(super.getValue().toString());
	} catch (NullPointerException x) {
	    return 0.0;
	}
    }

    public Object getValue() {
	return getAmount();
    }
}
