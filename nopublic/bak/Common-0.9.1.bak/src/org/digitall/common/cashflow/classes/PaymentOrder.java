package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class PaymentOrder {

    private int idPaymentOrder = -1;
    private int number;
    private String date;
    private int idEntity;
    private int idEntityType;
    private double amount = 0;
    private double detailAmount = 0;
    private double deductionAmount = 0;
    private String Observations;
    private Provider provider;
    private CostsCentre costCentre;
    private boolean isCancel = false;
    private boolean esAdelantada = false;
    
    private int _idaccount = -1;

    public PaymentOrder() {
    }

    public PaymentOrder(int _idPaymentOrder) {
	idPaymentOrder = _idPaymentOrder;
    }

    public void setIdPaymentOrder(int idPaymentOrder) {
	this.idPaymentOrder = idPaymentOrder;
    }

    public int getIdPaymentOrder() {
	return idPaymentOrder;
    }

    public void setNumber(int number) {
	this.number = number;
    }

    public int getNumber() {
	return number;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public String getDate() {
	return date;
    }

    public void setIdEntity(int idEntity) {
	this.idEntity = idEntity;
    }

    public int getIdEntity() {
	return idEntity;
    }

    public void setIdEntityType(int idEntityType) {
	this.idEntityType = idEntityType;
    }

    public int getIdEntityType() {
	return idEntityType;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public double getAmount() {
	return amount;
    }

    public void setObservations(String description) {
	this.Observations = description;
    }

    public String getObservations() {
	return Observations;
    }

    public int saveData() {
	String params = "'" + date + "'," + provider.getIdProvider() + "," + costCentre.getIdCostCentre() + ",0" + detailAmount + ",0" + deductionAmount + ",0" + amount + ",'" + Observations + "',"+ esAdelantada +"";
	int result = -1;
	if (idPaymentOrder == -1) {
	    try {
		ResultSet rs = LibSQL.exFunction("cashflow.addPaymentOrder", params);
		if (rs.next()) {
		    result = rs.getInt("idpaymentorder");
		    number = rs.getInt("number");
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    idPaymentOrder = result;
	} else {
	    params = idPaymentOrder + "," + params;
	    result = LibSQL.getInt("cashflow.setPaymentOrder", params);
	}
	return result;
    }

    public int addData() {
	String params = number + ",'" + date + "'," + idEntity + "," + idEntityType + "," + amount + ",'" + Observations + "'";
	idPaymentOrder = LibSQL.getInt("cashflow.addPaymentOrder", params);
	return idPaymentOrder;
    }

    public int setData() {
	String params = idPaymentOrder + "," + number + ",'" + date + "'," + idEntity + "," + idEntityType + "," + amount + ",'" + Observations + "'";
	return LibSQL.getInt("cashflow.setPaymentOrder", params);
    }

    public double updateAmount() {
	amount = LibSQL.getDouble("cashflow.setPaymentOrderAmount", "" + getIdPaymentOrder());
	return amount;
    }

    public int setVoucherPaid() {
	return LibSQL.getInt("cashflow.setVoucherPaid", "" + idPaymentOrder);
    }

    public void setDetailAmount(double _detailAmount) {
	this.detailAmount = _detailAmount;
    }

    public void setDeductionAmount(double _deductionAmount) {
	this.deductionAmount = _deductionAmount;
    }

    public double getAmountRetention() {
	return deductionAmount;
    }

    public void setCostCentre(CostsCentre costCentre) {
	this.costCentre = costCentre;
    }

    public CostsCentre getCostCentre() {
	return costCentre;
    }

    public void setProvider(Provider provider) {
	this.provider = provider;
    }

    public Provider getProvider() {
	return provider;
    }

    public double updateDetailAmount() {
	return LibSQL.getDouble("cashflow.getPaymentOrderTotalDetail", "" + idPaymentOrder);
    }

    public double updateDeductionAmount() {
	return LibSQL.getDouble("cashflow.getPaymentOrderTotalDeductions", "" + idPaymentOrder);
    }

    public double updatePaidAmount() {
	return LibSQL.getDouble("cashflow.getPaymentOrderTotalPaid", "" + idPaymentOrder);
    }

    public Vector<Check> getChecksVector() {
	ResultSet _checks = LibSQL.exFunction("cashflow.getPaymentOrderChecks", "" + idPaymentOrder);
	Vector<Check> checks = new Vector();
	try {
	    while (_checks.next()) {
		checks.add(new Check(_checks.getInt("idcheck")));
	    }
	} catch (SQLException x) {
	    Advisor.printException(x);
	}
	return checks;
    }

    public boolean rollBack() {
	return LibSQL.getBoolean("cashflow.rollBackPaymentOrder", "" + idPaymentOrder);
    }

    public boolean approveByFacturas() {
	return LibSQL.getBoolean("cashflow.approvePaymentOrderByFacturas", "" + idPaymentOrder);
    }
    
    public boolean approveByAdelantadas() {
	return LibSQL.getBoolean("cashflow.approvePaymentOrderByAdelantadas", "" + idPaymentOrder);
    }
    
    public boolean approveByMonto(Vector _idsVouchers, double _amount, int _idaccount) {
        return LibSQL.getBoolean("cashflow.approvePaymentOrderByMonto", "" + idPaymentOrder + "," + _amount +",'" + vectorToParams(_idsVouchers)+"',"+ _idaccount);
    }
    
    private String vectorToParams(Vector _vector){
	String resultado = "";
	int tam = _vector.size();
	for(int i = 0 ; i < tam; i++){
	    resultado += _vector.elementAt(i).toString() + ",";  
	}
	resultado = resultado.substring(0,resultado.length() - 1);
	return resultado;
    }

    public void setIsCancel(boolean isCancel) {
	this.isCancel = isCancel;
    }

    public boolean isIsCancel() {
	return isCancel;
    }

    public boolean cancel() {
	return LibSQL.getBoolean("cashflow.cancelPaymentOrder", "" + idPaymentOrder);
    }

    public void setEsAdelantada(boolean esAdelantada) {
        this.esAdelantada = esAdelantada;
    }

    public boolean EsAdelantada() {
        return esAdelantada;
    }
}
