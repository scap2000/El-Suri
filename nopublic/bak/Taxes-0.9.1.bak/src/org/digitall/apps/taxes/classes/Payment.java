package org.digitall.apps.taxes.classes;

import org.digitall.lib.sql.LibSQL;

public class Payment {

    private int idPago = -1;
    private int idBien = -1;
    private int anio = -1;
    private int idTipoImpuesto = -1;
    private int idTipoCuota = -1;
    private double valorReal = 0;
    private String fechaVto = "null";
    private String fechaPago = "null";
    private double monto = 0;
    private double interes = 0;
    private double descuento = 0;
    private int estado;
    private String bien = "";
    private boolean delBookKeepingEntry = false;

    public Payment() {

    }

    public int saveData(){
	//!!! DAR DE ALTA EL ASIENTO!!!
	int result = -1;
	String params = "";
	if (idPago == -1)  {
	    params = ""+ idBien +","+ anio +","+ idTipoImpuesto +","+ idTipoCuota +","+ valorReal +",'"+ 
			    fechaVto +"','"+ fechaPago +"'," + monto +"," + interes + ","+ descuento ;
	    result = LibSQL.getInt("impuestos.addPago", params);
	    idPago = result;
	} else {
	    params = ""+ idPago +"," + idBien +","+ anio +","+ idTipoImpuesto +","+ idTipoCuota +","+ valorReal +",'"+ 
	                     fechaVto +"','"+ fechaPago +"'," + monto +"," + interes + ","+ descuento ;
	    result = LibSQL.getInt("impuestos.setPago", params);
	}
	 return result;
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

    public void setFechaVto(String fechaVto) {
	this.fechaVto = fechaVto;
    }

    public String getFechaVto() {
	return fechaVto;
    }

    public void setFechaPago(String fechaPago) {
	this.fechaPago = fechaPago;
    }

    public String getFechaPago() {
	return fechaPago;
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

    public void setEstado(int estado) {
	this.estado = estado;
    }

    public int getEstado() {
	return estado;
    }

    public void setBien(String bien) {
	this.bien = bien;
    }

    public String getBien() {
	return bien;
    }

    public void setDelBookKeepingEntry(boolean _delBookKeepingEntry) {
	this.delBookKeepingEntry = _delBookKeepingEntry;
    }

    public boolean isDelBookKeepingEntry() {
	return delBookKeepingEntry;
    }

}
