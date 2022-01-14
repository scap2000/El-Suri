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
 * CCExpenditureAccountsTree.java
 *
 * */
package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.interfaces.costscentre.CCAssignedAmountList;
import org.digitall.common.cashflow.interfaces.costscentre.CCGenerateBudgetMgmt;
import org.digitall.common.cashflow.interfaces.costscentre.CCList;
import org.digitall.common.cashflow.interfaces.costscentre.CCSpentAmountMain;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.environment.Environment;

public class CCExpenditureAccountsTree extends BasicPrimitivePanel {

    private CostsCentre costsCentre;
    private JTree treeExpenses = new JTree();
    private BasicScrollPane jspExpenses = new BasicScrollPane();
    private BasicPanel panelTree = new BasicPanel();
    private CloseButton btnClose = new CloseButton();
    private ExpenditureAccount.CCItem ccExpenditureAccount;
    private AcceptButton btnSpentAmountDetail = new AcceptButton();
    private AcceptButton btnAssignedAmountDetail = new AcceptButton();
    private CCList parentList;
    private AcceptButton btnGenerateBudget = new AcceptButton();
    private CCGenerateBudgetMgmt ccGenerateBudgetMgmt;
    //private VouchersMgmt vouchersMgmt;
    private CCSpentAmountMain ccSpentAmountMain;
    private CCAssignedAmountList ccAssignedAmountList;

