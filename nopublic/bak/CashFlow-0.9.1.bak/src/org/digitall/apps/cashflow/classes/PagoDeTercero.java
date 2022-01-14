package org.digitall.apps.cashflow.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class PagoDeTercero {

    private int idpagodetercero = -1;
    private int idtipopago = -1; 
    private String numero = "";
    private String banco = "";
    private String nombre = "";
    private String fechacobro = "";
    private double monto = 0.0;
    private boolean cobrado = false;
    private boolean rebotado = false;
    private String observaciones = "";
    private String estado = "";
    private String fecharecepcion = "";

    public PagoDeTercero() {
    
    }

    public void setIdpagodetercero(int idpagodetercero) {
	this.idpagodetercero = idpagodetercero;
    }

    public int getIdpagodetercero() {
	return idpagodetercero;
    }

    public void setIdtipopago(int idtipopago) {
	this.idtipopago = idtipopago;
    }

    public int getIdtipopago() {
	return idtipopago;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public String getNumero() {
	return numero;
    }

    public void setBanco(String banco) {
	this.banco = banco;
    }

    public String getBanco() {
	return banco;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void setFechacobro(String fechacobro) {
	this.fechacobro = fechacobro;
    }

    public String getFechacobro() {
	return fechacobro;
    }

    public void setMonto(double monto) {
	this.monto = monto;
    }

    public double getMonto() {
	return monto;
    }

    public void setCobrado(boolean cobrado) {
	this.cobrado = cobrado;
    }

    public boolean isCobrado() {
	return cobrado;
    }

    public void setRebotado(boolean rebotado) {
	this.rebotado = rebotado;
    }

    public boolean isRebotado() {
	return rebotado;
    }

    public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
    }

    public String getObservaciones() {
	return observaciones;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setFecharecepcion(String fecharecepcion) {
	this.fecharecepcion = fecharecepcion;
    }

    public String getFecharecepcion() {
	return fecharecepcion;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("cashflow.getPagoDeTercero", idpagodetercero);
	try {
	    if (result.next()) {
		 setIdpagodetercero(result.getInt("idpagodetercero"));
		 setIdtipopago(result.getInt("idtipopago"));
		 setNumero(result.getString("numero"));
		 setBanco(result.getString("banco"));
		 setNombre(result.getString("nombre"));
		 setFechacobro(result.getString("fechacobro"));
		 setMonto(result.getDouble("monto"));
		 setCobrado(result.getBoolean("cobrado"));
		 setRebotado(result.getBoolean("rebotado"));
		 setObservaciones(result.getString("observaciones"));
		 setEstado(result.getString("estado"));
		 setFecharecepcion(result.getString("fecharecepcion"));
	    } 
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }   
}
