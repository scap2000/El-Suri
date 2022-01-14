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
