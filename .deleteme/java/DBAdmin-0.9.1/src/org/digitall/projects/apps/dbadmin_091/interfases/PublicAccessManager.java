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
 * PublicAccessManager.java
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
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckList;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.buttons.ReloadButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.DBAdminMain;
import org.digitall.projects.apps.dbadmin_091.classes.DBAdminConfiguration;
import org.digitall.projects.apps.dbadmin_091.classes.DBControl;
import org.digitall.projects.apps.dbadmin_091.classes.PublicAccess;

public class PublicAccessManager extends BasicPanel {

    private SaveDataButton saveUsersByGroup = new SaveDataButton();
    private CancelDataButton cancelUsersByGroup = new CancelDataButton();
    private BasicButton btnExportPublics = new BasicButton();
    private BasicButton btnExportPrivileges = new BasicButton();
    private RefreshGridButton btnRefresh = new RefreshGridButton();
    private BasicButton btnChangeOwner = new BasicButton();
    private BasicButton btnSecurityDefiner = new BasicButton();
    
    private BasicScrollPane spData = new BasicScrollPane();
    private BasicScrollPane spUsersByGroup = new BasicScrollPane();
    private BasicList dataList = new BasicList();
    private BasicCheckList usersByGroup = new BasicCheckList();
    
    private BasicLabel lblType = new BasicLabel();
    private BasicLabel lblPublicTypes = new BasicLabel();
    private BasicLabel lblUsers = new BasicLabel();
    private BasicLabel lblTipo = new BasicLabel();
    private BasicLabel lblEsquema = new BasicLabel();
    
    private JCombo cbTipo = new JCombo();
    private JCombo cbSchemes = new JCombo();
    
    private Vector newPublicTables = new Vector();
    private Vector newNonPublicTables = new Vector();
    private Vector newPublicFunctions = new Vector();
    private Vector newNonPublicFunctions = new Vector();
    
    private TransferList transferList = new TransferList("","");
    private PublicAccess publicAccess = new PublicAccess();
    private DBAdminMain manager;
    private DBAdminPanel dbAdminPanel;
    private final int TABLA = 0;
    private final int STORED = 1;
    private int opcionSeleccionada = -1;
    

