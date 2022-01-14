package org.digitall.apps.resourcescontrol.interfaces.resourcesadmin;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.resourcescontrol.classes.DistinguishableResource;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.grid.TableTransferHandler;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
/** CREO QUE YA NO SE USA -- MATIAS*/
public class DistinguishableResourcesList extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel searchPanel = new BasicPanel();

    private TFInput tfSearchResource = new TFInput(DataTypes.STRING, "FindResource",false);
    private CBInput cbDistinguishableResources = new CBInput(0,"DistinguishableResources",false);
    
    private int[] sizeColumnList = {205, 110, 90, 90, 105, 80 , 205};
    private Vector resourcesHeader = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel resourcesPanel = new GridPanel(50000, sizeColumnList, "Listado de Recursos Distinguibles", dataRow);
    
    private FindButton btnFind = new FindButton();
    private AddButton btnAdd = new AddButton();
    private ModifyButton btnEdit = new ModifyButton();
    
    private DistinguishableResource distinguishableResource;
    private DistinguishableResourcesMgmt distinguishableResourcesMgmt;
    
    public DistinguishableResourcesList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(null);
	this.setSize(new Dimension(710, 388));
	this.setPreferredSize(new Dimension(710, 515));
	tfSearchResource.setBounds(new Rectangle(50, 10, 185, 35));
	resourcesPanel.setBounds(new Rectangle(5, 75, 700, 270));
	contentPanel.setBounds(new Rectangle(5, 5, 700, 500));
	contentPanel.setLayout(null);
	contentPanel.setSize(new Dimension(700, 515));
	searchPanel.setBounds(new Rectangle(5, 10, 690, 50));
	searchPanel.setLayout(null);
        cbDistinguishableResources.setBounds(new Rectangle(290, 10, 335, 35));
	btnFind.setBounds(new Rectangle(660, 20, 30, 25));
	btnFind.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnFind_actionPerformed(e);
				  }

			      }
	);
	btnAdd.setBounds(new Rectangle(560, 525, 40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
        searchPanel.add(cbDistinguishableResources, null);
        searchPanel.add(tfSearchResource, null);
        searchPanel.add(btnFind, null);
        btnEdit.setBounds(new Rectangle(610, 525, 40, 25));
	btnEdit.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnEdit_actionPerformed(e);
			       }

			   }
	);
	contentPanel.add(resourcesPanel, null);
	contentPanel.add(searchPanel, null);
	this.add(contentPanel, null);
	this.addButton(btnEdit);
	this.addButton(btnAdd);
	resourcesPanel.getTable().setDragEnabled(true);
	resourcesPanel.getTable().setTransferHandler(new TableTransferHandler());
        tfSearchResource.getTextField().addKeyListener(new KeyAdapter() {

                                     public void keyReleased(KeyEvent e) {
                                         tfSearchResource_keyReleased(e);
                                     }

                                 }
        );
        cbDistinguishableResources.autoSize();
        btnEdit.setEnabled(false);
	setResourcesHeader();
        btnAdd.setEnabled(false);
        btnAdd.setVisible(false);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    
    public void refresh() {
	String params = ""+ cbDistinguishableResources.getSelectedValue();
	resourcesPanel.refresh(params);
        btnEdit.setEnabled(false);
    }

    private void setResourcesHeader() {
	resourcesHeader.removeAllElements();
        resourcesHeader.addElement("*"); //iddistinguishableresource
        resourcesHeader.addElement("*"); //idresource
        resourcesHeader.addElement(Environment.lang.getProperty("Resource"));
        resourcesHeader.addElement("*");
        resourcesHeader.addElement(Environment.lang.getProperty("Brand"));
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("Identificación");
        resourcesHeader.addElement("Nº Patrimonio");
        resourcesHeader.addElement("Estado");
        resourcesHeader.addElement("Fecha Adq.");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("Asignado a");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
        resourcesHeader.addElement("*");
	 
        resourcesPanel.getTable().addMouseListener(new MouseAdapter() {
        
                                              public void mouseClicked(MouseEvent e) {
                                                  if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                                                      loadObjectSelected();    
                                                      btnEdit.setEnabled(true);
                                                  } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                                                      loadMgmt(false);
                                                  }
                                              }
        
                                          }
        );
        String params = "-1";
        resourcesPanel.setParams("resourcescontrol.getAllDistinguishableResources", params, resourcesHeader);
    }

    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
            distinguishableResource = new DistinguishableResource();
            distinguishableResource.setIdDistinguishableResource(Integer.parseInt("" + dataRow.elementAt(0)));
            distinguishableResource.retrieveData();
	}
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	System.out.println("cant elementos combo "+cbDistinguishableResources.getCombo().getItemCount());
	if(cbDistinguishableResources.getCombo().getItemCount() > 0){
	    refresh();
	}
    }

    private void loadMgmt(boolean _addAction){
	if (_addAction){
            distinguishableResource = new DistinguishableResource();
	}
        distinguishableResourcesMgmt = new DistinguishableResourcesMgmt();
	ExtendedInternalFrame distinguishableResourcesMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
	distinguishableResourcesMgmtContainer.setCentralPanel(distinguishableResourcesMgmt);
        distinguishableResourcesMgmt.setDistinguishableResource(distinguishableResource);
        distinguishableResourcesMgmt.setParent(this);
	distinguishableResourcesMgmtContainer.show();
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	loadMgmt(true);
    }

    private void btnEdit_actionPerformed(ActionEvent e) {
	loadMgmt(false);
    }
    
    private void tfSearchResource_keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){         
            String params = "'"+ tfSearchResource.getValue().toString() +"'";
            cbDistinguishableResources.loadJCombo(LibSQL.exFunction("resourcescontrol.getDistinguishableResources", params));
        }
    }

    
}
