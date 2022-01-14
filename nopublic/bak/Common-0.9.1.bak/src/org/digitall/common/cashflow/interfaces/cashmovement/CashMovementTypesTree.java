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
