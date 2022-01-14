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
 * Employees.java
 *
 * */
package org.digitall.common.resourcescontrol.classes;

import org.digitall.lib.org.Companies;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.org.Persons;

public class Employees {

    private int idEmployee = -1;
    private Persons person;
    private Companies company;
    private String startDate;
    private String endDate;
    private int fileNumber;
    private int fileInternalNumber;
    private int idContractType;
    private String photo = "null";
    
    public Employees() {

    }

    public void setIdEmployee(int idEmployee) {
	this.idEmployee = idEmployee;
    }

    public int getIdEmployee() {
	return idEmployee;
    }

    public void setPerson(Persons person) {
	this.person = person;
    }

    public Persons getPerson() {
	return person;
    }

    public void setCompany(Companies company) {
	this.company = company;
    }

    public Companies getCompany() {
	return company;
    }

    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String getStartDate() {
	return startDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setFileNumber(int fileNumber) {
	this.fileNumber = fileNumber;
    }

    public int getFileNumber() {
	return fileNumber;
    }

    public void setFileInternalNumber(int fileInternalNumber) {
	this.fileInternalNumber = fileInternalNumber;
    }

    public int getFileInternalNumber() {
	return fileInternalNumber;
    }

    public void setIdContractType(int idContractType) {
	this.idContractType = idContractType;
    }

    public int getIdContractType() {
	return idContractType;
    }

    public void setPhoto(String photo) {
	this.photo = photo;
    }

    public String getPhoto() {
	return photo;
    }
    
    public int addData(){
	String params = person.getIdPerson() +","+ company.getIdCompany() +",'"+ startDate +"','"+ endDate +"',"+ 
			fileNumber +","+ fileInternalNumber +","+ idContractType +","+ photo;
	idEmployee = LibSQL.getInt("org.addEmployee", params);
	return idEmployee;
    }
    
    public int setData(){
	String params = idEmployee +","+ person.getIdPerson() +","+ company.getIdCompany() +",'"+ startDate +"','"+ endDate +"',"+ 
			fileNumber +","+ fileInternalNumber +","+ idContractType +","+ photo;
	return LibSQL.getInt("org.setEmployee", params);
    }
}
