package org.digitall.common.cashflow.interfaces.accounting;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.interfaces.accounting.AccountTreeLabel;

public class AccountTreeRenderer extends DefaultTreeCellRenderer{
    private String rootName;
    
    public AccountTreeRenderer(String _rootName) {
	rootName = _rootName;
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	AccountTreeLabel treeLabel;
	super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
	if (Account.class.isInstance(node.getUserObject())) {
	    treeLabel = new AccountTreeLabel((Account)(node.getUserObject()),AccountTreeLabel.ACCOUNT);
	} else {
	    treeLabel = new AccountTreeLabel(rootName);
	}
	
	
	if (sel)
	    treeLabel.setBackground(getBackgroundSelectionColor());
	else
	    treeLabel.setBackground(getBackgroundNonSelectionColor());
	
	return treeLabel;
    }
}
