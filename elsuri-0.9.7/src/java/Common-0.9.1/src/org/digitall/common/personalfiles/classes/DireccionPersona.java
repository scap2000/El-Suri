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
 * DireccionPersona.java
 *
 * */
package org.digitall.common.personalfiles.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class DireccionPersona extends Direccion{

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * La clase se llama DireccionPersona pero su primer atributo
     * idAddressPerson está bien nombrado en inglés...
     * */

    private int idAddressPerson = -1;
    private int idPerson = -1;   
    private boolean isDefault = false;   
    
    public DireccionPersona() {

    }
    
    public DireccionPersona(int _idPerson) {
	idPerson = _idPerson;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setIdPerson(int _idPerson) {
	this.idPerson = _idPerson;
    }

    public boolean isDefault() {
	return isDefault;
    }

    public void setDefault(boolean _isDefault) {
	isDefault = _isDefault;
    }

    public int saveData() {     
	String params = idPerson +","+isDefault+","+super.getIdCountry() + "," + super.getIdProvince() + "," + super.getIdLocation() + "," + super.getIdNeighborHood() + "," + super.getIdStreet() + ",0" + super.getNumber() + ",'" + super.getPostalCode() + "','" + super.getBlock() + "',0" + super.getFloor() + ",'" + super.getApartment() + "','" + super.getPhone() + "','" + super.getPhone2() + "','" + super.getMobile() + "','" + super.getMobile2() + "','" + super.getEmail() + "','" + super.getEmail2() + "','" + super.getFax() + "','" + super.getUrl() + "'," + super.getIdContact() + ",'" + super.getContactsChedule() + "'," + super.getIdBornCountry() +","+super.getIdAddressType() + ",'" + super.getNick() + "',0,0";
	int result = -1;
	if (super.getIdAddress() == -1){
	    result = LibSQL.getInt("org.addAddressPerson",params);   
	    idAddressPerson = result;
	}else{      
	    String params1 = idPerson+","+idAddressPerson +","+isDefault+",'"+super.getIdAddress()+"',"+super.getIdCountry() + "," + super.getIdProvince() + "," + super.getIdLocation() + "," + super.getIdNeighborHood() + "," + super.getIdStreet() + ",0" + super.getNumber() + ",'" + super.getPostalCode() + "','" + super.getBlock() + "',0" + super.getFloor() + ",'" + super.getApartment() + "','" + super.getPhone() + "','" + super.getPhone2() + "','" + super.getMobile() + "','" + super.getMobile2() + "','" + super.getEmail() + "','" + super.getEmail2() + "','" + super.getFax() + "','" + super.getUrl() + "'," + super.getIdContact() + ",'" + super.getContactsChedule() + "'," + super.getIdAddressType() + ",'" + super.getNick() + "',0,0";
	    result = LibSQL.getInt("org.setAddressPerson",params1);
	}           	
	return result;
    }
    
    public int getIdAddressPerson() {
	return idAddressPerson;
    }

    public void setIdAddressPerson(int _idAddressPerson) {
	this.idAddressPerson = _idAddressPerson;
    }

}
