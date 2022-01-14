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
 * UsersSystemFunctionsManager.java
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

import javax.swing.JButton;
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
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.DBAdminMain;
import org.digitall.projects.apps.dbadmin_091.classes.DBControl;
import org.digitall.projects.apps.dbadmin_091.classes.Funciones;

public class UsersSystemFunctionsManager extends BasicPanel {

    private SaveDataButton btnSavePrivileges = new SaveDataButton();
    private CancelDataButton btnCancelOperation = new CancelDataButton();
    private RefreshGridButton btnRefresh = new RefreshGridButton();
    
    private BasicScrollPane spFunctionsbyUser = new BasicScrollPane();
    private BasicScrollPane spFunctionsNotByUser = new BasicScrollPane();
    private BasicScrollPane spUsers = new BasicScrollPane();
    private BasicList usersList = new BasicList();
    private BasicCheckList functionsByUser = new BasicCheckList();
    private BasicCheckList functionsNotByUser = new BasicCheckList();
    
    private BasicLabel lblUsers = new BasicLabel();
    private BasicLabel lblSelectedFunction = new BasicLabel();
    private BasicLabel lblUnselectedFunction = new BasicLabel();
    private BasicLabel lblModulos = new BasicLabel();
    
    private JCombo cbModulos = new JCombo();
    
    private Vector userVector = new Vector();
    private DBAdminMain manager;
    private DBAdminPanel dbAdminPanel;
    private Funciones funcion = new Funciones();

