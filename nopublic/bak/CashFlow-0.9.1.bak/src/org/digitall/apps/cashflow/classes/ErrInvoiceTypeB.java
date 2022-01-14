package org.digitall.apps.cashflow.classes;

import org.digitall.lib.components.Advisor;

public class ErrInvoiceTypeB {

    private int error = 0;
    /* ENCABEZADO */
    public static final int voucherNumber = 1;
    public static final int voucherDate = 2;
    public static final int voucherProvider = 3;
    public static final int voucherCAI = 4;
    public static final int voucherPrefijo = 5;
    public static final int voucherSufijo = 6;
    
    /* DETALLE */
    public static final int requestedQty = 10;
    public static final int unitPrice = 11;
    public static final int vatp = 12;
    public static final int resource = 13;
    
    public ErrInvoiceTypeB() {
    }
    
    public void setError(int error) {
	this.error = error;
    }

    public int getError() {
	return error;
    }
    
    public void showMessage(){
	switch (error)  {
	    case voucherNumber: Advisor.messageBox("El campo \"Nº\" no debe estar vacío y debe ser mayor que cero (0)","Mensaje");
		break;
	    case voucherDate: Advisor.messageBox("El campo \"Fecha\" no debe estar vacío","Mensaje");
	        break;
	    case voucherProvider: Advisor.messageBox("El campo \"Proveedor\" no debe estar vacío","Mensaje");
	        break;
	    case voucherPrefijo: Advisor.messageBox("El prefijo del Nº de comprobante debe ser mayor que cero (0)","Mensaje");
	        break;
	    case voucherSufijo: Advisor.messageBox("El sufijo del Nº de comprobante debe ser mayor que cero (0)","Mensaje");
	        break;
	    case resource: Advisor.messageBox("El campo \"Recursos\" no debe estar vacío","Mensaje");
	        break;
	    case requestedQty: Advisor.messageBox("El campo \"Cant\" debe ser mayor que cero (0)","Mensaje");
		break;
	    case unitPrice: Advisor.messageBox("El campo \"($) Precio\" debe ser mayor que cero (0)","Mensaje");
	        break;
	    case vatp: Advisor.messageBox("El campo \"% IVA\" debe ser mayor que cero (0)","Mensaje");
	        break;
	}
    }
}
