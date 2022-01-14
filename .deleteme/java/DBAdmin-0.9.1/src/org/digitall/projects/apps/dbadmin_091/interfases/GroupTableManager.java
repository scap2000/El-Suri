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
 * GroupTableManager.java
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

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
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
import org.digitall.projects.apps.dbadmin_091.DBAdminMain;
import org.digitall.projects.apps.dbadmin_091.SystemConfiguration;

public class GroupTableManager extends BasicPanel {

    private SaveDataButton savetablesByGroup = new SaveDataButton();
    private CancelDataButton canceltablesByGroup = new CancelDataButton();
    
    private BasicScrollPane sptablesByGroup = new BasicScrollPane();
    private BasicScrollPane spgroupsList = new BasicScrollPane();
    private BasicList groupsList = new BasicList();
    private BasicList tablesByGroup = new BasicList();

    private BasicLabel lblScheme = new BasicLabel();
    private BasicLabel lblTables = new BasicLabel();
    private BasicLabel lblGroups = new BasicLabel();
    
    private JCombo cbTablesSchemes = new JCombo();
    
    private Vector<DBRole> groupVector = new Vector<DBRole>();
    private DBAdminMain manager;
    private DBAdminPanel dbAdminPanel;

    private ButtonGroup grupo = new ButtonGroup();
    private JRadioButton rbtnAdmin = new JRadioButton();
    private JRadioButton rbtnUser = new JRadioButton();
    private JRadioButton rbtnQuery = new JRadioButton();

