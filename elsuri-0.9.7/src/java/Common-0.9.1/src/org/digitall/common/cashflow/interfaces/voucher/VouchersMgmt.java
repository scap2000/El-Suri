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
 * VouchersMgmt.java
 *
 * */
package org.digitall.common.cashflow.interfaces.voucher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.digitall.common.cashflow.classes.CashMovement;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.VoucherDetail;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.cashflow.interfaces.costscentre.CCExpenditureAccountsTree;
import org.digitall.common.cashflow.interfaces.voucher.VouchersList;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.sql.LibSQL;

public class VouchersMgmt extends BasicPrimitivePanel {

    private BasicPanel jPanel1 = new BasicPanel("Agregar/Modificar Comprobante");
    private TFInput tfFindEntity = new TFInput(DataTypes.STRING, "FindEntity", false);
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfAmount = new TFInput(DataTypes.MONEY, "TotalAmount", false);
    private TFInput tfNumber = new TFInput(DataTypes.INTEGER, "Number", true);
    private TFInput tfPartialWOTaxes = new TFInput(DataTypes.MONEY, "PartialWOTaxes", false);
    private TFInput tfTaxes = new TFInput(DataTypes.MONEY, "Taxes", false);
    private TFInput tfPartialWTaxes = new TFInput(DataTypes.MONEY, "PartialWTaxes", false);
    private TFInput tfVATAmount = new TFInput(DataTypes.MONEY, "VATAmount", false);
    private TFInput tfVATp = new TFInput(DataTypes.PERCENT, "VATp", false);
    
    private CBInput cbVoucherType = new CBInput(CachedCombo.VOUCHERTYPE, "VoucherType");
    //private CBInput cbEntityType = new CBInput(CachedCombo.ENTITYTYPE, "EntityType");
    private CBInput cbEntity = new CBInput(0, "Entity", false);
    private CBInput cbActivity = new CBInput(CachedCombo.ACTIVITY, "Activities", true);
    //private CBInput cbCashMovementType = new CBInput(CachedCombo.CASHMOVEMENTTYPE, "CashMovementType");
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre");
    
    private CloseButton btnClose = new CloseButton();
    //private FindButton btnFindEntity = new FindButton();
    private SaveDataButton btnSave = new SaveDataButton();
    
    private BasicCheckBox chkDetail = new BasicCheckBox();
    private BasicCheckBox chkCloseVoucher = new BasicCheckBox();
    private BasicCheckBox chkCostCentre = new BasicCheckBox();
    
    private BasicRadioButton rbtnPerson = new BasicRadioButton();
    private BasicRadioButton rbtnCompany = new BasicRadioButton();
    
    private VouchersList parentList;
    private Voucher voucher;
    private String entityName;
    private CashMovement cashMovement;
    private ExpenditureAccount.CCItem ccExpenditureAccount;
    private CCExpenditureAccountsTree parentTree;
    private BasicPrimitivePanel parentPanel;

