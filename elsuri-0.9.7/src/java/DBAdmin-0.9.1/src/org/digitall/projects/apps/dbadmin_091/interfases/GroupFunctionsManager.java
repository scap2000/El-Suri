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
 * GroupFunctionsManager.java
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
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.DBAdminMain;

public class GroupFunctionsManager extends BasicPanel {

    private SaveDataButton saveGroupsByFunction = new SaveDataButton();
    private CancelDataButton cancelGroupsByFunction = new CancelDataButton();
    
    private BasicScrollPane spGroups = new BasicScrollPane();
    private BasicScrollPane spFunctionsByGroup = new BasicScrollPane();
    private BasicScrollPane spFunctionsNotByGroup = new BasicScrollPane();
    private BasicList groupsList = new BasicList();
    private BasicCheckList functionsByGroup = new BasicCheckList();
    private BasicCheckList functionsNotByGroup = new BasicCheckList();
    
    private BasicLabel lblScheme = new BasicLabel();
    private BasicLabel lblGroup = new BasicLabel();
    private BasicLabel lblFunction = new BasicLabel();
    
    private BasicLabel lblFunctionsNotByGroup = new BasicLabel();
    
    private JCombo cbFunctionsSchemes = new JCombo();
    private Vector<DBRole> groupVector = new Vector<DBRole>();
    private DBAdminMain manager;
    private DBAdminPanel dbAdminPanel;

