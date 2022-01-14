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
 * Payment.java
 *
 * */
package org.digitall.common.cashier;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Payment {

    private int idpayment = -1;
    private String fecha = "";
    private double monto = 0.0;
    private int idaccountingentry = -1;
    private String estado = "";
    private String usuario = "";
    private double montoformapago = 0.0;
    private String barcode = "";
    
    private String nroOperacion = "";
    private String nombreUsuario = "";

    public Payment() {
    }

    public void setIdpayment(int idpayment) {
	this.idpayment = idpayment;
    }

    public int getIdpayment() {
	return idpayment;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }

    public void setMonto(double monto) {
	this.monto = monto;
    }

    public double getMonto() {
	return monto;
    }

    public void setIdaccountingentry(int idaccountingentry) {
	this.idaccountingentry = idaccountingentry;
    }

    public int getIdaccountingentry() {
	return idaccountingentry;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setMontoformapago(double montoformapago) {
	this.montoformapago = montoformapago;
    }

    public double getMontoformapago() {
	return montoformapago;
    }

    public void setBarcode(String barcode) {
	this.barcode = barcode;
    }

    public String getBarcode() {
	return barcode;
    }

    public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
	return nombreUsuario;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("cashier.getPagoDeCaja", idpayment);
	try {
	    if (result.next()) {
		 setIdpayment(result.getInt("idpayment"));
		 setFecha(result.getString("fecha"));
		 setMonto(result.getDouble("monto"));
		 setIdaccountingentry(result.getInt("idaccountingentry"));
		 setUsuario(result.getString("usuario"));
		 setMontoformapago(result.getDouble("montoformapago"));
		 setEstado(result.getString("estado"));
		 setBarcode(result.getString("barcode"));
		 
		 setNroOperacion(result.getString("numero"));
	    } 
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }

    public void setNroOperacion(String nroOperacion) {
	this.nroOperacion = nroOperacion;
    }

    public String getNroOperacion() {
	return nroOperacion;
    }
}
