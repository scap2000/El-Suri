package org.digitall.lib.geo.mapping.components;

import javax.swing.table.DefaultTableModel;

public class LayerTableModel extends DefaultTableModel {

    public LayerTableModel() {
    }

    public boolean isCellEditable(int r, int c) {
	if ((c > 1)) {
	    //System.out.println("entro: return false!!");
	    return false;
	} else {
	    return true;
	}
    }

    public Class getColumnClass(int c) {
	return getValueAt(0, c).getClass();
    }

    public void deleteRow(int _rowNumber) {
	removeRow(_rowNumber);
    }

}
