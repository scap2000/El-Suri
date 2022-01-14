package org.digitall.common.resourcescontrol.classes;

import org.digitall.lib.sql.LibSQL;

public class Skills {

    private int idSkill = -1;
    private String Name;
    private boolean toPerson;
    private boolean toProvider;
    private boolean toCompany;
    
    public Skills(int _idSkill) {
	idSkill = _idSkill;
    }

    public Skills() {

    }
    
    public void setIdSkill(int idSkill) {
	this.idSkill = idSkill;
    }

    public int getIdSkill() {
	return idSkill;
    }

    public void setName(String name) {
	this.Name = name;
    }

    public String getName() {
	return Name;
    }

    public void setToPerson(boolean toPerson) {
	this.toPerson = toPerson;
    }

    public boolean isToPerson() {
	return toPerson;
    }

    public void setToProvider(boolean toProvider) {
	this.toProvider = toProvider;
    }

    public boolean isToProvider() {
	return toProvider;
    }

    public void setToCompany(boolean toCompany) {
	this.toCompany = toCompany;
    }

    public boolean isToCompany() {
	return toCompany;
    }

    public int saveData() {
	int result = -1;
	String params = "'"+ Name +"','"+ toPerson +"','"+ toProvider +"','"+ toCompany +"'";
	if (idSkill == -1){
	    result = LibSQL.getInt("tabs.addSkill",params);
	    idSkill = result;
	} else {
	    params = idSkill +","+ params;
	    result = LibSQL.getInt("tabs.setSkill",params);
	}
	return result;
    }
    
    public boolean deleteSkill() {
        return LibSQL.getBoolean("tabs.delskill", ""+ idSkill);
    }
}
