package org.digitall.lib.org;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class User {

    private int idUser = -1;
    private int idPerson = -1;
    private String userName = "";
    

    public User() {

    }

    public User(String _userName) {
	userName = _userName;
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
	/*ResultSet data = LibSQL.exFunction("task.getuser", params);

	try {
	    if (data.next()) {
		
		userName = data.getString("username");
		idPerson = data.getInt("idperson");
		//idUser = data.getInt("estimationtime");
		
		
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}*/
    }

    public void loadData(String _userName) {
	userName = _userName;
	ResultSet data = LibSQL.exFunction("org.getUser", "'"+ userName +"'");

	try {
	    if (data.next()) {
		idPerson = data.getInt("idperson");
		idUser = data.getInt("iduser");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
}
