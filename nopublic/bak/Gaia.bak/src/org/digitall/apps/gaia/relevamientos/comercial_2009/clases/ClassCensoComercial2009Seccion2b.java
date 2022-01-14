package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ClassCensoComercial2009Seccion2b {

    private int idEncuestaComercial = -1;
    private String f2k1_a = "";
    private String f2k1_b = "";  
    private String f2k1_c = "";
    private String f2k2_a = "";
    private String f2k2_b = "";
    private String f2k2_c = "";
    private String f2k3_a = "";
    private String f2k3_b = "";
    private String f2k3_c = "";
    private String f2k4_a = "";
    private String f2k4_b = "";
    private String f2k4_c = "";
    private String f2l = "";
    private String f2m = "";
    private String f2n = "";
    private String f2nn = "";
    private String f2o = "";
    private String f2p = "";
    private String f2q = "";
    private String f2r = "";
    private String f2s = "";
    private String f2t = "";
    private String f2u = "";
    private String f2v = "";
    private String f2w = "";
    private String f2x = "";
    private String estado = "";
    
    public ClassCensoComercial2009Seccion2b() {
    
    }
    
    public ClassCensoComercial2009Seccion2b(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }

    public void setIdEncuestaComercial(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }

    public int getIdEncuestaComercial() {
	return idEncuestaComercial;
    }

    public void setF2k1_a(String f2k1_a) {
	this.f2k1_a = f2k1_a;
    }

    public String getF2k1_a() {
	return f2k1_a;
    }

    public void setF2k1_b(String f2k1_b) {
	this.f2k1_b = f2k1_b;
    }

    public String getF2k1_b() {
	return f2k1_b;
    }

    public void setF2k1_c(String f2k1_c) {
	this.f2k1_c = f2k1_c;
    }

    public String getF2k1_c() {
	return f2k1_c;
    }

    public void setF2k2_a(String f2k2_a) {
	this.f2k2_a = f2k2_a;
    }

    public String getF2k2_a() {
	return f2k2_a;
    }

    public void setF2k2_b(String f2k2_b) {
	this.f2k2_b = f2k2_b;
    }

    public String getF2k2_b() {
	return f2k2_b;
    }

    public void setF2k2_c(String f2k2_c) {
	this.f2k2_c = f2k2_c;
    }

    public String getF2k2_c() {
	return f2k2_c;
    }

    public void setF2k3_a(String f2k3_a) {
	this.f2k3_a = f2k3_a;
    }

    public String getF2k3_a() {
	return f2k3_a;
    }

    public void setF2k3_b(String f2k3_b) {
	this.f2k3_b = f2k3_b;
    }

    public String getF2k3_b() {
	return f2k3_b;
    }

    public void setF2k3_c(String f2k3_c) {
	this.f2k3_c = f2k3_c;
    }

    public String getF2k3_c() {
	return f2k3_c;
    }

    public void setF2k4_a(String f2k4_a) {
	this.f2k4_a = f2k4_a;
    }

    public String getF2k4_a() {
	return f2k4_a;
    }

    public void setF2k4_b(String f2k4_b) {
	this.f2k4_b = f2k4_b;
    }

    public String getF2k4_b() {
	return f2k4_b;
    }

    public void setF2k4_c(String f2k4_c) {
	this.f2k4_c = f2k4_c;
    }

    public String getF2k4_c() {
	return f2k4_c;
    }

    public void setF2l(String f2l) {
	this.f2l = f2l;
    }

    public String getF2l() {
	return f2l;
    }

    public void setF2m(String f2m) {
	this.f2m = f2m;
    }

    public String getF2m() {
	return f2m;
    }

    public void setF2n(String f2n) {
	this.f2n = f2n;
    }

    public String getF2n() {
	return f2n;
    }

    public void setF2nn(String f2nn) {
	this.f2nn = f2nn;
    }

    public String getF2nn() {
	return f2nn;
    }

    public void setF2o(String f2o) {
	this.f2o = f2o;
    }

    public String getF2o() {
	return f2o;
    }

    public void setF2p(String f2p) {
	this.f2p = f2p;
    }

    public String getF2p() {
	return f2p;
    }

    public void setF2q(String f2q) {
	this.f2q = f2q;
    }

    public String getF2q() {
	return f2q;
    }

    public void setF2r(String f2r) {
	this.f2r = f2r;
    }

    public String getF2r() {
	return f2r;
    }

    public void setF2s(String f2s) {
	this.f2s = f2s;
    }

    public String getF2s() {
	return f2s;
    }

    public void setF2t(String f2t) {
	this.f2t = f2t;
    }

    public String getF2t() {
	return f2t;
    }

    public void setF2u(String f2u) {
	this.f2u = f2u;
    }

    public String getF2u() {
	return f2u;
    }

    public void setF2v(String f2v) {
	this.f2v = f2v;
    }

    public String getF2v() {
	return f2v;
    }

    public void setF2w(String f2w) {
	this.f2w = f2w;
    }

    public String getF2w() {
	return f2w;
    }

    public void setF2x(String f2x) {
	this.f2x = f2x;
    }

    public String getF2x() {
	return f2x;
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
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion2b", params);
	} else if (idEncuestaComercial > 0){
	    params = "";
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion2b", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gea_salta.getseccion2bcensocomercial", idEncuestaComercial);
	try {
	    if (result.next()) {
		setIdEncuestaComercial(result.getInt("idencuestacomercial"));
		setF2k1_a(result.getString("f2k1_a"));
	        setF2k1_b(result.getString("f2k1_b"));
	        setF2k1_c(result.getString("f2k1_c"));
	        setF2k2_a(result.getString("f2k2_a"));
	        setF2k2_b(result.getString("f2k2_b"));
	        setF2k2_c(result.getString("f2k2_c"));
	        setF2k3_a(result.getString("f2k3_a"));
	        setF2k3_b(result.getString("f2k3_b"));
	        setF2k3_c(result.getString("f2k3_c"));
	        setF2k4_a(result.getString("f2k4_a"));
	        setF2k4_b(result.getString("f2k4_b"));
	        setF2k4_c(result.getString("f2k4_c"));
		setF2l(result.getString("f2l"));
	        setF2m(result.getString("f2m"));
	        setF2n(result.getString("f2n"));
	        setF2nn(result.getString("f2nn"));
	        setF2o(result.getString("f2o"));
	        setF2p(result.getString("f2p"));
	        setF2q(result.getString("f2q"));
	        setF2r(result.getString("f2r"));
	        setF2s(result.getString("f2s"));
	        setF2t(result.getString("f2t"));
	        setF2u(result.getString("f2u"));
	        setF2v(result.getString("f2v"));
	        setF2w(result.getString("f2w"));
	        setF2x(result.getString("f2x"));
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
