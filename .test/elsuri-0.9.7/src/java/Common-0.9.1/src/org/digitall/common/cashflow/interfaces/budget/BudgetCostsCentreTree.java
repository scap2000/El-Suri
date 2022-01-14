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
 * BudgetCostsCentreTree.java
 *
 * */
package org.digitall.common.cashflow.interfaces.budget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.digitall.common.cashflow.interfaces.budget.BudgetCostsCentreMgmt;
import org.digitall.common.cashflow.classes.Budget;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.CostsCentreGroups;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.interfaces.costscentre.CostsCentreTreeRender;
import org.digitall.common.cashflow.interfaces.budget.BudgetCostsCentresList;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountTreeLabel;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountTreeRender;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

//

public class BudgetCostsCentreTree extends BasicPrimitivePanel implements DropTargetListener {

    private JTree treeCC = new JTree();
    private BasicScrollPane jspTreeCC = new BasicScrollPane();
    private BasicPanel panelTreeCC = new BasicPanel();
    private ExpenditureAccount selectedExpenditureAccount;
    private BudgetCostsCentresList parentList;
    private Budget budget;
    private CostsCentreGroups costsCentreVector = new CostsCentreGroups();
     
    private CloseButton btnClose = new CloseButton();
    private BasicScrollPane jspTreeExpenditureAccount = new BasicScrollPane();
    private JTree treeExpenditureAccount = new JTree();
    private BasicPanel panelTreeET = new BasicPanel();
    private DefaultMutableTreeNode nodeCCTarget;
    private DefaultMutableTreeNode selectedNode;
    private BasicButton btnExpand = new BasicButton();
    private BasicButton btnCollapse = new BasicButton();
    private BasicLabel jLabel1 = new BasicLabel();
    private BasicLabel jLabel2 = new BasicLabel();
    private BudgetCostsCentreMgmt budgetCostsCentreMgmt;

