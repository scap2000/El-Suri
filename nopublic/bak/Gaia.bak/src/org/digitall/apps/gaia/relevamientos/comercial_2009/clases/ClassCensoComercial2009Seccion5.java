package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ClassCensoComercial2009Seccion5 {

    private int idEncuestaComercial = -1;
    private String f5a = "";
    private String f5b = "";
    private String f5c = "";
    private String f5d = "";
    private String f5e = "";
    private String f5f = "";
    private String f5g = "";
    private String f6 = "";
    private String estado = "";	

    public ClassCensoComercial2009Seccion5() {
    
    }

    public ClassCensoComercial2009Seccion5(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }
    
    public void setIdEncuestaComercial(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }

    public int getIdEncuestaComercial() {
	return idEncuestaComercial;
    }

    public void setF5a(String f5a) {
	this.f5a = f5a;
    }

    public String getF5a() {
	return f5a;
    }

    public void setF5b(String f5b) {
	this.f5b = f5b;
    }

    public String getF5b() {
	return f5b;
    }

    public void setF5c(String f5c) {
	this.f5c = f5c;
    }

    public String getF5c() {
	return f5c;
    }

    public void setF5d(String f5d) {
	this.f5d = f5d;
    }

    public String getF5d() {
	return f5d;
    }

    public void setF5e(String f5e) {
	this.f5e = f5e;
    }

    public String getF5e() {
	return f5e;
    }

    public void setF5f(String f5f) {
	this.f5f = f5f;
    }

    public String getF5f() {
	return f5f;
    }

    public void setF5g(String f5g) {
	this.f5g = f5g;
    }

    public String getF5g() {
	return f5g;
    }

    public void setF6(String f6) {
	this.f6 = f6;
    }

    public String getF6() {
	return f6;
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
	if (idEncuestaComercial == -1)  {
	    params = "";
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion5", params);
	} else if (idEncuestaComercial > 0){
	    params = "";
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion5", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gea_salta.getseccion5censocomercial", idEncuestaComercial);
	try {
	    if (result.next()) {
		setIdEncuestaComercial(result.getInt("idencuestacomercial"));
		setF5a(result.getString("f5a"));
	        setF5b(result.getString("f5b"));
	        setF5c(result.getString("f5c"));
	        setF5d(result.getString("f5d"));
	        setF5e(result.getString("f5e"));
	        setF5f(result.getString("f5f"));
	        setF5g(result.getString("f5g"));
	        setF6(result.getString("f6"));
		setEstado(result.getString("estado"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }
}
