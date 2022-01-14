package org.digitall.common.cashier;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class PaymentWay {

    private int idpaymentway = -1;
    private int idpayment = -1;
    private int idaccount = -1;
    private double amount = 0.0;
    private int idpagodetercero = -1;
    private int idpaymenttype = -1;
    private String estado = "";

    public PaymentWay() {
    }

    public void setIdpaymentway(int idpaymentway) {
	this.idpaymentway = idpaymentway;
    }

    public int getIdpaymentway() {
	return idpaymentway;
    }

    public void setIdpayment(int idpayment) {
	this.idpayment = idpayment;
    }

    public int getIdpayment() {
	return idpayment;
    }

    public void setIdaccount(int idaccount) {
	this.idaccount = idaccount;
    }

    public int getIdaccount() {
	return idaccount;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public double getAmount() {
	return amount;
    }

    public void setIdpagodetercero(int idpagodetercero) {
	this.idpagodetercero = idpagodetercero;
    }

    public int getIdpagodetercero() {
	return idpagodetercero;
    }

    public void setIdpaymenttype(int idpaymenttype) {
	this.idpaymenttype = idpaymenttype;
    }

    public int getIdpaymenttype() {
	return idpaymenttype;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("cashier.getFormaDePago", idpayment);
	try {
	    if (result.next()) {
	        setIdpaymentway(result.getInt("idpaymentway"));
	        setIdpayment(result.getInt("idpayment"));
	        setIdaccount(result.getInt("idaccount"));
	        setAmount(result.getDouble("amount"));
	        setIdpagodetercero(result.getInt("idpagodetercero"));
	        setIdpaymenttype(result.getInt("idpaymenttype"));
	        setEstado(result.getString("estado"));
	    } 
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }

    public void retrieveDataByIdPagoDeTercero() {
	ResultSet result = LibSQL.exFunction("cashier.getFormaDePagoByIdPagoDeTercero", idpagodetercero);
	try {
	    if (result.next()) {
	        setIdpaymentway(result.getInt("idpaymentway"));
	        setIdpayment(result.getInt("idpayment"));
		setIdaccount(result.getInt("idaccount"));
		setAmount(result.getDouble("amount"));
		setIdpagodetercero(result.getInt("idpagodetercero"));
		setIdpaymenttype(result.getInt("idpaymenttype"));
		setEstado(result.getString("estado"));
	    }
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}

    }   
    
    
}
