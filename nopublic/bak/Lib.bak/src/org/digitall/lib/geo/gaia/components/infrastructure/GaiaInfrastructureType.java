package org.digitall.lib.geo.gaia.components.infrastructure;

import java.awt.image.BufferedImage;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;

public class GaiaInfrastructureType {

    private int idType = -1;
    private String name = "";
    private BufferedImage symbol = null;

    public GaiaInfrastructureType() {
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gis.getInfrastructureType", idType);
	try {
	    if (result.next()) {
		setName(result.getString("nombre"));
		retrieveSymbol();
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public boolean delete() {
	return LibSQL.getBoolean("gis.delInfrastructureType", idType);
    }

    public int saveData() {
	int _result = -1;
	String params = idType + ",'" + name + "'";
	_result = LibSQL.getInt("tabs.setInfrastructureType", params);
	if (_result == -1) {
	    Advisor.messageBox("Error al grabar los datos", "Error");
	} else {
	    idType = _result;
	    if (symbol != null) {
	        if (!LibIMG.saveImage(symbol, "tabs.infrastructuretype_tabs", "symbol", "WHERE idtype = " + idType)) {
		    Advisor.messageBox("Error al grabar la imagen", "Error");
		}
	    }
	}
	return _result;
    }

    public void setName(String _name) {
	this.name = _name;
    }

    public String getName() {
	return name;
    }

    public void retrieveSymbol() {
	symbol = LibIMG.getImage("tabs.infrastructuretype_tabs", "symbol", "WHERE idtype = " + idType);
    }

    public void setIdType(int idType) {
	this.idType = idType;
    }

    public int getIdType() {
	return idType;
    }

    public void setSymbol(BufferedImage symbol) {
	this.symbol = symbol;
    }

    public BufferedImage getSymbol() {
	return symbol;
    }

}
