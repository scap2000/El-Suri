package org.digitall.common.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.resourcescontrol.classes.Neighborhood;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class NeighborhoodList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 364 };
    private Vector headerList = new Vector(); 
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Barrios", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private AssignButton btnAssign = new AssignButton(true);
    private AddButton btnNew = new AddButton();
    private boolean addAction = true;
    private FindButton btnFind = new FindButton();
    private Neighborhood neighborhood;

    public NeighborhoodList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(432, 308));
	listPanel.setBounds(new Rectangle(5, 80, 305, 310));
	panelData.setBounds(new Rectangle(5, 0, 305, 70));
	panelData.setLayout(null);
	tfName.setBounds(new Rectangle(10, 25, 265, 35));
	btnAssign.setBounds(new Rectangle(310, 35, 40, 25));
	btnAssign.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAssign_actionPerformed(e);
			      }

			  }
	);
	btnNew.setBounds(new Rectangle(345, 35, 40, 25));
	btnNew.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnNew_actionPerformed(e);
				 }

			     }
	);
	btnFind.setBounds(new Rectangle(380, 35, 40, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
        panelData.add(btnFind, null);
        panelData.add(btnNew, null);
        panelData.add(tfName, null);
        panelData.add(btnAssign, null);
        this.add(panelData, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	panelData.setBorder(BorderPanel.getBorderPanel("Agregar nuevo Barrio", Color.blue, Color.black));
	panelData.setMinimumSize(new Dimension(1, 100));
	panelData.setPreferredSize(new Dimension(1, 80));
	setHeaderList();
	btnAssign.setToolTipText("Agregar/Modificar un Barrio");
	btnNew.setToolTipText("Limpiar campos");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Barrio");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadNeighborhood();
						   loadData();
					       }
					   }

				       }
	);
	listPanel.setParams("tabs.getAllNeighborhoods", "''", headerList);
    }

    public void refresh() {
	String params = "'" + tfName.getString() + "'";
	listPanel.refresh(params);
    }

    private void clearData() {
	tfName.setValue("");
	addAction = true;
    }

    private void btnNew_actionPerformed(ActionEvent e) {
	clearData();
    }

    private void btnAssign_actionPerformed(ActionEvent e) {
	if (addAction) {
            neighborhood.setIdneighborhood(-1);
	}
	neighborhood.setName(tfName.getString());
	if (neighborhood.saveData() > 0) {
	    refresh();        
	    clearData();
	} else {
            Advisor.messageBox("Ocurrió un error al grabar los datos","Error");
        }
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede Agregar/Modificar/Buscar Barrios");
    }

    
    public void setNeighborhoodObject(Neighborhood _neighborhood){
        neighborhood = _neighborhood;
    }

    private void loadNeighborhood(){
        neighborhood.setIdneighborhood(Integer.parseInt(dataRow.elementAt(0).toString()));
        neighborhood.setName(dataRow.elementAt(1).toString());
        neighborhood.setIdLocation(Integer.parseInt(dataRow.elementAt(2).toString()));
        addAction = false;
    }
    
    private void loadData(){
        tfName.setValue(neighborhood.getName());
    }
}
