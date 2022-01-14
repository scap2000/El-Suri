package org.digitall.apps.cashflow.classes;

import org.digitall.lib.sql.LibSQL;

public class ExpenseDetail {

    private int idCostCentreDetail = 0;
    private int idExpense = 0;
    private String date;
    private double spentAmount;
    private int idVoucherType;
    private int voucherNumber;
    private String personName;
    
    public ExpenseDetail(int _idCostCentreDetail) {
	idCostCentreDetail = _idCostCentreDetail;
    }

    

    public void setIdExpense(int idExpense) {
	this.idExpense = idExpense;
    }

    public int getIdExpense() {
	return idExpense;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public String getDate() {
	return date;
    }

    

    public void setIdVoucherType(int idVoucherType) {
	this.idVoucherType = idVoucherType;
    }

    public int getIdVoucherType() {
	return idVoucherType;
    }

    public void setVoucherNumber(int voucherNumber) {
	this.voucherNumber = voucherNumber;
    }

    public int getVoucherNumber() {
	return voucherNumber;
    }

    public void setPersonName(String personName) {
	this.personName = personName;
    }

    public String getPersonName() {
	return personName;
    }

    public int saveData(){
	String params = idCostCentreDetail +","+ spentAmount +",'"+ date +"',"+ idVoucherType +","+ voucherNumber +",'"+ personName  +"'"; 
	idExpense = LibSQL.getInt("cashflow.addCostsCentreExpense",params);
	return idExpense;
    }

    public void setIdCostCentreDetail(int idCostCentreDetail) {
	this.idCostCentreDetail = idCostCentreDetail;
    }

    public int getIdCostCentreDetail() {
	return idCostCentreDetail;
    }

    public void setSpentAmount(double spentAmount) {
	this.spentAmount = spentAmount;
    }

    public double getSpentAmount() {
	return spentAmount;
    }

}
