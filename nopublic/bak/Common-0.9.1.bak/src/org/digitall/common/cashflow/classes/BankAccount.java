package org.digitall.common.cashflow.classes;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class BankAccount {

    private int idBankAccount = 0;
    private String number;
    private int idBank;
    private int idOwner;
    private boolean isCompany;
    private int idAccountType;
    private int idMoneyType;
    private double amount;
    private double availableAmount;
    private double checkAmount;
    private long cbu;
    private String ownerName;
    private String startDate;
    private String endDate;
    private Account account;
    private boolean amountModifiable = false;


    public BankAccount(double _amount){
	amount = _amount;
	availableAmount = _amount;
    }
    
    public BankAccount(int _idAccount){
	idBankAccount = _idAccount;
    }
    
    public BankAccount(){
    }
    
    public void setIDBankAccount(int _idBankAccount) {
	this.idBankAccount = _idBankAccount;
    }

    public int getIDBankAccount() {
	return idBankAccount;
    }

    public void setNumber(String _number) {
	this.number = _number;
    }

    public String getNumber() {
	return number;
    }

    public void setIdBank(int idBank) {
	this.idBank = idBank;
    }

    public int getIdBank() {
	return idBank;
    }

    public void setIdOwner(int idOwner) {
	this.idOwner = idOwner;
    }

    public int getIdOwner() {
	return idOwner;
    }

    public void setIsCompany(boolean isCompany) {
	this.isCompany = isCompany;
    }

    public boolean isIsCompany() {
	return isCompany;
    }

    public void setIdAccountType(int idAccountType) {
	this.idAccountType = idAccountType;
    }

    public int getIdAccountType() {
	return idAccountType;
    }

    public void setIdMoneyType(int idMoneyType) {
	this.idMoneyType = idMoneyType;
    }

    public int getIdMoneyType() {
	return idMoneyType;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public double getAmount() {
	return amount;
    }

    public void setAvailableAmount(double availableAmount) {
	this.availableAmount = availableAmount;
    }

    public double getAvailableAmount() {
	return availableAmount;
    }

    public void setCbu(long cbu) {
	this.cbu = cbu;
    }

    public long getCbu() {
	return cbu;
    }

    public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
    }

    public String getOwnerName() {
	return ownerName;
    }

    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String getStartDate() {
	return startDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public int saveData(){
	String params = "'" + number +"',"+ idBank +","+ idOwner +",'"+ isCompany +"',"+ idAccountType +","+ idMoneyType +","+ amount +","+ 
			availableAmount +","+ cbu +",'"+ ownerName +"','"+ startDate +"','"+ endDate +"',"+ account.getIDAccount();
	if (account.getIDAccount() > 0) {
	    if (idBankAccount == 0){
	        return LibSQL.getInt("cashflow.addBankAccount",params);    
	    } else {            
	        params = idBankAccount +","+ params;
	        return LibSQL.getInt("cashflow.setBankAccount",params);    
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar una cuenta para imputar el gasto", "Error");
	    return -1;
	}
    }
    
    public int updateAmount(double _amount){
	String params = idBankAccount +","+ _amount;
	return LibSQL.getInt("cashflow.setBankAccountAmount",params);    
    }
    
    public int updateBudgetsAmount(){
	String params = idBankAccount +","+ amount;
	return LibSQL.getInt("cashflow.setBankAccountBudgetsAmount",params);    
    }

    public void setCheckAmount(double checkAmount) {
	this.checkAmount = checkAmount;
    }

    public double getCheckAmount() {
	return checkAmount;
    }

    public void setAccount(Account _account) {
	this.account = _account;
    }

    public Account getAccount() {
	return account;
    }

    public void setAmountModifiable(boolean _amountModifiable) {
	this.amountModifiable = _amountModifiable;
    }

    public boolean isAmountModifiable() {
	return amountModifiable;
    }

}
