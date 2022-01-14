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
 * CashMovementTypesTree.java
 *
 * */
package org.digitall.common.cashflow.interfaces.cashmovement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.digitall.common.cashflow.interfaces.cashmovement.CashMovementTypeTreeRender;
import org.digitall.common.cashflow.classes.CashMovementType;
import org.digitall.common.cashflow.classes.CashMovementTypeVector;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class CashMovementTypesTree extends BasicPrimitivePanel {

    private JTree treeCashFlowType;
    private BasicScrollPane jsptree = new BasicScrollPane();
    private BasicPanel panelTree = new BasicPanel();
    private BasicPanel jpData = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();
    private CancelDataButton btnCancelData = new CancelDataButton();
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", true);
    private AddButton btnNew = new AddButton();
    private CBInput cbCashMovementTypeRef = new CBInput(CachedCombo.CASHMOVEMENTTYPEALL, "CashMovementTypeRef");
    private CBInput cbSign = new CBInput(0, "Type");
    private CashMovementType selectedCashMovementType;
    private CashMovementType newCashMovementType;
    private BorderLayout borderLayout1 = new BorderLayout();

    public CashMovementTypesTree() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(455, 490));
	panelTree.setBounds(new Rectangle(5, 190, 445, 295));
	panelTree.setLayout(borderLayout1);
	jpData.setBounds(new Rectangle(5, 0, 445, 170));
	jpData.setLayout(null);
	tfName.setBounds(new Rectangle(20, 30, 275, 35));
	btnNew.setBounds(new Rectangle(350, 135, 40, 25));
	btnNew.setSize(new Dimension(40, 25));
	btnNew.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnNew_actionPerformed(e);
			      }

			  }
	);
	cbCashMovementTypeRef.setBounds(new Rectangle(20, 80, 405, 35));
	cbSign.setBounds(new Rectangle(300, 30, 125, 35));
	jpData.add(cbSign, null);
	jpData.add(cbCashMovementTypeRef, null);
	//jpData.add(btnCancelData, null);
	jpData.add(btnNew, null);
	jpData.add(btnSave, null);
	jpData.add(tfDescription, null);
	jpData.add(tfName, null);
	this.add(btnSave, null);
	this.add(jpData, BorderLayout.NORTH);
	jsptree.getViewport().add(treeCashFlowType, null);
	panelTree.add(jsptree, BorderLayout.CENTER);
	this.add(panelTree, BorderLayout.CENTER);
	jpData.setBorder(BorderPanel.getBorderPanel("Agregar nuevo Tipo de Movimiento de Fondos"));
	jpData.setPreferredSize(new Dimension(1, 180));
	panelTree.setBorder(BorderPanel.getBorderPanel("Tipos de Movimientos de Fondos"));
	panelTree.setSize(new Dimension(455, 1));
	btnSave.setBounds(new Rectangle(420, 520, 40, 25));
	tfDescription.setBounds(new Rectangle(20, 125, 325, 35));
	btnSave.setBounds(new Rectangle(395, 135, 30, 25));
	btnSave.setSize(new Dimension(30, 25));
	btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnAdd_actionPerformed(e);
			       }

			   }
	);
	btnCancelData.setBounds(new Rectangle(400, 135, 30, 25));
	btnCancelData.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnCancelData_actionPerformed(e);
				     }

				 }
	);
	cbCashMovementTypeRef.autoSize();
	cbSign.autoSize();
	btnNew.setToolTipText("Limpiar campos");
	btnSave.setToolTipText("Grabar datos");
    }

    private void loadComboCashFlowTypeRef() {
	cbCashMovementTypeRef.getCombo().update();
	cbCashMovementTypeRef.getCombo().addItem("Ninguno", "-1");
	cbCashMovementTypeRef.setSelectedID("-1");
    }

    public void reload() {
	refresh();
    }

    public void refresh() {
	loadCashFlowSign();
	drawTree();
	loadComboCashFlowTypeRef();
    }

    private void loadCashFlowSign() {
	JCombo combo = new JCombo();
	combo.addItem("Ingreso", "1");
	combo.addItem("Egreso", "-1");
	cbSign.setCombo(combo);
	cbSign.updateUI();
    }

    private TreeSelectionListener setTreeSelectionListener() {
	TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {

		public void valueChanged(TreeSelectionEvent evt) {
		    TreePath paths = evt.getPath();
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)paths.getLastPathComponent();
		    if (CashMovementType.class.isInstance(node.getUserObject())) {
			selectedCashMovementType = (CashMovementType)node.getUserObject();
			loadData();
		    } else {
			selectedCashMovementType = null;
			setNewItem();
		    }
		}

	    }
	;
	return treeSelectionListener;
    }

    private void drawTree() {
	try {
	    treeCashFlowType = CashMovementTypeVector.createTree();
	    jsptree.getViewport().add(treeCashFlowType, null);
	    treeCashFlowType.addTreeSelectionListener(setTreeSelectionListener());
	    treeCashFlowType.setCellRenderer(new CashMovementTypeTreeRender());
	    treeCashFlowType.setRowHeight(16);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void loadData() {
	tfName.setValue(selectedCashMovementType.getName());
	tfDescription.setValue(selectedCashMovementType.getDescription());
	cbCashMovementTypeRef.setSelectedID("" + selectedCashMovementType.getIdCashMovementTypeRef());
	cbSign.setSelectedID("" + selectedCashMovementType.getSign());
	newCashMovementType = selectedCashMovementType;
    }

    private void clearData() {
	tfName.setValue("");
	tfDescription.setValue("");
	cbCashMovementTypeRef.setSelectedID("-1");
    }

    private void setNewItem() {
	clearData();
	if (selectedCashMovementType == null) {
	    selectedCashMovementType = new CashMovementType();
	    selectedCashMovementType.setIdCashMovementType(0);
	}
	newCashMovementType = new CashMovementType();
	newCashMovementType.setIdParent(selectedCashMovementType.getIdCashMovementType());
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	setNewItem();
    }

    public boolean saveData() {
	if (tfName.getString().length()>0 && tfDescription.getString().length()>0) {
	    if (newCashMovementType == null) {
		newCashMovementType = new CashMovementType();
	    }
	    newCashMovementType.setName(tfName.getString());
	    newCashMovementType.setDescription(tfDescription.getString());
	    newCashMovementType.setIdCashMovementTypeRef(Integer.parseInt("" + cbCashMovementTypeRef.getSelectedValue()));
	    newCashMovementType.setSign(Integer.parseInt("" + cbSign.getSelectedValue()));
	    int result = newCashMovementType.saveData();
	    if (result >= 0) {
		if (result != 0) {
		    drawTree();
		} else {
		    selectedCashMovementType = newCashMovementType;
		    treeCashFlowType.updateUI();
		    treeCashFlowType.setRowHeight(16);
		}
		loadComboCashFlowTypeRef();
		treeCashFlowType.clearSelection();
		clearData();
		return true;
	    } else {
		return false;
	    }
	} else {
	    Advisor.messageBox("Debe completar todos los campos", "Aviso");
	    return false;
	}
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	saveData();
    }

    private void btnCancelData_actionPerformed(ActionEvent e) {
	treeCashFlowType.clearSelection();
	clearData();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Agregar/Modificar Tipos de Movimientos de Fondos");
    }

}
