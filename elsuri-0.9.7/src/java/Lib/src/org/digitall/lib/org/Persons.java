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
 * Persons.java
 *
 * */
package org.digitall.lib.org;

import java.awt.image.BufferedImage;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.org.User;
import org.digitall.lib.sql.LibSQL;

public class Persons {

    private int idPerson = -1;
    private int idIdentificationType;
    private String IdentificationNumber;
    private String Name;
    private String lastName;
    private String fullName;
    private int idPrefix;
    private int idSuffix;
    private int idFormatView;
    private int idContactType;
    private int idProfession;
    private String Title;
    private String birthDate;   
    private String digitalSign; 
    private String photo = "null";
    private String logo = "null";
    private int idCivilState;
    private int idCommunicationType; 
    private String Description;
    private String sex = "";
    private User user;
    private byte[] photoBytes = null;
    private BufferedImage photoImage = null;
    private BufferedImage logoImage = null;
    
    public Persons() {
    
    }
    
    public Persons(int _idPerson) {
	idPerson = _idPerson;
    }

    public void setIdPerson(int idPerson) {
	this.idPerson = idPerson;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setIdIdentificationType(int idIdentificationType) {
	this.idIdentificationType = idIdentificationType;
    }

    public int getIdIdentificationType() {
	return idIdentificationType;
    }

    public void setIdentificationNumber(String identificationNumber) {
	this.IdentificationNumber = identificationNumber;
    }

    public String getIdentificationNumber() {
	return IdentificationNumber;
    }

    public void setName(String name) {
	this.Name = name;
	fullName = lastName +", "+ Name;
    }

    public String getName() {
	return Name;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
	fullName = lastName +", "+ Name;
    }

    public String getLastName() {
	return lastName;
    }

    public void setIdPrefix(int idPrefix) {
	this.idPrefix = idPrefix;
    }

    public int getIdPrefix() {
	return idPrefix;
    }

    public void setIdSuffix(int idSuffix) {
	this.idSuffix = idSuffix;
    }

    public int getIdSuffix() {
	return idSuffix;
    }

    public void setIdFormatView(int idFormatView) {
	this.idFormatView = idFormatView;
    }

    public int getIdFormatView() {
	return idFormatView;
    }

    public void setIdContactType(int idContactType) {
	this.idContactType = idContactType;
    }

    public int getIdContactType() {
	return idContactType;
    }

    public void setIdProfession(int idProfession) {
	this.idProfession = idProfession;
    }

    public int getIdProfession() {
	return idProfession;
    }

    public void setTitle(String _title) {
	this.Title = _title;
    }

    public String getTitle() {
	return Title;
    }

    public void setBirthDate(String birthDate) {
	this.birthDate = birthDate;
    }

    public String getBirthDate() {
	return birthDate;
    }

    public void setDigitalSign(String digitalSign) {
	this.digitalSign = digitalSign;
    }

    public String getDigitalSign() {
	return digitalSign;
    }

    public void setPhoto(String photo) {
	this.photo = photo;
    }

    public String getPhoto() {
	return photo;
    }

    public void setLogo(String logo) {
	this.logo = logo;
    }

    public String getLogo() {
	return logo;
    }

    public void setIdCivilState(int idCivilState) {
	this.idCivilState = idCivilState;
    }

    public int getIdCivilState() {
	return idCivilState;
    }

    public void setIdCommunicationType(int idCommunicationType) {
	this.idCommunicationType = idCommunicationType;
    }

    public int getIdCommunicationType() {
	return idCommunicationType;
    }

    public void setDescription(String description) {
	this.Description = description;
    }

    public String getDescription() {
	return Description;
    }

    public int addData() {
	String params = idIdentificationType + ",'" + IdentificationNumber + "','" + Name + "','" + lastName + "'," + 
			idPrefix + "," + idSuffix + "," + idFormatView + "," + idContactType + "," + idProfession + ",'" + 
			Title + "','" + birthDate + "','" + digitalSign + "'," + photo + "," + logo + "," + idCivilState + "," + 
			idCommunicationType + ",'" + Description + "','" + sex + "'";
	idPerson = LibSQL.getInt("org.addPerson", params);
	if (idPerson != -1 && photoImage != null) {
	    LibSQL.saveImage(photoImage, "org.persons", "photo", "WHERE idperson = " + idPerson);
	}
	return idPerson;
    }
    
    public int setData() {
	String params = idPerson + "," + idIdentificationType + ",'" + IdentificationNumber + "','" + Name + "','" + lastName + "'," + 
			idPrefix + "," + idSuffix + "," + idFormatView + "," + idContactType + "," + idProfession + ",'" + 
			Title + "','" + birthDate + "','" + digitalSign + "'," + photo + "," + logo + "," + idCivilState + "," + 
			idCommunicationType + ",'" + Description + "','" + sex + "'";
	if (LibSQL.getInt("org.setPerson", params) != -1 && photoImage != null) {
	    LibSQL.saveImage(photoImage, "org.persons", "photo", "WHERE idperson = " + idPerson);
	}
	return idPerson;
    }
    
    

    public void setUser(User user) {
	this.user = user;
    }

    public User getUser() {
	return user;
    }

    public String getFullName() {
	return fullName;
    }

    public void setSex(String _sex) {
	sex = _sex;
    }

    public String getSex() {
	return sex;
    }

    public void setPhotoBytes(byte[] _photoBytes) {
	photoBytes = _photoBytes;
    }

    public byte[] getPhotoBytes() {
	return photoBytes;
    }

    public void setPhotoImage(BufferedImage photoImage) {
	this.photoImage = photoImage;
    }

    public BufferedImage getPhotoImage() {
	return photoImage;
    }

    public void setLogoImage(BufferedImage logoImage) {
	this.logoImage = logoImage;
    }

    public BufferedImage getLogoImage() {
	return logoImage;
    }
    
    public void retrievePicture() {
	photoImage = LibSQL.getImage("org.persons", "photo", "WHERE idperson = " + idPerson);
    }

}
