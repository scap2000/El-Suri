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
 * FeesGrid.java
 *
 * */
package org.digitall.apps.taxes.interfases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.sql.Types;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.digitall.apps.taxes.classes.Cuota;
import org.digitall.apps.taxes.classes.CuotasList;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JDecEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTable;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.data.Format;
import org.digitall.lib.data.listeners.decListen;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class FeesGrid extends BasicPanel {

    private static final int TypesCUOTA = 1017;
    private BasicPanel jpCenter = new BasicPanel();
    private boolean sort = false;
    private String functionName = "";
    private String params = "";
    private int resultTotalQty = 0;
    private boolean firstTime = true;
    protected int[] columnSizes;
    private Vector headerTable = new Vector();
    private BasicScrollPane scrollPane = new BasicScrollPane();
    private Vector editableColumns = new Vector();
    private Vector columnEditors = new Vector();
    private int indexCheckColumn = 0;
    private DefaultTableModel tableModel = new DefaultTableModel();
    public BasicTable table = new BasicTable(tableModel) {

	    /**
	     * Note that the data/cell address is constant, no matter where the cell appears onscreen.
	     */
	    public boolean isCellEditable(int row, int col) {
		if (editableColumns.contains(new Integer(col))) {
		    return true;
		} else {
		    return false;
		}
	    }

	    public Class getColumnClass(int c) {
		try {
		    return getValueAt(0, c).getClass();
		} catch (NullPointerException x) {
		    return null;
		}
	    }

	}
    ;
    private String title;
    private BasicPanel jpSouth = new BasicPanel();
    private ColumnHeaderListener sortListener = new ColumnHeaderListener();
    private boolean hasListener = false;
    private BasicLabel lblAmount = new BasicLabel();
    private BasicTextField tfAmount = new BasicTextField();
    private boolean byQuery = false;
    private int selectionMode = ListSelectionModel.SINGLE_SELECTION;
    private ActionListener monthlyListener;
    private CuotasList feesVector;
    private Vector actualRow;
    private int checkedRow = -1;
    private int checkedColumn = -1;
    private BasicLabel lblTotal = new BasicLabel();
    private BasicLabel lblDiscount = new BasicLabel();
    private double total = 0.0;
    private double discount = 0.0;
    private JDecEntry tfDiscount = new JDecEntry();
    private JDecEntry tfTotal = new JDecEntry();

    public FeesGrid() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void removeEditableColumn(int _column) {
	if (editableColumns.contains(new Integer(_column))) {
	    columnEditors.removeElementAt(editableColumns.indexOf(new Integer(_column)));
	    editableColumns.remove(new Integer(_column));
	}
    }

    private void jbInit() throws Exception {
	setLayout(new BorderLayout());
	jpCenter.setLayout(new BorderLayout());
	table.setVisible(true);
	scrollPane.getViewport().add(table, null);
	jpCenter.add(scrollPane, BorderLayout.CENTER);
	this.add(jpCenter, BorderLayout.CENTER);
	this.add(jpSouth, BorderLayout.SOUTH);
	jpSouth.setLayout(new GridBagLayout());
	lblAmount.setText("Deuda: ");
	lblTotal.setText("Total: ");
	lblDiscount.setText("Descuento: ");
	lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
	tfDiscount.setText("0.0");
	jpSouth.add(lblAmount, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 15, 0, 0), 0, 0));
	jpSouth.add(tfAmount, new GridBagConstraints(2, 0, 1, 1, 0.2, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	jpSouth.add(lblDiscount, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 15, 0, 0), 0, 0));
	jpSouth.add(tfDiscount, new GridBagConstraints(4, 0, 1, 1, 0.2, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	jpSouth.add(lblTotal, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 15, 0, 0), 0, 0));
	jpSouth.add(tfTotal, new GridBagConstraints(6, 0, 2, 1, 0.2, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	this.setPreferredSize(new Dimension(400, 300));
	table.setColumnSelectionAllowed(false);
	table.setRowSelectionAllowed(true);
	table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

							public void valueChanged(ListSelectionEvent e) {
							    if (!e.getValueIsAdjusting()) {
								ListSelectionModel tmodelo = (DefaultListSelectionModel)e.getSource();
								if (!tmodelo.isSelectionEmpty()) {
								    int fila = tmodelo.getMinSelectionIndex();
								    updateDataRow((Vector)tableModel.getDataVector().elementAt(fila));
								}
							    }
							}

						    }
	);
	table.addMouseListener(new MouseAdapter() {

			    public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				    int fila = table.rowAtPoint(e.getPoint());
				    updateDataRow((Vector)tableModel.getDataVector().elementAt(fila));
				} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
				    for (int i = 0; i < table.getColumnCount(); i++) {
					updateDataRow((Vector)tableModel.getDataVector().elementAt(table.rowAtPoint(e.getPoint())));
				    }
				}
			    }

			}
	);
	table.addMouseMotionListener(new MouseMotionAdapter() {

				  public void mouseMoved(MouseEvent e) {
				      //updateDataRow((Vector)tableModel.getDataVector().elementAt(table.rowAtPoint(e.getPoint())));
				  }

			      }
	);
	table.addKeyListener(new KeyAdapter() {

			  public void keyPressed(KeyEvent e) {
			      keyTyped(e, table.getSelectedRow());
			  }

			  public void keyReleased(KeyEvent e) {
			      keyTyped(e, table.getSelectedRow());
			  }

			  public void keyTyped(KeyEvent e, int _selectedRow) {
			      try {
				  if (_selectedRow != -1) {
				      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					  tableModel.setValueAt(true, _selectedRow, indexCheckColumn);
					  calculate();
					  e.consume();
				      }
				      if (table.isEditing()) {
					  table.getCellEditor().stopCellEditing();
				      } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					  //System.out.println(table.getColumnClass(table.getSelectedColumn()));
					  //table.setValueAt("?", _selectedRow, indexCheckColumn);
				      }
				  }
				  if (table.getSelectedRow() != -1) {
				      updateDataRow((Vector)tableModel.getDataVector().elementAt(table.getSelectedRow()));
				  }
			      } catch (ArrayIndexOutOfBoundsException x) {
				  e.consume();
			      }
			  }

		      }
	);
	tfAmount.setEnabled(false);
	monthlyListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    Cuota fee = (Cuota)e.getSource();
		    if (fee.isSelected()) {
			//tengo que tildar desde aquí hacia atrás
			if (fee.getIdTipoCuota() == 0) {
			    for (int i = 0; i <= checkedRow; i++) {
				for (int j = 0; j < table.getColumnCount(); j++) {
				    Cuota _fee = (Cuota)table.getValueAt(i, j);
				    if (_fee.isEnabled() && !_fee.isSelected()) {
					_fee.setSelected(true);
				    }
				}
			    }
			} else {
			    int i = 0;
			    int j = 1;
			    boolean found = false;
			    while (i < table.getRowCount() && !found) {
				int anual = 0;
				//para saber si paga un año completo deberá ser igual a seis
				while (j < table.getColumnCount() && !found) {
				    found = (i == checkedRow && j == checkedColumn);
				    Cuota _fee = (Cuota)table.getValueAt(i, j);
				    if (_fee.isEnabled() && !_fee.isSelected()) {
					_fee.setSelected(true);
				    }
				    if (_fee.isEnabled() && _fee.isSelected()) {
					anual++;
				    }
				    j++;
				}
				//System.out.println("Anual: " + anual);
				((Cuota)table.getValueAt(i, 0)).setSelected(anual == 6);
				i++;
				j = 1;
			    }
			}
		    } else {
			//!isSelected()
			if (fee.getIdTipoCuota() == 0) {
			    for (int i = checkedRow; i < table.getRowCount(); i++) {
				for (int j = 0; j < table.getColumnCount(); j++) {
				    Cuota _fee = (Cuota)table.getValueAt(i, j);
				    if (_fee.isEnabled() && _fee.isSelected()) {
					_fee.setSelected(false);
				    }
				}
			    }
			} else {
			    //tengo que destildar desde aquí para adelante
			    int i = checkedRow;
			    int j = checkedColumn + 1;
			    while (i < table.getRowCount()) {
				while (j < table.getColumnCount()) {
				    Cuota _fee = (Cuota)table.getValueAt(i, j);
				    if (_fee.isEnabled() && _fee.isSelected()) {
					_fee.setSelected(false);
					repaint();
				    }
				    j++;
				}
				((Cuota)table.getValueAt(i, 0)).setSelected(false);
				i++;
				j = 0;
			    }
			}
		    }
		    repaint();
		    int i = 0;
		    int j = 0;
		    total = 0;
		    while (i < table.getRowCount()) {
			double deduccion = 0.0;
			while (j < table.getColumnCount()) {
			    Cuota _fee = (Cuota)table.getValueAt(i, j);
			    if (_fee.isEnabled() && _fee.isSelected()) {
				if (_fee.getIdTipoCuota() != 0) {
				    total += _fee.getMonto() * (1.0 - deduccion);
				} else {
				    if (_fee.getAnio() >= Integer.parseInt(Environment.currentYear) && !LibSQL.getBoolean("impuestos.getvencida", _fee.getIdTipoImpuesto() + ",0")) {
					deduccion = LibSQL.getDouble("impuestos.gettasaanual", _fee.getAnio() + "," + _fee.getIdTipoImpuesto());
				    }
				}
			    }
			    j++;
			}
			i++;
			j = 0;
		    }
		    tfAmount.setText(Format.money(total));
		    discount = Double.parseDouble("0" + tfDiscount.getText().trim()) / 100.0;
		    tfTotal.setText("" + Format.toDouble(total * (1.0 - discount)));
		}

	    }
	;
	tfDiscount.addKeyListener(new KeyAdapter() {

			       public void keyReleased(KeyEvent e) {
				   char caracter = e.getKeyChar();
				   if ((caracter != KeyEvent.VK_ENTER)) {
				       discount = Double.parseDouble("0" + tfDiscount.getText().trim()) / 100.0;
				       tfTotal.setText("" + Format.toDouble(total * (1.0 - discount)));
				   }
			       }

			   }
	);
	tfTotal.addKeyListener(new KeyAdapter() {

			    public void keyReleased(KeyEvent e) {
				char caracter = e.getKeyChar();
				if ((caracter != KeyEvent.VK_ENTER)) {
				    discount = ((total - Double.parseDouble("0" + tfTotal.getText().trim())) / total);
				    tfDiscount.setText("" + Format.toDouble(discount * 100.0));
				}
			    }

			}
	);
    }

    public double getDiscount() {
	return discount;
    }

    public void addEditableColumn(int _column, int _type) {
	if (!editableColumns.contains(_column)) {
	    editableColumns.add(_column);
	    columnEditors.add(new Object[] { _column, _type });
	}
    }

    public void setGrid(CuotasList _feesVector) {
	tfAmount.setText(Format.money(0.0));
	tfTotal.setText(Format.money(0.0));
	tfDiscount.setText("0.0");
	tableModel.setColumnCount(0);
	tfAmount.setText(Format.money(0));
	feesVector = _feesVector;
	if (tableModel.getColumnCount() == 0) {
	    tableModel.addColumn("Año/Monto anual");
	    for (int i = 0; i < 6; i++) {
		tableModel.addColumn(i + 1 + "º bimestre");
	    }
	}
	for (int i = 0; i < 7; i++) {
	    addEditableColumn(i, TypesCUOTA);
	}
	tableModel.setRowCount(0);
	int index = 0;
	boolean eximido = false;
	while (index < _feesVector.size() && !eximido) {
	    actualRow = new Vector();
	    double montoAnual = 0.0;
	    double cuotaAnual = 0.0;
	    Cuota anualFee = new Cuota();
	    for (int i = index; i < index + 6; i++) {
		if (i == index) {
		    anualFee.addActionListener(monthlyListener);
		    anualFee.setAnio(((Cuota)_feesVector.elementAt(i)).getAnio());
		    anualFee.setIdTipoCuota(0);
		}
		boolean anualParcial = false;
		if (i < _feesVector.size()) {
		    Cuota fee = (Cuota)_feesVector.elementAt(i);
		    cuotaAnual += fee.getCuota();
		    anualParcial = fee.isPagada();
		    if (!fee.isPagada()) {
			montoAnual += fee.getMonto();
		    }
		    fee.addActionListener(monthlyListener);
		    actualRow.addElement(fee);
		} else {
		    actualRow.addElement("");
		}
		if (anualParcial) {
		    anualFee.setEnabled(false);
		} else {
		    anualFee.setMonto(montoAnual);
		    anualFee.setCuota(cuotaAnual);
		}
	    }
	    actualRow.insertElementAt(anualFee, 0);
	    index += 6;
	    tableModel.addRow(actualRow);
	}
	table.setSelectionMode(selectionMode);
	scrollPane.getViewport().add(table);
	addCellEditors();
	table.setAutoResizeMode(BasicTable.AUTO_RESIZE_OFF);
	table.getColumnModel().getColumn(0).setResizable(false);
	table.getColumnModel().getColumn(0).setPreferredWidth(150);
	for (int i = 0; i < 6; i++) {
	    table.getColumnModel().getColumn(i + 1).setResizable(false);
	    table.getColumnModel().getColumn(i + 1).setPreferredWidth(90);
	}
    }

    private void addCellEditors() {
	for (int i = 0; i < columnEditors.size(); i++) {
	    Object[] editors = (Object[])columnEditors.elementAt(i);
	    {
		int _column = new Integer(editors[0].toString());
		int _type = new Integer(editors[1].toString());
		switch (_type) {
		    case Types.DOUBLE :
			setCellEditor(new DefaultCellEditor(new JDecEntry()), _column);
			break;
		    case Types.BOOLEAN :
			JCheckBox jCheck = new JCheckBox();
			jCheck.setHorizontalAlignment(JCheckBox.CENTER);
			setCellEditor(new DefaultCellEditor(jCheck), _column);
			break;
		    case TypesCUOTA :
			table.getColumnModel().getColumn(_column).setCellRenderer(new FeeCellRenderer());
			table.getColumnModel().getColumn(_column).setCellEditor(new FeeCellEditor());
			break;
		    default :
			setCellEditor(new DefaultCellEditor(new JTextField()), _column);
			break;
		}
	    }
	}
    }

    private void setCellEditor(DefaultCellEditor _editor, int _column) {
	try {
	    table.getColumnModel().getColumn(_column).setCellEditor(_editor);
	} catch (ArrayIndexOutOfBoundsException ignore) {
	    ignore.printStackTrace();
	}
    }

    public void setCellEditor(int _type, int _column) {
	addEditableColumn(_column + 1, _type);
    }

    public double getAmount() {
	return Format.toDoubleWithMonetarySign(tfAmount.getText()).doubleValue();
    }

    public void removeTable() {
	scrollPane.getViewport().remove(table);
	tableModel.setColumnCount(0);
	tableModel.setRowCount(0);
	if (feesVector != null) {
	    feesVector.removeAllElements();
	}
    }

    /*****************************SIN USAR*******************************/
    public void setLayout(LayoutManager _layout) {
	/*patch
	 * hago un override del layout que me manden
	 * */
	super.setLayout(new BorderLayout());
    }

    public int getResultTotalQty() {
	return resultTotalQty;
    }

    public void columnSizes(int[] _columnSizes) {
	columnSizes = _columnSizes;
    }

    public int getIndexCheckColumn() {
	return indexCheckColumn;
    }

    private void sortAllRowsBy(DefaultTableModel _model, int _index, boolean _ascending) {
	Vector data = _model.getDataVector();
	Collections.sort(data, new ColumnSorter(_index, _ascending));
	_model.fireTableStructureChanged();
	removeColumns();
    }
    // This comparator is used to sort vectors of data
    //funcion que te devuelve un vector (registro) 

    private void setStatusResultText(String _resultCount, String _resultStart, String _resultEnd) {
	String statusGridResultMask = Environment.lang.getProperty("StatusGridCancelResult");
	if (!_resultCount.equals("0")) {
	    statusGridResultMask = Environment.lang.getProperty("StatusGridResultMask");
	    statusGridResultMask = statusGridResultMask.replaceFirst("#resultCount#", _resultCount);
	    statusGridResultMask = statusGridResultMask.replaceFirst("#start#", _resultStart);
	    statusGridResultMask = statusGridResultMask.replaceFirst("#end#", _resultEnd);
	}
    }

    /**
     * Los indices de cualquier tabla empiezan en 0, se debe especificar de atras hacia adelante
     */
    private void removeColumns() {
	removeSortListener();
	try {
	    /**REMOVER*/
	    for (int i = headerTable.size() - 1; i >= 0; i--) {
		if (headerTable.elementAt(i).equals("*")) {
		    table.removeColumn(table.getColumnModel().getColumn(i + 1));
		}
	    }
	    /**REDIMENSIONAR*/
	    table.setAutoResizeMode(BasicTable.AUTO_RESIZE_OFF);
	    table.getColumnModel().getColumn(getIndexCheckColumn()).setPreferredWidth(34);
	    table.getColumnModel().getColumn(getIndexCheckColumn()).setResizable(false);
	    for (int i = 0; i < columnSizes.length; ++i) {
		TableColumn col = table.getColumnModel().getColumn(i + 1);
		if (columnSizes[i] == 0) {
		    col.setResizable(false);
		}
		col.setPreferredWidth(columnSizes[i]);
	    }
	} catch (NullPointerException x) {
	    Advisor.messageBox(x.getMessage(), "ERROR columnsRemove: ");
	}
	addSortListener();
	addCellEditors();
    }

    public void setSelectionMode(int _mode) {
	selectionMode = _mode;
	table.setSelectionMode(selectionMode);
    }

    public void setParams(String _functionName, String _params, Vector _headerTable) {
	functionName = _functionName;
	params = _params;
	headerTable = _headerTable;
    }

    public void unsetQuery() {
	byQuery = false;
    }

    public BasicTable getTable() {
	return table;
    }

    public void changeColumnCount() {
	tableModel.setColumnCount(0);
	firstTime = true;
    }

    public Vector getSelectedsID() {
	Vector seleccionados = new Vector();
	for (int i = 0; i < table.getRowCount(); i++) {
	    String selected = table.getValueAt(i, getIndexCheckColumn()).toString();
	    if (selected.equalsIgnoreCase("true")) {
		seleccionados.addElement(((Vector)tableModel.getDataVector().elementAt(i)).elementAt(1));
		//        return table.getValueAt(rows[0], 0).toString();
	    }
	}
	return seleccionados;
    }

    public Vector getMultiSelectionSelectedsID() {
	Vector seleccionados = new Vector();
	int[] rows = table.getSelectedRows();
	for (int i = 0; i < rows.length; i++) {
	    seleccionados.addElement(((Vector)tableModel.getDataVector().elementAt(rows[i])).elementAt(1));
	    //System.out.println(((Vector)tableModel.getDataVector().elementAt(rows[i])).elementAt(1));
	}
	return seleccionados;
    }

    public Vector getSelectedsVector() {
	Vector<Vector> seleccionados = new Vector<Vector>();
	for (int i = 0; i < table.getRowCount(); i++) {
	    String selected = table.getValueAt(i, getIndexCheckColumn()).toString();
	    if (selected.equalsIgnoreCase("true")) {
		seleccionados.addElement(getFakeVector((Vector)tableModel.getDataVector().elementAt(i)));
	    }
	}
	return seleccionados;
    }

    public void updateDataRow(Vector _vector) {
	/*dataRow.removeAllElements();
	dataRow.addAll(getFakeVector(_vector));*/
    }

    public Vector getFakeVector(Vector _vector) {
	Vector fakeDataRow = new Vector(_vector);
	boolean value = new Boolean(fakeDataRow.elementAt(0).toString());
	fakeDataRow.remove(0);
	fakeDataRow.add(value);
	return fakeDataRow;
    }

    public Vector getSelectedsValuesAt(int _index) {
	Vector seleccionados = new Vector();
	for (int i = 0; i < table.getRowCount(); i++) {
	    String selected = table.getValueAt(i, getIndexCheckColumn()).toString();
	    if (selected.equalsIgnoreCase("true")) {
		seleccionados.addElement(((Vector)tableModel.getDataVector().elementAt(i)).elementAt(_index + 1));
	    }
	}
	return seleccionados;
    }

    public Vector getSelectedsValuesInTableAt(int _columna) {
	Vector seleccionados = new Vector();
	if (_columna < table.getColumnCount()) {
	    for (int i = 0; i < table.getRowCount(); i++) {
		String selected = table.getValueAt(i, getIndexCheckColumn()).toString();
		if (selected.equalsIgnoreCase("true")) {
		    seleccionados.addElement(table.getValueAt(i, _columna));
		    //        return table.getValueAt(rows[0], 0).toString();
		}
	    }
	} else {
	    System.out.println("La columna indicada supera la cantidad de columnas de la Grilla");
	}
	return seleccionados;
    }

    public void selectAllItems(boolean _value) {
	for (int i = 0; i < table.getRowCount(); i++) {
	    table.setValueAt(_value, i, getIndexCheckColumn());
	}
    }

    public void setTitle(String _title) {
	title = _title;
	this.setBorder(BorderPanel.getBorderPanel(title));
    }

    private void removeSortListener() {
	if (hasListener) {
	    table.getTableHeader().removeMouseListener(sortListener);
	    hasListener = false;
	}
    }

    private void addSortListener() {
	if (!hasListener) {
	    table.getTableHeader().addMouseListener(sortListener);
	    hasListener = true;
	}
    }

    private void btnCheckAll_actionPerformed(ActionEvent e) {
	//selectAllItems(btnCheckAll.isSelected());
    }

    public boolean isCurrentRowSelected() {
	return new Boolean(table.getValueAt(table.getSelectedRow(), 0).toString());
    }

    public void calculate() {
    }

    public CuotasList getFeesVector() {
	return feesVector;
    }

    public Vector getFeesToPay() {
	Vector feesToPay = new Vector();
	if (feesVector != null) {
	    for (int i = 0; i < table.getRowCount(); i++) {
		Cuota _anualFee = (Cuota)table.getValueAt(i, 0);
		if (_anualFee.isSelected() && _anualFee.isEnabled()) {
		    feesToPay.add(_anualFee);
		} else {
		    for (int j = 1; j < table.getColumnCount(); j++) {
			Cuota _fee = (Cuota)table.getValueAt(i, j);
			if (_fee.isSelected() && _fee.isEnabled()) {
			    feesToPay.add(_fee);
			}
		    }
		}
	    }
	}
	return feesToPay;
    }

    public class ColumnHeaderListener extends MouseAdapter {

	public void mouseClicked(MouseEvent evt) {
	    BasicTable _table = (BasicTable)((JTableHeader)evt.getSource()).getTable();
	    TableColumnModel colModel = _table.getColumnModel();
	    int vColIndex = colModel.getColumnIndexAtX(evt.getX());
	    // The index of the column whose header was clicked
	    int mColIndex = _table.convertColumnIndexToModel(vColIndex);
	    if (vColIndex == -1) {
		// Return if not clicked on any column header
		return;
	    }
	    Rectangle headerRect = _table.getTableHeader().getHeaderRect(vColIndex);
	    // Determine if mouse was clicked between column heads
	    if (vColIndex == 0) {
		// Hard-coded constant
		headerRect.width -= 3;
	    } else {
		headerRect.grow(-3, 0);
	    }
	    /**
	     * Mouse was clicked between column heads vColIndex is the column head closest to the click vLeftColIndex is the column head to the left of the click
	    **/
	    if (!headerRect.contains(evt.getX(), evt.getY())) {
		int vLeftColIndex = vColIndex;
		if (evt.getX() < headerRect.x) {
		    vLeftColIndex--;
		}
	    } else {
		sort = !sort;
		sortAllRowsBy(tableModel, mColIndex, sort);
	    }
	}

    }

    public class ColumnSorter implements Comparator {

	int colIndex;
	boolean ascending;

	ColumnSorter(int colIndex, boolean ascending) {
	    this.colIndex = colIndex;
	    this.ascending = ascending;
	}

	public int compare(Object a, Object b) {
	    Vector v1 = (Vector)a;
	    Vector v2 = (Vector)b;
	    Object o1 = v1.get(colIndex);
	    Object o2 = v2.get(colIndex);
	    // Treat empty strains like nulls
	    if (o1 instanceof String && ((String)o1).length() == 0) {
		o1 = null;
	    }
	    if (o2 instanceof String && ((String)o2).length() == 0) {
		o2 = null;
	    }
	    // Sort nulls so they appear last, regardless
	    // of sort order
	    if (o1 == null && o2 == null) {
		return 0;
	    } else if (o1 == null) {
		return 1;
	    } else if (o2 == null) {
		return -1;
	    } else if (o1 instanceof Comparable) {
		if (ascending) {
		    return ((Comparable)o1).compareTo(o2);
		} else {
		    return ((Comparable)o2).compareTo(o1);
		}
	    } else {
		if (ascending) {
		    return o1.toString().compareTo(o2.toString());
		} else {
		    return o2.toString().compareTo(o1.toString());
		}
	    }
	}

    }

    class FeeCellEditor extends AbstractCellEditor implements TableCellEditor {

	protected Cuota fee;

	public FeeCellEditor() {

	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	    if (Cuota.class.isInstance(value)) {
		fee = (Cuota)value;
	    }
	    checkedRow = row;
	    checkedColumn = column;
	    return fee;
	}

	public Object getCellEditorValue() {
	    return fee;
	}

    }

    class FeeCellRenderer extends DefaultTableCellRenderer {

	protected Cuota fee;

	public FeeCellRenderer() {
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    //	System.out.println("value: " + value.getClass());
	    if (Cuota.class.isInstance(value)) {
		fee = (Cuota)value;
		if (fee.getIdTipoCuota() != 0) {
		    fee.setText("" + Format.money(fee.getMonto()));
		    if (fee.isPagada()) {
			fee.setSelected(true);
			fee.setEnabled(false);
		    }
		    if (fee.isVencida()) {
			fee.setForeground(Color.RED);
		    }
		    if (fee.getIdTipoCuota() == -1) {
			fee.setForeground(new Color(121, 68, 116));
			fee.setText("N/A");
			fee.setSelected(true);
			fee.setEnabled(false);
		    }
		} else {
		    fee.setText(String.valueOf(fee.getAnio()) + " - " + Format.money(fee.getMonto()));
		}
	    }
	    return fee;
	}

    }

}
