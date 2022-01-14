package org.digitall.apps.taxes.interfases.cashier;

import java.awt.Dimension;

import org.digitall.apps.taxes.classes.Caja;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.sql.LibSQL;

public class CashierMain extends BasicTabContainer {

    private CashierButtons cashierButtons = new CashierButtons();
    private CashierMgmt cashierMgmt = new CashierMgmt();
    private CashierPaymentList cashierPaymentList = new CashierPaymentList();
    private CashierPaymentOrdersMgmt cashierPaymentOrdersMgmt = new CashierPaymentOrdersMgmt();
    private CashierAnticiposDeHaberesMgmt cashierAnticiposDeHaberesMgmt = new CashierAnticiposDeHaberesMgmt();
    private CashierAnticiposDeHaberesEmitidosMgmt cashierAnticiposDeHaberesEmitidosMgmt = new CashierAnticiposDeHaberesEmitidosMgmt();
    private CashierPagosRecaudacion cashierPagosRecaudacion = new CashierPagosRecaudacion();
    
    private Caja caja = new Caja(0);

    public CashierMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(700, 550));
	this.setPreferredSize(new Dimension(700, 550));
	cashierButtons.setParentMain(this);
	addTab("Opciones de Caja", cashierButtons);
	addTab("Caja", cashierMgmt);
	addTab("Detalle de Pagos", cashierPaymentList);
        //addTab("Órdenes de Pago", cashierPaymentOrdersMgmt);
        addTab("Anticipo de Haberes", cashierAnticiposDeHaberesMgmt);
        addTab("Anticipos de Haberes Emitidos", cashierAnticiposDeHaberesEmitidosMgmt);
	//addTab("Pagos/Recaudación", cashierPagosRecaudacion);
	getTabbedPane().setEnabledAt(0, true);
	getTabbedPane().setEnabledAt(1, false);
	getTabbedPane().setEnabledAt(2, false);
	getTabbedPane().setEnabledAt(3, true);
        getTabbedPane().setEnabledAt(4, true);
	//getTabbedPane().setEnabledAt(5, true);
	//getTabbedPane().setEnabledAt(6, true);
	getTabbedPane().setSelectedIndex(0);
	setEnabledForms();
    }

    public void changeSelectedTab() {
	if (getParentInternalFrame()!= null) {
            
	    if ( (getTabbedPane().getSelectedIndex() == 0) || (getTabbedPane().getSelectedIndex() == 1) || (getTabbedPane().getSelectedIndex() == 2) ) {
                getParentInternalFrame().setInfo(getInfoCaja());
            } else if (getTabbedPane().getSelectedIndex() == 3) {
                getParentInternalFrame().setInfo("Busque el Empleado, ingrese el Monto y presione el botón \"Registrar Anticipo de Haberes\" ");
            } else if (getTabbedPane().getSelectedIndex() == 4) {
                getParentInternalFrame().setInfo("Busque y seleccione los Anticipos, ingrese la Fecha de Pago y presione el botón \"Confirmar Anticipo de Haberes\" ");
            }
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    public void setEnabledForms() {
	caja.setIdCaja(LibSQL.getInt("cashier.isOpened",""));
	if ( caja.getIdCaja() > 0) {
	    getTabbedPane().setEnabledAt(1, true);
	    getTabbedPane().setEnabledAt(2, true);
	    cashierButtons.setEnabledButtons(true);
	    getTabbedPane().setSelectedIndex(1);
	} else {
	    getTabbedPane().setEnabledAt(1, false);
	    getTabbedPane().setEnabledAt(2, false); 
	    cashierButtons.setEnabledButtons(false);
	}
	caja.retrieveData();
	if (getParentInternalFrame()!= null) {
	    getParentInternalFrame().setInfo(getInfoCaja());
	}
    }
    
    public String getInfoCaja() {
	return caja.getIdCaja() > 0?"CAJA Nº "+ caja.getNroCaja() : "CAJERO";
    }
    
    public Caja getCaja(){
	return caja;
    }
    
}