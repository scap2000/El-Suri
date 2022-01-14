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
 * ErrorsResourcesRequestsMgmt.java
 *
 * */
package org.digitall.apps.resourcesrequests.interfaces.resourcesrequests.classes.errclasses;


import org.digitall.lib.components.Advisor;

public abstract class ErrorsResourcesRequestsMgmt {

    /* ENCABEZADO */
    public static final int EMPTY_NUMBER = 1;
    public static final int EMPTY_DATE = 2;
    public static final int INVALID_DATE = 3;
    public static final int EMPTY_SOLICITOR = 4;
    public static final int EMPTY_COSTSCENTRE = 5;

    /* DETALLE */    
    public static final int EMPTY_RESOURCE = 6;
    public static final int EMPTY_BRAND = 7;
    public static final int INVALID_QTY = 8;
    public static final int EMPTY_DETAIL = 9;
    public static final int EMPTY_ITEM = 10;
    public static final int DUPLICATE_DETAIL = 11;
    
    public static void showErrorMessage(int _error) {
	switch (_error)  {
	    case EMPTY_NUMBER:
		Advisor.messageBox("El campo \"Nº\" no debe estar vacío","Error");
		break;
	    case EMPTY_DATE:
		Advisor.messageBox("El campo \"Fecha\" no debe estar vacío","Error");
		break;
	    case INVALID_DATE:
		Advisor.messageBox("La Fecha de Solicitud no debe ser superior a la Fecha Actual","Error");
	        break;
	    case EMPTY_SOLICITOR:
		Advisor.messageBox("No se ha seleccionado un Solicitante","Error");
	        break;
	    case EMPTY_COSTSCENTRE:
		Advisor.messageBox("No se ha seleccionado un Centro de Costos","Error");
	        break;
	    case EMPTY_RESOURCE:
		Advisor.messageBox("No se ha seleccionado ningún Material", "Error");
		break;
	    case EMPTY_BRAND:
		Advisor.messageBox("No se ha seleccionado una Marca", "Error");
		break;
	    case INVALID_QTY:
	        Advisor.messageBox("La cantidad debe ser mayor que cero", "Error");
		break;
	    case EMPTY_DETAIL:
		Advisor.messageBox("No se puede grabar un Pedido de Materiales sin detalle", "Error");
		break;
	    case EMPTY_ITEM:
		Advisor.messageBox("Debe seleccionar un material para quitar", "Error");
		break;
	    case DUPLICATE_DETAIL:
		Advisor.messageBox("El Material ya se encuentra en el Detalle", "Error");
		break;
	    default:
		Advisor.messageBox("Error no especificado en Pedido de Materiales", "Error");
		break;
	}
    }
    
}
