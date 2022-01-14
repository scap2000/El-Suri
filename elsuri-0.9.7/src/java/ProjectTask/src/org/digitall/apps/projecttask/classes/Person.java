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
 * Person.java
 *
 * */
package org.digitall.apps.projecttask.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class Person {

    private int idPerson = -1;
    private int idIdentificationType = -1;
    private long identificationNumber = -1;
    private String name = "";
    private String lastName = "";
    

    public Person() {

    }
    
    

    public void setIdPerson(int _idPerson) {
	idPerson = _idPerson;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setIdentificationType(int _identificationType) {
	idIdentificationType = _identificationType;
    }

    public int getIdentificationType() {
	return idIdentificationType;
    }

    public void setIdentificationNumber(long _identificationNumber) {
	identificationNumber = _identificationNumber;
    }

    public long getIdentificationNumber() {
	return identificationNumber;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setLastName(String _lastName) {
	lastName = _lastName;
    }

    public String getLastName() {
	return lastName;
    }
    
    public void  loadDataAdmin(int _idPerson){

	 //OBTENER DATOS DE LA BASE DE DATOS
	 idPerson = _idPerson;
	 String params = String.valueOf(idPerson);
	
	 ResultSet data = LibSQL.exFunction("task.getpersonadmin", params);

	 try {
	     if (data.next()) {
		 
	         idIdentificationType = data.getInt("ididentificationType");
	         identificationNumber = data.getLong("identificationnumber");
		 name = data.getString("name");
	         lastName = data.getString("lastname");
	
					 
		 
	     }
	 } catch (Exception ex) {
	     ex.printStackTrace();
	     
	 }
    
    }
    public void  loadDataUser(int _idPerson){

	 //OBTENER DATOS DE LA BASE DE DATOS
	 idPerson = _idPerson;
	 String params = String.valueOf(idPerson);
	
	 ResultSet data = LibSQL.exFunction("task.getperson", params);

	 try {
	     if (data.next()) {
		 
		 idIdentificationType = data.getInt("ididentificationType");
		 identificationNumber = data.getLong("identificationnumber");
		 name = data.getString("name");
		 lastName = data.getString("lastname");
	
					 
		 
	     }
	 } catch (Exception ex) {
	     ex.printStackTrace();
	     
	 }
    
    }

}
