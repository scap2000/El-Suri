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
 * TipoDependencia.java
 *
 * */
package org.digitall.apps.personalfiles.classes;

import java.sql.ResultSet;
import org.digitall.lib.sql.LibSQL;

public class TipoDependencia {

    private int idTipo   = -1;
    private String nombre   = "";
    private int cupoMax = -1;
    private String cargoFemenino = "";
    private String cargoMasculino = "";

    public TipoDependencia() {

    }

    public int getIdTipo() {
	return idTipo;
    }

    public void setIdTipo(int _idTipo) {
	this.idTipo = _idTipo;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String _nombre) {
	this.nombre = _nombre;
    }

    public int getCupoMax() {
	return cupoMax;
    }

    public void setCupoMax(int _cupoMax) {
	this.cupoMax = _cupoMax;
    }

    public String getCargoFemenino() {
	return cargoFemenino;
    }

    public void setCargoFemenino(String _cargoFemenino) {
	this.cargoFemenino = _cargoFemenino;
    }

    public String getCargoMasculino() {
	return cargoMasculino;
    }

    public void setCargoMasculino(String _cargoMasculino) {
	this.cargoMasculino = _cargoMasculino;
    }
    
    public int savaData() {
	String params = "'"+nombre+"',"+cupoMax+",'"+cargoFemenino+"','"+cargoMasculino+"'";
	int result = -1;
	if (idTipo == -1){
	    result = LibSQL.getInt("personalfiles.addTipoDependencia",params);              
	    idTipo = result;        
	} else {            
	    params = idTipo+","+ params;
	    result = LibSQL.getInt("personalfiles.setTipoDependencia",params);
	}                       
	return result;
    }
    
    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("personalfiles.getTipoDependencia", idTipo);
	try {
	    if (data.next()) {
	        nombre   = data.getString("nombre");
	        cupoMax = data.getInt("cupomax");
	        cargoFemenino = data.getString("cargo_femenino");
	        cargoMasculino = data.getString("cargo_masculino");		
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

}
