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
 * AmountsExpenditureAccountsTree.java
 *
 * */
package org.digitall.apps.cashflowclient.interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDesktopPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.ExpenditureAccount.CCItem;
import org.digitall.common.cashflow.interfaces.costscentre.CCAssignedAmountList;
import org.digitall.common.cashflow.interfaces.costscentre.CCExpenditureAccountTreeRender;
import org.digitall.common.cashflow.interfaces.costscentre.CCSpentAmountInvoicesList;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.environment.Environment;

public class AmountsExpenditureAccountsTree extends BasicContainerPanel {
    private CostsCentre costsCentre;
    private JTree treeExpenses = new JTree();
    private BasicScrollPane jspExpenses = new BasicScrollPane();
    private BasicPanel panelTree = new BasicPanel();
    private CloseButton bntClose = new CloseButton();
    private CFCFrameContainer parentContainer;
    //private CFCFrameContainer expenseContainer =  new CFCFrameContainer("Cargar Gasto");
    private CFCFrameContainer assignedDetailContainer =  new CFCFrameContainer("");
    private CFCFrameContainer expenseDetailContainer =  new CFCFrameContainer("");
    private ExpenditureAccount.CCItem objItemSelected;
    private JDesktopPane parentDesktop;
    private BasicButton btnSpentAmountDetail = new BasicButton();
    private BasicButton btnAssignedAmountDetail = new BasicButton();
    private CCAssignedAmountList assignedAmountDetailList;
    private CCSpentAmountInvoicesList expenseDetailList;
    
    public AmountsExpenditureAccountsTree() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(720, 495));
	jspExpenses.setBounds(new Rectangle(10, 20, 690, 415));
	panelTree.setBounds(new Rectangle(5, 0, 710, 445));
	panelTree.setLayout(null);
	panelTree.setBorder(BorderPanel.getBorderPanel("Tipos de Gastos"));
	//new Rectangle(0, 455, 720, 10));
	bntClose.setBounds(new Rectangle(675, 465, 40, 25));
	bntClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bntClose_actionPerformed(e);
				}

			    }
	);
	btnSpentAmountDetail.setBounds(new Rectangle(545, 465, 120, 25));
	btnSpentAmountDetail.setText("Detalle ($) Gastado");
	btnSpentAmountDetail.setMargin(new Insets(1, 1, 1, 1));
	btnSpentAmountDetail.setFont(new Font("Default", 0, 11));
	btnSpentAmountDetail.addActionListener(new ActionListener() {

					    public void actionPerformed(ActionEvent e) {
						btnSpentAmountDetail_actionPerformed(e);
					    }

					}
	);
	btnAssignedAmountDetail.setText("Detalle ($) Asignado");
	btnAssignedAmountDetail.setBounds(new Rectangle(405, 465, 130, 25));
	btnAssignedAmountDetail.setMargin(new Insets(1, 1, 1, 1));
	btnAssignedAmountDetail.setFont(new Font("Default", 0, 11));
	btnAssignedAmountDetail.addActionListener(new ActionListener() {

					       public void actionPerformed(ActionEvent e) {
						   btnAssignedAmountDetail_actionPerformed(e);
					       }

					   }
	);
	jspExpenses.getViewport().add(treeExpenses, null);
	panelTree.add(jspExpenses, null);
	this.add(btnAssignedAmountDetail, null);
	this.add(btnSpentAmountDetail, null);
	this.add(bntClose, null);
	 
	this.add(panelTree, null);	
    }
    
    private TreeSelectionListener setTreeSelectionListener() {
	TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
		public void valueChanged(TreeSelectionEvent evt) {
		    TreePath paths = evt.getPath();
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)paths.getLastPathComponent();
		    if (ExpenditureAccount.CCItem.class.isInstance(node.getUserObject())) {
			objItemSelected = (ExpenditureAccount.CCItem)node.getUserObject();                 
		    } else {
			objItemSelected = null;
		    }
		}

	    };
	return treeSelectionListener;
    }
    
    public void drawTree() {
	try {
	    if (costsCentre != null){
		treeExpenses = costsCentre.getExpenditureAccountsTree();
		if (treeExpenses != null) {
		    treeExpenses.setRowHeight(20);
		    jspExpenses.getViewport().add(treeExpenses, null);
		    treeExpenses.addTreeSelectionListener(setTreeSelectionListener());
		    treeExpenses.setCellRenderer(new CCExpenditureAccountTreeRender());
		    treeExpenses.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
				    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
					if (objItemSelected != null){
					    loadExpense(objItemSelected);  
					}
				    }
				}
			    });
		    costsCentre.getDataByID(costsCentre.getIdCostCentre());
		} else
		    Advisor.messageBox(Environment.lang.getProperty("LoadError"), "Error");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void loadExpense(ExpenditureAccount.CCItem _params){
	/*ccExpensesMgmt.setParentContainer(expenseContainer);
	ccExpensesMgmt.setObjItem(_params);
	ccExpensesMgmt.setParentTreeClient(this);
	expenseContainer.setPanel(ccExpensesMgmt);
	expenseContainer.setVisible(true);  */
    }

    public void setCostsCentre(CostsCentre costsCentre) {
	this.costsCentre = costsCentre;
	drawTree();
    }

    private void bntClose_actionPerformed(ActionEvent e) {
	parentContainer.setVisible(false);
    }

    private void addPanelDesktop(){
	parentDesktop.add(assignedDetailContainer);
	parentDesktop.add(expenseDetailContainer);
    }
    
    public void setParentContainer(CFCFrameContainer parentContainer) {
	this.parentContainer = parentContainer;
    }
    
    public void setParentDesktop(JDesktopPane parentDesktop) {
	this.parentDesktop = parentDesktop;
	addPanelDesktop();
    }

    private void btnAssignedAmountDetail_actionPerformed(ActionEvent e) {
	assignedDetailContainer.setTitle("Detalle de Asignacion de Partidas del "+ objItemSelected.getCostCentre().getName());
	    //REPARADO POR SANTIAGO!!!
	//assignedAmountDetailList.setParentContainer(assignedDetailContainer);
	//assignedAmountDetailList.setObjItem(objItemSelected);
	assignedDetailContainer.setPanel(assignedAmountDetailList);
	assignedDetailContainer.setVisible(true);
    }

    private void btnSpentAmountDetail_actionPerformed(ActionEvent e) {
	/*expenseDetailContainer.setTitle("Detalle de Gastos del "+ objItemSelected.getCostCentre().getName());
	expenseDetailList.setParentContainer(expenseDetailContainer);
	expenseDetailList.setObjItem(objItemSelected);
	expenseDetailContainer.setPanel(expenseDetailList);
	expenseDetailContainer.setVisible(true);*/
    }

}
