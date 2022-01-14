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
 * VoucherDetail.java
 *
 * */
package org.digitall.common.cashflow.classes;

import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.lib.sql.LibSQL;

public class VoucherDetail {

    private int idVoucherDetail = -1;
    private Voucher voucher;
    private ExpenditureAccount.CCItem ccExpenditureAccount;
    private ResourceBrands resourceBrand;
    private double originalQty = 0;
    private double finalQty = 0;
    private double price = 0;
    private double partialWOTaxes = 0;
    private double taxes = 0;
    private double partialWTaxes = 0;
    private double VATAmount = 0; //IVA
    private double VATp = 0;
    private double Amount = 0;  
    private String Description = "";
    //------------------------------
    private int idVoucherDetailRef = -1;
    private int idPurchaseOrderRef = -1;
    private double bonusQty = -1;
    private double originalPrice = 0;
    
    private int idCostCentre = -1; 

    public VoucherDetail() {

    }

    public void setIdVoucherDetail(int idVoucherDetail) {
	this.idVoucherDetail = idVoucherDetail;
    }

    public int getIdVoucherDetail() {
	return idVoucherDetail;
    }

    public void setVoucher(Voucher _voucher) {
	voucher = _voucher;
    }

    public Voucher getVoucher() {
	return voucher;
    }

    public void setExpenditureAccount(ExpenditureAccount.CCItem _expenditureAccount) {
	this.ccExpenditureAccount = _expenditureAccount;
    }

    public ExpenditureAccount.CCItem getExpenditureAccount() {
	return ccExpenditureAccount;
    }

    public void setPartialWOTaxes(double partialWOTaxes) {
	this.partialWOTaxes = partialWOTaxes;
    }

    public double getPartialWOTaxes() {
	return partialWOTaxes;
    }

    public void setTaxes(double taxes) {
	this.taxes = taxes;
    }

