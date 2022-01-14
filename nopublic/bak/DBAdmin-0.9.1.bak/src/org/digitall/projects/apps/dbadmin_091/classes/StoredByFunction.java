package org.digitall.projects.apps.dbadmin_091.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class StoredByFunction {

    private int idStoredByFunction = -1;
    private int idFuncion  = -1;
    private String nombreStored = "";
    private String estado = "";
    
    public StoredByFunction(){
    
    }
    
    public int saveData() {
	int resultado = -1;
	String params = ""+idStoredByFunction+","+idFuncion+",'"+nombreStored+"','" +estado + "'";
	resultado = LibSQL.getInt("admin.addstoredbyfunction",params);
	return resultado;
    }
    
    public void retrieveData() {
	//ResultSet data = LibSQL.exQuery("SELECT * FROM admin.modulos WHERE idmodulo = "+idModulo+ "AND estado <>'*'");
	String params = ""+idStoredByFunction;
	ResultSet data = LibSQL.exFunction("admin.getstoredbyfunction",params);
	try {
	    if (data.next()) {
		idStoredByFunction  = data.getInt("idstoredbyfunction");
		idFuncion = data.getInt("idfuncion");
		nombreStored = data.getString("nombrestored");
		estado  = data.getString("estado");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdStoredByFunction(int idStoredByFunction) {
	this.idStoredByFunction = idStoredByFunction;
    }

    public int getIdStoredByFunction() {
	return idStoredByFunction;
    }

    public void setIdFuncion(int idFuncion) {
	this.idFuncion = idFuncion;
    }

    public int getIdFuncion() {
	return idFuncion;
    }

    public void setNombreStored(String nombreStored) {
	this.nombreStored = nombreStored;
    }

    public String getNombreStored() {
	return nombreStored;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
}
