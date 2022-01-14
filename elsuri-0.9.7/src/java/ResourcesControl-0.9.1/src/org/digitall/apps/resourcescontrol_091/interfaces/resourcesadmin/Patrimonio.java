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
 * Patrimonio.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Vector;

import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.apps.personalfiles.interfaces.PersonalPanel;
import org.digitall.apps.resourcescontrol.classes.HistorialRecursoDistinguible;
import org.digitall.apps.resourcescontrol_091.classes.DistinguishableResources;
import org.digitall.apps.resourcescontrol_091.classes.ResourcesDependency;
import org.digitall.apps.resourcescontrol_091.classes.ResourcesPerson;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.lib.sql.LibSQL;

public class Patrimonio {
    
    private HistorialRecursoDistinguible historialRecursoDistinguible;
    private Vector idsRecursosAsignar = new Vector();
    private int idPerson = -1;
    private int idDependencia = -1;
    private int idResource;
    private ResourcesDependency resourcesDependency;
    private Dependencia dependencia;
    private Persona persona;
    private ResourcesPerson resourcesPerson;
    private DistinguishableResources distingushableResources;
    private Resource recurso; 
    private RecursosVariosPanel recursosVariosPanel;
    private PersonalPanel personalPanel;
    private ListadoRecursosPatrimonialesPanel listadoRecursosPatrimonialesPanel;
    private Vector recursosDistSelectedToLiberty;
    private Persona personToReasigned;
    private Dependencia dependenciaToReasigned;
    private String dateAsigned = "";

    
    public Patrimonio() {
    }
    
    public boolean generarNumerosPatrimonios(Vector _sinPatrimonios){
	//se asignan los numeros de patrimonios a los recursos 
	//distinguibles que no tengan numero de patrimonio
	 boolean resultado = false;
	 Vector sinPatrimonios = _sinPatrimonios;
	 int hasta = sinPatrimonios.size();
	 for(int i = 0; i < hasta; i++){
	    Vector recurso = (Vector)sinPatrimonios.elementAt(i);
	    int idRecursoDist = Integer.parseInt("0"+recurso.elementAt(0));
	    String params = "" + idRecursoDist;
	     if(LibSQL.getInt("resourcescontrol.addresourcepatrimonio", params)!=idRecursoDist){
		resultado = false;       
	     }else{
		 resultado = true;
	     }
	 }
	 return(resultado);
    }
    
    public boolean saveDataResourcesPerson(String _dateAsigned,String _motivo,double _desgaste){
	boolean resultado = false;
	// Asignar los recursos a las personas
	grabarMovimientos( _dateAsigned, _motivo,_desgaste);
	asignarRecursosDistinguiblesAPersonas(_dateAsigned);
	return(true);//completar
    }
    
     public boolean grabarMovimientos(String _dateAsigned,String _motivo,double _desgaste){
	 boolean resultado = false;
	 Vector seleccionados = idsRecursosAsignar;
	 int hasta = seleccionados.size();
	 int idRecursoDistinguible;
	 for(int i = 0; i < hasta; i++){
	     //Lo siguiente guarda la asignacion de recursos en Historial
	    historialRecursoDistinguible = new HistorialRecursoDistinguible();
	     idRecursoDistinguible = Integer.parseInt(seleccionados.elementAt(i).toString());
	     historialRecursoDistinguible.setIdDistinguishableResource(idRecursoDistinguible);
	     historialRecursoDistinguible.setIdPerson(idPerson);
	     historialRecursoDistinguible.setInitDate(_dateAsigned);
	     historialRecursoDistinguible.setSubject(_motivo);
	     historialRecursoDistinguible.setWaste(_desgaste);
	     historialRecursoDistinguible.setIdDependency(dependencia.getIdDep());
	     int result = historialRecursoDistinguible.saveData();    
	 }
	 
	 return(true);//completar
     }
     
