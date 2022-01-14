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
 * PanelDrillData.java
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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumn;

import org.digitall.common.drilling.ColorColumnRenderer;
import org.digitall.common.drilling.DrillTableModel;
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

public class PanelDrillData extends BasicContainerPanel {

    private BasicLabel jLabel2 = new BasicLabel();
    private BasicLabel jLabel3 = new BasicLabel();
    private BasicLabel jLabel4 = new BasicLabel();
    private BasicLabel lblFrom = new BasicLabel();
    private BasicLabel lblTo = new BasicLabel();
    private BasicTextField jTextField1 = new BasicTextField();
    private BasicTextField jTextField2 = new BasicTextField();
    private BasicTextField jTextField3 = new BasicTextField();
    private BasicTextField texto = new BasicTextField();
    private JDecEntry tfFrom = new JDecEntry();
    private JDecEntry tfTo = new JDecEntry();
    private UnAssignButton bNew = new UnAssignButton(true);
    private AcceptButton bUpdate = new AcceptButton();
    private DeleteButton bDelete = new DeleteButton();
    private BasicCombo qaqcCombo = new BasicCombo();
    private Vector types = new Vector();
    private BasicScrollPane jScrollPane1 = new BasicScrollPane();
    private BasicTable tabla = new BasicTable();
    private Vector id[] = new Vector[4];
    private int[] cantCols = new int[3];
    private ResultSet[] resul = new ResultSet[3];
    private ResultSetMetaData[] rmd = new ResultSetMetaData[3];
    private ResultSet samples;
    private String idproject = "", iddrill = "", idDepthInt = "";
    private double newFrom = 0;
    String seccion[];

