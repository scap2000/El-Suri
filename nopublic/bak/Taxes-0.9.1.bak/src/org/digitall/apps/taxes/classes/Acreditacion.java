package org.digitall.apps.taxes.classes;

import org.digitall.lib.sql.LibSQL;

public class Acreditacion {

    private int idAcreditacion = -1;  
    private String fecha = "";
    private String entidad = "";
    private String nroCuenta = "";
    private double monto = 0.0;
    private String estado = "";
    private int nroCaja = 0;

    public Acreditacion() {
    
    }

    public void setIdacreditacion(int idacreditacion) {
	this.idAcreditacion = idacreditacion;
    }

    public int getIdacreditacion() {
	return idAcreditacion;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }

    public void setEntidad(String entidad) {
	this.entidad = entidad;
    }

    public String getEntidad() {
	return entidad;
    }

    public void setNroCuenta(String nroCuenta) {
	this.nroCuenta = nroCuenta;
    }

    public String getNroCuenta() {
	return nroCuenta;
    }

    public void setMonto(double monto) {
	this.monto = monto;
    }

    public double getMonto() {
	return monto;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
    
    public int saveData(){
	String params = "";
	int result = -1;
	params = "'"+ entidad +"','" + nroCuenta + "'," + monto +","+ nroCaja ;
	if (idAcreditacion == -1)  {
	    result = LibSQL.getInt("cashier.addAcreditacion",params);
	    idAcreditacion = result; 
	} else  {
	    params = idAcreditacion +","+ params;
	    result = LibSQL.getInt("cashier.setAcreditacion",params); //falta crearla (verificar si es necesario)
	}
	return result;
    }

    public void setNroCaja(int nroCaja) {
	this.nroCaja = nroCaja;
    }

    public int getNroCaja() {
	return nroCaja;
    }
}
