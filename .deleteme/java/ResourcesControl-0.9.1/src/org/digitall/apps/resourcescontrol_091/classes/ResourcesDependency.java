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
 * ResourcesDependency.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class ResourcesDependency {
    private int idResourcesDependency = -1;
    private int idDependency = -1;
    private int idDistResource = -1;
    private int stockAssigned = -1;
    private String estado = "";
    private String dateAssigned = "";
    private boolean transferido = false;
    
    public ResourcesDependency() {
    }

    public int saveData() {     
	String params = idResourcesDependency + "," + idDistResource + "," + idDependency 
	+ "," + stockAssigned + ",'" + estado + "','" + dateAssigned+ "'," + transferido; 
	int result = -1;
       result = LibSQL.getInt("personalfiles.addResourceDependency",params);
	return result;
    }
    
    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("personalfiles.getrecursosDependency", idResourcesDependency);
	try {
	    if (data.next()) {  
	        idResourcesDependency = data.getInt("idresourcesdependency");
		idDependency  = data.getInt("iddependency");
		idDistResource = data.getInt("iddistresource");
	        stockAssigned  = data.getInt("stockassigned");
		estado = data.getString("estado");
		dateAssigned = data.getString("dateassigned");
		transferido = data.getBoolean("transferido");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdResourcesDependency(int idResourcesDependency) {
	this.idResourcesDependency = idResourcesDependency;
    }

    public int getIdResourcesDependency() {
	return idResourcesDependency;
    }

    public void setIdDependency(int idDependency) {
	this.idDependency = idDependency;
    }

    public int getIdDependency() {
	return idDependency;
    }

    public void setIdDistResource(int idResource) {
	this.idDistResource = idResource;
    }

    public int getIdDistResource() {
	return idDistResource;
    }

    public void setSockAssigned(int stockAssigned) {
	this.stockAssigned = stockAssigned;
    }

    public int getSockAssigned() {
	return stockAssigned;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setDateAssigned(String dateAssigned) {
	this.dateAssigned = dateAssigned;
    }

    public String getDateAssigned() {
	return dateAssigned;
    }

    public void setTransferido(boolean transferido) {
	this.transferido = transferido;
    }

    public boolean isTransferido() {
	return transferido;
    }
}
