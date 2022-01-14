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
 * Modulos.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091.classes;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Modulos {

    private int idModulo = -1;
    private String nombre = "" ;
    private String estado = "";
    
    public Modulos() {
    
    }
    
    public Modulos(int _idModulo, String _nombre) {
	idModulo = _idModulo;
	nombre = _nombre;
    }
    
    public int saveData() {
	int resultado = -1;
	String params = ""+idModulo+",'"+nombre+"',''";
	resultado = LibSQL.getInt("admin.addmodulo",params);
	return resultado;
    }
        
    public void retrieveData() {
	//ResultSet data = LibSQL.exQuery("SELECT * FROM admin.modulos WHERE idmodulo = "+idModulo+ "AND estado <>'*'");
	String params = ""+idModulo;
	ResultSet data = LibSQL.exFunction("admin.getmodulo",params);
	try {
	    if (data.next()) {
		idModulo  = data.getInt("idmodulo");
		nombre = data.getString("nombre");
		estado  = data.getString("estado");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public void retrieveData(String _nombreModulo){
	ResultSet data = LibSQL.exQuery("SELECT * FROM admin.modulos WHERE nombre = '"+ _nombreModulo +"' AND estado <>'*'");
	try {
	    if (data.next()) {
		idModulo  = data.getInt("idmodulo");
		nombre = data.getString("nombre");
		estado  = data.getString("estado");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdModulo(int idModulo) {
	this.idModulo = idModulo;
    }

    public int getIdModulo() {
	return idModulo;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }
}