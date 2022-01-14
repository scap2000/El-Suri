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
 * PaymentOrderDetail.java
 *
 * */
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
