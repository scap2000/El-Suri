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
 * GaiaTipoAdminyServ.java
 *
 * */
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
