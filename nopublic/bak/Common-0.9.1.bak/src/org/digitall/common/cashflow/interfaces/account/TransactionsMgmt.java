package org.digitall.common.cashflow.interfaces.account;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.cashflow.classes.BankAccount;
import org.digitall.common.cashflow.classes.Transaction;
import org.digitall.common.cashflow.interfaces.account.TransactionsList;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.ReportButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class TransactionsMgmt extends BasicPanel {

    private CBInput cbTransactionType = new CBInput(CachedCombo.TRANSACTIONTYPE, "ConceptsBank", false);
    private TFInput tfAmount = new TFInput(DataTypes.MONEY, "Amount", true);
    private CBInput cbOperationMethod = new CBInput(CachedCombo.OPERATIONMETHOD, "OperationMethod");
    private TFInput tfMethodNumber = new TFInput(DataTypes.INTEGER, "Number", false);
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfConcept = new TFInput(DataTypes.STRING, "Concept", false);
    private TFInput tfTransactionNumber = new TFInput(DataTypes.INTEGER, "TransactionNumber", false);
    private AssignButton btnAdd = new AssignButton(true);
    private BankAccount bankAccount;
    private Transaction transaction;
    private TransactionsList parentList;
    private int listType;
    public static final int TRANSACTIONLIST = 1;
    public static final int ACCOUNTLIST = 2;

    public TransactionsMgmt(Transaction _transaction) {
	try {
	    transaction = _transaction;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public TransactionsMgmt(Transaction _transaction, int _listType) {
	try {
	    transaction = _transaction;
	    listType = _listType;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(620, 95));
	cbTransactionType.setBounds(new Rectangle(5, 5, 175, 40));
	tfAmount.setBounds(new Rectangle(185, 5, 135, 35));
	btnAdd.setBounds(new Rectangle(575, 65, 40, 25));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	cbOperationMethod.setBounds(new Rectangle(5, 55, 175, 35));
	tfMethodNumber.setBounds(new Rectangle(185, 55, 120, 35));
	tfDate.setBounds(new Rectangle(325, 5, 90, 35));
	tfConcept.setBounds(new Rectangle(310, 55, 265, 35));
	tfTransactionNumber.setBounds(new Rectangle(420, 5, 155, 35));
	this.add(tfTransactionNumber, null);
	this.add(tfDate, null);
	this.add(tfConcept, null);
	this.add(tfMethodNumber, null);
	this.add(cbOperationMethod, null);
	this.add(btnAdd, null);
	this.add(tfAmount, null);
	this.add(cbTransactionType, null);
	cbTransactionType.autoSize();
	cbOperationMethod.autoSize();
	btnAdd.setToolTipText("Efectuar la transacci??n");
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	if (transaction == null) {
	    transaction = new Transaction();
	}
	boolean valid = true;
	System.out.println("idcuentabancaria: "+bankAccount.getIDBankAccount());
	transaction.setIDBankAccount(bankAccount.getIDBankAccount());
	if (cbTransactionType.getSelectedValue().equals("2")) {
	    //Extraccion
	    if (tfAmount.getAmount() > bankAccount.getAmount()) {
		valid = false;
		Advisor.messageBox("Saldo insuficiente", "Transacci??n abortada");
	    }
	}
	if (tfDate.getString().length() == 0) {
	    valid = false;
	    Advisor.messageBox("Fecha no v??lida", "Fecha no v??lida");
	}

	/* VALIDO Monto */
	if (tfAmount.getAmount() <= 0) {
	    Advisor.messageBox("El monto debe ser mayor que cero", "Monto no v??lido");
	    valid = false;
	}

	/* VALIDO Nro. Cheque */
	if (new Integer(cbOperationMethod.getSelectedValue().toString()) == 1) {
	    if (Integer.parseInt("0" + tfMethodNumber.getString()) == 0) {
		Advisor.messageBox("El N?? de cheque no puede ser cero", "Cheque no v??lido");
		valid = false;
	    }
	}
	/* VALIDO Nro. Transacci??n */
	if (Integer.parseInt("0" + tfTransactionNumber.getString()) == 0) {
	    Advisor.messageBox("El N?? de transacci??n no puede ser cero", "Transacci??n no v??lida");
	    valid = false;
	}

	/* VALIDO Concepto */
	if (tfConcept.getString().length() == 0) {
	    Advisor.messageBox("Debe escribir el concepto de la transacci??n", "Transacci??n no v??lida");
	    valid = false;
	}
	if (valid) {
	    transaction.setAmount(tfAmount.getAmount());
	    transaction.setDate(Proced.setFormatDate(tfDate.getString(), false));
	    transaction.setConcept(tfConcept.getString());
	    transaction.setIdOperationMethod(Integer.parseInt(cbOperationMethod.getSelectedValue().toString()));
	    transaction.setIdTransactionType(Integer.parseInt(cbTransactionType.getSelectedValue().toString()));
	    transaction.setMethodNumber(Integer.parseInt("0" + tfMethodNumber.getString()));
	    transaction.setTransactionNumber(Integer.parseInt("0" + tfTransactionNumber.getString()));
	    if (transaction.saveData() >= 0) {
		/*tfAmount.setValue(0.0);
		tfDate.setValue("");
	        tfTransactionNumber.setValue("0");
	        tfMethodNumber.setValue("0");
		tfConcept.setValue("");*/
	        Advisor.messageBox("Transacci??n exitosa", "Transacci??n realizada");
		if (parentList != null) {
		    switch (listType) {
			case TRANSACTIONLIST :
			    {
				if (cbTransactionType.getSelectedValue().equals("2")) {
				    //Extraccion
				    bankAccount.setAmount(bankAccount.getAmount() - tfAmount.getAmount());
				} else {
				    bankAccount.setAmount(bankAccount.getAmount() + tfAmount.getAmount());
				}
				parentList.loadList();
			    }
			    break;
			case ACCOUNTLIST :
			    //((AccountsList)parentList).loadList();
			    break;
			default :
			    {
			    }
			    break;
		    }
		}
	    }
	}
    }

    public void setBankAccount(BankAccount _bankAccount) {
	this.bankAccount = _bankAccount;
    }

    public BankAccount getBankAccount() {
	return bankAccount;
    }

    public void setParentList(TransactionsList parentList) {
	this.parentList = parentList;
    }

}
