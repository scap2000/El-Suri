package org.digitall.apps.personalfiles.classes;

import java.sql.ResultSet;
import org.digitall.lib.sql.LibSQL;

public class TipoDependencia {

    private int idTipo   = -1;
    private String nombre   = "";
    private int cupoMax = -1;
    private String cargoFemenino = "";
    private String cargoMasculino = "";

    public TipoDependencia() {

    }

    public int getIdTipo() {
	return idTipo;
    }

    public void setIdTipo(int _idTipo) {
	this.idTipo = _idTipo;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String _nombre) {
	this.nombre = _nombre;
    }

    public int getCupoMax() {
	return cupoMax;
    }

    public void setCupoMax(int _cupoMax) {
	this.cupoMax = _cupoMax;
    }

    public String getCargoFemenino() {
	return cargoFemenino;
    }

    public void setCargoFemenino(String _cargoFemenino) {
	this.cargoFemenino = _cargoFemenino;
    }

    public String getCargoMasculino() {
	return cargoMasculino;
    }

    public void setCargoMasculino(String _cargoMasculino) {
	this.cargoMasculino = _cargoMasculino;
    }
    
    public int savaData() {
	String params = "'"+nombre+"',"+cupoMax+",'"+cargoFemenino+"','"+cargoMasculino+"'";
	int result = -1;
	if (idTipo == -1){
	    result = LibSQL.getInt("personalfiles.addTipoDependencia",params);              
	    idTipo = result;        
	} else {            
	    params = idTipo+","+ params;
	    result = LibSQL.getInt("personalfiles.setTipoDependencia",params);
	}                       
	return result;
    }
    
    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("personalfiles.getTipoDependencia", idTipo);
	try {
	    if (data.next()) {
	        nombre   = data.getString("nombre");
	        cupoMax = data.getInt("cupomax");
	        cargoFemenino = data.getString("cargo_femenino");
	        cargoMasculino = data.getString("cargo_masculino");		
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

}
