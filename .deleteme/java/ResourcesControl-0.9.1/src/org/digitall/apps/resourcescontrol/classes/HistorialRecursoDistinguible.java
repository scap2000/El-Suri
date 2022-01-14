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
 * HistorialRecursoDistinguible.java
 *
 * */
package org.digitall.apps.resourcescontrol.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class HistorialRecursoDistinguible {
 
    private int idDistinguishableResourceHistory = -1;
    private int idDistinguishableResource = -1;
    private String initDate = "";
    private String endDate = "";
    private int idPerson = -1;
    private String subject = "";
    private double waste = 0;
    private int idDependency = -1;
    
    
    public HistorialRecursoDistinguible() {

    }

    public int getIdDistinguishableResourceHistory() {
	return idDistinguishableResourceHistory;
    }

    public void setIdDistinguishableResourceHistory(int idDistinguishableResourceHistory) {
	this.idDistinguishableResourceHistory = idDistinguishableResourceHistory;
    }

    public int getIdDistinguishableResource() {
	return idDistinguishableResource;
    }

    public void setIdDistinguishableResource(int _idDistinguishableResource) {
	this.idDistinguishableResource = _idDistinguishableResource;
    }

    public String getInitDate() {
	return initDate;
    }

    public void setInitDate(String _initDate) {
	this.initDate = _initDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setEndDate(String _endDate) {
	this.endDate = _endDate;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setIdPerson(int _idPerson) {
	this.idPerson = _idPerson;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String _subject) {
	this.subject = _subject;
    }

    public double getWaste() {
	return waste;
    }

    public void setWaste(double _waste) {
	this.waste = _waste;
    }
    
    public int getIdDependency() {
	return idDependency;
    }

    public void setIdDependency(int _idDependency) {
	this.idDependency = _idDependency;
    }
    
    public int saveData() {          	
	String params = idDistinguishableResource+","+idPerson+",'"+initDate+"','"+endDate+"','"+subject+"'," + waste + ","+idDependency; 
	int result = -1;
	if (idDistinguishableResourceHistory == -1){           
	    result = LibSQL.getInt("personalfiles.addHistorialRecursoDisponible",params);    
	    idDistinguishableResourceHistory = result;
	}else{      
	    params = ""+idDistinguishableResourceHistory+","+ params; 
	    result = LibSQL.getInt("personalfiles.setHistorialRecursoDisponible",params); // falta crearla
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = String.valueOf(idPerson);
	ResultSet data = LibSQL.exFunction("personalfiles.getHistorialRecursoDisponible", params); // falta crearla
	try {
	    if (data.next()) {          
		idDistinguishableResourceHistory = data.getInt("iddistinguishableresourcehistory");
	        idDistinguishableResource = data.getInt("idDistinguishableResource");
	        initDate = data.getString("initdate");
	        endDate = data.getString("enddate");
	        idPerson = data.getInt("idPerson");
	        subject = data.getString("subject");
	        waste = data.getFloat("waste");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }    
}
