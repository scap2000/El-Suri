package org.digitall.projects.apps.dbadmin_091.classes;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Modulos {

    private int idModulo = -1;
    private String nombre = "" ;
    private String estado = "";
    
    public Modulos() {
    
    }
    
    public Modulos(int _idModulo, String _nombre) {
	idModulo = _idModulo;
	nombre = _nombre;
    }
    
    public int saveData() {
	int resultado = -1;
	String params = ""+idModulo+",'"+nombre+"',''";
	resultado = LibSQL.getInt("admin.addmodulo",params);
	return resultado;
    }
        
    public void retrieveData() {
	//ResultSet data = LibSQL.exQuery("SELECT * FROM admin.modulos WHERE idmodulo = "+idModulo+ "AND estado <>'*'");
	String params = ""+idModulo;
	ResultSet data = LibSQL.exFunction("admin.getmodulo",params);
	try {
	    if (data.next()) {
		idModulo  = data.getInt("idmodulo");
		nombre = data.getString("nombre");
		estado  = data.getString("estado");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public void retrieveData(String _nombreModulo){
	ResultSet data = LibSQL.exQuery("SELECT * FROM admin.modulos WHERE nombre = '"+ _nombreModulo +"' AND estado <>'*'");
	try {
	    if (data.next()) {
		idModulo  = data.getInt("idmodulo");
		nombre = data.getString("nombre");
		estado  = data.getString("estado");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdModulo(int idModulo) {
	this.idModulo = idModulo;
    }

    public int getIdModulo() {
	return idModulo;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }
}