package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Dimension;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.interfaces.costscentre.CCSpentAmountInvoicesList;
import org.digitall.common.cashflow.interfaces.costscentre.CCSpentAmountResourcesRequestsAuthList;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

public class CCSpentAmountMain extends BasicTabContainer {

    private CCSpentAmountInvoicesList ccSpentAmountInvoicesList = new CCSpentAmountInvoicesList();
    private CCSpentAmountResourcesRequestsAuthList ccSpentAmountResourcesRequestsAuthList = new CCSpentAmountResourcesRequestsAuthList();
    private ExpenditureAccount.CCItem ccExpenditureAccount;
    
    public CCSpentAmountMain(ExpenditureAccount.CCItem _ccExpenditureAccount) {
	try {
	    ccExpenditureAccount = _ccExpenditureAccount;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Listado de Facturas y Pedidos de Recursos Autorizados para el Centro de Costos: " + ccExpenditureAccount.getCostCentre().getName());
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(835, 390));
	this.setPreferredSize(new Dimension(835, 390));
	ccSpentAmountInvoicesList.setCcExpenditureAccount(ccExpenditureAccount);
	ccSpentAmountResourcesRequestsAuthList.setCCExpenditureAccount(ccExpenditureAccount);
	addTab(ccSpentAmountInvoicesList);
	addTab(ccSpentAmountResourcesRequestsAuthList);
    }
}
