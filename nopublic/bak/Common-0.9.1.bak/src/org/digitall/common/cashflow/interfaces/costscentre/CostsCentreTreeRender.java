package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.interfaces.costscentre.CCTreeLabel;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountTreeLabel;

public class CostsCentreTreeRender extends DefaultTreeCellRenderer{

    public CostsCentreTreeRender() {

    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	Component treeLabel;
	super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
	if (CostsCentre.class.isInstance(node.getUserObject())) {
	    treeLabel = new CCTreeLabel((CostsCentre)node.getUserObject());	    
	} else if (ExpenditureAccount.class.isInstance(node.getUserObject())) {
	    treeLabel = new ExpenditureAccountTreeLabel((ExpenditureAccount)node.getUserObject(), ExpenditureAccountTreeLabel.CC_ASSIGNED);
	} else {
	    treeLabel = new CCTreeLabel();
	}
	
	if (sel)
	    treeLabel.setBackground(getBackgroundSelectionColor());
	else
	    treeLabel.setBackground(getBackgroundNonSelectionColor());
	
	return treeLabel;
    }
}
