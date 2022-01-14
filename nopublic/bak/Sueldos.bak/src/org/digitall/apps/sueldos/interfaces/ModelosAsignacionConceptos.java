package org.digitall.apps.sueldos.interfaces;

import java.sql.ResultSet;
import org.digitall.lib.sql.LibSQL;

public class ModelosAsignacionConceptos {
    
    private int idModelo = -1;
    private int idConcepto = -1;
    private String usuario = ""; //usuario al que pertenece el modelo
    private String estado = "";

    public ModelosAsignacionConceptos() {
	
    }
    
    public int saveData() {             
	String params = idModelo+","+idConcepto+",'"+usuario+"',''";
	int result = -1;
	if (idModelo == -1){
	    result = LibSQL.getInt("sueldos.addModelos",params);              
	} else {            
	    params = idModelo+","+ params;
	    result = LibSQL.getInt("sueldos.setModelos",params);
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = ""+idModelo;
	ResultSet data = LibSQL.exFunction("sueldos.getModelo", params);
	try {
	    if (data.next()) {
		idModelo  = data.getInt("idmodelo");
		idConcepto = data.getInt("idconcepto");
		usuario = data.getString("user");
		estado  = data.getString("estado");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public int delData(){
	int result = -1;
	String params = ""+idModelo;
	result = LibSQL.getInt("sueldos.delModelo", params);
	return(result);
    }

    public void setidModelo(int idModelo) {
	this.idModelo = idModelo;
    }

    public int getidModelo() {
	return idModelo;
    }

    public void setidConcepto(int idConcepto) {
	this.idConcepto = idConcepto;
    }

    public int getidConcepto() {
	return idConcepto;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
}
