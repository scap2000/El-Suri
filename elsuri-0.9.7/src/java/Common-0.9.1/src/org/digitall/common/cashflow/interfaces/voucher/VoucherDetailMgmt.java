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
 * VoucherDetailMgmt.java
 *
 * */
package org.digitall.common.cashflow.interfaces.voucher;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JSeparator;

import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.VoucherDetail;
import org.digitall.common.cashflow.interfaces.voucher.VoucherDetailList;
import org.digitall.common.cashflow.interfaces.voucher.VouchersList;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.interfaces.ResourcesList;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount.CCItem;
import org.digitall.lib.sql.LibSQL;

public class VoucherDetailMgmt extends BasicPrimitivePanel {

    private SaveDataButton btnAccept = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private JSeparator jSeparator1 = new JSeparator();
    private TFInput tfFindResource = new TFInput(DataTypes.STRING, "FindResource", false);
    private CBInput cbResource = new CBInput(0, "Resources");
    private CBInput cbBrand = new CBInput(CachedCombo.BRANDS,"Brand");
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private TFInput tfAmount = new TFInput(DataTypes.MONEY, "TotalAmount", false);
    private TFInput tfPartialWOTaxes = new TFInput(DataTypes.MONEY, "PartialWOTaxes", false);
    private TFInput tfTaxes = new TFInput(DataTypes.MONEY, "Taxes", false);
    private TFInput tfPartialWTaxes = new TFInput(DataTypes.MONEY, "PartialWTaxes", false);
    private TFInput tfVATAmount = new TFInput(DataTypes.MONEY, "VATAmount", false);
    private TFInput tfVATp = new TFInput(DataTypes.PERCENT, "VATp", false);
    private TFInput tfUnit = new TFInput(DataTypes.STRING, "Unit", false);
    private TFInput tfPrice = new TFInput(DataTypes.MONEY, "Price", false);
    private BasicPanel dataPanel = new BasicPanel("Agregar/Modificar ítem");
    private VoucherDetail voucherDetail;
    private ExpenditureAccount.CCItem ccExpenditureAccount;
    private ResourceBrands resourceBrand;
    private Resource resource;
    private TFInput tfExpenditureAccount = new TFInput(DataTypes.STRING, "ExpenditureAccount", false);
    private FindButton btnFindExpenditureAccount = new FindButton();
    private VoucherDetailList parentList;
    private VouchersList vouchersList;
    private TFInput tfFinalQty = new TFInput(DataTypes.DOUBLE, "Quantity", false);
    private FindButton btnFindResources = new FindButton();
    private ResourcesList resourcesList;
    
