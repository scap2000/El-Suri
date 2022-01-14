package org.digitall.apps.resourcescontrol_091.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class Resources {    
    private int idResource = -1;	
    private String name = "";   
    private int idUnit = -1;		    
    private int idSkillToProvider = -1;	
    private int idAccount = -1;
    private boolean composite = false;      
    private String description = "";    
    private int lifeTime = -1;
    private int idLifeTimeType = -1;      
    private int barCode = -1;  
    private double totalStock = 0.0;

    public Resources() {

    }

    public int getIdResource() {
	return idResource;
    }

    public void setIdResource(int _idResource) {
	this.idResource = _idResource;
    }

    public String getName() {
	return name;
    }

    public void setName(String _name) {
	this.name = _name;
    }

    public int getIdUnit() {
	return idUnit;
    }

    public void setIdUnit(int _idUnit) {
	this.idUnit = _idUnit;
    }

    public int getIdSkillToProvider() {
	return idSkillToProvider;
    }

    public void setIdSkillToProvider(int _idSkillToProvider) {
	this.idSkillToProvider = _idSkillToProvider;
    }

    public int getIdAccount() {
	return idAccount;
    }

    public void setIdAccount(int _idAccount) {
	this.idAccount = _idAccount;
    }

    public boolean isComposite() {
	return composite;
    }

    public void setComposite(boolean _composite) {
	this.composite = _composite;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String _description) {
	this.description = _description;
    }

    public int getLifeTime() {
	return lifeTime;
    }

    public void setLifeTime(int _lifeTime) {
	this.lifeTime = _lifeTime;
    }

    public int getIdLifeTimeType() {
	return idLifeTimeType;
    }

    public void setIdLifeTimeType(int _idLifeTimeType) {
	this.idLifeTimeType = _idLifeTimeType;
    }

    public int getBarCode() {
	return barCode;
    }

    public void setBarCode(int _barCode) {
	this.barCode = _barCode;
    }

    public double getTotalStock() {
	return totalStock;
    }

    public void setTotalStock(double _totalStock) {
	this.totalStock = _totalStock;
    }
    
    public int saveData() {     		
	String params = "'" + name + "'," + idUnit + "," + idSkillToProvider 
	+ "," + idAccount + "," + composite + ",'" + description + "'," + lifeTime + ","
	+ idLifeTimeType + "," + barCode + "," + totalStock;
	int result = -1;
	if (idResource == -1){
	    result = LibSQL.getInt("personalfiles.addResouce",params);  
	    idResource = result;
	}else{      
	    params = idResource +","+ params;
	    result = LibSQL.getInt("personalfiles.setResource",params);
	}               
	return result;
    }
    
    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("personalfiles.getResouces", idResource);
	try {
	    if (data.next()) {  	       
	        name = data.getString("name");    
	        idUnit = data.getInt("idunit");     
	        idSkillToProvider = data.getInt("idskilltoprovider");
	        idAccount = data.getInt("idaccount");
	        composite = data.getBoolean("composite");
	        description = data.getString("description");    
	        lifeTime = data.getInt("lifetime");
	        idLifeTimeType = data.getInt("idlifetimetype");
	        barCode = data.getInt("barcode");  
	        totalStock = data.getDouble("totalstock");		
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

}
