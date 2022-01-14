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
 * CCAssignBudgetMgmt.java
 *
 * */
package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.digitall.common.cashflow.classes.Budget;
import org.digitall.common.cashflow.classes.CostsCentre;
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
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.data.ValueToPercentageListen;
import org.digitall.lib.data.listeners.PercentageToValueListen;
import org.digitall.lib.sql.LibSQL;

public class CCAssignBudgetMgmt extends BasicPrimitivePanel{

    private BasicPanel panelData = new BasicPanel("Asignar Partida Presupuestaria");
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "StartDate", false);
    private TFInput tfInitialAmount = new TFInput(DataTypes.MONEY, "InitialAmount", false);
    private TFInput tfInitialAmountP = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfLimitAmount = new TFInput(DataTypes.MONEY, "AvailableAmounts", false);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE, "EndDate", false);
    private CBInput cbBudget = new CBInput(0, "Budgets");
    private CBInput cbPeriodType = new CBInput(CachedCombo.PERIODTYPES_LIST, "PeriodType");
    private BasicCheckBox chkActual = new BasicCheckBox();
    private AcceptButton btnAccept = new AcceptButton();
    private CostsCentre costsCentre;
    private CloseButton btnClose = new CloseButton();
    private boolean isNew = true;
    private TFInput tfCostsCentre = new TFInput(DataTypes.STRING,"CostsCentre", false);
    private Budget budget;

    public CCAssignBudgetMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(431, 235));
	tfStartDate.setBounds(new Rectangle(230, 150, 90, 35));
	tfInitialAmount.setBounds(new Rectangle(230, 110, 130, 35));
	tfEndDate.setBounds(new Rectangle(325, 150, 90, 35));
	chkActual.setText("Actual");
	chkActual.setBounds(new Rectangle(505, 195, 65, 20));
	cbPeriodType.setBounds(new Rectangle(15, 150, 200, 35));
	panelData.setPreferredSize(new Dimension(580, 195));
	panelData.setLayout(null);
	panelData.add(tfCostsCentre, null);
	panelData.add(tfInitialAmountP, null);
	panelData.add(tfLimitAmount, null);
	panelData.add(cbBudget, null);
	panelData.add(cbPeriodType, null);
	panelData.add(chkActual, null);
	panelData.add(tfEndDate, null);
	panelData.add(tfInitialAmount, null);
	panelData.add(tfStartDate, null);
	btnAccept.setPreferredSize(new Dimension(30,25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnClose.setPreferredSize(new Dimension(30,25)); 
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	
	this.addButton(btnClose);
	this.addButton(btnAccept);
	this.add(panelData, BorderLayout.CENTER);
	
	tfCostsCentre.setBounds(new Rectangle(15, 30, 400, 35));
	tfInitialAmountP.setBounds(new Rectangle(365, 110, 50, 35));
	tfLimitAmount.setBounds(new Rectangle(15, 110, 200, 35));
	cbBudget.setBounds(new Rectangle(15, 70, 400, 35));
	cbPeriodType.autoSize();
	cbBudget.autoSize();
	tfLimitAmount.setEnabled(false);
	tfCostsCentre.setEnabled(false);
	chkActual.setVisible(false);
        panelData.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar Centro de Costos"));
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Seleccione una Partida y asigne el monto inicial");
    }

    private void loadBudgetCombo(){
	cbBudget.loadJCombo(LibSQL.exFunction("cashflow.getAllBudgetsByCostCentre",""+ costsCentre.getIdCostCentre()));
	cbBudget.getCombo().addItemListener(new ItemListener() {
	    public void itemStateChanged (ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    loadAmounts();
		}
	    }
	});
	loadAmounts();
    }

    private void loadAmounts(){
	double initialAmount = 0;
	double limitAmount = 0;
	budget = new Budget(Integer.parseInt(""+ cbBudget.getSelectedValue()));
	budget.retrieveData();
	limitAmount = budget.getInitialAmount() - budget.getAssignedAmountToCC();
	if (cbBudget.getSelectedValueRef().equals("0#0")){
	    initialAmount = budget.getInitialAmount();      
	    tfInitialAmountP.setValue(100 * limitAmount / budget.getInitialAmount());
	    tfInitialAmount.setValue(limitAmount);
	    isNew = true;
	    cbBudget.setEnabled(true);
	} else {
	    String[] values = cbBudget.getSelectedValueRef().toString().split("#");	    
	    initialAmount = Double.parseDouble(values[0]);
	    budget.setIdBudgetCostCentre(Integer.parseInt(values[1]));
	    limitAmount += initialAmount;
	    tfInitialAmountP.setValue(100 * initialAmount / budget.getInitialAmount());
	    tfInitialAmount.setValue(initialAmount>0?initialAmount:limitAmount);
	    isNew = false;
	    cbBudget.setEnabled(false);
	}
	cbPeriodType.setEnabled(false);
	tfStartDate.setValue(Proced.setFormatDate(budget.getStartDate(), true));
	tfEndDate.setValue(Proced.setFormatDate(budget.getEndDate(), true));
	tfStartDate.setEnabled(false);
	tfEndDate.setEnabled(false);
	tfLimitAmount.setValue(limitAmount);
	tfInitialAmountP.getTextField().addKeyListener(new PercentageToValueListen(tfInitialAmount, budget.getInitialAmount()));
	tfInitialAmount.getTextField().addKeyListener(new ValueToPercentageListen(tfInitialAmountP, budget.getInitialAmount()));
    }

    private void assignAmount() {
	double newAmount = tfInitialAmount.getAmount();
	double limitAmount = tfLimitAmount.getAmount();
	
	if (newAmount <= limitAmount && tfInitialAmountP.getAmount() <= 100.0) {
	    costsCentre.setBudget(budget);
	    costsCentre.getBudget().setInitialAmount(tfInitialAmount.getAmount());
	    costsCentre.getBudget().setInitialAmountP(tfInitialAmountP.getAmount());
	    costsCentre.getBudget().setAvailableAmount(tfInitialAmount.getAmount());
	    costsCentre.getBudget().setModifiedAmount(0);
	    costsCentre.getBudget().setEndDate(Proced.setFormatDate(tfEndDate.getString(), false));
	    costsCentre.getBudget().setStartDate(Proced.setFormatDate(tfStartDate.getString(), false));
	    costsCentre.getBudget().setIdPeriodType(Integer.parseInt(cbPeriodType.getSelectedValue().toString()));
	    
	    costsCentre.assignBudget(null, isNew, true);
	    getParentInternalFrame().close();
	} else {
	    Advisor.messageBox("<html><center>El Monto debe ser menor igual a "+ Format.money(limitAmount) + "\nEl porcentaje (total) debe ser menor o igual a 100.00 %\nPruebe a reducir el monto asignado de la misma partida a los otros centros de costos</center></html>", "Monto no válido");
	}
    }
    
    public void setCostsCentre(CostsCentre costsCentre) {
	this.costsCentre = costsCentre;
	tfCostsCentre.setValue(costsCentre.getName());
	loadBudgetCombo();
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	assignAmount();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    
}
