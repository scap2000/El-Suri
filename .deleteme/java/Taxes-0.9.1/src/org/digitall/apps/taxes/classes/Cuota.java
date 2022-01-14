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
 * Cuota.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.util.Date;

import javax.swing.JCheckBox;

import org.digitall.lib.sql.LibSQL;

public class Cuota extends JCheckBox {

    private boolean vencida = false;
    private boolean pagada = false;
    private Date fecha = null;
    private Date fechaVenc = null;
    private double cuota = 0.0;
    private double monto = 0.0;
    private double interes = 0.0;
    private double descuento = 0.0;    
    //el total = monto + interes;
    private int idTipoCuota = -1;
    private String tipoCuota = "";
    private int idTipoImpuesto = -1;
    private int idBien = -1;
    private int anio = -1;
    private boolean eximida = false;
    private boolean baja = false;

    public Cuota() {
    }

    public double getTotal() {
	return monto + interes;
    }

    public void setVencida(boolean vencida) {
	this.vencida = vencida;
    }

    public boolean isVencida() {
	return vencida;
    }

    public void setFechaVenc(Date fechaVenc) {
	this.fechaVenc = fechaVenc;
    }

    public Date getFechaVenc() {
	return fechaVenc;
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

    public void setIdTipoCuota(int idTipoCuota) {
	this.idTipoCuota = idTipoCuota;
    }

    public int getIdTipoCuota() {
	return idTipoCuota;
    }

    public void setTipoCuota(String tipoCuota) {
	this.tipoCuota = tipoCuota;
    }

    public String getTipoCuota() {
	return tipoCuota;
    }

    public void setPagada(boolean pagada) {
	this.pagada = pagada;
    }

    public boolean isPagada() {
	return pagada;
    }

    public void fetchData(int _idBien) {
	//pide su data a la base de datos;
	idBien = _idBien;
	setData();
    }

    public void setFecha(Date fecha) {
	this.fecha = fecha;
    }

    public Date getFecha() {
	return fecha;
    }

    public void setAnio(int _anio) {
	anio = _anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setIdTipoImpuesto(int _idTipoImpuesto) {
	idTipoImpuesto = _idTipoImpuesto;
    }

    public int getIdTipoImpuesto() {
	return idTipoImpuesto;
    }

    public void setIdBien(int _idBien) {
	idBien = _idBien;
    }

    public int getIdBien() {
	return idBien;
    }

    public void setCuota(double _cuota) {
	cuota = _cuota;
    }

    public double getCuota() {
	return cuota;
    }

    private void setData() {
	monto = LibSQL.getDouble("impuestos.getFee", "" + idTipoCuota + "," + anio + "," + idTipoImpuesto + "," + idBien);
	calcularCuota();
	interes = calcularInteres();
	vencida = LibSQL.getBoolean("impuestos.getvencida",idTipoImpuesto + "," + idTipoCuota +","+ anio);
	fechaVenc = LibSQL.getDate("impuestos.getfechavto2", (idTipoCuota==0?1:idTipoCuota) + "," + anio  + "," + idTipoImpuesto);
    }

    public void calcularCuota() {
	 if (idTipoImpuesto == 1) {
	     cuota = LibSQL.getDouble("impuestos.getValorCuotaTGS", "" + anio + "," + idBien);
	 } else if (idTipoImpuesto == 2) {
	     cuota = LibSQL.getDouble("impuestos.getValorCuotaInm", "" + anio + "," + idBien);
	 } else {
	     cuota = LibSQL.getDouble("impuestos.getValorCuotaAut", "" + anio + "," + idBien);
	 }
    }


    public double calcularInteres() {
	double intereses = 0;
	intereses = (monto/cuota)-1.0;
	return intereses;
    }

    public void setEximida(boolean _eximida) {
	eximida = _eximida;
    }

    public boolean isEximida() {
	return eximida;
    }

    public void setBaja(boolean _baja) {
	baja = _baja;
    }

    public boolean isBaja() {
	return baja;
    }

    public void setDescuento(double descuento) {
	this.descuento = descuento;
    }

    public double getDescuento() {
	return descuento;
    }

}
