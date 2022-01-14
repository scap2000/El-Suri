package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.common.resourcesrequests.classes.ResourcesRequest;
import org.digitall.lib.sql.LibSQL;

public class Voucher {

    private int idVoucher = -1;
    private int idVoucherType = 0;
    private long number = 0;
    private String Date;
    private int idEntitytype = 0;
    private int idEntity = -1;
    private double partialWOTaxes = 0;
    private double Taxes = 0;
    private double partialWTaxes = 0;
    private double VATAmount = 0;
    private double VATp = 0;
    private double Amount = 0;
    private boolean withDetail = false;
    private boolean closed = false;
    private String Description ="";
    private CostsCentre costsCentre;
    private String fullNumber;
    private boolean paid = false;
    private int idBudget = -1;
    private String nroFactura = "0000-000000";
    
    private double totalAmount = 0;
    
    public Voucher() {

    }

    public Voucher(int _idVoucher) {
	idVoucher = _idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
	this.idVoucher = idVoucher;
    }

    public int getIdVoucher() {
	return idVoucher;
    }

    public void setIdVoucherType(int idVoucherType) {
	this.idVoucherType = idVoucherType;
    }

    public int getIdVoucherType() {
	return idVoucherType;
    }

    public void setNumber(long _number) {
	this.number = _number;
    }

    public long getNumber() {
	return number;
    }

    public void setDate(String date) {
	this.Date = date;
    }

    public String getDate() {
	return Date;
    }

    public void setIdEntitytype(int idEntitytype) {
	this.idEntitytype = idEntitytype;
    }

    public int getIdEntitytype() {
	return idEntitytype;
    }

    public void setIdEntity(int idEntity) {
	this.idEntity = idEntity;
    }

    public int getIdEntity() {
	return idEntity;
    }

    public void setPartialWOTaxes(double partialWOTaxes) {
	this.partialWOTaxes = partialWOTaxes;
    }

    public double getPartialWOTaxes() {
	return partialWOTaxes;
    }

    public void setTaxes(double taxes) {
	this.Taxes = taxes;
    }

    public double getTaxes() {
	return Taxes;
    }

    public void setPartialWTaxes(double partialWTaxes) {
	this.partialWTaxes = partialWTaxes;
    }

    public double getPartialWTaxes() {
	return partialWTaxes;
    }

    public void setVATAmount(double vATAmount) {
	this.VATAmount = vATAmount;
    }

    public double getVATAmount() {
	return VATAmount;
    }

    public void setVATp(double vATp) {
	this.VATp = vATp;
    }

    public double getVATp() {
	return VATp;
    }

    public void setAmount(double amount) {
	this.Amount = amount;
    }

    public double getAmount() {
	return Amount;
    }

    public void setWithDetail(boolean withDetail) {
	this.withDetail = withDetail;
    }

    public boolean isWithDetail() {
	return withDetail;
    }

    public void setDescription(String description) {
	this.Description = description;
    }

    public String getDescription() {
	return Description;
    }
    
    public void retrieveData() {
	String params = String.valueOf(idVoucher);
	ResultSet voucherData = LibSQL.exFunction("cashflow.getVoucher",params);
	try  {
	    if (voucherData.next())  {
	        idVoucher = voucherData.getInt("idvoucher");
	        idVoucherType = voucherData.getInt("idvouchertype");
	        number = voucherData.getLong("number");
	        Date = voucherData.getString("date");
	        idEntity = voucherData.getInt("identity");
	        idEntitytype = voucherData.getInt("identitytype");
	        partialWOTaxes = voucherData.getDouble("partialwotaxes");
	        Taxes = voucherData.getDouble("taxes");
	        partialWTaxes = voucherData.getDouble("partialwtaxes");
	        VATAmount = voucherData.getDouble("vatamount");
	        VATp = voucherData.getDouble("vatp");
	        Amount = voucherData.getDouble("amount");
	        withDetail = voucherData.getBoolean("withdetail");
	        Description = voucherData.getString("description");
	        costsCentre = new CostsCentre(voucherData.getInt("idcostcentre"));
                setNroFactura(voucherData.getString("nrofactura"));
	    }
	        
	} catch (Exception ex)  {
	    ex.printStackTrace();
	}
	
    }
    public int addData(){
	String params = costsCentre.getIdCostCentre() +","+ idVoucherType +","+ number +",'"+ Date +"',"+ 
			idEntitytype +","+ idEntity +","+ partialWOTaxes +","+ Taxes +","+ partialWTaxes +","+ 
			VATAmount +","+ VATp +","+ Amount +",'"+ withDetail +"','"+ Description +"','"+ closed + 
                        "','"+ nroFactura +"'";
	
	idVoucher = LibSQL.getInt("cashflow.addVoucher", params);
        return idVoucher;
    }