    public CCExpenditureAccountsTree() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(720, 450));
	jspExpenses.setBounds(new Rectangle(10, 20, 690, 415));
	panelTree.setBounds(new Rectangle(5, 0, 710, 445));
	panelTree.setLayout(null);
	panelTree.setBorder(BorderPanel.getBorderPanel("Tipos de Gastos"));
	btnClose.setBounds(new Rectangle(675, 465, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bntClose_actionPerformed(e);
				}

			    }
	);
	btnSpentAmountDetail.setText("Detalle\n($) Ejecutado");
	btnSpentAmountDetail.addActionListener(new ActionListener() {

					    public void actionPerformed(ActionEvent e) {
						btnSpentAmountDetail_actionPerformed(e);
					    }

					}
	);
	btnAssignedAmountDetail.setText("Detalle\n($) Asignado");
	btnAssignedAmountDetail.addActionListener(new ActionListener() {

					       public void actionPerformed(ActionEvent e) {
						   btnAssignedAmountDetail_actionPerformed(e);
					       }

					   }
	);
	btnGenerateBudget.setText("Generar Partida\nPresupuestaria");
	btnGenerateBudget.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     btnGenerateBudget_actionPerformed(e);
					 }

				     }
	);
	jspExpenses.getViewport().add(treeExpenses, null);
	panelTree.add(jspExpenses, null);
	//this.add(btnGenerateBudget, null);
	this.add(btnAssignedAmountDetail, null);
	this.add(btnSpentAmountDetail, null);
	this.add(btnClose, null);
	this.add(panelTree, null);
	btnAssignedAmountDetail.setEnabled(false);
	btnSpentAmountDetail.setEnabled(false);
	btnAssignedAmountDetail.setToolTipText("Abre la ventana del detalle de los montos asignados para el Tipo de Gastos seleccionado");
	btnSpentAmountDetail.setToolTipText("Abre la ventana del detalle de los montos ejecutados para el Tipo de Gastos seleccionado");
	btnGenerateBudget.setToolTipText("Abre la ventana para generar automáticamente una nueva Partida Presupuestaria");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnAssignedAmountDetail);
	getParentInternalFrame().getGeneralButtons().addButton(btnSpentAmountDetail);
	getParentInternalFrame().getGeneralButtons().addButton(btnGenerateBudget);
	getParentInternalFrame().setClosable(true);
	getParentInternalFrame().setInfo("Haga doble click sobre un Tipo de Gasto para cargar un nuevo comprobante de gastos");
    }

    private TreeSelectionListener setTreeSelectionListener() {
	TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {

		public void valueChanged(TreeSelectionEvent evt) {
		    TreePath paths = evt.getPath();
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)paths.getLastPathComponent();
		    if (ExpenditureAccount.CCItem.class.isInstance(node.getUserObject())) {
			ccExpenditureAccount = (ExpenditureAccount.CCItem)node.getUserObject();
			btnAssignedAmountDetail.setEnabled(true);
			btnSpentAmountDetail.setEnabled(true);
		    } else {
			ccExpenditureAccount = null;
			btnAssignedAmountDetail.setEnabled(false);
			btnSpentAmountDetail.setEnabled(false);
		    }
		}

	    }
	;
	return treeSelectionListener;
    }

    public void drawTree() {
	try {
	    treeExpenses = costsCentre.getExpenditureAccountsTree();
	    if (treeExpenses != null) {
		treeExpenses.setRowHeight(20);
		jspExpenses.getViewport().add(treeExpenses, null);
		treeExpenses.addTreeSelectionListener(setTreeSelectionListener());
		treeExpenses.setCellRenderer(new CCExpenditureAccountTreeRender());
		treeExpenses.addMouseListener(new MouseAdapter() {

				    public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) {
					    if (ccExpenditureAccount != null) {
						//loadAddInvoice();
					    }
					} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
					    JTree tree = (JTree)e.getComponent();
					    TreePath parentpath = tree.getClosestPathForLocation(e.getX(), e.getY());
					    DefaultMutableTreeNode node = (DefaultMutableTreeNode)parentpath.getLastPathComponent();
					    if (ExpenditureAccount.CCItem.class.isInstance(node.getUserObject())) {
						tree.setSelectionPath(parentpath);
						ccExpenditureAccount = (ExpenditureAccount.CCItem)node.getUserObject();
						loadSpentAmount();
					    } else {
						ccExpenditureAccount = null;
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

    private void bntClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void loadAddInvoice() {
	/* NO PERMITIMOS LA CARGA DE COMPROBANTES DESDE AQUÍ YA QUE
	 * NECESITARÍAMOS HACER UN ASIENTO O NO GUARDAR LOS DATOS EN CashMovements */
	/*vouchersMgmt = new VouchersMgmt(CachedCombo.VOUCHERTYPE_INVOICES);
	vouchersMgmt.setCCExpenditureAccount(ccExpenditureAccount);
	vouchersMgmt.setParentTree(this);
	
	ExtendedInternalFrame vouchersMgmtContainer = new ExtendedInternalFrame("Comprobante de Gastos: "+ ccExpenditureAccount.getName().toUpperCase());
	vouchersMgmtContainer.setCentralPanel(vouchersMgmt);
	vouchersMgmtContainer.show();*/
    }

    private void loadSpentAmount() {
	ccSpentAmountMain = new CCSpentAmountMain(ccExpenditureAccount);
	ExtendedInternalFrame ccSpentAmountMainContainer = new ExtendedInternalFrame("Detalle de Gastos: " + ccExpenditureAccount.getName().toUpperCase());
	ccSpentAmountMainContainer.setCentralPanel(ccSpentAmountMain);
	ccSpentAmountMainContainer.show();
    }

    public void refreshExpenseNode(ExpenditureAccount.CCItem _obj) {
	ccExpenditureAccount = _obj;
	treeExpenses.updateUI();
	treeExpenses.setRowHeight(16);
    }

    private void btnSpentAmountDetail_actionPerformed(ActionEvent e) {
	loadSpentAmount();
    }

    private void btnAssignedAmountDetail_actionPerformed(ActionEvent e) {
	ccAssignedAmountList = new CCAssignedAmountList();
	ccAssignedAmountList.setCcExpenditureAccount(ccExpenditureAccount);
	ExtendedInternalFrame ccAssignedAmountListContainer = new ExtendedInternalFrame("Montos Asignados por Tipos de Gastos");
	ccAssignedAmountListContainer.setCentralPanel(ccAssignedAmountList);
	ccAssignedAmountListContainer.show();
    }

    private void btnGenerateBudget_actionPerformed(ActionEvent e) {
	ccGenerateBudgetMgmt = new CCGenerateBudgetMgmt();
	ccGenerateBudgetMgmt.setCostsCentre(costsCentre);
	ccGenerateBudgetMgmt.setParentTree(this);
	ExtendedInternalFrame ccGenerateBudgetMgmtConatiner = new ExtendedInternalFrame("Generar Partida Presupuestaria");
	ccGenerateBudgetMgmtConatiner.setCentralPanel(ccGenerateBudgetMgmt);
	ccGenerateBudgetMgmtConatiner.show();
    }

    public void setCostsCentre(CostsCentre costsCentre) {
	this.costsCentre = costsCentre;
	drawTree();
    }

    public void setParentList(CCList parentList) {
	this.parentList = parentList;
    }

    public void reload() {
	drawTree();
    }
}
