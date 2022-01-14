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