    public UsersSystemFunctionsManager(DBAdminMain _manager) {
	//manager = _manager;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public UsersSystemFunctionsManager(DBAdminPanel _manager) {
        dbAdminPanel = _manager;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(660, 486));
	spFunctionsbyUser.setBounds(new Rectangle(280, 25, 365, 175));
	spFunctionsbyUser.setSize(new Dimension(365, 173));
	functionsNotByUser.setSize(new Dimension(285, 172));
	spFunctionsNotByUser.setBounds(new Rectangle(280, 225, 365, 175));
	spFunctionsNotByUser.setSize(new Dimension(365, 174));
	spUsers.setBounds(new Rectangle(15, 75, 245, 325));
	spUsers.setSize(new Dimension(243, 325));
        this.add(btnRefresh, null);
        this.add(cbModulos, null);
	this.add(lblModulos, null);
	this.add(lblUnselectedFunction, null);
	this.add(lblSelectedFunction, null);
	this.add(lblUsers, null);
	spFunctionsbyUser.getViewport().add(functionsByUser);
	add(spFunctionsbyUser, null);
	spFunctionsNotByUser.getViewport().add(functionsNotByUser);
	add(spFunctionsNotByUser, null);
	spUsers.getViewport().add(usersList);
	add(spUsers, null);
        add(btnSavePrivileges, null);
        add(btnCancelOperation, null);
        btnSavePrivileges.setText("Guardar\ncambios");
	btnCancelOperation.setText("Cancelar\ncambios");
	usersList.setSize(new Dimension(243, 320));
	usersList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
					    if (!e.getValueIsAdjusting()) {
						if (usersList.getSelectedIndex() > -1) {
						    TransferListModel tr = (TransferListModel)usersList.getSelectedValue();
						    loadFunctionsByUser(tr.getReference());
						    //loadFunctionsByUser(usersList.getSelectedValue().toString());
						}
					    }
					}

				    }
	);
	usersList.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
				    users_mouseClicked(e);
				}

			    }
	);
	functionsByUser.setSize(new Dimension(243, 173));
	btnSavePrivileges.setBounds(new Rectangle(500, 410, 70, 60));
	btnSavePrivileges.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     saveFunctionsByUser_actionPerformed(e);
					 }

				     }
	);
	btnCancelOperation.setBounds(new Rectangle(580, 410, 70, 60));
	btnCancelOperation.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      cancelFunctionsByUser_actionPerformed(e);
					  }

				      }
	);
	lblUsers.setText("Usuarios");
	lblUsers.setBounds(new Rectangle(15, 55, 105, 15));
	lblSelectedFunction.setText("Funcionalidades asignadas");
	lblSelectedFunction.setBounds(new Rectangle(280, 5, 175, 15));
	lblUnselectedFunction.setText("Funcionalidades no asignadas");
	lblUnselectedFunction.setBounds(new Rectangle(280, 205, 190, 15));
	lblModulos.setText("Módulo");
	lblModulos.setBounds(new Rectangle(15, 5, 105, 15));
	cbModulos.setBounds(new Rectangle(15, 25, 217, 20));
	cbModulos.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbModulos.getSelectedIndex() > -1) {
						    if(usersList.getSelectedIndex() > -1){
							loadFunctionsByUser(usersList.getSelectedValue().toString());   
						    }
						}
					    }
					}

				    }
	);
        btnRefresh.setBounds(new Rectangle(235, 25, 25, 20));
        btnRefresh.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnRefresh_actionPerformed(e);
                }
            });
        btnRefresh.setToolTipText("Recargar lista ");
    }

    /**2010-03-09 (moraless)*/
    public void boot(){
	if(DBControl.existFunction("admin","getallmodulos","boolean")){
	    cbModulos.setGeneralItem(true);
	    cbModulos.loadJCombo("admin.getallmodulos","false");
	    loadUsers();    
	}
    }
    
    public void setEnabledTab(boolean _enabled){
	cbModulos.setEnabled(_enabled);
	functionsByUser.setEnabled(_enabled);
	functionsNotByUser.setEnabled(_enabled);
	usersList.setEnabled(_enabled);
	btnCancelOperation.setEnabled(_enabled);
	btnSavePrivileges.setEnabled(_enabled);
    }
    
    /**2009-10-05(moraless)*/	
    private void loadUsers() {
	//String query = "SELECT distinct usename as name, usesysid as uid FROM pg_user ORDER BY usename";
	String query = "SELECT distinct usename as nameref, org.getpersonnamebyusername(usename::character varying) as name, usesysid as uid FROM pg_user ORDER BY usename";
	ResultSet rs = LibSQL.exQuery(query);
	Vector _users = new Vector();
	try {
	    while (rs.next()) {
		TransferListModel transfer = new TransferListModel(rs.getInt("uid"),rs.getString("name"),rs.getString("nameref"));
	        _users.add(transfer);
		//_users.add(rs.getString("name"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	userVector = _users;
	usersList.setListData(_users);
	//manager.setUserVector(userVector);
	dbAdminPanel.setUserVector(userVector);
	btnCancelOperation.setEnabled(false);
	btnSavePrivileges.setEnabled(false);
    }
    
    private void loadFunctionsByUser(String _user) {
	String consultaIn = "SELECT funciones.idfuncion, funciones.nombre FROM admin.funciones WHERE grupo IN (SELECT groname as name FROM pg_group WHERE (SELECT usesysid FROM pg_user WHERE usename = '" + _user + "')= any(grolist) order by groname) AND estado <> '*'";
	//String consultaIn = "SELECT funciones.idfuncion, funciones.nombre FROM admin.funciones WHERE grupo IN (SELECT groname as name FROM pg_group WHERE (SELECT usesysid FROM pg_user WHERE usename = '" + _user + "')= any(grolist) order by groname) AND funciones.idmodulo = "+cbModulos.getSelectedValue();
	//AND funciones.idmodulo = 3
	String consultaNotIn = "SELECT funciones.idfuncion, funciones.nombre FROM admin.funciones WHERE funciones.grupo <> '' AND funciones.grupo NOT IN (SELECT groname as name FROM pg_group WHERE (SELECT usesysid FROM pg_user WHERE usename = '" + _user + "')= any(grolist) order by groname) AND estado <> '*'";
	//String consultaNotIn = "SELECT funciones.idfuncion, funciones.nombre FROM admin.funciones WHERE funciones.grupo <> '' AND funciones.grupo NOT IN (SELECT groname as name FROM pg_group WHERE (SELECT usesysid FROM pg_user WHERE usename = '" + _user + "')= any(grolist) order by groname) AND funciones.idmodulo = "+cbModulos.getSelectedValue();
	if(!cbModulos.getSelectedItem().equals("Todos")){
	    consultaIn += " AND funciones.idmodulo = "+cbModulos.getSelectedValue();
	    consultaNotIn += "AND funciones.idmodulo = "+cbModulos.getSelectedValue();
	}
	ResultSet rs = LibSQL.exQuery(consultaIn);
	Vector _functionsByUser = new Vector();
	Vector _functionsNotByUser = new Vector();
	try {
	    while (rs.next()) {
		BasicCheckableListItem item = new BasicCheckableListItem(rs.getInt("idfuncion"), rs.getString("nombre"));
		item.setSelected(true);
		_functionsByUser.add(item);
	    }
	    rs = LibSQL.exQuery(consultaNotIn);
	    while (rs.next()) {
	        BasicCheckableListItem item = new BasicCheckableListItem(rs.getInt("idfuncion"), rs.getString("nombre"));
	        item.setSelected(false);
	        _functionsNotByUser.add(item);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	functionsByUser.setListData(_functionsByUser);
	functionsByUser.setEnabled(false);
	functionsNotByUser.setListData(_functionsNotByUser);
	functionsNotByUser.setEnabled(false);
	btnCancelOperation.setEnabled(false);
	btnSavePrivileges.setEnabled(false);
	functionsByUser.setSelectedIndex(0);
	functionsNotByUser.setSelectedIndex(0);
    }
    
    /**2009-10-05(moraless)*/
    private void saveUsersByFunction(){
	String query = "";
	String nombreGrupo = "";
	String usuario = ((TransferListModel)usersList.getSelectedValue()).getReference();
	for (int i = 0; i < functionsByUser.getModel().getSize(); i++) {
	    ////BasicCheckableListItem function = (BasicCheckableListItem)functionsByUser.getModel().getElementAt(i);
	    BasicCheckableListItem function = (BasicCheckableListItem)functionsByUser.getModel().getElementAt(i);
	    //nombreGrupo = getGroupName(functionsByUser.getSelectedValue().toString());
	    //nombreGrupo = getGroupName(function.getName());
	    nombreGrupo = getGroupName(function.getId());
	    if (!function.isSelected()) {
		query += "REVOKE " + nombreGrupo + " FROM " + usuario + ";\n";
	    }
	}
	for (int i = 0; i < functionsNotByUser.getModel().getSize(); i++) {
	    BasicCheckableListItem function = (BasicCheckableListItem)functionsNotByUser.getModel().getElementAt(i);
	    //nombreGrupo = getGroupName(function.getName());
	    nombreGrupo = getGroupName(function.getId());
	    if (function.isSelected()) {
		query += "GRANT " + nombreGrupo + " TO " + usuario + ";\n";
	    }
	}
	if (LibSQL.exActualizar('a', query)) {
	    //loadFunctionsByUser(usersList.getSelectedValue().toString());
	    loadFunctionsByUser(usuario);
	} else {
	    Advisor.messageBox("Error al intentar asignar los privilegios", "Error");
	}
    }
    
    private String getGroupName(String _nombreFuncion){
	String retorno = "";
	String consulta = "SELECT grupo FROM admin.funciones WHERE upper(funciones.nombre) = upper('"+ _nombreFuncion +"')";
	ResultSet result = LibSQL.exQuery(consulta);
	try {
	    if(result.next()){
		retorno = result.getString("grupo");
	    }
	} catch (SQLException e) {
	    // TODO
	}
	return retorno;
    }
    
    private String getGroupName(int _idFuncion){
	funcion.setIdFuncion(_idFuncion);
	funcion.retrieveData();
	return funcion.getGrupo();
    }

    private void users_mouseClicked(MouseEvent e) {
	if (usersList.getSelectedIndex() >= 0) {
	    if (e.getClickCount() == 1) {
		 //loadFunctionsByUser(usersList.getSelectedValue().toString());
	    } else if (e.getClickCount() == 2) {
		functionsByUser.setEnabled(true);
		functionsNotByUser.setEnabled(true);
		btnCancelOperation.setEnabled(true);
		btnSavePrivileges.setEnabled(true);
	    }
	}
    }

    private void saveFunctionsByUser_actionPerformed(ActionEvent e) {
	saveUsersByFunction();
    }

    private void cancelFunctionsByUser_actionPerformed(ActionEvent e) {
	loadFunctionsByUser(usersList.getSelectedValue().toString());
    }
    
    /**2010-03-09 (moraless)*/
    private void btnRefresh_actionPerformed(ActionEvent e) {
        cbModulos.loadJCombo("admin.getallmodulos","false");
        loadUsers();    
    }
}
