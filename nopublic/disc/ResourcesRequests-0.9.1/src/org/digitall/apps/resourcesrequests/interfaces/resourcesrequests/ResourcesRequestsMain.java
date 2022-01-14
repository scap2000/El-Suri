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
 * ResourcesRequestsMain.java
 *
 * */
package org.digitall.apps.resourcesrequests.interfaces.resourcesrequests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.resourcesrequests.interfaces.resourcesrequests.ResourcesRequestsAuthMain;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;


public class ResourcesRequestsMain extends BasicTabContainer {

    private ResourcesRequestsList requestsList = new ResourcesRequestsList();
    private ResourcesRequestDetailMgmt detailMgmt = new ResourcesRequestDetailMgmt();
    private AcceptButton btnRequestAuth = new AcceptButton();
    private BasicPrimitivePanel firstTab = new BasicPrimitivePanel();
    private BasicPrimitivePanel secondTab = new BasicPrimitivePanel();
    private BasicContainerPanel firstPanel = new BasicContainerPanel();
    private BasicContainerPanel secondPanel = new BasicContainerPanel();

    public ResourcesRequestsMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(768, 585));
	this.setPreferredSize(new Dimension(768, 470));
	firstPanel.setLayout(new BorderLayout());
	firstPanel.add(detailMgmt, BorderLayout.CENTER);
	firstTab.setTitle("Nuevo Pedido de Materiales");
	firstTab.setContent(firstPanel);
	addTab(firstTab);
	secondPanel.setLayout(new BorderLayout());
	secondPanel.add(requestsList, BorderLayout.CENTER);
	secondTab.setTitle("Pedidos de Materiales realizados");
	secondTab.setContent(secondPanel);
	btnRequestAuth.setText("Autorización\nde Pedidos de Materiales");
	btnRequestAuth.setToolTipText("Abre la ventana de Autorización de Pedidos de Materiales");
	btnRequestAuth.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnRequestAuth_actionPerformed(e);
				      }

				  }
	);
	addTab(secondTab);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	requestsList.setParentInternalFrame(_e);
	detailMgmt.setParentInternalFrame(_e);
	changeSelectedTab();
    }

    public void changeSelectedTab() {
	if (getParentInternalFrame() != null) {
	    if (getSelectedTab() == 0) {
		getParentInternalFrame().setInfo("Complete los datos del pedido, agregue materiales al detalle y presione el botón \"Grabar e imprimir\"");
	    } else {
		getParentInternalFrame().setInfo("Listado de Pedidos de Materiales realizados");
	    }
	}
    }

    public boolean saveData() {
	return detailMgmt.saveData();
    }

    private void btnRequestAuth_actionPerformed(ActionEvent e) {
	ResourcesRequestsAuthMain resourcesRequestsAuthMain = new ResourcesRequestsAuthMain();
	ExtendedInternalFrame resourcesRequestsAuthMainContainer = new ExtendedInternalFrame("Autorización de Pedidos de Materiales");
	resourcesRequestsAuthMainContainer.setCentralPanel(resourcesRequestsAuthMain);
	resourcesRequestsAuthMainContainer.show();
    }

}
