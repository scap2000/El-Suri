package org.digitall.common.cashflow.interfaces.cashmovement;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.digitall.common.cashflow.classes.CashMovement;
import org.digitall.common.cashflow.classes.CashMovementType;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.cashflow.interfaces.voucher.VouchersList;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class CashMovementsMgmt extends BasicPrimitivePanel {

    private BasicPanel jPanel1 = new BasicPanel("Agregar/Modificar Movimiento de Fondo");
    private TFInput tfFindVoucher = new TFInput(DataTypes.INTEGER, "FindVoucher", false);
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfAmount = new TFInput(DataTypes.MONEY, "Amount", false);
    private CBInput cbCashMovementType = new CBInput(CachedCombo.CASHMOVEMENTTYPE, "CashMovementType");
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre");
    private SaveDataButton btnAccept = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
     
    private CBInput cbVoucher = new CBInput(0,"Vouchers",false);
    private CashMovement cashMovement;
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private CashMovementsList parentList;
    private FindButton btnFindVoucher = new FindButton();
    private BasicCheckBox chkVoucher = new BasicCheckBox();
    private Voucher voucher;
    private String voucherNumber;
    private VouchersList voucherList;
    private TFInput tfAmountP = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfProjAmount = new TFInput(DataTypes.MONEY, "ProjectedAmount", false);
    private TFInput tfProjAmountP = new TFInput(DataTypes.PERCENT, "%", false);
    private TFInput tfDesvioAmount = new TFInput(DataTypes.MONEY, "DesviationAmount", false);

    public CashMovementsMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(520, 280));
	this.setPreferredSize(new Dimension(520, 280));
	jPanel1.setBounds(new Rectangle(5, 0, 530, 275));
	jPanel1.setLayout(null);
	tfFindVoucher.setBounds(new Rectangle(45, 110, 145, 35));
	tfDate.setBounds(new Rectangle(420, 30, 85, 35));
	tfAmount.setBounds(new Rectangle(15, 70, 105, 35));
	cbCashMovementType.setBounds(new Rectangle(15, 30, 270, 35));
	cbCostsCentre.setBounds(new Rectangle(15, 155, 490, 35));
	btnAccept.setBounds(new Rectangle(445, 295, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnClose.setBounds(new Rectangle(495, 295, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	cbVoucher.setBounds(new Rectangle(210, 110, 255, 35));
	tfDescription.setBounds(new Rectangle(15, 195, 490, 35));
	btnFindVoucher.setBounds(new Rectangle(475, 120, 40, 25));
	btnFindVoucher.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnFindVoucher_actionPerformed(e);
				      }

				  }
	);
	chkVoucher.setBounds(new Rectangle(15, 125, 25, 20));
	chkVoucher.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      chkVoucher_actionPerformed(e);
				  }

			      }
	);
	tfAmountP.setBounds(new Rectangle(125, 70, 60, 35));
	tfProjAmount.setBounds(new Rectangle(205, 70, 105, 35));
	tfProjAmountP.setBounds(new Rectangle(315, 70, 60, 35));
	tfDesvioAmount.setBounds(new Rectangle(400, 70, 105, 35));
	jPanel1.add(tfDesvioAmount, null);
	jPanel1.add(tfProjAmountP, null);
	jPanel1.add(tfProjAmount, null);
	jPanel1.add(tfAmountP, null);
	jPanel1.add(chkVoucher, null);
	jPanel1.add(btnFindVoucher, null);
	jPanel1.add(tfDescription, null);
	jPanel1.add(cbVoucher, null);
	jPanel1.add(cbCostsCentre, null);
	jPanel1.add(tfAmount, null);
	jPanel1.add(tfDate, null);
	jPanel1.add(tfFindVoucher, null);
	jPanel1.add(cbCashMovementType, null);
	this.add(jPanel1, null);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	
	cbCashMovementType.autoSize();
	cbCostsCentre.autoSize();
	cbVoucher.autoSize();
	
	tfProjAmountP.setEnabled(false);
	tfAmountP.setEnabled(false);
	tfDesvioAmount.setEnabled(false);
	
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfFindVoucher.getTextField().addKeyListener(new KeyAdapter() {

					public void keyTyped(KeyEvent e) {
					    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
						loadComboVoucher();
					    }
					}

				    }
	);
	setEnabledVoucher(false);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void loadComboVoucher(){
	JCombo combo = new JCombo();
	String param = "'" + tfFindVoucher.getString() + "'";
	combo.loadJCombo(LibSQL.exFunction("cashflow.getAllVouchersByFilter", param));
	cbVoucher.setCombo(combo);        
	ItemListener itemListener = new ItemListener() {
		    public void itemStateChanged (ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    setVoucherData();
			}
		    }
		};
	if (combo.getItemCount() != 0){
	    cbVoucher.getCombo().addItemListener(itemListener);
	    setVoucherData();
	    cbVoucher.updateUI();
	} else {
	    Advisor.messageBox("No existen facturas a referenciar", "Facturas inexistentes");
	    chkVoucher.setSelected(false);
	    setEnabledVoucher(false);
	}
    }
    
    private void setVoucherData(){
	String[] value = cbVoucher.getSelectedValueRef().toString().split("#");
	tfProjAmount.setValue(Double.parseDouble(value[0]));
	tfDate.setValue(Proced.setFormatDate(value[1], true));
    }
    
    private void loadData(){
	tfAmount.setValue(cashMovement.getAmount());
	tfAmountP.setValue(cashMovement.getAmountP());
	tfProjAmount.setValue(cashMovement.getProjAmount());
	tfProjAmountP.setValue(cashMovement.getProjAmountP());
	tfDesvioAmount.setValue(cashMovement.getDesviationAmount());
	tfDate.setValue(Proced.setFormatDate(cashMovement.getDate(), true));
	cbCashMovementType.setSelectedID(""+ cashMovement.getCashMovementType().getIdCashMovementType());
	cbCostsCentre.setSelectedID(""+ cashMovement.getCostsCentre().getIdCostCentre());
	cbVoucher.setSelectedID(""+ cashMovement.getVoucher().getIdVoucher());
	if (cashMovement.getVoucher().getIdVoucher() == 0){
	    setEnabledVoucher(false);
	    chkVoucher.setSelected(false);
	} else {
	    setEnabledVoucher(true);
	    chkVoucher.setSelected(true);           
	    cbVoucher.getCombo().addItem(voucherNumber, ""+ cashMovement.getVoucher().getIdVoucher());
	}
	tfDescription.setValue(cashMovement.getDescription());
    }
    
    public boolean saveData(){
	if (cashMovement == null){
	    cashMovement = new CashMovement();
	}
	cashMovement.setAmount(tfAmount.getAmount());
	cashMovement.setAmountP(tfAmountP.getAmount());
	cashMovement.setProjAmount(tfProjAmount.getAmount());
	cashMovement.setProjAmountP(tfProjAmountP.getAmount());
	cashMovement.setDesviationAmount(tfDesvioAmount.getAmount());
	cashMovement.setCashMovementType(new CashMovementType(Integer.parseInt(""+ cbCashMovementType.getSelectedValue())));
	cashMovement.setCostsCentre(new CostsCentre(Integer.parseInt(""+ cbCostsCentre.getSelectedValue())));
	cashMovement.setDate(Proced.setFormatDate(tfDate.getString(), false));
	cashMovement.setDescription(tfDescription.getString());
	if (chkVoucher.isSelected()){
	    voucher = new Voucher(Integer.parseInt("0"+ cbVoucher.getSelectedValue()));
	} else {
	    voucher = new Voucher();
	}
	cashMovement.setVoucher(voucher);

	int result = -1;
	if (cashMovement.getIdCashMovement() == -1){
	    result = cashMovement.addData();
	} else {
	    result = cashMovement.setData();
	}
	
	if (result >= 0){
	    parentList.refresh();
	    getParentInternalFrame().close();
	    return true;	    
	} else {
	    return false;
	}
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	saveData();	
    }

    public void setParentList(CashMovementsList parentList) {
	this.parentList = parentList;
    }

    public void setCashMovement(CashMovement cashMovement) {
	this.cashMovement = cashMovement;
	loadData();
    }

    private void setEnabledVoucher(boolean _enabled){
	tfFindVoucher.setEnabled(_enabled);
	cbVoucher.setEnabled(_enabled);
	btnFindVoucher.setEnabled(_enabled);
	tfAmount.setEnabled(!_enabled);
	tfProjAmount.setEnabled(!_enabled);
	tfDate.setEnabled(!_enabled);
    }
    private void chkVoucher_actionPerformed(ActionEvent e) {
	if (chkVoucher.isSelected()){
	    setEnabledVoucher(true);
	} else {
	    setEnabledVoucher(false);
	}
    }

    public void setVoucher(Voucher voucher) {
	this.voucher = voucher;
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setVoucherNumber(String voucherNumber) {
	this.voucherNumber = voucherNumber;
    }

    private void btnFindVoucher_actionPerformed(ActionEvent e) {
	voucherList = new VouchersList();
	
	ExtendedInternalFrame voucherListContainer = new ExtendedInternalFrame("Comprobantes");
	voucherListContainer.setCentralPanel(voucherList);
	voucherListContainer.show();
    }

}


