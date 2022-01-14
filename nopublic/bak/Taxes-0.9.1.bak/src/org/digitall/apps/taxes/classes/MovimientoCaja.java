package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class MovimientoCaja {

    private int idmovimientocaja = -1;
    private int idtipomovimiento = -1;
    private String usuario = "";
    private String fecha = "";
    private double monto1 = 0.0;
    private double monto2 = 0.0;
    private String estado = "";
    private int nroCaja = 0;
    private int idRef = 0;
    private int idCaja = 0;
    
    private String hora = "";
    private double recaudacion = 0.0;
    
    public MovimientoCaja() {
    
    }
    
    public MovimientoCaja(int _idTipoMovimiento) {
	this.idtipomovimiento = _idTipoMovimiento;
    }

    public void setIdmovimientocaja(int idmovimientocaja) {
	this.idmovimientocaja = idmovimientocaja;
    }

    public int getIdmovimientocaja() {
	return idmovimientocaja;
    }

    public void setIdtipomovimiento(int idtipomovimiento) {
	this.idtipomovimiento = idtipomovimiento;
    }

    public int getIdtipomovimiento() {
	return idtipomovimiento;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }

    public void setMonto1(double monto1) {
	this.monto1 = monto1;
    }

    public double getMonto1() {
	return monto1;
    }

    public void setMonto2(double monto2) {
	this.monto2 = monto2;
    }

    public double getMonto2() {
	return monto2;
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
	params = idtipomovimiento +"," + monto1 +","+ monto2 +","+ nroCaja +","+ idRef +","+ idCaja ;
	if (idmovimientocaja == -1)  {
	    result = LibSQL.getInt("cashier.addMovimientoCaja",params);
	    idmovimientocaja = result; 
	} else  {
	    params = idmovimientocaja +","+ params;
	    result = LibSQL.getInt("cashier.setMovimientoCaja",params); //falta crearla (verificar si es necesario)
	}
	return result;
    }

    public void setNroCaja(int nroCaja) {
	this.nroCaja = nroCaja;
    }

    public int getNroCaja() {
	return nroCaja;
    }

    public void setIdRef(int idRef) {
	this.idRef = idRef;
    }

    public int getIdRef() {
	return idRef;
    }
    
    public void setHora(String hora) {
	this.hora = hora;
    }

    public String getHora() {
	return hora;
    }

    public void setRecaudacion(double recaudacion) {
	this.recaudacion = recaudacion;
    }

    public double getRecaudacion() {
	return recaudacion;
    }

    public void setIdCaja(int _idCaja) {
	idCaja = _idCaja;
    }

    public int getIdCaja() {
	return idCaja;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("cashier.getMovimientoCaja", idtipomovimiento);
	try {
	    if (result.next()) {
		setIdmovimientocaja(result.getInt("idmovimientocaja"));
		setIdtipomovimiento(result.getInt("idtipomovimiento")); 
		setUsuario(result.getString("usuario"));
		setFecha(result.getString("fecha"));
		setMonto1(result.getDouble("monto1"));
		setMonto2(result.getDouble("monto2"));
		setNroCaja(result.getInt("nroCaja"));
		setIdRef(result.getInt("idref"));
	        setHora(result.getString("hora"));
	        setIdCaja(result.getInt("idcaja"));
	    }
	} catch (NullPointerException e) {
	    // TODO
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} 
    }

}
