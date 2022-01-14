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
 * PanelLithologyData.java
 *
 * */
package org.digitall.common.drilling;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.digitall.lib.components.JDecEntry;
import org.digitall.lib.components.basic.BasicCombo;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTable;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.data.listeners.decListenTable;
import org.digitall.lib.sql.LibSQL;

//

public class PanelLithologyData extends BasicContainerPanel {

    private BasicLabel lblLithology = new BasicLabel();
    private BasicLabel lblStructure = new BasicLabel();
    private BasicLabel lblFromStr = new BasicLabel();
    private BasicLabel lblTo = new BasicLabel();
    private BasicLabel lblToLit = new BasicLabel();
    private BasicLabel lblFromLit = new BasicLabel();
    private BasicTextField texto = new BasicTextField();
    private JDecEntry tfFromStr = new JDecEntry();
    private JDecEntry tfToStr = new JDecEntry();
    private JDecEntry tfToLit = new JDecEntry();
    private JDecEntry tfFromLit = new JDecEntry();
    private UnAssignButton bNewStr = new UnAssignButton(true);
    private AcceptButton bUpdateLit = new AcceptButton();
    private UnAssignButton bNewLIt = new UnAssignButton(true);
    private DeleteButton btnDeleteLithology = new DeleteButton();
    private DeleteButton btnDeleteStructure = new DeleteButton();
    private AcceptButton bUpdateStr = new AcceptButton();
    private BasicScrollPane jScrollPane1 = new BasicScrollPane();
    private BasicScrollPane jScrollPane2 = new BasicScrollPane();
    private BasicTable tablaLit = new BasicTable();
    private BasicTable tablaStr = new BasicTable();
    private Vector id[] = new Vector[4];
    private String idproject = "", iddrill = "", idLithology = "", idStructure = "";
    private double newFromLit = 0;
    private double newFromStr = 0;
    String seccion[];
    private Vector types = new Vector();
    private BasicCombo litCombo = new BasicCombo();
    private BasicCombo strCombo = new BasicCombo();
    private ResultSet lithology;
    private ResultSet structure;
    //

