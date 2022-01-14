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
 * ResourcesAdminToReserveList.java
 *
 * */
package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ResourcesAdminToReserveList extends BasicPrimitivePanel{

    private ResourcesAdminMain parentMain;
    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel();
    private int[] sizeColumnList = {217, 140, 75, 113, 75, 113};
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Recursos", dataRow);
    private CBInput cbPurchaseOrder = new CBInput(0, "PurchaseOrders",false);
    private Resource resource;

    public ResourcesAdminToReserveList(ResourcesAdminMain _parentMain) {
	try {
	    parentMain = _parentMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(790, 390));
	this.setPreferredSize(new Dimension(790, 390));
	setTitle("Recursos Existentes en Reserva");
	content.setLayout(null);
	findPanel.setBounds(new Rectangle(5, 5, 780, 45));
	findPanel.setLayout(null);
	listPanel.setBounds(new Rectangle(5, 55, 780, 330));
	cbPurchaseOrder.setBounds(new Rectangle(10, 5, 110, 35));
	findPanel.add(cbPurchaseOrder, null);
	content.add(listPanel, null);
	content.add(findPanel, null);
	this.add(content, BorderLayout.CENTER);
	cbPurchaseOrder.autoSize();
	
	cbPurchaseOrder.getCombo().addItemListener(new ItemListener() {
	    public void itemStateChanged (ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		   refresh();
		}
	    }
	});
	
	setHeaderList();	
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Name"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Brand"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Unit"));
	headerList.addElement(Environment.lang.getProperty("ReceivedQty"));
	headerList.addElement(Environment.lang.getProperty("DeliveredQty"));
	headerList.addElement(Environment.lang.getProperty("TotalInReserveQty"));
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    
		}
	    }
	});
	
	listPanel.setParams("resourcescontrol.getAllResourcesInReserve", "-1,-1", headerList);
    }

    public void refresh() {
    	if (cbPurchaseOrder.getCombo().getItemCount() != 0){
	    String params = cbPurchaseOrder.getSelectedValue() +","+ resource.getIdResource();
	    listPanel.refresh(params);
	    listPanel.setTitle("Stock de Recursos en Reserva correspondiente al O.C. Nº"+ cbPurchaseOrder.getSelectedItem());
	} else {
	    listPanel.refresh("-1,-1");
	}
    }

    private void loadPurchaseOrderCombo(){
	cbPurchaseOrder.loadJCombo(LibSQL.exFunction("resourcescontrol.getAllPurchaseOrdersByResource",""+ resource.getIdResource()));
	
    }

    public void setResource(Resource resource) {
	this.resource = resource;	
	loadPurchaseOrderCombo();
	refresh();
    }

}
