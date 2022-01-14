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
 * PanelAddTablesToSystem.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.Color;
import java.awt.Component;
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

import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.List;
import org.digitall.lib.components.ListItem;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

//

public class PanelAddTablesToSystem extends BasicContainerPanel {

    private BasicPanel PanelSystems = new BasicPanel();
    private BasicPanel PanelTables = new BasicPanel();
    private BasicScrollPane spBDTables = new BasicScrollPane();
    private BasicScrollPane spSystemTables = new BasicScrollPane();
    private BasicScrollPane spComment = new BasicScrollPane();
    private BasicLabel lblSystems = new BasicLabel();
    private BasicLabel lblBDTables = new BasicLabel();
    private BasicLabel lblSystemTables = new BasicLabel();
    private BasicLabel lblComment = new BasicLabel();
    private JCombo cbSystems = new JCombo();
    private JList listBDTables = new JList();
    private JList listSystemTables = new JList();
    private List bdTablesList = new List();
    private List systemTablesList = new List();
    private ListItem itemListBDTables = new ListItem();
    private ListItem itemListSystemTables = new ListItem();
    private AssignButton bAssign = new AssignButton();
    private UnAssignButton bUnAssign = new UnAssignButton();
    private AcceptButton bAccept = new AcceptButton();
    private CloseButton bCLose = new CloseButton();
    private JTextArea taComment = new JTextArea();
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private String idsist, system = "";
    private BasicButton btnNewSystem = new BasicButton();
    private Set unAssignSet = new HashSet();
    private Set assignSet = new HashSet();

