package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.lib.sql.LibSQL;

public class TipoImpuesto {

    private int idTipoImpuesto = -1;
    private String nombre;
    private int idCuentaDebe = -1;
    private int idCuentaHaber = -1;
    private int idCuentaDeduccion = -1;
    private int idCuentaInteres = -1;
    
    public TipoImpuesto() {

    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }
    
    public void setIdTipoImpuesto(int idTipoImpuesto) {
	this.idTipoImpuesto = idTipoImpuesto; 
    }

    public int getIdTipoImpuesto() {
	return idTipoImpuesto;
    }

    public void setIdCuentaDebe(int idCuentaDebe) {
	this.idCuentaDebe = idCuentaDebe;
    }

    public int getIdCuentaDebe() {
	return idCuentaDebe;
    }

    public void setIdCuentaHaber(int idCuentaHaber) {
	this.idCuentaHaber = idCuentaHaber;
    }

    public int getIdCuentaHaber() {
	return idCuentaHaber;
    }

    public void setIdCuentaDeduccion(int idCuentaDeduccion) {
	this.idCuentaDeduccion = idCuentaDeduccion;
    }

    public int getIdCuentaDeduccion() {
	return idCuentaDeduccion;
    }

    public void setIdCuentaInteres(int idCuentaInteres) {
	this.idCuentaInteres = idCuentaInteres;
    }

    public int getIdCuentaInteres() {
	return idCuentaInteres;
    }

    public void retrieveData(){
	try {
	    ResultSet rs = LibSQL.exFunction("taxes.getTipoImpuesto",""+ idTipoImpuesto);  // Falta desarrollarla
	    if (rs.next()){
		setNombre(rs.getString("nombre"));
	        setIdCuentaDebe(rs.getInt("idcuentadebe"));
	        setIdCuentaHaber(rs.getInt("idcuentahaber"));
	        setIdCuentaDeduccion(rs.getInt("idcuentadeduccion"));
	        setIdCuentaInteres(rs.getInt("idcuentainteres"));
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public int saveData(){
	String params = "";
	int result = -1;
	    params = idTipoImpuesto +",'" + nombre + "'," + idCuentaDebe + "," + idCuentaHaber + "," + idCuentaDeduccion + ","+ idCuentaInteres;
	    result = LibSQL.getInt("taxes.addTipoImpuesto",params);
	    idTipoImpuesto = result;
	return result;
    }

    public boolean deleteData(){
	String params = "";
	boolean result = false;
	    params = ""+ idTipoImpuesto ;
	    result = LibSQL.getBoolean("taxes.delTipoImpuesto",params);
	return result;
    }
    
}
