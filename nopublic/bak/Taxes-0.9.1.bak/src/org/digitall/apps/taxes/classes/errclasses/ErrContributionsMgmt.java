package org.digitall.apps.taxes.classes.errclasses;

import org.digitall.lib.components.Advisor;

public class ErrContributionsMgmt {

    private int error = 0;
    /* ENCABEZADO */
    public static final int CBCONTRIBUCION = 1;
    public static final int CBALICUOTA = 2;
    public static final int CONTRIBUYENTE = 3;
    public static final int MONTO = 4;
    public static final int CONCEPTO = 5;
    public static final int NRODOCUMENTO = 6;
    
    
    public ErrContributionsMgmt() {
    }

    public void setError(int error) {
	this.error = error;
    }

    public int getError() {
	return error;
    }
    
    public void showMessage() {
	switch (error)  {
	    case CBCONTRIBUCION: Advisor.messageBox("Debe seleccionar una Contribución","Mensaje");
		break;
	    case CBALICUOTA: Advisor.messageBox("Debe seleccionar una Alícuota","Mensaje");
		break;
	    case CONTRIBUYENTE: Advisor.messageBox("El campo \"Contribuyente\" no debe estar vacío","Mensaje");
	        break;
	    case MONTO: Advisor.messageBox("El campo \"Total a Pagar\" debe ser mayor que cero ( $ 0,00 )","Mensaje");
	        break;
	    case CONCEPTO: Advisor.messageBox("El campo \"Concepto\" no debe estar vacío","Mensaje");
	        break;
	    case NRODOCUMENTO: Advisor.messageBox("El campo \"D.N.I/C.U.I.L./C.U.I.T\" no debe estar vacío","Mensaje");
	        break;
	}
    }
    
}
