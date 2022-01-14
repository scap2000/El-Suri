package org.digitall.lib.common;

import java.awt.Image;
import java.awt.image.BufferedImage;

import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.image.LibIMG;

public abstract class OrganizationInfo {

    private static String orgName = "DIGITALL S.H.";
    private static String title = "DIGITALL S.H.";
    private static String shortName = "DIGITALL S.H.";
    private static String description = "DIGITALL S.H. de Marcelo D\'Ambrosio y Santiago Cassina";
    private static String address = "Ntra. Sra. de la Talavera 45 - Block \"B\" - Piso 3 - Dpto. 21";
    private static String shortAddress = "Talavera 45 - \"B\", 3º, 21";
    private static String cuit = "30-71002258-1";
    private static String zipCode = "4400";
    private static String location = "Salta";
    private static String idLocation = "3379";
    private static String province = "Salta";
    private static String idprovince = "17";
    private static String country = "República Argentina";
    private static String webAddress = "http://www.digitallsh.com.ar";
    private static String phoneNumber1 = "(0387) 4313379";
    private static String phoneNumber2 = "(0387) 4313379";
    private static double iva = 0.0;
    private static Image leftLogo = IconTypes.organization_left_logo.getImage();
    private static Image rightLogo = IconTypes.organization_right_logo.getImage();
    //private static Image transparencyLogo = IconTypes.organization_transparency_logo.getImage();

    public static void setOrgName(String _orgName) {
	orgName = _orgName;
    }

    public static String getOrgName() {
	return orgName;
    }

    public static void setShortName(String _shortName) {
	shortName = _shortName;
    }

    public static String getShortName() {
	return shortName;
    }

    public static void setDescription(String _description) {
	description = _description;
    }

    public static String getDescription() {
	return description;
    }

    public static void setAddress(String _address) {
	address = _address;
    }

    public static String getAddress() {
	return address;
    }

    public static void setShortAddress(String _shortAddress) {
	shortAddress = _shortAddress;
    }

    public static String getShortAddress() {
	return shortAddress;
    }

    public static void setCuit(String _cuit) {
	cuit = _cuit;
    }

    public static String getCuit() {
	return cuit;
    }

    public static void setZipCode(String _zipCode) {
	zipCode = _zipCode;
    }

    public static String getZipCode() {
	return zipCode;
    }

    public static void setLocation(String _location) {
	location = _location;
    }

    public static String getLocation() {
	return location;
    }

    public static void setProvince(String _province) {
	province = _province;
    }

    public static String getProvince() {
	return province;
    }

    public static void setCountry(String _country) {
	country = _country;
    }

    public static String getCountry() {
	return country;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public static double getIva() {
        return iva;
    }

    public void setIdLocation(String _idLocation) {
        idLocation = _idLocation;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdprovince(String _idprovince) {
        idprovince = _idprovince;
    }

    public String getIdprovince() {
        return idprovince;
    }

    public static void setWebAddress(String _webAddress) {
        webAddress = _webAddress;
    }

    public static String getWebAddress() {
        return webAddress;
    }

    public static void setPhoneNumber1(String _phoneNumber1) {
        phoneNumber1 = _phoneNumber1;
    }

    public static String getPhoneNumber1() {
        return phoneNumber1;
    }

    public static void setPhoneNumber2(String _phoneNumber2) {
        phoneNumber2 = _phoneNumber2;
    }

    public static String getPhoneNumber2() {
        return phoneNumber2;
    }

    public static void setLeftLogo(Image _leftLogo) {
	leftLogo = _leftLogo;
    }

    public static Image getLeftLogo() {
	return leftLogo;
    }

    public static void setRightLogo(Image _rightLogo) {
	rightLogo = _rightLogo;
    }

    public static Image getRightLogo() {
	return rightLogo;
    }

    public static void setTitle(String _title) {
	title = _title;
    }

    public static String getTitle() {
	return title;
    }

    /*public void setTransparencyLogo(Image transparencyLogo) {
	this.transparencyLogo = transparencyLogo;
    }

    public static Image getTransparencyLogo() {
	return transparencyLogo;
    }*/
}
