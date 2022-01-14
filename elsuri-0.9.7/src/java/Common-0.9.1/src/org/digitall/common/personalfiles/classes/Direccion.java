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
 * Direccion.java
 *
 * */
package org.digitall.common.personalfiles.classes;

import java.awt.Point;

import java.awt.geom.Point2D;

import java.sql.ResultSet;

import java.util.Date;

import org.digitall.lib.sql.LibSQL;

public class Direccion {

    private int idAddress = -1;
    private int idCountry = -1;
    private int idProvince = -1;
    private int idLocation = -1;
    private int idNeighborHood = -1;
    private int idStreet = -1;
    private int number = -1;
    private String postalCode = "";
    private String block = "";
    private int floor = -1;
    private String apartment = "";
    private String phone = "";
    private String phone2 = "";
    private String mobile = "";
    private String mobile2 = "";
    private String email = "";
    private String email2 = "";
    private String fax = "";
    private String url = "";
    private int idContact = -1;
    private String contactsChedule = "";    
    private int idAddressType = -1;
    private String nick = "";
    private Point.Double coord;  
    private int idBornCountry = -1;

    public Direccion() {
    }

    public Direccion(int _idAddress) {
	idAddress = _idAddress;
    }

    public int getIdAddress() {
	return idAddress;
    }

    public void setIdAddress(int _idAddress) {
	this.idAddress = _idAddress;
    }

    public int getIdCountry() {
	return idCountry;
    }

    public void setIdCountry(int _idCountry) {
	this.idCountry = _idCountry;
    }

    public int getIdProvince() {
	return idProvince;
    }

    public void setIdProvince(int _idProvince) {
	this.idProvince = _idProvince;
    }

    public int getIdLocation() {
	return idLocation;
    }

    public void setIdLocation(int _idLocation) {
	this.idLocation = _idLocation;
    }

    public int getIdNeighborHood() {
	return idNeighborHood;
    }

    public void setIdNeighborHood(int _idNeighborHood) {
	this.idNeighborHood = _idNeighborHood;
    }

    public int getIdStreet() {
	return idStreet;
    }

    public void setIdStreet(int _idStreet) {
	this.idStreet = _idStreet;
    }

    public int getNumber() {
	return number;
    }

    public void setNumber(int _number) {
	this.number = _number;
    }

    public String getPostalCode() {
	return postalCode;
    }

    public void setPostalCode(String _postalCode) {
	this.postalCode = _postalCode;
    }

    public String getBlock() {
	return block;
    }

    public void setBlock(String _block) {
	this.block = _block;
    }

    public int getFloor() {
	return floor;
    }

    public void setFloor(int _floor) {
	this.floor = _floor;
    }

    public String getApartment() {
	return apartment;
    }

    public void setApartment(String _apartment) {
	this.apartment = _apartment;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String _phone) {
	this.phone = _phone;
    }

    public String getPhone2() {
	return phone2;
    }

    public void setPhone2(String _phone2) {
	this.phone2 = _phone2;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String _mobile) {
	this.mobile = _mobile;
    }

    public String getMobile2() {
	return mobile2;
    }

    public void setMobile2(String _mobile2) {
	this.mobile2 = _mobile2;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String _email) {
	this.email = _email;
    }

    public String getEmail2() {
	return email2;
    }

    public void setEmail2(String _email2) {
	this.email2 = _email2;
    }

    public String getFax() {
	return fax;
    }

    public void setFax(String _fax) {
	this.fax = _fax;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String _url) {
	this.url = _url;
    }

    public int getIdContact() {
	return idContact;
    }

    public void setIdContact(int _idContact) {
	this.idContact = _idContact;
    }

    public String getContactsChedule() {
	return contactsChedule;
    }

    public void setContactsChedule(String _contactsChedule) {
	this.contactsChedule = _contactsChedule;
    }

    public int getIdAddressType() {
	return idAddressType;
    }

    public void setIdAddressType(int _idAddressType) {
	this.idAddressType = _idAddressType;
    }

    public String getNick() {
	return nick;
    }

    public void setNick(String _nick) {
	this.nick = _nick;
    }

    public Point.Double getCoord() {
	return coord;
    }

    public void setCoord(Point.Double _coord) {
	this.coord = _coord;
    }

    public int saveData(int _idPerson) {     
    	String params = idCountry + "," + idProvince + "," + idLocation + "," + idNeighborHood + "," + idStreet + ",0" + number + ",'" + postalCode + "','" + block + "',0" + floor + ",'" + apartment + "','" + phone + "','" + phone2 + "','" + mobile + "','" + mobile2 + "','" + email + "','" + email2 + "','" + fax + "','" + url + "'," + idContact + ",'" + contactsChedule + ",0,"+idAddressType + ",'" + nick + "',0,0";
	int result = -1;
	if (idAddress == -1){
	    result = LibSQL.getInt("personalfiles.addAddress",params);  
	    idAddress = result;
	}else{      
	    params = "'"+_idPerson+"',"+ params;
	    result = LibSQL.getInt("personalfiles.setAddress",params);
	}               
	return result;
    }
    
    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("personalfiles.getAddress", idAddress);
	try {
	    if (data.next()) {	    
		idCountry = data.getInt("idcountry");
	        idProvince = data.getInt("idprovince");
	        idLocation = data.getInt("idlocation");
	        idNeighborHood = data.getInt("idneighborhood");
	        idStreet = data.getInt("idstreet");
	        number = data.getInt("number");
	        postalCode = data.getString("postalcode");    
	        block = data.getString("block");    
	        floor = data.getInt("floor");    
	        apartment = data.getString("apartment");    
	        phone  = data.getString("phone");    
	        phone2  = data.getString("phone2");    
	        mobile = data.getString("mobile");    
	        mobile2 = data.getString("mobile2");    
	        email = data.getString("email");    
	        email2 = data.getString("email2");    
	        fax = data.getString("fax");    
	        url = data.getString("url");    
	        idContact = data.getInt("idcontact");    
	        contactsChedule = data.getString("contactschedule");    	        
	        idAddressType = data.getInt("idaddresstype");    
	        nick = data.getString("nick");    	        
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdBornCountry(int _idBornCountry) {
        idBornCountry = _idBornCountry;
    }

    public int getIdBornCountry() {
        return idBornCountry;
    }
}
