package org.digitall.apps.resourcescontrol.interfaces.resourcesadmin;

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
