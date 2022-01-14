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
 * ClassCensoComercial2009Encabezado.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ClassCensoComercial2009Encabezado {

    private int idEncuestaComercial = -1;
    private int nroEncuestador = 0;
    private String nroZona = "";
    private String nroEncuesta = "";
    private String fecha = "";
    private String usuario = "";
    private String fechaCarga = "";
    private String estado = "";
    private String barcode = "";
    private ClassCensoComercial2009Seccion1 classCensoComercial2009Seccion1;
    private ClassCensoComercial2009Seccion2a classCensoComercial2009Seccion2a;
    private ClassCensoComercial2009Seccion2b classCensoComercial2009Seccion2b;
    private ClassCensoComercial2009Seccion3 classCensoComercial2009Seccion3;
    private ClassCensoComercial2009Seccion4 classCensoComercial2009Seccion4;
    private ClassCensoComercial2009Seccion5 classCensoComercial2009Seccion5;
    
    public ClassCensoComercial2009Encabezado(){
	
    }

    public void setIdEncuestaComercial(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }

    public int getIdEncuestaComercial() {
	return idEncuestaComercial;
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

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setClassCensoComercial2009Seccion1(ClassCensoComercial2009Seccion1 classCensoComercial2009Seccion1) {
	this.classCensoComercial2009Seccion1 = classCensoComercial2009Seccion1;
    }

    public ClassCensoComercial2009Seccion1 getClassCensoComercial2009Seccion1() {
	return classCensoComercial2009Seccion1;
    }

    public void setClassCensoComercial2009Seccion2a(ClassCensoComercial2009Seccion2a classCensoComercial2009Seccion2a) {
	this.classCensoComercial2009Seccion2a = classCensoComercial2009Seccion2a;
    }

    public ClassCensoComercial2009Seccion2a getClassCensoComercial2009Seccion2a() {
	return classCensoComercial2009Seccion2a;
    }

    public void setClassCensoComercial2009Seccion2b(ClassCensoComercial2009Seccion2b classCensoComercial2009Seccion2b) {
	this.classCensoComercial2009Seccion2b = classCensoComercial2009Seccion2b;
    }

    public ClassCensoComercial2009Seccion2b getClassCensoComercial2009Seccion2b() {
	return classCensoComercial2009Seccion2b;
    }

    public void setClassCensoComercial2009Seccion3(ClassCensoComercial2009Seccion3 classCensoComercial2009Seccion3) {
	this.classCensoComercial2009Seccion3 = classCensoComercial2009Seccion3;
    }

    public ClassCensoComercial2009Seccion3 getClassCensoComercial2009Seccion3() {
	return classCensoComercial2009Seccion3;
    }

    public void setClassCensoComercial2009Seccion4(ClassCensoComercial2009Seccion4 classCensoComercial2009Seccion4) {
	this.classCensoComercial2009Seccion4 = classCensoComercial2009Seccion4;
    }

    public ClassCensoComercial2009Seccion4 getClassCensoComercial2009Seccion4() {
	return classCensoComercial2009Seccion4;
    }

    public void setClassCensoComercial2009Seccion5(ClassCensoComercial2009Seccion5 classCensoComercial2009Seccion5) {
	this.classCensoComercial2009Seccion5 = classCensoComercial2009Seccion5;
    }

    public ClassCensoComercial2009Seccion5 getClassCensoComercial2009Seccion5() {
	return classCensoComercial2009Seccion5;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }
    
    public int saveData() {
	int result = -1;
	String params = "";
	if (idEncuestaComercial == -1)  {
	    params = "";
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009encabezado", params);
	} else if (idEncuestaComercial > 0){
	    params = "";
	    //result = LibSQL.getInt("gea_salta.addencuestacomercial2009encabezado", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gea_salta.getencabezadocensocomercial", idEncuestaComercial);
	try {
	    if (result.next()) {
		setIdEncuestaComercial(result.getInt("idencuestacomercial"));
		setNroEncuestador(result.getInt("nroencuestador"));
		setNroZona(result.getString("nrozona"));
		setNroEncuesta(result.getString("nroencuesta"));
		setFecha(result.getString("fecha"));
		setUsuario(result.getString("usuario"));
		setFechaCarga(result.getString("fechacarga"));
                setBarcode(result.getString("barcode"));
	        classCensoComercial2009Seccion1 = new ClassCensoComercial2009Seccion1(idEncuestaComercial);
		classCensoComercial2009Seccion1.retrieveData();
	        classCensoComercial2009Seccion2a = new ClassCensoComercial2009Seccion2a(idEncuestaComercial);
	        classCensoComercial2009Seccion2a.retrieveData();
	        classCensoComercial2009Seccion2b = new ClassCensoComercial2009Seccion2b(idEncuestaComercial);
	        classCensoComercial2009Seccion2b.retrieveData();
	        classCensoComercial2009Seccion3 = new ClassCensoComercial2009Seccion3(idEncuestaComercial);
	        classCensoComercial2009Seccion3.retrieveData();
	        classCensoComercial2009Seccion4 = new ClassCensoComercial2009Seccion4(idEncuestaComercial);
	        classCensoComercial2009Seccion4.retrieveData();
	        classCensoComercial2009Seccion5 = new ClassCensoComercial2009Seccion5(idEncuestaComercial);
	        classCensoComercial2009Seccion5.retrieveData();
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }
    
     public void retrieveData_encabezado() {
	String params = ""+nroEncuestador+",'"+nroZona+"','"+nroEncuesta+"'";
	 ResultSet result = LibSQL.exFunction("gea_salta.getencabezadocensocomercial", params);
	 try {
	     if (result.next()) {
		 setIdEncuestaComercial(result.getInt("idencuestacomercial"));
		 setNroEncuestador(result.getInt("nroencuestador"));
		 setNroZona(result.getString("nrozona"));
		 setNroEncuesta(result.getString("nroencuesta"));
		 setFecha(result.getString("fecha"));
		 setUsuario(result.getString("usuario"));
		 setFechaCarga(result.getString("fechacarga"));
		 classCensoComercial2009Seccion1 = new ClassCensoComercial2009Seccion1(idEncuestaComercial);
		 classCensoComercial2009Seccion1.retrieveData();
		 classCensoComercial2009Seccion2a = new ClassCensoComercial2009Seccion2a(idEncuestaComercial);
		 classCensoComercial2009Seccion2a.retrieveData();
		 classCensoComercial2009Seccion2b = new ClassCensoComercial2009Seccion2b(idEncuestaComercial);
		 classCensoComercial2009Seccion2b.retrieveData();
		 classCensoComercial2009Seccion3 = new ClassCensoComercial2009Seccion3(idEncuestaComercial);
		 classCensoComercial2009Seccion3.retrieveData();
		 classCensoComercial2009Seccion4 = new ClassCensoComercial2009Seccion4(idEncuestaComercial);
		 classCensoComercial2009Seccion4.retrieveData();
		 classCensoComercial2009Seccion5 = new ClassCensoComercial2009Seccion5(idEncuestaComercial);
		 classCensoComercial2009Seccion5.retrieveData();
	     }
	     
	 } catch (SQLException e) {
	     // TODO
	     System.out.println("error");
	 } catch (NullPointerException e) {
	     // TODO
	 }
     }

    
}

