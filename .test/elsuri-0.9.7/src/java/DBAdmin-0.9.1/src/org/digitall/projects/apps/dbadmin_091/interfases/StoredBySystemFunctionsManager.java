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
 * StoredBySystemFunctionsManager.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckList;
import org.digitall.lib.components.basic.BasicCheckableListItem;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.DBAdminMain;
import org.digitall.projects.apps.dbadmin_091.classes.DBControl;
import org.digitall.projects.apps.dbadmin_091.classes.Funciones;
import org.digitall.projects.apps.dbadmin_091.classes.StoredByFunction;

public class StoredBySystemFunctionsManager extends BasicPanel {

    private SaveDataButton btnSavePrivileges = new SaveDataButton();
    private CancelDataButton btnCancelOperation = new CancelDataButton();
    private BasicScrollPane spStoredbyFunction = new BasicScrollPane();
    private BasicScrollPane spStoredNotByFunction = new BasicScrollPane();
    private BasicScrollPane spFunctions = new BasicScrollPane();
    private BasicList functionsList = new BasicList();
    private BasicCheckList storedByFunction = new BasicCheckList();
    private BasicCheckList storedNotByFunction = new BasicCheckList();
    private BasicLabel lblFunction = new BasicLabel();
    private BasicLabel lblSelectedStored = new BasicLabel();
    private BasicLabel lblUnselectedStored = new BasicLabel();
    private BasicLabel lblModulos = new BasicLabel();
    private BasicLabel lblSChemes = new BasicLabel();
    private JCombo cbModulos = new JCombo();
    private JCombo cbSchemes = new JCombo();
    private DBAdminMain manager;
    private StoredByFunction storedFunction = new StoredByFunction();

