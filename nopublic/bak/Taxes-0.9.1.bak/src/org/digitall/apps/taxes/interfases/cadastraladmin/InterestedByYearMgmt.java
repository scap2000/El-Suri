package org.digitall.apps.taxes.interfases.cadastraladmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.digitall.apps.taxes.classes.Car;
import org.digitall.apps.taxes.classes.InterestedByYear;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;

public class InterestedByYearMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel("");
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnAccept = new SaveDataButton();
    private TFInput tfAnnualFee = new TFInput(DataTypes.DOUBLE,"YearTaxes",false);
    private CBInput cbYears = new CBInput(0,"FileYear",false);
    private CBInput cbImpuesto = new CBInput(0,"TaxesType",false);

    private InterestedByYearList parentList;
    private InterestedByYear interestedByYear;
    
    int error = 0;
    private static final int ok = 0;
    private static final int tasaAnual = 1;

    public InterestedByYearMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout( null );
	this.setSize(new Dimension(298, 147));
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
	cbYears.setBounds(new Rectangle(19, 60, 95, 35));
	dataPanel.setBounds(new Rectangle(5, 0, 555, 220));
	dataPanel.setLayout(null);
	cbImpuesto.setBounds(new Rectangle(19, 10, 260, 35));
	dataPanel.add(cbImpuesto, null);
	dataPanel.add(cbYears, null);
	dataPanel.add(tfAnnualFee, null);
	this.add(dataPanel, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	cbYears.autoSize();
	cbImpuesto.autoSize();
	
	tfAnnualFee.setBounds(new Rectangle(189, 60, 90, 35));
	cbYears.addItemListener(new ItemListener() {
	      public void itemStateChanged(ItemEvent evt) {
		  if (evt.getStateChange() == ItemEvent.SELECTED) {
		      //calcPayment();;
		  }
	      }
	});
	cbImpuesto.addItemListener(new ItemListener() {
	      public void itemStateChanged(ItemEvent evt) {
		  if (evt.getStateChange() == ItemEvent.SELECTED) {
		      //calcPayment();;
		  }
	      }
	});
	
	loadComboModels();
	//calcPayment();
    }
    
    private void loadComboModels(){
	int actualYear = Integer.parseInt("0" + Environment.currentYear);
	int cont = 0;
	for (int i = 1974 ; i <= (actualYear + 1) ; i++)  {
	    cont++;
	    cbYears.getCombo().addItem(i,cont);
	}
	cbYears.setSelectedID(cont - 1);
	cbImpuesto.getCombo().addItem("Tasa General de Servicios",1);
	cbImpuesto.getCombo().addItem("Impuesto Inmobiliario",2);
	cbImpuesto.getCombo().addItem("Impuesto Automotor",3);
	cbImpuesto.setSelectedID(1);
    }

    private void loadData(){
	 if (interestedByYear.getAnio() != -1) {
	    cbYears.setSelectedValue(interestedByYear.getAnio());
	    cbImpuesto.setSelectedID(interestedByYear.getIdtipoimpuesto());
	    tfAnnualFee.setValue("" + (interestedByYear.getTasaanual() * 100));
	    cbYears.setEnabled(false);
	    cbImpuesto.setEnabled(false);
	 } else {
	     cbYears.setEnabled(true);
	     cbImpuesto.setEnabled(true);
	 }
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public boolean saveData(){
	if (control() == ok)  {
	    if (interestedByYear == null){
	        interestedByYear = new InterestedByYear();
	    }
	    
	    interestedByYear.setAnio(Integer.parseInt("" + cbYears.getSelectedItem()));
	    interestedByYear.setIdtipoimpuesto(Integer.parseInt("" + cbImpuesto.getSelectedValue()));
	    interestedByYear.setTasaanual(tfAnnualFee.getDouble()/100.0);
	    
	    if (interestedByYear.saveData() >= 0) {
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
	    parentList.refresh();
	    getParentInternalFrame().close();
	}
    }
    
    public void setParentList(InterestedByYearList _parentList) {
	parentList = _parentList;
    }

    private int control() {
	error = ok;
	if (tfAnnualFee.getString().equals(""))  {
	    error = tasaAnual;
	}
	return error;
    }

    private void showMessage() {
	switch (error)  {
	    case tasaAnual: 
		    Advisor.messageBox("Debe ingresar la Tasa Anual", "Mensaje");
		break;
	}
    }

    public void setInterestedByYearMgmt(InterestedByYear interestedByYear) {
	this.interestedByYear = interestedByYear;
	loadData();
    }

}
