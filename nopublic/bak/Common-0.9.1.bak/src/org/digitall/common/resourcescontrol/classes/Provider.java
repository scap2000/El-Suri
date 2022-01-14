package org.digitall.common.resourcescontrol.classes;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.org.Persons;

public class Provider {
    
    private int idProvider = -1;
    private String name = "";
    private int idIdentificationType = 2;
    //private long identificationNumber = 0;
    private String identificationNumber = "";
    private int idTributaryCondition = 1;
    private Persons personCharge;
    private int idSuffix = 0; 
    private int idCompanyType = 0;
    private int idParent = 0;
    private String startDate = "";
    private int photo = 0;
    private int logo = 0;
    private int idCommunicationType = 0;
    private String description;
    private Account account;
    
    private boolean multilateralAggrement = false;
    private String multilateralAggrementNumber = "";

    public Provider() {

    }
    
    public Provider(int _idProvider) {
	idProvider = _idProvider;
    }
    
    public Provider(int _idProvider, String _name) {
	idProvider = _idProvider;
	name = _name;
    }

    public void setIdProvider(int _idprovider) {
	idProvider = _idprovider;
    }

    public int getIdProvider() {
	return idProvider;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setIdIdentificationType(int idIdentificationType) {
	this.idIdentificationType = idIdentificationType;
    }

    public int getIdIdentificationType() {
	return idIdentificationType;
    }

    /*public void setIdentificationNumber(long identificationNumber) {
	this.identificationNumber = identificationNumber;
    }*/
    
    public void setIdentificationNumber(String _identificationNumber) {
        identificationNumber = _identificationNumber;
    }

    /*public long getIdentificationNumber() {
	return identificationNumber;
    }*/

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdTributaryCondition(int idTributaryCondition) {
	this.idTributaryCondition = idTributaryCondition;
    }

    public int getIdTributaryCondition() {
	return idTributaryCondition;
    }

    public void setPersonCharge(Persons personCharge) {
	this.personCharge = personCharge;
    }

    public Persons getPersonCharge() {
	return personCharge;
    }

    public void setIdSuffix(int idSuffix) {
	this.idSuffix = idSuffix;
    }

    public int getIdSuffix() {
	return idSuffix;
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

    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String getStartDate() {
	return startDate;
    }

    public void setPhoto(int photo) {
	this.photo = photo;
    }

    public int getPhoto() {
	return photo;
    }

    public void setLogo(int logo) {
	this.logo = logo;
    }

    public int getLogo() {
	return logo;
    }

    public void setIdCommunicationType(int idCommunicationType) {
	this.idCommunicationType = idCommunicationType;
    }

    public int getIdCommunicationType() {
	return idCommunicationType;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    public int saveData(){
	/*String params = "'"+ name +"',"+ idSuffix +","+ idIdentificationType +","+ identificationNumber +","+ idTributaryCondition +","+ 
			personCharge.getIdPerson() +","+ idCommunicationType +",'"+ startDate +"',"+ idParent +",null,null,'"+ 
                        description +"',"+ idCompanyType +","+ multilateralAggrement +",'"+ multilateralAggrementNumber + "'";*/
        String params = "'"+ name +"',"+ idSuffix +","+ idIdentificationType +",'"+ identificationNumber +"',"+ idTributaryCondition +","+ 
                        personCharge.getIdPerson() +","+ idCommunicationType +",'"+ startDate +"',"+ idParent +",null,null,'"+ 
                        description +"',"+ idCompanyType +","+ multilateralAggrement +",'"+ multilateralAggrementNumber + "'";
	int result = -1;
	if (idProvider == -1){
	    result = LibSQL.getInt("org.addCompany", params);
	    idProvider = result;
	} else {
	    params = idProvider +","+ params;
	    result = LibSQL.getInt("org.setCompany", params);
	}
	return result;
    }

    public int delData(){
	int result = -1;
	String params = "" + idProvider;
	result = LibSQL.getInt("org.delCompany", params);
	return result;
    }

    public void setAccount(Account _account) {
	this.account = _account;
    }

    public Account getAccounting() {
	return account;
    }

    public int saveAccount(){
	return LibSQL.getInt("org.addCompanyAccount",idProvider +","+ account.getIDAccount());
    }
    
    public void retrieveData(){
        ResultSet data = LibSQL.exFunction("org.getProvider","" + idProvider);
        try  {
            if (data.next()) {
                idProvider = data.getInt("idcompany");
                name = data.getString("name");
                idIdentificationType = data.getInt("ididentificationtype");
                //identificationNumber = data.getLong("identificationnumber");
                identificationNumber = data.getString("identificationnumber");
                idTributaryCondition = data.getInt("idtributarycondition");
                idSuffix = data.getInt("idsuffix"); 
                idCompanyType = data.getInt("idcompanytype");
                idParent = data.getInt("idparent");
                startDate = data.getString("startdate");
                photo = -1;
                logo = -1;
                idCommunicationType = data.getInt("idcommunicationtype");
                description = data.getString("description");
                account = new Account(data.getInt("idaccount"));
                account.retrieveData();
                multilateralAggrement = data.getBoolean("multilateralagreement");
                multilateralAggrementNumber = data.getString("multilateralagreementnumber");
            }
        } catch (SQLException ex)  {
            ex.printStackTrace();
        }
    }

    public void setMultilateralAggrement(boolean multilateralAggrement) {
        this.multilateralAggrement = multilateralAggrement;
    }

    public boolean isMultilateralAggrement() {
        return multilateralAggrement;
    }

    public void setMultilateralAggrementNumber(String multilateralAggrementNumber) {
        this.multilateralAggrementNumber = multilateralAggrementNumber;
    }

    public String getMultilateralAggrementNumber() {
        return multilateralAggrementNumber;
    }
}
