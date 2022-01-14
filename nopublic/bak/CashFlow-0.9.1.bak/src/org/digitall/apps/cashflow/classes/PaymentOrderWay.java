package org.digitall.apps.cashflow.classes;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.Check;
import org.digitall.common.cashflow.classes.PaymentOrder;
import org.digitall.lib.sql.LibSQL;

public class PaymentOrderWay {

    public final static int EFECTIVO = 1;
    public final static int CHEQUE = 2;

    private int idPaymentOrderWay = -1;
    private PaymentOrder paymentOrder;
    private Account accounting;
    private double amount;
    private Check check;
    private int idPaymentType = EFECTIVO;
    
    public PaymentOrderWay() {

    }

    public void setIdPaymentOrderWay(int _idPaymentOrderWay) {
	this.idPaymentOrderWay = _idPaymentOrderWay;
    }

    public int getIdPaymentOrderWay() {
	return idPaymentOrderWay;
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

    public void setCheck(Check check) {
	this.check = check;
    }

    public Check getCheck() {
	return check;
    }

    public void setIdPaymentType(int idPaymentType) {
	this.idPaymentType = idPaymentType;
    }

    public int getIdPaymentType() {
	return idPaymentType;
    }


    public int saveData(){
	String params = paymentOrder.getIdPaymentOrder() +","+ accounting.getIDAccount() +","+ check.getIdCheck()
			+","+ amount  +","+ idPaymentType;
	
	int result = -1;
	if (idPaymentOrderWay == -1){
	    result = LibSQL.getInt("cashflow.addPaymentOrderWay",params);
	    idPaymentOrderWay = result;
	} else {
	    params = idPaymentOrderWay +","+ params;
	    result = LibSQL.getInt("cashflow.setPaymentOrderWay",params);
	}
	return result;
    }
    
    public int delete(){
	if (check != null) {
	    check.rollBack();
	}
	return LibSQL.getInt("cashflow.deletePaymentOrderWay", ""+ idPaymentOrderWay);
    }
}
