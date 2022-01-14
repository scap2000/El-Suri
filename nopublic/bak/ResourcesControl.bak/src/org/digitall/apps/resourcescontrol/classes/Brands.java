package org.digitall.apps.resourcescontrol.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class Brands{

    private int idBrand = -1;
    private String name;
    
    public Brands(int _idBrand, String _Name) {
	idBrand = _idBrand;
	name = _Name;
    }
    
    public Brands(int _idBrand) {
	idBrand = _idBrand;
    }

    public Brands() {
    }
    
    public void setIdBrand(int idBrand) {
	this.idBrand = idBrand;
    }

    public int getIdBrand() {
	return idBrand;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void loadData(int _idBrand){
	idBrand = _idBrand;
	String params = String.valueOf(idBrand);
	ResultSet data = LibSQL.exFunction("resourcescontrol.getBrand", params);

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
	    String params = idBrand +",'"+ name +"'";
	    int result = LibSQL.getInt("resourcescontrol.saveBrand", params);
	    if (idBrand == -1){
		idBrand = result;
	    }
	    return result;
	} else {
	    Advisor.messageBox("Debe ingresar un nombre para la Marca", "Nombre no válido");
	    return -1;
	}
    }
}
