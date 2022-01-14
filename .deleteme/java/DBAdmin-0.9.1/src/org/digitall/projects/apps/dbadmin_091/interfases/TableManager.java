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
 * TableManager.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Color;
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

import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckList;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.ReloadButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.SystemConfiguration;

public class TableManager extends BasicPanel {

    private SaveDataButton saveGroupsByTable = new SaveDataButton();
    private AssignButton excludeTable = new AssignButton();
    private ReloadButton resetExcludedTables = new ReloadButton();
    private CancelDataButton cancelGroupsByTable = new CancelDataButton();
    
    private BasicScrollPane spGroupsByTable = new BasicScrollPane();
    private BasicScrollPane spTablesByScheme = new BasicScrollPane();
    private BasicList tablesByScheme = new BasicList();
    private BasicCheckList groupsByTable = new BasicCheckList(new DefaultListModel());
    
    private BasicLabel lblTablesRed = new BasicLabel();
    private BasicLabel lblTablesYellow = new BasicLabel();
    private BasicLabel lblTablesGreen = new BasicLabel();
    private BasicLabel lblScheme = new BasicLabel();
    private BasicLabel lblTables = new BasicLabel();
    private BasicLabel lblGroups = new BasicLabel();
    
    private JCombo cbTablesSchemes = new JCombo();
    
    private Vector<DBRole> groupVector = new Vector<DBRole>();
    private DBAdminPanel dbAdminPanel;
    
