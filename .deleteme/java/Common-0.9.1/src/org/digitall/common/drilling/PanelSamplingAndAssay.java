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
 * PanelSamplingAndAssay.java
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

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextArea;
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
import org.digitall.lib.sql.LibSQL;

public class PanelSamplingAndAssay extends BasicContainerPanel {

    private BasicScrollPane spSampling = new BasicScrollPane();
    private BasicScrollPane spQaqcDescription = new BasicScrollPane();
    private UnAssignButton btnNew = new UnAssignButton(true);
    private AcceptButton btnUpdate = new AcceptButton();
    private DeleteButton btnDelete = new DeleteButton();
    private JDecEntry tfFrom = new JDecEntry();
    private JDecEntry tfTo = new JDecEntry();
    private BasicLabel lblFrom = new BasicLabel();
    private BasicLabel lblTo = new BasicLabel();
    private BasicLabel lblSamplinAndAssay = new BasicLabel();
    private BasicLabel lblSamplinAndAssay1 = new BasicLabel();
    private Vector id[] = new Vector[4];
    private BasicTextField texto = new BasicTextField();
    private BasicCombo qaqcCombo = new BasicCombo();
    private BasicTable tablaSamples = new BasicTable();
    private String idproject = "", iddrill = "", idSample = "";
    private ResultSet Samples;
    private double newFromSample = 0;
    private JTextArea taQaqcDescription = new JTextArea();
    //

