package org.digitall.common.cashflow.interfaces.cashmovement;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import org.digitall.common.cashflow.classes.CashMovementType;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;

public class CashMovementTypeTreeLabel  extends BasicContainerPanel {
    private BasicPanel jPanel1 = new BasicPanel();
    private GridLayout gridLayout1 = new GridLayout();
    private JToggleButton tbtnName = new JToggleButton();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    
    public CashMovementTypeTreeLabel(String _title) {
	try {
	    jbInit();
	    tbtnName.setText(_title);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public CashMovementTypeTreeLabel(CashMovementType _cashFlowType) {
	try {
	    jbInit();
	    tbtnName.setText(_cashFlowType.getName());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setLayout(gridBagLayout1);
	this.setBounds(new Rectangle(10, 10, 500, 16));
	jPanel1.setLayout(gridLayout1);
	jPanel1.setOpaque(false);
	tbtnName.setBorderPainted(false);
	tbtnName.setOpaque(false);
	tbtnName.setMargin(new Insets(1, 1, 1, 1));
	tbtnName.setFont(new Font("Default", 0, 12));
	tbtnName.setHorizontalTextPosition(SwingConstants.CENTER);
	tbtnName.setMinimumSize(new Dimension(46, 16));
	jPanel1.add(tbtnName, null);
	tbtnName.setContentAreaFilled(false);
	this.add(jPanel1, new GridBagConstraints(1, 0, 1, 16, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));	
    }
}
