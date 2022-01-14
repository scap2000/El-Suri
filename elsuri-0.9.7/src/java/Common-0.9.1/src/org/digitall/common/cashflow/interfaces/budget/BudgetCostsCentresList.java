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
 * BudgetCostsCentresList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.budget;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.interfaces.budget.BudgetCostsCentreTree;
import org.digitall.common.cashflow.classes.Budget;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesReceiveList;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

//

public class BudgetCostsCentresList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 163, 111, 50, 96, 50, 83, 50, 75, 83, 83 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Centros de Costos", dataRow);
    private Vector headerList = new Vector();
    private AddButton btnAssignBudget = new AddButton();
    private CloseButton btnClose = new CloseButton();
    private Budget budget;
    private BasicPanel panelBudget = new BasicPanel("Budget");
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "StartDate", false);
    private TFInput tfInitialAmount = new TFInput(DataTypes.MONEY, "InitialAmount", false);
    private TFInput tfSpentAmount = new TFInput(DataTypes.MONEY, "SpentAmount", false);
    private TFInput tfAvailableAmount = new TFInput(DataTypes.MONEY, "AvailableAmount", false);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE, "EndDate", false);
    private TFInput tfSpentAmountP = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfModifiedAmount = new TFInput(DataTypes.MONEY, "ModifiedAmount", false);
    private BudgetCostsCentreTree budgetCostCentre;
    private BasicPanel content = new BasicPanel();
    private PrintButton btnPrint = new PrintButton();

    public BudgetCostsCentresList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(760, 413));
	listPanel.setBounds(new Rectangle(5, 85, 750, 300));
	btnAssignBudget.setBounds(new Rectangle(665, 405, 40, 25));
	btnAssignBudget.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnAdd_actionPerformed(e);
		    }

		});
        btnPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                btnPrint_actionPerformed(e);
            }
        }
        );
	btnClose.setBounds(new Rectangle(715, 405, 40, 25));
	btnClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnClose_actionPerformed(e);
		    }

		});
	panelBudget.setPreferredSize(new Dimension(750, 70));
	panelBudget.setBounds(new Rectangle(5, 5, 750, 70));
	panelBudget.setLayout(null);
	tfInitialAmount.setBounds(new Rectangle(5, 25, 115, 35));
	tfAvailableAmount.setBounds(new Rectangle(420, 25, 115, 35));
	tfSpentAmount.setBounds(new Rectangle(125, 25, 115, 35));
	tfStartDate.setBounds(new Rectangle(540, 25, 90, 35));
	tfEndDate.setBounds(new Rectangle(635, 25, 90, 35));
	tfSpentAmountP.setBounds(new Rectangle(245, 25, 50, 35));
	tfModifiedAmount.setBounds(new Rectangle(300, 25, 115, 35));
	panelBudget.add(tfModifiedAmount, null);
	panelBudget.add(tfSpentAmountP, null);
	panelBudget.add(tfEndDate, null);
	panelBudget.add(tfStartDate, null);
	panelBudget.add(tfSpentAmount, null);
	panelBudget.add(tfAvailableAmount, null);
	panelBudget.add(tfInitialAmount, null);
	content.setLayout(new BorderLayout());
	content.setSize(new Dimension(662, 300));
	content.add(panelBudget, BorderLayout.NORTH);
	content.add(listPanel, BorderLayout.CENTER);
	setContent(content);
	
	this.addButton(btnClose);
	this.addButton(btnAssignBudget);
        this.addButton(btnPrint);
	 
	setHeaderList();
	btnPrint.setToolTipText("<html><p align=center><u>Reporte de la Partida Presupuestaria</u><br>Distribución por Centros de Costos</p></html>");
	btnAssignBudget.setToolTipText("Distribuir la Partida por Centros de Costos");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Asignación actual de la Partida por Centros de Costos");
    }

    private void loadBudgetData() {
	tfInitialAmount.setEnabled(false);
	tfAvailableAmount.setEnabled(false);
	tfModifiedAmount.setEnabled(false);
	tfSpentAmount.setEnabled(false);
	tfSpentAmountP.setEnabled(false);
	tfStartDate.setEnabled(false);
	tfEndDate.setEnabled(false);
	
	panelBudget.setTitle(budget.getName().toUpperCase());
	tfInitialAmount.setValue((budget.getInitialAmount()));
	tfAvailableAmount.setValue((budget.getAvailableAmount()));
	tfModifiedAmount.setValue((budget.getModifiedAmount()));
	tfSpentAmount.setValue((budget.getSpentAmount()));
	tfSpentAmountP.setValue((budget.getSpentAmountP()));
	tfStartDate.setValue(Proced.setFormatDate(budget.getStartDate(), true));
	tfEndDate.setValue(Proced.setFormatDate(budget.getEndDate(), true));
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Centro de Costo");
	headerList.addElement("($) Inicial");
	headerList.addElement("%");
	headerList.addElement("($) Ejecutado");
	headerList.addElement("%");
	headerList.addElement("($) Saldo");	
	headerList.addElement("%");
	headerList.addElement("*");
	headerList.addElement("Periodo");
	headerList.addElement("Desde");
	headerList.addElement("Hasta");
	headerList.addElement("*");
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter(){
	    public void mouseClicked(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
		    newAssignment();	
		}
	    }
	
	});
	listPanel.setParams("cashflow.getAllBudgetCostsCentres", "-1", headerList);
    }

    public void refresh() {
	String params = String.valueOf(budget.getIdBudget());
	listPanel.refresh(params);
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void newAssignment(){
	budgetCostCentre = new BudgetCostsCentreTree();
	budgetCostCentre.setBudgetExpTypes(budget);
	budgetCostCentre.setParentList(this);
	
	ExtendedInternalFrame budgetCostCentreContainer = new ExtendedInternalFrame("Asignar Partida");
	budgetCostCentreContainer.setCentralPanel(budgetCostCentre);
	budgetCostCentreContainer.show();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	newAssignment();
    }

    public void setBudget(Budget budget) {
	this.budget = budget;
	loadBudgetData();
	refresh();
    }
    
    private void btnPrint_actionPerformed(ActionEvent e){
        if (listPanel.getTable().getRowCount() != 0 )  {
            BasicReport report = new BasicReport(BudgetExpenditureAccountsTree.class.getResource("xml/BudgetCostsCentresReport.xml"));
            report.addTableModel("cashflow.getBudgetCostsCentresReport", "" + budget.getIdBudget());
            report.setProperty("title","Partida Presupuestaria: " + budget.getName());
            report.setProperty("subtitle","Ejercicio Financiero " + Environment.currentYear);
            report.doReport();
        } else {
            Advisor.messageBox("La Partida no está asignada a ningún Centro de Costos","Mensaje");
        }
    }

}
