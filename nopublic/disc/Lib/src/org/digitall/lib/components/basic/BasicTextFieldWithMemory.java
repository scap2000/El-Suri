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
 * BasicTextFieldWithMemory.java
 *
 * */
package org.digitall.lib.components.basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import org.digitall.lib.components.basic.BasicCombo;
import org.digitall.lib.components.basic.BasicTextField;

public class BasicTextFieldWithMemory extends BasicCombo {

    Vector items;
    CustomComboBoxModel model;
    BasicTextField tfield;
    int columns;
    /* constructor for a special usage, this obj calls handleEditedElement() */

    public BasicTextFieldWithMemory(Vector vec, int cs, boolean handleEdit) {
	items = vec;
	columns = cs;
	model = new CustomComboBoxModel(items);
	setModel(model);
	setEditable(false);
	tfield = (BasicTextField)getEditor().getEditorComponent();
	if (cs > 0) {
	    tfield.setColumns(columns);
	}
	if (handleEdit) {
	    // automatic self accumulation
	    tfield.addActionListener(new ActionListener() {
				  public void actionPerformed(ActionEvent e) {
				      handleEditedItem();
				  }

			      }
	    );
	}
    }
    /* ordinary usage constructor, application calls handleEditedElement() */

    public BasicTextFieldWithMemory(Vector vec, int cs) {
	this(vec, cs, false);
    }

    public BasicTextFieldWithMemory(Vector vec) {
	this(vec, 0);
    }

    public BasicTextFieldWithMemory() {
	this(new Vector(), 0);
    }

    public BasicTextField getTextField() {
	return tfield;
    }

   public void setText(String t) {
	tfield.setText(t);
	handleEditedItem();
    }

    public Vector getItems() {
	return items;
    }

    public void addActionListenerTf(ActionListener al) {
	tfield.addActionListener(al);
    }

    public void handleEditedItem() {
	String s = tfield.getText();
	if (s.length() > 0 && !model.hasElement(s)) {
	    model.addElement(s);
	    // add new element
	}
	tfield.setText(s);
	// prevent blanking out
    }
    
    public String getText() {
	return tfield.getText();
    }

    private class CustomComboBoxModel extends DefaultComboBoxModel {

	public CustomComboBoxModel(Vector vec) {
	    super(vec);
	}
	// like to add it to head, not to tail

	public void addElement(Object o) {
	    insertElementAt(o, 0);
	}

	public boolean hasElement(Object o) {
	    int size = getSize();
	    int idx = getIndexOf(o);
	    return (idx > -1 && idx < size);
	}

    }

}
