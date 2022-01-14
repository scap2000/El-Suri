package org.digitall.apps.accionsocial.classes.errclasses;

import org.digitall.lib.components.Advisor;

public class ErrorPanelEmbarazo {
    
    public ErrorPanelEmbarazo() {
        
    }
    
    private int error = 0;
    
    public static final int fechaAlta = 1;
    public static final int fechaFPP = 2;
    public static final int fechaFUM = 3;
    public static final int barrio = 4;
    
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
            case barrio: Advisor.messageBox("El campo \"Barrio\" no debe estar vacío","Mensaje");
                break;
            case fechaFPP: Advisor.messageBox("El campo \"FPP\" no debe estar vacío","Mensaje");
                break;
            case fechaFUM: Advisor.messageBox("El campo \"FUM\" no debe estar vacío","Mensaje");
                break;
        }
    }

}
