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
 * SystemFunctionsByStoredManager.java
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

public class SystemFunctionsByStoredManager extends BasicPanel {

    private SaveDataButton btnSavePrivileges = new SaveDataButton();
    private CancelDataButton btnCancelOperation = new CancelDataButton();
    private BasicScrollPane spFunctionsbyStored = new BasicScrollPane();
    private BasicScrollPane spFunctionsNotByStored = new BasicScrollPane();
    private BasicScrollPane spStored = new BasicScrollPane();
    private BasicList StoredList = new BasicList();
    private BasicCheckList functionsByStored = new BasicCheckList();
    private BasicCheckList functionsNotByStored = new BasicCheckList();
    private BasicLabel lblStored = new BasicLabel();
    private BasicLabel lblSelectedFunctions = new BasicLabel();
    private BasicLabel lblUnselectedFunctions = new BasicLabel();
    private BasicLabel lblModulos = new BasicLabel();
    private BasicLabel lblSChemes = new BasicLabel();
    private JCombo cbModulos = new JCombo();
    private JCombo cbSchemes = new JCombo();
    private DBAdminMain manager;
    private StoredByFunction storedFunction = new StoredByFunction();

    public SystemFunctionsByStoredManager(DBAdminMain _manager) {
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
	spFunctionsbyStored.setBounds(new Rectangle(320, 75, 245, 150));
	spFunctionsbyStored.setSize(new Dimension(245, 150));
	spFunctionsNotByStored.setBounds(new Rectangle(320, 250, 245, 150));
	spFunctionsNotByStored.setSize(new Dimension(245, 150));
	spStored.setBounds(new Rectangle(15, 75, 280, 325));
	spStored.setSize(new Dimension(280, 325));
	this.add(lblSChemes, null);
	this.add(cbSchemes, null);
	this.add(cbModulos, null);
	this.add(lblModulos, null);
	this.add(lblUnselectedFunctions, null);
	this.add(lblSelectedFunctions, null);
	this.add(lblStored, null);
	spFunctionsbyStored.getViewport().add(functionsByStored);
	add(spFunctionsbyStored, null);
	spFunctionsNotByStored.getViewport().add(functionsNotByStored);
	add(spFunctionsNotByStored, null);
	spStored.getViewport().add(StoredList);
	add(spStored, null);
	add(btnSavePrivileges, null);
	add(btnCancelOperation, null);
	btnSavePrivileges.setText("Guardar\ncambios");
	btnCancelOperation.setText("Cancelar\ncambios");
	StoredList.setSize(new Dimension(243, 320));
	StoredList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
					    if (StoredList.getSelectedIndex() > -1) {
					        loadFunctionsByStored(StoredList.getSelectedValue().toString());
					    }
					}

				    }
	);
	StoredList.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
				    functionList_mouseClicked(e);
				}

			    }
	);
	functionsByStored.setSize(new Dimension(243, 173));
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
	lblStored.setText("Stored");
	lblStored.setBounds(new Rectangle(15, 55, 105, 15));
	lblSelectedFunctions.setText("Funciones a los que pertenece");
	lblSelectedFunctions.setBounds(new Rectangle(320, 55, 175, 15));
	lblUnselectedFunctions.setText("Funciones");
	lblUnselectedFunctions.setBounds(new Rectangle(320, 230, 140, 15));
	lblModulos.setText("Módulo");
	lblModulos.setBounds(new Rectangle(320, 5, 105, 15));
	cbModulos.setBounds(new Rectangle(320, 25, 245, 20));
	cbModulos.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbModulos.getSelectedIndex() > -1) {
						    if(StoredList.getSelectedIndex() > -1){
							loadFunctionsByStored(StoredList.getSelectedValue().toString());
						    }
						}
					    }
					}

				    }
	);
	cbSchemes.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbSchemes.getSelectedIndex() > -1) {
						    loadFunctionsByScheme(cbSchemes.getSelectedItem().toString());
						}
					    }
					}

				    }
	);
	cbSchemes.setBounds(new Rectangle(15, 25, 245, 20));
	lblSChemes.setText("Esquema");
	lblSChemes.setBounds(new Rectangle(15, 5, 105, 15));
    }

    public void boot(){
	cbSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	loadFunctionsByScheme(cbSchemes.getSelectedItem().toString());
	//if(DBControl.existFunction("admin.getallmodulos","true")){
	    cbModulos.loadJCombo("admin.getallmodulos","false");
	//}
    }
    
    private void loadFunctionsByScheme(String _scheme) {
	String query = "SELECT p.oid AS prooid, p.proname as name, pg_catalog.format_type(p.prorettype, NULL) AS proresult, " + "CASE WHEN array_to_string(p.proargnames, '') IS NULL THEN '' ELSE array_to_string(p.proargnames, '') END AS proargnames, " + "pg_catalog.oidvectortypes(p.proargtypes) AS proarguments, " + "proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration " + "FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang " + "WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname = '" + _scheme + "' ORDER BY p.proname, proresult ";
	ResultSet rs = LibSQL.exQuery(query);
	Vector _functionsByScheme = new Vector();
	try {
	    while (rs.next()) {
		_functionsByScheme.add(rs.getString("declaration"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	StoredList.setListData(_functionsByScheme);
	StoredList.setSelectedIndex(0);
	if (StoredList.getSelectedIndex() > -1) {
	    loadFunctionsByStored(StoredList.getSelectedValue().toString());
	}
    }
    
    private void saveStoredByFunction(){
	String query = "";
	int idFuncion = -1;
	for (int i = 0; i < functionsByStored.getModel().getSize(); i++) {
	    BasicCheckableListItem function = (BasicCheckableListItem)functionsByStored.getModel().getElementAt(i);
	    if (!function.isSelected()) {
		idFuncion = function.getId();
		storedFunction.setIdStoredByFunction(function.getId());
		storedFunction.retrieveData();
		//storedFunction.setNombreStored("");
		storedFunction.setEstado("*");
		storedFunction.saveData();
	        saveDataGroup(false,storedFunction.getIdFuncion(),StoredList.getSelectedValue().toString());
	    }
	}
	for (int i = 0; i < functionsNotByStored.getModel().getSize(); i++) {
	    BasicCheckableListItem function = (BasicCheckableListItem)functionsNotByStored.getModel().getElementAt(i);
	    if (function.isSelected()) {
		storedFunction = new StoredByFunction();
	        String stored = StoredList.getSelectedValue().toString();
	        storedFunction.setIdFuncion(function.getId());
		storedFunction.setNombreStored(""+cbSchemes.getSelectedItem()+"."+stored);
		storedFunction.saveData();
		saveDataGroup(true,storedFunction.getIdFuncion(),stored);
	    }
	}
	if (LibSQL.exActualizar('a', query)) {
	    loadFunctionsByStored(StoredList.getSelectedValue().toString());
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
	DBFunction function = (DBFunction)StoredList.getSelectedValue();
	loadFunctionsByStored(StoredList.getSelectedValue().toString());
    }
    
    private void functionList_mouseClicked(MouseEvent e) {
	if (StoredList.getSelectedIndex() >= 0) {
	    if (e.getClickCount() == 1) {
		 loadFunctionsByStored(StoredList.getSelectedValue().toString());
	    } else if (e.getClickCount() == 2) {
		functionsByStored.setEnabled(true);
		functionsNotByStored.setEnabled(true);
		btnCancelOperation.setEnabled(true);
		btnSavePrivileges.setEnabled(true);
	    }
	}
    }

    private void saveFunctionsByUser_actionPerformed(ActionEvent e) {
	saveStoredByFunction();
    }

    private void cancelFunctionsByUser_actionPerformed(ActionEvent e) {
	loadFunctionsByStored(StoredList.getSelectedValue().toString());
    }

    private void loadFunctionsByStored(String _nombreStored) {
	String consultaIn = "SELECT storedbyfunction.idstoredbyfunction, funciones.nombre AS nombrefuncion FROM admin.storedbyfunction,admin.funciones WHERE storedbyfunction.nombrestored = '" +
	cbSchemes.getSelectedItem()+"."+_nombreStored+"' AND storedbyfunction.estado <> '*'  AND admin.funciones.idfuncion = storedbyfunction.idfuncion AND funciones.idmodulo = " +
	cbModulos.getSelectedValue() + "ORDER BY funciones.nombre";
	
	String consultaNotIn = "SELECT distinct funciones.idfuncion, funciones.nombre AS nombrefuncion FROM admin.storedbyfunction,admin.funciones WHERE funciones.idmodulo = " +
	cbModulos.getSelectedValue() + "AND funciones.grupo <> '' AND funciones.idfuncion NOT IN (SELECT distinct idfuncion FROM admin.storedbyfunction WHERE storedbyfunction.estado <> '*' AND storedbyfunction.nombrestored = '" +
	cbSchemes.getSelectedItem()+"."+StoredList.getSelectedValue() + "')";
	ResultSet rs = LibSQL.exQuery(consultaIn);
	Vector _functionByStored = new Vector();
	Vector _functionNotByStored = new Vector();
	try {
	    while (rs.next()) {
		BasicCheckableListItem item = new BasicCheckableListItem(rs.getInt("idstoredbyfunction"), rs.getString("nombrefuncion"));
		item.setSelected(true);
		_functionByStored.add(item);
	    }
	    rs = LibSQL.exQuery(consultaNotIn);
	    while (rs.next()) {
		BasicCheckableListItem item = new BasicCheckableListItem(rs.getInt("idfuncion"), rs.getString("nombrefuncion"));
		item.setSelected(false);
		_functionNotByStored.add(item);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	functionsByStored.setListData(_functionByStored);
	functionsByStored.setEnabled(false);
	functionsNotByStored.setListData(_functionNotByStored);
	functionsNotByStored.setEnabled(false);
	btnCancelOperation.setEnabled(false);
	btnSavePrivileges.setEnabled(false);
	functionsByStored.setSelectedIndex(0);
	functionsNotByStored.setSelectedIndex(0);
    }
}
