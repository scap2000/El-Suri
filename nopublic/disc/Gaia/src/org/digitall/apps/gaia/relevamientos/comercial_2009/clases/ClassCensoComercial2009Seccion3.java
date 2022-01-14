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
 * ClassCensoComercial2009Seccion3.java
 *
 * */
package org.digitall.apps.gaia.relevamientos.comercial_2009.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class ClassCensoComercial2009Seccion3 {

    private int idEncuestaComercial = -1;
    private String f3a = "";
    private String f3b = "";     
    private String f3c = "";
    private String f3d = "";
    private String f3e = "";
    private String f3f = "";
    private String f3g = "";
    private String f3h = "";
    private String f3i = "";
    private String f3j = "";
    private String f3k = "";
    private String f3l = "";
    private String f3m = "";
    private String f3n = "";
    private String f3nn = "";
    private String f3o = "";
    private String f3p = "";
    private String f3q = "";
    private String f3r = "";
    private String f3s = "";
    private String estado = "";

    public ClassCensoComercial2009Seccion3() {
    
    }
    
    public ClassCensoComercial2009Seccion3(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }

    public void setIdEncuestaComercial(int idEncuestaComercial) {
	this.idEncuestaComercial = idEncuestaComercial;
    }

    public int getIdEncuestaComercial() {
	return idEncuestaComercial;
    }

    public void setF3a(String f3a) {
	this.f3a = f3a;
    }

    public String getF3a() {
	return f3a;
    }

    public void setF3b(String f3b) {
	this.f3b = f3b;
    }

    public String getF3b() {
	return f3b;
    }

    public void setF3c(String f3c) {
	this.f3c = f3c;
    }

    public String getF3c() {
	return f3c;
    }

    public void setF3d(String f3d) {
	this.f3d = f3d;
    }

    public String getF3d() {
	return f3d;
    }

    public void setF3e(String f3e) {
	this.f3e = f3e;
    }

    public String getF3e() {
	return f3e;
    }

    public void setF3f(String f3f) {
	this.f3f = f3f;
    }

    public String getF3f() {
	return f3f;
    }

    public void setF3g(String f3g) {
	this.f3g = f3g;
    }

    public String getF3g() {
	return f3g;
    }

    public void setF3h(String f3h) {
	this.f3h = f3h;
    }

    public String getF3h() {
	return f3h;
    }

    public void setF3i(String f3i) {
	this.f3i = f3i;
    }

    public String getF3i() {
	return f3i;
    }

    public void setF3j(String f3j) {
	this.f3j = f3j;
    }

    public String getF3j() {
	return f3j;
    }

    public void setF3k(String f3k) {
	this.f3k = f3k;
    }

    public String getF3k() {
	return f3k;
    }

    public void setF3l(String f3l) {
	this.f3l = f3l;
    }

    public String getF3l() {
	return f3l;
    }

    public void setF3m(String f3m) {
	this.f3m = f3m;
    }

    public String getF3m() {
	return f3m;
    }

    public void setF3n(String f3n) {
	this.f3n = f3n;
    }

    public String getF3n() {
	return f3n;
    }

    public void setF3nn(String f3nn) {
	this.f3nn = f3nn;
    }

    public String getF3nn() {
	return f3nn;
    }

    public void setF3o(String f3o) {
	this.f3o = f3o;
    }

    public String getF3o() {
	return f3o;
    }

    public void setF3p(String f3p) {
	this.f3p = f3p;
    }

    public String getF3p() {
	return f3p;
    }

    public void setF3q(String f3q) {
	this.f3q = f3q;
    }

    public String getF3q() {
	return f3q;
    }

    public void setF3r(String f3r) {
	this.f3r = f3r;
    }

    public String getF3r() {
	return f3r;
    }

    public void setF3s(String f3s) {
	this.f3s = f3s;
    }

    public String getF3s() {
	return f3s;
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
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion3", params);
	} else if (idEncuestaComercial > 0){
	    params = "";
	    result = LibSQL.getInt("gea_salta.addencuestacomercial2009seccion3", params);
	}
	return result;
    }

    public void retrieveData() {
	ResultSet result = LibSQL.exFunction("gea_salta.getseccion3censocomercial", idEncuestaComercial);
	try {
	    if (result.next()) {
		setIdEncuestaComercial(result.getInt("idencuestacomercial"));
		setF3a(result.getString("f3a"));
	        setF3b(result.getString("f3b"));
	        setF3c(result.getString("f3c"));
	        setF3d(result.getString("f3d"));
	        setF3e(result.getString("f3e"));
	        setF3f(result.getString("f3f"));
	        setF3g(result.getString("f3g"));
	        setF3h(result.getString("f3h"));
	        setF3i(result.getString("f3i"));
	        setF3j(result.getString("f3j"));
	        setF3k(result.getString("f3k"));
	        setF3l(result.getString("f3l"));
	        setF3m(result.getString("f3m"));
	        setF3n(result.getString("f3n"));
	        setF3nn(result.getString("f3nn"));
	        setF3o(result.getString("f3o"));
	        setF3p(result.getString("f3p"));
	        setF3q(result.getString("f3q"));
	        setF3r(result.getString("f3r"));
	        setF3s(result.getString("f3s"));
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
