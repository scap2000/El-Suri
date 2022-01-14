package org.digitall.apps.gaia.entities.parcels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.geo.gaia.components.GaiaConfigPanel;
import org.digitall.lib.sql.LibSQL;

public class GaiaRecordEditorPanel extends GaiaConfigPanel {

    private JPanel jpCenter = new JPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JScrollPane jsScroll = new JScrollPane();
    private JPanel jPanel1 = new JPanel();
    private JLabel[] labels;
    private JTextField[] fields;
    private AssignButton test = new AssignButton();

    public GaiaRecordEditorPanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(445, 274));
	this.setPreferredSize(new Dimension(760, 512));
	jpCenter.setLayout(borderLayout1);
	jPanel1.setLayout(null);
	jsScroll.getViewport().add(jPanel1, null);
	jpCenter.add(jsScroll, BorderLayout.CENTER);
	jsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	jsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	this.add(jpCenter, BorderLayout.CENTER);
	addButton(test);
	test.addActionListener(new ActionListener() {

			    public void actionPerformed(ActionEvent e) {
				test_actionPerformed(e);
			    }

			}
	);
    }

    private void test_actionPerformed(ActionEvent e) {
	jPanel1.removeAll();
	ResultSet _test = LibSQL.exQuery("SELECT * FROM gis_salta.parcelas_sa WHERE idparcela = 5");
	try {
	    if (_test.next()) {
		ResultSetMetaData _metadata = _test.getMetaData();
		int _columns = _metadata.getColumnCount();
	        fields = new JTextField[_columns];
	        labels = new JLabel[_columns];
		for (int i = 0; i < _columns; i++) {

		    labels[i] = new JLabel(_metadata.getColumnName(i+1));
		    jPanel1.add(labels[i]);
		    labels[i].setBounds(10, (int)(jPanel1.getBounds().getMinX() + 30 * i), 100, 20);

		    fields[i] = new JTextField(_test.getString(i+1));
		    jPanel1.add(fields[i]);
		    fields[i].setBounds(120, (int)(jPanel1.getBounds().getMinX() + 30 * i), 120, 20);
		}
		jPanel1.setPreferredSize(new Dimension(getWidth(), _columns * 30));
	    }
	} catch (SQLException f) {
	    System.out.println(f.getMessage());
	}
    }

}
