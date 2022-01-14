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
 * GroupManager.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckList;
import org.digitall.lib.components.basic.BasicCheckableListItem;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AddGroupButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.DBAdminMain;
import org.digitall.projects.apps.dbadmin_091.SystemConfiguration;
import org.digitall.projects.apps.dbadmin_091.classes.DBAdminConfiguration;

public class GroupManager extends BasicPanel {

    private DeleteButton btnRevokeGroups = new DeleteButton();
    private BasicButton btnGrantPublicAccess = new BasicButton();
    private AssignButton btnMakeQueryUser = new AssignButton();
    private BasicButton btnExportAllPrivileges = new BasicButton();
    private DeleteButton btnDeleteGroup = new DeleteButton();
    private AddGroupButton btnAddGroup = new AddGroupButton();
    private SaveDataButton saveUsersByGroup = new SaveDataButton();
    private CancelDataButton cancelUsersByGroup = new CancelDataButton();
    
    private BasicScrollPane spGroups = new BasicScrollPane();
    private BasicScrollPane spUsersByGroup = new BasicScrollPane();
    private BasicList groupsList = new BasicList();
    private BasicCheckList usersByGroup = new BasicCheckList();
    
    private JCombo cbTablesSchemes = new JCombo();
    
    private BasicLabel lblGroups = new BasicLabel();
    private BasicLabel lblUsers = new BasicLabel();
    
    private Vector<DBRole> groupVector = new Vector<DBRole>();
    private Vector userVector = new Vector();
    private Vector dbVector = new Vector();
    private Vector nonPublicSchemes = new Vector();
    private Vector publicTables = new Vector();
    private Vector publicFunctions = new Vector();
    
    private DBAdminMain manager;
    private String selectedDB = "";
    private DBAdminPanel dbAdminPanel;
    
