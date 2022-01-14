package org.digitall.apps.projecttask.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

@Deprecated
public class User {

    private int idUser = -1;
    private int idPerson = -1;
    private String userName = "";
    

    public User() {

    }

    public User(int _idUser) {
    
	loadData (_idUser);
    }


    public void setIduser(int _iduser) {
	idUser = _iduser;
    }

    public int getIduser() {
	return idUser;
    }

    public void setIdperson(int _idperson) {
	idPerson = _idperson;
    }

    public int getIdperson() {
	return idPerson;
    }

    public void setUserName(String _userName) {
	userName = _userName;
    }

    public String getUserName() {
	return userName;
    }

    public void loadData(int _idUser ) {

	idUser = _idUser;
	String params = String.valueOf(idUser);
	ResultSet data = LibSQL.exFunction("task.getuser", params);

	try {
	    if (data.next()) {
		
		userName = data.getString("username");
		idPerson = data.getInt("idperson");
		//idUser = data.getInt("estimationtime");
		
		
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

    }

}
