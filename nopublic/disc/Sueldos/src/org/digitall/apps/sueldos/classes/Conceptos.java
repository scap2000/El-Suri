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
 * Conceptos.java
 *
 * */
package org.digitall.apps.sueldos.classes;

import java.sql.ResultSet;

import org.digitall.common.personalfiles.classes.ConceptosCategorias;
import org.digitall.lib.sql.LibSQL;

public class Conceptos {

    private int idconcepto = -1;
    private int idtipoconcepto = -1; //id del tipo de concepto referenciado a una tabla
    private int idtablareferenciada = -1; //id de la tabla de conceptos a la cual hace referencia
    /* 2009-09-11 (santiago) Mensaje para el codificador
     * ¿Qué valor toma en caso de ser "debe"?
     * El atributo debería llamarse debeHaber
     * */
    private int debehaber = -1; // -1 indica si el concepto es descuento; 1 si es haber
    private String comentario = ""; //comentario
     /* 2009-09-11 (santiago) Mensaje para el codificador
      * No hace falta el atributo "estado", y además es tipo char(1) en la DB
      * */
    private String estado = "";
    private int orden = -1;
    private int idaccount = -1;
    private TipoConcepto tipoConcepto = new TipoConcepto();
    private ConceptosEmpleado conceptoEmpleado = new ConceptosEmpleado();
    private ConceptosSindicatos conceptoSindicatos = new ConceptosSindicatos();
    private ConceptosCategorias conceptoCategorias = new ConceptosCategorias();
    private ConceptosArticulos conceptoArticulos = new ConceptosArticulos();
    private ConceptosNovedades conceptoNovedades = new ConceptosNovedades();
    private ConceptosObrasSociales conceptoObrasSociales = new ConceptosObrasSociales();
    private ConceptosFamiliares conceptoFamiliares = new ConceptosFamiliares();
    private ConceptosAportes conceptoAportes = new ConceptosAportes();
    private ConceptosSociales conceptoSociales = new ConceptosSociales(); 
    
    public Conceptos() {
    }
    
    public int saveData() {             
	/* 2009-09-11 (santiago) Mensaje para el codificador
	 * Puntuación y espacios
	 * */
	//String params = idconcepto+","+idtipoconcepto+","+idtablareferenciada+","+debehaber+",'"+ comentario+"',"+orden;
	String params = idconcepto + "," + idtipoconcepto + "," + idtablareferenciada + "," + debehaber + ",'" + comentario + "'," + orden + ", " +  idaccount;
	int result = -1;
	if (idconcepto == -1){
	    result = LibSQL.getInt("sueldos.addConcept",params);              
	} else {            
	    result = LibSQL.getInt("sueldos.setConcept",params);
	}   
        
	return result;
    }
    
