package org.digitall.common.cashflow.interfaces.account;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import org.digitall.common.cashflow.interfaces.account.PaymentOrderCheckList;
import org.digitall.common.cashflow.interfaces.account.PaymentOrderDetailList;
import org.digitall.common.cashflow.interfaces.account.PaymentsOrdersList;
import org.digitall.common.cashflow.interfaces.budget.BudgetList;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;

public class PaymentsOrdersMain extends BasicTabContainer{
    private PaymentsOrdersList paymentsOrdersList = new PaymentsOrdersList(this);
    private PaymentOrderDetailList paymentOrderDetailList = new PaymentOrderDetailList(this);
    private PaymentOrderCheckList paymentOrderCheckList = new PaymentOrderCheckList(this);
    private AcceptButton btnBudget = new AcceptButton();

    public PaymentsOrdersMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(650, 555));
	this.setPreferredSize(new Dimension(650, 555));
	addTab(paymentsOrdersList);
	addTab(paymentOrderDetailList);
	addTab(paymentOrderCheckList);
	setTitle("");
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnBudget);
    }

    private void btnBudget_actionPerformed(ActionEvent e) {
	BudgetList budgetList = new BudgetList();
	
	ExtendedInternalFrame budgetListContainer = new ExtendedInternalFrame("Administración de Partidas Presupuestarias");
	budgetListContainer.setCentralPanel(budgetList);
	budgetListContainer.show();
    }

    public PaymentsOrdersList getPaymentsOrdersList() {
	return paymentsOrdersList;
    }

    public PaymentOrderDetailList getPaymentOrderDetailList() {
	return paymentOrderDetailList;
    }

    public PaymentOrderCheckList getPaymentsCheckList() {
	return paymentOrderCheckList;
    }

}
