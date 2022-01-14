/**
 LIMITACIÃN DE RESPONSABILIDAD - COPYRIGHT - [EspaÃ±ol]
 ================================================================================
 KamarDesktop - Entorno JAVA de Trabajo y Desarrollo para Taller de Servicios Kamar
 ================================================================================

 Copyright (C) 2011 Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 La copia y distribuciÃ³n de este archivo, con o sin modificaciones,
 estÃ¡n permitidas por cualquier medio sin cargo mientras se preserven
 el Aviso de Copyright y este mismo aviso.

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los tÃ©rminos de la Licencia PÃºblica General GNU publicada
 por la FundaciÃ³n para el Software Libre, ya sea la versiÃ³n 3
 de la Licencia, o (a su elecciÃ³n) cualquier versiÃ³n posterior.

 Este programa se distribuye con la esperanza de que sea Ãºtil, pero
 SIN GARANTÃA ALGUNA; ni siquiera la garantÃ­a implÃ­cita
 MERCANTIL o de APTITUD PARA UN PROPÃSITO DETERMINADO.
 Consulte los detalles de la Licencia PÃºblica General GNU para obtener
 una informaciÃ³n mÃ¡s detallada.

 DeberÃ­a haber recibido una copia de la Licencia PÃºblica General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
KamarDesktop - JAVA Management & Development Environment for Kamar Mechanical Services
 =====================================================================================

 Copyright (C) 2011 by Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 Copying and distribution of this file, with or without modification,
 are permitted in any medium without royalty provided the copyright
 notice and this notice are preserved.

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
 * Comprobante.java
 *
 * */
package org.digitall.projects.kamar.taller.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class Comprobante {

    private int idComprobante = -1;
    private int idProveedor = -1;
    private String  nombreProveedor = "";
    private String numero;
    private String fecha;
    private double total = 0;
    
    public Comprobante() {
    }
    
    public Comprobante(int _idComprobante) {
	idComprobante = _idComprobante;
    }
    
    public Comprobante(int _idComprobante, String _nombre, String _domicilio, double _total) {
	idComprobante = _idComprobante;
	numero = _nombre;
	fecha = _domicilio;
	total = _total;
    }

    public void setIdComprobante(int _idComprobante) {
	this.idComprobante = _idComprobante;
    }

    public int getIdComprobante() {
	return idComprobante;
    }

    public void loadData(int _idComprobante){
	idComprobante = _idComprobante;
	ResultSet data = LibSQL.exFunction("taller.getComprobante", idComprobante);
	try {
	    if (data.next()) {
	        idProveedor = data.getInt("idproveedor");
	        nombreProveedor = data.getString("nombre");
	        numero = data.getString("numero");
	        fecha = data.getString("fecha");
	        total = data.getDouble("total");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}	    
    }
    
    public int saveData(){
	if (!numero.trim().equals("")){
	    int result = LibSQL.getInt("taller.setComprobante", idComprobante + "," + idProveedor + ",'" + numero + "','" + fecha + "'");
	    if (idComprobante == -1){
		idComprobante = result;
		loadData(idComprobante);
	    }
	    return result;
	} else {
	    Advisor.messageBox("Debe ingresar un número para el comprobante", "Número no válido");
	    return -1;
	}
    }

    public void setNumero(String _nombre) {
	numero = _nombre;
    }

    public String getNumero() {
	return numero;
    }

    public void setFecha(String domicilio) {
	this.fecha = domicilio;
    }

    public String getFecha() {
	return fecha;
    }

    public void setTotal(double total) {
	this.total = total;
    }

    public double getTotal() {
	return total;
    }

    public void setIdProveedor(int idProveedor) {
	this.idProveedor = idProveedor;
    }

    public int getIdProveedor() {
	return idProveedor;
    }

    public void setNombreProveedor(String _nombreProveedor) {
	this.nombreProveedor = _nombreProveedor;
    }

    public String getNombreProveedor() {
	return nombreProveedor;
    }
}
