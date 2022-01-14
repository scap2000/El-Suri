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
 * BudgetExpenditureAccountsTree.java
 *
 * */
package org.digitall.common.cashflow.interfaces.budget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import org.digitall.common.cashflow.classes.Budget;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.interfaces.budget.BudgetList;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountTreeLabel;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountTreeRender;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.environment.Environment;

public class BudgetExpenditureAccountsTree extends BasicPrimitivePanel {

    private JTree treeExpenditureAccount = new JTree();
    private BasicScrollPane jsptree = new BasicScrollPane();
    private BasicPanel panelTree = new BasicPanel();
    private BudgetList parentList;
    private Budget budget;
    private Budget budgetParent;
    private ExpenditureAccount selectedExpenditureAccount;
    private ExpenditureAccount expenditureAccountParent;
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnSave = new SaveDataButton();
    private AssignButton btnExpand = new AssignButton();
    private UnAssignButton btnCollapse = new UnAssignButton();
    private BudgetExpenditureAccountsMgmt budgetExpenditureAccountsMgmt;
    private PrintButton btnReport = new PrintButton();
    private PrintButton btnModifiedReport = new PrintButton();
    private BorderLayout borderLayout1 = new BorderLayout();

    public BudgetExpenditureAccountsTree() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(730, 455));
	panelTree.setBounds(new Rectangle(5, 0, 720, 450));
	panelTree.setLayout(borderLayout1);
	panelTree.setBorder(BorderPanel.getBorderPanel("Tipos de Gastos", Color.blue, Color.black));
	btnClose.setBounds(new Rectangle(685, 470, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
        btnReport.addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                    btnReport_actionPerformed(e);
                                }

                            }
        );
	btnSave.setBounds(new Rectangle(630, 470, 40, 25));
	btnSave.setSize(new Dimension(40, 25));
	btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }

			   }
	);
	btnExpand.setText("Expandir");
	btnExpand.setBounds(new Rectangle(585, 15, 65, 40));
	btnExpand.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnExpand_actionPerformed(e);
				 }

			     }
	);
	btnCollapse.setText("Contraer");
	btnCollapse.setBounds(new Rectangle(655, 15, 65, 40));
	btnCollapse.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnCollapse_actionPerformed(e);
				   }

			       }
	);
	this.addButton(btnClose);
	this.addButton(btnSave);
	this.addButton(btnReport);
	this.addButton(btnModifiedReport);
	this.addButton(btnCollapse);
	this.addButton(btnExpand);
	jsptree.getViewport().add(treeExpenditureAccount, null);
	panelTree.add(jsptree, BorderLayout.CENTER);
	this.add(panelTree, BorderLayout.CENTER);
	btnReport.setToolTipText("<html><p align=center><u>Reporte de la Partida Presupuestaria</u><br>Distribución por Tipos de Gastos</p></html>");
	btnModifiedReport.setToolTipText("<html><p align=center><u>Reporte de la Partida Presupuestaria MODIFICADA</u><br>Distribución por Tipos de Gastos</p></html>");
	btnModifiedReport.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     btnModifiedReport_actionPerformed(e);
					 }

				     }
	);
	btnExpand.setToolTipText("<html><p align=center>Muestra todos los ítems de la cuenta seleccionada</p></html>");
	btnCollapse.setToolTipText("<html><p align=center>Oculta todos los ítems de la cuenta seleccionada</p></html>");
        panelTree.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar Centro de Costos"));
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("A: ($) Asignado  -  As.TG: ($) Asignado en Tipos de Gastos  -  S/A: ($) Sin Asignar");
	getParentInternalFrame().setNeedSaving(true);
    }

    private TreeSelectionListener setTreeSelectionListener() {
	TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {

		public void valueChanged(TreeSelectionEvent evt) {
		    TreePath paths = evt.getPath();
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)paths.getLastPathComponent();
		    if (ExpenditureAccount.class.isInstance(node.getUserObject())) {
			selectedExpenditureAccount = (ExpenditureAccount)node.getUserObject();
			if (selectedExpenditureAccount.getIdBudget() == 0) {
			    selectedExpenditureAccount.setIdBudget(budget.getIdBudget());
			    selectedExpenditureAccount.setClosed(budget.isClosed());
			}
			DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode)node.getParent();
			if (ExpenditureAccount.class.isInstance(nodeParent.getUserObject())) {
			    expenditureAccountParent = (ExpenditureAccount)nodeParent.getUserObject();
			    expenditureAccountParent.setClosed(budget.isClosed());
			    //budgetParent = budget;
			} else {
			    budgetParent = (Budget)nodeParent.getUserObject();
			    expenditureAccountParent = null;
			}
		    } else {
			selectedExpenditureAccount = null;
			budgetParent = null;
			expenditureAccountParent = null;
		    }
		}

	    }
	;
	return treeSelectionListener;
    }

    private TreeWillExpandListener setTreeWillExpandListener() {
	TreeWillExpandListener treeWillExpandListener = new TreeWillExpandListener() {

		public void treeWillCollapse(TreeExpansionEvent treeExpansionEvent) throws ExpandVetoException {
		    TreePath path = treeExpansionEvent.getPath();
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
		    String data = node.getUserObject().toString();
		    System.out.println("WillCollapse: " + data);
		}

		public void treeWillExpand(TreeExpansionEvent treeExpansionEvent) throws ExpandVetoException {
		    TreePath path = treeExpansionEvent.getPath();
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
		    String data = node.getUserObject().toString();
		    System.out.println("WillExpand: " + data);
		}

	    }
	;
	return treeWillExpandListener;
    }

    public void drawTreeExpenditureAccount() {
	try {
	    treeExpenditureAccount = budget.getExpenditureAccountTree(false);
	    if (treeExpenditureAccount != null) {
		treeExpenditureAccount.setRowHeight(0);
		treeExpenditureAccount.setRowHeight(16);
		jsptree.getViewport().add(treeExpenditureAccount, null);
		treeExpenditureAccount.addTreeSelectionListener(setTreeSelectionListener());
		treeExpenditureAccount.setCellRenderer(new ExpenditureAccountTreeRender(ExpenditureAccountTreeLabel.TO_ASSIGN_BUDGET));
		treeExpenditureAccount.addMouseListener(new MouseAdapter() {

					      public void mouseClicked(MouseEvent e) {
						  if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						      if (selectedExpenditureAccount != null) {
							  loadBudgetExpenditureAccountMgmt();
							  treeExpenditureAccount.expandPath(treeExpenditureAccount.getSelectionPath());
						      }
						  }
					      }

					  }
		);
	    } else
		Advisor.messageBox(Environment.lang.getProperty("LoadError"), "Error");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void loadBudgetExpenditureAccountMgmt() {
	budgetExpenditureAccountsMgmt = new BudgetExpenditureAccountsMgmt();
	budgetExpenditureAccountsMgmt.setExpenditureAccount(selectedExpenditureAccount);
	budgetExpenditureAccountsMgmt.setBudgetParent(budgetParent);
	budgetExpenditureAccountsMgmt.setExpenditureAccountParent(expenditureAccountParent);
	budgetExpenditureAccountsMgmt.setParentTree(this);
	String title = "Asignar Monto al Tipo de Gasto";
	if (selectedExpenditureAccount != null) {
	    title = "Asignar Monto al Tipo de Gasto: " + selectedExpenditureAccount.getName().toUpperCase();
	}
	ExtendedInternalFrame budgetExpenditureAccountsMgmtContainer = new ExtendedInternalFrame(title);
	budgetExpenditureAccountsMgmtContainer.setCentralPanel(budgetExpenditureAccountsMgmt);
	budgetExpenditureAccountsMgmtContainer.show();
    }

    public void refreshNode(Budget _budgetParent, ExpenditureAccount _expenditureAccountParent, ExpenditureAccount _expenditureAccount) {
	expenditureAccountParent = _expenditureAccountParent;
	selectedExpenditureAccount = _expenditureAccount;
	budgetParent = _budgetParent;
	treeExpenditureAccount.updateUI();
	treeExpenditureAccount.setRowHeight(16);
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	saveData();
    }
    
    public boolean saveData() {
	return budget.saveAssignmentToExpenditureAccount();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close(true);
    }

    private void btnExpand_actionPerformed(ActionEvent e) {
	Proced.expandAll(treeExpenditureAccount, treeExpenditureAccount.getSelectionPath(), true);
    }

    private void btnCollapse_actionPerformed(ActionEvent e) {
	Proced.expandAll(treeExpenditureAccount, treeExpenditureAccount.getSelectionPath(), false);
    }

    public void setBudget(Budget budget) {
	this.budget = budget;
	drawTreeExpenditureAccount();
    }

    public void setParentList(BudgetList parentList) {
	this.parentList = parentList;
    }
    
    private void btnReport_actionPerformed(ActionEvent e) {
        BasicReport report = new BasicReport(BudgetExpenditureAccountsTree.class.getResource("xml/BudgetExpenditureAccountsBookReport.xml"));
        report.addTableModel("cashflow.getBudgetExpenditureAccountsBook", "" + budget.getIdBudget());
        report.setProperty("subtitle","Ejercicio Financiero " + Environment.currentYear);
        report.doReport();
    }

    private void btnModifiedReport_actionPerformed(ActionEvent e) {
	BasicReport report = new BasicReport(BudgetExpenditureAccountsTree.class.getResource("xml/ModifiedBudgetExpenditureAccountsBookReport.xml"));
	report.addTableModel("cashflow.getModifiedBudgetExpenditureAccountsBook", "" + budget.getIdBudget());
	report.setProperty("subtitle","Ejercicio Financiero (MODIFICADO) " + Environment.currentYear);
	report.doReport();
    }

}