    public double getTaxes() {
	return taxes;
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

    public void setDescription(String description) {
	this.Description = description;
    }

    public String getDescription() {
	return Description;
    }

    public int addData(){
	String params = voucher.getIdVoucher() +","+ ccExpenditureAccount.getIDExpenditureAccount() +","+ resourceBrand.getResource().getIdResource() +","+
			resourceBrand.getBrands().getIdBrand() +","+ originalQty +","+ finalQty +","+ price +","+ partialWOTaxes +","+ 
			taxes +","+ partialWTaxes +","+ VATAmount +","+ VATp +","+ Amount +",'"+ Description +"'," + bonusQty + "," + idCostCentre;
	//System.out.println("SELECT cashflow.addVoucherDetail(" + params + ")");
	idVoucherDetail = LibSQL.getInt("cashflow.addVoucherDetail", params);
	/** REVISAR!!
	if (idVoucherDetailRef != -1 )  {
	    params = voucher.getIdVoucher() +","+ idVoucherDetail + "," + finalQty;
	    int idVDxVD = LibSQL.getInt("cashflow.addVoucherDetailxVoucherDetail", params);
	}
	*/
	return idVoucherDetail;
    }


    public int addDataByInvoice(){
        String params = voucher.getIdVoucher() +","+ ccExpenditureAccount.getIDExpenditureAccount() +","+ resourceBrand.getResource().getIdResource() +","+
                        resourceBrand.getBrands().getIdBrand() +","+ originalQty +","+ finalQty +","+ price +","+ partialWOTaxes +","+ 
                        taxes +","+ partialWTaxes +","+ VATAmount +","+ VATp +","+ Amount +",'"+ Description +"'," + bonusQty + "," + idCostCentre;
        //System.out.println("SELECT cashflow.addVoucherDetail(" + params + ")");
        idVoucherDetail = LibSQL.getInt("cashflow.addVoucherDetail", params);
        
        if (idVoucherDetailRef != -1 )  {
            params = idVoucherDetail + "," + idVoucherDetailRef + "," + finalQty;
            int idVDxVD = LibSQL.getInt("cashflow.addVoucherDetailxVoucherDetail", params);
        }
        
        return idVoucherDetail;
    }

    public int addDataInvoiceByProvisionOrder(){
        String params = voucher.getIdVoucher() +","+ ccExpenditureAccount.getIDExpenditureAccount() +","+ resourceBrand.getResource().getIdResource() +","+
                        resourceBrand.getBrands().getIdBrand() +","+ originalQty +","+ finalQty +","+ price +","+ partialWOTaxes +","+ 
                        taxes +","+ partialWTaxes +","+ VATAmount +","+ VATp +","+ Amount +",'"+ Description +"'," + bonusQty + "," + idCostCentre;
        //System.out.println("SELECT cashflow.addVoucherDetail(" + params + ")");
        idVoucherDetail = LibSQL.getInt("cashflow.addVoucherDetailByProvisionOrderDetail", params);
        //System.out.println("idvoucherdetailref - idvoucherdetail: " + idVoucherDetailRef +" -- " + idVoucherDetail);
        if (idVoucherDetailRef != -1 && idVoucherDetail > 0)  {
            params = idVoucherDetail + "," + idVoucherDetailRef + "," + finalQty +", true" ;
            int idVDxVD = LibSQL.getInt("resourcescontrol.addPurchaseOrderDetailxVoucherDetail", params);
        }
        
        return idVoucherDetail;
    }

    public int setData(){
	String params = idVoucherDetail +","+ voucher.getIdVoucher() +","+ ccExpenditureAccount.getIDExpenditureAccount() +","+ 
			resourceBrand.getResource().getIdResource() +","+ resourceBrand.getBrands().getIdBrand() +","+ 
			originalQty +","+ finalQty +","+ price +","+ partialWOTaxes +","+ taxes +","+ partialWTaxes +","+ 
			VATAmount +","+ VATp +","+ Amount +",'"+ Description +"'";
	return LibSQL.getInt("cashflow.setVoucherDetail", params);
    }
    
    public int addBudgetMovementByInvoice(){
	int result = -1;
	String params = voucher.getIdVoucher() +","+ 
			resourceBrand.getResource().getIdResource() +","+
			resourceBrand.getBrands().getIdBrand() +","+
			finalQty +","+ 
			price + ","+
			Amount +","+ 
			idPurchaseOrderRef +","+
			voucher.getCostsCentre().getIdCostCentre() +","+
                        originalPrice;
	result = LibSQL.getInt("cashflow.addBudgetMovementByInvoice", params);
	
	return result;
    }
    
    public int deletevoucherDetail(){
        int result = -1;
        result = LibSQL.getInt("cashflow.delVoucherDetail",idVoucherDetail);
        return result;
    }
    
    public void setOriginalQty(double _originalQty) {
	originalQty = _originalQty;
    }

    public double getOriginalQty() {
	return originalQty;
    }

    public void setFinalQty(double _finalQty) {
	finalQty = _finalQty;
    }

    public double getFinalQty() {
	return finalQty;
    }

    public void setResourceBrand(ResourceBrands _resourceBrand) {
	resourceBrand = _resourceBrand;
    }

    public ResourceBrands getResourceBrand() {
	return resourceBrand;
    }

    public void setPrice(double _price) {
	price = _price;
    }

    public double getPrice() {
	return price;
    }

    public void setIdVoucherDetailRef(int _idVoucherDetailRef) {
	idVoucherDetailRef = _idVoucherDetailRef;
    }

    public int getIdVoucherDetailRef() {
	return idVoucherDetailRef;
    }

    public int addVouchersRefToInvoice(String _idvouchers){
	String params = "'"+ _idvouchers +"',"+ resourceBrand.getResource().getIdResource() +","+ 
			resourceBrand.getBrands().getIdBrand() +","+ idVoucherDetail;
	return LibSQL.getInt("cashflow.addVoucherDetailxVoucherDetailToInvoice", params);
    }
    
    public int setStatus(int _status){
	String params = idVoucherDetail +","+ _status;
	return LibSQL.getInt("cashflow.setVoucherDetailStatus", params);
    }

    public void setIdPurchaseOrderRef(int idPurchaseOrderRef) {
	this.idPurchaseOrderRef = idPurchaseOrderRef;
    }

    public int getIdPurchaseOrderRef() {
	return idPurchaseOrderRef;
    }

    public void setBonusQty(double bonusQty) {
	this.bonusQty = bonusQty;
    }

    public double getBonusQty() {
	return bonusQty;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setIdCostCentre(int idCostCentre) {
        this.idCostCentre = idCostCentre;
    }

    public int getIdCostCentre() {
        return idCostCentre;
    }
}
