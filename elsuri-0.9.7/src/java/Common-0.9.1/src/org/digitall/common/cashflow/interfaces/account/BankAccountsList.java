/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * BankAccountsList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.account;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.BankAccount;
import org.digitall.common.cashflow.interfaces.account.TransactionsList;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.environment.Environment;

public class BankAccountsList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 244, 108, 285 };
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Cuentas Bancarias", dataRow);
    private AddButton btnAdd = new AddButton();
    private BankAccount bankAccount;
    private ModifyButton btnModify = new ModifyButton();
    private BankAccountsMgmt accountsMgmt;
    private TransactionsList transactionsList;
    private AcceptButton btnTransactions = new AcceptButton();
    private AcceptButton btnChecks = new AcceptButton();
    private PrintButton btnBankJournal = new PrintButton();
    private PrintButton btnChecksReport = new PrintButton();
    private PrintButton btnIssueChecksReport = new PrintButton();
    private PrintButton btnFinancialBankBook = new PrintButton();
    private PrintButton btnBankBook = new PrintButton();
    private ChecksList checksList;

    public BankAccountsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(690, 290));
	this.setPreferredSize(new Dimension(690, 290));
	listPanel.setBounds(new Rectangle(5, 0, 680, 285));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	btnModify.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnModify_actionPerformed(e);
				 }

			     }
	);
	btnTransactions.setText("Transacciones");
	btnTransactions.setBounds(new Rectangle(5, 295, 95, 25));
	btnTransactions.setSize(new Dimension(95, 22));
	btnTransactions.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnTransactions_actionPerformed(e);
				       }

				   }
	);
	btnChecks.setText("Cheques");
	btnChecks.setBounds(new Rectangle(115, 295, 77, 22));
	btnChecks.setSize(new Dimension(55, 22));
	btnChecks.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnChecks_actionPerformed(e);
				 }

			     }
	);
	btnBankJournal.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnBankJournal_actionPerformed(e);
				      }

				  }
	);
	btnChecksReport.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnChecksReport_actionPerformed(e);
				       }

				   }
	);
        btnIssueChecksReport.addActionListener(new ActionListener() {

                                       public void actionPerformed(ActionEvent e) {
                                           btnIssueChecksReport_actionPerformed(e);
                                       }

                                   }
        );
	btnFinancialBankBook.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnFinancialBankBook_actionPerformed(e);
				       }

				   }
	);
	btnBankBook.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnBankBook_actionPerformed(e);
				       }

				   }
	);
	this.add(btnChecks, null);
	this.add(btnTransactions, null);
	this.add(listPanel, null);
	this.addButton(btnModify);
	this.addButton(btnAdd);
	this.addButton(btnBankJournal);
	this.addButton(btnChecksReport);
        this.addButton(btnIssueChecksReport);
	this.addButton(btnFinancialBankBook);
	this.addButton(btnBankBook);
	setHeaderList();
	btnTransactions.setEnabled(false);
	btnModify.setEnabled(false);
	btnChecks.setEnabled(false);
	btnChecksReport.setShowText(true);
        btnIssueChecksReport.setShowText(true);
	btnFinancialBankBook.setShowText(true);
	btnBankBook.setShowText(true);
	btnBankJournal.setToolTipText("<html><p align=center><u>Saldo Sobre Bancos (Transacciones)</u></p></html>");
	btnChecksReport.setToolTipText("<html><p align=center><u>Listado de cheques pendientes</u></p></html>");
        btnIssueChecksReport.setToolTipText("<html><p align=center><u>Listado de cheques emitidos</u></p></html>");
	btnFinancialBankBook.setToolTipText("<html><p align=center><u>Libro Banco (FINANCIERO)</u></p></html>");
	btnBankBook.setToolTipText("<html><p align=center><u>Libro Banco (CONTABLE)</u></p></html>");
	btnAdd.setToolTipText("<html><p align=center>Agregar una nueva Cuenta Bancaria</p></html>");
	btnModify.setToolTipText("<html><p align=center>Modificar la Cuenta Bancaria seleccionada</p></html>");
	btnTransactions.setToolTipText("<html><p align=center>Abre la ventana de administración de transacciones</p></html>");
	btnChecks.setToolTipText("<html><p align=center>Abre la ventana de administración de cheques</p></html>");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnChecks);
	getParentInternalFrame().getGeneralButtons().addButton(btnTransactions);
	getParentInternalFrame().setInfo("Listado de Cuentas Bancarias");
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("AccountNumber"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("AvailableAmount"));
	headerList.addElement("*");
	headerList.addElement("*");
	//headerList.addElement(Environment.lang.getProperty("Bank"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	//headerList.addElement(Environment.lang.getProperty("CBU"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Accounting"));
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       loadObject();
					       if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						   loadChecksList();
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
						   int index = listPanel.getTable().rowAtPoint(e.getPoint());
						   listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
						   loadAccountMgmt();
					       }
					   }

				       }
	);
	listPanel.setParams("cashflow.getAllBankAccounts", "''", headerList);
    }

    public void refresh() {
	String params = "''";
	listPanel.refresh(params);
	btnTransactions.setEnabled(false);
	btnModify.setEnabled(false);
	btnChecks.setEnabled(false);
    }

    private void loadObject() {
	if (!dataRow.isEmpty()) {
	    bankAccount = new BankAccount();
	    bankAccount.setIDBankAccount(Integer.parseInt(dataRow.elementAt(0).toString()));
	    bankAccount.setIdAccountType(Integer.parseInt(dataRow.elementAt(1).toString()));
	    bankAccount.setNumber(dataRow.elementAt(3).toString());
	    bankAccount.setIdMoneyType(Integer.parseInt(dataRow.elementAt(4).toString()));
	    bankAccount.setIdBank(Integer.parseInt(dataRow.elementAt(6).toString()));
	    bankAccount.setAmount(Double.parseDouble(dataRow.elementAt(8).toString()));
	    bankAccount.setAvailableAmount(Double.parseDouble(dataRow.elementAt(9).toString()));
	    bankAccount.setCbu(Long.parseLong(dataRow.elementAt(10).toString()));
	    bankAccount.setIdOwner(Integer.parseInt(dataRow.elementAt(11).toString()));
	    bankAccount.setIsCompany((dataRow.elementAt(12).toString() == "SI" ? true : false));
	    bankAccount.setOwnerName(dataRow.elementAt(13).toString());
	    bankAccount.setStartDate(Proced.setFormatDate(dataRow.elementAt(14).toString(), false));
	    bankAccount.setEndDate(Proced.setFormatDate(dataRow.elementAt(15).toString(), false));
	    Account account = new Account(Integer.parseInt(dataRow.elementAt(16).toString()));
	    account.setFullName(dataRow.elementAt(17).toString());
	    bankAccount.setAccount(account);
	    bankAccount.setAmountModifiable((dataRow.elementAt(12).toString() == "SI" ? true : false));
	    btnTransactions.setEnabled(true);
	    btnModify.setEnabled(true);
	    btnChecks.setEnabled(true);
	    btnModify.setToolTipText("<html><p align=center><u>Modificar Cuenta Bancaria</u><br>" + dataRow.elementAt(2) + " </p></html>");
	} else {
	    btnModify.setToolTipText("<html><p align=center>Modificar la Cuenta Bancaria seleccionada</p></html>");
	}
    }

    private void loadAccountMgmt() {
	if (!dataRow.isEmpty()) {
	    accountsMgmt = new BankAccountsMgmt();
	    accountsMgmt.setBankAccount(bankAccount);
	    accountsMgmt.setParentList(this);
	    ExtendedInternalFrame accountsMgmtContainer = new ExtendedInternalFrame("Modificar datos de la " + dataRow.elementAt(2).toString());
	    accountsMgmtContainer.setCentralPanel(accountsMgmt);
	    accountsMgmtContainer.show();
	}
    }

    private void loadTransactionsList() {
	if (!dataRow.isEmpty()) {
	    transactionsList = new TransactionsList();
	    transactionsList.setAccount(bankAccount);
	    ExtendedInternalFrame transactionsListContainer = new ExtendedInternalFrame("Transacciones de la " + dataRow.elementAt(2).toString());
	    transactionsListContainer.setCentralPanel(transactionsList);
	    transactionsListContainer.setParentInternalFrame(this.getParentInternalFrame());
	    transactionsListContainer.show();
	}
    }

    private void loadChecksList() {
	if (bankAccount.getIdAccountType() != 1) {
	    checksList = new ChecksList();
	    String title = "Listado de Cheques de la " + dataRow.elementAt(2).toString();
	    checksList.setAccount(bankAccount, title);
	    ExtendedInternalFrame checkListContainer = new ExtendedInternalFrame(title);
	    checkListContainer.setCentralPanel(checksList);
	    checkListContainer.show();
	} else {
	    Advisor.messageBox("Imposible generar cheques para una Caja de Ahorro", "Imposible generar cheques");
	}
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	accountsMgmt = new BankAccountsMgmt();
	accountsMgmt.setParentList(this);
	ExtendedInternalFrame accountsMgmtContainer = new ExtendedInternalFrame("Agregar Nueva Cuenta");
	accountsMgmtContainer.setCentralPanel(accountsMgmt);
	accountsMgmtContainer.show();
    }

    private void btnModify_actionPerformed(ActionEvent e) {
	loadAccountMgmt();
    }

    private void btnTransactions_actionPerformed(ActionEvent e) {
	loadTransactionsList();
    }

    private void btnChecks_actionPerformed(ActionEvent e) {
	loadChecksList();
    }

    private void btnBankJournal_actionPerformed(ActionEvent e) {
	/* REPORTE DE LAS CUENTAS BANCARIAS SELECCIONADAS */
	/*Vector selecteds = listPanel.getSelectedsID();
	if (selecteds.size()>0) {
	    BasicReport bankAccountsReport = new BasicReport(BankAccountsList.class.getResource("xml/bankAccountsReport.xml"));
	    String params = "";
	    for (int i = 0; i < selecteds.size()-1; i++)  {
		params += selecteds.elementAt(i) + ",";
	    }
	    params += selecteds.elementAt(selecteds.size()-1);
	    bankAccountsReport.addTableModel("cashflow.getalltransactionsbyaccounts",  "'" + params + "'");
	    bankAccountsReport.doReport();
	}*/
	if (!dataRow.isEmpty()) {
	    BasicReport report = new BasicReport(BankAccountsList.class.getResource("xml/bankAccountsReport.xml"));
	    report.addTableModel("cashflow.getalltransactionsbyaccount", bankAccount.getIDBankAccount());
	    report.doReport();
	}
    }

    private void btnChecksReport_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty()) {
	    BasicReport report = new BasicReport(ChecksList.class.getResource("xml/checksList.xml"));
	    report.addTableModel("cashflow.getAllChecksNotPaid", bankAccount.getIDBankAccount());
	    report.doReport();
	}
    }

    private void btnIssueChecksReport_actionPerformed(ActionEvent e) {
        if (!dataRow.isEmpty()) {
            BasicReport report = new BasicReport(ChecksList.class.getResource("xml/IssueChecksList.xml"));
            report.addTableModel("cashflow.getAllIssueChecks", bankAccount.getIDBankAccount());
            report.doReport();
        }
    }


    private void btnFinancialBankBook_actionPerformed(ActionEvent e) {
	if (!dataRow.isEmpty()) {
	    BasicReport report = new BasicReport(BankAccountsList.class.getResource("xml/financialBankBook.xml"));
	    report.addTableModel("cashflow.getFinancialBankBook", bankAccount.getIDBankAccount() + ",'2008-01-01','2008-12-31'");
	    report.doReport();
	}
    }

    private void btnBankBook_actionPerformed(ActionEvent e) {
    	if (!dataRow.isEmpty()) {
	    BasicReport report = new BasicReport(BankAccountsList.class.getResource("xml/bankBook.xml"));
	    bankAccount.getAccount().retrieveData();
	    report.setProperty("table_0.bankaccount", bankAccount.getNumber() + " - (" + bankAccount.getAccount().getCode() + ") " + bankAccount.getAccount().getName());
	    report.addTableModel("cashflow.getBankBook", bankAccount.getAccount().getIDAccount() + ",'" + Environment.currentYear + "-01-01','" + Environment.currentDate + "'");
	    report.setProperty("startdate", Proced.setFormatDate(Environment.currentYear + "-01-01", true));
	    report.setProperty("enddate", Proced.setFormatDate(Environment.currentDate, true));
	    report.doReport();
	}
    }

}
