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
 * DebitNotesAdmin.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.accountingentry;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

import org.digitall.apps.cashflow.classes.BookKeepingEntryDetail;
import org.digitall.apps.cashflow.classes.BookkKeepingEntryPaymentWay;
import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.BookKeepingEntry;
import org.digitall.common.cashflow.classes.CashMovement;
import org.digitall.common.cashflow.classes.CashMovementType;
import org.digitall.common.cashflow.classes.Check;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.cashflow.interfaces.accounting.AccountsMain;
import org.digitall.common.cashflow.interfaces.cashmovement.CashMovementsList;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.buttons.UnAssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class DebitNotesAdmin extends BasicPrimitivePanel {
    private BasicPanel jpHeader = new BasicPanel();
    private BasicPanel jpDetail = new BasicPanel();
    private BasicPanel payFormDataPanel = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicPanel BasicPanel4 = new BasicPanel();
    private TFInput tfDate = new TFInput(DataTypes.SIMPLEDATE, "Date", false);
    private TFInput tfConcept = new TFInput(DataTypes.STRING, "Concept", false);
    private TFInput tfFindAccount = new TFInput(DataTypes.STRING, "FindAccounting", false);
    private TFInput tfAccountAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private int[] sizeColumnList = { 318, 81, 83, 284 };
    private Vector dataRow = new Vector();
    private GridPanel listBookKeepingEntryDetailPanel = new GridPanel(30, sizeColumnList, "", dataRow);
    private Vector headerList = new Vector();
    private CBInput cbAccounting = new CBInput(0, "Accounting", false);
    private CBInput cbCashMovementType = new CBInput(CachedCombo.DEBITCASHMOVEMENTTYPES, "CashMovementType2");
    private BasicRadioButton rbtnDebit = new BasicRadioButton();
    private BasicRadioButton rbtnCredit = new BasicRadioButton();
    private BasicRadioButton rbtnPerson = new BasicRadioButton();
    private BasicRadioButton rbtnCompany = new BasicRadioButton();
    private JMoneyEntry tfDebitAmount = new JMoneyEntry();
    private JMoneyEntry tfCreditAmount = new JMoneyEntry();
    private JMoneyEntry tfAvailableAmount = new JMoneyEntry();
    private BasicLabel BasicLabel1 = new BasicLabel();
    private BasicLabel BasicLabel2 = new BasicLabel();
    private BasicLabel BasicLabel3 = new BasicLabel();
    private AssignButton btnAddBookKeepingEntry = new AssignButton(true);
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private BasicPanel content = new BasicPanel();
    private FindButton btnFindAccounting = new FindButton();
    private BookKeepingEntry bookKeepingEntry;
    private BookKeepingEntryDetail bookKeepingEntryDetail;
    private Account accounting;
    private CBInput cbCostsCentre = new CBInput(0, "CostsCentre", false);
    private CBInput cbActivity = new CBInput(CachedCombo.ACTIVITY, "Activities", true);
    private BorderLayout borderLayout2 = new BorderLayout();
    private DeleteButton btnDelete = new DeleteButton();
    private TFInput tfSearchProvider = new TFInput(DataTypes.STRING, "SearchButton", false);
    private CBInput cbBeneficiary = new CBInput(0, "BeneficiarySubject", false);
    //private AcceptButton btnTest = new AcceptButton();
    private final int PERSONA = 1;
    private final int EMPRESA = 2;
    //private BasicPanel payFormDataPanel = new BasicPanel();
    private TFInput tfCheckDate = new TFInput(DataTypes.SIMPLEDATE, "ExpirationDate", false);
    private Vector<Check> checksVector = new Vector(); 
    private CBInput cbCheckNumber = new CBInput(0, "CheckNumber", false);
    private DeleteButton btnDeletePaymentWay = new DeleteButton();
    private UnAssignButton btnAddPaymentWay = new UnAssignButton(true);
    private CBInput cbPaymentWay = new CBInput(0, "Accounting", false);
    private TFInput tfPaymentWayAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private BasicRadioButton rbtnCash = new BasicRadioButton();
    private BasicRadioButton rbtnCheck = new BasicRadioButton();
    private BookkKeepingEntryPaymentWay bookkKeepingEntryPaymentWay;
    private Check check;
    private TFInput tfDebitNoteNumber = new TFInput(DataTypes.INTEGER, "Number", false);

    public DebitNotesAdmin() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(662, 568));
        this.setPreferredSize(new Dimension(590, 500));
        this.add(content, null);
        jpHeader.setPreferredSize(new Dimension(10, 145));
        jpHeader.setLayout(null);
        jpDetail.setLayout(borderLayout1);
        payFormDataPanel.setPreferredSize(new Dimension(10, 70));
        payFormDataPanel.setLayout(null);
        tfDate.setBounds(new Rectangle(15, 20, 90, 35));
        tfConcept.setBounds(new Rectangle(225, 60, 425, 35));
        BasicPanel4.setPreferredSize(new Dimension(10, 105));
        BasicPanel4.setLayout(null);
        tfFindAccount.setBounds(new Rectangle(10, 20, 110, 35));
        cbActivity.setBounds(new Rectangle(10, 60, 420, 35));
        cbActivity.autoSize();
        tfAccountAmount.setBounds(new Rectangle(460, 60, 120, 35));
        cbAccounting.setBounds(new Rectangle(130, 20, 450, 35));
        cbCashMovementType.setBounds(new Rectangle(170, 20, 365, 35));
        rbtnDebit.setText(Environment.lang.getProperty("Debit"));
        rbtnDebit.setBounds(new Rectangle(10, 0, 88, 18));
        rbtnDebit.setSelected(true);
        rbtnDebit.setSize(new Dimension(88, 18));
        rbtnCredit.setText(Environment.lang.getProperty("Credit"));
        rbtnCredit.setBounds(new Rectangle(130, 0, 93, 18));
        rbtnCredit.setSize(new Dimension(93, 18));
        tfDebitAmount.setBounds(new Rectangle(105, 5, 115, 20));
        tfCreditAmount.setBounds(new Rectangle(335, 5, 115, 20));
        tfAvailableAmount.setBounds(new Rectangle(490, 5, 115, 20));
        BasicLabel1.setText(Environment.lang.getProperty("DebitAmount") + ":");
        BasicLabel1.setBounds(new Rectangle(5, 5, 97, 20));
        BasicLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        BasicLabel2.setText(Environment.lang.getProperty("CreditAmount") + ":");
        BasicLabel2.setBounds(new Rectangle(230, 5, 101, 20));
        BasicLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        BasicLabel3.setText(Environment.lang.getProperty("AvailableAmount") + ":");
        BasicLabel3.setBounds(new Rectangle(415, 5, 65, 20));
        BasicLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        btnAddBookKeepingEntry.setBounds(new Rectangle(585, 75, 20, 20));
        btnAddBookKeepingEntry.setSize(new Dimension(20, 20));
        btnAddBookKeepingEntry.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnAddBookKeepingEntry_actionPerformed(e);
                }
            }
        );
        btnSave.setBounds(new Rectangle(620, 10, 28, 33));
        btnSave.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnSave_actionPerformed(e);
                }
            }
        );
        btnClose.setBounds(new Rectangle(670, 10, 20, 20));
        btnClose.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnClose_actionPerformed(e);
                }
            }
        );
        jpHeader.add(tfDebitNoteNumber, null);
        jpHeader.add(tfConcept, null);
        jpHeader.add(cbCostsCentre, null);
        jpHeader.add(tfDate, null);
        jpHeader.add(cbCashMovementType, null);
        jpHeader.add(cbBeneficiary, null);
        jpHeader.add(tfSearchProvider, null);
        jpHeader.add(rbtnCompany, null);
        jpHeader.add(rbtnPerson, null);
        BasicPanel4.add(btnDelete, null);
        BasicPanel4.add(btnFindAccounting, null);
        BasicPanel4.add(btnAddBookKeepingEntry, null);
        BasicPanel4.add(rbtnCredit, null);
        BasicPanel4.add(rbtnDebit, null);
        BasicPanel4.add(cbAccounting, null);
        BasicPanel4.add(tfAccountAmount, null);
        BasicPanel4.add(cbActivity, null);
        BasicPanel4.add(tfFindAccount, null);
        jpDetail.add(BasicPanel4, BorderLayout.NORTH);
        jpDetail.add(listBookKeepingEntryDetailPanel, BorderLayout.CENTER);
        payFormDataPanel.add(BasicLabel3, null);
        payFormDataPanel.add(BasicLabel2, null);
        payFormDataPanel.add(BasicLabel1, null);
        payFormDataPanel.add(tfAvailableAmount, null);
        payFormDataPanel.add(tfCreditAmount, null);
        payFormDataPanel.add(tfDebitAmount, null);
        content.setLayout(borderLayout2);
        btnFindAccounting.setBounds(new Rectangle(585, 35, 20, 20));
        btnFindAccounting.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnFindAccounting_actionPerformed(e);
                }
            }
        );
        btnDelete.setBounds(new Rectangle(620, 75, 20, 20));
        btnDelete.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnDelete_actionPerformed(e);
                }
            }
        );
        content.add(jpHeader, BorderLayout.NORTH);
        content.add(jpDetail, BorderLayout.CENTER);
        content.add(payFormDataPanel, BorderLayout.SOUTH);
        addButton(btnClose);
        addButton(btnSave);
        cbAccounting.autoSize();
        cbCashMovementType.autoSize();
        ButtonGroup rbtn = new ButtonGroup();
        rbtn.add(rbtnDebit);
        rbtn.add(rbtnCredit);
        tfFindAccount.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        loadComboAccounting();
                    }
                }
            }
        );
        cbCostsCentre.setBounds(new Rectangle(15, 60, 200, 35));
        cbCostsCentre.autoSize();
        setHeaderList();
        //tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
        tfDebitAmount.setEnabled(false);
        tfCreditAmount.setEnabled(false);
        //tfAvailableAmount.setEnabled(false);
        btnSave.setToolTipText("Grabar datos");
        btnClose.setToolTipText("Cancelar asiento");
        btnDelete.setToolTipText("Borrar un ítem del asiento");
        tfSearchProvider.setBounds(new Rectangle(225, 100, 110, 35));
        cbBeneficiary.setBounds(new Rectangle(345, 100, 305, 35));
        rbtnPerson.setText("Persona");
        rbtnPerson.setBounds(new Rectangle(15, 110, 85, 20));
        rbtnCompany.setText("Empresa");
        rbtnCompany.setBounds(new Rectangle(135, 110, 85, 20));
        rbtnPerson.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    rbtnPerson_actionPerformed(e);
                }
            }
        );
        rbtnCompany.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    rbtnCompany_actionPerformed(e);
                }
            }
        );
        btnAddBookKeepingEntry.setToolTipText("Agregar un ítem al asiento");
        loadComboCostsCentre();
        ButtonGroup rbtnProvider = new ButtonGroup();
        rbtnProvider.add(rbtnPerson);
        rbtnProvider.add(rbtnCompany);
        rbtnPerson.setSelected(true);
        cbBeneficiary.autoSize();
        tfSearchProvider.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        loadComboBeneficiary();
                    }
                }
            }
        );
        tfDebitAmount.setVisible(false);
        tfCreditAmount.setVisible(false);
        //tfAvailableAmount.setVisible(false);
        BasicLabel1.setVisible(false);
        BasicLabel2.setVisible(false);
        BasicLabel3.setVisible(true);
        rbtnCash.setText(Environment.lang.getProperty("Cash"));
        rbtnCash.setBounds(new Rectangle(10, 5, 81, 18));
        rbtnCash.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    rbtnCash_actionPerformed(e);
                }
            }
        );
        rbtnCheck.setText(Environment.lang.getProperty("Check"));
        rbtnCheck.setBounds(new Rectangle(140, 5, 81, 18));
        rbtnCheck.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    rbtnCheck_actionPerformed(e);
                }
            }
        );
        tfDebitNoteNumber.setBounds(new Rectangle(560, 20, 90, 35));
        ButtonGroup rbtnPaymentWay = new ButtonGroup();
        rbtnPaymentWay.add(rbtnCash);
        rbtnPaymentWay.add(rbtnCheck);
        rbtnCash.setSelected(true);
        cbPaymentWay.setBounds(new Rectangle(10, 30, 250, 35));
        cbPaymentWay.autoSize();
        cbPaymentWay.getCombo().addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        if (rbtnCheck.isSelected()) {
                            cbCheckNumber.setEnabled(true);
                            tfCheckDate.setEnabled(true);
                            /** hay que hacer que funcione la llamada de abajo (loadComboChecks()) */
                            loadComboChecks();
                        } else {
                            cbCheckNumber.removeAllItems();
                            cbCheckNumber.setEnabled(false);
                            tfCheckDate.setEnabled(false);
                        }
                    }
                }
            }
        );
        cbCheckNumber.setBounds(new Rectangle(270, 30, 100, 35));
        cbCheckNumber.autoSize();
        tfCheckDate.setBounds(new Rectangle(380, 30, 100, 35));
        tfPaymentWayAmount.setBounds(new Rectangle(490, 30, 115, 35));
        tfPaymentWayAmount.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        savePaymentWay();
                    }
                }
            }
        );
        tfAccountAmount.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        saveDetailData();
                    }
                }
            }
        );
        btnAddPaymentWay.setBounds(new Rectangle(610, 45, 20, 20));
        btnAddPaymentWay.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnAddPaymentWay_actionPerformed(e);
                }
            }
        );
        btnAddPaymentWay.setToolTipText("Agregar forma de pago al Haber");
        btnDeletePaymentWay.setBounds(new Rectangle(635, 45, 20, 20));
        btnDeletePaymentWay.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    btnDeletePaymentWay_actionPerformed(e);
                }
            }
        );
        btnDeletePaymentWay.setToolTipText("Borrar forma de pago del Haber");
        payFormDataPanel.add(cbCheckNumber, null);
        payFormDataPanel.add(tfCheckDate, null);
        payFormDataPanel.add(btnDeletePaymentWay, null);
        payFormDataPanel.add(btnAddPaymentWay, null);
        payFormDataPanel.add(cbPaymentWay, null);
        payFormDataPanel.add(tfPaymentWayAmount, null);
        payFormDataPanel.add(rbtnCheck, null);
        payFormDataPanel.add(rbtnCash, null);
        payFormDataPanel.add(tfAvailableAmount, null);
        //cbCashMovementType.setSelectedID();
        btnDelete.setEnabled(false);
        //btnDelete.setVisible(false);
        btnDeletePaymentWay.setEnabled(false);
        btnDeletePaymentWay.setVisible(false);
        rbtnCash.setSelected(true);
        tfAvailableAmount.setEnabled(false);
        jpHeader.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("MainData")));
        jpDetail.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("NewAccountingEntry")));
    }

    private void loadComboCostsCentre() {
        cbCostsCentre.loadJCombo("cashflow.getAllCostsCentresByBudgets", "");
        cbCostsCentre.getCombo().updateUI();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar datos\". Recuerde que el asiento debe estar balanceado");
    }

    private void loadComboAccounting() {
        String param = "-1,'" + tfFindAccount.getString() + "'";
        cbAccounting.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsForBookKeepingEntry", param));
    }

    private void loadComboBeneficiary() {  
        String param = "'" + tfSearchProvider.getString() + "'";
        if (rbtnPerson.isSelected()) {
            cbBeneficiary.loadJCombo(LibSQL.exFunction("org.getAllPersonsByFilter", param));
        } else {
            cbBeneficiary.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", param));
        }
    }

    private void setHeaderList() {
        headerList.removeAllElements();
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement(Environment.lang.getProperty("Accounting"));
        headerList.addElement(Environment.lang.getProperty("Debit"));
        headerList.addElement(Environment.lang.getProperty("Credit"));
        headerList.addElement(Environment.lang.getProperty("Observations"));
        headerList.addElement("*");
        listBookKeepingEntryDetailPanel.getTable().addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    control();
                    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
                    } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
                        int index = listBookKeepingEntryDetailPanel.getTable().rowAtPoint(e.getPoint());
                        listBookKeepingEntryDetailPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
                    }
                }
            }
        );
        listBookKeepingEntryDetailPanel.setParams("accounting.getAllBookKeepingEntryDetail", "-1", headerList);
    }

    private void control() {
        /*System.out.println(dataRow);
        System.out.println(dataRow.get(2));*/
        System.out.println(LibSQL.getBoolean("cashflow.existInBankAccounts", "" + dataRow.get(2)));
        if (LibSQL.getBoolean("cashflow.existInBankAccounts", "" + dataRow.get(2))) {
            btnDelete.setEnabled(false);
            //btnDelete.setVisible(false);
            //System.out.println("Existe!");
        } else {
            btnDelete.setEnabled(true);
            //btnDelete.setVisible(true);
            //System.out.println("No Existe");
        }
    }

    public void refresh() {
        if (bookKeepingEntry != null) {
            updateAmount();
            String params = "" + bookKeepingEntry.getIDBookKeepingEntry();
            listBookKeepingEntryDetailPanel.refresh(params);
        }
        if (rbtnCheck.isSelected()) {
            loadComboAccountsForCheck();
        } else {
            loadComboAccountsCash();
        }
        loadComboChecks();
    }

    public void reload() {
        tfFindAccount.setValue("");
        cbAccounting.removeAllItems();
        if (accounting != null) {
            cbAccounting.getCombo().addItem(accounting.getName(), "" + accounting.getIDAccount());
        }
    }

    public boolean saveHeaderData() {
        if (bookKeepingEntry == null) {
            bookKeepingEntry = new BookKeepingEntry();
        }
        bookKeepingEntry.setConcept(tfConcept.getString());
        bookKeepingEntry.setDate(Proced.setFormatDate(tfDate.getDate(), false));
        bookKeepingEntry.setNoteType(2);
        System.out.println(bookKeepingEntry.getDate());
        bookKeepingEntry.setNumber(Integer.parseInt(tfDebitNoteNumber.getValue().toString()));
        if (bookKeepingEntry.saveDataAdmin() >= 0) {
	    Environment.addUnfinishedTask(this.getParentInternalFrame());
            setEnabledPanelBeneficiary(false);
            //cbAccounting.removeAllItems();
            tfDate.setEnabled(false);
            cbCostsCentre.setEnabled(false);
            cbCashMovementType.setEnabled(false);
            tfDebitNoteNumber.setEnabled(false);
            tfConcept.setEnabled(false);
            bookKeepingEntry.getData();
            return true;
        } else {
            Advisor.messageBox("El Numero de la Nota de Egreso ya esta registrado", "Mensaje");
            return false;
        }
    }

    public boolean saveDetailData() {
        if (tfConcept.getString().length() > 0 && (cbBeneficiary.getSelectedIndex() != -1) && tfDate.getString().length()>0 && cbAccounting.getSelectedIndex() != -1 && ( Integer.parseInt(tfDebitNoteNumber.getValue().toString()) > 0)) {
            boolean band = false;
            if (bookKeepingEntry == null || bookKeepingEntry.getIDBookKeepingEntry() == -1) {
                band = saveHeaderData();
            } else {
                band = true;
            }
            if (band) {
                if (cbActivity.getSelectedIndex() >= 0) {
                    if (bookKeepingEntryDetail == null) {
                        bookKeepingEntryDetail = new BookKeepingEntryDetail();
                    }
                    //System.out.println(cbAccounting.getSelectedValue());
                    bookKeepingEntryDetail.setAccounting(new Account(Integer.parseInt("" + cbAccounting.getSelectedValue())));
                    bookKeepingEntryDetail.setBookKeepingEntry(bookKeepingEntry);
                    if (rbtnDebit.isSelected()) {
                        bookKeepingEntryDetail.setDebitAmount(tfAccountAmount.getAmount());
                        bookKeepingEntryDetail.setCredtAmount(0);
                    } else {
                        bookKeepingEntryDetail.setDebitAmount(0);
                        bookKeepingEntryDetail.setCredtAmount(tfAccountAmount.getAmount());
                    }
                    bookKeepingEntryDetail.setObservations(cbActivity.getSelectedItem().toString());
                    bookKeepingEntryDetail.setVoucher(new Voucher());
                    bookKeepingEntryDetail.setIdbudget(Integer.parseInt(cbCostsCentre.getSelectedValueRef().toString()));
                    bookKeepingEntryDetail.setIdcostcentre(Integer.parseInt(cbCostsCentre.getSelectedValue().toString()));
                    if (bookKeepingEntryDetail.saveData() >= 0) { 
                        refresh();
                        bookKeepingEntryDetail = null;
                        tfFindAccount.setValue("");
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    Advisor.messageBox("Actividad/Destino requerido", "Datos requeridos");
                    return false;
                }
            } else {
                return false;
            }
        } else {
            Advisor.messageBox("Los campos Fecha, Concepto, Beneficiario/Sujeto y Cuenta no deben estar vacios\ny el campo Nº debe ser mayor que cero", "Datos requeridos");
            return false;
        }
    }

    private void setEnabledPanelBeneficiary(boolean _valor) {
        rbtnPerson.setEnabled(_valor);
        rbtnCompany.setEnabled(_valor);
        tfSearchProvider.setEnabled(_valor);
        cbBeneficiary.setEnabled(_valor);
        if (_valor) {
            tfFindAccount.setValue("");
            cbAccounting.removeAllItems();
        }
    }

    private void updateAmount() {
        if (bookKeepingEntry.updateAmount() >= 0) {
            bookKeepingEntry.getData();
            tfDebitAmount.setValue(bookKeepingEntry.getDebitAmount());
            tfCreditAmount.setValue(bookKeepingEntry.getCreditAmount());
            tfAvailableAmount.setValue(Math.abs(bookKeepingEntry.getAvailableAmount()));
            if (bookKeepingEntry.getAvailableAmount() == 0) {
                tfAvailableAmount.setBackground(Color.GREEN.brighter());
            } else {
                tfAvailableAmount.setBackground(new Color(200, 50, 50));
            }
        } else {
            Advisor.messageBox("Error al actualizar los montos", "Error de actualizacion");
        }
    }

    private void btnAddBookKeepingEntry_actionPerformed(ActionEvent e) {
        if (tfAccountAmount.getAmount() > 0 && cbAccounting.getSelectedIndex() != -1) {
            saveDetailData();
        } else {
            Advisor.messageBox("El campo Monto debe ser mayor que cero", "Error");
        }
    }

    private void btnSave_actionPerformed(ActionEvent e) {
        saveData();
    }

    public boolean saveData() {
        boolean returns = false;
        if ((tfDebitAmount.getAmount() == tfCreditAmount.getAmount()) && (tfCreditAmount.getAmount() > 0) && (tfAvailableAmount.getAmount() == 0)) {
            if (bookKeepingEntry != null) {
                bookKeepingEntry.approve();
                /** acá se tiene que grabar el movimiento de dinero */
                CashMovement cashMovement = new CashMovement();
                cashMovement.setIdbookkeepingentry(bookKeepingEntry.getIDBookKeepingEntry());
                CashMovementType cashMovementType = new CashMovementType();
                cashMovementType.setIdCashMovementType(Integer.parseInt(cbCashMovementType.getSelectedValue().toString()));
                cashMovement.setCostsCentre(new CostsCentre(Integer.parseInt(cbCostsCentre.getSelectedValue().toString())));
                cashMovement.setVoucher(new Voucher());
                cashMovement.setCashMovementType(cashMovementType);
                cashMovement.setDate(Proced.setFormatDate(tfDate.getDate(), false));
                cashMovement.setAmount(tfCreditAmount.getAmount());
                cashMovement.setDescription(tfConcept.getString());
                if (rbtnPerson.isSelected()) {
                    cashMovement.setBenefittype(PERSONA);
                } else {
                    cashMovement.setBenefittype(EMPRESA);
                }
                cashMovement.setIdbenefit(Integer.parseInt(cbBeneficiary.getSelectedValue().toString()));
                if (cashMovement.addData() > 0) {
                    returns = true;
                    if (Advisor.question("Informe", "¿Desea imprimir el asiento del movimiento?")) {
                        cashMovement.retrieveData();
                        String movementType = "";
                        if (cashMovement.getCashMovementType().getSign() == 1) {
                            movementType = "Nota de Ingreso";
                        } else {
                            movementType = "Nota de Egreso";
                        }
                        BasicReport report = new BasicReport(CashMovementsList.class.getResource("xml/selectedCashMovementReport.xml"));
                        //report.addTableModel("accounting.getBookKeepingEntry", "" + cashMovement.getIdbookkeepingentry());
                        report.addTableModel("accounting.xmlGetBookKeepingEntry", "" + cashMovement.getIdbookkeepingentry());
                        report.addTableModel("accounting.xmlGetBookKeepingEntryDetail", "" + cashMovement.getIdbookkeepingentry());
                        report.setProperty("movementtype", "" + movementType);
                        report.setProperty("totalAmount", tfCreditAmount.getAmount());
                        report.setProperty("textamount", Format.NumberToText.numberToText(tfCreditAmount.getAmount()) + ".-");
                        report.doReport();
                    }
                    Environment.removeUnfinishedTask(this.getParentInternalFrame());
                    clearData();
                    getParentInternalFrame().setIcon(true);
                } else {
                    Advisor.messageBox("Ocurrió un error al grabar el Movimiento de Fondos", "Error");
                }
            }
        } else {
            Advisor.messageBox("Los montos \"Debe\" y \"Haber\" deben ser superiores a cero e iguales entre sí", "Aviso");
        }
        return returns;
    }

    private void clearData() {
        tfFindAccount.setValue("");
        cbAccounting.removeAllItems();
        tfSearchProvider.setValue("");
        cbBeneficiary.removeAllItems();
        tfConcept.setValue("");
        tfAccountAmount.setValue(0.0);
        tfAvailableAmount.setValue(0.0);
        tfCreditAmount.setValue(0.0);
        tfDebitAmount.setValue(0.0);
        tfPaymentWayAmount.setValue(0.0);
        rbtnDebit.setSelected(true);
        rbtnCredit.setSelected(false);
        listBookKeepingEntryDetailPanel.refresh("0");
        tfDate.setEnabled(true);
        tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
        cbCostsCentre.setEnabled(true);
        cbCashMovementType.setEnabled(true);
        tfDebitNoteNumber.setEnabled(true);
        tfConcept.setEnabled(true);
        setEnabledPanelBeneficiary(true);
        checksVector.removeAllElements();
        rbtnCash.setSelected(true);
        tfDebitNoteNumber.setValue(0);
        bookKeepingEntry = null;
	tfAvailableAmount.setBackground(Color.RED); 
	tfDate.setValue(null);
        //tfCheckDate.setValue(null);
    }

    private void cancelBookKeepingEntry() {
        if (Advisor.question("Anular Asiento", "¿Desea anular el Asiento Nº" + bookKeepingEntry.getNumber() + " ?.")) {
            bookKeepingEntry.rollBack();
            for (int i = 0; i < checksVector.size(); i++) {
                checksVector.elementAt(i).rollBack();
            }
            clearData();
            refresh();
            getParentInternalFrame().setIcon(true);
        }
        Environment.removeUnfinishedTask(this.getParentInternalFrame());
	
    }

    private void btnClose_actionPerformed(ActionEvent e) {
        if (bookKeepingEntry != null) {
            cancelBookKeepingEntry();
        } else {
	    clearData();
            getParentInternalFrame().setIcon(true);
        }
    }

    private void loadAccounting() {
        AccountsMain accountingMain = new AccountsMain();
        accounting = new Account();
        accountingMain.setAccount(accounting);
        accountingMain.setParentPanel(this);
        ExtendedInternalFrame accountingContainer = new ExtendedInternalFrame("Cuentas");
        accountingContainer.setCentralPanel(accountingMain);
        accountingContainer.show();
    }

    private void btnFindAccounting_actionPerformed(ActionEvent e) {
        loadAccounting();
    }

    private void rbtnPerson_actionPerformed(ActionEvent e) {
        tfSearchProvider.setValue("");
        cbBeneficiary.removeAllItems();
    }

    private void rbtnCompany_actionPerformed(ActionEvent e) {
        tfSearchProvider.setValue("");
        cbBeneficiary.removeAllItems();
    }

    private void btnDelete_actionPerformed(ActionEvent e) {
        //System.out.println("dataRow.size(): " + dataRow.size());
        if (dataRow.size() != 0) {
            BookKeepingEntryDetail bookKeepingEntryDetail = new BookKeepingEntryDetail();
            bookKeepingEntryDetail.setIDBookKeepingEntryDetail(Integer.parseInt(dataRow.elementAt(0).toString()));
            if (bookKeepingEntryDetail.deleteData() > 0) {
                refresh();
            }
        } else {
            Advisor.messageBox("Debe seleccionar el ítem que desea borrar ", "Error");
        }
    }

    private void btnReportTest_actionPerformed(ActionEvent e) {
    }

    private void rbtnCash_actionPerformed(ActionEvent e) {
        cbPaymentWay.setSComponentName("Cash");
        cbPaymentWay.removeAllItems();
        loadComboAccountsCash();
        clearCheckData(false);
    }

    private void loadComboAccountsCash() {
        cbPaymentWay.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsCashByFilter", "''"));
    }

    private void rbtnCheck_actionPerformed(ActionEvent e) {
        cbPaymentWay.setSComponentName("AccountingCodeBankAccountNumber");
        cbPaymentWay.removeAllItems();
        clearCheckData(true);
        loadComboAccountsForCheck();
    }

    private boolean savePaymentWay() {
        boolean ok = false;
        if (tfConcept.getString().length() > 0 && (cbBeneficiary.getSelectedIndex() != -1)) {
            if (bookKeepingEntry == null) {
                saveHeaderData();
            }
            double amount = tfPaymentWayAmount.getAmount();
            // Esta linea la agregue momentaneamente
            //if (amount <= tfAmountToPay.getAmount()) { /**Cuidado con el valor de netAmount!!!!!!! */
            if (bookkKeepingEntryPaymentWay == null) {
                bookkKeepingEntryPaymentWay = new BookkKeepingEntryPaymentWay();
            }
            bookkKeepingEntryPaymentWay.setIdBookKeepingEntry(bookKeepingEntry.getIDBookKeepingEntry());
            bookkKeepingEntryPaymentWay.setAmount(amount);
            if (rbtnCash.isSelected() && tfPaymentWayAmount.getAmount() > 0 ) {
                bookkKeepingEntryPaymentWay.setAccount(new Account(Integer.parseInt("" + cbPaymentWay.getSelectedValue())));
                bookkKeepingEntryPaymentWay.setCheck(new Check());
                bookkKeepingEntryPaymentWay.setIdPaymentType(1);
                bookkKeepingEntryPaymentWay.setConcept("Efectivo");
                ok = true;
            } else if (rbtnCheck.isSelected() && tfCheckDate.getDate().length() > 0) {
                bookkKeepingEntryPaymentWay.setAccount(new Account());
                saveDataCheck(amount);
                bookkKeepingEntryPaymentWay.setCheck(check);
                bookkKeepingEntryPaymentWay.setIdPaymentType(2);
                bookkKeepingEntryPaymentWay.setAccount(new Account(Integer.parseInt("" + cbPaymentWay.getSelectedValue())));
                bookkKeepingEntryPaymentWay.setConcept("Pago con cheque Nº " + check.getNumber() + " - " + Proced.setFormatDate(check.getDate(), true));
                bookkKeepingEntryPaymentWay.setRefNumber(""+ check.getNumber());
                tfPaymentWayAmount.setValue(0.0);
                loadComboAccountsForCheck();
                //clearCheckData(true);
                loadComboChecks();
                ok = true;
            }
            bookkKeepingEntryPaymentWay.setNoteType(2);
            bookkKeepingEntryPaymentWay.setIdbudget(Integer.parseInt(cbCostsCentre.getSelectedValueRef().toString()));
            bookkKeepingEntryPaymentWay.setIdcostcentre(Integer.parseInt(cbCostsCentre.getSelectedValue().toString())); 
            if (ok) {
                if (bookkKeepingEntryPaymentWay.saveData() >= 0) {
                    bookkKeepingEntryPaymentWay = null;
                    clearCheckData(rbtnCheck.isSelected());
                    //refreshAllData();
                    refresh();
                    return true;
                } else {
                    return false;
                }
            }
            else {
                Advisor.messageBox("El campo fecha de Vto. no debe estar vacio\ny el campo Monto debe ser mayor que cero", "Datos requeridos");
                return false;
            }
            // } else {
            //  Advisor.messageBox("El importe a pagar es " + Format.money(netAmount), "Importe no vÃ¡lido");
            //  return false;
            //  }
        } else {
            Advisor.messageBox("Los campos Concepto y Beneficiario/Sujeto no deben estar vacios", "Datos requeridos");
            return false;
        }
    }

    private void btnAddPaymentWay_actionPerformed(ActionEvent e) {
        //System.out.println("size: "+ tfCheckDate.getString().length()); 
        if (cbPaymentWay.getSelectedIndex() != -1) {
            if (tfPaymentWayAmount.getAmount() > 0) {
                if ((tfCheckDate.getString().length() > 0 && rbtnCheck.isSelected() && cbCheckNumber.getSelectedIndex() != -1) || (rbtnCash.isSelected())) {
                    savePaymentWay();
                } else {
                    Advisor.messageBox("Datos incorrectos", "Error");
                }
            } else {
                Advisor.messageBox("El monto a pagar debe ser mayor que cero", "Error");
            }
        } else {
            Advisor.messageBox("No ha seleccionado una Cuenta", "Error");
        }
    }

    private void btnDeletePaymentWay_actionPerformed(ActionEvent e) {
        if (bookkKeepingEntryPaymentWay != null) {
            bookkKeepingEntryPaymentWay.delete();
            bookkKeepingEntryPaymentWay = null;
            //refreshAllData();
            refresh();
        }
    }

    private boolean saveDataCheck(double _amount) {
        if (bookkKeepingEntryPaymentWay.getIdBookKeepingEntryPaymentWay() == -1) {
            check = new Check((Integer.parseInt(cbCheckNumber.getSelectedValue().toString())));
            check.retrieveData();
            checksVector.add(check);
        }
        check.setAmount(_amount);
        check.setDate(Proced.setFormatDate(tfDate.getDate(), false));
        check.setDescription(tfConcept.getString());
        check.setExpiredDate(Proced.setFormatDate(tfCheckDate.getDate(), false));
        check.setIdCheckStatus(Check.HANDED);
        check.setIdEntity(Integer.parseInt(cbBeneficiary.getSelectedValue().toString()));
        if (rbtnPerson.isSelected()) {
            check.setIdEntitytype(15);
        } else {
            check.setIdEntitytype(67);
        }
        check.setEstado("p");
        int result = -1;
        result = check.setData();
        return (result > 0 ? true : false);
}

    private void loadComboAccountsForCheck() {
        cbPaymentWay.loadJCombo(LibSQL.exFunction("accounting.getAllBankAccountsForChecks", "''"));
        if (cbPaymentWay.getSelectedIndex() != -1) {
            loadComboChecks();
        }
    }

    private void loadComboChecks() {
        if (cbPaymentWay.getSelectedIndex() != -1) {
            cbCheckNumber.loadJCombo(LibSQL.exFunction("cashflow.getvalidchecksByAdmin", "" + cbPaymentWay.getSelectedValueRef()));
        } else {
            Advisor.messageBox("No tiene Asociada una Cuenta Bancaria", "Accion no válida");
        }
    }

    private void clearCheckData(boolean enabled) {
        cbCheckNumber.setEnabled(enabled);
        cbCheckNumber.removeAllItems();
        tfCheckDate.setValue(null);
        tfCheckDate.setEnabled(enabled);
    }
    
    public void setDebitNoteSelected(BookKeepingEntry _debitNotesSelected, CashMovement _cashMovementSelected){
        if (_debitNotesSelected.getIDBookKeepingEntry() > 0)  {
            bookKeepingEntry = _debitNotesSelected;
            
            loadDebitNote(_cashMovementSelected);
        }
    }

    private void loadDebitNote(CashMovement _cashMovementSelected) {
        tfDebitNoteNumber.setValue(bookKeepingEntry.getNumber());
        tfConcept.setValue(bookKeepingEntry.getConcept());
        tfDate.setValue(Proced.setFormatDate(bookKeepingEntry.getDate(),true));
        if (_cashMovementSelected.getBenefittype() == 1)  {
            rbtnPerson.setSelected(true);
            rbtnCompany.setSelected(false);
        } else  {
            rbtnCompany.setSelected(true);
            rbtnPerson.setSelected(false);
        }
        loadComboBeneficiary();
        cbBeneficiary.setSelectedID(_cashMovementSelected.getIdbenefit());
        
    }
}
