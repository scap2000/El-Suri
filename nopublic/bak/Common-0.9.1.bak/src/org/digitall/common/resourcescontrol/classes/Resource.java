package org.digitall.common.resourcescontrol.classes;

import java.sql.ResultSet;

import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.lib.components.Advisor;
import org.digitall.common.cashflow.classes.Entity;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.lib.sql.LibSQL;

public class Resource extends Entity {

    private int idResource = -1;
    private String name = "";
    private int idUnit = -1;
    /** quitar **/
    private Skills skillToProvider;
    private boolean composite = false;
    private String description = "";
    private String estado = "";
    private int lifeTime;
    private int idLifeTimeType = 0;
    private String barcode = "";
    private double totalStock = 0;
    private double availableStock = 0;
    private boolean distinguishable = false;
    private String destined = "p";
    //----------------------------------
    //OBJECTS
    private Units unit;
    private ExpenditureAccount expenditureAccount;

    public Resource() {
	this(EntityTypes.RESOURCE, -1);
    }

    public Resource(int _entityType) {
	this(_entityType, -1);
    }

    public Resource(int _entityType, int _idResource) {
	this(_entityType, _idResource, 0);
    }

    public Resource(int _entityType, int _idResource, double _quantity) {
	super(_entityType);
	idResource = _idResource;
	produce(_quantity);
    }
    //ACCESSORS

    public void setIdResource(int _idResource) {
	idResource = _idResource;
    }

    public int getIdResource() {
	return idResource;
    }

    public void setDescription(String _description) {
	description = _description;
    }

    public String getDescription() {
	return description;
    }

    public void setIdUnit(int _idUnit) {
	unit = new Units(_idUnit,"");
	idUnit = _idUnit;
    }

    public int getIdUnit() {
	return unit.getIdUnit();
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return this.name;
    }

    public void setComposite(boolean composite) {
	this.composite = composite;
    }

    public boolean getComposite() {
	return composite;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setLifetime(int lifetime) {
	this.lifeTime = lifetime;
    }

    public int getLifetime() {
	return lifeTime;
    }

    public void setIdLifeTimeType(int _idLifeTimetype) {
	idLifeTimeType = _idLifeTimetype;
    }

    public int getIdLifeTimeType() {
	return idLifeTimeType;
    }

    public void setBarcode(String _barcode) {
	barcode = _barcode;
    }

    public String getBarcode() {
	return barcode;
    }

    public void setExpenditureAccount(ExpenditureAccount _expenditureAccount) {
	this.expenditureAccount = _expenditureAccount;
    }

    public ExpenditureAccount getExpenditureAccount() {
	return expenditureAccount;
    }

    public int saveData() {
	if (!name.trim().equals("")){
	    String params = "'" + name + "'," + unit.getIdUnit() + "," + skillToProvider.getIdSkill() + "," + expenditureAccount.getIDExpenditureAccount() 
            + ",'false','" + description + "'," + lifeTime + "," + idLifeTimeType + ",0" + barcode +","+ distinguishable +",'"+ destined +"'";
	    if (idResource == -1) {
		idResource = LibSQL.getInt("resourcescontrol.addResource", params);
		return idResource;
	    } else {
		params = idResource + "," + params;
		return LibSQL.getInt("resourcescontrol.setResource", params);
	    }
	} else {
	    Advisor.messageBox("Debe ingresar un nombre para el Recurso", "Nombre no v??lido");
	    return -1;
	}
    }

    public int updateExpenditureAccount(){
	if (Advisor.question("Aviso", "No se deber??a modificar la Cuenta de egresos de un recurso\n??Est?? seguro de continuar?")) {
	    //Advisor.messageBox("No se puede modificar la Cuenta de egresos del recurso","Aviso");
	    String params = idResource + "," + expenditureAccount.getIDExpenditureAccount();
	    return LibSQL.getInt("resourcescontrol.setResourceExpenditureAccount", params);
	} else {
	    return -1;
	}
    }
    
    public double updateTotalStock(){
	String params = ""+ idResource;
	return LibSQL.getDouble("resourcescontrol.setResourceTotalStock", params);
    }
    
    /*public double updateStock(){
	String params = ""+ idResource;
	return LibSQL.getDouble("resourcescontrol.setResourceTotalStock", params);
    }*/
    
    public void retrieveData() {	
	String params = String.valueOf(idResource);
	ResultSet data = LibSQL.exFunction("resourcescontrol.getResources", params);
	try {
	    if (data.next()) {
		this.name = data.getString("name");
		
		//idUnit = data.getInt("idunit");
		if (unit == null) {
		    unit = new Units(data.getInt("idunit"), data.getString("unit"));
		} else {
		    unit.setIdUnit(data.getInt("idunit"));
		    unit.setName(data.getString("unit"));
		}
		skillToProvider = new Skills(data.getInt("idskilltoprovider"));
		composite = data.getBoolean("composite");
		description = data.getString("description");
		lifeTime = data.getInt("lifetime");
		idLifeTimeType = data.getInt("idlifetimetype");
		barcode = data.getString("barcode");
		totalStock = data.getDouble("totalstock");
                destined = data.getString("destined");
                distinguishable = data.getBoolean("distinguishable");
		
		expenditureAccount = new ExpenditureAccount(data.getInt("idaccount"));
	        expenditureAccount.setName(data.getString("accountsname"));
	    }else{
	        idResource = -1;
	    }
	} catch (Exception ex) {
	    idResource = -1;
	    ex.printStackTrace();
	}
    }

    public void setUnit(Units unit) {
	this.unit = unit;
    }

    public Units getUnit() {
	return unit;
    }

    public void setTotalStock(double totalStock) {
	this.totalStock = totalStock;
    }

    public double getTotalStock() {
	return totalStock;
    }

    public void setSkillToProvider(Skills skillToProvider) {
	this.skillToProvider = skillToProvider;
    }

    public Skills getSkillToProvider() {
	return skillToProvider;
    }

    public void setAvailableStock(double availableStock) {
	this.availableStock = availableStock;
    }

    public double getAvailableStock() {
	return availableStock;
    }

    public boolean delete() {
	return LibSQL.getBoolean("resourcesControl.delResource", idResource);
    }

    public void setDistinguishable(boolean _distinguishable) {
        distinguishable = _distinguishable;
    }

    public boolean isDistinguishable() {
        return distinguishable;
    }

    public void setDestined(String _destined) {
        destined = _destined;
    }

    public String getDestined() {
        return destined;
    }
}