    public BudgetCostsCentreTree() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(715, 565));
	panelTreeCC.setLayout(null);
	panelTreeCC.setBorder(BorderPanel.getBorderPanel("Partidas asignadas por Centro de Costo", Color.blue, Color.black));
	panelTreeCC.setSize(new Dimension(705, 270));
	panelTreeCC.setPreferredSize(new Dimension(1, 270));
	panelTreeET.setBorder(BorderPanel.getBorderPanel("Tipos de Gastos", Color.blue, Color.black));
	btnClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnClose_actionPerformed(e);
		    }

		});
	jspTreeCC.getViewport().add(treeCC, null);
	jspTreeCC.setBounds(new Rectangle(10, 20, 685, 220));
	jspTreeExpenditureAccount.setBounds(new Rectangle(10, 35, 685, 220));
	panelTreeCC.add(jLabel2, null);
	panelTreeCC.add(jspTreeCC, null);
	panelTreeET.add(jLabel1, null);
	panelTreeET.add(btnCollapse, null);
	panelTreeET.add(btnExpand, null);
	jspTreeExpenditureAccount.getViewport().add(treeExpenditureAccount, null);
	panelTreeET.add(jspTreeExpenditureAccount, null);
	panelTreeET.setLayout(null);
	panelTreeET.setSize(new Dimension(705, 350));
	panelTreeET.setPreferredSize(new Dimension(1, 270));
	btnExpand.setText("Expandir");
	btnExpand.setBounds(new Rectangle(525, 10, 81, 18));
	btnExpand.setFont(new Font("Default", 0, 11));
	btnExpand.setMargin(new Insets(1, 1, 1, 1));
	btnExpand.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnExpand_actionPerformed(e);
				 }

			     }
	);
	btnCollapse.setText("Contraer");
	btnCollapse.setBounds(new Rectangle(615, 10, 81, 18));
	btnCollapse.setFont(new Font("Default", 0, 11));
	btnCollapse.setMargin(new Insets(1, 1, 1, 1));
	btnCollapse.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnCollapse_actionPerformed(e);
				   }

			       }
	);
	jLabel1.setText("I: ($) Inicial  -  As.CC: ($) Asignado a Centros de Costos  -  S/A: ($) Sin Asignar");
	jLabel1.setBounds(new Rectangle(10, 265, 685, 15));
	jLabel1.setSize(new Dimension(685, 15));
	jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	jLabel2.setText("A: ($) Asignado  -  E: ($) Ejecutado  -  S: ($) Saldo - T.As: ($) Total Asignado");
	jLabel2.setBounds(new Rectangle(15, 245, 685, 15));
	jLabel2.setSize(new Dimension(685, 15));
	jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(panelTreeET, BorderLayout.CENTER);
	//this.add(btnClose, BorderLayout.SOUTH);
	this.add(panelTreeCC, BorderLayout.NORTH);
	treeExpenditureAccount.addMouseMotionListener(new MouseMotionListener() {

		    public void mouseMoved(MouseEvent e) {
		    }

		    public void mouseDragged(MouseEvent e) {
		    }

		});
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setClosable(true);
	getParentInternalFrame().setInfo("Arrastre un Tipo de Gasto desde la Partida al Centro de Costos");
    }
    
    public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {
    }

    public void dragOver(DropTargetDragEvent dropTargetDragEvent) {
	DropTargetContext dtc = dropTargetDragEvent.getDropTargetContext();
	JTree tree = (JTree)dtc.getComponent();
	Point pt = dropTargetDragEvent.getLocation();
	TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
	DefaultMutableTreeNode nodeTarget = (DefaultMutableTreeNode)parentpath.getLastPathComponent();

	if (CostsCentre.class.isInstance(nodeTarget.getUserObject())) {
	    tree.setSelectionPath(parentpath);
	    dropTargetDragEvent.acceptDrag(dropTargetDragEvent.getDropAction());
	} else {
	    dropTargetDragEvent.rejectDrag();
	}
    }

    public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {
    }

    public void drop(DropTargetDropEvent dropTargetDropEvent) {
	Point pt = dropTargetDropEvent.getLocation();
	DropTargetContext dtc = dropTargetDropEvent.getDropTargetContext();
	JTree tree = (JTree)dtc.getComponent();
	TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
	DefaultMutableTreeNode nodeTarget = (DefaultMutableTreeNode)parentpath.getLastPathComponent();
	nodeCCTarget = nodeTarget;
	try {
	    if (CostsCentre.class.isInstance(nodeTarget.getUserObject())) {
		budgetCostsCentreMgmt = new BudgetCostsCentreMgmt();
	        budgetCostsCentreMgmt.setExpenditureAccount(selectedExpenditureAccount);
	        budgetCostsCentreMgmt.setBudget(budget);
	        budgetCostsCentreMgmt.setCostsCentre((CostsCentre)nodeTarget.getUserObject());
	        budgetCostsCentreMgmt.setParentList(this);
		
		ExtendedInternalFrame budgetCostsCentreMgmtContainer = new ExtendedInternalFrame("Asignar monto al Centro de Costos");
		budgetCostsCentreMgmtContainer.setCentralPanel(budgetCostsCentreMgmt);
		budgetCostsCentreMgmtContainer.show();
		
		dropTargetDropEvent.dropComplete(true);
	    } else {
		dropTargetDropEvent.rejectDrop();
	    }
	    return;
	} catch (Exception e) {
	    e.printStackTrace();
	    dropTargetDropEvent.rejectDrop();
	}
    }

    public void dragExit(DropTargetEvent dropTargetEvent) {
    }

    private TreeSelectionListener setTreeSelectionListener() {
	TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
		public void valueChanged(TreeSelectionEvent evt) {
		    TreePath paths = evt.getPath();
		    selectedNode = (DefaultMutableTreeNode)paths.getLastPathComponent();
		    if (ExpenditureAccount.class.isInstance(selectedNode.getUserObject())) {
		        selectedExpenditureAccount = (ExpenditureAccount)selectedNode.getUserObject();
		    } else {
			selectedExpenditureAccount = null;
		    }		    
		}

	    };
	return treeSelectionListener;
    }

    public void drawTreeExpenditureAccount() {
	try {
	    treeExpenditureAccount = budget.getExpenditureAccountTree(true);
	    if (treeExpenditureAccount != null) {
		treeExpenditureAccount.setRowHeight(16);
		jspTreeExpenditureAccount.getViewport().add(treeExpenditureAccount, null);
		treeExpenditureAccount.addTreeSelectionListener(setTreeSelectionListener());
	        treeExpenditureAccount.setCellRenderer(new ExpenditureAccountTreeRender(ExpenditureAccountTreeLabel.TO_ASSIGN_CC));
	        treeExpenditureAccount.setDragEnabled(true);
	    } else
		Advisor.messageBox(Environment.lang.getProperty("LoadError"), "Error");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private TreeSelectionListener setTreeCCSelectionListener() {
	TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
		public void valueChanged(TreeSelectionEvent evt) {
		    TreePath paths = evt.getPath();
		    //nodeSelected = (DefaultMutableTreeNode)paths.getLastPathComponent();
		}

	    };
	return treeSelectionListener;
    }
    
    private JTree createTreeCC() throws Exception {
	DefaultMutableTreeNode tree = new DefaultMutableTreeNode("Centros de Costos");
	ResultSet treeRS = LibSQL.exFunction("cashflow.getAllCostsCentresByBudget", String.valueOf(budget.getIdBudget()));
	createSubTreeCC(true, tree, treeRS, null);
	return new JTree(tree);
    }

    private void createSubTreeCC(boolean _first, DefaultMutableTreeNode _parentTree, ResultSet _rs, CostsCentre _costsCentreParent) throws SQLException {
	while (_rs.next()) {
	    String params = "";
	    DefaultMutableTreeNode childrenNodes;
	    CostsCentre costsCentre = new CostsCentre(0);
	    if (_first) {
		costsCentre.setIdCostCentre(_rs.getInt("idcostcentre"));
		costsCentre.loadBudget(budget.getIdBudget());
		costsCentre.setName(_rs.getString("costscentre"));
	        costsCentre.setCode(_rs.getString("code"));
		costsCentre.getBudget().setInitialAmount(_rs.getDouble("initialamount"));
	        costsCentre.getBudget().setInitialAmountP(_rs.getDouble("initialamountp"));
	        costsCentre.getBudget().setSpentAmount(_rs.getDouble("spentamount"));
	        costsCentre.getBudget().setSpentAmountP(_rs.getDouble("spentamountp"));
	        costsCentre.getBudget().setAvailableAmount(_rs.getDouble("availableamount"));
	        costsCentre.getBudget().setModifiedAmount(_rs.getDouble("modifiedamount"));
		costsCentre.getBudget().setIdBudgetCostCentre(_rs.getInt("idbudgetcostcentre"));
	        costsCentre.getBudget().setIsAssigned(_rs.getBoolean("isassigned"));
	        costsCentre.getBudget().setWOutAssigned(_rs.getDouble("woutassigned"));
	        costsCentre.setInitialAmount(_rs.getDouble("totalassigned"));
		
		params = "0," + _rs.getString("idbudgetcostcentre");
		costsCentreVector.add(costsCentre);
		
		childrenNodes = new DefaultMutableTreeNode(costsCentre);
	    } else {
		ExpenditureAccount expenditureAccountCC = new ExpenditureAccount();
	        expenditureAccountCC.setName(_rs.getString("name"));
		expenditureAccountCC.setIDExpenditureAccount(_rs.getInt("idaccount"));	
		expenditureAccountCC.setInitialAmount(_rs.getDouble("initialamount"));
	        expenditureAccountCC.setInitialAmountP(_rs.getDouble("initialamountp"));
	        expenditureAccountCC.setSpentAmount(_rs.getDouble("spentamount"));
	        expenditureAccountCC.setSpentAmountP(_rs.getDouble("spentamountp"));
	        expenditureAccountCC.setAvailableAmount(_rs.getDouble("availableamount"));
	        expenditureAccountCC.setModifiedAmount(_rs.getDouble("modifiedamount"));
	        expenditureAccountCC.setInitialAmountColor(Format.hex2Color(_rs.getString("initialamountcolor")));
	        expenditureAccountCC.setAssignedAmountToETColor(Format.hex2Color(_rs.getString("assignedamounttoetcolor")));
	        expenditureAccountCC.setAssignedAmountToCCColor(Format.hex2Color(_rs.getString("assignedamounttocccolor")));
	        expenditureAccountCC.setValueBlock(_rs.getInt("valueblock"));
		
		_costsCentreParent.getBudget().addExpenditureAccount(expenditureAccountCC);
		costsCentre = _costsCentreParent;
		params = _rs.getString("idaccount") + "," + _rs.getString("idbudgetcostcentre");
		
		childrenNodes = new DefaultMutableTreeNode(expenditureAccountCC);
	    }
	    
	    _parentTree.add(childrenNodes);
	    ResultSet subTreeRS = LibSQL.exFunction("cashflow.getAllBudgetCostsCentreDetailByExpenditureAccount", params);
	    createSubTreeCC(false, childrenNodes, subTreeRS, costsCentre);
	}
	costsCentreVector.sort();
    }

    public void drawTreeCC() {
	try {
	    treeCC = getTreeCC();
	    DropTarget target = new DropTarget(treeCC, this);
	    
	    treeCC.setCellRenderer(new CostsCentreTreeRender());
	    jspTreeCC.getViewport().add(treeCC, null);
	    treeCC.setRowHeight(16);
	    treeCC.addTreeSelectionListener(setTreeCCSelectionListener());
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private JTree getTreeCC() throws Exception {
	DefaultMutableTreeNode tree = new DefaultMutableTreeNode("Centros de Costos");
	ResultSet treeRS = LibSQL.exFunction("cashflow.getAllCostsCentresByBudget", budget.getIdBudget());
	getTreeCC(tree, treeRS, new CostsCentre(0));
	return new JTree(tree);
    }

    private void getTreeCC(DefaultMutableTreeNode _parentTree, ResultSet _rs, CostsCentre _parent) throws SQLException {
	_rs.beforeFirst();
	while (_rs.next()) {
	    String params = "";
	    DefaultMutableTreeNode childrenNodes;
	    CostsCentre costsCentre = new CostsCentre(0);
	    costsCentre.setIdCostCentre(_rs.getInt("idcostcentre"));
	    costsCentre.loadBudget(budget.getIdBudget());
	    costsCentre.setName(_rs.getString("costscentre"));
	    costsCentre.setCode(_rs.getString("code"));
	    costsCentre.getBudget().setInitialAmount(_rs.getDouble("initialamount"));
	    costsCentre.getBudget().setInitialAmountP(_rs.getDouble("initialamountp"));
	    costsCentre.getBudget().setSpentAmount(_rs.getDouble("spentamount"));
	    costsCentre.getBudget().setSpentAmountP(_rs.getDouble("spentamountp"));
	    costsCentre.getBudget().setAvailableAmount(_rs.getDouble("availableamount"));
	    costsCentre.getBudget().setModifiedAmount(_rs.getDouble("modifiedamount"));
	    costsCentre.getBudget().setIdBudgetCostCentre(_rs.getInt("idbudgetcostcentre"));
	    costsCentre.getBudget().setIsAssigned(_rs.getBoolean("isassigned"));
	    costsCentre.getBudget().setWOutAssigned(_rs.getDouble("woutassigned"));
	    costsCentre.setInitialAmount(_rs.getDouble("totalassigned"));
	    params = "-1," + _rs.getString("idbudgetcostcentre");
	    costsCentreVector.add(costsCentre);
	    childrenNodes = new DefaultMutableTreeNode(costsCentre);
	    _parentTree.add(childrenNodes);
	    ResultSet subTreeRS = LibSQL.exFunction("cashflow.getAllBudgetCostsCentreDetailByExpenditureAccount", params);
	    getSubTreeCC(childrenNodes, subTreeRS, costsCentre, 0);
	}
	costsCentreVector.sort();
    }

    private void getSubTreeCC(DefaultMutableTreeNode _parentTree, ResultSet _rs, CostsCentre _parentCostCentre, int _parent) throws SQLException {
	_rs.beforeFirst();
	while (_rs.next()) {
	    if (_rs.getInt("idparent") == _parent) {
		DefaultMutableTreeNode childrenNodes;
		ExpenditureAccount expenditureAccountCC = new ExpenditureAccount();
		expenditureAccountCC.setName(_rs.getString("name"));
		expenditureAccountCC.setIDExpenditureAccount(_rs.getInt("idaccount"));
		expenditureAccountCC.setInitialAmount(_rs.getDouble("initialamount"));
		expenditureAccountCC.setInitialAmountP(_rs.getDouble("initialamountp"));
		expenditureAccountCC.setSpentAmount(_rs.getDouble("spentamount"));
		expenditureAccountCC.setSpentAmountP(_rs.getDouble("spentamountp"));
		expenditureAccountCC.setAvailableAmount(_rs.getDouble("availableamount"));
		expenditureAccountCC.setModifiedAmount(_rs.getDouble("modifiedamount"));
		expenditureAccountCC.setInitialAmountColor(Format.hex2Color(_rs.getString("initialamountcolor")));
		expenditureAccountCC.setAssignedAmountToETColor(Format.hex2Color(_rs.getString("assignedamounttoetcolor")));
		expenditureAccountCC.setAssignedAmountToCCColor(Format.hex2Color(_rs.getString("assignedamounttocccolor")));
		expenditureAccountCC.setValueBlock(_rs.getInt("valueblock"));
		_parentCostCentre.getBudget().addExpenditureAccount(expenditureAccountCC);
		CostsCentre costsCentre = _parentCostCentre;
		childrenNodes = new DefaultMutableTreeNode(expenditureAccountCC);
		_parentTree.add(childrenNodes);
		int previous = _rs.getRow();
		getSubTreeCC(childrenNodes, _rs, costsCentre, _rs.getInt("idaccount"));
		_rs.absolute(previous);
	    }
	}
    }


    private void btnClose_actionPerformed(ActionEvent e) {
	parentList.refresh();
	getParentInternalFrame().close();
    }

    public void refreshExpenditureAccountTree(ExpenditureAccount _expenditureAccount){
	selectedExpenditureAccount = _expenditureAccount;
	treeExpenditureAccount.updateUI();
	treeExpenditureAccount.setRowHeight(16);
    }

    public void refreshCostsCentreTree(ExpenditureAccount _expenditureAccount){
	drawTreeCC();
	budget.retrieveData();
	//drawTreeExpenditureAccount();
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
    
    }

    private void btnExpand_actionPerformed(ActionEvent e) {
	Proced.expandAll(treeExpenditureAccount, treeExpenditureAccount.getSelectionPath(), true);
    }

    private void btnCollapse_actionPerformed(ActionEvent e) {    
	Proced.expandAll(treeExpenditureAccount, treeExpenditureAccount.getSelectionPath(), false);
    }

    public void setParentList(BudgetCostsCentresList parentList) {
	this.parentList = parentList;
    }

    public void setBudgetExpTypes(Budget budgetExpTypes) {
	this.budget = budgetExpTypes;
	drawTreeCC();
	drawTreeExpenditureAccount();	
    }

}
