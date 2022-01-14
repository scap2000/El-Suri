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
 * CreditNotesMgmt.java
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

public class CreditNotesMgmt extends BasicPrimitivePanel {

    private BasicPanel jpHeader = new BasicPanel();
    private BasicPanel jpDetail = new BasicPanel();
    private BasicPanel jpFormaDePago = new BasicPanel();
    private BasicPanel jpNuevoAsiento = new BasicPanel();
    private BasicPanel content = new BasicPanel();
    
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    
    private TFInput tfDate = new TFInput(DataTypes.SIMPLEDATE, "Date", true);
    private TFInput tfConcept = new TFInput(DataTypes.STRING, "Concept", true);
    private TFInput tfFindAccount = new TFInput(DataTypes.STRING, "FindAccounting", false);
    private TFInput tfAccountAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private TFInput tfSearchProvider = new TFInput(DataTypes.STRING, "SearchButton", false);
    private TFInput tfPayformNumber = new TFInput(DataTypes.STRING, "Número", true);
    private TFInput tfPaymentWayAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private TFInput tfFindAccountToPay = new TFInput(DataTypes.STRING,"FindAccounting",false);
    
    private JMoneyEntry tfDebitAmount = new JMoneyEntry();
    private JMoneyEntry tfCreditAmount = new JMoneyEntry();
    private JMoneyEntry tfAvailableAmount = new JMoneyEntry();
    
    private int[] sizeColumnList = { 318, 81, 83, 284 };
    private Vector dataRow = new Vector();
    private GridPanel listBookKeepingEntryDetailPanel = new GridPanel(30, sizeColumnList, "", dataRow){
        public void finishLoading() {
            habilitarBotones();
        }
    };
    
    private CBInput cbAccounting = new CBInput(0, "Accounting", false);
    private CBInput cbCashMovementType = new CBInput(CachedCombo.CREDITCASHMOVEMENTTYPES, "CashMovementType2");
    private CBInput cbCostsCentre = new CBInput(0, "CostsCentre", false);
    private CBInput cbActivity = new CBInput(CachedCombo.ACTIVITY, "Activities", true);    
    private CBInput cbBeneficiary = new CBInput(0, "BeneficiarySubject", false);
    private CBInput cbPaymentWay = new CBInput(0, "PaymentWay", false);
    private CBInput cbAccountToPay = new CBInput(0, "Accounting", false);
    
    private BasicRadioButton rbtnCredit = new BasicRadioButton();
    private BasicRadioButton rbtnPerson = new BasicRadioButton();
    private BasicRadioButton rbtnCompany = new BasicRadioButton();
    private BasicRadioButton rbtnDebit = new BasicRadioButton();
    
    private BasicLabel lblOculto1 = new BasicLabel();
    private BasicLabel lblOculto2 = new BasicLabel();
    private BasicLabel lblSaldo = new BasicLabel();
    
    private AssignButton btnAddBookKeepingEntry = new AssignButton(true);
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private FindButton btnFindAccounting = new FindButton();
    private DeleteButton btnDelete = new DeleteButton();
    private DeleteButton btnDeletePaymentWay = new DeleteButton();
    private UnAssignButton btnAddPaymentWay = new UnAssignButton(true);
    
    private BookKeepingEntry bookKeepingEntry;
    private BookKeepingEntryDetail bookKeepingEntryDetail;
    private Account accounting;
    //private AcceptButton btnTest = new AcceptButton();
    private final int PERSONA = 1;
    private final int EMPRESA = 2;
    //private BasicPanel jpFormaDePago = new BasicPanel();
    private BookkKeepingEntryPaymentWay bookkKeepingEntryPaymentWay;
    private Check check;
    
    private int error = 0;
    private final int OK = 0;
    private final int FECHA_VACIA = 1;
    private final int COMBO_TIPO_MOVIMIENTO_VACIO = 2;
    private final int COMBO_CC_VACIO = 3;
    private final int CONCEPTO_VACIO = 4;
    private final int COMBO_BENEFICIARIO_VACIO = 5;
    private final int COMBO_CUENTA_VACIO = 6;
    private final int COMBO_ACTIVIDAD_VACIO = 7;
    private final int MONTO_CUENTA_VACIO = 8;
    
