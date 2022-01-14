package org.digitall.common.resourcescontrol.classes;

import java.sql.ResultSet;

import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.lib.sql.LibSQL;
import org.digitall.common.resourcescontrol.classes.Brands;

public class ResourceBrands extends Resource {

    private int idResourceBrand = -1;
    private double price = 0;
    private double qty = 0;
    private int idBrand = 0;
    
    //OBJECTS
    private Brands brands = new Brands();
    private Resource resource = new Resource();
    
    public ResourceBrands() {
    }
    
    public ResourceBrands(int _idResourceBrand) {
	idResourceBrand = _idResourceBrand;
    }
    
    public ResourceBrands(Resource _resource, Brands _brand) {
	resource = _resource;
	brands = _brand;
    }
    
    public ResourceBrands(Resource _resource) {
	resource = _resource;
    }

    public void setBrands(Brands _brands) {
	brands = _brands;
    }

    public Brands getBrands() {
	return brands;
	
    }

    public void retrieveData() {
	String params = resource.getIdResource() + "," + brands.getIdBrand();
	ResultSet rsData = LibSQL.exFunction("resourcescontrol.getResourceBrands", params);
	try {
	    if (rsData.next()) {
		idResourceBrand = rsData.getInt("idresourcebrand");
		setMinStock(rsData.getDouble("minstock"));
	        setInitiated(false);
		setStock(rsData.getDouble("stock"));
		price = rsData.getDouble("price");
		resource.setUnit(new Units(rsData.getInt("idunit"), rsData.getString("unit")));
	    }else {
	        idResourceBrand = -1;
	        setMinStock(0);
	        setInitiated(false);
	        setStock(0);
	        price = 0;
	        resource.setUnit(new Units(-1,"N/A"));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public int saveData(){
	String params = resource.getIdResource() +","+ brands.getIdBrand() +",0"+ getMinStock() +",0"+ getStock() +",0"+ price;
	
	int result = -1;
	if (idResourceBrand == -1){
	    idResourceBrand = LibSQL.getInt("resourcescontrol.addResourceBrand", params);
	    result = idResourceBrand;
	} else {
	    params = idResourceBrand +","+ params;	    
	    result = LibSQL.getInt("resourcescontrol.setResourceBrand", params);
	}
	if (result != -1){
	    resource.updateTotalStock();
	}
	return result;
    }
    
    public int updateStock(){
	String params = "" + resource.getIdResource() + "," + brands.getIdBrand() + "," + getStock();
	int result = -1;
	//System.out.println("params to update: " + params);
	result = LibSQL.getInt("resourcescontrol.setResourceBrandStock", params);
	return result;
    }
    public void setIdResourceBrand(int _idResourceBrand) {
	idResourceBrand = _idResourceBrand;
    }

    public int getIdResourceBrand() {
	return idResourceBrand;
    }

    public void setPrice(double _price) {
	price = _price;
    }

    public double getPrice() {
	return price;
    }

    public void setResource(Resource _resource) {
	resource = _resource;
    }

    public Resource getResource() {
	return resource;
    }

    public void setQty(double _qty) {
	qty = _qty;
    }

    public double getQty() {
	return qty;
    }

    /**
     * @deprecated
     * @param _idBrand
     */
    public void setIdBrand(int _idBrand) {
	idBrand = _idBrand;
    }

    /**
     * @deprecated
     * @return
     */
    public int getIdBrand() {
	return idBrand;
    }

}
