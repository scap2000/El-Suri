package org.digitall.common.cashflow.classes;

import org.digitall.common.cashflow.classes.PaymentOrder;
import org.digitall.lib.sql.LibSQL;

public class PaymentOrderCheck {

    private PaymentOrder paymentOrder;
    private Check check;
    
    public PaymentOrderCheck() {

    }

    public void setPaymentOrder(PaymentOrder paymentOrder) {
	this.paymentOrder = paymentOrder;
    }

    public PaymentOrder getPaymentOrder() {
	return paymentOrder;
    }

    public void setCheck(Check check) {
	this.check = check;
    }

    public Check getCheck() {
	return check;
    }
    
    public int saveData(){
	String params = paymentOrder.getIdPaymentOrder() +","+ check.getIdCheck();
	return LibSQL.getInt("cashflow.savePaymentOrderCheck", params);
    }

}
