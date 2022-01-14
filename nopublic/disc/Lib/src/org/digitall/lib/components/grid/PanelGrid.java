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
 * PanelGrid.java
 *
 * */
package org.digitall.lib.components.grid;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JDecEntry;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTable;
import org.digitall.lib.components.buttons.BackGridButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.LastGridButton;
import org.digitall.lib.components.buttons.NextGridButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

//

public class PanelGrid extends BasicContainerPanel {

    private NextGridButton btnNext = new NextGridButton();
    private BackGridButton btnBack = new BackGridButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private LastGridButton btnLast = new LastGridButton();
    private BasicPanel panel = new BasicPanel();
    private BasicLabel lblStatusResult = new BasicLabel();
    private String notCoincidence = "";
    private boolean sort = false;
    private String functionName = "";
    private String params = "";
    private Component parentForm;
    private int limit = 0;
    private int offset = 0;
    private int resultTotalQty = 0;
    private int resultQty;
    private boolean firstTime = true;
    protected int[] columnSizes;
    private Vector dataRow = new Vector();
    private Vector headerTable = new Vector();
    private BasicScrollPane scrollPane = new BasicScrollPane();
    private Vector editableColumns = new Vector();
    private int indexCheckColumn = 0;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private BasicTable table = new BasicTable(tableModel) {

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
		return getValueAt(0, c).getClass();
	    }

	}
    ;
    private String title;

    @Deprecated
    public PanelGrid(int _resultQty, int[] _columnSizes, String _title, boolean _checks) {
	this(_resultQty, _columnSizes, _title);
    }

    public PanelGrid(int _resultQty, int[] _columnSizes, String _title) {
	try {
	    resultQty = _resultQty;
	    columnSizes = _columnSizes;
	    title = _title;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    
    public void addEditableColumn(int _column) {
	editableColumns.add(new Integer(_column));
    }

    public void removeEditableColumn(int _column) {
	editableColumns.remove(new Integer(_column));
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(760, 296));
	panel.setBounds(new Rectangle(0, 0, 760, 295));
	panel.setLayout(null);
	lblStatusResult.setText("");
	lblStatusResult.setToolTipText(lblStatusResult.getText());
	lblStatusResult.setBounds(new Rectangle(5, 273, 535, 20));
	lblStatusResult.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	btnFirst.setBounds(new Rectangle(545, 273, 55, 20));
	btnBack.setBounds(new Rectangle(605, 273, 45, 20));
	btnNext.setBounds(new Rectangle(650, 273, 45, 20));
	btnLast.setBounds(new Rectangle(700, 273, 55, 20));
	scrollPane.setBounds(new Rectangle(5, 5, 750, 265));
	table.setVisible(false);
	scrollPane.getViewport().add(table, null);
	panel.add(scrollPane, null);
	panel.add(lblStatusResult, null);
	panel.add(btnFirst, null);
	panel.add(btnBack, null);
	panel.add(btnNext, null);
	panel.add(btnLast, null);
	this.add(panel, null);
	this.setBorder(BorderPanel.getBorderPanel(title));
	table.setColumnSelectionAllowed(false);
	table.setRowSelectionAllowed(true);
	table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

							public void valueChanged(ListSelectionEvent e) {
							    if (!e.getValueIsAdjusting()) {
								ListSelectionModel tmodelo = (DefaultListSelectionModel)e.getSource();
								if (!tmodelo.isSelectionEmpty()) {
								    int fila = tmodelo.getMinSelectionIndex();
								    //indice del vector de vectores
								    dataRow = (Vector)tableModel.getDataVector().elementAt(fila);
								}
							    }
							}

						    }
	);
	table.addMouseListener(new MouseAdapter() {

			    public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				    dataRow = (Vector)tableModel.getDataVector().elementAt(table.rowAtPoint(e.getPoint()));
				} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
				    for (int i = 0; i < table.getColumnCount(); i++) {
					dataRow = (Vector)tableModel.getDataVector().elementAt(table.rowAtPoint(e.getPoint()));
					System.out.println("Columna " + i + ": " + table.getColumnModel().getColumn(i).getWidth());
				    }
				}
			    }

			}
	);
	table.addMouseMotionListener(new MouseMotionAdapter() {

				  public void mouseMoved(MouseEvent e) {
				      dataRow = (Vector)tableModel.getDataVector().elementAt(table.rowAtPoint(e.getPoint()));
				  }

			      }
	);
	table.addKeyListener(new KeyAdapter() {

			  public void keyPressed(KeyEvent e) {
			      keyTyped(e);
			  }

			  public void keyReleased(KeyEvent e) {
			      keyTyped(e);
			  }

			  public void keyTyped(KeyEvent e) {
			      /*try {
		            dataRow = (Vector)tableModel.getDataVector().elementAt(table.rowAtPoint(e.getPoint()));
		        } catch (ArrayIndexOutOfBoundsException x) {
		            e.consume();
		        }*/
			      e.consume();
			  }

		      }
	);
	JTableHeader header = table.getTableHeader();
	header.addMouseListener(new ColumnHeaderListener());
	btnFirst.setEnabled(false);
	btnFirst.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnFirst_actionPerformed(e);
				}

			    }
	);
	btnLast.setEnabled(false);
	btnLast.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnLast_actionPerformed(e);
			       }

			   }
	);
	btnNext.setEnabled(false);
	btnNext.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnNext_actionPerformed(e);
			       }

			   }
	);
	btnBack.setEnabled(false);
	btnBack.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnBack_actionPerformed(e);
			       }

			   }
	);
    }

    private void btnFirst_actionPerformed(ActionEvent e) {
	offset = 0;
	setTable(parentForm, functionName, params, headerTable);
    }

    private void btnBack_actionPerformed(ActionEvent e) {
	offset = offset - resultQty;
	setTable(parentForm, functionName, params, headerTable);
    }

    private void btnNext_actionPerformed(ActionEvent e) {
	offset = offset + resultQty;
	setTable(parentForm, functionName, params, headerTable);
    }

    private void btnLast_actionPerformed(ActionEvent e) {
	if (resultTotalQty > resultQty) {
	    offset = resultTotalQty - resultQty;
	}
	setTable(parentForm, functionName, params, headerTable);
    }

    public Vector getDataRow() {
	return getFakeVector(dataRow);
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
	resizeColumns();
    }
    // This comparator is used to sort vectors of data

    public void setCellEditor(DefaultCellEditor _editor, int _column) {
	try {
	    table.getColumnModel().getColumn(_column).setCellEditor(_editor);
	    addEditableColumn(_column);
	} catch (ArrayIndexOutOfBoundsException ignore) {
	    
	}
    }
    
    public void setCellEditor(int _type, int _column) {
	switch (_type) {
	    case Types.DOUBLE:
		setCellEditor(new DefaultCellEditor(new JDecEntry()), _column);
		break;
	    default:
		setCellEditor(new DefaultCellEditor(new JTextField()), _column);
		break;
	}
    }

    //funcion que te devuelve un vector (registro) 
    public Vector getNextRow(ResultSet _rs, ResultSetMetaData _rsMD)throws SQLException {
	Vector actualRow = new Vector();
	actualRow.addElement(new Boolean(false));
	indexCheckColumn = 0;
	DefaultCellEditor chkCellEditor = new DefaultCellEditor(new JCheckBox());
	table.getColumnModel().getColumn(indexCheckColumn).setCellEditor(chkCellEditor);
	addEditableColumn(indexCheckColumn);
	try {
	    for (int i = 1; i <= _rsMD.getColumnCount(); ++i) {
		switch (_rsMD.getColumnType(i)) {
		    case Types.VARCHAR :
			/*if (_rs.getString(i).toString().startsWith("Color:")) {
                            String colorStr = _rs.getString(i).split(";")[0].replaceFirst("Color:", "");
                            Color color = new Color(Integer.parseInt(colorStr.split(",")[0]), Integer.parseInt(colorStr.split(",")[1]), Integer.parseInt(colorStr.split(",")[2]));
                            String date = _rs.getString(i).split(";")[1];
                            JPColorLabel exp = new JPColorLabel();
                            exp.setColor(color);
                            exp.setDate(Proced.setFormatDate(date, true));
                            FilaActual.addElement(exp);
                        } else {
                            FilaActual.addElement(_rs.getString(i));
                        }*/
			actualRow.addElement(_rs.getString(i));
			break;
		    case Types.CHAR :
			actualRow.addElement(_rs.getString(i));
			break;
		    case -7 :
			//BOOLEAN
			if (_rs.getBoolean(i))
			    actualRow.addElement("SI");
			else
			    actualRow.addElement("--");
			break;
		    case Types.DATE :
		    case Types.TIMESTAMP :
			actualRow.addElement(Proced.setFormatDate(Proced.TransformaNull_Texto(_rs.getString(i)), true));
			break;
		    case Types.TIME :
			actualRow.addElement(Proced.Hora(Proced.TransformaNull_Texto(_rs.getString(i)), true, true));
			break;
		    case Types.LONGVARCHAR :
			actualRow.addElement(_rs.getString(i));
			break;
		    case Types.INTEGER :
			actualRow.addElement(new Long(_rs.getLong(i)));
			break;
		    case Types.BIGINT :
			actualRow.addElement(new Long(_rs.getLong(i)));
			break;
		    case Types.DOUBLE :
			actualRow.addElement(Proced.DobleDec(String.valueOf(_rs.getDouble(i))));
			break;
		    case Types.NUMERIC :
			actualRow.addElement(new Double(_rs.getLong(i)));
			break;
		    case Types.DECIMAL :
			actualRow.addElement(new Double(_rs.getDouble(i)));
			break;
		    default :
		}
	    }
	} catch (Exception x) {
	    x.printStackTrace();
	    Advisor.messageBox(x.getMessage(), "ERROR getNextRow: ");
	}
	/**
        if (boton)
            FilaActual.addElement(new String("Acciï¿½"));
        **/
	return actualRow;
    }

    private void setResultCount(final ResultSet result) throws SQLException {
	if (offset < 0)
	    offset = 0;
	if (resultTotalQty > resultQty) {
	    if (offset + resultQty < resultTotalQty) {
		btnNext.setEnabled(true);
		btnLast.setEnabled(true);
	    } else {
		btnNext.setEnabled(false);
		btnLast.setEnabled(false);
	    }
	    if (offset > 0) {
		btnBack.setEnabled(true);
		btnFirst.setEnabled(true);
	    } else {
		btnBack.setEnabled(false);
		btnFirst.setEnabled(false);
	    }
	} else {
	    btnBack.setEnabled(false);
	    btnFirst.setEnabled(false);
	    btnNext.setEnabled(false);
	    btnLast.setEnabled(false);
	}
	if (resultTotalQty == 0) {
	    lblStatusResult.setText(Environment.lang.getProperty("StatusGridNotResult"));
	} else {
	    String resultEnd = "";
	    if (offset + 1 > resultQty) {
		if (offset + resultQty > resultTotalQty)
		    resultEnd = String.valueOf(resultTotalQty);
		else
		    resultEnd = String.valueOf(offset + resultQty);
	    } else {
		if (resultQty > resultTotalQty)
		    resultEnd = String.valueOf(resultTotalQty);
		else
		    resultEnd = String.valueOf(resultQty);
	    }
	    setStatusResultText(String.valueOf(resultTotalQty), String.valueOf(offset + 1), resultEnd);
	}
    }

    private void setStatusResultText(String _resultCount, String _resultStart, String _resultEnd) {
	String statusGridResultMask = Environment.lang.getProperty("StatusGridCancelResult");
	if (!_resultCount.equals("0")) {
	    statusGridResultMask = Environment.lang.getProperty("StatusGridResultMask");
	    statusGridResultMask = statusGridResultMask.replaceFirst("#resultCount#", _resultCount);
	    statusGridResultMask = statusGridResultMask.replaceFirst("#start#", _resultStart);
	    statusGridResultMask = statusGridResultMask.replaceFirst("#end#", _resultEnd);
	}
	lblStatusResult.setText(statusGridResultMask);
	lblStatusResult.setToolTipText(lblStatusResult.getText());
    }

    /**
     * Los indices de cualquier tabla empiezan en 0, se debe especificar de atras hacia adelante
     */
    private void removeColumns() {
	try {
	    for (int i = headerTable.size() - 1; i >= 0; i--) {
		if (headerTable.elementAt(i).equals("*")) {
		    table.removeColumn(table.getColumnModel().getColumn(i+1));
		}
	    }
	} catch (NullPointerException x) {
	    Advisor.messageBox(x.getMessage(), "ERROR columnsRemove: ");
	}
    }

    private void resizeColumns() {
	try {
	    table.setAutoResizeMode(BasicTable.AUTO_RESIZE_OFF);
	    table.getColumnModel().getColumn(getIndexCheckColumn()).setPreferredWidth(34);
	    table.getColumnModel().getColumn(getIndexCheckColumn()).setResizable(false);
	    for (int i = 0; i < columnSizes.length; ++i) {
		TableColumn col = table.getColumnModel().getColumn(i+1);
		if (columnSizes[i] == 0) {
		    col.setResizable(false);
		}
		col.setPreferredWidth(columnSizes[i]);
	    }
	} catch (NullPointerException x) {
	    Advisor.messageBox(x.getMessage(), "ERROR setColumnsSize: ");
	}
    }

    private void setGrid(Vector _headerTable, String _functionName, String _params, int _limit, int _offset, DefaultTableModel _tableModel, BasicTable _table, BasicScrollPane _scrollPane) throws SQLException {
	ResultSet result;
	ResultSetMetaData resultMD;
	if (_tableModel.getColumnCount() == 0) {
	    //agrego la columna con los checkbox
	    _tableModel.addColumn("Sel.");
	    for (int i = 0; i < _headerTable.size(); i++) {
		try {
		    _tableModel.addColumn(_headerTable.elementAt(i));
		} catch (ArrayIndexOutOfBoundsException x) {
		    Advisor.messageBox(x.getMessage(), "Error");
		}
	    }
	}
	_tableModel.setRowCount(0);
	result = LibSQL.exFunction(_functionName, _params, _limit, _offset);
	resultMD = result.getMetaData();
	/* Obtiene la META-Informacion de la tabla que devuelve la consulta "SQLString" */
	while (result.next()) {
	    _tableModel.addRow(getNextRow(result, resultMD));
	}
	_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	_table.getTableHeader().setReorderingAllowed(false);
	_table.getTableHeader().setResizingAllowed(true);
	_scrollPane.getViewport().remove(_table);
	_scrollPane.getViewport().add(_table);
	setResultCount(result);
    }

    public void setTable(final Component _parentForm, final String _functionName, final String _params, final Vector _headerTable) {
	try {
	    parentForm = _parentForm;
	    if (!params.equals(_params))
		limit = 0;
	    if (limit == 0) {
		ResultSet rsCount = LibSQL.exFunction(_functionName, _params, 0, 0);
		rsCount.last();
		resultTotalQty = rsCount.getRow();
		limit = resultQty;
	    }
	    Runnable query = new Runnable() {

		    public void run() {
			//final StatusBar statusBar = new StatusBar(parentForm);
			Thread threadTask = new Thread(new Runnable() {

				      public void run() {
					  try {
					      if (!functionName.equals(_functionName)) {
						  functionName = _functionName;
						  offset = 0;
					      }
					      params = _params;
					      setGrid(_headerTable, functionName, params, limit, offset, tableModel, table, scrollPane);
					      headerTable = _headerTable;
					      if (firstTime) {
						  table.setVisible(false);
						  removeColumns();
						  resizeColumns();
						  firstTime = false;
						  table.setVisible(true);
					      }
					      //statusBar.disposeme();
					  } catch (SQLException x) {
					      Advisor.messageBox(x.getMessage(), "ERROR setTable");
					  } catch (Exception x) {
					      x.printStackTrace();
					  }
				      }

				  }
			    );
			//statusBar.startTask(threadTask);
			threadTask.start();
			//ComponentsManager.centerWindow(statusBar);
			//statusBar.setModal(true);
			//statusBar.setVisible(true);
			/*if (statusBar.getActionCancel()) {
			    setStatusResultText("0", "0", "0");
			}*/
		    }

		}
	    ;
	    query.run();
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "ERROR queryRun");
	}
    }

    public void autoSize() {
	this.setSize(new Dimension(this.getWidth(), this.getHeight()));
	panel.setBounds(new Rectangle(5, 15, this.getWidth() - 8, this.getHeight() - 20));
	scrollPane.setBounds(new Rectangle(5, 10, this.getWidth() - 18, this.getHeight() - 60));
	lblStatusResult.setBounds(new Rectangle(5, this.getHeight() - 42, this.getWidth() - 233, 20));
	btnFirst.setBounds(new Rectangle(this.getWidth() - 223, this.getHeight() - 45, 55, 25));
	btnBack.setBounds(new Rectangle(this.getWidth() - 163, this.getHeight() - 45, 45, 25));
	btnNext.setBounds(new Rectangle(this.getWidth() - 118, this.getHeight() - 45, 45, 25));
	btnLast.setBounds(new Rectangle(this.getWidth() - 68, this.getHeight() - 45, 55, 25));
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

    public Vector getSelectedsVector() {
	Vector seleccionados = new Vector();
	for (int i = 0; i < table.getRowCount(); i++) {
	    String selected = table.getValueAt(i, getIndexCheckColumn()).toString();
	    if (selected.equalsIgnoreCase("true")) {
		seleccionados.addElement(getFakeVector((Vector)tableModel.getDataVector().elementAt(i)));
	    }
	}
	return seleccionados;
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
		seleccionados.addElement(((Vector)tableModel.getDataVector().elementAt(i)).elementAt(_index+1));
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

    public void setTitle(String _title){
	title = _title;
	this.setBorder(BorderPanel.getBorderPanel(title));
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
    


}
