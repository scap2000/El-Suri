package org.digitall.apps.cashflow.classes;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.PaymentOrder;
import org.digitall.lib.sql.LibSQL;

public class PaymentOrderDeduction {

    private int idPaymentOrderDeduction = -1;
    private PaymentOrder paymentOrder;
    private Account accounting;
    private double amount;
    private double percentage;
    
    public PaymentOrderDeduction() {

    }

    public void setIdPaymentOrderDeduction(int _idPaymentOrderDeduction) {
	this.idPaymentOrderDeduction = _idPaymentOrderDeduction;
    }

    public int getIdPaymentOrderDeduction() {
	return idPaymentOrderDeduction;
    }

    public void setPaymentOrder(PaymentOrder paymentOrder) {
	this.paymentOrder = paymentOrder;
    }

    public PaymentOrder getPaymentOrder() {
	return paymentOrder;
    }

    public void setAccount(Account accounting) {
	this.accounting = accounting;
    }

    public Account getAccount() {
	return accounting;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public double getAmount() {
	return amount;
    }

    public int saveData(){
	String params = paymentOrder.getIdPaymentOrder() +","+ accounting.getIDAccount() +","+ amount + "," + percentage;
	int result = -1;
	if (idPaymentOrderDeduction == -1){
	    result = LibSQL.getInt("cashflow.addPaymentOrderDeduction",params);
	    idPaymentOrderDeduction = result;
	} else {
	    params = idPaymentOrderDeduction +","+ params;
	    result = LibSQL.getInt("cashflow.setPaymentOrderDeduction",params);
	}
	return result;
    }
    
    public int delete(){
	return LibSQL.getInt("cashflow.deletePaymentOrderDeduction", ""+ idPaymentOrderDeduction);
    }

    public void setPercentage(double _percentage) {
	percentage = _percentage;
    }

    public double getPercentage() {
	return percentage;
    }

}
