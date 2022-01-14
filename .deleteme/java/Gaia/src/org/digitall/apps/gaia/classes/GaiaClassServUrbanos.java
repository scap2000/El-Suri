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
 * GaiaClassServUrbanos.java
 *
 * */
package org.digitall.apps.gaia.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaClassServUrbanos {

    private int idServUrbano = -1;
    private double x;
    private double y;
    private int catastro = 0;
    private int idTipoServUrbano = 0;
    private String nombre = "";
    private String responsable = "";
    private String telefono1 = "";
    private String telefono2 = "";
    private String fax = "";
    
    public GaiaClassServUrbanos() {
	super();
    }

    public void retrieveData() {
        if (idServUrbano != -1) {
            ResultSet result = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getServUrbanoObject", idServUrbano);
            try {
                if (result.next()) {
                    setX(result.getDouble("x"));
                    setY(result.getDouble("y"));
                    setCatastro(result.getInt("catastro"));
                    setIdTipoServUrbano(result.getInt("idtiposervurbano"));
                    setNombre(result.getString("nombre"));
                    setResponsable(result.getString("responsable"));
                    setTelefono1(result.getString("telefono1"));
                    setTelefono2(result.getString("telefono2"));
                    setFax(result.getString("fax"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean delete() {
	return LibSQL.getBoolean(GaiaEnvironment.getScheme() + ".delServUrbanoObject", idServUrbano);
    }

    public int saveData() {
	int _result = -1;
	String params = idServUrbano + ", " + x + ", " + y + ", " + catastro + ", " + idTipoServUrbano
		    + ", '" + responsable + "', ' " + telefono1 + "', '" + telefono2 + "', '" + fax + "','"+ nombre +"'";
	_result = LibSQL.getInt(GaiaEnvironment.getScheme() + ".setServUrbanoObject", params);
	if (_result == -1) {
	    Advisor.messageBox("Error al grabar los datos", "Error");
	} else {
	    idServUrbano = _result;
	}
	return _result;
    }


    public void setIdServUrbano(int idServUrbano) {
	this.idServUrbano = idServUrbano;
    }

    public int getIdServUrbano() {
	return idServUrbano;
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

    public void setCatastro(int catastro) {
	this.catastro = catastro;
    }

    public int getCatastro() {
	return catastro;
    }

    public void setIdTipoServUrbano(int idTipoServUrbano) {
	this.idTipoServUrbano = idTipoServUrbano;
    }

    public int getIdTipoServUrbano() {
	return idTipoServUrbano;
    }

    
    public void setResponsable(String responsable) {
	this.responsable = responsable;
    }

    public String getResponsable() {
	return responsable;
    }

    public void setTelefono1(String telefono1) {
	this.telefono1 = telefono1;
    }

    public String getTelefono1() {
	return telefono1;
    }

    public void setTelefono2(String telefono2) {
	this.telefono2 = telefono2;
    }

    public String getTelefono2() {
	return telefono2;
    }

    
    public void setFax(String fax) {
	this.fax = fax;
    }

    public String getFax() {
	return fax;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
