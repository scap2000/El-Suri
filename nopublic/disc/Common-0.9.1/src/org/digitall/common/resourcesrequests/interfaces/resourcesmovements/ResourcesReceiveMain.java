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
 * ResourcesReceiveMain.java
 *
 * */
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
