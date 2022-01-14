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
 * GaiaClassDelito.java
 *
 * */
package org.digitall.apps.gaia.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.geo.gaia.GaiaEnvironment;
import org.digitall.lib.sql.LibSQL;

public class GaiaClassDelito {

    private int idDelito = -1;
    private double x;
    private double y;
    private int idBarrio = -1;
    private int idCalle1 = -1;
    private int alturaCalle1 = 0;
    private int idCalle2 = -1;
    private int alturaCalle2 = 0;
    private int idMovilidadVictima = 0;
    private int idTipoDelito = 0;
    private String fechaDelito = "";
    private String horaDelito = "";
    private int cantDelincuentes = 0;
    private int cantVictimas = 0;
    private int idMovilidadDelincuentes = 0;

    public GaiaClassDelito() {
    }
    
    public void retrieveData() {
	if (idDelito != -1) {
	    ResultSet result = LibSQL.exFunction(GaiaEnvironment.getScheme() + ".getCasoDelitoObject", idDelito);
	    try {
		if (result.next()) {
		    setX(result.getDouble("x"));
		    setY(result.getDouble("y"));
		    setIdDelito(result.getInt("gid"));
		    setIdBarrio(result.getInt("idbarrio"));
		    setIdCalle1(result.getInt("idcalle1"));
		    setAlturaCalle1(result.getInt("alturacalle1"));
		    setIdCalle2(result.getInt("idcalle2"));
		    setAlturaCalle2(result.getInt("alturacalle2"));
		    setIdMovilidadVictima(result.getInt("idmovilidadvictimas"));
		    setIdTipoDelito(result.getInt("idtipodelito"));
		    setFechaDelito(result.getString("fecha"));
		    setHoraDelito(result.getString("hora"));
		    setCantDelincuentes(result.getInt("cantdelincuentes"));
		    setCantVictimas(result.getInt("cantvictimas"));
		    setIdMovilidadDelincuentes(result.getInt("idmovilidaddelincuentes"));
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
    }

    public boolean delete() {
	return LibSQL.getBoolean(GaiaEnvironment.getScheme() + ".delCasoDelitoObject", idDelito);
    }

    public int saveData() {
	int _result = -1;
	String params = idDelito + ", " + x + ", " + y + ", " + idBarrio + "," + idCalle1 + "," +
			alturaCalle1 +","+ idCalle2 + ", " + alturaCalle2 +" , "+ idMovilidadVictima +" , "+ 
			idTipoDelito + ", '" + fechaDelito + "','" + horaDelito + "',"+ 
			cantDelincuentes +","+ cantVictimas +","+ idMovilidadDelincuentes ;
	_result = LibSQL.getInt(GaiaEnvironment.getScheme() + ".setCasoDelitoObject", params);
	if (_result == -1) {
	    Advisor.messageBox("Error al grabar los datos", "Error");
	} else {
	    idDelito = _result;
	}
	return _result;
    }

    

    public void setIdDelito(int idDelito) {
	this.idDelito = idDelito;
    }

    public int getIdDelito() {
	return idDelito;
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

    public void setIdBarrio(int idBarrio) {
	this.idBarrio = idBarrio;
    }

    public int getIdBarrio() {
	return idBarrio;
    }

    public void setIdCalle1(int idCalle1) {
	this.idCalle1 = idCalle1;
    }

    public int getIdCalle1() {
	return idCalle1;
    }

    public void setAlturaCalle1(int alturaCalle1) {
	this.alturaCalle1 = alturaCalle1;
    }

    public int getAlturaCalle1() {
	return alturaCalle1;
    }

    public void setIdCalle2(int idCalle2) {
	this.idCalle2 = idCalle2;
    }

    public int getIdCalle2() {
	return idCalle2;
    }

    public void setAlturaCalle2(int alturaCalle2) {
	this.alturaCalle2 = alturaCalle2;
    }

    public int getAlturaCalle2() {
	return alturaCalle2;
    }

    public void setIdMovilidadVictima(int idMovilidadVictima) {
	this.idMovilidadVictima = idMovilidadVictima;
    }

    public int getIdMovilidadVictima() {
	return idMovilidadVictima;
    }

    public void setIdTipoDelito(int idTipoDelito) {
	this.idTipoDelito = idTipoDelito;
    }

    public int getIdTipoDelito() {
	return idTipoDelito;
    }

    public void setFechaDelito(String fechaDelito) {
	this.fechaDelito = fechaDelito;
    }

    public String getFechaDelito() {
	return fechaDelito;
    }

    public void setHoraDelito(String horaDelito) {
	this.horaDelito = horaDelito;
    }

    public String getHoraDelito() {
	return horaDelito;
    }

    public void setCantDelincuentes(int cantDelincuentes) {
	this.cantDelincuentes = cantDelincuentes;
    }

    public int getCantDelincuentes() {
	return cantDelincuentes;
    }

    public void setCantVictimas(int cantVictimas) {
	this.cantVictimas = cantVictimas;
    }

    public int getCantVictimas() {
	return cantVictimas;
    }

    public void setIdMovilidadDelincuentes(int idMovilidadDelincuentes) {
	this.idMovilidadDelincuentes = idMovilidadDelincuentes;
    }

    public int getIdMovilidadDelincuentes() {
	return idMovilidadDelincuentes;
    }
}
