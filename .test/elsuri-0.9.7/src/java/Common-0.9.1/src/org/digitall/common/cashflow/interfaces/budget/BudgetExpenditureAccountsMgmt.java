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
 * BudgetExpenditureAccountsMgmt.java
 *
 * */
package org.digitall.common.cashflow.interfaces.budget;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.cashflow.classes.Budget;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
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

public class BudgetExpenditureAccountsMgmt extends BasicPrimitivePanel {

    private BasicPanel panelData = new BasicPanel("Asignar Monto");
    private TFInput tfName = new TFInput(DataTypes.STRING, "ExpenditureAccount", false);
    private TFInput tfInitialAmount = new TFInput(DataTypes.MONEY, "AssignedAmount", false);
    private TFInput tfInitialAmountP = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfAssignedAmountToET = new TFInput(DataTypes.MONEY, "AssignedAmountToET", false);
    private TFInput tfAssignedAmountToETP = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfAvailableToAssign = new TFInput(DataTypes.MONEY, "NotAssigned", false);
    private TFInput tfAvailableToAssignP = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfModifiedAmount = new TFInput(DataTypes.MONEY, "ModifiedAmount", false);
    private TFInput tfInitialAmountParent = new TFInput(DataTypes.MONEY, "InitialAmount", false);
    private TFInput tfIntialAmountParentP = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfAvailableToAssignParent = new TFInput(DataTypes.MONEY, "NotAssigned", false);
    private TFInput tfAvailableToAssignParentP = new TFInput(DataTypes.PERCENT, "%", false);
    private AcceptButton btnAccept = new AcceptButton();
    private ExpenditureAccount expenditureAccountParent;
    private Budget budgetParent;
    private ExpenditureAccount expenditureAccount;
    private BudgetExpenditureAccountsTree parentTree;
    private CloseButton btnClose = new CloseButton();
    private TFInput tfStartDateParent = new TFInput(DataTypes.DATE, "StartDate", false);
    private TFInput tfEndDateParent = new TFInput(DataTypes.DATE, "EndDate", false);
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "StartDate", false);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE, "EndDate", false);
    private BasicPanel panelParent = new BasicPanel("Monto Disponible");
    private BasicPanel content = new BasicPanel();
    private boolean addAction = true;
    private CBInput cbPeriodType = new CBInput(CachedCombo.PERIODTYPES_LIST, "PeriodType");
    private double totalAmount = 0;
    private CBInput cbValueBlock = new CBInput(CachedCombo.VALUEBLOCK, "ValueBlock");

    public BudgetExpenditureAccountsMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(594, 300));
	tfInitialAmount.setBounds(new Rectangle(345, 30, 115, 35));
	tfModifiedAmount.setBounds(new Rectangle(20, 75, 115, 35));
	tfAssignedAmountToET.setBounds(new Rectangle(195, 75, 105, 35));
	tfName.setBounds(new Rectangle(20, 30, 285, 35));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	tfAvailableToAssign.setBounds(new Rectangle(410, 75, 115, 35));
	tfStartDateParent.setBounds(new Rectangle(390, 25, 90, 35));
	tfEndDateParent.setBounds(new Rectangle(490, 25, 90, 35));
	tfStartDate.setBounds(new Rectangle(145, 130, 90, 35));
	tfEndDate.setBounds(new Rectangle(245, 130, 90, 35));
	cbPeriodType.setBounds(new Rectangle(20, 130, 115, 35));
	tfInitialAmountParent.setBounds(new Rectangle(15, 25, 105, 35));
	tfIntialAmountParentP.setBounds(new Rectangle(125, 25, 50, 35));
	cbValueBlock.setBounds(new Rectangle(535, 30, 45, 35));
	tfInitialAmountP.setBounds(new Rectangle(465, 30, 50, 35));
	tfAssignedAmountToETP.setBounds(new Rectangle(315, 75, 50, 35));
	tfAvailableToAssignP.setBounds(new Rectangle(530, 75, 50, 35));
	tfAvailableToAssignParentP.setBounds(new Rectangle(310, 25, 50, 35));
	tfAvailableToAssignParent.setBounds(new Rectangle(200, 25, 105, 35));
	panelParent.setLayout(null);
	panelParent.setBounds(new Rectangle(2, 5, 590, 70));
	panelParent.add(tfIntialAmountParentP, null);
	panelParent.add(tfInitialAmountParent, null);
	panelParent.add(tfAvailableToAssignParentP, null);
	panelParent.add(tfAvailableToAssignParent, null);
	panelParent.add(tfEndDateParent, null);
	panelParent.add(tfStartDateParent, null);
	panelData.setLayout(null);
	panelData.setBounds(new Rectangle(2, 80, 590, 175));
	panelData.add(cbValueBlock, null);
	panelData.add(tfAvailableToAssignP, null);
	panelData.add(tfAssignedAmountToETP, null);
	panelData.add(tfInitialAmountP, null);
	panelData.add(cbPeriodType, null);
	panelData.add(tfEndDate, null);
	panelData.add(tfStartDate, null);
	panelData.add(tfName, null);
	panelData.add(tfAssignedAmountToET, null);
	panelData.add(tfInitialAmount, null);
	panelData.add(tfModifiedAmount, null);
	panelData.add(tfAvailableToAssign, null);
	content.setLayout(null);
	content.add(panelData, null);
	content.add(panelParent, null);
	this.add(content, null);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	cbPeriodType.autoSize();
	cbValueBlock.autoSize();
	tfInitialAmountParent.setEnabled(false);
	tfIntialAmountParentP.setEnabled(false);
	tfAvailableToAssignParent.setEnabled(false);
	tfAvailableToAssignParentP.setEnabled(false);
	tfStartDateParent.setEnabled(false);
	tfEndDateParent.setEnabled(false);
	tfAvailableToAssign.setEnabled(false);
	tfAvailableToAssignP.setEnabled(false);
	tfAssignedAmountToET.setEnabled(false);
	tfAssignedAmountToETP.setEnabled(false);
	btnAccept.setToolTipText("Aceptar");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("As.TG: ($) Asignado en Tipos de Gastos  -  S/A: ($) Sin Asignar");
    }

    private void loadBudgetExpenditureAccount() {
	double percentage = ((expenditureAccount.getInitialAmount() - expenditureAccount.getAssignedAmountToET()) * 100) / expenditureAccount.getInitialAmount();
	if (expenditureAccount.getInitialAmount() == 0) {
	    percentage = 0;
	}
	tfAvailableToAssign.setValue(expenditureAccount.getInitialAmount() - expenditureAccount.getAssignedAmountToET());
	tfAvailableToAssignP.setValue(percentage);
	tfAssignedAmountToET.setValue(expenditureAccount.getAssignedAmountToET());
	tfAssignedAmountToETP.setValue(expenditureAccount.getAssignedAmountToETp());
	tfName.setValue(expenditureAccount.getName());
	tfName.setEnabled(false);
	tfStartDate.setValue(Proced.setFormatDate(expenditureAccount.getStartDate(), true));
	tfEndDate.setValue(Proced.setFormatDate(expenditureAccount.getEndDate(), true));
	cbValueBlock.setSelectedID("" + expenditureAccount.getValueBlock());
	if (expenditureAccountParent != null) {
	    totalAmount = expenditureAccountParent.getInitialAmount();
	    panelParent.setTitle(expenditureAccountParent.getName());
	    tfInitialAmountParent.setValue(expenditureAccountParent.getInitialAmount());
	    tfIntialAmountParentP.setValue(expenditureAccountParent.getInitialAmountP());
	    tfAvailableToAssignParent.setValue(expenditureAccountParent.getInitialAmount() - expenditureAccountParent.getAssignedAmountToET());
	    percentage = ((expenditureAccountParent.getInitialAmount() - expenditureAccountParent.getAssignedAmountToET()) * 100) / expenditureAccountParent.getInitialAmount();
	    tfAvailableToAssignParentP.setValue(percentage);
	    tfStartDateParent.setValue(Proced.setFormatDate(expenditureAccountParent.getStartDate(), true));
	    tfEndDateParent.setValue(Proced.setFormatDate(expenditureAccountParent.getEndDate(), true));
	} else if (!budgetParent.isClosed()){
	    totalAmount = budgetParent.getInitialAmount();
	    panelParent.setTitle(budgetParent.getName());
	    tfInitialAmountParent.setValue(budgetParent.getInitialAmount());
	    tfIntialAmountParentP.setValue(100.0);
	    tfAvailableToAssignParent.setValue(budgetParent.getInitialAmount() - budgetParent.getAssignedAmountToET());
	    percentage = ((budgetParent.getInitialAmount() - budgetParent.getAssignedAmountToET()) * 100) / budgetParent.getInitialAmount();
	    tfAvailableToAssignParentP.setValue(percentage);
	    tfStartDateParent.setValue(Proced.setFormatDate(budgetParent.getStartDate(), true));
	    tfEndDateParent.setValue(Proced.setFormatDate(budgetParent.getEndDate(), true));
	}
	tfInitialAmountP.getTextField().addKeyListener(new PercentageToValueListen(tfInitialAmount, totalAmount));
	tfInitialAmount.getTextField().addKeyListener(new ValueToPercentageListen(tfInitialAmountP, totalAmount));
	if (expenditureAccount.getInitialAmount() != 0) {
	    addAction = false;
	    tfInitialAmount.setValue(expenditureAccount.getInitialAmount());
	    tfInitialAmountP.setValue(expenditureAccount.getInitialAmountP());
	} else {
	    addAction = true;
	    if (expenditureAccountParent != null) {
		tfInitialAmount.setValue(expenditureAccountParent.getInitialAmount() - expenditureAccountParent.getAssignedAmountToET());
	    } else {
		tfInitialAmount.setValue(tfAvailableToAssignParent.getAmount());
	    }
	    tfInitialAmountP.setValue(tfAvailableToAssignParentP.getAmount());
	}
	tfModifiedAmount.setValue(expenditureAccount.getModifiedAmount());
	boolean closed = false;
	if (expenditureAccountParent != null) {
	    closed = expenditureAccountParent.isClosed();
	} else {
	    closed = budgetParent.isClosed();
	}
	if (closed) {
	    tfInitialAmount.setEnabled(false);
	    tfStartDate.setEnabled(false);
	    tfEndDate.setEnabled(false);
	    cbPeriodType.setEnabled(false);
	    tfModifiedAmount.setEnabled(true);
	    tfInitialAmountP.setEnabled(false);
	    cbValueBlock.setEnabled(false);
	} else {
	    tfModifiedAmount.setEnabled(false);
	}
    }

    private void assignAmount() {
	double amount = tfInitialAmount.getAmount();
	double amountP = tfInitialAmountP.getAmount();
	double newAmount = amount - expenditureAccount.getInitialAmount();
	double newAmountP = amountP - expenditureAccount.getInitialAmountP();
	String startDate = Proced.setFormatDate(tfStartDate.getString(), false);
	String endDate = Proced.setFormatDate(tfEndDate.getString(), false);
	String idPeriodType = cbPeriodType.getSelectedValue().toString();
	if (Proced.compareDates(Proced.setFormatDate(tfStartDate.getString(),false),Proced.setFormatDate(tfEndDate.getString(),false)) == 2) {
	    Advisor.messageBox("La fecha de fin no puede ser menor a la fecha de inicio","Error");
	}else{
	    double availableAmountToAssign = tfAvailableToAssignParent.getAmount();
	    if (amount <= availableAmountToAssign || newAmount <= availableAmountToAssign) {
		expenditureAccount.setStartDate(startDate);
		expenditureAccount.setEndDate(endDate);
		expenditureAccount.setIdPeriodType(Integer.parseInt(idPeriodType));
		expenditureAccount.setInitialAmount(amount);
		expenditureAccount.setInitialAmountP(amountP);
		expenditureAccount.setModifiedAmount(tfModifiedAmount.getAmount());
		expenditureAccount.setValueBlock(Integer.parseInt("" + cbValueBlock.getSelectedValue()));
		if (expenditureAccountParent != null) {
		    expenditureAccountParent.setAssignedAmountToET(expenditureAccountParent.getAssignedAmountToET() + newAmount);
		    expenditureAccountParent.setAssignedAmountToETp(expenditureAccountParent.getAssignedAmountToETp() + newAmountP);
		} else {
		    budgetParent.setAssignedAmountToET(budgetParent.getAssignedAmountToET() + newAmount);
		    budgetParent.setAssignedAmountToETp(budgetParent.getAssignedAmountToETp() + newAmountP);
		}
		parentTree.refreshNode(budgetParent, expenditureAccountParent, expenditureAccount);
		getParentInternalFrame().close();
	    } else if (newAmount > availableAmountToAssign) {
		Advisor.messageBox("El Importe a incrementar debe ser menor o igual a " + Format.money(availableAmountToAssign), "Importe no permitido");
	    } else {
		Advisor.messageBox("El Importe debe ser menor o igual a " + Format.money(availableAmountToAssign), "Importe no permitido");
	    }
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	assignAmount();
    }

    public void setExpenditureAccountParent(ExpenditureAccount _expenditureAccountParent) {
	this.expenditureAccountParent = _expenditureAccountParent;
	loadBudgetExpenditureAccount();
    }

    public void setBudgetParent(Budget _budgetParent) {
	this.budgetParent = _budgetParent;
	if (_budgetParent != null) {
	    if (_budgetParent.isClosed()) {
	        tfInitialAmount.setEnabled(false);
	        tfStartDate.setEnabled(false);
	        tfEndDate.setEnabled(false);
	        cbPeriodType.setEnabled(false);
	        tfModifiedAmount.setEnabled(true);
	        tfInitialAmountP.setEnabled(false);
	        cbValueBlock.setEnabled(false);
	    } else {
	        tfModifiedAmount.setEnabled(false);
	    }
	}
    }

    public void setExpenditureAccount(ExpenditureAccount _expenditureAccount) {
	this.expenditureAccount = _expenditureAccount;
    }

    public void setParentTree(BudgetExpenditureAccountsTree parentTree) {
	this.parentTree = parentTree;
    }

}
