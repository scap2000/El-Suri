package org.digitall.lib.components.basic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.UIManager;

public class BasicCheckList extends BasicList {

    public BasicCheckList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public BasicCheckList(ListModel _model) {
	super(_model);
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() {
	setCellRenderer(new CheckListRenderer());
	addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    if (isEnabled() && e.getButton() == MouseEvent.BUTTON1) {
			int index = locationToIndex(e.getPoint());
		        BasicCheckableListItem item = (BasicCheckableListItem)getModel().getElementAt(index);
			item.setSelected(!item.isSelected());
			Rectangle rect = getCellBounds(index, index);
			repaint(rect);
		    }
		}

	    });
    }

    class CheckListRenderer extends JCheckBox implements ListCellRenderer {

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
	    setEnabled(list.isEnabled());
	    if (value instanceof BasicCheckableListItem) {
		setSelected(((BasicCheckableListItem)value).isSelected());
	    }
	    setFont(list.getFont());
	    setText(value.toString());
	    if (isSelected) {
		if (((BasicCheckableListItem)value).isEnabled()) {
		    setBackground(UIManager.getColor("List.selectionBackground"));
		} else {
		    setBackground(new Color(235, 213, 180));
		}
		setForeground(UIManager.getColor("List.selectionForeground"));
	    } else {
	        if (((BasicCheckableListItem)value).isEnabled()) {
	            setBackground(UIManager.getColor("List.textBackground"));
	        } else {
	            setBackground(new Color(235, 235, 235));
	        }
		setForeground(UIManager.getColor("List.textForeground"));
	    }
	    return this;
	}

    }

}