    public GroupManager(DBAdminMain _manager) {
	//manager = _manager;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public GroupManager(DBAdminPanel _manager) {
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
	spGroups.setBounds(new Rectangle(20, 25, 250, 435));
	spGroups.setSize(new Dimension(250, 435));
	spUsersByGroup.setBounds(new Rectangle(290, 25, 260, 435));
	spUsersByGroup.setSize(new Dimension(260, 435));
        this.add(lblUsers, null);
        this.add(lblGroups, null);
        add(btnRevokeGroups, null);
        add(btnGrantPublicAccess, null);
        add(btnDeleteGroup, null);
        add(btnAddGroup, null);
        add(saveUsersByGroup, null);
        add(cancelUsersByGroup, null);
        spGroups.getViewport().add(groupsList);
	add(spGroups, null);
        spUsersByGroup.getViewport().add(usersByGroup);
        add(spUsersByGroup, null);
        add(btnMakeQueryUser, null);
        add(btnExportAllPrivileges, null);
	groupsList.addListSelectionListener(new ListSelectionListener() {
					 public void valueChanged(ListSelectionEvent e) {
					     if (!e.getValueIsAdjusting()) {
						 if (groupsList.getSelectedIndex() > -1) {
						     loadUsersByGroup(groupsList.getSelectedValue().toString());
                                                     setEnabledButtons(true);
						 }
					     }
					 }

				     }
	);
	groupsList.addMouseListener(new MouseAdapter() {

				 public void mouseClicked(MouseEvent e) {
				     groups_mouseClicked(e);
				 }

			     }
	);
	lblGroups.setText("Grupos");
	lblGroups.setBounds(new Rectangle(20, 5, 80, 15));
	lblUsers.setText("Usuarios");
	lblUsers.setBounds(new Rectangle(290, 5, 75, 15));
	saveUsersByGroup.setBounds(new Rectangle(560, 300, 90, 60));
	saveUsersByGroup.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    saveUsersByGroup_actionPerformed(e);
					}

				    }
	);
	cancelUsersByGroup.setBounds(new Rectangle(560, 365, 90, 60));
	cancelUsersByGroup.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      cancelUsersByGroup_actionPerformed(e);
					  }

				      }
	);
	btnAddGroup.setBounds(new Rectangle(570, 30, 70, 60));
	btnAddGroup.setHorizontalAlignment(SwingConstants.CENTER);
	btnAddGroup.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnAddGroup_actionPerformed(e);
				   }

			       }
	);
	btnDeleteGroup.setBounds(new Rectangle(560, 155, 90, 60));
	btnDeleteGroup.setHorizontalAlignment(SwingConstants.CENTER);
	btnDeleteGroup.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnDeleteGroup_actionPerformed(e);
				      }

				  }
	);
	//btnRevokeGroups.setText("Pre-Delete");
	btnRevokeGroups.setText("Desvincular\np/eliminación");
	btnRevokeGroups.setBounds(new Rectangle(560, 90, 90, 60));
	btnRevokeGroups.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnRevokeGroup_actionPerformed(e);
				       }

				   }
	);
	btnGrantPublicAccess.setText("Grant Schemes");
	btnGrantPublicAccess.setBounds(new Rectangle(165, 450, 240, 20));
	btnGrantPublicAccess.addActionListener(new ActionListener() {

					    public void actionPerformed(ActionEvent e) {
						btnGrantPublicAccess_actionPerformed(e);
					    }

					}
	);
	//btnMakeQueryUser.setText("Transfer Query Privileges");
	btnMakeQueryUser.setText("Transferir\n Privilegios SELECT ");
	btnExportAllPrivileges.setText("Export All Privileges");
	btnMakeQueryUser.setBounds(new Rectangle(560, 225, 90, 65));
	btnExportAllPrivileges.setBounds(new Rectangle(170, 430, 240, 20));
	btnExportAllPrivileges.addActionListener(new ActionListener() {

					      public void actionPerformed(ActionEvent e) {
						  btnExportAllPrivileges_actionPerformed(e);
					      }

					  }
	);
	btnMakeQueryUser.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnMakeQueryUser_actionPerformed(e);
					}

				    }
	);
	refreshPublicTables();
	refreshPublicFunctions();
	btnAddGroup.setText("Agregar \ngrupo");
	btnAddGroup.setSize(new Dimension(70, 60));
	btnDeleteGroup.setText("Eliminar \ngrupo");
	saveUsersByGroup.setText("Guardar \ncambios");
	saveUsersByGroup.setText("Guardar \ncambios");
	cancelUsersByGroup.setText("Cancelar \ncambios");
	btnExportAllPrivileges.setVisible(false);
	btnGrantPublicAccess.setVisible(false);
    }

    public void boot() {
	loadGroups();
	cbTablesSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	cbTablesSchemes.setSelectedIndex(0);
	resetSchemesPrivileges();
    }
    
    public void boot(ResultSet _schemes) {
	loadGroups();
	cbTablesSchemes.loadJCombo(_schemes);
	cbTablesSchemes.setSelectedIndex(0);
	resetSchemesPrivileges();
        saveUsersByGroup.setEnabled(false);
        cancelUsersByGroup.setEnabled(false);
        setEnabledButtons(false);
    }
    
    public void setEnabledTab(boolean _enabled){
	usersByGroup.setEnabled(_enabled);
	groupsList.setEnabled(_enabled);
        if(!_enabled){
            btnAddGroup.setEnabled(_enabled);
            btnDeleteGroup.setEnabled(_enabled);
            btnExportAllPrivileges.setEnabled(_enabled);
            btnGrantPublicAccess.setEnabled(_enabled);
            btnMakeQueryUser.setEnabled(_enabled);
            btnRevokeGroups.setEnabled(_enabled);
            cancelUsersByGroup.setEnabled(_enabled);
            saveUsersByGroup.setEnabled(_enabled);    
        }
	
    }

    /**2009-10-05(moraless)*/
    private void loadUsersByGroup(String _group) {
	String query = "SELECT usename as nameref,org.getpersonnamebyusername(usename::character varying) as name, usesysid as uid, usesuper, groname, usesysid=any(pg_group.grolist) as belongs FROM pg_user, pg_group  WHERE groname = '" + _group + "' order by usename";
	ResultSet rs = LibSQL.exQuery(query);
	Vector _usersByGroup = new Vector();
	try {
	    while (rs.next()) {
		//BasicCheckableListItem item = new BasicCheckableListItem(rs.getInt("uid"), rs.getString("name"));
		TransferListModel item = new TransferListModel(rs.getInt("uid"), rs.getString("name"),rs.getString("nameref"));
		item.setSelected(rs.getBoolean("belongs"));
		_usersByGroup.add(item);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	usersByGroup.setListData(_usersByGroup);
	usersByGroup.setEnabled(false);
	cancelUsersByGroup.setEnabled(false);
	saveUsersByGroup.setEnabled(false);
	usersByGroup.setSelectedIndex(0);
    }

    private void groups_mouseClicked(MouseEvent e) {
	if (groupsList.getSelectedIndex() >= 0) {
	    if (e.getClickCount() == 1) {
		//loadUsersByGroup(groupsList.getSelectedValue().toString());
	    } else if (e.getClickCount() == 2) {
		usersByGroup.setEnabled(true);
		cancelUsersByGroup.setEnabled(true);
		saveUsersByGroup.setEnabled(true);
	    }
	}
    }

    /**2009-10-05(moraless)*/
    private void saveUsersByGroup_actionPerformed(ActionEvent e) {
	String query = "";
	for (int i = 0; i < usersByGroup.getModel().getSize(); i++) {
	    //BasicCheckableListItem user = (BasicCheckableListItem)usersByGroup.getModel().getElementAt(i);
	    TransferListModel user = (TransferListModel)usersByGroup.getModel().getElementAt(i);
	    if (user.isSelected()) {
		//query += "GRANT " + groupsList.getSelectedValue().toString() + " TO " + user.getName() + ";\n";
		query += "GRANT " + groupsList.getSelectedValue().toString() + " TO " + user.getReference() + ";\n";
	    } else {
		//query += "REVOKE " + groupsList.getSelectedValue().toString() + " FROM " + user.getName() + ";\n";
		query += "REVOKE " + groupsList.getSelectedValue().toString() + " FROM " + user.getReference() + ";\n";
	    }
	}
	if (LibSQL.exActualizar('a', query)) {
	    loadUsersByGroup(groupsList.getSelectedValue().toString());
	} else {
	    Advisor.messageBox("Error al intentar asignar los privilegios", "Error");
	}
    }

    private void cancelUsersByGroup_actionPerformed(ActionEvent e) {
	loadUsersByGroup(groupsList.getSelectedValue().toString());
    }

    private void loadGroups() {
	String query = "SELECT distinct grosysid as gid, groname as name FROM pg_group order by groname";
	ResultSet rs = LibSQL.exQuery(query);
	Vector _groups = new Vector();
	try {
	    while (rs.next()) {
		_groups.add(new DBRole(rs.getString("name")));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	groupVector = _groups;
	groupsList.setListData(_groups);
	//manager.setGroupVector(_groups);
	dbAdminPanel.setGroupVector(_groups);
    }

    private String resetSchemesPrivileges() {
	String query = "";
	for (int j = 0; j < cbTablesSchemes.getItemCount(); j++) {
	    if (!nonPublicSchemes.contains(cbTablesSchemes.getItemAt(j).toString())) {
		for (int i = 0; i < groupsList.getSelectedValues().length; i++) {
		    query += "REVOKE USAGE ON SCHEMA " + cbTablesSchemes.getItemAt(j).toString() + " FROM GROUP " + groupsList.getSelectedValues()[i] + ";\n";
		}
		query += "GRANT USAGE ON SCHEMA " + cbTablesSchemes.getItemAt(j).toString() + " TO PUBLIC;\n";
	    }
	}
	/*if (LibSQL.exActualizar('a', query)) {
	} else {
	    Advisor.messageBox("Error al intentar darles privilegios a los grupos", "Error");
	}*/
	return query;
    }

    private void btnAddGroup_actionPerformed(ActionEvent e) {
	String _groupName = "";
	_groupName = JOptionPane.showInputDialog("Nombre del grupo: ");
	if (_groupName != null) {
	    String query = "CREATE GROUP " + _groupName.trim();
	    if (LibSQL.exActualizar('a', query)) {
		loadGroups();
		//grantSchemesToGroups();
	    } else {
		Advisor.messageBox("Error al intentar crear el grupo " + _groupName, "Error");
	    }
	}
    }

    private void revokeAllToGroup(boolean _drop) {
	if (groupsList.getSelectedValues().length > 0) {
	    if (Advisor.question("Borrar privilegios a los grupo(s) seleccionado(s)", "Esta a punto de eliminar " + groupsList.getSelectedValues().length + " grupos...\n¿Está seguro?")) {
		StringBuilder query = new StringBuilder();
		for (int i = 0; i < groupsList.getSelectedValues().length; i++) {
		    String _groupName = groupsList.getSelectedValues()[i].toString();
		    for (int j = 0; j < cbTablesSchemes.getItemCount(); j++) {
			String _scheme = cbTablesSchemes.getItemAt(j).toString();
			query.append("REVOKE USAGE ON SCHEMA " + _scheme + " FROM GROUP " + _groupName + ";\n");
			ResultSet _tables = LibSQL.exQuery("SELECT relname as name FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND relkind = 'r' ORDER BY relname");
			try {
			    while (_tables.next()) {
				query.append("REVOKE ALL PRIVILEGES ON " + _scheme + "." + _tables.getString("name") + " FROM GROUP " + _groupName + ";\n");
			    }
			} catch (SQLException x) {
			    x.printStackTrace();
			}
			ResultSet _deps = LibSQL.exQuery("SELECT nspname||'.'||relname AS table FROM pg_class INNER JOIN pg_authid ON pg_authid.oid = pg_class.relowner " + "INNER JOIN pg_namespace ON pg_class.relnamespace = pg_namespace.oid " + "WHERE rolname = '" + _groupName + "'");
			try {
			    while (_deps.next()) {
				query.append("ALTER TABLE " + _deps.getString("table") + " OWNER TO " + Environment.sessionUser + ";\n");
			    }
			} catch (SQLException x) {
			    x.printStackTrace();
			}
			ResultSet _procs = LibSQL.exQuery("SELECT nspname ||'.'||proname|| '(' || pg_catalog.oidvectortypes(pg_proc.proargtypes) ||  ')' as declaration FROM pg_proc " + "INNER JOIN pg_catalog.pg_namespace ON pg_namespace.oid = pg_proc.pronamespace " + "INNER JOIN pg_authid ON pg_authid.oid = pg_proc.proowner " + "WHERE rolname = '" + _groupName + "'");
			try {
			    while (_procs.next()) {
				query.append("ALTER FUNCTION " + _procs.getString("declaration") + " OWNER TO " + Environment.sessionUser + ";\n");
			    }
			} catch (SQLException x) {
			    x.printStackTrace();
			}
		    }
		    if (_drop) {
			query.append("DROP GROUP " + _groupName + ";\n");
		    }
		}
		if (LibSQL.exActualizar('a', query.toString())) {
		    loadGroups();
		} else {
		    Advisor.messageBox("Error al intentar borrar el grupo " + groupsList.getSelectedValue(), "Error");
		}
	    }
	}
    }

    private void btnDeleteGroup_actionPerformed(ActionEvent e) {
	if (groupsList.getSelectedValues().length > 0) {
	    revokeAllToGroup(true);
	}
	else{
	    Advisor.messageBox("Debe seleccionar un Grupo","Mensaje");
	}
    }

    private void btnRevokeGroup_actionPerformed(ActionEvent e) {
	revokeAllToGroup(false);
    }

    private void btnMakeQueryUser_actionPerformed(ActionEvent e) {
	if(groupsList.getSelectedValues().length > 0){
	    String _groupName = "";
	    Object[] _groups = groupVector.toArray();
	    DBRole role = (DBRole)JOptionPane.showInputDialog(this, "Nombre del grupo destino: ", "Transferir privilegios solo lectura", JOptionPane.QUESTION_MESSAGE, null, _groups, _groups[0]);
	    if (role != null) {
	        _groupName = role.getName();
	        grantSelectPrivilegesToGroup(_groupName);
	    }    
	} else {
	    Advisor.messageBox("Debe seleccionar el Grupo de Origen de la lista", "Error");
	}
	
    }

    private void grantSelectPrivilegesToGroup(String _newGroup) {
	if (groupsList.getSelectedValues().length > 0) {
	    StringBuilder query = new StringBuilder();
	    String _groupName = groupsList.getSelectedValues()[0].toString();
	    for (int j = 0; j < cbTablesSchemes.getItemCount(); j++) {
		String _scheme = cbTablesSchemes.getItemAt(j).toString();
		ResultSet _tables = LibSQL.exQuery("SELECT relname as name, has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'INSERT') OR " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'UPDATE') OR " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'SELECT') " + " AS admin FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND relkind = 'r' ORDER BY relname");
		try {
		    while (_tables.next()) {
			if (_tables.getBoolean("admin")) {
			    query.append("GRANT SELECT ON " + _scheme + "." + _tables.getString("name") + " TO GROUP " + _newGroup + ";\n");
			}
		    }
		} catch (SQLException x) {
		    x.printStackTrace();
		}
	    }
	    if (LibSQL.exActualizar('a', query.toString())) {
		Advisor.messageBox("Operación concretada, ahora el grupo " + _newGroup + " tiene privilegios de lectura", "Error");
	    } else {
		Advisor.messageBox("Error al intentar la operación", "Error");
	    }
	}
    }

    private void exportAllPrivileges() {
	//System.out.println("SELECT relname as name, has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'INSERT') AS insert, " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'UPDATE') AS update, " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'SELECT') AS select" + " FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND NOT aclcontains(relacl, '=r/admin') AND relkind = 'r' ORDER BY relname");
	//System.out.println("SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration " + "FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang " + "WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname");
	Object[] _dbs = dbVector.toArray();
	String _dbName = "";
	_dbName = (String)JOptionPane.showInputDialog(this, "Nombre de la BD destino: ", "Transferir privilegios", JOptionPane.QUESTION_MESSAGE, null, _dbs, selectedDB.length() > 0 ? selectedDB : _dbs[0]);
	if (_dbName != null) {
	    StringBuilder query = new StringBuilder();
	    query.append("CREATE OR REPLACE FUNCTION org.create_group_if_not_exists(_group character varying) RETURNS boolean AS $BODY$\n" + "DECLARE\n" + "  _qty integer := 0;\n" + "\n" + "BEGIN\n" + "       SELECT INTO _qty count(*) FROM pg_group WHERE groname = _group;\n" + "       IF (_qty = 0)  THEN\n" + "               EXECUTE 'CREATE GROUP ' || _group;\n" + "               RETURN true::boolean;\n" + "       ELSE\n" + "               RETURN false::boolean;\n" + "       END IF;\n" + "END;$BODY$\n" + "    LANGUAGE 'plpgsql' VOLATILE;\n");
	    selectedDB = _dbName;
	    for (int i = 0; i < groupsList.getSelectedValues().length; i++) {
		//System.out.println(groupsList.getSelectedValues()[i]);
		String _groupName = groupsList.getSelectedValues()[i].toString();
		query.append("SELECT org.create_group_if_not_exists('" + _groupName + "');\n");
		for (int j = 0; j < cbTablesSchemes.getItemCount(); j++) {
		    String _scheme = cbTablesSchemes.getItemAt(j).toString();
		    ResultSet _tables = LibSQL.exQuery("SELECT relname as name, has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'INSERT') AS insert, " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'UPDATE') AS update, " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'SELECT') AS select" + " FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND NOT aclcontains(relacl, '=r/" + DBAdminConfiguration.getDbOwner() + "') AND relkind = 'r' ORDER BY relname");
		    try {
			while (_tables.next()) {
			    if (!SystemConfiguration.tablesExclusionList.contains(_scheme + "." + _tables.getString("name"))) {
				//query += "REVOKE ALL PRIVILEGES ON " + _scheme + "." + _tables.getString("name") + " FROM GROUP " + _groupName + ";\n";
				if (_tables.getBoolean("insert") && _tables.getBoolean("update") && _tables.getBoolean("select")) {
				    query.append("GRANT ALL PRIVILEGES ON " + _scheme + "." + _tables.getString("name") + " TO GROUP " + _groupName + ";\n");
				} else {
				    if (_tables.getBoolean("insert")) {
					query.append("GRANT INSERT ON " + _scheme + "." + _tables.getString("name") + " TO GROUP " + _groupName + ";\n");
				    }
				    if (_tables.getBoolean("update")) {
					query.append("GRANT UPDATE ON " + _scheme + "." + _tables.getString("name") + " TO GROUP " + _groupName + ";\n");
				    }
				    if (_tables.getBoolean("select")) {
					query.append("GRANT SELECT ON " + _scheme + "." + _tables.getString("name") + " TO GROUP " + _groupName + ";\n");
				    }
				}
			    } else {
			//	System.out.println("Excluyendo tabla " + _scheme + "." + _tables.getString("name"));
			    }
			}
		    } 
		    catch (SQLException x) {
			x.printStackTrace();
		    }

		    ResultSet _functions = LibSQL.exQuery("SELECT declaration, has_function_privilege('" + _groupName + "', declaration, 'EXECUTE') AS execute FROM (SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname = '" + _scheme + "' AND NOT aclcontains(proacl, '=X/" + DBAdminConfiguration.getDbOwner() + "') ORDER BY p.proname) AS foo;");
		    try {
			while (_functions.next()) {
			    boolean contains = false;
			    contains = SystemConfiguration.tablesExclusionList.contains(_scheme + "." + _functions.getString("declaration"));
			    if (!contains) {
				//query += "REVOKE ALL PRIVILEGES ON " + _scheme + "." + _functions.getString("declaration") + " FROM GROUP " + _groupName + ";\n";
				if (_functions.getBoolean("execute")) {
				    query.append("GRANT EXECUTE ON FUNCTION " + _functions.getString("declaration") + " TO GROUP " + _groupName + ";\n");
				}
			    } else {
			//	System.out.println("Excluyendo funcion " + _functions.getString("declaration"));
			    }
			}
		    } catch (SQLException x) {
			Advisor.printException(x);
		    }
		}
	    }

	    ResultSet _securityDefiner = LibSQL.exQuery("SELECT declaration FROM (SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname) AS foo;");
	    try {
	        while (_securityDefiner.next()) {
		    query.append("ALTER FUNCTION " + _securityDefiner.getString("declaration") + " SECURITY DEFINER;\n");
	        }
	    } catch (SQLException x) {
	        Advisor.printException(x);
	    }

	    query.append(grantPublicAcess());
	    //System.out.println("Start:\n" + query);
	    JArea taSQLLog = new JArea();
	    BasicDialog sqlDialog = new BasicDialog();
	    sqlDialog.setLayout(new BorderLayout());
	    sqlDialog.setSize(600, 600);
	    sqlDialog.getContentPane().add(taSQLLog, BorderLayout.CENTER);
	    taSQLLog.setText(query.toString());
	    sqlDialog.setVisible(true);
	    sqlDialog.show();
	    /*	    String _prevDB = LibSQL.getDataBase().split("/")[3];
	    LibSQL.setDataBase(_dbName);
	    LibSQL.closeConnection();
	    if (LibSQL.isConnected()) {
		if (LibSQL.exActualizar('a', query)) {
		    Advisor.messageBox("Operación concretada, ahora " + _dbName + " tiene los mismos privilegios de " + _prevDB, "Error");
		    JArea taSQLLog = new JArea();
		    BasicDialog sqlDialog = new BasicDialog();
		    sqlDialog.setLayout(new BorderLayout());
		    sqlDialog.setSize(600, 600);
		    sqlDialog.getContentPane().add(taSQLLog, BorderLayout.CENTER);
		    taSQLLog.setText(query);
		    sqlDialog.setVisible(true);
		    sqlDialog.show();
		} else {
		    Advisor.messageBox("Error al intentar la operación", "Error");
		}
	    } else {
		Advisor.messageBox("Error al intentar conectarse a " + _dbName, "Error");
	    }
	    LibSQL.setDataBase(_prevDB);
	    LibSQL.closeConnection();
	    if (LibSQL.isConnected()) {
	    } else {
		Advisor.messageBox("Error al intentar reconectarse a " + _prevDB, "Error");
	    }*/
	}
    }

    public void setUserVector(Vector _userVector) {
	userVector = _userVector;
    }

    public void setDBVector(Vector _dbVector) {
	dbVector = _dbVector;
    }

    private void btnGrantPublicAccess_actionPerformed(ActionEvent e) {
	//grantSchemesToGroups();
	String query = grantPublicAcess();
	if (LibSQL.exActualizar('a', query)) {
	    Advisor.messageBox("Privilegios públicos asignados exitosamente", "Aviso");
	} else {
	    Advisor.messageBox("Error al intentar asignar los privilegios", "Error");
	}
    }

    private void btnExportAllPrivileges_actionPerformed(ActionEvent e) {
	exportAllPrivileges();
    }

    private void refreshPublicTables() {
	publicTables.removeAllElements();
	ResultSet _publicTables = LibSQL.exQuery("select nspname || '.' || relname AS table from pg_class\n" + "INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid\n" + " where aclcontains (relacl, '=r/" + DBAdminConfiguration.getDbOwner() + "')\n");
	try {
	    while (_publicTables.next()) {
		publicTables.add(_publicTables.getString("table"));
	    }
	} catch (SQLException e) {
	    Advisor.printException(e);
	}
	ResultSet _tabsTables = LibSQL.exQuery("select nspname || '.' || relname AS table from pg_class\n" + "INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid\n" + " WHERE pg_namespace.nspname = 'tabs' AND relkind = 'r'\n");
	try {
	    while (_tabsTables.next()) {
		publicTables.add(_tabsTables.getString("table"));
	    }
	} catch (SQLException e) {
	    Advisor.printException(e);
	}
	publicTables.add("cashflow.costscentres");
	publicTables.add("org.cards");
	publicTables.add("resourcescontrol.brands");
    }

    private void refreshPublicFunctions() {
	publicFunctions.removeAllElements();
	ResultSet _publicFunctions = LibSQL.exQuery("SELECT declaration FROM (SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' AS declaration,\n" + "proacl FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace\n" + "INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang \n" + "WHERE NOT p.proisagg  AND lanname = 'plpgsql' \n" + "AND aclcontains(proacl, '=X/" + DBAdminConfiguration.getDbOwner() + "') ORDER BY p.proname) AS foo\n");
	try {
	    while (_publicFunctions.next()) {
		publicFunctions.add(_publicFunctions.getString("declaration"));
	    }
	} catch (SQLException e) {
	    Advisor.printException(e);
	}
	publicFunctions.add("org.getsessiondata()");
	publicFunctions.add("org.getuserdata()");
	publicFunctions.add("calendar.getallnewsbyfilter(integer, integer, integer)");
	publicFunctions.add("org.getcard(integer)");
	publicFunctions.add("calendar.getallnewsstatus(integer)");
	publicFunctions.add("calendar.getallstickynotes()");
	publicFunctions.add("org.updatecardposition(integer, double precision, double precision)");
	publicFunctions.add("taxes.xmlgetupdatedtaxesreport(character varying, character varying)");
	publicFunctions.add("public.setpassword(character varying)");
	publicFunctions.add("sueldos.getalltramos(integer, integer)");
	publicFunctions.add("sueldos.getallobrassociales(integer, integer)");
	publicFunctions.add("sueldos.getallcategorias(integer, integer)");
	publicFunctions.add("sueldos.getallmni(integer, integer)");
	publicFunctions.add("sueldos.getallplanesobrassociales(integer, integer)");
	publicFunctions.add("sueldos.getallsindicatos(integer, integer)");
	publicFunctions.add("personalfiles.getalldependencias()");
	publicFunctions.add("personalfiles.getallrecursos(integer, integer)");
	publicFunctions.add("personalfiles.getAllTiposDependencia()");
	publicFunctions.add("accounting.getaccountlink(integer)");
	publicFunctions.add("cashflow.getAllIssueChecks(integer)");
	publicFunctions.add("resourcescontrol.getDistinguishableResource(integer)");
	publicFunctions.add("resourcescontrol.getDistinguishableResources(character varying)");
    }

    private String grantPublicAcess() {
	refreshPublicFunctions();
	refreshPublicTables();
	refreshPublicNonPublicSchemes();
	StringBuilder query = new StringBuilder();
	query.append(resetSchemesPrivileges());
	for (int j = 0; j < publicTables.size(); j++) {
	    if (!SystemConfiguration.tablesExclusionList.contains(publicTables.elementAt(j).toString())) {
		query.append("GRANT SELECT ON " + publicTables.elementAt(j).toString() + " TO PUBLIC;\n");
	    } else {
	//	System.out.println("Excluyendo tabla " + publicTables.elementAt(j).toString());
	    }
	}
	for (int j = 0; j < publicFunctions.size(); j++) {
	    if (!SystemConfiguration.tablesExclusionList.contains(publicFunctions.elementAt(j).toString())) {
		query.append("GRANT EXECUTE ON FUNCTION " + publicFunctions.elementAt(j).toString() + " TO PUBLIC;\n");
	    } else {
	//	System.out.println("Excluyendo función " + publicFunctions.elementAt(j).toString());
	    }
	}

	ResultSet _securityDefiner = LibSQL.exQuery("SELECT declaration FROM (SELECT n.nspname || '.\"' || proname || '\"(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname) AS foo;");
	try {
	    while (_securityDefiner.next()) {
		query.append("ALTER FUNCTION " + _securityDefiner.getString("declaration") + " SECURITY DEFINER;\n");
		//System.out.println("ALTER FUNCTION " + _securityDefiner.getString("declaration") + " SECURITY DEFINER;\n");
	    }
	} catch (SQLException x) {
	    Advisor.printException(x);
	}

	/*if (LibSQL.exActualizar('a', query)) {
	    Advisor.messageBox("Privilegios públicos asignados exitosamente", "Aviso");
	} else {
	    Advisor.messageBox("Error al intentar asignar los privilegios", "Error");
	}*/
	return query.toString();
	/*
	query = "";
	ResultSet _tabsTables = LibSQL.exQuery("select nspname || '.' || relname AS table from pg_class\n" + 
	"INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid\n" + 
	" WHERE pg_namespace.nspname = 'tabs' AND relkind = 'r'\n");
	try {
	    while (_tabsTables.next()) {
		for (int i = 0; i < groupsList.getModel().getSize(); i++) {
		    String _groupName = ((DBRole)groupsList.getModel().getElementAt(i)).getName();
		    query += "REVOKE ALL PRIVILEGES ON " + _tabsTables.getString("table") + " FROM GROUP " + _groupName + ";\n";
		}
	    }
	} catch (SQLException e) {
	    Advisor.printException(e);
	}
	System.out.println("Start:\n" + query);
	if (LibSQL.exActualizar('a', query)) {
	    Advisor.messageBox("Privilegios públicos asignados exitosamente", "Aviso");
	} else {
	    Advisor.messageBox("Error al intentar asignar los privilegios", "Error");
	}
	*/
    }

    private void refreshPublicNonPublicSchemes() {
	nonPublicSchemes.removeAllElements();
	nonPublicSchemes.add("galpon");
	nonPublicSchemes.add("personalfiles");
    }

    private void setEnabledButtons(boolean _enabled){
        btnAddGroup.setEnabled(_enabled);
        btnRevokeGroups.setEnabled(_enabled);
        btnDeleteGroup.setEnabled(_enabled);
        btnMakeQueryUser.setEnabled(_enabled);
        //saveUsersByGroup.setEnabled(_enabled);
        //cancelUsersByGroup.setEnabled(_enabled);
    }
}

