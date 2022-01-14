package org.digitall.common.geo.mapping.panels;

import java.awt.Dimension;

import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.basic.BasicList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.sql.LibSQL;

public class TablesJoinerPanel extends BasicPanel {

    private JCombo cbTablesSchemes = new JCombo();
    private BasicList tablesByScheme = new BasicList();
    private BasicScrollPane spTablesByScheme = new BasicScrollPane();

    public TablesJoinerPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(560, 375));
	this.setLayout(null);

	spTablesByScheme.setBounds(new Rectangle(5, 30, 235, 215));
	cbTablesSchemes.setBounds(new Rectangle(5, 5, 235, 20));
	cbTablesSchemes.addItemListener(new ItemListener() {

				     public void itemStateChanged(ItemEvent e) {
					 if (e.getStateChange() == ItemEvent.SELECTED) {
					     if (cbTablesSchemes.getSelectedIndex() > -1) {
						 loadTablesByScheme(cbTablesSchemes.getSelectedItem().toString());
					     }
					 }
				     }

				 }
	);
	cbTablesSchemes.loadJCombo("SELECT oid, nspname, 0 FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' ORDER BY nspname");
	cbTablesSchemes.setSelectedIndex(0);
	loadTablesByScheme(cbTablesSchemes.getSelectedItem().toString());

	tablesByScheme.addListSelectionListener(new ListSelectionListener() {

					     public void valueChanged(ListSelectionEvent e) {
						 if (tablesByScheme.getSelectedIndex() > -1) {
						     try {
							 //loadGroupsByTable(tablesByScheme.getSelectedValue().toString());
						     } catch (NullPointerException x) {
							 //ignore
						     }
						 }
					     }

					 }
	);
	add(cbTablesSchemes, null);
	spTablesByScheme.getViewport().add(tablesByScheme);
	add(spTablesByScheme, null);
    }

    private void loadTablesByScheme(String _scheme) {
	//String query = "SELECT relname as name, description AS comment FROM pg_description de, pg_class bc, pg_namespace ns WHERE de.objoid = bc.oid and bc.relnamespace = ns.oid AND objsubid = 0 AND   ns.nspname = '" + _scheme + "' order by relname";
	String query = "SELECT relname as name FROM pg_class bc, pg_namespace ns WHERE bc.relnamespace = ns.oid AND ns.nspname = '" + _scheme + "' AND relkind = 'r' ORDER BY relname";
	ResultSet rs = LibSQL.exQuery(query);
	Vector _tablesByScheme = new Vector();
	try {
	    while (rs.next()) {
		_tablesByScheme.add(rs.getString("name"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	tablesByScheme.setListData(_tablesByScheme);
	tablesByScheme.setSelectedIndex(0);
    }

}
