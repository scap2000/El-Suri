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
 * NewResourcesList.java
 *
 * */
package org.digitall.common.resourcesrequests.interfaces.resourcesrequests;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.common.resourcescontrol.interfaces.ResourcesList;
import org.digitall.common.resourcesrequests.classes.ResourcesRequest;
import org.digitall.common.resourcesrequests.interfaces.resourcesrequests.ResourcesRequestsAuthMain;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.grid.GridPanel;

public class NewResourcesList extends BasicPrimitivePanel {

    private int[] sizeColumnList = {289, 75, 161, 223};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Listado de Pedidos de Recursos Solicitados", dataRow);
    private Vector headerList = new Vector();
    private ResourcesRequestsAuthMain parentAuthMain;
    private ResourcesRequest resourcesRequest;
    private Resource selectedResource;
    private ResourcesList resourcesList;

    public NewResourcesList(ResourcesRequestsAuthMain _parentAuthMain) {
	try {
	    parentAuthMain = _parentAuthMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(780, 555));
	setTitle("Nuevos Recursos");
	listPanel.setBounds(new Rectangle(5, 0, 770, 545));
	this.add(listPanel, null);
	
	setHeaderList();
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Nombre");
	headerList.addElement("*");
	headerList.addElement("Unidad");
	headerList.addElement("*");
	headerList.addElement("Tipo Recurso");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Descripcion");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {
		 public void mouseClicked(MouseEvent e) {
		     if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			loadObjectSelected();
		     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			loadResourcesList();
		     }
		 }
    
	     }
	);
	
	listPanel.setParams("resourcescontrol.getAllNewResources", "-1", headerList);
    }
    
    public void refresh() {
	String params = ""+ resourcesRequest.getIdResourcesRequest();
	System.out.println("SELECT resourcescontrol.getAllNewResources(" + params +")");
	listPanel.refresh(params);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	    selectedResource = new Resource();
	    selectedResource.setIdResource(Integer.parseInt(""+ dataRow.elementAt(0)));
	    selectedResource.setName(""+ dataRow.elementAt(1));
	    selectedResource.setSkillToProvider(new Skills(Integer.parseInt(""+ dataRow.elementAt(4))));

	    ExpenditureAccount eType = new ExpenditureAccount();
	    eType.setIDExpenditureAccount(Integer.parseInt(""+ dataRow.elementAt(6)));
	    eType.setName(""+ dataRow.elementAt(7)); 
	    selectedResource.setExpenditureAccount(eType);

	    selectedResource.setIdUnit(Integer.parseInt(""+ dataRow.elementAt(2)));
	    selectedResource.setLifetime(Integer.parseInt(""+ dataRow.elementAt(10)));
	    selectedResource.setIdLifeTimeType(Integer.parseInt(""+ dataRow.elementAt(11)));
	    selectedResource.setDescription(""+ dataRow.elementAt(9));
	}
    }
    
    private void loadResourcesList(){
	resourcesList = new ResourcesList();
	resourcesList.setFindResource(selectedResource);
	resourcesList.setNewResourcesList(this);
	
	ExtendedInternalFrame resourcesListContainer = new ExtendedInternalFrame("Listado de Recursos");
	resourcesListContainer.setCentralPanel(resourcesList);
	resourcesListContainer.show();
    }
    
    public void setResourcesRequest(ResourcesRequest resourcesRequest) {
	this.resourcesRequest = resourcesRequest;
	refresh();
    }

}
