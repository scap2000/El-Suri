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
 * ErrPublicidad.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.clases.errclasses;

import org.digitall.lib.components.Advisor;

public class ErrPublicidad {

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
    public static final int CoordenadasCartel = 79;
    
    public ErrPublicidad() {
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
	     case F2a: Advisor.messageBox("El campo \"2a. NOMBRE/S DEL PROPIETARIO, SOCIO PRINCIPAL O CONSORCIONISTA\" no debe estar vacío","Mensaje");
	         break;
	     case F2b: Advisor.messageBox("El campo \"2b. APELLIDOS/S DEL PROPIETARIO, SOCIO PRINCIPAL O CONSORCIONISTA\" no debe estar vacío","Mensaje");
	         break;
	     case F2c: Advisor.messageBox("El campo \"2c. SEXO\" no debe estar vacío","Mensaje");
	         break;
	     case F2d: Advisor.messageBox("El campo \"2d. TIPO DE DOCUMENTO\" no debe estar vacío","Mensaje");
	         break;
	     case F2e: Advisor.messageBox("El campo \"2e. Nº DE DOCUMENTO\" no debe estar vacío","Mensaje");
	         break;
	     case F2f: Advisor.messageBox("El campo \"2f. TELÉFONO FIJO\" no debe estar vacío","Mensaje");
	         break;
	     case F2g: Advisor.messageBox("El campo \"2g. FAX\" no debe estar vacío","Mensaje");
	         break;
	     case F2h: Advisor.messageBox("El campo \"2h. CELULAR\" no debe estar vacío","Mensaje");
	         break;
	     
	    /* SECCION 3 */
	     case F3a: Advisor.messageBox("El campo \"3a. FORMA BÁSICA DEL ANUNCIO\" no debe estar vacío","Mensaje");
	         break;
	     case F3b: Advisor.messageBox("El campo \"3b. CARACTERÍSTICAS DEL ANUNCIO\" no debe estar vacío","Mensaje");
	         break;
	     case F3c: Advisor.messageBox("El campo \"3c. ALTO\" no debe estar vacío","Mensaje");
	         break;
	     case F3d: Advisor.messageBox("El campo \"3d. ANCHO\" no debe estar vacío","Mensaje");
	         break;
	     case F3e: Advisor.messageBox("El campo \"3e. FAZ\" no debe estar vacío","Mensaje");
	         break;
	     case F3f: Advisor.messageBox("El campo \"3f. TOTAL\" no debe estar vacío","Mensaje");
	         break;
	     case F3g: Advisor.messageBox("El campo \"3g. TEXTO DEL ANUNCIO\" no debe estar vacío","Mensaje");
	         break;
	     case F3h: Advisor.messageBox("El campo \"3h. CORRESPONDE FOTOGRAFIA\" no debe estar vacío","Mensaje");
	         break;
	    case CoordenadasCartel: Advisor.messageBox("El campo \"COORDENADAS\" \nno debe estar vacío","Mensaje");
	        break;
	}
    }
    
    }
