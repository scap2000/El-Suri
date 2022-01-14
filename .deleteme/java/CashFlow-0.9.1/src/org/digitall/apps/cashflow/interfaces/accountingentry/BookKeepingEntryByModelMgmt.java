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
 * BookKeepingEntryByModelMgmt.java
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

import java.sql.Types;

import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

import org.digitall.apps.cashflow.classes.BookKeepingEntryDetail;
import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.BookKeepingEntry;
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
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class BookKeepingEntryByModelMgmt extends BasicPrimitivePanel {

    private BasicPanel jpHeader = new BasicPanel();
    private BasicPanel jpDetail = new BasicPanel();
    private BasicPanel jpTotals = new BasicPanel();
    private BasicPanel BasicPanel4 = new BasicPanel();
    private BasicPanel content = new BasicPanel();

    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();

    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfConcept = new TFInput(DataTypes.STRING, "Concept", false);
    
    private JMoneyEntry tfDebitAmount = new JMoneyEntry();
    private JMoneyEntry tfCreditAmount = new JMoneyEntry();
    private JMoneyEntry tfAvailableAmount = new JMoneyEntry();
    
    private int[] sizeColumnList = { 318, 81, 83, 284 };
    private Vector dataRow = new Vector();
    private GridPanel listBookKeepingEntryDetailPanel = new GridPanel(30, sizeColumnList, "", dataRow){

	    public void calculate() {
	        
		if (table.getSelectedRow() > -1) {
		    
		    /*System.out.println("[selected (debe),5]--> " + Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 2)));
		    System.out.println("[selected (haber),6]--> " + Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 3)));
		    System.out.println("[selected,7]--> " + Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 7)));
		    System.out.println("[selected,9]--> " + Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 9)));
		    System.out.println("[selected,10]--> " + Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 10)));*/
		    //double valor = (Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 3).toString()) - Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 4))) * Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 2));
		    //double iva = Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 8));
		    /*double debe = Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 2));
		    table.setValueAt(String.valueOf(debe), table.getSelectedRow(), 3);*/
		    /*if (Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 3)) >= Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 4))) {
			table.setValueAt(String.valueOf(valor), table.getSelectedRow(), 7);
			valor = Double.parseDouble("" + table.getValueAt(table.getSelectedRow(), 7)) * iva / 100;
			table.setValueAt(String.valueOf(valor), table.getSelectedRow(), 9);
			valor = Double.parseDouble("" + table.getValueAt(table.getSelectedRow(), 7)) + Double.parseDouble("" + table.getValueAt(table.getSelectedRow(), 9));
			table.setValueAt(String.valueOf(valor), table.getSelectedRow(), 10);
		    }*/
		}
	    }

	}
    ;
    private Vector headerList = new Vector();
    
    private CBInput cbModels = new CBInput(0, "Models", false);
    private CBInput cbAccountingByModel = new CBInput(0, "Accounts2", false);
    //private CBInput cbCashMovementType = new CBInput(CachedCombo.CASHMOVEMENTTYPE, "CashMovementType");
    private CBInput cbCashMovementType = new CBInput(0, "CashMovementType2");
    private CBInput cbCostsCentre = new CBInput(0, "CostsCentre", false);
    private CBInput cbActivity = new CBInput(CachedCombo.ACTIVITY, "Activities", true);
    
    private BasicLabel BasicLabel1 = new BasicLabel();
    private BasicLabel BasicLabel2 = new BasicLabel();
    private BasicLabel BasicLabel3 = new BasicLabel();
    
    private AssignButton btnAddBookKeepingEntry = new AssignButton(true);
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    
    private BookKeepingEntry bookKeepingEntry;
    private BookKeepingEntryDetail bookKeepingEntryDetail;
    private Account accounting;
    private boolean valido = false;
    
    private BasicRadioButton rbtnPerson = new BasicRadioButton();
    private BasicRadioButton rbtnCompany = new BasicRadioButton();
    private TFInput tfSearchProvider = new TFInput(DataTypes.STRING,"SearchButton",false);
    private CBInput cbBeneficiary = new CBInput(0, "BeneficiarySubject", false);
    
    private final int PERSONA = 1;
    private final int EMPRESA = 2;

    public BookKeepingEntryByModelMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(662, 544));
	this.setPreferredSize(new Dimension(590, 500));
        this.add(content, null);
	jpHeader.setPreferredSize(new Dimension(10, 105));
	jpHeader.setLayout(null);
	jpDetail.setLayout(borderLayout1);
	jpTotals.setPreferredSize(new Dimension(10, 30));
	jpTotals.setLayout(null);
	tfDate.setBounds(new Rectangle(15, 25, 90, 35));
	tfConcept.setBounds(new Rectangle(225, 65, 425, 35));
	BasicPanel4.setPreferredSize(new Dimension(10, 130));
	BasicPanel4.setLayout(null);
	cbActivity.setBounds(new Rectangle(10, 85, 420, 35));
	cbActivity.autoSize();
	cbModels.setBounds(new Rectangle(10, 0, 310, 35));
        cbAccountingByModel.setBounds(new Rectangle(335, 0, 305, 35));
	cbCashMovementType.setBounds(new Rectangle(170, 25, 480, 35));
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
	btnAddBookKeepingEntry.setBounds(new Rectangle(595, 100, 20, 20));
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
        BasicPanel4.add(btnAddBookKeepingEntry, null);
        BasicPanel4.add(cbModels, null);
        BasicPanel4.add(cbAccountingByModel, null);
        BasicPanel4.add(cbActivity, null);
        BasicPanel4.add(rbtnCompany, null);
        BasicPanel4.add(rbtnPerson, null);
        BasicPanel4.add(tfSearchProvider, null);
        BasicPanel4.add(cbBeneficiary, null);
        jpDetail.add(BasicPanel4, BorderLayout.NORTH);
	jpDetail.add(listBookKeepingEntryDetailPanel, BorderLayout.CENTER);
        listBookKeepingEntryDetailPanel.setCellEditor(Types.DOUBLE, 1);
        listBookKeepingEntryDetailPanel.setCellEditor(Types.DOUBLE, 2);
	jpTotals.add(BasicLabel3, null);
	jpTotals.add(BasicLabel2, null);
	jpTotals.add(BasicLabel1, null);
	jpTotals.add(tfAvailableAmount, null);
	jpTotals.add(tfCreditAmount, null);
	jpTotals.add(tfDebitAmount, null);
	content.setLayout(borderLayout2);
        /*BasicPanel4.add(tfRefNumber, null);
	tfRefNumber.setBounds(new Rectangle(10, 85, 110, 35));*/
	content.add(jpHeader, BorderLayout.NORTH);
	content.add(jpDetail, BorderLayout.CENTER);
	content.add(jpTotals, BorderLayout.SOUTH);
	addButton(btnClose);
	addButton(btnSave);
	cbModels.autoSize();
        cbAccountingByModel.autoSize();
	cbCashMovementType.autoSize();
        cbCashMovementType.addItemListener(new ItemListener() {

                                 public void itemStateChanged(ItemEvent evt) {
                                     if (evt.getStateChange() == ItemEvent.SELECTED) {
                                            
                                             String param = ""+ cbCashMovementType.getSelectedValue();
                                             cbModels.loadJCombo(LibSQL.exFunction("accounting.getAllModels", param));
                                             if (cbModels.getSelectedIndex() != -1) {
                                                 cbAccountingByModel.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsByModel", cbModels.getSelectedValue().toString()));
                                             }
                                     }
                                 }

                             }
         );
	cbModels.addItemListener(new ItemListener() {

	                         public void itemStateChanged(ItemEvent evt) {
	                             if (evt.getStateChange() == ItemEvent.SELECTED) {
	                                 if (cbModels.getSelectedIndex() != -1) {
	                                     cbAccountingByModel.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsByModel", cbModels.getSelectedValue().toString()));
	                                 }
	                             }
	                         }

	                     }
	 );
        listBookKeepingEntryDetailPanel.getTable().addKeyListener(new KeyAdapter() {

                                         public void keyReleased(KeyEvent e) {
                                            if (listBookKeepingEntryDetailPanel.getTable().getSelectedRow() != -1) {
                                             double Qty = Double.parseDouble(listBookKeepingEntryDetailPanel.getTable().getValueAt(listBookKeepingEntryDetailPanel.getTable().getSelectedRow(), 2).toString());
                                             //double bonusQty = Double.parseDouble(listBookKeepingEntryDetailPanel.getTable().getValueAt(listBookKeepingEntryDetailPanel.getTable().getSelectedRow(), 3).toString());
                                             //listBookKeepingEntryDetailPanel.getTable().setValueAt(Qty, listBookKeepingEntryDetailPanel.getTable().getSelectedRow(), 3);
                                             }
                                             calculateTotal();
                                         }

                                     }
        );
	cbCostsCentre.setBounds(new Rectangle(15, 65, 200, 35));
	cbCostsCentre.autoSize();
	setHeaderList();
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfDebitAmount.setEnabled(false);
	tfCreditAmount.setEnabled(false);
	tfAvailableAmount.setEnabled(false);
	btnSave.setToolTipText("Grabar datos");
	btnClose.setToolTipText("Cancelar asiento");
	btnAddBookKeepingEntry.setToolTipText("Agregar un ítem al asiento");
	
        setPanelsEnabled(true);
        rbtnPerson.setText("Persona");
        rbtnCompany.setText("Empresa");
        rbtnPerson.setBounds(new Rectangle(10, 55, 88, 18));
        rbtnCompany.setBounds(new Rectangle(120, 55, 88, 18));
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
        tfSearchProvider.getTextField().addKeyListener(new KeyAdapter() {

                                                 public void keyTyped(KeyEvent e) {
                                                     if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                         loadComboBeneficiary();
                                                     }
                                                 }

                                             }
        );
       tfSearchProvider.setBounds(new Rectangle(210, 40, 110, 35));
       ButtonGroup rbtnProvider = new ButtonGroup();
       rbtnProvider.add(rbtnPerson);
       rbtnProvider.add(rbtnCompany);
       rbtnPerson.setSelected(true);
       cbBeneficiary.setBounds(new Rectangle(335, 40, 305, 35));
       cbBeneficiary.autoSize();
       loadComboCashMovementTypes();
       loadComboCostsCentre();
       loadComboAccounting();
       jpHeader.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("MainData")));
       jpDetail.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("NewAccountingEntry")));
    }

    private void loadComboCashMovementTypes(){
        cbCashMovementType.getCombo().loadJCombo("cashflow.getcashmovementtypes","");
    }

    private void setPanelsEnabled(boolean _valor){
        tfDate.setEnabled(_valor);
        cbCostsCentre.setEnabled(_valor);
        cbCashMovementType.setEnabled(_valor);
        tfConcept.setEnabled(_valor);
        
        cbModels.setEnabled(_valor);
        cbAccountingByModel.setEnabled(_valor);
        //tfRefNumber.setEnabled(_valor);
        cbActivity.setEnabled(_valor);
        btnAddBookKeepingEntry.setEnabled(_valor);
    }

    private void calculateTotal() {
        listBookKeepingEntryDetailPanel.selectAllItems(true);
        //listBookKeepingEntryDetailPanel.calculate();
        /*Vector<Vector> values = listBookKeepingEntryDetailPanel.getSelectedsVector();
        if (values.size() > 0) {
            double creditTotalAmount = 0;
            double debitTotalAmount = 0;
            valido = true;
            for (int i = 0; i < values.size(); i++) {
                debitTotalAmount += Double.parseDouble("" + (values.elementAt(i)).elementAt(5));
                creditTotalAmount += Double.parseDouble("" + (values.elementAt(i)).elementAt(6));
                if (Double.parseDouble("" + (values.elementAt(i)).elementAt(5)) == 0 && Double.parseDouble("" + (values.elementAt(i)).elementAt(6)) == 0) {
                    valido = false;
                }
                
            }
            tfDebitAmount.setValue(debitTotalAmount);
            tfCreditAmount.setValue(creditTotalAmount);
            tfAvailableAmount.setValue(debitTotalAmount - creditTotalAmount);
            
            if (tfAvailableAmount.getAmount() == 0 && valido) {
                tfAvailableAmount.setBackground(Color.GREEN.brighter());
            } else {
                tfAvailableAmount.setBackground(new Color(200, 50, 50));
            }
        } else {
             tfDebitAmount.setValue(0.0);
             tfCreditAmount.setValue(0.0);
             tfAvailableAmount.setValue(0.0);
        }*/
        updateAmount();
        loadComboModels();
    }

    private void loadComboCostsCentre() {
	cbCostsCentre.loadJCombo("cashflow.getAllCostsCentresByBudgets", "");
	cbCostsCentre.getCombo().updateUI();
    }
    
    private void loadComboModels() {
        cbCostsCentre.loadJCombo("cashflow.getAllCostsCentresByBudgets", "");
        cbCostsCentre.getCombo().updateUI();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar datos\". Recuerde que el asiento debe estar balanceado");
    }

    public void loadComboAccounting() {
	String param = ""+ cbCashMovementType.getSelectedValue();
	cbModels.loadJCombo(LibSQL.exFunction("accounting.getAllModels", param));
        if (cbModels.getSelectedIndex() != -1) {
            cbAccountingByModel.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsByModel", cbModels.getSelectedValue().toString()));
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
        listBookKeepingEntryDetailPanel.selectAllItems(true);
    }

    public void refresh() {
	if (bookKeepingEntry != null) {
	    String params = "" + bookKeepingEntry.getIDBookKeepingEntry();
	    listBookKeepingEntryDetailPanel.refresh(params);
	}
    }

    public boolean saveHeaderData() {
	if (bookKeepingEntry == null) {
	    bookKeepingEntry = new BookKeepingEntry();
	}
	bookKeepingEntry.setConcept(tfConcept.getString());
	bookKeepingEntry.setDate(Proced.setFormatDate(tfDate.getString(), false));
        if (Integer.parseInt(cbCashMovementType.getSelectedValue().toString()) == 1 )  {
            bookKeepingEntry.setNoteType(1);
        } else  {
            bookKeepingEntry.setNoteType(2);
        }
        if (bookKeepingEntry.saveDataByNotes() >= 0) {
	    bookKeepingEntry.getData();
	    return true;
	} else {
	    return false;
	}
    }

    public boolean saveDetailData() {
        if (tfConcept.getString().length() > 0 && (cbBeneficiary.getSelectedIndex() != -1)) {
            if (bookKeepingEntry == null) {
                saveHeaderData();
            }
            if (cbActivity.getSelectedIndex() >= 0) {
                if (bookKeepingEntryDetail == null) {
                    bookKeepingEntryDetail = new BookKeepingEntryDetail();
                }
                
                //bookKeepingEntryDetail.setAccounting(new Account(Integer.parseInt("" + cbAccounting.getSelectedValue())));
                bookKeepingEntryDetail.setAccounting(new Account());
                bookKeepingEntryDetail.setBookKeepingEntry(bookKeepingEntry);
                bookKeepingEntryDetail.setCredtAmount(0);
                bookKeepingEntryDetail.setDebitAmount(0);
                bookKeepingEntryDetail.setObservations(cbActivity.getSelectedItem().toString());
                bookKeepingEntryDetail.setVoucher(new Voucher());
                //bookKeepingEntryDetail.setRefNumber(tfRefNumber.getString());
                bookKeepingEntryDetail.setIdmodel(Integer.parseInt(cbModels.getSelectedValue().toString()));
                bookKeepingEntryDetail.setIdcostcentre(Integer.parseInt(cbCostsCentre.getSelectedValue().toString()));
                bookKeepingEntryDetail.setIdbudget(Integer.parseInt(cbCostsCentre.getSelectedValueRef().toString()));
                if (bookKeepingEntryDetail.saveDataByModel() >= 0) {
                    refresh();
                    updateAmount();
                    setEnabledPanelBeneficiary(false);
                    bookKeepingEntryDetail = null;
                    getParentInternalFrame().setNeedSaving(true);
                    //tfRefNumber.setValue("");
                    setPanelsEnabled(false);
                    return true;
                } else {
                    return false;
                }
            } else {
                Advisor.messageBox("Actividad/Destino/Concepto requerido", "Datos requeridos");
                return false;
            }
        } else {
            Advisor.messageBox("Los campos Concepto y Beneficiario/Sujeto no deben estar vacios", "Datos requeridos");
            return false;
        }
    }



    private void updateAmount() {
        //bookKeepingEntry.getData();
         listBookKeepingEntryDetailPanel.selectAllItems(true);
         Vector<Vector> values = listBookKeepingEntryDetailPanel.getSelectedsVector();
         if (values.size() > 0) {
             double creditTotalAmount = 0;
             double debitTotalAmount = 0;
             valido = true;
             for (int i = 0; i < values.size(); i++) {
                 debitTotalAmount += Double.parseDouble("" + (values.elementAt(i)).elementAt(5));
                 creditTotalAmount += Double.parseDouble("" + (values.elementAt(i)).elementAt(6));
                 if (Double.parseDouble("" + (values.elementAt(i)).elementAt(5)) == 0 && Double.parseDouble("" + (values.elementAt(i)).elementAt(6)) == 0) {
                     valido = false;
                 }
             }
             tfDebitAmount.setValue(debitTotalAmount);
             tfCreditAmount.setValue(creditTotalAmount);
             tfAvailableAmount.setValue(debitTotalAmount - creditTotalAmount);
             if (tfAvailableAmount.getAmount() == 0 && valido) {
                 tfAvailableAmount.setBackground(Color.GREEN.brighter());
             } else {
                 tfAvailableAmount.setBackground(new Color(200, 50, 50));
             }
         } else {
             tfDebitAmount.setValue(0.0);
             tfCreditAmount.setValue(0.0);
             tfAvailableAmount.setValue(0.0);
         }
        /*if (bookKeepingEntry.getAvailableAmount() == 0) {
            tfAvailableAmount.setBackground(Color.GREEN.brighter());
        } else {
            tfAvailableAmount.setBackground(new Color(200, 50, 50));
        }*/
        
    }

    private void btnAddBookKeepingEntry_actionPerformed(ActionEvent e) {
	if (cbModels.getSelectedIndex() != -1 &&  cbAccountingByModel.getSelectedIndex() != -1 ) {
	     saveDetailData();
	     Environment.addUnfinishedTask(this.getParentInternalFrame());
	 } else {
	     Advisor.messageBox("Debe seleccionar un Modelo que tengo Cuentas asignadas", "Error");
	 }
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	saveData();
    }

    public boolean saveData() {
	boolean returns = false;
        updateAmount();
        if (tfDebitAmount.getAmount() == tfCreditAmount.getAmount() && tfCreditAmount.getAmount() > 0 && tfAvailableAmount.getAmount() == 0 && valido) {
            Vector<Vector> values = listBookKeepingEntryDetailPanel.getSelectedsVector();
            int id = -1;
            double debitAmount = 0.0;
            double creditAmount = 0.0;
            String params = "";
            int resul = -1;
            for (int i = 0; i < values.size(); i++) {
                id = Integer.parseInt("" + (values.elementAt(i)).elementAt(0));
                debitAmount = Double.parseDouble("" + (values.elementAt(i)).elementAt(5));
                creditAmount = Double.parseDouble("" + (values.elementAt(i)).elementAt(6));
                params = id +","+ debitAmount +","+ creditAmount;
                resul = LibSQL.getInt("accounting.setBookKeepingEntryDetail",""+ params);
            }
            bookKeepingEntry.updateAmount();
            bookKeepingEntry.approve();
            //acá se tiene que grabar el movimiento de dinero
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
            if (cashMovement.addData() > 0) {
                returns = true;
		Environment.removeUnfinishedTask(this.getParentInternalFrame());
                if (Advisor.question("Informe", "¿Desea imprimir el asiento del movimiento?")) {
                    cashMovement.retrieveData();
                    String movementType = "";
                    //if (cashMovement.getCashMovementType().getSign() == 1) {
                    if (Integer.parseInt(cbCashMovementType.getSelectedValue().toString())  == 1 ){
                        movementType = "Nota de Ingreso";
                    } else {
                        movementType = "Nota de Egreso";
                    }
                    BasicReport report = new BasicReport(CashMovementsList.class.getResource("xml/selectedCashMovementReport.xml"));
                    report.addTableModel("accounting.xmlGetBookKeepingEntry", "" + cashMovement.getIdbookkeepingentry());
                    report.addTableModel("accounting.xmlGetBookKeepingEntryDetail", "" + cashMovement.getIdbookkeepingentry());
                    report.setProperty("movementtype", "" + movementType);
                    report.setProperty("totalAmount", tfCreditAmount.getAmount());
                    report.setProperty("textamount",Format.NumberToText.numberToText(tfCreditAmount.getAmount()) + ".-"); 
                    report.doReport(); 
                }
                setEnabledPanelBeneficiary(true);
                clearData();
                getParentInternalFrame().closeWindow();
            } else {
                Advisor.messageBox("Ocurrió un error al grabar el Movimiento de Fondos", "Error");
            }
        } else {
            Advisor.messageBox("Los montos \"Debe\" y \"Haber\" deben ser superiores a cero e iguales entre sí", "Aviso");
        }
	return returns;
    }

    private void clearData() {
	tfConcept.setValue("");
	tfAvailableAmount.setValue(0.0);
	tfCreditAmount.setValue(0.0);
	tfDebitAmount.setValue(0.0);
	bookKeepingEntry = null;
	listBookKeepingEntryDetailPanel.refresh("0");
        tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
        setPanelsEnabled(true);
        setEnabledPanelBeneficiary(true);
    }
    public boolean cancelSaveData() {
        int number = bookKeepingEntry.getNumber();
        if (number < 0 )  {
            number = number * (-1);
        }
        if (Advisor.question("Anular Asiento", "¿Desea anular el Asiento Nº " + number + " ?.")) {
            bookKeepingEntry.rollBack();
            clearData();
            Environment.removeUnfinishedTask(this.getParentInternalFrame());
            return true;
        } else {
            return false;
        }
    }
    private void cancelBookKeepingEntry() {
        int number = bookKeepingEntry.getNumber();
        String noteType = "Desea anular la Nota de Egreso Nº "+ number +" ?.";
        if (number < 0 )  {
            number = number * (-1);
            noteType = "Desea anular la Nota de Ingreso Nº "+ number +" ?.";
        }
	if (Advisor.question("Anular Asiento", noteType)) {
	    bookKeepingEntry.rollBack();
	    clearData();
	    Environment.removeUnfinishedTask(this.getParentInternalFrame());
            tfAvailableAmount.setBackground(Color.GREEN.brighter());
            getParentInternalFrame().closeWindow();
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	if (bookKeepingEntry != null) {
	    cancelBookKeepingEntry();
	} else {
	    tfAvailableAmount.setBackground(Color.GREEN.brighter());
	    getParentInternalFrame().closeWindow();
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
    
    private void rbtnPerson_actionPerformed(ActionEvent e) {
        tfSearchProvider.setValue("");
        cbBeneficiary.removeAllItems();
    }
    
    private void rbtnCompany_actionPerformed(ActionEvent e) { 
        tfSearchProvider.setValue("");
        cbBeneficiary.removeAllItems();
    }
    
    private void setEnabledPanelBeneficiary(boolean _valor){
        rbtnPerson.setEnabled(_valor);
        rbtnCompany.setEnabled(_valor);
        tfSearchProvider.setEnabled(_valor);
        cbBeneficiary.setEnabled(_valor);
        if (_valor) {
            tfSearchProvider.setValue("");
            cbBeneficiary.removeAllItems();
        }
        
    }

    private void loadComboBeneficiary() {
        String param = "'" + tfSearchProvider.getString() + "'";
        if (rbtnPerson.isSelected()) {
            cbBeneficiary.loadJCombo(LibSQL.exFunction("org.getAllPersonsByFilter", param));
        } else {
            cbBeneficiary.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", param));
        }
    }
}
