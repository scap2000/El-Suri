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
 * Funciones.java
 *
 * */
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
