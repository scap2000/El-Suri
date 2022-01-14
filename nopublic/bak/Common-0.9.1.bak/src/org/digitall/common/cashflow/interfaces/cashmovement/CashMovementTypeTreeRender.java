package org.digitall.common.cashflow.interfaces.cashmovement;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.digitall.common.cashflow.interfaces.cashmovement.CashMovementTypeTreeLabel;
import org.digitall.common.cashflow.classes.CashMovementType;

public class CashMovementTypeTreeRender  extends DefaultTreeCellRenderer{

    public CashMovementTypeTreeRender() {

    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	CashMovementTypeTreeLabel treeLabel;
	super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
	if (CashMovementType.class.isInstance(node.getUserObject())) {
	    treeLabel = new CashMovementTypeTreeLabel((CashMovementType)(node.getUserObject()));                   
	} else {
	    treeLabel = new CashMovementTypeTreeLabel(node.getUserObject().toString());
	}
	
	if (sel)
	    treeLabel.setBackground(getBackgroundSelectionColor());
	else
	    treeLabel.setBackground(getBackgroundNonSelectionColor());
	
	return treeLabel;
    }
}
