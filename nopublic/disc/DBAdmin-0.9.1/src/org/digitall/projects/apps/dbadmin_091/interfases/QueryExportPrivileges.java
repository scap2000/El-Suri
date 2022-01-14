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
 * QueryExportPrivileges.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.classes.DBAdminConfiguration;

//public class QueryExportPrivileges extends BasicDialog {
public class QueryExportPrivileges extends JDialog {
    
    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private AddButton btnClear = new AddButton();
    private BasicButton btnGetQuery = new BasicButton();
    private BasicButton dbConnect = new BasicButton();
    
    private BasicScrollPane spDescripcion = new BasicScrollPane();
    
    private BasicPanel jPanel2 = new BasicPanel("Exportar Privilegios");
    
    private BasicLabel lblNombreFuncion = new BasicLabel();
    private BasicLabel lblDescripcion = new BasicLabel();
    private BasicLabel lblConnect = new BasicLabel();
    
    private BasicCheckBox chkGroup = new BasicCheckBox();
    private BasicCheckBox chkTable = new BasicCheckBox();
    private BasicCheckBox chkFunction = new BasicCheckBox();
    
    private JTextArea taDescripcion = new JTextArea();
    
    private JCombo cbDatabases = new JCombo();
    
    private Vector dbVector = new Vector();
    private String selectedDB = "";
    private String _dbName = "";
    int numeroFuncion = -1;
    static Set teclasPermitidas = new HashSet();
    static Set teclas = new HashSet();

