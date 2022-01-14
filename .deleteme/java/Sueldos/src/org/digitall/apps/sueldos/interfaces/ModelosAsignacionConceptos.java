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
 * ModelosAsignacionConceptos.java
 *
 * */
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
