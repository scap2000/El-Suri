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
 * GridPanel.java
 *
 * */
package org.digitall.lib.components.grid;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.math.BigDecimal;

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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JDecEntry;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicInfoLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTable;
import org.digitall.lib.components.buttons.BackGridButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.LastGridButton;
import org.digitall.lib.components.buttons.NextGridButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.html.BrowserLauncher;
import org.digitall.lib.sql.LibSQL;

import org.postgresql.util.PSQLException;

//

public class GridPanel extends BasicPanel {

    private NextGridButton btnNext = new NextGridButton();
    private BackGridButton btnBack = new BackGridButton();
    private FirstGridButton btnFirst = new FirstGridButton();
    private LastGridButton btnLast = new LastGridButton();
    private RefreshGridButton btnRefresh = new RefreshGridButton();
    private SaveDataButton btnExport = new SaveDataButton();
    private BasicCheckBox btnCheckAll = new BasicCheckBox(false);
    private BasicPanel jpCenter = new BasicPanel();
    private BasicInfoLabel lblStatusResult = new BasicInfoLabel();
    private boolean sort = false;
    private String functionName = "";
    private Object params = "";
    private int offset = 0;
    private int resultTotalQty = 0;
    private int resultQty;
    private boolean firstTime = true;
    protected int[] columnSizes;
    private Vector dataRow;
    private Vector tableHeader = new Vector();
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
    private BasicPanel jpControls = new BasicPanel();
    private ColumnHeaderListener sortListener = new ColumnHeaderListener();
    private boolean hasListener = false;
    private JLabel lblGlue = new JLabel();
    private boolean byQuery = false;
    private String byQueryString;
    private int selectionMode = ListSelectionModel.SINGLE_SELECTION;
    private boolean sortable = true;
    private boolean loading = false;

