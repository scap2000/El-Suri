package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.ExpenditureAccount.CCItem;

public class CCExpenditureAccountTreeRender extends DefaultTreeCellRenderer{

    public CCExpenditureAccountTreeRender() {

    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	CCExpenditureAccountTreeLabel treeLabel;
	super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
	if (ExpenditureAccount.CCItem.class.isInstance(node.getUserObject())) {
	    treeLabel = new CCExpenditureAccountTreeLabel((ExpenditureAccount.CCItem)node.getUserObject());      
	} else {
	    treeLabel = new CCExpenditureAccountTreeLabel((CostsCentre)node.getUserObject());
	}
	
	if (sel)
	    treeLabel.setBackground(getBackgroundSelectionColor());
	else
	    treeLabel.setBackground(getBackgroundNonSelectionColor());
	
	return treeLabel;
    }
}
