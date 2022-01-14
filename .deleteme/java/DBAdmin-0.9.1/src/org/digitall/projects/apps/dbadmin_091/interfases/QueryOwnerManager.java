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
 * QueryOwnerManager.java
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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JTArea;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.classes.DBAdminConfiguration;

public class QueryOwnerManager extends BasicDialog {
//public class DBOptionManager extends JDialog {
    
    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private AddButton btnClear = new AddButton();
    private BasicButton btnGetQuery = new BasicButton();
    private BasicButton dbConnect = new BasicButton();
    
    private BasicScrollPane spDescripcion = new BasicScrollPane();
    
    private BasicPanel jPanel2 = new BasicPanel("Opciones");
    
    private BasicLabel lblNombreFuncion = new BasicLabel();
    private BasicLabel lblDescripcion = new BasicLabel();
    private BasicLabel lblConnect = new BasicLabel();
    
    private BasicCheckBox chkSchema = new BasicCheckBox();
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

    public QueryOwnerManager() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setPreferredSize(new Dimension(392, 158));
	this.setSize(new Dimension(665, 489));
	this.setTitle("Cambiar Propietario");
	btnAccept.setBounds(new Rectangle(570, 435, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	jPanel2.add(chkFunction, null);
	jPanel2.add(chkTable, null);
	jPanel2.add(chkSchema, null);
	jPanel2.add(dbConnect, null);
	jPanel2.add(cbDatabases, null);
	spDescripcion.getViewport().add(taDescripcion, null);
	taDescripcion.setEditable(false);
	jPanel2.add(spDescripcion, null);
	jPanel2.add(lblDescripcion, null);
	jPanel2.add(lblNombreFuncion, null);
	this.getContentPane().add(btnGetQuery, null);
	this.getContentPane().add(lblConnect, null);
	this.getContentPane().add(btnClose, null);
	this.getContentPane().add(jPanel2, null);
	this.getContentPane().add(btnAccept, null);
	this.getContentPane().add(btnClear, null);
	jPanel2.setBounds(new Rectangle(0, 0, 660, 425));
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
	lblConnect.setBounds(new Rectangle(5, 430, 130, 25));
	lblConnect.setOpaque(true);
	lblConnect.setForeground(Color.black);
	lblConnect.setHorizontalTextPosition(SwingConstants.CENTER);
	lblConnect.setHorizontalAlignment(SwingConstants.CENTER);
	lblConnect.setBackground(Color.black);
	chkSchema.setText("Esquemas");
	chkSchema.setBounds(new Rectangle(380, 70, 90, 20));
	chkTable.setText("Tablas");
	chkTable.setBounds(new Rectangle(475, 72, 81, 18));
	chkFunction.setText("Funciones");
	chkFunction.setBounds(new Rectangle(555, 70, 100, 20));
	btnClear.setBounds(new Rectangle(525, 435, 40, 25));
	btnClear.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClear_actionPerformed(e);
		}
	    }
	);
	btnGetQuery.setText("Get Query");
	btnGetQuery.setBounds(new Rectangle(300, 435, 125, 20));
	btnGetQuery.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnGetQuery_actionPerformed(e);
		}
	    }
	);
	btnClose.setBounds(new Rectangle(615, 435, 40, 25));
	btnClose.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClose_actionPerformed(e);
		}
	    }
	);
	lblDescripcion.setText("Consulta");
	lblDescripcion.setBounds(new Rectangle(10, 75, 145, 15));

	spDescripcion.setBounds(new Rectangle(5, 95, 651, 325));
	spDescripcion.setSize(new Dimension(651, 325));
	taDescripcion.setEditable(false);
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
	if(!taDescripcion.getText().equals("")){
	    if(LibSQL.exActualizar('a',taDescripcion.getText())){
	        Advisor.messageBox("Consulta ejecutada exitosamente", "Aviso");
	    }    
	}
	else{
	    Advisor.messageBox("Consulta vacía", "Aviso");
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void limpiarCampos() {
	taDescripcion.setText("");
    }

    private void dbConnect_actionPerformed(ActionEvent e) {
	setDataBase();
	lblConnect.setText("Conectado");
	lblConnect.setBackground(Color.GREEN);
    }

    private void btnClear_actionPerformed(ActionEvent e) {
	taDescripcion.setText("");
    }
    
    private String changeOwnerSchemes(){
	String querySchemes = "SELECT 'ALTER SCHEMA ' || nspname || ' OWNER TO " + DBAdminConfiguration.getDbOwner() + ";' AS query FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname";
	StringBuilder consultaOwner = new StringBuilder();
	try {
	    ResultSet result = LibSQL.exQuery(querySchemes);
	    while(result.next()){
	    consultaOwner.append(result.getString("query")+"\n");
	    }
	    /*if(LibSQL.exActualizar('a',consultaOwner.toString())){
	      
	    } else {
		Advisor.messageBox("Error al intentar cambiar Propietario de Esquemas", "Error");
	    }*/
	} catch (SQLException e) {
	    // TODO
	}
	return consultaOwner.toString();
    }
    
    private String changeOwnerTables(){
	StringBuilder consultaOwner = new StringBuilder();
	String queryTables = "SELECT 'ALTER TABLE ' || nspname || '.' || relname || ' OWNER TO " + DBAdminConfiguration.getDbOwner() + ";' AS query FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND relkind = 'r' AND relname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname\n";
	try {
	    //String queryTables = "SELECT 'ALTER ' || nspname || '.' || relname || ' OWNER TO postgres;' FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND relkind = 'r' AND relname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname";
	    ResultSet resultTables = LibSQL.exQuery(queryTables);
	    while(resultTables.next()){
		consultaOwner.append(resultTables.getString("query")+"\n");
	    }
	    /*if(LibSQL.exActualizar('a',consultaOwner.toString())){
		  
	    } else {
		Advisor.messageBox("Error al intentar cambiar Propietario de Tablas", "Error");
	    }*/
	} catch (SQLException e){
	      // TODO
	}
	return consultaOwner.toString();
    }
    
    private String changeOwnerFunctions(){
	String queryFunctions = "SELECT 'ALTER FUNCTION ' || nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' || ' OWNER TO " + DBAdminConfiguration.getDbOwner() + ";' as declaration, n.nspname FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname\n";
	StringBuilder consultaOwner = new StringBuilder();
	try {
	    ResultSet resultFunctions = LibSQL.exQuery(queryFunctions);
	    while(resultFunctions.next()){
		consultaOwner.append(resultFunctions.getString("declaration")+"\n");
	    }
	    /*if(LibSQL.exActualizar('a',consultaOwner.toString())){
	     
	    } else {
		Advisor.messageBox("Error al intentar cambiar Propietario de Stored Procedures", "Error");
	    }*/
	} catch (SQLException e) {
	    // TODO
	}
	return consultaOwner.toString();
    }

    private void btnGetQuery_actionPerformed(ActionEvent e) {
	String query = "";
	taDescripcion.setText("");
	if(chkSchema.isSelected()){
	    query += changeOwnerSchemes();
	}
	if(chkTable.isSelected()){
	    query += changeOwnerTables();
	}
	if(chkFunction.isSelected()){
	    query += changeOwnerFunctions();
	}
	taDescripcion.setText(query);
    }
}
