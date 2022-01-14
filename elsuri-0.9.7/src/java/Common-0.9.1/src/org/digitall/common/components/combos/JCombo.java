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
 * JCombo.java
 *
 * */
package org.digitall.common.components.combos;

import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;

import org.digitall.common.components.combos.CachedCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCombo;
import org.digitall.lib.components.basic.BasicConfig;
import org.digitall.lib.environment.Debug;
import org.digitall.lib.sql.LibSQL;

//

/**
 * Creada: 11/03/2005
 * Última modificación: 23/07/2007
 * Esta clase extiende un BasicCombo con funcionalidades extra, a saber:
 * 1) Implementa un keySelectionManager que permite seleccionar un ítem por teclado
 * 2) Contiene tres vectores java.util.Vector() con los items minY su correspondiente valor minY los valores de referencia
 */
public class JCombo extends BasicCombo implements Serializable {

    private Color backgroundColor = BasicConfig.TEXTFIELD_ENABLED_BACKGROUND_COLOR;
    private Color disabledBackgroundColor = BasicConfig.TEXTFIELD_DISABLED_BACKGROUND_COLOR;
    private Color foregroundColor = BasicConfig.TEXTFIELD_ENABLED_FOREGROUND_COLOR;
    private Color disabledforegroundColor = BasicConfig.TEXTFIELD_DISABLED_FOREGROUND_COLOR;
    private Color uneditableBackgroundColor = BasicConfig.TEXTFIELD_UNEDITABLE_BACKGROUND_COLOR;
    private Color uneditableForegroundColor = BasicConfig.TEXTFIELD_UNEDITABLE_FOREGROUND_COLOR;

    private Vector valuesVector = new Vector();
    private Vector itemsVector = new Vector();
    private Vector refsVector = new Vector();
    private boolean updateNeeded = true;
    private boolean filtered = false;
    private ItemListener itemListener = null;
    private boolean hasItemListener = false;
    private long searchMillis = 1000;
    private int type;
    private boolean generalItem = false;
    private boolean notAssignedItem = false;
    private String sqlFunction = "";
    private Object sqlParams = "";
    private Object filter = null;
    private String sqlQuery = "";
    private final int STATIC = 0;
    private final int QUERY = 1;
    private final int FUNCTION = 2;
    private final int RESULTSET = 3;
    private int sqlType = STATIC;

