package org.digitall.apps.taskman.classes.errclasses;

import org.digitall.lib.components.Advisor;

public class ErrTareas {
    
    private int error = 0;
    
    /* ENCABEZADO */
    public static final int nombreTarea = 1;
    public static final int tiempoEstimado = 2;
    
    public ErrTareas() {
        super();
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }
    
    public void showMessage(){
        switch (error)  {
            case nombreTarea: Advisor.messageBox("El campo \"Nombre\" no puede estar vacío","Mensaje");
                break;
            case tiempoEstimado: Advisor.messageBox("El campo \"Tiempo estimado (Hs)\" debe ser mayor que cero","Mensaje");
                break;
        }
    }
}
