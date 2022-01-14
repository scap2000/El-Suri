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
 * AsignacionPatrimonioRecursoPanel.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import org.digitall.apps.personalfiles.classes.Dependencia;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.apps.personalfiles.interfaces.PersonalPanel;
import org.digitall.apps.resourcescontrol.classes.HistorialRecursoDistinguible;
import org.digitall.apps.resourcescontrol_091.classes.DistinguishableResources;
import org.digitall.apps.resourcescontrol_091.classes.ResourcesDependency;
import org.digitall.apps.resourcescontrol_091.classes.ResourcesPerson;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class AsignacionPatrimonioRecursoPanel extends BasicPrimitivePanel{
    private BasicPanel bpCentro = new BasicPanel();
    private TFInput tfiNombreRecurso = new TFInput(DataTypes.STRING,"Resource",false);
    private TFInput tfFechaIni = new TFInput(DataTypes.DATE,"Fecha de Entrega",false);
    private TFInput tfiMotivo = new TFInput(DataTypes.STRING,"Motivo",false);
    private TFInput tfDesgaste = new TFInput(DataTypes.DOUBLE,"Desgaste",false);
    private TFInput tfiToAssigned;
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();
       
    private int idPerson = -1;
    private int idDependencia = -1;
    private int idResource;
    private HistorialRecursoDistinguible historialRecursoDistinguible = null;
    private RecursosVariosPanel recursosVariosPanel;

    private PersonalPanel personalPanel;
    private ListadoRecursosPatrimonialesPanel listadoRecursosPatrimonialesPanel;
    private Vector idsRecursosAsignar = new Vector();
    private Vector datosPersona;
    private Resource recurso;	
    private Persona persona;
    private Dependencia dependencia;
    private boolean isPersonAssing;
    private ResourcesPerson resourcesPerson;
    private DistinguishableResources distingushableResources;
    private ResourcesDependency resourcesDependency;
    private Patrimonio patrimonio = new Patrimonio();
    
    public AsignacionPatrimonioRecursoPanel(boolean _isPersonAssing) {
	try {
	    isPersonAssing = _isPersonAssing;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(500, 303));
	this.setPreferredSize(new Dimension(500, 400));	
	setTextToAssigned();
	bpCentro.setLayout(null);
	bpCentro.setBorder(BorderPanel.getBorderPanel("Agregar asignación de recurso patrimonial"));
	tfiNombreRecurso.setBounds(new Rectangle(20, 80,  460, 35));
	tfiNombreRecurso.setEnabled(false);
	tfFechaIni.setBounds(new Rectangle(20, 190, 460, 35));
	tfiMotivo.setBounds(new Rectangle(20, 165, 460, 35));
	tfDesgaste.setBounds(new Rectangle(20, 280, 100, 35));
	tfFechaIni.setBounds(new Rectangle(20, 125, 130, 35));
	tfDesgaste.setBounds(new Rectangle(20, 210, 100, 35));
	tfiToAssigned.setBounds(new Rectangle(20, 35, 335, 35));
	tfiToAssigned.setEnabled(false);
	tfFechaIni.setValue(Proced.setFormatDate(Environment.currentDate,true));
	bpCentro.add(tfiToAssigned, null);
	bpCentro.add(tfDesgaste, null);
	bpCentro.add(tfiMotivo, null);
	bpCentro.add(tfFechaIni, null);
	bpCentro.add(tfiNombreRecurso, null);
	this.add(bpCentro, BorderLayout.CENTER);
	
	btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }

			   }
	);
	
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }	
	);
	this.addButton(btnClose);
	this.addButton(btnSave);
    }
    
    private void setTextToAssigned(){
	if(isPersonAssing){
	    tfiToAssigned = new TFInput(DataTypes.STRING,"Person",false);
	}else{
	    tfiToAssigned = new TFInput(DataTypes.STRING,"Dependencia",false);
	}
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {	
	getParentInternalFrame().close();
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {	
        grabarAsignacionPatrimonial();    
    }
    
    private void grabarAsignacionPatrimonial(){
	String mensajeError = pasoControles().elementAt(0).toString();//devuelve el mensaje
	String mensajeGeneracionPatrimonio = "";
	boolean controlOk = Boolean.parseBoolean(pasoControles().elementAt(1).toString());//devuelve una bandera
	if(controlOk){
	    Vector sinPatrimonios = existResourcesDistinguishableNonPat();
	    int cantRecursosSinPatrimonios = sinPatrimonios.size();
	    if( cantRecursosSinPatrimonios > 0){
	        boolean respuesta = Advisor.question("Generación Patrimonio: ","Existen " + cantRecursosSinPatrimonios + " recurso/s sin Número de Patrimonio \n¿Desea generar Números de Patrimonio?");
		if(respuesta){	
		    if(!patrimonio.generarNumerosPatrimonios(sinPatrimonios)){
		        mensajeGeneracionPatrimonio = "y no se pudo generar los números de patrimonios";
			Advisor.messageBox("No se pudo generar los números de patrimonio","Error");
		    }else{
		        mensajeGeneracionPatrimonio = "y se generó número de patrimonio para "+ cantRecursosSinPatrimonios+ " recurso/s distinguible/s";
		    }
		}
	    }
	    boolean check = false;
	    if(isPersonAssing){
		check = saveDataResourcesPerson();
	    }else{
	        check = saveDataResourcesDependency();
	    }
	    if(check){
	        Advisor.messageBox("Se grabó con éxito la asignación de rescursos "+mensajeGeneracionPatrimonio,"Aviso");
		listadoRecursosPatrimonialesPanel.getRecursosVariosPanel().refresh();
		listadoRecursosPatrimonialesPanel.getParentInternalFrame().close();
	        getParentInternalFrame().close();
	    }else{
	        Advisor.messageBox("No se pudo grabar asignación de recursos","Error");
	    }
	}else{
	    Advisor.messageBox(mensajeError,"Error");
	}
    }
    
    private Vector existResourcesDistinguishableNonPat(){
	//Sacar de los recursos distinguibles que solamente fueron seleccionados
	Vector resultado = new Vector();
	Vector seleccionados  = listadoRecursosPatrimonialesPanel.getGrillaPanelPatrimonio().getSelectedsVector();
	int hasta = seleccionados.size();
	for(int i = 0; i< hasta; i++){
	    Vector filaRecursoDist = (Vector)seleccionados.elementAt(i);
	    String numPatrimonio = ""+filaRecursoDist.elementAt(8).toString();
	    if(numPatrimonio.equals("")){
		resultado.add((Vector)filaRecursoDist);
	    }	}
	return(resultado);
    }
    
    private Vector pasoControles(){
	Vector resultado = new Vector();
	String mjeError = "";//0 Mensaje de Error
	boolean control = false;//1 Bandera de Control
	if(!tfFechaIni.getDate().equals("")){
	    control = true;
	}else{
	    mjeError = "Debe ingresar una fecha de asignación o entrega";
	}
	resultado.add(mjeError);
	resultado.add(control);
	return(resultado);
    }
    private boolean saveDataResourcesPerson() {
	boolean resultado = false;
	// Asignar los recursos a las personas
	String date = Proced.setFormatDate(tfFechaIni.getDate().toString(),false);
	String motivo = tfiMotivo.getString();
	double desgaste = Double.parseDouble(tfDesgaste.getString());
	patrimonio.grabarMovimientos(date,motivo,desgaste);
	patrimonio.asignarRecursosDistinguiblesAPersonas(date);

	//return resultado;
	return(true);//completar
    }
    
     private boolean saveDataResourcesDependency() {
	 boolean resultado = false;
	 // Asignar los recursos a la dependencia
	 idPerson = -1;
	 String date = Proced.setFormatDate(tfFechaIni.getDate().toString(),false);
	 String motivo = tfiMotivo.getString();
	 double desgaste = Double.parseDouble(tfDesgaste.getString());
	 patrimonio.setIdPerson(idPerson);
	 patrimonio.grabarMovimientos(date,motivo,desgaste);
	 patrimonio.asignarRecursosDistinguiblesADependencia(date);

	 //return resultado;
	 return(true);//completar
     }
     
     public void loadDataRecurso() {
	 //Cargo la persona a la cual se le va asignar un recurso si es necesario
	 if(isPersonAssing){
	     persona = new Persona();
	     persona.setIdPerson(idPerson);
	     persona.retrieveData();
	     patrimonio.setPersona(persona);
	 }else{
	     idPerson = -1;
	     patrimonio.setIdPerson(idPerson);
	 }
	 //Cargo el Recurso que va a ser asignado 
	 int idRecurso = Integer.parseInt(listadoRecursosPatrimonialesPanel.getRecursosVariosPanel().getGrillaPanelRecursos().getSelectedsID().elementAt(0).toString());
	 patrimonio.setIdResource(idRecurso);
	 recurso = new Resource();
	 recurso.setIdResource(idRecurso);
	 recurso.retrieveData();
	 recurso = new Resource();
	 recurso.setIdResource(idRecurso);
	 recurso.retrieveData();
	 patrimonio.setRecurso(recurso);
	 String nombreRecurso = recurso.getName();       
	 if(isPersonAssing){
	     datosPersona = (Vector)(personalPanel.getGridPanelPersonal().getSelectedsVector().elementAt(0));
	     dependencia = new Dependencia();//Se asigna como vacia no existe
	     patrimonio.setDependencia(dependencia);
	     String nombrePersona = datosPersona.elementAt(4).toString();
	     tfiToAssigned.setValue(nombrePersona);   
	 }else{
	     dependencia = listadoRecursosPatrimonialesPanel.getDependencia();
	     patrimonio.setDependencia(dependencia);
	    String nivelJerarquico = "";
	    if(dependencia.getNivelJerarquico() != 0){
	        nivelJerarquico = dependencia.getNombreNivelJerarquico() ;
	    }
	     tfiToAssigned.setValue(nivelJerarquico+ " " + dependencia.getNombre());   
	 }
	 tfiNombreRecurso.setValue(nombreRecurso);       
     }
    
    public void setIdPerson(int _idPerson) {
	idPerson = _idPerson;
	patrimonio.setIdPerson(_idPerson);
    }
    
    public void setIdResurce(int _idResource) {
	idResource = _idResource;
	patrimonio.setIdResource(_idResource);
    }
    
    public void setIdDependencia(int _idDependencia) {
	idDependencia = _idDependencia;
	patrimonio.setIdDependencia(_idDependencia);
    }    
    
    public void setNombreDependencia(String _nombreDependencia) {
	tfiToAssigned.setValue(_nombreDependencia);
    }
    
    public void setNombrePersona(String _nombrePersona) {
	tfiToAssigned.setValue(_nombrePersona);
    }
    
    public void setNombreRecurso(String _nombreRecurso) {
	tfiNombreRecurso.setValue(_nombreRecurso);
    }
    
    public void setRecursosVariosPanel(RecursosVariosPanel _recursosVariosPanel) {
	recursosVariosPanel = _recursosVariosPanel;
	patrimonio.setRecursosVariosPanel(_recursosVariosPanel);
    }
    
    public void setPersonalPanel(PersonalPanel _personalPanel) {
	personalPanel = _personalPanel;
	patrimonio.setPersonalPanel(_personalPanel);
    }

    public void setListadoRecursosPatrimonialesPanel(ListadoRecursosPatrimonialesPanel _listadoRecursosPatrimonialesPanel) {
	this.listadoRecursosPatrimonialesPanel = _listadoRecursosPatrimonialesPanel;
	patrimonio.setListadoRecursosPatrimonialesPanel(_listadoRecursosPatrimonialesPanel);
    }

    public ListadoRecursosPatrimonialesPanel getListadoRecursosPatrimonialesPanel() {
	return listadoRecursosPatrimonialesPanel;
    }

    public void setRecursosAsignar(Vector recursosAsignar) {
	this.idsRecursosAsignar = recursosAsignar;
	patrimonio.setIdsRecursosAsignar(idsRecursosAsignar);
    }

    public Vector getRecursosAsignar() {
	return idsRecursosAsignar;
    }
}
