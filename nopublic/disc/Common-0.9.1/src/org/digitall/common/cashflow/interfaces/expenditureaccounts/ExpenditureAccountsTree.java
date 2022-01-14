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
 * ExpenditureAccountsTree.java
 *
 * */
package org.digitall.common.cashflow.interfaces.expenditureaccounts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.interfaces.voucher.VouchersMgmt;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.Tree;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.inputpanels.TAInputPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.sql.LibSQL;

//

public class ExpenditureAccountsTree extends BasicPrimitivePanel {

    private Tree tree = new Tree();
    private JTree treeExpenditureAccount;
    private BasicScrollPane jsptree = new BasicScrollPane();
    private BasicPanel panelTree = new BasicPanel();
    private BasicPanel PanelData = new BasicPanel();
     
    private AssignButton btnAdd = new AssignButton(true);
    private CancelDataButton btnCancelData = new CancelDataButton();
    private TFInput tfCode = new TFInput(DataTypes.INTEGER, "Code", false);
    private TAInputPanel tfDescription = new TAInputPanel(DataTypes.STRING, "Description", false);
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private int idExpenditureAccount;
    private int codeExpType;
    private int idParent = 0;
    private boolean addAction = true;
    private String nameExpType;
    private String nodeSelected = "";
    private AddButton btnNew = new AddButton();
    private TFInput tfAmountColor = new TFInput(DataTypes.COLOR, "AmountColor", false);
    private TFInput tfExpenditureAccountAmountColor = new TFInput(DataTypes.COLOR, "ExpenditureAccountAmountColor", false);
    private TFInput tfCostCentreAmountColor = new TFInput(DataTypes.COLOR, "CostCentreAmountColor", false);
    private BasicPanel colorPanel = new BasicPanel();
    private AcceptButton btnSelect = new AcceptButton();
    private ExpenditureAccount expenditureAccount;
    private CloseButton btnCancel = new CloseButton();
    private VouchersMgmt vouchersMgmt;