    public void retrieveData() {
	String params = ""+idconcepto;
	ResultSet data = LibSQL.exFunction("sueldos.getConcept", params);
	try {
	    if (data.next()) {
		idconcepto  = data.getInt("idconcepto");
		idtipoconcepto = data.getInt("idtipoconcepto");
		idtablareferenciada = data.getInt("idtablareferenciada");
		debehaber = data.getInt("debehaber");
	        comentario = data.getString("name");
		estado  = data.getString("estado");
		orden = data.getInt("orden");
	        idaccount = data.getInt("idaccount");
                setTipoConcepto();
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    public void saveTipoConcepto(){
        switch (idtipoconcepto) {
            case 1:
                conceptoEmpleado.saveData();
            break;
            case 2:
                conceptoSindicatos.saveData();
            break;
            case 3:
                conceptoCategorias.saveData();
            break;
            case 4:
                conceptoArticulos.saveData();
            break;
            case 5:
                conceptoNovedades.saveData();
            break;
            case 6:
                conceptoObrasSociales.saveData();
            break;
            case 7:
                conceptoFamiliares.saveData();
            break;
            case 8:
                conceptoAportes.saveData();
            break;
            case 9:
                conceptoSociales.saveData();
            break;
        }
    }
    
    private void setTipoConcepto(){
        switch (idtipoconcepto) {
            case 1:
                conceptoEmpleado = new ConceptosEmpleado();
                conceptoEmpleado.setIdconceptoreferencia(idtablareferenciada);
                conceptoEmpleado.retrieveData();
                tipoConcepto.setIssetforlegajo(conceptoEmpleado.isIssetforlegajo());
                tipoConcepto.setSetgeneral(conceptoEmpleado.isSetgeneral());
                tipoConcepto.setName(conceptoEmpleado.getName());
                tipoConcepto.setIdaccount(conceptoEmpleado.getIdaccount());
                tipoConcepto.setPercentage(conceptoEmpleado.getPercentage());
                tipoConcepto.setValue(conceptoEmpleado.getValue());
            break;
            case 2:
                conceptoSindicatos = new ConceptosSindicatos();
                conceptoSindicatos.setIdconceptoreferencia(idtablareferenciada);
                conceptoSindicatos.retrieveData();
                tipoConcepto.setIssetforlegajo(conceptoSindicatos.isIssetforlegajo());
                tipoConcepto.setSetgeneral(conceptoSindicatos.isSetgeneral());
                tipoConcepto.setName(conceptoSindicatos.getName());
                tipoConcepto.setIdaccount(conceptoSindicatos.getIdaccount());
                tipoConcepto.setPercentage(conceptoSindicatos.getPercentage());
                tipoConcepto.setValue(conceptoSindicatos.getValue());
            break;
            case 3:
                conceptoCategorias = new ConceptosCategorias();
                conceptoCategorias.setIdconceptoreferencia(idtablareferenciada);
                conceptoCategorias.retrieveData();
                tipoConcepto.setIssetforlegajo(conceptoCategorias.isIssetforlegajo());
                tipoConcepto.setSetgeneral(conceptoCategorias.isSetgeneral());
                tipoConcepto.setName(conceptoCategorias.getName());
                tipoConcepto.setIdaccount(conceptoCategorias.getIdaccount());
                tipoConcepto.setPercentage(conceptoCategorias.getPercentage());
                tipoConcepto.setValue(conceptoCategorias.getValue());
            break;
            case 4:
                conceptoArticulos = new ConceptosArticulos();
                conceptoArticulos.setIdconceptoreferencia(idtablareferenciada);
                conceptoArticulos.retrieveData();
                tipoConcepto.setIssetforlegajo(conceptoArticulos.isIssetforlegajo());
                tipoConcepto.setSetgeneral(conceptoArticulos.isSetgeneral());
                tipoConcepto.setName(conceptoArticulos.getName());
                tipoConcepto.setIdaccount(conceptoArticulos.getIdaccount());
                tipoConcepto.setPercentage(conceptoArticulos.getPercentage());
                tipoConcepto.setValue(conceptoArticulos.getValue());
            break;
            case 5:
                conceptoNovedades = new ConceptosNovedades();
                conceptoNovedades.setIdconceptoreferencia(idtablareferenciada);
                conceptoNovedades.retrieveData();
                tipoConcepto.setIssetforlegajo(conceptoNovedades.isIssetforlegajo());
                tipoConcepto.setSetgeneral(conceptoNovedades.isSetgeneral());
                tipoConcepto.setName(conceptoNovedades.getName());
                tipoConcepto.setIdaccount(conceptoNovedades.getIdaccount());
                tipoConcepto.setPercentage(conceptoNovedades.getPercentage());
                tipoConcepto.setValue(conceptoNovedades.getValue());
            break;
            case 6:
                conceptoObrasSociales = new ConceptosObrasSociales();
                conceptoObrasSociales.setIdconceptoreferencia(idtablareferenciada);
                conceptoObrasSociales.retrieveData();
                tipoConcepto.setIssetforlegajo(conceptoObrasSociales.isIssetforlegajo());
                tipoConcepto.setSetgeneral(conceptoObrasSociales.isSetgeneral());
                tipoConcepto.setName(conceptoObrasSociales.getName());
                tipoConcepto.setIdaccount(conceptoObrasSociales.getIdaccount());
                tipoConcepto.setPercentage(conceptoObrasSociales.getPercentage());
                tipoConcepto.setValue(conceptoObrasSociales.getValue());
            break;
            case 7:
                conceptoFamiliares = new ConceptosFamiliares();
                conceptoFamiliares.setIdconceptoreferencia(idtablareferenciada);
                conceptoFamiliares.retrieveData();
                tipoConcepto.setIssetforlegajo(conceptoFamiliares.isIssetforlegajo());
                tipoConcepto.setSetgeneral(conceptoFamiliares.isSetgeneral());
                tipoConcepto.setName(conceptoFamiliares.getName());
                tipoConcepto.setIdaccount(conceptoFamiliares.getIdaccount());
                tipoConcepto.setPercentage(conceptoFamiliares.getPercentage());
                tipoConcepto.setValue(conceptoFamiliares.getValue());
            break;
            case 8:
                conceptoAportes = new ConceptosAportes();
                conceptoAportes.setIdconceptoreferencia(idtablareferenciada);
                conceptoAportes.retrieveData();
                tipoConcepto.setIssetforlegajo(conceptoAportes.isIssetforlegajo());
                tipoConcepto.setSetgeneral(conceptoAportes.isSetgeneral());
                tipoConcepto.setName(conceptoAportes.getName());
                tipoConcepto.setIdaccount(conceptoAportes.getIdaccount());
                tipoConcepto.setPercentage(conceptoAportes.getPercentage());
                tipoConcepto.setValue(conceptoAportes.getValue());
            break;
            case 9:
                conceptoSociales = new ConceptosSociales();
                conceptoSociales.setIdconceptoreferencia(idtablareferenciada);
                conceptoSociales.retrieveData();
                tipoConcepto.setIssetforlegajo(conceptoSociales.isIssetforlegajo());
                tipoConcepto.setSetgeneral(conceptoSociales.isSetgeneral());
                tipoConcepto.setName(conceptoSociales.getName());
                tipoConcepto.setIdaccount(conceptoSociales.getIdaccount());
                tipoConcepto.setPercentage(conceptoSociales.getPercentage());
                tipoConcepto.setValue(conceptoSociales.getValue());
            break;
        }
    }

    public void setIdconcepto(int idconcepto) {
	this.idconcepto = idconcepto;
    }

    public int getIdconcepto() {
	return idconcepto;
    }

    public void setIdtipoconcepto(int idtipoconcepto) {
	this.idtipoconcepto = idtipoconcepto;
    }

    public int getIdtipoconcepto() {
	return idtipoconcepto;
    }

    public void setIdtablareferenciada(int idtablareferenciada) {
	this.idtablareferenciada = idtablareferenciada;
    }

    public int getIdtablareferenciada() {
	return idtablareferenciada;
    }

    public void setDebehaber(int debehaber) {
	this.debehaber = debehaber;
    }

    public int getDebehaber() {
	return debehaber;
    }

    public void setComentario(String comentario) {
	this.comentario = comentario;
    }

    public String getComentario() {
	return comentario;
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

    public void setIdaccount(int idaccount) {
        this.idaccount = idaccount;
    }

    public int getIdaccount() {
        return idaccount;
    }

    public TipoConcepto getTipoConcepto() {
        return tipoConcepto;
    }
    
}
