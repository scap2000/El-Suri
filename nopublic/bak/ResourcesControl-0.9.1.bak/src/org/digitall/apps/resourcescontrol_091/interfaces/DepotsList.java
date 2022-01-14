package org.digitall.apps.resourcescontrol_091.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class DepotsList extends BasicContainerPanel {

    private BasicPanel jpPurchaseOrders = new BasicPanel();
    private CBInput cbDepots = new CBInput(0, "Depots", false);
    private TFInput tfNumber = new TFInput(DataTypes.STRING, "Number", false);
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "StartDate", false);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE, "EndDate", false);
    private FindButton btnSearch = new FindButton();

    public DepotsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(609, 495));
	jpPurchaseOrders.setBounds(new Rectangle(20, 25, 575, 125));
	jpPurchaseOrders.setLayout(null);
	jpPurchaseOrders.setBorder(BorderPanel.getBorderPanel("Buscar Orden de Compra"));
	cbDepots.setBounds(new Rectangle(15, 25, 235, 35));
	tfNumber.setBounds(new Rectangle(20, 70, 100, 35));
	tfNumber.setSize(new Dimension(100, 35));
	tfStartDate.setBounds(new Rectangle(135, 70, 85, 35));
	tfStartDate.setSize(new Dimension(85, 35));
	tfEndDate.setBounds(new Rectangle(245, 70, 85, 35));
	tfEndDate.setSize(new Dimension(85, 35));
	btnSearch.setText("Buscar");
	btnSearch.setBounds(new Rectangle(435, 85, 95, 25));
	btnSearch.setSize(new Dimension(95, 25));
	cbDepots.autoSize();
	jpPurchaseOrders.add(btnSearch, null);
	jpPurchaseOrders.add(tfEndDate, null);
	jpPurchaseOrders.add(tfStartDate, null);
	jpPurchaseOrders.add(tfNumber, null);
	jpPurchaseOrders.add(cbDepots, null);
	this.add(jpPurchaseOrders, null);
    }

}
