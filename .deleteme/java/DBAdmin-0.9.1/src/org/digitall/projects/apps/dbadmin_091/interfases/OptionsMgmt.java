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
 * OptionsMgmt.java
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

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import javax.swing.border.BevelBorder;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.SystemConfiguration;
import org.digitall.projects.apps.dbadmin_091.classes.DBAdminConfiguration;

public class OptionsMgmt extends BasicPanel {
//public class DBOptionManager extends JDialog {

    private BasicPanel pOpciones = new BasicPanel();
    
    private AcceptButton btnAccept = new AcceptButton();
    private AddButton btnClear = new AddButton();
    private BasicButton dbConnect = new BasicButton();
    private AssignButton btnExcluirTabla = new AssignButton();
    
    private BasicPanel jPanel2 = new BasicPanel("Opciones");
    private BasicLabel lblNombreFuncion = new BasicLabel();
    private BasicLabel lblNombrePropietario = new BasicLabel();
    private BasicLabel lblCambiarPropietario = new BasicLabel();
    private BasicLabel lblExcluirTabla = new BasicLabel();
    private BasicLabel lblAccesoPublico = new BasicLabel();

    private JCombo cbDatabases = new JCombo();
    private Vector dbVector = new Vector();
    private String selectedDB = "";
    private String _dbName = "";
    
    private BasicCheckBox chkChangeOwner = new BasicCheckBox();
    private BasicCheckBox chkOwnerSchema = new BasicCheckBox();
    private BasicCheckBox chkOwnerTable = new BasicCheckBox();
    private BasicCheckBox chkOwnerFunction = new BasicCheckBox();
    private BasicCheckBox chkPublicAccess = new BasicCheckBox();
    private BasicCheckBox chkPublicSchema = new BasicCheckBox();
    private BasicCheckBox chkPublicTable = new BasicCheckBox();
    private BasicCheckBox chkPublicFunction = new BasicCheckBox();
    private BasicCheckBox chkPrivileges = new BasicCheckBox();
    private BasicCheckBox chkPrivilegesGroup = new BasicCheckBox();
    private BasicCheckBox chkPrivilegesTable = new BasicCheckBox();
    private BasicCheckBox chkPrivilegesFunction = new BasicCheckBox();
    private BasicCheckBox chkSecurityDefiner = new BasicCheckBox();
    private BasicCheckBox chkSecurityFunction = new BasicCheckBox();

    private BasicTextField tfOwner = new BasicTextField();

    private JSeparator jSeparator1 = new JSeparator();
    private JSeparator jSeparator2 = new JSeparator();
    private JSeparator jSeparator3 = new JSeparator();
    
    private JTextArea taDescripcion = new JTextArea();
    
    private StringBuilder consulta = new StringBuilder();
    private String owner = "postgres";
    private JSeparator jSeparator4 = new JSeparator();
    private BasicCheckBox chkExportarFuncionalidades = new BasicCheckBox();
    private BasicCheckBox chkExportarFuncionalidadesSistema = new BasicCheckBox();
    private BasicCheckBox chkExportarModulosSistema = new BasicCheckBox();

