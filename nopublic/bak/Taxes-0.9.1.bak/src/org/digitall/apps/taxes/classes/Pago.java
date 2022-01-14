package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

import org.digitall.lib.sql.LibSQL;

public class Pago {

    private int idPago = -1;
    private int idBien = -1;
    private int anio = -1;
    private int idTipoImpuesto = -1;
    private int idTipoCuota = -1;
    private double valorReal = 0.0;
    private Date fechaVto = null;
    private String fechaPago = "";
    private double monto = 0.0;		// --> Débito: debitAmount
    private double interes = 0.0;
    private double descuento = 0.0;	// --> Descuento
    private double valorfinal = 0.0;	// --> Crédito: CreditAmount
    private boolean anual = false;
    private String concepto = "";
    private int idaccountigentry = -1;
    
    public Pago() {

    }

    public void setIdPago(int idPago) {
	this.idPago = idPago;
    }

    public int getIdPago() {
	return idPago;
    }

    public void setIdBien(int idBien) {
	this.idBien = idBien;
    }

    public int getIdBien() {
	return idBien;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setIdTipoImpuesto(int idTipoImpuesto) {
	this.idTipoImpuesto = idTipoImpuesto;
    }

    public int getIdTipoImpuesto() {
	return idTipoImpuesto;
    }

    public void setIdTipoCuota(int idTipoCuota) {
	this.idTipoCuota = idTipoCuota;
    }

    public int getIdTipoCuota() {
	return idTipoCuota;
    }

    public void setValorReal(double valorReal) {
	this.valorReal = valorReal;
    }

    public double getValorReal() {
	return valorReal;
    }

    public void setMonto(double monto) {
	this.monto = monto;
    }

    public double getMonto() {
	return monto;
    }

    public void setInteres(double interes) {
	this.interes = interes;
    }

    public double getInteres() {
	return interes;
    }

    public void setDescuento(double descuento) {
	this.descuento = descuento;
    }

    public double getDescuento() {
	return descuento;
    }

    public int saveData(){
	int result = -1;
	String params = idBien +","+ anio +","+ idTipoImpuesto +","+ idTipoCuota +","+ valorReal +",'"+ fechaVto 
			+"','"+ fechaPago +"',"+ monto +","+ interes +","+ descuento +",'"+ anual +"'," + valorfinal 
			+ "," + idaccountigentry;
	if (idPago == -1){
	    result = LibSQL.getInt("impuestos.addPago", params);
	    idPago = result;
	} else {
	    
	}
	return result;
    }

    public void setFechaVto(Date fechaVto) {
	this.fechaVto = fechaVto;
    }

    public Date getFechaVto() {
	return fechaVto;
    }

    public void setFechaPago(String fechaPago) {
	this.fechaPago = fechaPago;
    }

    public String getFechaPago() {
	return fechaPago;
    }

    public void setAnual(boolean anual) {
	this.anual = anual;
    }

    public boolean isAnual() {
	return anual;
    }

    public void setValorFinal(double _valor) {
	valorfinal = _valor;
    }

    public void setConcepto(String concepto) {
	this.concepto = concepto;
    }

    public String getConcepto() {
	return concepto;
    }

    public void setIDBookKeepingEntry(int idaccountigentry) {
	this.idaccountigentry = idaccountigentry;
    }

    public int getIdaccountigentry() {
	return idaccountigentry;
    }

}
