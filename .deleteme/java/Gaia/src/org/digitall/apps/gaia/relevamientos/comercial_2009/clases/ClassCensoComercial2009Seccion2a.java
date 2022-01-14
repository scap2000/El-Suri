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
 * ClassCensoComercial2009Seccion2a.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ClassCensoComercial2009Seccion2a {

    private int idEncuestaComercial = -1;
    private String f2a = "";
    private String f2b = "";     
    private String f2c = "";
    private String f2d = "";
    private String f2e = "";
    private String f2f = "";
    private String f2g = "";
    private String f2h = "";
    private String f2i_a = "";
    private String f2i_b = "";
    private String f2j1_a = "";
    private String f2j1_b = "";
    private String f2j2_a = "";
    private String f2j2_b = "";
    private String f2j3_a = "";
    private String f2j3_b = "";
    private String estado = "";
    private String f2j4 = "";
    private String f2j5 = "";
    private boolean f2j6 = false;

    public ClassCensoComercial2009Seccion2a() {
    
    }

    public ClassCensoComercial2009Seccion2a(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }
    
    public void setIdEncuestaComercial(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }

    public int getIdEncuestaComercial() {
	return idEncuestaComercial;
    }

    public void setF2a(String f2a) {
	this.f2a = f2a;
    }

    public String getF2a() {
	return f2a;
    }

    public void setF2b(String f2b) {
	this.f2b = f2b;
    }

    public String getF2b() {
	return f2b;
    }

    public void setF2c(String f2c) {
	this.f2c = f2c;
    }

    public String getF2c() {
	return f2c;
    }

    public void setF2d(String f2d) {
	this.f2d = f2d;
    }

    public String getF2d() {
	return f2d;
    }

    public void setF2e(String f2e) {
	this.f2e = f2e;
    }

    public String getF2e() {
	return f2e;
    }

    public void setF2f(String f2f) {
	this.f2f = f2f;
    }

    public String getF2f() {
	return f2f;
    }

    public void setF2g(String f2g) {
	this.f2g = f2g;
    }

    public String getF2g() {
	return f2g;
    }

    public void setF2h(String f2h) {
	this.f2h = f2h;
    }

    public String getF2h() {
	return f2h;
    }

    public void setF2i_a(String f2i_a) {
	this.f2i_a = f2i_a;
    }

    public String getF2i_a() {
	return f2i_a;
    }

    public void setF2i_b(String f2i_b) {
	this.f2i_b = f2i_b;
    }

    public String getF2i_b() {
	return f2i_b;
    }

    public void setF2j1_a(String f2j1_a) {
	this.f2j1_a = f2j1_a;
    }

    public String getF2j1_a() {
	return f2j1_a;
    }

    public void setF2j1_b(String f2j1_b) {
	this.f2j1_b = f2j1_b;
    }

    public String getF2j1_b() {
	return f2j1_b;
    }

    public void setF2j2_a(String f2j2_a) {
	this.f2j2_a = f2j2_a;
    }

    public String getF2j2_a() {
	return f2j2_a;
    }

    public void setF2j2_b(String f2j2_b) {
	this.f2j2_b = f2j2_b;
    }

    public String getF2j2_b() {
	return f2j2_b;
    }

    public void setF2j3_a(String f2j3_a) {
	this.f2j3_a = f2j3_a;
    }

    public String getF2j3_a() {
	return f2j3_a;
    }

    public void setF2j3_b(String f2j3_b) {
	this.f2j3_b = f2j3_b;
    }

    public String getF2j3_b() {
	return f2j3_b;
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
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion2a", params);
	} else if (idEncuestaComercial > 0){
	    params = "";
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion2a", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gea_salta.getseccion2acensocomercial", idEncuestaComercial);
	try {
	    if (result.next()) {
		setIdEncuestaComercial(result.getInt("idencuestacomercial"));
		setF2a(result.getString("f2a"));
	        setF2b(result.getString("f2b"));
	        setF2c(result.getString("f2c"));
	        setF2d(result.getString("f2d"));
	        setF2e(result.getString("f2e"));
	        setF2f(result.getString("f2f"));
	        setF2g(result.getString("f2g"));
	        setF2h(result.getString("f2h"));
	        setF2i_a(result.getString("f2i_a"));
	        setF2i_b(result.getString("f2i_b"));
	        setF2j1_a(result.getString("f2j1_a"));
	        setF2j1_b(result.getString("f2j1_b"));
	        setF2j2_a(result.getString("f2j2_a"));
	        setF2j2_b(result.getString("f2j2_b"));
	        setF2j3_a(result.getString("f2j3_a"));
	        setF2j3_b(result.getString("f2j3_b"));
		setEstado(result.getString("estado"));
                setF2j4(result.getString("f2j4"));
	        setF2j5(result.getString("f2j5"));
	        setF2j6(result.getBoolean("f2j6"));
	    }
	    
	} catch (SQLException e) {
	    // TODO
	    System.out.println("error");
	} catch (NullPointerException e) {
	    // TODO
	}
    }

    public void setF2j4(String f2j4) {
        this.f2j4 = f2j4;
    }

    public String getF2j4() {
        return f2j4;
    }

    public void setF2j5(String f2j5) {
        this.f2j5 = f2j5;
    }

    public String getF2j5() {
        return f2j5;
    }

    public void setF2j6(boolean f2j6) {
        this.f2j6 = f2j6;
    }

    public boolean isF2j6() {
        return f2j6;
    }
}
