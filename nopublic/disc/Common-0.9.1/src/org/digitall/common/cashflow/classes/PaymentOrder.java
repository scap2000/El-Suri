/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * PaymentOrder.java
 *
 * */
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