    public PanelDrillData(String _idproject, String _iddrill) {
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
	this.setSize(new Dimension(715, 271));
	this.setBounds(new Rectangle(5, 5, 715, 265));
	this.setBounds(new Rectangle(5, 5, 715, 265));
	jScrollPane1.setBounds(new Rectangle(5, 25, 700, 210));
	bDelete.setBounds(new Rectangle(5, 240, 40, 25));
	bDelete.setMnemonic('N');
	bDelete.setFont(new Font("Dialog", 1, 10));
	bDelete.setSize(new Dimension(40, 25));
	bDelete.setToolTipText("Delete");
	bDelete.setHorizontalTextPosition(SwingConstants.LEFT);
	bDelete.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnDelete_actionPerformed(e);
		    }

		});
	this.add(bDelete, null);
	this.add(lblTo, null);
	this.add(lblFrom, null);
	this.add(tfTo, null);
	this.add(tfFrom, null);
	this.add(bNew, null);
	this.add(jTextField3, null);
	this.add(jLabel4, null);
	this.add(jTextField2, null);
	this.add(jLabel3, null);
	this.add(jTextField1, null);
	this.add(jLabel2, null);
	this.add(jScrollPane1, null);
	this.add(bUpdate, null);
	texto.addKeyListener(new decListenTable());
	tfFrom.setBounds(new Rectangle(275, 250, 45, 15));
	tfFrom.setForeground(Color.blue);
	tfFrom.setFont(new Font("Dialog", 1, 11));
	tfTo.setBounds(new Rectangle(335, 250, 45, 15));
	tfTo.setForeground(Color.red);
	tfTo.setFont(new Font("Dialog", 1, 11));
	lblFrom.setText("From:");
	lblFrom.setBounds(new Rectangle(275, 240, 45, 10));
	lblFrom.setFont(new Font("Dialog", 1, 10));
	lblFrom.setHorizontalAlignment(SwingConstants.LEFT);
	lblTo.setText("To:");
	lblTo.setBounds(new Rectangle(335, 240, 45, 10));
	lblTo.setFont(new Font("Dialog", 1, 10));
	lblTo.setHorizontalAlignment(SwingConstants.LEFT);
	bUpdate.setBounds(new Rectangle(665, 240, 40, 25));
	bUpdate.setMnemonic('U');
	bUpdate.setFont(new Font("Dialog", 1, 10));
	bUpdate.setToolTipText("Update");
	bUpdate.setSize(new Dimension(40, 25));
	bUpdate.setHorizontalTextPosition(SwingConstants.LEFT);
	bUpdate.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bUpdate_actionPerformed(e);
		    }

		});
	jLabel2.setText("Depth Interval");
	jLabel2.setBounds(new Rectangle(570, 5, 110, 15));
	jLabel2.setFont(new Font("Dialog", 1, 10));
	jTextField1.setBounds(new Rectangle(510, 5, 55, 15));
	jTextField1.setEditable(false);
	jTextField1.setBackground(Color.white);
	jLabel3.setText("Vein Mineralogy (%)");
	jLabel3.setBounds(new Rectangle(120, 5, 105, 15));
	jLabel3.setFont(new Font("Dialog", 1, 10));
	jTextField2.setBounds(new Rectangle(60, 5, 55, 15));
	jTextField2.setEditable(false);
	jTextField2.setBackground(Color.cyan);
	jLabel4.setText("WallAlt'n (w) 1-2-3 (s)");
	jLabel4.setBounds(new Rectangle(350, 5, 125, 15));
	jLabel4.setFont(new Font("Dialog", 1, 10));
	jTextField3.setBounds(new Rectangle(290, 5, 55, 15));
	jTextField3.setEditable(false);
	jTextField3.setBackground(Color.yellow);
	bNew.setBounds(new Rectangle(395, 240, 40, 25));
	bNew.setMnemonic('N');
	bNew.setFont(new Font("Dialog", 1, 10));
	bNew.setSize(new Dimension(40, 25));
	bNew.setToolTipText("New");
	bNew.setHorizontalTextPosition(SwingConstants.LEFT);
	bNew.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			New_actionPerformed(e);
		    }

		});
	cargaCols();
    }

    private void cargaCols() {
	try {
	    DrillTableModel modelo = new DrillTableModel();
	    types.removeAllElements();
	    //idmin_dep_int
	    //idalt_dep_int
	    //idsample
	    seccion = new String[] { "drilling.mineralization_depth_interval", "drilling.alteration_depth_interval", "drilling.depth_interval" };
	    /***** consulta para obtener la cabecera de las mineralizaciones permitidas *********/
	    String ConsultaMin = "SELECT idmin_tab, name FROM tabs.mineralization_tabs WHERE idmin_tab IN " + "	(SELECT idmin_tab FROM drilling.allowed_mineralizations WHERE idproject = " + idproject + " AND status <> '*' ) " + " ORDER BY idmin_tab";
	    //System.out.println("mineralizaciones----> "+ConsultaMin);
	    resul[0] = org.digitall.lib.sql.LibSQL.exQuery(ConsultaMin);
	    rmd[0] = resul[0].getMetaData();
	    cantCols[0] = 0;
	    while (resul[0].next()) {
		modelo.addColumn(resul[0].getString("name"));
		/** Agrego las mineralizaciones permitidas al modelo*/
		types.addElement(new Integer(Types.DOUBLE));
		cantCols[0]++;
	    }
	    // consulta para obtener la cabecera de las alteraciones permitidas
	    String ConsultaAlt = "SELECT idalt_tab, name FROM tabs.alteration_tabs WHERE idalt_tab IN " + "	(SELECT idalt_tab FROM drilling.allowed_alterations WHERE idproject = " + idproject + " AND status <> '*' ) " + "order by idalt_tab;";
	    resul[1] = org.digitall.lib.sql.LibSQL.exQuery(ConsultaAlt);
	    rmd[1] = resul[1].getMetaData();
	    cantCols[1] = 0;
	    while (resul[1].next()) {
		modelo.addColumn(resul[1].getString("name"));
		/** Agrego las Alteraciones permitidas al modelo*/
		types.addElement(new Integer(Types.DOUBLE));
		cantCols[1]++;
	    }
	    cantCols[2] = 0;
	    /**Agrego las columnas para: From y To  */
	    modelo.addColumn("Vein\nDensity");
	    types.add(new Integer(Types.DOUBLE));
	    modelo.addColumn("From");
	    types.add(new Integer(Types.DOUBLE));
	    modelo.addColumn("To");
	    types.add(new Integer(Types.DOUBLE));
	    //cantCols[2]=2;
	    cantCols[2] = 3;
	    tabla = new BasicTable(modelo);
	    /** Agrego el modelo a la tabla*/
	    tabla.setCellEditor(new DefaultCellEditor(texto));
	    /** Seteo las cabeceras de la tabla distinguiendo por colores las mineralogias, alteraciones y Sampling*/
	    for (int i = 0; i < tabla.getColumnCount(); i++) {
		TableColumn tm = tabla.getColumnModel().getColumn(i);
		if (i < cantCols[0]) {
		    tm.setCellRenderer(new ColorColumnRenderer(Color.CYAN, Color.black));
		} else if (i < cantCols[0] + cantCols[1]) {
		    tm.setCellRenderer(new ColorColumnRenderer(Color.YELLOW, Color.black));
		} else if (i < cantCols[0] + cantCols[1] + cantCols[2]) {
		    tm.setCellRenderer(new ColorColumnRenderer(Color.white, Color.black));
		    /*if (i==(cantCols[0] + cantCols[1] + cantCols[2])-2){
                        tm.set
                    }*/
		}
	    }
	    id[0] = new Vector();
	    id[1] = new Vector();
	    id[2] = new Vector();
	    id[0].removeAllElements();
	    id[1].removeAllElements();
	    id[2].removeAllElements();
	    /**
             *
             * Para cargar los datos de la tabla necesito:
             * 1) Seleccionar los intervalos de profundidad del pozo y guardarlos en el vector intervals.-
             * 2) Por cada intervalo de profundidad selecciono el id de
             *    Mineralizaciones y su correspondiente valor, luego guardo en el vector fila solo amount.-
             * 3) Por cada intervalo de profundidad selecciono el id de
             *    alteraciones y su correspondiente valor, luego guardo en el vector fila solo amount.-
             * 4) Seleccionolos valores de: Vein Density, code, From, To y name (de QAQC) y los guardo en el vector fila.-
             * 5) Finalmente agrego la fila al modelo.-
             *
             * */
	    boolean band = false;
	    //System.out.println("consulta A: SELECT iddep_int FROM drilling.depth_interval WHERE iddrill = " + iddrill + " Order By \"from\", \"to\"");
	    ResultSet intervals = org.digitall.lib.sql.LibSQL.exQuery("SELECT iddep_int FROM drilling.depth_interval WHERE iddrill = " + iddrill + " AND estado <> '*' ORDER BY \"from\", \"to\"");
	    while (intervals.next()) {
		Vector fila = new Vector();
		//es un intervalo
		ResultSet mins = org.digitall.lib.sql.LibSQL.exQuery("SELECT idmin_dep_int, amount FROM " + seccion[0] + " WHERE iddrill = " + iddrill + " AND iddep_int = " + intervals.getString("iddep_int") + " AND status <> '*' ORDER BY idall_min");
		while (mins.next()) {
		    id[0].addElement(new Integer(mins.getInt("idmin_dep_int")));
		    fila.add(new Double(mins.getDouble("amount")));
		}
		ResultSet alts = org.digitall.lib.sql.LibSQL.exQuery("SELECT idalt_dep_int, amount FROM " + seccion[1] + " WHERE iddrill = " + iddrill + " AND iddep_int = " + intervals.getString("iddep_int") + " AND status <> '*' ORDER BY idall_alt");
		while (alts.next()) {
		    id[1].addElement(new Integer(alts.getInt("idalt_dep_int")));
		    fila.add(new Double(alts.getDouble("amount")));
		}
		String ConsultaDepthInterval = "SELECT drilling.depth_interval.iddep_int, " + " drilling.depth_interval.veindensity, " + " drilling.depth_interval.from, drilling.depth_interval.to " + " FROM drilling.depth_interval " + " WHERE drilling.depth_interval.iddrill = " + iddrill + " AND drilling.depth_interval.iddep_int = " + intervals.getString("iddep_int") + " AND depth_interval.estado <> '*'" + " ORDER BY \"from\", \"to\"";
		//System.out.println("depth_int: " + ConsultaDepthInterval);
		samples = org.digitall.lib.sql.LibSQL.exQuery(ConsultaDepthInterval);
		if (samples.next()) {
		    band = true;
		    fila.add(new Double(samples.getDouble("veindensity")));
		    fila.add(new Double(samples.getDouble("from")));
		    //modelo.setColFrom(fila.size()-1);
		    fila.add(new Double(samples.getDouble("to")));
		    //modelo.setColTo(fila.size()-1);
		    id[2].add(new Integer(samples.getInt("iddep_int")));
		}
		modelo.addRow(fila);
		//TableColumnModel qaqcModel = tabla.getColumnModel();
		//TableColumn qaqcColumn = qaqcModel.getColumn(modelo.getColumnCount()-1);
		//qaqcColumn.setCellEditor(new DefaultCellEditor(qaqcCombo));
	    }
	    tabla.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent key) {
			}

			public void keyPressed(KeyEvent key) {
			}

			public void keyReleased(KeyEvent key) {
			    //System.out.println(tabla.getSelectedRow() + "," + tabla.getSelectedColumn());
			    //System.out.println(tabla.getValueAt(tabla.getSelectedRow(),tabla.getSelectedColumn()));
			}

		    });
	    tabla.addKeyListener(new decListenTable());
	    tabla.addMouseListener(new MouseListener() {

			public void mouseExited(MouseEvent me) {
			}

			public void mouseEntered(MouseEvent me) {
			}

			public void mousePressed(MouseEvent me) {
			}

			public void mouseClicked(MouseEvent me) {
			    //me.consume();
			}

			public void mouseReleased(MouseEvent me) {
			    //System.out.println(tabla.getSelectedRow() + "," + tabla.getSelectedColumn());
			    //System.out.println(tabla.getValueAt(tabla.getSelectedRow(),tabla.getSelectedColumn()));
			    idDepthInt = id[2].elementAt(tabla.getSelectedRow()).toString();
			    bDelete.setEnabled(true);
			}

		    });
	    tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    tabla.getTableHeader().setReorderingAllowed(false);
	    tabla.getTableHeader().setResizingAllowed(true);
	    tabla.setAutoResizeMode(BasicTable.AUTO_RESIZE_OFF);
	    if (band) {
		newFrom = samples.getDouble("To");
	    } else {
		newFrom = 0;
	    }
	    jScrollPane1.getViewport().add(tabla, null);
	    if (band) {
		newFrom = samples.getDouble("To");
	    } else {
		newFrom = 0;
	    }
	    double To = newFrom + 1;
	    tfTo.setText("0" + To);
	    tfFrom.setText("" + newFrom);
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	idDepthInt = "";
	bDelete.setEnabled(false);
    }

    private char[] separador(int idx) {
	char ret[] = new char[2];
	int type = Integer.parseInt(types.elementAt(idx).toString());
	if ((type == Types.BLOB) || (type == Types.BOOLEAN) || (type == Types.CHAR) || (type == Types.DATE) || (type == Types.VARCHAR) || (type == Types.LONGVARCHAR) || (type == Types.TIME)) {
	    ret[0] = '\"';
	    ret[1] = '\"';
	    return ret;
	} else {
	    ret[0] = '0';
	    ret[1] = ' ';
	    return ret;
	}
    }

    public void createSQL(ResultSetMetaData[] rmd, Vector[] id) {
	String[] SQL = new String[3];
	boolean ok = true;
	for (int rows = 0; rows < tabla.getRowCount(); rows++) {
	    //Update para las mineralizaciones
	    for (int j = 0; j < cantCols[0]; j++) {
		SQL[0] = "UPDATE " + seccion[0] + " SET ";
		SQL[0] += " amount = " + separador(j)[0] + tabla.getValueAt(rows, j) + separador(j)[1] + " WHERE ";
		SQL[0] += " idmin_dep_int = " + id[0].elementAt(rows * cantCols[0] + j);
		//System.out.println("SQL[0]("+ j +")----> "+SQL[0]);
		if (LibSQL.exActualizar('a', SQL[0])) {
		    ok = true;
		} else {
		    ok = false;
		}
	    }
	    //Update para las alteraciones
	    for (int j = cantCols[0]; j < cantCols[0] + cantCols[1]; j++) {
		SQL[1] = "UPDATE " + seccion[1] + " SET ";
		SQL[1] += " amount = " + separador(j)[0] + tabla.getValueAt(rows, j) + separador(j)[1] + " WHERE ";
		SQL[1] += " idalt_dep_int = " + id[1].elementAt(rows * cantCols[1] + (j - cantCols[0]));
		//System.out.println("SQL[1]("+ j +")----> "+SQL[1]);
		if (LibSQL.exActualizar('a', SQL[1])) {
		    ok = true;
		} else {
		    ok = false;
		}
	    }
	    //Update para los intevalos
	    SQL[2] = "UPDATE " + seccion[2] + " SET ";
	    SQL[2] += " \"from\" = " + separador(cantCols[0] + cantCols[1] + 1)[0] + tabla.getValueAt(rows, cantCols[0] + cantCols[1] + 1) + separador(cantCols[0] + cantCols[1] + 1)[1] + ", \"to\" = " + separador(cantCols[0] + cantCols[1] + 2)[0] + tabla.getValueAt(rows, cantCols[0] + cantCols[1] + 2) + separador(cantCols[0] + cantCols[1] + 2)[1] + ", veindensity = " + separador(cantCols[0] + cantCols[1])[0] + tabla.getValueAt(rows, cantCols[0] + cantCols[1]) + separador(cantCols[0] + cantCols[1])[1] + " WHERE iddrill = " + iddrill + " AND iddep_int = " + id[2].elementAt(rows);
	    //System.out.println("SQL[2]("+ rows +")----> "+SQL[2]);
	    if (LibSQL.exActualizar('a', SQL[2])) {
		ok = true;
	    } else {
		ok = false;
	    }
	}
    }

    private void bUpdate_actionPerformed(ActionEvent e) {
	createSQL(rmd, id);
	cargaCols();
    }

    private void New_actionPerformed(ActionEvent e) {
	/**
       *    Antes de ingresar un intervalo pregunto hasta donde llegará la profundidad (campo To)
       * */
	createSQL(rmd, id);
	double newTo = Double.parseDouble("0" + tfTo.getText().trim());
	newFrom = Double.parseDouble("0" + tfFrom.getText().trim());
	String idDepth_int = org.digitall.lib.sql.LibSQL.getCampo("SELECT MAX(iddep_int) + 1 FROM drilling.depth_interval WHERE iddrill = " + iddrill);
	String consultaInsert = "INSERT INTO drilling.depth_interval VALUES ( " + idDepth_int + ", " + iddrill + " ,0" + newFrom + ", 0" + newTo + ", '', 0, '')";
	//System.out.println("insert: "+consultaInsert);
	if (LibSQL.exActualizar('a', consultaInsert)) {
	    cargaCols();
	} else {
	    org.digitall.lib.components.Advisor.messageBox("Se produjo un error en la consulta", "Error");
	}
	bDelete.setEnabled(false);
	idDepthInt = "";
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
	String deleteMinRow = " UPDATE " + seccion[0] + " SET status = '*' " + " WHERE iddrill = " + iddrill + " AND iddep_int = " + id[2].elementAt(tabla.getSelectedRow()) + ";";
	//Consulta para borrar las mineralizaciones de la fila seleccionada
	String deleteAltRow = " UPDATE " + seccion[1] + " SET status = '*' " + " WHERE iddrill = " + iddrill + " AND iddep_int = " + id[2].elementAt(tabla.getSelectedRow()) + ";";
	//Consulta para borrar las alteraciones de la fila seleccionada
	String deleteDepIntRow = " UPDATE " + seccion[2] + " SET estado = '*' " + " WHERE iddrill = " + iddrill + " AND iddep_int = " + id[2].elementAt(tabla.getSelectedRow()) + ";";
	//Consulta para borrar el intervalo de la fila seleccionada
	if (LibSQL.exActualizar('m', deleteMinRow + deleteAltRow + deleteDepIntRow)) {
	    org.digitall.lib.components.Advisor.messageBox("Record deleted", "Message");
	    cargaCols();
	}
    }

}
