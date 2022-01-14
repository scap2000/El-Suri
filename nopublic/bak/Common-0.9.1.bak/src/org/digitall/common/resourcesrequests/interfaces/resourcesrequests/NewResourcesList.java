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
