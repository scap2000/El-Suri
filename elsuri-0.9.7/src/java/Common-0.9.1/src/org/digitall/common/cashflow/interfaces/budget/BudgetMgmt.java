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
 * BudgetMgmt.java
 *
 * */
package org.digitall.common.cashflow.interfaces.budget;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.BankAccount;
import org.digitall.common.cashflow.classes.Budget;
import org.digitall.common.cashflow.classes.BudgetAccount;
import org.digitall.common.cashflow.interfaces.budget.BudgetList;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class BudgetMgmt extends BasicPrimitivePanel {

    private BasicPanel panelData = new BasicPanel();
    private TFInput tfInitialAmount = new TFInput(DataTypes.MONEY, "InitialAmount", true);
    private TFInput tfSpentAmount = new TFInput(DataTypes.MONEY, "SpentAmount", false);
    private TFInput tfAvailableAmount = new TFInput(DataTypes.MONEY, "AvailableAmount", false);
    private TFInput tfStartDate = new TFInput(DataTypes.SIMPLEDATE, "From", true);
    private TFInput tfEndDate = new TFInput(DataTypes.SIMPLEDATE, "To", true);
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", true);
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", true);
    private CBInput cbPeriodType = new CBInput(CachedCombo.PERIODTYPES_LIST, "PeriodType");
    private BasicCheckBox chkCloseBudget = new BasicCheckBox();
    private TFInput tfModifiedAmount = new TFInput(DataTypes.MONEY, "ModifiedAmount", false);
    private TFInput tfSpentAmountP = new TFInput(DataTypes.PERCENT, "%", false);
    private SaveDataButton btnAccept = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private Budget budget;
    private BudgetList parentList;
    private BasicPanel panelAccounts = new BasicPanel();
    private int[] sizeColumnList = { 262, 236 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Cuentas Corrientes", dataRow);
    private CBInput cbBankAccountsList = new CBInput(0, "Accounts");
    private AssignButton btnAssignBankAccount = new AssignButton(true);
    //private TFInput tfAmountP = new TFInput(DataTypes.PERCENT, "%", false);
    private BudgetAccount budgetAccount;
    //private TFInput tfAccountsAmount = new TFInput(DataTypes.MONEY, "AccountAvailableAmount", false);
    private BasicPanel content = new BasicPanel();
    //private CBInput cbValueBlock = new CBInput(CachedCombo.VALUEBLOCK, "ValueBlock");
    private int error = 0;
    private int camposVacios = 1;
    private int fechaBajaInvalida = 2;

    public BudgetMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(565, 515));
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
	panelData.setLayout(null);
	panelData.setBounds(new Rectangle(7, 5, 550, 195));
	tfModifiedAmount.setBounds(new Rectangle(10, 70, 155, 35));
	tfSpentAmountP.setBounds(new Rectangle(330, 70, 50, 35));
	cbPeriodType.setBounds(new Rectangle(10, 110, 130, 35));
	tfDescription.setBounds(new Rectangle(10, 150, 530, 35));
	tfName.setBounds(new Rectangle(10, 30, 340, 35));
	//tfAccountsAmount.setBounds(new Rectangle(425, 30, 110, 35));
	//panelData.add(tfAccountsAmount, null);
	//tfAccountsAmount.setEnabled(false);
	tfInitialAmount.setBounds(new Rectangle(385, 30, 160, 35));
	tfSpentAmount.setBounds(new Rectangle(170, 70, 155, 35));
	tfAvailableAmount.setBounds(new Rectangle(385, 70, 155, 35));
	tfStartDate.setBounds(new Rectangle(170, 110, 100, 35));
	tfEndDate.setBounds(new Rectangle(275, 110, 100, 35));
	chkCloseBudget.setText("Partida cerrada");
        panelData.add(tfSpentAmountP, null);
        panelData.add(tfModifiedAmount, null);
        panelData.add(tfName, null);
        panelData.add(tfDescription, null);
        panelData.add(cbPeriodType, null);
        panelData.add(chkCloseBudget, null);
        panelData.add(tfEndDate, null);
        panelData.add(tfAvailableAmount, null);
        panelData.add(tfSpentAmount, null);
        panelData.add(tfInitialAmount, null);
        panelData.add(tfStartDate, null);
        panelAccounts.setLayout(null);
	chkCloseBudget.setBounds(new Rectangle(410, 125, 115, 20));
	panelAccounts.setBounds(new Rectangle(7, 205, 550, 270));
	//tfAmountP.setBounds(new Rectangle(345, 30, 60, 35));
	listPanel.setBounds(new Rectangle(5, 75, 540, 190));
	cbBankAccountsList.setBounds(new Rectangle(5, 30, 335, 35));
	btnAssignBankAccount.setBounds(new Rectangle(355, 40, 30, 25));
	btnAssignBankAccount.addActionListener(new ActionListener() {

					    public void actionPerformed(ActionEvent e) {
						btnAssignBankAccount_actionPerformed(e);
					    }

					}
	);
	panelAccounts.add(listPanel, null);
	/*
	panelAccounts.add(tfAmountP, null);
	panelAccounts.add(cbValueBlock, null);
	cbValueBlock.setBounds(new Rectangle(410, 30, 45, 35));
	cbValueBlock.autoSize();
	tfAmountP.setValue(100.0);
	tfAmountP.setBounds(new Rectangle(335, 30, 60, 35));
	*/
	panelAccounts.add(cbBankAccountsList, null);
	panelAccounts.add(btnAssignBankAccount, null);
	content.setLayout(null);
	content.add(panelData, null);
	content.add(panelAccounts, null);
	this.add(content, null);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	tfAvailableAmount.setEnabled(false);
	tfModifiedAmount.setEnabled(false);
	tfSpentAmount.setEnabled(false);
	tfSpentAmountP.setEnabled(false);
	//chkClosed.setVisible(false);
	cbPeriodType.autoSize();
	cbBankAccountsList.autoSize();
	setHeaderList();
	//btnAssignBankAccount.setEnabled(!cbBankAccountsList.getSelectedValue().equals("-1"));
	chkCloseBudget.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  chkActual_actionPerformed(e);
				      }

				  }
	);
	loadBankAccountsList();
	btnAssignBankAccount.setToolTipText("Asociar Cuenta Bancaria a la Partida Presupuestaria");
        panelData.setBorder(BorderPanel.getBorderPanel("Partida Presupuestaria"));
        panelAccounts.setBorder(BorderPanel.getBorderPanel("Cuentas Corrientes Asociadas"));
	tfStartDate.setValue("01/01/" + Environment.currentYear);
	tfEndDate.setValue("31/12/" + Environment.currentYear);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar\"");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("AccountNumber"));
	headerList.addElement("*");
	//headerList.addElement(Environment.lang.getProperty("%"));
	//headerList.addElement(Environment.lang.getProperty("AssignedAmount"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Bank"));
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent me) {
					       loadObject();
					       if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 2) {
						   int index = listPanel.getTable().rowAtPoint(me.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
					       } else if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
					       }
					   }

				       }
	);
	listPanel.setParams("cashflow.getAllBudgetBankAccounts", "-1,''", headerList);
    }

    public void refresh() {
	if (budget != null) {
	    String params = budget.getIdBudget() + ",''";
	    listPanel.refresh(params);
	    loadBankAccountsList();
	}
    }

    private void loadObject() {
	if (!dataRow.isEmpty()) {
	    budgetAccount = new BudgetAccount();
	    budgetAccount.setBudget(new Budget(Integer.parseInt("" + dataRow.elementAt(0))));
	    budgetAccount.setBankAccount(new BankAccount(Integer.parseInt("" + dataRow.elementAt(1))));
	    budgetAccount.setAmountP(Double.parseDouble("" + dataRow.elementAt(4)));
	    cbBankAccountsList.setSelectedID(dataRow.elementAt(1));
	    //tfAmountP.setValue(Double.parseDouble(dataRow.elementAt(4).toString()));
	    //cbValueBlock.setSelectedID(dataRow.elementAt(6));
	}
    }

    private void loadData() {
	tfName.setValue(budget.getName());
	tfInitialAmount.setValue(budget.getInitialAmount());
	tfSpentAmount.setValue(budget.getSpentAmount());
	tfSpentAmountP.setValue(budget.getSpentAmountP());
	tfAvailableAmount.setValue(budget.getAvailableAmount());
	tfModifiedAmount.setValue(budget.getModifiedAmount());
	cbPeriodType.setSelectedID("" + budget.getIdPeriodType());
	tfStartDate.setValue(Proced.setFormatDate(budget.getStartDate(), true));
	tfEndDate.setValue(Proced.setFormatDate(budget.getEndDate(), true));
	chkCloseBudget.setSelected(budget.isClosed());
	tfDescription.setValue(budget.getDescription());
	if (budget.isClosed()) {
	    this.getParentInternalFrame().setInfo("PARTIDA PRESUPUESTARIA BLOQUEADA");
	    chkCloseBudget.setEnabled(false);
	    tfName.setEnabled(false);
	    tfStartDate.setEnabled(false);
	    tfEndDate.setEnabled(false);
	    tfDescription.setEnabled(false);
	    tfModifiedAmount.setEnabled(true);
	    cbPeriodType.setEnabled(false);
	    tfInitialAmount.setEnabled(false);
	    btnAssignBankAccount.setEnabled(false);
	    cbBankAccountsList.setEnabled(false);
	} else {
	    this.getParentInternalFrame().setInfo("Modifique los datos y presione el botón \"Grabar\"");
	}
	//tfAccountsAmount.setText(Format.moneyWithOutSign(budget.getAccountsAmount()));
    }

    public void setBudget(Budget budget) {
	this.budget = budget;
	loadData();
	refresh();
    }

    private int saveBudgetData() {
	if (control()) {
	    if (budget == null) {
		budget = new Budget();
	    }
	    budget.setName(tfName.getString());
	    budget.setStartDate(Proced.setFormatDate(tfStartDate.getString(), false));
	    budget.setEndDate(Proced.setFormatDate(tfEndDate.getString(), false));
	    budget.setInitialAmount(tfInitialAmount.getAmount());
	    budget.setModifiedAmount(tfModifiedAmount.getAmount());
	    budget.setIdPeriodType(Integer.parseInt(cbPeriodType.getSelectedValue().toString()));
	    budget.setClosed(chkCloseBudget.isSelected());
	    budget.setDescription(tfDescription.getString());
	    int result = -1;
	    if (budget.getIdBudget() == -1) {
		result = budget.addData();
	    } else {
		result = budget.setData();
	    }
	    return result;
	} else {
	    mostrarError();
	    return -1;
	}
    }
    
    private void mostrarError(){
	switch (error)  {
	    case 1: {
		    Advisor.messageBox("Todos los campos obligatorios no pueden estar vacíos","Error");
		}
	    break;
	    case 2: {
	            Advisor.messageBox("La fecha de fin no puede ser menor a la fecha de baja","Error");
	        }
	    break;
	}
	
    }
    
    private boolean control(){
	boolean resultado = true;
	System.out.println("nombre: "+ tfName.getString().length());
	System.out.println("Inicial: "+ tfInitialAmount.getAmount());
	System.out.println("inicial: "+ tfStartDate.getString().length());
	System.out.println("fin: "+ tfEndDate.getString().length());
	System.out.println("descripcion: "+ tfDescription.getString().length());
	if (!(tfName.getString().length() > 0 && tfInitialAmount.getAmount() > 0 && tfStartDate.getString().length() > 0 && tfEndDate.getString().length() > 0 && tfDescription.getString().length() > 0) ){
	    resultado = false;
	    error = camposVacios;
	}else{
	    if (Proced.compareDates(Proced.setFormatDate(tfStartDate.getString(),false),Proced.setFormatDate(tfEndDate.getString(),false)) == 2) {
		resultado = false;
	        error = fechaBajaInvalida;
	    }     
	}
	return(resultado);
    }

    public boolean saveData() {
	int result = saveBudgetData();
	if (result >= 0) {
	    parentList.refresh();
	    getParentInternalFrame().close();
	    return true;
	} else {
	    return false;
	}
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	saveData();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parentList.refresh();
	getParentInternalFrame().close();
    }

    public void setParentList(BudgetList parentList) {
	this.parentList = parentList;
    }

    private void btnAssignBankAccount_actionPerformed(ActionEvent e) {
	int result = -1;
	//double percentLimit = Double.parseDouble(cbBankAccountsList.getSelectedValueRef().toString());
	/*if (tfAmountP.getAmount() > percentLimit) {
	    Advisor.messageBox("El porcentaje no puede superar el " + Format.toDouble(percentLimit) + "% de la cuenta bancaria", "Error");
	} else {*/
	if (Advisor.question("Asignar cuenta bancaria", "Si decide asignar una cuenta bancaria, se grabará TODA la información de la partida\n¿Está seguro?")) {
	    if (budget == null) {
		result = saveBudgetData();
	    } else {
		result = 0;
	    }
	    if (result >= 0) {
		if (budgetAccount == null) {
		    budgetAccount = new BudgetAccount();
		}
		budgetAccount.setBudget(budget);
		budgetAccount.setBankAccount(new BankAccount(Integer.parseInt("" + cbBankAccountsList.getSelectedValue())));
		//budgetAccount.setAmountP(tfAmountP.getAmount());
		//budgetAccount.setValueBlock(Integer.parseInt("" + cbValueBlock.getSelectedValue()));
		if (budgetAccount.saveData() >= 0) {
		    //btnAssignAccount.setVisible(false);
		    //cbAccount.setEnabled(false);
		    //tfAccountsAmount.setText(Format.moneyWithOutSign(budget.updateAccountsAmount()));
		    btnAccept.setVisible(true);
		    refresh();
		} else {
		    Advisor.messageBox("Error al grabar los datos de la cuenta bancaria", "Error");
		}
	    } else {
		Advisor.messageBox("Error al grabar los datos de la partida presupuestaria", "Error");
	    }
	    // }
	}
    }

    private void loadBankAccountsList() {
	String params = "-1";
	if (budget != null) {
	    params = String.valueOf(budget.getIdBudget());
	}
	cbBankAccountsList.loadJCombo("cashflow.getBudgetFreeBankAccounts", params);
        if (budget != null) {
            btnAssignBankAccount.setEnabled(cbBankAccountsList.getSelectedIndex() != -1 && !budget.isClosed());
        }
    }

    private void chkActual_actionPerformed(ActionEvent e) {
	if (chkCloseBudget.isSelected()) {
	    if (!Advisor.question("Cerrar Partida", "Esta opción bloqueará para siempre la partida presupuestaria\n¿Está seguro?")) {
		chkCloseBudget.setSelected(false);
	    }
	}
    }

}
