/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * Certificado.java
 *
 * */
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
