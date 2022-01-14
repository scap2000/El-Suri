package org.digitall.projects.apps.dbadmin_091.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.digitall.lib.sql.LibSQL;

public class Funciones {

    private int idFuncion = -1;
    private int idModulo = -1;
    private int numeroFuncion = -1;
    private String nombre = "";
    private String descripcion = "";
    private String grupo = "";
    private String estado = "";
    private Modulos modulo = new Modulos();
    
    public Funciones() {
    
    }

    public int saveData() {
	int resultado = -1;
	int idfn = -1;
	String params = "";
	if (idFuncion == -1){//insertar
	    params = ""+idFuncion+","+idModulo+","+numeroFuncion+",'"+nombre+"','"+descripcion+"','"+grupo+"','"+estado+"'";
	    idfn = LibSQL.getInt("admin.addfuncion",params);
	    resultado = 0;
	} else {           
	    params = ""+idFuncion+","+idModulo+","+numeroFuncion+",'"+nombre+"','"+descripcion+"','"+grupo+"','"+estado+"'";
	    idfn = LibSQL.getInt("admin.addfuncion",params);
	    resultado = 1;
	}
	return resultado;
    }
    
    public void retrieveData() {
	//ResultSet data = LibSQL.exQuery("SELECT * FROM admin.funciones WHERE funciones.idfuncion = "+_idFuncion+" AND estado <> '*'");
	String params = ""+ idFuncion;
	ResultSet data = LibSQL.exFunction("admin.getfuncion",params);
	try {
	    if (data.next()) {
		idFuncion = data.getInt("idfuncion");
		idModulo  = data.getInt("idmodulo");
		numeroFuncion = data.getInt("numerofuncion");
		nombre = data.getString("nombre");
		descripcion = data.getString("descripcion");
		grupo = data.getString("grupo");
		estado  = data.getString("estado");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public void retrieveData(String _nombreFuncion, int _idModulo) {
	modulo = new Modulos();
	modulo.setIdModulo(_idModulo);
	modulo.retrieveData();
	ResultSet data = LibSQL.exQuery("SELECT * FROM admin.funciones WHERE funciones.nombre = '"+ _nombreFuncion +"' AND funciones.idmodulo = " + modulo.getIdModulo() + " AND estado <> '*'");
	try {
	    if (data.next()) {
		idFuncion = data.getInt("idfuncion");
		idModulo  = data.getInt("idmodulo");
		numeroFuncion = data.getInt("numerofuncion");
		nombre = data.getString("nombre");
		descripcion = data.getString("descripcion");
		grupo = data.getString("grupo");
		estado  = data.getString("estado");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdFuncion(int idFuncion) {
	this.idFuncion = idFuncion;
    }

    public int getIdFuncion() {
	return idFuncion;
    }

    public void setIdModulo(int idModulo) {
	this.idModulo = idModulo;
    }

    public int getIdModulo() {
	return idModulo;
    }

    public void setNumeroFuncion(int numeroFuncion) {
	this.numeroFuncion = numeroFuncion;
    }

    public int getNumeroFuncion() {
	return numeroFuncion;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setGrupo(String grupo) {
	this.grupo = grupo;
    }

    public String getGrupo() {
	return grupo;
    }
    
    public int getSiguienteNumeroFuncion(int _idModulo){
	String params = ""+ _idModulo;
	int result = LibSQL.getInt("admin.getmaxnumberfunction",params);
	return result;
    }
    
    public int getIdFunction(String _nombreFuncion){
	String consulta = "SELECT idfuncion FROM admin.funciones WHERE funciones.nombre = '"+_nombreFuncion+"'";
	ResultSet result = LibSQL.exQuery(consulta);
	int retorno = -1;
	try {
	    if(result.next()){
		retorno = result.getInt("idfuncion");
	    }
	} catch (SQLException e) {
	    
	}
	return retorno;
    }
    
    public String getNombreModulo(int _idModulo){
	Modulos modulo = new Modulos();
	modulo.setIdModulo(_idModulo);
	modulo.retrieveData();
	return modulo.getNombre();
    }

    public void setModulo(Modulos modulo) {
	this.modulo = modulo;
    }

    public Modulos getModulo() {
	return modulo;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
}
