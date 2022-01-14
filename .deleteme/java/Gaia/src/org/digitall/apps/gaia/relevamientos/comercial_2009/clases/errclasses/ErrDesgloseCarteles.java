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
 * ErrDesgloseCarteles.java
 *
 * */
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
