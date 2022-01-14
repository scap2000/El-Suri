package org.digitall.apps.cashflow.interfaces.voucher;

import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

public class PagosDeTercerosMain extends BasicTabContainer {

    private PagosDeTercerosList pagosDeTercerosList = new PagosDeTercerosList();
    private PagosCajeroList pagosCajeroList = new PagosCajeroList();

    public PagosDeTercerosMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(800, 460));
	this.setPreferredSize(new Dimension(800, 460));
	pagosDeTercerosList.setParentMain(this);
	pagosCajeroList.setParentMain(this);
	addTab("Pagos de Terceros", pagosDeTercerosList);
	addTab("Pagos en Caja", pagosCajeroList);
    }

    public void changeSelectedTab() {
	
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Pagos de Terceros");
    }
    
    public PagosDeTercerosList getPagosDeTercerosList() {
	return pagosDeTercerosList;
    }

    public PagosCajeroList getPagosCajeroList() {
	return pagosCajeroList;
    }
}

