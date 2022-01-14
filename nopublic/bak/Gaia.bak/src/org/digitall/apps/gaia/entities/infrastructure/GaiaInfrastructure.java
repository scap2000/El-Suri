package org.digitall.apps.gaia.entities.infrastructure;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaInfrastructure {

    private int idInfrastructure = -1;
    private String name = "";
    private String description = "";
    private int type = -1;
    private double x;
    private double y;

    public GaiaInfrastructure() {
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gis.getInfrastructure", "" + idInfrastructure + ",'" + GaiaEnvironment.getScheme() + "'");
	try {
	    if (result.next()) {
		setName(result.getString("nombre"));
		setDescription(result.getString("descripcion"));
	        setType(result.getInt("tipo"));
	        setX(result.getDouble("x"));
	        setY(result.getDouble("y"));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public boolean delete() {
	return LibSQL.getBoolean("gis.delInfrastructure", "" + idInfrastructure + ",'" + GaiaEnvironment.getScheme() + "'");
    }

    public int saveData() {
	int _result = -1;
	String params = "" + idInfrastructure + "," + type + ",'" + name + "','" + description + "'," + x + "," + y + ",'" + GaiaEnvironment.getScheme() + "'";
	_result = LibSQL.getInt("gis.setInfrastructure", params);
	if (_result == -1) {
	    Advisor.messageBox("Error al grabar los datos", "Error");
	} else {
	    idInfrastructure = _result;
	}
	return _result;
    }

    public void setIdInfrastructure(int _idInfrastructure) {
	idInfrastructure = _idInfrastructure;
    }

    public int getIdInfrastructure() {
	return idInfrastructure;
    }

    public void setName(String _name) {
	this.name = _name;
    }

    public String getName() {
	return name;
    }

    public void setDescription(String _description) {
	this.description = _description;
    }

    public String getDescription() {
	return description;
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

}
