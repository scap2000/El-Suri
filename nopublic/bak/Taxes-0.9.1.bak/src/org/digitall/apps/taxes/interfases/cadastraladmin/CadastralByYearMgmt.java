package org.digitall.apps.taxes.interfases.cadastraladmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.apps.taxes.classes.CadastralByYear;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class CadastralByYearMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel("Agregar/Modificar un Catastro");
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnAccept = new SaveDataButton();
    
    private TFInput tfCadastral = new TFInput(DataTypes.INTEGER, "Cadastral", true);
    private TFInput tfFecha = new TFInput(DataTypes.DATE, "AssignedDate", true);
    private TFInput tfValorFiscal = new TFInput(DataTypes.MONEY, "TaxableValue", true);
    private TFInput tfMetrosFrente = new TFInput(DataTypes.DOUBLE, "MetrosFrente", true);

    private CadastralByYear cadastralByYear;
    private CadastralByYearList parentList;
    
    int error = 0;
    private static final int ok = 0;
    private static final int catastro = 1;
    private static final int fechaAsig = 2;
    private static final int valorFiscal = 3;
    private static final int metrosFrente = 4;
    

    public CadastralByYearMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	//this.setLayout( null );
	this.setSize(new Dimension(374, 179));
	this.setPreferredSize(new Dimension(565, 510));
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
	dataPanel.setLayout(null);
	tfValorFiscal.setBounds(new Rectangle(50, 80, 110, 35));
	tfFecha.setBounds(new Rectangle(205, 30, 125, 35));
	tfMetrosFrente.setBounds(new Rectangle(205, 80, 125, 35));
	tfCadastral.setBounds(new Rectangle(50, 25, 110, 35));
	dataPanel.add(tfValorFiscal, null);
	dataPanel.add(tfMetrosFrente, null);
	dataPanel.add(tfFecha, null);
	dataPanel.add(tfCadastral, null);
	this.add(dataPanel, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnAccept);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void loadData() {
	if (cadastralByYear.getIdCatastroxanio() != -1) {
	    tfCadastral.setEnabled(false);
	    tfFecha.setEnabled(false);
	    tfCadastral.setValue("" + cadastralByYear.getCatastro());
	    if (!cadastralByYear.getFecha().equals("null")) {
		tfFecha.setValue(Proced.setFormatDate(cadastralByYear.getFecha(),true));
	    }
	    tfValorFiscal.setValue("" + cadastralByYear.getVf());
	    tfMetrosFrente.setValue("" + cadastralByYear.getMf());
	    
	} else {
	    tfCadastral.setEnabled(true);
	    tfFecha.setEnabled(true);
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public boolean saveData() {
	if (control() == ok) {
	    if (cadastralByYear == null) {
		cadastralByYear = new CadastralByYear();
	    }
	    cadastralByYear.setCatastro(Integer.parseInt("0" + tfCadastral.getString()));
	    if (!tfFecha.getString().equals("")) {
		cadastralByYear.setFecha(Proced.setFormatDate(tfFecha.getString(), false));
	    } else {
		cadastralByYear.setFecha("null");
	    }
	    cadastralByYear.setVf(tfValorFiscal.getDouble());
	    cadastralByYear.setMf(tfMetrosFrente.getDouble());
	    
	    if (cadastralByYear.saveData() >= 0) {
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
	if (saveData()) {
	    parentList.refresh();
	    getParentInternalFrame().close();
	}
    }

    public void setParentList(CadastralByYearList _parentList) {
	parentList = _parentList;
    }

    private int control() {
	error = ok;
	if (tfCadastral.getString().equals("")) {
	    error = catastro;
	} else if (tfFecha.getString().equals("")) {
	    error = fechaAsig;
	} else if (tfValorFiscal.getString().equals("")) {
	    error = valorFiscal;
	} else if (tfMetrosFrente.getString().equals("")) {
	    error = metrosFrente;
	}
	return error;
    }

    private void showMessage() {
	switch (error) {
	    case 1 :
		Advisor.messageBox("Debe ingresar el número de Catastro", "Mensaje");
		break;
	    case 2 :
	    Advisor.messageBox("Debe ingresar la Fecha", "Mensaje");
		break;
	    case 3 :
		Advisor.messageBox("Debe ingresar el Valor Fiscal del Catastro", "Mensaje");
		break;
	    case 4 :
	        Advisor.messageBox("Debe ingresar los Metros de Frente del Catastro", "Mensaje");
	        break;
	}
    }

    public void setCadastralByYear(CadastralByYear cadastralByYear) {
	this.cadastralByYear = cadastralByYear;
	loadData();
    }

}
