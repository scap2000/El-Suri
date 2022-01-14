package org.digitall.common.geo.mapping.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.RefreshGridButton;

public class TableReferencePanel extends BasicPanel {

    private BasicLabel lblTableRefName = new BasicLabel();
    private BasicPanel jpCenter = new BasicPanel();
    private JCombo cbColumns = new JCombo();
    private RefreshGridButton btnLoad = new RefreshGridButton();
    private TableReference tableReference;
    private BorderLayout borderLayout1 = new BorderLayout();
    private AcceptButton btnAccept = new AcceptButton();

    public TableReferencePanel(TableReference _ref) {
	tableReference = _ref;
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(378, 20));
	this.setLayout(borderLayout1);
	this.setMinimumSize(new Dimension(378, 20));
	this.setOpaque(true);
	if (tableReference.isForward()) {
	    setBackground(Color.green);
	} else {
	    setBackground(Color.cyan);
	}
	lblTableRefName.setText(tableReference.getReferenceCondition());
	setToolTipText(tableReference.getReferenceCondition());
	lblTableRefName.setForeground(Color.black);
	cbColumns.setPreferredSize(new Dimension(150, 20));
	btnLoad.setSize(new Dimension(20, 20));
	btnLoad.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			load();
		    }

		});
	btnLoad.setToolTipText("Obtener subrelaciones");
	btnAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			acceptColumn();
		    }

		});
	this.add(jpCenter, BorderLayout.CENTER);
	jpCenter.setOpaque(false);
	jpCenter.setLayout(new BorderLayout());
	jpCenter.add(lblTableRefName, BorderLayout.CENTER);
	jpCenter.add(btnAccept, BorderLayout.EAST);
	this.add(btnLoad, BorderLayout.WEST);
    }

    public void load() {
	
    }

    public void acceptColumn() {

    }
    
    public void showColumns() {
	    for (int i = 0; i < tableReference.getColumns().size(); i++) {
	        cbColumns.addItem(tableReference.getColumns().elementAt(i).toString().split("&")[2]);
	    }
	    this.add(cbColumns, BorderLayout.EAST);
    }
    
    public String getSelectedColumn() {
	return tableReference.getColumns().elementAt(cbColumns.getSelectedIndex()).toString().split("&")[0];
    }
    
    public String getSelectedColumnDescription() {
	return tableReference.getColumns().elementAt(cbColumns.getSelectedIndex()).toString().split("&")[2];
    }
    
    public void hideColumns() {
	cbColumns.removeAllItems();
	this.remove(cbColumns);
    }

}
