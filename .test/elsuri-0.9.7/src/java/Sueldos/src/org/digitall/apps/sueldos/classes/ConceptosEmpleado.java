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
 * ConceptosEmpleado.java
 *
 * */
package org.digitall.apps.sueldos.classes;

import java.sql.ResultSet;

import org.digitall.lib.sql.LibSQL;

public class ConceptosEmpleado {

    private int idconceptoreferencia = -1; //id tabla que sirve como referencia a la tabla conceptos
    private String name = "";  //Descripcion del concepto
    private double value = 0; //Importe del concepto
    private double percentage = 0; //Porcentaje del concepto
    private int debehaber = -1; //1: Indica si es haber; -1: Indica si es debe
    private String estado = ""; //Estado
    private int orden = -1; //Orden que tiene en el listado de Haberes o Descuentos segun corresponda
    private boolean issetforlegajo = false; // Indica si el concepto se carga por legajo o es generico para todos
    private boolean setgeneral = false; // Indica si el concepto se asigna a todos los legajos en general
    private int idaccount = -1; //Id de la cuenta del plan de cuentas al que corresponda

    public ConceptosEmpleado() {
    }
    
    public int saveData() {             
	String params = idconceptoreferencia + ",'" + name + "'," + value + "," + percentage + "," + debehaber + ",'" + estado + "'," + orden + "," + issetforlegajo + "," + setgeneral + "," + idaccount;
	int result = -1;
	if (idconceptoreferencia == -1){
	    result = LibSQL.getInt("sueldos.addConceptoEmpleado",params);              
	} else {            
	    result = LibSQL.getInt("sueldos.setConceptoEmpleado",params);
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = ""+idconceptoreferencia;
	ResultSet data = LibSQL.exFunction("sueldos.getConceptoEmpleado", params);
	try {
	    if (data.next()) {
		idconceptoreferencia  = data.getInt("idconceptoreferencia");
		name = data.getString("name");
		value = data.getDouble("value");
	        percentage  = data.getDouble("percentage");
		debehaber = data.getInt("debehaber");
		estado  = data.getString("estado");
		orden = data.getInt("orden");
	        issetforlegajo = data.getBoolean("issetforlegajo");
		setgeneral = data.getBoolean("setgeneral");
	        idaccount = data.getInt("idaccount");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setIdconceptoreferencia(int idconceptoreferencia) {
	this.idconceptoreferencia = idconceptoreferencia;
    }

    public int getIdconceptoreferencia() {
	return idconceptoreferencia;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setValue(double value) {
	this.value = value;
    }

    public double getValue() {
	return value;
    }

    public void setPercentage(double percentage) {
	this.percentage = percentage;
    }

    public double getPercentage() {
	return percentage;
    }

    public void setDebehaber(int debehaber) {
	this.debehaber = debehaber;
    }

    public int getDebehaber() {
	return debehaber;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }

    public void setOrden(int orden) {
	this.orden = orden;
    }

    public int getOrden() {
	return orden;
    }

    public void setIssetforlegajo(boolean issetforlegajo) {
	this.issetforlegajo = issetforlegajo;
    }

    public boolean isIssetforlegajo() {
	return issetforlegajo;
    }

    public void setSetgeneral(boolean setgeneral) {
	this.setgeneral = setgeneral;
    }

    public boolean isSetgeneral() {
	return setgeneral;
    }

    public void setIdaccount(int idaccount) {
        this.idaccount = idaccount;
    }

    public int getIdaccount() {
        return idaccount;
    }
}
