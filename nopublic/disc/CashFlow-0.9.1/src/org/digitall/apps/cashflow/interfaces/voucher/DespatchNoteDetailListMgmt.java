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
 * DespatchNoteDetailListMgmt.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.voucher;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.VoucherDetail;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.cashflow.classes.ExpenditureAccount.CCItem;
import org.digitall.common.cashflow.interfaces.voucher.VoucherDetailList;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.interfaces.ResourcesList;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.combos.JCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class DespatchNoteDetailListMgmt extends BasicPrimitivePanel {

    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private TFInput tfFindResource = new TFInput(DataTypes.STRING, "FindResource", false);
    private CBInput cbResource = new CBInput(0, "Resources");
    private CBInput cbBrand = new CBInput(CachedCombo.BRANDS,"Brand");
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private TFInput tfUnit = new TFInput(DataTypes.STRING, "Unit", false);
    private BasicPanel dataPanel = new BasicPanel("Agregar/Modificar Detalle");
    private BasicPanel searchVoucherPanel = new BasicPanel("Buscar Comprobante");
    private BasicPanel addVoucherPanel = new BasicPanel("Agregar Comprobante");
    private Voucher voucher;
    private VoucherDetail voucherDetail;
    private ExpenditureAccount.CCItem ccExpenditureAccount;
    private ResourceBrands resourceBrand;
    private Resource resource;
    private TFInput tfExpenditureAccount = new TFInput(DataTypes.STRING, "ExpenditureAccount", false);
    private FindButton btnFindExpenditureAccount = new FindButton();
    private VoucherDetailList parentList;
    private TFInput tfFinalQty = new TFInput(DataTypes.DOUBLE, "Quantity", false);
    private FindButton btnFindResources = new FindButton();
    private ResourcesList resourcesList;
    private DespatchNoteDetailList despatchNoteList;
    private CBInput cbVouchers = new CBInput(CachedCombo.VOUCHERTYPE,"VoucherType",false);
    private TFInput tfFindVoucherNumber = new TFInput(DataTypes.STRING,"FindVoucher",false);
    private CBInput cbVoucherNumber = new CBInput(0,"VoucherNumber",false);
    private String entityName = "";

    public DespatchNoteDetailListMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout( null );
	this.setSize(new Dimension(701, 525));
	btnAccept.setBounds(new Rectangle(595, 495, 40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	btnClose.setBounds(new Rectangle(650, 495, 40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	tfFindResource.setBounds(new Rectangle(20, 30, 125, 35));
	cbResource.setBounds(new Rectangle(155, 30, 485, 35));
	cbBrand.setBounds(new Rectangle(20, 130, 245, 35));
	tfUnit.setBounds(new Rectangle(295, 130, 110, 35));
	tfDescription.setBounds(new Rectangle(20, 170, 620, 35));
	searchVoucherPanel.add(cbVoucherNumber, null);
	searchVoucherPanel.add(tfFindVoucherNumber, null);
	searchVoucherPanel.add(cbVouchers, null);
	dataPanel.add(addVoucherPanel, null);
	dataPanel.add(searchVoucherPanel, null);
	dataPanel.add(btnFindResources, null);
	dataPanel.add(tfFinalQty, null);
	dataPanel.add(btnFindExpenditureAccount, null);
	dataPanel.add(tfExpenditureAccount, null);
	dataPanel.add(tfFindResource, null);
	dataPanel.add(tfUnit, null);
	dataPanel.add(tfDescription, null);
	dataPanel.add(cbBrand, null);
	dataPanel.add(cbResource, null);
	dataPanel.setBounds(new Rectangle(5, 0, 690, 490));
	dataPanel.setLayout(null);
	tfExpenditureAccount.setBounds(new Rectangle(20, 80, 620, 35));
	btnFindExpenditureAccount.setBounds(new Rectangle(650, 90, 33, 25));
	tfFinalQty.setBounds(new Rectangle(525, 130, 115, 35));
	btnFindResources.setBounds(new Rectangle(650, 40, 33, 25));
	btnFindResources.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnFindResources_actionPerformed(e);
					}

				    }
	);
	cbVouchers.setBounds(new Rectangle(15, 25, 155, 35));
	tfFindVoucherNumber.setBounds(new Rectangle(190, 25, 135, 35));
	cbVoucherNumber.setBounds(new Rectangle(340, 25, 295, 35));
	addVoucherPanel.setBounds(new Rectangle(10, 305, 665, 165));
	searchVoucherPanel.setBounds(new Rectangle(10, 215, 665, 85));
	searchVoucherPanel.setLayout(null);
	this.add(dataPanel, null);
	this.add(btnClose, null);
	this.add(btnAccept, null);
	cbBrand.autoSize();
	cbResource.autoSize();
	cbVouchers.autoSize();
	tfUnit.setEnabled(false);
	tfExpenditureAccount.setEnabled(false);
	tfFindResource.getTextField().addKeyListener(new KeyAdapter() {
		 public void keyTyped(KeyEvent e) {
		     if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
		         loadComboResource();
		         loadResource();
		     }
		 }
	     }
	);
	
	tfFindVoucherNumber.getTextField().addKeyListener(new KeyAdapter() {

					public void keyTyped(KeyEvent e) {
					    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
					        loadComboVouchers();    
					    }
					}

				    }
	);
	
	cbBrand.setItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
		    loadResourceBrand();
		}
	    }
	});
	cbVouchers.setItemListener(new ItemListener() {
		    public void itemStateChanged (ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    if (cbVouchers.getSelectedValue().equals("-1")){
				setEnabledVoucher(false);
				cbVoucherNumber.getCombo().removeAllItems();
			    } else {
				setEnabledVoucher(true);                         
				cbVoucherNumber.getCombo().removeAllItems();
				if (voucher != null ){
				    tfFindVoucherNumber.setValue("");
				    if (cbVouchers.getSelectedValue().equals(""+ voucher.getIdEntitytype())){
					cbVoucherNumber.getCombo().addItem(entityName, ""+ voucher.getIdEntity());
				    }
				}
			    }
			}
		    }
		});
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().getGeneralButtons().addButton(btnAccept);
	getParentInternalFrame().getGeneralButtons().addButton(btnClose);
	getParentInternalFrame().setClosable(true);
    }
    
    private void setEnabledVoucher(boolean _enabled){
	 tfFindVoucherNumber.setEnabled(_enabled);
	 cbVoucherNumber.setEnabled(_enabled);
    }
    public void setEntityName(String _entityName) {
	entityName = _entityName;
    }
   
    
    private void loadComboVouchers(){
	JCombo combo = new JCombo();
	String VoucherNumber = "0";
	if (!tfFindVoucherNumber.getString().equals(""))  {
	    VoucherNumber = tfFindVoucherNumber.getString();
	} 
	String param = cbVouchers.getSelectedValue().toString() + "," + VoucherNumber;
	combo.loadJCombo(LibSQL.exFunction("cashflow.getallvouchersbyNumber", param));    
	ItemListener itemListener = new ItemListener() {
		    public void itemStateChanged (ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			   
			}
		    }
		};
	cbVoucherNumber.setCombo(combo);        
	cbVoucherNumber.setItemListener(itemListener);
	cbVoucherNumber.updateUI();
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
	    getParentInternalFrame().setTitle("Modificar Detalle del Remito Nº " + voucher.getNumber());
	    dataPanel.setBorder(BorderPanel.getBorderPanel("Modificar"));
	    tfDescription.setValue(voucherDetail.getDescription());
	    ccExpenditureAccount = voucherDetail.getExpenditureAccount();
	    tfExpenditureAccount.setValue(ccExpenditureAccount.getName());
	    tfFinalQty.setValue(voucherDetail.getFinalQty());
	    resourceBrand = voucherDetail.getResourceBrand();
	    tfUnit.setValue(""+ resourceBrand.getUnit().getName());
	    cbBrand.setSelectedID(""+ resourceBrand.getBrands().getIdBrand());
	    cbResource.getCombo().addItem(resourceBrand.getResource().getName(), ""+ resourceBrand.getResource().getIdResource());
	} else {
	    getParentInternalFrame().setTitle("Agregar detalle del Remito Nº " + voucher.getNumber());
	    dataPanel.setBorder(BorderPanel.getBorderPanel("Agregar"));
	}
    }
    
    public boolean saveData(){
	if (voucherDetail == null){
	    voucherDetail = new VoucherDetail();
	}
	voucherDetail.setDescription(tfDescription.getString());
	voucherDetail.setExpenditureAccount(ccExpenditureAccount);
	voucherDetail.setOriginalQty(0);
	voucherDetail.setFinalQty(tfFinalQty.getDouble());
	voucherDetail.setResourceBrand(resourceBrand);
	System.out.println("xx: " + cbVoucherNumber.getSelectedValue());
	if (!cbVoucherNumber.getSelectedValue().toString().equals("0"))  {
	    voucherDetail.setIdVoucherDetailRef(Integer.parseInt(cbVoucherNumber.getSelectedValue().toString()));	
	}
	
	int result = -1;
	if (voucherDetail.getIdVoucherDetail() == -1){
	    result = voucherDetail.addData();
	} else {
	    result = voucherDetail.setData();
	}
	
	if (result >= 0){
	    voucherDetail.getVoucher().updateTotal();
	    despatchNoteList.loadList();
	    getParentInternalFrame().close();
	    return true;
	} else {
	    return false;
	}
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	if ( control() )  {
	    saveData();    
	} 
    }
    private boolean control() {
	boolean reply = false;
	if (!cbVoucherNumber.getSelectedValue().toString().equals("0"))  {
	    String params = cbVouchers.getSelectedValue().toString() + "," + cbVoucherNumber.getSelectedItem().toString();
	    boolean closeVoucher = LibSQL.getBoolean("cashflow.getCloseVoucher",params);
	    if (closeVoucher)  {
		Advisor.messageBox("Operación no permitida, el Comprobante ya fué cerrado","Aviso");  
		reply = false;
	    } else {
		if (!(tfFinalQty.getDouble()<=0))  {
		    reply = true;    
		} else  {
		    reply = false;
		    Advisor.messageBox("La cantidad debe ser mayor que cero","Error");  
		}
	    }
	} else {
	    reply = true;
	}
	return reply;
    }
    public void setExpenditureAccount(ExpenditureAccount.CCItem _expenditureAccount) {
	ccExpenditureAccount = _expenditureAccount;
    }

    public void setParentList(VoucherDetailList _parentList) {
	parentList = _parentList;
    }

    public void setVoucherDetail(Voucher _voucher, VoucherDetail _voucherDetail) {
	voucher = _voucher;
	voucherDetail = _voucherDetail;
	loadData();
    }

    private void btnFindResources_actionPerformed(ActionEvent e) {
	resourcesList = new ResourcesList();
	
	ExtendedInternalFrame resourcesListContainer = new ExtendedInternalFrame("Listado de Recursos");
	resourcesListContainer.setCentralPanel(resourcesList);
	resourcesListContainer.show();
    }

    public void setDespatchNoteList(DespatchNoteDetailList _despatchNoteList) {
	despatchNoteList = _despatchNoteList;
    }

   

}
