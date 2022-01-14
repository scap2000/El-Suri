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
 * BookKeepingEntryMgmt.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.accountingentry;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

import org.digitall.common.cashflow.classes.BookKeepingEntry;
import org.digitall.apps.cashflow.classes.BookKeepingEntryDetail;
import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.CashMovement;
import org.digitall.common.cashflow.classes.CashMovementType;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.cashflow.interfaces.accounting.AccountsMain;
import org.digitall.common.cashflow.interfaces.cashmovement.CashMovementsList;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class BookKeepingEntryMgmt extends BasicPrimitivePanel {

    private BasicPanel jpHeader = new BasicPanel(Environment.lang.getProperty("MainData"));
    private BasicPanel jpDetail = new BasicPanel(Environment.lang.getProperty("NewAccountingEntry"));
    private BasicPanel jpTotals = new BasicPanel();
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfConcept = new TFInput(DataTypes.STRING, "Concept", false);
    private TFInput tfFindAccount = new TFInput(DataTypes.STRING, "FindAccounting", false);
    private TFInput tfAccountAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    //private TFInput tfRefNumber = new TFInput(DataTypes.STRING, "VoucherNumber", false);
    //private TFInput tfObservations = new TFInput(DataTypes.STRING,"Observations",false);
    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicPanel BasicPanel4 = new BasicPanel();
    private int[] sizeColumnList = { 318, 81, 83, 284 };
    private Vector dataRow = new Vector();
    private GridPanel listBookKeepingEntryDetailPanel = new GridPanel(30, sizeColumnList, "", dataRow);
    private Vector headerList = new Vector();
    private CBInput cbAccounting = new CBInput(0, "Accounting", false);
    private CBInput cbCashMovementType = new CBInput(CachedCombo.CASHMOVEMENTTYPE, "CashMovementType");
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
    private TFInput tfSearchProvider = new TFInput(DataTypes.STRING,"SearchButton",false);
    private CBInput cbBeneficiary = new CBInput(0, "BeneficiarySubject", false);
    //private AcceptButton btnTest = new AcceptButton();
    private final int PERSONA = 1;
    private final int EMPRESA = 2;
    public BookKeepingEntryMgmt() {
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
	jpHeader.setPreferredSize(new Dimension(10, 105));
	jpHeader.setLayout(null);
	jpDetail.setLayout(borderLayout1);
	jpTotals.setPreferredSize(new Dimension(10, 30));
	jpTotals.setLayout(null);
	tfDate.setBounds(new Rectangle(15, 20, 90, 35));
	tfConcept.setBounds(new Rectangle(225, 60, 425, 35));
	BasicPanel4.setPreferredSize(new Dimension(10, 155));
	BasicPanel4.setLayout(null);
	tfFindAccount.setBounds(new Rectangle(10, 30, 110, 35));
	cbActivity.setBounds(new Rectangle(10, 110, 420, 35));
	cbActivity.autoSize();
	tfAccountAmount.setBounds(new Rectangle(525, 30, 120, 35));
	cbAccounting.setBounds(new Rectangle(130, 30, 355, 35));
	cbCashMovementType.setBounds(new Rectangle(170, 20, 480, 35));
	rbtnDebit.setText(Environment.lang.getProperty("Debit"));
	rbtnDebit.setBounds(new Rectangle(10, 5, 90, 20));
	rbtnDebit.setSelected(true);
	rbtnDebit.setSize(new Dimension(88, 18));
	rbtnCredit.setText(Environment.lang.getProperty("Credit"));
	rbtnCredit.setBounds(new Rectangle(130, 5, 93, 18));
	rbtnCredit.setSize(new Dimension(93, 18));
	tfDebitAmount.setBounds(new Rectangle(105, 5, 115, 20));
	tfCreditAmount.setBounds(new Rectangle(335, 5, 115, 20));
	tfAvailableAmount.setBounds(new Rectangle(535, 5, 115, 20));
	BasicLabel1.setText(Environment.lang.getProperty("DebitAmount") + ":");
	BasicLabel1.setBounds(new Rectangle(5, 5, 97, 20));
	BasicLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
	BasicLabel2.setText(Environment.lang.getProperty("CreditAmount") + ":");
	BasicLabel2.setBounds(new Rectangle(230, 5, 101, 20));
	BasicLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
	BasicLabel3.setText(Environment.lang.getProperty("AvailableAmount") + ":");
	BasicLabel3.setBounds(new Rectangle(465, 5, 65, 20));
	BasicLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
	btnAddBookKeepingEntry.setBounds(new Rectangle(585, 125, 20, 20));
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
        jpHeader.add(tfConcept, null);
        jpHeader.add(cbCostsCentre, null);
        jpHeader.add(tfDate, null);
        jpHeader.add(cbCashMovementType, null);
        BasicPanel4.add(tfSearchProvider, null);
        BasicPanel4.add(rbtnCompany, null);
        BasicPanel4.add(rbtnPerson, null);
        BasicPanel4.add(btnDelete, null);
        //BasicPanel4.add(tfRefNumber, null);
        BasicPanel4.add(btnFindAccounting, null);
        BasicPanel4.add(btnAddBookKeepingEntry, null);
        BasicPanel4.add(rbtnCredit, null);
	BasicPanel4.add(rbtnDebit, null);
        BasicPanel4.add(cbAccounting, null);
        BasicPanel4.add(tfAccountAmount, null);
        BasicPanel4.add(cbActivity, null);
        BasicPanel4.add(tfFindAccount, null);
        BasicPanel4.add(cbBeneficiary, null);
        jpDetail.add(BasicPanel4, BorderLayout.NORTH);
	jpDetail.add(listBookKeepingEntryDetailPanel, BorderLayout.CENTER);
	jpTotals.add(BasicLabel3, null);
	jpTotals.add(BasicLabel2, null);
	jpTotals.add(BasicLabel1, null);
	jpTotals.add(tfAvailableAmount, null);
	jpTotals.add(tfCreditAmount, null);
	jpTotals.add(tfDebitAmount, null);
	content.setLayout(borderLayout2);
	btnFindAccounting.setBounds(new Rectangle(495, 45, 20, 20));
	btnFindAccounting.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     btnFindAccounting_actionPerformed(e);
					 }

				     }
	);
	//tfRefNumber.setBounds(new Rectangle(10, 110, 110, 35));
	btnDelete.setBounds(new Rectangle(620, 125, 20, 20));
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
        /*BasicPanel4.add(btnTest, null);
        btnTest.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnTest_actionPerformed(e);
                                 }

                             }
        );
         btnTest.setBounds(new Rectangle(490, 120, 45, 25));
        */
	content.add(jpHeader, BorderLayout.NORTH);
	content.add(jpDetail, BorderLayout.CENTER);
	content.add(jpTotals, BorderLayout.SOUTH);
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
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfDebitAmount.setEnabled(false);
	tfCreditAmount.setEnabled(false);
	tfAvailableAmount.setEnabled(false);
	btnSave.setToolTipText("Grabar datos");
	btnClose.setToolTipText("Cancelar asiento");
	btnDelete.setToolTipText("Borrar un ítem del asiento");
        tfSearchProvider.setBounds(new Rectangle(220, 70, 110, 35));
        cbBeneficiary.setBounds(new Rectangle(340, 70, 305, 35));
        rbtnPerson.setText("Persona");
        
        rbtnPerson.setBounds(new Rectangle(10, 80, 88, 18));
        rbtnCompany.setText("Empresa");
        rbtnCompany.setBounds(new Rectangle(130, 80, 88, 18));
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
        if (rbtnPerson.isSelected())  {
            cbBeneficiary.loadJCombo(LibSQL.exFunction("org.getAllPersonsByFilter", param));    
        } else  {
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

    public void refresh() {
	if (bookKeepingEntry != null) {
	    String params = "" + bookKeepingEntry.getIDBookKeepingEntry();
	    listBookKeepingEntryDetailPanel.refresh(params);
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
	bookKeepingEntry.setDate(Proced.setFormatDate(tfDate.getString(), false));
	if (bookKeepingEntry.saveData() >= 0) {
	    bookKeepingEntry.getData();
	    return true;
	} else {
	    return false;
	}
    }

    public boolean saveDetailData() {
	if (tfConcept.getString().length() > 0  && (cbBeneficiary.getSelectedIndex() != -1)) {
	    if (bookKeepingEntry == null) {
		saveHeaderData();
	    }
	    if (cbActivity.getSelectedIndex() >= 0) {
		if (bookKeepingEntryDetail == null) {
		    bookKeepingEntryDetail = new BookKeepingEntryDetail();
		}
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
		//bookKeepingEntryDetail.setRefNumber(tfRefNumber.getString());
		if (bookKeepingEntryDetail.saveData() >= 0) {
		    updateAmount();
		    refresh();
		    bookKeepingEntryDetail = null;
		    tfFindAccount.setValue("");
		    //tfRefNumber.setValue("");
                    setEnabledPanelBeneficiary(false);
		    cbAccounting.removeAllItems();
		    tfDate.setEnabled(false);
		    cbCostsCentre.setEnabled(false);
		    cbCashMovementType.setEnabled(false);
		    tfConcept.setEnabled(false);
		    return true;
		} else {
		    return false;
		}
	    } else {
		Advisor.messageBox("Actividad/Destino requerido", "Datos requeridos");
		return false;
	    }
	} else {
	    Advisor.messageBox("Los campos Concepto y Beneficiario/Sujeto no deben estar vacios", "Datos requeridos");
	    return false;
	}
    }

    private void setEnabledPanelBeneficiary(boolean _valor){
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
	if (tfAccountAmount.getAmount() > 0 && cbAccounting.getSelectedIndex() != -1) {
	    saveDetailData();
	    Environment.addUnfinishedTask(this.getParentInternalFrame());
	} else {
	    Advisor.messageBox("El campo Monto debe ser mayor que cero", "Error");
	}
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	saveData();
    }

    public boolean saveData() {
	boolean returns = false;
	if ( (tfDebitAmount.getAmount() == tfCreditAmount.getAmount()) && (tfCreditAmount.getAmount() > 0) && (tfAvailableAmount.getAmount() == 0) ) {
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
		cashMovement.setDate(Proced.setFormatDate(tfDate.getString(), false));
		cashMovement.setAmount(tfCreditAmount.getAmount());
		cashMovement.setDescription(tfConcept.getString());
                if (rbtnPerson.isSelected())  {
                    cashMovement.setBenefittype(PERSONA);    
                } else  {
                    cashMovement.setBenefittype(EMPRESA);
                }
                cashMovement.setIdbenefit(Integer.parseInt(cbBeneficiary.getSelectedValue().toString()));
		//bookKeepingEntry
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
		        report.setProperty("textamount",Format.NumberToText.numberToText(tfCreditAmount.getAmount()) + ".-");
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
	tfConcept.setValue("");
	tfAccountAmount.setValue(0.0);
	tfAvailableAmount.setValue(0.0);
	tfCreditAmount.setValue(0.0);
	tfDebitAmount.setValue(0.0);
	rbtnDebit.setSelected(true);
	rbtnCredit.setSelected(false);
	bookKeepingEntry = null;
	listBookKeepingEntryDetailPanel.refresh("0");
	tfDate.setEnabled(true);
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	cbCostsCentre.setEnabled(true);
	cbCashMovementType.setEnabled(true);
	tfConcept.setEnabled(true);
        setEnabledPanelBeneficiary(true);
    }

    private void cancelBookKeepingEntry() {
	if (Advisor.question("Anular Asiento", "¿Desea anular el Asiento Nº" + bookKeepingEntry.getNumber() + " ?.")) {
	    bookKeepingEntry.rollBack();
	    clearData();
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
	//System.out.println("dataRow.size(): " + dataRow.size());
	if (dataRow.size() != 0) {
	    BookKeepingEntryDetail bookKeepingEntryDetail = new BookKeepingEntryDetail();
	    bookKeepingEntryDetail.setIDBookKeepingEntryDetail(Integer.parseInt(dataRow.elementAt(0).toString()));
	    if (bookKeepingEntryDetail.deleteData() > 0) {
		refresh();
		updateAmount();
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar el ítem que desea borrar ", "Error");
	}
    }

    private void btnReportTest_actionPerformed(ActionEvent e) {

    }

   /* private void btnTest_actionPerformed(ActionEvent e) {
        //tfCreditAmount.setValue(0);
        BasicReport report = new BasicReport(CashMovementsList.class.getResource("xml/selectedCashMovementReport.xml"));
        report.addTableModel("accounting.xmlGetBookKeepingEntry", "35");
        report.addTableModel("accounting.xmlGetBookKeepingEntryDetail", "35");
        report.setProperty("movementtype", "Nota de Egreso");
        report.setProperty("textamount",Format.NumberToText.numberToText(1000.50) + ".-");
        report.doReport();
    }
*/

}