    public StoredBySystemFunctionsManager(DBAdminMain _manager) {
	manager = _manager;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(577, 486));
	spStoredbyFunction.setBounds(new Rectangle(280, 75, 285, 150));
	spStoredbyFunction.setSize(new Dimension(285, 150));
	spStoredNotByFunction.setBounds(new Rectangle(280, 250, 285, 150));
	spStoredNotByFunction.setSize(new Dimension(285, 150));
	spFunctions.setBounds(new Rectangle(15, 75, 245, 325));
	spFunctions.setSize(new Dimension(243, 325));
	this.add(lblSChemes, null);
	this.add(cbSchemes, null);
	this.add(cbModulos, null);
	this.add(lblModulos, null);
	this.add(lblUnselectedStored, null);
	this.add(lblSelectedStored, null);
	this.add(lblFunction, null);
	spStoredbyFunction.getViewport().add(storedByFunction);
	add(spStoredbyFunction, null);
	spStoredNotByFunction.getViewport().add(storedNotByFunction);
	add(spStoredNotByFunction, null);
	spFunctions.getViewport().add(functionsList);
	add(spFunctions, null);
	add(btnSavePrivileges, null);
	add(btnCancelOperation, null);
	btnSavePrivileges.setText("Guardar\ncambios");
	btnCancelOperation.setText("Cancelar\ncambios");
	functionsList.setSize(new Dimension(243, 320));
	functionsList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
					    if (functionsList.getSelectedIndex() > -1) {
					        loadData();
					    }
					}

				    }
	);
	functionsList.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
				    functionList_mouseClicked(e);
				}

			    }
	);
	storedByFunction.setSize(new Dimension(243, 173));
	btnSavePrivileges.setBounds(new Rectangle(410, 410, 70, 60));
	btnSavePrivileges.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     saveFunctionsByUser_actionPerformed(e);
					 }

				     }
	);
	btnCancelOperation.setBounds(new Rectangle(495, 410, 70, 60));
	btnCancelOperation.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      cancelFunctionsByUser_actionPerformed(e);
					  }

				      }
	);
	lblFunction.setText("Funciones");
	lblFunction.setBounds(new Rectangle(15, 55, 105, 15));
	lblSelectedStored.setText("Stored a las que pertenece");
	lblSelectedStored.setBounds(new Rectangle(280, 55, 175, 15));
	lblUnselectedStored.setText("Stored");
	lblUnselectedStored.setBounds(new Rectangle(280, 230, 140, 15));
	lblModulos.setText("Módulo");
	lblModulos.setBounds(new Rectangle(15, 5, 105, 15));
	cbModulos.setBounds(new Rectangle(15, 25, 245, 20));
	cbModulos.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbModulos.getSelectedIndex() > -1) {
						    loadFunctions();
						}
					    }
					}

				    }
	);
	cbSchemes.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbSchemes.getSelectedIndex() > -1) {
						    loadData();
						}
					    }
					}

				    }
	);
	cbSchemes.setBounds(new Rectangle(280, 25, 245, 20));
	lblSChemes.setText("Esquema");
	lblSChemes.setBounds(new Rectangle(280, 5, 105, 15));
    }

    public void boot(){
	cbSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	//if(DBControl.existFunction("admin.getallmodulos","false")){
	    cbModulos.loadJCombo("admin.getallmodulos","false");
	    loadFunctions();
	//}
    }
    
    private void saveStoredByFunction(){
	String query = "";
	int idFuncion = ((DBFunction)functionsList.getSelectedValue()).getId();
	for (int i = 0; i < storedByFunction.getModel().getSize(); i++) {
	    BasicCheckableListItem stored = (BasicCheckableListItem)storedByFunction.getModel().getElementAt(i);
	    if (!stored.isSelected()) {
		storedFunction.setIdStoredByFunction(stored.getId());
		storedFunction.retrieveData();
		storedFunction.setEstado("*");
		storedFunction.saveData();
	        saveDataGroup(false,idFuncion,stored.getName());
	    }
	}
	for (int i = 0; i < storedNotByFunction.getModel().getSize(); i++) {
	    BasicCheckableListItem stored = (BasicCheckableListItem)storedNotByFunction.getModel().getElementAt(i);
	    if (stored.isSelected()) {
		storedFunction = new StoredByFunction();
	        DBFunction function = (DBFunction)functionsList.getSelectedValue();
	        storedFunction.setIdFuncion(function.getId());
		storedFunction.setNombreStored(""+cbSchemes.getSelectedItem()+"."+stored.getName());
		storedFunction.saveData();
		saveDataGroup(true,function.getId(),stored.getName());
	    }
	}
	if (LibSQL.exActualizar('a', query)) {
	    loadData();
	} else {
	    Advisor.messageBox("Error al intentar asignar los privilegios", "Error");
	}
    }
    
    private void saveDataGroup(boolean _isSelect, int _idFuncion,String _nombreFuncion){
	String query = "";
	Funciones funcion = new Funciones();
	funcion.setIdFuncion(_idFuncion);
	funcion.retrieveData();
	String _group = funcion.getGrupo();
		String _function = cbSchemes.getSelectedItem().toString() + "." + _nombreFuncion;
		String priv = "'" + _group + "','" + _function + "',";
		query += "REVOKE ALL PRIVILEGES ON FUNCTION " + _function + " FROM PUBLIC;\n";
		query += "REVOKE ALL PRIVILEGES ON FUNCTION " + _function + " FROM GROUP " + _group + ";\n";
		if(_isSelect){
		    query += "GRANT EXECUTE ON FUNCTION " + _function + " TO GROUP " + _group + ";\n";    
		}
	if (LibSQL.exActualizar('a', query)) {
	    
	} 
	else {
	    Advisor.messageBox("Error al intentar asignar los permisos", "Error");
	}
    }
    
    private void loadData(){
	if(functionsList.getModel().getSize()>0){
	    DBFunction function = (DBFunction)functionsList.getSelectedValue();
	    loadStoredByFunction(function.getId());    
	}
    }
    
    private void functionList_mouseClicked(MouseEvent e) {
	if (functionsList.getSelectedIndex() >= 0) {
	    if (e.getClickCount() == 1) {
		 DBFunction dataFunction = (DBFunction)functionsList.getSelectedValue();
		 loadStoredByFunction(dataFunction.getId());
	    } else if (e.getClickCount() == 2) {
		storedByFunction.setEnabled(true);
		storedNotByFunction.setEnabled(true);
		btnCancelOperation.setEnabled(true);
		btnSavePrivileges.setEnabled(true);
	    }
	}
    }

    private void saveFunctionsByUser_actionPerformed(ActionEvent e) {
	saveStoredByFunction();
    }

    private void cancelFunctionsByUser_actionPerformed(ActionEvent e) {
	loadData();
    }

    private void loadFunctions() {
	String query = "SELECT funciones.idfuncion AS id, funciones.nombre AS funcion, funciones.descripcion AS descripcion, funciones.grupo, modulos.nombre AS modulo FROM admin.funciones INNER JOIN admin.modulos ON modulos.idmodulo = funciones.idmodulo WHERE funciones.estado <> '*' AND funciones.idmodulo = " + cbModulos.getSelectedValue() + " AND funciones.grupo <> '' ORDER BY funciones.nombre";
	ResultSet rs = LibSQL.exQuery(query);
	Vector _functions = new Vector();
	try {
	    while (rs.next()) {
		_functions.add(new DBFunction(rs.getInt("id"),rs.getString("funcion"),0));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	functionsList.setListData(_functions);
	btnCancelOperation.setEnabled(false);
	btnSavePrivileges.setEnabled(false);
	functionsList.setSelectedIndex(0);
    }

    private void loadStoredByFunction(int _idFuncion) {
	String consultaIn = "SELECT idstoredbyfunction, nombrestored AS nombre FROM admin.storedbyfunction WHERE storedbyfunction.idfuncion = "+_idFuncion +" AND storedbyfunction.estado <> '*' AND storedbyfunction.nombrestored <> '' AND storedbyfunction.nombrestored LIKE '" + cbSchemes.getSelectedItem() +"%' ORDER BY nombrestored";
	String consultaNotIn = "SELECT proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname = '" +
	cbSchemes.getSelectedItem() + "' AND '" + 
	cbSchemes.getSelectedItem() + ".' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' NOT IN (SELECT distinct admin.storedbyfunction.nombrestored FROM admin.storedbyfunction,admin.funciones WHERE storedbyfunction.idfuncion = " + _idFuncion + " AND storedbyfunction.estado <> '*' AND funciones.idmodulo = " + 
	cbModulos.getSelectedValue() + ") ORDER BY declaration";
	ResultSet rs = LibSQL.exQuery(consultaIn);
	Vector _storedByFunction = new Vector();
	Vector _storedNotByFunction = new Vector();
	try {
	    while (rs.next()) {
		String nombre = rs.getString("nombre");
		nombre = nombre.substring(nombre.indexOf(".")+1,nombre.length());
		BasicCheckableListItem item = new BasicCheckableListItem(rs.getInt("idstoredbyfunction"), nombre);
		item.setSelected(true);
		_storedByFunction.add(item);
	    }
	    rs = LibSQL.exQuery(consultaNotIn);
	    while (rs.next()) {
		BasicCheckableListItem item = new BasicCheckableListItem(0, rs.getString("declaration"));
		item.setSelected(false);
		_storedNotByFunction.add(item);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	storedByFunction.setListData(_storedByFunction);
	storedByFunction.setEnabled(false);
	storedNotByFunction.setListData(_storedNotByFunction);
	storedNotByFunction.setEnabled(false);
	btnCancelOperation.setEnabled(false);
	btnSavePrivileges.setEnabled(false);
	storedByFunction.setSelectedIndex(0);
	storedNotByFunction.setSelectedIndex(0);
    }
}