    public ExpenditureAccountsTree() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(500, 589));
	panelTree.setBounds(new Rectangle(5, 300, 490, 285));
	panelTree.setLayout(null);
	PanelData.setBounds(new Rectangle(5, 0, 490, 295));
	PanelData.setLayout(null);
	colorPanel.setBorder(BorderPanel.getBorderPanel("Color",Color.BLACK,Color.BLACK));
	jsptree.setBounds(new Rectangle(10, 25, 470, 255));
	tfName.setBounds(new Rectangle(105, 30, 370, 40));
	btnNew.setBounds(new Rectangle(370, 265, 30, 25));
	btnNew.setSize(new Dimension(40, 25));
	btnNew.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnNew_actionPerformed(e);
		    }

		});
	tfAmountColor.setBounds(new Rectangle(10, 25, 110, 35));
	tfExpenditureAccountAmountColor.setBounds(new Rectangle(175, 25, 110, 35));
	tfExpenditureAccountAmountColor.setSize(new Dimension(110, 35));
	tfCostCentreAmountColor.setBounds(new Rectangle(340, 25, 110, 35));
	tfCostCentreAmountColor.setSize(new Dimension(110, 35));
	colorPanel.setBounds(new Rectangle(15, 180, 460, 70));
	colorPanel.setLayout(null);
	btnSelect.setBounds(new Rectangle(15, 265, 30, 25));
	btnSelect.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnSelect_actionPerformed(e);
				  }

			      }
	);
	btnCancel.setBounds(new Rectangle(50, 265, 30, 25));
	btnCancel.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnCancel_actionPerformed(e);
				    }

				}
	);
	colorPanel.add(tfAmountColor, null);
	colorPanel.add(tfCostCentreAmountColor, null);
	colorPanel.add(tfExpenditureAccountAmountColor, null);
	PanelData.add(btnCancel, null);
	PanelData.add(btnSelect, null);
	PanelData.add(colorPanel, null);
	PanelData.add(btnNew, null);
	PanelData.add(btnCancelData, null);
	PanelData.add(btnAdd, null);
	PanelData.add(tfDescription, null);
	PanelData.add(tfCode, null);
	PanelData.add(tfName, null);
	this.add(btnAdd, null);
	this.add(PanelData, null);
	jsptree.getViewport().add(treeExpenditureAccount, null);
	panelTree.add(jsptree, null);
	this.add(panelTree, null);
	PanelData.setBorder(BorderPanel.getBorderPanel("Agregar nuevo Tipo de Gasto", Color.blue, Color.black));
	panelTree.setBorder(BorderPanel.getBorderPanel("Tipos de Gastos", Color.blue, Color.black));
	btnAdd.setBounds(new Rectangle(420, 520, 40, 25));
	tfCode.setBounds(new Rectangle(15, 30, 75, 35));
	tfDescription.setBounds(new Rectangle(15, 70, 460, 105));
	btnAdd.setBounds(new Rectangle(440, 265, 30, 25));
	btnAdd.setSize(new Dimension(30, 25));
	btnAdd.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnAdd_actionPerformed(e);
		    }

		});
	btnCancelData.setBounds(new Rectangle(405, 265, 30, 25));
	btnCancelData.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnCancelData_actionPerformed(e);
		    }

		});
	btnSelect.setEnabled(false);
	btnCancel.setEnabled(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    
    public void refresh() {
	drawTreeExpenditureAccount();
	setComponentEnabled(false);
    }

    public void reload(){
	refresh();
    }
    
    private TreeSelectionListener setTreeSelectionListener() {
	TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {

		public void valueChanged(TreeSelectionEvent evt) {
		    TreePath paths = evt.getPath();
		    nodeSelected = paths.getLastPathComponent().toString();
		    codeExpType = Integer.parseInt(nodeSelected.substring(0, nodeSelected.indexOf("-") - 1).trim());
		    nameExpType = nodeSelected.substring(nodeSelected.indexOf("-") + 1, nodeSelected.length()).trim();
		    if (codeExpType != 0) {
			loadExpenditureAccount();
		    } else {
			idExpenditureAccount = 0;
			clearData();
			setComponentEnabled(false);
		    }
		    /**
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)paths.getLastPathComponent();
		    if (ExpenditureAccount.class.isInstance(node.getUserObject())) {
		        selectedExpenditureAccount = (ExpenditureAccount)node.getUserObject();
		        
		    } else {
		        selectedExpenditureAccount = null;
		    }*/
		}

	    };
	return treeSelectionListener;
    }

    public void drawTreeExpenditureAccount() {
	try {
	    treeExpenditureAccount = tree.createTree("accounting.accounts", "idaccount", "name", "0", "code");
	    /** IMPLEMENTAR 
	    treeExpenditureAccount = expenditureAccountsGroup.createTree();
	    **/
	    if (treeExpenditureAccount != null){
	        jsptree.setVisible(true);
		treeExpenditureAccount.setRowHeight(0);
		jsptree.getViewport().add(treeExpenditureAccount, null);
		treeExpenditureAccount.addTreeSelectionListener(setTreeSelectionListener());
		/**
		treeExpenditureAccount.setCellRenderer(new ExpenditureAccountTreeRender(ExpenditureAccountTreeLabel.EXPENDITUREACCOUNT));
		**/
		/*if (!nodeSelected.equals("")) {
		    treeExpenditureAccount.setSelectionPath(treeExpenditureAccount.getNextMatch(nodeSelected, 0, Position.Bias.Forward));
		}*/
	    } else {
	        jsptree.setVisible(false);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void loadExpenditureAccount() {
	try {
	    addAction = false;
	    ResultSet rs = LibSQL.exFunction("accounting.getExpenditureAccount", String.valueOf(codeExpType));
	    if (rs.next()) {
		expenditureAccount = new ExpenditureAccount(rs.getInt("idaccount"), rs.getString("name"));
		idExpenditureAccount = rs.getInt("idaccount");
		tfCode.setValue(codeExpType);
		tfName.setValue(rs.getString("name"));
		tfDescription.setText(rs.getString("description"));
		tfAmountColor.setValue(rs.getString("initialamountcolor"));
	        tfAmountColor.setBackgroundColor(Format.hex2Color(rs.getString("initialamountcolor")));
	        tfExpenditureAccountAmountColor.setValue(rs.getString("assignedamounttoetcolor"));
	        tfExpenditureAccountAmountColor.setBackgroundColor(Format.hex2Color(rs.getString("assignedamounttoetcolor")));
	        tfCostCentreAmountColor.setValue(rs.getString("assignedamounttocccolor"));
	        tfCostCentreAmountColor.setBackgroundColor(Format.hex2Color(rs.getString("assignedamounttocccolor")));
	        idParent = Integer.parseInt(rs.getString("idparent"));
		
		setComponentEnabled(true);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	/** 
	idExpenditureAccount = selectedExpenditureAccount.getIdExpenditureAccount();
	tfCode.setText(selectedExpenditureAccount.getCode());
	tfName.setText(selectedExpenditureAccount.getName());
	tfDescription.setText(selectedExpenditureAccount.getDescription());
	tfAmountColor.setText(rs.getString("initialamountcolor"));
	tfAmountColor.setBackgroundColor(Format.hex2Color(rs.getString("initialamountcolor")));
	tfExpenditureAccountAmountColor.setText(rs.getString("assignedamounttoetcolor"));
	tfExpenditureAccountAmountColor.setBackgroundColor(Format.hex2Color(rs.getString("assignedamounttoetcolor")));
	tfCostCentreAmountColor.setText(rs.getString("assignedamounttocccolor"));
	tfCostCentreAmountColor.setBackgroundColor(Format.hex2Color(rs.getString("assignedamounttocccolor")));
	idParent = Integer.parseInt(rs.getString("idparent"));
	setComponentEnabled(true);
	 */
    }

    private void setComponentEnabled(boolean _option) {
	tfCode.setEnabled(_option);
	tfName.setEnabled(_option);
	tfDescription.setEnabled(_option);
    }

    private void clearData() {
	tfCode.setValue("");
	tfName.setValue("");
	tfDescription.setText("");
	tfAmountColor.setBackgroundColor(Color.WHITE);
	tfAmountColor.setValue("");
	tfExpenditureAccountAmountColor.setBackgroundColor(Color.WHITE);
	tfExpenditureAccountAmountColor.setValue("");
	tfCostCentreAmountColor.setBackgroundColor(Color.WHITE);
	tfCostCentreAmountColor.setValue("");
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	String code = tfCode.getString();
	String name = tfName.getString();
	String description = tfDescription.getText();
	String amountColor = tfAmountColor.getString().replaceAll("#","");
	String expTypeAmountColor = tfExpenditureAccountAmountColor.getString().replaceAll("#","");
	String costCentreAmountColor = tfCostCentreAmountColor.getString().replaceAll("#","");
	String params = "";
	
	if (addAction) {
	    params = code + ",'" + name + "'," + String.valueOf(idParent) + ",'" + description + "','"+ amountColor +"','"+ expTypeAmountColor +"','"+ costCentreAmountColor +"'";
	    LibSQL.getInt("accounting.addExpenditureAccount",params);    
	} else {
	    params = idExpenditureAccount + "," + code + ",'" + name + "'," + String.valueOf(idParent) + ",'" + description + "','"+ amountColor +"','"+ expTypeAmountColor +"','"+ costCentreAmountColor +"'";
	    LibSQL.getInt("accounting.setAccount",params);
	}
	if (Advisor.question("Configuracion de Colores","Configurar colores de los sub-items automaticamente?")){
	    params = idExpenditureAccount +",'"+ expTypeAmountColor +"'";
	    LibSQL.getInt("accounting.setAccountColor",params);
	}

	drawTreeExpenditureAccount();
	clearData();
	setComponentEnabled(false);
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	idParent = idExpenditureAccount;
	clearData();
	setComponentEnabled(true);
	addAction = true;
    }

    private void btnCancelData_actionPerformed(ActionEvent e) {
	clearData();
	setComponentEnabled(false);
    }

    private void btnSelect_actionPerformed(ActionEvent e) {
	if (idExpenditureAccount == 0){
	    expenditureAccount = null;
	}
	vouchersMgmt.addCCExpenditureAccount(expenditureAccount);
	getParentInternalFrame().close();
    }

    public ExpenditureAccount getExpenditureAccount() {
	return expenditureAccount;
    }

    private void btnCancel_actionPerformed(ActionEvent e) {
	expenditureAccount = null;
	vouchersMgmt.addCCExpenditureAccount(expenditureAccount);
	getParentInternalFrame().close();
    }

    public void setVouchersMgmt(VouchersMgmt vouchersMgmt) {
	this.vouchersMgmt = vouchersMgmt;
	btnSelect.setEnabled(true);
	btnCancel.setEnabled(true);
    }

}
