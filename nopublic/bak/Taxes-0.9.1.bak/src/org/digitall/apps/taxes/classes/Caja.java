package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Caja {

    private int idCaja = -1;
    private int nroCaja = 0;
    private double montoMinimo = 0.0;
    private double montoMaximo = 0.0;
    private boolean cajaAbierta = false;
    private double montoEfectivo = 0.0;
    private double montoCheques = 0.0;
    private int idResponsable = -1;
    private String ubicacion = "";
    private String ip = "";
    private String mac = "";
    private String estado = "";

    public Caja() {
    
    }

    public Caja(int _idCaja) {
	idCaja = _idCaja;
    }

    public void setIdCaja(int idCaja) {
	this.idCaja = idCaja;
    }

    public int getIdCaja() {
	return idCaja;
    }

    public void setNroCaja(int nroCaja) {
	this.nroCaja = nroCaja;
    }

    public int getNroCaja() {
	return nroCaja;
    }

    public void setMontoMinimo(double montoMinimo) {
	this.montoMinimo = montoMinimo;
    }

    public double getMontoMinimo() {
	return montoMinimo;
    }

    public void setMontoMaximo(double montoMaximo) {
	this.montoMaximo = montoMaximo;
    }

    public double getMontoMaximo() {
	return montoMaximo;
    }

    public void setCajaAbierta(boolean cajaAbierta) {
	this.cajaAbierta = cajaAbierta;
    }

    public boolean isCajaAbierta() {
	return cajaAbierta;
    }

    public void setMontoEfectivo(double montoEfectivo) {
	this.montoEfectivo = montoEfectivo;
    }

    public double getMontoEfectivo() {
	return montoEfectivo;
    }

    public void setMontoCheques(double montoCheques) {
	this.montoCheques = montoCheques;
    }

    public double getMontoCheques() {
	return montoCheques;
    }

    public void setIdResponsable(int idResponsable) {
	this.idResponsable = idResponsable;
    }

    public int getIdResponsable() {
	return idResponsable;
    }

    public void setUbicacion(String ubicacion) {
	this.ubicacion = ubicacion;
    }

    public String getUbicacion() {
	return ubicacion;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }

    public String getIp() {
	return ip;
    }

    public void setMac(String mac) {
	this.mac = mac;
    }

    public String getMac() {
	return mac;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("cashier.getCaja", idCaja);
	try {
	    if (result.next()) {
		setIdCaja(result.getInt("idcaja"));
		setNroCaja(result.getInt("nrocaja")); 
		setMontoMinimo(result.getDouble("montominimo"));
		setMontoMaximo(result.getDouble("montomaximo"));
		setCajaAbierta(result.getBoolean("cajaabierta"));
		setMontoEfectivo(result.getDouble("montoefectivo"));
		setMontoCheques(result.getInt("montocheques"));
		setIdResponsable(result.getInt("idresponsable"));
		setUbicacion(result.getString("ubicacion"));
		setIp(result.getString("ip"));
		setMac(result.getString("mac"));
	    }
	} catch (SQLException e) {
	    // TODO
	    e.printStackTrace();
	} catch (NullPointerException e) {
	    // TODO
	}
    }


    
}
