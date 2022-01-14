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
 * BoletaPlanesDePago.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class BoletaPlanesDePago {

    private int idBoletaPlanDePago = -1;
    private int idPlanDePago = -1;
    private int idTipoImpuesto = 0;
    private int idAccountingEntry = -1;
    private String fechaPago = "";
    private String fechaImpresion = "";
    private String contribuyente = "";
    private String seccion = "";
    private String manzana = "";
    private String parcela = "";
    private String zona = "";
    private int catastro = -1;
    private String dominio = "";
    private String nroCuenta = "";
    private String concepto = "";
    private String vencimiento = "";
    private double importe = 0.0;
    private double recargo = 0.0;
    private double deducciones = 0.0;
    private String nombreDeduccion = "";
    private int idDeduccion = -1;
    private int idDescuento = -1;
    private String nombreDescuento = "";
    private double descuento = 0.0;
    private int idDescuentoEspecial = -1;
    private String nombreDescuentoEspecial = "";
    private double descuentoEspecial = 0.0;
    private double total = 0.0;
    private int idusuario = -1;
    private int nroImpresiones = -1;
    private String estado = "";
    private String localidad = "";
    private String domicilio = "";
    private String categoria = "";
    private String terreno = "";
    private double valedificacion = 0.0;
    private double valfiscal = 0.0;
    private String usuario = "";
    private String barcode = "";
    private String apoderado = "";
    private String informacion = "";
    private int anio = -1;
    private String numero = "";
    private int idBien = -1;
    private boolean pagada = false;
    private int cantCuotas = 0;
    private int idTipoPlanDePago = 0;
    
    private String dni = "";
    private String tipoautomotor = "";
    private String marca = "";
    private String nromotor = "";
    private int modelo = 0;
    
    private double porcentajeIntFin = 0.0;
    private double montoIntFin = 0.0;

    public BoletaPlanesDePago() {
    }

    public void setIdBoletaPlanDePago(int idBoletaPlanDePago) {
	this.idBoletaPlanDePago = idBoletaPlanDePago;
    }

    public int getIdBoletaPlanDePago() {
	return idBoletaPlanDePago;
    }

    public void setIdPlanDePago(int idPlanDePago) {
	this.idPlanDePago = idPlanDePago;
    }

    public int getIdPlanDePago() {
	return idPlanDePago;
    }

    public void setIdTipoImpuesto(int idTipoImpuesto) {
	this.idTipoImpuesto = idTipoImpuesto;
    }

    public int getIdTipoImpuesto() {
	return idTipoImpuesto;
    }

    public void setIdAccountingEntry(int idAccountingEntry) {
	this.idAccountingEntry = idAccountingEntry;
    }

    public int getIdAccountingEntry() {
	return idAccountingEntry;
    }

    public void setFechaPago(String fechaPago) {
	this.fechaPago = fechaPago;
    }

    public String getFechaPago() {
	return fechaPago;
    }

    public void setFechaImpresion(String fechaImpresion) {
	this.fechaImpresion = fechaImpresion;
    }

    public String getFechaImpresion() {
	return fechaImpresion;
    }

    public void setContribuyente(String contribuyente) {
	this.contribuyente = contribuyente;
    }

    public String getContribuyente() {
	return contribuyente;
    }

    public void setSeccion(String seccion) {
	this.seccion = seccion;
    }

    public String getSeccion() {
	return seccion;
    }

    public void setManzana(String manzana) {
	this.manzana = manzana;
    }

    public String getManzana() {
	return manzana;
    }

    public void setParcela(String parcela) {
	this.parcela = parcela;
    }

    public String getParcela() {
	return parcela;
    }

    public void setZona(String zona) {
	this.zona = zona;
    }

    public String getZona() {
	return zona;
    }

    public void setCatastro(int catastro) {
	this.catastro = catastro;
    }

    public int getCatastro() {
	return catastro;
    }

    public void setDominio(String dominio) {
	this.dominio = dominio;
    }

    public String getDominio() {
	return dominio;
    }

    public void setNroCuenta(String nroCuenta) {
	this.nroCuenta = nroCuenta;
    }

    public String getNroCuenta() {
	return nroCuenta;
    }

    public void setConcepto(String concepto) {
	this.concepto = concepto;
    }

    public String getConcepto() {
	return concepto;
    }

    public void setVencimiento(String vencimiento) {
	this.vencimiento = vencimiento;
    }

    public String getVencimiento() {
	return vencimiento;
    }

    public void setImporte(double importe) {
	this.importe = importe;
    }

    public double getImporte() {
	return importe;
    }

    public void setRecargo(double recargo) {
	this.recargo = recargo;
    }

    public double getRecargo() {
	return recargo;
    }

    public void setDeducciones(double deducciones) {
	this.deducciones = deducciones;
    }

    public double getDeducciones() {
	return deducciones;
    }

    public void setNombreDeduccion(String nombreDeduccion) {
	this.nombreDeduccion = nombreDeduccion;
    }

    public String getNombreDeduccion() {
	return nombreDeduccion;
    }

    public void setIdDeduccion(int idDeduccion) {
	this.idDeduccion = idDeduccion;
    }

    public int getIdDeduccion() {
	return idDeduccion;
    }

    public void setIdDescuento(int idDescuento) {
	this.idDescuento = idDescuento;
    }

    public int getIdDescuento() {
	return idDescuento;
    }

    public void setNombreDescuento(String nombreDescuento) {
	this.nombreDescuento = nombreDescuento;
    }

    public String getNombreDescuento() {
	return nombreDescuento;
    }

    public void setDescuento(double descuento) {
	this.descuento = descuento;
    }

    public double getDescuento() {
	return descuento;
    }

    public void setIdDescuentoEspecial(int idDescuentoEspecial) {
	this.idDescuentoEspecial = idDescuentoEspecial;
    }

    public int getIdDescuentoEspecial() {
	return idDescuentoEspecial;
    }

    public void setNombreDescuentoEspecial(String nombreDescuentoEspecial) {
	this.nombreDescuentoEspecial = nombreDescuentoEspecial;
    }

    public String getNombreDescuentoEspecial() {
	return nombreDescuentoEspecial;
    }

    public void setDescuentoEspecial(double descuentoEspecial) {
	this.descuentoEspecial = descuentoEspecial;
    }

    public double getDescuentoEspecial() {
	return descuentoEspecial;
    }

    public void setTotal(double total) {
	this.total = total;
    }

    public double getTotal() {
	return total;
    }

    public void setIdusuario(int idusuario) {
	this.idusuario = idusuario;
    }

    public int getIdusuario() {
	return idusuario;
    }

    public void setNroImpresiones(int nroImpresiones) {
	this.nroImpresiones = nroImpresiones;
    }

    public int getNroImpresiones() {
	return nroImpresiones;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setLocalidad(String localidad) {
	this.localidad = localidad;
    }

    public String getLocalidad() {
	return localidad;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    public String getDomicilio() {
	return domicilio;
    }

    public void setCategoria(String categoria) {
	this.categoria = categoria;
    }

    public String getCategoria() {
	return categoria;
    }

    public void setTerreno(String terreno) {
	this.terreno = terreno;
    }

    public String getTerreno() {
	return terreno;
    }

    public void setValedificacion(double valedificacion) {
	this.valedificacion = valedificacion;
    }

    public double getValedificacion() {
	return valedificacion;
    }

    public void setValfiscal(double valfiscal) {
	this.valfiscal = valfiscal;
    }

    public double getValfiscal() {
	return valfiscal;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setBarcode(String barcode) {
	this.barcode = barcode;
    }

    public String getBarcode() {
	return barcode;
    }

    public void setApoderado(String apoderado) {
	this.apoderado = apoderado;
    }

    public String getApoderado() {
	return apoderado;
    }

    public void setInformacion(String informacion) {
	this.informacion = informacion;
    }

    public String getInformacion() {
	return informacion;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public String getNumero() {
	return numero;
    }

    public void setIdBien(int idBien) {
	this.idBien = idBien;
    }

    public int getIdBien() {
	return idBien;
    }

    public void setPagada(boolean pagada) {
	this.pagada = pagada;
    }

    public boolean isPagada() {
	return pagada;
    }
    
    public void setCantCuotas(int cantCuotas) {
	this.cantCuotas = cantCuotas;
    }

    public int getCantCuotas() {
	return cantCuotas;
    }
    
    public int saveData() {
	int result = -1;
	String params = + idPlanDePago +","+ idTipoImpuesto +",'"+ contribuyente +"','"+ seccion +"','"+ manzana +"','"+ parcela +"','"+ zona +"',"
			+ catastro +",'"+ dominio +"','"+ nroCuenta +"','"+ concepto +"',"+ importe +",'"+ localidad +"','"+ domicilio +"','"
			+ categoria +"','"+ terreno +"',"+ valedificacion +","+ valfiscal +",'"+ apoderado +"','"+ informacion +"',"
			+ idBien +","+ cantCuotas +",'"+ dni +"','"+ tipoautomotor +"','"+ marca +"','"+ nromotor +"',"+ modelo +","
			+ porcentajeIntFin +","+ montoIntFin;
	if (getIdBoletaPlanDePago() == -1) {
	    result = LibSQL.getInt("taxes.addBoletaPlanDePago", params);
	    setIdBoletaPlanDePago(result);
	}
	return result;
    }
    
    public int saveData2() {
	int result = -1;
	String params = + idPlanDePago +","+ idTipoImpuesto +",'"+ contribuyente +"','"+ seccion +"','"+ manzana +"','"+ parcela +"','"+ zona +"',"
			+ catastro +",'"+ dominio +"','"+ nroCuenta +"','"+ concepto +"',"+ importe +",'"+ localidad +"','"+ domicilio +"','"
			+ categoria +"','"+ terreno +"',"+ valedificacion +","+ valfiscal +",'"+ apoderado +"','"+ informacion +"',"
			+ idBien +","+ cantCuotas +",'"+ dni +"','"+ tipoautomotor +"','"+ marca +"','"+ nromotor +"',"+ modelo +","
			+ porcentajeIntFin +","+ montoIntFin;
	if (getIdBoletaPlanDePago() == -1) {
	    result = LibSQL.getInt("taxes.addBoletaPlanDePago2", params);
	    setIdBoletaPlanDePago(result);
	}
	return result;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("taxes.getBoletaPlanDePago", idBoletaPlanDePago);
	try {
	    if (result.next()) {
		idBoletaPlanDePago = result.getInt("idboletaplandepago");
		idPlanDePago = result.getInt("idplandepago");
		idTipoImpuesto = result.getInt("idtipoimpuesto");
		idAccountingEntry = result.getInt("idaccountingentry");
		fechaPago = result.getString("fechapago");
		fechaImpresion = result.getString("fechaimpresion");
		contribuyente = result.getString("contribuyente");
		seccion = result.getString("seccion");
		manzana = result.getString("manzana");
		parcela = result.getString("parcela");
		zona = result.getString("zona");
		catastro = result.getInt("catastro");
	        dominio = result.getString("dominio");
		nroCuenta = result.getString("nrocuenta");
		concepto = result.getString("concepto");
		vencimiento = result.getString("vencimiento");
		importe = result.getDouble("importe");
		recargo = result.getDouble("recargo");
		deducciones = result.getDouble("deducciones");
	        nombreDeduccion = result.getString("nombrededuccion");
	        idDeduccion = result.getInt("iddeduccion");
	        idDescuento = result.getInt("iddescuento");
	        nombreDescuento = result.getString("nombredescuento");
	        descuento = result.getDouble("descuento");
	        idDescuentoEspecial = result.getInt("iddescuentoespecial");
	        nombreDescuentoEspecial = result.getString("nombredescuentoespecial");
	        descuentoEspecial = result.getDouble("descuentoespecial");
	        total = result.getDouble("total");
		idusuario = result.getInt("idusuario");
		nroImpresiones = result.getInt("nroimpresiones");
		estado = result.getString("estado");
		localidad = result.getString("localidad");
		domicilio = result.getString("domicilio");
		categoria = result.getString("categoria");
		terreno = result.getString("terreno");
		valedificacion = result.getDouble("valedificacion");
		valfiscal = result.getDouble("valfiscal");
		usuario = result.getString("usuario");
		barcode = result.getString("barcode");
	        apoderado = result.getString("apoderado");
	        informacion = result.getString("informacion");
	        numero = result.getString("numero");
	        idBien = result.getInt("idbien");
	        pagada = result.getBoolean("pagada");
	        cantCuotas = result.getInt("cantcuotas");
		idTipoPlanDePago = result.getInt("idtipoplandepago");
		dni = result.getString("dni");
		tipoautomotor = result.getString("tipoautomotor");
	        marca = result.getString("marca");
		nromotor = result.getString("nromotor");
		modelo = result.getInt("modelo");
		anio = result.getInt("anio");
	    } 
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }

    public void setIdTipoPlanDePago(int idTipoPlanDePago) {
	this.idTipoPlanDePago = idTipoPlanDePago;
    }

    public int getIdTipoPlanDePago() {
	return idTipoPlanDePago;
    }

    public void setDni(String dni) {
	this.dni = dni;
    }

    public String getDni() {
	return dni;
    }

    public void setTipoautomotor(String tipoautomotor) {
	this.tipoautomotor = tipoautomotor;
    }

    public String getTipoautomotor() {
	return tipoautomotor;
    }

    public void setMarca(String marca) {
	this.marca = marca;
    }

    public String getMarca() {
	return marca;
    }

    public void setNromotor(String nromotor) {
	this.nromotor = nromotor;
    }

    public String getNromotor() {
	return nromotor;
    }

    public void setModelo(int modelo) {
	this.modelo = modelo;
    }

    public int getModelo() {
	return modelo;
    }

    public void setPorcentajeIntFin(double porcentajeIntFin) {
	this.porcentajeIntFin = porcentajeIntFin;
    }

    public double getPorcentajeIntFin() {
	return porcentajeIntFin;
    }

    public void setMontoIntFin(double montoIntFin) {
	this.montoIntFin = montoIntFin;
    }

    public double getMontoIntFin() {
	return montoIntFin;
    }
}
