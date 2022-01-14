package org.digitall.lib.org;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class Companies {

    private int idCompany = -1;
    private int idIdentificationType = -1;
    //private long idNumber = 0;
    private String idNumber = "";
    private int idTributaryCondition = -1;
    private String name = "";
    private int idPersonInCharge = -1;
    private int idSuffix = -1;
    private String suffix = "";
    private int idCompanyType = -1;
    private int idParent = -1;
    private Date startDate = null;
    private String photo = null;
    private String logo = null;
    private int idCommunicationType = -1;
    private String description = "";
    private byte[] photoBytes = null;
    private String identification = "";
    private String tributaryCondition = "";
    private String companyType = "";
    private String communicationType = "";
    private boolean multilateralAgreement = false;
    private String multilateralAgreementNumber = "";

    public Companies() {

    }

    public Companies(int _idCompany) {
	idCompany = _idCompany;
    }

    public void setIDCompany(int _idCompany) {
	this.idCompany = _idCompany;
    }

    public int getIDCompany() {
	return idCompany;
    }

    public void setIdIdentificationType(int idIdentificationType) {
	this.idIdentificationType = idIdentificationType;
    }

    public int getIdIdentificationType() {
	return idIdentificationType;
    }

     public void setIdentificationNumber(String _idNumber) {
         idNumber = _idNumber;
     }

    public String getIdentificationNumber() {
	return idNumber;
    }

    public void setIdSuffix(int idSuffix) {
	this.idSuffix = idSuffix;
    }

    public int getIdSuffix() {
	return idSuffix;
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

    public void setIdCommunicationType(int idCommunicationType) {
	this.idCommunicationType = idCommunicationType;
    }

    public int getIdCommunicationType() {
	return idCommunicationType;
    }

    public int addData() {
	/*String params = "'" + name + "'," + idSuffix + "," + idIdentificationType + "," + idNumber + "," + idTributaryCondition + "," + idPersonInCharge + "," 
                            + idCommunicationType + ",'" + startDate + "'," + idParent + "," + photo + "," + logo + ",'" + description + "'," 
                            + idCompanyType +","+ multilateralAgreement +",'"+ multilateralAgreementNumber +"'";*/
        String params = "'" + name + "'," + idSuffix + "," + idIdentificationType + ",'" + idNumber + "'," + idTributaryCondition + "," + idPersonInCharge + "," 
                            + idCommunicationType + ",'" + startDate + "'," + idParent + "," + photo + "," + logo + ",'" + description + "'," 
                            + idCompanyType +","+ multilateralAgreement +",'"+ multilateralAgreementNumber +"'";
	idCompany = LibSQL.getInt("org.addCompany", params);
	return idCompany;
    }

    /**
     * nota: la variable param que esta comentada tanto en el método addData() como en el método setData(), es porque se cambió el tipo de dato
     *          de un campo (idnumber pasó del tipo de dato "bigint" al tipo de dato "character varying", es decir, esta como respaldo.
     */

    public int setData() {
	/*String params = idCompany + ",'" + name + "'," + idSuffix + "," + idIdentificationType + "," + idNumber + "," + idTributaryCondition + "," 
                        + idPersonInCharge + "," + idCommunicationType + ",'" + startDate + "'," + idParent + "," + photo + "," + logo + ",'" 
                        + description + "'," + idCompanyType +","+ multilateralAgreement +",'"+ multilateralAgreementNumber +"'";*/
        String params = idCompany + ",'" + name + "'," + idSuffix + "," + idIdentificationType + ",'" + idNumber + "'," + idTributaryCondition + "," 
                        + idPersonInCharge + "," + idCommunicationType + ",'" + startDate + "'," + idParent + "," + photo + "," + logo + ",'" 
                        + description + "'," + idCompanyType +","+ multilateralAgreement +",'"+ multilateralAgreementNumber +"'";
	return LibSQL.getInt("org.setCompany", params);
    }

    public void retrieveData() {
	ResultSet data = LibSQL.exFunction("org.getCompany", idCompany);
	try {
	    if (data.next()) {
		idCompany = data.getInt("idcompany");
		idIdentificationType = data.getInt("ididentificationtype");
		identification = data.getString("identification");
		//idNumber = data.getLong("identificationnumber");
	        idNumber = data.getString("identificationnumber");
		idTributaryCondition = data.getInt("idtributarycondition");
		tributaryCondition = data.getString("tributarycondition");
		name = data.getString("name");
		idPersonInCharge = data.getInt("idpersoncharge");
		idSuffix = data.getInt("idsuffix");
		suffix = data.getString("suffix");
		idCompanyType = data.getInt("idcompanytype");
		companyType = data.getString("companytype");
		idParent = data.getInt("idparent");
		startDate = data.getDate("startdate");
		photo = null;
		logo = null;
		idCommunicationType = data.getInt("idcommunicationtype");
		communicationType = data.getString("communicationtype");
		description = data.getString("description");
		photoBytes = null;
	    }
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	}
    }

    public void setPhotoBytes(byte[] _photoBytes) {
	photoBytes = _photoBytes;
    }

    public byte[] getPhotoBytes() {
	return photoBytes;
    }

    public void setIdCompany(int idCompany) {
	this.idCompany = idCompany;
    }

    public int getIdCompany() {
	return idCompany;
    }

    /*public void setIdNumber(long idNumber) {
	this.idNumber = idNumber;
    }

    public long getIdNumber() {
	return idNumber;
    }*/
    
    public void setIdNumber(String _idNumber) {
        idNumber = _idNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdTributaryCondition(int idTributaryCondition) {
	this.idTributaryCondition = idTributaryCondition;
    }

    public int getIdTributaryCondition() {
	return idTributaryCondition;
    }

    public void setIdPersonInCharge(int idPersonInCharge) {
	this.idPersonInCharge = idPersonInCharge;
    }

    public int getIdPersonInCharge() {
	return idPersonInCharge;
    }

    public void setIdCompanyType(int idCompanyType) {
	this.idCompanyType = idCompanyType;
    }

    public int getIdCompanyType() {
	return idCompanyType;
    }

    public void setIdParent(int idParent) {
	this.idParent = idParent;
    }

    public int getIdParent() {
	return idParent;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    public Date getStartDate() {
	return startDate;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setSuffix(String suffix) {
	this.suffix = suffix;
    }

    public String getSuffix() {
	return suffix;
    }

    public void setIdentification(String identification) {
	this.identification = identification;
    }

    public String getIdentification() {
	return identification;
    }

    public void setTributaryCondition(String tributaryCondition) {
	this.tributaryCondition = tributaryCondition;
    }

    public String getTributaryCondition() {
	return tributaryCondition;
    }

    public void setCompanyType(String companyType) {
	this.companyType = companyType;
    }

    public String getCompanyType() {
	return companyType;
    }

    public void setCommunicationType(String communicationType) {
	this.communicationType = communicationType;
    }

    public String getCommunicationType() {
	return communicationType;
    }

    public void setMultilateralAgreement(boolean multilateralAgreement) {
        this.multilateralAgreement = multilateralAgreement;
    }

    public boolean isMultilateralAgreement() {
        return multilateralAgreement;
    }

    public void setMultilateralAgreementNumber(String multilateralAgreementNumber) {
        this.multilateralAgreementNumber = multilateralAgreementNumber;
    }

    public String getMultilateralAgreementNumber() {
        return multilateralAgreementNumber;
    }
}
