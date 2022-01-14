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
 * Servicio.java
 *
 * */
package org.digitall.projects.kamar.taller.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class Servicio {

    private int idServicio = -1;
    private String name;
    private double price = 0;
    private int idCategoria = -1;
    
    public Servicio() {
    }
    
    public Servicio(int _idServicio, String _name, double _price) {
	idServicio = _idServicio;
	name = _name;
	price = _price;
    }
    
    public Servicio(int _idServicio, String _name) {
	idServicio = _idServicio;
	name = _name;
    }
    
    public Servicio(int _idServicio) {
	idServicio = _idServicio;
    }

    public void setIdServicio(int idServicio) {
	this.idServicio = idServicio;
    }

    public int getIdServicio() {
	return idServicio;
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void loadData(int _idServicio){
	idServicio = _idServicio;
	ResultSet data = LibSQL.exFunction("taller.getServicio", idServicio);
	try {
	    if (data.next()) {
		name = data.getString("name");
		price = data.getDouble("price");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}	    
    }
    
    public int saveData(){
	if (!name.trim().equals("")){
	    int result = LibSQL.getInt("taller.setServicio", idServicio +",'"+ name +"'" + "," + price + "," + idCategoria);
	    if (idServicio == -1){
		idServicio = result;
	    }
	    return result;
	} else {
	    Advisor.messageBox("Debe ingresar un nombre para el servicio", "Nombre no válido");
	    return -1;
	}
    }

    public void setPrice(double _price) {
	price = _price;
    }

    public double getPrice() {
	return price;
    }

    public void setIdCategoria(int _idCategoria) {
	this.idCategoria = _idCategoria;
    }

    public int getIdCategoria() {
	return idCategoria;
    }
}
