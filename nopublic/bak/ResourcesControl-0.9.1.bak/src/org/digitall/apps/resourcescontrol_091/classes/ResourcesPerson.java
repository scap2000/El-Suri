package org.digitall.apps.resourcescontrol_091.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class ResourcesPerson {

    private int idResourcesPerson = -1;
    private int idDistResource = -1;
    private int idPerson = -1;
    private int stockassigned = -1;
    private String estado = "";
    private String dateAssigned = "";
    private boolean transferido = false;
    
    public ResourcesPerson() {
    }

    public void setidResourcesPerson(int idResourcesPerson) {
	this.idResourcesPerson = idResourcesPerson;
    }

    public int getidResourcesPerson() {
	return idResourcesPerson;
    }

    public void setidDistResource(int idResource) {
	this.idDistResource = idResource;
    }

    public int getidDistResource() {
	return idDistResource;
    }

    public void setIdPerson(int idPerson) {
	this.idPerson = idPerson;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setStockassigned(int stockassigned) {
	this.stockassigned = stockassigned;
    }

    public int getStockassigned() {
	return stockassigned;
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
    
     public void retrieveData() {
	 ResultSet data = LibSQL.exFunction("personalfiles.getResourcesperson", idResourcesPerson);
	 try {
	     if (data.next()) {  
		  idResourcesPerson = data.getInt("idResourcesperson");
		  idDistResource = data.getInt("iddistResource");
		  idPerson = data.getInt("idperson");
		  stockassigned = data.getInt("stockassigned");
		  estado = data.getString("estado");
		  dateAssigned = data.getString("dateassigned");
		  transferido = data.getBoolean("transferido");
	     }
	 } catch (Exception ex) {
	     ex.printStackTrace();
	 }
     }
     
    public int saveData() {
	String params = idResourcesPerson + "," + idDistResource + "," + idPerson 
	+ "," + stockassigned + ",'" + dateAssigned + "'," + transferido;
	int result = -1;
	if (idResourcesPerson == -1){
		result = LibSQL.getInt("personalfiles.addResourcePerson",params);  
	    idResourcesPerson = result;
	}else{      
	    params = idResourcesPerson +","+ params;
	    result = LibSQL.getInt("personalfiles.addResourcePerson",params);
	}               
	return 0;
    }
}
