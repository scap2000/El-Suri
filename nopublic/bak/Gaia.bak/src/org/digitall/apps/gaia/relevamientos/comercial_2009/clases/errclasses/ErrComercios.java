package org.digitall.apps.gaia.relevamientos.comercial_2009.clases.errclasses;

import org.digitall.lib.components.Advisor;

public class ErrComercios {

    private int error = 0;
    /* ENCABEZADO */
    public static final int F0a = 1;
    public static final int F0b = 2;
    public static final int F0c = 3;
    public static final int F0d = 4;
    
    /* SECCION 1 */
    public static final int F1a = 20;
    public static final int F1b = 21;
    public static final int F1c = 22;
    public static final int F1d = 23;
    public static final int F1e = 24;
    public static final int F1f = 25;
    public static final int F1g = 26;
    public static final int F1h = 27;
    public static final int F1i = 28;
    public static final int F1j = 29;
    public static final int F1k = 30;
    public static final int F1l = 31;
    public static final int Coordenadas = 32;
    
    /* SECCION 2 */
    public static final int F2a = 40;
    public static final int F2b = 41;
    public static final int F2c = 42;
    public static final int F2d = 43;
    public static final int F2e = 44;
    public static final int F2f = 45;
    public static final int F2g = 46;
    public static final int F2h = 47;
    public static final int F2i = 48;
    public static final int F2j = 49;
    public static final int F2k = 50;
    public static final int F2l = 51;
    
    /* SECCION 3 */
    public static final int F3a = 60;
    public static final int F3b = 61;
    public static final int F3c = 62;
    public static final int F3d = 63;
    public static final int F3e = 64;
    public static final int F3f = 65;
    public static final int F3g = 66;
    public static final int F3h = 67;
    public static final int F3i = 68;
    public static final int F3j = 69;
    public static final int F3k = 70;
    public static final int F3l = 71;
    public static final int F3m = 72;
    public static final int F3n = 73;
    public static final int F3o = 74;
    public static final int F3p = 75;
    public static final int F3q = 76;
    public static final int F3r = 77;
    
    /* SECCION 4 */
    public static final int F4a = 80;
    public static final int F4b = 81;
    public static final int F4c = 82;
    public static final int F4d = 83;
    public static final int F4e = 84;
    public static final int F4f = 85;
    public static final int F4g = 86;
    public static final int F4h = 87;
    public static final int F4i = 88;
    public static final int F4j = 89;
    public static final int F4k = 90;
    public static final int F4l = 91;
    public static final int F4m = 92;
    public static final int F4n = 93;
    public static final int F4o = 94;
    public static final int F4p = 95;
    public static final int F4q = 96;
    public static final int F4r = 97;
    
    /* SECCION 5 */
    public static final int F5a = 98;
    public static final int F5b = 99;
    public static final int F5c = 100;
    public static final int F5d = 101;
    public static final int F5e = 102;
    public static final int F6a = 103;
    
    public ErrComercios() {
    }

    public void setError(int error) {
	this.error = error;
    }

    public int getError() {
	return error;
    }
    
