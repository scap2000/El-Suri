package org.digitall.apps.resourcescontrol_091.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class ResourcesDependency {
    private int idResourcesDependency = -1;
    private int idDependency = -1;
    private int idDistResource = -1;
    private int stockAssigned = -1;
    private String estado = "";
    private String dateAssigned = "";
    private boolean transferido = false;
    
    public ResourcesDependency() {
    }

    public int saveData() {     
	String params = idResourcesDependency + "," + idDistResource + "," + idDependency 
	+ "," + stockAssigned + ",'" + estado + "','" + dateAssigned+ "'," + transferido; 
	int result = -1;
       result = LibSQL.getInt("personalfiles.addResourceDependency",params);
	return result;
    }
    
    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("personalfiles.getrecursosDependency", idResourcesDependency);
	try {
	    if (data.next()) {  
	        idResourcesDependency = data.getInt("idresourcesdependency");
		idDependency  = data.getInt("iddependency");
		idDistResource = data.getInt("iddistresource");
	        stockAssigned  = data.getInt("stockassigned");
		estado = data.getString("estado");
		dateAssigned = data.getString("dateassigned");
		transferido = data.getBoolean("transferido");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdResourcesDependency(int idResourcesDependency) {
	this.idResourcesDependency = idResourcesDependency;
    }

    public int getIdResourcesDependency() {
	return idResourcesDependency;
    }

    public void setIdDependency(int idDependency) {
	this.idDependency = idDependency;
    }

    public int getIdDependency() {
	return idDependency;
    }

    public void setIdDistResource(int idResource) {
	this.idDistResource = idResource;
    }

    public int getIdDistResource() {
	return idDistResource;
    }

    public void setSockAssigned(int stockAssigned) {
	this.stockAssigned = stockAssigned;
    }

    public int getSockAssigned() {
	return stockAssigned;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setDateAssigned(String dateAssigned) {
	this.dateAssigned = dateAssigned;
    }

    public String getDateAssigned() {
	return dateAssigned;
    }

    public void setTransferido(boolean transferido) {
	this.transferido = transferido;
    }

    public boolean isTransferido() {
	return transferido;
    }
}
