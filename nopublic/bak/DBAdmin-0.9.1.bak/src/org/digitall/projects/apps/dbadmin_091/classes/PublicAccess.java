package org.digitall.projects.apps.dbadmin_091.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class PublicAccess {

    private int idPublicAccess = -1;
    private String nombre = "";
    private String tipo = "";
    private String estado = "";

    public PublicAccess() {
    
    }

    public int saveData() {
	int resultado = -1;
	String params = ""+idPublicAccess+",'"+nombre+"','"+tipo+"','"+estado+"'";
	resultado = LibSQL.getInt("admin.addpublicaccess",params);
	return resultado;
    }
	
    public void retrieveData() {
	String params = ""+idPublicAccess;
	ResultSet data = LibSQL.exFunction("admin.getpublicaccess",params);
	try {
	    if (data.next()) {
		idPublicAccess  = data.getInt("idpublic");
		nombre = data.getString("nombre");
		tipo = data.getString("tipo");
		estado  = data.getString("estado");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdPublicAccess(int idPublicAccess) {
	this.idPublicAccess = idPublicAccess;
    }

    public int getIdPublicAccess() {
	return idPublicAccess;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void setTipo(String tipo) {
	this.tipo = tipo;
    }

    public String getTipo() {
	return tipo;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
}
