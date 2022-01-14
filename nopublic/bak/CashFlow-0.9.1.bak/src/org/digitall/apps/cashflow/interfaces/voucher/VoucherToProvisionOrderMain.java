package org.digitall.apps.cashflow.interfaces.voucher;

import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;


public class VoucherToProvisionOrderMain extends BasicTabContainer {

    private VoucherToProvisionOrderList voucherToProvisionOrderList = new VoucherToProvisionOrderList(this);
    private VoucherToProvisionOrderMgmt voucherToProvisionOrderMgmt = new VoucherToProvisionOrderMgmt(this);

    public VoucherToProvisionOrderMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 500));
	this.setPreferredSize(new Dimension(900, 500));
	addTab(voucherToProvisionOrderList);
	addTab(voucherToProvisionOrderMgmt);
	getTabbedPane().setEnabledAt(1, false);
    }

    

    public void changeSelectedTab() {
	if (getParentInternalFrame() != null) {
	    if (getSelectedTab() == 0) {
		getParentInternalFrame().setInfo("Seleccione las Órdenes de Provisión a facturar y presione el botón \"Facturar\"");
	    } else {
		getParentInternalFrame().setInfo("Ajuste los campos Bonificación y/o Precio en cada uno de los ítems y presione el botón \"Grabar Factura\"");
	    }
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	changeSelectedTab();
    }

    public VoucherToProvisionOrderList getVoucherToProvisionOrderList() {
	return voucherToProvisionOrderList;
    }

    public VoucherToProvisionOrderMgmt getVoucherToProvisionOrderMgmt() {
	return voucherToProvisionOrderMgmt;
    }

    @Override
    public boolean saveData() {
	return voucherToProvisionOrderMgmt.saveData();
    }
}

