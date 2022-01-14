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
 * OrganizationInfo.java
 *
 * */
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