    public VoucherDetailMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(710, 276));
	
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	jSeparator1.setBounds(new Rectangle(0, 330, 720, 2));
	tfFindResource.setBounds(new Rectangle(10, 30, 125, 35));
	cbResource.setBounds(new Rectangle(150, 30, 485, 35));
	cbBrand.setBounds(new Rectangle(10, 110, 245, 35));
	tfAmount.setBounds(new Rectangle(570, 150, 115, 35));
	tfPartialWOTaxes.setBounds(new Rectangle(10, 150, 130, 35));
	tfTaxes.setBounds(new Rectangle(155, 150, 100, 35));
	tfPartialWTaxes.setBounds(new Rectangle(265, 150, 130, 35));
	tfVATAmount.setBounds(new Rectangle(480, 150, 75, 35));
	tfVATp.setBounds(new Rectangle(410, 150, 60, 35));
	tfUnit.setBounds(new Rectangle(300, 110, 90, 35));
	tfPrice.setBounds(new Rectangle(460, 110, 95, 35));
	tfDescription.setBounds(new Rectangle(10, 190, 675, 35));
	dataPanel.add(btnFindResources, null);
	dataPanel.add(tfFinalQty, null);
	dataPanel.add(btnFindExpenditureAccount, null);
	dataPanel.add(tfExpenditureAccount, null);
	dataPanel.add(tfFindResource, null);
	dataPanel.add(tfPrice, null);
	dataPanel.add(tfUnit, null);
	dataPanel.add(tfDescription, null);
	dataPanel.add(tfVATp, null);
	dataPanel.add(tfVATAmount, null);
	dataPanel.add(tfPartialWTaxes, null);
	dataPanel.add(tfTaxes, null);
	dataPanel.add(tfPartialWOTaxes, null);
	dataPanel.add(tfAmount, null);
	dataPanel.add(cbBrand, null);
	dataPanel.add(cbResource, null);
	dataPanel.setBounds(new Rectangle(5, 0, 700, 235));
	dataPanel.setLayout(null);
	tfExpenditureAccount.setBounds(new Rectangle(10, 70, 620, 35));
	btnFindExpenditureAccount.setBounds(new Rectangle(650, 80, 33, 25));
	btnFindExpenditureAccount.setEnabled(false);
	tfFinalQty.setBounds(new Rectangle(570, 110, 115, 35));
	btnFindResources.setBounds(new Rectangle(650, 40, 33, 25));
	btnFindResources.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnFindResources_actionPerformed(e);
					}

				    }
	);
	this.add(dataPanel, null);
	this.add(jSeparator1, null);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	cbBrand.autoSize();
	cbResource.autoSize();
	
	tfUnit.setEnabled(false);
	tfExpenditureAccount.setEnabled(false);
	tfVATAmount.setEnabled(false);

	tfFindResource.getTextField().addKeyListener(new KeyAdapter() {
		 public void keyTyped(KeyEvent e) {
		     if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
		         loadComboResource();
		         loadResource();
		     }
		 }
	     }
	);
	
	cbBrand.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    loadResourceBrand();
		}
	    }
	});
	
	ActionListener calculateListen = new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        calculateTotal();
	    }
	};
	
	tfPrice.getTextField().addActionListener(calculateListen);
	tfFinalQty.getTextField().addActionListener(calculateListen);
	tfTaxes.getTextField().addActionListener(calculateListen);
	tfPartialWOTaxes.getTextField().addActionListener(calculateListen);
	tfVATp.getTextField().addActionListener(calculateListen);
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Complete los campos y presione el botón \"Grabar\"");
    }
    
    private void calculateTotal(){
	try {
	    tfPartialWOTaxes.setValue(tfPrice.getAmount() * tfFinalQty.getDouble());
	    tfPartialWTaxes.setValue(tfPartialWOTaxes.getAmount() + tfTaxes.getAmount());
	    tfVATAmount.setValue(tfPartialWTaxes.getAmount() * tfVATp.getAmount() / 100.0);
	    tfAmount.setValue(tfPartialWTaxes.getAmount() + tfVATAmount.getAmount());
	}
	catch (Exception ex) {
	    tfPartialWOTaxes.setValue(0.0);
	    tfPartialWTaxes.setValue(0.0);
	    tfVATAmount.setValue(0.0);
	    tfAmount.setValue(0.0);
	}
    }

    public void loadComboResource() {
	JCombo combo = new JCombo();
	String param = "'" + tfFindResource.getString() + "',0";
	combo.loadJCombo(LibSQL.exFunction("resourcescontrol.getAllResourcesByFilter", param));
	ItemListener resourceItemListener = new ItemListener() {
		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			loadResource();
		    }
		}
	    };
	cbResource.setCombo(combo);
	cbResource.setItemListener(resourceItemListener);
	cbResource.updateUI();
    }

    private void loadResourceBrand(){
	if (resourceBrand == null){
	    resourceBrand = new ResourceBrands();
	}
	if (resource != null){
	    resourceBrand.setResource(resource);
	}
	resourceBrand.setBrands(new Brands(Integer.parseInt(""+ cbBrand.getSelectedValue())));  
	resourceBrand.retrieveData();
	tfPrice.setValue(resourceBrand.getPrice());	
    }
    
    private void loadResource() {
	if (cbResource.getSelectedValue() != null){
	    resource = new Resource(EntityTypes.RESOURCE, Integer.parseInt(cbResource.getSelectedValue().toString()));
	    resource.retrieveData();
	    if (resource.getExpenditureAccount().getIDExpenditureAccount() != 0){
		ccExpenditureAccount = new ExpenditureAccount.CCItem(resource.getExpenditureAccount().getIDExpenditureAccount());
		tfExpenditureAccount.setValue(resource.getExpenditureAccount().getName());
		tfUnit.setValue(resource.getUnit().getName());
	    }
	    
	    loadResourceBrand();
	}
    }
    
    private void loadData(){
	if (voucherDetail.getIdVoucherDetail() != -1){
	    tfAmount.setValue(voucherDetail.getAmount());
	    tfDescription.setValue(voucherDetail.getDescription());
	    
	    ccExpenditureAccount = voucherDetail.getExpenditureAccount();
	    tfExpenditureAccount.setValue(ccExpenditureAccount.getName());
	    
	    tfFinalQty.setValue(voucherDetail.getFinalQty());
	    tfPartialWOTaxes.setValue(voucherDetail.getPartialWOTaxes());
	    tfPartialWTaxes.setValue(voucherDetail.getPartialWTaxes());
	    
	    resourceBrand = voucherDetail.getResourceBrand();
	    tfPrice.setValue(resourceBrand.getPrice());
	    tfUnit.setValue(resourceBrand.getUnit().getName());
	    cbBrand.setSelectedID(""+ resourceBrand.getBrands().getIdBrand());
	    cbResource.getCombo().addItem(resourceBrand.getResource().getName(), ""+ resourceBrand.getResource().getIdResource());
	    
	    tfTaxes.setValue(voucherDetail.getTaxes());
	    tfVATAmount.setValue(voucherDetail.getVATAmount());
	    tfVATp.setValue(voucherDetail.getVATp());
	}
	if (voucherDetail.getVoucher().isClosed()) {
	    tfFindResource.setEnabled(false);
	    cbResource.setEnabled(false);
	    cbBrand.setEnabled(false);
	    tfPrice.setEnabled(false);
	    tfPartialWOTaxes.setEnabled(false);
	    tfPartialWTaxes.setEnabled(false);
	    tfFinalQty.setEnabled(false);
	    tfVATAmount.setEnabled(false);
	    tfVATp.setEnabled(false);
	    tfDescription.setEnabled(false);
	    btnFindResources.setEnabled(false);
	    tfAmount.setEnabled(false);
	    tfTaxes.setEnabled(false);
	    btnAccept.setVisible(false);
	}
    }
    
    public boolean saveData(){
	if (tfAmount.getAmount()>0 && tfFinalQty.getDouble() > 0 && cbResource.getSelectedIndex()!=-1) {
	    if (voucherDetail == null){
		voucherDetail = new VoucherDetail();
	    }
	    
	    double total = tfAmount.getAmount();
	    double totalVoucher = 0;
	    boolean validate = true;
	    if (!voucherDetail.getVoucher().isWithDetail()){
		totalVoucher = voucherDetail.getVoucher().getTotalAmount();
		if (voucherDetail.getIdVoucherDetail() != -1){
		    totalVoucher += voucherDetail.getAmount();
		}
		if (total > totalVoucher){
		    validate = false;
		}
	    }
	    
	    if (validate){
		voucherDetail.setAmount(total);
		voucherDetail.setDescription(tfDescription.getString());
		voucherDetail.setExpenditureAccount(ccExpenditureAccount);
		voucherDetail.setPartialWOTaxes(tfPartialWOTaxes.getAmount());
		voucherDetail.setPartialWTaxes(tfPartialWTaxes.getAmount());
		voucherDetail.setOriginalQty(0);
		voucherDetail.setFinalQty(tfFinalQty.getDouble());
		voucherDetail.setPrice(tfPrice.getAmount());
		voucherDetail.setResourceBrand(resourceBrand);
		voucherDetail.setTaxes(tfTaxes.getAmount());
		voucherDetail.setVATAmount(tfVATAmount.getAmount());
		voucherDetail.setVATp(tfVATp.getAmount());
		
		int result = -1;
		if (voucherDetail.getIdVoucherDetail() == -1){
		    result = voucherDetail.addData();
		} else {
		    result = voucherDetail.setData();
		}
		
		if (result >= 0){
		    voucherDetail.getVoucher().updateTotal();
		    parentList.refresh();
		    if (vouchersList != null){
			vouchersList.refresh();
		    }
		    getParentInternalFrame().close();
		    return true;
		} else {
		    return false;
		}
	    } else {
		if (totalVoucher == 0){
		    Advisor.messageBox("Imposible agregar mas item a este comprobante","Comprobante cerrado");
		} else {
		    Advisor.messageBox("El total del ítem debe ser menor o igual $"+ totalVoucher,"Total no válido");
		}
		return false;
	    }
	}
	else {
	    Advisor.messageBox("Debe completar todos los campos", "Aviso");
	    return false;
	}
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	parentList.clearList();
	getParentInternalFrame().close();
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	saveData();
    }

    public void setExpenditureAccount(ExpenditureAccount.CCItem _expenditureAccount) {
	this.ccExpenditureAccount = _expenditureAccount;
    }

    public void setParentList(VoucherDetailList parentList) {
	this.parentList = parentList;
    }

    public void setVoucherDetail(VoucherDetail voucherDetail) {
	this.voucherDetail = voucherDetail;
	if (voucherDetail.getVoucher().isClosed()){
	    btnAccept.setEnabled(false);
	} else {
	    btnAccept.setEnabled(true);
	}
	loadData();
    }

    private void btnFindResources_actionPerformed(ActionEvent e) {
	resourcesList = new ResourcesList();
	
	ExtendedInternalFrame resourcesListContainer = new ExtendedInternalFrame("Listado de Recursos");
	resourcesListContainer.setCentralPanel(resourcesList);
	resourcesListContainer.show();
    }

    public void setVouchersList(VouchersList vouchersList) {
	this.vouchersList = vouchersList;
    }

}
