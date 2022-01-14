package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JToggleButton;

import org.digitall.common.cashflow.classes.Budget;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.data.Format;

public class CCTreeLabel extends BasicContainerPanel {

    private BasicPanel jPanel1 = new BasicPanel();
    private GridLayout gridLayout1 = new GridLayout();
    private JToggleButton tbtnName = new JToggleButton();
    private BasicPanel jPanel2 = new BasicPanel();
    
    private BasicPanel jPanel3 = new BasicPanel();
    private BasicLabel lblUsedAmount = new BasicLabel();
    private BasicLabel lblAssignAmount = new BasicLabel();
    private GridLayout gridLayout2 = new GridLayout();
    private GridLayout gridLayout3 = new GridLayout();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    
    private BasicPanel jPanel4 = new BasicPanel();
    private BasicLabel lblAvailableAmount = new BasicLabel();
    private GridLayout gridLayout4 = new GridLayout();
    
    private BasicPanel jPanel5 = new BasicPanel();
    private BasicLabel lblTotalAssign = new BasicLabel();
    private GridLayout gridLayout5 = new GridLayout();
    
    public CCTreeLabel(CostsCentre _costsCentre) {
	try {
	    setCostsCentre(_costsCentre);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public CCTreeLabel() {
	try {
	    jbInit();
	    setEmptyLabel();
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
	tbtnName.setFont(new Font("Default", 1, 13));
	tbtnName.setContentAreaFilled(false);
	jPanel2.setLayout(gridLayout2);
	jPanel2.setOpaque(false);
	jPanel3.setLayout(gridLayout3);
	jPanel3.setOpaque(false);
	jPanel4.setLayout(gridLayout4);
	jPanel4.setOpaque(false);
	jPanel5.setLayout(gridLayout5);
	jPanel5.setOpaque(false);
	
	jPanel1.add(tbtnName, null);
	this.add(jPanel1, new GridBagConstraints(1, 0, 1, 16, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	
	jPanel2.add(lblAssignAmount, null);
	this.add(jPanel2, new GridBagConstraints(2, 0, 1, 16, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
	
	jPanel3.add(lblUsedAmount, null);
	this.add(jPanel3, new GridBagConstraints(3, 0, 1, 16, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
	
	jPanel4.add(lblAvailableAmount, null);
	this.add(jPanel4, new GridBagConstraints(4, 0, 1, 16, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
	
	jPanel5.add(lblTotalAssign, null);
	this.add(jPanel5, new GridBagConstraints(5, 0, 1, 16, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
    }
    
    private void setEmptyLabel(){
	tbtnName.setText("Centro de Costos");
	lblAssignAmount.setVisible(false);
	lblAvailableAmount.setVisible(false);
	lblUsedAmount.setVisible(false);
	lblTotalAssign.setVisible(false);
    }

    private void setCostsCentre(CostsCentre _costsCentre) {
	Budget budget = _costsCentre.getBudget();
	
	lblAssignAmount.setText("A: "+ Format.money(budget.getInitialAmount()));
	lblAssignAmount.setForeground(Color.BLACK);
	
	lblUsedAmount.setText("-- G: "+ Format.money(budget.getSpentAmount()));
	lblUsedAmount.setForeground(Color.BLACK);
	
	lblAvailableAmount.setText(" -- S: "+ Format.money(budget.getInitialAmount() - budget.getSpentAmount()));
	lblAvailableAmount.setForeground(Color.GRAY);
	
	lblTotalAssign.setText(" -- (T.As.: "+ Format.money(_costsCentre.getInitialAmount()) +")");
	lblTotalAssign.setForeground(Color.BLUE);
	
	tbtnName.setText("("+ _costsCentre.getCode() +") "+ _costsCentre.getName());
    }
}
