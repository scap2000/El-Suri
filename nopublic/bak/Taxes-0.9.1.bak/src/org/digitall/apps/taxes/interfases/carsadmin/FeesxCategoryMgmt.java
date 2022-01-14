package org.digitall.apps.taxes.interfases.carsadmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.digitall.apps.taxes.classes.FeesxCategory;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class FeesxCategoryMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel();
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnAccept = new SaveDataButton();
    
    private TFInput tfYear = new TFInput(DataTypes.STRING,"FileYear",true);
    private TFInput tfAnnualFee = new TFInput(DataTypes.MONEY,"AnnualFee",true);
    private CBInput cbCategory = new CBInput(CachedCombo.CATEGORYTYPES,"Category",false);
    //private Car car;
    private FeesxCategory feesxCategory;
    private FeesxCategoryList parentList;
    private CarsMgmt_old parentMgmt;
    
    private boolean isList = true;
    
    int error = 0;
    private static final int ok = 0;
    private static final int year = 1;
    private static final int annualFee = 2;
    
    public FeesxCategoryMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout( null );
	this.setSize(new Dimension(324, 113));
	this.setPreferredSize(new Dimension(565, 300));
	btnClose.setBounds(new Rectangle(540, 535, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAccept.setBounds(new Rectangle(490, 535, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	tfYear.setBounds(new Rectangle(120, 25, 85, 35));
	cbCategory.setBounds(new Rectangle(15, 25, 75, 35));
	dataPanel.setBounds(new Rectangle(5, 0, 555, 220));
	dataPanel.setLayout(null);
	dataPanel.add(tfYear, null);
	dataPanel.add(cbCategory, null);
	dataPanel.add(tfAnnualFee, null);
	this.add(dataPanel, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	cbCategory.autoSize();
	tfAnnualFee.setBounds(new Rectangle(215, 25, 100, 35));
	cbCategory.addItemListener(new ItemListener() {
	      public void itemStateChanged(ItemEvent evt) {
		  if (evt.getStateChange() == ItemEvent.SELECTED) {
		      
		  }
	      }
	});
	dataPanel.setTitle("Agregar/Modificar Cuota");
    }
    
    
    private void loadData(){
	 if (feesxCategory.getAnio() != -1) {
	    tfYear.setValue("" + feesxCategory.getAnio());
	    cbCategory.setSelectedID(feesxCategory.getIdtipocategoria());
	    tfAnnualFee.setValue(feesxCategory.getAnual());
	    tfYear.setEnabled(false);
	    cbCategory.setEnabled(false);
	 } else {
	     tfYear.setEnabled(true);
	     cbCategory.setEnabled(true);
	 }
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	if (!isList)  {
	    parentMgmt.setBtn(true);
	}
	getParentInternalFrame().close();
    }

    public boolean saveData(){
	if (control() == ok)  {
	    feesxCategory.setAnio(Integer.parseInt(tfYear.getValue().toString()));
	    feesxCategory.setIdtipocategoria(Integer.parseInt(cbCategory.getSelectedItem().toString()));
	    feesxCategory.setAnual(tfAnnualFee.getAmount());
	    if (feesxCategory.saveData() >= 0) {
	        return true;
	    } else {
	        return false;
	    }	
	} else {
	    showMessage();
	    return false;
	}
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	if (saveData()){
	    if (isList)  {
		parentList.refresh();	    
	    } else {
	        parentMgmt.recalcCuota();
	    }
	    getParentInternalFrame().close();
	}
    }
    
    public void setParentList(FeesxCategoryList _parentList) {
	parentList = _parentList;
	isList = true;
    }
    
    public void setParentMgmt(CarsMgmt_old _parentMgmt) {
	parentMgmt = _parentMgmt;
	isList = false;
	
    }
    
    private int control() {
	error = ok;
	if (tfYear.getString().equals(""))  {
	    error = year;
	} else if (tfAnnualFee.getAmount() == 0) {
	    error = annualFee;
	}
	return error;
    }

    private void showMessage() {
	switch (error)  {
	    case 1: 
		    Advisor.messageBox("Debe ingresar el año", "Mensaje");
		break;
	    case 2: 
		    Advisor.messageBox("El Monto Anual debe ser mayor que cero", "Mensaje");
		break;
	}
    }

    public void setFeesxCategory(FeesxCategory _feesxCategory) {
	feesxCategory = _feesxCategory;
	loadData();
    }
    
    

}
