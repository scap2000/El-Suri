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
 * GeneratePurchaseOrder.java
 *
 * */
package org.digitall.common.resourcesrequests.interfaces.purchaseorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.environment.Environment;

public class GeneratePurchaseOrder extends BasicPrimitivePanel {

    private BasicPanel findPanel = new BasicPanel();
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre", false);
    private CBInput cbPriority = new CBInput(CachedCombo.PRIORITYREQUEST, "Priority", false);
    private FindButton btnFind = new FindButton();
    private int[] sizeColumnListAuth = { 269, 75, 75, 103, 86, 85, 89, 197 };
    private Vector detailDataRow = new Vector();
    private GridPanel detailPanel = new GridPanel(50000, sizeColumnListAuth, "Materiales Aprobados", detailDataRow);
    private BasicPanel contenPanel = new BasicPanel();
    private AcceptButton btnAccept = new AcceptButton();

    public GeneratePurchaseOrder() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(780, 500));
	setTitle("Generar nueva Orden de Compra");
	findPanel.add(btnFind, null);
	findPanel.add(cbCostsCentre, null);
	findPanel.setLayout(null);
	
	findPanel.setBounds(new Rectangle(0, 0, 770, 100));
	
	cbCostsCentre.setBounds(new Rectangle(195, 15, 395, 35));
	cbPriority.setBounds(new Rectangle(465, 10, 85, 35));
	btnFind.setBounds(new Rectangle(595, 25, 30, 25));
	btnFind.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnFind_actionPerformed(e);
		}
	    });
	detailPanel.setSize(new Dimension(780, 200));
	detailPanel.setMinimumSize(new Dimension(173, 200));
	detailPanel.setPreferredSize(new Dimension(400, 200));
	contenPanel.setBounds(new Rectangle(85, 45, 770, 270));
	
	contenPanel.setLayout(new BorderLayout());
	contenPanel.add(findPanel,BorderLayout.NORTH);
	contenPanel.add(detailPanel,BorderLayout.CENTER);
	
	this.setContent(contenPanel);
	cbPriority.addGeneralItem();
	cbCostsCentre.addGeneralItem();
	btnAccept.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }
			     }
	);
	addButton(btnAccept);
	btnAccept.setEnabled(false);
	btnAccept.setToolTipText("Generar la Orden de Compra para los Materiales seleccionados");
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar Pedido de Materiales"));
	findPanel.setPreferredSize(new Dimension(780, 60));
	setDetailHeaderList();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede generar una Orden de Compra con sólo doble click sobre un Pedido de Materiales Aprobado");
    }
    
    public void refresh() {
	btnAccept.setEnabled(true);
	btnAccept.setToolTipText("Generar la Orden de Compra para el Pedido de Materiales seleccionado");
	detailPanel.refresh("-1," + cbCostsCentre.getSelectedValue());
    }

    private void tryToGeneratePurchaseOrder() {
	String _selectedIDs = detailPanel.getSelectedsIDSeparatedByComma();
	if (_selectedIDs.length() > 0) {
	    ExtendedInternalFrame purchaseOrdersMgmtContainer = new ExtendedInternalFrame("Nueva Orden de Compra");
	    PurchaseOrderMgmt purchaseOrderMgmt = new PurchaseOrderMgmt();
	    purchaseOrderMgmt.setParent(this);
	    purchaseOrderMgmt.setSelectedIDs(_selectedIDs);
	    purchaseOrderMgmt.setIDCostsCentre(Integer.parseInt(cbCostsCentre.getSelectedValue().toString()));
	    purchaseOrdersMgmtContainer.setCentralPanel(purchaseOrderMgmt);
	    purchaseOrdersMgmtContainer.show();
	} else {
	    Advisor.messageBox("Debe seleccionar uno o varios Materiales Aprobados", "Error");
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void setDetailHeaderList() {
	Vector detailHeaderList = new Vector();
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement(Environment.lang.getProperty("Material"));
        detailHeaderList.addElement("Nº Pedido");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement(Environment.lang.getProperty("Brand"));
	detailHeaderList.addElement(Environment.lang.getProperty("Quantity"));
	detailHeaderList.addElement("*");
	detailHeaderList.addElement(Environment.lang.getProperty("Unit"));
	detailHeaderList.addElement(Environment.lang.getProperty("Price"));
	detailHeaderList.addElement(Environment.lang.getProperty("Cost"));
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");//Environment.lang.getProperty("Destination"));
	detailHeaderList.addElement("*");//Environment.lang.getProperty("QuantityTo"));
	detailHeaderList.addElement(Environment.lang.getProperty("Observations"));
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailPanel.setParams("resourcescontrol.getAllResourcesRequestDetailAuth", "-1,-1", detailHeaderList);
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	tryToGeneratePurchaseOrder();
    }

}
