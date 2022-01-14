package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JToggleButton;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.data.Format;
import org.digitall.common.cashflow.classes.ExpenditureAccount.CCItem;

public class CCExpenditureAccountTreeLabel extends BasicContainerPanel {
    private BasicPanel jPanel1 = new BasicPanel();
    private GridLayout gridLayout1 = new GridLayout();
    private JToggleButton tbtnName = new JToggleButton();
    private BasicPanel jPanel2 = new BasicPanel();
    private GridLayout gridLayout2 = new GridLayout();
    private BasicLabel lblAssignedAmount = new BasicLabel();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    
    private BasicPanel jPanel3 = new BasicPanel();
    private BasicLabel lblSpentAmount = new BasicLabel();
    private GridLayout gridLayout3 = new GridLayout();
    private BasicPanel jPanel4 = new BasicPanel();
    private BasicLabel lblAvailableAmount = new BasicLabel();
    private GridLayout gridLayout4 = new GridLayout();
    private ExpenditureAccount.CCItem objItem = new ExpenditureAccount.CCItem();
    
    
    public CCExpenditureAccountTreeLabel(ExpenditureAccount.CCItem _objItem) {
	try {
	    objItem = _objItem;
	    setData();
	    jbInit();
	} catch (Exception e) {
	e.printStackTrace();
	}
    }
    
    public CCExpenditureAccountTreeLabel(CostsCentre _costsCentre) {
	try {
	    setCostsCentre(_costsCentre);
	    jbInit();
	} catch (Exception e) {
	e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setLayout(gridBagLayout1);
	this.setBounds(new Rectangle(10, 10, 500, 20));
	jPanel1.setLayout(gridLayout1);
	jPanel1.setOpaque(false);
	jPanel2.setLayout(gridLayout2);
	jPanel2.setOpaque(false);
	jPanel3.setLayout(gridLayout3);
	jPanel3.setOpaque(false);
	jPanel4.setLayout(gridLayout4);
	jPanel4.setOpaque(false);
	tbtnName.setBorderPainted(false);
	tbtnName.setMargin(new Insets(1, 1, 1, 1));
	tbtnName.setFont(new Font("Default", 0, 12));
	tbtnName.setOpaque(false);
	tbtnName.setContentAreaFilled(false);
	jPanel1.add(tbtnName, null);
	this.add(jPanel1, new GridBagConstraints(1, 0, 1, 16, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	jPanel2.add(lblAssignedAmount, null);
	this.add(jPanel2, new GridBagConstraints(2, 0, 1, 16, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
	jPanel3.add(lblSpentAmount, null);
	this.add(jPanel3, new GridBagConstraints(3, 0, 1, 16, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
	jPanel4.add(lblAvailableAmount, null);
	this.add(jPanel4, new GridBagConstraints(4, 0, 1, 16, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
    }

    public void setData() {
	lblAssignedAmount.setText("A: "+ Format.money(objItem.getInitialAmount()));
	lblAssignedAmount.setForeground(Color.BLUE);
	
	lblSpentAmount.setText(" | E: "+ Format.money(objItem.getSpentAmount()));
	lblSpentAmount.setForeground(Color.RED);
	
	lblAvailableAmount.setText(" | S: "+ Format.money(objItem.getAvailableCCAmount()));
	if (objItem.getAvailableCCAmount() > 0){
	    lblAvailableAmount.setForeground(new Color(30,170,30));
	} else {
	    lblAvailableAmount.setForeground(Color.RED);
	}
	
	tbtnName.setText(objItem.getName());
    }
    
    public void setCostsCentre(CostsCentre _costsCentre) {
	objItem = null;
	
	tbtnName.setText("("+ _costsCentre.getCode() +") "+ _costsCentre.getName());
	tbtnName.setForeground(Color.BLUE);
    }
}
