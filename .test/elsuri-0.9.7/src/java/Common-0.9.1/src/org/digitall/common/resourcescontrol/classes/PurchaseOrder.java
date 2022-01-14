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
 * PurchaseOrder.java
 *
 * */
package org.digitall.common.resourcescontrol.classes;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.lib.sql.LibSQL;

public class PurchaseOrder {

    private int idPurchaseOrder = -1;
    private String number = "";
    private String date = "";
    private double amount = 0;
    private String description = "";
    private String estado = "";
    private CostsCentre costsCentre;
    private Provider provider;
    private int idRequestStatus = 1;
    private boolean isProvisionOrder = false;

    public PurchaseOrder(int _idPurchaseOrder) {
	idPurchaseOrder = _idPurchaseOrder;
    }

    public PurchaseOrder() {
    }

    public void setIdPurchaseOrder(int _idPurchaseOrder) {
	idPurchaseOrder = _idPurchaseOrder;
    }

    public int getIdPurchaseOrder() {
	return idPurchaseOrder;
    }

    public void setNumber(String _number) {
	number = _number;
    }

    public String getNumber() {
	return number;
    }

    public void setDate(String _date) {
	date = _date;
    }

    public String getDate() {
	return date;
    }

    public void setAmount(double _amount) {
	amount = _amount;
    }

    public double getAmount() {
	return amount;
    }

    public void setDescription(String _description) {
	description = _description;
    }

    public String getDescription() {
	return description;
    }

    public void setEstado(String _estado) {
	estado = _estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setCostsCentre(CostsCentre _costsCentre) {
	costsCentre = _costsCentre;
    }

    public CostsCentre getCostsCentre() {
	return costsCentre;
    }

    public void setProvider(Provider _provider) {
	provider = _provider;
    }

    public Provider getProvider() {
	return provider;
    }
    
    public int saveData(){
	int result = -1;
	String params = "'"+ date +"','"+ number +"',"+ amount +",'"+ description +"',"+ costsCentre.getIdCostCentre() +","+ 
			provider.getIdProvider() +","+ idRequestStatus;
	if (idPurchaseOrder == -1)  {
	    result = LibSQL.getInt("resourcescontrol.addPurchaseOrder", params);
	    idPurchaseOrder = result;
	} else {
	    params = idPurchaseOrder + "," + params;
	    result = LibSQL.getInt("resourcescontrol.setPurchaseOrder", params);
	}
	 return result;
    }

    public int getNewNumber(){
	return LibSQL.getInt("resourcescontrol.getNewNumber", "");
    }
    
    public void setIdRequestStatus(int idRequestStatus) {
	this.idRequestStatus = idRequestStatus;
    }

    public int getIdRequestStatus() {
	return idRequestStatus;
    }

    public void setIsProvisionOrder(boolean isProvisionNote) {
        this.isProvisionOrder = isProvisionNote;
    }

    public boolean isProvisionOrder() {
        return isProvisionOrder;
    }
    
    public void retrieveData(){
        ResultSet data = LibSQL.exFunction("resourcescontrol.getPurchaseOrder","" + idPurchaseOrder);
        try  {
            if (data.next()) {
                idPurchaseOrder = data.getInt("idpurchaseorder");
                number = data.getString("number");
                date = data.getString("date");
                amount = data.getDouble("amount");
                description = data.getString("description");
                costsCentre = new CostsCentre(data.getInt("idcostcentre"));
                provider = new Provider(data.getInt("idprovider"));
                idRequestStatus = data.getInt("idrequeststatus");
                isProvisionOrder = data.getBoolean("isprovisionorder");
            }
            
        } catch (SQLException ex)  {
            ex.printStackTrace();
        }
        
    }
    
    public int saveProvisionOrder(){
        int result = -1;
        String params = "'"+ date +"','"+ number +"',"+ amount +",'"+ description +"',"+ costsCentre.getIdCostCentre() +","+ 
                        provider.getIdProvider() +","+ idRequestStatus + "," + isProvisionOrder();
        if (idPurchaseOrder == -1)  {
            result = LibSQL.getInt("resourcescontrol.addPurchaseOrderByProvisionOrder", params);
            idPurchaseOrder = result;
        } else {
            /*params = idPurchaseOrder + "," + params;
            result = LibSQL.getInt("resourcescontrol.setPurchaseOrder", params);*/
            result = -1;
        }
        return result;
    }
    
    public int delProvisionOrder() {
        String params = "" + idPurchaseOrder;
        return LibSQL.getInt("resourcescontrol.delProvisionOrder","" + params);

    }
    
    public int delPurchaseOrder() {
        String params = "" + idPurchaseOrder;
	idPurchaseOrder = -1;
        return LibSQL.getInt("resourcescontrol.delPurchaseOrder","" + params);

    }
    
    public int addBudgetMovementByProvisionOrder() {
        return LibSQL.getInt("cashflow.addBudgetMovementByProvisionOrder","" + idPurchaseOrder);
    }    
    
    public int addBudgetMovementByPurchaseOrder() {
        return LibSQL.getInt("cashflow.addBudgetMovementByPurchaseOrder","" + idPurchaseOrder);
    }    
    
}