    public JCombo() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setKeySelectionManager(new KeySelectionManager() {

				 long lastKeyTime = 0;
				 String pattern = "";

				 public int selectionForKey(char aKey, ComboBoxModel model) {
				     // Find index of selected item
				     int selIx = 01;
				     Object sel = model.getSelectedItem();
				     if (sel != null) {
					 for (int i = 0; i < model.getSize(); i++) {
					     if (sel.equals(model.getElementAt(i))) {
						 selIx = i;
						 break;
					     }
					 }
				     }
				     // Get the current time
				     long curTime = System.currentTimeMillis();
				     // If last key was typed less than VARIABLE SET ms ago, append to current pattern
				     if (curTime - lastKeyTime < searchMillis) {
					 pattern += ("" + aKey).toLowerCase();
				     } else {
					 pattern = ("" + aKey).toLowerCase();
				     }
				     // Save current time
				     lastKeyTime = curTime;
				     // Search forward from current selection
				     for (int i = selIx + 1; i < model.getSize(); i++) {
					 String s = model.getElementAt(i).toString().toLowerCase();
					 if (s.startsWith(pattern)) {
					     return i;
					 }
				     }
				     // Search from top to current selection
				     for (int i = 0; i < selIx; i++) {
					 if (model.getElementAt(i) != null) {
					     String s = model.getElementAt(i).toString().toLowerCase();
					     if (s.startsWith(pattern)) {
						 return i;
					     }
					 }
				     }
				     return -1;
				 }

			     }
	);
	this.addMouseListener(new MouseAdapter() {

			   public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 2) {
				    update();
				}
			   }

		       }
	);
	setToolTipText("Doble click con el botón derecho para recargar");
    }

    public String getItemTexto() {
	return this.getSelectedItem().toString().replaceAll("\'", "\\\\'");
    }

    public void addItem(Object _object, Object _value, Object _ref) {
	removeItemListener();
	super.addItem(_object);
	itemsVector.addElement(_object);
	valuesVector.addElement(_value);
	refsVector.addElement(_ref);
	restoreItemListener();
    }

    public void addItem(Object _object, Object _value) {
	removeItemListener();
	super.addItem(_object);
	itemsVector.addElement(_object);
	valuesVector.addElement(_value);
	refsVector.addElement("");
	restoreItemListener();
    }

    public void addItem(Object _object) {
	removeItemListener();
	super.addItem(_object);
	itemsVector.addElement(_object);
	valuesVector.addElement("");
	refsVector.addElement("");
	restoreItemListener();
    }

    public Object getSelectedValue() {
	Object result = null;
	if (getSelectedIndex() > -1) {
	    if (filtered) {
		int i = 0;
		boolean found = false;
		while (!found && i < itemsVector.size()) {
		    if (getItemAt(getSelectedIndex()).toString().equals(itemsVector.elementAt(i))) {
			result = valuesVector.elementAt(i);
			found = true;
		    }
		    i++;
		}
		return result;
	    } else {
		return valuesVector.elementAt(getSelectedIndex());
	    }
	} else {
	    return "-1";
	}
    }

    public Object getSelectedValueRef() {
	Object result = null;
	if (getSelectedIndex() > -1) {
	    if (filtered) {
		int i = 0;
		boolean found = false;
		while (!found && i < itemsVector.size()) {
		    if (getItemAt(getSelectedIndex()).toString().equals(itemsVector.elementAt(i))) {
			result = refsVector.elementAt(i);
			found = true;
		    }
		    i++;
		}
		return result;
	    } else {
		return refsVector.elementAt(getSelectedIndex());
	    }
	} else {
	    return "0";
	}
    }

    public void removeAllItems() {
	removeItemListener();
	super.removeAllItems();
	itemsVector.removeAllElements();
	valuesVector.removeAllElements();
	refsVector.removeAllElements();
	restoreItemListener();
    }

    public void removeItemAt(int _index) {
	removeItemListener();
	super.removeItemAt(_index);
	itemsVector.removeElementAt(_index);
	valuesVector.removeElementAt(_index);
	refsVector.removeElementAt(_index);
	restoreItemListener();
    }

    public Vector getValuesVector() {
	return valuesVector;
    }
    
    public Vector getRefValuesVector() {
        return refsVector;
    }

    public Vector getItemsVector() {
	return itemsVector;
    }

    public void update() {
	//UPDATE ALL DATA!!!
	if (isEnabled()) {
	    updateNeeded = true;
            if (type !=0 ){
                CachedCombo.refreshCombo(this, type);
            } else {
                switch (sqlType) {
                    case QUERY:
                        loadJCombo(sqlQuery);
                        break;
                    case FUNCTION:
                        loadJCombo(sqlFunction, sqlParams);
                        break;
                    case STATIC:
                        Debug.println("Warning: couldn't reload a STATIC combo");
                        break;
                    case RESULTSET:
                        Debug.println("Warning: couldn't reload a RESULTSET combo");
                        break;
                }
            }
	    setFilter(filter);
	}
    }

    public boolean isUpdateNeeded() {
	return updateNeeded;
    }

    public JCombo getCopy() {
	JCombo _temp = new JCombo();
	for (int i = 0; i < itemsVector.size(); i++) {
	    _temp.addItem(itemsVector.elementAt(i), valuesVector.elementAt(i), refsVector.elementAt(i));
	}
	return _temp;
    }

    public void setSelectedValue(Object _value) {
	if (_value != null) {
	    int i = 0;
	    boolean found = false;
	    while (!found && i < getItemCount()) {
		if (getItemAt(i).toString().equals(_value.toString())) {
		    setSelectedItem(_value);
		    found = true;
		}
		i++;
	    }
	} else {
	    if (getItemCount() >= 0) {
		setSelectedIndex(0);
	    }
	}
    }

    public void setSelectedID(Object _id) {
	if (_id != null) {
	    int i = 0;
	    boolean found = false;
	    while (!found && i < valuesVector.size()) {
		if (valuesVector.elementAt(i).toString().equals(_id.toString())) {
		    setSelectedItem(itemsVector.elementAt(i));
		    found = true;
		}
		i++;
	    }
	} else {
	    if (getItemCount() >= 0) {
		setSelectedIndex(0);
	    }
	}
    }

    public void removeFilter() {
	filter = null;
	setFilter(filter);
    }

    public void setFilter(Object _ref) {
	filter = _ref;
	removeItemListener();
	if (filter == null) {
	    if (filtered) {
		filtered = false;
		super.removeAllItems();
		for (int i = 0; i < itemsVector.size(); i++) {
		    super.addItem(itemsVector.elementAt(i));
		}
	    }
	} else {
	    if (filtered) {
		//if (true) {
		//System.out.println("REF: " + _ref);
		super.removeAllItems();
		for (int i = 0; i < itemsVector.size(); i++) {
		    if (refsVector.elementAt(i).toString().equals(filter.toString()) || valuesVector.elementAt(i).toString().equals("0")) {
			super.addItem(itemsVector.elementAt(i));
		    }
		}
	    } else {
		filtered = true;
		for (int i = refsVector.size() - 1; i >= 0; i--) {
		    if (!refsVector.elementAt(i).toString().equals(filter.toString()) && !valuesVector.elementAt(i).toString().equals("0")) {
			super.removeItemAt(i);
		    }
		}
	    }
	}
	restoreItemListener();
    }

    public void addItemListener(ItemListener _itemListener) {
	hasItemListener = true;
	itemListener = _itemListener;
	super.addItemListener(itemListener);
    }

    private void removeItemListener() {
	if (hasItemListener) {
	    super.removeItemListener(itemListener);
	}
    }

    private void restoreItemListener() {
	if (hasItemListener) {
	    super.addItemListener(itemListener);
	}
    }

    public void setUpdateNeeded(boolean _updateNeeded) {
	updateNeeded = _updateNeeded;
    }

    public void setType(int _type) {
	type = _type;
    }

    public int getType() {
	return type;
    }

    /**
     * @param ConsultaCombo: del tipo -> SELECT CampoID,CampoLABEL FROM <tabla>
     * @since 23/07/03 se cambia el componente por uno mejor con cache pero se necesitan 3 (TRES) campos en la consulta SQL
     */
    public void loadJCombo(String _query) {
	//System.out.println(_query );
        sqlType = QUERY;
        sqlQuery= _query;
	if (_query.length() > 0) {
	    loadJCombo(LibSQL.exQuery(_query));
	}
    }

    public void loadJCombo(String _function, Object _params) {
        sqlType = FUNCTION;
        sqlFunction = _function;
        sqlParams = _params;
	loadJCombo(LibSQL.exFunction(_function, _params));
    }

    public void loadJCombo(ResultSet _results) {
	removeAllItems();
	if (hasGeneralItem()) {
	    addGeneralItem();
	}
        if (hasNotAssignedItem()) {
            addItem("Sin Asignar","-1","-1");
        }
	try {
	    while (_results.next()) {
		addItem(_results.getString(2), _results.getString(1), _results.getString(3));
	    }
	} catch (SQLException x) {
	    Advisor.printException(x);
        } catch (NullPointerException x) {
	    //ignore
	}
    }

    public void setGeneralItem(boolean _generalItem) {
	generalItem = _generalItem;
    }

    public boolean hasGeneralItem() {
	return generalItem;
    }

    public void setNotAssignedItem(boolean _notAssignedItem) {
        notAssignedItem = _notAssignedItem;
    }

    public boolean hasNotAssignedItem() {
        return notAssignedItem;
    }

    @Override
    public void setEditable(boolean _editable) {
	super.setEditable(_editable);
    }

    @Override
    public void setEnabled(boolean _enabled) {
	super.setEnabled(_enabled);
    }

    public void updateColors() {
	if (isEnabled()) {
	    if (isEditable()) {
		setBorder(BorderFactory.createLineBorder(BasicConfig.TEXTFIELD_ENABLED_BORDER_COLOR, 1));
		setBackground(backgroundColor);
		setForeground(foregroundColor);
	    } else {
		setBorder(BorderFactory.createLineBorder(BasicConfig.TEXTFIELD_UNEDITABLE_BORDER_COLOR, 1));
		setBackground(uneditableBackgroundColor);
		setForeground(uneditableForegroundColor);
	    }
	} else {
	    setBorder(BorderFactory.createLineBorder(BasicConfig.TEXTFIELD_DISABLED_BORDER_COLOR, 1));
	    setBackground(disabledBackgroundColor);
	    setForeground(disabledforegroundColor);
	}
    }

    public void addGeneralItem() {
	addItem("Todos","-1","-1");
    }
}
