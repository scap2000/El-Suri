package org.digitall.common.cashflow.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class Activity{

    private int idActivity = -1;
    private String name;
    
    public Activity(int _idActivity, String _Name) {
	idActivity = _idActivity;
	name = _Name;
    }
    
    public Activity(int _idActivity) {
	idActivity = _idActivity;
    }

    public Activity() {
    }
    
    public void setIdActivity(int idActivity) {
	this.idActivity = idActivity;
    }

    public int getIdActivity() {
	return idActivity;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void loadData(int _idActivity){
	idActivity = _idActivity;
	String params = String.valueOf(idActivity);
	ResultSet data = LibSQL.exFunction("tabs.getActivity", params);

	try {
	    if (data.next()) {
		
		name = data.getString("name");
				
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}	    
    }
    
    public int saveData(){
	if (!name.trim().equals("")){
	    String params = idActivity +",'"+ name +"'";
	    int result = LibSQL.getInt("tabs.saveActivity", params);
	    if (idActivity == -1){
		idActivity = result;
	    }
	    return result;
	} else {
	    Advisor.messageBox("Debe ingresar un nombre para la Actividad/Centro de Costos/Concepto", "Nombre no válido");
	    return -1;
	}
    }
}
