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
 * CurrentPaymentWay.java
 *
 * */
package org.digitall.apps.taxes.classes;

import org.digitall.lib.sql.LibSQL;

public class CurrentPaymentWay {

    private int idcurrentpaymentway = -1;
    private String username = "";
    private int idtipopago = -1;
    private String numero = "";
    private String banco  = "";
    private String nombre  = "";
    private String fechacobro  = "";
    private double monto = 0.0;

    public CurrentPaymentWay() {
    }

    public void setIdcurrentpaymentway(int idcurrentpaymentway) {
	this.idcurrentpaymentway = idcurrentpaymentway;
    }

    public int getIdcurrentpaymentway() {
	return idcurrentpaymentway;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getUsername() {
	return username;
    }

    public void setIdtipopago(int idtipopago) {
	this.idtipopago = idtipopago;
    }

    public int getIdtipopago() {
	return idtipopago;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public String getNumero() {
	return numero;
    }

    public void setBanco(String banco) {
	this.banco = banco;
    }

    public String getBanco() {
	return banco;
    }

    public void setFechacobro(String fechacobro) {
	this.fechacobro = fechacobro;
    }

    public String getFechacobro() {
	return fechacobro;
    }

    public void setMonto(double monto) {
	this.monto = monto;
    }

    public double getMonto() {
	return monto;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public int saveData(){
	String params = "";
	int result = -1;
	params = idtipopago +","+ monto +",'"+ numero + "','" + banco +"','"+ nombre +"','"+ fechacobro +"'";
	if (idcurrentpaymentway == -1)  {
	    result = LibSQL.getInt("cashier.addCurrentPaymentWay",params);
	    idcurrentpaymentway = result; 
	} else  {
	    params = idcurrentpaymentway +","+ params;
	    result = LibSQL.getInt("cashier.setCurrentPaymentWay",params); //falta crearla (verificar si es necesario)
	}
	return result;
    }
    
    public void retrieveData() {
    
    }
    
    
    public boolean deletePaid(){
	boolean result = false;
	if (idcurrentpaymentway != -1)  {
	    result = LibSQL.getBoolean("cashier.delCurrentPaymentWay",""+ idcurrentpaymentway);
	}
	return result;
    }

}
