package org.digitall.common.resourcesrequests.interfaces.provisionorder;

import java.awt.BorderLayout;
import java.awt.Dimension;

import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;


public class ProvisionOrderMain extends BasicTabContainer {

    private ProvisionOrderMgmt provisionOrderMgmt = new ProvisionOrderMgmt();
    private ProvisionOrderList provisionOrderList = new ProvisionOrderList();
    private BasicPrimitivePanel firstTab = new BasicPrimitivePanel();
    private BasicPrimitivePanel secondTab = new BasicPrimitivePanel();
    private BasicContainerPanel firstPanel = new BasicContainerPanel();
    private BasicContainerPanel secondPanel = new BasicContainerPanel();

    public ProvisionOrderMain() {
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
	firstPanel.add(provisionOrderMgmt, BorderLayout.CENTER);
	firstTab.setTitle("Nueva Orden de Provisión");
	firstTab.setContent(firstPanel);
	addTab(firstTab);
	secondPanel.setLayout(new BorderLayout());
	secondPanel.add(provisionOrderList, BorderLayout.CENTER);
	secondTab.setTitle("Órdenes de Provisión realizadas");
	secondTab.setContent(secondPanel);
	addTab(secondTab);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	provisionOrderList.setParentInternalFrame(_e);
	provisionOrderMgmt.setParentInternalFrame(_e);
	changeSelectedTab();
    }

    public void changeSelectedTab() {
	if (getParentInternalFrame() != null) {
	    if (getSelectedTab() == 0) {
	        getParentInternalFrame().setInfo("Complete los datos de la orden, agregue materiales al detalle y presione el botón \"Grabar e imprimir\"");
	    } else {
		getParentInternalFrame().setInfo("Listado de Órdenes de Provisión realizadas");
	    }
	}
    }

    public boolean saveData() {
	return provisionOrderMgmt.saveData();
    }

}

