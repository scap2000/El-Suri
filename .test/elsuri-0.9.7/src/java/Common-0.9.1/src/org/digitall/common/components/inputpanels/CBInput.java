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
 * CBInput.java
 *
 * */
package org.digitall.common.components.inputpanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import java.sql.ResultSet;

import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

//

public class CBInput extends BasicPanel {

    private BasicLabel lbl = new BasicLabel();
    private JCombo comboBox;
    private int comboType;
    private String componentName;
    private boolean editable = false;
    private AddComboButton btnAddItem;
    private ExtendedInternalFrame addItemDialog;
    private ActionListener actionListener;
    private KeyAdapter keyAdapter;

    public CBInput(int _comboType, String _componentName) {
	try {
	    comboType = _comboType;
	    componentName = _componentName;
	    editable = false;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public CBInput(int _nComboType, String _sComponentName, boolean _bEditable) {
	try {
	    comboType = _nComboType;
	    componentName = _sComponentName;
	    editable = _bEditable;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public CBInput() {
	try {
	    comboType = DataTypes.STRING;
	    componentName = "na";
	    editable = false;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(new BorderLayout());
	this.setSize(new Dimension(333, 60));
	this.addComponentListener(new ComponentAdapter() {

			       public void componentResized(ComponentEvent e) {
			           if (e.getID() == ComponentEvent.COMPONENT_RESIZED) {
			               //autoSize();
			           }
			       }

			   }
	);
	//lbl.setBounds(new Rectangle(0, 0, getWidth(), 14));
	lbl.setText("Label");
	if (comboType == 0) {
	    comboBox = new JCombo();
	} else {
	    comboBox = CachedCombo.getCachedCombo(comboType);
	}
	comboBox.setName(componentName);
	//comboBox.setBounds(new Rectangle(0, 14, getWidth(), 20));
	this.add(comboBox, BorderLayout.CENTER);
	this.add(lbl, BorderLayout.NORTH);
	btnAddItem = new AddComboButton();
	if (editable) {
	    btnAddItem.setToolTipText("Agregar");
	    //btnAddItem.setBounds(new Rectangle(getWidth() - addItemSize, getHeight() - addItemSize, addItemSize, addItemSize));
	    this.add(btnAddItem, BorderLayout.EAST);
	}

	Set forwardKeys = getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
	Set newForwardKeys = new HashSet(forwardKeys);
	newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
	setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardKeys);
	comboBox.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    updateToolTipText();
		}
	    }
	});
	updateToolTipText();
	load();
    }

    private void load() {
	setAddItemDialog(CachedCombo.getAddItemBasicDialog(comboType));
	this.add(lbl, BorderLayout.NORTH);
	this.add(comboBox, BorderLayout.CENTER);
	//lbl.setBounds(new Rectangle(0, 0, this.getWidth(), 14));
	updateLabel();
	updateToolTipText();
    }
    
    private void updateLabel() {
	lbl.setText(Environment.lang.getProperty(componentName) + ":");
    }

    public void setEnabled(boolean _enabled) {
	comboBox.setEnabled(_enabled);
	if (btnAddItem != null) {
	    btnAddItem.setEnabled(_enabled);
	}
    }

    public void setSComponentName(String _sComponentName) {
	componentName = _sComponentName;
	updateLabel();
    }

    public String getSComponentName() {
	return componentName;
    }

    public JCombo getCombo() {
	return comboBox;
    }

    public void setCombo(JCombo _cbExternal) {
	remove(comboBox);
	comboBox = _cbExternal;
	this.add(comboBox, BorderLayout.CENTER);
	autoSize();
    }

    public Object getSelectedItem() {
	return comboBox.getSelectedItem();
    }

    public Object getSelectedValueRef(){
	return comboBox.getSelectedValueRef();
    }

    public Object getSelectedValue() {
	return comboBox.getSelectedValue();
    }

    public void setFilter(Object _filter) {
	comboBox.setFilter(_filter);
	updateToolTipText();
    }

    public void removeFilter() {
	comboBox.removeFilter();
    }

    public void setSelectedValue(Object _value) {
	comboBox.setSelectedValue(_value);
	updateToolTipText();
    }

