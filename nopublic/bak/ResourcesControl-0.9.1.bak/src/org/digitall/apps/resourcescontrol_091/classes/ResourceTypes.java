package org.digitall.apps.resourcescontrol_091.classes;

import org.digitall.lib.sql.LibSQL;

public class ResourceTypes {

    private int idResourceType = -1;
    private String name;
    
    public ResourceTypes() {

    }

    public ResourceTypes(int _idResourceType, String _name) {
	idResourceType = _idResourceType;
	name = _name;
    }
    
    public void setIdResourceType(int idResourceType) {
	this.idResourceType = idResourceType;
    }

    public int getIdResourceType() {
	return idResourceType;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public int saveData(){
	String params = idResourceType +",'"+ name +"'";
	int result = LibSQL.getInt("resourcescontrol.saveResourceType", params);
	if (idResourceType == -1){
	    idResourceType = result;
	}
	return result;
    }
}
