/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * ErrComercios.java
 *
 * */
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
	    case F0b: Advisor.messageBox("El campo \"Zona Nro.\" no debe estar vacío y debe ser mayor que cero (0)","Mensaje");
		break;
	    case F0c: Advisor.messageBox("El campo \"Nro. de Encuesta\" no debe estar vacío y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case F0d: Advisor.messageBox("El campo \"Fecha\" no debe estar vacío","Mensaje");
	        break;
	    /* SECCION 1 */
	    case F1a: Advisor.messageBox("El campo \"1a. BARRIO\" no debe estar vacío","Mensaje");
	        break;
	    case F1b: Advisor.messageBox("El campo \"1b. CALLE\" no debe estar vacío","Mensaje");
	        break;
	    case F1c: Advisor.messageBox("El campo \"1c. CÓD. CALLE\" no debe estar vacío","Mensaje");
	        break;
	    case F1d: Advisor.messageBox("El campo \"1d. NUMERACIÓN\" no debe estar vacío","Mensaje");
	        break;
	    case F1e: Advisor.messageBox("El campo \"1e. PISO\" no debe estar vacío","Mensaje");
	        break;
	    case F1f: Advisor.messageBox("El campo \"1f. Nº o LETRA\" no debe estar vacío","Mensaje");
	        break;
	    case F1g: Advisor.messageBox("El campo \"1g. SECT/BLOQ\" no debe estar vacío","Mensaje");
	        break;
	    case F1h: Advisor.messageBox("El campo \"1h. MANZANA\" no debe estar vacío","Mensaje");
	        break;
	    case F1i: Advisor.messageBox("El campo \"1i. CASA\" no debe estar vacío","Mensaje");
	        break;
	    case F1j: Advisor.messageBox("El campo \"1j. MEDIDOR\" no debe estar vacío","Mensaje");
	        break;
	    case F1k: Advisor.messageBox("El campo \"1k. CÉDULA PARCELARIA\" no debe estar vacío y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case F1l: Advisor.messageBox("El campo \"1l. (Si aplica) NOMBRE DEL CENTRO COMERCIAL U OTRO\" \nno debe estar vacío","Mensaje");
	        break;
	    case Coordenadas: Advisor.messageBox("El campo \"COORDENADAS\" \nno debe estar vacío","Mensaje");
	        break;
	    /* SECCION 2 */
	    case F2a: Advisor.messageBox("El campo \"2a. NOMBRE DE FANTASÍA\" no debe estar vacío","Mensaje");
	        break;
	    case F2b: Advisor.messageBox("El campo \"2b. RAZÓN SOCIAL\" no debe estar vacío","Mensaje");
	        break;
	    case F2c: Advisor.messageBox("El campo \"2c. Nº de C.U.I.T.\" no debe estar vacío","Mensaje");
	        break;
	    case F2d: Advisor.messageBox("El campo \"2d. Nº DE PADRÓN COMERCIAL\" no debe estar vacío","Mensaje");
	        break;
	    case F2e: Advisor.messageBox("El campo \"2e. TELÉFONO FIJO\" no debe estar vacío","Mensaje");
	        break;
	    case F2f: Advisor.messageBox("El campo \"2f. FAX\" no debe estar vacío","Mensaje");
	        break;
	    case F2g: Advisor.messageBox("El campo \"2g. CELULAR\" no debe estar vacío","Mensaje");
	        break;
	    case F2h: Advisor.messageBox("El campo \"2h. E-MAIL\" no debe estar vacío","Mensaje");
	        break;
	    case F2i: Advisor.messageBox("El campo \"2i. CARACTERÍSTICAS DEL LOCAL\" no debe estar vacío","Mensaje");
	        break;
	    case F2j: Advisor.messageBox("El campo \"2j. RUBRO PRINCIPAL\" no debe estar vacío","Mensaje");
	        break;
	    case F2k: Advisor.messageBox("El campo \"2k. DESCRIPCIÓN DEL RUBRO PRINCIPAL\" no debe estar vacío y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case F2l: Advisor.messageBox("El campo \"2l. DESCRIPCIÓN DEL LOS RUBROS SECUNDARIÓS\" \nno debe estar vacío","Mensaje");
	        break;
	    /* SECCION 3 */
	    case F3a: Advisor.messageBox("El campo \"3a. NOMBRE/S DEL PROPIETARIO O SOCIO PRINCIPAL\" no debe estar vacío","Mensaje");
	        break;
	    case F3b: Advisor.messageBox("El campo \"3b. APELLIDO/S DEL PROPIETARIO O SOCIO PRINCIPAL\" no debe estar vacío","Mensaje");
	        break;
	    case F3c: Advisor.messageBox("El campo \"3c. SEXO\" no debe estar vacío","Mensaje");
	        break;
	    case F3d: Advisor.messageBox("El campo \"3d. TIPO DE DOCUMENTO\" no debe estar vacío","Mensaje");
	        break;
	    case F3e: Advisor.messageBox("El campo \"3e. Nº DE DOCUMENTO\" no debe estar vacío","Mensaje");
	        break;
	    case F3f: Advisor.messageBox("El campo \"3f. TELÉFONO FIJO\" no debe estar vacío","Mensaje");
	        break;
	    case F3g: Advisor.messageBox("El campo \"3g. FAX\" no debe estar vacío","Mensaje");
	        break;
	    case F3h: Advisor.messageBox("El campo \"3h. CELULAR\" no debe estar vacío","Mensaje");
	        break;
	    case F3i: Advisor.messageBox("El campo \"3i. BARRIO\" no debe estar vacío","Mensaje");
	        break;
	    case F3j: Advisor.messageBox("El campo \"3j. CALLE\" no debe estar vacío","Mensaje");
	        break;
	    case F3k: Advisor.messageBox("El campo \"3k. CÓD. CALLE\" no debe estar vacío y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case F3l: Advisor.messageBox("El campo \"3l. NUMERACIÓN\" \nno debe estar vacío","Mensaje");
	        break;	
	    case F3m: Advisor.messageBox("El campo \"3m. PISO\" no debe estar vacío","Mensaje");
	        break;
	    case F3n: Advisor.messageBox("El campo \"3n. Nº o LETRA\" no debe estar vacío","Mensaje");
	        break;
	    case F3o: Advisor.messageBox("El campo \"3o. SECT/BLOQ\" no debe estar vacío","Mensaje");
	        break;
	    case F3p: Advisor.messageBox("El campo \"3p. MANZANA\" no debe estar vacío","Mensaje");
	        break;
	    case F3q: Advisor.messageBox("El campo \"3q. CASA\" no debe estar vacío y debe ser mayor que cero (0)","Mensaje");
	        break;
	    case F3r: Advisor.messageBox("El campo \"3r. MEDIDOR\" \nno debe estar vacío","Mensaje");
	        break;  
	    /* SECCION 4 */
	     case F5a: Advisor.messageBox("El campo \"4a. NOMBRE/S DEL SUMINISTRADOR\" no debe estar vacío","Mensaje");
	         break;
	     case F5b: Advisor.messageBox("El campo \"4b. APELLIDO/S DEL SUMINISTRADOR\" no debe estar vacío","Mensaje");
	         break;
	     case F5c: Advisor.messageBox("El campo \"4c. TIPO DE DOCUMENTO\" no debe estar vacío","Mensaje");
	         break;
	     case F5d: Advisor.messageBox("El campo \"4d. Nº DE DOCUMENTO\" no debe estar vacío","Mensaje");
	         break;
	     case F5e: Advisor.messageBox("El campo \"4e. RELACIÓN CON EL PROPIETARIO DEL NEGOCIO\" no debe estar vacío","Mensaje");
	         break;
	     /*case F5a: Advisor.messageBox("El campo \"5a. OBSERvacíoNES\" no debe estar vacío","Mensaje");
	         break;*/
	    
	}
    }
    
}
