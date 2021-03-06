package org.digitall.apps.gaia.relevamientos.comercial_2009.clases.errclasses;

import org.digitall.lib.components.Advisor;

public class ErrCensoComercial2009 {

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

    
    /* SECCION 2a */
    public static final int F2a = 40;
    public static final int F2b = 41;
    public static final int F2c = 42;
    public static final int F2i1_a = 43;
    public static final int F2i1_b = 44;
    
    public static final int F2j1_a = 45;
    public static final int F2j1_b = 46;
    public static final int F2j2 = 137;
    
    
     /* SECCION 2b */
    public static final int F2k1_a = 60;
    public static final int F2k1_b = 61;
    public static final int F2k1_c = 62;
    public static final int F2m = 63;
    public static final int F2n = 64;
    public static final int F2nn = 65;
    public static final int F2o = 66;
    public static final int F2p = 67;
    public static final int F2q = 68;
    public static final int F2r = 69;
    public static final int F2s = 71;
    public static final int F2t = 72;
    public static final int F2u = 73;
    public static final int F2v = 74;
    public static final int F2w = 75;
    public static final int F2x = 76;
    
    /* SECCION 3 */
    public static final int F3a = 80;
    public static final int F3b = 81;
    public static final int F3c = 82;
    public static final int F3d = 83;
    public static final int F3e = 84;
    public static final int F3f = 85;
    public static final int F3g = 86;
    public static final int F3h = 87;
    public static final int F3i = 88;
    public static final int F3j = 89;
    
    public static final int F3k = 90;
    public static final int F3l = 91;
    public static final int F3m = 92;
    public static final int F3n = 93;
    public static final int F3nn = 94;
    public static final int F3o = 95;
    public static final int F3p = 96;
    public static final int F3q = 97;
    public static final int F3r = 98;
    public static final int F3s = 99;
    
    
    /* SECCION 4 */
    public static final int F4a = 110;
    public static final int F4b = 111;
    public static final int F4c1_a = 112;
    public static final int F4c1_b = 113;
    public static final int F4c1_c = 114;
    public static final int F4c1_d = 115;
    public static final int F4c1_e = 116;
    public static final int F4c1_f = 117;
    public static final int F4c1_g = 118;
    
    
    /* SECCION 5 */
    public static final int F5a = 130;
    public static final int F5b = 131;
    public static final int F5c = 132;
    public static final int F5d = 133;
    public static final int F5e = 134;
    public static final int F5f = 135;
    public static final int F6a = 136;
    
