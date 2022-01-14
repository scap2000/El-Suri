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
