package org.digitall.apps.taxes.classes;

import org.digitall.lib.sql.LibSQL;

public class CurrentPaymentWay {

    private int idcurrentpaymentway = -1;
    private String username = "";
    private int idtipopago = -1;
    private String numero = "";
    private String banco  = "";
    private String nombre  = "";
    private String fechacobro  = "";
    private double monto = 0.0;

    public CurrentPaymentWay() {
    }

    public void setIdcurrentpaymentway(int idcurrentpaymentway) {
	this.idcurrentpaymentway = idcurrentpaymentway;
    }

    public int getIdcurrentpaymentway() {
	return idcurrentpaymentway;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getUsername() {
	return username;
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

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public int saveData(){
	String params = "";
	int result = -1;
	params = idtipopago +","+ monto +",'"+ numero + "','" + banco +"','"+ nombre +"','"+ fechacobro +"'";
	if (idcurrentpaymentway == -1)  {
	    result = LibSQL.getInt("cashier.addCurrentPaymentWay",params);
	    idcurrentpaymentway = result; 
	} else  {
	    params = idcurrentpaymentway +","+ params;
	    result = LibSQL.getInt("cashier.setCurrentPaymentWay",params); //falta crearla (verificar si es necesario)
	}
	return result;
    }
    
    public void retrieveData() {
    
    }
    
    
    public boolean deletePaid(){
	boolean result = false;
	if (idcurrentpaymentway != -1)  {
	    result = LibSQL.getBoolean("cashier.delCurrentPaymentWay",""+ idcurrentpaymentway);
	}
	return result;
    }

}
