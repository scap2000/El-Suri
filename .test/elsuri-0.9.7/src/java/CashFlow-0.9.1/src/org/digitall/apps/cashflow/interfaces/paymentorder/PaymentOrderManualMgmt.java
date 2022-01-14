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
 * PaymentOrderManualMgmt.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.paymentorder;

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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.math.BigDecimal;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.digitall.apps.cashflow.classes.PaymentOrderDeduction;
import org.digitall.apps.cashflow.classes.PaymentOrderWay;
import org.digitall.common.cashflow.classes.Account;
import org.digitall.common.cashflow.classes.Check;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.PaymentOrder;
import org.digitall.common.cashflow.classes.PaymentOrderDetail;
import org.digitall.common.cashflow.classes.VoucherType;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.cashflow.interfaces.account.BankAccountsList;
import org.digitall.common.cashflow.interfaces.accounting.AccountsMain;
import org.digitall.common.cashflow.interfaces.voucher.VouchersList;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.common.resourcescontrol.interfaces.providers.ProvidersMain;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintSaveButton;
import org.digitall.lib.components.buttons.RefreshGridButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class PaymentOrderManualMgmt extends BasicPrimitivePanel {

    private BasicTabbedPane tabbedPane = new BasicTabbedPane();

    private BasicPanel dataPanel = new BasicPanel();
    private BasicPanel jpImputations = new BasicPanel();
    private BasicPanel jpDeductions = new BasicPanel();
    private BasicPanel jpPayments = new BasicPanel();
    private BasicPanel itemDeductionPanel = new BasicPanel();
    private BasicPanel itemDetailPanel = new BasicPanel();
    private BasicPanel detailPartialAmountPanel = new BasicPanel();
    private BasicPanel deductionPartialAmountPanel = new BasicPanel();
    private BasicPanel totalPanel = new BasicPanel();
    private BasicPanel container = new BasicPanel();
    private BasicPanel jPanel4 = new BasicPanel();
    private BasicPanel jpAccountEntry = new BasicPanel();
    private BasicPanel payFormDataPanel = new BasicPanel();
    
    private BasicLabel jLabel2 = new BasicLabel();
    private BasicLabel lblMontoALiquidar1 = new BasicLabel();
    private BasicLabel lblSubtotal = new BasicLabel();
    private BasicLabel jLabel3 = new BasicLabel();
    private BasicLabel jLabel4 = new BasicLabel();
    private BasicLabel jLabel5 = new BasicLabel();
    private BasicLabel jLabel6 = new BasicLabel();
    private BasicLabel jLabel7 = new BasicLabel();
    
    private int[] sizeColumnList = { 192, 97 , 107 , 140 , 97 };
    private Vector dataRowDetail = new Vector();
    private GridPanel listDetailPanel = new GridPanel(5000, sizeColumnList, "", dataRowDetail){
	public void calculate(){
	    if (rbtnFacturas.isSelected()){
		calcularSubTotal();
	    }
	}
    };
    private Vector headerListDetail = new Vector();
    
    private int [] sizeColumnListConcept = {192, 97, 107, 140, 97};
    private Vector dataRowDetailConcept = new Vector();
    private GridPanel listDetailPanelConcept = new GridPanel(5000, sizeColumnListConcept, "", dataRowDetailConcept){
	public void calculate(){
	    if (rbtnConcepto.isSelected()){
	        calcularMontoConceptos();
	    }
	}
	public void finishLoading(){
	    btnDeleteDetail.setEnabled(false);
	}
    };
    private Vector headerListDetailConcept = new Vector();
    
    private int[] sizeColumnListDeduction = { 542, 91 };
    private Vector dataRowDeduction = new Vector();
    private GridPanel listDeductionPanel = new GridPanel(5000, sizeColumnListDeduction, "", dataRowDeduction);
    private Vector headerListDeduction = new Vector();
    
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    private BorderLayout borderLayout3 = new BorderLayout();
    private BorderLayout borderLayout4 = new BorderLayout();
    
    private JMoneyEntry tfDetailPartialAmount = new JMoneyEntry();
    private JMoneyEntry tfDeductionPartialAmount = new JMoneyEntry();
    private JMoneyEntry tfSubtotal = new JMoneyEntry();
    private JMoneyEntry tfNetAmount = new JMoneyEntry();
    private JMoneyEntry tfDebitAmount = new JMoneyEntry();
    private JMoneyEntry tfCreditAmount = new JMoneyEntry();
    private JMoneyEntry tfAvailableAmount = new JMoneyEntry();
    
    private AssignButton btnAddDetail = new AssignButton(true);
    private AssignButton btnAddDeduction = new AssignButton(true);
    private FindButton btnFindProvider = new FindButton();
    private PrintSaveButton btnPrintSave = new PrintSaveButton();
    private RefreshGridButton btnRecoverPaymentOrder = new RefreshGridButton();
    private CloseButton btnClose = new CloseButton();
    private AssignButton btnAddPaymentWay = new AssignButton(true);
    private AddButton btnAddProvider = new AddButton();
    private DeleteButton btnDeleteDetail = new DeleteButton();
    private DeleteButton btnDeleteDeduction = new DeleteButton();
    private DeleteButton btnDeletePaymentWay = new DeleteButton();
    
    private CBInput cbProvider = new CBInput(0, "Provider", false);
    private CBInput cbAccountDeduction = new CBInput(0, "Deductions", false);
    private CBInput cbIva = new CBInput(0, "VATp", false);
    private CBInput cbDeduction = new CBInput(0, "DeductionPercent", false);
     private CBInput cbPaymentWay = new CBInput(0, "Accounting", false);
     private CBInput cbAccount = new CBInput(0, "Accounting", false);
    private CBInput cbCheckNumber = new CBInput(0, "CheckNumber", false);
     
    private TFInput tfFindProvider = new TFInput(DataTypes.STRING, "FindProvider", false);
    private TFInput tfObservations = new TFInput(DataTypes.STRING, "Observations", true);
    private TFInput tfFindAccountDetail = new TFInput(DataTypes.STRING, "SearchButton", false);
    private TFInput tfDetailAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private TFInput tfFindAccountDeduction = new TFInput(DataTypes.STRING, "SearchButton", false);
    private TFInput tfDeductionAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private TFInput tfCUIT = new TFInput(DataTypes.STRING, "Number", false);
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfAmountOwed = new TFInput(DataTypes.MONEY, "Monto Adeudado", false);
    private TFInput tfConcept = new TFInput(DataTypes.STRING, "Concept", false);
    private TFInput tfPaymentWayAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private TFInput tfPercentage = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfCheckDate = new TFInput(DataTypes.DATE, "ExpirationDate", false);

    private PaymentOrder paymentOrder;
    private PaymentOrderDetail paymentOrderDetail;
    private PaymentOrderDeduction paymentOrderDeduction;
    private PaymentOrderWay paymentOrderWay;

    private BasicRadioButton rbtnAccount = new BasicRadioButton();
    private BasicRadioButton rbtnVouchers = new BasicRadioButton();
    private BasicRadioButton rbtnCash = new BasicRadioButton();
    private BasicRadioButton rbtnCheck = new BasicRadioButton();
    
    private Vector dataRowPaymentWay = new Vector();
    private int[] sizeColumnListPaymentWay = {532 , 100};
    private GridPanel listPaymentWayPanel = new GridPanel(30, sizeColumnListPaymentWay, Environment.lang.getProperty("PaymentWay"), dataRowPaymentWay) {
	public void calculate() {
	    calculateNetAmount();
	}
    };
    
    private Vector headerListPaymentWay = new Vector();
    private BorderLayout borderLayout5 = new BorderLayout();
    private int[] sizeColumnListBookKeepingEntry = { 244, 199, 96, 96 };
    private Vector dataRowBookKeepingEntry = new Vector();
    private GridPanel listBookKeepingEntryPanel = new GridPanel(50000, sizeColumnListBookKeepingEntry, "", dataRowBookKeepingEntry);
    private Vector headerListBookKeepingEntry = new Vector();

    private double netAmount;
    private double amountToPay = 0;

    private JMoneyEntry tfAmountToPay = new JMoneyEntry();
    private Account account;
    private Voucher voucher;
    private Check check;
    private int selectAccountType = -1;
    private final int ACCOUNTDETAIL = 1;
    private final int ACCOUNTDEDUCTION = 2;
    private final int ACCOUNTCASH = 3;
    //private final int ACCOUNTACCOUNT = 4;
    private final int ACCOUNTDETAIL_NEWVOUCHER = 5;

    private Vector<Check> checksVector = new Vector<Check>();
    private int idBudget = -1;
    
    private BasicRadioButton rbtnConcepto = new BasicRadioButton();
    private BasicRadioButton rbtnFacturas = new BasicRadioButton();
    private BasicRadioButton rbtnMonto = new BasicRadioButton();
    private ButtonGroup grupo = new ButtonGroup();
    private AcceptButton btnSaveHeader = new AcceptButton();
    
    private Vector facturaSeleccionada = new Vector();



    public PaymentOrderManualMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(684, 600));
	this.setPreferredSize(new Dimension(590, 500));
	container.setLayout(borderLayout4);
	tfNetAmount.setBounds(new Rectangle(100, 10, 115, 20));
	jLabel3.setText(Environment.lang.getProperty("NetAmount") + ":");
	jLabel3.setBounds(new Rectangle(5, 10, 92, 20));
	jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
	jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel3.setSize(new Dimension(92, 20));
	jpAccountEntry.setLayout(borderLayout5);
	dataPanel.setLayout(null);
	dataPanel.setPreferredSize(new Dimension(1, 150));
	jpImputations.setLayout(borderLayout1);
	jpDeductions.setLayout(borderLayout2);
	jpPayments.setLayout(borderLayout3);
	jpPayments.setPreferredSize(new Dimension(440, 1));
	detailPartialAmountPanel.setLayout(null);
	detailPartialAmountPanel.setPreferredSize(new Dimension(1, 50));
	detailPartialAmountPanel.setSize(new Dimension(679, 50));
	itemDetailPanel.setLayout(null);
	itemDetailPanel.setPreferredSize(new Dimension(1, 50));
	itemDetailPanel.setBounds(new Rectangle(0, 0, 679, 90));
	itemDetailPanel.setSize(new Dimension(679, 90));
	deductionPartialAmountPanel.setLayout(null);
	deductionPartialAmountPanel.setPreferredSize(new Dimension(1, 30));
	itemDeductionPanel.setLayout(null);
	itemDeductionPanel.setPreferredSize(new Dimension(1, 55));
	tfDetailPartialAmount.setBounds(new Rectangle(555, 15, 115, 20));
	lblMontoALiquidar1.setText("($) Monto a liquidar:");
	lblMontoALiquidar1.setBounds(new Rectangle(425, 15, 120, 20));
	lblMontoALiquidar1.setHorizontalAlignment(SwingConstants.RIGHT);
	lblMontoALiquidar1.setPreferredSize(new Dimension(120, 20));
	btnAddDetail.setBounds(new Rectangle(615, 20, 20, 20));
	btnAddDetail.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnAddDetail_actionPerformed(e);
				    }

				}
	);
	btnAddDeduction.setBounds(new Rectangle(630, 25, 20, 20));
	btnAddDeduction.setToolTipText("Asignar");
	btnAddDeduction.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnAddDeduction_actionPerformed(e);
				       }

				   }
	);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnPrintSave.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnPrintSave_actionPerformed(e);
				    }

				}
	);
	jLabel2.setText(Environment.lang.getProperty("PartialAmount") + ":");
	jLabel2.setBounds(new Rectangle(480, 5, 84, 20));
	jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel2.setSize(new Dimension(84, 20));
	tfDeductionPartialAmount.setBounds(new Rectangle(570, 5, 100, 20));
	tfFindProvider.setBounds(new Rectangle(20, 65, 120, 35));
	cbProvider.setBounds(new Rectangle(150, 65, 330, 35));
	btnFindProvider.setBounds(new Rectangle(590, 80, 20, 20));
	btnFindProvider.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnFindProvider_actionPerformed(e);
				       }

				   }
	);
	tfObservations.setBounds(new Rectangle(20, 105, 435, 35));
	tfObservations.setSize(new Dimension(475, 35));
	tfFindAccountDetail.setBounds(new Rectangle(5, 50, 95, 35));
	tfDetailAmount.setEditable(false);
	btnAddDetail.setEnabled(false);
	tfDetailAmount.setBounds(new Rectangle(455, 5, 145, 35));
	tfFindAccountDeduction.setBounds(new Rectangle(5, 10, 90, 35));
	cbAccountDeduction.setBounds(new Rectangle(100, 10, 265, 35));
        cbIva.setBounds(new Rectangle(370, 10, 60, 35));
        cbDeduction.setBounds(new Rectangle(435, 10, 60, 35));
	tfDeductionAmount.setBounds(new Rectangle(500, 10, 125, 35));
	tfCUIT.setBounds(new Rectangle(520, 65, 150, 35));
	tfDate.setBounds(new Rectangle(20, 25, 100, 35));
	totalPanel.setLayout(null);
	totalPanel.setPreferredSize(new Dimension(590, 40));
	rbtnAccount.setVisible(false);
	rbtnAccount.setEnabled(false);
	rbtnVouchers.setVisible(false);	
	rbtnVouchers.setEnabled(false);
	rbtnVouchers.setSelected(true);
	rbtnAccount.setText(Environment.lang.getProperty("Concepts"));
	rbtnAccount.setBounds(new Rectangle(200, 0, 100, 26));
	rbtnVouchers.setText(Environment.lang.getProperty("Invoices"));
	rbtnVouchers.setBounds(new Rectangle(365, 0, 85, 26));
	rbtnVouchers.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					rbtnVouchers_actionPerformed(e);
				    }

				}
	);
        detailPartialAmountPanel.add(lblSubtotal, null);
	detailPartialAmountPanel.add(tfSubtotal, null);
	detailPartialAmountPanel.add(lblMontoALiquidar1, null);
	detailPartialAmountPanel.add(tfDetailPartialAmount, null);
	itemDetailPanel.add(tfConcept, null);
	itemDetailPanel.add(btnDeleteDetail, null);
	itemDetailPanel.add(tfDetailAmount, null);
	itemDetailPanel.add(btnAddDetail, null);
	itemDetailPanel.add(rbtnVouchers, null);
	itemDetailPanel.add(rbtnAccount, null);
        itemDeductionPanel.add(btnDeleteDeduction, null);
        itemDeductionPanel.add(tfPercentage, null);
        itemDeductionPanel.add(tfDeductionAmount, null);
        itemDeductionPanel.add(cbAccountDeduction, null);
        itemDeductionPanel.add(tfFindAccountDeduction, null);
        itemDeductionPanel.add(btnAddDeduction, null);
        itemDeductionPanel.add(cbIva, null);
        itemDeductionPanel.add(cbDeduction, null);
        jpImputations.add(itemDetailPanel, BorderLayout.NORTH);
	jpImputations.add(detailPartialAmountPanel, BorderLayout.SOUTH);
	jpImputations.add(listDetailPanel, BorderLayout.CENTER);
	jpImputations.add(listDetailPanelConcept, BorderLayout.CENTER);
	deductionPartialAmountPanel.add(tfDeductionPartialAmount, null);
	deductionPartialAmountPanel.add(jLabel2, null);
	jpDeductions.add(itemDeductionPanel, BorderLayout.NORTH);
	jpDeductions.add(deductionPartialAmountPanel, BorderLayout.SOUTH);
	jpDeductions.add(listDeductionPanel, BorderLayout.CENTER);
	tabbedPane.addTab(Environment.lang.getProperty("DebitCharges"), jpImputations);
	tabbedPane.addTab(Environment.lang.getProperty("Deductions"), jpDeductions);
	tabbedPane.addTab(Environment.lang.getProperty("PaymentWay"), jpPayments);
	jPanel4.add(tfAvailableAmount, null);
	jPanel4.add(tfCreditAmount, null);
	jPanel4.add(tfDebitAmount, null);
	jPanel4.add(jLabel6, null);
	jPanel4.add(jLabel4, null);
	jPanel4.add(jLabel5, null);
	jpAccountEntry.add(jPanel4, BorderLayout.SOUTH);
	jpAccountEntry.add(listBookKeepingEntryPanel, BorderLayout.CENTER);
	tabbedPane.addTab(Environment.lang.getProperty("AccountingEntry"), jpAccountEntry);
	jpPayments.add(listPaymentWayPanel, BorderLayout.CENTER);
	jpPayments.add(totalPanel, BorderLayout.SOUTH);
	payFormDataPanel.add(cbCheckNumber, null);
	payFormDataPanel.add(tfCheckDate, null);
	payFormDataPanel.add(btnDeletePaymentWay, null);
	payFormDataPanel.add(btnAddPaymentWay, null);
	payFormDataPanel.add(cbPaymentWay, null);
	payFormDataPanel.add(tfPaymentWayAmount, null);
	payFormDataPanel.add(rbtnCheck, null);
	payFormDataPanel.add(rbtnCash, null);
	jpPayments.add(payFormDataPanel, BorderLayout.NORTH);
	dataPanel.add(tfAmountOwed, null);
	dataPanel.add(tfDate, null);
	dataPanel.add(tfCUIT, null);
	dataPanel.add(cbProvider, null);
	dataPanel.add(tfFindProvider, null);
	dataPanel.add(tfObservations, null);
	dataPanel.add(btnSaveHeader, null);
	dataPanel.add(rbtnConcepto, null);
	dataPanel.add(rbtnFacturas, null);
	dataPanel.add(rbtnMonto, null);
	totalPanel.add(tfAmountToPay, null);
	totalPanel.add(jLabel7, null);
	totalPanel.add(jLabel3, null);
	totalPanel.add(tfNetAmount, null);
	container.add(tabbedPane, BorderLayout.CENTER);
	container.add(dataPanel, BorderLayout.NORTH);
	addButton(btnClose);
	addButton(btnPrintSave);
	//addButton(btnRecoverPaymentOrder);
	this.add(container, null);
	payFormDataPanel.setLayout(null);
	payFormDataPanel.setPreferredSize(new Dimension(1, 70));
	rbtnCash.setText(Environment.lang.getProperty("Cash"));
	rbtnCash.setBounds(new Rectangle(10, 5, 81, 18));
	rbtnCash.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    rbtnCash_actionPerformed(e);
				}

			    }
	);
	rbtnCheck.setText(Environment.lang.getProperty("Check"));
	rbtnCheck.setBounds(new Rectangle(299, 5, 81, 18));
	rbtnCheck.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     rbtnCheck_actionPerformed(e);
				 }

			     }
	);
	
	tfPaymentWayAmount.setBounds(new Rectangle(490, 30, 130, 35));
	cbPaymentWay.setBounds(new Rectangle(10, 30, 250, 35));
	cbAccount.setBounds(new Rectangle(105, 50, 355, 35));
	btnAddPaymentWay.setBounds(new Rectangle(625, 45, 20, 20));
	btnAddPaymentWay.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnAddPaymentWay_actionPerformed(e);
					}

				    }
	);
	jPanel4.setBounds(new Rectangle(120, 10, 65, 20));
	jPanel4.setPreferredSize(new Dimension(10, 40));
	jPanel4.setLayout(null);
	jLabel4.setText(Environment.lang.getProperty("DebitAmount") + ":");
	jLabel5.setText(Environment.lang.getProperty("CreditAmount") + ":");
	jLabel4.setBounds(new Rectangle(0, 10, 97, 20));
	jLabel5.setBounds(new Rectangle(240, 10, 97, 20));
	jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel6.setText(Environment.lang.getProperty("AvailableAmount") + ":");
	jLabel6.setBounds(new Rectangle(490, 10, 65, 20));
	jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel6.setSize(new Dimension(65, 20));
	tfDebitAmount.setBounds(new Rectangle(100, 10, 115, 20));
	tfCreditAmount.setBounds(new Rectangle(340, 10, 115, 20));
	tfAvailableAmount.setBounds(new Rectangle(555, 10, 115, 20));
	jLabel7.setText(Environment.lang.getProperty("AmountToPay") + ":");
	jLabel7.setBounds(new Rectangle(440, 10, 112, 20));
	jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
	tfAmountToPay.setBounds(new Rectangle(555, 10, 115, 20));
	tfAmountToPay.setValue(0.0);
	cbProvider.autoSize();
	cbAccountDeduction.autoSize();
        cbIva.autoSize();
        cbDeduction.autoSize();
	cbPaymentWay.autoSize();
	cbAccount.autoSize();
	tfCUIT.setEnabled(false);
	tfCUIT.setSize(new Dimension(150, 35));
	tfDeductionPartialAmount.setEnabled(false);
	tfDetailPartialAmount.setEnabled(false);
	tfNetAmount.setEnabled(false);
	tfNetAmount.setValue(0.0);
	tfNetAmount.setSize(new Dimension(115, 20));
	tfAvailableAmount.setEnabled(false);
	tfAmountToPay.setEnabled(false);
	tfAmountToPay.setSize(new Dimension(115, 20));
	tfPercentage.setBounds(new Rectangle(300, 5, 40, 15));
	btnAddProvider.setBounds(new Rectangle(585, 80, 20, 20));
	btnAddProvider.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnAddProvider_actionPerformed(e);
				      }

				  }
	);
	btnDeleteDetail.setBounds(new Rectangle(650, 20, 20, 20));
	btnDeleteDetail.setEnabled(false);
	btnDeleteDetail.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnDeleteDetail_actionPerformed(e);
				       }

				   }
	);
	btnDeleteDeduction.setBounds(new Rectangle(655, 25, 20, 20));
	btnDeleteDeduction.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      btnDeleteDeduction_actionPerformed(e);
					  }

				      }
	);
	btnDeletePaymentWay.setBounds(new Rectangle(650, 45, 20, 20));
	btnDeletePaymentWay.addActionListener(new ActionListener() {

					   public void actionPerformed(ActionEvent e) {
					       btnDeletePaymentWay_actionPerformed(e);
					   }

				       }
	);
	tfCheckDate.setBounds(new Rectangle(380, 30, 100, 35));
	cbCheckNumber.setBounds(new Rectangle(270, 30, 100, 35));
	rbtnConcepto.setBounds(new Rectangle(150, 35, 125, 25));
	rbtnFacturas.setBounds(new Rectangle(290, 35, 190, 25));
	rbtnMonto.setBounds(new Rectangle(495, 35, 170, 25));
	tfConcept.setBounds(new Rectangle(15, 5, 395, 35));
	tfSubtotal.setBounds(new Rectangle(300, 15, 115, 20));
	lblSubtotal.setText("($) Sub total:");
	lblSubtotal.setBounds(new Rectangle(215, 15, 85, 20));
	tfAmountOwed.setBounds(new Rectangle(520, 105, 150, 35));
	tfAvailableAmount.setValue(0.0);
	tfDebitAmount.setEnabled(false);
	tfDebitAmount.setValue(0.0);
	tfCreditAmount.setEnabled(false);
	tfCreditAmount.setValue(0.0);
	rbtnAccount.setSelected(true);
	rbtnAccount.setSize(new Dimension(100, 26));
	rbtnCash.setSelected(true);
	tfDeductionPartialAmount.setValue(0.0);
	tfDetailPartialAmount.setValue(0.0);
	tfDetailPartialAmount.setSize(new Dimension(115, 20));
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	rbtnAccount.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       rbtnAccount_actionPerformed(e);
				   }

			       }
	);
	tfFindProvider.getTextField().addKeyListener(new KeyAdapter() {

						  public void keyTyped(KeyEvent e) {
						      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							  loadComboProvider();
						          loadAmountOwed();
						      }
						  }

					      }
	);
	tfObservations.getTextField().addKeyListener(new KeyAdapter() {

						  public void keyTyped(KeyEvent e) {
						      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							  saveHeader();
						      }
						  }

					      }
	);
	tfFindAccountDeduction.getTextField().addKeyListener(new KeyAdapter() {

							  public void keyTyped(KeyEvent e) {
							      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
								  loadComboAccountDeduction();
                                                                  calcDeduction();
							      }
							  }

						      }
	);
	cbProvider.getCombo().addItemListener(new ItemListener() {

					   public void itemStateChanged(ItemEvent evt) {
					       if (evt.getStateChange() == ItemEvent.SELECTED) {
						   tfCUIT.setValue(cbProvider.getSelectedValueRef().toString().split("#")[0]);
						   if (rbtnConcepto.isSelected()) {
							loadConcepts();
						   } else {
							loadVouchersNotPaid();
						   }
					           loadAmountOwed();
					       }
					   }

				       }
	);
	cbPaymentWay.getCombo().addItemListener(new ItemListener() {

					     public void itemStateChanged(ItemEvent evt) {
						 if (evt.getStateChange() == ItemEvent.SELECTED) {
						     if (rbtnCheck.isSelected()) {
							 //tfPaymentWayAmount.setText("" + cbPaymentWay.getSelectedValueRef());
							 //tfCheckNumber.setEnabled(true);
							 cbCheckNumber.setEnabled(true);
							 tfCheckDate.setEnabled(true);
							 loadComboChecks();
							 //tfCheckNumber.setValue();
						     } else {
							 //tfCheckNumber.setEnabled(false);
							 cbCheckNumber.removeAllItems();
							 cbCheckNumber.setEnabled(false);
							 tfCheckDate.setEnabled(false);
						     }
						     btnDeletePaymentWay.setEnabled(false);
						 }
					     }

					 }
	);
	
	cbAccount.getCombo().addItemListener(new ItemListener() {

					  public void itemStateChanged(ItemEvent evt) {
					      if (evt.getStateChange() == ItemEvent.ITEM_STATE_CHANGED) {
						  //btnAddDetail.setEnabled(tfDetailAmount.getAmount() > 0 && cbAccount.getSelectedIndex() != -1);
					      }
					  }

				      }
	);
	tfDetailAmount.getTextField().addKeyListener(new KeyAdapter() {

						  public void keyTyped(KeyEvent e) {
						      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							  agregarMontoConcepto();
						      }
						  }

					      }
	);
        tfConcept.getTextField().addKeyListener(new KeyAdapter() {

                                                  public void keyTyped(KeyEvent e) {
                                                      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                          agregarMontoConcepto();
                                                      }
                                                  }

                                              }
        );
	tfDeductionAmount.getTextField().addKeyListener(new KeyAdapter() {

						     public void keyTyped(KeyEvent e) {
							 if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							     saveDeduction();
							 }
						     }

						 }
	);
	tfPaymentWayAmount.getTextField().addKeyListener(new KeyAdapter() {

						      public void keyTyped(KeyEvent e) {
							  if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
								paymentWayControl();
							      //savePaymentWay();
							  }
						      }

						  }
	);
        cbIva.getCombo().addItemListener(new ItemListener() {

                                           public void itemStateChanged(ItemEvent evt) {
                                               if (evt.getStateChange() == ItemEvent.SELECTED) {
                                                   /*tfCUIT.setValue(cbProvider.getSelectedValueRef().toString().split("#")[0]);
                                                   */
                                                   calcDeduction();
                                               }
                                           }

                                       }
        );
        cbDeduction.getCombo().addItemListener(new ItemListener() {

                                           public void itemStateChanged(ItemEvent evt) {
                                               if (evt.getStateChange() == ItemEvent.SELECTED) {
                                                   /*tfCUIT.setValue(cbProvider.getSelectedValueRef().toString().split("#")[0]);
                                                   */
                                                    calcDeduction();
                                               }
                                           }

                                       }
        );
	ButtonGroup rbtn = new ButtonGroup();
	rbtn.add(rbtnAccount);
	rbtn.add(rbtnVouchers);
	ButtonGroup rbtnPaymentWay = new ButtonGroup();
	rbtnPaymentWay.add(rbtnCash);
	rbtnPaymentWay.add(rbtnCheck);
	setHeaderListDetail();
	setHeaderListDetailConcept();
	setGridVisible();
	setHeaderListDeduction();
	setHeaderListPaymentWay();
	setHeaderListBookKeepingEntry();
	tabbedPane.addChangeListener(new ChangeListener() {

				  public void stateChanged(ChangeEvent evt) {
				      JTabbedPane pane = (JTabbedPane)evt.getSource();
				      int sel = pane.getSelectedIndex();
				      if (sel == 3) {
					  refreshAllData();
				      } else if (sel == 1) {
                                          calcDeduction();
				      }
				  }

			      }
	);
	cbCheckNumber.autoSize();
	tabbedPane.addChangeListener(new ChangeListener() {

				  public void stateChanged(ChangeEvent changeEvent) {
				      if (paymentOrder == null && tabbedPane.getSelectedIndex() != 0) {
					  Advisor.messageBox("Orden de pago incompleta\n debe fijar el proveedor", "Aviso");
					  tabbedPane.setSelectedIndex(0);
				      }
				  }

			      }
	);
	cbAccount.setEnabled(false);
	tfDate.setEnabled(false);
	btnAddDetail.setToolTipText("Agregar factura al Debe");
	btnDeleteDetail.setToolTipText("Borrar factura del Debe");
	btnAddPaymentWay.setToolTipText("Agregar forma de pago al Haber");
	btnDeletePaymentWay.setToolTipText("Borrar forma de pago del Haber");
	btnPrintSave.setToolTipText("Grabar e imprimir la Orden de Pago");
	btnRecoverPaymentOrder.setToolTipText("Recuperar una Orden de Pago incompleta");
	btnRecoverPaymentOrder.addActionListener(new ActionListener() {

					      public void actionPerformed(ActionEvent e) {
						  btnRecoverPaymentOrder_actionPerformed(e);
					      }

					  }
	);
	btnClose.setToolTipText("Cancelar la Orden de Pago");
        loadComboIva();
        loadComboDeduction();
        tfPercentage.setVisible(false);
	btnDeletePaymentWay.setEnabled(false);
	btnDeletePaymentWay.setVisible(false);
        dataPanel.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("MainData")));
	listBookKeepingEntryPanel.setSortable(false);
	listDeductionPanel.setSortable(false);
	listDetailPanel.setSortable(false);
	listDetailPanel.setSize(new Dimension(679, 180));
	listDetailPanel.setPreferredSize(new Dimension(400, 180));
	listPaymentWayPanel.setSortable(false);
	tfAmountOwed.setEditable(false);
	
	rbtnConcepto.addActionListener(new ActionListener() {
				      public void actionPerformed(ActionEvent e) {
					  rbtnConcepto_actionPerformed(e);
				      }
				  }
	);
	rbtnFacturas.addActionListener(new ActionListener() {
				     public void actionPerformed(ActionEvent e) {
					 rbtnFacturas_actionPerformed(e);
				     }
				 }
	);
	rbtnMonto.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
				   rbtnMonto_actionPerformed(e);
			       }
			   }
	);
	btnSaveHeader.setBounds(new Rectangle(485, 75, 20, 20));
	btnSaveHeader.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSaveHeader_actionPerformed(e);
		}
	    }
	);
        tfDetailPartialAmount.addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    tfDetailPartialAmount_propertyChange(e);
                }
            });
        grupo.add(rbtnConcepto);
	grupo.add(rbtnFacturas);
	grupo.add(rbtnMonto);
	rbtnFacturas.setSelected(true);
	btnDeleteDetail.setEnabled(false);
	rbtnConcepto.setText("O.P. Adelantada");
	rbtnFacturas.setText("O.P. x Facturas Completas");
	rbtnMonto.setText("O.P. x Facturas Parciales");
	tfFindProvider.getTextField().requestFocus(true);
	listDetailPanelConcept.setSelectionMode(1);
	
    }
    
    
    private void setGridVisible(){
	if(rbtnConcepto.isSelected()){
	    jpImputations.remove(listDetailPanel);
	    jpImputations.add(listDetailPanelConcept, BorderLayout.CENTER);
	    listDetailPanelConcept.setVisible(true);
	    listDetailPanel.setVisible(false);
	}else{
	    jpImputations.remove(listDetailPanelConcept);
	    jpImputations.add(listDetailPanel, BorderLayout.CENTER);
	    listDetailPanelConcept.setVisible(false);
	    listDetailPanel.setVisible(true);
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Seleccione un proveedor, las facturas a pagar, formas de pago y registre la Orden de Pago");
    }

    private void loadComboChecks() {
	if (cbPaymentWay.getSelectedIndex() != -1) {
	    cbCheckNumber.loadJCombo(LibSQL.exFunction("cashflow.getvalidchecks", "" + cbPaymentWay.getSelectedValueRef()));
	} else {
	    //Advisor.messageBox("No tiene Asociada una Cuenta Bancaria", "Accion no vï¿½lida");
	}
    }

    private void loadComboAccountsForCheck() {
	if (paymentOrder != null) {
	    cbPaymentWay.loadJCombo(LibSQL.exFunction("accounting.getAllBankAccountsForChecks", "''"));
	    if (cbPaymentWay.getSelectedIndex() != -1) {
		loadComboChecks();
	    }
	} else {
	    Advisor.messageBox("Orden de pago incompleta", "Aviso");
	}
    }

    private void loadVouchersNotPaid() {
	/** Cargar las facturas adeudadas del Proveedor seleccionado */
	String params = "0,-1,"+rbtnMonto.isSelected();
	if (cbProvider.getSelectedIndex() != -1) {
	    params = "" + cbProvider.getSelectedValue() +",-1,"+rbtnMonto.isSelected();
	}
	//setHeaderListDetail(); deberia cambiar de grilla
	listDetailPanel.refresh(params);
    }
     
    private void loadConcepts() {
	/** Cargar los conceptos */
	String params = "-1";
	if (paymentOrder != null) {
	    params = ""+ paymentOrder.getIdPaymentOrder();
	}
	//setHeaderListDetail(); deberia cambiar de grilla
	listDetailPanelConcept.refresh(params);
    }

    private void rbtnConcepto_actionPerformed(ActionEvent e) {
	    if (LibSQL.getBoolean("admin.canUseEarlyPaymentorder","")) {
		cambiarModoConcepto();
		loadAmountOwed();    
	    } else {
		rbtnFacturas.setSelected(true);
		setOpFacturasCompletas();
	    }
    }
    
    private void cambiarModoConcepto(){
	/** recargar el combo proveedores (mostrando todos los proveedores) y recargar la grilla con los conceptos */
	if (true) {
	    this.getParentInternalFrame().setVisible(true);
	    setGridVisible();
	    loadData();
	    tfDetailAmount.setEditable(true);
	    tfConcept.setEditable(true);
	    btnDeleteDetail.setEnabled(true);
	    //btnAddDetail.setEnabled(true);    
	}else{
	    rbtnFacturas.setSelected(true);
	}
    }

    private void rbtnFacturas_actionPerformed(ActionEvent e) {
	setOpFacturasCompletas();
    }
    
    private void setOpFacturasCompletas(){
	/** recargar el combo proveedores (solo proveedores con facturas adeudadas) y recargar la grilla con las facturas */
	setGridVisible();
	loadData();
	tfDetailAmount.setEditable(false);
	btnAddDetail.setEnabled(false);
	btnDeleteDetail.setEnabled(false);
	tfConcept.setEditable(false);
	loadAmountOwed();
    }

    private void rbtnMonto_actionPerformed(ActionEvent e) {
	 /** recargar el combo proveedores (solo proveedores con facturas adeudadas) y recargar la grilla con las facturas */
	setGridVisible();
	loadData();
	tfDetailAmount.setEditable(true);
	btnAddDetail.setEnabled(false);
	btnDeleteDetail.setEnabled(false);
	tfConcept.setEditable(true);
	loadAmountOwed();
	
    }
  
    private void loadData() {
	if (paymentOrder == null) {
	    loadComboProvider();
	    limpiarPanelInferior();
	}
    }
     
    private void loadAmountOwed() {
	Double amountOwed = LibSQL.getDouble("org.getMontoAdeudadoPorProveedor",""+ cbProvider.getSelectedValue());
	tfAmountOwed.setValue(amountOwed);
	tfAmountOwed.getTextField().setOpaque(true);
	tfAmountOwed.getTextField().setForeground(Color.BLACK);
	if (amountOwed > 0) {
	    tfAmountOwed.getTextField().setBackground(Color.RED);
	} else if (amountOwed < 0) {
	    tfAmountOwed.getTextField().setBackground(Color.YELLOW);
	} else {
	    tfAmountOwed.getTextField().setBackground(Color.GREEN);
	}
    }

    private void loadComboProvider() {
	String param = "'" + tfFindProvider.getString() + "'";
	if (rbtnConcepto.isSelected()) {
	    cbProvider.loadJCombo(LibSQL.exFunction("org.getAllProviderFromPaymentOrderManualByFilter", param));
	    loadConcepts();
	} else {
	    //cbProvider.loadJCombo(LibSQL.exFunction("org.getallproviderswithcreditbalancebyfilter", param));
	    cbProvider.loadJCombo(LibSQL.exFunction("org.getAllProveedoresConFacturasImpagas", param));
	    tfCUIT.setValue(cbProvider.getSelectedValueRef().toString().split("#")[0]);
	    if (cbProvider.getSelectedIndex() != -1) {
		loadVouchersNotPaid();
		loadComboAccount(cbProvider.getSelectedValueRef().toString().split("#")[1]);
	    }   
	}
    }

    private void loadComboAccount(String _idAccount) {
	cbAccount.loadJCombo(LibSQL.exFunction("accounting.getAccount", _idAccount));
	//btnAddDetail.setEnabled(tfDetailAmount.getAmount() > 0 && cbAccount.getSelectedIndex() != -1);
    }

    private void loadComboAccountDeduction() {
	String param = "'" + tfFindAccountDeduction.getString() + "'";
	cbAccountDeduction.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsDeductionByFilter", param));
	if (cbAccountDeduction.getSelectedIndex() == -1) {
	    tfDeductionAmount.setEnabled(false);
	    tfPercentage.setEnabled(false);
	    btnAddDeduction.setEnabled(false);
	} else {
	    tfDeductionAmount.setEnabled(true);
	    tfPercentage.setEnabled(true);
	    btnAddDeduction.setEnabled(true);
	}
	tfDeductionAmount.setValue(0.0);
	tfPercentage.setValue(0.0);
    }

    private void loadComboIva() {
	cbIva.removeAllItems();
	cbIva.getCombo().addItem("Sin",1,0.0);
	cbIva.getCombo().addItem("10.5%",2,0.105);
	cbIva.getCombo().addItem("21%",3,0.21);
    }

    private void loadComboDeduction() {
	cbDeduction.removeAllItems();
	cbDeduction.getCombo().addItem("Sin",1,0.0);
	cbDeduction.getCombo().addItem("1.5%",2,0.015);
	cbDeduction.getCombo().addItem("3%",3,0.03);
	cbDeduction.getCombo().addItem("6%",4,0.06);
	cbDeduction.getCombo().addItem("9%",5,0.09);
    }

    private void loadComboAccountsCash() {
	cbPaymentWay.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsCashByFilter", "''"));
    }

    /*private void setHeaderListDetail() {
	String params = "";
	if (rbtnConcepto.isSelected()) {
	    headerListDetail.removeAllElements();
	    System.out.println("header: "+headerListDetail);
	    headerListDetail.addElement("*");
	    headerListDetail.addElement("*");
	    headerListDetail.addElement("*");
	    headerListDetail.addElement(Environment.lang.getProperty("Concept"));
	    headerListDetail.addElement("s/a");
	    headerListDetail.addElement("s/a");
	    headerListDetail.addElement("s/a");
	    headerListDetail.addElement(Environment.lang.getProperty("Amount"));
	    headerListDetail.addElement("*");
	    if (paymentOrder != null) {
	        params = ""+ paymentOrder.getIdPaymentOrder();
	    } else {
	        params = "-1";
	    }
	    listDetailPanel.setParams("cashflow.getAllPaymentOrderDetailManual", params, headerListDetail);
	    System.out.println("header: "+listDetailPanel.getHeaderTable());
	    listDetailPanel.getTable().addMouseListener(new MouseAdapter() {
    
						     public void mouseClicked(MouseEvent e) {
							 //loadObjectDetail();
							 if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							 } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
							     int index = listDetailPanel.getTable().rowAtPoint(e.getPoint());
							     listDetailPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
							 }
						     }
    
						 }
	    );

	} else {
	    headerListDetail.removeAllElements();
	    headerListDetail.addElement("*");
	    headerListDetail.addElement("Comprobante");
	    headerListDetail.addElement("Fecha");
	    headerListDetail.addElement(Environment.lang.getProperty("Amount"));
	    headerListDetail.addElement("($) Monto Cancelado");
	    headerListDetail.addElement("Saldo");
	    //Cambiado de lugar
	    if (cbProvider.getSelectedIndex() != -1) {
		params = cbProvider.getSelectedValue() +",-1";
	    } else {
		params = "0,-1";
	    }
	    listDetailPanel.setParams("cashflow.getAllVouchersNotPaid", params, headerListDetail);
	    //Cambiado de lugar
	    System.out.println("header fact: "+ listDetailPanel.getHeaderTable());
	    listDetailPanel.getTable().addMouseListener(new MouseAdapter() {
						  public void mouseClicked(MouseEvent e) {
						     if (rbtnConcepto.isSelected()) {
							 //Analizar que va
						     } else {
							 calcularSubTotal();
						     }
						     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
							  int index = listDetailPanel.getTable().rowAtPoint(e.getPoint());
							  listDetailPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
							  
						      }
						  }
	    
					      }
	    );
	}
    }*/
    
    private void setHeaderListDetail(){
	String params = "";
	headerListDetail.removeAllElements();
	headerListDetail.addElement("*");
	headerListDetail.addElement("Comprobante");
	headerListDetail.addElement("Fecha");
	headerListDetail.addElement(Environment.lang.getProperty("Amount"));
	headerListDetail.addElement("($) Monto Cancelado");
	headerListDetail.addElement("Saldo");
	//Cambiado de lugar
	if (cbProvider.getSelectedIndex() != -1) {
	    params = cbProvider.getSelectedValue() +",-1";
	} else {
	    params = "0,-1,"+rbtnMonto.isSelected();
	}
	listDetailPanel.setParams("cashflow.getAllVouchersNotPaid", params, headerListDetail);
	//Cambiado de lugar
	listDetailPanel.getTable().addMouseListener(new MouseAdapter() {
						  public void mouseClicked(MouseEvent e) {
						 /*if (rbtnConcepto.isSelected()) {
						     //Analizar que va
						 } else {
						     calcularSubTotal();
						 }*/
						 calcularSubTotal();
					         int index = listDetailPanel.getTable().rowAtPoint(e.getPoint());
					         listDetailPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
						 facturaSeleccionada = ((Vector)(listDetailPanel.getAllValues().elementAt(index)));
						 if((paymentOrder != null) && (rbtnFacturas.isSelected())) {
						     saveDetail();
						 }
					         
						 if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						 } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
						      
						  }
					      }
	
					  }
	);
    }
    
    private void setHeaderListDetailConcept(){
	String params = "";
	headerListDetailConcept.removeAllElements();
	headerListDetailConcept.addElement("*");
	headerListDetailConcept.addElement("*");
	headerListDetailConcept.addElement("*");
	headerListDetailConcept.addElement(Environment.lang.getProperty("Concept"));
	headerListDetailConcept.addElement("s/a");
	headerListDetailConcept.addElement("s/a");
	headerListDetailConcept.addElement("s/a");
	headerListDetailConcept.addElement(Environment.lang.getProperty("Amount"));
	headerListDetailConcept.addElement("*");
	if (paymentOrder != null) {
	    params = ""+ paymentOrder.getIdPaymentOrder();
	} else {
	    params = "-1";
	}
	listDetailPanelConcept.setParams("cashflow.getAllPaymentOrderDetailManual", params, headerListDetailConcept);
	listDetailPanelConcept.getTable().addMouseListener(new MouseAdapter() {
	
						 public void mouseClicked(MouseEvent e) {
						     //loadObjectDetail();
						     selectedGrid();
						     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
							 int index = listDetailPanelConcept.getTable().rowAtPoint(e.getPoint());
							 listDetailPanelConcept.getTable().getSelectionModel().setSelectionInterval(index, index);
						     }
						 }
	
					     }
	);
    }

    //metodo que obtiene el subtotal correspondiente a la suma de las facturas seleccionadas
    private void calcularSubTotal(){
	BigDecimal subTotal = new BigDecimal("0");
	Vector filasSeleccionadas = new Vector();
	if(rbtnConcepto.isSelected()) {
	    listDetailPanelConcept.selectAllItems(true);
	    filasSeleccionadas = listDetailPanelConcept.getSelectedsVector();
	} else {
	    filasSeleccionadas = listDetailPanel.getSelectedsVector();
	}
	for(int i=0;i< filasSeleccionadas.size();i++) {
	    Vector fila = (Vector)filasSeleccionadas.elementAt(i);
	    //System.out.println("FILA: "+fila.elementAt(5));
	    subTotal = subTotal.add(new BigDecimal(fila.elementAt(5).toString()));
	}
	
	if (rbtnConcepto.isSelected()) {
	    tfDetailPartialAmount.setValue(subTotal.doubleValue());
	    tfSubtotal.setValue(0.0);
	    tfSubtotal.setBackground(Color.LIGHT_GRAY);
	} else if (rbtnFacturas.isSelected()) {
	    tfDetailPartialAmount.setValue(subTotal.doubleValue());
	    tfSubtotal.setValue(0.0);
	    tfSubtotal.setBackground(Color.LIGHT_GRAY);
	} else {
	    //tfDetailPartialAmount.setValue(0.0);
	    if (subTotal.doubleValue() > Double.parseDouble(tfDetailPartialAmount.getValue().toString())) {
		tfSubtotal.setBackground(Color.RED);
	    } else {
		tfSubtotal.setBackground(Color.LIGHT_GRAY);
	    }
	    tfSubtotal.setValue(subTotal.doubleValue());
	}
	if(rbtnConcepto.isSelected()) {
	    listDetailPanelConcept.selectAllItems(false);
	}
    }

    public void refreshDetail() {
	if (paymentOrder != null) {
	    if (rbtnConcepto.isSelected()) {
		loadConcepts();
	    } else {
		if(rbtnFacturas.isSelected()){
		    
		}else{
		    loadVouchersNotPaid();
		}
	    }
	    btnDeleteDetail.setEnabled(false);
	} else {
	    Advisor.messageBox("Proveedor requerido.", "Datos requeridos");
	}
	
    }

    private void selectedGrid() {
	if (!dataRowDetailConcept.isEmpty()) {
	    btnDeleteDetail.setEnabled(true);
	    paymentOrderDetail = new PaymentOrderDetail(Integer.parseInt(dataRowDetailConcept.elementAt(0).toString()));
	} else {
	    paymentOrderDetail = null;
	}
    }

    private void loadDetailData() {
	int idConcept = paymentOrderDetail.getAccount().getIDAccount();
	idConcept = paymentOrderDetail.getVoucher().getIdVoucher();
	rbtnVouchers.setSelected(true);
	//rbtnAccount.setSelected(false);
	/*if (idConcept == -1) {
	    idConcept = paymentOrderDetail.getVoucher().getIdVoucher();
	    rbtnVouchers.setSelected(true);
	    rbtnAccount.setSelected(false);
	} else {
	    rbtnVouchers.setSelected(false);
	    rbtnAccount.setSelected(true);
	}*/
	//tfDetailAmount.setValue(paymentOrderDetail.getAmount());
	btnDeleteDetail.setEnabled(true);
    }

    private boolean saveDetail() {
	boolean result = true;
	if (paymentOrder == null) {
	    result = saveHeaderData();
	}
	if (result) {
	    if(rbtnConcepto.isSelected()) {
	        //if (paymentOrderDetail == null) {
	            paymentOrderDetail = new PaymentOrderDetail();
	        //}
	        /* ojo que se elimino el combo vouchers, analizar como se van a relacionar*/
	        int idConcept = -1;
	        //paymentOrderDetail.setAccount(new Account(Integer.parseInt(cbAccount.getSelectedValue().toString())));
	        paymentOrderDetail.setAccount(new Account(Integer.parseInt(cbProvider.getSelectedValueRef().toString().split("#")[1])));
	        paymentOrderDetail.setVoucher(new Voucher(idConcept));
	        idBudget = -1;
	        paymentOrderDetail.setPaymentOrder(paymentOrder);
	        paymentOrderDetail.setDescription(tfConcept.getString());
	        paymentOrderDetail.setAmount(Format.toDouble(tfDetailAmount.getAmount()).doubleValue());
	        if (paymentOrderDetail.saveData() >= 0) {
	            updateDetailAmount();
	            loadComboAccountsCash();
	            clearCheckData(false);
	            refreshAllData();
	            return true;
	        } else {
	            return false;
	        }	
	    } else {
		if(rbtnFacturas.isSelected()) {
		    //Se deben cargar las facturas de la grilla donde aparecen las facturas cuando estas esten seleccionadas y sacarlas cuando no lo esten
		     if(Boolean.parseBoolean(facturaSeleccionada.elementAt(0).toString())){
			 if(!estaEnAsiento(Integer.parseInt(facturaSeleccionada.elementAt(1).toString()))){
			     //Agregar a asiento
			     //System.out.println("TIENE QUE AGREGAR EN ASIENTO");
			     agregarEnAsiento();
			 } else {
			     //Por ahora nada
			 }
		     } else {
		         if(estaEnAsiento(Integer.parseInt(facturaSeleccionada.elementAt(1).toString()))){
		             //Sacar de asiento
		              //System.out.println("TIENE QUE SACAR DE ASIENTO");
			      sacarDeAsiento();
		         } else {
			     //Por ahora nada
			 }
		     }
		 } else {
		     //Agregar lo relacionado a monto
		      if (paymentOrderDetail == null) {
		          paymentOrderDetail = new PaymentOrderDetail();
		      }
		      int idConcept = -1;
		      paymentOrderDetail.setAccount(new Account(Integer.parseInt(cbProvider.getSelectedValueRef().toString().split("#")[1])));
		      paymentOrderDetail.setVoucher(new Voucher(idConcept));
		      idBudget = -1;
		      paymentOrderDetail.setPaymentOrder(paymentOrder);
		      paymentOrderDetail.setDescription(tfConcept.getString());
		      paymentOrderDetail.setAmount(Format.toDouble(tfDetailAmount.getAmount()).doubleValue());
		      paymentOrder.setAmount(Format.toDouble(tfDetailAmount.getAmount()).doubleValue());
		      
		      if (paymentOrderDetail.saveData() >= 0) {
		          updateDetailAmount();
		          loadComboAccountsCash();
		          clearCheckData(false);
		          //refreshAllData();
		          return true;
		      } else {
		          return false;
		      }
		 }
		 return true;
	    }
	    
	} else {
	    return false;
	}
    }
    
    private boolean agregarEnAsiento(){
	if (paymentOrderDetail == null) {
	    paymentOrderDetail = new PaymentOrderDetail();
	}
	//se carga el id del voucher con la fila seleccionada de la grilla
	int idConcept = Integer.parseInt("" + facturaSeleccionada.elementAt(1));
	paymentOrderDetail.setAccount(new Account(Integer.parseInt(cbAccount.getSelectedValue().toString())));
	paymentOrderDetail.setVoucher(new Voucher(idConcept));
	idBudget = -1;
	paymentOrderDetail.setPaymentOrder(paymentOrder);
	paymentOrderDetail.setAmount(new Double(facturaSeleccionada.elementAt(6).toString()));
	paymentOrderDetail.setDescription("");
	if (paymentOrderDetail.saveData() >= 0) {
	    updateDetailAmount();
	    loadComboAccountsCash();
	    clearCheckData(false);
	    refreshAllData();
	    return true;
	} else {
	    return false;
	}
    }
    
    private boolean sacarDeAsiento(){
	if (paymentOrderDetail == null) {
	    paymentOrderDetail = new PaymentOrderDetail();
	}
	int idPaymenOrderDetail = getIdPaymentOrderDetail(Integer.parseInt("" + facturaSeleccionada.elementAt(1)));
	paymentOrderDetail.setIdPaymentOrderDetail(idPaymenOrderDetail);
	if (paymentOrderDetail != null) {
	    paymentOrderDetail.delete();
	    updateDetailAmount();
	    refreshAllData();
	    return true;
	}else{
	    return false;
	}
    }
    
    private int getIdPaymentOrderDetail(int _idVoucher){
	boolean encontrado = false;
	int filas = listBookKeepingEntryPanel.getTable().getRowCount();
	int i = 0;
	while((!encontrado) && (i < filas)){
	    if (Integer.parseInt(((Vector)(listBookKeepingEntryPanel.getAllValues().elementAt(i))).elementAt(5).toString()) == _idVoucher){
		encontrado = true;
	    }else{
		i++;
	    }
	}
	return Integer.parseInt(((Vector)(listBookKeepingEntryPanel.getAllValues().elementAt(i))).elementAt(2).toString());
    }
    
    private void recargarEstadoGrilla(){
	int filas = listDetailPanel.getTable().getRowCount();
	listDetailPanel.selectAllItems(true);
	for(int i = 0; i < filas; i++){
	    if (!estaEnAsiento(Integer.parseInt(((Vector)(listDetailPanel.getAllValues().elementAt(i))).elementAt(1).toString()))){
		listDetailPanel.getTable().setValueAt(false,i,0);
	    }
	}
    }
    
    private boolean estaEnAsiento(int _idVoucher){
	boolean encontrado = false;
	int filas = listBookKeepingEntryPanel.getTable().getRowCount();
	int i = 0;
	while((!encontrado) && (i < filas)){
	    if (Integer.parseInt(((Vector)(listBookKeepingEntryPanel.getAllValues().elementAt(i))).elementAt(5).toString()) == _idVoucher){
		encontrado = true;
	    }else{
		i++;
	    }
	}
	return encontrado;
    }
    
    private void updateDetailAmount() {
	tfDetailPartialAmount.setValue(Format.toDouble(paymentOrder.updateDetailAmount()).doubleValue());
	netAmount = tfDetailPartialAmount.getAmount() - tfDeductionPartialAmount.getAmount();
	netAmount = Format.toDouble(netAmount).doubleValue();
	tfNetAmount.setValue(netAmount);
	tfPaymentWayAmount.setValue(netAmount);
	paymentOrder.setAmount(netAmount);
	calcDeduction();
	if(!rbtnMonto.isSelected()){
	    paymentOrderDetail = null;
	    refreshDetail();
	}
    }

    private void setHeaderListDeduction() {
	headerListDeduction.removeAllElements();
	headerListDeduction.addElement("*");
	headerListDeduction.addElement("*");
	headerListDeduction.addElement("*");
	headerListDeduction.addElement(Environment.lang.getProperty("Concept"));
	headerListDeduction.addElement(Environment.lang.getProperty("Amount"));
	headerListDeduction.addElement("*");
	listDeductionPanel.getTable().addMouseListener(new MouseAdapter() {

						    public void mouseClicked(MouseEvent e) {
							loadObjectDeduction();
							if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							} else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
							    int index = listDeductionPanel.getTable().rowAtPoint(e.getPoint());
							    listDeductionPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
							}
						    }

						}
	);
	listDeductionPanel.setParams("cashflow.getAllPaymentOrderDeductions", "-1", headerListDeduction);
    }

    public void refreshDeduction() {
	if (paymentOrder != null) {
	    String params = "" + paymentOrder.getIdPaymentOrder();
	    listDeductionPanel.refresh(params);
	    btnDeleteDeduction.setEnabled(false);
	} else {
	    Advisor.messageBox("Proveedor requerido.", "Datos requeridos");
	}
    }

    private void loadObjectDeduction() {
	if (!dataRowDeduction.isEmpty()) {
	    paymentOrderDeduction = new PaymentOrderDeduction();
	    paymentOrderDeduction.setIdPaymentOrderDeduction(Integer.parseInt("" + dataRowDeduction.elementAt(0)));
	    paymentOrderDeduction.setPaymentOrder(new PaymentOrder(Integer.parseInt("" + dataRowDeduction.elementAt(1))));
	    paymentOrderDeduction.setAccount(new Account(Integer.parseInt("" + dataRowDeduction.elementAt(2))));
	    //paymentOrderDeduction.setAmount(Double.parseDouble("" + dataRowDeduction.elementAt(4)));
	    //Matias
	    paymentOrderDeduction.setAmount(Format.toDouble(Double.parseDouble("" + dataRowDeduction.elementAt(4))).doubleValue());
	    loadDeductionData();
	}
    }

    private void loadDeductionData() {
	cbAccountDeduction.removeAllItems();
	cbAccountDeduction.getCombo().addItem("" + dataRowDeduction.elementAt(3), "" + paymentOrderDeduction.getAccount().getIDAccount());
	//tfDeductionAmount.setValue(paymentOrderDeduction.getAmount());
	//Matias
	tfDeductionAmount.setValue(Format.toDouble(paymentOrderDeduction.getAmount()).doubleValue());
	btnDeleteDeduction.setEnabled(true);
    }

    private boolean saveDeduction() {
	if (paymentOrder == null) {
	    saveHeaderData();
	}
	if (paymentOrderDeduction == null) {
	    paymentOrderDeduction = new PaymentOrderDeduction();
	}
	paymentOrderDeduction.setAccount(new Account(Integer.parseInt("" + cbAccountDeduction.getSelectedValue())));
	paymentOrderDeduction.setPaymentOrder(paymentOrder);
        //paymentOrderDeduction.setAmount(tfDeductionAmount.getAmount());
	//Matias
	paymentOrderDeduction.setAmount(Format.toDouble(tfDeductionAmount.getAmount()).doubleValue());
        paymentOrderDeduction.setPercentage(Double.parseDouble(cbDeduction.getSelectedValueRef().toString())*100.0);
	if (paymentOrderDeduction.saveData() >= 0) {
	    updateDeductionAmount();
	    refreshAllData();
	    return true;
	} else {
	    return false;
	}
    }

    private void updateDeductionAmount() {
	//tfDeductionPartialAmount.setValue(paymentOrder.updateDeductionAmount());
	tfDeductionPartialAmount.setValue(Format.toDouble(paymentOrder.updateDeductionAmount()).doubleValue());
	netAmount = tfDetailPartialAmount.getAmount() - tfDeductionPartialAmount.getAmount();
	//Matias +
	netAmount = Format.toDouble(netAmount).doubleValue();
	
	tfNetAmount.setValue(netAmount);
	tfPaymentWayAmount.setValue(netAmount);
	paymentOrder.setAmount(netAmount);
	paymentOrderDeduction = null;
	refreshDeduction();
    }

    private void setHeaderListPaymentWay() {
	headerListPaymentWay.removeAllElements();
	headerListPaymentWay.addElement("*");
	headerListPaymentWay.addElement("*");
	headerListPaymentWay.addElement("*");
	headerListPaymentWay.addElement("*");
	headerListPaymentWay.addElement(Environment.lang.getProperty("Concept"));
	headerListPaymentWay.addElement(Environment.lang.getProperty("Amount"));
	headerListPaymentWay.addElement("*");
	headerListPaymentWay.addElement("*");
	
	listPaymentWayPanel.getTable().addMouseListener(new MouseAdapter() {

						     public void mouseClicked(MouseEvent e) {
							 loadObjectPaymentWayData();
							 if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							 } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
							     int index = listPaymentWayPanel.getTable().rowAtPoint(e.getPoint());
							     listPaymentWayPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
							 }
						     }

						 }
	);
	listPaymentWayPanel.setParams("cashflow.getAllPaymentOrderPaymentWays", "-1, 1", headerListPaymentWay);
    }

    public void refreshPaymentWay() {
	String params = "" + paymentOrder.getIdPaymentOrder();
	if (rbtnCash.isSelected()) {
	    params += ", 1";
	} else if (rbtnCheck.isSelected()) {
	    params += ", 2";
	} else {
	    params += ", 3";
	}
	tfAmountToPay.setValue(paymentOrder.updatePaidAmount());
	//tfPaymentWayAmount.setValue(tfDetailPartialAmount.getAmount() - tfDeductionPartialAmount.getAmount() - tfAmountToPay.getAmount());
	//Matias
	tfPaymentWayAmount.setValue(Format.toDouble(tfDetailPartialAmount.getAmount() - tfDeductionPartialAmount.getAmount() - tfAmountToPay.getAmount()).doubleValue());
	listPaymentWayPanel.refresh(params);
	btnDeletePaymentWay.setEnabled(false);
    }

    private void loadObjectPaymentWayData() {
	if (!dataRowPaymentWay.isEmpty()) {
	    paymentOrderWay = new PaymentOrderWay();
	    paymentOrderWay.setIdPaymentOrderWay(Integer.parseInt("" + dataRowPaymentWay.elementAt(0)));
	    paymentOrderWay.setPaymentOrder(new PaymentOrder(Integer.parseInt("" + dataRowPaymentWay.elementAt(1))));
	    paymentOrderWay.setAccount(new Account(Integer.parseInt("" + dataRowPaymentWay.elementAt(2))));
	    paymentOrderWay.setCheck(new Check(Integer.parseInt("" + dataRowPaymentWay.elementAt(3))));
	    //paymentOrderWay.setAmount(Double.parseDouble("" + dataRowPaymentWay.elementAt(5)));
	    //Matias
	    paymentOrderWay.setAmount(Format.toDouble(Double.parseDouble("" + dataRowPaymentWay.elementAt(5))).doubleValue());
	    paymentOrderWay.setIdPaymentType(Integer.parseInt("" + dataRowPaymentWay.elementAt(6)));
	    loadPaymentWayData();
	}
    }

    /** 2009-09-21(moraless)*/
    private void loadPaymentWayData() {
	cbPaymentWay.removeAllItems();
	if (paymentOrderWay.getIdPaymentType() == 1) {
	    enablePayMethod(true, false);
	    opcionEfectivo();
	    //, false);
	    btnAddPaymentWay.setEnabled(true);
	    cbPaymentWay.getCombo().addItem("" + dataRowPaymentWay.elementAt(4), "" + paymentOrderWay.getAccount().getIDAccount());
	} else if (paymentOrderWay.getIdPaymentType() == 2) {
	    enablePayMethod(false, true);
	    opcionCheque();
	    //, false);
	    check = paymentOrderWay.getCheck();
	    check.retrieveData();
	    //tfCheckNumber.setValue(check.getNumber());
	    cbCheckNumber.setSelectedID(check.getIdCheck());
	    tfCheckDate.setValue(Proced.setFormatDate(check.getDate(), true));
	    cbPaymentWay.getCombo().addItem("" + dataRowPaymentWay.elementAt(4), "" + paymentOrderWay.getAccount().getIDAccount(), "" + check.getAccount().getIDBankAccount());
	    if (paymentOrderWay.getAccount().getIDAccount() > 0)  {
		cbPaymentWay.setSelectedID(paymentOrderWay.getAccount().getIDAccount());
	    }
	    
	}
	//tfPaymentWayAmount.setValue(paymentOrderWay.getAmount());
	//Matias
	tfPaymentWayAmount.setValue(Format.toDouble(paymentOrderWay.getAmount()).doubleValue());
	//btnDeletePaymentWay.setEnabled(true); /*2009-09-21*/
	netAmount += paymentOrderWay.getAmount(); 
	//Matias +
	netAmount = Format.toDouble(netAmount).doubleValue();
    }

    private boolean saveDataCheck(double _amount) {
	//Matias +
	_amount = Format.toDouble(_amount).doubleValue();
	
	if (paymentOrderWay.getIdPaymentOrderWay() == -1) {
	    check = new Check((Integer.parseInt(cbCheckNumber.getSelectedValue().toString())));
	    //check.setAccount(new BankAccount(Integer.parseInt("" + cbPaymentWay.getSelectedValueRef())));
	    check.retrieveData();
	    checksVector.add(check);
	}
	check.setAmount(_amount);
	check.setDate(Proced.setFormatDate(tfDate.getString(), false));
	check.setDescription(tfObservations.getString());
	check.setExpiredDate(Proced.setFormatDate(tfCheckDate.getString(), false));
	check.setIdCheckStatus(Check.HANDED);
	check.setIdEntity(Integer.parseInt(cbProvider.getSelectedValue().toString()));
	//check.setIdCheckType(0);
	check.setIdEntitytype(EntityTypes.PROVIDER); //67 = Proveedor
	//check.setLetter();
	//check.setNumber(Integer.parseInt(tfCheckNumber.getString()));
	//check.setNumber(Integer.parseInt(cbCheckNumber.getSelectedValue().toString()));
	check.setEstado("p");
	int result = -1;
	result = check.setData();
	/*if (check.getIdCheck() == -1) {
	    result = check.addData();
	} else {
	    result = check.setData();
	}*/
	return (result > 0 ? true : false);
    }

    private boolean savePaymentWay() {
	//Matias
	//double amount = tfPaymentWayAmount.getAmount();
	double amount = Format.toDouble(tfPaymentWayAmount.getAmount()).doubleValue();
	int idCuentaBanco = -1;
	int resguardoCuenta = -1;
	String resguardo = "";
	netAmount = Format.toDouble(netAmount).doubleValue();
	if (amount <= netAmount) {
	    if (paymentOrderWay == null) {
		paymentOrderWay = new PaymentOrderWay();
	    }
	    paymentOrderWay.setPaymentOrder(paymentOrder);
	    amountToPay = amountToPay - paymentOrderWay.getAmount() + amount;
	    //Matias +
	    amountToPay = Format.toDouble(amountToPay).doubleValue();
	    
	    paymentOrderWay.setAmount(amount);
	    netAmount -= amount;
	    //Matias +
	    netAmount = Format.toDouble(netAmount).doubleValue();
	    
	    
	    if (rbtnCash.isSelected()) {
		paymentOrderWay.setAccount(new Account(Integer.parseInt("" + cbPaymentWay.getSelectedValue())));
		paymentOrderWay.setCheck(new Check());
		paymentOrderWay.setIdPaymentType(PaymentOrderWay.EFECTIVO);
		enablePayMethod(true, false);
		//, false);
	    } else if (rbtnCheck.isSelected()) {
		paymentOrderWay.setAccount(new Account());
		saveDataCheck(amount);
		paymentOrderWay.setCheck(check);
		paymentOrderWay.setIdPaymentType(PaymentOrderWay.CHEQUE);
		paymentOrderWay.setAccount(new Account(Integer.parseInt("" + cbPaymentWay.getSelectedValue())));
	        
		resguardoCuenta = Integer.parseInt("" + cbPaymentWay.getSelectedValue());
		resguardo = cbPaymentWay.getSelectedItem().toString();
		
		idCuentaBanco = paymentOrderWay.getAccount().getIDAccount();
		enablePayMethod(false, true);
		//, false);
		tfPaymentWayAmount.setValue(0.0);
		loadComboAccountsForCheck();
		clearCheckData(true);
		loadComboChecks();
	    } else {
		enablePayMethod(false, false);
		//, true);
	    }
	    if (paymentOrderWay.saveData() >= 0) {
		paymentOrderWay = null;
		tfAmountToPay.setValue(amountToPay);
		clearCheckData(rbtnCheck.isSelected());
		refreshPaymentWay();
		refreshAllData();
		if (idCuentaBanco != -1)  {
		    cbPaymentWay.getCombo().setSelectedItem(resguardo);
		}
		return true;
	    } else {
		return false;
	    }
	} else {
	    Advisor.messageBox("El importe a pagar es " + Format.money(netAmount), "Importe no válido");
	    return false;
	}
    }
    
    private void refreshAllData() {
	refreshBookKeepingEntry();
	tfDebitAmount.setValue(Format.toDouble(tfDetailPartialAmount.getAmount()).doubleValue());
	tfCreditAmount.setValue(Format.toDouble(tfDeductionPartialAmount.getAmount() + tfAmountToPay.getAmount()).doubleValue());
	double available = Math.abs(tfDebitAmount.getAmount() - tfCreditAmount.getAmount());
	available = Format.toDouble(available).doubleValue();
	tfAvailableAmount.setValue(available);
	if (available != 0) {
	    tfAvailableAmount.setBackground(Color.RED);
	} else {
	    tfAvailableAmount.setBackground(Color.GREEN);
	}
	if (rbtnCheck.isSelected()) {
	    loadComboAccountsForCheck();
	} else {
	    loadComboAccountsCash();
	}
	loadComboChecks();
    }

    private void enablePayMethod(boolean _cash, boolean _check) {
	rbtnCash.setSelected(_cash);
	rbtnCheck.setSelected(_check);
    }

    private void setHeaderListBookKeepingEntry() {
	headerListBookKeepingEntry.removeAllElements();
	headerListBookKeepingEntry.addElement("*");
	headerListBookKeepingEntry.addElement("*");
	headerListBookKeepingEntry.addElement("*");
	headerListBookKeepingEntry.addElement("*");
	headerListBookKeepingEntry.addElement("*");
	headerListBookKeepingEntry.addElement(Environment.lang.getProperty("Accounting"));
	headerListBookKeepingEntry.addElement(Environment.lang.getProperty("Concept"));
	headerListBookKeepingEntry.addElement(Environment.lang.getProperty("Debit"));
	headerListBookKeepingEntry.addElement(Environment.lang.getProperty("Credit"));
	listBookKeepingEntryPanel.getTable().addMouseListener(new MouseAdapter() {

							   public void mouseClicked(MouseEvent e) {
							       if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
							       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
								   int index = listBookKeepingEntryPanel.getTable().rowAtPoint(e.getPoint());
								   listBookKeepingEntryPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
							       }
							   }

						       }
	);
	listBookKeepingEntryPanel.setParams("cashflow.getAllPaymentOrderBookKeepingEntry", "-1", headerListBookKeepingEntry);
    }

    public void refreshBookKeepingEntry() {
	if (paymentOrder != null) {
	    String params = "" + paymentOrder.getIdPaymentOrder();
	    listBookKeepingEntryPanel.refresh(params);
	}
    }

    private void assignAccountDeduction() {
	tfFindAccountDeduction.setValue("");
	cbAccountDeduction.removeAllItems();
	if (account != null) {
	    cbAccountDeduction.getCombo().addItem(account.getName(), "" + account.getIDAccount());
	} else {
	    tfDeductionAmount.setEnabled(false);
	    tfPercentage.setEnabled(false);
	}
    }

    private void assignAccountDetail() {
	tfFindAccountDetail.setValue("");
	if (account != null) {

	} else if (voucher != null) {
	    //Matias
	     tfDetailAmount.setValue(Format.toDouble(voucher.getAmount()).doubleValue());
	}
    }

    private void assignAccountPaymentWay() {
	cbPaymentWay.removeAllItems();
	if (account != null) {
	    cbPaymentWay.getCombo().addItem(account.getName(), "" + account.getIDAccount());
	} else if (check != null) {
	    cbPaymentWay.getCombo().addItem(check.getFullNumber(), "" + check.getIdCheck());
	}
    }

    public void reload() {
	switch (selectAccountType) {
	    case ACCOUNTDETAIL :
		assignAccountDetail();
		break;
	    case ACCOUNTDETAIL_NEWVOUCHER :
		loadVouchersNotPaid();
		break;
	    case ACCOUNTDEDUCTION :
		assignAccountDeduction();
		break;
	    case ACCOUNTCASH :
		assignAccountPaymentWay();
		break;
	    default :
		break;
	}
	account = null;
    }

    private void loadAccount() {
	AccountsMain accountMain = new AccountsMain();
	account = new Account();
	accountMain.setAccount(account);
	accountMain.setParentPanel(this);
	ExtendedInternalFrame accountContainer = new ExtendedInternalFrame("Cuentas");
	accountContainer.setCentralPanel(accountMain);
	accountContainer.show();
    }

    private void loadVoucher() {
	voucher = new Voucher();
	VouchersList vouchersList = new VouchersList();
	vouchersList.setVoucher(voucher);
	vouchersList.setParentPanel(this);
	vouchersList.setVoucherType(VoucherType.INVOICE_B);
	vouchersList.setProvider(new Provider(Integer.parseInt("" + cbProvider.getSelectedValue()), cbProvider.getSelectedItem().toString()));
	vouchersList.refresh();
	ExtendedInternalFrame vouchersListContainer = new ExtendedInternalFrame("Comprobantes");
	vouchersListContainer.setCentralPanel(vouchersList);
	vouchersListContainer.show();
    }

    private void loadAccounts() {
	BankAccountsList accountsList = new BankAccountsList();
	accountsList.refresh();
	ExtendedInternalFrame accountContainer = new ExtendedInternalFrame("Cuentas Bancarias");
	accountContainer.setCentralPanel(accountsList);
	accountContainer.show();
    }

    private void btnFindDetail_actionPerformed(ActionEvent e) {
	selectAccountType = ACCOUNTDETAIL;
	if (rbtnAccount.isSelected()) {
	    loadAccount();
	} else {
	    loadVoucher();
	}
    }
    
    public boolean saveHeaderData() {
	if (!cbProvider.getSelectedValue().equals("-1")) {
	    if (paymentOrder == null) {
		paymentOrder = new PaymentOrder();
	    }
	    paymentOrder.setDate(Proced.setFormatDate(tfDate.getString(), false));
	    //paymentOrder.setCostCentre(new CostsCentre(Integer.parseInt("" + cbCostCentre.getSelectedValue())));
	    paymentOrder.setCostCentre(new CostsCentre());
	    paymentOrder.setProvider(new Provider(Integer.parseInt("" + cbProvider.getSelectedValue())));
	    paymentOrder.setObservations(tfObservations.getString());
            if (rbtnConcepto.isSelected()) {
                paymentOrder.setEsAdelantada(true);
            }
	    if (paymentOrder.saveData() >= 0) {
		paymentOrderDetail = null;
		return true;
	    } else {
		return false;
	    }
	} else {
	    Advisor.messageBox("Proveedor requerido.", "Datos requeridos");
	    return false;
	}
    }

    private void btnAddDeduction_actionPerformed(ActionEvent e) {
	if (tfDeductionAmount.getAmount() > 0 && cbAccountDeduction.getSelectedIndex() != -1) {
	    saveDeduction();
	} else {
	    Advisor.messageBox("El monto de retenciones debe ser mayor que cero\ny debe seleccionar una Cuenta para aplicar la Retención.", "Error");
	}
    }

    private void btnAddDetail_actionPerformed(ActionEvent e) {
	agregarMontoConcepto();
    }
    
    private void agregarMontoConcepto(){
	if (paymentOrder == null) {
	    Advisor.messageBox("Antes de asignar algún Concepto,\ndebe fijar el Proveedor", "Aviso");
	    tabbedPane.setSelectedIndex(0);
	} else {
	    if (Double.parseDouble(tfDetailAmount.getValue().toString()) > 0 && !tfConcept.getString().trim().equals("")) {
		if(saveDetail()){
		     if(rbtnMonto.isSelected()) {
			    tfDetailPartialAmount.setValue(tfDetailAmount.getValue());
			    tfSubtotal.setValue(new BigDecimal("0.00"));
			    tfSubtotal.setBackground(Color.LIGHT_GRAY);
		     } else {
			 //tfSubtotal.setValue(calcularMontoConceptos());
		         tfDetailPartialAmount.setValue(tfSubtotal.getValue());
		     }
		    enabledHeader(false);
		}else{
		    Advisor.messageBox("Se produjo un error al grabar los datos", "Error");
		}
		/*
		if (!Environment.internalFramesVector.contains(this.getParentInternalFrame())) {
		    Environment.addInternalFrameForSaving(this.getParentInternalFrame());
		}
		*/
	    } else {
		if(tfConcept.getString().trim().equals("")){
		    Advisor.messageBox("Debe completar los campos\n Observaciones, Concepto", "Error");
		}else{
		    Advisor.messageBox("El campo ($) Monto no puede ser cero", "Error");
		}
	    }
	}
	/*
	if (!tfObservations.getString().trim().equals("")) {
	    saveDetail();
	    enabledHeader(false);
	    if (!Environment.internalFramesVector.contains(this.getParentInternalFrame())) {
		Environment.addInternalFrameForSaving(this.getParentInternalFrame());
	    }
	} else {
	    Advisor.messageBox("Debe completar el campo Observaciones", "Error");
	}*/
    }
    
    private void calcularMontoConceptos(){
	BigDecimal subTotal = new BigDecimal("0");
	Vector filas = new Vector();
	if(rbtnConcepto.isSelected()){
	    filas = listDetailPanelConcept.getAllValues();
	}
	for(int i=0;i< filas.size();i++){
	    Vector fila = (Vector)filas.elementAt(i);
	    subTotal = subTotal.add(new BigDecimal(fila.elementAt(8).toString()));
	}
	tfSubtotal.setValue(subTotal);
	tfDetailPartialAmount.setValue(subTotal);
    }

    private void btnFindProvider_actionPerformed(ActionEvent e) {
	ProvidersMain providersMain = new ProvidersMain();
	ExtendedInternalFrame providersMainContainer = new ExtendedInternalFrame("Proveedores");
	providersMainContainer.setCentralPanel(providersMain);
	providersMainContainer.show();
    }

    private void rbtnAccount_actionPerformed(ActionEvent e) {
	
    }

    private void rbtnVouchers_actionPerformed(ActionEvent e) {
	/*if (!cbProvider.getSelectedValue().equals("-1")) {
	    cbAccountDetail.removeAllItems();
	    cbAccountDetail.setSComponentName("Invoices");
	    //btnNewVoucher.setVisible(true);
	} else {
	    Advisor.messageBox("Proveedor requerido.", "Datos requeridos");
	    rbtnAccount.setSelected(true);
	    rbtnVouchers.setSelected(false);
	}*/
    }

    private void cancelPaymentWay() {
	if (Advisor.question("Cancelar Orden de Pago", "¿Desea cancelar la Orden de Pago Nº " + paymentOrder.getNumber() + " ?")) {
	    Environment.removeUnfinishedTask(this.getParentInternalFrame());
	    paymentOrder.rollBack();
	    for (int i = 0; i < checksVector.size(); i++) {
		checksVector.elementAt(i).rollBack();
	    }
	    clearData();
	    /* (05/02/2010 - Cesar) Marcelo solicito que no se minimice la ventana  */
	    //getParentInternalFrame().setIcon(true);
	}
    }

    private void clearData() {
	tfFindProvider.setValue("");
	cbProvider.removeAllItems();
	tfCUIT.setValue("");
	tfObservations.setValue("");
	tfFindAccountDetail.setValue("");
	tfConcept.setValue("");
	tfDetailAmount.setValue(0.0);
	//tfDetailAmount.setEnabled(false);
	tfDetailPartialAmount.setValue(0.0);
	tfFindAccountDeduction.setValue("");
	cbAccountDeduction.removeAllItems();
	tfDeductionAmount.setEnabled(false);
	tfPercentage.setEnabled(false);
	tfDeductionAmount.setValue(0.0);
	tfDeductionPartialAmount.setValue(0.0);
	cbPaymentWay.removeAllItems();
	tfPaymentWayAmount.setValue(0.0);
	tfAmountToPay.setValue(0.0);
	tfNetAmount.setValue(0.0);
	tfAvailableAmount.setValue(0.0);
	tfCreditAmount.setValue(0.0);
	tfDebitAmount.setValue(0.0);
	tfCUIT.setEnabled(false);
	tfAvailableAmount.setBackground(Color.RED);
	tabbedPane.setSelectedIndex(0);
	enablePayMethod(true, false);
	cbPaymentWay.setSComponentName("Cash");
	cbPaymentWay.removeAllItems();
	paymentOrder = null;
	paymentOrderDetail = null;
	if (rbtnConcepto.isSelected()) {
	    listDetailPanelConcept.refresh("-1");
	} else {
	    listDetailPanel.refresh("0, -1,"+rbtnMonto.isSelected());
	}
	listDeductionPanel.refresh("-1");
	listPaymentWayPanel.refresh("-1, -1");
	listBookKeepingEntryPanel.refresh("-1");
	amountToPay = 0;
	netAmount = 0;
	checksVector.removeAllElements();
	enabledHeader(true);
	clearCheckData(true);
        idBudget = -1;
	tfAmountOwed.setValue(0.0);
	rbtnConcepto.setEnabled(true);
	rbtnFacturas.setEnabled(true);
	rbtnMonto.setEnabled(true);
	btnAddDetail.setEnabled(false);
	tfFindProvider.getTextField().requestFocus();
	tfSubtotal.setValue(0.0);
	tfAmountOwed.setValue(0.0);
	tfSubtotal.setBackground(Color.LIGHT_GRAY);
	tfAmountOwed.setBackground(Color.LIGHT_GRAY);
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	if (paymentOrder != null) {
	    cancelPaymentWay();
	} else {
	    /* (05/02/2010 - Cesar) Marcelo solicito que no se minimice la ventana  */
	    //getParentInternalFrame().setIcon(true);
	}
    }

    private void btnPrintSave_actionPerformed(ActionEvent e) {
	saveData();
    }

    public boolean saveData() {
	boolean returns = false;
	boolean approve = false;
	int error = 0;
	if (paymentOrder != null) {
	    //if ( Math.abs(tfCreditAmount.getAmount() - tfDebitAmount.getAmount()) < 0.001 && tfDebitAmount.getAmount() > 0) {
	    //Matias
	    if ( Format.toDouble(Math.abs(tfCreditAmount.getAmount() - tfDebitAmount.getAmount())).doubleValue() < 0.001 && Format.toDouble(tfDebitAmount.getAmount()).doubleValue() > 0) {
		if (rbtnMonto.isSelected())  {
		    if (listDetailPanel.getSelectedsID().size() > 0)  {
			if ((Format.toDouble(tfSubtotal.getAmount()).doubleValue()) >= (Format.toDouble(tfDetailAmount.getAmount()).doubleValue()))  {
			    approve = paymentOrder.approveByMonto(listDetailPanel.getSelectedsID(),new Double(tfDetailPartialAmount.getValue().toString()), Integer.parseInt(cbProvider.getSelectedValueRef().toString().split("#")[1]) );
			    //System.out.println("APPROVE MONTO: "+approve);
			} else {
			    error = 2;
			}
		    } else  {
			approve = false;
			error = 1;
		    }		     
		} else  {
		    if (rbtnFacturas.isSelected()){
		        approve = paymentOrder.approveByFacturas();
			//System.out.println("APPROVE FACT: "+approve);
		    } else{
		        approve = paymentOrder.approveByAdelantadas();
		    }
		}
		if (approve) {
		    print();
		    //if (tfDeductionPartialAmount.getAmount() > 0) {
		    //Matias
		    /* 
		     * Se reemplazo estas lineas porque corresponden a lo constancia de Retencion de la Provincia
		    if (Format.toDouble(tfDeductionPartialAmount.getAmount()).doubleValue() > 0) {
			if (Advisor.question("Constancia de Retenciï¿½n DGR", "ï¿½Desea imprimir la Constancia de Retenciï¿½n DGR?")) {
			    BasicReport report = new BasicReport(PaymentOrderMgmt.class.getResource("xml/DeductionTaxVoucher.xml"));
			    report.addTableModel("cashflow.xmlGetDeductionTaxVoucher", "" + paymentOrder.getIdPaymentOrder());
			    report.setProperty("textamount", Format.NumberToText.numberToText(tfDeductionPartialAmount.getAmount()) + ".-");
			    report.setProperty("organizacion",Environment.organization);
			    report.doReport();
			}
		    }
		    */
		    // Cesar

		    if (listDeductionPanel.getAllValues().size() > 0) {
		        if (Advisor.question("Constancia de Retención", "¿Desea imprimir la Constancia de Retención?")) {
		            BasicReport report = new BasicReport(PaymentOrderMgmt.class.getResource("xml/ExtraDeductionTaxVoucher.xml"));
		            report.addTableModel("cashflow.xmlGetDeductionTaxVoucher", "" + paymentOrder.getIdPaymentOrder());
		            report.setProperty("textamount", Format.NumberToText.numberToText(tfDeductionPartialAmount.getAmount()) + ".-");
		            report.setProperty("organizacion", OrganizationInfo.getOrgName());
		            report.setProperty("localidad", OrganizationInfo.getLocation());
		            report.setProperty("nrocomprobante", "");
		            report.doReport();
		        }	
		    }
		    
		    clearData();
		    //getParentInternalFrame().setIcon(true);
		    Environment.removeUnfinishedTask(this.getParentInternalFrame());
		    returns = true;
		} else {
		    if (error == 1) {
		        Advisor.messageBox("Debe seleccionar al menos una factura de la Grilla", "Error");
		    } else {
			if (error == 2) {
			    Advisor.messageBox("La suma de los montos de las facturas seleccionadas\n debe ser mayor o igual al monto de la Orden de Pago.", "Error"); 
			} else{
			    Advisor.messageBox("Ocurrió un error mientras se guardaba\nla Orden de Pago Nº" + paymentOrder.getNumber(), "Error al guardar");		    
			}
		    }
		}
	    } else {
		Advisor.messageBox("El asiento no está balanceado", "Aviso");
	    }
	} else {
	    Advisor.messageBox("Orden de Pago incompleta", "Aviso");
	}
	return returns;
    }

    private void clearCheckData(boolean enabled) {
	cbCheckNumber.setEnabled(enabled);
	cbCheckNumber.removeAllItems();
	tfCheckDate.setValue("");
	tfCheckDate.setEnabled(enabled);
	if (enabled) {
	    tfCheckDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	}
    }

    private void rbtnCash_actionPerformed(ActionEvent e) {
	opcionEfectivo();
    }

    private void opcionEfectivo() {
	cbPaymentWay.setSComponentName("Cash");
	cbPaymentWay.removeAllItems();
	loadComboAccountsCash();
	clearCheckData(false);
    }

    private void rbtnCheck_actionPerformed(ActionEvent e) {
	opcionCheque();
    }
    
    private void opcionCheque() {
	btnAddPaymentWay.setEnabled(true);
	cbPaymentWay.setSComponentName("AccountingCodeBankAccountNumber");
	cbPaymentWay.removeAllItems();
	clearCheckData(true);
	loadComboAccountsForCheck();
	if (dataRowPaymentWay.size() > 0)  {
	    cbPaymentWay.setSelectedID(paymentOrderWay.getAccount().getIDAccount());
	}
	//tfPaymentWayAmount.setValue(tfDetailPartialAmount.getAmount() - tfDeductionPartialAmount.getAmount() - tfAmountToPay.getAmount());
	//Matias
	tfPaymentWayAmount.setValue(Format.toDouble(tfDetailPartialAmount.getAmount() - tfDeductionPartialAmount.getAmount() - tfAmountToPay.getAmount()).doubleValue());
    }
    private void btnAddPaymentWay_actionPerformed(ActionEvent e) {
	paymentWayControl();
    }
    
    private void paymentWayControl() {
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

    private void print() {
	//PaymentOrdersReport report = new PaymentOrdersReport("xml/paymentorder.xml", paymentOrder);
	BasicReport report = new BasicReport(PaymentOrderMgmt.class.getResource("xml/paymentorder.xml"));
	report.addTableModel("cashflow.getPaymentOrders", "" + paymentOrder.getIdPaymentOrder());
	report.addTableModel("cashflow.xmlGetAllPaymentOrderBookKeepingEntry", paymentOrder.getIdPaymentOrder());
        report.setProperty("totalAmount",paymentOrder.getAmount());
	report.setProperty("textamount", Format.NumberToText.numberToText(paymentOrder.getAmount()) + ".-");
	report.doReport();
    }

    private void btnAddProvider_actionPerformed(ActionEvent e) {
	ProvidersMain providersMain = new ProvidersMain();
	providersMain.setSelectedTab(1);
	ExtendedInternalFrame providersMainContainer = new ExtendedInternalFrame("Proveedores");
	providersMainContainer.setCentralPanel(providersMain);
	providersMainContainer.show();
    }

    private void btnDeleteDetail_actionPerformed(ActionEvent e) {
	if (paymentOrderDetail != null) {
	    paymentOrderDetail.delete();
	    updateDetailAmount();
	    refreshAllData();
	} else {
	    Advisor.messageBox("Debe seleccionar un elemnto de la Grilla","Aviso");
	}
    }

    private void btnDeleteDeduction_actionPerformed(ActionEvent e) {
	if (paymentOrderDeduction != null) {
	    paymentOrderDeduction.delete();
	    updateDeductionAmount();
	    refreshAllData();
	}
    }

    private void btnDeletePaymentWay_actionPerformed(ActionEvent e) {
	boolean borrar = true;
	if (paymentOrderWay != null) { 
	    amountToPay -= paymentOrderWay.getAmount();
	    tfAmountToPay.setValue(amountToPay);
	    paymentOrderWay.delete();
	    paymentOrderWay = null;
	    refreshPaymentWay();
	    refreshAllData();
	}
    }
    
    private void enabledHeader(boolean _valor) {
	tfFindProvider.setEnabled(_valor);
	cbProvider.setEnabled(_valor);
	btnAddProvider.setEnabled(_valor);
	btnFindProvider.setEnabled(_valor);
	tfObservations.setEnabled(_valor);
	btnSaveHeader.setEnabled(_valor);
	if (rbtnConcepto.isSelected()) {
	    rbtnFacturas.setEnabled(_valor);
	    rbtnMonto.setEnabled(_valor);
	} else if (rbtnFacturas.isSelected()) {
	    rbtnConcepto.setEnabled(_valor);
	    rbtnMonto.setEnabled(_valor);
	} else {
	    rbtnConcepto.setEnabled(_valor);
	    rbtnFacturas.setEnabled(_valor);
	}
    }

    private void btnRecoverPaymentOrder_actionPerformed(ActionEvent e) {
	if (paymentOrder != null) {
	    Advisor.messageBox("Antes de recuperar una Orden de Pago debe cancelar la actual", "Aviso");
	} else {
	    ResultSet _recovery = LibSQL.exQuery("SELECT cashflow.getPaymentOrderForRecovery()");
	    if (_recovery != null) {
		try {
		    if (_recovery.next()) {
			String _recover = _recovery.getString(1);
			int _idPaymentOrder = Integer.parseInt(_recover.split("#")[0]);
			if (_idPaymentOrder == -1) {
			    Advisor.messageBox("No se han encontrado Órdenes de Pago para recuperar", "Aviso");
			} else {
			    paymentOrder = new PaymentOrder(_idPaymentOrder);
			    paymentOrder.setNumber(Integer.parseInt(_recover.split("#")[1]));
			    tfFindProvider.setValue(_recover.split("#")[2]);
			    tfObservations.setValue(_recover.split("#")[3]);
			    loadComboProvider();
			    checksVector = paymentOrder.getChecksVector();
			    updateDetailAmount();
			    updateDeductionAmount();
			    refreshPaymentWay();
			    refreshAllData();
			}
		    } else {
			Advisor.messageBox("No se han encontrado órdenes de pago para recuperar", "Aviso");
		    }
		} catch (SQLException f) {
		    // TODO
		}
	    } else {
		Advisor.messageBox("No se han encontrado órdenes de pago para recuperar", "Aviso");
	    }
	}
    }
    
    private void calcDeduction() {
        double totalAmount = tfDetailPartialAmount.getAmount();
        /*double myAmount =  (totalAmount/(Double.parseDouble(cbIva.getSelectedValueRef().toString())) ) * (Double.parseDouble(cbDeduction.getSelectedValueRef().toString())/100.0);*/
        //double myAmount =  (totalAmount * (Double.parseDouble(cbIva.getSelectedValueRef().toString())) ) * (Double.parseDouble(cbDeduction.getSelectedValueRef().toString()));
        double myAmount =  (totalAmount/(1+Double.parseDouble(cbIva.getSelectedValueRef().toString())) ) * (Double.parseDouble(cbDeduction.getSelectedValueRef().toString()));
	//Matias +
	myAmount = Format.toDouble(myAmount).doubleValue();
	tfDeductionAmount.setValue(Format.toDouble(myAmount)); 
    }
    
    /**2009-09-22(moraless)*/
    private void calculateNetAmount() {
	netAmount = Format.toDouble(tfDetailPartialAmount.getAmount() - tfDeductionPartialAmount.getAmount() - listPaymentWayPanel.getSum(2)).doubleValue();
	//netAmount = listPaymentWayPanel.getSum(2);//amount
    }
    
    private void limpiarPanelInferior() {
	tfDetailAmount.setValue(0.0);
	tfDetailPartialAmount.setValue(0.0);
	tfSubtotal.setValue(0.0);
	tfDetailPartialAmount.setBackground(Color.LIGHT_GRAY);
	tfSubtotal.setBackground(Color.LIGHT_GRAY);
    }

    private void btnSaveHeader_actionPerformed(ActionEvent e) {
	saveHeader();
    }
    
    private void saveHeader() {
	if (cbProvider.getSelectedIndex() != -1) {
	    if (!tfObservations.getString().toString().equals("") ) {
		if (paymentOrder == null) {
		    if (saveHeaderData()) {
			enabledHeader(false);
			if (rbtnFacturas.isSelected()) {
			    btnAddDetail.setEnabled(false);
			    tfConcept.setEditable(false);
			    btnDeleteDetail.setEnabled(false);

			} else {
			    btnAddDetail.setEnabled(true);
			    tfConcept.setEditable(true);
			    if (rbtnConcepto.isSelected())  {
				btnDeleteDetail.setEnabled(true);
			    } else {
			        btnDeleteDetail.setEnabled(false);
			    }
			    
			}
			listDetailPanel.selectAllItems(false);
			listDetailPanelConcept.selectAllItems(false);
			Environment.addUnfinishedTask(this.getParentInternalFrame());
			//setHeaderListDetail(); deberia cambiar de grilla
		    }
		}
	    } else {
		Advisor.messageBox("Debe completar el campo Observaciones","Aviso");
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar un Proveedor","Aviso");
	}
    }
    
    private boolean control() {
	boolean result = false;
	if (paymentOrder == null) {
	    if (rbtnConcepto.isSelected()) {
		Advisor.messageBox("Antes de asignar algún Concepto,\ndebe fijar el Proveedor", "Aviso");
		tabbedPane.setSelectedIndex(0);
	    } else if (rbtnFacturas.isSelected()) {
	        Advisor.messageBox("Debe fijar el Proveedor", "Aviso");
	        tabbedPane.setSelectedIndex(0);
	    } else if (rbtnMonto.isSelected()) {
	        Advisor.messageBox("Antes de asignar un Monto,\ndebe fijar el Proveedor", "Aviso");
	        tabbedPane.setSelectedIndex(0);
	    } else {
		result = true;
	    }
	} 
	return result;
    }

    private void tfDetailPartialAmount_propertyChange(PropertyChangeEvent e) {
        if (tfDetailPartialAmount.getAmount() == 0) {
            tfDetailPartialAmount.setBackground(Color.LIGHT_GRAY);
        } else {
            tfDetailPartialAmount.setBackground(Color.GREEN);
        }
    }
    
    /*private boolean getPrivilegeAccess(){
	//nombre de grupo asignado para acceder solo a op adelantadas
	String params = "'mimi'";
	boolean retorno = LibSQL.getBoolean("org.getprivilegeaccess",params);
	return retorno;
    }*/
}
