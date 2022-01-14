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
 * ResourcesRequestsAuthMain.java
 *
 * */
package org.digitall.common.resourcesrequests.interfaces.resourcesrequests;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.cashflow.interfaces.CompanyTreePanel;
import org.digitall.common.cashflow.interfaces.budget.BudgetList;
import org.digitall.common.resourcescontrol.interfaces.ResourcesList;
import org.digitall.common.resourcesrequests.interfaces.purchaseorder.PurchaseOrderGenerateList;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;

public class ResourcesRequestsAuthMain extends BasicTabContainer {

    private ResourcesRequestsForAuthList requestsForAuthList = new ResourcesRequestsForAuthList(this);
    private ResourcesRequestsAuthList requestsAuthList = new ResourcesRequestsAuthList(this);
    private AcceptButton btnProviders = new AcceptButton();
    private AcceptButton btnPurchaseOrders = new AcceptButton();
    private AcceptButton btnBudget = new AcceptButton();
    private AcceptButton btnResources = new AcceptButton();

    public ResourcesRequestsAuthMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(790, 600));
	this.setPreferredSize(new Dimension(790, 600));
	btnBudget.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnBudget_actionPerformed(e);
				 }

			     }
	);
	btnBudget.setText("Administración\nde Partidas\nPresupuestarias");
	btnResources.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnResources_actionPerformed(e);
				    }

				}
	);
	btnResources.setText("Administración\nde Materiales");
	btnProviders.setText("Proveedores");
	btnProviders.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnPrividers_actionPerformed(e);
				    }

				}
	);
	btnPurchaseOrders.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     btnPurchaseOrders_actionPerformed(e);
					 }
				     }
	);
	btnPurchaseOrders.setText("Generar Orden\nde Compra");
	btnProviders.setToolTipText("Abre la ventana de Administración de Proveedores");
	btnResources.setToolTipText("Abre la ventana de Administración de Materiales");
	btnPurchaseOrders.setToolTipText("Abre la ventana de Generación de Órdenes de Compra");
	addTab(requestsForAuthList);
	addTab(requestsAuthList);

	setTitle("No se ha seleccionado un Pedido de Materiales");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	requestsForAuthList.setParentInternalFrame(_e);
	requestsAuthList.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnResources);
	getParentInternalFrame().getGeneralButtons().addButton(btnProviders);
	getParentInternalFrame().getGeneralButtons().addButton(btnPurchaseOrders);
	changeSelectedTab();
    }

    public ResourcesRequestsAuthList getRequestsAuthList() {
	return requestsAuthList;
    }

    public ResourcesRequestsForAuthList getRequestsForAuthList() {
	return requestsForAuthList;
    }

    private void btnPrividers_actionPerformed(ActionEvent e) {
	CompanyTreePanel providerList = new CompanyTreePanel();
	ExtendedInternalFrame providerListContainer = new ExtendedInternalFrame("Proveedores");
	providerListContainer.setCentralPanel(providerList);
	providerListContainer.show();
    }

    private void btnPurchaseOrders_actionPerformed(ActionEvent e) {
	PurchaseOrderGenerateList purchaseOrderGenerateList = new PurchaseOrderGenerateList();
	ExtendedInternalFrame purchaseOrdersListContainer = new ExtendedInternalFrame("Generar Orden de Compra");
	purchaseOrdersListContainer.setCentralPanel(purchaseOrderGenerateList);
	purchaseOrdersListContainer.show();
    }

    private void btnBudget_actionPerformed(ActionEvent e) {
	BudgetList budgetList = new BudgetList();
	ExtendedInternalFrame budgetListContainer = new ExtendedInternalFrame("Administración de Partidas Presupuestarias");
	budgetListContainer.setCentralPanel(budgetList);
	budgetListContainer.show();
    }

    private void btnResources_actionPerformed(ActionEvent e) {
	ResourcesList resourcesList = new ResourcesList();
	ExtendedInternalFrame resourcesListContainer = new ExtendedInternalFrame("Administración de Materiales");
	resourcesListContainer.setCentralPanel(resourcesList);
	resourcesListContainer.show();
    }

    public void changeSelectedTab() {
	if (getParentInternalFrame() != null) {
	    if (getSelectedTab() == 0) {
		getParentInternalFrame().setInfo("Haga doble click sobre algún Pedido de Materiales (solicitado) para autorizarlo");
	    } else {
		getParentInternalFrame().setInfo("Haga doble click sobre los ítems de la lista de Materiales solicitados para autorizar las cantidades");
	    }
	}
    }
    
    public int getCantItemsAutorizados(){
        return requestsAuthList.getCantItemsAutorizados();
    }

    @Override
    public boolean saveData() {
	return requestsAuthList.saveData();
    }
}
