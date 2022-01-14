package org.digitall.common.cashflow.classes;

import org.digitall.lib.sql.LibSQL;

public class Transaction {

    private int idTransaction = 0;
    private int idBankAccount;
    private int idTransactionType;
    private double amount;
    private int idOperationMethod; 
    private int methodNumber;
    private int transactionNumber;
    private String date;
    private String concept;

    public void setIdTransaction(int idTransaction) {
	this.idTransaction = idTransaction;
    }

    public int getIdTransaction() {
	return idTransaction;
    }

    public void setIDBankAccount(int _idBankAccount) {
	this.idBankAccount = _idBankAccount;
    }

    public int getIDBankAccount() {
	return idBankAccount;
    }

    public void setIdTransactionType(int idTransactionType) {
	this.idTransactionType = idTransactionType;
    }

    public int getidTransactionType() {
	return idTransactionType;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public double getAmount() {
	return amount;
    }

    public void setIdOperationMethod(int idOperationMethod) {
	this.idOperationMethod = idOperationMethod;
    }

    public int getIdOperationMethod() {
	return idOperationMethod;
    }

    public void setMethodNumber(int methodNumber) {
	this.methodNumber = methodNumber;
    }

    public int getMethodNumber() {
	return methodNumber;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public String getDate() {
	return date;
    }
    
    public int saveData(){
	String params = idBankAccount +","+ idTransactionType  +","+ amount +","+ idOperationMethod +","+ methodNumber +","+ transactionNumber +",'"+ date +"','"+ concept +"'";
	if (idTransaction == 0){
	    return LibSQL.getInt("cashflow.addTransaction",params);    
	} else {	    
	    /*params = idTransaction +","+ params;
	    return LibSQL.getInt("cashflow.setTransaction",params);    */
	    return -1;
	}
    }

    public void setTransactionNumber(int transactionNumber) {
	this.transactionNumber = transactionNumber;
    }

    public int getTransactionNumber() {
	return transactionNumber;
    }

    public void setConcept(String concept) {
	this.concept = concept;
    }

    public String getConcept() {
	return concept;
    }

}
