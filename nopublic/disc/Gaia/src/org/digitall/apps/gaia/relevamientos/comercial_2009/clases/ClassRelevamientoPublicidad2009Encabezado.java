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
 * ClassRelevamientoPublicidad2009Encabezado.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ClassRelevamientoPublicidad2009Encabezado {

    private int idRelevamientoPublicidad = -1;
    private int nroEncuestador = 0;
    private String nroZona = "";
    private String nroEncuesta = "";
    private String fecha = "";
    private String usuario = "";
    private String fechaCarga = "";
    private boolean encuestaAsociada = false;
    private ClassRelevamientoPublicidad2009Seccion1 classRelevamientoPublicidad2009Seccion1;
    private ClassRelevamientoPublicidad2009Seccion2 classRelevamientoPublicidad2009Seccion2;
    private ClassDetalleRelevamientoPublicidad classDetalleRelevamientoPublicidad;

    public ClassRelevamientoPublicidad2009Encabezado() {
    }

    public void setIdRelevamientoPublicidad(int idRelevamientoPublicidad) {
	this.idRelevamientoPublicidad = idRelevamientoPublicidad;
    }

    public int getIdRelevamientoPublicidad() {
	return idRelevamientoPublicidad;
    }

    public void setNroEncuestador(int nroEncuestador) {
	this.nroEncuestador = nroEncuestador;
    }

    public int getNroEncuestador() {
	return nroEncuestador;
    }

    public void setNroZona(String nroZona) {
	this.nroZona = nroZona;
    }

    public String getNroZona() {
	return nroZona;
    }

    public void setNroEncuesta(String nroEncuesta) {
	this.nroEncuesta = nroEncuesta;
    }

    public String getNroEncuesta() {
	return nroEncuesta;
    }

    public void setFecha(String fecha) {
	this.fecha = fecha;
    }

    public String getFecha() {
	return fecha;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setFechaCarga(String fechaCarga) {
	this.fechaCarga = fechaCarga;
    }

    public String getFechaCarga() {
	return fechaCarga;
    }

    public void setEncuestaAsociada(boolean encuestaAsociada) {
	this.encuestaAsociada = encuestaAsociada;
    }

    public boolean isEncuestaAsociada() {
	return encuestaAsociada;
    }
    
    public int saveData() {
	int result = -1;
	String params = "";
	if (idRelevamientoPublicidad == -1)  {
	    params = "";
	    result = LibSQL.getInt("gea_salta.addrelevamientopublicidadencabezado", params);
	} else if (idRelevamientoPublicidad > 0){
	    params = "";
	     //result = LibSQL.getInt("gea_salta.addrelevamientopublicidadencabezado", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gea_salta.getencabezadorelevamientopublicidad", idRelevamientoPublicidad);
	try {
	    if (result.next()) {
		setIdRelevamientoPublicidad(result.getInt("idrelevamientopublicidad"));
		setNroEncuestador(result.getInt("nroencuestador"));
		setNroZona(result.getString("nrozona"));
		setNroEncuesta(result.getString("nroencuesta"));
		setFecha(result.getString("fecha"));
		setUsuario(result.getString("usuario"));
		setFechaCarga(result.getString("fechacarga"));
		setEncuestaAsociada(result.getBoolean("anuncioasociada"));
		classRelevamientoPublicidad2009Seccion1 = new ClassRelevamientoPublicidad2009Seccion1(idRelevamientoPublicidad);
		classRelevamientoPublicidad2009Seccion1.retrieveData();
	        classRelevamientoPublicidad2009Seccion2 = new ClassRelevamientoPublicidad2009Seccion2(idRelevamientoPublicidad);
	        classRelevamientoPublicidad2009Seccion2.retrieveData();
	        classDetalleRelevamientoPublicidad = new ClassDetalleRelevamientoPublicidad(idRelevamientoPublicidad);
		classDetalleRelevamientoPublicidad.setIdrelevamientopublicidad(idRelevamientoPublicidad);
	        classDetalleRelevamientoPublicidad.retrieveData_encabezado();
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error en encabezado");
	} catch (NullPointerException e) {
	    // TODO
	}
    }

    public void setClassRelevamientoPublicidad2009Seccion1(ClassRelevamientoPublicidad2009Seccion1 classRelevamientoPublicidad2009Seccion1) {
	this.classRelevamientoPublicidad2009Seccion1 = classRelevamientoPublicidad2009Seccion1;
    }

    public ClassRelevamientoPublicidad2009Seccion1 getClassRelevamientoPublicidad2009Seccion1() {
	return classRelevamientoPublicidad2009Seccion1;
    }

    public void setClassRelevamientoPublicidad2009Seccion2(ClassRelevamientoPublicidad2009Seccion2 classRelevamientoPublicidad2009Seccion2) {
	this.classRelevamientoPublicidad2009Seccion2 = classRelevamientoPublicidad2009Seccion2;
    }

    public ClassRelevamientoPublicidad2009Seccion2 getClassRelevamientoPublicidad2009Seccion2() {
	return classRelevamientoPublicidad2009Seccion2;
    }

    public void setClassDetalleRelevamientoPublicidad(ClassDetalleRelevamientoPublicidad classDetalleRelevamientoPublicidad) {
	this.classDetalleRelevamientoPublicidad = classDetalleRelevamientoPublicidad;
    }

    public ClassDetalleRelevamientoPublicidad getClassDetalleRelevamientoPublicidad() {
	return classDetalleRelevamientoPublicidad;
    }
    
    public void inicializarCartel(){
	classDetalleRelevamientoPublicidad = new ClassDetalleRelevamientoPublicidad();
    }
}
