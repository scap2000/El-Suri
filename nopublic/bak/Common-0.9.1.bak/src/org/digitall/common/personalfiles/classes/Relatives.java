package org.digitall.common.personalfiles.classes;

import java.sql.ResultSet;
import org.digitall.lib.sql.LibSQL;

public class Relatives {

    private int idRelative = -1;
    private int idPerson = -1;
    private int idKinships = -1;   
    private int idPersonEmployee = -1;    

    public Relatives() {
    }
    
    public Relatives(int _idRelative) {
	idRelative = _idRelative;
    }

    public int getIdRelative() {
	return idRelative;
    }

    public void setIdRelative(int idRelative) {
	this.idRelative = idRelative;
    }
    
    public int getIdPerson() {
	return idPerson;
    }

    public void setIdPerson(int _idPerson) {
	this.idPerson = _idPerson;
    }
    
    public int getIdKinships() {
	return idKinships;
    }

    public void setIdKinships(int _idKinships) {
	this.idKinships = _idKinships;
    }

    public int getIdPersonEmployee() {
	return idPersonEmployee;
    }

    public void setIdPersonEmployee(int _idPersonEmployee) {
	this.idPersonEmployee = _idPersonEmployee;
    }
    
    public int saveData(){
	int result = -1;
	if (idRelative == -1){
	    String params = idPerson +","+ idKinships+","+idPersonEmployee;
	    result = LibSQL.getInt("personalfiles.addRelatives", params);
	    idRelative = result;
	}
	return result;
    }
    
    public void retrieveData() {	
	ResultSet data = LibSQL.exFunction("personalfiles.getRelative", idRelative);
	try {
	    if (data.next()) {
		idPerson = data.getInt("idperson");
		idKinships = data.getInt("idkinships");
		idPersonEmployee = data.getInt("idpersonemployee");		
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

}
