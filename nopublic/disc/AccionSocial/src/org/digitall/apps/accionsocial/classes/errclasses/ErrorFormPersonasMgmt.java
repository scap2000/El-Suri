package org.digitall.apps.accionsocial.classes.errclasses;

import org.digitall.lib.components.Advisor;

public class ErrorFormPersonasMgmt {

    private int error = 0;
    
    /* FORMULARIO PERSONAS MANAGEMENT */
    public static final int FApellido = 1;
    public static final int FNombres = 2;
    public static final int FDni = 3;
    public static final int FFechaNacimiento = 4;
    public static final int FSexo = 5;
    public static final int FCuil = 20;
    public static final int FDomicilio = 21;
    public static final int FCoordenadas = 22;
    public static final int FNumeracion = 23;

    
    public ErrorFormPersonasMgmt() {
        
    }

    public void setError(int error) {
	this.error = error;
    }

    public int getError() {
	return error;
    }
    
    public void showMessage() {
	switch (error)  {
	    case FApellido: Advisor.messageBox("El campo \"Apellido\" no debe estar vacío ","Mensaje");
		break;
	    case FNombres: Advisor.messageBox("El campo \"Nombres\" no debe estar vacío","Mensaje");
		break;
	    case FDni: Advisor.messageBox("Debe ingresar un número de documento en el campo \"Número\"","Mensaje");
	        break;
	    case FFechaNacimiento: Advisor.messageBox("El campo \"Fecha de Nacimiento\" no debe estar vacío","Mensaje");
	        break;
	    case FSexo: Advisor.messageBox("Debe seleccionar el sexo de la persona","Mensaje");
	        break;
	    case FCuil: Advisor.messageBox("El campo \"CUIL\" no debe estar vacío","Mensaje");
	        break;
	    case FDomicilio: Advisor.messageBox("El campo \"Domicilio\" no debe estar vacío","Mensaje");
	        break;
	    case FCoordenadas: Advisor.messageBox("El campo \"Coordenadas\" no debe estar vacío","Mensaje");
	        break;
	    case FNumeracion: Advisor.messageBox("El campo \"Nº\" debe ser mayor que cero (0)","Mensaje");
	        break;
	}
    }
}
