/*
GNU Lesser General Public License

PropertiesDialog
Copyright (C) 2003 Howard Kistler

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package org.digitall.extras.ekit;

import java.awt.Frame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicCombo;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicTextField;

/** Class for providing a dialog that lets the user specify values for tag attributes
  */
public class PropertiesDialog extends BasicDialog {

    private JOptionPane jOptionPane;
    private Hashtable htInputFields;

    public PropertiesDialog(Frame parent, String[] fields, String[] types, String[] values, String title, boolean bModal) {
	super(parent, title, bModal);
	htInputFields = new Hashtable();
	final Object[] buttonLabels = { Translatrix.getTranslationString("DialogAccept"), Translatrix.getTranslationString("DialogCancel") };
	Object[] panelContents = new Object[(fields.length * 2)];
	int objectCount = 0;
	for (int iter = 0; iter < fields.length; iter++) {
	    String fieldName = fields[iter];
	    String fieldType = types[iter];
	    Object fieldComponent;
	    if (fieldType.equals("text")) {
		fieldComponent = new BasicTextField(3);
		if (values[iter] != null && values[iter].length() > 0) {
		    ((BasicTextField)(fieldComponent)).setText(values[iter]);
		}
	    } else if (fieldType.equals("bool")) {
		fieldComponent = new BasicCheckBox();
		if (values[iter] != null) {
		    ((BasicCheckBox)(fieldComponent)).setSelected(values[iter] == "true");
		}
	    } else if (fieldType.equals("combo")) {
		fieldComponent = new BasicCombo();
		if (values[iter] != null) {
		    StringTokenizer stParse = new StringTokenizer(values[iter], ",", false);
		    while (stParse.hasMoreTokens()) {
			((BasicCombo)(fieldComponent)).addItem(stParse.nextToken());
		    }
		}
	    } else {
		fieldComponent = new BasicTextField(3);
	    }
	    htInputFields.put(fieldName, fieldComponent);
	    panelContents[objectCount] = fieldName;
	    // Translatrix.getTranslationString(fieldName);
	    panelContents[objectCount + 1] = fieldComponent;
	    objectCount += 2;
	}
	jOptionPane = new JOptionPane(panelContents, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, buttonLabels, buttonLabels[0]);
	setContentPane(jOptionPane);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	jOptionPane.addPropertyChangeListener(new PropertyChangeListener() {

					   public void propertyChange(PropertyChangeEvent e) {
					       String prop = e.getPropertyName();
					       if (isVisible() && (e.getSource() == jOptionPane) && (prop.equals(JOptionPane.VALUE_PROPERTY) || prop.equals(JOptionPane.INPUT_VALUE_PROPERTY))) {
						   Object value = jOptionPane.getValue();
						   if (value == JOptionPane.UNINITIALIZED_VALUE) {
						       return;
						   }
						   if (value.equals(buttonLabels[0])) {
						       setVisible(false);
						   } else {
						       setVisible(false);
						   }
					       }
					   }

				       }
	);
	this.pack();
    }

    public PropertiesDialog(Frame parent, String[] fields, String[] types, String title, boolean bModal) {
	this(parent, fields, types, new String[fields.length], title, bModal);
    }

    public String getFieldValue(String fieldName) {
	Object dataField = htInputFields.get(fieldName);
	if (dataField instanceof BasicTextField) {
	    return ((BasicTextField)dataField).getText();
	} else if (dataField instanceof BasicCheckBox) {
	    if (((BasicCheckBox)dataField).isSelected()) {
		return "true";
	    } else {
		return "false";
	    }
	} else if (dataField instanceof BasicCombo) {
	    return (String)(((BasicCombo)dataField).getSelectedItem());
	} else {
	    return (String)null;
	}
    }

    public String getDecisionValue() {
	return jOptionPane.getValue().toString();
    }

}
