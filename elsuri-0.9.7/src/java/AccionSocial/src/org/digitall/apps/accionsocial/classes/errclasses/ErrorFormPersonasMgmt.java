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
 * ErrorFormPersonasMgmt.java
 *
 * */
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