    public ErrCensoComercial2009() {
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
	    case Coordenadas: Advisor.messageBox("El campo \"COORDENADAS\" \n debe ser distinto de \"0 ; 0\" ","Mensaje");
	        break;
		
	    /* SECCION 2a */
	    
	    case F2a: Advisor.messageBox("El campo \"2a. NOMBRE DE FANTAS??A\" no debe estar vac??o","Mensaje");
	        break;
	    case F2b: Advisor.messageBox("El campo \"2b. C.U.I.T\" no debe estar vac??o","Mensaje");
	        break;
	    case F2c: Advisor.messageBox("El campo \"2c. N?? DE PADR??N COMERCIAL\" no debe estar vac??o","Mensaje");
	        break;
	    case F2i1_a: Advisor.messageBox("El campo \"2i. DESCRIPCI??N DEL RUBRO PRINCIPAL\" no debe estar vac??o","Mensaje");
	        break;
	    case F2i1_b: Advisor.messageBox("El campo \"2i. C??digo de actividad\" no debe estar vac??o","Mensaje");
	        break;
	    case F2j1_a: Advisor.messageBox("El campo \"2j. DESCRIPCI??N DEL/LOS RUBROS SECUNDARIO/S\" no debe estar vac??o","Mensaje");
	        break;
	    case F2j1_b: Advisor.messageBox("El campo \"2j. C??digo de actividad \" no debe estar vac??o","Mensaje");
	        break;
	    case F2j2: Advisor.messageBox("La fecha de cierre no puede ser menor a la fecha de habilitaci??n","Mensaje");
	        break;
	    
	    /* SECCION 2b */
	     case F2m: Advisor.messageBox("El campo \"2m. TEL??FONO 1\" no debe estar vac??o","Mensaje");
	         break;
	    case F2n: Advisor.messageBox("El campo \"2n. TEL??FONO 2\" no debe estar vac??o","Mensaje");
	        break;
	    case F2nn: Advisor.messageBox("El campo \"2??. FAX\" no debe estar vac??o","Mensaje");
	        break;
	    case F2o: Advisor.messageBox("El campo \"2o. E-MAIL\" no debe estar vac??o","Mensaje");
	        break;
	    case F2p: Advisor.messageBox("El campo \"2p. BARRIO\" no debe estar vac??o","Mensaje");
	        break;
	    case F2q: Advisor.messageBox("El campo \"2q. CALLE\" no debe estar vac??o","Mensaje");
	        break;
	    case F2r: Advisor.messageBox("El campo \"2r. NUMERACI??N\" no debe estar vac??o","Mensaje");
	        break;
	    case F2s: Advisor.messageBox("El campo \"2s. Piso\" no debe estar vac??o","Mensaje");
	       break;
	    case F2t: Advisor.messageBox("El campo \"2t. N??/Letra\" no debe estar vac??o","Mensaje");
	       break;
	    case F2u: Advisor.messageBox("El campo \"2u. Sect/Bloq\" no debe estar vac??o","Mensaje");
	       break;
	    case F2v: Advisor.messageBox("El campo \"2v. Manzana\" no debe estar vac??o","Mensaje");
	       break;
	    case F2w: Advisor.messageBox("El campo \"2w. Casa\" no debe estar vac??o","Mensaje");
	       break;
	    case F2x: Advisor.messageBox("El campo \"2x. Medidor\" no debe estar vac??o","Mensaje");
	        break;
	    
		
	    /* SECCION 3 */
	    case F3a: Advisor.messageBox("El campo \"3a. APELLIDO/S\" no debe estar vac??o","Mensaje");
	        break;
	    case F3b: Advisor.messageBox("El campo \"3b. NOMBRE/S\" no debe estar vac??o","Mensaje");
	        break;
	    case F3c: Advisor.messageBox("El campo \"3c. TIPO DE DOCUMENTO\" no debe estar vac??o","Mensaje");
	        break;
	    case F3d: Advisor.messageBox("El campo \"3d. N??\" no debe estar vac??o","Mensaje");
		break;
	    case F3e: Advisor.messageBox("El campo \"3e. SEXO\" no debe estar vac??o","Mensaje");
	        break;
	    case F3f: Advisor.messageBox("El campo \"3f. NACIONALIDAD\" no debe estar vac??o","Mensaje");
	        break;
	    case F3h: Advisor.messageBox("El campo \"3h. TEL??FONO FIJO\" no debe estar vac??o","Mensaje");
	        break;
	    case F3i: Advisor.messageBox("El campo \"3i. FAX\" no debe estar vac??o","Mensaje");
	        break;
	    case F3j: Advisor.messageBox("El campo \"3j. CELULAR\" no debe estar vac??o","Mensaje");
	        break;
	    case F3k: Advisor.messageBox("El campo \"3k. E-MAIL\" no debe estar vac??o","Mensaje");
	        break;
	    case F3l: Advisor.messageBox("El campo \"3l. BARRIO\" no debe estar vac??o","Mensaje");
	        break;
	    case F3m: Advisor.messageBox("El campo \"3m. CALLE\" no debe estar vac??o","Mensaje");
	        break;
	    case F3n: Advisor.messageBox("El campo \"3n. NUMERACI??N\" no debe estar vac??o","Mensaje");
	        break;	
	    case F3nn: Advisor.messageBox("El campo \"3??. PISO\" no debe estar vac??o","Mensaje");
	        break;
	    case F3o: Advisor.messageBox("El campo \"3o. N?? o LETRA\" no debe estar vac??o","Mensaje");
	        break;
	    case F3p: Advisor.messageBox("El campo \"3p. SECT/BLOQ\" no debe estar vac??o","Mensaje");
	        break;
	    case F3q: Advisor.messageBox("El campo \"3q. MANZANA\" no debe estar vac??o","Mensaje");
	        break;
	    case F3r: Advisor.messageBox("El campo \"3r. CASA\" no debe estar vac??o y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case F3s: Advisor.messageBox("El campo \"3s. MEDIDOR\" no debe estar vac??o","Mensaje");
	        break;  
		

	    /* SECCION 4 */
	     case F4a: Advisor.messageBox("El campo \"4a. RAZ??N SOCIAL\" no debe estar vac??o","Mensaje");
	         break;  
	    case F4b: Advisor.messageBox("El campo \"4b. NATURALEZA JUR??DICA DE LA ENTIDAD\" no debe estar vac??o","Mensaje");
	        break;  
		

	    /* SECCION 5 */
	     case F5a: Advisor.messageBox("El campo \"5a. APELLIDO/S\" no debe estar vac??o","Mensaje");
	         break;
	     case F5b: Advisor.messageBox("El campo \"5b. NOMBRE/S\" no debe estar vac??o","Mensaje");
	         break;
	     case F5c: Advisor.messageBox("El campo \"5c. TIPO DE DOCUMENTO\" no debe estar vac??o","Mensaje");
	         break;
	     case F5d: Advisor.messageBox("El campo \"5d. N?? DE DOCUMENTO\" no debe estar vac??o","Mensaje");
	         break;
	     case F5e: Advisor.messageBox("El campo \"5e. SEXO\" no debe estar vac??o","Mensaje");
	         break;
	    case F5f: Advisor.messageBox("El campo \"5f. NACIONALIDAD\" no debe estar vac??o","Mensaje");
	        break;
	    case F6a: Advisor.messageBox("El campo \"6. OBSERVACIONES\" no debe estar vac??o","Mensaje");
	         break;
	    
	}
    }
}