      public boolean asignarRecursosDistinguiblesAPersonas(String _dateAsigned){
	  boolean resultado = false;
	  int idPerson = persona.getIdPerson();
	  int hasta = idsRecursosAsignar.size();
	  for(int i = 0; i < hasta; i++){
	      resourcesPerson = new ResourcesPerson();
	      distingushableResources = new DistinguishableResources();
	      distingushableResources.setIdDistinguishableResource(Integer.parseInt(idsRecursosAsignar.elementAt(i).toString()));
	      distingushableResources.retrieveData();
	      distingushableResources.setAquisitionDate(_dateAsigned);
	      distingushableResources.setIdPerson(idPerson);
	      //Para ResourcesPerson
	       resourcesPerson.setIdPerson(idPerson);
	       resourcesPerson.setidDistResource(Integer.parseInt(idsRecursosAsignar.elementAt(i).toString()));
	       resourcesPerson.setDateAssigned(_dateAsigned);
	       resourcesPerson.setStockassigned(1);
	      //Para ResourcesPerson
	      if(distingushableResources.saveData() != -1){
		  resultado = true;
	      }
	      resourcesPerson.saveData();
	  }
	  return(resultado);
      }
    
    public int asignarRecursoDependencia(String dateAsigned){
	int resultado = -1;
	int cantidadAsignada = idsRecursosAsignar.size();
	resourcesDependency = new ResourcesDependency();
	resourcesDependency.setIdDependency(dependencia.getIdDep());
	resourcesDependency.setIdDistResource(recurso.getIdResource());
	resourcesDependency.setDateAssigned(dateAsigned);
	resourcesDependency.setSockAssigned(cantidadAsignada);
	resultado = resourcesDependency.saveData();
	return(resultado);
    }
    
    public boolean asignarRecursosDistinguiblesADependencia(String _dateAsigned){
	boolean resultado = false;
	int idPerson = -1;
	int hasta = idsRecursosAsignar.size();
	for(int i = 0; i < hasta; i++){
	    distingushableResources = new DistinguishableResources();
	    distingushableResources.setIdDistinguishableResource(Integer.parseInt(idsRecursosAsignar.elementAt(i).toString()));
	    distingushableResources.retrieveData();
	    distingushableResources.setAquisitionDate(_dateAsigned);
	    distingushableResources.setIdPerson(idPerson);
	    //Para ResourcesDependency
	    resourcesDependency = new ResourcesDependency();
	    resourcesDependency.setIdDependency(dependencia.getIdDep());
	    resourcesDependency.setIdDistResource(Integer.parseInt(idsRecursosAsignar.elementAt(i).toString()));
	    resourcesDependency.setDateAssigned(_dateAsigned);
	    resourcesDependency.setSockAssigned(1);
	    //Para ResourcesDependency
	    if(distingushableResources.saveData() != -1){
		resultado = true;
	    }
	    resourcesDependency.saveData();
	}
	return(resultado);
    }
    
    public boolean liberarRecursosPerson(){
	boolean resultado;
	String asParams = vectorToParams(recursosDistSelectedToLiberty);
	String params = "" + persona.getIdPerson() + ",'"+ asParams+ "',0" + "," + "'" +getDateAsigned() + "'";
	if(LibSQL.getInt("resourcescontrol.liberarrecursospersona", params) == 1){
	    resultado = true;
	}else{
	    resultado = false;
	}
	return(resultado);
    }
    
    public boolean liberarRecursosDependency(){
	boolean resultado;
	String asParams = vectorToParams(recursosDistSelectedToLiberty);
	String params = "" + dependencia.getIdDep()+ ",'"+ asParams+ "',0" + "," + "'" +getDateAsigned() + "'";
	if(LibSQL.getInt("resourcescontrol.liberarrecursosdependencia", params) == 1){
	    resultado = true;
	}else{
	    resultado = false;
	}
	return(resultado);
    }
    
