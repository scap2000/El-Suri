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
