package org.digitall.common.drilling;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorColumnRenderer extends DefaultTableCellRenderer {

    Color back, fore;

    public ColorColumnRenderer(Color _back, Color _fore) {
	super();
	back = _back;
	fore = _fore;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	cell.setBackground(back);
	cell.setForeground(fore);
	return cell;
    }

}
