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
 * ResourcesReleasePanel.java
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

public class ResourcesReleasePanel extends BasicPrimitivePanel{
    private BasicPanel content = new BasicPanel();
    private Resource recurso;   
    private Persona persona;
    private Dependencia dependencia;
    private RecursosAsignadosPanel recursosAsignadosPanel;
    private boolean isPersonAsigned;
    private TFInput TFDateAsigned = new TFInput(DataTypes.DATE,"Fecha de Liberación",true);
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();
    private Patrimonio patrimonio = new Patrimonio();
    private BasicPanel panelSur = new BasicPanel();

    public ResourcesReleasePanel(Resource _recurso,Persona _persona,Dependencia _dependencia) {
	try {
	    recurso = _recurso;
	    persona = _persona;
	    dependencia = _dependencia;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    private void jbInit() throws Exception {
	this.setSize(new Dimension(500, 429));
	content.setLayout(new BorderLayout());
	TFDateAsigned.setBounds(new Rectangle(5, 5, 125, 35));
	panelSur.setBounds(new Rectangle(0, 0, 480, 55));
	panelSur.setPreferredSize(new Dimension(480, 55));
	panelSur.setLayout(null);
	panelSur.add(TFDateAsigned,null);
	panelSur.setBorder(BorderPanel.getBorderPanel(""));
	TFDateAsigned.setValue(Proced.setFormatDate(Environment.currentDate,true));
	setPersonAsigned();
	recursosAsignadosPanel = new RecursosAsignadosPanel(recurso,persona,dependencia,isPersonAsigned,true);
	content.add(panelSur, BorderLayout.SOUTH);
	content.add(recursosAsignadosPanel,BorderLayout.CENTER);
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
	this.add(content, null);
	recursosAsignadosPanel.refresh();
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {       
	liberarRecursos();
    }
    
    private void liberarRecursos(){
	if(recursosAsignadosPanel.getCounSelected() > 0){
	    if(Advisor.question("Confirmación","¿Desea realizar la operación?")){
		Vector recursosDistSelectedToLiberty = new Vector();
		recursosDistSelectedToLiberty = recursosAsignadosPanel.getSelectedsID();
		patrimonio.setRecursosDistSelectedToLiberty(recursosDistSelectedToLiberty);
		patrimonio.setDateAsigned(Proced.setFormatDate(TFDateAsigned.getDate(), false));
		System.out.println(TFDateAsigned.getDate());
		if(isPersonAsigned){
		    patrimonio.setPersona(persona);
		    if(patrimonio.liberarRecursosPerson()){
			Advisor.messageBox("Se grabó la liberación con exito.","Aviso");		    
			cerrar();
		    }else{
			Advisor.messageBox("Se produjo un error al grabar los datos.","Error");                    
		    }
		}else{
		    patrimonio.setDependencia(dependencia);
		    if(patrimonio.liberarRecursosDependency()){
			Advisor.messageBox("Se grabó la liberación con exito.","Aviso");                
			cerrar();
		    }else{
			Advisor.messageBox("Se produjo un error al grabar los datos.","Error");                    
		    }
		}
	    }
	}else{
	    Advisor.messageBox("Debe seleccionar al menos un recurso.","Acción invalida");
	}
	
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {      			
	cerrar();
    }
    
    private void cerrar(){
	getParentInternalFrame().close();
    }
    
    private void setPersonAsigned(){
	if(persona != null){
	    isPersonAsigned = true;
	}else{
	    isPersonAsigned = false;
	}
    }

    public void setRecurso(Resource recurso) {
	this.recurso = recurso;
    }

    public Resource getRecurso() {
	return recurso;
    }

    public void setPersona(Persona persona) {
	this.persona = persona;
    }

    public Persona getPersona() {
	return persona;
    }

    public void setDependencia(Dependencia dependencia) {
	this.dependencia = dependencia;
    }

    public Dependencia getDependencia() {
	return dependencia;
    }
}
