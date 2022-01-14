/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * ExpenditureAccountTreeLabel.java
 *
 * */
package org.digitall.common.cashflow.interfaces.expenditureaccounts;

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
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.data.Format;

public class ExpenditureAccountTreeLabel extends BasicContainerPanel {

    private BasicPanel jPanel1 = new BasicPanel();
    private GridLayout gridLayout1 = new GridLayout();
    private JToggleButton tbtnName = new JToggleButton();
    private BasicPanel jPanel2 = new BasicPanel();
    private BasicPanel jPanel3 = new BasicPanel();
    private BasicLabel lblSpentAmount = new BasicLabel();
    private BasicLabel lblInitialAmount = new BasicLabel();
    private GridLayout gridLayout2 = new GridLayout();
    private GridLayout gridLayout3 = new GridLayout();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    private ExpenditureAccount expenditureAccount = new ExpenditureAccount();
    private BasicPanel jPanel4 = new BasicPanel();
    private BasicLabel lblAvailableAmount = new BasicLabel();
    private GridLayout gridLayout4 = new GridLayout();
    private int nodeType;
    public static final int CC_ASSIGNED = 1;
    public static final int COSTCENTREEXPENSE = 2;
    public static final int TO_ASSIGN_BUDGET = 3;
    public static final int TO_ASSIGN_CC = 4;
    public static final int EXPENDITUREACCOUNT = 5;

