package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;


public class CCMgmt extends BasicPrimitivePanel {

    private BasicPanel dataPanel = new BasicPanel();
    private BasicPrimitivePanel parentPanel;
    private TFInput tfCode = new TFInput(DataTypes.STRING, "Code", true);
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private TFInput tfName = new TFInput(DataTypes.STRING, "Name", true);
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private BasicCheckBox chkProvisionOrder = new BasicCheckBox();
    private CCList parentList;
    private CostsCentre costCentre;
    private int error = 0;
    
    public CCMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(629, 185));
	this.setPreferredSize(new Dimension(705, 270));
	dataPanel.setBounds(new Rectangle(5, 0, 695, 235));
	dataPanel.setLayout(null);
        btnSave.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnSave_actionPerformed(e);
			       }

			   }
	);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
        tfDescription.setBounds(new Rectangle(15, 70, 600, 35));
        chkProvisionOrder.setBounds(new Rectangle(15, 115, 600, 20));
	chkProvisionOrder.setText("Puede hacer Ordenes de Provisión");
	chkProvisionOrder.setHorizontalAlignment(SwingConstants.LEFT);
        tfCode.setBounds(new Rectangle(15, 25, 120, 35));
        dataPanel.add(tfName, null);
        dataPanel.add(tfCode, null);
        dataPanel.add(chkProvisionOrder, null);
        dataPanel.add(tfDescription, null);
        this.add(dataPanel, null);
	this.addButton(btnClose);
	this.addButton(btnSave);
        tfName.setBounds(new Rectangle(160, 25, 455, 35));
        dataPanel.setBorder(BorderPanel.getBorderPanel("Agregar/Modificar Centro de Costos"));
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar\"");
    }


    private void loadData() {
        tfCode.setValue(costCentre.getCode());
        tfName.setValue(costCentre.getName());
	tfDescription.setValue(costCentre.getDescription());
        chkProvisionOrder.setSelected(costCentre.isProvisionOrder());
    }

    public boolean saveData() {
        if (costCentre.getIdCostCentre() == -1) { /** Nuevo Centro de Costos */
            if (tfName.getString().length() > 0 && tfCode.getString().length() > 0 && tfDescription.getString().length() > 0){
                costCentre.setCode(tfCode.getString());
                costCentre.setName(tfName.getString());
                costCentre.setDescription(tfDescription.getString());
                costCentre.setIsProvisionOrder(chkProvisionOrder.isSelected());
                if (costCentre.addData() > 0) {
                    costCentre.setProvisionOrder();
                    //clearData();
                    return true;
                } else {
                    error = 2;
                    return false;
                }
            } else {
                error = 1;
                return false;
            }
        } else {
            /** modificar */
             costCentre.setCode(tfCode.getString());
             costCentre.setName(tfName.getString());
             costCentre.setDescription(tfDescription.getString());
             costCentre.setIsProvisionOrder(chkProvisionOrder.isSelected());
             if (costCentre.setData() >= 0) {
                 costCentre.setProvisionOrder();
                 //clearData();
                 return true;
             } else {
                 error = 3;
                 return false;
             }
        }
    }

    /*public void addCCExpenditureAccount(ExpenditureAccount _expenditureAccount) {
	//ccExpenditureAccount = new ExpenditureAccount.CCItem(new CostsCentre(Integer.parseInt("" + cbCostsCentre.getSelectedValue())));
	ccExpenditureAccount.setIDExpenditureAccount(_expenditureAccount.getIDExpenditureAccount());
	ccExpenditureAccount.setIdCostCentreDetail(0);
	addSpentToCC();
    }

    private boolean addSpentToCC() {
	VoucherDetail voucherDetail = new VoucherDetail();
	voucherDetail.setVoucher(voucher);
	voucherDetail.setExpenditureAccount(ccExpenditureAccount);
	voucherDetail.setResourceBrand(new ResourceBrands(new Resource(), new Brands()));
	voucherDetail.setPartialWOTaxes(voucher.getPartialWOTaxes());
	voucherDetail.setTaxes(voucher.getTaxes());
	voucherDetail.setPartialWTaxes(voucher.getPartialWTaxes());
	voucherDetail.setVATAmount(voucher.getVATAmount());
	voucherDetail.setVATp(voucher.getVATp());
	voucherDetail.setAmount(voucher.getAmount());
	if (voucherDetail.addData() > 0) {
	    if (ccExpenditureAccount.getIdCostCentreDetail() == 0) {
		String params = ccExpenditureAccount.getCostCentre().getIdCostCentre() + "," + ccExpenditureAccount.getIDExpenditureAccount() + ", 0, 0, 0, 0, 0, 0";
		//int idCostCentreDetail = LibSQL.getInt("cashflow.addCostsCentreAmountDetail", params);
		//ccExpenditureAccount.setIdCostCentreDetail(idCostCentreDetail);
	    }
	    //ccExpenditureAccount.updateSpentAmount(voucher.getAmount());
	    voucher.setClose(true);
	    if (parentTree != null) {
		parentTree.drawTree();
	    }
	    getParentInternalFrame().close();
	    return true;
	} else {
	    return false;
	}
    }
*/
    private void btnSave_actionPerformed(ActionEvent e) {
	if (saveData()) {
            parentList.refresh();
            getParentInternalFrame().close();
        } else {
            switch (error) {
                 case 1: Advisor.messageBox("Debe completar todos los campos","Aviso");
                    break;
                 case 2: Advisor.messageBox("Ocurrio un error al grabar los datos","Error");
                    break;
                 case 3: Advisor.messageBox("Ocurrio un error al modificar los datos","Error");
                   break;
            }

        }
    }

    public void setParentList(CCList _parentList) {
	parentList = _parentList;
    }

    public void setCostCentre(CostsCentre _costCentre) {
	this.costCentre = _costCentre;
	if (costCentre.getIdCostCentre() != -1) {
	    loadData();
	} else {
            clearData();
        }
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }
   
    public void setParentPanel(BasicPrimitivePanel parentPanel) {
	this.parentPanel = parentPanel;
    }
    
    public void clearData() {
        tfName.setValue("");
        tfCode.setValue("");
        tfDescription.setValue("");
        chkProvisionOrder.setSelected(false);
        this.setTitle("Nuevo Centro de Costos");
    }
    
}
