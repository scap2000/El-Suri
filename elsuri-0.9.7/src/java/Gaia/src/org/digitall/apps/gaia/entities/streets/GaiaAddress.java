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
 * GaiaAddress.java
 *
 * */
package org.digitall.apps.gaia.entities.streets;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaAddress {

    private int idAddress = -1;
    private int idstreet = -1;
    private String streetName = "";
    private String number = "";
    private int type = -1;
    private double x;
    private double y;

    public GaiaAddress() {

    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gis.getAddress", "" + idAddress + ",'" + GaiaEnvironment.getScheme() + "'");
	try {
	    if (result.next()) {
		setIdStreet(result.getInt("idcalle"));
		setStreetName(result.getString("calle"));
		setNumber(result.getString("numero"));
	        setType(result.getInt("tipo"));
	        setX(result.getDouble("x"));
	        setY(result.getDouble("y"));
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public boolean delete() {
	return LibSQL.getBoolean("gis.delAddress", "" + idAddress + ",'" + GaiaEnvironment.getScheme() + "'");
    }

    public int saveData() {
	int _result = -1;
	String params = "" + idAddress + "," + type + "," + idstreet + ",'" + number + "'," + x + "," + y + ",'" + GaiaEnvironment.getScheme() + "'";
	_result = LibSQL.getInt("gis.setAddress", params);
	if (_result == -1) {
	    Advisor.messageBox("Error al grabar los datos", "Error");
	} else {
	    idAddress = _result;
	}
	return _result;
    }

    public void setIdAddress(int _idAddress) {
	idAddress = _idAddress;
    }

    public int getIdAddress() {
	return idAddress;
    }

    public void setIdStreet(int _idstreet) {
	this.idstreet = _idstreet;
    }

    public int getIdStreet() {
	return idstreet;
    }

    public void setNumber(String _number) {
	this.number = _number;
    }

    public String getNumber() {
	return number;
    }

    public void setType(int _type) {
	this.type = _type;
    }

    public int getType() {
	return type;
    }

    public void setX(double x) {
	this.x = x;
    }

    public double getX() {
	return x;
    }

    public void setY(double y) {
	this.y = y;
    }

    public double getY() {
	return y;
    }

    public void setStreetName(String streetName) {
	this.streetName = streetName;
    }

    public String getStreetName() {
	return streetName;
    }

}
