package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ClassDetalleRelevamientoPublicidad {

    private int iddetallerelevamiento = -1;
    private int idrelevamientopublicidad = -1;
    private String f3a = "";
    private String f3b = "";
    private double f3c = 0.0;
    private double f3d = 0.0;
    private int f3e = -1;
    private double f3f = 0.0;
    private String f3g = "";
    private String f3h = "";
    //foto    
    private String estado = "";
    private String f3i = "";
    //the_geom

    private int nroEncuestador = -1;
    private String nroZona = "-1";
    private int nroEncuesta = -1;
    private String fecha = "";
    private double x = 0;
    private double y = 0;

    public ClassDetalleRelevamientoPublicidad() {

    }
    
    public ClassDetalleRelevamientoPublicidad(int iddetallerelevamiento) {
	this.iddetallerelevamiento = iddetallerelevamiento;
    }

    public void setIddetallerelevamiento(int iddetallerelevamiento) {
	this.iddetallerelevamiento = iddetallerelevamiento;
    }

    public int getIddetallerelevamiento() {
	return iddetallerelevamiento;
    }

    public void setIdrelevamientopublicidad(int idrelevamientopublicidad) {
	this.idrelevamientopublicidad = idrelevamientopublicidad;
    }

    public int getIdrelevamientopublicidad() {
	return idrelevamientopublicidad;
    }

    public void setF3a(String f3a) {
	this.f3a = f3a;
    }

    public String getF3a() {
	return f3a;
    }

    public void setF3b(String f3b) {
	this.f3b = f3b;
    }

    public String getF3b() {
	return f3b;
    }

    public void setF3c(double f3c) {
	this.f3c = f3c;
    }

    public double getF3c() {
	return f3c;
    }

    public void setF3d(double f3d) {
	this.f3d = f3d;
    }

    public double getF3d() {
	return f3d;
    }

    public void setF3e(int f3e) {
	this.f3e = f3e;
    }

    public int getF3e() {
	return f3e;
    }

    public void setF3f(double f3f) {
	this.f3f = f3f;
    }

    public double getF3f() {
	return f3f;
    }

    public void setF3g(String f3g) {
	this.f3g = f3g;
    }

    public String getF3g() {
	return f3g;
    }

    public void setF3h(String f3h) {
	this.f3h = f3h;
    }

    public String getF3h() {
	return f3h;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setF3i(String f3i) {
	this.f3i = f3i;
    }

    public String getF3i() {
	return f3i;
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

    public void setNroEncuesta(int nroEncuesta) {
	this.nroEncuesta = nroEncuesta;
    }

    public int getNroEncuesta() {
	return nroEncuesta;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }
    
    public int saveData() {
	int result = -1;
	String params = "";
	if (idrelevamientopublicidad == -1)  {
	    params = "";
	    result = LibSQL.getInt("gea_salta.addDetalleRelevamiento", params);
	} else if (idrelevamientopublicidad > 0){
	    params = "";
	    result = LibSQL.getInt("gea_salta.setDetalleRelevamiento", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gea_salta.getDetalleRelevamientoPublicidad", iddetallerelevamiento);

	try {
	    if (result.next()) {
	        setIddetallerelevamiento(result.getInt("iddetallerelevamiento"));
	        setIdrelevamientopublicidad(result.getInt("idrelevamientopublicidad"));
	        setF3a(result.getString("f3a"));
	        setF3b(result.getString("f3b"));
	        setF3c(result.getDouble("f3c"));
	        setF3d(result.getDouble("f3d"));
	        setF3e(result.getInt("f3e"));
	        setF3f(result.getDouble("f3f"));
	        setF3g(result.getString("f3g"));
	        setF3h(result.getString("f3h"));
		//System.out.println("catastro = ******* "+ result.getString("f3i"));
	        setF3i(result.getString("f3i"));
	        setNroEncuestador(result.getInt("nroencuestador"));
	        setNroZona(result.getString("nrozona"));
	        setNroEncuesta(result.getInt("nroencuesta"));
		setFecha(result.getString("fecha"));
		setX(result.getDouble("x"));
	        setY(result.getDouble("y"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }

    
     public void retrieveData_encabezado() {
	String params = ""+idrelevamientopublicidad+",0,0";
	 ResultSet result = LibSQL.exFunction("gea_salta.getalldetallerelevamientopublicidad", params);
	 try {
	     if (result.next()) {
		 setIddetallerelevamiento(result.getInt("iddetallerelevamiento"));
		 setIdrelevamientopublicidad(result.getInt("idrelevamientopublicidad"));
		 setF3a(result.getString("f3a"));
		 setF3b(result.getString("f3b"));
		 setF3c(result.getDouble("f3c"));
		 setF3d(result.getDouble("f3d"));
		 setF3e(result.getInt("f3e"));
		 setF3f(result.getDouble("f3f"));
		 setF3g(result.getString("f3g"));
		 setF3h(result.getString("f3h"));
		 //System.out.println("catastro = ******* "+ result.getString("f3i"));
		 setF3i(result.getString("f3i"));
		 setNroEncuestador(result.getInt("nroencuestador"));
		 setNroZona(result.getString("nrozona"));
		 setNroEncuesta(result.getInt("nroencuesta"));
		 setFecha(result.getString("fecha"));
		 setX(result.getDouble("x"));
		 setY(result.getDouble("y"));
	     }
	     
	 } catch (SQLException e) {
	     // TODO
	     System.out.println("error al cargar el encabezado de publicidad");
	 } catch (NullPointerException e) {
	     // TODO
	 }
     }


    public void setX(double x) {
	this.x = x;
    }

    public double getX() {
	return x;
    }

    public void setY(double y) {
	this.y = y;
    }

    public double getY() {
	return y;
    }
}
