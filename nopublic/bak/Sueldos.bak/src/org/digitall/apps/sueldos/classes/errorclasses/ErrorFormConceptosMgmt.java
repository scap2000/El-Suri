package org.digitall.apps.sueldos.classes.errorclasses;

import org.digitall.lib.components.Advisor;

public class ErrorFormConceptosMgmt {
    
    private int error = 0;
    
    /* FORMULARIO CONCEPTOS MANAGEMENT */
    
    public static final int FNOMBRE = 1;

    
    public ErrorFormConceptosMgmt() {
        super();
    }
    
    public void setError(int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }
    
    public void showMessage() {
        switch (error)  {
            case FNOMBRE: Advisor.messageBox("El campo \"Nombre de Concepto\" no debe estar vacío ","Mensaje");
                break;
        }
    }
}

