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
 * ConceptosLegajos.java
 *
 * */
package org.digitall.apps.sueldos.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class ConceptosLegajos {

    private int idconceptolegajo = -1; //id concepto x legajo
    private int idlegajo = -1; //id legajo
    private int idconcepto = -1; //id concepto
    private double valor = 0.0; //ultimo valor grabado de la liquidacion
    private String estado = ""; //* , Borrado
    private int cantidad = -1; //Si existe cantidad segun el concepto
    private boolean issetforlegajo = false; //Indica si el concepto se carga por legajo o es generico para todos

    public ConceptosLegajos() {
    }
    
    public int saveData() {             
	String params = idconceptolegajo+","+idlegajo+","+idconcepto+","+valor+",'"+ estado+"',"+cantidad+"," + issetforlegajo;
	int result = -1;
	if (idconceptolegajo == -1){
	    result = LibSQL.getInt("sueldos.addConceptoLegajo",params);              
	} else {            
	    result = LibSQL.getInt("sueldos.setConceptoLegajo",params);
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = ""+idconceptolegajo;
	ResultSet data = LibSQL.exFunction("sueldos.getConceptoLegajo", params);
	try {
	    if (data.next()) {
		idconceptolegajo  = data.getInt("idconceptolegajo");
		idlegajo = data.getInt("idlegajo");
		idconcepto = data.getInt("idconcepto");
		valor = data.getDouble("valor");
		estado  = data.getString("estado");
		cantidad  = data.getInt("cantidad");
		issetforlegajo = data.getBoolean("issetforlegajo");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdconceptolegajo(int idconceptolegajo) {
	this.idconceptolegajo = idconceptolegajo;
    }

    public int getIdconceptolegajo() {
	return idconceptolegajo;
    }

    public void setIdlegajo(int idlegajo) {
	this.idlegajo = idlegajo;
    }

    public int getIdlegajo() {
	return idlegajo;
    }

    public void setIdconcepto(int idconcepto) {
	this.idconcepto = idconcepto;
    }

    public int getIdconcepto() {
	return idconcepto;
    }

    public void setValor(double valor) {
	this.valor = valor;
    }

    public double getValor() {
	return valor;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
    }

    public int getCantidad() {
	return cantidad;
    }

    public void setIssetforlegajo(boolean issetforlegajo) {
	this.issetforlegajo = issetforlegajo;
    }

    public boolean isIssetforlegajo() {
	return issetforlegajo;
    }
}
