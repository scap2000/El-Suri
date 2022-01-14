/**
 LIMITACIÃN DE RESPONSABILIDAD - COPYRIGHT - [EspaÃ±ol]
 ================================================================================
 KamarDesktop - Entorno JAVA de Trabajo y Desarrollo para Taller de Servicios Kamar
 ================================================================================

 Copyright (C) 2011 Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 La copia y distribuciÃ³n de este archivo, con o sin modificaciones,
 estÃ¡n permitidas por cualquier medio sin cargo mientras se preserven
 el Aviso de Copyright y este mismo aviso.

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los tÃ©rminos de la Licencia PÃºblica General GNU publicada
 por la FundaciÃ³n para el Software Libre, ya sea la versiÃ³n 3
 de la Licencia, o (a su elecciÃ³n) cualquier versiÃ³n posterior.

 Este programa se distribuye con la esperanza de que sea Ãºtil, pero
 SIN GARANTÃA ALGUNA; ni siquiera la garantÃ­a implÃ­cita
 MERCANTIL o de APTITUD PARA UN PROPÃSITO DETERMINADO.
 Consulte los detalles de la Licencia PÃºblica General GNU para obtener
 una informaciÃ³n mÃ¡s detallada.

 DeberÃ­a haber recibido una copia de la Licencia PÃºblica General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
KamarDesktop - JAVA Management & Development Environment for Kamar Mechanical Services
 =====================================================================================

 Copyright (C) 2011 by Lic. Santiago Cassina (scap2000@yahoo.com)
 http://www.scassi.com.ar

 Copying and distribution of this file, with or without modification,
 are permitted in any medium without royalty provided the copyright
 notice and this notice are preserved.

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
 * Vehiculo.java
 *
 * */
package org.digitall.projects.kamar.taller.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class Vehiculo {

    private int idVehiculo = -1;
    private String numerocuadro = "";
    private String anio = "";
    private String marca = "";
    private int idTipoVehiculo = 0;
    private boolean interna = true;
    
    public Vehiculo() {
    }
    
    public Vehiculo(int _idVehiculo) {
	idVehiculo = _idVehiculo;
    }
    
    public Vehiculo(int _idVehiculo, String _numerocuadro, String _anio, String _marca, int _idTipoVehiculo, boolean _interna) {
	idVehiculo = _idVehiculo;
	numerocuadro = _numerocuadro;
	anio = _anio;
	marca = _marca;
	idTipoVehiculo = _idTipoVehiculo;
	interna = _interna;
    }

    public void setIdVehiculo(int idVehiculo) {
	this.idVehiculo = idVehiculo;
    }

    public int getIdVehiculo() {
	return idVehiculo;
    }

    public void loadData(int _idVehiculo){
	idVehiculo = _idVehiculo;
	ResultSet data = LibSQL.exFunction("taller.getDatosVehiculo", idVehiculo);
	try {
	    if (data.next()) {
	        numerocuadro = data.getString("numerocuadro");
	        anio = data.getString("anio");
	        marca = data.getString("marca");
	        idTipoVehiculo = data.getInt("idtipovehiculo");
		interna = data.getBoolean("interna");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}	    
    }
    
    public int saveData(){
	if (!numerocuadro.trim().equals("")){
	    int result = LibSQL.getInt("taller.setVehiculo", idVehiculo + ",'" + numerocuadro + "','" + anio+ "','" + marca + "'," + idTipoVehiculo + "," + interna);
	    if (idVehiculo == -1){
		idVehiculo = result;
	    }
	    return result;
	} else {
	    Advisor.messageBox("Debe ingresar un número de cuadro para el vehículo", "Vehículo no válido");
	    return -1;
	}
    }

    public void setNumerocuadro(String _numerocuadro) {
	numerocuadro = _numerocuadro;
    }

    public String getNumerocuadro() {
	return numerocuadro;
    }

    public void setAnio(String anio) {
	this.anio = anio;
    }

    public String getAnio() {
	return anio;
    }

    public void setMarca(String marca) {
	this.marca = marca;
    }

    public String getMarca() {
	return marca;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
	this.idTipoVehiculo = idTipoVehiculo;
    }

    public int getIdTipoVehiculo() {
	return idTipoVehiculo;
    }
}
