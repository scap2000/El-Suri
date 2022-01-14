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
 * CBInputPanel.java
 *
 * */
package org.digitall.common.components.inputpanels;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

//

/**
 * @deprecated
 */
public class CBInputPanel extends BasicPanel {

    private BasicLabel lbl = new BasicLabel();
    private JCombo comboBox;
    private int nComboType;
    private String sComponentName;
    private boolean bEditable = false;
    private AddComboButton btnAddItem;
    private ExtendedInternalFrame addItemDialog;

    public CBInputPanel(int _nComboType, String _sComponentName) {
	try {
	    nComboType = _nComboType;
	    sComponentName = _sComponentName;
	    bEditable = false;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public CBInputPanel(int _nComboType, String _sComponentName, boolean _bEditable) {
	try {
	    nComboType = _nComboType;
	    sComponentName = _sComponentName;
	    bEditable = _bEditable;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public CBInputPanel() {
	try {
	    nComboType = DataTypes.STRING;
	    sComponentName = "na";
	    bEditable = false;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setLayout(null);
	lbl.setText("Label Text");
	add(lbl, null);
	if (bEditable) {
	    btnAddItem = new AddComboButton();
	    add(btnAddItem, null);
	    btnAddItem.setBounds(new Rectangle(getWidth() - 20, 14, 20, 20));
	}
	lbl.setBounds(new Rectangle(0, 0, getWidth(), 14));
	// setSize(200,50);
	/***/
	String sLabelText = Environment.lang.getProperty(sComponentName) + ":";
	int nComponentLenght = 0;
	lbl.setText(sLabelText);
	/*
         * Instance JCombo
         */
	comboBox = CachedCombo.getCachedCombo(nComboType);
	comboBox.setName(sComponentName);
	comboBox.setBounds(new Rectangle(0, 14, getWidth(), 20));
	add(comboBox, null);
	setAddItemDialog(CachedCombo.getAddItemBasicDialog(nComboType));
    }

    public void setSComponentName(String _sComponentName) {
	sComponentName = _sComponentName;
    }

    public String getSComponentName() {
	return sComponentName;
    }

    public JCombo getCombo() {
	return comboBox;
    }

    public void setCombo(JCombo _cbExternal) {
	remove(comboBox);
	comboBox = _cbExternal;
	add(comboBox, null);
	setBounds(getBounds());
    }

    public Object getSelectedItem() {
	return comboBox.getSelectedItem();
    }

    public Object getSelectedValue() {
	return comboBox.getSelectedValue();
    }

    public void setFilter(Object _filter) {
	comboBox.setFilter(_filter);
    }

    public void setSelectedValue(Object _value) {
	comboBox.setSelectedValue(_value);
    }

    public void setSelectedID(Object _value) {
	comboBox.setSelectedID(_value);
    }

    public int getSelectedIndex() {
	return comboBox.getSelectedIndex();
    }

    public void removeItemAt(int _index) {
	comboBox.removeItemAt(_index);
    }

    public void setBounds(Rectangle _bounds) {
	super.setBounds(_bounds);
	lbl.setBounds(new Rectangle(0, 0, getWidth(), 14));
	if (bEditable) {
	    btnAddItem.setBounds(new Rectangle(getWidth() - 20, 14, 20, 20));
	    comboBox.setBounds(new Rectangle(0, 14, getWidth() - 25, 20));
	} else {
	    comboBox.setBounds(new Rectangle(0, 14, getWidth(), 20));
	}
    }

    private void setAddItemDialog(ExtendedInternalFrame _dialog) {
	if (bEditable && _dialog != null) {
	    addItemDialog = _dialog;
	    btnAddItem.setEnabled(true);
	    btnAddItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			    /*addItemDialog.setModal(true);*/
			    addItemDialog.show();
			    /*comboBox.update();*/
			}

		    });
	}
    }

}
