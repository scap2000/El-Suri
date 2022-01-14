package org.digitall.common.resourcesrequests.interfaces.resourcesmovements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.MovementsTypes;
import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesReceiveMain;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ResourcesReceiveMgmt extends BasicPrimitivePanel {

    private TFInput tfDelivery = new TFInput(DataTypes.STRING, "Delivery", false);
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", true);
    private TFInput tfDispatcher = new TFInput(DataTypes.STRING, "Dispatcher", false);
    private TFInput tfReceiver = new TFInput(DataTypes.STRING, "Receiver", false);
    private TFInput tfRecipient = new TFInput(DataTypes.STRING, "Recipient", false);
    private TFInput tfSearchProvider = new TFInput(DataTypes.STRING, "SearchProvider", false);
    private TFInput tfMovementType = new TFInput(DataTypes.STRING, "ResourcesMovementsTypes", false);
    private CBInput cbDepots = new CBInput(CachedCombo.ALLDEPOTS, "Depots", false);
    private CBInput cbProviders = new CBInput(0, "Provider", false);
    private FindButton btnFindEntity = new FindButton();
    private ResourcesReceiveMain parentMain;
    private BasicPanel dataPanel = new BasicPanel();
    private JArea taObservations = new JArea();
    private JLabel lblObservations = new JLabel();

    public ResourcesReceiveMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public ResourcesReceiveMgmt(ResourcesReceiveMain _parentMain) {
	try {
	    parentMain = _parentMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(730, 515));
	tfDate.setBounds(new Rectangle(585, 55, 110, 35));
	tfDate.setSize(new Dimension(110, 35));
	tfDispatcher.setBounds(new Rectangle(15, 100, 325, 35));
	tfDispatcher.setSize(new Dimension(325, 35));
	tfReceiver.setBounds(new Rectangle(370, 150, 325, 35));
	tfReceiver.setSize(new Dimension(325, 35));
	tfRecipient.setBounds(new Rectangle(370, 100, 325, 35));
	tfRecipient.setSize(new Dimension(325, 35));
	cbDepots.setBounds(new Rectangle(370, 5, 325, 35));
	tfDelivery.setBounds(new Rectangle(15, 150, 325, 35));
	tfDelivery.setSize(new Dimension(325, 35));
	cbProviders.setBounds(new Rectangle(195, 55, 325, 35));
	cbProviders.setSize(new Dimension(325, 35));
	btnFindEntity.setBounds(new Rectangle(695, 120, 30, 35));
	tfSearchProvider.setBounds(new Rectangle(15, 55, 170, 35));
	tfMovementType.setBounds(new Rectangle(15, 5, 195, 35));
	dataPanel.add(lblObservations, null);
	dataPanel.add(taObservations, null);
	dataPanel.add(tfDispatcher, null);
	dataPanel.add(tfDate, null);
	dataPanel.add(tfRecipient, null);
	dataPanel.add(tfReceiver, null);
	dataPanel.add(tfDelivery, null);
	dataPanel.add(cbProviders, null);
	dataPanel.add(tfSearchProvider, null);
	dataPanel.add(cbDepots, null);
	dataPanel.add(tfMovementType, null);
	this.add(dataPanel, null);
	this.add(btnFindEntity, null);
	this.setLayout(null);
	cbDepots.autoSize();
	cbProviders.autoSize();
	tfMovementType.setEnabled(false);
	btnFindEntity.setVisible(false);
	dataPanel.setBounds(new Rectangle(0, 0, 730, 540));
	dataPanel.setLayout(null);
	dataPanel.setSize(new Dimension(730, 515));
	taObservations.setBounds(new Rectangle(15, 220, 685, 225));
	lblObservations.setText("Observaciones:");
	lblObservations.setBounds(new Rectangle(15, 200, 115, 20));
	lblObservations.setFont(new Font("Dialog", 0, 12));
	lblObservations.setForeground(Color.white);
	tfMovementType.setValue(MovementsTypes.ENTRYBYPURCHASEORDER);
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfSearchProvider.getTextField().addKeyListener(new KeyAdapter() {

						    public void keyTyped(KeyEvent e) {
							if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							    loadComboProviders();
							    if (!cbProviders.getSelectedValue().toString().equals("0")) {
								loadFroms();
							    }
							}
						    }

						}
	);
	cbProviders.getCombo().addItemListener(new ItemListener() {

					    public void itemStateChanged(ItemEvent evt) {
						if (evt.getStateChange() == ItemEvent.SELECTED) {
						    loadFroms();
						}
					    }

					}
	);
	cbDepots.getCombo().addItemListener(new ItemListener() {

					 public void itemStateChanged(ItemEvent evt) {
					     if (evt.getStateChange() == ItemEvent.SELECTED) {
						 tfRecipient.setValue(cbDepots.getSelectedItem().toString());
						 tfReceiver.setValue(cbDepots.getSelectedItem().toString());
					     }
					 }

				     }
	);
    }

    private void loadFroms() {
	if (cbProviders.getSelectedIndex() != -1) {
	    if (cbDepots.getSelectedIndex() != -1) {
		tfRecipient.setValue(cbDepots.getSelectedItem().toString());
		tfReceiver.setValue(cbDepots.getSelectedItem().toString());
	    }
	    tfDispatcher.setValue(cbProviders.getSelectedItem().toString());
	    tfDelivery.setValue(cbProviders.getSelectedItem().toString());
	    parentMain.getResourcesMovementsDetail().setProvider(Integer.parseInt(cbProviders.getSelectedValue().toString()), cbProviders.getSelectedItem().toString());
	    parentMain.getResourcesMovementsDetail().setData();
	    parentMain.getResourcesMovementsDetail().clearTfDespatchNoteNumber();
	}
    }

    private void loadComboProviders() {
	//String param = "'" + tfSearchProvider.getString() + "'";
	String param = "'" + tfSearchProvider.getString() + "',"+ parentMain.getResourcesMovementsDetail().getIdBudget();
	cbProviders.loadJCombo((LibSQL.exFunction("resourcescontrol.getAllProvidersWithPurchaseOrders", param)));
    }

    public int getIdMovementType() {
	return MovementsTypes.IDENTRYBYPURCHASEORDER;
    }

    public String getMovementType() {
	return MovementsTypes.ENTRYBYPURCHASEORDER;
    }

    public String getMovementDate() {
	return Proced.setFormatDate(tfDate.getString(), false);
    }

    public int getIdDepot() {
	return Integer.parseInt(cbDepots.getSelectedValue().toString());
    }

    public String getDepot() {
	return cbDepots.getSelectedItem().toString();
    }

    public String getDispatcher() {
	return tfDispatcher.getString();
    }

    public String getReceiver() {
	return tfReceiver.getString();
    }

    public String getDelivery() {
	return tfDelivery.getString();
    }

    public String getRecipient() {
	return tfRecipient.getString();
    }
    public String getTaObservations() {
	return taObservations.getText().trim();
    }

    public void setTaObservations(String _text) {
	taObservations.setText(_text);
    }
    
    public void setEnabledProviders(boolean _valor) {
        tfSearchProvider.setEnabled(_valor);
        cbProviders.setEnabled(_valor);
    }
    
}
