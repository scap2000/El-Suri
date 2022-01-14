package org.digitall.lib.geo.gaia.components.infrastructure;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class GaiaTipoActividadAdminyServ{

    private int idTipoActividad = -1;
    private String nombre;
    
    public GaiaTipoActividadAdminyServ(int _idTipoActividad, String _Nombre) {
	idTipoActividad = _idTipoActividad;
	nombre = _Nombre;
    }
    
    public GaiaTipoActividadAdminyServ(int _idTipoActividad) {
	idTipoActividad = _idTipoActividad;
    }

    public GaiaTipoActividadAdminyServ() {
    }
    
    public void setIdTipoActividadAdminyServ(int idTipoActividad) {
	this.idTipoActividad = idTipoActividad;
    }

    public int getIdTipoActividadAdminyServ() {
	return idTipoActividad;
    }

    public void setNombre(String _nombre) {
	nombre = _nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void loadData(int _idTipoActividadAdminyserv){
	idTipoActividad = _idTipoActividadAdminyserv;
	String params = String.valueOf(idTipoActividad);
	ResultSet data = LibSQL.exFunction("tabs.getTipoActividadAdminyServ", params);

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
	    String params = idTipoActividad +",'"+ nombre +"'";
            System.out.println("SELECT tabs.saveTipoActividadAdminyServ("+ params +")");
	    int result = LibSQL.getInt("tabs.saveTipoActividadAdminyServ", params);
	    if (idTipoActividad == -1){
		idTipoActividad = result;
	    }
	    return result;
	} else {
	    Advisor.messageBox("Debe ingresar un nombre para el Tipo de Actividad", "Nombre no válido");
	    return -1;
	}
    }
}
