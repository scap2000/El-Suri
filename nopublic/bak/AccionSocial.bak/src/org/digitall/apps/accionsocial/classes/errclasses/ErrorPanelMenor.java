package org.digitall.apps.accionsocial.classes.errclasses;

import org.digitall.lib.components.Advisor;

public class ErrorPanelMenor {
    
    public ErrorPanelMenor() {
        
    }
    
    private int error = 0;
    
    public static final int fechaAlta = 1;
    public static final int fechaNacimiento = 2;
    public static final int estadoNutricional = 3;
    public static final int tutor = 4;
    public static final int domicilioTutor = 5;
    
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
            case fechaNacimiento: Advisor.messageBox("El campo \"Fecha de Nacimiento\" no debe estar vacío","Mensaje");
                break;
            case estadoNutricional: Advisor.messageBox("Debe seleccionar un Estado Nutricional","Mensaje");
                break;
            case tutor: Advisor.messageBox("Debe seleccionar un Tutor","Mensaje");
                break;
            case domicilioTutor: Advisor.messageBox("El campo \"Domicilio del Tutor\" no debe estar vacío","Mensaje");
                break;
        }
    }
}
