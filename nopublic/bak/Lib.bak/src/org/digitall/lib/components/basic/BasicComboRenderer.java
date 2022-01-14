package org.digitall.lib.components.basic;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class BasicComboRenderer extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
	Component c = super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
	if (isSelected) {
	    c.setBackground(BasicConfig.TABLE_SELECTED_BACKGROUND_COLOR);
	} else {
	    if (index % 2 == 0) {
		c.setBackground(BasicConfig.TABLE_BASIC_BACKGROUND_COLOR);
	    } else {
		c.setBackground(BasicConfig.TABLE_ALTERNATE_BACKGROUND_COLOR);
	    }
	}
	if (list.getSelectedIndex() > -1) {
	    list.setToolTipText(list.getSelectedValue().toString());
	}
	this.setIconTextGap(6);
	return c;
    }

}
