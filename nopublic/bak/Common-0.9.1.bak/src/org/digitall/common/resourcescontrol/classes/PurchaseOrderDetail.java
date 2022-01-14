package org.digitall.common.resourcescontrol.classes;

import org.digitall.common.resourcescontrol.classes.PurchaseOrder;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.ResourcesRequestDetail;
import org.digitall.lib.sql.LibSQL;

public class PurchaseOrderDetail {
    
    private int idPurchaseOrderDetail = -1;
    private PurchaseOrder purchaseOrder;
    private double approvedQty = 0;
    private double receivedQty = 0;
    private double price = 0;
    private double amount = 0;
    private String description = "";
    private ResourceBrands resourceBrand;
    private ResourcesRequestDetail resourcesRequestAuthDetailRef;
    private int idRequestStatus = 1;
    
    public PurchaseOrderDetail(PurchaseOrder _purchaseOrder){
	purchaseOrder = _purchaseOrder;
    }

    public PurchaseOrderDetail(){
	
    }
    
    public void setIdPurchaseOrderDetail(int idPurchaseOrderDetail) {
	this.idPurchaseOrderDetail = idPurchaseOrderDetail;
    }

    public int getIdPurchaseOrderDetail() {
	return idPurchaseOrderDetail;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
	this.purchaseOrder = purchaseOrder;
    }

    public PurchaseOrder getPurchaseOrder() {
	return purchaseOrder;
    }

    public void setApprovedQty(double approvedQty) {
	this.approvedQty = approvedQty;
    }

    public double getApprovedQty() {
	return approvedQty;
    }

    public void setReceivedQty(double receivedQty) {
	this.receivedQty = receivedQty;
    }

    public double getReceivedQty() {
	return receivedQty;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public double getPrice() {
	return price;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public double getAmount() {
	return amount;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    public void setResourceBrand(ResourceBrands resourceBrand) {
	this.resourceBrand = resourceBrand;
    }

    public ResourceBrands getResourceBrand() {
	return resourceBrand;
    }

    public void setIdRequestStatus(int idRequestStatus) {
	this.idRequestStatus = idRequestStatus;
    }

    public int getIdRequestStatus() {
	return idRequestStatus;
    }

    public int saveData(){
	int result = -1;
	String param = purchaseOrder.getIdPurchaseOrder() +","+ resourceBrand.getResource().getIdResource() +","+ approvedQty +","+ 
			price +","+ amount +",'"+ description +"',"+ resourceBrand.getBrands().getIdBrand() +","+ 
			resourcesRequestAuthDetailRef.getIdResourcesRequestDetail() +","+ idRequestStatus +","+ receivedQty;
        result = LibSQL.getInt("resourcescontrol.addPurchaseOrderDetail","" +  param);
	return result;
    }

    public int generateDetail(int _idResourcesRequest){
	String params = purchaseOrder.getIdPurchaseOrder() +","+ _idResourcesRequest;
	return LibSQL.getInt("resourcescontrol.generatePurchaseOrderDetail", params);
    }
    
    public void setResourcesRequestAuthDetailRef(ResourcesRequestDetail resourcesRequestAuthDetailRef) {
	this.resourcesRequestAuthDetailRef = resourcesRequestAuthDetailRef;
    }

    public ResourcesRequestDetail getResourcesRequestAuthDetailRef() {
	return resourcesRequestAuthDetailRef;
    }
    
    public int delete(){
        String params = "" + idPurchaseOrderDetail;
        return LibSQL.getInt("resourcescontrol.delPurchaseOrderDetail", params);
    }
    
    public int saveDataByProvisionOrder() {
        int result = -1;
        String param = purchaseOrder.getIdPurchaseOrder() +","+ resourceBrand.getResource().getIdResource() +","+ approvedQty +","+ 
                        price +","+ amount +",'"+ description +"',"+ resourceBrand.getBrands().getIdBrand() +","+ 
                        + idRequestStatus +","+ receivedQty;
            result = LibSQL.getInt("resourcescontrol.addPurchaseOrderDetailByProvisionOrder",param);
        return result;
    }
    
   
    
}
