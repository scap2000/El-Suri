package org.digitall.common.resourcescontrol.classes;

import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.lib.sql.LibSQL;

public class ResourceComponents{

    private Resource resource;
    private Resource componentResource;
    private int idSkill = -1;
    private double Quantity = 0;
    private int Value = 0;
    private String skillName="";

    public ResourceComponents() {

    }

    public int saveData() {
	String params = resource.getIdResource() +","+ componentResource.getIdResource() +","+ idSkill +","+ Quantity +","+ Value;
	return LibSQL.getInt("resourcescontrol.saveResourceComponent",params);
    }
    
    public void retrieveData() {
	//TODOS LOS DATOS
    }

    public void setResource(Resource resource) {
	this.resource = resource;
    }

    public Resource getResource() {
	return resource;
    }

    public void setComponentResource(Resource componentResource) {
	this.componentResource = componentResource;
    }

    public Resource getComponentResource() {
	return componentResource;
    }

    public void setIdSkill(int idSkill) {
	this.idSkill = idSkill;
    }

    public int getIdSkill() {
	return idSkill;
    }

    public void setQuantity(double quantity) {
	this.Quantity = quantity;
    }

    public double getQuantity() {
	return Quantity;
    }

    public void setSkillName(String skillName) {
	this.skillName = skillName;
    }

    public String getSkillName() {
	return skillName;
    }

    public void setValue(int value) {
	this.Value = value;
    }

    public int getValue() {
	return Value;
    }

}
