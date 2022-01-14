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
 * ResourcesRequest.java
 *
 * */
package org.digitall.common.resourcesrequests.classes;

import java.sql.ResultSet;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.lib.sql.LibSQL;

public class ResourcesRequest {

    /** ATTRIBUTES **/
    private int idResourcesRequest = -1;
    private String date;
    private int number;
    private double estimatedAmount = 0;
    private double estimatedAmountAuth = 0;
    private String description;
    private int idSolicitor = -1;
    private CostsCentre costsCentre;
    private int idRequestStatus = 0;
    private int idPriorityRequest = 3;
    private String estado = "";
    private String approved = "";
    private String approvedDate = "";
    private String liberationDate = "";
    //CONSTS
    public static final int REQUESTED = 1;
    public static final int AUTHORIZED = 2;
    
    
    //ESTADO DEL TRAMITE DE COMPRA
    private final int PURCHASE_NOT_INITIATED = 1;
    // No iniciado
    private final int PURCHASE_INITIATED = 2;
    //En tramite
    private final int PURCHASE_NOT_FINISHED = 3;
    //Cuando falta satisfacer algun item del detalle
    private final int PURCHASE_FINISHED_TOTALLY_SATISFIED = 4;
    //Cuando estan satisfechos todos los items y se ha finalizado
    private final int PURCHASE_FINISHED_PARTIALLY_SATISFIED = 5;
    //Cuando NO estan satisfechos todos los items y se ha finalizado
    //ESTADO DEL TRAMITE DE AUTORIZACION
    private final int AUTHORIZATION_NOT_INITIATED = 6;
    // No iniciado
    private final int AUTHORIZATION_INITIATED = 7;
    //En tramite
    private final int AUTHORIZATION_NOT_FINISHED = 8;
    //Cuando falta satisfacer algun item del detalle
    private final int AUTHORIZATION_FINISHED_TOTALLY_SATISFIED = 9;
    //Cuando estan satisfechos todos los items y se ha finalizado
    private final int AUTHORIZATION_FINISHED_PARTIALLY_SATISFIED = 10;
    //Cuando NO estan satisfechos todos los items y se ha finalizado

    public ResourcesRequest(int _idResourcesRequest) {
	idResourcesRequest = _idResourcesRequest;
    }

    public ResourcesRequest() {

    }

    public void retrieveData() {
	String params = String.valueOf(idResourcesRequest);
	ResultSet data = LibSQL.exFunction("resourcescontrol.getResourcesRequest", params);
	try {
	    if (data.next()) {
		date = data.getString("date");
		number = data.getInt("number");
		estimatedAmount = data.getDouble("estimateamount");
		description = data.getString("description");
		estimatedAmountAuth = data.getDouble("estimatedamountauth");
		idSolicitor = data.getInt("idsolicitor");
		costsCentre = new CostsCentre(data.getInt("idcostcentre"));
	        idRequestStatus = data.getInt("idrequeststatus");
	        idPriorityRequest = data.getInt("idpriorityrequest");
	        approved = data.getString("approved");
	        approvedDate = data.getString("dateapproved");
	        liberationDate = data.getString("liberationdate");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public int addData() {
	String param = "'"+ date +"',"+ number +",0"+ estimatedAmount +",'"+ description +"',"+ 
			costsCentre.getIdCostCentre() +",0,"+ idSolicitor +","+ idRequestStatus +","+ 
			idPriorityRequest + ",'" + estado +"'";
	idResourcesRequest = LibSQL.getInt("resourcescontrol.addResourcesRequest", param);
	return idResourcesRequest;
    }

    public int setData() {
	String param = idResourcesRequest + ",'" + date + "'," + number + ",0" + estimatedAmount + ",'" + 
			description +"',"+ idSolicitor +","+ idRequestStatus +","+ idPriorityRequest;
	return LibSQL.getInt("resourcescontrol.setResourcesRequest", param);
    }

    public int delData() {
	String param = "" + idResourcesRequest ;
	return LibSQL.getInt("resourcescontrol.delResourcesRequest", param);
    }
    
    public int toRecord() {
	return LibSQL.getInt("resourcescontrol.setResourcesRequest", idResourcesRequest);
    }

    public void setIdResourcesRequest(int _idResourcesRequest) {
	idResourcesRequest = _idResourcesRequest;
    }

    public int getIdResourcesRequest() {
	return idResourcesRequest;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public String getDate() {
	return date;
    }

    

    public void setEstimatedAmount(double _estimatedAmount) {
	estimatedAmount = _estimatedAmount;
    }

    public double getEstimatedAmount() {
	return estimatedAmount;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    public void setCostsCentre(CostsCentre _costsCentre) {
	costsCentre = _costsCentre;
    }

    public CostsCentre getCostsCentre() {
	return costsCentre;
    }

    public void setEstimatedAmountAuth(double _estimatedAmountAuth) {
	estimatedAmountAuth = _estimatedAmountAuth;
    }

    public double getEstimatedAmountAuth() {
	return estimatedAmountAuth;
    }

    public void setIdSolicitor(int idSolicitor) {
	this.idSolicitor = idSolicitor;
    }

    public int getIdSolicitor() {
	return idSolicitor;
    }

    public void updateEstimateAmount(){
	String param = ""+ idResourcesRequest;
	estimatedAmount = LibSQL.getDouble("resourcescontrol.setResourcesRequestEstimateAmount", param);
    }

    public void setIdRequestStatus(int idRequestStatus) {
	this.idRequestStatus = idRequestStatus;
    }

    public int getIdRequestStatus() {
	return idRequestStatus;
    }

    public void setNumber(int number) {
	this.number = number;
    }

    public int getNumber() {
	return number;
    }

    public void setIdPriorityRequest(int idPriorityRequest) {
	this.idPriorityRequest = idPriorityRequest;
    }

    public int getIdPriorityRequest() {
	return idPriorityRequest;
    }

    public boolean toDeliver(){
	return LibSQL.getBoolean("resourcescontrol.getResourcesRequestToDeliver",""+ idResourcesRequest);
    }
    
    public ResultSet getDetail(){
	return LibSQL.exFunction("resourcescontrol.getAllResourcesRequestDetailAuth", idResourcesRequest +",'',0,0");
    }
    
    public int setRequestStatus(int _idRequestStatus){
	idRequestStatus = _idRequestStatus;
	String params = idResourcesRequest +","+ idRequestStatus;
	return LibSQL.getInt("resourcescontrol.setResourcesRequestStatus", params);
    }


    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public int deleteResourcesRequestAuthDetail() {
        return LibSQL.getInt("resourcescontrol.delResourcesRequestAuthDetail","" + idResourcesRequest);
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getApproved() {
        return approved;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setLiberationDate(String liberationDate) {
        this.liberationDate = liberationDate;
    }

    public String getLiberationDate() {
        return liberationDate;
    }
}
