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
 * GaiaStreet.java
 *
 * */
package org.digitall.apps.gaia.entities.streets;

import java.sql.ResultSet;

import org.digitall.common.resourcescontrol.classes.Street;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaStreet extends Street {

    private int idPolyline = -1;

    public GaiaStreet() {
    }

    public void setIdPolyline(int _idPolyline) {
	this.idPolyline = _idPolyline;
    }

    public int getIdPolyline() {
	return idPolyline;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gis.getStreet", "" + getIdstreet() + "," + idPolyline + ",'" + GaiaEnvironment.getScheme() + "'");
	try {
	    if (result.next()) {
		setIdstreet(result.getInt("idstreet"));
	        setIdLocation(result.getInt("idlocation"));
	        setName(result.getString("name"));
	        setCode(result.getString("code"));
	        setType(result.getInt("type"));
		setIdPolyline(result.getInt("gid"));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public int saveData() {
	int _result = -1;
	if (idPolyline != -1 && saveStreetData() != -1)  {
	    String params = "" + getIdstreet() + "," + idPolyline + ",'" + GaiaEnvironment.getScheme() + "'";
	    _result = LibSQL.getInt("gis.setStreetPolyline", params);
	    if ( _result == -1) {
		Advisor.messageBox("Error al grabar los datos", "Error");
	    }
	}
	return _result;
    }
    
    public int saveStreetData() {
	return super.saveData();
    }

}
