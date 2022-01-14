package org.digitall.apps.resourcescontrol_091.interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.apps.resourcescontrol_091.classes.Brands;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class BrandsList extends BasicPrimitivePanel {
    private int[] sizeColumnList = {41, 278};
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Marcas", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
     
    private CloseButton btnClose = new CloseButton();
    private AssignButton btnAdd = new AssignButton(true);
    private CancelDataButton btnCancel = new CancelDataButton();
    private BasicDialog parentContainer;
    private Brands brand;
    private boolean addAction = true;
    private FindButton btnFind = new FindButton();
    
    public BrandsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(350, 440));
	listPanel.setBounds(new Rectangle(5, 80, 340, 310));
	panelData.setBounds(new Rectangle(5, 0, 340, 70));
	panelData.setLayout(null);
	tfName.setBounds(new Rectangle(15, 25, 165, 35));
	//new Rectangle(0, 400, 315, 2));
	//setSize(new Dimension(350, 2));
	btnClose.setBounds(new Rectangle(305, 410, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAdd.setBounds(new Rectangle(190, 35, 40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	btnCancel.setBounds(new Rectangle(240, 35, 40, 25));
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	btnFind.setBounds(new Rectangle(290, 35, 40, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	panelData.add(btnFind, null);
	panelData.add(btnCancel, null);
	panelData.add(tfName, null);
	panelData.add(btnAdd, null);
	this.add(btnClose, null);
	 
	this.add(panelData, null);
	this.add(listPanel, null);
	panelData.setBorder(BorderPanel.getBorderPanel("Agregar nueva Marca", Color.blue, Color.black));
	setHeaderList();
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("ID");
	headerList.addElement("Nombre");
	headerList.addElement("*");
	
	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		    loadUnit();
		    loadData();
		}
	    }
	});
	listPanel.setParams("resourcescontrol.getAllBrands", "''", headerList);
    }

    public void refresh() {
	String params = "''";
	listPanel.refresh(params);
    }
    
    private void loadUnit(){
	if (brand == null){
	    brand = new Brands(Integer.parseInt(""+ dataRow.elementAt(0)), dataRow.elementAt(1).toString());
	} else {
	    brand.setIdBrand(Integer.parseInt(""+ dataRow.elementAt(0)));
	    brand.setName(dataRow.elementAt(1).toString());
	}
    }
    
    private void loadData(){
	tfName.setValue(brand.getName());
	addAction = false;
    }

    private void clearData() {
	tfName.setValue("");
	addAction = true;
    }
    
    private void btnCancel_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	if (addAction){
	    brand = new Brands();
	}
	brand.setName(tfName.getString());
	brand.saveData();
	
	refresh();
	clearData();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parentContainer.setVisible(false);
    }

    public void setParentContainer(BasicDialog parentContainer) {
	this.parentContainer = parentContainer;
	
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }
}
