package org.digitall.apps.systemmanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicTextField;

public class PanelNewGroup extends BasicContainerPanel {

    private BasicLabel lblGroupName = new BasicLabel();
    private BasicLabel lblSystem = new BasicLabel();
    private BasicTextField tfName = new BasicTextField();
    public String sistema = "";

    public PanelNewGroup() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	this.setSize(new Dimension(271, 47));
	tfName.setBounds(new Rectangle(180, 13, 85, 20));
	lblGroupName.setText("Group name:");
	lblGroupName.setBounds(new Rectangle(5, 13, 75, 20));
	lblGroupName.setFont(new Font("Dialog", 1, 11));
	lblGroupName.setHorizontalAlignment(SwingConstants.LEFT);
	lblSystem.setBounds(new Rectangle(85, 13, 95, 20));
	lblSystem.setFont(new Font("Dialog", 1, 11));
	lblSystem.setForeground(Color.red);
	lblSystem.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	lblSystem.setHorizontalAlignment(SwingConstants.RIGHT);
	this.add(lblSystem, null);
	this.add(lblGroupName, null);
	this.add(tfName, null);
    }

    public String getGroupName() {
	String cadena = tfName.getText().trim().toLowerCase();
	return cadena;
    }

    public void setSystem(String _sistema) {
	lblSystem.setText(_sistema + "_");
    }

}