    public TableManager(DBAdminPanel _dbAdminPanel) {
        dbAdminPanel = _dbAdminPanel;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(660, 486));
	spGroupsByTable.setBounds(new Rectangle(295, 75, 253, 345));
	spGroupsByTable.setSize(new Dimension(253, 345));
	spTablesByScheme.setBounds(new Rectangle(20, 75, 253, 345));
	spTablesByScheme.setSize(new Dimension(253, 345));
	lblTablesRed.setText("ADMIN");
	lblTablesRed.setBounds(new Rectangle(20, 435, 70, 25));
	lblTablesRed.setBackground(Color.red);
	lblTablesRed.setOpaque(true);
	lblTablesRed.setHorizontalAlignment(SwingConstants.CENTER);
	lblTablesRed.setForeground(Color.black);
	lblTablesYellow.setText("USER");
	lblTablesYellow.setBounds(new Rectangle(100, 435, 70, 25));
	lblTablesYellow.setOpaque(true);
	lblTablesYellow.setBackground(Color.yellow);
	lblTablesYellow.setHorizontalAlignment(SwingConstants.CENTER);
	lblTablesYellow.setForeground(Color.black);
	lblTablesGreen.setText("QUERY");
	lblTablesGreen.setBounds(new Rectangle(180, 435, 70, 25));
	lblTablesGreen.setOpaque(true);
	lblTablesGreen.setBackground(Color.green);
	lblTablesGreen.setHorizontalAlignment(SwingConstants.CENTER);
	lblTablesGreen.setForeground(Color.black);
	groupsByTable.setCellRenderer(new BasicCellRenderer());
        tablesByScheme.setBounds(new Rectangle(0, 0, 251, 338));
        tablesByScheme.setSize(new Dimension(251, 339));
        tablesByScheme.addListSelectionListener(new ListSelectionListener() {
					     public void valueChanged(ListSelectionEvent e) {
					         if (!e.getValueIsAdjusting()) {
						     if (tablesByScheme.getSelectedIndex() > -1) {
							 try {
							     loadGroupsByTable(tablesByScheme.getSelectedValue().toString());
							 } catch (NullPointerException x) {
							     //ignore
							 }
						     }
						 }
					     }

					 }
	);
	groupsByTable.addMouseListener(new MouseAdapter() {

				    public void mouseClicked(MouseEvent me) {
					if (groupsByTable.getSelectedValues().length > 0 && me.getButton() == MouseEvent.BUTTON3) {
					    for (int i = 0; i < groupsByTable.getSelectedValues().length; i++) {
						DBRole _group = (DBRole)groupsByTable.getSelectedValues()[i];
						_group.incPrivilege();
						_group.setSelected(true);
					    }
					    spGroupsByTable.repaint();
					}
				    }

				}
	);
	saveGroupsByTable.setBounds(new Rectangle(570, 205, 70, 60));
	saveGroupsByTable.setSize(new Dimension(70, 60));
	excludeTable.setBounds(new Rectangle(555, 75, 105, 60));
	resetExcludedTables.setBounds(new Rectangle(555, 140, 105, 60));
	resetExcludedTables.addActionListener(new ActionListener() {

					   public void actionPerformed(ActionEvent e) {
					       resetExcludedTables_actionPerformed(e);
					   }

				       }
	);
	excludeTable.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					excludeTable_actionPerformed(e);
				    }

				}
	);
	saveGroupsByTable.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     saveGroupsByTable_actionPerformed(e);
					 }

				     }
	);
	saveGroupsByTable.setText("Guardar\ncambios");
	cancelGroupsByTable.setBounds(new Rectangle(555, 270, 105, 60));
	cancelGroupsByTable.setText("Cancelar \ncambios");
	excludeTable.setText("Excluir\n tabla");
        excludeTable.setToolTipText("Excluir Tabla");
	resetExcludedTables.setText("Limpiar listado\n de Exclusiones");
        resetExcludedTables.setToolTipText("Limpiar lista de Tablas excluidas");
	lblScheme.setText("Esquema");
	lblScheme.setBounds(new Rectangle(20, 5, 75, 15));
	lblTables.setText("Tablas");
	lblTables.setBounds(new Rectangle(20, 55, 75, 15));
	lblGroups.setText("Grupos");
	lblGroups.setBounds(new Rectangle(295, 55, 75, 14));
        this.add(lblGroups, null);
        this.add(lblTables, null);
	this.add(lblScheme, null);
        spGroupsByTable.getViewport().add(groupsByTable);
        add(spGroupsByTable, null);
        add(lblTablesGreen, null);
        add(lblTablesYellow, null);
        add(lblTablesRed, null);
        add(cbTablesSchemes, null);
	spTablesByScheme.getViewport().add(tablesByScheme);
	add(spTablesByScheme, null);
        add(saveGroupsByTable, null);
        add(excludeTable, null);
        add(resetExcludedTables, null);
        add(cancelGroupsByTable, null);
        cbTablesSchemes.setBounds(new Rectangle(20, 25, 200, 20));
	cbTablesSchemes.addItemListener(new ItemListener() {

				     public void itemStateChanged(ItemEvent e) {
					 if (e.getStateChange() == ItemEvent.SELECTED) {
					     if (cbTablesSchemes.getSelectedIndex() > -1) {
						 loadTablesByScheme(cbTablesSchemes.getSelectedItem().toString());
					     }
					 }
				     }

				 }
	);
    }

    public void boot() {
	cbTablesSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	cbTablesSchemes.setSelectedIndex(0);
	loadGroupsList();
	if(cbTablesSchemes.getModel().getSize()>0){
	    loadTablesByScheme(cbTablesSchemes.getSelectedItem().toString());
	    
	    /*if(tablesByScheme.getModel().getSize()>0){
		loadGroupsByTable(tablesByScheme.getSelectedValue().toString());    	
	    }*/
	}
    }
    
    /**2009-12-30(moraless)*/
    public void boot(ResultSet _schemes) {
	cbTablesSchemes.loadJCombo(_schemes);
	cbTablesSchemes.setSelectedIndex(0);
	loadGroupsList();
	if(cbTablesSchemes.getModel().getSize()>0){
	    loadTablesByScheme(cbTablesSchemes.getSelectedItem().toString());
	    /*if(tablesByScheme.getModel().getSize()>0){
	    loadGroupsByTable(tablesByScheme.getSelectedValue().toString());        
	    }*/
	}
    }
    
    public void setEnabledTab(boolean _enabled){
	cbTablesSchemes.setEnabled(_enabled);
	tablesByScheme.setEnabled(_enabled);
	groupsByTable.setEnabled(_enabled);
	saveGroupsByTable.setEnabled(_enabled);
	cancelGroupsByTable.setEnabled(_enabled);
    }

    /*private void loadGroupsByTable(String _table) {
	((DefaultListModel)groupsByTable.getModel()).removeAllElements();
	for (int i = 0; i < groupVector.size(); i++) {
	    DBRole _group = groupVector.elementAt(i);
	    String priv = "'" + _group.getText() + "','" + cbTablesSchemes.getSelectedItem().toString() + "." + _table + "',";
	    String queryPriv = priv + "'SELECT'";
	    String adminPriv = priv + "'UPDATE'";
	    String userPriv = priv + "'INSERT'";
	    ResultSet privs = LibSQL.exQuery("SELECT has_table_privilege(" + queryPriv + ") AS queryPriv," + " has_table_privilege(" + userPriv + ") AS userPriv," + " has_table_privilege(" + adminPriv + ") AS adminPriv");
	    try {
		if (privs.next()) {
		    _group.setPrivilege(SystemConfiguration.ZERO_PRIV);
		    _group.setPrivilege(privs.getBoolean("queryPriv") ? SystemConfiguration.QUERY_PRIV : _group.getPrivilege());
		    _group.setPrivilege(privs.getBoolean("userPriv") ? SystemConfiguration.USER_PRIV : _group.getPrivilege());
		    _group.setPrivilege(privs.getBoolean("adminPriv") ? SystemConfiguration.ADMIN_PRIV : _group.getPrivilege());
		}
	    } catch (SQLException e) {
		Advisor.printException(e);
	    }
	    ((DefaultListModel)groupsByTable.getModel()).addElement(_group);
	}
    }*/
    
    /**2009-12-29(moraless)*/
    private void loadGroupsByTable(String _table) {
	loadGroup();
	//se obtiene el acl con los grupos asociados a la tabla
	ResultSet result = LibSQL.exQuery("(SELECT array_to_string(relacl,',') AS acl FROM pg_class INNER JOIN pg_namespace esquema ON esquema.oid = pg_class .relnamespace WHERE pg_class.relkind = 'r' AND esquema.nspname = '" +
	cbTablesSchemes.getSelectedItem().toString() + "' AND relname = '" + _table + "')");
	try {
	    if(result.next()){
	    //se obtienen los grupos asociados a la tabla. 
	    String[] grupos = getGroupByTable(result.getString("acl"));
		for(int j = 0;j < grupos.length; j++){
		    if(grupos[j].startsWith("=r/")){ //TABLA PUBLICA
			for(int k = 0; k < groupVector.size();k++){
			    DBRole role = (DBRole)((DefaultListModel)groupsByTable.getModel()).getElementAt(k);
			    //controlo si el grupo no tiene asignado privilegios
			    if(!((role.getPrivilege() == SystemConfiguration.ADMIN_PRIV)||(role.getPrivilege() == SystemConfiguration.USER_PRIV))){
				role.setPrivilege(SystemConfiguration.QUERY_PRIV);    
			    }
			}
		    }else{
			//ingresa por el ELSE cuando la tabla no tiene permiso publico.
			 if(grupos[j].indexOf("=r/") > -1){ // SELECT 
			     int i = 0;
			     //while((i < groupVector.size())&&(!grupos[j].startsWith(groupVector.elementAt(i).toString()))){
			     while((i < groupVector.size())&&(!grupos[j].substring(0,grupos[j].indexOf("=")).equals(groupVector.elementAt(i).toString()))){
				 i++;
			     }
			     if(i < groupVector.size()){
				 DBRole role = (DBRole)((DefaultListModel)groupsByTable.getModel()).getElementAt(i);
				 role.setPrivilege(SystemConfiguration.QUERY_PRIV);    
			     }
			 }
		    }
		    int i = 0;
		    while((i < groupVector.size())&&(!grupos[j].substring(0,grupos[j].indexOf("=")).equals(groupVector.elementAt(i).toString()))){
			i++;
		    }
		    if(i < groupVector.size()){
			DBRole role = (DBRole)((DefaultListModel)groupsByTable.getModel()).getElementAt(i);
			if(grupos[j].indexOf("=ar/") > -1){// SELECT INSERT
			    role.setPrivilege(SystemConfiguration.USER_PRIV);
			}else{
			    //if(grupos[j].indexOf("=arwdRxt/") > -1){ // ALL PRIVILEGES
			    if(grupos[j].indexOf("=arwd") > -1){ // ALL PRIVILEGES
				role.setPrivilege(SystemConfiguration.ADMIN_PRIV);
			    }
			}    
		    }
		}
	    }	
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    private String[] getGroupByTable(String _acl){
	String[] com = _acl.split(",");
	/*for (int i=0 ;i<com.length ;i++ ) {
	    System.out.println("grupo = "+com[i]);
	}*/
	return com;
    }

    private void loadTablesByScheme(String _scheme) {
	//String query = "SELECT relname as name, description AS comment FROM pg_description de, pg_class bc, pg_namespace ns WHERE de.objoid = bc.oid and bc.relnamespace = ns.oid AND objsubid = 0 AND   ns.nspname = '" + _scheme + "' order by relname";
	String query = "SELECT relname as name FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND relkind = 'r' ORDER BY relname";
	ResultSet rs = LibSQL.exQuery(query);
	Vector _tablesByScheme = new Vector();
	try {
	    while (rs.next()) {
		_tablesByScheme.add(rs.getString("name"));
	    }
	} catch (SQLException e){
	    e.printStackTrace();
	}
	tablesByScheme.setListData(_tablesByScheme);
	tablesByScheme.setSelectedIndex(0);
	/*if (tablesByScheme.getSelectedIndex() > -1){
	    loadGroupsByTable(tablesByScheme.getSelectedValue().toString());
	}*/
    }

    private void saveGroupsByTable_actionPerformed(ActionEvent e) {
	//REVISAR COLORES Y TABLAS!!!
	String query = "";
	for (int i = 0; i < groupsByTable.getModel().getSize(); i++) {
	    DBRole _group = (DBRole)groupsByTable.getModel().getElementAt(i);
	    if (_group.isSelected()) {
		//query += "GRANT USAGE ON SCHEMA " + cbSchemes.getSelectedItem().toString() + " TO GROUP " + _group.getRoleName() + ";\n";
		for (int j = 0; j < tablesByScheme.getSelectedValues().length; j++) {
		    //burbuja maldita
		    //me recuerda a la U
		    //tengo que revisar en las tablas seleccionadas
		    //que permisos tenian antes los usuarios seleccionados
		    //y si han cambiado, entonces actualizarlos, sino, no
		    String _table = cbTablesSchemes.getSelectedItem().toString() + "." + tablesByScheme.getSelectedValues()[j].toString();
		    String priv = "'" + _group.getText() + "','" + _table + "',";
		    String queryPriv = priv + "'SELECT'";
		    String adminPriv = priv + "'UPDATE'";
		    String userPriv = priv + "'INSERT'";
		    if (((LibSQL.getBoolean("has_table_privilege", queryPriv) || LibSQL.getBoolean("has_table_privilege", adminPriv) || LibSQL.getBoolean("has_table_privilege", userPriv)))) {
			// && _group.privilege == ZERO_PRIV)) {
			//tengo que quitarle todos los privilegios
			query += "REVOKE ALL PRIVILEGES ON " + _table + " FROM GROUP " + _group.getText() + ";\n";
		    }
		    if (_group.getPrivilege() == SystemConfiguration.QUERY_PRIV) {
			//tengo que actualizar estos privilegios
			query += "GRANT SELECT ON " + _table + " TO GROUP " + _group.getText() + ";\n";
		    } else if (_group.getPrivilege() == SystemConfiguration.USER_PRIV) {
			query += "GRANT SELECT, INSERT ON " + _table + " TO GROUP " + _group.getText() + ";\n";
		    } else if (_group.getPrivilege() == SystemConfiguration.ADMIN_PRIV) {
			query += "GRANT ALL PRIVILEGES ON " + _table + " TO GROUP " + _group.getText() + ";\n";
		    }
		}
		_group.setSelected(false);
		groupsByTable.updateUI();
	    }
	}
	//System.out.println(query);
	if (LibSQL.exActualizar('a', query)) {
	    //taránnnnn
	} else {
	    Advisor.messageBox("Error al intentar asignar los permisos", "Error");
	}
    }

    /**2009-12-30(moraless)*/
    public void setGroupVector(Vector<DBRole> _groupVector) {
	//groupVector = _groupVector;
    }
    
    /**2009-12-30(moraless)*/
    //Método que obtiene los grupos mediante consulta a la bd. Este método se ejecuta solo cuando el sistema se conecta a una nueva BD.
    private void loadGroupsList() {
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
	//groupsByTable.setListData(_groups);
	//manager.setGroupVector(_groups);
    }

    private void excludeTable_actionPerformed(ActionEvent e) {
	for (int j = 0; j < tablesByScheme.getSelectedValues().length; j++) {
	    SystemConfiguration.tablesExclusionList.add(cbTablesSchemes.getSelectedItem().toString() + "." + tablesByScheme.getSelectedValues()[j].toString());
	}
	System.out.println("listado de tablas excluidas");
	System.out.println(SystemConfiguration.tablesExclusionList);
    }

    private void resetExcludedTables_actionPerformed(ActionEvent e) {
	SystemConfiguration.tablesExclusionList.removeAllElements();
    }

    /**2009-12-29(moraless)*/
    //metodo que carga la lista de grupos iniciandolos sin privilegios
    private void loadGroup() {
	//se eliminan los grupos de la lista
	((DefaultListModel)groupsByTable.getModel()).removeAllElements();
	//se cargan los grupos en la lista
	for(int i = 0; i< groupVector.size();i++){
	    DBRole _group = groupVector.elementAt(i);
	    _group.setPrivilege(SystemConfiguration.ZERO_PRIV);
	    ((DefaultListModel)groupsByTable.getModel()).addElement(_group);
	}
    }
}
