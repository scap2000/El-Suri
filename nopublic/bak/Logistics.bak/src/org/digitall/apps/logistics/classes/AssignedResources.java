package org.digitall.apps.logistics.classes;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.lib.sql.LibSQL;

public class AssignedResources {

    private int idAssigned = -1;
    private CostsCentre costsCentre;
    private Resource resource;
    private double Quantity = 0;
    private String date;
    
    public AssignedResources() {

    }

    public void setIdAssigned(int idAssigned) {
	this.idAssigned = idAssigned;
    }

    public int getIdAssigned() {
	return idAssigned;
    }

    public void setCostsCentre(CostsCentre costsCentre) {
	this.costsCentre = costsCentre;
    }

    public CostsCentre getCostsCentre() {
	return costsCentre;
    }

    public void setResource(Resource resource) {
	this.resource = resource;
    }

    public Resource getResource() {
	return resource;
    }

    public void setQuantity(double quantity) {
	this.Quantity = quantity;
    }

    public double getQuantity() {
	return Quantity;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public String getDate() {
	return date;
    }

    public int saveData(){
	String params = costsCentre.getIdCostCentre() +","+ resource.getIdResource() +","+ Quantity +",'"+ date +"'";
	int result = LibSQL.getInt("logistics.saveAsignedResource",params);
	if (result != 0){
	    idAssigned = result;
	}
	return result;
    }
}
