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