    public void setSelectedID(Object _value) {
	comboBox.setSelectedID(_value);
	updateToolTipText();
    }

    public int getSelectedIndex() {
	return comboBox.getSelectedIndex();
    }

    public void removeItemAt(int _index) {
	comboBox.removeItemAt(_index);
    }

    public void autoSize() {
	//lbl.setBounds(new Rectangle(0, 0, this.getWidth(), 14));
	if (editable) {
	    //btnAddItem.setBounds(new Rectangle(this.getWidth() - addItemSize, getHeight()-addItemSize, addItemSize, addItemSize));
	    //comboBox.setBounds(new Rectangle(0, 14, this.getWidth() - 25, 20));
	} else {
	    //comboBox.setBounds(new Rectangle(0, 14, this.getWidth(), 20));
	}
    }

    private void setAddItemDialog(ExtendedInternalFrame _dialog) {
	if (editable && _dialog != null) {
	    addItemDialog = _dialog;
	    btnAddItem.setEnabled(true);
	    btnAddItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			addItemDialog.show();
		    }
		}
	    );
	} else {
	    if (editable) {
		setCanAddItem(true);
	    }
	}
    }

    private void setCanAddItem(boolean _canAddItem) {
	if (editable && _canAddItem) {
	    btnAddItem.setEnabled(true);
	    btnAddItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
                        performAddButtonAction();
		    }
		}
	    );
	}
    }


    public void addItemListener(ItemListener _itemListener) {
	comboBox.addItemListener(_itemListener);
    }
    
    @Deprecated
    public void setItemListener(ItemListener _itemListener) {
	addItemListener(_itemListener);
    }

    public void setList(Object[] _list) {
	comboBox.removeAllItems();
	comboBox.addItem(_list);
	updateToolTipText();
    }


    public void addItem(Object _item) {
	comboBox.addItem(_item);
	updateToolTipText();
    }

    public void removeAllItems() {
	comboBox.removeAllItems();
	updateToolTipText();
    }

    public void loadJCombo(ResultSet _resultSet) {
	comboBox.setUpdateNeeded(true);
	comboBox.loadJCombo(_resultSet);
	updateToolTipText();
    }

    public void loadJCombo(String _function, Object _params) {
	comboBox.setUpdateNeeded(true);
	comboBox.loadJCombo(_function, _params);
	updateToolTipText();
    }

    public void loadJCombo(String _query) {
	comboBox.setUpdateNeeded(true);
	comboBox.loadJCombo(_query);
	updateToolTipText();
    }

    public void removeItemListener(ItemListener _itemListener) {
	comboBox.removeItemListener(_itemListener);
    }

    public void setActionListener(ActionListener _actionListener) {
	actionListener = _actionListener;
	comboBox.addActionListener(actionListener);
    }

    public void setKeyListener(KeyAdapter _keyAdapter) {
	keyAdapter = _keyAdapter;
	comboBox.addKeyListener(keyAdapter);
    }
    
    public void update() {
	comboBox.update();
	updateToolTipText();
    }

    public void setGeneralItem(boolean _generalItem) {
	comboBox.setGeneralItem(_generalItem);
    }

    public boolean hasGeneralItem() {
	return comboBox.hasGeneralItem();
    }

    private void updateToolTipText() {
	if (getSelectedIndex() != -1) {
	    setToolTipText(lbl.getText() + " " + getSelectedItem().toString());
	} else {
	    setToolTipText(null);
	}
    }

    public void addGeneralItem() {
	setGeneralItem(true);
	comboBox.addGeneralItem();
    }
    
    public AddComboButton getAddButton() {
	return btnAddItem;
    }

    /**
     * Método para override, se ejecuta cuando se presiona el botón "Agregar"
     * */
    public void performAddButtonAction() {
        String _title = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Ingrese un nuevo ítem", "Valor del ítem", JOptionPane.QUESTION_MESSAGE);
        if (_title != null) {
            _title = _title.trim().toUpperCase().replaceAll("  ", " ");
            if (_title.length() > 0) {
                addItem(_title);
                setSelectedValue(_title);
            } else {
                Advisor.messageBox("El valor no puede estar vacío", "Error");
            }
        }

    }

}
