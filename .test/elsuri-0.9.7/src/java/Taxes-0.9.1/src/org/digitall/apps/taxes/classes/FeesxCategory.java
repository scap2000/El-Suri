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
 * FeesxCategory.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class FeesxCategory {

    private int anio = -1;
    private int idtipocategoria = -1;
    private double cuota = 0;
    private String estado = "";
    private double anual = 0;
    private String categoria = "";
    private boolean nuevo = true;
    
    public FeesxCategory() {

    }

    public int saveData(){
	int result = -1;
	String params = "";
	if (nuevo)  {
	    params = ""+ anio +","+ idtipocategoria +","+ anual;
	    result = LibSQL.getInt("taxes.addCuotaxCategoria", params);
	    anio = result;
	} else {
	    params = ""+ anio +","+ idtipocategoria +","+ cuota +","+ anual;
	    result = LibSQL.getInt("taxes.setCuotasxCategoria", params);
	}
	 return result;
    }

    public void setAnio(int anio) {
	this.anio = anio;
    }

    public int getAnio() {
	return anio;
    }

    public void setIdtipocategoria(int idtipocategoria) {
	this.idtipocategoria = idtipocategoria;
    }

    public int getIdtipocategoria() {
	return idtipocategoria;
    }

    public void setCuota(double cuota) {
	this.cuota = cuota;
    }

    public double getCuota() {
	return cuota;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setAnual(double anual) {
	this.anual = anual;
    }

    public double getAnual() {
	return anual;
    }

    public void setCategoria(String categoria) {
	this.categoria = categoria;
    }

    public String getCategoria() {
	return categoria;
    }

    public void setNuevo(boolean _nuevo) {
	nuevo = _nuevo;
    }

    public boolean isNuevo() {
	return nuevo;
    }
    
    public int retrieveData(){
	try {
	    ResultSet rs = LibSQL.exFunction("taxes.getFeesxCategory", ""+ anio +","+ idtipocategoria);
	    if(rs.next()){
		anio = rs.getInt("anio");
		idtipocategoria = rs.getInt("idcategoriaautomotor");
	        cuota = rs.getDouble("cuota");
		anual = rs.getDouble("anual");
		categoria = rs.getString("categoria");
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();
	}
	return anio;
    }

}
