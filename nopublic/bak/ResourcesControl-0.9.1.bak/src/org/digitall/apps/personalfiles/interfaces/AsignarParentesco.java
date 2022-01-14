package org.digitall.apps.personalfiles.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.personalfiles.classes.Relatives;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.sql.LibSQL;

public class AsignarParentesco extends BasicPrimitivePanel{

    private BasicPanel jPanel1 = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private CBInput cbKinships = new CBInput();
    private SaveDataButton btnSave = new SaveDataButton();    
    private CloseButton btnClose = new CloseButton();
    private Relatives relative = null;
    private SelectRelatives selectRelatives = null;

    public AsignarParentesco() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout(borderLayout1);
	this.setSize(new Dimension(370, 113));	
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderPanel.getBorderPanel("Asignar parentesco"));
	cbKinships.setBounds(new Rectangle(15, 25, 175, 35));	
	btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }

			   }
	);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	jPanel1.add(cbKinships, null);
	this.add(jPanel1, BorderLayout.CENTER);
	addButton(btnClose);       
	addButton(btnSave);
	cbKinships.autoSize();
	loadCombos();
    }
    
    public void setRelatives(Relatives _relative){
	this.relative = _relative;
    }
    
    public void setParent(SelectRelatives _selectRelatives){
	this.selectRelatives = _selectRelatives;
    }
    
    private void loadCombos() {
	cbKinships.loadJCombo(LibSQL.exFunction("personalfiles.getAllKinships",""));   
	cbKinships.setSelectedID("1");
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	//Asiganamos el parentesco al familiar
	relative.setIdKinships(Integer.parseInt(cbKinships.getSelectedValue().toString()));	
	if (saveData()) {           
	    selectRelatives.refreshDatos();	    
	    getParentInternalFrame().close();
	}
    }
    
    public boolean saveData() {
	boolean resul = false;
	if (relative.saveData() > 0){
	    resul =  true;
	} else {
	    Advisor.messageBox("Ocurrio un error al grabar los datos!", "Error");
	}	
	return resul;
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();	
    }

}
