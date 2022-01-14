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
 * BudgetMovements.java
 *
 * */
package org.digitall.common.resourcesrequests.classes;

import org.digitall.lib.sql.LibSQL;

public class BudgetMovements {

    private int idbudgetmovement = -1;
    private int idbudget = -1;
    private int idaccount = -1;
    private int idpurchaseorder = -1;
    private double purchaseorderamount = 0;
    private int idinvoice = -1;
    private double invoiceamount = 0;
    private String estado = "";


    public BudgetMovements() {

    }

    public void setIdbudgetmovement(int idbudgetmovement) {
	this.idbudgetmovement = idbudgetmovement;
    }

    public int getIdbudgetmovement() {
	return idbudgetmovement;
    }

    public void setIdbudget(int idbudget) {
	this.idbudget = idbudget;
    }

    public int getIdbudget() {
	return idbudget;
    }

    public void setIdaccount(int idaccount) {
	this.idaccount = idaccount;
    }

    public int getIdaccount() {
	return idaccount;
    }

    public void setIdpurchaseorder(int idpurchaseorder) {
	this.idpurchaseorder = idpurchaseorder;
    }

    public int getIdpurchaseorder() {
	return idpurchaseorder;
    }

    public void setPurchaseorderamount(double purchaseorderamount) {
	this.purchaseorderamount = purchaseorderamount;
    }

    public double getPurchaseorderamount() {
	return purchaseorderamount;
    }

    public void setIdinvoice(int idinvoice) {
	this.idinvoice = idinvoice;
    }

    public int getIdinvoice() {
	return idinvoice;
    }

    public void setInvoiceamount(double invoiceamount) {
	this.invoiceamount = invoiceamount;
    }

    public double getInvoiceamount() {
	return invoiceamount;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
    
    public void saveData(){
	int result = -1;
	String params = "";
	if (idbudgetmovement == -1)  {
	    params =  idbudget+ "," 
	            + idaccount + "," 
	            + idpurchaseorder + "," 
	            + purchaseorderamount + "," 
	            + idinvoice + "," 
	            + invoiceamount + ",'" 
	            + estado + "'" ;
	    //System.out.println("SELECT resourcescontrol.addResourcesMovements(" + params + ")");
	    result = LibSQL.getInt("cashflow.addBudgetMovements", params);
	    idbudgetmovement = result;
	} else {
	    params =  idbudgetmovement + "," 
		    + idbudget + "," 
	            + idaccount + "," 
	            + idpurchaseorder + "," 
	            + purchaseorderamount + "," 
	            + idinvoice + "," 
	            + invoiceamount + ",'" 
	            + estado + "'" ;
	    //System.out.println("SELECT resourcescontrol.addResourcesMovements(" + params + ")");
	    result = LibSQL.getInt("cashflow.setBudgetMovements", params);
	}
	
    }

}
