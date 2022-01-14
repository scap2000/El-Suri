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
 * TipoImpuesto.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.lib.sql.LibSQL;

public class TipoImpuesto {

    private int idTipoImpuesto = -1;
    private String nombre;
    private int idCuentaDebe = -1;
    private int idCuentaHaber = -1;
    private int idCuentaDeduccion = -1;
    private int idCuentaInteres = -1;
    
    public TipoImpuesto() {

    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }
    
    public void setIdTipoImpuesto(int idTipoImpuesto) {
	this.idTipoImpuesto = idTipoImpuesto; 
    }

    public int getIdTipoImpuesto() {
	return idTipoImpuesto;
    }

    public void setIdCuentaDebe(int idCuentaDebe) {
	this.idCuentaDebe = idCuentaDebe;
    }

    public int getIdCuentaDebe() {
	return idCuentaDebe;
    }

    public void setIdCuentaHaber(int idCuentaHaber) {
	this.idCuentaHaber = idCuentaHaber;
    }

    public int getIdCuentaHaber() {
	return idCuentaHaber;
    }

    public void setIdCuentaDeduccion(int idCuentaDeduccion) {
	this.idCuentaDeduccion = idCuentaDeduccion;
    }

    public int getIdCuentaDeduccion() {
	return idCuentaDeduccion;
    }

    public void setIdCuentaInteres(int idCuentaInteres) {
	this.idCuentaInteres = idCuentaInteres;
    }

    public int getIdCuentaInteres() {
	return idCuentaInteres;
    }

    public void retrieveData(){
	try {
	    ResultSet rs = LibSQL.exFunction("taxes.getTipoImpuesto",""+ idTipoImpuesto);  // Falta desarrollarla
	    if (rs.next()){
		setNombre(rs.getString("nombre"));
	        setIdCuentaDebe(rs.getInt("idcuentadebe"));
	        setIdCuentaHaber(rs.getInt("idcuentahaber"));
	        setIdCuentaDeduccion(rs.getInt("idcuentadeduccion"));
	        setIdCuentaInteres(rs.getInt("idcuentainteres"));
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public int saveData(){
	String params = "";
	int result = -1;
	    params = idTipoImpuesto +",'" + nombre + "'," + idCuentaDebe + "," + idCuentaHaber + "," + idCuentaDeduccion + ","+ idCuentaInteres;
	    result = LibSQL.getInt("taxes.addTipoImpuesto",params);
	    idTipoImpuesto = result;
	return result;
    }

    public boolean deleteData(){
	String params = "";
	boolean result = false;
	    params = ""+ idTipoImpuesto ;
	    result = LibSQL.getBoolean("taxes.delTipoImpuesto",params);
	return result;
    }
    
}
