package org.digitall.lib.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.data.DataTypes;

public class PanelDataEntry extends BasicContainerPanel {

    private BasicLabel lbl = new BasicLabel();
    private BasicTextField jtxt = new BasicTextField();
    private BasicLabel lblValidator = new BasicLabel();

    public PanelDataEntry(int _nDataType, String _sComponentName, boolean _bRequired) {
	try {
	    jbInit(_nDataType, _sComponentName);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit(int nDataType, String sComponentName) throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(107, 42));
	lbl.setText("Label Text");
	lbl.setBounds(new Rectangle(0, 0, 80, 20));
	jtxt.setBounds(new Rectangle(0, 20, 85, 20));
	lblValidator.setText("(*)");
	lblValidator.setBounds(new Rectangle(90, 20, 20, 20));
	lblValidator.setForeground(new Color(231, 0, 0));
	lblValidator.setFont(new Font("Dialog", 1, 11));
	this.add(lblValidator, null);
	this.add(jtxt, null);
	this.add(lbl, null);
	/*
         * Instance JEntry for nDataType
         */
	switch (nDataType) {
	    case DataTypes.INTEGER:
		JIntEntry txtField = new JIntEntry();
		txtField.setName(sComponentName);
		break;
		/*JCombo
            JArea
            JDecEntry
            JTFecha*/
	}
    }

}
