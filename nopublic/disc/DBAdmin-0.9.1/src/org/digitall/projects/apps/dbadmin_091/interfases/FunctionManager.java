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
 * FunctionManager.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.BorderLayout;
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
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.sql.LibSQL;

import org.digitall.projects.apps.dbadmin_091.DBAdminMain;
import org.digitall.projects.apps.dbadmin_091.SystemConfiguration;
import org.postgresql.util.PSQLException;

public class FunctionManager extends BasicPanel {

    private SaveDataButton saveGroupsByFunction = new SaveDataButton();
    private CancelDataButton cancelGroupsByFunction = new CancelDataButton();
    
    private BasicLabel lblFunctionsGreen = new BasicLabel();
    private BasicLabel lblScheme = new BasicLabel();
    private BasicLabel lblFunctions = new BasicLabel();
    private BasicLabel lblGroups = new BasicLabel();
    
    private BasicScrollPane spGroupsByFunction = new BasicScrollPane();
    private BasicCheckList groupsByFunction = new BasicCheckList(new DefaultListModel());
    private BasicScrollPane spFunctionsByScheme = new BasicScrollPane();
    private BasicList functionsByScheme = new BasicList();
    
    private JCombo cbFunctionsSchemes = new JCombo();
    private Vector<DBRole> groupVector = new Vector<DBRole>();
    private DBAdminMain manager;
    private DBAdminPanel dbAdminPanel;

