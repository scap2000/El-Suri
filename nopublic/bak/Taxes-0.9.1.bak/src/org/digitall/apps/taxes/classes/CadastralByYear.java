package org.digitall.apps.taxes.classes;

import org.digitall.lib.sql.LibSQL;

public class CadastralByYear {

    private int idCatastroxanio = -1;
    private int idCatastro = -1; 
    private String fecha = "null";
    private double vf = 0;
    private double mf  = 0;
    private double tgs = -1;
    private double impinm = -1;
    private double tasaanual = -1;  
    private double tasadiaria = -1;
    private int nrocuenta = -1;
    private String estado = "";
    private String titular = "";
    private int catastro = -1;

    public CadastralByYear() {

    }

    public int saveData(){
	int result = -1;
	String params = "";
	if (idCatastroxanio == -1)  {
	    params = ""+ catastro +",'"+ fecha +"',"+ vf +","+ mf +","+ tgs +","+ 
			    impinm +","+ tasaanual +"," + tasadiaria +"," + nrocuenta;
	    result = LibSQL.getInt("impuestos.addCatastrosxAnio", params);
	    idCatastroxanio = result;
	} else {
	    params = ""+ idCatastroxanio + "," + idCatastro +",'"+ fecha +"',"+ vf +","+ mf ;
	    result = LibSQL.getInt("impuestos.setCatastrosxAnio", params);
	}
	 return result;
    }


    public void setIdCatastroxanio(int idCatastroxanio) {
	this.idCatastroxanio = idCatastroxanio;
    }

    public int getIdCatastroxanio() {
	return idCatastroxanio;
    }

    public void setIdCatastro(int idCatastro) {
	this.idCatastro = idCatastro;
    }

    public int getIdCatastro() {
	return idCatastro;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }

    public void setVf(double vf) {
	this.vf = vf;
    }

    public double getVf() {
	return vf;
    }

    public void setMf(double mf) {
	this.mf = mf;
    }

    public double getMf() {
	return mf;
    }

    public void setTgs(double tgs) {
	this.tgs = tgs;
    }

    public double getTgs() {
	return tgs;
    }

    public void setImpinm(double impinm) {
	this.impinm = impinm;
    }

    public double getImpinm() {
	return impinm;
    }

    public void setTasaanual(double tasaanual) {
	this.tasaanual = tasaanual;
    }

    public double getTasaanual() {
	return tasaanual;
    }

    public void setTasadiaria(double tasadiaria) {
	this.tasadiaria = tasadiaria;
    }

    public double getTasadiaria() {
	return tasadiaria;
    }

    public void setNrocuenta(int nrocuenta) {
	this.nrocuenta = nrocuenta;
    }

    public int getNrocuenta() {
	return nrocuenta;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setTitular(String titular) {
	this.titular = titular;
    }

    public String getTitular() {
	return titular;
    }

    public void setCatastro(int catastro) {
	this.catastro = catastro;
    }

    public int getCatastro() {
	return catastro;
    }

}
