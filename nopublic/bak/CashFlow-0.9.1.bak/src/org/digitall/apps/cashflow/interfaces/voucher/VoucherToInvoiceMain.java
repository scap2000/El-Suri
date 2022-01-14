package org.digitall.apps.cashflow.interfaces.voucher;

import java.awt.Dimension;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

public class VoucherToInvoiceMain extends BasicTabContainer {

    private VoucherToInvoiceList voucherToInvoiceList = new VoucherToInvoiceList(this);
    private VoucherToInvoiceMgmt voucherToInvoiceMgmt = new VoucherToInvoiceMgmt(this);

    public VoucherToInvoiceMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 500));
	this.setPreferredSize(new Dimension(900, 500));
	addTab(voucherToInvoiceList);
	addTab(voucherToInvoiceMgmt);
	setTitle("");
    }

    public void changeSelectedTab() {
	if (getParentInternalFrame() != null) {
	    if (getSelectedTab() == 0) {
		getParentInternalFrame().setInfo("Haga doble click sobre una Nota de Recepción o presione el botón \"Facturar\"");
	    } else {
		getParentInternalFrame().setInfo("Ajuste los campos IVA, Bonificación y/o Precio en cada uno de los ítems y presione el botón \"Grabar Factura\"");
	    }
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Haga doble click sobre una Nota de Recepción o presione el botón \"Facturar\"");
    }

    public VoucherToInvoiceList getVoucherToInvoiceList() {
	return voucherToInvoiceList;
    }

    public VoucherToInvoiceMgmt getVoucherToInvoiceMgmt() {
	return voucherToInvoiceMgmt;
    }

}
