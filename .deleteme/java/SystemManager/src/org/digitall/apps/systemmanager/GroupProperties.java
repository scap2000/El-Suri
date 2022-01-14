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
 * GroupProperties.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.List;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddGroupButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.sql.LibSQL;

//

public class GroupProperties extends BasicDialog {

    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel panelGroups = new BasicPanel();
    private BasicPanel panelOptions = new BasicPanel();
    private BasicLabel lblPrivileges = new BasicLabel();
    private BasicLabel lblGroup = new BasicLabel();
    private BasicLabel lblSystem5 = new BasicLabel();
    private BasicLabel lblComment = new BasicLabel();
    private BasicCheckBox checkSelect = new BasicCheckBox();
    private BasicCheckBox checkUpdate = new BasicCheckBox();
    private BasicCheckBox checkInsert = new BasicCheckBox();
    private CloseButton bCancel = new CloseButton();
    private AcceptButton bPrivileges = new AcceptButton();
    private AddGroupButton bNewGroup = new AddGroupButton();
    private DeleteButton bDelGroup = new DeleteButton();
    private BasicScrollPane spTables = new BasicScrollPane();
    private BasicScrollPane spComment = new BasicScrollPane();
    private JCombo cbGroupName = new JCombo();
    private JTextArea taComment = new JTextArea();
    private JList listTable = new JList();
    private List tablesList = new List();
    private String esquema = "", idsist = "", sistema = "";
    private boolean primeraVez = true;
    private String groupName = "", systemName = "";
    private BasicCheckBox checkAudit = new BasicCheckBox();
    private final String TEXTO = "Group Properties";
    private BasicLabel lblSystemPrivileges = new BasicLabel();
    private BasicLabel lblGroups = new BasicLabel();

