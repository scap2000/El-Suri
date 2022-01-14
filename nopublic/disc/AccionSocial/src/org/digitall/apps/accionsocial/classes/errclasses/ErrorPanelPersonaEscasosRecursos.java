package org.digitall.apps.accionsocial.classes.errclasses;

import org.digitall.lib.components.Advisor;

public class ErrorPanelPersonaEscasosRecursos {
    
    private int error = 0;
    
    public static final int fechaAlta = 1;
    public static final int domicilio = 2;
    
    public ErrorPanelPersonaEscasosRecursos() {
        
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }
    
    public void showMessage() {
        switch (error)  {
            case fechaAlta: Advisor.messageBox("El campo \"Fecha de Alta\" no debe estar vacío","Mensaje");
                break;
            case domicilio: Advisor.messageBox("El campo \"Domicilio\" no debe estar vacío","Mensaje");
                break;
        }
    }
}
