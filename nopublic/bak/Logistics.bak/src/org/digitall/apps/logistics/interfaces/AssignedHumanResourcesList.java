package org.digitall.apps.logistics.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.apps.logistics.classes.AssignedResources;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.interfaces.ResourcesList;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.grid.PanelGrid;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class AssignedHumanResourcesList extends BasicContainerPanel {

    private CBInput cbHumanResource = new CBInput(0, "HumanResource");
    private TFInput tfFindHumanResource = new TFInput(DataTypes.STRING, "FindResource",false);
    private AssignButton btnAssign = new AssignButton(true);
    private BasicPanel jpSearch = new BasicPanel();
    private int[] sizeColumnList = {580, 75, 89};
    private PanelGrid listPanel = new PanelGrid(30, sizeColumnList, "Recursos Humanos Asignados", false);
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private FindButton btnFindHumanResource = new FindButton();
    private CloseButton btnClose = new CloseButton();
    private LFrameContainer parentContainer;
    private AssignedResources assignedResources;
    private AssignedResourcesMgmt mgmt = new AssignedResourcesMgmt(AssignedResourcesMgmt.HUMANRESOURCE);
    private LFrameContainer containerMgmt = new LFrameContainer("Asignar Recurso");
    private ResourcesList resourcesList;
    private Resource selectedResource;
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre");
    private CostsCentre selectedCC;

    public AssignedHumanResourcesList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(775, 455));
	cbHumanResource.setBounds(new Rectangle(370, 25, 290, 35));
	tfFindHumanResource.setBounds(new Rectangle(250, 25, 115, 40));
	btnAssign.setBounds(new Rectangle(715, 35, 40, 25));
	btnAssign.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAssign_actionPerformed(e);
				 }

			     }
	);
	jpSearch.setBounds(new Rectangle(5, 0, 765, 70));
	jpSearch.setLayout(null);
	jpSearch.setBorder(BorderPanel.getBorderPanel("Asignar Recurso Humano"));
	listPanel.setBounds(new Rectangle(5, 85, 765, 320));
	btnFindHumanResource.setBounds(new Rectangle(670, 35, 40, 25));
	btnFindHumanResource.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFindHumanResource_actionPerformed(e);
				  }

			      }
	);
	btnClose.setBounds(new Rectangle(730, 425, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	jpSearch.add(btnFindHumanResource, null);
	jpSearch.add(tfFindHumanResource, null);
	jpSearch.add(cbHumanResource, null);
	jpSearch.add(btnAssign, null);
	jpSearch.add(cbCostsCentre, null);
	cbCostsCentre.setBounds(new Rectangle(15, 25, 210, 35));
	this.add(btnClose, null);
	this.add(jpSearch, null);
	this.add(listPanel, null);
	listPanel.autoSize();
	cbHumanResource.autoSize();
	cbCostsCentre.autoSize();
	tfFindHumanResource.setKeyListener(new KeyAdapter() {

					public void keyTyped(KeyEvent e) {
					    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
						loadComboHumanResource();
					    }
					}

				    }
	);
	tfFindHumanResource.setSize(new Dimension(115, 35));
	setHeaderList();
	
	Environment.mainDesktop.add(containerMgmt);
	mgmt.setParentContainer(containerMgmt);
	mgmt.setParentHumanResourcesList(this);
	
	ItemListener itemListenerCC = new ItemListener() {
	    public void itemStateChanged (ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    setCostsCentre();
		}
	    }
	};
	cbCostsCentre.setItemListener(itemListenerCC);	
    }
    
    private void setCostsCentre(){
	if (cbCostsCentre.getSelectedValue() != null){
	    selectedCC = new CostsCentre(Integer.parseInt(""+ cbCostsCentre.getSelectedValue()));
	    if (assignedResources == null){
	        assignedResources = new AssignedResources();
	    }
	    assignedResources.setIdAssigned(-1);
	    assignedResources.setCostsCentre(selectedCC);
	    loadList();    
	}
    }
    
    private void assignHumanResource(){
	if (cbHumanResource.getSelectedValue() != null){
	    selectedResource = new Resource(EntityTypes.RESOURCE, Integer.parseInt(""+ cbHumanResource.getSelectedValue()));
	    selectedResource.retrieveData();
	    if (assignedResources == null){
		assignedResources = new AssignedResources();
	    }
	    assignedResources.setIdAssigned(-1);
	    assignedResources.setResource(selectedResource);
	}       
    }
    
    private void loadComboHumanResource(){
	JCombo combo = new JCombo();
	String param = "'" + tfFindHumanResource.getString() + "'";
	combo.loadJCombo(LibSQL.exFunction("resourcescontrol.getAllHumanResources", param));
	ItemListener itemListener = new ItemListener() {
		    public void itemStateChanged (ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			   assignHumanResource();			   
			}
		    }
		};
	cbHumanResource.setCombo(combo);        
	cbHumanResource.setItemListener(itemListener);
	cbHumanResource.updateUI();         
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Recurso");
	headerList.addElement("Cantidad");
	headerList.addElement("Fecha");
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			    dataRow = listPanel.getDataRow();
			    loadObjectSelected();
			} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			}
		    }

		});
    }

    public void loadList() {
	if (parentContainer != null){
	    String params = ""+ assignedResources.getCostsCentre().getIdCostCentre();
	    listPanel.setTable(parentContainer, "logistics.getAllAssignedHumanResources", params, headerList);
	}
    }
    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    //setear el resto de los atributos
	}
    }
    
    private void btnAssign_actionPerformed(ActionEvent e) {
	assignHumanResource();
	mgmt.setAssignedResources(assignedResources);  
	containerMgmt.setPanel(mgmt);
	containerMgmt.setVisible(true);
    }

    private void btnFindHumanResource_actionPerformed(ActionEvent e) {
	resourcesList = new ResourcesList();
	resourcesList.show();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parentContainer.setVisible(false);
    }

    public void setParentContainer(LFrameContainer parentContainer) {
	this.parentContainer = parentContainer;
	setCostsCentre();
    }

}