    public GroupFunctionsManager(DBAdminMain _manager) {
	//manager = _manager;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }


    public GroupFunctionsManager(DBAdminPanel _manager) {
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
        cbFunctionsSchemes.setBounds(new Rectangle(15, 125, 200, 20));
	saveGroupsByFunction.setBounds(new Rectangle(505, 105, 70, 60));
	saveGroupsByFunction.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    saveGroupsByFunction_actionPerformed(e);
		}
	    }
	);
	saveGroupsByFunction.setText("Guardar\ncambios");
	saveGroupsByFunction.setSize(new Dimension(70, 60));
	cancelGroupsByFunction.setBounds(new Rectangle(580, 105, 70, 60));
	cancelGroupsByFunction.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    cancelGroupsByFunction_actionPerformed(e);
		}
	    }
	);
	cancelGroupsByFunction.setText("Cancelar\ncambios");
	cancelGroupsByFunction.setSize(new Dimension(70, 60));
	lblScheme.setBounds(new Rectangle(15, 110, 85, 15));
	lblScheme.setToolTipText("null");
	lblScheme.setText("Esquema");
	lblGroup.setText("Grupos");
	lblGroup.setBounds(new Rectangle(15, 0, 85, 14));
	lblFunction.setText("Funciones a las que pertenece");
	lblFunction.setBounds(new Rectangle(15, 150, 180, 15));
	spGroups.setBounds(new Rectangle(15, 15, 290, 90));
	spGroups.setSize(new Dimension(290, 90));
	spFunctionsByGroup.setBounds(new Rectangle(15, 170, 635, 135));
	spFunctionsByGroup.setSize(new Dimension(635, 133));
	spFunctionsNotByGroup.getViewport().add(functionsNotByGroup, null);
	this.add(lblFunctionsNotByGroup, null);
	this.add(spFunctionsNotByGroup, null);
	spFunctionsByGroup.getViewport().add(functionsByGroup);
	this.add(spFunctionsByGroup, null);
	spGroups.getViewport().add(groupsList, null);
	this.add(spGroups, null);
        this.add(lblFunction, null);
        this.add(lblGroup, null);
	this.add(lblScheme, null);
        add(saveGroupsByFunction);
        add(cancelGroupsByFunction);
        add(cbFunctionsSchemes, null);
	groupsList.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent e) {
						    if (!e.getValueIsAdjusting()) {
							if (groupsList.getSelectedIndex() > -1) {
							    try {
								 loadFunctionsByGroup(groupsList.getSelectedValue().toString());
							    } catch (NullPointerException x) {
								//ignore
							    }
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
	cbFunctionsSchemes.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbFunctionsSchemes.getSelectedIndex() > -1) {
						     loadFunctionsByGroup(groupsList.getSelectedValue().toString());
						}
					    }
					}

				    }
	);
	spFunctionsNotByGroup.setBounds(new Rectangle(15, 330, 635, 135));
	spFunctionsNotByGroup.setSize(new Dimension(635, 133));
	lblFunctionsNotByGroup.setText("Funciones a las que NO pertenece");
	lblFunctionsNotByGroup.setBounds(new Rectangle(15, 310, 275, 15));
	//saveGroupsByFunction.setText("Guardar");
	//cancelGroupsByFunction.setText("Cancelar");
    }

    public void boot() {
	cbFunctionsSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	cbFunctionsSchemes.setSelectedIndex(0);
	loadGroups();
    }
    
    public void boot(ResultSet _schemes) {
	cbFunctionsSchemes.loadJCombo(_schemes);
	cbFunctionsSchemes.setSelectedIndex(0);
	loadGroups();
    }
    
    public void setEnabledTab(boolean _enabled){
	cbFunctionsSchemes.setEnabled(_enabled);
	functionsByGroup.setEnabled(_enabled);
	functionsNotByGroup.setEnabled(_enabled);
	groupsList.setEnabled(_enabled);
	cancelGroupsByFunction.setEnabled(_enabled);
	saveGroupsByFunction.setEnabled(_enabled);
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
	//manager.setGroupVector(groupVector);
	groupsList.setSelectedIndex(0);
    }
     
    public String removeScheme(String _text){
	String text = "";
	text = _text.substring(_text.indexOf(".")+1,_text.length());
	return text;
    }
    
    private void loadFunctionsByGroup(String _grupo) {
	String queryIn = "SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) || ')'as declaration FROM pg_catalog.pg_proc p Inner join pg_catalog.pg_namespace n on n.oid = p.pronamespace inner join pg_catalog.pg_language pl on pl.oid = p.prolang where not p.proisagg and lanname = 'plpgsql' AND (array_to_string(proacl,',') like '%," +
	_grupo + "=X/%' ) AND n.nspname = '" + cbFunctionsSchemes.getSelectedItem() + "' ORDER BY nspname, proname";
	String queryNotIn = "SELECT n.nspname || '.' || proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) || ')'as declaration FROM pg_catalog.pg_proc p Inner join pg_catalog.pg_namespace n on n.oid = p.pronamespace inner join pg_catalog.pg_language pl on pl.oid = p.prolang where not p.proisagg and lanname = 'plpgsql' AND NOT (array_to_string(proacl,',') like '%," +
	_grupo + "=X/%' ) AND n.nspname = '" + cbFunctionsSchemes.getSelectedItem() + "' ORDER BY nspname, proname";
	ResultSet rs = LibSQL.exQuery(queryIn);
	Vector _functionsByGroup = new Vector();
	Vector _functionsNotByGroup = new Vector();
	try {
	    while(rs.next()){
	        BasicCheckableListItem item = new BasicCheckableListItem(0, rs.getString("declaration"), true);
	        _functionsByGroup.add(item);
	    }
	    rs = LibSQL.exQuery(queryNotIn);
	    while(rs.next()){
	        BasicCheckableListItem item = new BasicCheckableListItem(0, rs.getString("declaration"), false);
	        _functionsNotByGroup.add(item);
	    }
	    
	} catch (SQLException e) {
	    // TODO
	}
	functionsByGroup.setListData(_functionsByGroup);
	functionsByGroup.setEnabled(false);
	functionsNotByGroup.setListData(_functionsNotByGroup);
	functionsNotByGroup.setEnabled(false);
	cancelGroupsByFunction.setEnabled(false);
	saveGroupsByFunction.setEnabled(false);
	functionsByGroup.setSelectedIndex(0);
	functionsNotByGroup.setSelectedIndex(0);
    }
    
    private void saveFunctionsByGroup(){
	String query = "";
	String nombreGrupo = "";
	String grupo = groupsList.getSelectedValue().toString();
	for (int i = 0; i < functionsByGroup.getModel().getSize(); i++) {
	    BasicCheckableListItem function = (BasicCheckableListItem)functionsByGroup.getModel().getElementAt(i);
	    if (!function.isSelected()) {
	        query += "REVOKE ALL PRIVILEGES ON FUNCTION " + function.getName() + " FROM PUBLIC;\n";
	        query += "REVOKE ALL PRIVILEGES ON FUNCTION " + function.getName() + " FROM GROUP " + grupo + ";\n";
	    }
	}
	for (int i = 0; i < functionsNotByGroup.getModel().getSize(); i++) {
	    BasicCheckableListItem function = (BasicCheckableListItem)functionsNotByGroup.getModel().getElementAt(i);
	    if (function.isSelected()) {
	        query += "GRANT EXECUTE ON FUNCTION " + function.getName() + " TO GROUP " + grupo + ";\n";   
	    }
	}
	if (LibSQL.exActualizar('a', query)) {
	    loadFunctionsByGroup(groupsList.getSelectedValue().toString());
	} else {
	    Advisor.messageBox("Error al intentar asignar los privilegios", "Error");
	}
    }
    
    private void cancelGroupsByFunction_actionPerformed(ActionEvent e) {
	loadFunctionsByGroup(groupsList.getSelectedValue().toString());
    }

    private void saveGroupsByFunction_actionPerformed(ActionEvent e) {
	//saveChanges();
	saveFunctionsByGroup();
    }
    
    private void groups_mouseClicked(MouseEvent e) {
	if (groupsList.getSelectedIndex() >= 0) {
	    if (e.getClickCount() == 1) {
		 //loadFunctionsByGroup(groupsList.getSelectedValue().toString());
	    } else if (e.getClickCount() == 2) {
		functionsByGroup.setEnabled(true);
		functionsNotByGroup.setEnabled(true);
		cancelGroupsByFunction.setEnabled(true);
		saveGroupsByFunction.setEnabled(true);
	    }
	}
    }
}