    public ExpenditureAccountTreeLabel(String _title) {
	try {
	    setObjectTitle(_title);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ExpenditureAccountTreeLabel(ExpenditureAccount _expenditureAccount, int _type) {
	try {
	    nodeType = _type;
	    setExpenditureAccount(_expenditureAccount);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ExpenditureAccountTreeLabel(Budget _budget, int _type) {
	try {
	    nodeType = _type;
	    setBudget(_budget);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ExpenditureAccountTreeLabel(CostsCentre _costcentre) {
	try {
	    setCostsCentre(_costcentre);
	    jbInit();
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
	tbtnName.setContentAreaFilled(false);
	jPanel2.setLayout(gridLayout2);
	jPanel2.setOpaque(false);
	jPanel3.setLayout(gridLayout3);
	jPanel3.setOpaque(false);
	jPanel4.setLayout(gridLayout4);
	jPanel4.setOpaque(false);
	jPanel1.add(tbtnName, null);
	this.add(jPanel1, new GridBagConstraints(1, 0, 1, 16, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	jPanel2.add(lblInitialAmount, null);
	this.add(jPanel2, new GridBagConstraints(2, 0, 1, 16, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
	jPanel3.add(lblSpentAmount, null);
	this.add(jPanel3, new GridBagConstraints(3, 0, 1, 16, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 0, 0));
	jPanel4.add(lblAvailableAmount, null);
	this.add(jPanel4, new GridBagConstraints(4, 0, 1, 16, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
    }

    public void setCostsCentre(CostsCentre _costcentre) {
	tbtnName.setText(_costcentre.getName());
	tbtnName.setForeground(Color.BLUE);
    }

    public void setObjectTitle(String _title) {
	tbtnName.setText(_title.toUpperCase());
	lblInitialAmount.setVisible(false);
	lblSpentAmount.setVisible(false);
	lblAvailableAmount.setVisible(false);
    }

    public void setBudget(Budget _budget) {
	expenditureAccount = null;
	lblInitialAmount.setText("I: " + Format.money(_budget.getInitialAmount()+_budget.getModifiedAmount()));
	lblInitialAmount.setForeground(Color.BLACK);
	switch (nodeType) {
	    case TO_ASSIGN_BUDGET :
		{
		    lblSpentAmount.setText("-- As.TG: " + Format.money(_budget.getAssignedAmountToET()));
		    lblAvailableAmount.setText(" - s/A: " + Format.money(_budget.getInitialAmount() - _budget.getAssignedAmountToET()));
		}
		break;
	    case TO_ASSIGN_CC :
		{
		    lblSpentAmount.setText("-- As.CC: " + Format.money(_budget.getAssignedAmountToCC()));
		    lblAvailableAmount.setText(" - s/A: " + Format.money(_budget.getInitialAmount() - _budget.getAssignedAmountToCC()));
		}
		break;
	    default :
		{
		}
		break;
	}
	lblSpentAmount.setForeground(Color.RED);
	lblAvailableAmount.setForeground(Color.GRAY);
	tbtnName.setText(_budget.getName().toUpperCase());
	tbtnName.setForeground(Color.RED);
    }

    public void setExpenditureAccount(ExpenditureAccount _expenditureAccount) {
	expenditureAccount = _expenditureAccount;
	lblInitialAmount.setText("I: " + Format.money(expenditureAccount.getInitialAmount() + expenditureAccount.getModifiedAmount()));
	switch (nodeType) {
	    case CC_ASSIGNED :
		{
		    lblInitialAmount.setForeground(expenditureAccount.getInitialAmount() != 0 ? expenditureAccount.getInitialAmountColor() : Color.BLUE);
		    lblSpentAmount.setText("-- E: " + Format.money(expenditureAccount.getSpentAmount()));
		    lblSpentAmount.setForeground(expenditureAccount.getSpentAmount() != 0 ? Color.GREEN.darker().darker(): Color.GRAY);
		    lblAvailableAmount.setText(" -- S: " + Format.money(expenditureAccount.getAvailableAmount()));
		    lblAvailableAmount.setForeground(expenditureAccount.getAvailableAmount() != 0 ? Color.GREEN.darker().darker() : Color.GRAY);
		}
		break;
	    case COSTCENTREEXPENSE :
		{
		    lblSpentAmount.setVisible(false);
		    lblAvailableAmount.setVisible(false);
		}
		break;
	    case TO_ASSIGN_BUDGET :
		{
		    lblInitialAmount.setForeground(expenditureAccount.getInitialAmount()!=0?expenditureAccount.getInitialAmountColor():Color.GRAY);
		    lblSpentAmount.setText("-- As.TG: " + Format.money(expenditureAccount.getAssignedAmountToET()));
		    lblSpentAmount.setForeground(expenditureAccount.getAssignedAmountToET()!=0?expenditureAccount.getAssignedAmountToETColor():Color.GRAY);
		    lblAvailableAmount.setText(" -- s/A: " + Format.money(expenditureAccount.getInitialAmount() - expenditureAccount.getAssignedAmountToET()));
		    lblAvailableAmount.setForeground((expenditureAccount.getInitialAmount() - expenditureAccount.getAssignedAmountToET()) != 0 ? Color.GREEN.darker().darker() : Color.GRAY);
		}
		break;
	    case TO_ASSIGN_CC :
		{
		    lblInitialAmount.setForeground(expenditureAccount.getInitialAmount()!=0?expenditureAccount.getInitialAmountColor():Color.GRAY);
		    lblSpentAmount.setText("-- As.CC: " + Format.money(expenditureAccount.getAssignedAmountToCC()));
		    lblSpentAmount.setForeground(expenditureAccount.getAssignedAmountToCC()!=0?expenditureAccount.getAssignedAmountToCCColor():Color.GRAY);
		    lblAvailableAmount.setText(" -- s/A: " + Format.money(expenditureAccount.getInitialAmount() - expenditureAccount.getAssignedAmountToCC()));
		    lblAvailableAmount.setForeground((expenditureAccount.getInitialAmount() - expenditureAccount.getAssignedAmountToCC()) != 0 ? Color.GREEN.darker().darker() : Color.GRAY);
		}
		break;
	    case EXPENDITUREACCOUNT :
		{
		    lblInitialAmount.setVisible(false);
		    lblSpentAmount.setVisible(false);
		    lblAvailableAmount.setVisible(false);
		}
		break;
	    default :
		{
		}
		break;
	}
	tbtnName.setText(expenditureAccount.getName());
    }

    public ExpenditureAccount getExpenditureAccount() {
	return expenditureAccount;
    }

    public void setNodeType(int nodeType) {
	this.nodeType = nodeType;
    }

    public int getNodeType() {
	return nodeType;
    }

}
