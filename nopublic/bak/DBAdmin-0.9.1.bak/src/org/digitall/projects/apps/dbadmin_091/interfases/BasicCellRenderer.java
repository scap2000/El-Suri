package org.digitall.projects.apps.dbadmin_091.interfases;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class BasicCellRenderer extends JCheckBox implements ListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	DBRole item = (DBRole)value;
	setText(item.getText());
	setOpaque(true);
	if (isSelected) {
	    setBackground(item.getBackground().darker());
	    setForeground(item.getForeground().darker());
	} else {
	    setBackground(item.getBackground());
	    setForeground(item.getForeground());
	}
	setSelected(item.isSelected());
	return this;
    }

}
