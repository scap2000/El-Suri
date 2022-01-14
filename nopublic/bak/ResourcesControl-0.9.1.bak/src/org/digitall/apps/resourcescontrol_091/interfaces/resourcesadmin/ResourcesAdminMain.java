package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

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