    public PanelLithologyData(String _idproject, String _iddrill) {
	try {
	    idproject = _idproject;
	    iddrill = _iddrill;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(715, 273));
	this.setBounds(new Rectangle(5, 5, 715, 273));
	jScrollPane1.setBounds(new Rectangle(5, 15, 335, 220));
	jScrollPane2.setBounds(new Rectangle(370, 15, 335, 220));
	bNewLIt.setBounds(new Rectangle(200, 240, 40, 25));
	bNewLIt.setMnemonic('N');
	bNewLIt.setFont(new Font("Dialog", 1, 10));
	bNewLIt.setToolTipText("Insert a New Lithology");
	bNewLIt.setSize(new Dimension(40, 25));
	bNewLIt.setHorizontalTextPosition(SwingConstants.LEFT);
	bNewLIt.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			NewLit_actionPerformed(e);
		    }

		});
	tfToLit.setBounds(new Rectangle(130, 250, 40, 15));
	tfToLit.setFont(new Font("Dialog", 1, 11));
	tfToLit.setForeground(Color.red);
	lblToLit.setText("To:");
	lblToLit.setBounds(new Rectangle(130, 240, 20, 10));
	lblToLit.setFont(new Font("Dialog", 1, 10));
	lblToLit.setHorizontalAlignment(SwingConstants.CENTER);
	tfFromLit.setBounds(new Rectangle(80, 250, 40, 15));
	tfFromLit.setFont(new Font("Dialog", 1, 11));
	tfFromLit.setForeground(Color.blue);
	lblFromLit.setText("From:");
	lblFromLit.setBounds(new Rectangle(80, 240, 30, 10));
	lblFromLit.setFont(new Font("Dialog", 1, 10));
	lblFromLit.setHorizontalAlignment(SwingConstants.RIGHT);
	//setBounds(new Rectangle(352, 0, 5, 270));
	//setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	bUpdateStr.setBounds(new Rectangle(665, 240, 40, 25));
	bUpdateStr.setMnemonic('U');
	bUpdateStr.setFont(new Font("Dialog", 1, 10));
	bUpdateStr.setToolTipText("Update Structures");
	bUpdateStr.setSize(new Dimension(40, 25));
	bUpdateStr.setHorizontalTextPosition(SwingConstants.LEFT);
	bUpdateStr.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnUpdateStr_actionPerformed(e);
		    }

		});
	btnDeleteLithology.setBounds(new Rectangle(5, 240, 40, 25));
	btnDeleteLithology.setToolTipText("Delete a Lithology");
	btnDeleteLithology.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnDeleteLithology_actionPerformed(e);
		    }

		});
	btnDeleteStructure.setBounds(new Rectangle(370, 240, 40, 25));
	btnDeleteStructure.setToolTipText("Delete a Structure");
	btnDeleteStructure.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnDeleteStructure_actionPerformed(e);
		    }

		});
	this.add(bUpdateStr, null);
	this.add(btnDeleteStructure, null);
	this.add(btnDeleteLithology, null);
	//or, null);
	this.add(lblFromLit, null);
	this.add(tfFromLit, null);
	this.add(lblToLit, null);
	this.add(tfToLit, null);
	this.add(bNewLIt, null);
	this.add(jScrollPane2, null);
	this.add(lblTo, null);
	this.add(lblFromStr, null);
	this.add(tfToStr, null);
	this.add(tfFromStr, null);
	this.add(bNewStr, null);
	this.add(lblStructure, null);
	this.add(lblLithology, null);
	this.add(jScrollPane1, null);
	this.add(bUpdateLit, null);
	texto.addKeyListener(new decListenTable());
	tfFromStr.setBounds(new Rectangle(445, 250, 40, 15));
	tfFromStr.setFont(new Font("Dialog", 1, 11));
	tfFromStr.setForeground(Color.blue);
	tfToStr.setBounds(new Rectangle(495, 250, 40, 15));
	tfToStr.setFont(new Font("Dialog", 1, 11));
	tfToStr.setForeground(Color.red);
	lblFromStr.setText("From:");
	lblFromStr.setBounds(new Rectangle(445, 240, 30, 10));
	lblFromStr.setFont(new Font("Dialog", 1, 10));
	lblFromStr.setHorizontalAlignment(SwingConstants.RIGHT);
	lblTo.setText("To:");
	lblTo.setBounds(new Rectangle(495, 240, 20, 10));
	lblTo.setFont(new Font("Dialog", 1, 10));
	lblTo.setHorizontalAlignment(SwingConstants.CENTER);
	bUpdateLit.setBounds(new Rectangle(300, 240, 40, 25));
	bUpdateLit.setMnemonic('U');
	bUpdateLit.setFont(new Font("Dialog", 1, 10));
	bUpdateLit.setToolTipText("Update Lithologies");
	bUpdateLit.setSize(new Dimension(40, 25));
	bUpdateLit.setHorizontalTextPosition(SwingConstants.LEFT);
	bUpdateLit.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnUpdateLit_actionPerformed(e);
		    }

		});
	lblLithology.setText("Lithology");
	lblLithology.setBounds(new Rectangle(10, 0, 55, 15));
	lblLithology.setFont(new Font("Dialog", 1, 10));
	lblLithology.setForeground(Color.blue);
	lblStructure.setText("Structure");
	lblStructure.setBounds(new Rectangle(370, 0, 60, 15));
	lblStructure.setFont(new Font("Dialog", 1, 10));
	lblStructure.setForeground(Color.blue);
	bNewStr.setBounds(new Rectangle(565, 240, 40, 25));
	bNewStr.setMnemonic('W');
	bNewStr.setFont(new Font("Dialog", 1, 10));
	bNewStr.setToolTipText("New Structur");
	bNewStr.setSize(new Dimension(40, 25));
	bNewStr.setHorizontalTextPosition(SwingConstants.LEFT);
	bNewStr.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			NewStr_actionPerformed(e);
		    }

		});
	litCombo();
	cargaColsLit();
	strCombo();
	cargaColsStr();
    }

    private void litCombo() {
	String consultaCombo = "SELECT idlit_tab, name FROM tabs.lithology_tabs WHERE status <> '*' AND idlit_tab > 0 ";
	ResultSet combo = org.digitall.lib.sql.LibSQL.exQuery(consultaCombo);
	try {
	    while (combo.next()) {
		litCombo.addItem(combo.getString("name"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private void strCombo() {
	String consultaCombo = "SELECT idstr_tab, name FROM tabs.structure_tabs WHERE status <> '*' ";
	ResultSet combo = org.digitall.lib.sql.LibSQL.exQuery(consultaCombo);
	try {
	    while (combo.next()) {
		strCombo.addItem(combo.getString("name"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private void cargaColsLit() {
	try {
	    DefaultTableModel modeloLit = new DefaultTableModel();
	    types.removeAllElements();
	    /** Agrego las cabeceras para la tabla de las Litologias */
	    modeloLit.addColumn("From");
	    types.addElement(new Integer(Types.DOUBLE));
	    modeloLit.addColumn("To");
	    types.addElement(new Integer(Types.DOUBLE));
	    modeloLit.addColumn("Lithology");
	    types.addElement(new Integer(Types.VARCHAR));
	    modeloLit.addColumn("Geological Description");
	    types.addElement(new Integer(Types.VARCHAR));
	    /****************** instancio el modeloLit a la tablaLit ****************/
	    tablaLit = new BasicTable(modeloLit);
	    tablaLit.setCellEditor(new DefaultCellEditor(texto));
	    id[0] = new Vector();
	    id[0].removeAllElements();
	    String consultaLit = "SELECT drill_lithologies.iddrill_lit, drill_lithologies.idlit_tab, lithology_tabs.name," + " drill_lithologies.\"from\", drill_lithologies.\"to\", drill_lithologies.description " + " FROM drilling.drill_lithologies, tabs.lithology_tabs" + " WHERE iddrill = " + iddrill + " AND drilling.drill_lithologies.idlit_tab = tabs.lithology_tabs.idlit_tab" + " AND drill_lithologies.status <> '*'" + " Order By \"from\", \"to\"";
	    //System.out.println("consultaLit--> "+ consultaLit);
	    boolean band = false;
	    lithology = org.digitall.lib.sql.LibSQL.exQuery(consultaLit);
	    while (lithology.next()) {
		band = true;
		Vector fila = new Vector();
		fila.add(new Double(lithology.getDouble("from")));
		//modeloLit.setColFrom(fila.size()-1);
		fila.add(new Double(lithology.getDouble("to")));
		//modeloLit.setColTo(fila.size()-1);
		fila.add(lithology.getString("name"));
		fila.add(new String(lithology.getString("description")));
		id[0].add(new Integer(lithology.getInt("iddrill_lit")));
		modeloLit.addRow(fila);
	    }
	    /** Seteo el combo de litologias*/
	    TableColumnModel litModel = tablaLit.getColumnModel();
	    TableColumn litColumn = litModel.getColumn(modeloLit.getColumnCount() - 2);
	    litColumn.setCellEditor(new DefaultCellEditor(litCombo));
	    tablaLit.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent key) {
			}

			public void keyPressed(KeyEvent key) {
			}

			public void keyReleased(KeyEvent key) {
			    //System.out.println(tabla.getSelectedRow() + "," + tabla.getSelectedColumn());
			    //System.out.println(tabla.getValueAt(tabla.getSelectedRow(),tabla.getSelectedColumn()));
			}

		    });
	    tablaLit.addMouseListener(new MouseListener() {

			public void mouseExited(MouseEvent me) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mousePressed(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}

			public void mouseReleased(MouseEvent me) {
			    //System.out.println(tablaLit.getSelectedRow() + "," + tablaLit.getSelectedColumn());
			    //System.out.println(tablaLit.getValueAt(tablaLit.getSelectedRow(),tablaLit.getSelectedColumn()));
			    idLithology = id[0].elementAt(tablaLit.getSelectedRow()).toString();
			    btnDeleteLithology.setEnabled(true);
			}

		    });
	    tablaLit.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    tablaLit.getTableHeader().setReorderingAllowed(false);
	    tablaLit.getTableHeader().setResizingAllowed(true);
	    tablaLit.setAutoResizeMode(BasicTable.AUTO_RESIZE_OFF);
	    if (band) {
		lithology.previous();
		newFromLit = lithology.getDouble("To");
	    } else {
		newFromLit = 0.0;
	    }
	    double ToLit = newFromLit + 1;
	    //System.out.println("toLit: "+ToLit);
	    tfToLit.setText("0" + ToLit);
	    tfFromLit.setText("" + newFromLit);
	    jScrollPane1.getViewport().add(tablaLit, null);
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	btnDeleteLithology.setEnabled(false);
	idLithology = "";
    }

    private void cargaColsStr() {
	try {
	    DefaultTableModel modeloStr = new DefaultTableModel();
	    /********** Seteo la cabecera de la tabla Structure ***********/
	    modeloStr.addColumn("From");
	    modeloStr.addColumn("To");
	    modeloStr.addColumn("Structure");
	    modeloStr.addColumn("Dip dir.");
	    modeloStr.addColumn("dip angle");
	    /****************** Agrego el modelostr a la tablaStr ****************/
	    tablaStr = new BasicTable(modeloStr);
	    tablaStr.setCellEditor(new DefaultCellEditor(texto));
	    String consultaStr = "SELECT drill_structures.iddrill_str, drill_structures.idstr_tab, structure_tabs.name, " + " drill_structures.\"from\", drill_structures.\"to\", drill_structures.dip_direction," + " drill_structures.dip_angle" + " FROM drilling.drill_structures, tabs.structure_tabs " + " WHERE iddrill = " + iddrill + " AND drilling.drill_structures.idstr_tab = tabs.structure_tabs.idstr_tab " + " AND drill_structures.status <> '*'" + " Order By \"from\", \"to\"";
	    //System.out.println("consultaLit--> "+ consultaStr);
	    boolean band = false;
	    id[1] = new Vector();
	    id[1].removeAllElements();
	    structure = org.digitall.lib.sql.LibSQL.exQuery(consultaStr);
	    while (structure.next()) {
		band = true;
		Vector fila = new Vector();
		fila.add(new Double(structure.getDouble("from")));
		//modeloStr.setColFrom(fila.size()-1);
		fila.add(new Double(structure.getDouble("to")));
		//modeloStr.setColTo(fila.size()-1);
		fila.add(structure.getString("name"));
		fila.add(new Double(structure.getString("dip_direction")));
		fila.add(new Double(structure.getString("dip_angle")));
		id[1].add(new Integer(structure.getInt("iddrill_str")));
		modeloStr.addRow(fila);
	    }
	    TableColumnModel strModel = tablaStr.getColumnModel();
	    TableColumn strColumn = strModel.getColumn(modeloStr.getColumnCount() - 3);
	    strColumn.setCellEditor(new DefaultCellEditor(strCombo));
	    tablaStr.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent key) {
			}

			public void keyPressed(KeyEvent key) {
			}

			public void keyReleased(KeyEvent key) {
			    //System.out.println(tabla.getSelectedRow() + "," + tabla.getSelectedColumn());
			    //System.out.println(tabla.getValueAt(tabla.getSelectedRow(),tabla.getSelectedColumn()));
			}

		    });
	    tablaStr.addMouseListener(new MouseListener() {

			public void mouseExited(MouseEvent me) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mousePressed(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}

			public void mouseReleased(MouseEvent me) {
			    //System.out.println(tablaStr.getSelectedRow() + "," + tablaStr.getSelectedColumn());
			    idStructure = id[1].elementAt(tablaStr.getSelectedRow()).toString();
			    btnDeleteStructure.setEnabled(true);
			}

		    });
	    /** Scrol para la tabla tablaStr */
	    tablaStr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    tablaStr.getTableHeader().setReorderingAllowed(false);
	    tablaStr.getTableHeader().setResizingAllowed(true);
	    tablaStr.setAutoResizeMode(BasicTable.AUTO_RESIZE_OFF);
	    if (band) {
		structure.previous();
		newFromStr = structure.getDouble("To");
	    } else {
		newFromStr = 0;
	    }
	    tfFromStr.setText("" + newFromStr);
	    double ToStr = newFromStr + 1;
	    tfToStr.setText("0" + ToStr);
	    jScrollPane2.getViewport().add(tablaStr, null);
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	btnDeleteStructure.setEnabled(false);
	idStructure = "";
    }

    public void createSQLLit(Vector[] id) {
	String[] SQL = new String[3];
	for (int rows = 0; rows < tablaLit.getRowCount(); rows++) {
	    SQL[0] = "UPDATE drilling.drill_lithologies set " + " idlit_tab = (Select idlit_tab From tabs.lithology_tabs Where name = '" + tablaLit.getValueAt(rows, tablaLit.getColumnCount() - 2) + "')" + ", description = '" + tablaLit.getValueAt(rows, 3) + "' " + ", \"from\" = " + tablaLit.getValueAt(rows, 0) + ", \"to\" = " + tablaLit.getValueAt(rows, 1) + " WHERE iddrill = " + iddrill + " AND iddrill_lit = " + id[0].elementAt(rows);
	    //System.out.println("SQL[0]("+ rows +")----> "+SQL[0]);
	    LibSQL.exActualizar('a', SQL[0]);
	}
	cargaColsLit();
    }

    private void createSQLStr(Vector[] id) {
	String[] SQL = new String[3];
	for (int rows = 0; rows < tablaStr.getRowCount(); rows++) {
	    SQL[1] = "UPDATE drilling.drill_structures set " + " idstr_tab = (Select idstr_tab From tabs.structure_tabs Where name = '" + tablaStr.getValueAt(rows, tablaStr.getColumnCount() - 3) + "')" + ", \"from\" = " + tablaStr.getValueAt(rows, 0) + ", \"to\" = " + tablaStr.getValueAt(rows, 1) + ", dip_direction = 0" + tablaStr.getValueAt(rows, 3) + ", dip_angle = 0" + tablaStr.getValueAt(rows, 4) + " WHERE iddrill = " + iddrill + " AND iddrill_str = " + id[1].elementAt(rows);
	    //System.out.println("SQL[1]("+ rows +")----> "+SQL[1]);
	    LibSQL.exActualizar('a', SQL[1]);
	}
	cargaColsStr();
    }

    private void btnUpdateLit_actionPerformed(ActionEvent e) {
	createSQLLit(id);
    }

    private void btnUpdateStr_actionPerformed(ActionEvent e) {
	createSQLStr(id);
    }

    private void NewLit_actionPerformed(ActionEvent e) {
	/**
       *    Antes de ingresar un nuevo registro me aseguro de que el campo To sea mas grande que el campo From
       * */
	createSQLLit(id);
	double newTo = Double.parseDouble("0" + tfToLit.getText());
	newFromLit = Double.parseDouble(tfFromLit.getText().trim());
	String consultaInsert = "INSERT INTO drilling.drill_lithologies VALUES ( (Select max(iddrill_lit)+1 From drilling.drill_lithologies)," + iddrill + ", 0, 0" + newFromLit + ", 0" + newTo + ", '', '')";
	if (LibSQL.exActualizar('a', consultaInsert)) {
	    cargaColsLit();
	} else {
	    org.digitall.lib.components.Advisor.messageBox("Se produjo un error en la consulta \nal cargar una nueva lithologia", "Error");
	}
	//   System.out.println("insert: "+consultaInsert);
	btnDeleteLithology.setEnabled(false);
	idLithology = "";
    }

    private void NewStr_actionPerformed(ActionEvent e) {
	/**
       *    Antes de ingresar un nuevo registro me aseguro de que el campo To sea mas grande que el campo From
       * */
	createSQLStr(id);
	double newTo = Double.parseDouble("0" + tfToStr.getText());
	newFromStr = Double.parseDouble(tfFromStr.getText().trim());
	String consultaInsert = "INSERT INTO drilling.drill_structures VALUES ( (Select max(iddrill_str)+1 From drilling.drill_structures)," + iddrill + ", 0, 0" + newFromStr + ", 0" + newTo + ", 0, 0, '')";
	if (LibSQL.exActualizar('a', consultaInsert)) {
	    cargaColsStr();
	} else {
	    org.digitall.lib.components.Advisor.messageBox("Se produjo un error en la consulta \nal cargar una nueva Structura", "Error");
	}
	btnDeleteStructure.setEnabled(false);
	idStructure = "";
    }

    void tfTo_actionPerformed(ActionEvent e) {
    }

    private void btnDeleteLithology_actionPerformed(ActionEvent e) {
	String deleteLithologyRow = "UPDATE drilling.drill_lithologies SET status = '*'  WHERE iddrill_lit = " + id[0].elementAt(tablaLit.getSelectedRow());
	if (LibSQL.exActualizar('m', deleteLithologyRow)) {
	    org.digitall.lib.components.Advisor.messageBox("Delete success!", "Message");
	    cargaColsLit();
	}
    }

    private void btnDeleteStructure_actionPerformed(ActionEvent e) {
	String deleteStructureRow = "UPDATE drilling.drill_structures SET status = '*'  WHERE iddrill_Str = " + id[1].elementAt(tablaStr.getSelectedRow());
	if (LibSQL.exActualizar('m', deleteStructureRow)) {
	    org.digitall.lib.components.Advisor.messageBox("Delete success!", "Message");
	    cargaColsStr();
	}
    }

}
