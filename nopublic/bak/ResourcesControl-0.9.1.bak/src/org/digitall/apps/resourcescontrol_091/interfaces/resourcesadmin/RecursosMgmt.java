package org.digitall.apps.resourcescontrol_091.interfaces.resourcesadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.apps.resourcescontrol_091.classes.Resources;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.SaveDataButton;

public class RecursosMgmt extends BasicPrimitivePanel{
    private BasicPanel bpCentro = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();   
    private BasicTabbedPane jTabbedPane1 = new BasicTabbedPane();
    private BorderLayout borderLayout1 = new BorderLayout();
    private RecursosPanel recursosPanel = new RecursosPanel();
    private RecursosDistinguiblesPanel recursosDistinguiblesPanel = new RecursosDistinguiblesPanel();
    private Resources resources = null;
    private RecursosList parentRecursosList = null;

    public RecursosMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public RecursosMgmt(RecursosList recursosList) {
	try {
	    parentRecursosList = recursosList;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(580, 600));
	this.setPreferredSize(new Dimension(580, 600));
	bpCentro.setLayout(borderLayout1);
	this.add(bpCentro, BorderLayout.CENTER);
	jTabbedPane1.addTab("Tipos recursos", recursosPanel);
	jTabbedPane1.addTab("Recursos distinguibles", recursosDistinguiblesPanel);
	//jTabbedPane1.addTab("Historial asignación de recursos", panelDatosFamily);
	bpCentro.add(jTabbedPane1, BorderLayout.CENTER);
	btnClose.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent e) {
				       btnClose_actionPerformed(e);
				  }

			       }
	);
	btnSave.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnSave_actionPerformed(e);
				   }

			       }
	);
	this.addButton(btnClose);
	this.addButton(btnSave);	
    }
    
    public void setResource(Resources _resources) {
	resources = _resources;
	loadData();
    }
    
    private void loadData() {
	recursosPanel.setResource(resources);;
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {
	if (recursosPanel.saveData()) {	  
	    parentRecursosList.refresh();
	    getParentInternalFrame().close();
	}else{
	    Advisor.messageBox("Ocurrio un error al grabar los datos", "Aviso");
	}
    }
}
