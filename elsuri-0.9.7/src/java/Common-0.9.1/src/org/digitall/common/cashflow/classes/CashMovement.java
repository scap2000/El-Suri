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
 * CashMovement.java
 *
 * */
package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.lib.sql.LibSQL;

public class CashMovement {

    private int idCashMovement = -1;
    private CashMovementType cashMovementType;
    private CostsCentre costsCentre;
    private String Date;
    private double Amount;
    private double AmountP = 0; 	
    private double projAmount;
    private double projAmountP = 0;
    private double desviationAmount;
    private Voucher voucher;
    private String Description;
    private int idbookkeepingentry = -1;
    private int benefittype = -1;
    private int idbenefit = -1;
    
    public final int PERSONA = 1;
    public final int EMPRESA = 2;
    
    public CashMovement() {

    }
    
    public CashMovement(int _idCashMovement) {
	idCashMovement = _idCashMovement;
    }
    
    public int addData(){
	String params = cashMovementType.getIdCashMovementType() +","+ costsCentre.getIdCostCentre() +",'"+ Date +"',"+ 
			Amount +","+ AmountP +","+ projAmount +","+ projAmountP +","+ desviationAmount +","+ 
                        voucher.getIdVoucher() +",'"+ Description +"',"+ idbookkeepingentry +","+ benefittype +","+ idbenefit;
	idCashMovement = LibSQL.getInt("cashflow.addCashMovement", params);
	return idCashMovement;
    }

    public int setData(){
	String params = idCashMovement +","+ cashMovementType.getIdCashMovementType() +","+ costsCentre.getIdCostCentre() +",'"+ 
			Date +"',"+ Amount +","+ AmountP +","+ projAmount +","+ projAmountP +","+ desviationAmount +","+ 
			voucher.getIdVoucher() +",'"+ Description +"',"+ benefittype +","+ idbenefit;
	return LibSQL.getInt("cashflow.setCashMovement", params);
    }
    
    public void retrieveData() {
        ResultSet data = LibSQL.exFunction("cashflow.getCashMovement","" + idCashMovement);
        try  {
            if (data.next())  {
                cashMovementType = new CashMovementType(data.getInt("idcashmovementtype"));
                cashMovementType.retrieveData();
                costsCentre = new CostsCentre(data.getInt("idcostcentre"));
                Date = data.getString("date");
                Amount = data.getDouble("amount");
                AmountP  = data.getDouble("amountp");
                projAmount = data.getDouble("projamount");
                projAmountP = data.getDouble("projamountp");
                desviationAmount  = data.getDouble("desviationamount");
                voucher = new Voucher(data.getInt("idvoucher"));
                Description = data.getString("description");
                idbookkeepingentry = data.getInt("idbookkeepingentry");
                benefittype = data.getInt("benefittype");
                idbenefit = data.getInt("idbenefit");
            }
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
    }
        
    public void setIdCashMovement(int idCashMovement) {
	this.idCashMovement = idCashMovement;
    }

    public int getIdCashMovement() {
	return idCashMovement;
    }

    public void setCashMovementType(CashMovementType cashMovementType) {
	this.cashMovementType = cashMovementType;
    }

    public CashMovementType getCashMovementType() {
	return cashMovementType;
    }

    public void setCostsCentre(CostsCentre costsCentre) {
	this.costsCentre = costsCentre;
    }

    public CostsCentre getCostsCentre() {
	return costsCentre;
    }

    public void setDate(String date) {
	this.Date = date;
    }

    public String getDate() {
	return Date;
    }

    public void setAmount(double amount) {
	this.Amount = amount;
    }

    public double getAmount() {
	return Amount;
    }

    public void setAmountP(double amountP) {
	this.AmountP = amountP;
    }

    public double getAmountP() {
	return AmountP;
    }

    public void setDescription(String description) {
	this.Description = description;
    }

    public String getDescription() {
	return Description;
    }

    public void setVoucher(Voucher voucher) {
	this.voucher = voucher;
    }

    public Voucher getVoucher() {
	return voucher;
    }

    public void setProjAmount(double projAmount) {
	this.projAmount = projAmount;
    }

    public double getProjAmount() {
	return projAmount;
    }

    public void setProjAmountP(double projAmountP) {
	this.projAmountP = projAmountP;
    }

    public double getProjAmountP() {
	return projAmountP;
    }

    public void setDesviationAmount(double desviationAmount) {
	this.desviationAmount = desviationAmount;
    }

    public double getDesviationAmount() {
	return desviationAmount;
    }

    public void setIdbookkeepingentry(int idbookkeepingentry) {
        this.idbookkeepingentry = idbookkeepingentry;
    }

    public int getIdbookkeepingentry() {
        return idbookkeepingentry;
    }

    public void setBenefittype(int benefittype) {
        this.benefittype = benefittype;
    }

    public int getBenefittype() {
        return benefittype;
    }

    public void setIdbenefit(int idbenefit) {
        this.idbenefit = idbenefit;
    }

    public int getIdbenefit() {
        return idbenefit;
    }
}
