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
 * PaneJobs.java
 *
 * */
package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.personalfiles.classes.Persona;
import org.digitall.common.personalfiles.classes.TrabajoPersona;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class PaneJobs extends BasicContainerPanel{

    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicPanel jPanel1 = new BasicPanel();
    private TFInput tfSalary = new TFInput(DataTypes.DOUBLE, "Salary",true);
    private TFInput tfDateDesignation = new TFInput(DataTypes.DATE, "AssignedDate", true);
    private TFInput tfDateStart = new TFInput(DataTypes.DATE, "StartDate", true);
    private TFInput tfDateEnd = new TFInput(DataTypes.DATE, "EndDate", false);
    private CBInput cbDependency = new CBInput(0, "Dependency");
    private CBInput cbJobs = new CBInput(0, "Task");
    private BasicPanel jPanel2 = new BasicPanel();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BasicPanel panelBotonesJobs = new BasicPanel();
    private DeleteButton btnDeleteJob = new DeleteButton();
    private AssignButton btnAddJobs = new AssignButton(true);
    private BasicLabel jLabel3 = new BasicLabel(); 
    private PersonMgmt parent;    
    private TrabajoPersona trabajoPersona = null;
    private GridPanel listPanelJobs;
    private boolean actualizando;
    
    public PaneJobs(){
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PaneJobs(PersonMgmt _personMgmt){
	try {
	    parent = _personMgmt;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(borderLayout1);
	this.setSize(580,190);
	jPanel1.setLayout(null);	
	jPanel1.setSize(new Dimension(400, 610));
	tfSalary.setBounds(new Rectangle(10, 45, 90, 35));
	tfDateDesignation.setBounds(new Rectangle(10, 85, 120, 35));
	tfDateStart.setBounds(new Rectangle(145, 85, 120, 35));
	tfDateEnd.setBounds(new Rectangle(275, 85, 120, 35));
	cbDependency.setBounds(new Rectangle(10, 5, 270, 35));
	cbJobs.setBounds(new Rectangle(325, 5, 235, 35));
	jPanel2.setLayout(borderLayout2);
	jPanel2.setBorder(BorderPanel.getBorderPanel("Agregar Cargo"));
        jPanel1.add(cbJobs, null);
        jPanel1.add(cbDependency, null);
        jPanel1.add(tfDateEnd, null);
        jPanel1.add(tfDateStart, null);
        jPanel1.add(tfDateDesignation, null);
	jPanel1.add(tfSalary, null);
	jPanel2.add(jPanel1, BorderLayout.CENTER);	
	panelBotonesJobs.setPreferredSize(new Dimension(580, 40));
	panelBotonesJobs.setLayout(null);
	jLabel3.setText("Puede agregar o borrar cargos con los botones a su derecha -->");
	jLabel3.setBounds(new Rectangle(60, 20, 385, 15));
	btnDeleteJob.setBounds(new Rectangle(535, 15, 30, 25));
	btnDeleteJob.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnDeleteJob_actionPerformed(e);
				    }

				}
	);
	btnAddJobs.setBounds(new Rectangle(505, 15, 30, 25));
	btnAddJobs.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnAddJobs_actionPerformed(e);
				  }

			      }
	);
	panelBotonesJobs.add(jLabel3, null);
	panelBotonesJobs.add(btnAddJobs, null);
	panelBotonesJobs.add(btnDeleteJob, null);	
	jPanel2.add(panelBotonesJobs, BorderLayout.SOUTH);	
	this.add(jPanel2, BorderLayout.CENTER);
	cbDependency.autoSize();
	cbJobs.autoSize();
	loadCombos();	
	btnDeleteJob.setEnabled(false);
    }
    
    public boolean saveData() {
	boolean resul = false;	
	if (trabajoPersona == null) {
	    trabajoPersona = new TrabajoPersona();
	    actualizando = false;
	}   	
	trabajoPersona.setIdJobs(Integer.parseInt(cbJobs.getSelectedValue().toString()));
	Persona person = parent.getPerson();
	trabajoPersona.setIdPerson(person.getIdPerson());
	trabajoPersona.setSalary(Float.parseFloat(tfSalary.getValue().toString()));
	trabajoPersona.setDateDesignation(Proced.setFormatDate(tfDateDesignation.getString(), false));
	trabajoPersona.setDateStart(Proced.setFormatDate(tfDateStart.getString(), false));
	trabajoPersona.setDateEnd(Proced.setFormatDate(tfDateEnd.getString(), false));	
	trabajoPersona.setIddependency(Integer.parseInt(cbDependency.getSelectedValue().toString()));        
	if (!tfDateDesignation.getString().equals(""))  {
	    if ((!tfDateStart.getString().equals(""))&&(!tfDateEnd.getString().equals("")))  {
	        if (isFechasSuperpuestas()){
	            Advisor.messageBox("¡Las fechas ingresadas estan superpuestas!", "Error");            
	        }else{                     
	            if (trabajoPersona.saveData() > 0) {
	                resul = true;                
	            }else{
	                Advisor.messageBox("¡Ocurrio un error al grabar los datos!", "Error");
	            }
	        } 
	    } else {
	        if (isDadoDeBajaCargosAnterior() && !actualizando)  {
	            if (isFechasSuperpuestas() && !actualizando){
	                Advisor.messageBox("¡Las fechas ingresadas estan superpuestas!", "Error");            
	            }else{    
			if (!isFechaIniAnteriorFechaFin() && !actualizando)  {
			    if (trabajoPersona.saveData() > 0) {
			        resul = true;                
			    }else{
			        Advisor.messageBox("¡Ocurrio un error al grabar los datos!", "Error");
			    }	
			}else{
			    Advisor.messageBox("¡La feha de inicio debe ser posterior a la fecha fin del ultimo cargo!", "Error");            
			}				                
	            }                       
	        } else {
	            Advisor.messageBox("¡Debe dar de baja el cargo anterior!", "Error");
	        }
	    }	    				    
	}else {
	    Advisor.messageBox("¡Falta fecha de asignación!", "Error");
	}
	
	return resul;
    }
    
    public boolean isDadoDeBajaCargosAnterior() {
	boolean band = true;   
	listPanelJobs = parent.getListPanelJobs();	
	int nroRow = 0;
	if ((listPanelJobs.getTable().getRowCount() > 0)) {//Pregunto > 1 por que si es el 1º cargo que tiene no hago el control
	    nroRow = listPanelJobs.getTable().getRowCount() -1;
	    String fechaFin = listPanelJobs.getValuesAt(7).get(nroRow)+"";	    	    	   
	    if (fechaFin.equals(""))  {
	        band = false;
	    }
	}	
	return band;     
    }
    
    public boolean isFechaIniAnteriorFechaFin() {
	boolean band = false;   
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
	listPanelJobs = parent.getListPanelJobs();      
	int nroRow = 0;
	try{
	    if ((listPanelJobs.getTable().getRowCount() > 0)  && !actualizando) {//Pregunto > 1 por que si es el 1º cargo que tiene no hago el control
		nroRow = listPanelJobs.getTable().getRowCount() -1;
		String fechaFin = listPanelJobs.getValuesAt(7).get(nroRow)+"";                  	    
		Date dateEnd = sdf.parse(fechaFin);     
	        Date dateStart = sdf.parse(tfDateStart.getString());        
		if (dateStart.before(dateEnd))  {
		    band = true;
		    System.out.println("band :"+band);
		}
	    }       
	}
	catch (ParseException e) {
	    e.printStackTrace();
	}
	return band;     
    }
    
    public boolean isFechasSuperpuestas(){
	boolean band = false;   
	try{
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");      
	    Date dateDesignation = sdf.parse(tfDateDesignation.getString());
	    Date dateStart = sdf.parse(tfDateStart.getString());	    
	    if (tfDateEnd.getString().equals("")) {
	        if (dateDesignation.after(dateStart))  {
		    band = true;
	        }
	        	
	    }else {
	        Date dateEnd = sdf.parse(tfDateEnd.getString());          
	        if ((dateDesignation.after(dateStart))||(dateEnd.before(dateDesignation)))  {
	            band = true;
	        }
	    }	    
	}
	catch (ParseException e) {
	    e.printStackTrace();
	}
	return band;    
    }
    

    private void loadCombos() {
	cbDependency.loadJCombo(LibSQL.exFunction("personalfiles.getAllDependency", ""));
	cbDependency.setSelectedValue("ORGANIGRAMA GENERAL");
	cbJobs.loadJCombo(LibSQL.exFunction("personalfiles.getAllJobs", ""));
    }
    
    private void clearData(){
	tfSalary.setValue(0);
	tfDateDesignation.setValue("");
	tfDateStart.setValue("");
	tfDateEnd.setValue("");
	cbDependency.setSelectedID(1);
	cbJobs.setSelectedID(1);
	trabajoPersona = null;
    }

    private void btnAddJobs_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    parent.refreshGridJobs();
	    clearData();
	    btnDeleteJob.setEnabled(false);
	}	
    }

    public String getSalary() {
	return tfSalary.getValue().toString();
    }

    public void setSalary(String _salary) {
	tfSalary.setValue(_salary);
    }

    public String getDateDesignation() {
	return tfDateDesignation.getValue().toString();
    }

    public void setDateDesignation(String _dateDesignation) {
	tfDateDesignation.setValue(_dateDesignation);
    }

    public String getDateStart() {
	return tfDateStart.getValue().toString();
    }

    public void setDateStart(String _dateStart) {
	tfDateStart.setValue(_dateStart);
    }

    public String getDateEnd() {
	return tfDateEnd.getValue().toString();
    }

    public void setDateEnd(String _dateEnd) {
	tfDateEnd.setValue(_dateEnd);
    }

    public String getDependency() {
	return cbDependency.getSelectedItem().toString();
    }

    public void setDependency(String _dependency) {
	cbDependency.setSelectedValue(_dependency);;
    }

    public String getJobs() {
	return cbJobs.getSelectedItem().toString();
    }

    public void setJobs(String _jobs) {
	cbJobs.setSelectedValue(_jobs);
    }
    
    public void setJobsPerson(TrabajoPersona _trabajoPersona){
	this.trabajoPersona = _trabajoPersona;
	loadData();
	actualizando = true;
	btnDeleteJob.setEnabled(true);
    }
    
    private void loadData(){    
	tfSalary.setValue(trabajoPersona.getSalary());
	tfDateDesignation.setValue(trabajoPersona.getDateDesignation());
	tfDateStart.setValue(trabajoPersona.getDateStart());
	tfDateEnd.setValue(trabajoPersona.getDateEnd());  	
	cbJobs.setSelectedID(trabajoPersona.getIdJobs());
	cbDependency.setSelectedID(trabajoPersona.getIddependency());
    }

    private void btnDeleteJob_actionPerformed(ActionEvent e) {	
	if (trabajoPersona != null) {
	    if (Advisor.question("Borrar Cargo", "¿Está seguro de borrar el cargo seleccionado?")) {	      
		if (LibSQL.getInt("personalfiles.delJobsPerson", "" + trabajoPersona.getIdJobPerson()) == 0) {
		    parent.refreshGridJobs();
		    clearData();
		    btnDeleteJob.setEnabled(false);		
		}
	    }
	}
    }

}
