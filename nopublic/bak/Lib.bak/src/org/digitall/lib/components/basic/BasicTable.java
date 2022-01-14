package org.digitall.lib.components.basic;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import sun.awt.X11.ColorData;

public class BasicTable extends JTable {

    public BasicTable() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicTable(TableModel _model) {
	super(_model);
	setDefaultRenderer(Number.class, new DCellRenderer());
	setDefaultRenderer(Double.class, new DCellRenderer());
	setDefaultRenderer(Object.class, new DCellRenderer());
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /*public Component prepareRenderer(TableCellRenderer r, int row, int col) {
	Component c = super.prepareRenderer(r, row, col);
	if (false) {
	    c.setBackground(BasicConfig.TABLE_SELECTED_BACKGROUND_COLOR);
	    c.setForeground(BasicConfig.TABLE_SELECTED_FOREGROUND_COLOR);
	} else {
	    c.setBackground((row % 2 == 0) ? BasicConfig.TABLE_ALTERNATE_BACKGROUND_COLOR : BasicConfig.TABLE_BASIC_BACKGROUND_COLOR);
	    c.setForeground(BasicConfig.TABLE_NOT_SELECTED_FOREGROUND_COLOR);
	}
	return c;
    }*/

    private void jbInit() throws Exception {
	this.getTableHeader().setBackground(BasicConfig.TABLE_HEADER_BACKGROUND_COLOR);
	this.getTableHeader().setDefaultRenderer(new BasicHeaderRenderer());
    }

    private class DCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
	    if (value instanceof Long) {
		Long in = (Long) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
	        setHorizontalAlignment(RIGHT);
	    } else if (value instanceof Integer) {
		Integer in = (Integer) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
	        setHorizontalAlignment(RIGHT);
	    } else if (value instanceof Double) {
		Double in = (Double) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
		setHorizontalAlignment(RIGHT);
	    } else if (value instanceof Number) {
		Number in = (Number) value;
		super.getTableCellRendererComponent(table, in, selected, focused, row, column);
		setHorizontalAlignment(RIGHT);
	    } else {
	        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
	        setHorizontalAlignment(LEFT);
	    }
	    // Set the background color
	    if (selected) {
		setBackground(BasicConfig.TABLE_SELECTED_BACKGROUND_COLOR);
		setForeground(BasicConfig.TABLE_SELECTED_FOREGROUND_COLOR);
	    } else {
		setBackground((row % 2 == 0) ? BasicConfig.TABLE_ALTERNATE_BACKGROUND_COLOR : BasicConfig.TABLE_BASIC_BACKGROUND_COLOR);
		setForeground(BasicConfig.TABLE_NOT_SELECTED_FOREGROUND_COLOR);
	    }
	    return this;
	}
	
	

    }

}
