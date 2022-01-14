package org.digitall.apps.accionsocial.classes.errclasses;

import org.digitall.lib.components.Advisor;

public class ErrorPanelCeliacos {
    
    private int error = 0;
    
    public static final int fechaAlta = 1;
    public static final int barrio = 2;
    public static final int centroSalud = 3;
    
    public ErrorPanelCeliacos() {
        
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
            case barrio: Advisor.messageBox("El campo \"Barrio\" no debe estar vacío","Mensaje");
                break;
            case centroSalud: Advisor.messageBox("Debe seleccionar un Centro de Salud","Mensaje");
                break;
        }
    }
}
