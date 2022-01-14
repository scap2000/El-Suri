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
 * ResourcesRequestDetail.java
 *
 * */
package org.digitall.common.resourcescontrol.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.resourcescontrol.classes.Destinations;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.common.resourcesrequests.classes.ResourcesRequest;
import org.digitall.lib.sql.LibSQL;

public class ResourcesRequestDetail {

    private int idResourcesRequestDetail = -1;
    private ResourcesRequest resourcesRequest;
    private ResourceBrands resourceBrands;
    private double Qty = 0;
    private Destinations destinations;
    private double actualPrice = 0;
    private double amount = 0;
    private String description = "";
    private double actualStock = 0;
    private int idRequestStatus = 0;
    private int idPriorityRequest = 3;
    private boolean toBuy;
    private String estado = "";
    /** ID del Recurso de referencia en el Pedido de Recurso correspondiente*/
    private int idResourcesRequestDetailRef = -1;

    private double authorizatedQty = 0;

    //---------------------------------
    private int ResourcesRequestDetailType = -1;
    private int objectType = ResourcesRequest.REQUESTED;

    private boolean delete = true;
    
    
    public ResourcesRequestDetail() {
	resourceBrands = new ResourceBrands();
    }

    public ResourcesRequestDetail(int _objectType, ResourcesRequest _resourcesRequest) {
	objectType = _objectType;
	resourcesRequest = _resourcesRequest;
    }

    public void setIdResourcesRequestDetail(int _idResourcesRequestDetail) {
	idResourcesRequestDetail = _idResourcesRequestDetail;
    }

    public int getIdResourcesRequestDetail() {
	return idResourcesRequestDetail;
    }

    public void setQty(double _qty) {
	Qty = _qty;
    }

    public double getQty() {
	return Qty;
    }

    public void setActualPrice(double _actualPrice) {
	actualPrice = _actualPrice;
    }

