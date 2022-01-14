package org.digitall.apps.sueldos.classes;

import java.awt.image.BufferedImage;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.image.LibIMG;
import org.digitall.lib.sql.LibSQL;

public class Credencial {

    private int idCredencial = -1;
    private int idTipoCredencial = -1;
    private int idPerson = -1;
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * "fechaIni" debería llamarse "fechaInicio" y sus accesores también
     * */
    private String fechaIni = "";
    private String fechaFin = "";
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * En la base de datos, la columna barcode permite valores nulos y no debería
     * */
    private String barcode = "";
    private String apellido = "";
    private String nombres = "";
    private String dni = "";
    private String cargo = "";
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * "nrolegajo" debería llamarse "numeroLegajo" o "nroLegajo" y sus accesores también
     * */
    private int nrolegajo = 0;
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * ¿Qué es "leyenda1"?
     * */
    private String leyenda1 = "";
    private String dependencia = "";
    
    private BufferedImage photoImage = null;
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * "photoBarCode" debería llamarse "barcodeImage" y sus accesores también
     * En la Base de Datos se llama barcodeimage...
     * Aunque no sé por qué se está guardando la imagen, debería generarse con el sistema
     * la librería la genera automáticamente y no haría falta guardarla en la tabla
     * Supongo que es para poder generar muchas credenciales en un solo reporte
     * */
     
    private BufferedImage photoBarCode = null;
    private BufferedImage logoImage = null;

    public Credencial() {
    }

    public void setIdCredencial(int idCredencial) {
	this.idCredencial = idCredencial;
    }

    public int getIdCredencial() {
	return idCredencial;
    }

    public void setIdTipoCredencial(int idTipoCredencial) {
	this.idTipoCredencial = idTipoCredencial;
    }

    public int getIdTipoCredencial() {
	return idTipoCredencial;
    }

    public void setIdPerson(int idPerson) {
	this.idPerson = idPerson;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setFechaIni(String fechaIni) {
	this.fechaIni = fechaIni;
    }

    public String getFechaIni() {
	return fechaIni;
    }

    public void setFechaFin(String fechaFin) {
	this.fechaFin = fechaFin;
    }

    public String getFechaFin() {
	return fechaFin;
    }

    public void setBarcode(String barcode) {
	this.barcode = barcode;
    }

    public String getBarcode() {
	return barcode;
    }

    public void setApellido(String apellido) {
	this.apellido = apellido;
    }

    public String getApellido() {
	return apellido;
    }

    public void setNombres(String nombres) {
	this.nombres = nombres;
    }

    public String getNombres() {
	return nombres;
    }

    public void setDni(String dni) {
	this.dni = dni;
    }

    public String getDni() {
	return dni;
    }

    public void setCargo(String cargo) {
	this.cargo = cargo;
    }

    public String getCargo() {
	return cargo;
    }

    public void setNrolegajo(int nrolegajo) {
	this.nrolegajo = nrolegajo;
    }

    public int getNrolegajo() {
	return nrolegajo;
    }

    public void setLeyenda1(String leyenda1) {
	this.leyenda1 = leyenda1;
    }

    public String getLeyenda1() {
	return leyenda1;
    }

    public void setDependencia(String dependencia) {
	this.dependencia = dependencia;
    }

    public String getDependencia() {
	return dependencia;
    }

    public void setPhotoImage(BufferedImage photoImage) {
	this.photoImage = photoImage;
    }

    public BufferedImage getPhotoImage() {
	return photoImage;
    }

    public void setLogoImage(BufferedImage logoImage) {
	this.logoImage = logoImage;
    }

    public BufferedImage getLogoImage() {
	return logoImage;
    }
    
    public int saveData(){
	int result = -1;
	String params = "";
	if (idCredencial == -1)  {
	    params = idTipoCredencial +","+ idPerson +",'"+ fechaIni +"','"+ fechaFin +"','"+ 
	                  apellido +"','" + nombres +"','" + dni + "','"+ cargo +"',"+ 
	                  nrolegajo +",'"+ leyenda1 +"','"+ dependencia + "'";
	    result = LibSQL.getInt("credenciales.addCredencial", params);
	    idCredencial = result;
	} else {
	    params = ""+ idCredencial +","+ idTipoCredencial +","+ idPerson +",'"+ fechaIni +"','"+ fechaFin +"','"+ 
	                  apellido +"','" + nombres +"','" + dni + "','"+ cargo +"',"+ 
	                  nrolegajo +",'"+ leyenda1 +"','"+ dependencia + "'";
	    result = LibSQL.getInt("credenciales.setCredencial", params);
	}
	idCredencial = result;
	barcode = LibSQL.getString("credenciales.getBarCode",""+idCredencial);
	return result;
    }
    
    public int saveCodePicture(){
	int result = -1;
	if (idCredencial != -1 && photoBarCode != null) {
	    if (!LibIMG.saveImage(photoBarCode, "credenciales.credenciales", "barcodeimage", "WHERE idcredencial = " + idCredencial)) {
		result = -1;
	    }
	}
	return result;
    }
    
    
    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("credenciales.getCredencial","" + idCredencial);
	try  {
	    if (data.next()) {
		setIdCredencial(data.getInt("idcredencial"));
		setIdTipoCredencial(data.getInt("idtipocredencial"));
		setIdPerson(data.getInt("idperson"));
		setFechaIni(data.getString("fechaini"));
		setFechaFin(data.getString("fechafin"));
		setBarcode(data.getString("barcode"));
		setApellido(data.getString("apellido"));
		setNombres(data.getString("nombres"));
		setDni(data.getString("dni"));
		setCargo(data.getString("cargo"));
		setNrolegajo(data.getInt("nrolegajo"));
		setLeyenda1(data.getString("leyenda1"));
		setDependencia(data.getString("dependencia"));
	    }
	} catch (SQLException ex)  {
	    ex.printStackTrace();
	} 
    }


    public void retrievePicture() {
	/* 2009-09-17 (santiago) Mensaje para el codificador
	 * Error, se está llamando a dos Stored Proc. que modifican el mismo atributo "photoImage"
	 * */

	photoImage = LibIMG.getImage("org.persons", "photo", "WHERE idperson = " + idPerson);
	photoImage = LibIMG.getImage("credenciales.credenciales", "codephoto", "WHERE idcredencial = " + idCredencial);
    }

    public void setPhotoBarCode(BufferedImage photoBarCode) {
	this.photoBarCode = photoBarCode;
    }

    public BufferedImage getPhotoBarCode() {
	return photoBarCode;
    }
}
