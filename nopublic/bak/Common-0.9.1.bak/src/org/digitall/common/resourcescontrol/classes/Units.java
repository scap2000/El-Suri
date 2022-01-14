package org.digitall.common.resourcescontrol.classes;

import org.digitall.lib.sql.LibSQL;

public class Units {

    private int idUnit = -1;
    private String Name;
    
    public Units() {

    }

    public Units(int _idUnit, String _Name) {
	idUnit = _idUnit;
	Name = _Name;
    }
    
    public void setIdUnit(int idUnit) {
	this.idUnit = idUnit;
    }

    public int getIdUnit() {
	return idUnit;
    }

    public void setName(String name) {
	this.Name = name;
    }

    public String getName() {
	return Name;
    }

    public int saveData(){
	String params = idUnit +",'"+ Name +"'";
	int result = LibSQL.getInt("tabs.saveUnit", params);
	if (idUnit == -1){
	    idUnit = result;
	}
	return result;
    }
}
