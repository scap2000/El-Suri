package org.digitall.apps.cashflow.interfaces.costscentre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountTreeLabel;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountTreeRender;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.environment.Environment;

public class CCTotalAmountsTree extends BasicContainerPanel {
    private JTree treeExpenditureAccounts = new JTree();
    private BasicScrollPane jsptree = new BasicScrollPane();
    private BasicPanel panelTree = new BasicPanel();
    private CostsCentre costsCentre;
     
    private CloseButton btnClose = new CloseButton();
    private BasicTextField tfAmount = new BasicTextField();
    private BasicLabel jLabel1 = new BasicLabel();
    
    public CCTotalAmountsTree() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(690, 500));
	panelTree.setBounds(new Rectangle(5, 0, 680, 450));
	panelTree.setLayout(null);
	panelTree.setBorder(BorderPanel.getBorderPanel("Tipos de Gastos", Color.blue, Color.black));
	btnClose.setBounds(new Rectangle(645, 465, 40, 25));
	btnClose.setSize(new Dimension(40, 25));
	tfAmount.setBounds(new Rectangle(50, 465, 155, 25));
	tfAmount.setForeground(Color.blue);
	tfAmount.setFont(new Font("Default", 1, 16));
	jLabel1.setText("Total:");
	jLabel1.setBounds(new Rectangle(5, 470, 45, 14));
	jLabel1.setFont(new Font("Default", 1, 13));
	jLabel1.setSize(new Dimension(45, 14));
	jsptree.setBounds(new Rectangle(10, 25, 660, 415));
	jsptree.getViewport().add(treeExpenditureAccounts, null);
	panelTree.add(jsptree, null);
	this.add(jLabel1, null);
	this.add(tfAmount, null);
	this.add(btnClose, null);
	 
	this.add(panelTree, null);
	
	drawTreeExpenditureAccount();
    }

    public void drawTreeExpenditureAccount() {
	try {
	    treeExpenditureAccounts = costsCentre.getExpenditureAccountsTree();
	    if (treeExpenditureAccounts != null) {
		treeExpenditureAccounts.setRowHeight(0);
		treeExpenditureAccounts.setRowHeight(16);
		jsptree.getViewport().add(treeExpenditureAccounts, null);
//		treeExpenditureAccounts.addTreeSelectionListener(setTreeSelectionListener());
		treeExpenditureAccounts.setCellRenderer(new ExpenditureAccountTreeRender(ExpenditureAccountTreeLabel.CC_ASSIGNED));
		
		treeExpenditureAccounts.addMouseListener(new MouseAdapter() {
			    public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == e.BUTTON3) { 
				    JTree tree = (JTree)e.getComponent();
				    TreePath parentpath = tree.getClosestPathForLocation(e.getX(), e.getY());
				    DefaultMutableTreeNode node = (DefaultMutableTreeNode)parentpath.getLastPathComponent();
				    if (ExpenditureAccount.class.isInstance(node.getUserObject())) {
					tree.setSelectionPath(parentpath);
					Vector params = new Vector();
					params.add(costsCentre);
					params.add((ExpenditureAccount)node.getUserObject());
					
					
					/*FrameContainer ccAssignedAmountDetail = new FrameContainer(FrameContainer.CCASSIGNAMOUNTDETAIL, parentDesktop, null, params);
					parentDesktop.add(ccAssignedAmountDetail);
					ccAssignedAmountDetail.setVisible(true);    */
				    }
				}
			    }
			});
	    } else
		Advisor.messageBox(Environment.lang.getProperty("LoadError"), "Error");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
