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
 * MovimientoCaja.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class MovimientoCaja {

    private int idmovimientocaja = -1;
    private int idtipomovimiento = -1;
    private String usuario = "";
    private String fecha = "";
    private double monto1 = 0.0;
    private double monto2 = 0.0;
    private String estado = "";
    private int nroCaja = 0;
    private int idRef = 0;
    private int idCaja = 0;
    
    private String hora = "";
    private double recaudacion = 0.0;
    
    public MovimientoCaja() {
    
    }
    
    public MovimientoCaja(int _idTipoMovimiento) {
	this.idtipomovimiento = _idTipoMovimiento;
    }

    public void setIdmovimientocaja(int idmovimientocaja) {
	this.idmovimientocaja = idmovimientocaja;
    }

    public int getIdmovimientocaja() {
	return idmovimientocaja;
    }

    public void setIdtipomovimiento(int idtipomovimiento) {
	this.idtipomovimiento = idtipomovimiento;
    }

    public int getIdtipomovimiento() {
	return idtipomovimiento;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }

    public void setMonto1(double monto1) {
	this.monto1 = monto1;
    }

    public double getMonto1() {
	return monto1;
    }

    public void setMonto2(double monto2) {
	this.monto2 = monto2;
    }

    public double getMonto2() {
	return monto2;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
    
    public int saveData(){ 
	String params = "";
	int result = -1;
	params = idtipomovimiento +"," + monto1 +","+ monto2 +","+ nroCaja +","+ idRef +","+ idCaja ;
	if (idmovimientocaja == -1)  {
	    result = LibSQL.getInt("cashier.addMovimientoCaja",params);
	    idmovimientocaja = result; 
	} else  {
	    params = idmovimientocaja +","+ params;
	    result = LibSQL.getInt("cashier.setMovimientoCaja",params); //falta crearla (verificar si es necesario)
	}
	return result;
    }

    public void setNroCaja(int nroCaja) {
	this.nroCaja = nroCaja;
    }

    public int getNroCaja() {
	return nroCaja;
    }

    public void setIdRef(int idRef) {
	this.idRef = idRef;
    }

    public int getIdRef() {
	return idRef;
    }
    
    public void setHora(String hora) {
	this.hora = hora;
    }

    public String getHora() {
	return hora;
    }

    public void setRecaudacion(double recaudacion) {
	this.recaudacion = recaudacion;
    }

    public double getRecaudacion() {
	return recaudacion;
    }

    public void setIdCaja(int _idCaja) {
	idCaja = _idCaja;
    }

    public int getIdCaja() {
	return idCaja;
    }
    
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("cashier.getMovimientoCaja", idtipomovimiento);
	try {
	    if (result.next()) {
		setIdmovimientocaja(result.getInt("idmovimientocaja"));
		setIdtipomovimiento(result.getInt("idtipomovimiento")); 
		setUsuario(result.getString("usuario"));
		setFecha(result.getString("fecha"));
		setMonto1(result.getDouble("monto1"));
		setMonto2(result.getDouble("monto2"));
		setNroCaja(result.getInt("nroCaja"));
		setIdRef(result.getInt("idref"));
	        setHora(result.getString("hora"));
	        setIdCaja(result.getInt("idcaja"));
	    }
	} catch (NullPointerException e) {
	    // TODO
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} 
    }

}
