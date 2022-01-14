package org.digitall.apps.gaia.relevamientos.comercial_2009.clases.errclasses;

import org.digitall.lib.components.Advisor;

public class ErrDesgloseCarteles {

    private int error = 0;
    
    /* ENCABEZADO */
    public static final int textoCartel = 1;
    public static final int ancho = 2;
    public static final int alto = 3;
    public static final int superficie = 4;
    public static final int faz = 5;
    public static final int forma = 6;
    public static final int iluminacion = 7;
    public static final int empresa = 8;
    
    public ErrDesgloseCarteles() {
    }

    public void setError(int error) {
	this.error = error;
    }

    public int getError() {
	return error;
    }
    
    public void showMessage(){
	switch (error)  {
	    case textoCartel: Advisor.messageBox("El campo \"Texto del cartel \" no debe estar vacío","Mensaje");
		break;
	    case superficie: Advisor.messageBox("El campo \"Superficie\" debe ser mayor que cero","Mensaje");
	        break;
	    case empresa: Advisor.messageBox("El campo \"Superficie\" debe ser mayor que cero","Mensaje");
	        break;
	    /*case ancho: Advisor.messageBox("El campo \"Zona Nro.\" no debe estar vacío y debe ser mayor que cero (0)","Mensaje");
		break;
	    case alto: Advisor.messageBox("El campo \"Nro. de Encuesta\" no debe estar vacío y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case faz: Advisor.messageBox("El campo \"1a. BARRIO\" no debe estar vacío","Mensaje");
	        break;
	    case forma: Advisor.messageBox("El campo \"1b. CALLE\" no debe estar vacío","Mensaje");
	        break;
	    case iluminacion: Advisor.messageBox("El campo \"1c. CÓD. CALLE\" no debe estar vacío","Mensaje");
	        break;
	    */
	}
    }
}
