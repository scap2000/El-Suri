package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class FeesxCategory {

    private int anio = -1;
    private int idtipocategoria = -1;
    private double cuota = 0;
    private String estado = "";
    private double anual = 0;
    private String categoria = "";
    private boolean nuevo = true;
    
    public FeesxCategory() {

    }

    public int saveData(){
	int result = -1;
	String params = "";
	if (nuevo)  {
	    params = ""+ anio +","+ idtipocategoria +","+ anual;
	    result = LibSQL.getInt("taxes.addCuotaxCategoria", params);
	    anio = result;
	} else {
	    params = ""+ anio +","+ idtipocategoria +","+ cuota +","+ anual;
	    result = LibSQL.getInt("taxes.setCuotasxCategoria", params);
	}
	 return result;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setIdtipocategoria(int idtipocategoria) {
	this.idtipocategoria = idtipocategoria;
    }

    public int getIdtipocategoria() {
	return idtipocategoria;
    }

    public void setCuota(double cuota) {
	this.cuota = cuota;
    }

    public double getCuota() {
	return cuota;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setAnual(double anual) {
	this.anual = anual;
    }

    public double getAnual() {
	return anual;
    }

    public void setCategoria(String categoria) {
	this.categoria = categoria;
    }

    public String getCategoria() {
	return categoria;
    }

    public void setNuevo(boolean _nuevo) {
	nuevo = _nuevo;
    }

    public boolean isNuevo() {
	return nuevo;
    }
    
    public int retrieveData(){
	try {
	    ResultSet rs = LibSQL.exFunction("taxes.getFeesxCategory", ""+ anio +","+ idtipocategoria);
	    if(rs.next()){
		anio = rs.getInt("anio");
		idtipocategoria = rs.getInt("idcategoriaautomotor");
	        cuota = rs.getDouble("cuota");
		anual = rs.getDouble("anual");
		categoria = rs.getString("categoria");
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();
	}
	return anio;
    }

}
