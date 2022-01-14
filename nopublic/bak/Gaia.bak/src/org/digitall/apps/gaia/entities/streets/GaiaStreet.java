package org.digitall.apps.gaia.entities.streets;

import java.sql.ResultSet;

import org.digitall.common.resourcescontrol.classes.Street;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaStreet extends Street {

    private int idPolyline = -1;

    public GaiaStreet() {
    }

    public void setIdPolyline(int _idPolyline) {
	this.idPolyline = _idPolyline;
    }

    public int getIdPolyline() {
	return idPolyline;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gis.getStreet", "" + getIdstreet() + "," + idPolyline + ",'" + GaiaEnvironment.getScheme() + "'");
	try {
	    if (result.next()) {
		setIdstreet(result.getInt("idstreet"));
	        setIdLocation(result.getInt("idlocation"));
	        setName(result.getString("name"));
	        setCode(result.getString("code"));
	        setType(result.getInt("type"));
		setIdPolyline(result.getInt("gid"));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public int saveData() {
	int _result = -1;
	if (idPolyline != -1 && saveStreetData() != -1)  {
	    String params = "" + getIdstreet() + "," + idPolyline + ",'" + GaiaEnvironment.getScheme() + "'";
	    _result = LibSQL.getInt("gis.setStreetPolyline", params);
	    if ( _result == -1) {
		Advisor.messageBox("Error al grabar los datos", "Error");
	    }
	}
	return _result;
    }
    
    public int saveStreetData() {
	return super.saveData();
    }

}