    public PublicAccessManager(DBAdminMain _manager) {
	//manager = _manager;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PublicAccessManager(DBAdminPanel _manager) {
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
	spData.setBounds(new Rectangle(-95, 100, 245, 250));
	spData.setSize(new Dimension(245, 250));
	spUsersByGroup.setBounds(new Rectangle(810, 30, 245, 355));
	spUsersByGroup.setSize(new Dimension(245, 355));
        this.add(btnRefresh, null);
        this.add(btnSecurityDefiner, null);
	this.add(btnChangeOwner, null);
	this.add(btnExportPrivileges, null);
	this.add(btnExportPublics, null);
        this.add(lblPublicTypes, null);
        this.add(transferList, null);
        this.add(cbSchemes, null);
        this.add(lblEsquema, null);
        this.add(cbTipo, null);
        this.add(lblTipo, null);
        this.add(lblUsers, null);
        this.add(lblType, null);
        add(saveUsersByGroup, null);
        add(cancelUsersByGroup, null);
        spData.getViewport().add(dataList);
	usersByGroup.add(spData, null);
	spUsersByGroup.getViewport().add(usersByGroup);
	add(spUsersByGroup, null);
	dataList.addListSelectionListener(new ListSelectionListener() {

					 public void valueChanged(ListSelectionEvent e) {
					     if (dataList.getSelectedIndex() > -1) {
						 //loadUsersByGroup(dataList.getSelectedValue().toString());
					     }
					 }

				     }
	);
	dataList.addMouseListener(new MouseAdapter() {

				 public void mouseClicked(MouseEvent e) {
				     //groups_mouseClicked(e);
				 }

			     }
	);
	lblType.setBounds(new Rectangle(20, 55, 200, 20));
	lblUsers.setText("Usuarios");
	lblUsers.setBounds(new Rectangle(810, 10, 75, 15));
	lblTipo.setText("Tipo");
	lblTipo.setBounds(new Rectangle(350, 5, 80, 15));
	cbTipo.setBounds(new Rectangle(350, 25, 245, 20));
	lblEsquema.setText("Esquema");
	lblEsquema.setBounds(new Rectangle(20, 5, 80, 14));
	cbSchemes.setBounds(new Rectangle(20, 25, 285, 20));
	lblPublicTypes.setBounds(new Rectangle(350, 55, 200, 20));
	transferList.setBounds(new Rectangle(15, 90, 510, 235));
	transferList.setBounds(new Rectangle(15, 70, 625, 340));
	transferList.setSize(new Dimension(627, 340));
        btnRefresh.setBounds(new Rectangle(314, 25, 30, 20));
        btnRefresh.setToolTipText("Recargar listas");
        btnRefresh.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnRefresh_actionPerformed(e);
                }
            });
        btnExportPrivileges.setText("Export Privileges");
	btnExportPrivileges.setBounds(new Rectangle(170, 430, 85, 35));
	btnExportPrivileges.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnExportPrivileges_actionPerformed(e);
		}
	    }
	);
	btnChangeOwner.setText("Change Owner");
	btnChangeOwner.setBounds(new Rectangle(65, 430, 85, 35));
	btnChangeOwner.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnChangeOwner_actionPerformed(e);
		}
	    }
	);
	btnSecurityDefiner.setText("Security Definer");
	btnSecurityDefiner.setBounds(new Rectangle(270, 430, 85, 35));
	btnSecurityDefiner.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSecurityDefiner_actionPerformed(e);
		}
	    }
	);
	btnExportPublics.setText("Exportar Funciones Públicas");
	btnExportPublics.setBounds(new Rectangle(375, 430, 115, 35));
	btnExportPublics.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnExportPublics_actionPerformed(e);
		}
	    }
	);
	saveUsersByGroup.setBounds(new Rectangle(500, 415, 70, 50));
	saveUsersByGroup.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    saveUsersByGroup_actionPerformed(e);
					}

				    }
	);
	saveUsersByGroup.setText("Guardar\ncambios");
	cancelUsersByGroup.setBounds(new Rectangle(575, 415, 70, 50));
	cancelUsersByGroup.addActionListener(new ActionListener() {
					  public void actionPerformed(ActionEvent e) {
					      cancelUsersByGroup_actionPerformed(e);
					  }

				      }
	);
	cancelUsersByGroup.setText("Cancelar\ncambios");
	cbSchemes.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbSchemes.getSelectedIndex() > -1) {
						    loadData();
						}
					    }
					}

				    }
	);
	cbTipo.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbSchemes.getSelectedIndex() > -1) {
						    loadData();
						}
					    }
					}

				    }
	);
	loadTipos();
	btnChangeOwner.setVisible(false);
	btnExportPrivileges.setVisible(false);
	btnExportPublics.setVisible(false);
	btnSecurityDefiner.setVisible(false);
    }

    public void boot() {
	cbSchemes.setGeneralItem(true);
	cbSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	//cbSchemes.addItem("Todos");
	cbSchemes.setSelectedIndex(0);
	//loadPublicAccess();
	loadData();
    }
    
    public void boot(ResultSet _schemes) {
	cbSchemes.setGeneralItem(true);
	cbSchemes.loadJCombo(_schemes);
	//cbSchemes.addItem("Todos");
	cbSchemes.setSelectedIndex(0);
	//loadPublicAccess();
	loadData();
    }
    
    public void setEnabledTab(boolean _enabled){
	cbSchemes.setEnabled(_enabled);
	cbTipo.setEnabled(_enabled);
	transferList.setEnabled(_enabled);
	transferList.setEnabled(_enabled);
	btnChangeOwner.setEnabled(_enabled);
	btnExportPrivileges.setEnabled(_enabled);
	btnExportPublics.setEnabled(_enabled);
	btnSecurityDefiner.setEnabled(_enabled);
	saveUsersByGroup.setEnabled(_enabled);
	cancelUsersByGroup.setEnabled(_enabled);
    }
    
    private void loadData(){
	if(cbTipo.getSelectedItem().equals("Tablas")){
	    lblType.setText("Tablas");
	    lblPublicTypes.setText("Tablas públicas");
	    loadTablesByScheme(cbSchemes.getSelectedItem().toString());
	    btnExportPublics.setText("Exportar Tablas Públicas");
	    opcionSeleccionada = TABLA;
	}
	else{
	    lblType.setText("Stored Procedures");
	    lblPublicTypes.setText("Stored Procedures Públicos");
	    loadFunctionsByScheme(cbSchemes.getSelectedItem().toString());
	    btnExportPublics.setText("Exportar Stored Públicos");
	    opcionSeleccionada = STORED;
	}
    }

    /**2009-12-07(moraless)*/
    private void loadTablesByScheme(String _scheme){
	//if(DBControl.existTable("admin","publicaccess")){
	    String queryMadre = "";
	    String queryHija = "";
	    
            if(_scheme.equals("Todos")){
                ////queryMadre = "SELECT relnamespace, relname as name,nspname , nspname ||'.'|| relname as originalname FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND relkind = 'r' AND nspname || '.' || bc.relname NOT IN (SELECT admin.publicaccess.nombre FROM admin.publicaccess WHERE publicaccess.estado <> '*') ORDER BY nspname, relname";
                ////queryHija = "SELECT idpublic,nombre,tipo FROM admin.publicaccess WHERE tipo = 'tabla' AND estado <> '*' ORDER BY nombre";
                //queryMadre = "SELECT relnamespace, relname as name, nspname ||'.' || relname as originalname FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND relkind = 'r' AND 'tabs.' || bc.relname NOT IN (SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid where aclcontains (relacl, '=r/postgres') AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' ORDER BY nspname,relname) ORDER BY originalname,relname;";
                    queryMadre = "SELECT relnamespace, relname as name, nspname ||'.' || relname as originalname FROM pg_class bc, pg_namespace ns WHERE relname NOT LIKE 'pg%' AND nspname <> 'information_schema' AND bc.relnamespace = ns.oid AND relkind = 'r' AND  ns.nspname||'.' || bc.relname NOT IN (SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid WHERE ((array_to_string(relacl,',') LIKE '%,=r/%' ) OR (array_to_string(relacl,',') LIKE '=r/%' ) OR relacl IS NULL) AND relkind = 'r' AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' ORDER BY nspname,relname) ORDER BY originalname,relname;";
                queryHija = "SELECT nspname || '.' || relname AS table, array_to_string(relacl,',') from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid WHERE ((array_to_string(relacl,',') LIKE '%,=r/%' ) OR (array_to_string(relacl,',') LIKE '=r/%' ) OR relacl IS NULL) AND relkind = 'r' AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' ORDER BY nspname,relname;";
            }
            else{
                ////queryMadre = "SELECT relnamespace, relname as name, '" + _scheme + ".' || relname as originalname FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + cbSchemes.getSelectedItem() + "' AND relkind = 'r' AND '" + cbSchemes.getSelectedItem() + ".' || bc.relname NOT IN (SELECT admin.publicaccess.nombre FROM admin.publicaccess WHERE publicaccess.estado <> '*') ORDER BY relname";
                ////queryHija = "SELECT idpublic,nombre,tipo FROM admin.publicaccess WHERE UPPER(nombre) LIKE UPPER('" +_scheme+"." +"%') AND tipo = 'tabla' AND estado <> '*'";    
                queryMadre = "SELECT relnamespace, relname as name, '" + _scheme +".' || relname as originalname FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" +
                _scheme + "' AND relkind = 'r' AND '" + _scheme + ".' || bc.relname NOT IN (SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid where ((array_to_string(relacl,',') LIKE '%,=r/%' ) OR (array_to_string(relacl,',') LIKE '=r/%' ) OR relacl IS NULL) AND relkind = 'r' AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' AND nspname = '" + _scheme + "' ORDER BY nspname,relname) ORDER BY relname;\n";
                queryHija = "SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid where ((array_to_string(relacl,',') LIKE '%,=r/%' ) OR (array_to_string(relacl,',') LIKE '=r/%' ) OR relacl IS NULL) AND relkind = 'r' AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' AND nspname = '" + _scheme + "' ORDER BY nspname,relname;";
            }
            
            /* el siguiente codigo comentado funciona correctamente, permite cargar las funciones/tablas públicas pero 
             * obtiene solo las funciones/tablas para un propietario fijado previamente.
            if(_scheme.equals("Todos")){
		////queryMadre = "SELECT relnamespace, relname as name,nspname , nspname ||'.'|| relname as originalname FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND relkind = 'r' AND nspname || '.' || bc.relname NOT IN (SELECT admin.publicaccess.nombre FROM admin.publicaccess WHERE publicaccess.estado <> '*') ORDER BY nspname, relname";
		////queryHija = "SELECT idpublic,nombre,tipo FROM admin.publicaccess WHERE tipo = 'tabla' AND estado <> '*' ORDER BY nombre";
		//queryMadre = "SELECT relnamespace, relname as name, nspname ||'.' || relname as originalname FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND relkind = 'r' AND 'tabs.' || bc.relname NOT IN (SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid where aclcontains (relacl, '=r/postgres') AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' ORDER BY nspname,relname) ORDER BY originalname,relname;";
		queryMadre = "SELECT relnamespace, relname as name, nspname ||'.' || relname as originalname FROM pg_class bc, pg_namespace ns WHERE relname NOT LIKE 'pg%' AND nspname <> 'information_schema' AND bc.relnamespace = ns.oid AND relkind = 'r' AND  ns.nspname||'.' || bc.relname NOT IN (SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid where (aclcontains (relacl, '=r/postgres') OR relacl IS NULL) AND relkind = 'r' AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' ORDER BY nspname,relname) ORDER BY originalname,relname;";
	        queryHija = "SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid where (aclcontains (relacl, '=r/postgres') OR relacl IS NULL) AND relkind = 'r' AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' ORDER BY nspname,relname;";
	    }
	    else{
		////queryMadre = "SELECT relnamespace, relname as name, '" + _scheme + ".' || relname as originalname FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + cbSchemes.getSelectedItem() + "' AND relkind = 'r' AND '" + cbSchemes.getSelectedItem() + ".' || bc.relname NOT IN (SELECT admin.publicaccess.nombre FROM admin.publicaccess WHERE publicaccess.estado <> '*') ORDER BY relname";
		////queryHija = "SELECT idpublic,nombre,tipo FROM admin.publicaccess WHERE UPPER(nombre) LIKE UPPER('" +_scheme+"." +"%') AND tipo = 'tabla' AND estado <> '*'";    
		queryMadre = "SELECT relnamespace, relname as name, '" + _scheme +".' || relname as originalname FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" +
		_scheme + "' AND relkind = 'r' AND '" +	_scheme + ".' || bc.relname NOT IN (SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid where (aclcontains (relacl, '=r/postgres') OR relacl IS NULL) AND relkind = 'r' AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' ORDER BY nspname,relname) ORDER BY relname;\n";
	        queryHija = "SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid where (aclcontains (relacl, '=r/postgres') OR relacl IS NULL) AND relkind = 'r' AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' AND nspname = '" + _scheme + "' ORDER BY nspname,relname;";
	    }
            */
	    //
	    ResultSet rs = LibSQL.exQuery(queryMadre);
	    Vector vecMadre = new Vector();
	    try {
		while (rs.next()) {
		    vecMadre.add(new TransferListModel(-1,rs.getString("originalname"),rs.getString("originalname")));
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    rs = LibSQL.exQuery(queryHija);
	    Vector vecHija = new Vector();
	    try {
		while (rs.next()) {
		    //vecHija.add(new TransferListModel(rs.getInt("idpublic"),removeScheme(rs.getString("nombre")),rs.getString("nombre")));
		    ////vecHija.add(new TransferListModel(rs.getInt("idpublic"),rs.getString("nombre"),rs.getString("nombre")));
		    vecHija.add(new TransferListModel(-1,rs.getString("table"),rs.getString("table")));
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    transferList.cargarListas(vecMadre,vecHija);
	//}
    }
    
    private void refreshTablesPublics(){
	String queryTablesPublicInBD = "SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid where aclcontains (relacl, '=r/" + DBAdminConfiguration.getDbOwner() + "') AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' ORDER BY nspname,relname";
	 //String queryTablesPublicInBD = "SELECT nspname || '.' || relname AS table from pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid where aclcontains (relacl, '=r/admin') AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' ORDER BY nspname,relname";
	//String queryTablesNotInPublic = "SELECT nspname || '.' || relname AS table FROM pg_class INNER JOIN pg_namespace ON relnamespace = pg_namespace.oid WHERE aclcontains (relacl, '=r/postgres') AND relname NOT LIKE 'pg%' AND nspname <> 'information_schema' AND nspname ||'.'|| relname NOT IN (SELECT publicaccess.nombre FROM admin.publicaccess WHERE publicaccess.tipo = 'tabla' AND publicaccess.estado <> '* ') ORDER BY nspname, relname";
	ResultSet _resultPublicTables = LibSQL.exQuery(queryTablesPublicInBD);
	Vector publicTables = new Vector();
	try {
	    while(_resultPublicTables.next()){
		publicTables.add(_resultPublicTables.getString("table"));
	    }
	    for(int i=0;i<publicTables.size();i++){
	        publicAccess = new PublicAccess();
	        publicAccess.setNombre(publicTables.elementAt(i).toString());
	        publicAccess.setTipo("tabla");
	        publicAccess.saveData();
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    private boolean revokePublicAccessToTables(){
	boolean retorno = false;
	String queryUpdate = "UPDATE admin.publicaccess SET estado = '*' WHERE publicaccess.tipo = 'tabla'";
	if(LibSQL.exActualizar('a',queryUpdate)){
	    retorno = true;
	}
	return retorno;
    }
    
    private void refreshFunctionsPublics(){
	String queryFunctionsPublicInBD = "SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) || ')' AS declaration, array_to_string(proacl,',') AS aclitem FROM pg_catalog.pg_proc p Inner join pg_catalog.pg_namespace n on n.oid = p.pronamespace inner join pg_catalog.pg_language pl on pl.oid = p.prolang where not p.proisagg and lanname = 'plpgsql' AND (array_to_string(proacl,',') like '%,=X/%' OR array_to_string(proacl,',') like '=X/%' OR proacl IS NULL)";
	ResultSet _resultPublicFunctions = LibSQL.exQuery(queryFunctionsPublicInBD);
	Vector publicFunctions = new Vector();
	try {
	    while(_resultPublicFunctions.next()){
		publicFunctions.add(_resultPublicFunctions.getString("declaration"));
	    }
	    for(int i=0;i<publicFunctions.size();i++){
		publicAccess = new PublicAccess();
		publicAccess.setNombre(publicFunctions.elementAt(i).toString());
		publicAccess.setTipo("funcion");
		publicAccess.saveData();
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    private boolean revokePublicAccessToFunctions(){
	boolean retorno = false;
	String queryUpdate = "UPDATE admin.publicaccess SET estado = '*' WHERE publicaccess.tipo = 'funcion'";
	if(LibSQL.exActualizar('a',queryUpdate)){
	    retorno = true;
	}
	return retorno;
    }

    /**2009-12-07(moraless)*/
    private void loadFunctionsByScheme(String _scheme) {
	String queryMadre = "";
	String queryHija = "";
	if(_scheme.equals("Todos")){
	    ////queryMadre = "SELECT nspname ||'.'|| proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' NOT IN (SELECT admin.publicaccess.nombre FROM admin.publicaccess WHERE publicaccess.estado <> '*') ORDER BY nspname, p.proname";
	    ////queryHija = "SELECT idpublic,nombre,tipo FROM admin.publicaccess WHERE tipo = 'funcion' AND estado <> '*' ORDER BY nombre";
	     queryMadre = "SELECT nspname ||'.'|| proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND nspname||'.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' NOT IN ((SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) || ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n on n.oid = p.pronamespace INNER JOIN pg_catalog.pg_language pl on pl.oid = p.prolang WHERE NOT p.proisagg AND lanname = 'plpgsql' AND (array_to_string(proacl,',') LIKE '%,=X/%' OR array_to_string(proacl,',') LIKE '=X/%' OR proacl IS NULL)) ORDER BY declaration) ORDER BY declaration";
	     queryHija = "(SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) || ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n on n.oid = p.pronamespace INNER JOIN pg_catalog.pg_language pl on pl.oid = p.prolang WHERE NOT p.proisagg AND lanname = 'plpgsql' AND (array_to_string(proacl,',') LIKE '%,=X/%' OR array_to_string(proacl,',') LIKE '=X/%' OR proacl IS NULL)) ORDER BY declaration;\n";
	}
	else{
	    //queryMadre = "SELECT p.oid AS prooid, p.proname as name, pg_catalog.format_type(p.prorettype, NULL) AS proresult, CASE WHEN array_to_string(p.proargnames, '') IS NULL THEN '' ELSE array_to_string(p.proargnames, '') END AS proargnames, pg_catalog.oidvectortypes(p.proargtypes) AS proarguments, nspname ||'.'|| proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname = '" + _scheme +"' AND '" + _scheme + ".' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' NOT IN (SELECT admin.publicaccess.nombre FROM admin.publicaccess WHERE publicaccess.estado <> '*') ORDER BY p.proname, proresult";    
	    ////queryMadre = "SELECT nspname ||'.'|| proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname = '" + _scheme +"' AND '" + _scheme + ".' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' NOT IN (SELECT admin.publicaccess.nombre FROM admin.publicaccess WHERE publicaccess.estado <> '*') ORDER BY p.proname";    
	    ////queryHija = "SELECT idpublic,nombre,tipo FROM admin.publicaccess WHERE UPPER(nombre) LIKE UPPER('" +_scheme+"." +"%') AND tipo = 'funcion' AND estado <> '*'";   
	    queryMadre = "SELECT nspname ||'.'|| proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND lanname = 'plpgsql' AND n.nspname = '" +
	    _scheme + "' AND '" + _scheme + ".' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' NOT IN ((SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) || ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n on n.oid = p.pronamespace INNER JOIN pg_catalog.pg_language pl on pl.oid = p.prolang WHERE NOT p.proisagg AND lanname = 'plpgsql' AND (array_to_string(proacl,',') LIKE '%,=X/%' OR array_to_string(proacl,',') LIKE '=X/%' OR proacl IS NULL)) ORDER BY declaration) ORDER BY p.proname\n";
	    queryHija = "(SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) || ')' AS declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n on n.oid = p.pronamespace INNER JOIN pg_catalog.pg_language pl on pl.oid = p.prolang WHERE NOT p.proisagg AND nspname = '" + _scheme + "' AND lanname = 'plpgsql' AND (array_to_string(proacl,',') LIKE '%,=X/%' OR array_to_string(proacl,',') LIKE '=X/%' OR proacl IS NULL)) ORDER BY declaration;\n";
	}
	//
	ResultSet rs = LibSQL.exQuery(queryMadre);
	Vector vecMadre = new Vector();
	Vector vecHija = new Vector();
	try {
	    while (rs.next()) {
		vecMadre.add(new TransferListModel(-1,rs.getString("declaration"),rs.getString("declaration")));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	rs = LibSQL.exQuery(queryHija);
	try {
	    while (rs.next()) {
		//vecHija.add(new TransferListModel(rs.getInt("idpublic"),removeScheme(rs.getString("nombre")),rs.getString("nombre")));
		////vecHija.add(new TransferListModel(rs.getInt("idpublic"),rs.getString("nombre"),rs.getString("nombre")));
		 vecHija.add(new TransferListModel(-1,rs.getString("declaration"),rs.getString("declaration")));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	transferList.cargarListas(vecMadre,vecHija);
    }
    
    private Vector getNewPublicTables(){
	return transferList.getTransferListMadre();
    }
    
    private Vector getNewPublicFunctions(){
	return transferList.getTransferListMadre();
    }
    
    private Vector getNewNonPublicTables(){
	return transferList.getTransferListHija();
    }
    
    private Vector getNewNonPublicFunctions(){
	return transferList.getTransferListHija();
    }
    
    private String getQueryNewPublicTables(){
	newPublicTables = getNewPublicTables();
	String query = "";
	for (int j = 0; j < newPublicTables.size(); j++) {
	    query += "GRANT SELECT ON " + getReference(newPublicTables.elementAt(j)) + " TO PUBLIC;\n";
	}
	return query;
    }
    
    private String getQueryNewPublicTables(Vector _newPublicTables){
	StringBuilder query = new StringBuilder();
	for (int j = 0; j < _newPublicTables.size(); j++) {
	    query.append("GRANT SELECT ON " + getReference(_newPublicTables.elementAt(j)) + " TO PUBLIC;\n");
	}
	return query.toString();
    }
    
    private String getQueryNewPublicFunctions(){
	newPublicFunctions = getNewPublicFunctions();
	String query = "";
	for (int j = 0; j < newPublicFunctions.size(); j++) {
	    //query += "GRANT SELECT ON " + getReference(newPublicTables.elementAt(j)) + " TO PUBLIC;\n";
	    query += "GRANT EXECUTE ON FUNCTION " + getReference(newPublicFunctions.elementAt(j)) + " TO PUBLIC;\n";
	}
	return query;
    }
    
    private String getReference(Object _objeto){
	TransferListModel tr = (TransferListModel)_objeto;
	return tr.getReference();
    }
    
    private int getId(Object _objeto){
	TransferListModel tr = (TransferListModel)_objeto;
	return tr.getId();
    }
    
    private String getQueryNewNonPublicTables(){
	newNonPublicTables = getNewNonPublicTables();
	String query = "";
	for (int j = 0; j < newNonPublicTables.size(); j++) {
	    query += "REVOKE SELECT ON " + getReference(newNonPublicTables.elementAt(j)) + " FROM PUBLIC;\n";
	}
	return query;
    }
    
    private String getQueryNewNonPublicFunctions(){
	newNonPublicFunctions = getNewNonPublicFunctions();
	String query = "";
	for (int j = 0; j < newNonPublicFunctions.size(); j++) {
	    query += "REVOKE ALL ON FUNCTION " + getReference(newNonPublicFunctions.elementAt(j)) + " FROM PUBLIC;\n";
	}
	return query;
    }
    
    
    private void saveChanges(){
	String query = "";
	//if(cbTipo.getSelectedItem().equals("Tablas")){
	if(opcionSeleccionada == TABLA){
	    query += getQueryNewPublicTables();
	    query += getQueryNewNonPublicTables();
	    if (LibSQL.exActualizar('a', query)) {
	        /*for(int i=0;i<newPublicTables.size();i++){
	            publicAccess = new PublicAccess();
	            publicAccess.setNombre(getReference(newPublicTables.elementAt(i)));
	            publicAccess.setTipo("tabla");
	            publicAccess.saveData();
	        }
	        for(int i=0;i<newNonPublicTables.size();i++){
	            publicAccess = new PublicAccess();
	            publicAccess.setIdPublicAccess(getId(newNonPublicTables.elementAt(i)));
	            publicAccess.retrieveData();
	            publicAccess.setEstado("*");
	            publicAccess.saveData();
	        }*/
	        //loadData();
	    } else {
	        Advisor.messageBox("Error al intentar asignar los permisos", "Error");
	    }
	}
	else{
	    query += getQueryNewPublicFunctions();
	    query += getQueryNewNonPublicFunctions();
	    if (LibSQL.exActualizar('a', query)) {
	        /*for(int i=0;i<newPublicFunctions.size();i++){
	            publicAccess = new PublicAccess();
	            publicAccess.setNombre(getReference(newPublicFunctions.elementAt(i)));
	            publicAccess.setTipo("funcion");
	            publicAccess.saveData();
	        }
	        for(int i=0;i<newNonPublicFunctions.size();i++){
	            publicAccess = new PublicAccess();
	            publicAccess.setIdPublicAccess(getId(newNonPublicFunctions.elementAt(i)));
	            publicAccess.retrieveData();
	            publicAccess.setEstado("*");
	            publicAccess.saveData();
	        }*/
	        //loadData();
	    } else {
	        Advisor.messageBox("Error al intentar asignar los permisos", "Error");
	    }   
	}
	loadData();
    }
    
    public String removeScheme(String _text){
	String text = "";
	text = _text.substring(_text.indexOf(".")+1,_text.length());
	return text;
    }

    private void loadTipos(){
	cbTipo.addItem("Stored Procedure");
	cbTipo.addItem("Tablas");
	cbTipo.setSelectedItem("Tablas");
	opcionSeleccionada = TABLA;
    }
        
    private void saveUsersByGroup_actionPerformed(ActionEvent e) {
	saveChanges();
    }

    private void cancelUsersByGroup_actionPerformed(ActionEvent e) {
	loadData();
    }

    private void btnExportPrivileges_actionPerformed(ActionEvent e) {
	QueryExportPrivileges priv = new QueryExportPrivileges();
	ComponentsManager.centerWindow(priv);
	priv.setModal(true);
	priv.setVisible(true);
    }
    
    private void btnExportPublics_actionPerformed(ActionEvent e) {
	///int opcion = -1;
	//if(cbTipo.getSelectedItem().equals("Tablas")){
	/*if(cbTipo.getSelectedItem().equals("Tablas")){
	    opcion = 0;
	}
	else{
	    opcion = 1;
	}*/
	QueryExportFunctionsTablesPublic exportPublic = new QueryExportFunctionsTablesPublic(opcionSeleccionada);
	ComponentsManager.centerWindow(exportPublic);
	exportPublic.setModal(true);
	exportPublic.setVisible(true);
    }

    private void btnRefresh_actionPerformed(ActionEvent e) {
        loadData();
	//loadPublicAccess();	
	/*cbSchemes.setGeneralItem(true);
	cbSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");*/
    }
    
    /**2009-12-04(moraless)*/
    //Metodo que obtiene las tablas y funciones publicas
    private void loadPublicAccess(){
	revokePublicAccessToTables();
	refreshTablesPublics();
	revokePublicAccessToFunctions();
	refreshFunctionsPublics();
	if(opcionSeleccionada == TABLA){
	    loadTablesByScheme(cbSchemes.getSelectedItem().toString());
	}    
	else{
	    loadFunctionsByScheme(cbSchemes.getSelectedItem().toString());
	}
	/*if(opcionSeleccionada == TABLA){
	    if(revokePublicAccessToTables()){
		refreshTablesPublics();
		loadTablesByScheme(cbSchemes.getSelectedItem().toString());
	    }    
	}
	else{
	    if(revokePublicAccessToFunctions()){
		refreshFunctionsPublics();
		loadFunctionsByScheme(cbSchemes.getSelectedItem().toString());
	    }    
	}*/   
    }
    
    private Vector getSelectedPublicAccess(){
	Vector listado = transferList.getListModelHija();
	Vector retorno = new Vector();
	for(int i=0; i< listado.size();i++){
	    TransferListModel _group = (TransferListModel)listado.elementAt(i);
	    retorno.add(_group.getReference());
	}
	return retorno;
    }
    
    private Vector getNonSelectedPublicAccess(){
	Vector listado = transferList.getListModelMadre();
	Vector retorno = new Vector();
	for(int i=0; i< listado.size();i++){
	    TransferListModel _group = (TransferListModel)listado.elementAt(i);
	    retorno.add(_group.getReference());
	}
	return retorno;
    }

    private void btnChangeOwner_actionPerformed(ActionEvent e) {
	QueryOwnerManager changeOwner = new QueryOwnerManager();
	ComponentsManager.centerWindow(changeOwner);
	changeOwner.setModal(true);
	changeOwner.setVisible(true);
    }

    private void btnSecurityDefiner_actionPerformed(ActionEvent e) {
	QuerySecurityDefiner secDefiner = new QuerySecurityDefiner();
	ComponentsManager.centerWindow(secDefiner);
	secDefiner.setModal(true);
	secDefiner.setVisible(true);
    }
}

