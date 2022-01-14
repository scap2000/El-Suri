package org.digitall.common.resourcescontrol.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.resourcescontrol.classes.ResourceComponents;
import org.digitall.common.resourcescontrol.interfaces.ResourcesComposerList;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class ResourcesComposerMgmt extends BasicPrimitivePanel {

    private TFInput tfResourceName = new TFInput(DataTypes.STRING,"Resource",false);
    private TFInput tfQuantity = new TFInput(DataTypes.DOUBLE,"Quantity",false);
    private BasicPanel jPanel1 = new BasicPanel("Asignar");
     
    private CloseButton btnClose = new CloseButton();
    private AcceptButton btnAccept = new AcceptButton();
    private ResourcesComposerList parentList;
    private ResourceComponents resourceComponents;
    private TFInput tfValue = new TFInput(DataTypes.INTEGER,"Value",false);

    public ResourcesComposerMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(485, 80));
	tfResourceName.setBounds(new Rectangle(15, 25, 280, 35));
	tfQuantity.setBounds(new Rectangle(315, 25, 65, 35));
	jPanel1.setBounds(new Rectangle(5, 5, 475, 70));
	jPanel1.setLayout(null);
	btnClose.setBounds(new Rectangle(440, 90, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAccept.setBounds(new Rectangle(390, 90, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	tfValue.setBounds(new Rectangle(395, 25, 65, 35));
	this.add(btnAccept, null);
	this.add(btnClose, null);
	 
	this.add(jPanel1, null);
	jPanel1.add(tfValue, null);
	jPanel1.add(tfResourceName, null);
	jPanel1.add(tfQuantity, null);
	tfResourceName.setEnabled(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnAccept);
	getParentInternalFrame().getGeneralButtons().addButton(btnClose);
	getParentInternalFrame().setClosable(true);
    }
    
    private void loadData(){
	if (resourceComponents.getIdSkill() == -1){
	    tfResourceName.setValue(resourceComponents.getComponentResource().getName());    
	} else {
	    tfResourceName.setValue(resourceComponents.getSkillName());
	}
	
	tfQuantity.setValue(resourceComponents.getQuantity());
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setParentList(ResourcesComposerList parentList) {
	this.parentList = parentList;
    }

    public void setResourceComponents(ResourceComponents resourceComponents) {
	this.resourceComponents = resourceComponents;
	loadData();
    }

    public boolean saveData(){
	resourceComponents.setQuantity(tfQuantity.getDouble());
	resourceComponents.setValue(Integer.parseInt("0"+ tfValue.getString()));
	if (resourceComponents.saveData()>=0){
	    parentList.refresh();
	    getParentInternalFrame().close();
	    return true;
	} else {
	    return false;
	}
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	saveData();
    }

}
