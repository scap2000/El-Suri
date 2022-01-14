package org.digitall.apps.logistics.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.apps.logistics.classes.AssignedResources;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class AssignedResourcesMgmt extends BasicContainerPanel {

    private TFInput tfQuantity = new TFInput(DataTypes.INTEGER, "Quantity", false);
    private TFInput tfDate = new TFInput(DataTypes.DATE,"Date",false);
    private TFInput tfUnit = new TFInput(DataTypes.STRING,"Unit",false);
    private TFInput tFInput4 = new TFInput();
    private TFInput tFInput5 = new TFInput();
    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private BasicPanel panel = new BasicPanel();
    private LFrameContainer parentContainer;
    private AssignedResources assignedResources;
    private AssignedHumanResourcesList parentHumanResourcesList;
    private AssignedMaterialResourcesList parentMaterialResourcesList;
    private int assignType;
    public static final int HUMANRESOURCE = 1;
    public static final int MATERIALRESOURCE = 2;
    
    //DATOS QUE QUIZA SE UTILICEN:
    //Asignacion Periodica: los dias 1º, 7, 14 y 21 de c/mes
    //Asignacion Temporal: desde el dd/mm/yyyy hasta el dd/mm/yyyy
    //Asignacion indefinida: desde el dd/mm/yyyy hasta ??/??/????
    //
    //La persona va equipada?
    //La persona viaja en vehiculo propio o de la empresa?
    //Otros que puedan surgir

    public AssignedResourcesMgmt(int _assignType) {
	try {
	    assignType = _assignType;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(340, 180));
	tfQuantity.setBounds(new Rectangle(10, 30, 90, 35));
	tfDate.setBounds(new Rectangle(230, 30, 90, 35));
	tfDate.setSize(new Dimension(90, 35));
	tfUnit.setBounds(new Rectangle(120, 30, 90, 35));
	tFInput4.setBounds(new Rectangle(10, 75, 90, 35));
	tFInput5.setBounds(new Rectangle(230, 80, 90, 35));
	btnAccept.setBounds(new Rectangle(245, 145, 40, 25));
	btnAccept.setSize(new Dimension(40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnClose.setSize(new Dimension(40, 25));
	btnClose.setBounds(new Rectangle(295, 145, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	panel.setBounds(new Rectangle(5, 0, 330, 125));
	panel.setLayout(null);
	this.add(panel, null);
	this.add(btnClose, null);
	this.add(btnAccept, null);
	panel.add(tFInput5, null);
	panel.add(tFInput4, null);
	panel.add(tfUnit, null);
	panel.add(tfDate, null);
	panel.add(tfQuantity, null);
	
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate,true));
	tfUnit.setEnabled(false);
	
	switch (assignType)  {
	    case HUMANRESOURCE: {
		    panel.setBorder(BorderPanel.getBorderPanel("Asignar Recurso Humano"));
		}
		break;
	    case MATERIALRESOURCE: {
		    panel.setBorder(BorderPanel.getBorderPanel("Asignar Recurso Material"));
		}
		break;
	    default: {
		    
		}
		break;
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	parentContainer.setVisible(false);
    }

    public void setParentContainer(LFrameContainer parentContainer) {
	this.parentContainer = parentContainer;
    }

    public void setAssignedResources(AssignedResources assignedResources) {
	this.assignedResources = assignedResources;	
	tfUnit.setValue(assignedResources.getResource().getUnit().getName());
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	assignedResources.setDate(Proced.setFormatDate(tfDate.getString(),false));
	assignedResources.setQuantity(Double.parseDouble("0"+ tfQuantity.getString()));
	if (assignedResources.saveData() >= 0){
	    switch (assignType)  {
		case HUMANRESOURCE: {
			parentHumanResourcesList.loadList();		
		    }
		    break;
	        case MATERIALRESOURCE: {
	                parentMaterialResourcesList.loadList();            
	            }
	            break;
		default: {
			
		    }
		    break;
	    }
	    
	    parentContainer.setVisible(false);
	}
    }

    public void setParentHumanResourcesList(AssignedHumanResourcesList parentHumanResourcesList) {
	this.parentHumanResourcesList = parentHumanResourcesList;
    }

    public void setParentMaterialResourcesList(AssignedMaterialResourcesList parentMaterialResourcesList) {
	this.parentMaterialResourcesList = parentMaterialResourcesList;
    }

}
