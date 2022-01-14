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
 * ProcEditor.java
 *
 * */
package org.digitall.apps.jpgadmin.sqleditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.beans.PropertyChangeListener;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTable;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;

import org.postgresql.util.PSQLException;

public class ProcEditor extends BasicInternalFrame {

    private SQLTextPane editPane;
    private BasicScrollPane scp;
    private DefaultTableModel modelo = new DefaultTableModel();
    private BasicTable resultsTable = new BasicTable(modelo);
    private BasicScrollPane results = new BasicScrollPane(resultsTable);
    private JPanel northPane = new JPanel();
    private SaveDataButton btnSave = new SaveDataButton();
    private CancelDataButton btnCancel = new CancelDataButton();
    private BasicButton btnRun = new BasicButton(IconTypes.assignRight_16x16);
    private ProcedureClass procedure = new ProcedureClass();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private JLabel lblStatus = new JLabel();
    private JPanel errorPanel = new JPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JSearch search;

    public ProcEditor(int _oid) {
        super("SQL Editor");
        procedure.setID(_oid);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ProcEditor(String _title, int _oid) {
        super(_title);
	procedure.setID(_oid);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setSize(new Dimension(1024, 768));
	setIconifiable(true);
	//this.setTitle("Procedure Editor");
	super.setTitle("Procedure Editor");
	this.setLayout(gridBagLayout1);
	btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }

			   }
	);
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	btnRun.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnRun_actionPerformed(e);
			      }

			  }
	);
	errorPanel.setLayout(borderLayout1);
	createEditPane(procedure.getContent());
	this.add(results, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	//this.add(errorPanel,  new GridBagConstraints(0, 3, 1, 1, 1.0, 4.0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	northPane.add(btnSave, null);
	northPane.add(btnCancel, null);
	northPane.add(btnRun, null);
	this.add(northPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 915, 0));
	this.getContentPane().add(lblStatus, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    public void createKeyBindings() {
	Action anAction = new RunAction();
	editPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, true), "run");
	editPane.getActionMap().put("run", anAction);
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	if (!saveStoredData()) {
	    Advisor.messageBox("Error al intentar grabar la funcion", "Error");
	}
    }

    private boolean saveStoredData() {
	procedure.setContent(editPane.getText());
	return SQLExecutor.exActualizar('a', procedure.getProcedureString() + ";\n" + "COMMENT ON FUNCTION " + procedure.getDeclaration() + " IS '" + Environment.currentDate + ": (" + Environment.sessionUser + ")'", true);
    }

    private void btnCancel_actionPerformed(ActionEvent e) {
	if (Advisor.question("Descartar cambios", "¿Está seguro de descartar los cambios?")) {
	    procedure.cancelEdition();
	    createEditPane(procedure.getContent());
	}
    }

    private void createEditPane(String _text) {
	editPane = new SQLTextPane(_text);
	editPane.setCaretPosition(0);
	scp = new BasicScrollPane(editPane);
	search = new JSearch(editPane);
	this.add(scp, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1017, 500));
	createKeyBindings();
    }

    private void btnRun_actionPerformed(ActionEvent e) {
	runQuery();
    }

    private void runSelectedQuery(String _query) {
	ResultSet _test = SQLExecutor.exQuery(_query, true);
	try {
	    if (_test != null) {
		if (_test.first()) {
		    ResultSetMetaData _meta = _test.getMetaData();
		    modelo.setColumnCount(0);
		    for (int i = 1; i <= _meta.getColumnCount(); i++) {
			modelo.addColumn("<html>" + _meta.getColumnName(i) + "<BR>" + _meta.getColumnTypeName(i) + "</html>");
		    }
		    modelo.setRowCount(0);
		    while (_test.next()) {
			Vector row = new Vector();
			for (int i = 1; i <= _meta.getColumnCount(); i++) {
			    row.add(_test.getObject(i));
			}
			modelo.addRow(row);
		    }
		}
	    }
	} catch (PSQLException x) {
	    //x.printStackTrace();
	    Advisor.messageBox(x.getMessage(), "Error");
	} catch (SQLException x) {
	    //x.printStackTrace();
	    Advisor.messageBox(x.getMessage(), "Error");
	}
    }

    private void runQuery() {
	String query = "";
	if (editPane.getSelectionStart() != editPane.getSelectionEnd()) {
	    //ejecuto lo seleccionado
	    query = editPane.getSelectedText();
	    runSelectedQuery(query);
	} else if (procedure.getID() != 0) {
	    //grabo y ejecuto
	    if (saveStoredData()) {
		String params = "";
		String answer = null;
		if (procedure.getArgNames() != null) {
		    int in = 0;
		    do {
			answer = JOptionPane.showInputDialog(this, "Valor del parámetro " + procedure.getArgNames()[in] + "\ndel tipo " + procedure.getArgTypes()[in], "Ejecutar funcion", JOptionPane.QUESTION_MESSAGE);
			params += answer;
			in++;
			if (in < procedure.getArgNames().length) {
			    params += ", ";
			}
		    } while (in < procedure.getArgNames().length && answer != null);
		}
		if (answer != null) {
		    if (procedure.getReturns().equals("refcursor")) {
			ResultSet _test = SQLExecutor.exFunction(procedure.getSchemeName() + "." + procedure.getProcedureName(), params, true);
			try {
			    ResultSetMetaData _meta = _test.getMetaData();
			    modelo.setColumnCount(0);
			    for (int i = 1; i <= _meta.getColumnCount(); i++) {
				modelo.addColumn("<html>" + _meta.getColumnName(i) + "<BR>" + _meta.getColumnTypeName(i) + "</html>");
			    }
			    modelo.setRowCount(0);
			    while (_test.next()) {
				Vector row = new Vector();
				for (int i = 1; i <= _meta.getColumnCount(); i++) {
				    row.addElement(_test.getObject(i));
				}
				modelo.addRow(row);
			    }
			    modelo.getRowCount();
			    resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			    resultsTable.getTableHeader().setReorderingAllowed(false);
			    resultsTable.getTableHeader().setResizingAllowed(true);
			    results.getViewport().remove(resultsTable);
			    results.getViewport().add(resultsTable);
			} catch (SQLException x) {
			    //x.printStackTrace();
			    Advisor.messageBox(x.getMessage(), "Error");
			}
		    } else {
			modelo.setColumnCount(0);
			modelo.setRowCount(0);
			modelo.addColumn("<html>output<BR>" + procedure.getReturns() + "</html>");
			Vector row = new Vector();
			if (procedure.getReturns().startsWith("bool")) {
			    row.addElement(SQLExecutor.getBoolean(procedure.getSchemeName() + "." + procedure.getProcedureName(), params, true));
			} else if (procedure.getReturns().startsWith("int")) {
			    row.addElement(SQLExecutor.getInt(procedure.getSchemeName() + "." + procedure.getProcedureName(), params, true));
			} else if (procedure.getReturns().startsWith("doub")) {
			    row.addElement(SQLExecutor.getDouble(procedure.getSchemeName() + "." + procedure.getProcedureName(), params, true));
			} else if (procedure.getReturns().startsWith("date")) {
			    row.addElement(SQLExecutor.getDate(procedure.getSchemeName() + "." + procedure.getProcedureName(), params, true));
			} else {
			    Advisor.messageBox("El tipo de datos " + procedure.getReturns() + " aún no está soportado, por favor agregarlo a esta clase :)", "Error");
			}
			modelo.addRow(row);
			resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			resultsTable.getTableHeader().setReorderingAllowed(false);
			resultsTable.getTableHeader().setResizingAllowed(true);
			results.getViewport().remove(resultsTable);
			results.getViewport().add(resultsTable);
		    }
		} else {
		    Advisor.messageBox("Ejecución cancelada", "Ejecutar procedure");
		}
	    }
	} else {
	    runSelectedQuery(editPane.getText());
	}
    }

    public void setDocument(SQLDocument _doc) {
	editPane.setDocument(_doc);
    }

    public void setText(String _text) {
	editPane.setText(_text);
    }

    private class RunAction implements Action {

	public void addPropertyChangeListener(PropertyChangeListener arg0) {
	    //System.out.println("addPropertyChangeListener: \"U\" has been typed!");
	}

	public Object getValue(String arg0) {
	    //System.out.println("getValue: \"U\" has been typed!");
	    return null;
	}

	public boolean isEnabled() {
	    //System.out.println("isEnabled: \"U\" has been typed!");
	    runQuery();
	    return false;
	}

	public void putValue(String arg0, Object arg1) {
	    //System.out.println("putValue: \"U\" has been typed!");
	}

	public void removePropertyChangeListener(PropertyChangeListener arg0) {
	    //System.out.println("removePropertyChangeListener: \"U\" has been typed!");
	}

	public void setEnabled(boolean arg0) {
	    //System.out.println("set enabled: \"U\" has been typed!");
	}

	public void actionPerformed(ActionEvent arg0) {
	    //System.out.println("action performed: \"U\" has been typed!");
	}

    }

}
