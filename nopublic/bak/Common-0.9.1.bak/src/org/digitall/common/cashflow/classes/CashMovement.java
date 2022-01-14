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
