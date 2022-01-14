package org.digitall.common.resourcescontrol.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CancelDataButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class UnitsList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 32, 252 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Unidades", dataRow);
    private BasicPanel panelData = new BasicPanel();
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", false);
    private AssignButton btnAssign = new AssignButton(true);
    private AddButton btnNew = new AddButton();
    private Units unit;
    private boolean addAction = true;
    private FindButton btnFind = new FindButton();

    public UnitsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(373, 262));
	listPanel.setBounds(new Rectangle(5, 80, 305, 310));
	panelData.setBounds(new Rectangle(5, 0, 305, 70));
	panelData.setLayout(null);
	tfName.setBounds(new Rectangle(45, 20, 130, 35));
	btnAssign.setBounds(new Rectangle(190, 30, 40, 25));
	btnAssign.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAssign_actionPerformed(e);
			      }

			  }
	);
	btnNew.setBounds(new Rectangle(240, 30, 40, 25));
	btnNew.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnNew_actionPerformed(e);
				 }

			     }
	);
	btnFind.setBounds(new Rectangle(285, 30, 40, 25));
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
	panelData.setMinimumSize(new Dimension(1, 100));
	panelData.setPreferredSize(new Dimension(1, 70));
	setHeaderList();
	btnAssign.setToolTipText("Agregar/Modificar unidad");
	btnNew.setToolTipText("Limpiar campos");
        panelData.setBorder(BorderPanel.getBorderPanel("Agregar nueva Unidad"));
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

				       }
	);
	listPanel.setParams("tabs.getAllUnits", "''", headerList);
    }

    public void refresh() {
	String params = "'" + tfName.getString() + "'";
	listPanel.refresh(params);
    }

    private void loadUnit() {
	if (unit == null) {
	    unit = new Units(Integer.parseInt("" + dataRow.elementAt(0)), dataRow.elementAt(1).toString());
	} else {
	    unit.setIdUnit(Integer.parseInt("" + dataRow.elementAt(0)));
	    unit.setName(dataRow.elementAt(1).toString());
	}
    }

    private void loadData() {
	tfName.setValue(unit.getName());
	addAction = false;
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
	    unit = new Units();
	}
	unit.setName(tfName.getString());
	unit.saveData();
	refresh();
	clearData();
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede Agregar/Modificar/Buscar unidades");
    }

}
