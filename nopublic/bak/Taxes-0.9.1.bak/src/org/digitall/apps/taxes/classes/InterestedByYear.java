package org.digitall.apps.taxes.classes;

import org.digitall.lib.sql.LibSQL;

public class InterestedByYear {

    private int anio = -1;
    private int idtipoimpuesto = -1;
    private double tasaanual = 0;
    private double tasadiaria = 0;
    private String estado = "";
    private String tipoImpuesto = "";
    private boolean nuevo = true;

    public InterestedByYear() {

    }

    public int saveData(){
	int result = -1;
	String params = "";
	if (nuevo)  {
	    params = ""+ anio +","+ idtipoimpuesto +","+ tasaanual;
	    result = LibSQL.getInt("impuestos.addInteresesxAnio", params);
	    anio = result;
	} else {
	    params = ""+ anio +","+ idtipoimpuesto +","+ tasaanual;
	    result = LibSQL.getInt("impuestos.setInteresesxAnio", params);
	}
	 return result;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setIdtipoimpuesto(int idtipoimpuesto) {
	this.idtipoimpuesto = idtipoimpuesto;
    }

    public int getIdtipoimpuesto() {
	return idtipoimpuesto;
    }

    public void setTasaanual(double tasaanual) {
	this.tasaanual = tasaanual;
    }

    public double getTasaanual() {
	return tasaanual;
    }

    public void setTasadiaria(double tasadiaria) {
	this.tasadiaria = tasadiaria;
    }

    public double getTasadiaria() {
	return tasadiaria;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    
    public void setTipoImpuesto(String tipoImpuesto) {
	this.tipoImpuesto = tipoImpuesto;
    }

    public String getTipoImpuesto() {
	return tipoImpuesto;
    }

    public void setNuevo(boolean nuevo) {
	this.nuevo = nuevo;
    }

    public boolean isNuevo() {
	return nuevo;
    }

}
