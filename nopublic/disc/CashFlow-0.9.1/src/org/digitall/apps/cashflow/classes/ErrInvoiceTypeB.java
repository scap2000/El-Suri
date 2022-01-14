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
 * ErrInvoiceTypeB.java
 *
 * */
package org.digitall.apps.cashflow.classes;

import org.digitall.lib.components.Advisor;

public class ErrInvoiceTypeB {

    private int error = 0;
    /* ENCABEZADO */
    public static final int voucherNumber = 1;
    public static final int voucherDate = 2;
    public static final int voucherProvider = 3;
    public static final int voucherCAI = 4;
    public static final int voucherPrefijo = 5;
    public static final int voucherSufijo = 6;
    
    /* DETALLE */
    public static final int requestedQty = 10;
    public static final int unitPrice = 11;
    public static final int vatp = 12;
    public static final int resource = 13;
    
    public ErrInvoiceTypeB() {
    }
    
    public void setError(int error) {
	this.error = error;
    }

    public int getError() {
	return error;
    }
    
    public void showMessage(){
	switch (error)  {
	    case voucherNumber: Advisor.messageBox("El campo \"Nº\" no debe estar vacío y debe ser mayor que cero (0)","Mensaje");
		break;
	    case voucherDate: Advisor.messageBox("El campo \"Fecha\" no debe estar vacío","Mensaje");
	        break;
	    case voucherProvider: Advisor.messageBox("El campo \"Proveedor\" no debe estar vacío","Mensaje");
	        break;
	    case voucherPrefijo: Advisor.messageBox("El prefijo del Nº de comprobante debe ser mayor que cero (0)","Mensaje");
	        break;
	    case voucherSufijo: Advisor.messageBox("El sufijo del Nº de comprobante debe ser mayor que cero (0)","Mensaje");
	        break;
	    case resource: Advisor.messageBox("El campo \"Recursos\" no debe estar vacío","Mensaje");
	        break;
	    case requestedQty: Advisor.messageBox("El campo \"Cant\" debe ser mayor que cero (0)","Mensaje");
		break;
	    case unitPrice: Advisor.messageBox("El campo \"($) Precio\" debe ser mayor que cero (0)","Mensaje");
	        break;
	    case vatp: Advisor.messageBox("El campo \"% IVA\" debe ser mayor que cero (0)","Mensaje");
	        break;
	}
    }
}
