package org.digitall.common.cashflow.classes;

import org.digitall.common.cashflow.classes.PaymentOrder;
import org.digitall.lib.sql.LibSQL;

public class PaymentOrderDetail {

    private int idPaymentOrderDetail = -1;
    private PaymentOrder paymentOrder;
    private Voucher voucher;
    private String description;
    private Account account;
    private double amount;

    public PaymentOrderDetail(int _idPaymentOrderDetail) {
	idPaymentOrderDetail = _idPaymentOrderDetail;
    }

    public PaymentOrderDetail() {

    }
    
    public void setIdPaymentOrderDetail(int idPaymentOrderDetail) {
	this.idPaymentOrderDetail = idPaymentOrderDetail;
    }

    public int getIdPaymentOrderDetail() {
	return idPaymentOrderDetail;
    }
   
    public void setVoucher(Voucher voucher) {
	this.voucher = voucher;
    }

    public Voucher getVoucher() {
	return voucher;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    public int saveData_old(){
	String params = idPaymentOrderDetail +","+ paymentOrder.getIdPaymentOrder() +","+ voucher.getIdVoucher() +",'"+ description +"'" ;
	int result = LibSQL.getInt("cashflow.savePaymentOrderDetail",params);    	
	if (result > 0){
	    idPaymentOrderDetail = result;
	}
	return result;
    }

    public int saveData(){
	String params = paymentOrder.getIdPaymentOrder() +","+ voucher.getIdVoucher() +","+ account.getIDAccount() 
			+","+ amount +",'"+ description +"'" ;
	int result = -1;
	if (idPaymentOrderDetail == -1){
	    result = LibSQL.getInt("cashflow.addPaymentOrderDetail",params);
	    idPaymentOrderDetail = result;
	} else {
	    params = idPaymentOrderDetail +","+ params;
	    result = LibSQL.getInt("cashflow.setPaymentOrderDetail",params);
	}
	return result;
    }
    
    public int delete(){
	if (voucher != null) {
	    voucher.setStatus(0);
	}
	return LibSQL.getInt("cashflow.deletePaymentOrderDetail", ""+ idPaymentOrderDetail);
    }
    
    public void setPaymentOrder(PaymentOrder paymentOrder) {
	this.paymentOrder = paymentOrder;
    }

    public PaymentOrder getPaymentOrder() {
	return paymentOrder;
    }

    public void setAccount(Account _account) {
	this.account = _account;
    }

    public Account getAccount() {
	return account;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public double getAmount() {
	return amount;
    }

}
