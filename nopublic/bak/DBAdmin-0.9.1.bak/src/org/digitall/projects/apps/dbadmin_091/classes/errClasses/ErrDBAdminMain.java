package org.digitall.projects.apps.dbadmin_091.classes.errClasses;

import org.digitall.lib.components.Advisor;

public class ErrDBAdminMain {

    private int error = 0;
    
    /* Todas las ventanas */    
    public static final int OK = 0;
    
    /* FuncionesMgmt */
    public static final int EXISTEFUNCION = 1;
    public static final int SINNOMBREFUNCION = 2;
    
    /* SystemFunctionsGroupsManager */
    public static final int FILANOSELECCIONADA = 3;
    public static final int GRUPONOSELECCIONADO = 4;
    
    
    public ErrDBAdminMain(){
    
    }

    public void setError(int error) {
	this.error = error;
    }

    public int getError() {
	return error;
    }
    
    public void showMessage(){
	switch (error)  {
	    case EXISTEFUNCION: Advisor.messageBox("La funcionalidad ingresada ya existe","Mensaje");
	    break;
	    case SINNOMBREFUNCION: Advisor.messageBox("El campo \"Nombre\" no debe estar vacío","Mensaje");
	    break;
	    case FILANOSELECCIONADA: Advisor.messageBox("Debe tildar una funcionalidad","Mensaje");
	    break;
	    case GRUPONOSELECCIONADO: Advisor.messageBox("Debe seleccionar un grupo","Mensaje");
	    break;
	}
    }
}
