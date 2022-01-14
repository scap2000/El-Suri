package org.digitall.common.geo.mapping.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;

import java.awt.event.ItemListener;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTable;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class TableEditionPanel extends BasicPrimitivePanel {

    private DefaultTableModel modelo = new DefaultTableModel();
    private BasicTable resultsTable = new BasicTable(modelo);
    private BasicScrollPane results = new BasicScrollPane(resultsTable);
    private String sqlScheme = "";
    private String sqlTable = "";
    private int offset = 0;
    private int limit = 30;
    private BasicPanel jpButtons = new BasicPanel(new GridLayout(1, 5, 10, 0));
    private RefreshGridButton btnRefresh = new RefreshGridButton();
    private TFInput tfLimit = new TFInput(DataTypes.INTEGER, "Límite", false);
    private TFInput tfOffset = new TFInput(DataTypes.INTEGER, "Offset", false);
    private CBInput cbSchemes = new CBInput(0,"Scheme",false);
    private CBInput cbTables = new CBInput(0,"Table",false);

    public TableEditionPanel(String _sqlScheme, String _sqlTable) {
	sqlScheme = _sqlScheme;
	sqlTable = _sqlTable;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setSize(new Dimension(600, 200));
	setLayout(new BorderLayout());
	jpButtons.setTitle("Tabla: " + sqlScheme + "." + sqlTable);
	tfLimit.setValue(30);
	tfOffset.setValue(0);
	btnRefresh.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnRefresh_actionPerformed(e);
		}
	    });
	jpButtons.add(cbSchemes);
	jpButtons.add(cbTables);
	jpButtons.add(tfLimit);
	jpButtons.add(tfOffset);
	jpButtons.add(btnRefresh);
	add(jpButtons, BorderLayout.NORTH);
	add(results, BorderLayout.CENTER);
	cbSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	cbSchemes.addItemListener(new ItemListener() {

				     public void itemStateChanged(ItemEvent e) {
					 if (e.getStateChange() == ItemEvent.SELECTED) {
					     if (cbSchemes.getSelectedIndex() > -1) {
					         sqlScheme = cbSchemes.getSelectedItem().toString();
						 loadTablesByScheme(cbSchemes.getSelectedItem().toString());
					     }
					 }
				     }

				 }
	);
	cbSchemes.setSelectedValue(sqlScheme);
	cbTables.setSelectedValue(sqlTable);
	cbTables.addItemListener(new ItemListener() {

				     public void itemStateChanged(ItemEvent e) {
					 if (e.getStateChange() == ItemEvent.SELECTED) {
					     if (cbTables.getSelectedIndex() > -1) {
						 sqlTable = cbTables.getSelectedItem().toString();
					     }
					 }
				     }

				 }
	);
    }

    private void loadTablesByScheme(String _scheme) {
	cbTables.loadJCombo("SELECT bc.oid, relname as name, 0 FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND relkind = 'r' ORDER BY relname");
    }



    private void runQuery() {
	setLimit(tfLimit.getInteger());
	setOffset(tfOffset.getInteger());
	ResultSet _test = LibSQL.exQuery("SELECT * FROM " + sqlScheme + "." + sqlTable + " LIMIT " + limit + " OFFSET " + offset);
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
	resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	resultsTable.getTableHeader().setReorderingAllowed(false);
	resultsTable.getTableHeader().setResizingAllowed(true);
	results.getViewport().remove(resultsTable);
	results.getViewport().add(resultsTable);
    }

    public void setOffset(int _offset) {
	offset = _offset;
    }

    public int getOffset() {
	return offset;
    }

    public void setLimit(int _limit) {
	limit = _limit;
    }

    public int getLimit() {
	return limit;
    }

    private void btnRefresh_actionPerformed(ActionEvent e) {
	runQuery();
    }
}
