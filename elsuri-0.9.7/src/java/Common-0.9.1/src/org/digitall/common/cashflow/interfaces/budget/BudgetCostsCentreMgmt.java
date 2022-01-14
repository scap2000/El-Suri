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
 * BudgetCostsCentreMgmt.java
 *
 * */
package org.digitall.common.cashflow.interfaces.budget;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.cashflow.classes.Budget;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.interfaces.budget.BudgetCostsCentreTree;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.data.ValueToPercentageListen;
import org.digitall.lib.data.listeners.PercentageToValueListen;

//

public class BudgetCostsCentreMgmt extends BasicPrimitivePanel {

    private BasicPanel panelData = new BasicPanel("Asignación de Partida Presupuestaria");
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "StartDate", false);
    private TFInput tfInitialAmount = new TFInput(DataTypes.MONEY, "InitialAmount", false);
    private TFInput tfInitialAmountP = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfSpentAmount = new TFInput(DataTypes.MONEY, "SpentAmount", false);
    private TFInput tfSpentAmountP = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfAvailableAmount = new TFInput(DataTypes.MONEY, "AvailableAmount", false);
    private TFInput tfModifiedAmount = new TFInput(DataTypes.MONEY, "ModifiedAmount", false);
    private TFInput tfExpenditureAccountAmountLimit = new TFInput(DataTypes.MONEY, "LimitAmount", false);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE, "EndDate", false);
    private TFInput tfCostsCentre = new TFInput(DataTypes.STRING, "CostsCentre", false);
    private TFInput tfExpenditureAccount = new TFInput(DataTypes.STRING, "ExpenditureAccount", false);
    private CBInput cbPeriodType = new CBInput(CachedCombo.PERIODTYPES_LIST, "PeriodType");
    private BasicCheckBox chkActual = new BasicCheckBox();
    private AcceptButton btnAccept = new AcceptButton();
    private BudgetCostsCentreTree parentList;
    private CostsCentre costsCentre;
    private ExpenditureAccount expenditureAccount;
    private CloseButton btnClose = new CloseButton();
    private Budget budget;
    private boolean isNew = true;
    private ExpenditureAccount expTypeExist;
    private CBInput cbValueBlock = new CBInput(CachedCombo.VALUEBLOCK,"ValueBlock");

    public BudgetCostsCentreMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(590, 232));
	tfStartDate.setBounds(new Rectangle(215, 150, 90, 35));
	tfInitialAmount.setBounds(new Rectangle(345, 70, 105, 35));
	tfSpentAmount.setBounds(new Rectangle(150, 110, 130, 35));
	tfAvailableAmount.setBounds(new Rectangle(340, 110, 130, 35));
	tfEndDate.setBounds(new Rectangle(325, 150, 90, 35));
	chkActual.setText("Actual");
	chkActual.setBounds(new Rectangle(505, 195, 65, 20));
	cbPeriodType.setBounds(new Rectangle(15, 150, 120, 35));
	tfCostsCentre.setBounds(new Rectangle(15, 30, 555, 35));
	panelData.setBounds(new Rectangle(5, 0, 580, 195));
	panelData.setLayout(null);
	panelData.add(cbValueBlock, null);
	panelData.add(tfSpentAmountP, null);
	panelData.add(tfModifiedAmount, null);
	panelData.add(tfInitialAmountP, null);
	panelData.add(tfExpenditureAccountAmountLimit, null);
	panelData.add(tfExpenditureAccount, null);
	panelData.add(tfCostsCentre, null);
	panelData.add(cbPeriodType, null);
	panelData.add(chkActual, null);
	panelData.add(tfEndDate, null);
	panelData.add(tfAvailableAmount, null);
	panelData.add(tfSpentAmount, null);
	panelData.add(tfInitialAmount, null);
	panelData.add(tfStartDate, null);
	btnAccept.setBounds(new Rectangle(490, 245, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	 
	this.addButton(btnClose);
	this.addButton(btnAccept);
	this.add(panelData, null);
	btnClose.setBounds(new Rectangle(545, 245, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	cbValueBlock.setBounds(new Rectangle(525, 70, 45, 35));
	tfInitialAmountP.setBounds(new Rectangle(455, 70, 50, 35));
	tfModifiedAmount.setBounds(new Rectangle(15, 110, 130, 35));
	tfSpentAmountP.setBounds(new Rectangle(285, 110, 50, 35));
	tfExpenditureAccountAmountLimit.setBounds(new Rectangle(475, 110, 100, 35));
	tfExpenditureAccount.setBounds(new Rectangle(15, 70, 305, 35));
	cbValueBlock.autoSize();
	
	tfAvailableAmount.setEnabled(false);
	tfModifiedAmount.setEnabled(false);
	tfSpentAmount.setEnabled(false);
	tfSpentAmount.setEnabled(false);
	tfExpenditureAccountAmountLimit.setEnabled(false);
	tfSpentAmount.setEnabled(false);
	tfCostsCentre.setEnabled(false);
	tfExpenditureAccount.setEnabled(false);
	tfSpentAmountP.setEnabled(false);
	chkActual.setVisible(false);
	btnAccept.setToolTipText("Aceptar");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Aceptar\"");
    }
    
    private void loadData() {
    	String name;
	double totalAmount = 0;
	double initialAmount = 0;
	double totalAmountP = 0;
	double limitAmount = 0;
	if (expenditureAccount == null){
	    name = budget.getName();
	    initialAmount = budget.getInitialAmount();
	    limitAmount = initialAmount;
	    if(costsCentre.getBudget().getInitialAmount() == 0){
	        isNew = true;
	        totalAmount = initialAmount - budget.getAssignedAmountToCC();	
	        totalAmountP = (totalAmount * 100) / initialAmount;
	    } else {	        
	        totalAmount = costsCentre.getBudget().getInitialAmount();
	        totalAmountP = costsCentre.getBudget().getInitialAmountP();
		limitAmount = budget.getInitialAmount() - budget.getAssignedAmountToCC() + costsCentre.getBudget().getInitialAmount(); 
	        isNew = false;
	    }
	    expTypeExist = null;
	} else {
	    name = expenditureAccount.getName();
	    initialAmount = expenditureAccount.getInitialAmount();
	    limitAmount = initialAmount;
	    totalAmount = initialAmount - expenditureAccount.getAssignedAmountToCC();
	    expTypeExist = costsCentre.getBudget().getExpenditureAccount(expenditureAccount.getIDExpenditureAccount());
	    totalAmountP = (totalAmount * 100) / initialAmount;
	    isNew = true;
	    if (expTypeExist != null) {
	        totalAmount = expTypeExist.getInitialAmount();
	        totalAmountP = expTypeExist.getInitialAmountP();
	        limitAmount = expenditureAccount.getInitialAmount() - expenditureAccount.getAssignedAmountToCC() + expTypeExist.getInitialAmount();
		cbValueBlock.setSelectedID(expTypeExist.getValueBlock());
	        isNew = false;
	    }
	}
	
	tfCostsCentre.setValue(costsCentre.getName());
	tfExpenditureAccount.setValue(name);
	tfExpenditureAccountAmountLimit.setValue(limitAmount);
	tfInitialAmount.setValue(totalAmount);
	tfInitialAmountP.setValue(totalAmountP);
	tfInitialAmountP.getTextField().addKeyListener(new PercentageToValueListen(tfInitialAmount, initialAmount));
	tfInitialAmount.getTextField().addKeyListener(new ValueToPercentageListen(tfInitialAmountP, initialAmount));
    }

    private void assignAmount() {
	boolean approvedAmount = true;
	double totalAmount = 0;
	double newAmount = tfInitialAmount.getAmount();
	double newAssignedAmountToCC = 0;
	double limitAmount = tfExpenditureAccountAmountLimit.getAmount();
	
	if (expenditureAccount == null){
	    if(costsCentre.getBudget().getInitialAmount() == 0){
		totalAmount = budget.getInitialAmount() - budget.getAssignedAmountToCC();
		newAssignedAmountToCC = budget.getAssignedAmountToCC() + newAmount;
	    } else {
	        totalAmount = costsCentre.getBudget().getInitialAmount() - costsCentre.getBudget().getAssignedAmountToCC();
	        newAssignedAmountToCC = costsCentre.getBudget().getAssignedAmountToCC() + newAmount;
	    }
	} else {
	    totalAmount = expenditureAccount.getInitialAmount() - expenditureAccount.getAssignedAmountToCC();	    
	    newAssignedAmountToCC = expenditureAccount.getAssignedAmountToCC() + newAmount;
	}

	if (expTypeExist != null) {
	    totalAmount = expTypeExist.getInitialAmount();
	    newAssignedAmountToCC = expenditureAccount.getAssignedAmountToCC() - totalAmount + newAmount;
	}
	
	if (costsCentre.getBudget().isIsAssigned()){
	    if (isNew && newAmount > costsCentre.getBudget().getWOutAssigned()){
		approvedAmount = false;	        
	    } else if (!isNew && newAmount > costsCentre.getBudget().getWOutAssigned() + totalAmount){
	        approvedAmount = false;
	    }
	}
	
	if ((newAmount <= limitAmount || newAmount <= totalAmount) && approvedAmount){
	    if (expenditureAccount != null){
		ExpenditureAccount newExpenditureAccount = new ExpenditureAccount();
		newExpenditureAccount.setName(expenditureAccount.getName());
		newExpenditureAccount.setClosed(false);
		newExpenditureAccount.setInitialAmount(tfInitialAmount.getAmount());
		newExpenditureAccount.setInitialAmountP(tfInitialAmountP.getAmount());
		newExpenditureAccount.setEndDate(Proced.setFormatDate(tfEndDate.getString(), false));
		newExpenditureAccount.setStartDate(Proced.setFormatDate(tfStartDate.getString(), false));
		newExpenditureAccount.setIDExpenditureAccount(expenditureAccount.getIDExpenditureAccount());
		newExpenditureAccount.setIdPeriodType(Integer.parseInt(cbPeriodType.getSelectedValue().toString()));
		newExpenditureAccount.setIdParent(expenditureAccount.getIdParent());
	        newExpenditureAccount.setValueBlock(Integer.parseInt(""+ cbValueBlock.getSelectedValue()));
		newExpenditureAccount.setModifiedAmount(tfModifiedAmount.getAmount());
		if (expenditureAccount.getIdParent() == 0) {
		    double amount = costsCentre.getBudget().getInitialAmount();
		    double amountP = costsCentre.getBudget().getInitialAmountP();
		    costsCentre.getBudget().setInitialAmount(newExpenditureAccount.getInitialAmount() + amount);
		    costsCentre.getBudget().setInitialAmountP(newExpenditureAccount.getInitialAmountP() + amountP);
		    costsCentre.getBudget().setAvailableAmount(newExpenditureAccount.getInitialAmount() + amount);
		    costsCentre.getBudget().setModifiedAmount(newExpenditureAccount.getModifiedAmount());
		}
		if (isNew) {
		    costsCentre.getBudget().addExpenditureAccount(newExpenditureAccount);
		}
		expenditureAccount.setAssignedAmountToCC(newAssignedAmountToCC);
		expenditureAccount.setAssignedAmountToCCp(newAssignedAmountToCC);
		
	        costsCentre.assignBudget(newExpenditureAccount, isNew, false);
		
	    } else {
	        costsCentre.getBudget().setInitialAmount(tfInitialAmount.getAmount());
	        costsCentre.getBudget().setInitialAmountP(tfInitialAmountP.getAmount());
	        costsCentre.getBudget().setAvailableAmount(tfInitialAmount.getAmount());
	        costsCentre.getBudget().setModifiedAmount(tfModifiedAmount.getAmount());
	        costsCentre.getBudget().setEndDate(Proced.setFormatDate(tfEndDate.getString(), false));
	        costsCentre.getBudget().setStartDate(Proced.setFormatDate(tfStartDate.getString(), false));
	        costsCentre.getBudget().setIdPeriodType(Integer.parseInt(cbPeriodType.getSelectedValue().toString()));
		
	        costsCentre.assignBudget(null, isNew, true);
	    }
	    
	    parentList.refreshCostsCentreTree(expenditureAccount);
	    getParentInternalFrame().close();
	} else {
	    double amount = 0;
	    if (approvedAmount){
		amount = tfExpenditureAccountAmountLimit.getAmount();
	    } else if (isNew){
		amount = costsCentre.getBudget().getWOutAssigned();
	    } else {
	        amount = costsCentre.getBudget().getWOutAssigned() + totalAmount;
	    }
	    Advisor.messageBox("El Monto debe ser menor igual a "+ Format.money(amount), "Monto no válido");       
	}
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	assignAmount();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setParentList(BudgetCostsCentreTree parentList) {
	this.parentList = parentList;
    }

    public void setCostsCentre(CostsCentre costsCentre) {
	this.costsCentre = costsCentre;
	loadData();
    }

    public void setExpenditureAccount(ExpenditureAccount _expenditureAccount) {
	this.expenditureAccount = _expenditureAccount;
    }

    public void setBudget(Budget budget) {
	this.budget = budget;
    }

}