    public int setData(){
	String params = idVoucher +","+ costsCentre.getIdCostCentre() +","+ idVoucherType +","+ number +",'"+ 
			Date +"',"+ idEntitytype +","+ idEntity +","+ partialWOTaxes +","+ Taxes +","+ partialWTaxes +","+ 
			VATAmount +","+ VATp +","+ Amount +",'"+ withDetail +"','"+ Description +"','"+ closed + "'";
	return LibSQL.getInt("cashflow.setVoucher", params);
    }
    
    public int delete() {
        int result = -1;
        result = LibSQL.getInt("cashflow.deleteVoucher",idVoucher);
        return result;
    }
    
    public void updateTotal(){
	try {
	    String params = ""+ idVoucher;
	    ResultSet rs = LibSQL.exFunction("cashflow.setVoucherTotal", params);
	    if (rs.next()){
		partialWOTaxes = rs.getDouble("partialwotaxes");
		Taxes = rs.getDouble("taxes");
		partialWTaxes = rs.getDouble("partialwtaxes");
		VATAmount = rs.getDouble("vatamount");
		Amount = rs.getDouble("amount");
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void setCostsCentre(CostsCentre costsCentre) {
	this.costsCentre = costsCentre;
    }

    public CostsCentre getCostsCentre() {
	return costsCentre;
    }

    public void setTotalAmount(double totalAmount) {
	this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
	return totalAmount;
    }

    public void setClosed(boolean _closed) {
	this.closed = _closed;
    }

    public boolean isClosed() {
	return closed;
    }

    public int generateDetail(ResourcesRequest _resourcesRequest){
	String params = idVoucher +","+ _resourcesRequest.getIdResourcesRequest();
	return LibSQL.getInt("cashflow.generateDetailByResourcesRequest",params);
    }
    
    public int setStatus(int _status){
	String params = idVoucher +","+ _status;
	return LibSQL.getInt("cashflow.setVoucherStatus", params);
    }
    
    public int setVouchersBilled(String _idvouchers){
	return LibSQL.getInt("cashflow.setVouchersBilled","'"+ _idvouchers +"'");
    }
    
    public int setClose(boolean _close){
	String params = idVoucher +",'"+ _close +"'";
	return LibSQL.getInt("cashflow.setVoucherClose", params);
    }
    
    public ResultSet getDetailByExpenditureAccount(){
	return LibSQL.exFunction("cashflow.getAllVoucherDetailByExpenditureAccount",""+ idVoucher);
    }

    public void setFullNumber(String fullNumber) {
	this.fullNumber = fullNumber;
    }

    public String getFullNumber() {
	return fullNumber;
    }

    public void setPaid(boolean paid) {
	this.paid = paid;
    }

    public boolean isPaid() {
	return paid;
    }

    public void setIdBudget(int _idBudget) {
        idBudget = _idBudget;
    }

    public int getIdBudget() {
        return idBudget;
    }
    
    public int updateIdBudget(int _idVoucher){
        return LibSQL.getInt("cashflow.setVoucherByIdBudget",""+ _idVoucher +","+ idBudget);
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public String getNroFactura() {
        return nroFactura;
    }
}
