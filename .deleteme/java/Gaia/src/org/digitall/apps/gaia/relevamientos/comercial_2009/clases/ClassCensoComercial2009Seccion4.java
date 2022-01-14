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
 * ClassCensoComercial2009Seccion4.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ClassCensoComercial2009Seccion4 {
    
    private int idEncuestaComercial = -1;
    private String f4a = "";
    private String f4b = "";     
    private String f4c1_a = "";
    private String f4c1_b = "";
    private String f4c1_c = "";
    private String f4c1_d = "";
    private String f4c1_e = "";
    private String f4c1_f = "";
    private String f4c1_g = "";
    private String f4c2_a = "";
    private String f4c2_b = "";
    private String f4c2_c = "";
    private String f4c2_d = "";
    private String f4c2_e = "";
    private String f4c2_f = "";
    private String f4c2_g = "";
    private String f4c3_a = "";
    private String f4c3_b = "";
    private String f4c3_c = "";
    private String f4c3_d = "";
    private String f4c3_e = "";
    private String f4c3_f = "";
    private String f4c3_g = "";
    private String f4c4_a = "";
    private String f4c4_b = "";
    private String f4c4_c = "";
    private String f4c4_d = "";
    private String f4c4_e = "";
    private String f4c4_f = "";
    private String f4c4_g = "";
    private String f4c5_a = "";
    private String f4c5_b = "";
    private String f4c5_c = "";
    private String f4c5_d = "";
    private String f4c5_e = "";
    private String f4c5_f = ""; 
    private String f4c5_g = "";
    private String estado = "";

    public ClassCensoComercial2009Seccion4() {
    
    }

    public ClassCensoComercial2009Seccion4(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }
    
    public void setIdEncuestaComercial(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }

    public int getIdEncuestaComercial() {
	return idEncuestaComercial;
    }

    public void setF4a(String f4a) {
	this.f4a = f4a;
    }

    public String getF4a() {
	return f4a;
    }

    public void setF4b(String f4b) {
	this.f4b = f4b;
    }

    public String getF4b() {
	return f4b;
    }

    public void setF4c1_a(String f4c1_a) {
	this.f4c1_a = f4c1_a;
    }

    public String getF4c1_a() {
	return f4c1_a;
    }

    public void setF4c1_b(String f4c1_b) {
	this.f4c1_b = f4c1_b;
    }

    public String getF4c1_b() {
	return f4c1_b;
    }

    public void setF4c1_c(String f4c1_c) {
	this.f4c1_c = f4c1_c;
    }

    public String getF4c1_c() {
	return f4c1_c;
    }

    public void setF4c1_d(String f4c1_d) {
	this.f4c1_d = f4c1_d;
    }

    public String getF4c1_d() {
	return f4c1_d;
    }

    public void setF4c1_e(String f4c1_e) {
	this.f4c1_e = f4c1_e;
    }

    public String getF4c1_e() {
	return f4c1_e;
    }

    public void setF4c1_f(String f4c1_f) {
	this.f4c1_f = f4c1_f;
    }

    public String getF4c1_f() {
	return f4c1_f;
    }

    public void setF4c1_g(String f4c1_g) {
	this.f4c1_g = f4c1_g;
    }

    public String getF4c1_g() {
	return f4c1_g;
    }

    public void setF4c2_a(String f4c2_a) {
	this.f4c2_a = f4c2_a;
    }

    public String getF4c2_a() {
	return f4c2_a;
    }

    public void setF4c2_b(String f4c2_b) {
	this.f4c2_b = f4c2_b;
    }

    public String getF4c2_b() {
	return f4c2_b;
    }

    public void setF4c2_c(String f4c2_c) {
	this.f4c2_c = f4c2_c;
    }

    public String getF4c2_c() {
	return f4c2_c;
    }

    public void setF4c2_d(String f4c2_d) {
	this.f4c2_d = f4c2_d;
    }

    public String getF4c2_d() {
	return f4c2_d;
    }

    public void setF4c2_e(String f4c2_e) {
	this.f4c2_e = f4c2_e;
    }

    public String getF4c2_e() {
	return f4c2_e;
    }

    public void setF4c2_f(String f4c2_f) {
	this.f4c2_f = f4c2_f;
    }

    public String getF4c2_f() {
	return f4c2_f;
    }

    public void setF4c2_g(String f4c2_g) {
	this.f4c2_g = f4c2_g;
    }

    public String getF4c2_g() {
	return f4c2_g;
    }

    public void setF4c3_a(String f4c3_a) {
	this.f4c3_a = f4c3_a;
    }

    public String getF4c3_a() {
	return f4c3_a;
    }

    public void setF4c3_b(String f4c3_b) {
	this.f4c3_b = f4c3_b;
    }

    public String getF4c3_b() {
	return f4c3_b;
    }

    public void setF4c3_c(String f4c3_c) {
	this.f4c3_c = f4c3_c;
    }

    public String getF4c3_c() {
	return f4c3_c;
    }

    public void setF4c3_d(String f4c3_d) {
	this.f4c3_d = f4c3_d;
    }

    public String getF4c3_d() {
	return f4c3_d;
    }

    public void setF4c3_e(String f4c3_e) {
	this.f4c3_e = f4c3_e;
    }

    public String getF4c3_e() {
	return f4c3_e;
    }

    public void setF4c3_f(String f4c3_f) {
	this.f4c3_f = f4c3_f;
    }

    public String getF4c3_f() {
	return f4c3_f;
    }

    public void setF4c3_g(String f4c3_g) {
	this.f4c3_g = f4c3_g;
    }

    public String getF4c3_g() {
	return f4c3_g;
    }

    public void setF4c4_a(String f4c4_a) {
	this.f4c4_a = f4c4_a;
    }

    public String getF4c4_a() {
	return f4c4_a;
    }

    public void setF4c4_b(String f4c4_b) {
	this.f4c4_b = f4c4_b;
    }

    public String getF4c4_b() {
	return f4c4_b;
    }

    public void setF4c4_c(String f4c4_c) {
	this.f4c4_c = f4c4_c;
    }

    public String getF4c4_c() {
	return f4c4_c;
    }

    public void setF4c4_d(String f4c4_d) {
	this.f4c4_d = f4c4_d;
    }

    public String getF4c4_d() {
	return f4c4_d;
    }

    public void setF4c4_e(String f4c4_e) {
	this.f4c4_e = f4c4_e;
    }

    public String getF4c4_e() {
	return f4c4_e;
    }

    public void setF4c4_f(String f4c4_f) {
	this.f4c4_f = f4c4_f;
    }

    public String getF4c4_f() {
	return f4c4_f;
    }

    public void setF4c4_g(String f4c4_g) {
	this.f4c4_g = f4c4_g;
    }

    public String getF4c4_g() {
	return f4c4_g;
    }

    public void setF4c5_a(String f4c5_a) {
	this.f4c5_a = f4c5_a;
    }

    public String getF4c5_a() {
	return f4c5_a;
    }

    public void setF4c5_b(String f4c5_b) {
	this.f4c5_b = f4c5_b;
    }

    public String getF4c5_b() {
	return f4c5_b;
    }

    public void setF4c5_c(String f4c5_c) {
	this.f4c5_c = f4c5_c;
    }

    public String getF4c5_c() {
	return f4c5_c;
    }

    public void setF4c5_d(String f4c5_d) {
	this.f4c5_d = f4c5_d;
    }

    public String getF4c5_d() {
	return f4c5_d;
    }

    public void setF4c5_e(String f4c5_e) {
	this.f4c5_e = f4c5_e;
    }

    public String getF4c5_e() {
	return f4c5_e;
    }

    public void setF4c5_f(String f4c5_f) {
	this.f4c5_f = f4c5_f;
    }

    public String getF4c5_f() {
	return f4c5_f;
    }

    public void setF4c5_g(String f4c5_g) {
	this.f4c5_g = f4c5_g;
    }

    public String getF4c5_g() {
	return f4c5_g;
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
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion4", params);
	} else if (idEncuestaComercial > 0){
	    params = "";
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion4", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gea_salta.getseccion4censocomercial", idEncuestaComercial);
	try {
	    if (result.next()) {
		setIdEncuestaComercial(result.getInt("idencuestacomercial"));
		setF4a(result.getString("f4a"));
	        setF4b(result.getString("f4b"));
		
	        setF4c1_a(result.getString("f4c1_a"));
	        setF4c1_b(result.getString("f4c1_b"));
	        setF4c1_c(result.getString("f4c1_c"));
	        setF4c1_d(result.getString("f4c1_d"));
	        setF4c1_e(result.getString("f4c1_e"));
	        setF4c1_f(result.getString("f4c1_f"));
	        setF4c1_g(result.getString("f4c1_g"));
		
	        setF4c2_a(result.getString("f4c2_a"));
	        setF4c2_b(result.getString("f4c2_b"));
	        setF4c2_c(result.getString("f4c2_c"));
	        setF4c2_d(result.getString("f4c2_d"));
	        setF4c2_e(result.getString("f4c2_e"));
	        setF4c2_f(result.getString("f4c2_f"));
	        setF4c2_g(result.getString("f4c2_g"));
		
	        setF4c3_a(result.getString("f4c3_a"));
	        setF4c3_b(result.getString("f4c3_b"));
	        setF4c3_c(result.getString("f4c3_c"));
	        setF4c3_d(result.getString("f4c3_d"));
	        setF4c3_e(result.getString("f4c3_e"));
	        setF4c3_f(result.getString("f4c3_f"));
	        setF4c3_g(result.getString("f4c3_g"));
		
	        setF4c4_a(result.getString("f4c4_a"));
	        setF4c4_b(result.getString("f4c4_b"));
	        setF4c4_c(result.getString("f4c4_c"));
	        setF4c4_d(result.getString("f4c4_d"));
	        setF4c4_e(result.getString("f4c4_e"));
	        setF4c4_f(result.getString("f4c4_f"));
	        setF4c4_g(result.getString("f4c4_g"));
		
	        setF4c5_a(result.getString("f4c5_a"));
	        setF4c5_b(result.getString("f4c5_b"));
	        setF4c5_c(result.getString("f4c5_c"));
	        setF4c5_d(result.getString("f4c5_d"));
	        setF4c5_e(result.getString("f4c5_e"));
	        setF4c5_f(result.getString("f4c5_f"));
	        setF4c5_g(result.getString("f4c5_g"));
		
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
