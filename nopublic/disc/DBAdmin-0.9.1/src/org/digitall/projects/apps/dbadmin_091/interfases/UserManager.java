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
 * UserManager.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicCheckList;
import org.digitall.lib.components.basic.BasicCheckableListItem;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AddUserButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.DBAdminMain;


public class UserManager extends BasicPanel {

    private BasicList usersList = new BasicList();
    private BasicCheckList groupsByUser = new BasicCheckList();
    private BasicScrollPane spGroupsByUser = new BasicScrollPane();
    private BasicCheckList groupsNotByUser = new BasicCheckList();
    private BasicScrollPane spGroupsNotByUser = new BasicScrollPane();
    private BasicScrollPane spUsers = new BasicScrollPane();
    
    private SaveDataButton btnSavePrivileges = new SaveDataButton();
    private CancelDataButton btnCancelOperation = new CancelDataButton();
    private AddUserButton btnAddUser = new AddUserButton();
    private DeleteButton btnDeleteUser = new DeleteButton();
    private ModifyButton btnModifyUser = new ModifyButton();
    private DeleteButton btnPreDelete = new DeleteButton();
    
    private BasicLabel lblUser = new BasicLabel();
    private BasicLabel lblSelectedGroup = new BasicLabel();
    private BasicLabel lblUnselectedGroup = new BasicLabel();
    private BasicLabel lblReferencia = new BasicLabel();
    
    private JCombo cbTablesSchemes = new JCombo();
    
    private Vector<DBRole> groupVector = new Vector<DBRole>();
    private Vector userVector = new Vector();
    private DBAdminMain manager;
    private DBAdminPanel dbAdminPanel;

