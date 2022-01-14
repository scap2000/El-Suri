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
 * Pago.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

import org.digitall.lib.sql.LibSQL;

public class Pago {

    private int idPago = -1;
    private int idBien = -1;
    private int anio = -1;
    private int idTipoImpuesto = -1;
    private int idTipoCuota = -1;
    private double valorReal = 0.0;
    private Date fechaVto = null;
    private String fechaPago = "";
    private double monto = 0.0;		// --> Débito: debitAmount
    private double interes = 0.0;
    private double descuento = 0.0;	// --> Descuento
    private double valorfinal = 0.0;	// --> Crédito: CreditAmount
    private boolean anual = false;
    private String concepto = "";
    private int idaccountigentry = -1;
    
    public Pago() {

    }

    public void setIdPago(int idPago) {
	this.idPago = idPago;
    }

    public int getIdPago() {
	return idPago;
    }

    public void setIdBien(int idBien) {
	this.idBien = idBien;
    }

    public int getIdBien() {
	return idBien;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setIdTipoImpuesto(int idTipoImpuesto) {
	this.idTipoImpuesto = idTipoImpuesto;
    }

    public int getIdTipoImpuesto() {
	return idTipoImpuesto;
    }

    public void setIdTipoCuota(int idTipoCuota) {
	this.idTipoCuota = idTipoCuota;
    }

    public int getIdTipoCuota() {
	return idTipoCuota;
    }

    public void setValorReal(double valorReal) {
	this.valorReal = valorReal;
    }

    public double getValorReal() {
	return valorReal;
    }

    public void setMonto(double monto) {
	this.monto = monto;
    }

    public double getMonto() {
	return monto;
    }

    public void setInteres(double interes) {
	this.interes = interes;
    }

    public double getInteres() {
	return interes;
    }

    public void setDescuento(double descuento) {
	this.descuento = descuento;
    }

    public double getDescuento() {
	return descuento;
    }

    public int saveData(){
	int result = -1;
	String params = idBien +","+ anio +","+ idTipoImpuesto +","+ idTipoCuota +","+ valorReal +",'"+ fechaVto 
			+"','"+ fechaPago +"',"+ monto +","+ interes +","+ descuento +",'"+ anual +"'," + valorfinal 
			+ "," + idaccountigentry;
	if (idPago == -1){
	    result = LibSQL.getInt("impuestos.addPago", params);
	    idPago = result;
	} else {
	    
	}
	return result;
    }

    public void setFechaVto(Date fechaVto) {
	this.fechaVto = fechaVto;
    }

    public Date getFechaVto() {
	return fechaVto;
    }

    public void setFechaPago(String fechaPago) {
	this.fechaPago = fechaPago;
    }

    public String getFechaPago() {
	return fechaPago;
    }

    public void setAnual(boolean anual) {
	this.anual = anual;
    }

    public boolean isAnual() {
	return anual;
    }

    public void setValorFinal(double _valor) {
	valorfinal = _valor;
    }

    public void setConcepto(String concepto) {
	this.concepto = concepto;
    }

    public String getConcepto() {
	return concepto;
    }

    public void setIDBookKeepingEntry(int idaccountigentry) {
	this.idaccountigentry = idaccountigentry;
    }

    public int getIdaccountigentry() {
	return idaccountigentry;
    }

}
