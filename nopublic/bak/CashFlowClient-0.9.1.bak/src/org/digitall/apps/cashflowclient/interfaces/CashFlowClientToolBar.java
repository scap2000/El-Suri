package org.digitall.apps.cashflowclient.interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.cashflow.classes.CashFlowEnvironment;
import org.digitall.common.cashflow.interfaces.costscentre.CCToolBar;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDesktop;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicToolBar;

public class CashFlowClientToolBar extends BasicToolBar {

    private BasicLabel jLabel1 = new BasicLabel();
    private BasicDesktop desktop;
    private BasicButton btnAmount = new BasicButton();
    private AmountsExpenditureAccountsTree amountsTree;
    private CFCFrameContainer containerAmounts = new CFCFrameContainer("Tipos de Gastos del Centro de Costos");
    private CFCFrameContainer containerCC = new CFCFrameContainer();
    private CCToolBar ccToolBar;

    public CashFlowClientToolBar(BasicDesktop _desktop) {
	try {
	    desktop = _desktop;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(830, 38));
	jLabel1.setText("CashFlow-Client");
	jLabel1.setMaximumSize(new Dimension(100, 14));
	jLabel1.setFont(new Font("Default", 0, 11));
	//setOrientation(SwingConstants.VERTICAL);
	//setMaximumSize(new Dimension(5, 32767));
	btnAmount.setText("Montos");
	btnAmount.setFont(new Font("Default", 0, 11));
	btnAmount.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAmount_actionPerformed(e);
				 }

			     }
	);
	this.add(jLabel1, null);
	this.add(btnAmount, null);
	containerAmounts.setVisible(false);
	containerCC.setVisible(false);
	desktop.add(containerAmounts);
	desktop.add(containerCC);
    }

    private void loadTotalToolBar() {
	if (containerCC.isClosed()) {
	    containerCC = new CFCFrameContainer();
	    desktop.add(containerCC);
	}
	ccToolBar.setCostscentre(CashFlowEnvironment.getCostsCentre());
	containerCC.setTitle(CashFlowEnvironment.getCostsCentre().getName());
	containerCC.setPanel(ccToolBar);
	containerCC.setVisible(true);
    }

    private void loadAmountTree() {
	if (containerAmounts.isClosed()) {
	    containerAmounts = new CFCFrameContainer("Tipos de Gastos del Centro de Costos");
	    desktop.add(containerAmounts);
	}
	amountsTree.setCostsCentre(CashFlowEnvironment.getCostsCentre());
	containerAmounts.setPanel(amountsTree);
	containerAmounts.setVisible(true);
    }

    private void btnAmount_actionPerformed(ActionEvent e) {
	loadAmountTree();
	loadTotalToolBar();
    }

}
