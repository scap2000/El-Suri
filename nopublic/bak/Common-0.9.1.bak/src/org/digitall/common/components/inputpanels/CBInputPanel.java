package org.digitall.common.components.inputpanels;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AddComboButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

//

/**
 * @deprecated
 */
public class CBInputPanel extends BasicPanel {

    private BasicLabel lbl = new BasicLabel();
    private JCombo comboBox;
    private int nComboType;
    private String sComponentName;
    private boolean bEditable = false;
    private AddComboButton btnAddItem;
    private ExtendedInternalFrame addItemDialog;

    public CBInputPanel(int _nComboType, String _sComponentName) {
	try {
	    nComboType = _nComboType;
	    sComponentName = _sComponentName;
	    bEditable = false;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public CBInputPanel(int _nComboType, String _sComponentName, boolean _bEditable) {
	try {
	    nComboType = _nComboType;
	    sComponentName = _sComponentName;
	    bEditable = _bEditable;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public CBInputPanel() {
	try {
	    nComboType = DataTypes.STRING;
	    sComponentName = "na";
	    bEditable = false;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setLayout(null);
	lbl.setText("Label Text");
	add(lbl, null);
	if (bEditable) {
	    btnAddItem = new AddComboButton();
	    add(btnAddItem, null);
	    btnAddItem.setBounds(new Rectangle(getWidth() - 20, 14, 20, 20));
	}
	lbl.setBounds(new Rectangle(0, 0, getWidth(), 14));
	// setSize(200,50);
	/***/
	String sLabelText = Environment.lang.getProperty(sComponentName) + ":";
	int nComponentLenght = 0;
	lbl.setText(sLabelText);
	/*
         * Instance JCombo
         */
	comboBox = CachedCombo.getCachedCombo(nComboType);
	comboBox.setName(sComponentName);
	comboBox.setBounds(new Rectangle(0, 14, getWidth(), 20));
	add(comboBox, null);
	setAddItemDialog(CachedCombo.getAddItemBasicDialog(nComboType));
    }

    public void setSComponentName(String _sComponentName) {
	sComponentName = _sComponentName;
    }

    public String getSComponentName() {
	return sComponentName;
    }

    public JCombo getCombo() {
	return comboBox;
    }

    public void setCombo(JCombo _cbExternal) {
	remove(comboBox);
	comboBox = _cbExternal;
	add(comboBox, null);
	setBounds(getBounds());
    }

    public Object getSelectedItem() {
	return comboBox.getSelectedItem();
    }

    public Object getSelectedValue() {
	return comboBox.getSelectedValue();
    }

    public void setFilter(Object _filter) {
	comboBox.setFilter(_filter);
    }

    public void setSelectedValue(Object _value) {
	comboBox.setSelectedValue(_value);
    }

    public void setSelectedID(Object _value) {
	comboBox.setSelectedID(_value);
    }

    public int getSelectedIndex() {
	return comboBox.getSelectedIndex();
    }

    public void removeItemAt(int _index) {
	comboBox.removeItemAt(_index);
    }

    public void setBounds(Rectangle _bounds) {
	super.setBounds(_bounds);
	lbl.setBounds(new Rectangle(0, 0, getWidth(), 14));
	if (bEditable) {
	    btnAddItem.setBounds(new Rectangle(getWidth() - 20, 14, 20, 20));
	    comboBox.setBounds(new Rectangle(0, 14, getWidth() - 25, 20));
	} else {
	    comboBox.setBounds(new Rectangle(0, 14, getWidth(), 20));
	}
    }

    private void setAddItemDialog(ExtendedInternalFrame _dialog) {
	if (bEditable && _dialog != null) {
	    addItemDialog = _dialog;
	    btnAddItem.setEnabled(true);
	    btnAddItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			    /*addItemDialog.setModal(true);*/
			    addItemDialog.show();
			    /*comboBox.update();*/
			}

		    });
	}
    }

}
