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
 * PurchaseOrderDetail.java
 *
 * */
package org.digitall.common.resourcescontrol.classes;

import org.digitall.common.resourcescontrol.classes.PurchaseOrder;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.ResourcesRequestDetail;
import org.digitall.lib.sql.LibSQL;

public class PurchaseOrderDetail {
    
    private int idPurchaseOrderDetail = -1;
    private PurchaseOrder purchaseOrder;
    private double approvedQty = 0;
    private double receivedQty = 0;
    private double price = 0;
    private double amount = 0;
    private String description = "";
    private ResourceBrands resourceBrand;
    private ResourcesRequestDetail resourcesRequestAuthDetailRef;
    private int idRequestStatus = 1;
    
    public PurchaseOrderDetail(PurchaseOrder _purchaseOrder){
	purchaseOrder = _purchaseOrder;
    }

    public PurchaseOrderDetail(){
	
    }
    
    public void setIdPurchaseOrderDetail(int idPurchaseOrderDetail) {
	this.idPurchaseOrderDetail = idPurchaseOrderDetail;
    }

    public int getIdPurchaseOrderDetail() {
	return idPurchaseOrderDetail;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
	this.purchaseOrder = purchaseOrder;
    }

    public PurchaseOrder getPurchaseOrder() {
	return purchaseOrder;
    }

    public void setApprovedQty(double approvedQty) {
	this.approvedQty = approvedQty;
    }

    public double getApprovedQty() {
	return approvedQty;
    }

    public void setReceivedQty(double receivedQty) {
	this.receivedQty = receivedQty;
    }

    public double getReceivedQty() {
	return receivedQty;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public double getPrice() {
	return price;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public double getAmount() {
	return amount;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    public void setResourceBrand(ResourceBrands resourceBrand) {
	this.resourceBrand = resourceBrand;
    }

    public ResourceBrands getResourceBrand() {
	return resourceBrand;
    }

    public void setIdRequestStatus(int idRequestStatus) {
	this.idRequestStatus = idRequestStatus;
    }

    public int getIdRequestStatus() {
	return idRequestStatus;
    }

    public int saveData(){
	int result = -1;
	String param = purchaseOrder.getIdPurchaseOrder() +","+ resourceBrand.getResource().getIdResource() +","+ approvedQty +","+ 
			price +","+ amount +",'"+ description +"',"+ resourceBrand.getBrands().getIdBrand() +","+ 
			resourcesRequestAuthDetailRef.getIdResourcesRequestDetail() +","+ idRequestStatus +","+ receivedQty;
        result = LibSQL.getInt("resourcescontrol.addPurchaseOrderDetail","" +  param);
	return result;
    }

    public int generateDetail(int _idResourcesRequest){
	String params = purchaseOrder.getIdPurchaseOrder() +","+ _idResourcesRequest;
	return LibSQL.getInt("resourcescontrol.generatePurchaseOrderDetail", params);
    }
    
    public void setResourcesRequestAuthDetailRef(ResourcesRequestDetail resourcesRequestAuthDetailRef) {
	this.resourcesRequestAuthDetailRef = resourcesRequestAuthDetailRef;
    }

    public ResourcesRequestDetail getResourcesRequestAuthDetailRef() {
	return resourcesRequestAuthDetailRef;
    }
    
    public int delete(){
        String params = "" + idPurchaseOrderDetail;
        return LibSQL.getInt("resourcescontrol.delPurchaseOrderDetail", params);
    }
    
    public int saveDataByProvisionOrder() {
        int result = -1;
        String param = purchaseOrder.getIdPurchaseOrder() +","+ resourceBrand.getResource().getIdResource() +","+ approvedQty +","+ 
                        price +","+ amount +",'"+ description +"',"+ resourceBrand.getBrands().getIdBrand() +","+ 
                        + idRequestStatus +","+ receivedQty;
            result = LibSQL.getInt("resourcescontrol.addPurchaseOrderDetailByProvisionOrder",param);
        return result;
    }
    
   
    
}
