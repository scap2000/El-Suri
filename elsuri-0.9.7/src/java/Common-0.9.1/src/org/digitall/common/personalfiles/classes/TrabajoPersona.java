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
 * TrabajoPersona.java
 *
 * */
package org.digitall.common.personalfiles.classes;

import java.sql.ResultSet;
import org.digitall.lib.sql.LibSQL;

public class TrabajoPersona {
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * El atributo idJobPerson está bien nombrado, sin embargo
     * la clase se llama TrabajoPersona en lugar de JobPerson
     * No hay coherencia
     * */

    private int idJobPerson = -1;
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * dateStart --> startDate
     * dateEnd --> endDate
     * dateDesignation --> designationDate
     * y sus accesores
     * */
    
    private String dateStart = "";	
    private String dateEnd = "";
    private int idJobs = -1;
    private double salary = 0.0;
    private int idPerson = -1;	
    private String dateDesignation = "";    
    private int idDependency = -1;
    private int idCodigoContrato = -1;
    private String cuentaBancaria = "";
    private int idCategoria = -1;
    

    public TrabajoPersona(int _idJobPerson) {
	idJobPerson = _idJobPerson;
    }
    
    public TrabajoPersona() {

    }
    
    public String getDateStart() {
	return dateStart;
    }

    public void setDateStart(String _dateStart) {
	this.dateStart = _dateStart;
    }

    public String getDateEnd() {
	return dateEnd;
    }

    public void setDateEnd(String _dateEnd) {
	this.dateEnd = _dateEnd;
    }

    public int getIdJobs() {
	return idJobs;
    }

    public void setIdJobs(int _idJobs) {
	this.idJobs = _idJobs;
    }

    public double getSalary() {
	return salary;
    }

    public void setSalary(double _salary) {
	this.salary = _salary;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setIdPerson(int _idPerson) {
	this.idPerson = _idPerson;
    }

    public String getDateDesignation() {
	return dateDesignation;
    }

    public void setDateDesignation(String _dateDesignation) {
	this.dateDesignation = _dateDesignation;
    }

    public int getIdJobPerson() {
	return idJobPerson;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * mayMin (el otro accesor está bien... ¿por qué?)
     * */
    public void setIdjobperson(int _idjobperson) {
	this.idJobPerson = _idjobperson;
    }
 
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * mayMin
     * */
    public int getIddependency() {
	return idDependency;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * mayMin
     * */
    public void setIddependency(int iddependency) {
	this.idDependency = iddependency;
    }
    
    public int saveData() {     
	String params = idPerson+","+idJobs+",'"+dateStart+"','"+dateEnd+"','"+dateDesignation+"'," + salary + ","+idDependency; 
	int result = -1;
	if (idJobPerson == -1){
	    result = LibSQL.getInt("personalfiles.addJobPerson",params);    
	    idJobPerson = result;
	}else{      
	    params = "" + idJobPerson+",'"+dateStart+"','"+dateEnd+"','"+dateDesignation+"'," + salary + ","+idDependency + ","
                        + idCategoria + "," + idCodigoContrato + ",'" + cuentaBancaria + "'"; 
	    result = LibSQL.getInt("personalfiles.setJobPerson",params);
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = String.valueOf(idJobPerson);
	ResultSet data = LibSQL.exFunction("personalfiles.getJobPersonal", params);
	try {
	    if (data.next()) {
		dateStart = data.getString("datestart");
	        dateEnd = data.getString("dateend");
	        idJobs = data.getInt("idjobs");
	        salary = data.getFloat("salary");
	        idPerson = data.getInt("idperson");
	        dateDesignation = data.getString("datedesignation");
	        idDependency = data.getInt("iddependency");
	        idCodigoContrato = data.getInt("idcodigocontrato");
	        cuentaBancaria = data.getString("cuentabancaria");
	        idCategoria = data.getInt("idcategoria");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdCodigoContrato(int idCodigoContrato) {
        this.idCodigoContrato = idCodigoContrato;
    }

    public int getIdCodigoContrato() {
        return idCodigoContrato;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }
}
