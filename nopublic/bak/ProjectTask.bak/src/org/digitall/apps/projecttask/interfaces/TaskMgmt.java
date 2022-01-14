package org.digitall.apps.projecttask.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.digitall.apps.projecttask.classes.Task;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class TaskMgmt extends BasicContainerPanel {
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private int idTaskSelecter = -1;   
    private String date = Environment.currentDate;

    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private BasicPanel dataPanel = new BasicPanel();
    
    private TFInput tfName = new TFInput(DataTypes.STRING,"Name",true);
    private TFInput tfEstimationTime = new TFInput(DataTypes.INTEGER,"EstimationTime",false);
    private TFInput tfPriority = new TFInput(DataTypes.INTEGER,"Priority", false);
     
    
    private Task taskClass;
    private Advisor advisor;

    public TaskMgmt(BasicInternalFrame _parent, int _idTaskSelecter, Task _taskClass) {
	try {
	    taskClass = _taskClass; 
	    idTaskSelecter = _idTaskSelecter;
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public TaskMgmt(BasicDialog _parent, int _idTaskSelecter, Task _taskClass) {
	try {
	    idTaskSelecter = _idTaskSelecter;
	    taskClass = _taskClass;
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public TaskMgmt(JFrame _parent,int _idTaskSelecter, Task _taskClass) {
	try {
	    idTaskSelecter = _idTaskSelecter;
	    taskClass = _taskClass;
	    parent = _parent;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(300, 235));

	if ( idTaskSelecter != 0  ) {
	    dataPanel.setBorder(BorderPanel.getBorderPanel("Agregar Tarea",Color.BLUE,Color.BLACK));

	}
	else {
	    dataPanel.setBorder(BorderPanel.getBorderPanel("Agregar Proyecto",Color.BLUE,Color.BLACK));

	} 
	
	
	btnAccept.setBounds(new Rectangle(190, 185, 40, 25));
	btnAccept.setSize(new Dimension(40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnClose.setBounds(new Rectangle(245, 185, 40, 25));
	btnClose.setSize(new Dimension(40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	dataPanel.setBounds(new Rectangle(15, 5, 270, 165));
	dataPanel.setLayout(null);
	
	tfName.setBounds(new Rectangle(15, 25, 245, 35));

	tfEstimationTime.setBounds(new Rectangle(15, 70, 245, 35));

	tfPriority.setBounds(new Rectangle(15, 115, 245, 35));
	tfPriority.setSize(new Dimension(245, 35));
	//new Rectangle(0, 175, 295, 2));
	dataPanel.add(tfName, null);
	dataPanel.add(tfEstimationTime, null);
	dataPanel.add(tfPriority, null);
	 
	this.add(dataPanel, null);
	this.add(btnClose, null);
	this.add(btnAccept, null);
    }


    private void dispose() {
	    switch (parentType) {
		case DIALOG:
		    ((BasicDialog)parent).dispose();
		    break;
		case INTERNALFRAME:
		    ((BasicInternalFrame)parent).setVisible(false);
		    ((BasicInternalFrame)parent).hide();
		    break;
		case FRAME:
		    ((JFrame)parent).dispose();
		    break;
	    }
    }

  

    private void btnClose_actionPerformed(ActionEvent e) {
	
	dispose();

    }

    private void btnAccept_actionPerformed(ActionEvent e) {
    
	if (tfName.getString().equals("")){
	    advisor.messageBox("El nombre es obligatorio","Atención");
	}else {
	   taskClass.setName(tfName.getString());
	   taskClass.setEstimationTime(Integer.parseInt("0"+tfEstimationTime.getString()));
	   taskClass.setPriority(Integer.parseInt("0"+tfPriority.getString()));
	   taskClass.setIdParent(idTaskSelecter);
	   taskClass.setStartDate(date);
	   taskClass.saveData();
	    dispose();
	}
	   
	
    }

}
