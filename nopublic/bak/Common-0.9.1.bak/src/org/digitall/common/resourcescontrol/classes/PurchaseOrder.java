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
