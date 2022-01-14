package org.digitall.apps.personalfiles.classes;

import java.sql.ResultSet;

import java.util.Vector;

import org.digitall.lib.sql.LibSQL;

public class Dependencia {

    private int idDep = -1;
    private int nivelJerarquico = -1;
    private String nombre = "";
    private int padre = -1;
    private Dependencia dependenciaPadre = null;
    private String misiones = "";
    private String funciones = "";
    private String nombreNivelJerarquico = "";
    private boolean mostrarNombreTipDep = true; 
    
    private Vector<Dependencia> dependenciaHijosVector = new Vector<Dependencia>();    
    
    public Dependencia() {
    
    }
    
    public Dependencia(int _idDep) {	    
	idDep = _idDep;
    }

    public int getIdDep() {
	return idDep;
    }

    public void setIdDep(int _idDep) {
	this.idDep = _idDep;
    }

    public int getNivelJerarquico() {
	return nivelJerarquico;
    }

    public void setNivelJerarquico(int _nivelJerarquico) {
	this.nivelJerarquico = _nivelJerarquico;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String _nombre) {
	this.nombre = _nombre;
    }

    public int getPadre() {
	return padre;
    }

    public void setPadre(int _padre) {
	this.padre = _padre;
    }
    
    //METODOS PARA EL VECTOR DEPENDENCIA-->INICIO
    
    public void loadDependenciasHijos() {
	dependenciaHijosVector.removeAllElements();     
	ResultSet data = LibSQL.exFunction("personalfiles.getAllIdDependencias", idDep);
	try {
	    while (data.next()) {
		dependenciaHijosVector.add(new Dependencia(data.getInt("idDep")));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	} 
	for (int i = 0; i < dependenciaHijosVector.size() ; i++ )  {
	    dependenciaHijosVector.elementAt(i).retrieveData();
	}
    }
    
    public void addDependenciaHijo(Dependencia _dependencia) {
	getDependenciaHijosVector().add(_dependencia);
    } 

    public Dependencia getDependenciaPadre(int _idDep) {
	Dependencia _result = null;
	int i = 0;
	int vecSize = getDependenciaHijosVector().size();
	while ((dependenciaHijosVector.elementAt(i).getIdDep() != _idDep) && (i < vecSize)){
	    i++;
	}
	if (i != vecSize) {
	    _result = dependenciaHijosVector.elementAt(i);
	}
	return _result;
    }
    
    public int getCantDepContine() {
	return dependenciaHijosVector.size();
    }
    
    public void delDependenciaHijo(Dependencia _dependencia) {
	dependenciaHijosVector.remove(_dependencia);
    }
        
    //METODOS PARA EL VECTOR DEPENDENCIA-->FIN
    
    public void setDependenciaPadre(Dependencia _dependencia) {
	dependenciaPadre = _dependencia;
    }
    
    public Dependencia getDependenciaPadre() {
	return dependenciaPadre;
    }

    public int savaData() {
	String params = nivelJerarquico+",'"+nombre+"',"+padre+","+mostrarNombreTipDep;;
	int result = -1;
	if (idDep == -1){
	    result = LibSQL.getInt("personalfiles.addDependencia",params);              
	    idDep = result;	   
	} else {            
	    params = idDep + ",'"+nombre+"'";
	    result = LibSQL.getInt("personalfiles.setDependencia",params);
	}               	
	return result;
    }    
    
   public void retrieveData() {	
	ResultSet data = LibSQL.exFunction("personalfiles.getDependencia", idDep);
	try {
	    if (data.next()) {		
	     	nivelJerarquico = data.getInt("niveljerarquico");
		nombre = data.getString("nombre");
		padre = data.getInt("padre");		
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public int setMisionesYFunciones() {
	String params = idDep+",'" + misiones + "','" + funciones + "'";
	int result = -1;          
	result = LibSQL.getInt("personalfiles.setMisionesYFunciones",params);                  
	return result;
    }

    public Vector<Dependencia> getDependenciaHijosVector() {
	return dependenciaHijosVector;
    }

    public void setDependenciaHijosVector(Vector<Dependencia> _dependenciaHijosVector) {
	this.dependenciaHijosVector = _dependenciaHijosVector;
    }

    public String getMisiones() {
	return misiones;
    }

    public void setMisiones(String _misiones) {
	this.misiones = _misiones;
    }

    public String getFunciones() {
	return funciones;
    }

    public void setFunciones(String _funciones) {
	this.funciones = _funciones;
    }

    public String getNombreNivelJerarquico() {
	return nombreNivelJerarquico;
    }

    public void setNombreNivelJerarquico(String _nombreNivelJerarquico) {
	this.nombreNivelJerarquico = _nombreNivelJerarquico;
    }

    public boolean isMostrarNombreTipDep() {
	return mostrarNombreTipDep;
    }

    public void setMostrarNombreTipDep(boolean _mostrarNombreTipDep) {
	this.mostrarNombreTipDep = _mostrarNombreTipDep;
    }

}
