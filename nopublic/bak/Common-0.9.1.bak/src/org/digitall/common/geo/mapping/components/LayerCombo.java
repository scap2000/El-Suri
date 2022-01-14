package org.digitall.common.geo.mapping.components;

import java.awt.BorderLayout;
import java.awt.Component;

import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Map;

import javax.swing.ComboBoxEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.BevelBorder;

public class LayerCombo extends JComboBox {

    public LayerCombo() {
	setRenderer(new myRen());
	addItemListener(new ItemListener() {

		     public void itemStateChanged(ItemEvent e) {
			 System.out.println(e);
			 System.out.println(getItemAt(0).getClass());
		     }

		 }
	);
    }

    class myRen extends JPanel implements ListCellRenderer {

	JPanel testPanel = null;
	JCheckBox text1 = null;
	JLabel text2 = null;

	myRen() {
	    text1 = new JCheckBox("", true);
	    addMouseListener(new MouseListener() {

			  public void mouseClicked(MouseEvent e) {
			      System.out.println(e);
			  }

			  public void mousePressed(MouseEvent e) {
			      System.out.println(e);
			  }

			  public void mouseReleased(MouseEvent e) {
			      System.out.println(e);
			  }

			  public void mouseEntered(MouseEvent e) {
			      System.out.println(e);
			  }

			  public void mouseExited(MouseEvent e) {
			      System.out.println(e);
			  }

		      }
	    );
	    text2 = new JLabel("", 10);
	    testPanel = new JPanel();
	    testPanel.setLayout(new GridLayout(1, 2));
	    testPanel.add(text1);
	    testPanel.add(text2);
	}

	public Component getListCellRendererComponent(JList list, Object value, int idx, boolean isSel, boolean hasFocus) {
	    if (value != null) {
		text1.setText((String)value);
		text2.setText(((String)value).substring(1, 2));
	    }
	    testPanel.setBackground((isSel)?list.getSelectionBackground(): list.getBackground());
	    testPanel.setForeground((isSel)?list.getSelectionForeground(): list.getForeground());
	    return testPanel;
	}

    }

}
