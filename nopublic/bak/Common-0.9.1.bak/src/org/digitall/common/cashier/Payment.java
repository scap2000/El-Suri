package org.digitall.common.cashier;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Payment {

    private int idpayment = -1;
    private String fecha = "";
    private double monto = 0.0;
    private int idaccountingentry = -1;
    private String estado = "";
    private String usuario = "";
    private double montoformapago = 0.0;
    private String barcode = "";
    
    private String nroOperacion = "";
    private String nombreUsuario = "";

    public Payment() {
    }

    public void setIdpayment(int idpayment) {
	this.idpayment = idpayment;
    }

    public int getIdpayment() {
	return idpayment;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }

    public void setMonto(double monto) {
	this.monto = monto;
    }

    public double getMonto() {
	return monto;
    }

    public void setIdaccountingentry(int idaccountingentry) {
	this.idaccountingentry = idaccountingentry;
    }

    public int getIdaccountingentry() {
	return idaccountingentry;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setMontoformapago(double montoformapago) {
	this.montoformapago = montoformapago;
    }

    public double getMontoformapago() {
	return montoformapago;
    }

    public void setBarcode(String barcode) {
	this.barcode = barcode;
    }

    public String getBarcode() {
	return barcode;
    }

    public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
	return nombreUsuario;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("cashier.getPagoDeCaja", idpayment);
	try {
	    if (result.next()) {
		 setIdpayment(result.getInt("idpayment"));
		 setFecha(result.getString("fecha"));
		 setMonto(result.getDouble("monto"));
		 setIdaccountingentry(result.getInt("idaccountingentry"));
		 setUsuario(result.getString("usuario"));
		 setMontoformapago(result.getDouble("montoformapago"));
		 setEstado(result.getString("estado"));
		 setBarcode(result.getString("barcode"));
		 
		 setNroOperacion(result.getString("numero"));
	    } 
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }

    public void setNroOperacion(String nroOperacion) {
	this.nroOperacion = nroOperacion;
    }

    public String getNroOperacion() {
	return nroOperacion;
    }
}