    public OptionsMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setPreferredSize(new Dimension(577, 486));
	this.setSize(new Dimension(660, 486));
	this.setTitle("Cambiar Propietario");
	btnAccept.setBounds(new Rectangle(590, 350, 70, 50));
        btnAccept.setText("Generar \nScript");
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
        jPanel2.add(dbConnect, null);
	jPanel2.add(cbDatabases, null);
	jPanel2.add(lblNombreFuncion, null);
	pOpciones.setBorder(BorderPanel.getBorderPanel("Opciones"));
        pOpciones.add(chkExportarModulosSistema, null);
        pOpciones.add(chkExportarFuncionalidadesSistema, null);
        pOpciones.add(chkExportarFuncionalidades, null);
        pOpciones.add(jSeparator4, null);
        pOpciones.add(jSeparator3, null);
        pOpciones.add(jSeparator2, null);
        pOpciones.add(jSeparator1, null);
        pOpciones.add(chkSecurityFunction, null);
        pOpciones.add(chkPrivilegesFunction, null);
        pOpciones.add(chkPrivilegesTable, null);
        pOpciones.add(chkPrivilegesGroup, null);
        pOpciones.add(lblAccesoPublico, null);
        pOpciones.add(btnExcluirTabla, null);
	pOpciones.add(lblExcluirTabla, null);
        pOpciones.add(chkPublicFunction, null);
        pOpciones.add(chkPublicTable, null);
        pOpciones.add(chkPublicSchema, null);
        pOpciones.add(lblCambiarPropietario, null);
        pOpciones.add(lblNombrePropietario, null);
        pOpciones.add(tfOwner, null);
        pOpciones.add(chkPrivileges, null);
        pOpciones.add(chkPublicAccess, null);
        pOpciones.add(chkSecurityDefiner, null);
        pOpciones.add(chkChangeOwner, null);
        pOpciones.add(chkOwnerTable, null);
        pOpciones.add(chkOwnerFunction, null);
        pOpciones.add(chkOwnerSchema, null);
        this.add(taDescripcion, null);
        this.add(pOpciones, null);
	this.add(jPanel2, null);
        this.add(btnAccept, null);
        this.add(btnClear, null);
        jPanel2.setBounds(new Rectangle(0, 360, 575, 65));
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
	chkOwnerSchema.setText("Esquemas");
	chkOwnerSchema.setBounds(new Rectangle(300, 75, 90, 20));
	chkOwnerTable.setText("Tablas");
	chkOwnerTable.setBounds(new Rectangle(395, 75, 70, 20));
	chkOwnerFunction.setText("Procedimientos");
	chkOwnerFunction.setBounds(new Rectangle(470, 75, 125, 20));
	btnClear.setBounds(new Rectangle(590, 415, 70, 50));
        btnClear.setText("Limpiar\nCampos");
        btnClear.setToolTipText("Limpiar Campos");
	btnClear.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClear_actionPerformed(e);
		}
	    }
	);
	pOpciones.setBounds(new Rectangle(5, 0, 650, 340));
	pOpciones.setLayout(null);
	chkChangeOwner.setText("1. Cambiar Propietario:");
	chkChangeOwner.setBounds(new Rectangle(10, 25, 160, 20));
	chkSecurityDefiner.setText("4. Security Definer:");
	chkSecurityDefiner.setBounds(new Rectangle(10, 230, 160, 18));
	chkPublicAccess.setText("2. Acceso Público:");
	chkPublicAccess.setBounds(new Rectangle(10, 115, 160, 18));
	chkPrivileges.setText("3. Privilegios:");
	chkPrivileges.setBounds(new Rectangle(10, 170, 130, 20));
	tfOwner.setBounds(new Rectangle(300, 45, 120, 20));
	lblNombrePropietario.setText(". Nombre propietario: ");
	lblNombrePropietario.setBounds(new Rectangle(160, 50, 140, 15));
	lblCambiarPropietario.setText(". Cambiar propietario a: ");
	lblCambiarPropietario.setBounds(new Rectangle(160, 80, 145, 15));
	chkPublicSchema.setText("Esquemas");
	chkPublicSchema.setBounds(new Rectangle(455, 130, 90, 18));
	chkPublicTable.setText("Tablas");
	chkPublicTable.setBounds(new Rectangle(285, 130, 75, 20));
	chkPublicFunction.setText("Procedimientos");
	chkPublicFunction.setBounds(new Rectangle(360, 130, 125, 20));
	lblExcluirTabla.setText(". Excluir Tabla:");
	lblExcluirTabla.setBounds(new Rectangle(15, 145, 95, 15));
	btnExcluirTabla.setBounds(new Rectangle(105, 140, 25, 20));
	btnExcluirTabla.setHorizontalAlignment(SwingConstants.LEFT);
	btnExcluirTabla.setEnabled(false);
	lblAccesoPublico.setText(". Acceso Público a:");
	lblAccesoPublico.setBounds(new Rectangle(160, 135, 125, 15));
	chkPrivilegesGroup.setText("Grupos");
	chkPrivilegesGroup.setBounds(new Rectangle(160, 190, 80, 20));
	chkPrivilegesTable.setText("Tablas");
	chkPrivilegesTable.setBounds(new Rectangle(245, 190, 75, 18));
	chkPrivilegesFunction.setText("Procedimientos");
	chkPrivilegesFunction.setBounds(new Rectangle(325, 190, 125, 18));
	chkSecurityFunction.setText("Procedimientos");
	chkSecurityFunction.setBounds(new Rectangle(160, 250, 125, 18));
	chkChangeOwner.addActionListener(new ActionListener() {

		/*public void itemStateChanged(ActionEvent e) {
		    chkChangeOwner_itemStateChanged(e);
		}*/

		public void actionPerformed(ActionEvent e) {
		    chkChangeOwner_itemStateChanged(e);
		}
	    }
	);
	
	chkPublicAccess.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
		    chkPublicAccess_itemStateChanged(e);
		}
	    }
	);
	
	chkPrivileges.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
		    chkPrivileges_itemStateChanged(e);
		}
	    }
	);
	
	chkSecurityDefiner.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
		    chkSecurityDefiner_itemStateChanged(e);
		}
	    }
	);
	jSeparator1.setBounds(new Rectangle(5, 105, 640, 2));
	jSeparator2.setBounds(new Rectangle(5, 160, 640, 2));
	jSeparator3.setBounds(new Rectangle(5, 220, 640, 2));
        taDescripcion.setBounds(new Rectangle(7, 340, 585, 130));
        taDescripcion.setBackground(new Color(148, 148, 148));
        taDescripcion.setForeground(Color.white);
        taDescripcion.setText("" +
            "Es preciso seguir un orden de ejecución para exportar los privilegios de una Base de Datos. \n" +
            "Los pasos son los siguientes:\n" +
            //"\n" +
            "       1. Exportar Script para cambiar el Propietario a esquemas, tablas y procedimientos\n" + 
            "       2. Exportar Script de tablas y procedimientos públicos " + "\n" +
            "       3.1 (Opcional) Exportar Script para la creación de Grupos\n" + 
            "       3.2 Exportar Script de privilegios de tablas y procedimientos\n" + 
            "       4. Exportar Script para establecer Security Definer a procedimientos\n" +
            "       5. (Opcional) Exportar Script de Módulos y Funcionalidades del Sistema");
        taDescripcion.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jPanel2.setVisible(false);
	//loadStuff();
	tfOwner.setText("postgres");
	chkPublicSchema.setVisible(false);
	tfOwner.setEditable(false);
	chkChangeOwner.setSelected(true);
	chkPublicAccess.setSelected(true);
	chkPrivileges.setSelected(true);
	chkSecurityDefiner.setSelected(true);
	limpiarCampos();
	lblExcluirTabla.setVisible(false);
	btnExcluirTabla.setVisible(false);
        taDescripcion.setEditable(false);
        taDescripcion.setEnabled(false);
        jSeparator4.setBounds(new Rectangle(5, 280, 640, 2));
        jSeparator4.setSize(new Dimension(640, 2));
        chkExportarFuncionalidades.setText("5. Funcionalidades del Sistema:");
        chkExportarFuncionalidades.setBounds(new Rectangle(10, 290, 210, 20));
        chkExportarFuncionalidades.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    chkExportarFuncionalidades_itemStateChanged(e);
                }
            });
        chkExportarFuncionalidadesSistema.setText("Funcionalidades del Sistema");
        chkExportarFuncionalidadesSistema.setBounds(new Rectangle(315, 310,
                                                                  190, 20));
        chkExportarModulosSistema.setText("Módulos del Sistema");
        chkExportarModulosSistema.setBounds(new Rectangle(160, 310, 145, 20));
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
	    
	} else {
	    Advisor.messageBox("Error al intentar conectarse a " + _dbName, "Error");
	}
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	consulta = new StringBuilder();
	obtenerConsultas();
        if(!consulta.toString().equals("")){
            QueryTextArea queryTextArea = new QueryTextArea(consulta);
            ComponentsManager.centerWindow(queryTextArea);
            queryTextArea.setModal(true);
            queryTextArea.setVisible(true);    
        } else {
            Advisor.messageBox("Debe seleccionar al menos una opción", "Mensaje");
        }
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	//dispose();
    }

    private void dbConnect_actionPerformed(ActionEvent e) {
	setDataBase();
    }

    private void btnClear_actionPerformed(ActionEvent e) {
	limpiarCampos();
    }
    
    private void chkChangeOwner_itemStateChanged(ActionEvent e){
	if(chkChangeOwner.isSelected()){
	    chkOwnerSchema.setEnabled(true);
	    chkOwnerTable.setEnabled(true);
	    chkOwnerFunction.setEnabled(true);
	}else{
	    chkOwnerSchema.setEnabled(false);
	    chkOwnerTable.setEnabled(false);
	    chkOwnerFunction.setEnabled(false);
	}
    }
    
    private void chkPrivileges_itemStateChanged(ActionEvent e){
	if(chkPrivileges.isSelected()){
	    chkPrivilegesGroup.setEnabled(true);
	    chkPrivilegesTable.setEnabled(true);
	    chkPrivilegesFunction.setEnabled(true);
	}else{
	    chkPrivilegesGroup.setEnabled(false);
	    chkPrivilegesTable.setEnabled(false);
	    chkPrivilegesFunction.setEnabled(false);
	}
    }
    
    private void chkPublicAccess_itemStateChanged(ActionEvent e){
	if(chkPublicAccess.isSelected()){
	    chkPublicTable.setEnabled(true);
	    chkPublicFunction.setEnabled(true);
	}else{
	    chkPublicTable.setEnabled(false);
	    chkPublicFunction.setEnabled(false);
	}
    }
    
    private void chkSecurityDefiner_itemStateChanged(ActionEvent e){
	if(chkSecurityDefiner.isSelected()){
	    chkSecurityFunction.setEnabled(true);
	}else{
	    chkSecurityFunction.setEnabled(false);
	}
    }
    
    private void chkExportarFuncionalidades_itemStateChanged(ItemEvent e) {
        if(chkExportarFuncionalidades.isSelected()){
            chkExportarModulosSistema.setEnabled(true);
            chkExportarFuncionalidadesSistema.setEnabled(true);
        }else{
            chkExportarModulosSistema.setEnabled(false);
            chkExportarFuncionalidadesSistema.setEnabled(false);
        }
    }
    
    private void limpiarCampos(){
	chkChangeOwner.setSelected(false);
	chkPublicAccess.setSelected(false);
	chkPrivileges.setSelected(false);
	chkSecurityDefiner.setSelected(false);
	
	chkOwnerSchema.setSelected(false);
	chkOwnerTable.setSelected(false);
	chkOwnerFunction.setSelected(false);
	
	chkOwnerSchema.setEnabled(false);
	chkOwnerTable.setEnabled(false);
	chkOwnerFunction.setEnabled(false);
	
	chkPublicSchema.setSelected(false);
	chkPublicTable.setSelected(false);
	chkPublicFunction.setSelected(false);
	
	chkPublicSchema.setEnabled(false);
	chkPublicTable.setEnabled(false);
	chkPublicFunction.setEnabled(false);
	
	chkPrivilegesGroup.setSelected(false);
	chkPrivilegesTable.setSelected(false);
	chkPrivilegesFunction.setSelected(false);
	
	chkPrivilegesGroup.setEnabled(false);
	chkPrivilegesTable.setEnabled(false);
	chkPrivilegesFunction.setEnabled(false);
	
	chkSecurityFunction.setSelected(false);
	chkSecurityFunction.setEnabled(false);
        
        chkExportarFuncionalidades.setSelected(false);
        chkExportarFuncionalidadesSistema.setSelected(false);
        chkExportarModulosSistema.setSelected(false);
        chkExportarFuncionalidadesSistema.setEnabled(false);
        chkExportarModulosSistema.setEnabled(false);
    }
    
    private String changeOwnerSchemes(){
	String querySchemes = "SELECT 'ALTER SCHEMA ' || nspname || ' OWNER TO " + owner + ";' AS query FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname";
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
	String queryTables = "SELECT 'ALTER TABLE ' || nspname || '.' || relname || ' OWNER TO " + owner + ";' AS query FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND relkind = 'r' AND relname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname\n";
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
	String queryFunctions = "SELECT 'ALTER FUNCTION ' || nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' || ' OWNER TO " + owner + ";' as declaration, n.nspname FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname\n";
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
	
    }
    
    private void obtenerConsultas(){
	if(chkChangeOwner.isSelected()){
	    changeOwner();
	}
	if(chkPublicAccess.isSelected()){
	    getPublicAccess();    
	}
	if(chkPrivileges.isSelected()){
	    getPrivileges();
	}
	if(chkSecurityDefiner.isSelected()){
	    getSecurityDefiner();
	}
        if(chkExportarFuncionalidades.isSelected()){
            getFuncionalidades();
        }
    }
    
    private void changeOwner(){
	consulta.append("-- <<Cambiar Propietario >> "+"\n");
	consulta.append(" "+"\n");
	if(chkOwnerSchema.isSelected()){
	    consulta.append(changeOwnerSchemes());
	}
	if(chkOwnerTable.isSelected()){
	    consulta.append(changeOwnerTables());
	}
	if(chkOwnerFunction.isSelected()){
	    consulta.append(changeOwnerFunctions());
	}
	//System.out.println("ingreso changeOwner");
    }
    
    private void getPublicAccess(){
	consulta.append("-- << Acceso Público >> \n");
	consulta.append(" "+"\n");
	if(chkPublicTable.isSelected()){
	    consulta.append(getPublicTables());
	}
	if(chkPublicFunction.isSelected()){
	    consulta.append(getPublicFunctions());
	}
    }
    
    private void getPrivileges(){
	consulta.append("-- << Privilegios exportados >> \n");
	consulta.append(" "+"\n");
	if(chkPrivilegesGroup.isSelected()){
	    consulta.append(getQueryCreateGroups());
	}
	if(chkPrivilegesTable.isSelected()){
	    consulta.append(exportTablesPrivileges());
	}
	if(chkPrivilegesFunction.isSelected()){
	    consulta.append(exportFunctionsPrivileges());
	}
    }
    
    private void getSecurityDefiner(){
	consulta.append("-- << Security Definer >>\n");
	consulta.append(" "+"\n");
	consulta.append(loadQuerySecurityDefiner());
    }
    
    private void getFuncionalidades(){
        consulta.append("-- << Funcionalidades del Sistema >> \n");
        consulta.append(" "+"\n");
        if(chkExportarModulosSistema.isSelected()){
            consulta.append(exportarModulosSistema());
        }
        if(chkExportarFuncionalidadesSistema.isSelected()){
            consulta.append(exportarFuncionalidadesSistema());
        }
    }
    
    private String loadQuerySecurityDefiner() {
	StringBuilder query = new StringBuilder();
	//ResultSet _securityDefiner = LibSQL.exQuery("SELECT declaration FROM (SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname) AS foo;");
	ResultSet _securityDefiner = LibSQL.exQuery("SELECT 'ALTER FUNCTION '|| n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ') SECURITY DEFINER' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' ORDER BY p.proname;");
	
	try {
	    while (_securityDefiner.next()) {
		//query.append("ALTER FUNCTION " + _securityDefiner.getString("declaration") + " SECURITY DEFINER;\n");
		query.append(_securityDefiner.getString("declaration")+";\n");
	    }
	} catch (SQLException x) {
	    Advisor.printException(x);
	}
	return query.toString();
    }
    
    private String getPublicTables() {
	StringBuilder query = new StringBuilder();
	Vector tablasPublicas = new Vector();
            ResultSet _publicTables = LibSQL.exQuery("SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid WHERE (aclcontains (relacl, '=r/" + owner + "') OR relacl IS NULL) AND relkind = 'r' AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' ORDER BY nspname,relname");
	try {
	    while (_publicTables.next()){
		tablasPublicas.add(_publicTables.getString("table"));
	    }
	} catch (SQLException e){
	    Advisor.printException(e);
	}
	for(int i=0; i< tablasPublicas.size();i++){
	    query.append("REVOKE ALL PRIVILEGES ON " + tablasPublicas.elementAt(i) + " FROM PUBLIC;\n");
	    query.append("GRANT SELECT ON " + tablasPublicas.elementAt(i) + " TO PUBLIC;\n");
	}
	/*ResultSet _tabsTables = LibSQL.exQuery("select nspname || '.' || relname AS table from pg_class\n" + "INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid\n" + " WHERE pg_namespace.nspname = 'tabs' AND relkind = 'r'\n");
	try {
	    while (_tabsTables.next()) {
		tablasPublicas.add(_tabsTables.getString("table"));
	    }
	} catch (SQLException e) {
	    Advisor.printException(e);
	}*/
	return query.toString();
    }
    
    private String getPublicFunctions() {
	Vector funcionesPublicas = new Vector();
	StringBuilder query = new StringBuilder();
	ResultSet _publicFunctions = LibSQL.exQuery("SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) || ')' AS declaration, array_to_string(proacl,',') AS aclitem FROM pg_catalog.pg_proc p Inner join pg_catalog.pg_namespace n on n.oid = p.pronamespace inner join pg_catalog.pg_language pl on pl.oid = p.prolang where not p.proisagg and lanname = 'plpgsql' AND (array_to_string(proacl,',') like '%,=X/%' OR array_to_string(proacl,',') like '=X/%' OR proacl IS NULL)");
	try {
	    while (_publicFunctions.next()) {
		funcionesPublicas.add(_publicFunctions.getString("declaration"));
	    }
	} catch (SQLException e) {
	    Advisor.printException(e);
	}
	for (int j = 0; j < funcionesPublicas.size(); j++) {
	    if (!SystemConfiguration.tablesExclusionList.contains(funcionesPublicas.elementAt(j).toString())) {
		query.append("REVOKE ALL ON FUNCTION " + funcionesPublicas.elementAt(j).toString() + " FROM PUBLIC;\n");
		query.append("GRANT EXECUTE ON FUNCTION " + funcionesPublicas.elementAt(j).toString() + " TO PUBLIC;\n");
	    } else {
		System.out.println("Excluyendo función " + funcionesPublicas.elementAt(j).toString());
	    }
	}
	
	return query.toString();
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
	//      queryCreateGroup.append("CREATE GROUP " + rs.getString("name")+";\n");
		queryCreateGroup.append(rs.getString("name")+"\n");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return queryCreateGroup.toString();
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
    
    private String exportarModulosSistema(){
        StringBuilder query = new StringBuilder();
        String consulta = "SELECT 'INSERT INTO admin.modulos (idmodulo,nombre,estado) VALUES('|| idmodulo || ',''' || nombre || ''',''' || estado || ''');' AS modulos FROM admin.modulos WHERE modulos.estado <> '*';";
        
        ResultSet rs = LibSQL.exQuery(consulta);
        try {
            while (rs.next()) {
                query.append(rs.getString("modulos")+"\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.toString();
    }
    
    private String exportarFuncionalidadesSistema(){
        StringBuilder query = new StringBuilder();
        String consulta = "SELECT 'INSERT INTO admin.funciones (idfuncion,idmodulo,numerofuncion,nombre,descripcion,grupo,estado) VALUES('||idfuncion|| ',' || idmodulo || ','|| numerofuncion ||',''' || nombre || ''',''' || descripcion || ''',''' || grupo || ''',''' || estado || ''');' AS funcionalidades FROM admin.funciones WHERE funciones.estado <> '*';";
        ResultSet rs = LibSQL.exQuery(consulta);
        try {
            while (rs.next()) {
                query.append(rs.getString("funcionalidades")+"\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.toString();
    }
}
