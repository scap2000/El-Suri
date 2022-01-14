package org.digitall.common.resourcesrequests.interfaces.resourcesmovements;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.digitall.common.resourcescontrol.classes.MovementsTypes;
import org.digitall.common.resourcescontrol.interfaces.ResourcesList;
import org.digitall.common.resourcesrequests.classes.ResourceMovements;
import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesDeliverList;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ResourcesDeliverMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel();
    private TFInput tfDelivery = new TFInput(DataTypes.STRING, "Delivery", false);
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", true);
    private TFInput tfDispatcher = new TFInput(DataTypes.STRING, "Dispatcher", false);
    private TFInput tfReceiver = new TFInput(DataTypes.STRING, "Receiver", false);
    private TFInput tfRecipient = new TFInput(DataTypes.STRING,"Recipient",false);
    private TFInput tfObservations = new TFInput(DataTypes.STRING, "Observations", false);
    private TFInput tfMovementType = new TFInput(DataTypes.STRING,"ResourcesMovementsTypes",false);
    private CBInput cbDepots = new CBInput(CachedCombo.ALLDEPOTS,"Depots",false);
    private CBInput cbProviders = new CBInput(0,"Provider",false);
    private FindButton btnFindEntity = new FindButton();
    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private AcceptButton btnResources = new AcceptButton();
    private String costCentre = "";
    //OBJECTS
    private ResourceMovements resourceMovements;
    private ResourcesDeliverList parentMain;

    public ResourcesDeliverMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    public ResourcesDeliverMgmt(ResourcesDeliverList _parentMain) {
	try {
	    parentMain = _parentMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    private void jbInit() throws Exception {
	this.setSize(new Dimension(745, 276));
	tfDate.setBounds(new Rectangle(365, 20, 110, 35));
	tfDate.setSize(new Dimension(110, 35));
	tfDispatcher.setBounds(new Rectangle(10, 110, 325, 35));
	tfDispatcher.setSize(new Dimension(325, 35));
	tfReceiver.setBounds(new Rectangle(365, 155, 325, 35));
	tfReceiver.setSize(new Dimension(325, 35));
	tfRecipient.setBounds(new Rectangle(365, 110, 325, 35));
	tfRecipient.setSize(new Dimension(325, 35));
	cbDepots.setBounds(new Rectangle(10, 65, 325, 35));
	tfDelivery.setBounds(new Rectangle(10, 155, 325, 35));
	tfDelivery.setSize(new Dimension(325, 35));
	cbProviders.setBounds(new Rectangle(365, 65, 325, 35));
	cbProviders.setSize(new Dimension(325, 35));
	btnFindEntity.setBounds(new Rectangle(695, 120, 30, 25));
	tfObservations.setBounds(new Rectangle(10, 195, 680, 35));
	tfMovementType.setBounds(new Rectangle(10, 20, 325, 35));
        dataPanel.add(tfMovementType, null);
        dataPanel.add(cbDepots, null);
        dataPanel.add(tfRecipient, null);
        dataPanel.add(tfDelivery, null);
        dataPanel.add(tfDate, null);
        dataPanel.add(tfDispatcher, null);
        dataPanel.add(tfReceiver, null);
        dataPanel.add(btnFindEntity, null);
        dataPanel.add(cbProviders, null);
        dataPanel.add(tfObservations, null);
        this.add(dataPanel, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	cbDepots.autoSize();
	cbProviders.autoSize();
	tfMovementType.setEnabled(false);
	btnFindEntity.setVisible(false);
	tfMovementType.setValue(MovementsTypes.DELIVEREDBYPURCHASEORDER);
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	btnResources.setText("Administracion\nde Recursos");
	btnResources.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnResources_actionPerformed(e);
				 }

			     }
	);
	cbProviders.getCombo().addItemListener(new ItemListener() {

		           public void itemStateChanged (ItemEvent evt) {
		               if (evt.getStateChange() == ItemEvent.SELECTED) {
				   loadForms();
		               }
		           }
		       }
	);	
	
	cbDepots.getCombo().addItemListener(new ItemListener() {

			   public void itemStateChanged(ItemEvent evt) {
			       if (evt.getStateChange() == ItemEvent.SELECTED) {
			            tfDispatcher.setValue(cbDepots.getSelectedItem().toString());
				    tfDelivery.setValue(cbDepots.getSelectedItem().toString());
			       }
			   }

		       }
	);
	btnAccept.setBounds(new Rectangle(620, 250, 25, 20));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnClose.setBounds(new Rectangle(680, 250, 25, 20));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	//dataPanel.setBounds(new Rectangle(5, 5, 705, 230));
	dataPanel.setLayout(null);
	loadComboProviders();
	loadForms();
	btnClose.setToolTipText("Cancelar");
        dataPanel.setBorder(BorderPanel.getBorderPanel("Datos para la entrega"));
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e){
	super.setParentInternalFrame(_e);
	//getParentInternalFrame().getGeneralButtons().addButton(btnResources);
    }

    private void btnResources_actionPerformed(ActionEvent e) {
	ResourcesList resourcesList = new ResourcesList();
	ExtendedInternalFrame resourcesListContainer = new ExtendedInternalFrame("Administracion de Recursos");
	resourcesListContainer.setCentralPanel(resourcesList);
	resourcesListContainer.show();
    }   

    private void loadForms(){
	tfDispatcher.setValue(cbDepots.getSelectedItem().toString());
	tfDelivery.setValue(cbDepots.getSelectedItem().toString());
    }

    private void loadComboProviders(){
	String param = "'Sermaq'";
	cbProviders.loadJCombo((LibSQL.exFunction("org.getAllProvidersByFilter", param)));
	cbProviders.setEnabled(false);
    }
    
    private boolean control() {
	boolean statusControl = true;
	if ( tfDate.getString().equals("") ){
	    Advisor.messageBox("El campo Fecha está vacio","Error");
	    statusControl = false;
	} else if( tfRecipient.getString().equals("") ){
	    Advisor.messageBox("El campo Destinatario está vacio","Error");
	    statusControl = false;
	} else if (tfReceiver.getString().equals("")){
	    Advisor.messageBox("El campo Recibido por está vacio","Error");
	    statusControl = false;
	} else {
	    loadResourcesMovement();
	}
	return statusControl;
    }

    private void loadResourcesMovement() {
	resourceMovements = new ResourceMovements();
	resourceMovements.setIdmovementtype(MovementsTypes.IDDELIVEREDBYPURCHASEORDER);
	resourceMovements.setMovementtype(MovementsTypes.DELIVEREDBYPURCHASEORDER);
	resourceMovements.setDate(Proced.setFormatDate(tfDate.getString(),false));
	resourceMovements.setIddepot(Integer.parseInt(cbDepots.getSelectedValue().toString()));
	resourceMovements.setDepot(cbDepots.getSelectedItem().toString());
	resourceMovements.setDelivery(tfDelivery.getString());
	resourceMovements.setDispatcher(tfDispatcher.getString());
	resourceMovements.setReceiver(tfReceiver.getString());
	resourceMovements.setRecipient(tfRecipient.getString());
	resourceMovements.setObservations(tfObservations.getString());
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	if (control())  {
	    parentMain.setResourceMovements(resourceMovements);
	    getParentInternalFrame().close();
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setCostCentre(String _costCentre) {
	costCentre = _costCentre;
	tfReceiver.setValue(costCentre);
	tfRecipient.setValue(costCentre);
    }

}
