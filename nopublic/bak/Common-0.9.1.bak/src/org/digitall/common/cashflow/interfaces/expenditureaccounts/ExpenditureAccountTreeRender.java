package org.digitall.common.cashflow.interfaces.expenditureaccounts;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.digitall.common.cashflow.classes.Budget;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;

public class ExpenditureAccountTreeRender extends DefaultTreeCellRenderer{
    private int renderType;
    
    public ExpenditureAccountTreeRender(int _renderType) {
	renderType = _renderType;
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	ExpenditureAccountTreeLabel treeLabel;
	super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
	if (ExpenditureAccount.class.isInstance(node.getUserObject())) {
	    treeLabel = new ExpenditureAccountTreeLabel((ExpenditureAccount)(node.getUserObject()),renderType);       	    
	} else if (Budget.class.isInstance(node.getUserObject())) {
	    treeLabel = new ExpenditureAccountTreeLabel((Budget)(node.getUserObject()),renderType);
	} else if (CostsCentre.class.isInstance(node.getUserObject())) {
	    treeLabel = new ExpenditureAccountTreeLabel((CostsCentre)(node.getUserObject()));
	} else {
	    treeLabel = new ExpenditureAccountTreeLabel("Tipos de Gastos");
	}
	
	
	if (sel)
	    treeLabel.setBackground(getBackgroundSelectionColor());
	else
	    treeLabel.setBackground(getBackgroundNonSelectionColor());
	
	return treeLabel;
    }
}