    private final int NUMERO_VACIO = 9;
    private final int COMBO_CUENTA_PAGO_VACIO = 10;
    private final int MONTO_PAGO_VACIO = 11;

    public CreditNotesMgmt() {
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
	jpFormaDePago.setPreferredSize(new Dimension(10, 80));
	jpFormaDePago.setLayout(null);
	tfDate.setBounds(new Rectangle(15, 20, 90, 35));
	tfConcept.setBounds(new Rectangle(225, 60, 425, 35));
	jpNuevoAsiento.setPreferredSize(new Dimension(10, 105));
	jpNuevoAsiento.setLayout(null);
	tfFindAccount.setBounds(new Rectangle(10, 20, 110, 35));
	cbActivity.setBounds(new Rectangle(10, 60, 420, 35));
	tfAccountAmount.setBounds(new Rectangle(460, 60, 120, 35));
	cbAccounting.setBounds(new Rectangle(130, 20, 450, 35));
	cbCashMovementType.setBounds(new Rectangle(225, 20, 425, 35));
	rbtnDebit.setText(Environment.lang.getProperty("Debit"));
	rbtnDebit.setBounds(new Rectangle(10, 0, 88, 18));
	rbtnDebit.setSelected(false);
	rbtnDebit.setSize(new Dimension(88, 18));
	rbtnCredit.setText(Environment.lang.getProperty("Credit"));
	rbtnCredit.setBounds(new Rectangle(130, 0, 93, 18));
	rbtnCredit.setSize(new Dimension(93, 18));
        rbtnCredit.setSelected(true);
	tfDebitAmount.setBounds(new Rectangle(290, 10, 115, 20));
	tfCreditAmount.setBounds(new Rectangle(330, 10, 115, 20));
	tfAvailableAmount.setBounds(new Rectangle(510, 10, 115, 20));
	lblOculto1.setText(Environment.lang.getProperty("DebitAmount") + ":");
	lblOculto1.setBounds(new Rectangle(290, 5, 97, 20));
	lblOculto1.setHorizontalAlignment(SwingConstants.RIGHT);
	lblOculto2.setText(Environment.lang.getProperty("CreditAmount") + ":");
	lblOculto2.setBounds(new Rectangle(230, 5, 101, 20));
	lblOculto2.setHorizontalAlignment(SwingConstants.RIGHT);
	lblSaldo.setText(Environment.lang.getProperty("AvailableAmount") + ":");
	lblSaldo.setBounds(new Rectangle(435, 10, 65, 20));
	lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
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
        jpHeader.add(tfDate, null);
        jpHeader.add(cbCashMovementType, null);
	jpHeader.add(cbCostsCentre, null);
        jpHeader.add(tfConcept, null);
        jpHeader.add(rbtnPerson, null);
        jpHeader.add(rbtnCompany, null);
        jpHeader.add(tfSearchProvider, null);
        jpHeader.add(cbBeneficiary, null);
        
        jpNuevoAsiento.add(btnDelete, null);
        jpNuevoAsiento.add(btnFindAccounting, null);
        jpNuevoAsiento.add(btnAddBookKeepingEntry, null);
        jpNuevoAsiento.add(rbtnCredit, null);
        jpNuevoAsiento.add(rbtnDebit, null);
        jpNuevoAsiento.add(cbAccounting, null);
        jpNuevoAsiento.add(tfAccountAmount, null);
        jpNuevoAsiento.add(cbActivity, null);
        jpNuevoAsiento.add(tfFindAccount, null);
        jpDetail.add(jpNuevoAsiento, BorderLayout.NORTH);
        jpDetail.add(listBookKeepingEntryDetailPanel, BorderLayout.CENTER);
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
        jpFormaDePago.add(tfFindAccountToPay, null);
        jpFormaDePago.add(lblSaldo, null);
        jpFormaDePago.add(lblOculto2, null);
        jpFormaDePago.add(lblOculto1, null);
        jpFormaDePago.add(tfAvailableAmount, null);
        jpFormaDePago.add(tfCreditAmount, null);
        jpFormaDePago.add(tfDebitAmount, null);
        jpFormaDePago.add(tfPayformNumber, null);
        jpFormaDePago.add(btnDeletePaymentWay, null);
        jpFormaDePago.add(btnAddPaymentWay, null);
        jpFormaDePago.add(cbPaymentWay, null);
        jpFormaDePago.add(cbAccountToPay, null);
        jpFormaDePago.add(tfPaymentWayAmount, null);
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
        content.add(jpFormaDePago, BorderLayout.SOUTH);
        addButton(btnClose);
        addButton(btnSave);
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
        tfFindAccountToPay.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        loadComboAccountingToPay();
                    }
                }
            }
        );
        cbCostsCentre.setBounds(new Rectangle(15, 60, 200, 35));
        setHeaderList();
        tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
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
        lblOculto1.setVisible(false);
        lblOculto2.setVisible(false);
        lblSaldo.setVisible(true);
        ButtonGroup rbtnPaymentWay = new ButtonGroup();
        cbPaymentWay.setBounds(new Rectangle(10, 0, 175, 35));
        cbPaymentWay.getCombo().addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        if (cbPaymentWay.getSelectedValue().equals("1")) {
                            //cbCheckNumber.setEnabled(true);
                            //tfPayformNumber.setEnabled(true);
                            /** hay que hacer que funcione la llamada de abajo (loadComboChecks()) */
                            //loadComboChecks();
                        } else {
                            /*cbCheckNumber.removeAllItems();
                            cbCheckNumber.setEnabled(false);*/
                            //tfPayformNumber.setEnabled(false);
                        }
                    }
                }
            }
        );
        cbAccountToPay.setBounds(new Rectangle(130, 40, 370, 35));
        cbAccountToPay.getCombo().addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        if (cbAccountToPay.getSelectedValue().equals("1")) {
                            //cbCheckNumber.setEnabled(true);
                            //tfPayformNumber.setEnabled(true);
                            /** hay que hacer que funcione la llamada de abajo (loadComboChecks()) */
                            //loadComboChecks();
                        } else {
                            /*cbCheckNumber.removeAllItems();
                         cbCheckNumber.setEnabled(false);*/
                            //tfPayformNumber.setEnabled(false);
                        }
                    }
                }
            }
        );
        tfPayformNumber.setBounds(new Rectangle(200, 0, 85, 35));
        tfPaymentWayAmount.setBounds(new Rectangle(510, 40, 115, 35));
        tfFindAccountToPay.setBounds(new Rectangle(10, 40, 115, 35));
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
        btnAddPaymentWay.setBounds(new Rectangle(635, 55, 20, 20));
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
        jpFormaDePago.add(tfAvailableAmount, null);
	btnDelete.setEnabled(false);
	btnDeletePaymentWay.setEnabled(false);
	btnDeletePaymentWay.setVisible(false);
        tfAvailableAmount.setEnabled(false);
        loadComboAccountsCash();
        jpHeader.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("MainData")));
        jpDetail.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("NewAccountingEntry")));
        btnAddPaymentWay.setEnabled(false);
        btnAddBookKeepingEntry.setEnabled(false);
        tfDate.requestFocus();
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
        cbAccounting.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsForBookKeepingEntry", "-1," + tfFindAccount.getStringForSQL()));
        if (cbAccounting.getCombo().getItemCount() > 0) {
            btnAddBookKeepingEntry.setEnabled(true);
        } else {
            btnAddBookKeepingEntry.setEnabled(false);
        }
    }

    private void loadComboAccountingToPay() {
        cbAccountToPay.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsForBookKeepingEntry", "-1," + tfFindAccountToPay.getStringForSQL()));
        if (cbAccountToPay.getCombo().getItemCount() > 0) {
            btnAddPaymentWay.setEnabled(true);
        } else {
            btnAddPaymentWay.setEnabled(false);
        }
    }


    private void loadComboBeneficiary() {
	if (rbtnPerson.isSelected()) {
	    cbBeneficiary.loadJCombo(LibSQL.exFunction("org.getAllPersonsByFilter", tfSearchProvider.getStringForSQL()));
	} else {
	    cbBeneficiary.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", tfSearchProvider.getStringForSQL()));
	}
    }

    private void setHeaderList() {
        Vector headerList = new Vector();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Accounting"));
	headerList.addElement(Environment.lang.getProperty("Debit"));
	headerList.addElement(Environment.lang.getProperty("Credit"));
	headerList.addElement(Environment.lang.getProperty("Observations"));
	headerList.addElement("*");
	
	listBookKeepingEntryDetailPanel.setParams("accounting.getAllBookKeepingEntryDetail", "-1", headerList);
    }

    private void control() {
        if (LibSQL.getBoolean("cashflow.existInBankAccounts",""+ dataRow.get(2)))  {
            btnDelete.setEnabled(false);
        } else  {
            btnDelete.setEnabled(true);
        }
        
    }
 
    public void refresh() {
	if (bookKeepingEntry != null) {
	    updateAmount();
	    listBookKeepingEntryDetailPanel.refresh(bookKeepingEntry.getIDBookKeepingEntry());
	}
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
        bookKeepingEntry.setNoteType(1);
	if (bookKeepingEntry.saveDataByNotes() >= 0) {
	    Environment.addUnfinishedTask(this.getParentInternalFrame());
	    setEnabledPanelBeneficiary(false);
	    tfDate.setEnabled(false);
	    cbCostsCentre.setEnabled(false);
	    cbCashMovementType.setEnabled(false);
	    tfConcept.setEnabled(false);
	    bookKeepingEntry.getData();
	    return true;
	} else {
	    return false;
	}
    }

    public boolean saveDetailData() {
        
        if (controlAgregarItemDebeHaber()) {
            if (bookKeepingEntry == null) {
                saveHeaderData();
            }
            if (bookKeepingEntryDetail == null) {
                bookKeepingEntryDetail =
                        new BookKeepingEntryDetail();
            }
            bookKeepingEntryDetail.setAccounting(new Account(Integer.parseInt("" +
                                                                              cbAccounting.getSelectedValue())));
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
            switch (error) {
                case FECHA_VACIA:
                        Advisor.messageBox("El campo \"Fecha\" no puede estar vacío", "Datos requeridos");
                    break;
                 case COMBO_TIPO_MOVIMIENTO_VACIO:
                           Advisor.messageBox("El combo \"Tipo de Movimiento de fondos\" no debe estar vacío,\npor favor seleccione alguno.", "Datos requeridos");
                    break;
                case COMBO_CC_VACIO:
                        Advisor.messageBox("El combo \"Centro de Costos (C.C.)\" no debe estar vacío,\npor favor seleccione alguno.", "Datos requeridos");
                    break;
                case CONCEPTO_VACIO:
                        Advisor.messageBox("El campo \"Concepto\" no debe estar vacío", "Datos requeridos");
                    break;
                case COMBO_BENEFICIARIO_VACIO:
                        Advisor.messageBox("El combo \"Beneficiario/Sujeto\" no debe estar vacío", "Datos requeridos");
                    break;
                case COMBO_CUENTA_VACIO:
                        Advisor.messageBox("El combo \"Cuenta\" no debe estar vacío", "Datos requeridos");
                    break;
                case COMBO_ACTIVIDAD_VACIO:
                        Advisor.messageBox("El combo \"Actividad/Destino/Concepto\" no debe estar vacío", "Datos requeridos");
                    break;
                case MONTO_CUENTA_VACIO:
                        Advisor.messageBox("El campo \"Monto\" debe ser mayor que cero ($ 0.00)", "Datos requeridos");
                    break;
                default:
            }
            return false;
        }
    }
    
    private boolean controlAgregarItemDebeHaber() {
        boolean _return = false;
        error = OK;
        if (tfDate.getDate().length() == 0) {
            error = FECHA_VACIA;
        } else if (cbCashMovementType.getSelectedIndex() == -1) {
            error = COMBO_TIPO_MOVIMIENTO_VACIO;
        } else if (cbCostsCentre.getSelectedIndex() == -1) {
            error = COMBO_CC_VACIO;
        } else if (tfConcept.getString().length() == 0) {
            error = CONCEPTO_VACIO;
        } else if (cbBeneficiary.getSelectedIndex() == -1) {
            error = COMBO_BENEFICIARIO_VACIO;
        } else if (cbAccounting.getSelectedIndex() == -1) {
            error = COMBO_CUENTA_VACIO;
        } else if (cbActivity.getSelectedIndex() == -1) {
            error = COMBO_ACTIVIDAD_VACIO;
        } else if (tfAccountAmount.getAmount() <= 0) {
            error = MONTO_CUENTA_VACIO;
        } else {
            _return = true;
        }
        
        return _return;
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
	    tfAvailableAmount.setValue(bookKeepingEntry.getAvailableAmount());
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
	saveDetailData();
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
		    if (Advisor.question("Informe", "¿Desea imprimir la Nota de Ingreso?")) {
			cashMovement.retrieveData();
			String movementType = "";
                        movementType = "Nota de Ingreso";
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
	rbtnDebit.setSelected(false);
	rbtnCredit.setSelected(true);
	bookKeepingEntry = null;
	listBookKeepingEntryDetailPanel.refresh("0");
	tfDate.setEnabled(true);
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	cbCostsCentre.setEnabled(true);
	cbCashMovementType.setEnabled(true);
	tfConcept.setEnabled(true);
	setEnabledPanelBeneficiary(true);
        tfPayformNumber.setValue("");
    }

    private void cancelBookKeepingEntry() {
	if (Advisor.question("Anular Asiento", "¿Desea anular la Nota de Ingreso Nº" + (bookKeepingEntry.getNumber() * (-1)) + "?.")) {
	    bookKeepingEntry.rollBack();
	    /*for (int i = 0; i < checksVector.size(); i++) {
	        checksVector.elementAt(i).rollBack();
	    }*/
	    clearData();
	    refresh();
	    getParentInternalFrame().setIcon(true);
	}
	Environment.removeUnfinishedTask(this.getParentInternalFrame());
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	tfAvailableAmount.setBackground(Color.GREEN.brighter());
	if (bookKeepingEntry != null) {
	    cancelBookKeepingEntry();
	} else {
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

    private void loadComboAccountsCash() {
        cbPaymentWay.getCombo().addItem("Cheque","2");
        cbPaymentWay.getCombo().addItem("Boleta de Depósito","3");
    }

    private boolean savePaymentWay() {
        if (controlAgregaItemPago()) {
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
	    if (cbPaymentWay.getSelectedValue().equals("3")) {
		bookkKeepingEntryPaymentWay.setAccount(new Account(Integer.parseInt("" + cbAccountToPay.getSelectedValue())));
		bookkKeepingEntryPaymentWay.setCheck(new Check());
		bookkKeepingEntryPaymentWay.setIdPaymentType(3);
	        bookkKeepingEntryPaymentWay.setConcept("B.D. "+ tfPayformNumber.getValue());
	        bookkKeepingEntryPaymentWay.setRefNumber("B.D. "+ tfPayformNumber.getValue());
	    } else if (cbPaymentWay.getSelectedValue().equals("2")) {
		bookkKeepingEntryPaymentWay.setAccount(new Account());
		bookkKeepingEntryPaymentWay.setCheck(check);
		bookkKeepingEntryPaymentWay.setIdPaymentType(4);
		bookkKeepingEntryPaymentWay.setAccount(new Account(Integer.parseInt("" + cbAccountToPay.getSelectedValue())));
	        bookkKeepingEntryPaymentWay.setConcept("cheque Nº "+ tfPayformNumber.getValue());
	        bookkKeepingEntryPaymentWay.setRefNumber(""+ tfPayformNumber.getValue());
	        bookkKeepingEntryPaymentWay.setRefNumber(""+ tfPayformNumber.getValue());
		tfPaymentWayAmount.setValue(0.0);
                bookkKeepingEntryPaymentWay.setCheck(new Check(-1));
	    } 
            bookkKeepingEntryPaymentWay.setNoteType(1);
	    bookkKeepingEntryPaymentWay.setIdbudget(Integer.parseInt(cbCostsCentre.getSelectedValueRef().toString()));
	    bookkKeepingEntryPaymentWay.setIdcostcentre(Integer.parseInt(cbCostsCentre.getSelectedValue().toString())); 
	    if (bookkKeepingEntryPaymentWay.saveData() >= 0) {
		bookkKeepingEntryPaymentWay = null;
		refresh();
		return true;
	    } else {
		return false;
	    }
        } else {
            switch (error) {
                case FECHA_VACIA:
                        Advisor.messageBox("El campo \"Fecha\" no puede estar vacío", "Datos requeridos");
                    break;
                 case COMBO_TIPO_MOVIMIENTO_VACIO:
                           Advisor.messageBox("El combo \"Tipo de Movimiento de fondos\" no debe estar vacío,\npor favor seleccione alguno.", "Datos requeridos");
                    break;
                case COMBO_CC_VACIO:
                        Advisor.messageBox("El combo \"Centro de Costos (C.C.)\" no debe estar vacío,\npor favor seleccione alguno.", "Datos requeridos");
                    break;
                case CONCEPTO_VACIO:
                        Advisor.messageBox("El campo \"Concepto\" no debe estar vacío", "Datos requeridos");
                    break;
                case COMBO_BENEFICIARIO_VACIO:
                        Advisor.messageBox("El combo \"Beneficiario/Sujeto\" no debe estar vacío", "Datos requeridos");
                    break;
                case COMBO_CUENTA_PAGO_VACIO:
                        Advisor.messageBox("El combo \"Cuenta (Pago)\" no debe estar vacío", "Datos requeridos");
                    break;
                case NUMERO_VACIO:
                        Advisor.messageBox("El combo \"Número\" no debe estar vacío", "Datos requeridos");
                    break;
                case MONTO_PAGO_VACIO:
                        Advisor.messageBox("El campo \"Monto\" debe ser mayor que cero ($ 0.00)", "Datos requeridos");
                    break;
                default:
            }
            return false;
        }
    }

    private void btnAddPaymentWay_actionPerformed(ActionEvent e) {
	savePaymentWay();
    }
    
    private boolean controlAgregaItemPago(){
        boolean _return = false;
        error = OK;
        if (tfDate.getDate().length() == 0) {
            error = FECHA_VACIA;
        } else if (tfConcept.getString().length() <= 0) {
            error = CONCEPTO_VACIO;
        } else if (cbBeneficiary.getSelectedIndex() == -1) {
            error = COMBO_BENEFICIARIO_VACIO;
        } else if (tfPayformNumber.getString().length() <= 0) {
            error = NUMERO_VACIO;
        } else if (cbAccountToPay.getSelectedIndex() == -1) {
            error = COMBO_CUENTA_PAGO_VACIO;
        } else if (tfPaymentWayAmount.getAmount() == 0) {
            error = MONTO_PAGO_VACIO;
        
        } else {
            _return = true;
        }
        return _return;
    }

    private void btnDeletePaymentWay_actionPerformed(ActionEvent e) {
	if (bookkKeepingEntryPaymentWay != null) {
	    bookkKeepingEntryPaymentWay.delete();
	    bookkKeepingEntryPaymentWay = null;
	    //refreshAllData();
	    refresh();
	}
    }

  private void habilitarBotones() {
      if (listBookKeepingEntryDetailPanel.getTable().getRowCount() > 0) {
          btnSave.setEnabled(true);
      } else {
          btnSave.setEnabled(false);
          btnDelete.setEnabled(false);
      }
  }
}