    public GridPanel(int _resultQty, int[] _columnSizes, String _title, Vector _dataRow) {
	try {
	    resultQty = _resultQty;
	    columnSizes = _columnSizes;
	    title = _title;
	    dataRow = _dataRow;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setSize(new Dimension(760, 296));
	setLayout(new BorderLayout());
	jpCenter.setLayout(new BorderLayout());
	lblStatusResult.setText(Environment.lang.getProperty("StatusGridNoResult"));
	lblStatusResult.setToolTipText(Environment.lang.getProperty("StatusGridNoResult"));
	lblStatusResult.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	table.setVisible(false);
	scrollPane.getViewport().add(table, null);
	jpCenter.add(scrollPane, BorderLayout.CENTER);
	this.add(jpCenter, BorderLayout.CENTER);
	this.add(jpControls, BorderLayout.SOUTH);
	btnExport.setToolTipText("Exportar estos datos");
	jpControls.setLayout(new GridBagLayout());
	jpControls.add(btnRefresh, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	jpControls.add(btnExport, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	jpControls.add(btnCheckAll, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	jpControls.add(lblGlue, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	jpControls.add(btnFirst, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	jpControls.add(btnBack, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	jpControls.add(btnNext, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	jpControls.add(btnLast, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	jpControls.add(lblStatusResult, new GridBagConstraints(0, 1, 7, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	this.setBorder(BorderPanel.getBorderPanel(title));
	this.setPreferredSize(new Dimension(400, 300));
	table.setColumnSelectionAllowed(false);
	table.setRowSelectionAllowed(true);
	table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

							public void valueChanged(ListSelectionEvent e) {
							    if (!e.getValueIsAdjusting()) {
								ListSelectionModel tmodelo = (DefaultListSelectionModel)e.getSource();
								if (!tmodelo.isSelectionEmpty()) {
								    int fila = tmodelo.getMinSelectionIndex();
								    //indice del vector de vectores
								    updateDataRow((Vector)tableModel.getDataVector().elementAt(fila));
								}
							    }
							}

						    }
	);
	table.addMouseListener(new MouseAdapter() {

			    public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
				    updateDataRow((Vector)tableModel.getDataVector().elementAt(table.rowAtPoint(e.getPoint())));
				} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
				    String _colWidth = "";
				    for (int i = 0; i < table.getColumnCount(); i++) {
					updateDataRow((Vector)tableModel.getDataVector().elementAt(table.rowAtPoint(e.getPoint())));
					System.out.println("Columna " + i + ": " + table.getColumnModel().getColumn(i).getWidth());
					_colWidth += table.getColumnModel().getColumn(i).getWidth() + ", ";
				    }
				    System.out.println(_colWidth);
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
	btnFirst.setEnabled(false);
	btnFirst.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnFirst_actionPerformed(e);
				}

			    }
	);
	btnRefresh.setEnabled(true);
	btnRefresh.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnRefresh_actionPerformed(e);
				  }

			      }
	);
	btnCheckAll.setEnabled(true);
	btnCheckAll.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnCheckAll_actionPerformed(e);
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
	btnRefresh.setToolTipText("Recargar la lista");
	btnExport.setEnabled(false);
	btnExport.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnExport_actionPerformed(e);
		}
	    });
	btnCheckAll.setToolTipText("Seleccionar todos los ítems de la lista");
    }
    
    /**
     * Matias
     * @return
     */
    public Vector getTableHeader(){
	return tableHeader;
    }

    public void addEditableColumn(int _column, int _type) {
	if (!editableColumns.contains(_column)) {
	    editableColumns.add(_column);
	    columnEditors.add(new Object[] { _column, _type });
	}
    }

    public void removeEditableColumn(int _column) {
	if (editableColumns.contains(new Integer(_column))) {
	    columnEditors.removeElementAt(editableColumns.indexOf(new Integer(_column)));
	    editableColumns.remove(new Integer(_column));
	}
    }

    public void setLayout(LayoutManager _layout) {
	/*patch
	 * hago un override del layout que me manden
	 * */
	super.setLayout(new BorderLayout());
    }

    private void btnFirst_actionPerformed(ActionEvent e) {
	offset = 0;
	refresh(false);
    }

    private void btnBack_actionPerformed(ActionEvent e) {
	offset = offset - resultQty;
	refresh(false);
    }

    private void btnNext_actionPerformed(ActionEvent e) {
	offset = offset + resultQty;
	refresh(false);
    }

    private void btnLast_actionPerformed(ActionEvent e) {
	if (resultTotalQty > resultQty) {
	    offset = resultTotalQty - resultQty;
	}
	refresh(false);
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

    private void addCellEditors() {
	for (int i = 0; i < columnEditors.size(); i++) {
	    Object[] editors = (Object[])columnEditors.elementAt(i);
	    {
		int _column = new Integer(editors[0].toString());
		int _type = new Integer(editors[1].toString());
		switch (_type) {
		    case Types.DOUBLE:
		    case DataTypes.DOUBLE:
		    case DataTypes.MONEY:
			setCellEditor(new DefaultCellEditor(new JDecEntry()), _column);
			break;
		    /*case DataTypes.MONEY:
			setCellEditor(new DefaultCellEditor(new JMoneyEntry()), _column);
			break;*/
		    case DataTypes.DOUBLE_EXTENDED :
		    case DataTypes.MONEY_EXTENDED :
			setCellEditor(new DefaultCellEditor(new JDecEntry("#0.0000")), _column);
			break;
		    /*case DataTypes.MONEY_EXTENDED :
		        setCellEditor(new DefaultCellEditor(new JMoneyEntry(true)), _column);
		        break;*/
		    case DataTypes.BOOLEAN:
			JCheckBox jCheck = new JCheckBox();
			jCheck.setHorizontalAlignment(JCheckBox.CENTER);
			setCellEditor(new DefaultCellEditor(jCheck), _column);
			break;
		    default :
			setCellEditor(new DefaultCellEditor(new JTextField()), _column);
			break;
		}
	    }
	}
    }
    //funcion que te devuelve un vector (registro) 

    public Vector getNextRow(ResultSet _rs, ResultSetMetaData _rsMD) throws SQLException {
	Vector actualRow = new Vector();
	actualRow.addElement(new Boolean(false));
	indexCheckColumn = 0;
	/*DefaultCellEditor chkCellEditor = new DefaultCellEditor(new JCheckBox());
	table.getColumnModel().getColumn(indexCheckColumn).setCellEditor(chkCellEditor);*/
	//setCellEditor(Types.BOOLEAN, indexCheckColumn);
	addEditableColumn(indexCheckColumn, Types.BOOLEAN);
	try {
	    for (int i = 1; i <= _rsMD.getColumnCount(); ++i) {
                try {
		//System.out.println(_rsMD.getColumnName(i) + ": " +  _rsMD.getColumnTypeName(i) + " (" + _rsMD.getColumnType(i) + ")");
		    if (_rs.getString(i) != null) {
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
				    //[2010-05-13]
				    //Antes se mostraban "--", revisar esta línea
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
				//actualRow.addElement(Proced.DobleDec(String.valueOf(_rs.getDouble(i))));
				actualRow.addElement(_rs.getDouble(i));
				break;
			    case Types.NUMERIC :
				actualRow.addElement(new Double(_rs.getLong(i)));
				break;
			    case Types.DECIMAL :
				actualRow.addElement(new Double(_rs.getDouble(i)));
				break;
			    default :
				actualRow.addElement(_rs.getString(i));
				break;
			}
		    } else {
			actualRow.addElement("N/A");
		    }
	        } catch (PSQLException x) {
                    actualRow.addElement(_rs.getString(i));
	            //x.printStackTrace();
	            Advisor.messageBox(x.getMessage(), "ERROR getNextRow");
                    Advisor.messageBox("Verify datatypes on resultset", "ERROR getNextRow: "  + _rs.getString(i));
	            //break;
                    continue;
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
	//Debug.println(actualRow);
	System.out.println(actualRow);
	return actualRow;
    }

    private void updateResultCount() {
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
	    lblStatusResult.setText(Environment.lang.getProperty("StatusGridNoResult"));
	    lblStatusResult.setToolTipText(Environment.lang.getProperty("StatusGridNoResult"));
	} else {
	    btnExport.setEnabled(true);
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
	    statusGridResultMask += " (máx. " + resultQty + " por pág.)";
	}
	lblStatusResult.setText(statusGridResultMask);
	lblStatusResult.setToolTipText(lblStatusResult.getText());
    }

    /**
     * Los indices de cualquier tabla empiezan en 0, se debe especificar de atras hacia adelante
     */
    private void removeColumns() {
	removeSortListener();
	try {
	    /**REMOVER*/
	    for (int i = tableHeader.size() - 1; i >= 0; i--) {
		if (tableHeader.elementAt(i).toString().startsWith("*")) {
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
	} catch (ArrayIndexOutOfBoundsException x) {
	    Advisor.messageBox("Se han especificado más columnas de las existentes", x.getMessage());
	}
	addSortListener();
	addCellEditors();
    }

    private void setGrid(int _limit, int _offset, DefaultTableModel _tableModel, BasicTable _table, BasicScrollPane _scrollPane) throws SQLException {
	removeSortListener();
	ResultSet result;
	ResultSetMetaData resultMD;
	if (_tableModel.getColumnCount() == 0) {
	    //agrego la columna con los checkbox
	    _tableModel.addColumn("Sel.");
	    for (int i = 0; i < tableHeader.size(); i++) {
		try {
		    _tableModel.addColumn(tableHeader.elementAt(i));
		} catch (ArrayIndexOutOfBoundsException x) {
		    Advisor.messageBox(x.getMessage(), "Error");
		}
	    }
	}
	_tableModel.setRowCount(0);
	//limpio el dataRow
	dataRow.removeAllElements();
	if (byQuery) {
	    result = LibSQL.exQuery(byQueryString);
	} else {
	    result = LibSQL.exFunction(functionName, params, _limit, _offset);
	}
	resultMD = result.getMetaData();
	/* Obtiene la META-Informacion de la tabla que devuelve la consulta "SQLString" */
	while (result.next()) {
	    _tableModel.addRow(getNextRow(result, resultMD));
	}
	_table.setSelectionMode(selectionMode);
	_table.getTableHeader().setReorderingAllowed(false);
	_table.getTableHeader().setResizingAllowed(true);
	_scrollPane.getViewport().remove(_table);
	_scrollPane.getViewport().add(_table);
	addSortListener();
	updateResultCount();
    }

    public void setSelectionMode(int _mode) {
	selectionMode = _mode;
	table.setSelectionMode(selectionMode);
    }

    public void setParams(String _functionName, Object  _params, Vector _tableHeader) {
	functionName = _functionName;
	params = _params;
	tableHeader = _tableHeader;
    }

    public void setQuery(String _query) {
	byQuery = true;
	byQueryString = _query;
	refresh(false);
    }

    public void unsetQuery() {
	byQuery = false;
    }

    public void refresh(Object _params) {
	if (!params.toString().equalsIgnoreCase(_params.toString())) {
	    offset = 0;
	    params = _params;
	}
	refresh(true);
    }

    private void refresh(final boolean _refresh) {
	    btnCheckAll.setSelected(false);
	    if (!loading) {
		Thread threadTask = new Thread(new Runnable() {
    
					    public void run() {
						    //comps.setCursor(new Cursor(Cursor.WAIT_CURSOR));
						//Environment.getActiveDesktop().setVisible(false);
						loading = true;
						Environment.jpStatusBar.setIndeterminate(true);
						Environment.jpStatusBar.setAction("Retrieving data...");
						btnRefresh.setEnabled(false);
					        btnExport.setEnabled(false);
						scrollPane.setVisible(false);
						try {
						    if (_refresh) {
							ResultSet rsCount = LibSQL.exFunction(functionName, params, 0, 0);
							rsCount.last();
							resultTotalQty = rsCount.getRow();
						    }
						    setGrid(resultQty, offset, tableModel, table, scrollPane);
						    if (firstTime) {
							table.setVisible(false);
							addSortListener();
							removeColumns();
							firstTime = false;
							table.setVisible(true);
						    }
						    calculate();
						    finishLoading();
						} catch (SQLException x) {
						    Advisor.messageBox(x.getMessage(), "ERROR setTable");
						    Environment.jpStatusBar.setAction("Error retrieving data...");
						} catch (Exception x) {
						    x.printStackTrace();
						    Environment.jpStatusBar.setAction("Error retrieving data...");
						}
						scrollPane.setVisible(true);
						btnRefresh.setEnabled(true);
						loading = false;
						Environment.jpStatusBar.setAction("Data retrieved succesfuly");
						Environment.jpStatusBar.setIndeterminate(false);
						//Environment.getActiveDesktop().setVisible(true);
						    //comps.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					    }
    
					}
		    );
		//Hit uncaught exception java.lang.ArrayIndexOutOfBoundsException
	      //SwingUtilities.invokeLater(threadTask);
	      threadTask.start();
	    }
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
	for (int i = 0; i < getItemCount(); i++) {
	    String selected = table.getValueAt(i, getIndexCheckColumn()).toString();
	    if (selected.equalsIgnoreCase("true")) {
		seleccionados.addElement(((Vector)tableModel.getDataVector().elementAt(i)).elementAt(1));
		//        return table.getValueAt(rows[0], 0).toString();
	    }
	}
	return seleccionados;
    }

    public Vector getAllIDs() {
	Vector seleccionados = new Vector();
	for (int i = 0; i < getItemCount(); i++) {
	    seleccionados.addElement(((Vector)tableModel.getDataVector().elementAt(i)).elementAt(1));
	}
	return seleccionados;
    }

    public String getSelectedsIDSeparatedByComma() {
	return getSelectedsIDSeparatedBy(",");
    }
    
    public String getSelectedsIDSeparatedBy(String _separator) {
	Vector _selectedsVector = getSelectedsID();
	StringBuilder _selectedIDS = new StringBuilder();
	if (_selectedsVector.size() > 0) {
	    for (int i = 0; i < _selectedsVector.size() - 1; i++) {
		_selectedIDS.append(_selectedsVector.elementAt(i).toString() + _separator);
	    }
	    _selectedIDS.append((_selectedsVector.elementAt(_selectedsVector.size() - 1)).toString());
	}
	return _selectedIDS.toString();
    }

    public Vector getMultiSelectionSelectedsID() {
	Vector seleccionados = new Vector();
	int[] rows = table.getSelectedRows();
	for (int i = 0; i < rows.length; i++) {
		seleccionados.addElement(((Vector)tableModel.getDataVector().elementAt(rows[i])).elementAt(1));
		System.out.println(((Vector)tableModel.getDataVector().elementAt(rows[i])).elementAt(1));
	}
	return seleccionados;
    }

    public Vector getSelectedsVector() {
	Vector<Vector> seleccionados = new Vector<Vector>();
	for (int i = 0; i < getItemCount(); i++) {
	    String selected = table.getValueAt(i, getIndexCheckColumn()).toString();
	    if (selected.equalsIgnoreCase("true")) {
		seleccionados.addElement(getFakeVector((Vector)tableModel.getDataVector().elementAt(i)));
	    }
	}
	return seleccionados;
    }

    public void updateDataRow(Vector _vector) {
	dataRow.removeAllElements();
	dataRow.addAll(getFakeVector(_vector));
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
        for (int i = 0; i < getItemCount(); i++) {
            String selected = table.getValueAt(i, getIndexCheckColumn()).toString();
            if (selected.equalsIgnoreCase("true")) {
                seleccionados.addElement(((Vector)tableModel.getDataVector().elementAt(i)).elementAt(_index + 1));
            }
        }
        return seleccionados;
    }

    public Vector getValuesAt(int _index) {
        Vector values = new Vector();
        for (int i = 0; i < getItemCount(); i++) {
            values.addElement(((Vector)tableModel.getDataVector().elementAt(i)).elementAt(_index + 1));
        }
        return values;
    }

    public Vector getAllValues() {
        Vector values = new Vector();
        for (int i = 0; i < getItemCount(); i++) {
            values.addElement(new Vector((Vector)tableModel.getDataVector().elementAt(i)));
        }
        return values;
    }

    public Vector getSelectedsValuesInTableAt(int _columna) {
	Vector seleccionados = new Vector();
	if (_columna < table.getColumnCount()) {
	    for (int i = 0; i < getItemCount(); i++) {
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

    public Vector getValuesInTableAt(int _columna) {
        Vector value = new Vector();
        if (_columna < table.getColumnCount()) {
            for (int i = 0; i < getItemCount(); i++) {
                    value.addElement(table.getValueAt(i, _columna));
            }
        } else {
            System.out.println("La columna indicada supera la cantidad de columnas de la Grilla");
        }
        return value;
    }
    
    /**
     * Matias
     * Devuleve un vector con los valores de una columna indicada por su nombre
     * @param _columna
     * @return
     */
    public Vector getValuesInTableAt(String _columna) {
	Vector value = new Vector();
	int _indiceColumna = getIndexFromHeader(_columna);
	if ((_indiceColumna < table.getColumnCount()) && (_indiceColumna != -1)) {
	    for (int i = 0; i < getItemCount(); i++) {
		    value.addElement(table.getValueAt(i, _indiceColumna));
	    }
	} else {
	    System.out.println("La columna indicada supera la cantidad de columnas de la Grilla");
	}
	return value;
    }
    
    /**
     * Matias
     * Devuelve el indice en la grilla de una columna a partir de su nombre
     * @param _columna
     */
     private int getIndexFromHeader(String _columna){
	 int _indice = 0 ;
	 int _asteriscos = 0;
	 boolean _encontrado = false;
	 while ((_indice < tableHeader.size()) && (!_encontrado)){
	     if (tableHeader.elementAt(_indice).toString().toUpperCase().equals(_columna.toUpperCase())){
		 _encontrado = true;
	     }else{
		if (tableHeader.elementAt(_indice).toString().startsWith("*")){
		    _asteriscos++;
		}
		_indice++;
	     }
	 }
	 if (!_encontrado){
	     System.out.println("La columna indicada supera la cantidad de columnas de la Grilla");
	     _indice = -1;
	 }else{
	     _indice = (_indice - _asteriscos);
	 }
	 return (_indice + 1);
     }

    public void selectAllItems(boolean _value) {
	for (int i = 0; i < getItemCount(); i++) {
	    table.setValueAt(_value, i, getIndexCheckColumn());
	}
	calculate();
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

    private void btnRefresh_actionPerformed(ActionEvent e) {
	refresh(true);
    }

    private void btnCheckAll_actionPerformed(ActionEvent e) {
	selectAllItems(btnCheckAll.isSelected());
    }
    
    public boolean isCurrentRowSelected() {
	return new Boolean(table.getValueAt(table.getSelectedRow(),0).toString());
    }

    public void calculate() {
    }

    public void finishLoading() {
    }

    public void setSortable(boolean sortable) {
	this.sortable = sortable;
    }

    public boolean isSortable() {
	return sortable;
    }
    
    public double getSum(int _columna) {
	BigDecimal _result = new BigDecimal("0");
	for (int i = 0; i < getItemCount(); i++) {
		_result = _result.add(new BigDecimal(table.getValueAt(i,_columna).toString()));
	}
	return _result.doubleValue();
    }

    public boolean areAllNonZero(int _columna) {
	boolean _allnonzero = true;
	int i = 0;
	while (i < getItemCount() && _allnonzero) {
	    _allnonzero = (new BigDecimal(table.getValueAt(i,_columna).toString())).doubleValue() != 0;
	    i++;
	}
	return _allnonzero;
    }
    
    public void removeControls() {
	this.remove(jpControls);
	updateUI();
    }

    public boolean hasItems() {
	return (getItemCount() > 0);
    }

    public int getItemCount() {
	return table.getRowCount();
    }
    
    private void exportDataToExcel() {
	JFileChooser chooser = new JFileChooser(Environment.cfg.getProperty("GridPanelXLSExport") + File.separator);
	chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	int returnVal = chooser.showSaveDialog(chooser.getParent());
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    // IF File Selected
	    try {
		String path = chooser.getSelectedFile().getAbsolutePath();
		if (!path.toLowerCase().endsWith(".xls")) {
		    path += ".xls";
		}
		Environment.cfg.setProperty("GridPanelXLSExport", chooser.getSelectedFile().getParent());
		File file = new File(path);
	        FileOutputStream fileOut = new FileOutputStream(file);
	        ResultSet result;
	        ResultSetMetaData resultMD;
	        if (byQuery) {
	            result = LibSQL.exQuery(byQueryString);
	        } else {
	            //result = LibSQL.exFunction(functionName, params, _limit, _offset);
	            result = LibSQL.exFunction(functionName, params, 0, 0);
	        }
	        resultMD = result.getMetaData();
	        /* Obtiene la META-Informacion de la tabla que devuelve la consulta "SQLString" */
	        HSSFWorkbook workBook = new HSSFWorkbook();
	        String _sheetTitle = title;
	        if (_sheetTitle.length() == 0) {
	            _sheetTitle = "Hoja 1";
	        } else if (_sheetTitle.length()>31) {
	            _sheetTitle = _sheetTitle.substring(0,31);
	        }
	        _sheetTitle = _sheetTitle.replaceAll("/", "_").replaceAll("\\\\", "_").replaceAll("\\[", "_").replaceAll("\\]", "_").replaceAll("\\*", "_").replaceAll("\\?", "_");
	        HSSFSheet sheet = workBook.createSheet(_sheetTitle);
	        int rowNumber = 0;
	        HSSFRow headerRow = sheet.createRow(rowNumber);
	        HSSFFont headerFont = workBook.createFont();
	        headerFont.setFontHeightInPoints((short)11);
	        headerFont.setFontName(headerFont.FONT_ARIAL);
	        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        HSSFCellStyle headerStyle = workBook.createCellStyle();
	        headerStyle.setWrapText(true);
	        headerStyle.setAlignment(HSSFCellStyle. ALIGN_CENTER);
	        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        headerStyle.setBorderBottom((short)1);
	        headerStyle.setBorderTop((short)1);
	        headerStyle.setBorderLeft((short)1);
	        headerStyle.setBorderRight((short)1);
	        headerStyle.setFont(headerFont);
	        //for (int i = 1; i <= resultMD.getColumnCount(); ++i) {
	        for (int i = 1; i <= tableHeader.size(); ++i) {
	            HSSFCell celda = headerRow.createCell(i-1);
	            celda.setCellStyle(headerStyle);
	            //celda.setCellValue(new HSSFRichTextString(resultMD.getColumnName(i)));
	            if (tableHeader.elementAt(i-1).toString().startsWith("*")) {
		        celda.setCellValue(new HSSFRichTextString(tableHeader.elementAt(i-1).toString().replaceFirst("\\*", "").trim()));
			sheet.setColumnHidden(i-1, true);
		    } else {
		        celda.setCellValue(new HSSFRichTextString(tableHeader.elementAt(i-1).toString()));
		    }
	        }
	        sheet.setAutoFilter(new CellRangeAddress(0, 1, 0, tableHeader.size()-1));
	        rowNumber++;
	        while (result.next()) {
	            HSSFRow fila = sheet.createRow(rowNumber);
	            for (int i = 1; i <= resultMD.getColumnCount(); ++i) {
	                HSSFCell celda = fila.createCell(i-1);
	                HSSFCellStyle style;
	                HSSFDataFormat format = workBook.createDataFormat();
			try {
			    switch (resultMD.getColumnType(i)) {
				case -7 :
				    celda.setCellValue(result.getBoolean(i));
				    break;
				case Types.DATE :
				    celda.setCellValue(result.getDate(i));
				    style = workBook.createCellStyle();
				    style.setDataFormat(format.getFormat("dd/MM/yyyy"));
				    celda.setCellStyle(style);
				    break;
				case Types.TIMESTAMP :
				    celda.setCellValue(new HSSFRichTextString(result.getString(i)));
				    style = workBook.createCellStyle();
				    style.setDataFormat(format.getFormat("dd/MM/yyyy HH:mm:ss"));
				    celda.setCellStyle(style);
				    break;
				case Types.TIME :
				    celda.setCellValue(result.getTime(i));
				    style = workBook.createCellStyle();
				    style.setDataFormat(format.getFormat("HH:mm:ss"));
				    celda.setCellStyle(style);
				    break;
				case Types.INTEGER :
				    celda.setCellValue(result.getInt(i));
				    break;
				case Types.BIGINT :
				    celda.setCellValue(result.getLong(i));
				    break;
				case Types.DOUBLE :
				case Types.NUMERIC :
				case Types.DECIMAL :
				    celda.setCellValue(result.getDouble(i));
				    if (tableHeader.elementAt(i-1).toString().startsWith("$")) {
					style = workBook.createCellStyle();
					style.setDataFormat(format.getFormat("$ * #,##0.00"));
					celda.setCellStyle(style);
				    }
				    break;
				case Types.VARCHAR :
				case Types.CHAR :
				case Types.LONGVARCHAR :
				default :
				    celda.setCellValue(new HSSFRichTextString(result.getString(i)));
				    break;
			    }
			} catch (Exception x) {
			    //x.printStackTrace();
			    continue;
			}
	            }
	            rowNumber++;
	        }
	        HSSFRow fila = sheet.createRow(rowNumber);
	        for (int i = 1; i <= tableHeader.size(); ++i) {
	            //celda.setCellValue(new HSSFRichTextString(resultMD.getColumnName(i)));
	            if (tableHeader.elementAt(i-1).toString().startsWith("$")) {
	                HSSFCell celda = fila.createCell(i-1);
	                HSSFCellStyle totalStyle = workBook.createCellStyle();
	                totalStyle.setWrapText(true);
	                totalStyle.setAlignment(HSSFCellStyle. ALIGN_RIGHT);
	                totalStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	                totalStyle.setFont(headerFont);
	                totalStyle.setBorderBottom((short)1);
	                totalStyle.setBorderTop((short)1);
	                totalStyle.setBorderLeft((short)1);
	                totalStyle.setBorderRight((short)1);
			totalStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
	                totalStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	                totalStyle.setDataFormat(workBook.createDataFormat().getFormat("$ * #,##0.00"));
			celda.setCellStyle(totalStyle);
	                celda.setCellFormula("SUM(" + getColName(i-1) + "2:" + getColName(i-1) + (rowNumber) + ")");
	            }
	        }

	        for (int i = 1; i <= resultMD.getColumnCount(); ++i) {
	            sheet.autoSizeColumn(i-1);
	        }

		sheet.createFreezePane(0, 1);
	        workBook.write(fileOut);
	        fileOut.close();
		Advisor.messageBox("Los datos se han exportado correctamente", "Exportar datos");
	        BrowserLauncher.openURL(file.getAbsolutePath());
	    } catch (IOException e) {
		Advisor.messageBox("No se pudo guardar el archivo", "Error de E/S");
	        Advisor.printException(e);
	    } catch (SQLException e) {
		Advisor.printException(e);
	    } catch (Exception e) {
		e.printStackTrace();
	        Advisor.printException(e);
	    }
	}
    }

    private String getColName(int num) {
	    Vector v = new Vector();
	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    for (int i = 0; i < alphabet.length(); i++) {
		    v.add(alphabet.charAt(i));
	    }
	    for (int i = 0; i < alphabet.length(); i++) {
		    for (int j = 0; j < alphabet.length(); j++) {
			    v.add(alphabet.charAt(i) + alphabet.charAt(j));
		    }
	    }
	    return(v.elementAt(num).toString());
    }
    
    public Vector getDataRow() {
	return dataRow;
    }

    private void btnExport_actionPerformed(ActionEvent e) {
	if (Advisor.question("Exportar datos", "¿Desea exportar los resultados a un archivo de Excel?")) {
	    exportDataToExcel();
	}
    }

    public class ColumnHeaderListener extends MouseAdapter {

	public void mouseClicked(MouseEvent evt) {
	    if (sortable) {
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
