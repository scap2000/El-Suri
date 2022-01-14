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
 * Caja.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Caja {

    private int idCaja = -1;
    private int nroCaja = 0;
    private double montoMinimo = 0.0;
    private double montoMaximo = 0.0;
    private boolean cajaAbierta = false;
    private double montoEfectivo = 0.0;
    private double montoCheques = 0.0;
    private int idResponsable = -1;
    private String ubicacion = "";
    private String ip = "";
    private String mac = "";
    private String estado = "";

    public Caja() {
    
    }

    public Caja(int _idCaja) {
	idCaja = _idCaja;
    }

    public void setIdCaja(int idCaja) {
	this.idCaja = idCaja;
    }

    public int getIdCaja() {
	return idCaja;
    }

    public void setNroCaja(int nroCaja) {
	this.nroCaja = nroCaja;
    }

    public int getNroCaja() {
	return nroCaja;
    }

    public void setMontoMinimo(double montoMinimo) {
	this.montoMinimo = montoMinimo;
    }

    public double getMontoMinimo() {
	return montoMinimo;
    }

    public void setMontoMaximo(double montoMaximo) {
	this.montoMaximo = montoMaximo;
    }

    public double getMontoMaximo() {
	return montoMaximo;
    }

    public void setCajaAbierta(boolean cajaAbierta) {
	this.cajaAbierta = cajaAbierta;
    }

    public boolean isCajaAbierta() {
	return cajaAbierta;
    }

    public void setMontoEfectivo(double montoEfectivo) {
	this.montoEfectivo = montoEfectivo;
    }

    public double getMontoEfectivo() {
	return montoEfectivo;
    }

    public void setMontoCheques(double montoCheques) {
	this.montoCheques = montoCheques;
    }

    public double getMontoCheques() {
	return montoCheques;
    }

    public void setIdResponsable(int idResponsable) {
	this.idResponsable = idResponsable;
    }

    public int getIdResponsable() {
	return idResponsable;
    }

    public void setUbicacion(String ubicacion) {
	this.ubicacion = ubicacion;
    }

    public String getUbicacion() {
	return ubicacion;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }

    public String getIp() {
	return ip;
    }

    public void setMac(String mac) {
	this.mac = mac;
    }

    public String getMac() {
	return mac;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("cashier.getCaja", idCaja);
	try {
	    if (result.next()) {
		setIdCaja(result.getInt("idcaja"));
		setNroCaja(result.getInt("nrocaja")); 
		setMontoMinimo(result.getDouble("montominimo"));
		setMontoMaximo(result.getDouble("montomaximo"));
		setCajaAbierta(result.getBoolean("cajaabierta"));
		setMontoEfectivo(result.getDouble("montoefectivo"));
		setMontoCheques(result.getInt("montocheques"));
		setIdResponsable(result.getInt("idresponsable"));
		setUbicacion(result.getString("ubicacion"));
		setIp(result.getString("ip"));
		setMac(result.getString("mac"));
	    }
	} catch (SQLException e) {
	    // TODO
	    e.printStackTrace();
	} catch (NullPointerException e) {
	    // TODO
	}
    }


    
}
