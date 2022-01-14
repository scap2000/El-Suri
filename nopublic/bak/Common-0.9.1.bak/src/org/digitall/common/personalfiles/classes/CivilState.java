package org.digitall.common.personalfiles.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class CivilState {
    
    private int idCivilState = -1;
    private String name = "";
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * No hace falta el atributo estado...
     * */
    private String estado = "";

    public CivilState() {
    
    }
    
    
    public int saveData() {        
	/* 2009-09-17 (santiago) Mensaje para el codificador
	 * Se está enviando el atributo estado con el VALOR FIJO '' en lugar
	 * del valor del atributo... Error...
	 * */

	String params = idCivilState+",'"+name+"',''";
	int result = -1;
	if (idCivilState == -1){
	    result = LibSQL.getInt("personalfiles.addCivilState",params);              
	    idCivilState = result;      
	} else {            
	    params = idCivilState+","+ params;
	    result = LibSQL.getInt("personalstate.setCivilState",params);
	}               
	return result;
    }
    
    public void retrieveData() {
    
	/* 2009-09-17 (santiago) Mensaje para el codificador
	 * No hace falta instanciar String params, ya que LibSQL.exFunction acepta objetos como parámetros
	 * */
	String params = ""+idCivilState;
	ResultSet data = LibSQL.exFunction("personalfiles.getCivilState", params);
	try {
	    if (data.next()) {
		idCivilState  = data.getInt("idCivilState");
		name = data.getString("name");
		estado  = data.getString("estado");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public void setIdcivilstate(int idcivilstate) {
	this.idCivilState = idcivilstate;
    }

    public int getIdcivilstate() {
	return idCivilState;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
}
