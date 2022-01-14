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
 * CashCountMgmt.java
 *
 * */
package org.digitall.apps.taxes.interfases.cashier;

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

import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.BookKeepingEntry;
import org.digitall.common.cashflow.classes.CashMovement;
import org.digitall.common.cashflow.classes.CashMovementType;
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

public class CashCountMgmt extends BasicPrimitivePanel {

    private BasicPanel jpHeader = new BasicPanel();
    private BasicPanel jpDetail = new BasicPanel();
    private BasicPanel payFormDataPanel = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BasicPanel BasicPanel4 = new BasicPanel();
    private TFInput tfFecha = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfResponsable = new TFInput(DataTypes.STRING, "Responsable", false);
    private TFInput tfFindAccount = new TFInput(DataTypes.STRING, "FindAccounting", false);
    private TFInput tfAccountAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private int[] sizeColumnList = { 318, 81, 83, 284 };
    private Vector dataRow = new Vector();
    private GridPanel listBookKeepingEntryDetailPanel = new GridPanel(30, sizeColumnList, "", dataRow);
    private Vector headerList = new Vector();
    private CBInput cbAccounting = new CBInput(0, "Accounting", false);

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
    private Account accounting;

    private CBInput cbActivity = new CBInput(CachedCombo.ACTIVITY, "Activities", true);
    private BorderLayout borderLayout2 = new BorderLayout();
    private DeleteButton btnDelete = new DeleteButton();
    private TFInput tfNroCaja = new TFInput(DataTypes.STRING, "SearchButton", false);
    //private AcceptButton btnTest = new AcceptButton();
    private final int PERSONA = 1;
    private final int EMPRESA = 2;
    //private BasicPanel payFormDataPanel = new BasicPanel();
    private TFInput tfNroArqueo = new TFInput(DataTypes.STRING, "Number", false);
    private DeleteButton btnDeletePaymentWay = new DeleteButton();
    private UnAssignButton btnAddPaymentWay = new UnAssignButton(true);

    private TFInput tfPaymentWayAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private TFInput tfFindAccountToPay = new TFInput(DataTypes.STRING,"FindAccounting",false);
    private TFInput tfCajero = new TFInput(DataTypes.STRING, "Cajero", false);

    private CBInput cbAccountToPay = new CBInput(0, "Accounting", false);