    public GroupTableManager(DBAdminMain _manager) {
	//manager = _manager;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }


    public GroupTableManager(DBAdminPanel _manager) {
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
	sptablesByGroup.setBounds(new Rectangle(290, 75, 270, 385));
	sptablesByGroup.setSize(new Dimension(270, 385));
	spgroupsList.setBounds(new Rectangle(20, 75, 255, 385));
	spgroupsList.setSize(new Dimension(253, 385));
        //**tablesByGroup.setCellRenderer(new BasicCellRenderer()); // esta propiedad permite mostrar colores en la lista
	groupsList.addListSelectionListener(new ListSelectionListener() {
					     public void valueChanged(ListSelectionEvent e) {
					         if (!e.getValueIsAdjusting()) {
						     if (groupsList.getSelectedIndex() > -1) {
							 try {
							     loadtablesByGroup(groupsList.getSelectedValue().toString());
							 } catch (NullPointerException x) {
							     //ignore
							 }
						     }
						 }
					     }

					 }
	);
	tablesByGroup.addMouseListener(new MouseAdapter() {

				    public void mouseClicked(MouseEvent me) {
					if (tablesByGroup.getSelectedValues().length > 0 && me.getButton() == MouseEvent.BUTTON3) {
					    for (int i = 0; i < tablesByGroup.getSelectedValues().length; i++) {
						DBRole _group = (DBRole)tablesByGroup.getSelectedValues()[i];
						_group.incPrivilege();
						_group.setSelected(true);
					    }
					    sptablesByGroup.repaint();
					}
				    }

				}
	);
	savetablesByGroup.setBounds(new Rectangle(430, 410, 70, 60));
	savetablesByGroup.setSize(new Dimension(70, 60));
        savetablesByGroup.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     savetablesByGroup_actionPerformed(e);
					 }

				     }
	);
	savetablesByGroup.setText("Guardar\ncambios");
	canceltablesByGroup.setBounds(new Rectangle(505, 410, 70, 60));
	canceltablesByGroup.setText("Cancelar \ncambios");
        lblScheme.setText("Esquema");
	lblScheme.setBounds(new Rectangle(20, 5, 75, 15));
	lblTables.setText("Tablas");
	lblTables.setBounds(new Rectangle(290, 54, 75, 15));
	lblGroups.setText("Grupos");
	lblGroups.setBounds(new Rectangle(20, 55, 75, 14));
        this.add(rbtnQuery, null);
        this.add(lblGroups, null);
        this.add(lblTables, null);
        this.add(lblScheme, null);
	sptablesByGroup.getViewport().add(tablesByGroup);
	add(sptablesByGroup, null);
        add(cbTablesSchemes, null);
	spgroupsList.getViewport().add(groupsList);
	add(spgroupsList, null);
        add(savetablesByGroup, null);
        add(canceltablesByGroup, null);
        this.add(rbtnAdmin, null);
        this.add(rbtnUser, null);
        cbTablesSchemes.setBounds(new Rectangle(20, 25, 200, 20));
	cbTablesSchemes.addItemListener(new ItemListener() {

				     public void itemStateChanged(ItemEvent e) {
					 if (e.getStateChange() == ItemEvent.SELECTED) {
					     if (cbTablesSchemes.getSelectedIndex() > -1) {
						 //loadGroups();
                                                loadTablas();
					     }
					 }
				     }

				 }
	);
        rbtnAdmin.setText("ADMIN");
        rbtnAdmin.setBounds(new Rectangle(575, 270, 70, 20));
        rbtnAdmin.setBackground(new Color(255, 33, 33));
        rbtnAdmin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    rbtnAdmin_actionPerformed(e);
                }
            });
        rbtnUser.setText("USER");
        rbtnUser.setBounds(new Rectangle(575, 235, 70, 20));
        rbtnUser.setBackground(Color.yellow);
        rbtnUser.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    rbtnUser_actionPerformed(e);
                }
            });
        rbtnQuery.setText("QUERY");
        rbtnQuery.setBounds(new Rectangle(575, 200, 70, 20));
        rbtnQuery.setBackground(Color.green);
        rbtnQuery.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    rbtnQuery_actionPerformed(e);
                }
            });
        grupo.add(rbtnAdmin);
        grupo.add(rbtnUser);
        grupo.add(rbtnQuery);
        cbTablesSchemes.setGeneralItem(true);
        savetablesByGroup.setVisible(false);
        canceltablesByGroup.setVisible(false);
        rbtnQuery.setSelected(true);
    }

    public void boot() {
	cbTablesSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	cbTablesSchemes.setSelectedIndex(0);
        loadGroups();
	if(cbTablesSchemes.getModel().getSize()>0){

	}
    }
    
    /**2009-12-30(moraless)*/
    public void boot(ResultSet _schemes) {
	cbTablesSchemes.loadJCombo(_schemes);
	cbTablesSchemes.setSelectedIndex(0);
        loadGroups();
	if(cbTablesSchemes.getModel().getSize()>0){

	}
    }
    
    public void setEnabledTab(boolean _enabled){
	cbTablesSchemes.setEnabled(_enabled);
	groupsList.setEnabled(_enabled);
	tablesByGroup.setEnabled(_enabled);
	savetablesByGroup.setEnabled(_enabled);
	canceltablesByGroup.setEnabled(_enabled);
    }
    
    //Metodo que carga los grupos en la lista
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
    }

    private void loadtablesByGroup(String _group) {
        /*SELECT relname, aclcontains(relacl,'accounting_admin=w') AS write,
                aclcontains(relacl,'accounting_admin=r') AS read, 
                aclcontains(relacl,'accounting_admin=a') AS a,
                aclcontains(relacl,'accounting_admin=t') AS t,
                aclcontains(relacl,'accounting_admin=R') AS R,
                relacl 
        FROM pg_class 
        INNER JOIN pg_namespace esquema ON esquema.oid = pg_class .relnamespace 
        WHERE pg_class.relkind = 'r' AND relname NOT LIKE 'pg_%'
        /* AND esquema.nspname
        = 'accounting' */ 
        String filtroPrivilegios = "";
        if(rbtnAdmin.isSelected()){
            //La cadena arwdRxt indica que el usuario tiene asignado todos los privilegios. 
            //Esta cadena solo es válida para la versión 8.1 de postgres. A partir de la versión 8.2 
            //desaparece la letra R (Rule) quedando la cadena de la siguiente manera: arwdxt
            //filtroPrivilegios = "arwdRxt";
            filtroPrivilegios = "arwd";
        } else {
            if(rbtnUser.isSelected()){
                filtroPrivilegios = "ar/";
            } else {
                filtroPrivilegios = "r/";
            }
        }
        String filtroEsquema = "";
        String filtroMostrarEsquema = "";
        String consulta = "";
        if(!cbTablesSchemes.getSelectedItem().equals("Todos")){
            filtroEsquema = "AND esquema.nspname = '"+ cbTablesSchemes.getSelectedItem()+"'";
        } else {
            filtroMostrarEsquema = "nspname ||'.'|| ";
        }
        consulta = "SELECT " + filtroMostrarEsquema + "relname AS relname, relacl FROM pg_class INNER JOIN pg_namespace esquema ON esquema.oid = pg_class.relnamespace WHERE pg_class.relkind = 'r' AND relname NOT LIKE 'pg_%' AND (array_to_string(relacl,',') like '%" + _group + "=" + filtroPrivilegios + "%' ) "+ filtroEsquema + " ORDER BY nspname,relname";
        //consulta = "SELECT " + filtroMostrarEsquema + "relname AS relname, aclcontains(relacl,'" + _group + "=" + filtroPrivilegios + "'), relacl FROM pg_class INNER JOIN pg_namespace esquema ON esquema.oid = pg_class.relnamespace WHERE pg_class.relkind = 'r' AND relname NOT LIKE 'pg_%' AND aclcontains(relacl,'" + _group + "=" + filtroPrivilegios + "') AND (array_to_string(relacl,',') like '%" + _group + "=" + filtroPrivilegios + "/%' ) "+ filtroEsquema;
	ResultSet result = LibSQL.exQuery(consulta);
        Vector _tablesByGroup = new Vector();
        try {
            while (result.next()) {
                _tablesByGroup.add(new DBRole(result.getString("relname")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tablesByGroup.setListData(_tablesByGroup);
    }
    
    private void savetablesByGroup_actionPerformed(ActionEvent e) {
        
    }

    /**2009-12-30(moraless)*/
    public void setGroupVector(Vector<DBRole> _groupVector) {
	//groupVector = _groupVector;
    }
    
    private void rbtnAdmin_actionPerformed(ActionEvent e) {
        loadTablas();
    }

    private void rbtnUser_actionPerformed(ActionEvent e) {
        loadTablas();
    }

    private void rbtnQuery_actionPerformed(ActionEvent e) {
        loadTablas();
    }
    
    private void loadTablas(){
        if(groupsList.getSelectedIndex() > -1){
            loadtablesByGroup(groupsList.getSelectedValue().toString());    
        }
    }
}