    public void showMessage(){
	switch (error)  {
	    case F0a: Advisor.messageBox("El campo \"Nro. de Encuestador\" debe ser mayor que cero (0)","Mensaje");
		break;
	    case F0b: Advisor.messageBox("El campo \"Zona Nro.\" no debe estar vac??o y debe ser mayor que cero (0)","Mensaje");
		break;
	    case F0c: Advisor.messageBox("El campo \"Nro. de Encuesta\" no debe estar vac??o y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case F0d: Advisor.messageBox("El campo \"Fecha\" no debe estar vac??o","Mensaje");
	        break;
	    /* SECCION 1 */
	    case F1a: Advisor.messageBox("El campo \"1a. BARRIO\" no debe estar vac??o","Mensaje");
	        break;
	    case F1b: Advisor.messageBox("El campo \"1b. CALLE\" no debe estar vac??o","Mensaje");
	        break;
	    case F1c: Advisor.messageBox("El campo \"1c. C??D. CALLE\" no debe estar vac??o","Mensaje");
	        break;
	    case F1d: Advisor.messageBox("El campo \"1d. NUMERACI??N\" no debe estar vac??o","Mensaje");
	        break;
	    case F1e: Advisor.messageBox("El campo \"1e. PISO\" no debe estar vac??o","Mensaje");
	        break;
	    case F1f: Advisor.messageBox("El campo \"1f. N?? o LETRA\" no debe estar vac??o","Mensaje");
	        break;
	    case F1g: Advisor.messageBox("El campo \"1g. SECT/BLOQ\" no debe estar vac??o","Mensaje");
	        break;
	    case F1h: Advisor.messageBox("El campo \"1h. MANZANA\" no debe estar vac??o","Mensaje");
	        break;
	    case F1i: Advisor.messageBox("El campo \"1i. CASA\" no debe estar vac??o","Mensaje");
	        break;
	    case F1j: Advisor.messageBox("El campo \"1j. MEDIDOR\" no debe estar vac??o","Mensaje");
	        break;
	    case F1k: Advisor.messageBox("El campo \"1k. C??DULA PARCELARIA\" no debe estar vac??o y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case F1l: Advisor.messageBox("El campo \"1l. (Si aplica) NOMBRE DEL CENTRO COMERCIAL U OTRO\" \nno debe estar vac??o","Mensaje");
	        break;
	    case Coordenadas: Advisor.messageBox("El campo \"COORDENADAS\" \nno debe estar vac??o","Mensaje");
	        break;
	    /* SECCION 2 */
	    case F2a: Advisor.messageBox("El campo \"2a. NOMBRE DE FANTAS??A\" no debe estar vac??o","Mensaje");
	        break;
	    case F2b: Advisor.messageBox("El campo \"2b. RAZ??N SOCIAL\" no debe estar vac??o","Mensaje");
	        break;
	    case F2c: Advisor.messageBox("El campo \"2c. N?? de C.U.I.T.\" no debe estar vac??o","Mensaje");
	        break;
	    case F2d: Advisor.messageBox("El campo \"2d. N?? DE PADR??N COMERCIAL\" no debe estar vac??o","Mensaje");
	        break;
	    case F2e: Advisor.messageBox("El campo \"2e. TEL??FONO FIJO\" no debe estar vac??o","Mensaje");
	        break;
	    case F2f: Advisor.messageBox("El campo \"2f. FAX\" no debe estar vac??o","Mensaje");
	        break;
	    case F2g: Advisor.messageBox("El campo \"2g. CELULAR\" no debe estar vac??o","Mensaje");
	        break;
	    case F2h: Advisor.messageBox("El campo \"2h. E-MAIL\" no debe estar vac??o","Mensaje");
	        break;
	    case F2i: Advisor.messageBox("El campo \"2i. CARACTER??STICAS DEL LOCAL\" no debe estar vac??o","Mensaje");
	        break;
	    case F2j: Advisor.messageBox("El campo \"2j. RUBRO PRINCIPAL\" no debe estar vac??o","Mensaje");
	        break;
	    case F2k: Advisor.messageBox("El campo \"2k. DESCRIPCI??N DEL RUBRO PRINCIPAL\" no debe estar vac??o y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case F2l: Advisor.messageBox("El campo \"2l. DESCRIPCI??N DEL LOS RUBROS SECUNDARI??S\" \nno debe estar vac??o","Mensaje");
	        break;
	    /* SECCION 3 */
	    case F3a: Advisor.messageBox("El campo \"3a. NOMBRE/S DEL PROPIETARIO O SOCIO PRINCIPAL\" no debe estar vac??o","Mensaje");
	        break;
	    case F3b: Advisor.messageBox("El campo \"3b. APELLIDO/S DEL PROPIETARIO O SOCIO PRINCIPAL\" no debe estar vac??o","Mensaje");
	        break;
	    case F3c: Advisor.messageBox("El campo \"3c. SEXO\" no debe estar vac??o","Mensaje");
	        break;
	    case F3d: Advisor.messageBox("El campo \"3d. TIPO DE DOCUMENTO\" no debe estar vac??o","Mensaje");
	        break;
	    case F3e: Advisor.messageBox("El campo \"3e. N?? DE DOCUMENTO\" no debe estar vac??o","Mensaje");
	        break;
	    case F3f: Advisor.messageBox("El campo \"3f. TEL??FONO FIJO\" no debe estar vac??o","Mensaje");
	        break;
	    case F3g: Advisor.messageBox("El campo \"3g. FAX\" no debe estar vac??o","Mensaje");
	        break;
	    case F3h: Advisor.messageBox("El campo \"3h. CELULAR\" no debe estar vac??o","Mensaje");
	        break;
	    case F3i: Advisor.messageBox("El campo \"3i. BARRIO\" no debe estar vac??o","Mensaje");
	        break;
	    case F3j: Advisor.messageBox("El campo \"3j. CALLE\" no debe estar vac??o","Mensaje");
	        break;
	    case F3k: Advisor.messageBox("El campo \"3k. C??D. CALLE\" no debe estar vac??o y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case F3l: Advisor.messageBox("El campo \"3l. NUMERACI??N\" \nno debe estar vac??o","Mensaje");
	        break;	
	    case F3m: Advisor.messageBox("El campo \"3m. PISO\" no debe estar vac??o","Mensaje");
	        break;
	    case F3n: Advisor.messageBox("El campo \"3n. N?? o LETRA\" no debe estar vac??o","Mensaje");
	        break;
	    case F3o: Advisor.messageBox("El campo \"3o. SECT/BLOQ\" no debe estar vac??o","Mensaje");
	        break;
	    case F3p: Advisor.messageBox("El campo \"3p. MANZANA\" no debe estar vac??o","Mensaje");
	        break;
	    case F3q: Advisor.messageBox("El campo \"3q. CASA\" no debe estar vac??o y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case F3r: Advisor.messageBox("El campo \"3r. MEDIDOR\" \nno debe estar vac??o","Mensaje");
	        break;  
	    /* SECCION 4 */
	     case F5a: Advisor.messageBox("El campo \"4a. NOMBRE/S DEL SUMINISTRADOR\" no debe estar vac??o","Mensaje");
	         break;
	     case F5b: Advisor.messageBox("El campo \"4b. APELLIDO/S DEL SUMINISTRADOR\" no debe estar vac??o","Mensaje");
	         break;
	     case F5c: Advisor.messageBox("El campo \"4c. TIPO DE DOCUMENTO\" no debe estar vac??o","Mensaje");
	         break;
	     case F5d: Advisor.messageBox("El campo \"4d. N?? DE DOCUMENTO\" no debe estar vac??o","Mensaje");
	         break;
	     case F5e: Advisor.messageBox("El campo \"4e. RELACI??N CON EL PROPIETARIO DEL NEGOCIO\" no debe estar vac??o","Mensaje");
	         break;
	     /*case F5a: Advisor.messageBox("El campo \"5a. OBSERvac??oNES\" no debe estar vac??o","Mensaje");
	         break;*/
	    
	}
    }
    
}