    public boolean reasignarRecursosDepToDep(){
	boolean resultado;
	String asParams = vectorToParams(recursosDistSelectedToLiberty);
	String params = "" + dependencia.getIdDep()+ ",'"+ asParams+ "',0" + "," + "'" +getDateAsigned() + "'"+ "," +dependenciaToReasigned.getIdDep();
	if(LibSQL.getInt("resourcescontrol.reasignarrecursosdependenciatodependencia", params) == 1){
	    resultado = true;
	}else{
	    resultado = false;
	}
	return(resultado);
    }
    public boolean reasignarRecursosPersonToDep(){
	boolean resultado;
	String asParams = vectorToParams(recursosDistSelectedToLiberty);
	String params = "" + persona.getIdPerson()+ ",'"+ asParams+ "',0" + "," + "'" +getDateAsigned() + "'"+ "," +dependenciaToReasigned.getIdDep();
	if(LibSQL.getInt("resourcescontrol.reasignarrecursospersontodependencia", params) == 1){
	    resultado = true;
	}else{
	    resultado = false;
	}
	return(resultado);
    }
    
    public boolean reasignarRecursosPersonToPerson(){
	boolean resultado;
	String asParams = vectorToParams(recursosDistSelectedToLiberty);
	String params = "" + persona.getIdPerson()+ ",'"+ asParams+ "',0" + "," + "'" +getDateAsigned() + "'"+ "," +personToReasigned.getIdPerson();
	if(LibSQL.getInt("resourcescontrol.reasignarrecursospersontoperson", params) == 1){
	    resultado = true;
	}else{
	    resultado = false;
	}
	return(resultado);
    }
    
    public boolean reasignarRecursosDepToPerson(){	
	boolean resultado;
	String asParams = vectorToParams(recursosDistSelectedToLiberty);
	String params = "" + dependencia.getIdDep()+ ",'"+ asParams+ "',0" + "," + "'" +getDateAsigned() + "'"+ "," +personToReasigned.getIdPerson();
	if(LibSQL.getInt("resourcescontrol.reasignarrecursosdependenciatoperson", params) == 1){
	    resultado = true;
	}else{
	    resultado = false;
	}
	return(resultado);
    }
    
    private String vectorToParams(Vector _vector){
	String resultado = "";
	int tam = _vector.size();
	for(int i = 0 ; i < tam; i++){
	    resultado += _vector.elementAt(i).toString() + ",";  
	}
	return(resultado);
    }
    
    public Vector darBaja(Vector _idDistngguishableResourceToDown){
	Vector resultado = new Vector();
	String mensaje = "Exito";
	boolean realizado = false;
	String params = vectorToParams(_idDistngguishableResourceToDown);
	boolean checkLiberacion = verificarLiberacionDistinguishableResources(params);
	if(checkLiberacion ){
	    if(registrarBaja(params)){
		realizado = true;
	    }else{
		mensaje = "Error al grabar los datos de baja";
	    }
	}else{
	    mensaje = "Existen recursos que no estan liberados, por favor revise el estado de los recursos seleccionados";
	}
	resultado.add(mensaje);
	resultado.add(realizado);
	return(resultado);
    }
    
    private boolean registrarBaja(String _params){
	boolean resultado = false;
	resultado = LibSQL.getBoolean("resourcescontrol.darbaja","'"+_params+"'");
	return resultado;
    }
    
    private boolean verificarLiberacionDistinguishableResources(String _params){
	boolean resultado = false;
	resultado = LibSQL.getBoolean("resourcescontrol.verificarLiberacionDistinguishableResources","'"+_params+"'");
	return resultado;
    }
    
    public Vector getPersonasSinLiberacion(Vector _ids){
	Vector resultado = new Vector();
	ResultSet result;
	String params = vectorToParams(_ids);
	params = params.substring(0,params.length() - 1);
	result = LibSQL.exFunction("resourcescontrol.getPersonasSinLiberacion","'"+params+"'");
	resultado = resultAsVector(result);
	return resultado;
    }
    
    private Vector resultAsVector(ResultSet _result){
	Vector resultado = new Vector();
	try {
	    while(_result.next()){
		resultado.add(_result.getString(1));
	    }
	} catch (SQLException e) {
	    // TODO
	}
	return resultado;
    }
    
