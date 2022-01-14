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
 * Legajo.java
 *
 * */
package org.digitall.common.personalfiles.classes;

import java.sql.ResultSet;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.sql.LibSQL;

public class Legajo {
    private int idLegajo = -1;
    private int idPerson = -1;
    private int numero = -1;
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * En otras clases se ha utilizado el formato "isActivo"
     * y aquí "esActivo", debería ser "isActivo" para conservar
     * coherencia, pero lo recomendado es "activo", con los
     * accesores "setActivo(boolean)", "isActivo()"
     * */
    private boolean esActivo = false;

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * No hace falta el atributo estado, además no se lo envia en el Stored Proc.
     * */
    private String estado = "";
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * El atributo "person" no debería instanciarse aquí
     * sino esperar a tener el idPerson
     * */
    private Persona person = new Persona();
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Según se ha visto en la clase Persona, los valores
     * de (TrabajoPersona)trabajoActual y (CivilState)civilState
     * son propios de la clase Persona y no de la clase Legajo
     * y debería llamarse a los accesores en lugar de crear instancias aquí
     * Revisar también las observaciones hechas en la clase Persona
     * */
    private TrabajoPersona trabajoActual = new TrabajoPersona();    
    private CivilState civilState = new CivilState();
    
    public Legajo() {
    
    }

    public Legajo(int _idlegajo) {
        idLegajo = _idlegajo;
    }
    
    public int saveData() {             
	String params = idLegajo +","+ idPerson +","+ numero +","+ esActivo;
	int result = -1;
	if (idLegajo == -1){
	    result = LibSQL.getInt("sueldos.addLegajo",params);              
	    idPerson = result;      
	} else {            
	    params = idLegajo+","+ params;
	    //result = LibSQL.getInt("sueldos.setLegajo",params);
            result = trabajoActual.saveData();
	}               
	return result;
    }
    
