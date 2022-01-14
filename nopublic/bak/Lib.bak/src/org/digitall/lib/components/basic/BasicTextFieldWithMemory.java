package org.digitall.lib.components.basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import org.digitall.lib.components.basic.BasicCombo;
import org.digitall.lib.components.basic.BasicTextField;

public class BasicTextFieldWithMemory extends BasicCombo {

    Vector items;
    CustomComboBoxModel model;
    BasicTextField tfield;
    int columns;
    /* constructor for a special usage, this obj calls handleEditedElement() */

    public BasicTextFieldWithMemory(Vector vec, int cs, boolean handleEdit) {
	items = vec;
	columns = cs;
	model = new CustomComboBoxModel(items);
	setModel(model);
	setEditable(false);
	tfield = (BasicTextField)getEditor().getEditorComponent();
	if (cs > 0) {
	    tfield.setColumns(columns);
	}
	if (handleEdit) {
	    // automatic self accumulation
	    tfield.addActionListener(new ActionListener() {
				  public void actionPerformed(ActionEvent e) {
				      handleEditedItem();
				  }

			      }
	    );
	}
    }
    /* ordinary usage constructor, application calls handleEditedElement() */

    public BasicTextFieldWithMemory(Vector vec, int cs) {
	this(vec, cs, false);
    }

    public BasicTextFieldWithMemory(Vector vec) {
	this(vec, 0);
    }

    public BasicTextFieldWithMemory() {
	this(new Vector(), 0);
    }

    public BasicTextField getTextField() {
	return tfield;
    }

   public void setText(String t) {
	tfield.setText(t);
	handleEditedItem();
    }

    public Vector getItems() {
	return items;
    }

    public void addActionListenerTf(ActionListener al) {
	tfield.addActionListener(al);
    }

    public void handleEditedItem() {
	String s = tfield.getText();
	if (s.length() > 0 && !model.hasElement(s)) {
	    model.addElement(s);
	    // add new element
	}
	tfield.setText(s);
	// prevent blanking out
    }
    
    public String getText() {
	return tfield.getText();
    }

    private class CustomComboBoxModel extends DefaultComboBoxModel {

	public CustomComboBoxModel(Vector vec) {
	    super(vec);
	}
	// like to add it to head, not to tail

	public void addElement(Object o) {
	    insertElementAt(o, 0);
	}

	public boolean hasElement(Object o) {
	    int size = getSize();
	    int idx = getIndexOf(o);
	    return (idx > -1 && idx < size);
	}

    }

}
