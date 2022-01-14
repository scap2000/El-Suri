package org.digitall.common.resourcesrequests.interfaces.resourcesmovements;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.resourcescontrol.interfaces.ResourcesList;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;

public class ResourcesReceiveMain extends BasicTabContainer {

    private ResourcesReceiveMgmt resourcesMovementsMgmt = new ResourcesReceiveMgmt(this);
    private ResourcesReceiveList resourcesMovementsDetail = new ResourcesReceiveList(this);
    private AcceptButton btnResources = new AcceptButton();

    public ResourcesReceiveMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBounds(new Rectangle(10, 10, 730, 540));
	this.setSize(new Dimension(730, 540));
	btnResources.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnResources_actionPerformed(e);
				    }

				}
	);
	btnResources.setText("Administración\nde Recursos");
	addTab("Datos del Movimiento", resourcesMovementsMgmt);
	addTab("Datos del Remito", resourcesMovementsDetail);
	this.setTitle("");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	//getParentInternalFrame().getGeneralButtons().addButton(btnResources);
	 getParentInternalFrame().setInfo("Seleccione un Proveedor y luego vaya a la pestaña \"Datos del Remito\"");
    }

    public ResourcesReceiveList getResourcesMovementsDetail() {
	return resourcesMovementsDetail;
    }

    public ResourcesReceiveMgmt getResourcesMovementsMgmt() {
	return resourcesMovementsMgmt;
    }

    private void btnResources_actionPerformed(ActionEvent e) {
	ResourcesList resourcesList = new ResourcesList();
	ExtendedInternalFrame resourcesListContainer = new ExtendedInternalFrame("Administración de Recursos");
	resourcesListContainer.setCentralPanel(resourcesList);
	resourcesListContainer.show();
    }

    public void setTAObservations(String _text) {
	resourcesMovementsMgmt.setTaObservations(_text);
    }
    
    public void changeSelectedTab() {
	if (getParentInternalFrame() != null) {
	    if (getSelectedTab() == 0) {
	        getParentInternalFrame().setInfo("Seleccione un Proveedor y luego vaya a la pestaña \"Datos del Remito\"");
	    } else {
	        getParentInternalFrame().setInfo("Combine ítems de diversas Órdenes de Compra y genere la Nota de Recepción presionando el botón \"Grabar e Imprimir\"");
	    }
	}
    }

}
