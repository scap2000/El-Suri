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
 * SystemFunctionUsersManager.java
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

public class SystemFunctionUsersManager extends BasicPanel {

    private SaveDataButton btnSavePrivileges = new SaveDataButton();
    private CancelDataButton btnCancelOperation = new CancelDataButton();
    private RefreshGridButton btnRefresh = new RefreshGridButton();
    
    private BasicScrollPane spUsersbyFunction = new BasicScrollPane();
    private BasicScrollPane spUsersNotByFunction = new BasicScrollPane();
    private BasicScrollPane spFunctions = new BasicScrollPane();
    
    private BasicList functionsList = new BasicList();
    private BasicCheckList usersByFunction = new BasicCheckList();
    private BasicCheckList usersNotByFunction = new BasicCheckList();
    
    private BasicLabel lblFunctions = new BasicLabel();
    private BasicLabel lblSelectedUser = new BasicLabel();
    private BasicLabel lblUnselectedUser = new BasicLabel();
    private BasicLabel lblModulos = new BasicLabel();
    
    private JCombo cbModulos = new JCombo();
    
    private DBAdminMain manager;
    private DBAdminPanel dbAdminPanel;
    private Funciones funcion = new Funciones();
    

    public SystemFunctionUsersManager(DBAdminMain _manager) {
	//manager = _manager;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    

    public SystemFunctionUsersManager(DBAdminPanel _manager) {
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
	spUsersbyFunction.setBounds(new Rectangle(360, 25, 285, 173));
	spUsersbyFunction.setSize(new Dimension(285, 173));
	usersNotByFunction.setSize(new Dimension(285, 172));
	spUsersNotByFunction.setBounds(new Rectangle(360, 225, 285, 174));
	spUsersNotByFunction.setSize(new Dimension(285, 174));
	spFunctions.setBounds(new Rectangle(15, 80, 325, 320));
	spFunctions.setSize(new Dimension(325, 320));
        this.add(btnRefresh, null);
        this.add(cbModulos, null);
	this.add(lblModulos, null);
        this.add(lblUnselectedUser, null);
        this.add(lblSelectedUser, null);
        this.add(lblFunctions, null);
        spUsersbyFunction.getViewport().add(usersByFunction);
        add(spUsersbyFunction, null);
        spUsersNotByFunction.getViewport().add(usersNotByFunction);
        add(spUsersNotByFunction, null);
        spFunctions.getViewport().add(functionsList);
	add(spFunctions, null);
        add(btnSavePrivileges, null);
        add(btnCancelOperation, null);
        btnSavePrivileges.setText("Guardar\ncambios");
	btnCancelOperation.setText("Cancelar\ncambios");
	functionsList.setSize(new Dimension(243, 333));
	functionsList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e){
					    if (!e.getValueIsAdjusting()) {
						if (functionsList.getSelectedIndex() > -1){
						    loadUsersByFunction(functionsList.getSelectedValue().toString());
						    //btnSavePrivileges.setEnabled(true);
						}
					    }
					}

				    }
	);
	functionsList.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
				    users_mouseClicked(e);
				}

			    }
	);
	usersByFunction.setSize(new Dimension(243, 173));
	btnSavePrivileges.setBounds(new Rectangle(490, 410, 70, 60));

	btnSavePrivileges.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSavePrivileges_actionPerformed(e);
		}
	    }
	);
	btnCancelOperation.setBounds(new Rectangle(570, 410, 70, 60));
	btnCancelOperation.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      cancelUsersByFunction_actionPerformed(e);
					  }

				      }
	);
	lblFunctions.setText("Funcionalidades");
	lblFunctions.setBounds(new Rectangle(15, 60, 105, 15));
	lblSelectedUser.setText("Usuarios a los que pertenece");
	lblSelectedUser.setBounds(new Rectangle(360, 5, 175, 15));
	lblUnselectedUser.setText("Usuarios ");
	lblUnselectedUser.setBounds(new Rectangle(360, 205, 140, 15));
	lblModulos.setText("Módulos");
	lblModulos.setBounds(new Rectangle(15, 5, 105, 15));
	cbModulos.setBounds(new Rectangle(15, 25, 295, 20));
	cbModulos.setSize(new Dimension(295, 20));
	cbModulos.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbModulos.getSelectedIndex() > -1) {
						    //loadFunctionsByScheme(cbModulos.getSelectedItem().toString());
						    loadFunctions();
						}
                                                if (cbModulos.getModel().getSize() > 0) {
                                                    btnRefresh.setEnabled(true);
                                                } else {
                                                    btnRefresh.setEnabled(false);
                                                }
					    }
					}

				    }
	);
        btnRefresh.setBounds(new Rectangle(315, 25, 25, 20));
        btnRefresh.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnRefresh_actionPerformed(e);
                }
            });
        btnRefresh.setToolTipText("Recargar lista ");
        //boot();
    }

    /**2010-03-09(moraless)*/
    public void boot() {
	if(DBControl.existFunction("admin","getallmodulos","boolean")){
	    cbModulos.setGeneralItem(true);//linea agregada
	    cbModulos.loadJCombo("admin.getallmodulos","false");
	    loadFunctions();
	}
    }
    
    public void setEnabledTab(boolean _enabled){
	cbModulos.setEnabled(_enabled);
	functionsList.setEnabled(_enabled);
	usersByFunction.setEnabled(_enabled);
	usersNotByFunction.setEnabled(_enabled);
	btnSavePrivileges.setEnabled(_enabled);
	btnCancelOperation.setEnabled(_enabled);
    }
    
    private void loadFunctions() {
	//String query = "SELECT idfuncion, nombre FROM admin.funciones WHERE funciones.grupo = '' order by nombre";
	//String query = "SELECT idfuncion, nombre FROM admin.funciones WHERE funciones.idmodulo = " + cbModulos.getSelectedValue() +"AND funciones.grupo <> '' AND funciones.estado <> '*' order by nombre";	 
	//ResultSet rs = LibSQL.exQuery(query);
	String params = "";
	if(!cbModulos.getSelectedItem().equals("Todos")){
	    params = ""+cbModulos.getSelectedValue();
	}
	else{
	    params = "-1";
	}
	ResultSet rs = LibSQL.exFunction("admin.getfunctionsassigned",params);
	Vector _functions = new Vector();
	try {
	    while (rs.next()) {
		_functions.add(new DBFunction(rs.getInt("idfuncion"),rs.getString("nombre"),rs.getInt("idmodulo")));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	btnSavePrivileges.setEnabled(false);
	btnCancelOperation.setEnabled(false);
	functionsList.setListData(_functions);
	functionsList.setSelectedIndex(0);
    }

    /**2009-10-05(moraless)*/
    private void loadUsersByFunction(String _nombreFuncion) {
	//String query = "SELECT usename as name, usesysid as uid, usesuper, groname, usesysid=any(pg_group.grolist) as belongs FROM pg_user, pg_group  WHERE groname = '" + getGroupName(_nombreFuncion) + "' order by usename";
	String query = "SELECT org.getpersonnamebyusername(usename::character varying) as name, usename as nameref, usesysid as uid, usesuper, groname, usesysid=any(pg_group.grolist) as belongs FROM pg_user, pg_group  WHERE groname = '" + getGroupName(_nombreFuncion) + "' order by usename";
	ResultSet rs = LibSQL.exQuery(query);
	Vector _usersByGroup = new Vector();
	Vector _usersByNotGroup = new Vector();
	try {
	    while (rs.next()) {
		//BasicCheckableListItem item = new BasicCheckableListItem(rs.getInt("uid"), rs.getString("name"));
		TransferListModel item = new TransferListModel(rs.getInt("uid"), rs.getString("name"),rs.getString("nameref"));
		item.setSelected(rs.getBoolean("belongs"));
	        if (item.isSelected()) {
	            _usersByGroup.add(item);
	        } else {
	            _usersByNotGroup.add(item);
	        }
		
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	usersByFunction.setListData(_usersByGroup);
	usersByFunction.setEnabled(false);
	usersNotByFunction.setListData(_usersByNotGroup);
	usersNotByFunction.setEnabled(false);
	usersByFunction.setSelectedIndex(0);
	btnSavePrivileges.setEnabled(false);
	btnCancelOperation.setEnabled(false);
    }
    
    /**2009-10-05(moraless)*/
    private void saveUsersByFunction(){
	String query = "";
	//String nombreGrupo = getGroupName(functionsList.getSelectedValue().toString());
	DBFunction dbFunction = (DBFunction)functionsList.getSelectedValue();
	String nombreGrupo = getGroupName(dbFunction.getId());
	//String nombreGrupo = getGroupName(functionsList.getSelectedValue().toString());
	for (int i = 0; i < usersByFunction.getModel().getSize(); i++) {
	    //BasicCheckableListItem user = (BasicCheckableListItem)usersByFunction.getModel().getElementAt(i);
	    TransferListModel user = (TransferListModel)usersByFunction.getModel().getElementAt(i);
	    if (!user.isSelected()) {
		//query += "REVOKE " + nombreGrupo + " FROM " + user.getName() + ";\n";
		 query += "REVOKE " + nombreGrupo + " FROM " + user.getReference() + ";\n";
	    }
	}
	for (int i = 0; i < usersNotByFunction.getModel().getSize(); i++) {
	   // System.out.println("size = " + usersNotByFunction.getModel().getSize());
	    //BasicCheckableListItem user = (BasicCheckableListItem)usersNotByFunction.getModel().getElementAt(i);
	    TransferListModel user = (TransferListModel)usersNotByFunction.getModel().getElementAt(i);
	    if (user.isSelected()) {
		//query += "GRANT " + nombreGrupo + " TO " + user.getName() + ";\n";
		 query += "GRANT " + nombreGrupo + " TO " + user.getReference() + ";\n";
	    }
	}
	if (LibSQL.exActualizar('a', query)) {
	    loadUsersByFunction(functionsList.getSelectedValue().toString());
	} else {
	    Advisor.messageBox("Error al intentar asignar los privilegios", "Error");
	}
    }
    
    
    private String getGroupName(String _nombreFuncion){
	//funcion.retrieveData(_nombreFuncion,Integer.parseInt(cbModulos.getSelectedValue().toString()));
	DBFunction dbFunction = (DBFunction)functionsList.getSelectedValue();
	funcion.retrieveData(_nombreFuncion,dbFunction.getIdModulo());
	return funcion.getGrupo();
    }
    
    private String getGroupName(int _idFuncion){
	funcion.setIdFuncion(_idFuncion);
	funcion.retrieveData();
	return funcion.getGrupo();
    }
    
    private void users_mouseClicked(MouseEvent e) {
	if (functionsList.getSelectedIndex() >= 0) {
	    if (e.getClickCount() == 1) {
		//loadUsersByFunction(functionsList.getSelectedValue().toString());
	    } else if (e.getClickCount() == 2) {
		usersByFunction.setEnabled(true);
		usersNotByFunction.setEnabled(true);
		btnCancelOperation.setEnabled(true);
		btnSavePrivileges.setEnabled(true);
	    }
	}
    }

    private void cancelUsersByFunction_actionPerformed(ActionEvent e) {
	loadUsersByFunction(functionsList.getSelectedValue().toString());
    }

    private void btnSavePrivileges_actionPerformed(ActionEvent e) {
	saveUsersByFunction();
    }

    /**2010-03-09 (moraless)*/
    private void btnRefresh_actionPerformed(ActionEvent e) {
        cbModulos.loadJCombo("admin.getallmodulos","false");
        loadFunctions();
    }
}
