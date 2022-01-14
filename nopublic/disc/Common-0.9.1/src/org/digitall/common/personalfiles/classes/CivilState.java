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
 * CivilState.java
 *
 * */
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
