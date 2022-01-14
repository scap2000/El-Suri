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

public class PaymentOrderMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel();
    private BasicPanel jpImputations = new BasicPanel();
    private BasicPanel jpDeductions = new BasicPanel();
    private BasicPanel jpPayments = new BasicPanel();
    private BasicLabel jLabel2 = new BasicLabel();
    private BasicTabbedPane tabbedPane = new BasicTabbedPane();
    private int[] sizeColumnList = { 536, 97 };
    private Vector dataRowDetail = new Vector();
    private GridPanel listDetailPanel = new GridPanel(30, sizeColumnList, "", dataRowDetail);
    private Vector headerListDetail = new Vector();
    private int[] sizeColumnListDeduction = { 542, 91 };
    private Vector dataRowDeduction = new Vector();
    private GridPanel listDeductionPanel = new GridPanel(30, sizeColumnListDeduction, "", dataRowDeduction);
    private Vector headerListDeduction = new Vector();
    private BasicPanel itemDeductionPanel = new BasicPanel();
    private BasicPanel itemDetailPanel = new BasicPanel();
    private BasicPanel detailPartialAmountPanel = new BasicPanel();
    private BasicPanel deductionPartialAmountPanel = new BasicPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    private JMoneyEntry tfDetailPartialAmount = new JMoneyEntry();
    private BasicLabel jLabel1 = new BasicLabel();
    private AssignButton btnAddDetail = new AssignButton(true);
    private AssignButton btnAddDeduction = new AssignButton(true);
    private FindButton btnFindProvider = new FindButton();
    private JMoneyEntry tfDeductionPartialAmount = new JMoneyEntry();
    private CBInput cbProvider = new CBInput(0, "Provider", false);
    private CBInput cbAccountDeduction = new CBInput(0, "Deductions", false);
    private CBInput cbIva = new CBInput(0, "VATp", false);
    private CBInput cbDeduction = new CBInput(0, "DeductionPercent", false);
    private CBInput cbVouchers = new CBInput(0, "Invoices", false);
    private TFInput tfFindProvider = new TFInput(DataTypes.STRING, "FindProvider", false);
    private TFInput tfObservations = new TFInput(DataTypes.STRING, "Observations", true);
    private TFInput tfFindAccountDetail = new TFInput(DataTypes.STRING, "SearchButton", false);
    private TFInput tfDetailAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private TFInput tfFindAccountDeduction = new TFInput(DataTypes.STRING, "SearchButton", false);
    private TFInput tfDeductionAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private TFInput tfCUIT = new TFInput(DataTypes.STRING, "Number", false);
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfAmountOwed = new TFInput(DataTypes.MONEY, "Monto Adeudado", false);
    private PaymentOrder paymentOrder;
    private PaymentOrderDetail paymentOrderDetail;
    private PaymentOrderDeduction paymentOrderDeduction;
    private PrintSaveButton btnPrintSave = new PrintSaveButton();
    private RefreshGridButton btnRecoverPaymentOrder = new RefreshGridButton();
    private CloseButton btnClose = new CloseButton();
    private BasicPanel totalPanel = new BasicPanel();
    private BorderLayout borderLayout4 = new BorderLayout();
    private BasicRadioButton rbtnAccount = new BasicRadioButton();
    private BasicRadioButton rbtnVouchers = new BasicRadioButton();
    private BasicPanel container = new BasicPanel();
    private JMoneyEntry tfNetAmount = new JMoneyEntry();
    private BasicLabel jLabel3 = new BasicLabel();
    private BasicPanel jpAccountEntry = new BasicPanel();
    private BasicPanel payFormDataPanel = new BasicPanel();
    private Vector dataRowPaymentWay = new Vector();
    private GridPanel listPaymentWayPanel = new GridPanel(30, sizeColumnList, Environment.lang.getProperty("PaymentWay"), dataRowPaymentWay) {
	public void calculate() {
	    calculateNetAmount();
	}
    };
    private Vector headerListPaymentWay = new Vector();
    private BorderLayout borderLayout3 = new BorderLayout();
    private BasicRadioButton rbtnCash = new BasicRadioButton();
    private BasicRadioButton rbtnCheck = new BasicRadioButton();
    private TFInput tfPaymentWayAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private CBInput cbPaymentWay = new CBInput(0, "Accounting", false);
    private CBInput cbAccount = new CBInput(0, "Accounting", false);
    private AssignButton btnAddPaymentWay = new AssignButton(true);
    private PaymentOrderWay paymentOrderWay;
    private BorderLayout borderLayout5 = new BorderLayout();
    private int[] sizeColumnListBookKeepingEntry = { 244, 199, 96, 96 };
    private Vector dataRowBookKeepingEntry = new Vector();
    private GridPanel listBookKeepingEntryPanel = new GridPanel(50000, sizeColumnListBookKeepingEntry, "", dataRowBookKeepingEntry);
    private Vector headerListBookKeepingEntry = new Vector();
    private double netAmount;
    private double amountToPay = 0;
    private BasicPanel jPanel4 = new BasicPanel();
    private BasicLabel jLabel4 = new BasicLabel();
    private BasicLabel jLabel5 = new BasicLabel();
    private BasicLabel jLabel6 = new BasicLabel();
    private JMoneyEntry tfDebitAmount = new JMoneyEntry();
    private JMoneyEntry tfCreditAmount = new JMoneyEntry();
    private JMoneyEntry tfAvailableAmount = new JMoneyEntry();
    private BasicLabel jLabel7 = new BasicLabel();
    private JMoneyEntry tfAmountToPay = new JMoneyEntry();
    private Account account;
    private Voucher voucher;
    private Check check;
    private int selectAccountType = -1;
    private final int ACCOUNTDETAIL = 1;
    private final int ACCOUNTDEDUCTION = 2;
    private final int ACCOUNTCASH = 3;
    private final int ACCOUNTACCOUNT = 4;
    private final int ACCOUNTDETAIL_NEWVOUCHER = 5;
    private TFInput tfPercentage = new TFInput(DataTypes.PERCENT, "%", false);
    private AddButton btnAddProvider = new AddButton();
    private DeleteButton btnDeleteDetail = new DeleteButton();
    private DeleteButton btnDeleteDeduction = new DeleteButton();
    private DeleteButton btnDeletePaymentWay = new DeleteButton();
    private TFInput tfCheckDate = new TFInput(DataTypes.DATE, "ExpirationDate", false);
    private Vector<Check> checksVector = new Vector<Check>();
    private CBInput cbCheckNumber = new CBInput(0, "CheckNumber", false);
    private int idBudget = -1;

    public PaymentOrderMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(684, 514));
	this.setPreferredSize(new Dimension(590, 500));
	container.setLayout(borderLayout4);
	tfNetAmount.setBounds(new Rectangle(100, 10, 115, 20));
	jLabel3.setText(Environment.lang.getProperty("NetAmount") + ":");
	jLabel3.setBounds(new Rectangle(5, 10, 92, 20));
	jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
	jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel3.setSize(new Dimension(92, 20));
	jpAccountEntry.setLayout(borderLayout5);
	//btnNewVoucher.setBounds(new Rectangle(400, 45, 20, 20));
	dataPanel.setLayout(null);
	dataPanel.setPreferredSize(new Dimension(1, 150));
	jpImputations.setLayout(borderLayout1);
	jpDeductions.setLayout(borderLayout2);
	jpPayments.setLayout(borderLayout3);
	jpPayments.setPreferredSize(new Dimension(440, 1));
	detailPartialAmountPanel.setLayout(null);
	detailPartialAmountPanel.setPreferredSize(new Dimension(1, 40));
	itemDetailPanel.setLayout(null);
	itemDetailPanel.setPreferredSize(new Dimension(1, 50));
	itemDetailPanel.setBounds(new Rectangle(0, 0, 679, 90));
	itemDetailPanel.setSize(new Dimension(679, 90));
	deductionPartialAmountPanel.setLayout(null);
	deductionPartialAmountPanel.setPreferredSize(new Dimension(1, 30));
	itemDeductionPanel.setLayout(null);
	itemDeductionPanel.setPreferredSize(new Dimension(1, 55));
	tfDetailPartialAmount.setBounds(new Rectangle(555, 10, 115, 20));
	jLabel1.setText(Environment.lang.getProperty("PartialAmount") + ":");
	jLabel1.setBounds(new Rectangle(470, 10, 84, 20));
	jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel1.setSize(new Dimension(84, 20));
	btnAddDetail.setBounds(new Rectangle(475, 25, 20, 20));
	/*btnNewVoucher.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnNewVoucher_actionPerformed(e);
				     }

				 }
	);*/
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
	cbVouchers.setBounds(new Rectangle(5, 10, 315, 35));
	tfDetailAmount.setEnabled(false);
	btnAddDetail.setEnabled(false);
	tfDetailAmount.setBounds(new Rectangle(330, 10, 135, 35));
	/*btnFindDetail.setBounds(new Rectangle(435, 45, 20, 20));
	btnFindDetail.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnFindDetail_actionPerformed(e);
				     }

				 }
	);*/
	tfFindAccountDeduction.setBounds(new Rectangle(5, 10, 90, 35));
	cbAccountDeduction.setBounds(new Rectangle(100, 10, 265, 35));
        cbIva.setBounds(new Rectangle(370, 10, 60, 35));
        cbDeduction.setBounds(new Rectangle(435, 10, 60, 35));
	tfDeductionAmount.setBounds(new Rectangle(500, 10, 125, 35));
	/*btnFindDeduction.setBounds(new Rectangle(400, 25, 20, 20));
	btnFindDeduction.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnFindDeduction_actionPerformed(e);
					}

				    }
	);*/
	tfCUIT.setBounds(new Rectangle(520, 65, 150, 35));
	tfDate.setBounds(new Rectangle(20, 25, 100, 35));
	//cbCostCentre.setBounds(new Rectangle(185, 25, 290, 35));
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
	//itemDetailPanel.add(btnNewDetail, null);
	//itemDetailPanel.add(btnNewVoucher, null);
	//itemDetailPanel.add(btnFindDetail, null);
	detailPartialAmountPanel.add(jLabel1, null);
	detailPartialAmountPanel.add(tfDetailPartialAmount, null);
	itemDetailPanel.add(btnDeleteDetail, null);
	itemDetailPanel.add(tfDetailAmount, null);
	itemDetailPanel.add(cbVouchers, null);
	//itemDetailPanel.add(tfFindAccountDetail, null);
	itemDetailPanel.add(btnAddDetail, null);
	itemDetailPanel.add(rbtnVouchers, null);
	itemDetailPanel.add(rbtnAccount, null);
	//itemDeductionPanel.add(btnNewDeduction, null);
	//itemDetailPanel.add(cbAccount, null);
        itemDeductionPanel.add(btnDeleteDeduction, null);
        //itemDeductionPanel.add(btnFindDeduction, null);
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
	jPanel4.add(jLabel5, null);
	jPanel4.add(jLabel4, null);
	jpAccountEntry.add(jPanel4, BorderLayout.SOUTH);
	jpAccountEntry.add(listBookKeepingEntryPanel, BorderLayout.CENTER);
	tabbedPane.addTab(Environment.lang.getProperty("AccountingEntry"), jpAccountEntry);
	jpPayments.add(listPaymentWayPanel, BorderLayout.CENTER);
	jpPayments.add(totalPanel, BorderLayout.SOUTH);
	payFormDataPanel.add(cbCheckNumber, null);
	//payFormDataPanel.add(btnNewPaymentWay, null);
	payFormDataPanel.add(tfCheckDate, null);
	//payFormDataPanel.add(tfCheckNumber, null);
	payFormDataPanel.add(btnDeletePaymentWay, null);
	payFormDataPanel.add(btnAddPaymentWay, null);
	payFormDataPanel.add(cbPaymentWay, null);
	//payFormDataPanel.add(rbtnCurrentAccount, null);
	payFormDataPanel.add(tfPaymentWayAmount, null);
	payFormDataPanel.add(rbtnCheck, null);
	payFormDataPanel.add(rbtnCash, null);
	jpPayments.add(payFormDataPanel, BorderLayout.NORTH);
	//dataPanel.add(btnAddProvider, null);
	//dataPanel.add(chkCostCentre, null);
	//dataPanel.add(btnFindProvider, null);
	//dataPanel.add(cbCostCentre, null);
	dataPanel.add(tfAmountOwed, null);
	dataPanel.add(tfDate, null);
	dataPanel.add(tfCUIT, null);
	dataPanel.add(cbProvider, null);
	dataPanel.add(tfFindProvider, null);
	dataPanel.add(tfObservations, null);
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
	//btnNewVoucher.setVisible(false);
	//chkCostCentre.setBounds(new Rectangle(645, 35, 25, 25));
	/*chkCostCentre.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 chkCostCentre_actionPerformed(e);
				     }

				 }
	);*/
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
	/*rbtnCurrentAccount.setText(Environment.lang.getProperty("CurrentAccount"));
	rbtnCurrentAccount.setBounds(new Rectangle(558, 5, 117, 18));
	rbtnCurrentAccount.setSize(new Dimension(117, 18));
	rbtnCurrentAccount.addActionListener(new ActionListener() {

					  public void actionPerformed(ActionEvent e) {
					      rbtnCurrentAccount_actionPerformed(e);
					  }

				      }
	);*/
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
	jLabel4.setBounds(new Rectangle(0, 10, 97, 20));
	jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
	jLabel5.setText(Environment.lang.getProperty("CreditAmount") + ":");
	jLabel5.setBounds(new Rectangle(235, 10, 101, 20));
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
	//cbCostCentre.autoSize();
	cbVouchers.autoSize();
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
	btnDeleteDetail.setBounds(new Rectangle(510, 25, 20, 20));
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
	/*btnNewPaymentWay.setBounds(new Rectangle(625, 45, 20, 20));
	btnNewPaymentWay.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnNewPaymentWay_actionPerformed(e);
				     }

				 }
	);*/
	/*btnNewDetail.setBounds(new Rectangle(620, 45, 20, 20));
	btnNewDetail.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnNewDetail_actionPerformed(e);
				    }

				}
	);*/
	/*btnNewDeduction.setBounds(new Rectangle(620, 25, 20, 20));
	btnNewDeduction.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnNewDeduction_actionPerformed(e);
				       }

				   }
	);*/
	//tfCheckNumber.setBounds(new Rectangle(125, 0, 100, 35));
	tfCheckDate.setBounds(new Rectangle(380, 30, 100, 35));
	cbCheckNumber.setBounds(new Rectangle(270, 30, 100, 35));
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
						      }
						  }

					      }
	);
	/*tfFindAccountDetail.getTextField().addKeyListener(new KeyAdapter() {

						       public void keyTyped(KeyEvent e) {
							   if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							       loadComboAccount(-1, tfFindAccountDetail.getString());
							   }
						       }

						   }
	);*/
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
						   loadComboVoucher();
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
	cbVouchers.getCombo().addItemListener(new ItemListener() {

					   public void itemStateChanged(ItemEvent evt) {
					       if (evt.getStateChange() == ItemEvent.SELECTED) {
						   tfDetailAmount.setValue(Double.parseDouble(cbVouchers.getSelectedValueRef().toString().split("#")[0]));
						   btnAddDetail.setEnabled(tfDetailAmount.getAmount() > 0 && cbAccount.getSelectedIndex() != -1);
						   /*if (rbtnVouchers.isSelected()) {
							    tfDetailAmount.setValue(Double.parseDouble(cbAccountDetail.getSelectedValueRef().toString().split("#")[0]));
							    btnAddDetail.setEnabled(tfDetailAmount.getAmount() >= 0);
							} else {
							    tfDetailAmount.setValue(Double.parseDouble(cbAccountDetail.getSelectedValueRef().toString().split("#")[0]));
							    btnAddDetail.setEnabled(tfDetailAmount.getAmount() >= 0);
							}*/
					       }
					   }

				       }
	);
	cbAccount.getCombo().addItemListener(new ItemListener() {

					  public void itemStateChanged(ItemEvent evt) {
					      if (evt.getStateChange() == ItemEvent.ITEM_STATE_CHANGED) {
						  tfDetailAmount.setValue(Double.parseDouble(cbVouchers.getSelectedValueRef().toString().split("#")[0]));
						  btnAddDetail.setEnabled(tfDetailAmount.getAmount() > 0 && cbAccount.getSelectedIndex() != -1);
					      }
					  }

				      }
	);
	tfDetailAmount.getTextField().addKeyListener(new KeyAdapter() {

						  public void keyTyped(KeyEvent e) {
						      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							  saveDetail();
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
                                                   loadComboVoucher();*/
                                                   calcDeduction();
                                               }
                                           }

                                       }
        );
        cbDeduction.getCombo().addItemListener(new ItemListener() {

                                           public void itemStateChanged(ItemEvent evt) {
                                               if (evt.getStateChange() == ItemEvent.SELECTED) {
                                                   /*tfCUIT.setValue(cbProvider.getSelectedValueRef().toString().split("#")[0]);
                                                   loadComboVoucher();*/
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
	//rbtnPaymentWay.add(rbtnCurrentAccount);
	setHeaderListDetail();
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
					  /*tfPercentage.getTextField().addKeyListener(new PercentageToValueListen(tfDeductionAmount, totalAmount));
					  tfDeductionAmount.getTextField().addKeyListener(new ValueToPercentageListen(tfPercentage, totalAmount));
					  tfPercentage.setValue(0.0);
					  tfDeductionAmount.setValue(0.0);
        				  double totalAmount = tfDetailPartialAmount.getAmount();
                                          double myAmount = (totalAmount/(Double.parseDouble(cbIva.getSelectedValueRef().toString())) ) * (Double.parseDouble(cbDeduction.getSelectedValueRef().toString())/100.0);
				          tfDeductionAmount.setValue(myAmount);*/
                                          calcDeduction();
				      }
				  }

			      }
	);
	cbCheckNumber.autoSize();
	tabbedPane.addChangeListener(new ChangeListener() {

				  public void stateChanged(ChangeEvent changeEvent) {
				      if (paymentOrder == null && tabbedPane.getSelectedIndex() != 0) {
					  Advisor.messageBox("Orden de pago incompleta", "Aviso");
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
	listPaymentWayPanel.setSortable(false);
	//tfAmountOwed.setEnabled(false);
	tfAmountOwed.setEditable(false);
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

    private void loadComboVoucher() {
	if (!cbProvider.getSelectedValue().equals("-1")) {
	    String param = "''," + cbProvider.getSelectedValue() + "," + (paymentOrder != null ? paymentOrder.getIdPaymentOrder() : -1) +","+ idBudget;
	    cbVouchers.loadJCombo(LibSQL.exFunction("cashflow.getAllVouchersNotPaid", param));
	    tfDetailAmount.setValue(Format.toDouble(Double.parseDouble(cbVouchers.getSelectedValueRef().toString().split("#")[0])));
	    //btnAddDetail.setEnabled(tfDetailAmount.getAmount() > 0 && cbAccount.getSelectedIndex() != -1);
	    //Matias
	    btnAddDetail.setEnabled(Format.toDouble(tfDetailAmount.getAmount()).doubleValue() > 0 && cbAccount.getSelectedIndex() != -1);
	} else {
	    Advisor.messageBox("Proveedor requerido.", "Datos requeridos");
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
	    tfAmountOwed.getTextField().setBackground(new Color(130,230,70));
	}
	
    }

    private void loadComboProvider() {
	String param = "'" + tfFindProvider.getString() + "'";
	cbProvider.loadJCombo(LibSQL.exFunction("org.getallproviderswithcreditbalancebyfilter", param));
	tfCUIT.setValue(cbProvider.getSelectedValueRef().toString().split("#")[0]);
	if (cbProvider.getSelectedIndex() != -1) {
	    loadComboVoucher();
	    loadComboAccount(cbProvider.getSelectedValueRef().toString().split("#")[1]);
	}
	loadAmountOwed();
    }

    private void loadComboAccount(String _idAccount) {
	cbAccount.loadJCombo(LibSQL.exFunction("accounting.getAccount", _idAccount));
	btnAddDetail.setEnabled(tfDetailAmount.getAmount() > 0 && cbAccount.getSelectedIndex() != -1);
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

    private void setHeaderListDetail() {
	headerListDetail.removeAllElements();
	headerListDetail.addElement("*");
	headerListDetail.addElement("*");
	headerListDetail.addElement("*");
	headerListDetail.addElement("*");
	headerListDetail.addElement(Environment.lang.getProperty("Concept"));
	headerListDetail.addElement("*");
	headerListDetail.addElement("*");
	headerListDetail.addElement("*");
	headerListDetail.addElement("*");
	headerListDetail.addElement("*");
	headerListDetail.addElement("*");
	headerListDetail.addElement("*");
	headerListDetail.addElement("*");
	headerListDetail.addElement(Environment.lang.getProperty("Amount"));
	listDetailPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     loadObjectDetail();
						     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
							 int index = listDetailPanel.getTable().rowAtPoint(e.getPoint());
							 listDetailPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
						     }
						 }

					     }
	);
	listDetailPanel.setParams("cashflow.getAllPaymentOrderDetail", "-1", headerListDetail);
    }

    public void refreshDetail() {
	if (paymentOrder != null) {
	    String params = "" + paymentOrder.getIdPaymentOrder();
	    listDetailPanel.refresh(params);
	    btnDeleteDetail.setEnabled(false);
	} else {
	    Advisor.messageBox("Proveedor requerido.", "Datos requeridos");
	}
    }

    private void loadObjectDetail() {
	if (!dataRowDetail.isEmpty()) {
	    paymentOrderDetail = new PaymentOrderDetail();
	    paymentOrderDetail.setIdPaymentOrderDetail(Integer.parseInt("" + dataRowDetail.elementAt(0)));
	    paymentOrderDetail.setPaymentOrder(new PaymentOrder(Integer.parseInt("" + dataRowDetail.elementAt(1))));
	    paymentOrderDetail.setVoucher(new Voucher(Integer.parseInt("" + dataRowDetail.elementAt(2))));
	    paymentOrderDetail.setAccount(new Account(Integer.parseInt("" + dataRowDetail.elementAt(3))));
	    paymentOrderDetail.setAmount(Double.parseDouble("" + dataRowDetail.elementAt(5)));
	    paymentOrderDetail.setDescription("" + dataRowDetail.elementAt(6));
	    loadDetailData();
	}
    }

    private void loadDetailData() {
	//cbVouchers.removeAllItems();
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
	//cbVouchers.getCombo().addItem("" + dataRowDetail.elementAt(4), "" + idConcept);
	//tfDetailAmount.setValue(paymentOrderDetail.getAmount());
	btnDeleteDetail.setEnabled(true);
    }

    private boolean saveDetail() {
	boolean result = true;
	if (paymentOrder == null) {
	    result = saveHeaderData();
	}
	if (result) {
	    if (paymentOrderDetail == null) {
		paymentOrderDetail = new PaymentOrderDetail();
	    }
	    int idConcept = Integer.parseInt("" + cbVouchers.getSelectedValue());
	    paymentOrderDetail.setAccount(new Account(Integer.parseInt(cbAccount.getSelectedValue().toString())));
	    paymentOrderDetail.setVoucher(new Voucher(idConcept));
	    /*if (rbtnAccount.isSelected()) {
		paymentOrderDetail.setAccount(new Account(idConcept));
		paymentOrderDetail.setVoucher(new Vouchers());
	    } else {
		paymentOrderDetail.setAccount(new Account());
		paymentOrderDetail.setVoucher(new Vouchers(idConcept));
	    }*/
	    idBudget = Integer.parseInt(cbVouchers.getSelectedValueRef().toString().split("#")[2]);
	    paymentOrderDetail.setPaymentOrder(paymentOrder);
	    //paymentOrderDetail.setAmount(tfDetailAmount.getAmount());
	     //Matias
	    paymentOrderDetail.setAmount(Format.toDouble(tfDetailAmount.getAmount()).doubleValue());
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
	} else {
	    return false;
	}
    }

    private void updateDetailAmount() {
	//tfDetailPartialAmount.setValue(paymentOrder.updateDetailAmount());
	//Matias
	tfDetailPartialAmount.setValue(Format.toDouble(paymentOrder.updateDetailAmount()).doubleValue());
	netAmount = tfDetailPartialAmount.getAmount() - tfDeductionPartialAmount.getAmount();
	//Matias
	netAmount = Format.toDouble(netAmount).doubleValue();
	tfNetAmount.setValue(netAmount);
	tfPaymentWayAmount.setValue(netAmount);
	paymentOrder.setAmount(netAmount);
	paymentOrderDetail = null;
	calcDeduction();
	refreshDetail();
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
	//tfDebitAmount.setValue(tfDetailPartialAmount.getAmount());
	//Matias
	tfDebitAmount.setValue(Format.toDouble(tfDetailPartialAmount.getAmount()).doubleValue());
	 
	//tfCreditAmount.setValue(tfDeductionPartialAmount.getAmount() + tfAmountToPay.getAmount());
	//Matias
	 tfCreditAmount.setValue(Format.toDouble(tfDeductionPartialAmount.getAmount() + tfAmountToPay.getAmount()).doubleValue());
	 
	double available = Math.abs(tfDebitAmount.getAmount() - tfCreditAmount.getAmount());
	//Matias
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
	loadComboVoucher();
    }

    private void enablePayMethod(boolean _cash, boolean _check) {
	//, boolean _currentAccount) {
	rbtnCash.setSelected(_cash);
	rbtnCheck.setSelected(_check);
	//rbtnCurrentAccount.setSelected(_currentAccount);
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
	cbVouchers.removeAllItems();
	if (account != null) {
	    cbVouchers.getCombo().addItem(account.getName(), "" + account.getIDAccount());
	} else if (voucher != null) {
	    cbVouchers.getCombo().addItem(voucher.getFullNumber(), "" + voucher.getIdVoucher());
	    //tfDetailAmount.setValue(voucher.getAmount());
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
		loadComboVoucher();
		cbVouchers.setSelectedID("" + voucher.getIdVoucher());
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
    /*private void btnFindDeduction_actionPerformed(ActionEvent e) {
	selectAccountType = ACCOUNTDEDUCTION;
	loadAccount();
    }*/

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
	    if (paymentOrder.saveData() >= 0) {
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
	    Advisor.messageBox("El monto de retenciones debe ser mayor que cero\ny debe seleccionar una Cuenta para aplicar la Retención", "Error");
	}
    }

    private void btnAddDetail_actionPerformed(ActionEvent e) {
	if (!tfObservations.getString().trim().equals("")) {
	    saveDetail();
	    enabledHeader(false);
	    Environment.addUnfinishedTask(this.getParentInternalFrame());
	} else {
	    Advisor.messageBox("Debe completar el campo Observaciones", "Error");
	}
    }

    private void btnFindProvider_actionPerformed(ActionEvent e) {
	ProvidersMain providersMain = new ProvidersMain();
	ExtendedInternalFrame providersMainContainer = new ExtendedInternalFrame("Proveedores");
	providersMainContainer.setCentralPanel(providersMain);
	providersMainContainer.show();
    }

    private void rbtnAccount_actionPerformed(ActionEvent e) {
	cbVouchers.removeAllItems();
	cbVouchers.setSComponentName("Accounting");
	//btnNewVoucher.setVisible(false);
    }

    private void rbtnVouchers_actionPerformed(ActionEvent e) {
	/*if (!cbProvider.getSelectedValue().equals("-1")) {
	    cbAccountDetail.removeAllItems();
	    cbAccountDetail.setSComponentName("Invoices");
	    loadComboVoucher();
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
	    getParentInternalFrame().setIcon(true);
	}
    }

    private void clearData() {
	//chkCostCentre.setSelected(false);
	//loadCostCentre(false);
	tfFindProvider.setValue("");
	cbProvider.removeAllItems();
	tfCUIT.setValue("");
	tfObservations.setValue("");
	tfFindAccountDetail.setValue("");
	cbVouchers.removeAllItems();
	tfDetailAmount.setValue(0.0);
	tfDetailAmount.setEnabled(false);
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
	tfAvailableAmount.setBackground(Color.RED);
	tabbedPane.setSelectedIndex(0);
	enablePayMethod(true, false);
	//, false);
	cbPaymentWay.setSComponentName("Cash");
	cbPaymentWay.removeAllItems();
	paymentOrder = null;
	listDetailPanel.refresh("'', -1");
	listDeductionPanel.refresh("-1");
	listPaymentWayPanel.refresh("-1, -1");
	listBookKeepingEntryPanel.refresh("-1");
	amountToPay = 0;
	netAmount = 0;
	checksVector.removeAllElements();
	enabledHeader(true);
	clearCheckData(true);
        idBudget = -1;
        cbVouchers.getCombo().removeAllItems();
	tfAmountOwed.setValue(0.0);
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	if (paymentOrder != null) {
	    cancelPaymentWay();
	} else {
	    getParentInternalFrame().setIcon(true);
	}
    }

    private void btnPrintSave_actionPerformed(ActionEvent e) {
	saveData();
    }

    public boolean saveData() {
	boolean returns = false;
	if (paymentOrder != null) {
	    //if ( Math.abs(tfCreditAmount.getAmount() - tfDebitAmount.getAmount()) < 0.001 && tfDebitAmount.getAmount() > 0) {
	    //Matias
	    if ( Format.toDouble(Math.abs(tfCreditAmount.getAmount() - tfDebitAmount.getAmount())).doubleValue() < 0.001 && Format.toDouble(tfDebitAmount.getAmount()).doubleValue() > 0) {
		//if (paymentOrder.approve()) {
		if (paymentOrder.approveByFacturas()) {
		    print();
		    //if (tfDeductionPartialAmount.getAmount() > 0) {
		    //Matias
		    /* 
		     * Se reemplazo estas lineas porque corresponden a lo constancia de Retencion de la Provincia
		    if (Format.toDouble(tfDeductionPartialAmount.getAmount()).doubleValue() > 0) {
			if (Advisor.question("Constancia de Retención DGR", "¿Desea imprimir la Constancia de Retención DGR?")) {
			    BasicReport report = new BasicReport(PaymentOrderMgmt.class.getResource("xml/DeductionTaxVoucher.xml"));
			    report.addTableModel("cashflow.xmlGetDeductionTaxVoucher", "" + paymentOrder.getIdPaymentOrder());
			    report.setProperty("textamount", Format.NumberToText.numberToText(tfDeductionPartialAmount.getAmount()) + ".-");
			    report.setProperty("organizacion",Environment.organization);
			    report.doReport();
			}
		    }
		    */
		    // Cesar
		     if (Advisor.question("Constancia de Retención", "¿Desea imprimir la Constancia de Retención?")) {
		         BasicReport report = new BasicReport(PaymentOrderMgmt.class.getResource("xml/MosconiDeductionTaxVoucher.xml"));
		         report.addTableModel("cashflow.xmlGetDeductionTaxVoucher", "" + 61);
		         report.setProperty("textamount", Format.NumberToText.numberToText(tfDeductionPartialAmount.getAmount()) + ".-");
		         report.setProperty("organizacion",OrganizationInfo.getOrgName());
		         report.setProperty("localidad",OrganizationInfo.getLocation());
		         report.setProperty("nrocomprobante","");
		         report.doReport();
		     }
		    clearData();
		    getParentInternalFrame().setIcon(true);
		    Environment.removeUnfinishedTask(this.getParentInternalFrame());
		    returns = true;
		} else {
		    Advisor.messageBox("Ocurrió un error mientras se guardaba\nla Orden de Pago Nº" + paymentOrder.getNumber(), "Error al guardar");
		}
	    } else {
		Advisor.messageBox("El asiento no está balanceado", "Aviso");
	    }
	} else {
	    Advisor.messageBox("Orden de Pago incompleta", "Aviso");
	}
	return returns;
    }

    private void btnNewVoucher_actionPerformed(ActionEvent e) {
	/*selectAccountType = ACCOUNTDETAIL_NEWVOUCHER;
	voucher = new Vouchers();
	VouchersMgmt vouchersMgmt = new VouchersMgmt();
	vouchersMgmt.setEntity(EntityTypes.PROVIDER, Integer.parseInt("" + cbProvider.getSelectedValue()), cbProvider.getSelectedItem().toString());
	vouchersMgmt.setEnabledCostCentre(false);
	//chkCostCentre.isSelected());
	vouchersMgmt.setVoucher(voucher);
	vouchersMgmt.setParentPanel(this);
	vouchersMgmt.setCloseVoucher(true);
	vouchersMgmt.setVoucherType(VoucherType.INVOICE_A);
	/*if (chkCostCentre.isSelected()) {
	    vouchersMgmt.setCostCentre(new CostsCentre(Integer.parseInt("" + cbCostCentre.getSelectedValue())));
	}*/
	/*ExtendedInternalFrame vouchersMgmtContainer = new ExtendedInternalFrame("Nueva Factura");
	vouchersMgmtContainer.setCentralPanel(vouchersMgmt);
	vouchersMgmtContainer.show();*/
    }
    /*private void loadCostCentre(boolean _load) {
	cbCostCentre.setEnabled(_load);
	if (_load) {
	    cbCostCentre.setCombo(CachedCombo.getCachedCombo(CachedCombo.COSTSCENTRE));
	} else {
	    cbCostCentre.removeAllItems();
	}
    }*/
    /*private void chkCostCentre_actionPerformed(ActionEvent e) {
	if (chkCostCentre.isSelected()) {
	    loadCostCentre(true);
	} else {
	    loadCostCentre(false);
	}
    }*/

    private void clearCheckData(boolean enabled) {
	//tfCheckNumber.setEditable(_op);
	cbCheckNumber.setEnabled(enabled);
	//tfCheckNumber.setValue("");
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
	//cbCostCentre.setEnabled(_valor);
	tfCUIT.setEnabled(_valor);
	btnAddProvider.setEnabled(_valor);
	btnFindProvider.setEnabled(_valor);
	tfObservations.setEnabled(_valor);
    }
    /*private void btnNewDetail_actionPerformed(ActionEvent e) {
	paymentOrderDetail = null;
	tfFindAccountDetail.setValue("");
	cbAccountDetail.removeAllItems();
	tfDetailAmount.setValue(0.0);
    }*/
    /*private void btnNewDeduction_actionPerformed(ActionEvent e) {
	paymentOrderDeduction = null;
	tfFindAccountDeduction.setValue("");
	cbAccountDeduction.removeAllItems();
	tfDeductionAmount.setValue(0.0);
	tfPercentage.setValue(0.0);
    }*/
    /* private void btnNewPaymentWay_actionPerformed(ActionEvent e) {
	paymentOrderWay = null;
	cbPaymentWay.removeAllItems();
	tfPaymentWayAmount.setValue(0.0);
	netAmount = tfAmountToPay.getAmount();
    }*/

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
			    Advisor.messageBox("No se han encontrado Órdenes de pago para recuperar", "Aviso");
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
			Advisor.messageBox("No se han encontrado Órdenes de pago para recuperar", "Aviso");
		    }
		} catch (SQLException f) {
		    // TODO
		}
	    } else {
		Advisor.messageBox("No se han encontrado Órdenes de pago para recuperar", "Aviso");
	    }
	}
    }
    
    private void calcDeduction(){
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

}
