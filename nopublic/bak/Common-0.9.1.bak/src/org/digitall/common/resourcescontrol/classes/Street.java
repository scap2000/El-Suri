package org.digitall.common.resourcescontrol.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class Street {

    private int idstreet = -1;
    private int idLocation = -1;
    private String name = "";
    private int type = -1;
    private String estado = "";
    private String code = "";

    public Street() {

    }

    public void setIdLocation(int _idLocation) {
	idLocation = _idLocation;
    }

    public int getIdLocation() {
	return idLocation;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setEstado(String _estado) {
	estado = _estado;
    }

    public String getEstado() {
	return estado;
    }

    public int saveData() {
	int result = -1;
	String params = "";
	if (idstreet == -1) {
	    params = "" + idLocation + ",'" + name + "'," + type + ",'" + code + "'";
	    result = LibSQL.getInt("tabs.addStreet", params);
	    idstreet = result;
	} else {
	    params = "" + idstreet + "," + idLocation + ",'" + name + "'," + type + ",'" + code + "'";
	    result = LibSQL.getInt("tabs.setStreet", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("org.getStreet", "" + idstreet + "," + idLocation);
	try {
	    if (result.next()) {
		System.out.println("no se desarrollo");
	    } else {

	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdstreet(int idstreet) {
	this.idstreet = idstreet;
    }

    public int getIdstreet() {
	return idstreet;
    }

    public void setType(int type) {
	this.type = type;
    }

    public int getType() {
	return type;
    }

    public void setCode(String _code) {
	this.code = _code;
    }

    public String getCode() {
	return code;
    }

}
