package org.digitall.apps.projecttask.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class Person {

    private int idPerson = -1;
    private int idIdentificationType = -1;
    private long identificationNumber = -1;
    private String name = "";
    private String lastName = "";
    

    public Person() {

    }
    
    

    public void setIdPerson(int _idPerson) {
	idPerson = _idPerson;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setIdentificationType(int _identificationType) {
	idIdentificationType = _identificationType;
    }

    public int getIdentificationType() {
	return idIdentificationType;
    }

    public void setIdentificationNumber(long _identificationNumber) {
	identificationNumber = _identificationNumber;
    }

    public long getIdentificationNumber() {
	return identificationNumber;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setLastName(String _lastName) {
	lastName = _lastName;
    }

    public String getLastName() {
	return lastName;
    }
    
    public void  loadDataAdmin(int _idPerson){

	 //OBTENER DATOS DE LA BASE DE DATOS
	 idPerson = _idPerson;
	 String params = String.valueOf(idPerson);
	
	 ResultSet data = LibSQL.exFunction("task.getpersonadmin", params);

	 try {
	     if (data.next()) {
		 
	         idIdentificationType = data.getInt("ididentificationType");
	         identificationNumber = data.getLong("identificationnumber");
		 name = data.getString("name");
	         lastName = data.getString("lastname");
	
					 
		 
	     }
	 } catch (Exception ex) {
	     ex.printStackTrace();
	     
	 }
    
    }
    public void  loadDataUser(int _idPerson){

	 //OBTENER DATOS DE LA BASE DE DATOS
	 idPerson = _idPerson;
	 String params = String.valueOf(idPerson);
	
	 ResultSet data = LibSQL.exFunction("task.getperson", params);

	 try {
	     if (data.next()) {
		 
		 idIdentificationType = data.getInt("ididentificationType");
		 identificationNumber = data.getLong("identificationnumber");
		 name = data.getString("name");
		 lastName = data.getString("lastname");
	
					 
		 
	     }
	 } catch (Exception ex) {
	     ex.printStackTrace();
	     
	 }
    
    }

}
