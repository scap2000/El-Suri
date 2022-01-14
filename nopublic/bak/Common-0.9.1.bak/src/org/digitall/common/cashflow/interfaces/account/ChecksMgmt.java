package org.digitall.common.cashflow.interfaces.account;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.cashflow.classes.Check;
import org.digitall.common.cashflow.interfaces.account.PaymentOrderCheckList;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class ChecksMgmt extends BasicPrimitivePanel {

    private Check check;
    private TFInput tfLetter = new TFInput(DataTypes.STRING, "FileLetter", true);
    private TFInput tfNumber = new TFInput(DataTypes.INTEGER, "Number", true);
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private String entityName;
    private AcceptButton btnAccept = new AcceptButton();
    private BasicPanel panel = new BasicPanel("Cheque");
    private ChecksList parentList;
    private PaymentOrderCheckList paymentOrderCheckList;
    private TFInput tfGenerate = new TFInput(DataTypes.INTEGER, "Generate", false);
    private CloseButton btnClose = new CloseButton();
    private double availableAmount = 0;

    public ChecksMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(570, 160));
	this.setPreferredSize(new Dimension(570, 160));
	tfLetter.setBounds(new Rectangle(20, 30, 65, 35));
	tfNumber.setBounds(new Rectangle(105, 30, 125, 35));
	tfDescription.setBounds(new Rectangle(20, 80, 540, 35));
	btnAccept.setBounds(new Rectangle(470, 240, 40, 25));
	btnAccept.setSize(new Dimension(40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	panel.setBounds(new Rectangle(5, 0, 560, 225));
	panel.setLayout(null);
	tfGenerate.setBounds(new Rectangle(240, 30, 80, 35));
	btnClose.setBounds(new Rectangle(510, 240, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	panel.add(tfGenerate, null);
	panel.add(tfLetter, null);
	panel.add(tfDescription, null);
	panel.add(tfNumber, null);
	this.add(panel, null);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	btnAccept.setToolTipText("<html><p align=center>Agregar nueva chequera</p></html>");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los campos y presione el botón \"Aceptar\" para generar una nueva chequera");
    }

    private void loadData() {
	if (check.getIdCheck() != -1) {
	    tfDescription.setValue(check.getDescription());
	    tfLetter.setValue(check.getLetter());
	    tfNumber.setValue("" + check.getNumber());
	    tfGenerate.setEnabled(false);
	}
    }

    public void setCheck(Check check) {
	this.check = check;
	loadData();
    }

    public void setEntityName(String entityName) {
	this.entityName = entityName;
    }

    public boolean saveData() {
	boolean returns = false;
	int qty = Integer.parseInt("0" + tfGenerate.getString());
	if (qty > 0 && tfLetter.getString().length() > 0 && tfNumber.getString().length() > 0) {
	    if (Advisor.question("Generación de chequera", "¿Desea generar " + qty + " cheques (" + (Integer.parseInt("0" + tfNumber.getString())) + ".." + (Integer.parseInt("0" + tfNumber.getString()) + (qty-1)) + ")?")) {
		check.setDescription(tfDescription.getString());
		check.setIdCheckType(0);
		check.setIdEntity(0);
		check.setIdEntitytype(0);
		check.setLetter(tfLetter.getString());
		check.setNumber(Integer.parseInt("0" + tfNumber.getString()));
		returns = check.generate(qty);
		if (returns) {
		    if (parentList != null) {
			parentList.refresh();
		    } else {
			paymentOrderCheckList.updateAmountChecks(check.getAmount());
			paymentOrderCheckList.refresh();
		    }
		    getParentInternalFrame().close();
		}
	    }
	} else {
	    Advisor.messageBox("<html><center>El número de cheques a generar debe ser mayor que cero\n y los campos Letra y Número no pueden estar vacíos</center></html>", "Error");
	}
	return returns;
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	saveData();
    }

    public void setParentList(ChecksList parentList) {
	this.parentList = parentList;
    }

    public void setPaymentOrderCheckList(PaymentOrderCheckList _paymentOrderCheckList) {
	this.paymentOrderCheckList = _paymentOrderCheckList;
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setAvailableAmount(double availableAmount) {
	this.availableAmount = availableAmount;
    }

}