    public UserManager(DBAdminMain _manager) {
	//manager = _manager;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public UserManager(DBAdminPanel _manager) {
        dbAdminPanel = _manager;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(577, 486));
        this.setBounds(new Rectangle(10, 10, 660, 486));
        spGroupsByUser.setBounds(new Rectangle(295, 25, 245, 200));
	spGroupsByUser.setSize(new Dimension(245, 200));
	groupsNotByUser.setSize(new Dimension(245, 172));
	spGroupsNotByUser.setBounds(new Rectangle(295, 260, 245, 200));
	spGroupsNotByUser.setSize(new Dimension(245, 200));
	spUsers.setBounds(new Rectangle(20, 25, 260, 435));
	spUsers.setSize(new Dimension(260, 435));
        this.add(lblReferencia, null);
        this.add(lblUnselectedGroup, null);
        this.add(lblSelectedGroup, null);
        this.add(lblUser, null);
        spGroupsByUser.getViewport().add(groupsByUser);
        add(spGroupsByUser, null);
        spGroupsNotByUser.getViewport().add(groupsNotByUser);
        add(spGroupsNotByUser, null);
        spUsers.getViewport().add(usersList);
	add(spUsers, null);
        add(btnModifyUser, null);
        add(btnDeleteUser, null);
        add(btnAddUser, null);
        add(btnSavePrivileges, null);
        add(btnCancelOperation, null);
        add(btnPreDelete, null);
        btnAddUser.setText("Agregar\nusuario");
	btnPreDelete.setText("Desvincular\np/eliminación");
	btnDeleteUser.setText("Eliminar\nusuario");
	btnModifyUser.setText("Modificar\nusuario");
	btnSavePrivileges.setText("Guardar\ncambios");
	btnCancelOperation.setText("Cancelar\ncambios");
	usersList.setSize(new Dimension(243, 333));
	usersList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
					    if (!e.getValueIsAdjusting()) {
						if (usersList.getSelectedIndex() > -1) {
						    loadGroupsByUser(((TransferListModel)usersList.getSelectedValue()).getReference());
                                                    setEnabledButtons(true);
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
	groupsByUser.setSize(new Dimension(243, 173));
	btnSavePrivileges.setBounds(new Rectangle(560, 285, 90, 60));
	btnSavePrivileges.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     saveGroupsByUser_actionPerformed(e);
					 }

				     }
	);
	btnCancelOperation.setBounds(new Rectangle(560, 350, 90, 60));
	btnCancelOperation.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      cancelGroupsByUser_actionPerformed(e);
					  }

				      }
	);
	btnAddUser.setBounds(new Rectangle(560, 25, 90, 60));
	btnAddUser.setHorizontalAlignment(SwingConstants.CENTER);
	btnAddUser.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnAddUser_actionPerformed(e);
				  }

			      }
	);
	btnDeleteUser.setBounds(new Rectangle(560, 220, 90, 60));
	btnDeleteUser.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnDeleteUser_actionPerformed(e);
				     }

				 }
	);
	btnPreDelete.setBounds(new Rectangle(560, 155, 90, 60));
	btnPreDelete.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnRevokeUsers_actionPerformed(e);
				    }

				}
	);
	lblUser.setText("Usuarios");
	lblUser.setBounds(new Rectangle(20, 5, 105, 15));
	lblSelectedGroup.setText("Grupos a los que pertenece");
	lblSelectedGroup.setBounds(new Rectangle(295, 5, 175, 15));
	lblUnselectedGroup.setText("Grupos a los que NO pertenece");
	lblUnselectedGroup.setBounds(new Rectangle(295, 240, 225, 15));
        lblReferencia.setText("*  SUPER USUARIO");
        lblReferencia.setBounds(new Rectangle(545, 445, 115, 15));
        lblReferencia.setFont(new Font("Lucida Sans", 1, 12));
        btnModifyUser.setBounds(new Rectangle(560, 90, 90, 60));
	btnModifyUser.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnModifyUser_actionPerformed(e);
				     }

				 }
	);
    }

    public void boot(){
	loadUsers();
	cbTablesSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	cbTablesSchemes.setSelectedIndex(0);
        btnSavePrivileges.setEnabled(false);
        btnCancelOperation.setEnabled(false);
        setEnabledButtons(false);
    }
    
    public void setEnabledTab(boolean _enabled){
	usersList.setEnabled(_enabled);
	groupsByUser.setEnabled(_enabled);
	groupsNotByUser.setEnabled(_enabled);
        if(!_enabled){
            btnAddUser.setEnabled(_enabled);
            btnCancelOperation.setEnabled(_enabled);
            btnDeleteUser.setEnabled(_enabled);
            btnModifyUser.setEnabled(_enabled);
            btnPreDelete.setEnabled(_enabled);
            btnSavePrivileges.setEnabled(_enabled);    
        }
    }

    /** 2009-12-04(moraless)*/
    private void revokeAllToUser(boolean _drop) {
	String owner = "postgres";
	if (usersList.getSelectedValues().length > -1) {
	    String query = "";
	    for (int i = 0; i < usersList.getSelectedValues().length; i++) {
	    	//String _userName = usersList.getSelectedValues()[i].toString();
                TransferListModel _objetoUsuario = (TransferListModel)usersList.getSelectedValues()[i];
	        String _userName = _objetoUsuario.getReference();
                //System.out.println("user seleccionado = "+usersList.getsele);
	        for (int j = 0; j < cbTablesSchemes.getItemCount(); j++) {
	            String _scheme = cbTablesSchemes.getItemAt(j).toString();
	            query += "REVOKE USAGE ON SCHEMA " + _scheme + " FROM " + _userName + ";\n";
	            ResultSet _tables = LibSQL.exQuery("SELECT relname as name FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND relkind = 'r' ORDER BY relname");
	            try {
	                while (_tables.next()) {
	                    query += "REVOKE ALL PRIVILEGES ON " + _scheme + "." + _tables.getString("name") + " FROM " + _userName + ";\n";
	                }
	            } catch (SQLException x) {
	                x.printStackTrace();
	            }
		    
	            ResultSet _functions = LibSQL.exQuery("SELECT proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname = '" + _scheme + "' ORDER BY p.proname");
	            try {
	                while (_tables.next()) {
	                    query += "REVOKE ALL PRIVILEGES ON " + _scheme + "." + _functions.getString("declaration") + " FROM " + _userName + ";\n";
	                }
	            } catch (SQLException x) {
	                x.printStackTrace();
	            }
	        }
		
	        //obtener los esquemas que pertenezcan a un usuario
	        ResultSet _schemes = LibSQL.exQuery("SELECT nspname as scheme FROM pg_namespace INNER JOIN pg_authid ON pg_authid.oid = pg_namespace.nspowner WHERE rolname = '" + _userName + "' AND nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	        try {
	            //cambiar el propietario de los esquemas que pertenecen al usuario a eliminar
	            while (_schemes.next()) {
	                query += "ALTER SCHEMA " + _schemes.getString("scheme") + " OWNER TO postgres;\n";
	            }
	        } catch (SQLException x) {
	            x.printStackTrace();
	        }
	        //obtener las tablas que pertenezcan a un usuario
	        ResultSet _deps = LibSQL.exQuery("SELECT nspname||'.'||relname AS table FROM pg_class INNER JOIN pg_authid ON pg_authid.oid = pg_class.relowner " + "INNER JOIN pg_namespace ON pg_class.relnamespace = pg_namespace.oid " + "WHERE rolname = '" + _userName + "' AND nspname NOT LIKE 'pg_%'");
	        try {
	            while (_deps.next()) {
	                //cambiar el propietario de las tablas que pertenecen al usuario a eliminar
	                query += "ALTER TABLE " + _deps.getString("table") + " OWNER TO " + owner + ";\n";
	            }
	        } catch (SQLException x) {
	            x.printStackTrace();
	        }
	        //obtener los stored que pertenezcan a un usuario
	        ResultSet _procs = LibSQL.exQuery("SELECT nspname ||'.'||proname|| '(' || pg_catalog.oidvectortypes(pg_proc.proargtypes) ||  ')' as declaration FROM pg_proc " + "INNER JOIN pg_catalog.pg_namespace ON pg_namespace.oid = pg_proc.pronamespace " + "INNER JOIN pg_authid ON pg_authid.oid = pg_proc.proowner " + "WHERE rolname = '" + _userName + "'");
	        try {
	            while (_procs.next()) {
	                //cambiar el propietario de los stored que pertenecen al usuario a eliminar
	                query += "ALTER FUNCTION " + _procs.getString("declaration") + " OWNER TO " + owner + ";\n";
	            }
	        } catch (SQLException x) {
	            x.printStackTrace();
	        }
		
	        //obtener los tipos que pertenezcan a un usuario
	        ResultSet _types = LibSQL.exQuery("SELECT nspname||'.'||typname AS type,rolname FROM pg_type INNER JOIN pg_authid ON pg_authid.oid = pg_type.typowner INNER JOIN pg_catalog.pg_namespace ON pg_namespace.oid = pg_type.typnamespace WHERE rolname = '" + _userName + "' ORDER BY typname;");
	        try {
	            ////cambiar el propietario de los tipos que pertenecen al usuario a eliminar
	            while (_types.next()) {
	                //query += "ALTER TYPE " + _types.getString("type") + " OWNER TO " + owner + ";\n";
	            }
	        } catch (SQLException x) {
	            x.printStackTrace();
	        }
		
	        //obtener conversiones que pertenezcan a un usuario
	        /*ResultSet _conversion = LibSQL.exQuery("SELECT nspname||'.'||conname AS conversion FROM pg_conversion INNER JOIN pg_authid ON pg_authid.oid = pg_conversion.conowner INNER JOIN pg_catalog.pg_namespace ON pg_namespace.oid = pg_conversion.connamespace WHERE rolname = '" + _userName + "' ORDER BY conname;");
	        try {
	            ////cambiar el propietario de conversiones que pertenecen al usuario a eliminar
	            while (_conversion.next()) {
	                query += "ALTER CONVERSION " + _conversion.getString("conversion") + " OWNER TO " + owner + ";\n";
	            }
	        } catch (SQLException x) {
	            x.printStackTrace();
	        }*/
		
	         //obtener lenguajes que pertenezcan a un usuario
	         /*ResultSet _lenguaje = LibSQL.exQuery("SELECT lanname AS lenguaje FROM pg_language INNER JOIN pg_authid ON pg_authid.oid = pg_language.lanowner WHERE rolname = '" + _userName + "' ORDER BY lanname;");
	         try {
	             ////cambiar el propietario de lenguajes que pertenecen al usuario a eliminar
	             while (_lenguaje.next()) {
	                 query += "ALTER LANNGUAGE " + _lenguaje.getString("lenguaje") + " OWNER TO " + owner + ";\n";
	             }
	         } catch (SQLException x) {
	             x.printStackTrace();
	         }*/
		
		
	        //No se modifican los propietarios de las vistas porque la función ALTER OWNER no está disponible 
		//para la versión 8.1 de postgres.
		
		if (_drop) {
		    query += "DROP USER " + _userName + ";\n";
		}

	        ResultSet _users = LibSQL.exQuery("SELECT count(*) AS qty FROM pg_class INNER JOIN pg_namespace ON pg_class.relnamespace = pg_namespace.oid " + "WHERE relname='users' AND nspname='org'");
	        try {
	            if (_users.next()) {
			if (_users.getInt("qty") > 0){
			    query += " DELETE FROM org.users WHERE username = '" + _userName + "';\n";
			}
	            }
	        } catch (SQLException x) {
	            x.printStackTrace();
	        }
		if (LibSQL.exActualizar('a', query)) {
		    loadUsers();
		} else {
		    Advisor.messageBox("Error al intentar borrar el usuario " + usersList.getSelectedValue(), "Error");
		}
	    }
	}
    }

    private void btnDeleteUser_actionPerformed(ActionEvent e) {
	if (usersList.getSelectedValues().length > 0) {
	    if (Advisor.question("Borrar usuario(s)", "¿Esta seguro de borrar el(los) usuario(s)? "+ getAllSelectedUsers())) {
		revokeAllToUser(true);
	    }
	}
	else{
	    Advisor.messageBox("Debe seleccionar un Usuario","Mensaje");
	}
    }
    
    private String getAllSelectedUsers(){
        int[] usuariosSeleccioandos = usersList.getSelectedIndices();
        String mensajeUsuariosSeleccionados = "\n";
        for(int i = 0;i < usuariosSeleccioandos.length;i++){
            mensajeUsuariosSeleccionados += usersList.getModel().getElementAt(usuariosSeleccioandos[i])+"\n";
        }
        return mensajeUsuariosSeleccionados+"";
    }

    private void loadGroupsByUser(String _user) {
	String query = "SELECT grosysid as gid, groname as name, (SELECT usesysid FROM pg_user WHERE usename = '" + _user + "')=any(grolist) as belongs FROM pg_group order by groname";
	ResultSet rs = LibSQL.exQuery(query);
	Vector _groupsByUser = new Vector();
	Vector _groupsNotByUser = new Vector();
	try {
	    while (rs.next()) {
		BasicCheckableListItem item = new BasicCheckableListItem(rs.getInt("gid"), rs.getString("name"));
		item.setSelected(rs.getBoolean("belongs"));
		if (item.isSelected()) {
		    _groupsByUser.add(item);
		} else {
		    _groupsNotByUser.add(item);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	groupsByUser.setListData(_groupsByUser);
	groupsByUser.setEnabled(false);
	groupsNotByUser.setListData(_groupsNotByUser);
	groupsNotByUser.setEnabled(false);
	btnCancelOperation.setEnabled(false);
	btnSavePrivileges.setEnabled(false);
	groupsByUser.setSelectedIndex(0);
	groupsNotByUser.setSelectedIndex(0);
    }

    /**2009-10-05(moraless)*/
    private void loadUsers() {
	//String query = "SELECT distinct usename as name, usesysid as uid FROM pg_user ORDER BY usename";
	//String query = "SELECT distinct usename as nameref, org.getpersonnamebyusername(usename::character varying) as name, usesysid as uid FROM pg_user ORDER BY usename";
	//String query = "SELECT pg_user.usename as nameref, CASE WHEN pg_user.usesuper THEN '*'|| org.getpersonnamebyusername(usename::character varying) ELSE org.getpersonnamebyusername(usename::character varying) END AS name, pg_user.usesysid as uid FROM pg_catalog.pg_user ORDER BY pg_user.usesuper DESC, pg_user.usename;";
	String query = "SELECT pg_roles.rolname as nameref, CASE WHEN pg_roles.rolsuper THEN '*'|| org.getpersonnamebyusername(rolname::character varying) ELSE org.getpersonnamebyusername(rolname::character varying) END AS name, pg_roles.oid as uid FROM pg_catalog.pg_roles ORDER BY pg_roles.rolsuper DESC, pg_roles.rolname;";
	ResultSet rs = LibSQL.exQuery(query);
	Vector _users = new Vector();
	try {
	    while (rs.next()) {
		TransferListModel user = new TransferListModel(rs.getInt("uid"),rs.getString("name"),rs.getString("nameref"));
	        _users.add(user);
		//_users.add(rs.getString("name"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	userVector = _users;
	usersList.setListData(_users);
	//manager.setUserVector(userVector);
	dbAdminPanel.setUserVector(userVector);
    }
    
     /*private void loadUsers() {
	 String query = "SELECT distinct usename as name, usesysid as uid FROM pg_user ORDER BY usename";
	 ResultSet rs = LibSQL.exQuery(query);
	 Vector _users = new Vector();
	 try {
	     while (rs.next()) {
		 _users.add(rs.getString("name"));
	     }
	 } catch (SQLException e) {
	     e.printStackTrace();
	 }
	 userVector = _users;
	 usersList.setListData(_users);
	 manager.setUserVector(userVector);
     }*/

    private void users_mouseClicked(MouseEvent e) {
	if (usersList.getSelectedIndex() >= 0) {
	    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
		//loadGroupsByUser(usersList.getSelectedValue().toString());
		loadUsers();
	    } else if (e.getClickCount() == 2) {
		groupsByUser.setEnabled(true);
		groupsNotByUser.setEnabled(true);
		btnCancelOperation.setEnabled(true);
		btnSavePrivileges.setEnabled(true);
	    }
	}
    }

    /**2009-10-05(moraless)*/
    private void saveGroupsByUser_actionPerformed(ActionEvent e) {
	String query = "";
	TransferListModel userSelected = ((TransferListModel)usersList.getSelectedValue());
	for (int i = 0; i < groupsByUser.getModel().getSize(); i++) {
	    BasicCheckableListItem group = (BasicCheckableListItem)groupsByUser.getModel().getElementAt(i);
	    if (!group.isSelected()) {
	        query += "REVOKE " + group.getName() + " FROM " + userSelected.getReference() + ";\n";
		//query += "REVOKE " + group.getName() + " FROM " + usersList.getSelectedValue().toString() + ";\n";
	    }
	}
	for (int i = 0; i < groupsNotByUser.getModel().getSize(); i++) {
	    BasicCheckableListItem group = (BasicCheckableListItem)groupsNotByUser.getModel().getElementAt(i);
	    if (group.isSelected()) {
	        query += "GRANT " + group.getName() + " TO " + userSelected.getReference() + ";\n";
		//query += "GRANT " + group.getName() + " TO " + usersList.getSelectedValue().toString() + ";\n";
	    }
	}
	if (LibSQL.exActualizar('a', query)) {
	    //loadGroupsByUser(usersList.getSelectedValue().toString());
	    loadGroupsByUser(userSelected.getReference());
	} else {
	    Advisor.messageBox("Error al intentar asignar los permisos", "Error");
	}
    }

    private void cancelGroupsByUser_actionPerformed(ActionEvent e) {
	loadGroupsByUser(usersList.getSelectedValue().toString());
        String _userName = usersList.getSelectedValues()[0].toString();
        //System.out.println("name = "+_userName);
        TransferListModel _objetoUsuario = (TransferListModel)usersList.getSelectedValues()[0];
        _userName = _objetoUsuario.getReference();
        //System.out.println("nameref = "+_userName);
    }

    private void btnAddUser_actionPerformed(ActionEvent e) {
	NewUserPanel newUserPanel = new NewUserPanel();
	ComponentsManager.centerWindow(newUserPanel);
	newUserPanel.setModal(true);
	newUserPanel.setVisible(true);
	loadUsers();
    }

    private void btnRevokeUsers_actionPerformed(ActionEvent e) {
	if (usersList.getSelectedValues().length > 0) {
	    if (Advisor.question("Desvincular usuario(s)", "¿Esta seguro de desvincular el(los) usuario(s) de las bases de datos?"+getAllSelectedUsers())) {
		revokeAllToUser(false);
	    }
	}
	else{
	    Advisor.messageBox("Debe seleccionar un Usuario","Mensaje");
	}
    }

    private void btnModifyUser_actionPerformed(ActionEvent e) {
	TransferListModel userSelected = ((TransferListModel)usersList.getSelectedValue());
	if (usersList.getSelectedValues().length > 0) {
	    if (!usersList.getSelectedValue().equals(null)) {
		String userName = usersList.getSelectedValue().toString();
		NewUserPanel newUserPanel = new NewUserPanel();
		/* (2009-10-05) Santiago: Mensaje para el codificador
		 * El metodo setUserName necesita el nombre del rol de la base de datos
		 * no el nombre de la persona
		 * Se modifico la linea siguiente
		 * y se deberia probar en el resto del sistema si existe el mismo error
		 * */
		//if (newUserPanel.setUserName(userName)) {
		 if (newUserPanel.setUserName(userSelected.getReference())) {
		    ComponentsManager.centerWindow(newUserPanel);
		    newUserPanel.setModal(true);
		    newUserPanel.setVisible(true);
		    loadUsers();
		} else {
		    Advisor.messageBox("Incongruencia de Datos, revise las Tablas relacionadas al Usuario", "Usuario no válido");
		}
	    }
	}
	else{
	    Advisor.messageBox("Debe seleccionar un Usuario","Mensaje");
	}
    }

    public void setGroupVector(Vector<DBRole> _groupVector) {
	groupVector = _groupVector;
    }

    private void setEnabledButtons(boolean _enabled){
        btnAddUser.setEnabled(_enabled);
        btnModifyUser.setEnabled(_enabled);
        btnDeleteUser.setEnabled(_enabled);
        btnPreDelete.setEnabled(_enabled);
        /*btnCancelOperation.setEnabled(_enabled);
        btnSavePrivileges.setEnabled(_enabled);*/
    }
}