    public QueryExportPrivileges() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setPreferredSize(new Dimension(392, 158));
	this.setSize(new Dimension(665, 501));
	this.setTitle("Privilegios");
	btnAccept.setBounds(new Rectangle(570, 440, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	spDescripcion.getViewport().add(taDescripcion, null);
	taDescripcion.setEditable(false);
	jPanel2.add(chkFunction, null);
	jPanel2.add(chkTable, null);
	jPanel2.add(chkGroup, null);
	jPanel2.add(dbConnect, null);
	jPanel2.add(cbDatabases, null);
	jPanel2.add(spDescripcion, null);
	jPanel2.add(lblDescripcion, null);
	jPanel2.add(lblNombreFuncion, null);
	this.getContentPane().add(btnGetQuery, null);
	this.getContentPane().add(lblConnect, null);
	this.getContentPane().add(btnClose, null);
	this.getContentPane().add(jPanel2, null);
	this.getContentPane().add(btnAccept, null);
	this.getContentPane().add(btnClear, null);
	jPanel2.setBounds(new Rectangle(0, 0, 655, 425));
	jPanel2.setLayout(null);
	lblNombreFuncion.setText("Base de Datos");
	lblNombreFuncion.setBounds(new Rectangle(10, 20, 165, 20));
	cbDatabases.setBounds(new Rectangle(10, 40, 230, 20));
	dbConnect.setText("Conectar");
	dbConnect.setBounds(new Rectangle(245, 40, 80, 20));
	dbConnect.setToolTipText("null");
	dbConnect.setSize(new Dimension(80, 20));
	dbConnect.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    dbConnect_actionPerformed(e);
		}
	    }
	);
	lblConnect.setBounds(new Rectangle(5, 435, 130, 25));
	lblConnect.setOpaque(true);
	lblConnect.setForeground(Color.black);
	lblConnect.setHorizontalTextPosition(SwingConstants.CENTER);
	lblConnect.setHorizontalAlignment(SwingConstants.CENTER);
	lblConnect.setBackground(Color.black);
	chkGroup.setText("Grupos");
	chkGroup.setBounds(new Rectangle(365, 67, 81, 18));
	chkTable.setText("Tablas");
	chkTable.setBounds(new Rectangle(455, 67, 81, 18));
	chkFunction.setText("Funciones");
	chkFunction.setBounds(new Rectangle(540, 65, 100, 20));
	btnClear.setBounds(new Rectangle(510, 440, 65, 25));
	btnClear.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClear_actionPerformed(e);
		}
	    }
	);
	btnGetQuery.setText("Get Query");
	btnGetQuery.setBounds(new Rectangle(305, 443, 77, 22));
	btnGetQuery.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnGetQuery_actionPerformed(e);
		}
	    }
	);
	btnClose.setBounds(new Rectangle(615, 440, 40, 25));
	btnClose.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClose_actionPerformed(e);
		}
	    }
	);
	lblDescripcion.setText("Consulta");
	lblDescripcion.setBounds(new Rectangle(10, 70, 145, 15));

	spDescripcion.setBounds(new Rectangle(10, 90, 635, 325));
	loadStuff();
    }

    private void loadStuff() {
	cbDatabases.loadJCombo("SELECT datdba, datname, 0 FROM pg_database WHERE datallowconn");
	dbVector = cbDatabases.getItemsVector();
	cbDatabases.setSelectedItem(LibSQL.getDataBase().split("/")[3]);
    }
    
    private void setDataBase(){
	_dbName = cbDatabases.getSelectedItem().toString();
	LibSQL.setDataBase(_dbName);
	LibSQL.closeConnection();
	if (LibSQL.isConnected()) {
	    
	}
	else{
	    Advisor.messageBox("Error al intentar conectarse a " + _dbName, "Error");
	}
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void limpiarCampos() {
	taDescripcion.setText("");
    }
    
    private Vector getGroups(){
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
	return _groups;
    }
    
    private String getQueryCreateGroups(){
	StringBuilder queryCreateGroup = new StringBuilder();
	//String query = "SELECT distinct grosysid as gid, groname as name FROM pg_group order by groname";
	String query = "SELECT distinct grosysid as gid, 'CREATE GROUP ' || groname || ';' as name FROM pg_group";
	ResultSet rs = LibSQL.exQuery(query);
	//Vector _groups = new Vector();
	try {
	    while (rs.next()) {
		//_groups.add(new DBRole(rs.getString("name")));
	//	queryCreateGroup.append("CREATE GROUP " + rs.getString("name")+";\n");
		queryCreateGroup.append(rs.getString("name")+"\n");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return queryCreateGroup.toString();
    }
    
    private Vector getSchemes(){
	String querySchemes = "SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname";
	ResultSet rs = LibSQL.exQuery(querySchemes);
	Vector _schemes = new Vector();
	try {
	    while (rs.next()) {
		_schemes.add(new DBRole(rs.getString("nspname")));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return _schemes;
    }
    
    private void exportAllPrivileges() {
	String _dbName = "";
	_dbName = cbDatabases.getSelectedItem().toString();
	LibSQL.setDataBase(_dbName);
	LibSQL.closeConnection();
	if (LibSQL.isConnected()) {
	    if (_dbName != null) {
		Vector grupos = getGroups();
		Vector esquemas = getSchemes();
		String query = "";
		query += getQueryCreateGroups();
		selectedDB = _dbName; 
		for (int i = 0; i < grupos.size(); i++) {
		    String _groupName = grupos.elementAt(i).toString();
		    for (int j = 0; j < esquemas.size(); j++) {
			String _scheme = esquemas.elementAt(j).toString();
			ResultSet _tables = LibSQL.exQuery("SELECT relname as name, has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'INSERT') AS insert, " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'UPDATE') AS update, " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'SELECT') AS select" + " FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND NOT aclcontains(relacl, '=r/" + DBAdminConfiguration.getDbOwner() + "') AND relkind = 'r' ORDER BY relname");
			try{
			    while (_tables.next()) {
			        query += "REVOKE ALL PRIVILEGES ON " + _scheme + "." + _tables.getString("name") + " FROM GROUP " + _groupName + ";\n";
				if (_tables.getBoolean("insert") && _tables.getBoolean("update") && _tables.getBoolean("select")) {
				    query += "GRANT ALL PRIVILEGES ON " + _scheme + "." + _tables.getString("name") + " TO GROUP " + _groupName + ";\n";
				} else {
				    if (_tables.getBoolean("insert")) {
					query += "GRANT INSERT ON " + _scheme + "." + _tables.getString("name") + " TO GROUP " + _groupName + ";\n";
				    }
				    if (_tables.getBoolean("update")) {
					query += "GRANT UPDATE ON " + _scheme + "." + _tables.getString("name") + " TO GROUP " + _groupName + ";\n";
				    }
				    if (_tables.getBoolean("select")) {
					query += "GRANT SELECT ON " + _scheme + "." + _tables.getString("name") + " TO GROUP " + _groupName + ";\n";
				    }
				}
			    }
			} 
			catch (SQLException x) {
			    x.printStackTrace();
			}
    
			ResultSet _functions = LibSQL.exQuery("SELECT declaration, has_function_privilege('" + _groupName + "', declaration, 'EXECUTE') AS execute FROM (SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname = '" + _scheme + "' AND NOT aclcontains(proacl, '=X/" +
			DBAdminConfiguration.getDbOwner() + "') ORDER BY p.proname) AS foo;");
			try {
			    while (_functions.next()){
				if (_functions.getBoolean("execute")) {
				    query += "GRANT EXECUTE ON FUNCTION " + _functions.getString("declaration") + " TO GROUP " + _groupName + ";\n";
				}
				else{
				    query += "REVOKE ALL ON FUNCTION" + _functions.getString("declaration") + " FROM GROUP " + _groupName + ";\n";
				}
			    }
			} catch (SQLException x) {
			    Advisor.printException(x);
			}
		    }
		}
		taDescripcion.setText(query);
	    }
	} else {
	    Advisor.messageBox("Error al intentar conectarse a " + _dbName, "Error");
	}
    }
    
    private String exportGroups(){
	return getQueryCreateGroups();
    }
    
    private String exportTablesPrivileges(){
	String _dbName = "";
	StringBuilder query = new StringBuilder();
	    Vector grupos = getGroups();
	    Vector esquemas = getSchemes();
	    selectedDB = _dbName; 
	    for (int i = 0; i < grupos.size(); i++) {
		String _groupName = grupos.elementAt(i).toString();
		for (int j = 0; j < esquemas.size(); j++) {
		    String _scheme = esquemas.elementAt(j).toString();
		    //ResultSet _tables = LibSQL.exQuery("SELECT relname as name, has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'INSERT') AS insert, " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'UPDATE') AS update, " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'SELECT') AS select" + " FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND NOT aclcontains(relacl, '=r/admin') AND relkind = 'r' ORDER BY relname");
		    ResultSet _tables = LibSQL.exQuery("SELECT relname as name, has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'INSERT') AS insert, " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'UPDATE') AS update, " + "has_table_privilege('" + _groupName + "', nspname ||\'.\'|| relname,'SELECT') AS select" + " FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND NOT (aclcontains(relacl, '=X/" + DBAdminConfiguration.getDbOwner() + "') OR relacl IS NULL) AND relkind = 'r' ORDER BY relname");
		    try{
			while (_tables.next()) {
			    query.append("REVOKE ALL PRIVILEGES ON " + _scheme + "." + _tables.getString("name") + " FROM GROUP " + _groupName + ";\n");
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
			}
		    } 
		    catch (SQLException x) {
			x.printStackTrace();
		    }
		}
	    }
	return query.toString();
    }
    
    private String exportFunctionsPrivileges(){
	StringBuilder query = new StringBuilder();
	Vector grupos = getGroups();
	//long contador = 0;
	//String consulta = "SELECT p.oid AS prooid, p.proname as name, pg_catalog.format_type(p.prorettype, NULL) AS proresult, " + "CASE WHEN array_to_string(p.proargnames, '') IS NULL THEN '' ELSE array_to_string(p.proargnames, '') END AS proargnames, " + "pg_catalog.oidvectortypes(p.proargtypes) AS proarguments, " + "proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration " + "FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang " + "WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname, proresult";
	String consulta = "SELECT n.nspname || '.' ||proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql'";
	ResultSet resultRevoke = LibSQL.exQuery(consulta);
	try {
	    for (int i = 0; i < grupos.size(); i++) {
		String _groupName = grupos.elementAt(i).toString();
		    while(resultRevoke.next()){
			query.append("REVOKE ALL ON FUNCTION " + resultRevoke.getString("declaration") + " FROM GROUP " + _groupName + ";\n");		    
			//contador++;
		    }
		    ResultSet _functions = LibSQL.exQuery("SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) || ')' AS declaration, array_to_string(proacl,',') AS aclitems FROM pg_catalog.pg_proc p Inner join pg_catalog.pg_namespace n on n.oid = p.pronamespace inner join pg_catalog.pg_language pl on pl.oid = p.prolang where not p.proisagg and lanname = 'plpgsql' AND (array_to_string(proacl,',') like '%" + _groupName + "=X/%')");
		    while (_functions.next()){
		        query.append("GRANT EXECUTE ON FUNCTION " + _functions.getString("declaration") + " TO GROUP " + _groupName + ";\n");
		    }
		resultRevoke.beforeFirst();
	    }
	} catch (SQLException x) {
	    Advisor.printException(x);
	}
	return query.toString();
    }
    
    /*
    
     SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) || ')', array_to_string(proacl,',') AS declaration FROM pg_catalog.pg_proc p Inner join pg_catalog.pg_namespace n on n.oid = p.pronamespace inner join pg_catalog.pg_language pl on pl.oid = p.prolang where not p.proisagg and lanname = 'plpgsql' AND (array_to_string(proacl,',') like '%yakuwari=X/%')
    
    */
    
    /*private String exportFunctionsPrivileges(){
	StringBuilder query = new StringBuilder();
	    Vector grupos = getGroups();
	    Vector esquemas = getSchemes();
	    for (int i = 0; i < grupos.size(); i++) {
		String _groupName = grupos.elementAt(i).toString();
		for (int j = 0; j < esquemas.size(); j++){
		    String _scheme = esquemas.elementAt(j).toString();
		    //ResultSet _functions = LibSQL.exQuery("SELECT declaration, has_function_privilege('" + _groupName + "', declaration, 'EXECUTE') AS execute FROM (SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname = '" + _scheme + "' AND NOT aclcontains(proacl, '=X/admin') ORDER BY p.proname) AS foo;");
		    ResultSet _functions = LibSQL.exQuery("SELECT declaration, has_function_privilege('" + _groupName + "', declaration, 'EXECUTE') AS execute FROM (SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname = '" + _scheme + "' AND NOT (aclcontains(proacl, '=X/admin') OR proacl IS NULL) ORDER BY p.proname) AS foo;");
		    try {
			while (_functions.next()){
			    if (_functions.getBoolean("execute")) {
				query.append("GRANT EXECUTE ON FUNCTION " + _functions.getString("declaration") + " TO GROUP " + _groupName + ";\n");
			    }
			    else{
				query.append("REVOKE ALL ON FUNCTION" + _functions.getString("declaration") + " FROM GROUP " + _groupName + ";\n");
			    }
			}
		    } catch (SQLException x) {
			Advisor.printException(x);
		    }
		}
	    }
	return query.toString();
    }*/

    private Vector getPublicTables() {
	Vector tablasPublicas = new Vector();
	ResultSet _publicTables = LibSQL.exQuery("select nspname || '.' || relname AS table from pg_class\n" + "INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid\n" + " where aclcontains (relacl, '=r/" + DBAdminConfiguration.getDbOwner() + "')\n");
	try {
	    while (_publicTables.next()) {
		tablasPublicas.add(_publicTables.getString("table"));
	    }
	} catch (SQLException e) {
	    Advisor.printException(e);
	}
	ResultSet _tabsTables = LibSQL.exQuery("select nspname || '.' || relname AS table from pg_class\n" + "INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid\n" + " WHERE pg_namespace.nspname = 'tabs' AND relkind = 'r'\n");
	try {
	    while (_tabsTables.next()) {
		tablasPublicas.add(_tabsTables.getString("table"));
	    }
	} catch (SQLException e) {
	    Advisor.printException(e);
	}
	return tablasPublicas;
    }

    private Vector getPublicFunctions() {
	Vector funcionesPublicas = new Vector();
	ResultSet _publicFunctions = LibSQL.exQuery("SELECT declaration FROM (SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' AS declaration,\n" + "proacl FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace\n" + "INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang \n" + "WHERE NOT p.proisagg  AND lanname = 'plpgsql' \n" + "AND aclcontains(proacl, '=X/" + DBAdminConfiguration.getDbOwner() + "') ORDER BY p.proname) AS foo\n");
	try {
	    while (_publicFunctions.next()) {
		funcionesPublicas.add(_publicFunctions.getString("declaration"));
	    }
	} catch (SQLException e) {
	    Advisor.printException(e);
	}
	return funcionesPublicas;
    }

    private void dbConnect_actionPerformed(ActionEvent e) {
	setDataBase();
	lblConnect.setText("Conectado");
	lblConnect.setBackground(Color.GREEN);
    }

    private void btnClear_actionPerformed(ActionEvent e) {
	taDescripcion.setText("");
    }

    private void btnGetQuery_actionPerformed(ActionEvent e) {
	String query = "";
	taDescripcion.setText("");
	if(chkGroup.isSelected()){
	    query += exportGroups();
	}
	if(chkTable.isSelected()){
	    query += exportTablesPrivileges();
	}
	if(chkFunction.isSelected()){
	    query += exportFunctionsPrivileges();
	}
	taDescripcion.setText(query);
    }
}
