package org.digitall.common.resourcescontrol.classes;

import org.digitall.lib.org.Companies;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.org.Persons;

public class Employees {

    private int idEmployee = -1;
    private Persons person;
    private Companies company;
    private String startDate;
    private String endDate;
    private int fileNumber;
    private int fileInternalNumber;
    private int idContractType;
    private String photo = "null";
    
    public Employees() {

    }

    public void setIdEmployee(int idEmployee) {
	this.idEmployee = idEmployee;
    }

    public int getIdEmployee() {
	return idEmployee;
    }

    public void setPerson(Persons person) {
	this.person = person;
    }

    public Persons getPerson() {
	return person;
    }

    public void setCompany(Companies company) {
	this.company = company;
    }

    public Companies getCompany() {
	return company;
    }

    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String getStartDate() {
	return startDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setFileNumber(int fileNumber) {
	this.fileNumber = fileNumber;
    }

    public int getFileNumber() {
	return fileNumber;
    }

    public void setFileInternalNumber(int fileInternalNumber) {
	this.fileInternalNumber = fileInternalNumber;
    }

    public int getFileInternalNumber() {
	return fileInternalNumber;
    }

    public void setIdContractType(int idContractType) {
	this.idContractType = idContractType;
    }

    public int getIdContractType() {
	return idContractType;
    }

    public void setPhoto(String photo) {
	this.photo = photo;
    }

    public String getPhoto() {
	return photo;
    }
    
    public int addData(){
	String params = person.getIdPerson() +","+ company.getIdCompany() +",'"+ startDate +"','"+ endDate +"',"+ 
			fileNumber +","+ fileInternalNumber +","+ idContractType +","+ photo;
	idEmployee = LibSQL.getInt("org.addEmployee", params);
	return idEmployee;
    }
    
    public int setData(){
	String params = idEmployee +","+ person.getIdPerson() +","+ company.getIdCompany() +",'"+ startDate +"','"+ endDate +"',"+ 
			fileNumber +","+ fileInternalNumber +","+ idContractType +","+ photo;
	return LibSQL.getInt("org.setEmployee", params);
    }
}
