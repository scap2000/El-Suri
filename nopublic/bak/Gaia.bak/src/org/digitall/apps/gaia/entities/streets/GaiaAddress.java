package org.digitall.apps.gaia.entities.streets;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaAddress {

    private int idAddress = -1;
    private int idstreet = -1;
    private String streetName = "";
    private String number = "";
    private int type = -1;
    private double x;
    private double y;

    public GaiaAddress() {

    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gis.getAddress", "" + idAddress + ",'" + GaiaEnvironment.getScheme() + "'");
	try {
	    if (result.next()) {
		setIdStreet(result.getInt("idcalle"));
		setStreetName(result.getString("calle"));
		setNumber(result.getString("numero"));
	        setType(result.getInt("tipo"));
	        setX(result.getDouble("x"));
	        setY(result.getDouble("y"));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public boolean delete() {
	return LibSQL.getBoolean("gis.delAddress", "" + idAddress + ",'" + GaiaEnvironment.getScheme() + "'");
    }

    public int saveData() {
	int _result = -1;
	String params = "" + idAddress + "," + type + "," + idstreet + ",'" + number + "'," + x + "," + y + ",'" + GaiaEnvironment.getScheme() + "'";
	_result = LibSQL.getInt("gis.setAddress", params);
	if (_result == -1) {
	    Advisor.messageBox("Error al grabar los datos", "Error");
	} else {
	    idAddress = _result;
	}
	return _result;
    }

    public void setIdAddress(int _idAddress) {
	idAddress = _idAddress;
    }

    public int getIdAddress() {
	return idAddress;
    }

    public void setIdStreet(int _idstreet) {
	this.idstreet = _idstreet;
    }

    public int getIdStreet() {
	return idstreet;
    }

    public void setNumber(String _number) {
	this.number = _number;
    }

    public String getNumber() {
	return number;
    }

    public void setType(int _type) {
	this.type = _type;
    }

    public int getType() {
	return type;
    }

    public void setX(double x) {
	this.x = x;
    }

    public double getX() {
	return x;
    }

    public void setY(double y) {
	this.y = y;
    }

    public double getY() {
	return y;
    }

    public void setStreetName(String streetName) {
	this.streetName = streetName;
    }

    public String getStreetName() {
	return streetName;
    }

}