    public VouchersMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public VouchersMgmt(int _comboVoucherType) {
	try {
	    cbVoucherType.setCombo(CachedCombo.getCachedCombo(_comboVoucherType));
	    cbVoucherType.setSelectedValue("Factura B");
	    cbVoucherType.setEnabled(false);
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(705, 278));
	this.setPreferredSize(new Dimension(705, 270));
	jPanel1.setBounds(new Rectangle(5, 0, 695, 235));
	jPanel1.setLayout(null);
	tfFindEntity.setBounds(new Rectangle(195, 70, 130, 35));
	tfDate.setBounds(new Rectangle(595, 30, 85, 35));
	tfAmount.setBounds(new Rectangle(575, 110, 105, 35));
	cbVoucherType.setBounds(new Rectangle(15, 30, 155, 35));
	
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
	cbEntity.setBounds(new Rectangle(335, 70, 345, 35));
	cbActivity.setBounds(new Rectangle(15, 190, 445, 35));
	cbActivity.autoSize();
	chkDetail.setBounds(new Rectangle(485, 205, 95, 20));
	chkDetail.setText("Con Detalle");
	chkDetail.setHorizontalAlignment(SwingConstants.LEFT);
	
	cbCostsCentre.setBounds(new Rectangle(15, 150, 325, 35));
	chkCloseVoucher.setText("Cerrada");
	chkCloseVoucher.setBounds(new Rectangle(590, 205, 90, 20));
	tfNumber.setBounds(new Rectangle(195, 30, 120, 35));
	tfPartialWOTaxes.setBounds(new Rectangle(15, 110, 130, 35));
	tfTaxes.setBounds(new Rectangle(157, 110, 105, 35));
	tfPartialWTaxes.setBounds(new Rectangle(274, 110, 130, 35));
	tfVATAmount.setBounds(new Rectangle(488, 110, 75, 35));
	tfVATp.setBounds(new Rectangle(416, 110, 60, 35));
	jPanel1.add(chkCostCentre, null);
	jPanel1.add(chkCloseVoucher, null);
	jPanel1.add(cbCostsCentre, null);
        
	jPanel1.add(tfVATp, null);
	jPanel1.add(tfVATAmount, null);
	jPanel1.add(tfPartialWTaxes, null);
	jPanel1.add(tfTaxes, null);
	jPanel1.add(tfPartialWOTaxes, null);
	jPanel1.add(tfNumber, null);
	jPanel1.add(chkDetail, null);
        /*btnFindEntity.setBounds(new Rectangle(535, 35, 40, 25));
        jPanel1.add(btnFindEntity, null);*/
        jPanel1.add(cbActivity, null);
	jPanel1.add(cbEntity, null);
        jPanel1.add(tfAmount, null);
	jPanel1.add(tfDate, null);
	jPanel1.add(tfFindEntity, null);
	jPanel1.add(cbVoucherType, null);
        jPanel1.add(rbtnPerson, null);
        jPanel1.add(rbtnCompany, null);
        this.add(jPanel1, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnSave);
	cbVoucherType.autoSize();
        /*cbEntityType.setBounds(new Rectangle(365, 25, 155, 35));
        jPanel1.add(cbEntityType, null);
	cbEntityType.autoSize();
        cbEntityType.getCombo().addItemListener(new ItemListener() {

                                             public void itemStateChanged(ItemEvent evt) {
                                                 if (evt.getStateChange() == ItemEvent.SELECTED) {
                                                     if (cbEntityType.getSelectedValue().equals("-1")) {
                                                         setEnabledEntity(false);
                                                         cbEntity.getCombo().removeAllItems();
                                                     } else {
                                                         setEnabledEntity(true);
                                                         cbEntity.getCombo().removeAllItems();
                                                         if (voucher != null) {
                                                             if (cbEntityType.getSelectedValue().equals("" + voucher.getIdEntitytype())) {
                                                                 cbEntity.getCombo().addItem(entityName, "" + voucher.getIdEntity());
                                                             }
                                                         }
                                                     }
                                                 }
                                             }

                                         }
        );*/
	cbEntity.autoSize();
	
	cbCostsCentre.autoSize();
	setEnabledEntity(true);
	chkCloseVoucher.setEnabled(false);
	chkCostCentre.setBounds(new Rectangle(350, 160, 25, 25));
	chkCostCentre.setSize(new Dimension(25, 25));
	chkCostCentre.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 chkCostCentre_actionPerformed(e);
				     }

				 }
	);
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfFindEntity.getTextField().addKeyListener(new KeyAdapter() {

						public void keyTyped(KeyEvent e) {
						    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							loadComboEntity();
						    }
						}

					    }
	);
	/*
        cbCashMovementType.setBounds(new Rectangle(410, 150, 270, 35));
        jPanel1.add(cbCashMovementType, null);
        cbCashMovementType.autoSize();
	cbCashMovementType.getCombo().addItem("N/A", "0");
	cbCashMovementType.setSelectedID("0");
        */
	ActionListener calculateListen = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    calculateTotal();
		}

	    }
	;
	tfTaxes.getTextField().addActionListener(calculateListen);
	tfPartialWOTaxes.getTextField().addActionListener(calculateListen);
	tfVATp.getTextField().addActionListener(calculateListen);
	chkDetail.setSelected(true);
	chkCostCentre.setSelected(true);
        rbtnPerson.setText("Persona");
        rbtnPerson.setBounds(new Rectangle(15, 80, 88, 18));
        //tfFindEntity.setBounds(new Rectangle(195, 70, 130, 35));
        rbtnCompany.setText("Empresa");
        rbtnCompany.setBounds(new Rectangle(95, 80, 88, 18));
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
        ButtonGroup rbtnProvider = new ButtonGroup();
        rbtnProvider.add(rbtnPerson);
        rbtnProvider.add(rbtnCompany);
        rbtnPerson.setSelected(true);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los datos y presione el botón \"Grabar\"");
    }

    private void calculateTotal() {
	try {
	    tfPartialWTaxes.setValue(tfPartialWOTaxes.getAmount() + tfTaxes.getAmount());
	    tfVATAmount.setValue((tfPartialWTaxes.getAmount() * tfVATp.getAmount()) / 100.0);
	    tfAmount.setValue(tfPartialWTaxes.getAmount() + tfVATAmount.getAmount());
	} catch (Exception ex) {
	    tfPartialWOTaxes.setValue(0.0);
	    tfPartialWTaxes.setValue(0.0);
	    tfVATAmount.setValue(0.0);
	    tfAmount.setValue(0.0);
	}
    }

    private void setEnabledEntity(boolean _enabled) {
	tfFindEntity.setEnabled(_enabled);
	cbEntity.setEnabled(_enabled);
	//btnFindEntity.setEnabled(_enabled);
    }

    private void loadComboEntity() {
	/*String param = "'" + tfFindEntity.getString() + "'";
	switch (Integer.parseInt("" + cbEntityType.getSelectedValue())) {
	    case EntityTypes.PERSON :
		cbEntity.loadJCombo(LibSQL.exFunction("org.getAllPersonsByFilter", param));
		break;
	    case EntityTypes.PROVIDER :
		cbEntity.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", param));
		break;
	    default :
		break;
	}
        */
        String param = "'" + tfFindEntity.getString() + "'";
        if (rbtnPerson.isSelected())  {
            cbEntity.loadJCombo(LibSQL.exFunction("org.getAllPersonsByFilter", param));    
        } else  {
            cbEntity.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", param));    
        }
    }

    private void loadData() {
	tfAmount.setValue(voucher.getAmount());
	tfDate.setValue(Proced.setFormatDate(voucher.getDate(), true));
	cbActivity.setSelectedValue(voucher.getDescription());
	tfNumber.setValue("" + voucher.getNumber());
	tfPartialWOTaxes.setValue(voucher.getPartialWOTaxes());
	tfPartialWTaxes.setValue(voucher.getPartialWTaxes());
	tfTaxes.setValue(voucher.getTaxes());
	tfVATAmount.setValue(voucher.getVATAmount());
	tfVATp.setValue(voucher.getVATp());
	//cbEntityType.setSelectedID("" + voucher.getIdEntitytype());
        if (voucher.getIdEntitytype() == 15)  {
            rbtnPerson.setSelected(true);
        } else  {
            rbtnCompany.setSelected(true);
        }
        loadComboEntity();
        cbEntity.setSelectedID(voucher.getIdEntity());
	cbVoucherType.setSelectedID("" + voucher.getIdVoucherType());
	cbCostsCentre.setSelectedID("" + voucher.getCostsCentre().getIdCostCentre());
	chkDetail.setSelected(voucher.isWithDetail());
	if (voucher.isWithDetail()) {
	    chkDetail.setEnabled(false);
	} else {
	    chkDetail.setEnabled(true);
	}
	chkCloseVoucher.setSelected(voucher.isClosed());
	if (voucher.isClosed()) {
	    chkCloseVoucher.setEnabled(false);
	    cbVoucherType.setEnabled(false);
	    tfNumber.setEnabled(false);
	    tfDate.setEnabled(false);
	    cbEntity.setEnabled(false);
	    //cbEntityType.setEnabled(false);
            rbtnPerson.setEnabled(false);
            rbtnCompany.setEnabled(false);
	    //btnFindEntity.setEnabled(false);
	    tfPartialWOTaxes.setEnabled(false);
	    tfPartialWTaxes.setEnabled(false);
	    tfTaxes.setEnabled(false);
	    tfVATAmount.setEnabled(false);
	    tfVATp.setEnabled(false);
	    cbCostsCentre.setEnabled(false);
	    chkCostCentre.setEnabled(false);
	    //cbCashMovementType.setEnabled(false);
	    cbActivity.setEnabled(false);
	    chkDetail.setEnabled(false);
	    btnSave.setVisible(false);
	    tfAmount.setEnabled(false);
	    tfFindEntity.setEnabled(false);
	} else {
	    chkCloseVoucher.setEnabled(true);
	}
	/*if (cashMovement != null) {
	    cbCashMovementType.setSelectedID("" + cashMovement.getCashMovementType().getIdCashMovementType());
	}*/
    }

    public boolean saveData() {
	if ( (tfAmount.getAmount() > 0)  && (cbEntity.getSelectedIndex() != -1)) {
	    if (voucher == null) {
		voucher = new Voucher();
	    }
	    voucher.setAmount(tfAmount.getAmount());
	    voucher.setDate(Proced.setFormatDate(tfDate.getString(), false));
	    voucher.setDescription(cbActivity.getSelectedItem().toString());
	    //voucher.setIdEntitytype(Integer.parseInt("" + cbEntityType.getSelectedValue()));
            if (rbtnPerson.isSelected())  {
                voucher.setIdEntitytype(15);
            } else  {
                voucher.setIdEntitytype(67);
            }
	    voucher.setIdEntity(Integer.parseInt(cbEntity.getSelectedValue().toString()));
	    voucher.setIdVoucherType(Integer.parseInt("" + cbVoucherType.getSelectedValue()));
	    voucher.setPartialWOTaxes(tfPartialWOTaxes.getAmount());
	    voucher.setPartialWTaxes(tfPartialWTaxes.getAmount());
	    voucher.setTaxes(tfTaxes.getAmount());
	    voucher.setVATAmount(tfVATAmount.getAmount());
	    voucher.setVATp(tfVATp.getAmount());
	    voucher.setNumber(Long.parseLong("0" + tfNumber.getString()));
	    voucher.setWithDetail(chkDetail.isSelected());
	    voucher.setFullNumber(cbVoucherType.getSelectedItem() + " - Nº " + tfNumber.getString());
	    if (chkCostCentre.isSelected()) {
		voucher.setCostsCentre(new CostsCentre(Integer.parseInt("" + cbCostsCentre.getSelectedValue())));
	    } else {
		voucher.setCostsCentre(new CostsCentre());
	    }
	    voucher.setClosed(chkCloseVoucher.isSelected());
	    int result = -1;
	    if (voucher.getIdVoucher() == -1) {
		result = voucher.addData();
	    } else {
		result = voucher.setData();
	    }
	    
           /* if (result >= 0) {
		if (!cbCashMovementType.getSelectedValue().equals("-1")) {
		    if (cashMovement == null) {
			cashMovement = new CashMovement();
		    }
		    cashMovement.setAmount(tfAmount.getAmount());
		    cashMovement.setCashMovementType(new CashMovementType(Integer.parseInt("" + cbCashMovementType.getSelectedValue())));
		    cashMovement.setCostsCentre(voucher.getCostsCentre());
		    cashMovement.setDate(Proced.setFormatDate(tfDate.getString(), false));
		    cashMovement.setDescription("");
		    cashMovement.setVoucher(voucher);
		    if (rbtnPerson.isSelected())  {
		        cashMovement.setBenefittype(cashMovement.PERSONA);
		    } else  {
		        cashMovement.setBenefittype(cashMovement.EMPRESA);
		    }
		    cashMovement.setIdbenefit(Integer.parseInt(cbEntity.getSelectedValue().toString()) );
		    result = -1;
		    if (cashMovement.getIdCashMovement() == -1) {
			result = cashMovement.addData();
		    } else {
			result = cashMovement.setData();
		    }
		}
	    }*/
	    if (result >= 0) {
		if (ccExpenditureAccount != null) {
		    return addSpentToCC();
		} else if (!chkDetail.isSelected()) {
		    if (cbCostsCentre.getSelectedItem() != null) {
			int answer = JOptionPane.showInternalConfirmDialog(this, "¿Desea cargar esta factura como gasto\nal " + cbCostsCentre.getSelectedItem() + "?", "Cancelar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, IconTypes.close_ro_16x16);
			/*if (answer == JOptionPane.OK_OPTION) {
			    ExpenditureAccountsTree expenditureAccountTree = new ExpenditureAccountsTree();
			    expenditureAccountTree.setVouchersMgmt(this);
			    ExtendedInternalFrame expenditureAccountTreeContainer = new ExtendedInternalFrame("Seleccionar Tipo de Gasto");
			    expenditureAccountTreeContainer.setCentralPanel(expenditureAccountTree);
			    expenditureAccountTreeContainer.show();
			}*/
		    }
		    if (parentList != null) {
			parentList.refresh();
		    } else {
			parentPanel.reload();
		    }
		    getParentInternalFrame().close();
		    return true;
		} else {
		    if (parentList != null) {
			parentList.refresh();
		    } else {
			parentPanel.reload();
		    }
		    getParentInternalFrame().close();
		    return true;
		}
	    } else {
		Advisor.messageBox("El Nº de Factura ya existe", "Aviso");
		return false;
	    }
	}
	else {
	    Advisor.messageBox("El Monto de la de Factura debe ser mayor que cero\ny debe seleccionar una Entidad", "Aviso");
	    return false;
	}
    }

    public void addCCExpenditureAccount(ExpenditureAccount _expenditureAccount) {
	ccExpenditureAccount = new ExpenditureAccount.CCItem(new CostsCentre(Integer.parseInt("" + cbCostsCentre.getSelectedValue())));
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

    private void btnSave_actionPerformed(ActionEvent e) {
	saveData();
    }

    public void setParentList(VouchersList parentList) {
	this.parentList = parentList;
    }

    public void setVoucher(Voucher voucher) {
	this.voucher = voucher;
	if (voucher.isClosed()) {
	    btnSave.setEnabled(false);
	} else {
	    btnSave.setEnabled(true);
	}
	if (voucher.getIdVoucher() != -1) {
	    loadData();
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setEntityName(String entityName) {
	this.entityName = entityName;
    }

    public void setCashMovement(CashMovement cashMovement) {
	this.cashMovement = cashMovement;
    }

    public void setCCExpenditureAccount(ExpenditureAccount.CCItem _ccExpenditureAccount) {
	this.ccExpenditureAccount = _ccExpenditureAccount;
	cbCostsCentre.setSelectedID("" + _ccExpenditureAccount.getCostCentre().getIdCostCentre());
	cbCostsCentre.setEnabled(false);
	chkCostCentre.setVisible(false);
    }

    public void setParentTree(CCExpenditureAccountsTree parentTree) {
	this.parentTree = parentTree;
    }

    public void setEntity(int _idEntityType, int _idEntity, String _entityName) {
	setEnabledEntity(true);
	//cbEntityType.setSelectedID("" + _idEntityType);
        if (_idEntityType == 1)  {
            rbtnPerson.setEnabled(true);
        } else  {
            rbtnCompany.setEnabled(true);
        }
        
	setEntityName(_entityName);
	cbEntity.getCombo().addItem(_entityName, "" + _idEntity);
    }

    public void setCostCentre(CostsCentre _costsCentre) {
	cbCostsCentre.setSelectedID("" + _costsCentre.getIdCostCentre());
    }

    private void loadCostCentre(boolean _load) {
	cbCostsCentre.setEnabled(_load);
	if (_load) {
	    cbCostsCentre.setCombo(CachedCombo.getCachedCombo(CachedCombo.COSTSCENTRE));
	} else {
	    cbCostsCentre.removeAllItems();
	}
    }

    private void chkCostCentre_actionPerformed(ActionEvent e) {
	if (chkCostCentre.isSelected()) {
	    loadCostCentre(true);
	} else {
	    loadCostCentre(false);
	}
    }

    public void setEnabledCostCentre(boolean _enabled) {
	chkCostCentre.setSelected(_enabled);
	loadCostCentre(_enabled);
    }

    public void setCloseVoucher(boolean _value) {
	chkCloseVoucher.setSelected(_value);
    }

    public void setParentPanel(BasicPrimitivePanel parentPanel) {
	this.parentPanel = parentPanel;
    }

    public void setVoucherType(int _idVoucherType) {
	cbVoucherType.setSelectedID("" + _idVoucherType);
    }
    
    private void rbtnPerson_actionPerformed(ActionEvent e) {
        tfFindEntity.setValue("");
        cbEntity.removeAllItems();
    }
    
    private void rbtnCompany_actionPerformed(ActionEvent e) {
        tfFindEntity.setValue("");
        cbEntity.removeAllItems();
    }

}
