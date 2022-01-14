package org.digitall.lib.components.grid;

import javax.swing.JComponent;
import javax.swing.JTable;

import org.digitall.lib.misc.StringTransferHandler;

public class TableTransferHandler extends StringTransferHandler {
    private int[] rows = null;
    private int addIndex = -1; //Location where items were added
    private int addCount = 0;  //Number of items added.
    
    protected String exportString(JComponent c) {
       JTable table = (JTable)c;
       rows = table.getSelectedRows();
       int colCount = table.getColumnCount();
       
       StringBuffer buff = new StringBuffer();
       
       for (int i = 0; i < rows.length; i++) {
	   for (int j = 0; j < colCount; j++) {
	       Object val = table.getValueAt(rows[i], j);
	       buff.append(val == null ? "" : val.toString());
	       if (j != colCount - 1) {
		   buff.append(",");
	       }
	   }
	   if (i != rows.length - 1) {
	       buff.append("\n");
	   }
       }
       
       return buff.toString();
    }
    
    protected void importString(JComponent c, String str) {
    
    }
    
    protected void cleanup(JComponent c, boolean remove) {
    
    }
}
