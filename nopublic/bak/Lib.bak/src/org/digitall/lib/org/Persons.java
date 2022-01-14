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
