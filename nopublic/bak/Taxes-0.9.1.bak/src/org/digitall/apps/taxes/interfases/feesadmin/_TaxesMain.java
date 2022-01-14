package org.digitall.apps.taxes.interfases.feesadmin;

import java.awt.Dimension;

import org.digitall.apps.taxes.interfases.feesadmin.TaxPayerList;
import org.digitall.apps.taxes.interfases.feesadmin.TaxesMgmt;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;


public class _TaxesMain extends BasicTabContainer {

    private TaxPayerList taxPayerList = new TaxPayerList();
    private TaxesMgmt taxesMgmt = new TaxesMgmt();
    private PlansOfPaymentMgmt plansOfPaymentMgmt = new PlansOfPaymentMgmt();
    private PayMoratoriumFeesMgmt payMoratoriumFeesMgmt = new PayMoratoriumFeesMgmt();
    private PayFeesMgmt payFeesMgmt = new PayFeesMgmt();

    public _TaxesMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 500));
	this.setPreferredSize(new Dimension(900, 500));
	addTab(taxPayerList);
	addTab(taxesMgmt);
        addTab(plansOfPaymentMgmt);
	addTab(payMoratoriumFeesMgmt);
	addTab(payFeesMgmt);
	setTitle("");
	getTabbedPane().setEnabledAt(0,true);
	getTabbedPane().setEnabledAt(1,false);
	getTabbedPane().setEnabledAt(2,false);
	getTabbedPane().setEnabledAt(3,false);
	getTabbedPane().setEnabledAt(4,false);
	setSelectedTab(0);
    }

    public void changeSelectedTab() {
	if (getParentInternalFrame() != null) {
	    if (getSelectedTab() == 0) {
		getParentInternalFrame().setInfo("Haga doble click sobre un Catastro/Dominio");
	    } else if (getSelectedTab() == 1) {
		getParentInternalFrame().setInfo("Seleccione los anticipos a pagar y presione el botón \"Config. Moratoria\" o \"Grabar Boleta\"");
	    } else if (getSelectedTab() == 2) {
		getParentInternalFrame().setInfo("Seleccione la forma de Pago y los descuentos y presione el botón \"Grabar Moratoria\"");
	    }
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Haga doble click sobre Catastro/Dominio");
    }

    public TaxPayerList getTaxPayerList() {
	return taxPayerList;
    }

    public TaxesMgmt getTaxesMgmt() {
	return taxesMgmt;
    }

    public PlansOfPaymentMgmt plansOfPaymentMgmt() {
        return plansOfPaymentMgmt;
    }
    
    public PayMoratoriumFeesMgmt getPayMoratoriumFeesMgmt() {
	return payMoratoriumFeesMgmt;
    }
    
    public PayFeesMgmt getPayFeesMgmt() {
	return payFeesMgmt;
    }
    
    public void setEnabledPanels(int _solapa) {
	switch (_solapa)  {
	    case 0: {	
		    getTabbedPane().setEnabledAt(0,true);
		    getTabbedPane().setEnabledAt(1,false);
		    getTabbedPane().setEnabledAt(2,false);
		    getTabbedPane().setEnabledAt(3,false);
		    getTabbedPane().setEnabledAt(4,false);
		    setSelectedTab(0);
		}
		break;
	    case 1: {
	            getTabbedPane().setEnabledAt(0,false);
	            getTabbedPane().setEnabledAt(1,true);
	            getTabbedPane().setEnabledAt(2,false);
	            getTabbedPane().setEnabledAt(3,false);
	            getTabbedPane().setEnabledAt(4,false);
	            setSelectedTab(1);
	        }
	        break;
	    case 2: {
	            getTabbedPane().setEnabledAt(0,false);
	            getTabbedPane().setEnabledAt(1,false);
	            getTabbedPane().setEnabledAt(2,true);
	            getTabbedPane().setEnabledAt(3,false);
	            getTabbedPane().setEnabledAt(4,false);
	            setSelectedTab(2);
	        }
	        break;
	    case 3: {
	            getTabbedPane().setEnabledAt(0,false);
	            getTabbedPane().setEnabledAt(1,false);
	            getTabbedPane().setEnabledAt(2,false);
	            getTabbedPane().setEnabledAt(3,true);
	            getTabbedPane().setEnabledAt(4,false);
	            setSelectedTab(3);
	        }
	        break;
	    case 4: {
	            getTabbedPane().setEnabledAt(0,false);
	            getTabbedPane().setEnabledAt(1,false);
	            getTabbedPane().setEnabledAt(2,false);
	            getTabbedPane().setEnabledAt(3,false);
	            getTabbedPane().setEnabledAt(4,true);
	            setSelectedTab(4);
	        }
	        break;
	}
	
    }

}