    public double getActualPrice() {
	return actualPrice;
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

    public void setActualStock(double _actualStock) {
	actualStock = _actualStock;
    }

    public double getActualStock() {
	return actualStock;
    }

    public void setResourceBrands(ResourceBrands _resourceBrands) {
	resourceBrands = _resourceBrands;
    }

    public ResourceBrands getResourceBrands() {
	return resourceBrands;
    }

    public void setResourcesRequest(ResourcesRequest _resourcesRequest) {
	resourcesRequest = _resourcesRequest;
    }

    public ResourcesRequest getResourcesRequest() {
	return resourcesRequest;
    }

    public void setResourcesRequestDetailType(int _resourcesRequestDetailType) {
	ResourcesRequestDetailType = _resourcesRequestDetailType;
    }

    public int getResourcesRequestDetailType() {
	return ResourcesRequestDetailType;
    }

    public void setDestinations(Destinations _destinations) {
	destinations = _destinations;
    }

    public Destinations getDestinations() {
	return destinations;
    }

    public void setObjectType(int _operationType) {
	objectType = _operationType;
    }

    public int getObjectType() {
	return objectType;
    }

    public void setAuthorizatedQty(double _authorizatedQty) {
	authorizatedQty = _authorizatedQty;
    }

    public double getAuthorizatedQty() {
	return authorizatedQty;
    }
    
    public int saveData() {
	int result = -1;
	String params = "";
	if (objectType == ResourcesRequest.REQUESTED)  {
	    /** Agrega recurso autorizado **/
	    params = resourcesRequest.getIdResourcesRequest() +","+ resourceBrands.getResource().getIdResource() +","+ 
		    Qty +","+ destinations.getIddestination() +","+ actualPrice +","+ amount +","+ resourceBrands.getBrands().getIdBrand() +","+
		    actualStock +","+ idResourcesRequestDetailRef +",'"+ description +"','"+ toBuy +"'";
	    result = LibSQL.getInt("resourcescontrol.addResourcesRequestAuthDetail", params);
	    
	} else if (objectType == ResourcesRequest.AUTHORIZED) {
	    /** Modifica recurso autorizado **/
	    params = idResourcesRequestDetail +","+ Qty +","+ resourceBrands.getBrands().getIdBrand() + "," 
	            + actualPrice +","+ amount +","+ actualStock +","+ resourcesRequest.getIdResourcesRequest() +","+ 
		    resourceBrands.getResource().getIdResource() +",'"+ toBuy +"'";
	    result = LibSQL.getInt("resourcescontrol.setResourcesRequestAuthDetail", params);
	}
	if (result >= 0) {
	    return updateResourcesRequestDetailStatus();
	} else {
	    return result;
	}
    }
    
    public int updateResourcesRequestDetailStatus() {
	return LibSQL.getInt("resourcescontrol.setResourceRequestDetailStatus", resourcesRequest.getIdResourcesRequest() +","+ idResourcesRequestDetailRef);
    }

    /** Agrega/Modifica recurso solicitado en el pedido de recurso **/
    public int saveRequestData() {
	String params = resourcesRequest.getIdResourcesRequest() +","+ resourceBrands.getResource().getIdResource() + "," 
		        + Qty +","+ destinations.getIddestination() +","+ actualPrice +","+ amount +",'"+ description + "'," 
		        + resourceBrands.getBrands().getIdBrand() +","+ actualStock +","+ idRequestStatus +","+ 
			idPriorityRequest + ",'" + estado +"'";
	int result = -1;
	if (idResourcesRequestDetail == -1) { //insert
	    idResourcesRequestDetail = LibSQL.getInt("resourcescontrol.addResourceRequestDetail", params);
	    result = idResourcesRequestDetail;
	}else {//update
	    params = idResourcesRequestDetail +","+ params;
	    result = LibSQL.getInt("resourcescontrol.setResourceRequestDetail", params);
	}
	return result;
    }

    public void setIdResourcesRequestDetailRef(int idResourcesRequestDetailRef) {
	this.idResourcesRequestDetailRef = idResourcesRequestDetailRef;
    }

    public int getIdResourcesRequestDetailRef() {
	return idResourcesRequestDetailRef;
    }

    public void setIdRequestStatus(int idRequestStatus) {
	this.idRequestStatus = idRequestStatus;
    }

    public int getIdRequestStatus() {
	return idRequestStatus;
    }

    public void retrieveRequestData(){
	try {
	    String params = ""+ idResourcesRequestDetail;
	    ResultSet rs = LibSQL.exFunction("resourcescontrol.getResourceRequestDetail", params);
	    if (rs.next()){
	        actualPrice = rs.getDouble("actualprice");
	        amount = rs.getDouble("amount");
	        description = rs.getString("description");
	        authorizatedQty = rs.getDouble("authorizatedqty");
	        Qty = rs.getDouble("qtyrequest");
	        
	        resourceBrands = new ResourceBrands();
	        resourceBrands.setBrands(new Brands(rs.getInt("idbrand"), rs.getString("brandrequested")));
	        resourceBrands.setResource(new Resource(rs.getInt("idresource")));
	        resourceBrands.getResource().setName(rs.getString("resource"));
	        resourceBrands.getResource().setUnit(new Units(rs.getInt("idunit"), rs.getString("unit")));
	        resourceBrands.setStock(rs.getDouble("stock"));
	        
	        destinations = new Destinations(rs.getInt("iddestination"));
	        destinations.setName(rs.getString("destination"));
		
	        resourcesRequest.setCostsCentre(new CostsCentre(rs.getInt("idcostcentre")));
	        resourcesRequest.getCostsCentre().setName(rs.getString("costscentre"));
	        resourceBrands.getResource().setExpenditureAccount(new ExpenditureAccount(rs.getInt("idaccount"), rs.getString("name")));
	        resourceBrands.getResource().getExpenditureAccount().setInitialAmount(rs.getDouble("initialamount"));
	        resourceBrands.getResource().getExpenditureAccount().setSpentAmount(rs.getDouble("spentamount"));
	        resourceBrands.getResource().getExpenditureAccount().setAvailableAmount(rs.getDouble("availableamount"));
		
	        idRequestStatus = rs.getInt("idrequeststatus");
	        idPriorityRequest = rs.getInt("idpriorityrequest");
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public int delData() {
	int result = -1;
	String params = "";
	if (idResourcesRequestDetail != -1)  {
	    /** borra recurso **/
	    params = "" + idResourcesRequestDetail;
	    //System.out.println("SELECT resourcescontrol.delResourcesRequestDetail(" + params + ")");
	    result = LibSQL.getInt("resourcescontrol.delResourcesRequestDetail", params);
	}
	return result;
    }


    public void setIdPriorityRequest(int idPriorityRequest) {
	this.idPriorityRequest = idPriorityRequest;
    }

    public int getIdPriorityRequest() {
	return idPriorityRequest;
    }

    public void setToBuy(boolean toBuy) {
	this.toBuy = toBuy;
    }

    public boolean isToBuy() {
	return toBuy;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setDelete(boolean delete) {
	this.delete = delete;
    }

    public boolean isDelete() {
	return delete;
    }

}
