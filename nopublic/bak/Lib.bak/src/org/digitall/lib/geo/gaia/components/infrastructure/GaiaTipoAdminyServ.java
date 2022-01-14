package org.digitall.lib.geo.gaia.components.infrastructure;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class GaiaTipoAdminyServ{

    private int idTipoAdminyServ = -1;
    private String nombre;
    
    public GaiaTipoAdminyServ(int _idTipoAdminyServ, String _Nombre) {
	idTipoAdminyServ = _idTipoAdminyServ;
	nombre = _Nombre;
    }
    
    public GaiaTipoAdminyServ(int _idTipoAdminyServ) {
	idTipoAdminyServ = _idTipoAdminyServ;
    }

    public GaiaTipoAdminyServ() {
    }
    
    public void setIdActivity(int idTipoAdminyServ) {
	this.idTipoAdminyServ = idTipoAdminyServ;
    }

    public int getIdTipoAdminyServ() {
	return idTipoAdminyServ;
    }

    public void setNombre(String _nombre) {
	nombre = _nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void loadData(int _idTipoAdminyServ){
	idTipoAdminyServ = _idTipoAdminyServ;
	String params = String.valueOf(idTipoAdminyServ);
	ResultSet data = LibSQL.exFunction("tabs.getTipoAdminyServ", params);

	try {
	    if (data.next()) {
		
		nombre = data.getString("nombre");
				
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}	    
    }
    
    public int saveData(){
	if (!nombre.trim().equals("")){
	    String params = idTipoAdminyServ +",'"+ nombre +"'";
	    int result = LibSQL.getInt("tabs.saveTipoAdminyServ", params);
	    if (idTipoAdminyServ == -1){
		idTipoAdminyServ = result;
	    }
	    return result;
	} else {
	    Advisor.messageBox("Debe ingresar un nombre para el Tipo de Actividad o Servicio", "Nombre no válido");
	    return -1;
	}
    }
}
