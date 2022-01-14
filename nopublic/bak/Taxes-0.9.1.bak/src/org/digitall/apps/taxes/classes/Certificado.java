package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Certificado {

    private int idCertificado = -1;
    private int idAlicuotaContribucion = -1;
    private int anio = -1;
    private int numero = -1;
    private String titulo1 = "";
    private String titulo2 = "";
    private int idContribuyente = -1;
    private String contribuyente = "";
    private String dni = "";
    private String fechaEmision = "";
    private int idBien = -1;
    private String nroBien = "";
    private String tipoAutomotor = "";
    private String marca = "";
    private String modelo = "";
    private String categoria = "";    
    private int mesHasta = -1;
    private int anioHasta = -1;
    private String usuario = "";
    private String leyenda1 = "";
    private String leyenda2 = "";
    private String estado = "";
    private int idDetalleBoletaContribucion = -1;
    private String domicilio = "";

    private int dia = 1;
    

    public Certificado() {
    }

    public void setIdCertificado(int idCertificado) {
	this.idCertificado = idCertificado;
    }

    public int getIdCertificado() {
	return idCertificado;
    }

    public void setIdAlicuotaContribucion(int idAlicuotaContribucion) {
	this.idAlicuotaContribucion = idAlicuotaContribucion;
    }

    public int getIdAlicuotaContribucion() {
	return idAlicuotaContribucion;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setNumero(int numero) {
	this.numero = numero;
    }

    public int getNumero() {
	return numero;
    }

    public void setTitulo1(String titulo1) {
	this.titulo1 = titulo1;
    }

    public String getTitulo1() {
	return titulo1;
    }

    public void setTitulo2(String titulo2) {
	this.titulo2 = titulo2;
    }

    public String getTitulo2() {
	return titulo2;
    }

    public void setIdContribuyente(int idContribuyente) {
	this.idContribuyente = idContribuyente;
    }

    public int getIdContribuyente() {
	return idContribuyente;
    }

    public void setContribuyente(String contribuyente) {
	this.contribuyente = contribuyente;
    }

    public String getContribuyente() {
	return contribuyente;
    }

    public void setDni(String dni) {
	this.dni = dni;
    }

    public String getDni() {
	return dni;
    }

    public void setFechaEmision(String fechaEmision) {
	this.fechaEmision = fechaEmision;
    }

    public String getFechaEmision() {
	return fechaEmision;
    }

    public void setIdBien(int idBien) {
	this.idBien = idBien;
    }

    public int getIdBien() {
	return idBien;
    }

    public void setNroBien(String nroBien) {
	this.nroBien = nroBien;
    }

    public String getNroBien() {
	return nroBien;
    }

    public void setTipoAutomotor(String tipoAutomotor) {
	this.tipoAutomotor = tipoAutomotor;
    }

    public String getTipoAutomotor() {
	return tipoAutomotor;
    }

    public void setMarca(String marca) {
	this.marca = marca;
    }

    public String getMarca() {
	return marca;
    }

    public void setModelo(String modelo) {
	this.modelo = modelo;
    }

    public String getModelo() {
	return modelo;
    }

    public void setCategoria(String categoria) {
	this.categoria = categoria;
    }

    public String getCategoria() {
	return categoria;
    }

    public void setMesHasta(int mesHasta) {
	this.mesHasta = mesHasta;
    }

    public int getMesHasta() {
	return mesHasta;
    }

    public void setAnioHasta(int anioHasta) {
	this.anioHasta = anioHasta;
    }

    public int getAnioHasta() {
	return anioHasta;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setLeyenda1(String leyenda1) {
	this.leyenda1 = leyenda1;
    }

    public String getLeyenda1() {
	return leyenda1;
    }

    public void setLeyenda2(String leyenda2) {
	this.leyenda2 = leyenda2;
    }

    public String getLeyenda2() {
	return leyenda2;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setIdDetalleBoletaContribucion(int idDetalleBoletaContribucion) {
	this.idDetalleBoletaContribucion = idDetalleBoletaContribucion;
    }

    public int getIdDetalleBoletaContribucion() {
	return idDetalleBoletaContribucion;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("taxes.getCertificado",""+ idCertificado);
	try  {
	    if (result.next())  {
	        setIdAlicuotaContribucion(result.getInt("idalicuotacontribucion"));
	        setAnio(result.getInt("anio"));
	        setNumero(result.getInt("numero"));
	        setTitulo1(result.getString("titulo"));
	        setTitulo2(result.getString("titulo2"));
	        setIdContribuyente(result.getInt("idcontribuyente"));
	        setContribuyente(result.getString("contribuyente"));
	        setDni(result.getString("dni"));
	        setFechaEmision(result.getString("fechaemision"));
	        setIdBien(result.getInt("idbien"));
	        setNroBien(result.getString("nrobien"));
	        setTipoAutomotor(result.getString("tipoautomotor"));
	        setMarca(result.getString("marca"));
	        setModelo(result.getString("modelo"));
	        setCategoria(result.getString("categoria"));
	        setMesHasta(result.getInt("meshasta"));
	        setAnioHasta(result.getInt("aniohasta"));
	        setUsuario(result.getString("usuario"));
	        setLeyenda1(result.getString("leyenda1"));
	        setLeyenda2(result.getString("leyenda2"));
	        setEstado(result.getString("estado"));
	        setIdDetalleBoletaContribucion(result.getInt("iddetalleboletacontribucion"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	}
    }

    public void retrieveDataByIdDetalleBoletaContribucion() {
	ResultSet result = LibSQL.exFunction("taxes.getCertificadoByIdDetalleBoletaContribucion",""+ idDetalleBoletaContribucion);
	try  {
	    if (result.next())  {
		setIdCertificado(result.getInt("idcertificado"));
		setIdAlicuotaContribucion(result.getInt("idalicuotacontribucion"));
		setAnio(result.getInt("anio"));
		setNumero(result.getInt("numero"));
		setTitulo1(result.getString("titulo1"));
		setTitulo2(result.getString("titulo2"));
		setIdContribuyente(result.getInt("idcontribuyente"));
		setContribuyente(result.getString("contribuyente"));
		setDni(result.getString("dni"));
		setFechaEmision(result.getString("fechaemision"));
		setIdBien(result.getInt("idbien"));
		setNroBien(result.getString("nrobien"));
		setTipoAutomotor(result.getString("tipoautomotor"));
		setMarca(result.getString("marca"));
		setModelo(result.getString("modelo"));
		setCategoria(result.getString("categoria"));
		setMesHasta(result.getInt("meshasta"));
		setAnioHasta(result.getInt("aniohasta"));
		setUsuario(result.getString("usuario"));
		setLeyenda1(result.getString("leyenda1"));
		setLeyenda2(result.getString("leyenda2"));
		setEstado(result.getString("estado"));
		setIdDetalleBoletaContribucion(result.getInt("iddetalleboletacontribucion"));
	        setDia(result.getInt("dia"));
		setDomicilio(result.getString("domicilio"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	}
	
    }

    public void setDia(int dia) {
	this.dia = dia;
    }

    public int getDia() {
	return dia;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    public String getDomicilio() {
	return domicilio;
    }
}
