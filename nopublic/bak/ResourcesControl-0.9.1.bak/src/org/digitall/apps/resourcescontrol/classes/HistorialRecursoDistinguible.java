package org.digitall.apps.resourcescontrol.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class HistorialRecursoDistinguible {
 
    private int idDistinguishableResourceHistory = -1;
    private int idDistinguishableResource = -1;
    private String initDate = "";
    private String endDate = "";
    private int idPerson = -1;
    private String subject = "";
    private double waste = 0;
    private int idDependency = -1;
    
    
    public HistorialRecursoDistinguible() {

    }

    public int getIdDistinguishableResourceHistory() {
	return idDistinguishableResourceHistory;
    }

    public void setIdDistinguishableResourceHistory(int idDistinguishableResourceHistory) {
	this.idDistinguishableResourceHistory = idDistinguishableResourceHistory;
    }

    public int getIdDistinguishableResource() {
	return idDistinguishableResource;
    }

    public void setIdDistinguishableResource(int _idDistinguishableResource) {
	this.idDistinguishableResource = _idDistinguishableResource;
    }

    public String getInitDate() {
	return initDate;
    }

    public void setInitDate(String _initDate) {
	this.initDate = _initDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setEndDate(String _endDate) {
	this.endDate = _endDate;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setIdPerson(int _idPerson) {
	this.idPerson = _idPerson;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String _subject) {
	this.subject = _subject;
    }

    public double getWaste() {
	return waste;
    }

    public void setWaste(double _waste) {
	this.waste = _waste;
    }
    
    public int getIdDependency() {
	return idDependency;
    }

    public void setIdDependency(int _idDependency) {
	this.idDependency = _idDependency;
    }
    
    public int saveData() {          	
	String params = idDistinguishableResource+","+idPerson+",'"+initDate+"','"+endDate+"','"+subject+"'," + waste + ","+idDependency; 
	int result = -1;
	if (idDistinguishableResourceHistory == -1){           
	    result = LibSQL.getInt("personalfiles.addHistorialRecursoDisponible",params);    
	    idDistinguishableResourceHistory = result;
	}else{      
	    params = ""+idDistinguishableResourceHistory+","+ params; 
	    result = LibSQL.getInt("personalfiles.setHistorialRecursoDisponible",params); // falta crearla
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = String.valueOf(idPerson);
	ResultSet data = LibSQL.exFunction("personalfiles.getHistorialRecursoDisponible", params); // falta crearla
	try {
	    if (data.next()) {          
		idDistinguishableResourceHistory = data.getInt("iddistinguishableresourcehistory");
	        idDistinguishableResource = data.getInt("idDistinguishableResource");
	        initDate = data.getString("initdate");
	        endDate = data.getString("enddate");
	        idPerson = data.getInt("idPerson");
	        subject = data.getString("subject");
	        waste = data.getFloat("waste");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }    
}