    public PanelAddTablesToSystem(BasicInternalFrame _parent, String _idsist) {
	try {
	    parent = _parent;
	    idsist = _idsist;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelAddTablesToSystem(BasicDialog _parent, String _idsist) {
	try {
	    parent = _parent;
	    idsist = _idsist;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelAddTablesToSystem(JFrame _parent, String _idsist) {
	try {
	    parent = _parent;
	    idsist = _idsist;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(552, 393));
	PanelSystems.setBounds(new Rectangle(5, 5, 540, 40));
	PanelSystems.setLayout(null);
	PanelSystems.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	cbSystems.setBounds(new Rectangle(165, 10, 215, 20));
	cbSystems.setFont(new Font("Dialog", 1, 11));
	cbSystems.setForeground(Color.blue);
	cbSystems.addItemListener(new ItemListener() {

		    public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    system = cbSystems.getSelectedItem().toString();
			    //System.out.println("system: "+ system);
			    idsist = org.digitall.lib.sql.LibSQL.getCampo("Select idsist from admin.systems where nombre = '" + system + "'");
			    setList();
			}
		    }

		});
	lblSystems.setText("System:");
	lblSystems.setBounds(new Rectangle(110, 15, 50, 10));
	lblSystems.setFont(new Font("Dialog", 1, 11));
	lblSystems.setHorizontalAlignment(SwingConstants.LEFT);
	PanelTables.setBounds(new Rectangle(5, 60, 540, 295));
	PanelTables.setLayout(null);
	PanelTables.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	spBDTables.setBounds(new Rectangle(10, 20, 235, 165));
	spSystemTables.setBounds(new Rectangle(295, 20, 235, 165));
	lblBDTables.setText("B.D. Tables:");
	lblBDTables.setBounds(new Rectangle(10, 8, 235, 10));
	lblBDTables.setFont(new Font("Dialog", 1, 11));
	lblBDTables.setHorizontalAlignment(SwingConstants.LEFT);
	lblSystemTables.setText("System's Tables:");
	lblSystemTables.setBounds(new Rectangle(295, 8, 235, 10));
	lblSystemTables.setFont(new Font("Dialog", 1, 11));
	lblSystemTables.setHorizontalAlignment(SwingConstants.LEFT);
	listBDTables.setBounds(new Rectangle(0, 0, 235, 165));
	listBDTables.addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			listBDTables_mouseClicked(e);
		    }

		});
	listBDTables.addKeyListener(new KeyAdapter() {

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
	listSystemTables.addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			listSystemTables_mouseClicked(e);
		    }

		});
	listSystemTables.addKeyListener(new KeyAdapter() {

		    public void keyPressed(KeyEvent e) {
			listSystemTables_keyTyped(e);
		    }

		    public void keyReleased(KeyEvent e) {
			listSystemTables_keyTyped(e);
		    }

		    public void keyTyped(KeyEvent e) {
			listSystemTables_keyTyped(e);
		    }

		});
	bAssign.setBounds(new Rectangle(250, 50, 40, 25));
	bAssign.setToolTipText("Assign");
	bAssign.setMnemonic('S');
	bAssign.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAssign_actionPerformed(e);
		    }

		});
	bUnAssign.setBounds(new Rectangle(250, 100, 40, 30));
	bUnAssign.setToolTipText("Remove");
	bUnAssign.setMnemonic('R');
	bUnAssign.setSize(new Dimension(40, 25));
	bUnAssign.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bUnAssign_actionPerformed(e);
		    }

		});
	bAccept.setBounds(new Rectangle(505, 360, 40, 25));
	bAccept.setToolTipText("Accept");
	bAccept.setMnemonic('A');
	bAccept.setSize(new Dimension(40, 25));
	bAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAccept_actionPerformed(e);
		    }

		});
	bCLose.setBounds(new Rectangle(5, 360, 40, 25));
	bCLose.setToolTipText("Close");
	bCLose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCLose_actionPerformed(e);
		    }

		});
	spComment.setBounds(new Rectangle(10, 205, 520, 80));
	spComment.getViewport().setLayout(null);
	spComment.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	taComment.setBounds(new Rectangle(0, 0, 515, 75));
	btnNewSystem.setText("New System");
	btnNewSystem.setBounds(new Rectangle(425, 10, 105, 20));
	btnNewSystem.setToolTipText("Add new System");
	btnNewSystem.setFont(new Font("Dialog", 1, 10));
	btnNewSystem.setMnemonic('N');
	btnNewSystem.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnNewSystem_actionPerformed(e);
		    }

		});
	lblComment.setText("Comment:");
	lblComment.setBounds(new Rectangle(10, 193, 520, 10));
	lblComment.setFont(new Font("Dialog", 1, 11));
	lblComment.setHorizontalAlignment(SwingConstants.LEFT);
	PanelSystems.add(btnNewSystem, null);
	PanelSystems.add(lblSystems, null);
	PanelSystems.add(cbSystems, null);
	spComment.getViewport().add(taComment, null);
	PanelTables.add(lblComment, null);
	PanelTables.add(spComment, null);
	PanelTables.add(bUnAssign, null);
	PanelTables.add(bAssign, null);
	PanelTables.add(lblSystemTables, null);
	spSystemTables.getViewport().add(listSystemTables, null);
	PanelTables.add(spSystemTables, null);
	spBDTables.getViewport().add(listBDTables, null);
	PanelTables.add(spBDTables, null);
	PanelTables.add(lblBDTables, null);
	this.add(bCLose, null);
	this.add(bAccept, null);
	this.add(PanelTables, null);
	this.add(PanelSystems, null);
	loadData();
    }

    private void dispose() {
	switch (parentType) {
	    case DIALOG:
		((BasicDialog)parent).dispose();
		break;
	    case INTERNALFRAME:
		((BasicInternalFrame)parent).dispose();
		break;
	    case FRAME:
		((JFrame)parent).dispose();
		break;
	}
    }

    private void bCLose_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void loadData() {
	if (!Environment.sessionUser.equals("admin")) {
	    btnNewSystem.setVisible(false);
	} else
	    btnNewSystem.setVisible(true);
	String nombre = org.digitall.lib.sql.LibSQL.getCampo("SELECT nombre FROM admin.systems WHERE idsist = " + idsist);
	cbSystems.loadJCombo("SELECT nombre FROM admin.systems WHERE estado <> '*' And idsist > 0;");
	cbSystems.setSelectedValue(nombre);
	setList();
    }

    private void setList() {
	String Q = "Select idtabla as id,(esquema || '.' || tabla) as dato From admin.tables Where idtabla not in (Select idtabla From admin.syst_tables where idsist = " + idsist + " ) AND estado <>'*' order by esquema";
	bdTablesList.getListFromQuery(Q);
	listBDTables.setListData(bdTablesList.getNombres());
	listBDTables.setSelectedIndex(0);
	Q = "Select idtabla as id,(esquema || '.' || tabla) as dato From admin.tables Where idtabla in (Select idtabla From admin.syst_tables where idsist = " + idsist + " ) AND estado <>'*' order by esquema";
	systemTablesList.getListFromQuery(Q);
	listSystemTables.setListData(systemTablesList.getNombres());
	listSystemTables.setSelectedIndex(0);
    }

    private void listBDTables_mouseClicked(MouseEvent e) {
	if ((e.getClickCount() == 1) && (listBDTables.getSelectedIndex() >= 0)) {
	    itemListBDTables = bdTablesList.getItemFromString(listBDTables.getSelectedValue().toString());
	    int idtabla = itemListBDTables.getId();
	    ResultSet tablas = org.digitall.lib.sql.LibSQL.exQuery("SELECT esquema,tabla FROM admin.tables WHERE idtabla = " + idtabla);
	    try {
		if (tablas.next()) {
		    String consulta = "SELECT description AS comment FROM pg_description de, pg_class bc, pg_namespace ns WHERE de.objoid = bc.oid and " + " bc.relnamespace = ns.oid AND  objsubid = 0 AND   ns.nspname = '" + tablas.getString("esquema") + "'  AND bc.relname = '" + tablas.getString("tabla") + "' ";
		    String comment = org.digitall.lib.sql.LibSQL.getCampo(consulta);
		    taComment.setText(comment);
		}
	    } catch (SQLException f) {
		f.printStackTrace();
		// TODO
	    }
	} else if ((e.getClickCount() == 2) && (listBDTables.getSelectedIndex() >= 0)) {
	    /*bdTablesList.swapItem(systemTablesList, bdTablesList.getIndexFromString(listBDTables.getSelectedValue().toString()));
            listBDTables.setListData(bdTablesList.getNombres());
            listSystemTables.setListData(systemTablesList.getNombres());*/
	    assign();
	}
    }

    private void listSystemTables_mouseClicked(MouseEvent e) {
	if ((e.getClickCount() == 1) && (listBDTables.getSelectedIndex() >= 0)) {
	    itemListSystemTables = systemTablesList.getItemFromString(listSystemTables.getSelectedValue().toString());
	    int idtabla = itemListSystemTables.getId();
	    ResultSet tablas = org.digitall.lib.sql.LibSQL.exQuery("SELECT esquema,tabla FROM admin.tables WHERE idtabla = " + idtabla);
	    try {
		if (tablas.next()) {
		    String consulta = "SELECT description AS comment FROM pg_description de, pg_class bc, pg_namespace ns WHERE de.objoid = bc.oid and " + " bc.relnamespace = ns.oid AND  objsubid = 0 AND   ns.nspname = '" + tablas.getString("esquema") + "'  AND bc.relname = '" + tablas.getString("tabla") + "' ";
		    String comment = org.digitall.lib.sql.LibSQL.getCampo(consulta);
		    taComment.setText(comment);
		}
	    } catch (SQLException f) {
		// TODO
	    }
	} else if ((e.getClickCount() == 2) && (listSystemTables.getSelectedIndex() >= 0)) {
	    /*systemTablesList.swapItem(bdTablesList, systemTablesList.getIndexFromString(listSystemTables.getSelectedValue().toString()));
            listSystemTables.setListData(systemTablesList.getNombres());
            listBDTables.setListData(bdTablesList.getNombres());*/
	    unAssign();
	}
    }

    private void listSystemTables_keyTyped(KeyEvent e) {
    }

    private void listTable_keyTyped(KeyEvent e) {
    
    }

    private void assign() {
	if (listBDTables.getSelectedIndices().length > 0) {
	    int[] selecteds = listBDTables.getSelectedIndices();
	    for (int i = selecteds.length - 1; i >= 0; i--) {
		if (unAssignSet.contains(bdTablesList.getIds().elementAt(selecteds[i]))) {
		    unAssignSet.remove(bdTablesList.getIds().elementAt(selecteds[i]));
		} else {
		    assignSet.add(bdTablesList.getIds().elementAt(selecteds[i]));
		}
		bdTablesList.swapItem(systemTablesList, selecteds[i]);
		listBDTables.setListData(bdTablesList.getNombres());
		listSystemTables.setListData(systemTablesList.getNombres());
	    }
	}
    }

    private void unAssign() {
	if (listSystemTables.getSelectedIndices().length > 0) {
	    int[] selecteds = listSystemTables.getSelectedIndices();
	    for (int i = selecteds.length - 1; i >= 0; i--) {
		if (assignSet.contains(systemTablesList.getIds().elementAt(selecteds[i]))) {
		    assignSet.remove(systemTablesList.getIds().elementAt(selecteds[i]));
		} else {
		    unAssignSet.add(systemTablesList.getIds().elementAt(selecteds[i]));
		}
		systemTablesList.swapItem(bdTablesList, selecteds[i]);
		listSystemTables.setListData(systemTablesList.getNombres());
		listBDTables.setListData(bdTablesList.getNombres());
	    }
	}
    }

    private void bAccept_actionPerformed(ActionEvent e) {
	seteaDataBaseList();
    }

    private void seteaDataBaseList() {
	String insertQuery = "";
	String deleteQuery = "";
	for (int i = 0; i < assignSet.size(); i++) {
	    String idtabla = assignSet.toArray()[i].toString();
	    insertQuery += "INSERT INTO admin.syst_tables VALUES( (Select max(idsis_tab) + 1 From admin.syst_tables) " + " ," + idsist + ", " + idtabla + ",'','');\n";
	}
	for (int i = 0; i < unAssignSet.size(); i++) {
	    String idtabla = unAssignSet.toArray()[i].toString();
	    deleteQuery += "DELETE FROM admin.syst_tables WHERE idsist = " + idsist + " AND idtabla = " + idtabla + ";\n";
	}
	if (Advisor.question("Warning!", "Are you sure?")) {
	    if (LibSQL.exActualizar('a', insertQuery + deleteQuery)) {
		org.digitall.lib.components.Advisor.messageBox("Update success!", "Message");
		dispose();
	    } else {
		org.digitall.lib.components.Advisor.messageBox("Update success!", "Error");
	    }
	    //System.out.println(insertQuery + deleteQuery);
	}
    }

    private void btnNewSystem_actionPerformed(ActionEvent e) {
	NewSystem nuevo = new NewSystem();
	nuevo.setModal(true);
	nuevo.setVisible(true);
	loadData();
    }

    private void bAssign_actionPerformed(ActionEvent e) {
	assign();
    }

    private void bUnAssign_actionPerformed(ActionEvent e) {
	unAssign();
    }

}