    public void setIDs(){
	
    }

    public void setIdsRecursosAsignar(Vector idsRecursosAsignar) {
	this.idsRecursosAsignar = idsRecursosAsignar;
    }

    public Vector getIdsRecursosAsignar() {
	return idsRecursosAsignar;
    }

    public void setIdPerson(int idPerson) {
	this.idPerson = idPerson;
    }

    public int getIdPerson() {
	return idPerson;
    }

    public void setIdDependencia(int idDependencia) {
	this.idDependencia = idDependencia;
    }

    public int getIdDependencia() {
	return idDependencia;
    }

    public void setResourcesDependency(ResourcesDependency resourcesDependency) {
	this.resourcesDependency = resourcesDependency;
    }

    public ResourcesDependency getResourcesDependency() {
	return resourcesDependency;
    }


    public void setDependencia(Dependencia dependencia) {
	this.dependencia = dependencia;
    }

    public Dependencia getDependencia() {
	return dependencia;
    }

    public void setPersona(Persona persona) {
	this.persona = persona;
    }

    public Persona getPersona() {
	return persona;
    }

    public void setHistorialRecursoDistinguible(HistorialRecursoDistinguible historialRecursoDistinguible) {
	this.historialRecursoDistinguible = historialRecursoDistinguible;
    }

    public HistorialRecursoDistinguible getHistorialRecursoDistinguible() {
	return historialRecursoDistinguible;
    }

    public void setResourcesPerson(ResourcesPerson resourcesPerson) {
	this.resourcesPerson = resourcesPerson;
    }

    public ResourcesPerson getResourcesPerson() {
	return resourcesPerson;
    }

    public void setDistingushableResources(DistinguishableResources distingushableResources) {
	this.distingushableResources = distingushableResources;
    }

    public DistinguishableResources getDistingushableResources() {
	return distingushableResources;
    }

    public void setRecurso(Resource recurso) {
	this.recurso = recurso;
    }

    public Resource getRecurso() {
	return recurso;
    }

    public void setIdResource(int idResource) {
	this.idResource = idResource;
    }

    public int getIdResource() {
	return idResource;
    }

    public void setRecursosVariosPanel(RecursosVariosPanel recursosVariosPanel) {
	this.recursosVariosPanel = recursosVariosPanel;
    }

    public RecursosVariosPanel getRecursosVariosPanel() {
	return recursosVariosPanel;
    }

    public void setPersonalPanel(PersonalPanel personalPanel) {
	this.personalPanel = personalPanel;
    }

    public PersonalPanel getPersonalPanel() {
	return personalPanel;
    }

    public void setListadoRecursosPatrimonialesPanel(ListadoRecursosPatrimonialesPanel listadoRecursosPatrimonialesPanel) {
	this.listadoRecursosPatrimonialesPanel = listadoRecursosPatrimonialesPanel;
    }

    public ListadoRecursosPatrimonialesPanel getListadoRecursosPatrimonialesPanel() {
	return listadoRecursosPatrimonialesPanel;
    }

    public void setRecursosDistSelectedToLiberty(Vector recursosDistSelectedToLiberty) {
	this.recursosDistSelectedToLiberty = recursosDistSelectedToLiberty;
    }

    public Vector getRecursosDistSelectedToLiberty() {
	return recursosDistSelectedToLiberty;
    }

    public void setPersonToReasigned(Persona personToReasigned) {
	this.personToReasigned = personToReasigned;
    }

    public Persona getPersonToReasigned() {
	return personToReasigned;
    }

    public void setDependenciaToReasigned(Dependencia dependenciaToReasigned) {
	this.dependenciaToReasigned = dependenciaToReasigned;
    }

    public Dependencia getDependenciaToReasigned() {
	return dependenciaToReasigned;
    }

    public void setDateAsigned(String dateAsigned) {
	this.dateAsigned = dateAsigned;
    }

    public String getDateAsigned() {
	return dateAsigned;
    }
}
