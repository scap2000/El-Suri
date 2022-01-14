package org.digitall.common.resourcesrequests.interfaces.purchaseorder;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.resourcesrequests.classes.ResourcesRequest;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.environment.Environment;

public class PurchaseOrderGenerateDetailList extends BasicPrimitivePanel {

    private int[] sizeColumnListAuth = {129, 75, 67, 55, 69, 75, 125, 80, 197};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnListAuth, "Recursos Autorizados", dataRow);
    private Vector headerList = new Vector();
    private ResourcesRequest resourcesRequest;
    
    public PurchaseOrderGenerateDetailList() {
	try {
		jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(780, 360));
	setTitle("Detalle");
	listPanel.setBounds(new Rectangle(5, 5, 770, 350));
	listPanel.setSize(new Dimension(770, 350));
	this.add(listPanel, null);
	
	setHeaderList();
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Resource"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Brand"));
	headerList.addElement(Environment.lang.getProperty("Quantity"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Unit"));
	headerList.addElement(Environment.lang.getProperty("Price"));
	headerList.addElement(Environment.lang.getProperty("Cost"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Destination"));
	headerList.addElement(Environment.lang.getProperty("QuantityTo"));
	headerList.addElement(Environment.lang.getProperty("Observations"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {

		    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

		    }
		}
	    }
	);
	
	listPanel.setParams("resourcescontrol.getAllResourcesRequestDetailAuth", "-1, 'true'", headerList);
    }

    public void refresh() {
	String params = ""+ resourcesRequest.getIdResourcesRequest() +", 'true'";
	listPanel.refresh(params);
    }

    public void setResourcesRequest(ResourcesRequest resourcesRequest) {
	this.resourcesRequest = resourcesRequest;
	refresh();
    }

}