    public CashCountMgmt() {
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
	payFormDataPanel.setPreferredSize(new Dimension(10, 80));
	payFormDataPanel.setLayout(null);
	tfFecha.setBounds(new Rectangle(170, 20, 90, 35));
	tfResponsable.setBounds(new Rectangle(355, 20, 295, 35));
	BasicPanel4.setPreferredSize(new Dimension(10, 105));
	BasicPanel4.setLayout(null);
	tfFindAccount.setBounds(new Rectangle(10, 20, 110, 35));
	cbActivity.setBounds(new Rectangle(10, 60, 420, 35));
	cbActivity.autoSize();
	tfAccountAmount.setBounds(new Rectangle(460, 60, 120, 35));
	cbAccounting.setBounds(new Rectangle(130, 20, 450, 35));
	tfDebitAmount.setBounds(new Rectangle(105, 5, 115, 20));
	tfCreditAmount.setBounds(new Rectangle(335, 5, 115, 20));
	tfAvailableAmount.setBounds(new Rectangle(510, 10, 115, 20));
	BasicLabel1.setText(Environment.lang.getProperty("DebitAmount") + ":");
	BasicLabel1.setBounds(new Rectangle(5, 5, 97, 20));
	BasicLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
	BasicLabel2.setText(Environment.lang.getProperty("CreditAmount") + ":");
	BasicLabel2.setBounds(new Rectangle(230, 5, 101, 20));
	BasicLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
	BasicLabel3.setText(Environment.lang.getProperty("AvailableAmount") + ":");
	BasicLabel3.setBounds(new Rectangle(435, 10, 65, 20));
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
	jpHeader.add(tfCajero, null);
	jpHeader.add(tfResponsable, null);
	jpHeader.add(tfFecha, null);
	jpHeader.add(tfNroCaja, null);
	jpHeader.add(tfNroArqueo, null);
	BasicPanel4.add(btnDelete, null);
	BasicPanel4.add(btnFindAccounting, null);
	BasicPanel4.add(btnAddBookKeepingEntry, null);
	BasicPanel4.add(cbAccounting, null);
	BasicPanel4.add(tfAccountAmount, null);
	BasicPanel4.add(cbActivity, null);
	BasicPanel4.add(tfFindAccount, null);
	jpDetail.add(BasicPanel4, BorderLayout.NORTH);
	jpDetail.add(listBookKeepingEntryDetailPanel, BorderLayout.CENTER);
	payFormDataPanel.add(tfFindAccountToPay, null);
	payFormDataPanel.add(BasicLabel3, null);
	payFormDataPanel.add(BasicLabel2, null);
	payFormDataPanel.add(BasicLabel1, null);
	payFormDataPanel.add(tfAvailableAmount, null);
	payFormDataPanel.add(tfCreditAmount, null);
	payFormDataPanel.add(tfDebitAmount, null);
	payFormDataPanel.add(btnDeletePaymentWay, null);
        payFormDataPanel.add(btnAddPaymentWay, null);
	payFormDataPanel.add(cbAccountToPay, null);
        payFormDataPanel.add(tfPaymentWayAmount, null);
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
	ButtonGroup rbtn = new ButtonGroup();
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
	setHeaderList();
        tfFecha.setValue(Proced.setFormatDate(Environment.currentDate, true));
        tfDebitAmount.setEnabled(false);
        tfCreditAmount.setEnabled(false);
        //tfAvailableAmount.setEnabled(false);
        btnSave.setToolTipText("Grabar datos");
        btnClose.setToolTipText("Cancelar asiento");
        btnDelete.setToolTipText("Borrar un ítem del asiento");
        tfNroCaja.setBounds(new Rectangle(15, 60, 85, 35));
	btnAddBookKeepingEntry.setToolTipText("Agregar un ítem al asiento");
        ButtonGroup rbtnProvider = new ButtonGroup();
	tfNroCaja.getTextField().addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                        
                    }
                }
            }
        );
        tfDebitAmount.setVisible(false);
        tfCreditAmount.setVisible(false);
        BasicLabel1.setVisible(false);
        BasicLabel2.setVisible(false);
        BasicLabel3.setVisible(true);
        ButtonGroup rbtnPaymentWay = new ButtonGroup();
	cbAccountToPay.setBounds(new Rectangle(130, 40, 370, 35));
	tfCajero.setBounds(new Rectangle(170, 60, 245, 35));
	cbAccountToPay.autoSize();
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
        tfNroArqueo.setBounds(new Rectangle(15, 20, 85, 35));
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
        payFormDataPanel.add(tfAvailableAmount, null);
	btnDelete.setEnabled(false);
	btnDeletePaymentWay.setEnabled(false);
	btnDeletePaymentWay.setVisible(false);
        tfAvailableAmount.setEnabled(false);
        jpHeader.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("Arqueo")));
        jpDetail.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("Detalle de Arqueo")));
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar datos\". Recuerde que el asiento debe estar balanceado");
    }

    private void loadComboAccounting() {
	String param = "-1,'" + tfFindAccount.getString() + "'";
	cbAccounting.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsForBookKeepingEntry", param));
    }

    private void loadComboAccountingToPay() {
        String param = "-1,'" + tfFindAccountToPay.getString() + "'";
        cbAccountToPay.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsForBookKeepingEntry", param));
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
        if (LibSQL.getBoolean("cashflow.existInBankAccounts",""+ dataRow.get(2)))  {
            btnDelete.setEnabled(false);
        } else  {
            btnDelete.setEnabled(true);
        }
        
    }
 
    public void refresh() {
	if (bookKeepingEntry != null) {
	    updateAmount();
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
	bookKeepingEntry.setConcept(tfResponsable.getString());
	bookKeepingEntry.setDate(Proced.setFormatDate(tfFecha.getString(), false));
        bookKeepingEntry.setNoteType(1);
	if (bookKeepingEntry.saveDataByNotes() >= 0) {
	    Environment.addUnfinishedTask(this.getParentInternalFrame());
	    setEnabledPanelBeneficiary(false);
	    tfFecha.setEnabled(false);
	    tfResponsable.setEnabled(false);
	    bookKeepingEntry.getData();
	    return true;
	} else {
	    return false;
	}
    }

    public boolean saveDetailData() {
	if (tfResponsable.getString().length() > 0) {
	    if (bookKeepingEntry == null) {
		saveHeaderData();
	    }
	    if (cbActivity.getSelectedIndex() >= 0) {
		/*if (bookKeepingEntryDetail == null) {
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
		bookKeepingEntryDetail.setVoucher(new Vouchers());
	        bookKeepingEntryDetail.setIdbudget(Integer.parseInt(cbCostsCentre.getSelectedValueRef().toString()));
	        bookKeepingEntryDetail.setIdcostcentre(Integer.parseInt(cbCostsCentre.getSelectedValue().toString()));
		*/
		/*if (bookKeepingEntryDetail.saveData() >= 0) {
		    refresh();
		    bookKeepingEntryDetail = null;
		    tfFindAccount.setValue("");
		    return true;
		    
		} else {
		    return false;
		}*/
	    } else {
		Advisor.messageBox("Actividad/Destino requerido", "Datos requeridos");
		return false;
	    }
	} else {
	    Advisor.messageBox("Los campos Concepto y Beneficiario/Sujeto no deben estar vacios", "Datos requeridos");
	    return false;
	}
	/** AGREGADO POR MI, BORRAR */
	return false;
    }

    private void setEnabledPanelBeneficiary(boolean _valor) {
	tfNroCaja.setEnabled(_valor);
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
		cashMovement.setVoucher(new Voucher());
		cashMovement.setCashMovementType(cashMovementType);
		cashMovement.setDate(Proced.setFormatDate(tfFecha.getString(), false));
		cashMovement.setAmount(tfCreditAmount.getAmount());
		cashMovement.setDescription(tfResponsable.getString());

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
        tfNroCaja.setValue("");
	tfResponsable.setValue("");
	tfAccountAmount.setValue(0.0);
	tfAvailableAmount.setValue(0.0);
	tfCreditAmount.setValue(0.0);
	tfDebitAmount.setValue(0.0);
        tfPaymentWayAmount.setValue(0.0);
	bookKeepingEntry = null;
	listBookKeepingEntryDetailPanel.refresh("0");
	tfFecha.setEnabled(true);
	tfFecha.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfResponsable.setEnabled(true);
	setEnabledPanelBeneficiary(true);
        tfNroArqueo.setValue("");
    }

    private void cancelBookKeepingEntry() {
	if (Advisor.question("Anular Asiento", "¿Desea anular la Nota de Ingreso Nº" + (bookKeepingEntry.getNumber() * (-1)) + " ?.")) {
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

    private void btnDelete_actionPerformed(ActionEvent e) {
	if (dataRow.size() != 0) {
	    /*BookKeepingEntryDetail bookKeepingEntryDetail = new BookKeepingEntryDetail();
	    bookKeepingEntryDetail.setIDBookKeepingEntryDetail(Integer.parseInt(dataRow.elementAt(0).toString()));
	    if (bookKeepingEntryDetail.deleteData() > 0) {
		refresh();
	    }*/
	} else {
	    Advisor.messageBox("Debe seleccionar el ítem que desea borrar ", "Error");
	}
    }

    private boolean savePaymentWay() {
	if (tfResponsable.getString().length() > 0) {
	    if (bookKeepingEntry == null) {
		saveHeaderData();
	    }
	    double amount = tfPaymentWayAmount.getAmount();
	    // Esta linea la agregue momentaneamente
	    //if (amount <= tfAmountToPay.getAmount()) { /**Cuidado con el valor de netAmount!!!!!!! */
	    /*
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
	    */
	    //  }
	} else {
	    Advisor.messageBox("Los campos Concepto y Beneficiario/Sujeto no deben estar vacios", "Datos requeridos");
	    return false;
	}
	/** AGREGADO POR MI, BORRAR*/
	return false;
    }

    private void btnAddPaymentWay_actionPerformed(ActionEvent e) {
	
    }

    private void btnDeletePaymentWay_actionPerformed(ActionEvent e) {
	/*if (bookkKeepingEntryPaymentWay != null) {
	    bookkKeepingEntryPaymentWay.delete();
	    bookkKeepingEntryPaymentWay = null;
	    //refreshAllData();
	    refresh();
	}*/
    }
}