    public void retrieveData() {
	String params = ""+idLegajo;
	ResultSet data = LibSQL.exFunction("sueldos.getLegajo", params);
	try {
	    if (data.next()) {
		idLegajo  = data.getInt("idlegajo");
		idPerson = data.getInt("idPerson");
		numero = data.getInt("numero");
		esActivo = data.getBoolean("esActivo");
		estado  = data.getString("estado");
	    }else{
		idPerson = -1;
	    }
	    person.setIdPerson(idPerson);
	    person.retrieveData();
	    setOthers(idPerson);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
    
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Está mal el nombre: retriveDataForNumber (dice retrive en lugar de retrieve)
     * Además, podría llamarse retrieveData(int)
     * ya que no afecta la existencia de retrieveData()
     * */
    public void retriveDataForNumber(int _number){
	String params = "" + _number;
	ResultSet data = LibSQL.exFunction("sueldos.getLegajoForNumber", params);
	try {
	    if (data.next()) {
		idLegajo  = data.getInt("idlegajo");
		idPerson = data.getInt("idPerson");
		numero = data.getInt("numero");
		esActivo = data.getBoolean("esActivo");
		estado  = data.getString("estado");
	    }
	    person.setIdPerson(idPerson);
	    person.retrieveData();
	    person.retrievePicture();
	    setOthers(idPerson);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * qué es ¿getNumPreview? Eso significa "obtenerPrevisualizaciónNumero"
     * Si se quiere significar "obtenerNumeroPrevio" debe llamarse getPreviousNumber o mejor
     * aún, getPreviousLegajo o getPreviousFile
     * */

    public int getNumPreview(int _number){
	/* 2009-09-17 (santiago) Mensaje para el codificador
	 * Se puede programar todo el método en una sola línea de la siguiente manera
	 * return LibSQL.getInt("sueldos.getLegajoNumberPreview", _number);
	 * */
	String params = "" + _number;
	int resultado = LibSQL.getInt("sueldos.getLegajoNumberPreview", params);
	return(resultado);
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * getNumNext --> getNextFile
     * */
    
    public int getNumNext(int _number){
	String params = "" + _number;
	int resultado = LibSQL.getInt("sueldos.getLegajoNumberNext", params);
	return(resultado);
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Pareciera estar bien, getLastFile...
     * */
    public int getLastNum(){
	String params = "";
	int resultado = LibSQL.getInt("sueldos.getLegajoNumberLast", params);
	return(resultado);
    }
    
    public void setidLegajo(int idLegajo) {
	this.idLegajo = idLegajo;
    }

    public int getidLegajo() {
	return idLegajo;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * setidPerson --> setIdPerson
     * */
    public void setidPerson(int idPerson) {
	this.idPerson = idPerson;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * getidPerson --> getIdPerson
     * */
    public int getidPerson() {
	return idPerson;
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * getDependenciaName --> getDependencyName
     * */
    public String getDependenciaName(){
	String params = "" + idPerson;
	String resultado = LibSQL.getString("sueldos.getDependenciaName", params);
	return resultado;
    }

    public void setNumero(int numero) {
	this.numero = numero;
    }

    public int getNumero() {
	return numero;
    }

    public void setesActivo(boolean esActivo) {
	this.esActivo = esActivo;
    }

    public boolean isesActivo() {
	return esActivo;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public String getEstado() {
	return estado;
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Debería usarse getPerson y la clase que necesita llamar a getPerson().getFirstName()
     * Además, este método debería llamarse getPersonFirstName y el otro 
     * getPersonLastName para conservar la coherencia de nomenclatura
     * */
    
    public String getNombresPersona(){
	return(person.getFirstName());
    }
    
    public String getApellidoPersona(){
	return(person.getLastName());
    }
    
    public String getApellidoCasado(){
	return(person.getMarriedLastName());
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Debería usarse getTrabajoActual o getCurrentJob().getDateStart()
     * en lugar de crear interfaces (o accesores intermediarios) para algo tan específico
     * Los accesores intermediarios se utilizan en casos especiales
     * donde se necesita un acceso más PRIVADO o con cálculos intermedios,
     * de otra manera no hacen falta porque ensucian el código
     * ya que se está llamando getFechaAlta en lugar de getDateStart, por lo menos
     * que tengan el mismo nombre.
     * */
    public String getFechaAlta(){
	if(trabajoActual.getDateStart() != null){
	    return(trabajoActual.getDateStart());
	}else{
	    return("");
	}
    }
    
    public String getFechaBaja(){
	if(trabajoActual.getDateEnd() != null){
	    return(trabajoActual.getDateEnd());  
	}else{
	    /* 2009-09-17 (santiago) Mensaje para el codificador
	     * No hace falta pues trabajoActual.getDateEnd() devuelve un String
	     * que parece que NUNCA va a tomar el valor NULL
	     * */
	    return("");
	}
    }
    
    public String getIdentifactionNumber(){
	    return(person.getIdentificationNumber());  
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Accesor intermediario...
     * */
    public double getSalario(){
	return(trabajoActual.getSalary());
    }
    
    public String getEstadoCivil(){
	return(civilState.getName());
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * No se entiende el nombre del método "setOtros"...
     * ¿Qué hace?
     * */
    private void setOthers(int _idPerson) {
	civilState.setIdcivilstate(person.getIdCivilState());
	civilState.retrieveData();
	String params = "" + _idPerson;
	trabajoActual.setIdjobperson(LibSQL.getInt("personalFiles.getTrabajoActual", params));
	trabajoActual.retrieveData();
    }

    public void setCivilState(CivilState civilState) {
	this.civilState = civilState;
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * El formato no es responsabilidad de esta clase, sino de quien la necesita realmente
     * */
    public String getFechaNacimiento(){
	return(Proced.setFormatDate(person.getBirthDate(),true));
    }
    
    public int getIdNacionalidad(){
	return(person.getIdBornCountry());
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Cuerpo del programa en tres líneas cuando puede ser de una línea
     * */
    public String getCargo(int _number){
	String params = "" + _number;
	String resultado = LibSQL.getString("sueldos.getJobPersonLegajo", params);
	return(resultado);
    }
    
    public String getCargo(){
	String params = "" + numero;
	String resultado = LibSQL.getString("sueldos.getJobPersonLegajo", params);
	return(resultado);
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * existNumber --> numberExists
     * */
    public boolean existNumber(int _number){
	String params = "" + _number;
	boolean resultado = LibSQL.getBoolean("sueldos.existLegajoByNumber", params);
	return(resultado);
    }

    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Cuerpo del programa en tres líneas
     * */
    public boolean tieneUnicoLegajoActivo(int _idPerson){
	String params = "" + _idPerson;
	boolean resultado = LibSQL.getBoolean("sueldos.tieneLegajoActivoUnico", params);
	return(resultado);
    }
    
    /* 2009-09-17 (santiago) Mensaje para el codificador
     * Cuerpo mal formado, siempre devuelve ""
     * */
    public String getCategoria(){
	String params = "" + idPerson;
	String resultado = "";
	//String resultado = LibSQL.getString("sueldos.getCategoria", params);
	return(resultado);
    }

    public CivilState getCivilState() {
	return civilState;
    }

    public void setPerson(Persona person) {
	this.person = person;
    }

    public Persona getPerson() {
	return person;
    }
    
    public int getIdCodigoContrato(){
        return trabajoActual.getIdCodigoContrato();
    }
    
    public String getNumeroCuentaBancaria(){
        return trabajoActual.getCuentaBancaria();
    }
    
    public String getNombreCategoria(){
	ConceptosCategorias conceptoCategoria = new ConceptosCategorias();
        conceptoCategoria.setIdconceptoreferencia(trabajoActual.getIdCategoria());
        conceptoCategoria.retrieveData();
        return conceptoCategoria.getName();
    }
    
    public int getIdCategoria(){
        return trabajoActual.getIdCategoria();
    }
    
    public void setIdCategoria(int _idCategoria){
        trabajoActual.setIdCategoria(_idCategoria);
    }
    
    public void setIdCodigoContrato(int _idCodigoContrato){
        trabajoActual.setIdCodigoContrato(_idCodigoContrato);
    }
    
    public void setNumeroCuentaBancaria(String _numCuentaBancaria){
        trabajoActual.setCuentaBancaria(_numCuentaBancaria);
    }
    
}
