package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ClassEncabezadoRelevamientoPublicidad {

    private int idRelevamientoPublicidad = -1;
    private int nroEncuestador = 0;
    private String nroZona = "";
    private String nroEncuesta = "";
    private String fecha = "";
    private String usuario = "";
    private String fechaCarga = "";
    private boolean encuestaAsociada = false;

    public ClassEncabezadoRelevamientoPublicidad() {
    }

    public void setIdRelevamientoPublicidad(int idRelevamientoPublicidad) {
	this.idRelevamientoPublicidad = idRelevamientoPublicidad;
    }

    public int getIdRelevamientoPublicidad() {
	return idRelevamientoPublicidad;
    }

    public void setNroEncuestador(int nroEncuestador) {
	this.nroEncuestador = nroEncuestador;
    }

    public int getNroEncuestador() {
	return nroEncuestador;
    }

    public void setNroZona(String nroZona) {
	this.nroZona = nroZona;
    }

    public String getNroZona() {
	return nroZona;
    }

    public void setNroEncuesta(String nroEncuesta) {
	this.nroEncuesta = nroEncuesta;
    }

    public String getNroEncuesta() {
	return nroEncuesta;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setFechaCarga(String fechaCarga) {
	this.fechaCarga = fechaCarga;
    }

    public String getFechaCarga() {
	return fechaCarga;
    }

    public void setEncuestaAsociada(boolean encuestaAsociada) {
	this.encuestaAsociada = encuestaAsociada;
    }

    public boolean isEncuestaAsociada() {
	return encuestaAsociada;
    }
    
    public int saveData() {
	int result = -1;
	String params = "";
	if (idRelevamientoPublicidad == -1)  {
	    params = "";
	    result = LibSQL.getInt("gea_salta.addrelevamientopublicidadencabezado", params);
	} else if (idRelevamientoPublicidad > 0){
	    params = "";
	     //result = LibSQL.getInt("gea_salta.addrelevamientopublicidadencabezado", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gea_salta.getencabezadorelevamientopublicidad", idRelevamientoPublicidad);
	try {
	    if (result.next()) {
		setIdRelevamientoPublicidad(result.getInt("idrelevamientopublicidad"));
		setNroEncuestador(result.getInt("nroencuestador"));
		setNroZona(result.getString("nrozona"));
		setNroEncuesta(result.getString("nroencuesta"));
		setFecha(result.getString("fecha"));
		setUsuario(result.getString("usuario"));
		setFechaCarga(result.getString("fechacarga"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }
}
