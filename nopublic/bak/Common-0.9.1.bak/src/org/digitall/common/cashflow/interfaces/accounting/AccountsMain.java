package org.digitall.common.cashflow.interfaces.accounting;

import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

public class AccountsMain extends BasicTabContainer {

    private AccountsList accountsList = new AccountsList(this);
    private AccountsMgmt accountsMgmt = new AccountsMgmt(this);
    private Account account;
    private BasicPrimitivePanel parentPanel;

    public AccountsMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(550, 530));
	this.setPreferredSize(new Dimension(550, 530));
	addTab("Listado de Cuentas", accountsList);
	addTab("Nueva Cuenta", accountsMgmt);
    }

    public void changeSelectedTab() {
	if (getTabbedPane().getSelectedIndex() == 1 && !accountsMgmt.isModifying()) {
	    newAccount();
	} else if (getTabbedPane().getSelectedIndex() == 1 && accountsMgmt.isModifying()) {
	    newAccount();
	} else if (getTabbedPane().getSelectedIndex() == 0 && accountsMgmt.isModifying()) {
	    if (Advisor.question("Datos de la cuenta", "¿Desea guardar los cambios?")) {
		accountsMgmt.saveData();
	    }
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede ver las cuentas como un listado o árbol");
    }

    public void newAccount() {
	accountsMgmt.setModifying(false);
	accountsMgmt.clearData();
	setTitleAt(1, "Nueva Cuenta");
	if (accountsList.getAccount() != null) {
	    accountsMgmt.setParentAccount(accountsList.getAccount());
	} else {
	    if (Advisor.question("Nueva Cuenta", "<html><center>No seleccionó una Cuenta superior,\n" + "la nueva cuenta se agregará a la raiz.\n¿Desea continuar?</center></html>")) {
		accountsMgmt.setParentAccount(accountsList.getParentAccount());
	    } else {
		setSelectedTab(0);
	    }
	}
    }

    public AccountsList getAccountsList() {
	return accountsList;
    }

    public AccountsMgmt getAccountMgmt() {
	return accountsMgmt;
    }

    public void setAccount(Account account) {
	this.account = account;
	accountsList.setAccount(account);
    }

    public void setParentPanel(BasicPrimitivePanel parentPanel) {
	this.parentPanel = parentPanel;
	accountsList.setParentPanel(parentPanel);
    }

}
