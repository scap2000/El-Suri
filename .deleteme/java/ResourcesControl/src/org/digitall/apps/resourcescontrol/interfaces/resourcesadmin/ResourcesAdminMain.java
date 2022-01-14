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
 * ResourcesAdminMain.java
 *
 * */
package org.digitall.apps.resourcescontrol.interfaces.resourcesadmin;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.interfaces.SkillList;
import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesDeliverList;
import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesReceiveMain;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;

public class ResourcesAdminMain extends BasicTabContainer{

    private ResourcesAdminList resourcesAdminList = new ResourcesAdminList(this);
    private ResourcesAdminToReserveList resourcesAdminToReserveList = new ResourcesAdminToReserveList(this);
    private ResourcesAdminToAvailableList resourcesAdminToAvailableList = new ResourcesAdminToAvailableList(this);
    private Resource resource;
    private AcceptButton btnInternalDespathNotes = new AcceptButton();
    private AcceptButton btnExternalDespatchNotes = new AcceptButton();
    private AcceptButton btnSkills = new AcceptButton();
    
    public ResourcesAdminMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(790, 435));
	this.setPreferredSize(new Dimension(790, 435));
	btnSkills.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnSkills_actionPerformed(e);
				 }

			     }
	);
	btnSkills.setText("Calificaciones\nde Habilidades");
	btnInternalDespathNotes.setText("Entrega\nde Recursos");
	btnInternalDespathNotes.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnInternalDespatchNotes_actionPerformed(e);
				}

			    }
	);
	btnExternalDespatchNotes.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnExternalDespatchNotes_actionPerformed(e);
				 }

			     }
	);
	btnExternalDespatchNotes.setText("Ingreso de\nRecursos Externos");
	addTab(resourcesAdminList);
	addTab(resourcesAdminToReserveList);
	addTab(resourcesAdminToAvailableList);    
	setTitle("");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnInternalDespathNotes);
	getParentInternalFrame().getGeneralButtons().addButton(btnExternalDespatchNotes);
	getParentInternalFrame().getGeneralButtons().addButton(btnSkills);
    }
    
    public ResourcesAdminList getResourcesAdminList() {
	return resourcesAdminList;
    }

    public ResourcesAdminToReserveList getResourcesAdminToReserveList() {
	return resourcesAdminToReserveList;
    }

    public ResourcesAdminToAvailableList getResourcesAdminToAvailableList() {
	return resourcesAdminToAvailableList;
    }

    public void setResource(Resource resource) {
	this.resource = resource;
	resourcesAdminToReserveList.setResource(resource);
	resourcesAdminToAvailableList.setResource(resource);
    }

    private void btnSkills_actionPerformed(ActionEvent e) {
	SkillList skillsList = new SkillList(); 
	ExtendedInternalFrame skillsListContainer = new ExtendedInternalFrame("Calificacion de Habilidades");
	skillsListContainer.setCentralPanel(skillsList);
	skillsListContainer.show();
    }    
    
    private void btnExternalDespatchNotes_actionPerformed(ActionEvent e) {
	ResourcesReceiveMain resourcesMovementsMain = new ResourcesReceiveMain();
	
	ExtendedInternalFrame resourcesMovementsMainContainer = new ExtendedInternalFrame("Ingresos de Recursos Externo");
	resourcesMovementsMainContainer.setCentralPanel(resourcesMovementsMain);
	resourcesMovementsMainContainer.show();
    }
    
    private void btnInternalDespatchNotes_actionPerformed(ActionEvent e) {
	ResourcesDeliverList internalDespatchNotesMain = new ResourcesDeliverList();
	
	ExtendedInternalFrame internalDespatchNotesMainContainer = new ExtendedInternalFrame("Entrega de Recursos Interno");
	internalDespatchNotesMainContainer.setCentralPanel(internalDespatchNotesMain);
	internalDespatchNotesMainContainer.show();
    }
}