    public GroupProperties(String _idsist, String _systemName, String _groupName) {
	try {
	    idsist = _idsist;
	    systemName = _systemName;
	    groupName = _groupName;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(443, 450));
	this.getContentPane().add(lblGroups, null);
	this.getContentPane().add(lblSystemPrivileges, null);
	this.getContentPane().add(panelGroups, null);
	this.getContentPane().add(bCancel, null);
	this.getContentPane().add(jPanel1, null);
	this.getContentPane().setLayout(null);
	loadData();
	this.setTitle("Group properties - System: " + sistema);
	jPanel1.setBounds(new Rectangle(5, 120, 425, 255));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblPrivileges.setText(" Privileges");
	lblPrivileges.setBounds(new Rectangle(20, 165, 65, 15));
	lblPrivileges.setFont(new Font("Dialog", 1, 11));
	lblPrivileges.setHorizontalAlignment(SwingConstants.LEFT);
	lblPrivileges.setOpaque(true);
	checkSelect.setText("Select");
	checkSelect.setBounds(new Rectangle(15, 20, 75, 15));
	checkSelect.setMnemonic('S');
	checkSelect.setFont(new Font("Dialog", 1, 11));
	checkUpdate.setText("Update");
	checkUpdate.setBounds(new Rectangle(265, 20, 75, 15));
	checkUpdate.setMnemonic('U');
	checkUpdate.setFont(new Font("Dialog", 1, 11));
	checkInsert.setText("Insert");
	checkInsert.setBounds(new Rectangle(145, 20, 75, 15));
	checkInsert.setMnemonic('I');
	checkInsert.setFont(new Font("Dialog", 1, 11));
	bCancel.setBounds(new Rectangle(5, 390, 40, 25));
	bCancel.setMnemonic('C');
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	bPrivileges.setBounds(new Rectangle(355, 20, 40, 25));
	bPrivileges.setMnemonic('A');
	bPrivileges.setSize(new Dimension(40, 25));
	bPrivileges.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAcept_actionPerformed(e);
		    }

		});
	panelGroups.setBounds(new Rectangle(5, 45, 425, 50));
	panelGroups.setLayout(null);
	panelGroups.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblGroup.setText("Group:");
	lblGroup.setBounds(new Rectangle(10, 20, 40, 10));
	lblGroup.setFont(new Font("Dialog", 1, 11));
	lblGroup.setHorizontalAlignment(SwingConstants.LEFT);
	spTables.setBounds(new Rectangle(10, 25, 200, 125));
	listTable.setBounds(new Rectangle(0, 0, 165, 125));
	listTable.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblSystem5.setText("Tables:");
	lblSystem5.setBounds(new Rectangle(10, 15, 200, 10));
	lblSystem5.setFont(new Font("Dialog", 1, 11));
	lblSystem5.setHorizontalAlignment(SwingConstants.LEFT);
	panelOptions.setBounds(new Rectangle(10, 175, 405, 65));
	panelOptions.setLayout(null);
	panelOptions.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	panelGroups.add(cbGroupName, null);
	panelGroups.add(bNewGroup, null);
	panelGroups.add(lblGroup, null);
	panelGroups.add(bDelGroup, null);
	jPanel1.add(lblPrivileges, null);
	jPanel1.add(lblComment, null);
	spComment.getViewport().add(taComment, null);
	jPanel1.add(spComment, null);
	spTables.getViewport().add(listTable, null);
	jPanel1.add(spTables, null);
	jPanel1.add(lblSystem5, null);
	jPanel1.add(panelOptions, null);
	panelOptions.add(checkAudit, null);
	panelOptions.add(checkSelect, null);
	panelOptions.add(checkInsert, null);
	panelOptions.add(checkUpdate, null);
	panelOptions.add(bPrivileges, null);
	listTable.addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			listTable_mouseClicked(e);
		    }

		});
	listTable.addKeyListener(new KeyAdapter() {

		    public void keyPressed(KeyEvent e) {
			listTable_keyTyped(e);
		    }

		    public void keyReleased(KeyEvent e) {
			listTable_keyTyped(e);
		    }

		    public void keyTyped(KeyEvent e) {
			listTable_keyTyped(e);
		    }

		});
	checkAudit.setText("Audit");
	checkAudit.setBounds(new Rectangle(15, 45, 75, 15));
	checkAudit.setMnemonic('S');
	checkAudit.setFont(new Font("Dialog", 1, 11));
	lblSystemPrivileges.setText(" System's tables & Privileges");
	lblSystemPrivileges.setBounds(new Rectangle(15, 110, 170, 15));
	lblSystemPrivileges.setOpaque(true);
	lblSystemPrivileges.setFont(new Font("Dialog", 1, 11));
	lblSystemPrivileges.setHorizontalAlignment(SwingConstants.LEFT);
	lblGroups.setText(" Groups");
	lblGroups.setBounds(new Rectangle(15, 35, 50, 15));
	lblGroups.setOpaque(true);
	lblGroups.setFont(new Font("Dialog", 1, 11));
	lblGroups.setHorizontalAlignment(SwingConstants.LEFT);
	spComment.setBounds(new Rectangle(220, 25, 200, 125));
	spComment.getViewport().setLayout(null);
	spComment.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblComment.setText("Comment:");
	lblComment.setBounds(new Rectangle(220, 15, 200, 10));
	lblComment.setFont(new Font("Dialog", 1, 11));
	lblComment.setHorizontalAlignment(SwingConstants.LEFT);
	taComment.setBounds(new Rectangle(0, 0, 195, 120));
	taComment.setEditable(false);
	taComment.setWrapStyleWord(true);
	taComment.setLineWrap(true);
	cbGroupName.setBounds(new Rectangle(55, 15, 230, 20));
	cbGroupName.setFont(new Font("Dialog", 1, 11));
	cbGroupName.setForeground(Color.blue);
	cbGroupName.addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    groupName = cbGroupName.getSelectedItem().toString();
			    //System.out.println("grounpName: "+groupName);
			    control();
			}
		    }

		});
	bNewGroup.setBounds(new Rectangle(315, 11, 40, 29));
	bNewGroup.setSize(new Dimension(40, 29));
	bNewGroup.setFont(new Font("Dialog", 1, 11));
	bNewGroup.setToolTipText("Add new group");
	bNewGroup.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bNewGroup_actionPerformed(e);
		    }

		});
	cbGroupName.addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    groupName = org.digitall.lib.sql.LibSQL.getCampo("Select rolname From pg_catalog.pg_roles Where rolname like '" + systemName + "%'");
			    //System.out.println("tabla: "+listTable.getSelectedValue().toString());
			    setChecks();
			    if (primeraVez) {
				setChecks();
				primeraVez = false;
			    } else {
				setChecks();
			    }
			}
		    }

		});
	bDelGroup.setBounds(new Rectangle(370, 10, 40, 30));
	bDelGroup.setSize(new Dimension(40, 29));
	bDelGroup.setMnemonic('D');
	bDelGroup.setFont(new Font("Dialog", 1, 11));
	bDelGroup.setToolTipText("Delete group");
	bDelGroup.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bDelGroup_actionPerformed(e);
		    }

		});
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
	setFields();
	setTables();
    }

    private void setFields() {
	String consulta = "Select rolname From pg_catalog.pg_roles Where rolname like '" + systemName + "%'";
	cbGroupName.loadJCombo(consulta);
	cbGroupName.setSelectedValue(groupName);
	control();
    }

    private void habilitarBotones(boolean _valor) {
	checkSelect.setEnabled(_valor);
	checkInsert.setEnabled(_valor);
	checkUpdate.setEnabled(_valor);
	checkAudit.setEnabled(false);
	bPrivileges.setEnabled(_valor);
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void bAcept_actionPerformed(ActionEvent e) {
	String cadena = "";
	if (checkSelect.isSelected()) {
	    cadena = "GRANT SELECT ON " + esquema + "." + listTable.getSelectedValue().toString() + " TO GROUP " + groupName;
	    //System.out.println("cadena: "+cadena);
	    LibSQL.exActualizar('a', cadena);
	} else {
	    cadena = "REVOKE SELECT ON " + esquema + "." + listTable.getSelectedValue().toString() + " FROM GROUP " + groupName;
	    //System.out.println("cadena Revoke select: "+cadena);
	    LibSQL.exActualizar('a', cadena);
	}
	if (checkInsert.isSelected()) {
	    cadena = "GRANT INSERT ON " + esquema + "." + listTable.getSelectedValue().toString() + " TO GROUP " + groupName;
	    LibSQL.exActualizar('a', cadena);
	} else {
	    cadena = "REVOKE INSERT ON " + esquema + "." + listTable.getSelectedValue().toString() + " FROM GROUP " + groupName;
	    LibSQL.exActualizar('a', cadena);
	}
	if (checkUpdate.isSelected()) {
	    cadena = "GRANT UPDATE ON " + esquema + "." + listTable.getSelectedValue().toString() + " TO GROUP " + groupName;
	    LibSQL.exActualizar('a', cadena);
	} else {
	    cadena = "REVOKE UPDATE ON " + esquema + "." + listTable.getSelectedValue().toString() + " FROM GROUP " + groupName;
	    LibSQL.exActualizar('a', cadena);
	}
    }

    /****************  CLICK EN EL LIST ***************/
    private void listTable_mouseClicked(MouseEvent e) {
	if ((e.getClickCount() == 1) && (listTable.getSelectedIndex() >= 0)) {
	    String esquema = org.digitall.lib.sql.LibSQL.getCampo("Select esquema From admin.tables Where tabla = '" + listTable.getSelectedValue().toString() + "' ");
	    String consulta = "SELECT description AS comment FROM pg_description de, pg_class bc, pg_namespace ns WHERE de.objoid = bc.oid and " + " bc.relnamespace = ns.oid AND  objsubid = 0 AND   ns.nspname = '" + esquema + "'  AND bc.relname = '" + listTable.getSelectedValue().toString() + "' ";
	    //System.out.println("consulta --> "+consulta);
	    String comentario = org.digitall.lib.sql.LibSQL.getCampo(consulta);
	    taComment.setText(comentario);
	    setChecks();
	    control();
	}
    }

    private void setChecks() {
	if (!primeraVez) {
	    if (listTable.getSelectedIndex() > -1) {
		String consultaEsquema = " Select esquema From admin.tables where tabla like '" + listTable.getSelectedValue().toString() + "%' ";
		//System.out.println("consultaEsquema --> "+consultaEsquema);
		esquema = org.digitall.lib.sql.LibSQL.getCampo(consultaEsquema);
		String consulta = "SELECT has_table_privilege('" + cbGroupName.getSelectedItem().toString() + "', '" + esquema + "." + listTable.getSelectedValue().toString() + "', 'SELECT')";
		//System.out.println("consulta--> "+consulta);
		String opcion = org.digitall.lib.sql.LibSQL.getCampo(consulta).toLowerCase();
		if (opcion.equals("t")) {
		    checkSelect.setSelected(true);
		} else
		    checkSelect.setSelected(false);
		consulta = "SELECT has_table_privilege('" + cbGroupName.getSelectedItem().toString() + "', '" + esquema + "." + listTable.getSelectedValue().toString() + "', 'INSERT')";
		opcion = org.digitall.lib.sql.LibSQL.getCampo(consulta).toLowerCase();
		if (opcion.equals("t")) {
		    checkInsert.setSelected(true);
		} else
		    checkInsert.setSelected(false);
		consulta = "SELECT has_table_privilege('" + cbGroupName.getSelectedItem().toString() + "', '" + esquema + "." + listTable.getSelectedValue().toString() + "', 'UPDATE')";
		opcion = org.digitall.lib.sql.LibSQL.getCampo(consulta).toLowerCase();
		if (opcion.equals("t")) {
		    checkUpdate.setSelected(true);
		} else
		    checkUpdate.setSelected(false);
	    }
	}
    }

    private boolean control() {
	boolean valido = true;
	String grupo = groupName.substring(groupName.indexOf("_") + 1, groupName.length());
	if (grupo.equals("admin") || grupo.equals("user") || grupo.equals("query")) {
	    bDelGroup.setEnabled(false);
	} else {
	    bDelGroup.setEnabled(true);
	}
	habilitarBotones(true);
	return valido;
    }

    private void bNewGroup_actionPerformed(ActionEvent e) {
	AddGroup agregarGrupo = new AddGroup(idsist);
	agregarGrupo.setModal(true);
	agregarGrupo.setVisible(true);
	setFields();
	setChecks();
    }

    /*************** Guardo en el vector 'tablas' las tablas de los esquemas ***********************/
    private void setTables() {
	String Q = "Select idtabla as id, tables.tabla as dato From admin.tables WHere idtabla in" + " (Select idtabla From admin.syst_tables, admin.systems Where syst_tables.idsist = systems.idsist" + " AND systems.idsist = " + idsist + " ) AND estado <> '*'";
	tablesList.getListFromQuery(Q);
	listTable.setListData(tablesList.getNombres());
	listTable.setSelectedIndex(0);
	setChecks();
    }

    private void listTable_keyTyped(KeyEvent e) {
	control();
    }

    private void bDelGroup_actionPerformed(ActionEvent e) {
	if (Advisor.question("Warning!", "Are you sure to delete this group?")) {
	    boolean band = false;
	    String consulta = "Select idtabla as id, tables.esquema||'.'||tables.tabla as dato" + " From admin.tables WHere idtabla in (Select idtabla From admin.syst_tables," + " admin.systems Where syst_tables.idsist = systems.idsist AND systems.idsist = " + idsist + ")";
	    //System.out.println("tablas--> "+consulta);
	    ResultSet tablas = LibSQL.exQuery(consulta);
	    consulta = "SELECT distinct(esquema) FROM admin.tables " + " WHere idtabla in (Select idtabla From admin.syst_tables, " + " admin.systems Where syst_tables.idsist = systems.idsist AND systems.idsist = " + idsist + " )";
	    //System.out.println("esquemas--> "+consulta);
	    ResultSet esquemas = org.digitall.lib.sql.LibSQL.exQuery(consulta);
	    try {
		while (tablas.next()) {
		    consulta = "REVOKE SELECT ON " + tablas.getString("dato") + " FROM GROUP " + groupName;
		    LibSQL.exActualizar('a', consulta);
		    consulta = "REVOKE INSERT ON " + tablas.getString("dato") + " FROM GROUP " + groupName;
		    LibSQL.exActualizar('a', consulta);
		    consulta = "REVOKE UPDATE ON " + tablas.getString("dato") + " FROM GROUP " + groupName;
		    LibSQL.exActualizar('a', consulta);
		    band = true;
		}
		while (esquemas.next()) {
		    String revoke = "REVOKE ALL PRIVILEGES ON SCHEMA " + esquemas.getString("esquema") + " FROM " + groupName;
		    LibSQL.exActualizar('a', revoke);
		    band = true;
		}
		if (band) {
		    String borrarGrupo = "DROP GROUP " + groupName;
		    if (LibSQL.exActualizar('a', borrarGrupo)) {
			org.digitall.lib.components.Advisor.messageBox("The group has been deleted", "Success");
			setFields();
		    }
		}
	    } catch (SQLException ex) {
		ex.printStackTrace();
	    }
	}
    }

    private void loadData() {
	sistema = org.digitall.lib.sql.LibSQL.getCampo("SELECT nombre FROM admin.systems WHERE idsist = " + idsist);
    }

}
