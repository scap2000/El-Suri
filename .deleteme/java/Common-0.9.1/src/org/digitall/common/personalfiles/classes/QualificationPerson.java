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
 * QualificationPerson.java
 *
 * */
package org.digitall.common.personalfiles.classes;

import java.sql.ResultSet;
import org.digitall.lib.sql.LibSQL;

public class QualificationPerson {
    private int idQualificationPerson = -1;
    private String name = "";
    private int idTypeQualification = -1;
    private String establishment = "";
    private String observations = "";
    private String dateGraduation = "";
    private int year = -1;

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Todos los atributos en ingles y promedio y puntaje en español
     * No hay coherencia
     * */
    private double promedio = 0;
    private double puntaje = 0;
    private int idPerson = -1;    
    private int idQualificationStatus = -1;
    private String dateStart = "";
    
    public QualificationPerson() {
    
    }
    
    public QualificationPerson(int _idQualificationPerson) {
	idQualificationPerson = _idQualificationPerson;
    }

    public int getIdQualificationPerson() {
	return idQualificationPerson;
    }

    public void setIdQualificationPerson(int idQualificationPerson) {
	this.idQualificationPerson = idQualificationPerson;
    }

    public String getName() {
	return name;
    }

    public void setName(String _name) {
	this.name = _name;
    }

    public int getIdTypeQualification() {
	return idTypeQualification;
    }

    public void setIdTypeQualification(int _idTypeQualification) {
	this.idTypeQualification = _idTypeQualification;
    }

    public String getEstablishment() {
	return establishment;
    }

    public void setEstablishment(String _establishment) {
	this.establishment = _establishment;
    }

    public String getObservations() {
	return observations;
    }

    public void setObservations(String _observations) {
	this.observations = _observations;
    }

    public String getDateGraduation() {
	return dateGraduation;
    }

    public void setDateGraduation(String _dateGraduation) {	
	this.dateGraduation = _dateGraduation;
    }

    public int getYear() {
	return year;
    }

    public void setYear(int _year) {
	this.year = _year;
    }

    public double getPromedio() {
	return promedio;
    }

    public void setPromedio(double _promedio) {
	this.promedio = _promedio;
    }

    public double getPuntaje() {
	return puntaje;
    }

    public void setPuntaje(double _puntaje) {
	this.puntaje = _puntaje;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setIdPerson(int _idperson) {
	this.idPerson = _idperson;
    }
    
    public int saveData() {  		
	String params = idTypeQualification+","+idPerson+",'"+name+"','"+establishment+"','"+dateGraduation+"'," + year + ","+promedio+","+puntaje+",'"+observations+"',"+ idQualificationStatus+",'"+dateStart+"'"; 
	int result = -1;
	if (idQualificationPerson == -1){	    
	    result = LibSQL.getInt("personalfiles.addQualificationPerson",params);    
	    idQualificationPerson = result;
	}else{      
	    params = ""+idQualificationPerson+","+ params; 
	    result = LibSQL.getInt("personalfiles.setQualificationPerson",params);
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = String.valueOf(idPerson);
	ResultSet data = LibSQL.exFunction("personalfiles.getQualificationPerson", params);
	try {
	    if (data.next()) {		
	        name = data.getString("name");
	        idTypeQualification = data.getInt("idtypequalification");
	        establishment = data.getString("establishment");
	        observations = data.getString("observations");
	        dateGraduation = data.getString("dategraduation");
	        year = data.getInt("year");
	        promedio = data.getFloat("promedio");
	        puntaje  = data.getFloat("puntaje");
	        idPerson = data.getInt("idperson");
	        idQualificationStatus = data.getInt("idqualificationstatus");
	        dateStart = data.getString("datestart");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public int getIdQualificationStatus() {
	return idQualificationStatus;
    }

    public void setIdQualificationStatus(int _qualificationStatus) {
	this.idQualificationStatus = _qualificationStatus;
    }

    public String getDateStart() {
	return dateStart;
    }

    public void setDateStart(String _dateStart) {
	this.dateStart = _dateStart;
    }

}
