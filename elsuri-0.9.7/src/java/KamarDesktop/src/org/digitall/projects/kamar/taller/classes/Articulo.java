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
 * Articulo.java
 *
 * */
package org.digitall.projects.kamar.taller.classes;

import java.sql.ResultSet;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class Articulo {

    private int idArticulo = -1;
    private int idExterno = -1;
    private String nombre;
    private double precio = 0;
    
    public Articulo() {
    }

    public Articulo(int _idArticulo, String _nombre, double _price) {
	this(_idArticulo, -1, _nombre, _price);
    }
    
    public Articulo(int _idArticulo, int _idExterno, String _nombre, double _price) {
	idArticulo = _idArticulo;
	idExterno = _idExterno;
	nombre = _nombre;
	precio = _price;
    }
    
    public Articulo(int _idArticulo) {
	idArticulo = _idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
	this.idArticulo = idArticulo;
    }

    public int getIdArticulo() {
	return idArticulo;
    }

    public void setNombre(String _nombre) {
	nombre = _nombre;
    }

    public String getNombre() {
	return nombre;
    }

    public void loadData(int _idArticulo){
	idArticulo = _idArticulo;
	ResultSet data = LibSQL.exFunction("taller.getArticulo", idArticulo);
	try {
	    if (data.next()) {
	        idExterno = data.getInt("idexterno");
	        nombre = data.getString("nombre");
		precio = data.getDouble("precio");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}	    
    }
    
    public int saveData(){
	if (!nombre.trim().equals("")){
	    int result = LibSQL.getInt("taller.setArticulo", idArticulo + "," + idExterno +",'"+ nombre +"'" + "," + precio);
	    if (idArticulo == -1){
		idArticulo = result;
	    }
	    return result;
	} else {
	    Advisor.messageBox("Debe ingresar un nombre para el artículo", "Nombre no válido");
	    return -1;
	}
    }

    public void setPrecio(double _price) {
	precio = _price;
    }

    public double getPrecio() {
	return precio;
    }

    public void setIdExterno(int idExterno) {
	this.idExterno = idExterno;
    }

    public int getIdExterno() {
	return idExterno;
    }
}
