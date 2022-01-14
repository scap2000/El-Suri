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
 * Contribucion.java
 *
 * */
package org.digitall.apps.taxes.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class Contribucion {

    private int idContribucion = -1;
    private String nombre = "";
    private String descripcion = "";
    private String desde = "";
    private String hasta = "";
    private String ordenanza = "";
    private int idtipoimpuesto = -1;

    public Contribucion() {
    }

    public Contribucion(int _idcontribucion) {
	setIdContribucion(_idcontribucion);
    }

    public void setIdContribucion(int idContribucion) {
	this.idContribucion = idContribucion;
    }

    public int getIdContribucion() {
	return idContribucion;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDesde(String desde) {
	this.desde = desde;
    }

    public String getDesde() {
	return desde;
    }

    public void setHasta(String hasta) {
	this.hasta = hasta;
    }

    public String getHasta() {
	return hasta;
    }

    public void setOrdenanza(String ordenanza) {
	this.ordenanza = ordenanza;
    }

    public String getOrdenanza() {
	return ordenanza;
    }		
    public int saveData(){
	int result = -1;
	String params = "";
	    params = idContribucion + ",'"+nombre +"','"+descripcion+"',"+"'"+desde+"',"+"'"+hasta+"',"+"'"+ordenanza+"',"+idtipoimpuesto;
	    result = LibSQL.getInt("taxes.addContribucion", params);
	    idContribucion = result;
	return (result) ;
    }
    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("taxes.getContribucion",""+ idContribucion);
	try  {
	    if (result.next())  {
		setNombre(result.getString("nombre"));
		setDescripcion(result.getString("descripcion"));
		setDesde(result.getString("desde"));
		setHasta(result.getString("hasta"));
		setOrdenanza(result.getString("ordenanza"));
	        setIdtipoimpuesto(result.getInt("idtipoimpuesto"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	}
	
    }
    
    public int delete(){
	return LibSQL.getInt("taxes.deleteContribucion",""+ idContribucion);
    }

    public void setIdtipoimpuesto(int idtipoimpuesto) {
	this.idtipoimpuesto = idtipoimpuesto;
    }

    public int getIdtipoimpuesto() {
	return idtipoimpuesto;
    }
}
