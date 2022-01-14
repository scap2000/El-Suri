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
 * ClassCensoComercial2009Seccion5.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ClassCensoComercial2009Seccion5 {

    private int idEncuestaComercial = -1;
    private String f5a = "";
    private String f5b = "";
    private String f5c = "";
    private String f5d = "";
    private String f5e = "";
    private String f5f = "";
    private String f5g = "";
    private String f6 = "";
    private String estado = "";	

    public ClassCensoComercial2009Seccion5() {
    
    }

    public ClassCensoComercial2009Seccion5(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }
    
    public void setIdEncuestaComercial(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }

    public int getIdEncuestaComercial() {
	return idEncuestaComercial;
    }

    public void setF5a(String f5a) {
	this.f5a = f5a;
    }

    public String getF5a() {
	return f5a;
    }

    public void setF5b(String f5b) {
	this.f5b = f5b;
    }

    public String getF5b() {
	return f5b;
    }

    public void setF5c(String f5c) {
	this.f5c = f5c;
    }

    public String getF5c() {
	return f5c;
    }

    public void setF5d(String f5d) {
	this.f5d = f5d;
    }

    public String getF5d() {
	return f5d;
    }

    public void setF5e(String f5e) {
	this.f5e = f5e;
    }

    public String getF5e() {
	return f5e;
    }

    public void setF5f(String f5f) {
	this.f5f = f5f;
    }

    public String getF5f() {
	return f5f;
    }

    public void setF5g(String f5g) {
	this.f5g = f5g;
    }

    public String getF5g() {
	return f5g;
    }

    public void setF6(String f6) {
	this.f6 = f6;
    }

    public String getF6() {
	return f6;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
    
    public int saveData() {
	int result = -1;
	String params = "";
	if (idEncuestaComercial == -1)  {
	    params = "";
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion5", params);
	} else if (idEncuestaComercial > 0){
	    params = "";
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion5", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gea_salta.getseccion5censocomercial", idEncuestaComercial);
	try {
	    if (result.next()) {
		setIdEncuestaComercial(result.getInt("idencuestacomercial"));
		setF5a(result.getString("f5a"));
	        setF5b(result.getString("f5b"));
	        setF5c(result.getString("f5c"));
	        setF5d(result.getString("f5d"));
	        setF5e(result.getString("f5e"));
	        setF5f(result.getString("f5f"));
	        setF5g(result.getString("f5g"));
	        setF6(result.getString("f6"));
		setEstado(result.getString("estado"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }
}
