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
