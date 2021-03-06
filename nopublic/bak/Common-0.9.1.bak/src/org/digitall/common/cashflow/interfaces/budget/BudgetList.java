package org.digitall.common.cashflow.interfaces.budget;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.Budget;
import org.digitall.common.cashflow.interfaces.cashmovement.CashMovementsList;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;

public class BudgetList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 242, 107, 109, 103, 78, 103, 78 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Partidas Presupuestarias", dataRow);
    private Budget selectedBudget;
    private AcceptButton btnAddExpType = new AcceptButton();
    private AcceptButton btnCostCentre = new AcceptButton();
    private BudgetExpenditureAccountsTree budgetExpenditureAccountsTree;
    private BudgetCostsCentresList budgetCostsCentresList;
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnModify = new ModifyButton();
    private AcceptButton btnCashMovements = new AcceptButton();

    public BudgetList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(870, 335));
	btnCashMovements.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnCashMovements_actionPerformed(e);
					}

				    }
	);
	btnCashMovements.setText("Movimientos\nde Fondos");
	btnCashMovements.setToolTipText("Abre la ventana de Movimientos de Fondos");
	btnAddExpType.setBounds(new Rectangle(130, 340, 130, 25));
	btnAddExpType.setText("Distribuir por\nTipos de Gastos");
	btnAddExpType.setToolTipText("Abre la ventana de Distribuci??n por Tipos de Gastos");
	btnAddExpType.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnAddExpType_actionPerformed(e);
				     }

				 }
	);
	btnCostCentre.setText("Asignar por\nCentros de Costos");
	btnCostCentre.setToolTipText("Abre la ventana de Distribuci??n por Centros de Costos");
	btnCostCentre.setBounds(new Rectangle(5, 340, 120, 25));
	btnCostCentre.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnCostCentre_actionPerformed(e);
				     }

				 }
	);
	btnAdd.setBounds(new Rectangle(700, 340, 40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	btnModify.setBounds(new Rectangle(745, 340, 75, 25));
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
	listPanel.setBounds(new Rectangle(5, 0, 860, 330));
	this.addButton(btnModify);
	this.addButton(btnAdd);
	this.add(listPanel, BorderLayout.CENTER);
	setHeaderList();
	btnModify.setEnabled(false);
	btnCostCentre.setEnabled(false);
	btnAddExpType.setEnabled(false);
	btnModify.setToolTipText("Modificar la Partida Presupuestaria seleccionada");
	btnAdd.setToolTipText("Agregar una nueva Partida Presupuestaria");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnAddExpType);
	getParentInternalFrame().getGeneralButtons().addButton(btnCostCentre);
	getParentInternalFrame().getGeneralButtons().addButton(btnCashMovements);
	getParentInternalFrame().setInfo("Listado de Partidas Presupuestarias");
	getParentInternalFrame().setToolTipText("<html><p><font size=5><u>Administraci??n de Partidas Presupuestarias</p>\n" + 
	"  * Definici??n de una o varias Partidas Presupuestarias (anuales, mensuales, etc.) y las Cuentas Bancarias relacionadas<br>" + 
	"  * Ventana de informaci??n de los montos m??s importantes (inicial, ejecutado y saldo)<br>" + 
	"  * Modificaci??n de las Partidas parciales o totales<br>" + 
	"  * Distribuci??n de una Partida Presupuestaria en los diferentes Tipos de Gastos por monto o porcentaje, con la posibilidad de fijar uno de ellos<br>" + 
	"  * Generaci??n de reportes acorde a las necesidades de la Organizaci??n</html>");
	///getParentInternalFrame().getGeneralButtons().addButton(btnExpType);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Partida");
	headerList.addElement("($) Presupuesto");
	headerList.addElement("($) Modificado");
	headerList.addElement("($) Ejecutado");
	headerList.addElement("% Ejecutado");
	headerList.addElement("($) Saldo");
	headerList.addElement("% Saldo");
	/** ($) Saldo Cta.Cte., no lo ponemos porque no tiene sentido para el usuario */
	headerList.addElement("*");
	/** ($) As.T.Gastos, no lo ponemos porque no tiene sentido para el usuario */
	headerList.addElement("*");
	headerList.addElement("*");
	/** ($) As.C.Costos, no lo ponemos porque no tiene sentido para el usuario */
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent me) {
					       loadBudget();
					       if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 2) {
						   int index = listPanel.getTable().rowAtPoint(me.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
						   loadBudgetCostCentre();
					       } else if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
						   loadBudgetExpenditureAccountTree();
					       }
					   }

				       }
	);
	listPanel.setParams("cashflow.getAllBudgets", "''", headerList);
    }

    public void refresh() {
	String params = "''";
	listPanel.refresh(params);
	btnModify.setEnabled(false);
	btnCostCentre.setEnabled(false);
	btnAddExpType.setEnabled(false);
    }

    private void loadBudget() {
	if (!dataRow.isEmpty()) {
	    selectedBudget = new Budget(Integer.parseInt(dataRow.elementAt(0).toString()));
	    selectedBudget.setName(dataRow.elementAt(1).toString());
	    selectedBudget.setInitialAmount(Double.parseDouble(dataRow.elementAt(2).toString()));
	    selectedBudget.setModifiedAmount(Double.parseDouble(dataRow.elementAt(3).toString()));
	    selectedBudget.setSpentAmount(Double.parseDouble(dataRow.elementAt(4).toString()));
	    selectedBudget.setSpentAmountP(Double.parseDouble(dataRow.elementAt(5).toString()));
	    selectedBudget.setAvailableAmount(Double.parseDouble(dataRow.elementAt(6).toString()));
	    selectedBudget.setAccountsAmount(Double.parseDouble(dataRow.elementAt(8).toString()));
	    selectedBudget.setAssignedAmountToET(Double.parseDouble(dataRow.elementAt(9).toString()));
	    selectedBudget.setAssignedAmountToETp(Double.parseDouble(dataRow.elementAt(10).toString()));
	    selectedBudget.setAssignedAmountToCC(Double.parseDouble(dataRow.elementAt(11).toString()));
	    selectedBudget.setAssignedAmountToCCp(Double.parseDouble(dataRow.elementAt(12).toString()));
	    selectedBudget.setIdPeriodType(Integer.parseInt(dataRow.elementAt(13).toString()));
	    selectedBudget.setStartDate(Proced.setFormatDate(dataRow.elementAt(15).toString(), false));
	    selectedBudget.setEndDate(Proced.setFormatDate(dataRow.elementAt(16).toString(), false));
	    selectedBudget.setClosed(!dataRow.elementAt(17).toString().equals("--"));
	    selectedBudget.setDescription(dataRow.elementAt(18).toString());
	    btnModify.setEnabled(true);
	    btnCostCentre.setEnabled(true);
	    btnAddExpType.setEnabled(true);
	    btnModify.setToolTipText("<html><p align=center><u>Modificar Partida Presupuestaria</u><br>" + selectedBudget.getName());
	} else {
	    btnModify.setToolTipText("Modificar la Partida Presupuestaria seleccionada");
	}
    }

    private void loadBudgetExpenditureAccountTree() {
	if (!dataRow.isEmpty()) {
	    budgetExpenditureAccountsTree = new BudgetExpenditureAccountsTree();
	    budgetExpenditureAccountsTree.setBudget(selectedBudget);
	    budgetExpenditureAccountsTree.setParentList(this);
	    ExtendedInternalFrame budgetExpenditureAccountsTreeContainer = new ExtendedInternalFrame("Distribuci??n de la Partida " + selectedBudget.getName().toUpperCase() + " por Tipos de Gastos");
	    budgetExpenditureAccountsTreeContainer.setCentralPanel(budgetExpenditureAccountsTree);
	    budgetExpenditureAccountsTreeContainer.show();
	}
    }

    private void loadBudgetCostCentre() {
	if (!dataRow.isEmpty()) {
	    budgetCostsCentresList = new BudgetCostsCentresList();
	    budgetCostsCentresList.setBudget(selectedBudget);
	    ExtendedInternalFrame budgetCostsCentresListContainer = new ExtendedInternalFrame("Asignaci??n de la Partida " + selectedBudget.getName().toUpperCase() + " por Centros de Costos");
	    budgetCostsCentresListContainer.setCentralPanel(budgetCostsCentresList);
	    budgetCostsCentresListContainer.show();
	}
    }

    private void btnAddExpType_actionPerformed(ActionEvent e) {
	loadBudgetExpenditureAccountTree();
    }

    private void btnCostCentre_actionPerformed(ActionEvent e) {
	loadBudgetCostCentre();
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	BudgetMgmt budgetMgmt = new BudgetMgmt();
	budgetMgmt.setParentList(this);
	ExtendedInternalFrame budgetMgmtContainer = new ExtendedInternalFrame("Agregar Nueva Partida Presupuestaria");
	budgetMgmtContainer.setCentralPanel(budgetMgmt);
	budgetMgmtContainer.show();
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty()) {
	    BudgetMgmt budgetMgmt = new BudgetMgmt();
	    ExtendedInternalFrame budgetMgmtContainer = new ExtendedInternalFrame("Modificar Partida: " + selectedBudget.getName());
	    budgetMgmtContainer.setCentralPanel(budgetMgmt);
	    budgetMgmt.setBudget(selectedBudget);
	    budgetMgmt.setParentList(this);
	    budgetMgmtContainer.show();
	}
    }

    private void btnCashMovements_actionPerformed(ActionEvent e) {
	CashMovementsList cashMovementsList = new CashMovementsList();
	ExtendedInternalFrame cashMovementsListContainer = new ExtendedInternalFrame("Movimientos de Fondos");
	cashMovementsListContainer.setCentralPanel(cashMovementsList);
	cashMovementsListContainer.show();
    }

}
