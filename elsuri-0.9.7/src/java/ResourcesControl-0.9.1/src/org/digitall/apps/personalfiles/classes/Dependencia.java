/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * Dependencia.java
 *
 * */
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