    public PanelSamplingAndAssay(String _idproject, String _iddrill) {
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
	this.setSize(new Dimension(715, 272));
	spSampling.setBounds(new Rectangle(5, 20, 380, 210));
	//spSampling.setSize(new Dimension(700, 210));
	btnNew.setBounds(new Rectangle(250, 240, 40, 25));
	btnNew.setMnemonic('N');
	btnNew.setFont(new Font("Dialog", 1, 10));
	btnNew.setSize(new Dimension(40, 25));
	btnNew.setToolTipText("New");
	btnNew.setHorizontalTextPosition(SwingConstants.LEFT);
	btnNew.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnNew_actionPerformed(e);
		    }

		});
	btnUpdate.setBounds(new Rectangle(345, 240, 40, 25));
	btnUpdate.setMnemonic('U');
	btnUpdate.setFont(new Font("Dialog", 1, 10));
	btnUpdate.setToolTipText("Update");
	btnUpdate.setSize(new Dimension(40, 25));
	btnUpdate.setHorizontalTextPosition(SwingConstants.LEFT);
	btnUpdate.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bUpdate_actionPerformed(e);
		    }

		});
	tfFrom.setBounds(new Rectangle(130, 250, 45, 15));
	tfFrom.setSize(new Dimension(45, 15));
	tfFrom.setForeground(Color.blue);
	tfFrom.setFont(new Font("Default", 1, 11));
	tfTo.setBounds(new Rectangle(190, 250, 45, 15));
	tfTo.setForeground(Color.red);
	tfTo.setFont(new Font("Default", 1, 11));
	lblFrom.setText("From:");
	lblFrom.setBounds(new Rectangle(130, 240, 45, 10));
	lblFrom.setFont(new Font("Dialog", 1, 10));
	lblFrom.setHorizontalAlignment(SwingConstants.LEFT);
	lblTo.setText("To:");
	lblTo.setBounds(new Rectangle(190, 240, 45, 10));
	lblTo.setFont(new Font("Dialog", 1, 10));
	lblTo.setHorizontalAlignment(SwingConstants.LEFT);
	lblSamplinAndAssay.setText("Sampling & Assay");
	lblSamplinAndAssay.setBounds(new Rectangle(10, 5, 375, 15));
	lblSamplinAndAssay.setFont(new Font("Dialog", 1, 10));
	lblSamplinAndAssay.setForeground(Color.blue);
	spQaqcDescription.setBounds(new Rectangle(410, 20, 295, 210));
	taQaqcDescription.setBounds(new Rectangle(0, 0, 383, 193));
	taQaqcDescription.setEditable(false);
	taQaqcDescription.setWrapStyleWord(true);
	taQaqcDescription.setLineWrap(true);
	taQaqcDescription.setFont(new Font("Default", 0, 10));
	lblSamplinAndAssay1.setText("Quality control procedure");
	lblSamplinAndAssay1.setBounds(new Rectangle(410, 5, 295, 15));
	lblSamplinAndAssay1.setFont(new Font("Dialog", 1, 10));
	lblSamplinAndAssay1.setForeground(Color.blue);
	//setBounds(new Rectangle(395, 0, 5, 270));
	//setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	btnDelete.setBounds(new Rectangle(5, 240, 40, 25));
	btnDelete.setToolTipText("Delete");
	btnDelete.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnDelete_actionPerformed(e);
		    }

		});
	spQaqcDescription.getViewport().add(taQaqcDescription, null);
	//or, null);
	this.add(lblSamplinAndAssay1, null);
	this.add(spQaqcDescription, null);
	this.add(btnDelete, null);
	this.add(lblSamplinAndAssay, null);
	this.add(lblTo, null);
	this.add(lblFrom, null);
	this.add(tfTo, null);
	this.add(tfFrom, null);
	this.add(btnUpdate, null);
	this.add(btnNew, null);
	this.add(spSampling, null);
	btnDelete.setEnabled(false);
	cargaQaqcCombo();
	cargaCols();
	cargaQualityControl();
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	/**
       *    Antes de ingresar un nuevo registro me aseguro de que el campo To sea mas grande que el campo From
       * */
	createSQL(id);
	double newTo = Double.parseDouble("0" + tfTo.getText());
	newFromSample = Double.parseDouble(tfFrom.getText().trim());
	//System.out.println("to - from: " + newTo + " - " + newFromSample);
	String consultaInsert = "INSERT INTO drilling.samples " + " VALUES ( (Select max(idsample) + 1 From drilling.samples)" + "," + iddrill + ",'', 0" + newFromSample + ", 0" + newTo + ",1,0,'',null,'',null,'','')";
	//System.out.println("insert--> " + consultaInsert);
	if (LibSQL.exActualizar('a', consultaInsert)) {
	    cargaCols();
	} else {
	    org.digitall.lib.components.Advisor.messageBox("Se produjo un error en la consulta \nal cargar un nuevo intervalo para Samples", "Error");
	}
	idSample = "";
    }

    private void bUpdate_actionPerformed(ActionEvent e) {
	createSQL(id);
	cargaCols();
    }

    private void cargaCols() {
	try {
	    DefaultTableModel modeloSamplingAssay = new DefaultTableModel();
	    /** Agrego las cabeceras para la tabla Sampling and Assay*/
	    modeloSamplingAssay.addColumn("# Sample");
	    modeloSamplingAssay.addColumn("From");
	    modeloSamplingAssay.addColumn("To");
	    modeloSamplingAssay.addColumn("Mass");
	    modeloSamplingAssay.addColumn("QAQC");
	    /****************** instancio el modeloSamplingAssay a la tablaSampling ****************/
	    tablaSamples = new BasicTable(modeloSamplingAssay);
	    tablaSamples.setCellEditor(new DefaultCellEditor(texto));
	    id[0] = new Vector();
	    id[0].removeAllElements();
	    String consultaSamples = " SELECT idsample,samples.iddrill,samples.idqaqc, upper(samplenumber) as samplenumber,\"from\", \"to\", qaqc_tabs.name as qaqc, mass " + " FROM drilling.samples,tabs.qaqc_tabs " + " WHERE samples.iddrill = " + iddrill + " AND qaqc_tabs.idqaqc = samples.idqaqc " + " AND samples.estado <> '*' " + " ORDER BY \"from\",\"to\"";
	    //System.out.println("consultaSamples--> "+ consultaSamples);
	    boolean band = false;
	    Samples = org.digitall.lib.sql.LibSQL.exQuery(consultaSamples);
	    while (Samples.next()) {
		band = true;
		Vector fila = new Vector();
		fila.add(new String(Samples.getString("samplenumber")));
		fila.add(new Double(Samples.getDouble("from")));
		fila.add(new Double(Samples.getDouble("to")));
		fila.add(Samples.getString("mass"));
		fila.add(new String(Samples.getString("qaqc")));
		id[0].add(new Integer(Samples.getInt("idsample")));
		modeloSamplingAssay.addRow(fila);
	    }
	    TableColumnModel sampleModel = tablaSamples.getColumnModel();
	    TableColumn sampleColumn = sampleModel.getColumn(modeloSamplingAssay.getColumnCount() - 1);
	    sampleColumn.setCellEditor(new DefaultCellEditor(qaqcCombo));
	    tablaSamples.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent key) {
			}

			public void keyPressed(KeyEvent key) {
			}

			public void keyReleased(KeyEvent key) {
			    //System.out.println(tabla.getSelectedRow() + "," + tabla.getSelectedColumn());
			    //System.out.println("ma ver: "+tablaSamples.getValueAt(tablaSamples.getSelectedRow(),tablaSamples.getSelectedColumn()));
			}

		    });
	    tablaSamples.addMouseListener(new MouseListener() {

			public void mouseExited(MouseEvent me) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mousePressed(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			}

			public void mouseReleased(MouseEvent me) {
			    //System.out.println("qaqc name: " + tablaSamples.getValueAt(tablaSamples.getSelectedRow(),4));
			    //String qaqcName = tablaSamples.getValueAt(tablaSamples.getSelectedRow(),4).toString();
			    idSample = id[0].elementAt(tablaSamples.getSelectedRow()).toString();
			    btnDelete.setEnabled(true);
			}

		    });
	    tablaSamples.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    tablaSamples.getTableHeader().setReorderingAllowed(false);
	    tablaSamples.getTableHeader().setResizingAllowed(true);
	    tablaSamples.setAutoResizeMode(BasicTable.AUTO_RESIZE_OFF);
	    if (band) {
		Samples.previous();
		newFromSample = Samples.getDouble("To");
	    } else {
		newFromSample = 0;
	    }
	    double To = newFromSample + 1;
	    tfTo.setText("0" + To);
	    tfFrom.setText("" + newFromSample);
	    spSampling.getViewport().add(tablaSamples, null);
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	idSample = "";
	btnDelete.setEnabled(false);
    }

    private void cargaQaqcCombo() {
	String consultaCombo = "SELECT idqaqc, name FROM tabs.qaqc_tabs WHERE estado <> '*' AND idqaqc > 0 ";
	ResultSet combo = org.digitall.lib.sql.LibSQL.exQuery(consultaCombo);
	try {
	    while (combo.next()) {
		qaqcCombo.addItem(combo.getString("name"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private void createSQL(Vector[] p0) {
	String[] SQL = new String[3];
	for (int rows = 0; rows < tablaSamples.getRowCount(); rows++) {
	    SQL[0] = "UPDATE drilling.samples SET ";
	    SQL[0] += " samplenumber = '" + tablaSamples.getValueAt(rows, 0) + "', " + " \"from\" = '" + tablaSamples.getValueAt(rows, 1) + "', " + " \"to\" = '" + tablaSamples.getValueAt(rows, 2) + "', " + " idqaqc = (Select idqaqc From tabs.qaqc_tabs Where name = '" + tablaSamples.getValueAt(rows, tablaSamples.getColumnCount() - 1) + "')" + ", mass = '" + tablaSamples.getValueAt(rows, 3) + "' WHERE iddrill = " + iddrill + " AND idsample = " + id[0].elementAt(rows);
	    LibSQL.exActualizar('a', SQL[0]);
	}
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	String deleteRow = "UPDATE drilling.samples SET estado = '*'  WHERE idsample = " + id[0].elementAt(tablaSamples.getSelectedRow());
	if (LibSQL.exActualizar('m', deleteRow)) {
	    org.digitall.lib.components.Advisor.messageBox("Delete success!", "Message");
	    cargaCols();
	}
    }

    private void cargaQualityControl() {
	String consulta = "SELECT qualitycontrol FROM drilling.projects WHERE idproject = " + idproject;
	taQaqcDescription.setText(org.digitall.lib.sql.LibSQL.getCampo(consulta));
    }

}
