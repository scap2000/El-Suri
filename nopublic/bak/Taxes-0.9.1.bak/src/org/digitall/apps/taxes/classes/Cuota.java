package org.digitall.apps.taxes.classes;

import java.util.Date;

import javax.swing.JCheckBox;

import org.digitall.lib.sql.LibSQL;

public class Cuota extends JCheckBox {

    private boolean vencida = false;
    private boolean pagada = false;
    private Date fecha = null;
    private Date fechaVenc = null;
    private double cuota = 0.0;
    private double monto = 0.0;
    private double interes = 0.0;
    private double descuento = 0.0;    
    //el total = monto + interes;
    private int idTipoCuota = -1;
    private String tipoCuota = "";
    private int idTipoImpuesto = -1;
    private int idBien = -1;
    private int anio = -1;
    private boolean eximida = false;
    private boolean baja = false;

    public Cuota() {
    }

    public double getTotal() {
	return monto + interes;
    }

    public void setVencida(boolean vencida) {
	this.vencida = vencida;
    }

    public boolean isVencida() {
	return vencida;
    }

    public void setFechaVenc(Date fechaVenc) {
	this.fechaVenc = fechaVenc;
    }

    public Date getFechaVenc() {
	return fechaVenc;
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

    public void setIdTipoCuota(int idTipoCuota) {
	this.idTipoCuota = idTipoCuota;
    }

    public int getIdTipoCuota() {
	return idTipoCuota;
    }

    public void setTipoCuota(String tipoCuota) {
	this.tipoCuota = tipoCuota;
    }

    public String getTipoCuota() {
	return tipoCuota;
    }

    public void setPagada(boolean pagada) {
	this.pagada = pagada;
    }

    public boolean isPagada() {
	return pagada;
    }

    public void fetchData(int _idBien) {
	//pide su data a la base de datos;
	idBien = _idBien;
	setData();
    }

    public void setFecha(Date fecha) {
	this.fecha = fecha;
    }

    public Date getFecha() {
	return fecha;
    }

    public void setAnio(int _anio) {
	anio = _anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setIdTipoImpuesto(int _idTipoImpuesto) {
	idTipoImpuesto = _idTipoImpuesto;
    }

    public int getIdTipoImpuesto() {
	return idTipoImpuesto;
    }

    public void setIdBien(int _idBien) {
	idBien = _idBien;
    }

    public int getIdBien() {
	return idBien;
    }

    public void setCuota(double _cuota) {
	cuota = _cuota;
    }

    public double getCuota() {
	return cuota;
    }

    private void setData() {
	monto = LibSQL.getDouble("impuestos.getFee", "" + idTipoCuota + "," + anio + "," + idTipoImpuesto + "," + idBien);
	calcularCuota();
	interes = calcularInteres();
	vencida = LibSQL.getBoolean("impuestos.getvencida",idTipoImpuesto + "," + idTipoCuota +","+ anio);
	fechaVenc = LibSQL.getDate("impuestos.getfechavto2", (idTipoCuota==0?1:idTipoCuota) + "," + anio  + "," + idTipoImpuesto);
    }

    public void calcularCuota() {
	 if (idTipoImpuesto == 1) {
	     cuota = LibSQL.getDouble("impuestos.getValorCuotaTGS", "" + anio + "," + idBien);
	 } else if (idTipoImpuesto == 2) {
	     cuota = LibSQL.getDouble("impuestos.getValorCuotaInm", "" + anio + "," + idBien);
	 } else {
	     cuota = LibSQL.getDouble("impuestos.getValorCuotaAut", "" + anio + "," + idBien);
	 }
    }


    public double calcularInteres() {
	double intereses = 0;
	intereses = (monto/cuota)-1.0;
	return intereses;
    }

    public void setEximida(boolean _eximida) {
	eximida = _eximida;
    }

    public boolean isEximida() {
	return eximida;
    }

    public void setBaja(boolean _baja) {
	baja = _baja;
    }

    public boolean isBaja() {
	return baja;
    }

    public void setDescuento(double descuento) {
	this.descuento = descuento;
    }

    public double getDescuento() {
	return descuento;
    }

}