    public FunctionManager(DBAdminMain _manager) {
	//manager = _manager;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    

    public FunctionManager(DBAdminPanel _manager) {
        dbAdminPanel = _manager;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(609, 486));
        this.setBounds(new Rectangle(10, 10, 660, 486));
        cbFunctionsSchemes.setBounds(new Rectangle(15, 20, 200, 20));
	spGroupsByFunction.setBounds(new Rectangle(15, 285, 290, 180));
	spGroupsByFunction.setSize(new Dimension(290, 180));
	spFunctionsByScheme.setBounds(new Rectangle(15, 70, 635, 190));
	spFunctionsByScheme.setSize(new Dimension(635, 188));
	groupsByFunction.addMouseListener(new MouseAdapter() {

				       public void mouseClicked(MouseEvent me) {
					   if (groupsByFunction.getSelectedValues().length > 0 && me.getButton() == MouseEvent.BUTTON3) {
					       for (int i = 0; i < groupsByFunction.getSelectedValues().length; i++) {
			    DBRole _group = (DBRole)groupsByFunction.getSelectedValues()[i];
						   _group.incPrivilege();
						   _group.setSelected(true);
					       }
					       spGroupsByFunction.repaint();
					   }
				       }

				   }
	);
	groupsByFunction.setCellRenderer(new BasicCellRenderer());
	saveGroupsByFunction.setBounds(new Rectangle(510, 410, 70, 55));
	saveGroupsByFunction.setText("Guardar\ncambios");
	saveGroupsByFunction.addActionListener(new ActionListener() {

					    public void actionPerformed(ActionEvent e) {
						saveGroupsByFunction_actionPerformed(e);
					    }

					}
	);
	cancelGroupsByFunction.setBounds(new Rectangle(575, 410, 70, 55));
	cancelGroupsByFunction.setText("Cancelar \ncambios");
	lblScheme.setBounds(new Rectangle(15, 0, 80, 20));
	lblFunctionsGreen.setText("EXEC");
        lblFunctionsGreen.setForeground(Color.BLACK);
	lblScheme.setText("Esquema");
	lblFunctions.setText("Funciones");
	lblFunctions.setBounds(new Rectangle(15, 50, 70, 15));
	lblGroups.setText("Grupos");
	lblGroups.setBounds(new Rectangle(15, 265, 60, 15));
	lblFunctionsGreen.setBounds(new Rectangle(320, 285, 70, 25));
	lblFunctionsGreen.setOpaque(true);
	lblFunctionsGreen.setBackground(Color.green);
	lblFunctionsGreen.setHorizontalAlignment(SwingConstants.CENTER);
	functionsByScheme.addListSelectionListener(new ListSelectionListener() {

						public void valueChanged(ListSelectionEvent e) {
						    if (!e.getValueIsAdjusting()) {
							if (functionsByScheme.getSelectedIndex() > -1) {
							    try {
								loadGroupsByFunction(functionsByScheme.getSelectedValue().toString());
							    } catch (NullPointerException x) {
								//ignore
							    }
							}
						    }
						}

					    }
	);
        this.add(lblGroups, null);
        this.add(lblFunctions, null);
	this.add(lblScheme, null);
        add(saveGroupsByFunction);
        add(cancelGroupsByFunction);
        add(lblFunctionsGreen, null);
        add(cbFunctionsSchemes, null);
	spGroupsByFunction.getViewport().add(groupsByFunction);
	add(spGroupsByFunction, null);
	spFunctionsByScheme.getViewport().add(functionsByScheme);
	add(spFunctionsByScheme, BorderLayout.CENTER);
	cbFunctionsSchemes.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbFunctionsSchemes.getSelectedIndex() > -1) {
						    loadFunctionsByScheme(cbFunctionsSchemes.getSelectedItem().toString());
						}
					    }
					}

				    }
	);
    }

    /**2009-12-29(moraless)*/
    public void boot() {
	cbFunctionsSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	cbFunctionsSchemes.setSelectedIndex(0);
	loadFunctionsByScheme(cbFunctionsSchemes.getSelectedItem().toString());
	//loadGroupsByFunction(functionsByScheme.getSelectedValue().toString());   
	setSecurityDefinerToAllFunctions();
    }
    
    /**2009-12-30(moraless)*/
    public void boot(ResultSet _schemes) {
	cbFunctionsSchemes.loadJCombo(_schemes);
	cbFunctionsSchemes.setSelectedIndex(0);
	loadFunctionsByScheme(cbFunctionsSchemes.getSelectedItem().toString());
	//loadGroupsByFunction(functionsByScheme.getSelectedValue().toString());   
	//setSecurityDefinerToAllFunctions();
    }
    
    public void setEnabledTab(boolean _enabled){
	cbFunctionsSchemes.setEnabled(_enabled);
	functionsByScheme.setEnabled(_enabled);
	groupsByFunction.setEnabled(_enabled);
	saveGroupsByFunction.setEnabled(_enabled);
	cancelGroupsByFunction.setEnabled(_enabled);
    }

    private void revokePublicAccessToAllFunctions() {
	String query = "SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration " + "FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang " + "WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname";
	ResultSet rs = LibSQL.exQuery(query);
	String _query = "";
	try {
	    while (rs.next()) {
		_query += "REVOKE ALL PRIVILEGES ON FUNCTION " + rs.getString("declaration") + " FROM PUBLIC;\n";
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	if (!LibSQL.executeQuery(_query)) {
	   Advisor.messageBox("Fallo al ejecutar revokePublicAccessToAllFunctions", "DBAdmin");
	}
    }

     private void setSecurityDefinerToAllFunctions() {
	 String query = "SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration " + "FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang " + "WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname";
	 ResultSet rs = LibSQL.exQuery(query);
	 String _query = "";
	 try {
	     while (rs.next()) {
		 _query += "ALTER FUNCTION " + rs.getString("declaration") + " SECURITY DEFINER;\n";
	     }
	 } catch (SQLException e) {
	     e.printStackTrace();
	 }
	 if (!LibSQL.executeQuery(_query)) {
	    Advisor.messageBox("Fallo al ejecutar setSecurityDefinerToAllFunctions", "DBAdmin");
	 }
     }

    /**2009-12-29(moraless)*/
    private void loadFunctionsByScheme(String _scheme) {
	//String query = "SELECT relname as name, description AS comment FROM pg_description de, pg_class bc, pg_namespace ns WHERE de.objoid = bc.oid and bc.relnamespace = ns.oid AND objsubid = 0 AND   ns.nspname = '" + _scheme + "' order by relname";
	//String query = "SELECT relname as name FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND relkind = 'r' ORDER BY relname";
	//String query = "SELECT p.oid AS prooid, p.proname as name, pg_catalog.format_type(p.prorettype, NULL) AS proresult, " + "CASE WHEN array_to_string(p.proargnames, '') IS NULL THEN '' ELSE array_to_string(p.proargnames, '') " + "END AS proargnames, pg_catalog.oidvectortypes(p.proargtypes) AS proarguments, pl.lanname AS prolanguage," + "CASE WHEN pg_catalog.obj_description(p.oid, 'pg_proc') IS NULL THEN '' " + "ELSE pg_catalog.obj_description(p.oid, 'pg_proc') END AS procomment, prosrc FROM pg_catalog.pg_proc p " + "INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace " + "INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang " + "WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname LIKE '%" + _scheme + "%' " + "ORDER BY p.proname, proresult";
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
	functionsByScheme.setListData(_functionsByScheme);
	functionsByScheme.setSelectedIndex(0);
	/*if (functionsByScheme.getSelectedIndex() > -1) {
	    loadGroupsByFunction(functionsByScheme.getSelectedValue().toString());
	}*/
    }
    
    /**2009-12-29(moraless)*/
     private void loadGroupsByFunction(String _function) {
	loadGroup();
	//se obtiene el acl con los grupos asociados al stored
	ResultSet result = LibSQL.exQuery("SELECT array_to_string(proacl,',') AS acl, proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname = '" + 
	cbFunctionsSchemes.getSelectedItem() + "' AND ( (proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')')= '" + _function + "') ORDER BY p.proname");
	try {
	    if(result.next()){
	    //se obtienen los grupos asociados al stored
		String[] grupos = getGroupByFunction(result.getString("acl"));
		for(int j = 0;j < grupos.length; j++){
		    //if(grupos[j].startsWith("NULL")){ //STORED PUBLICO
		    if((grupos[j].startsWith("NULL"))||(grupos[j].startsWith("=X/"))){ //STORED PUBLICO (acl = NULL o ,=X/...)
			for(int k = 0; k < groupVector.size();k++){
			    DBRole role = (DBRole)((DefaultListModel)groupsByFunction.getModel()).getElementAt(k);
			    role.setPrivilege(SystemConfiguration.QUERY_PRIV);
			}
		    }else{
			//ingresa por el ELSE cuando el stored no tiene permiso publico.
			 if(grupos[j].indexOf("=X/") > -1){ // EXECUTE 
			     int i = 0;
			     while((i < groupVector.size())&&(!grupos[j].substring(0,grupos[j].indexOf("=")).equals(groupVector.elementAt(i).toString()))){
				 i++;
			     }
			     if(i < groupVector.size()){
				 DBRole role = (DBRole)((DefaultListModel)groupsByFunction.getModel()).getElementAt(i);
				 role.setPrivilege(SystemConfiguration.QUERY_PRIV);    
			     }
			 }
		    }
		}
	    }   
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    private String[] getGroupByFunction(String _acl){
	String[] com;
	//System.out.println("ACL **= "+_acl.length()+"-");
	//System.out.println("_acl.equals(null) = "+_acl.equals(null));
	if(_acl == null){
	    com = new String[1];
	    com[0] = "NULL";
	}else{
	    com = _acl.split(",");
	}
	return com;
    }
    
    /*
    private void loadGroupsByFunction(String _function) {
	((DefaultListModel)groupsByFunction.getModel()).removeAllElements();
	for (int i = 0; i < groupVector.size(); i++) {
	    DBRole _group = groupVector.elementAt(i);
	    String priv = "'" + _group.getText() + "','" + cbFunctionsSchemes.getSelectedItem().toString() + "." + _function + "',";
	    String execPriv = priv + "'EXECUTE'";
	    try {
		ResultSet privs = LibSQL.exQuery("SELECT has_function_privilege(" + execPriv + ") AS execPriv");
		if (privs.next()) {
		    _group.setPrivilege(SystemConfiguration.ZERO_PRIV);
		    _group.setPrivilege(privs.getBoolean("execPriv") ? SystemConfiguration.QUERY_PRIV : SystemConfiguration.ZERO_PRIV);
		}
	    } catch (PSQLException e) {
		Advisor.printException(e);
	    } catch (SQLException e) {
		Advisor.printException(e);
	    }
	    ((DefaultListModel)groupsByFunction.getModel()).addElement(_group);
	}
    }*/

    private void saveGroupsByFunction_actionPerformed(ActionEvent e) {
	String query = "";
	for (int i = 0; i < groupsByFunction.getModel().getSize(); i++) {
	    DBRole _group = (DBRole)groupsByFunction.getModel().getElementAt(i);
	    if (_group.isSelected()) {
		//query += "GRANT USAGE ON SCHEMA " + cbSchemes.getSelectedItem().toString() + " TO GROUP " + _group.getRoleName() + ";\n";
		for (int j = 0; j < functionsByScheme.getSelectedValues().length; j++) {
		    //burbuja maldita
		    //me recuerda a la U
		    //tengo que revisar en las tablas seleccionadas
		    //que permisos tenian antes los usuarios seleccionados
		    //y si han cambiado, entonces actualizarlos, sino, no
		    String _function = cbFunctionsSchemes.getSelectedItem().toString() + "." + functionsByScheme.getSelectedValues()[j].toString();
		    String priv = "'" + _group.getText() + "','" + _function + "',";
		    String execPriv = priv + "'EXECUTE'";
		    query += "REVOKE ALL PRIVILEGES ON FUNCTION " + _function + " FROM PUBLIC;\n";
		    query += "REVOKE ALL PRIVILEGES ON FUNCTION " + _function + " FROM GROUP " + _group.getText() + ";\n";
		    //System.out.println(_group.getPrivilege());
		    if (_group.getPrivilege() != SystemConfiguration.ZERO_PRIV) {
			//tengo que actualizar estos privilegios
			query += "GRANT EXECUTE ON FUNCTION " + _function + " TO GROUP " + _group.getText() + ";\n";
		    }
		}
	    }
	}
	if (LibSQL.exActualizar('a', query)) {
	    //taránnnnn
	    for (int i = 0; i < groupsByFunction.getModel().getSize(); i++) {
		((DBRole)groupsByFunction.getModel().getElementAt(i)).setSelected(false);
		updateUI();
	    }
	} 
	else {
	    Advisor.messageBox("Error al intentar asignar los permisos", "Error");
	}
    }

    public void setGroupVector(Vector<DBRole> _groupVector) {
	groupVector = _groupVector;
    }

    /**2009-12-29(moraless)*/
    //metodo que carga la lista de grupos iniciandolos sin privilegios
    private void loadGroup() {
	//se eliminan los grupos de la lista
	((DefaultListModel)groupsByFunction.getModel()).removeAllElements();
	//se cargan los grupos en la lista
	for(int i = 0; i< groupVector.size();i++){
	    DBRole _group = groupVector.elementAt(i);
	    _group.setPrivilege(SystemConfiguration.ZERO_PRIV);
	    ((DefaultListModel)groupsByFunction.getModel()).addElement(_group);
	}
    }
}
