package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ClassRelevamientoPublicidad2009Seccion2 {

    private int idRelevamientoPublicidad = -1;
    private String f2a = "";
    private String f2b = "";
    private String f2c = "";
    private String f2d = "";
    private String f2e = "";
    private String f2f = "";
    private String f2g = "";
    private String f2h = "";
    private String f2i = "";
    private String estado = "";

    public ClassRelevamientoPublicidad2009Seccion2() {
    
    }
    
    public ClassRelevamientoPublicidad2009Seccion2(int idRelevamientoPublicidad) {
	this.idRelevamientoPublicidad = idRelevamientoPublicidad;
    }

    public void setIdRelevamientoPublicidad(int idRelevamientoPublicidad) {
	this.idRelevamientoPublicidad = idRelevamientoPublicidad;
    }

    public int getIdRelevamientoPublicidad() {
	return idRelevamientoPublicidad;
    }

    public void setF2a(String f2a) {
	this.f2a = f2a;
    }

    public String getF2a() {
	return f2a;
    }

    public void setF2b(String f2b) {
	this.f2b = f2b;
    }

    public String getF2b() {
	return f2b;
    }

    public void setF2c(String f2c) {
	this.f2c = f2c;
    }

    public String getF2c() {
	return f2c;
    }

    public void setF2d(String f2d) {
	this.f2d = f2d;
    }

    public String getF2d() {
	return f2d;
    }

    public void setF2e(String f2e) {
	this.f2e = f2e;
    }

    public String getF2e() {
	return f2e;
    }

    public void setF2f(String f2f) {
	this.f2f = f2f;
    }

    public String getF2f() {
	return f2f;
    }

    public void setF2g(String f2g) {
	this.f2g = f2g;
    }

    public String getF2g() {
	return f2g;
    }

    public void setF2h(String f2h) {
	this.f2h = f2h;
    }

    public String getF2h() {
	return f2h;
    }

    public void setF2i(String f2i) {
	this.f2i = f2i;
    }

    public String getF2i() {
	return f2i;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
    
    public int saveData() {
	int result = -1;
	String params = "";
	if (idRelevamientoPublicidad == -1)  {
	    params = "";
	    result = LibSQL.getInt("gea_salta.addrelevamientopublicidadseccion2", params);
	} else if (idRelevamientoPublicidad > 0){
	    params = "";
	    result = LibSQL.getInt("gea_salta.addrelevamientopublicidadseccion2", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gea_salta.getseccion2relevamientopublicidad", idRelevamientoPublicidad);
	try {
	    if (result.next()) {
		setIdRelevamientoPublicidad(result.getInt("idrelevamientopublicidad"));
		setF2a(result.getString("f2a"));
		setF2b(result.getString("f2b"));
		setF2c(result.getString("f2c"));
		setF2d(result.getString("f2d"));
		setF2e(result.getString("f2e"));
		setF2f(result.getString("f2f"));
		setF2g(result.getString("f2g"));
		setF2h(result.getString("f2h"));
		setF2i(result.getString("f2i"));
		setEstado(result.getString("estado"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error al cargar la seccion 2");
	} catch (NullPointerException e) {
	    // TODO
	}
    }
}
