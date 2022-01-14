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
 * ResourcesRequestsAuthMgmt.java
 *
 * */
package org.digitall.common.resourcesrequests.interfaces.resourcesrequests;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.ResourcesRequestDetail;
import org.digitall.common.resourcescontrol.interfaces.ResourceBrandsStock;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;


public class ResourcesRequestsAuthMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private BasicPanel requestPanel = new BasicPanel("Cantidad Solicitada");
    private BasicPanel authPanel = new BasicPanel("Cantidad Autorizada");
    private BasicPanel budgetPanel = new BasicPanel("Partida Presupuestaria");

    private CBInput cbBrand = new CBInput(CachedCombo.BRANDS,"Brand", false);
    
    private TFInput tfAuthQty = new TFInput(DataTypes.DOUBLE, "Quantity", true);
    private TFInput tfQtyRequest = new TFInput(DataTypes.DOUBLE, "Quantity", false);
    private TFInput tfDestination = new TFInput(DataTypes.STRING,"Actividad/Destino/Concepto",false);
    private TFInput tfBrandRequest = new TFInput(DataTypes.STRING,"BrandRequest",false);
    private TFInput tfStock = new TFInput(DataTypes.DOUBLE,"Stock",false);
    private TFInput tfUnitRequest = new TFInput(DataTypes.STRING,"Unit",false);
    private TFInput tfAuthUnit = new TFInput(DataTypes.STRING,"Unit",false);
    private TFInput tfAuthPrice = new TFInput(DataTypes.MONEY_EXTENDED,"Price",false);
    private TFInput tfWithoutQtyAssign = new TFInput(DataTypes.DOUBLE,"WithoutQuantityAssign",false);
    private TFInput tfCostsCentre = new TFInput(DataTypes.STRING, "CostsCentre", false);
    private TFInput tfExpenditureAccount = new TFInput(DataTypes.STRING, "ExpenditureAccount", false);
    private TFInput tfAssignedAmount = new TFInput(DataTypes.MONEY,"AssignedAmount",false);
    private TFInput tfSpentAmount = new TFInput(DataTypes.MONEY,"SpentAmount",false);
    private TFInput tfAvailableAmount = new TFInput(DataTypes.MONEY,"AvailableAmount",false);
    private TFInput tfCost = new TFInput(DataTypes.MONEY,"Cost",false);
    private TFInput tfAuthCost = new TFInput(DataTypes.MONEY,"Cost",false);
    private TFInput tfNewAvailableAmount = new TFInput(DataTypes.MONEY,"($) Saldo proyectado",false);
    private TFInput tfTotalStock = new TFInput(DataTypes.DOUBLE,"TotalStock",false);
    private TFInput tfAvailableStock = new TFInput(DataTypes.DOUBLE,"AvailableQty",false);
    
    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton(); 
    private FindButton btnBrandDetail = new FindButton();

    private BasicRadioButton rbtnToBuy = new BasicRadioButton();
    private BasicRadioButton rbtnToDeliver = new BasicRadioButton();
    
    private ResourcesRequestDetail resourcesRequestDetail;
    private ResourcesRequestDetail resourcesRequestAuthDetail;
    
    private ResourcesRequestsAuthList parentList;
    private ResourceBrands resourceAuthBrands;
    private double authQty;
    private ResourceBrandsStock resourceBrandsStock;
    private ExpenditureAccount.CCItem ccExpenditureAccount;
    private int error = 0;

    public ResourcesRequestsAuthMgmt() {
	try {
	    jbInit();
	} catch (Exception x) {
	    x.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setSize(new Dimension(585, 435));
	this.setPreferredSize(new Dimension(585, 440));
	cbBrand.setBounds(new Rectangle(15, 100, 210, 35));
	cbBrand.autoSize();
	tfAuthQty.setBounds(new Rectangle(15, 55, 80, 35));
	tfQtyRequest.setBounds(new Rectangle(15, 25, 80, 35));
	btnAccept.setBounds(new Rectangle(510, 400, 30, 25));
	btnAccept.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAccept_actionPerformed(e);
			      }
			  }
	);
	requestPanel.setBounds(new Rectangle(5, 0, 575, 110));
	requestPanel.setLayout(null);
	btnClose.setBounds(new Rectangle(550, 400, 30, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}
			    }
	);
	tfDestination.setBounds(new Rectangle(15, 65, 545, 35));
	tfBrandRequest.setBounds(new Rectangle(215, 25, 250, 35));
	
	tfStock.setBounds(new Rectangle(240, 100, 100, 35));
	authPanel.setBounds(new Rectangle(5, 245, 575, 145));
	authPanel.setLayout(null);
	budgetPanel.setBounds(new Rectangle(5, 120, 575, 115));
	budgetPanel.setLayout(null);
	tfCostsCentre.setBounds(new Rectangle(15, 25, 295, 35));
	tfExpenditureAccount.setBounds(new Rectangle(15, 70, 415, 35));
	tfAssignedAmount.setBounds(new Rectangle(315, 25, 85, 35));
	tfSpentAmount.setBounds(new Rectangle(405, 25, 85, 35));
	tfAvailableAmount.setBounds(new Rectangle(495, 25, 65, 35));
	tfUnitRequest.setBounds(new Rectangle(100, 25, 110, 35));
	tfAuthUnit.setBounds(new Rectangle(100, 55, 110, 35));
	tfAuthPrice.setBounds(new Rectangle(355, 100, 95, 35));
	tfWithoutQtyAssign.setBounds(new Rectangle(230, 10, 100, 35));
	tfCost.setBounds(new Rectangle(470, 25, 90, 35));
	tfAuthCost.setBounds(new Rectangle(465, 100, 95, 35));
	tfAuthCost.setValue(0.0);
	btnBrandDetail.setBounds(new Rectangle(530, 65, 30, 25));
	btnBrandDetail.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent e) {
					  btnBrandDetail_actionPerformed(e);
				      }

				  }
	);
	btnBrandDetail.setEnabled(false);
	rbtnToBuy.setText("Cantidad p/Comprar");
	rbtnToBuy.setBounds(new Rectangle(405, 25, 155, 20));
	rbtnToBuy.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     rbtnToBuy_actionPerformed(e);
				 }

			     }
	);
	rbtnToDeliver.setEnabled(false);
	//rbtnToDeliver.setVisible(false);
	rbtnToBuy.setSelected(true);
	rbtnToBuy.setEnabled(false);
	//rbtnToBuy.setVisible(false);
	rbtnToDeliver.setText("Cantidad p/Entregar");
	rbtnToDeliver.setBounds(new Rectangle(10, 25, 155, 20));
	rbtnToDeliver.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 rbtnToDeliver_actionPerformed(e);
				     }

				 }
	);
	tfNewAvailableAmount.setBounds(new Rectangle(435, 70, 125, 35));
	tfTotalStock.setBounds(new Rectangle(420, 55, 100, 35));
	requestPanel.add(tfCost, null);
	requestPanel.add(tfBrandRequest, null);
	requestPanel.add(tfQtyRequest, null);
	requestPanel.add(tfDestination, null);
	requestPanel.add(tfUnitRequest, null);
	budgetPanel.add(tfNewAvailableAmount, null);
	budgetPanel.add(tfAvailableAmount, null);
	budgetPanel.add(tfSpentAmount, null);
	budgetPanel.add(tfAssignedAmount, null);
	budgetPanel.add(tfExpenditureAccount, null);
	budgetPanel.add(tfCostsCentre, null);
	authPanel.add(tfAvailableStock, null);
	authPanel.add(tfAuthQty, null);
	authPanel.add(tfTotalStock, null);
	authPanel.add(cbBrand, null);
	authPanel.add(tfStock, null);
	authPanel.add(tfAuthPrice, null);
	authPanel.add(tfAuthCost, null);
	authPanel.add(btnBrandDetail, null);
	authPanel.add(tfAuthUnit, null);
	authPanel.add(tfWithoutQtyAssign, null);
	authPanel.add(rbtnToDeliver, null);
	authPanel.add(rbtnToBuy, null);
	content.add(requestPanel, null);
	content.add(authPanel, null);
	content.add(budgetPanel, null);
	content.setLayout(null);
	tfAvailableStock.setBounds(new Rectangle(300, 55, 100, 35));
	this.add(content, null);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	
	ButtonGroup btn = new ButtonGroup();
	btn.add(rbtnToBuy);
	btn.add(rbtnToDeliver);
	
	tfDestination.setEditable(false);
	tfBrandRequest.setEditable(false);
	tfStock.setEditable(false);
	tfStock.setValue(0.0);
	tfQtyRequest.setEditable(false);
	tfUnitRequest.setEditable(false);
	tfAuthUnit.setEditable(false);
	//tfAuthPrice.setEditable(false);
	tfAuthPrice.setValue(0.0);
	tfWithoutQtyAssign.setEditable(false);
	tfWithoutQtyAssign.setValue(0.0);
	tfCostsCentre.setEditable(false);
	//tfAuthCost.setEditable(false);
	tfCost.setEditable(false);
	tfExpenditureAccount.setEditable(false);
	tfAssignedAmount.setEditable(false);
	tfSpentAmount.setEditable(false);
	tfAvailableAmount.setEditable(false);
	tfNewAvailableAmount.setEditable(false);	
	tfTotalStock.setEditable(false);
	tfAvailableStock.setEditable(false);
	cbBrand.addItemListener(new ItemListener(){
	    public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED)  {
		    loadAuthBrand();
		}
	    }
	});
	tfAuthQty.getTextField().addKeyListener(new KeyAdapter() {
		public void keyReleased(KeyEvent e) {
		    calculateTotal();
		}

	    });
	tfAuthPrice.getTextField().addKeyListener(new KeyAdapter() {
		public void keyReleased(KeyEvent e) {
		    calculateTotal();
		}

	    });
	tfWithoutQtyAssign.setEnabled(false);
	tfCostsCentre.setBounds(new Rectangle(15, 25, 275, 35));
	tfExpenditureAccount.setBounds(new Rectangle(15, 70, 410, 35));
	tfAssignedAmount.setBounds(new Rectangle(295, 25, 85, 35));
	tfSpentAmount.setBounds(new Rectangle(385, 25, 85, 35));
	tfAvailableAmount.setBounds(new Rectangle(475, 25, 85, 35));
	tfNewAvailableAmount.setBounds(new Rectangle(430, 70, 130, 35));
	btnClose.setToolTipText("Cancelar");
	tfAuthCost.setEditable(false);
	tfUnitRequest.setHorizontalAlignment(TFInput.CENTER);
	tfAuthUnit.setHorizontalAlignment(TFInput.CENTER);
	tfBrandRequest.setHorizontalAlignment(TFInput.CENTER);
	tfCostsCentre.setHorizontalAlignment(TFInput.CENTER);
	tfExpenditureAccount.setHorizontalAlignment(TFInput.CENTER);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Seleccione cantidad y marca, verificando no sobrepasar el presupuesto y presione \"Aceptar\"");
    }

    private void calculateTotal(){
	if (rbtnToBuy.isSelected()){
	    tfAuthCost.setValue(tfAuthQty.getDouble() * tfAuthPrice.getAmount());   
	    tfNewAvailableAmount.setValue(tfAvailableAmount.getAmount()- tfAuthCost.getAmount());
	} else {
	    tfAuthCost.setValue(0.0);   
	    tfNewAvailableAmount.setValue(0.0);
	}
    }
    
    private void loadAuthBrand() {
	if (resourceAuthBrands == null){
	    resourceAuthBrands = new ResourceBrands(resourcesRequestAuthDetail.getResourceBrands().getResource());
	}
	resourceAuthBrands.setBrands(new Brands(Integer.parseInt(cbBrand.getSelectedValue().toString()), cbBrand.getCombo().getItemTexto()));
	loadPrice();
    }
    
    private void loadPrice(){
	if (resourceAuthBrands.getBrands() != null){
	    if (resourcesRequestAuthDetail.getIdResourcesRequestDetail()!=-1 && resourceAuthBrands.getBrands().getIdBrand() == resourcesRequestAuthDetail.getResourceBrands().getBrands().getIdBrand()){
		tfStock.setValue(resourcesRequestAuthDetail.getResourceBrands().getStock());
		tfAuthPrice.setValue(resourcesRequestAuthDetail.getActualPrice());               
		tfAuthUnit.setValue(resourcesRequestAuthDetail.getResourceBrands().getResource().getUnit().getName());
		
	    } else {
		resourceAuthBrands.retrieveData();    
		tfStock.setValue(resourceAuthBrands.getStock());
		tfAuthPrice.setValue(resourceAuthBrands.getPrice());               
		tfAuthUnit.setValue(resourceAuthBrands.getResource().getUnit().getName());
	    }	    
	    calculateTotal();
	    
	    if (tfStock.getDouble() == 0){
		//btnBrandDetail.setEnabled(false);
		//rbtnToDeliver.setEnabled(false);
		//rbtnToBuy.setSelected(true);
	        //rbtnToDeliver.setSelected(false);
	    } else {
	        //btnBrandDetail.setEnabled(true);
	        //rbtnToDeliver.setEnabled(true);
	        //rbtnToBuy.setSelected(false);
	       // rbtnToDeliver.setSelected(true);
	    }
	}
    }
    
    public boolean saveData(){
	if (( tfAuthQty.getDouble() <= (authQty + tfWithoutQtyAssign.getDouble()) ) ) { //&& (tfAuthQty.getDouble() > 0))  {
            //if (tfNewAvailableAmount.getAmount() >= 0) { // Si el "nuevo saldo" del C.C. es <= 0, entonces no permite continuar, ya que, el C.C. no dispone de dinero
                resourcesRequestAuthDetail.setQty(tfAuthQty.getDouble());
                resourcesRequestAuthDetail.setActualStock(tfStock.getDouble());
                resourcesRequestAuthDetail.setResourceBrands(resourceAuthBrands);
		resourcesRequestAuthDetail.setActualPrice(tfAuthPrice.getAmount());
		resourcesRequestAuthDetail.setDescription(tfDestination.getString());
                resourcesRequestAuthDetail.setAmount(resourcesRequestAuthDetail.getActualPrice() * resourcesRequestAuthDetail.getQty());        
                resourcesRequestAuthDetail.setToBuy(rbtnToBuy.isSelected());
                if (resourcesRequestAuthDetail.getIdResourcesRequestDetail() == -1){
                    resourcesRequestAuthDetail.setIdResourcesRequestDetailRef(resourcesRequestDetail.getIdResourcesRequestDetail());
                }
                if (resourcesRequestAuthDetail.saveData() >= 0)  {
                    if (rbtnToBuy.isSelected()){
                        if (ccExpenditureAccount.getIdCostCentreDetail() == 0){
                            String params = ccExpenditureAccount.getCostCentre().getIdCostCentre() +","+ ccExpenditureAccount.getIDExpenditureAccount() +", 0, 0, 0, 0, 0, 0";
                            //int idCostCentreDetail = LibSQL.getInt("cashflow.addCostsCentreAmountDetail", params);
                            //ccExpenditureAccount.setIdCostCentreDetail(idCostCentreDetail);
                        }
                        //ccExpenditureAccount.updateSpentAmount(resourcesRequestAuthDetail.getAmount());
                    }
                    return true;
                } else{
                    error = 2;
                    return false; 
                }
            /*} else {
                error = 3;
                return false;
            }*/
	} else  {
            error = 1;
	    return false;
	}
    }
    
    private void loadData() {
	loadRequestData();
	loadAuthorizatedData();
        
	/*if (resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getInitialAmount() == 0){
	    getParentInternalFrame().close();
	    Advisor.messageBox("El Centro de Costo solicitante\nno tiene Partida Presupuestaria disponible\npara el tipo de gasto seleccionado", "Partida Presupuestaria no disponible");
	    //Acá la pantalla se cerraría ya que el centro de costo no tiene Part. Presup. asignada 
	} else if (tfAvailableAmount.getAmount() <= 0 ) {
	    getParentInternalFrame().close();
            Advisor.messageBox("El Centro de Costo solicitante\nno tiene Partida Presupuestaria disponible\npara el tipo de gasto seleccionado", "Partida Presupuestaria no disponible");
        }*/ 
	tfAuthQty.getTextField().requestFocus();
    }
    
    private void loadRequestData() {
	tfBrandRequest.setValue(resourcesRequestDetail.getResourceBrands().getBrands().getName());
	tfUnitRequest.setValue(resourcesRequestDetail.getResourceBrands().getResource().getUnit().getName());
	tfDestination.setValue(resourcesRequestDetail.getDescription());
	tfCost.setValue(resourcesRequestDetail.getAmount());
	tfQtyRequest.setValue(resourcesRequestDetail.getQty());
	tfCostsCentre.setValue(resourcesRequestDetail.getResourcesRequest().getCostsCentre().getName());
	tfExpenditureAccount.setValue(resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getName());
	tfAssignedAmount.setValue(resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getInitialAmount());
	tfSpentAmount.setValue(resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getSpentAmount());
	tfAvailableAmount.setValue(resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getAvailableAmount());
	//tfAvailableAmount.setValue(100000);
	tfTotalStock.setValue(resourcesRequestDetail.getResourceBrands().getResource().getTotalStock());
	tfAvailableStock.setValue(resourcesRequestDetail.getResourceBrands().getResource().getAvailableStock());
	if (resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getIDExpenditureAccount() == 0){
	    Advisor.messageBox("Asignar Tipo de Gasto a este Material.","Tipo de Gasto no válido");
	}

    }

    private void loadAuthorizatedData() {
	//tfAuthQty.setText(""+ resourcesRequestAuthDetail.getQty());
	tfAuthQty.setValue(resourcesRequestAuthDetail.getQty());
	if (resourcesRequestAuthDetail.isToBuy()){
	    //rbtnToBuy.setSelected(true);
	} else {
	    //rbtnToDeliver.setSelected(true);
	}
	tfAvailableAmount.setValue(tfAvailableAmount.getAmount() - resourcesRequestAuthDetail.getAmount());
	tfNewAvailableAmount.setValue(resourcesRequestAuthDetail.getAmount());
	cbBrand.setSelectedID(String.valueOf(resourcesRequestAuthDetail.getResourceBrands().getBrands().getIdBrand()));
	loadAuthBrand();
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	if (saveData())  {
	    parentList.refreshLists();      
	    getParentInternalFrame().close();
	} else  {
	    switch (error)  {
	        case 1: Advisor.messageBox("Cantidad no permitida","Cantidad no permitida");
	            break;
	        case 2: Advisor.messageBox("Material y Marca existentes", "Material y Marca existentes");
	            break;
	        case 3: Advisor.messageBox("El Centro de Costo solicitante\nno tiene Partida Presupuestaria disponible\npara el tipo de gasto seleccionado", "Partida Presupuestaria no disponible");
	            break;
	    }
	}
    }     
     
    public void setResourceRequestDetail(ResourcesRequestDetail _resourcesRequestDetail, ResourcesRequestDetail _resourcesRequestAuthDetail){
	resourcesRequestDetail = _resourcesRequestDetail;
	resourcesRequestAuthDetail = _resourcesRequestAuthDetail;
	if (resourcesRequestAuthDetail == null){
	    resourcesRequestAuthDetail = new ResourcesRequestDetail();
	    resourcesRequestAuthDetail.setResourcesRequest(resourcesRequestDetail.getResourcesRequest());
	    resourcesRequestAuthDetail.setResourceBrands(resourcesRequestDetail.getResourceBrands());
	    resourcesRequestAuthDetail.setDestinations(resourcesRequestDetail.getDestinations());    
	    resourcesRequestAuthDetail.setActualPrice(resourcesRequestDetail.getActualPrice());
	    resourcesRequestAuthDetail.setActualStock(resourcesRequestDetail.getActualStock());
	    resourcesRequestAuthDetail.setQty(resourcesRequestDetail.getQty() - resourcesRequestDetail.getAuthorizatedQty());
	    authQty = 0;
	    tfWithoutQtyAssign.setValue(resourcesRequestAuthDetail.getQty());
	} else {
	    authQty = resourcesRequestAuthDetail.getQty();
	    //tfWithoutQtyAssign.setText(""+ (resourcesRequestDetail.getQty() - resourcesRequestDetail.getAuthorizatedQty()));
	    tfWithoutQtyAssign.setValue(resourcesRequestDetail.getQty() - resourcesRequestDetail.getAuthorizatedQty());
	    btnBrandDetail.setEnabled(false);
	}
	
	if (ccExpenditureAccount == null){
	    ccExpenditureAccount = new ExpenditureAccount.CCItem(resourcesRequestDetail.getResourcesRequest().getCostsCentre());
	    ccExpenditureAccount.setIDExpenditureAccount(resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getIDExpenditureAccount());
	    
	}
	loadData();
    }
    
    public void setParentList(ResourcesRequestsAuthList _parentList){
	parentList = _parentList;
    }

    private void btnBrandDetail_actionPerformed(ActionEvent e) {
	resourceBrandsStock = new ResourceBrandsStock();
	resourceBrandsStock.setResourcesRequestDetail(resourcesRequestDetail);
	resourceBrandsStock.setParentList(parentList);
	resourceBrandsStock.setParentMgmt(this);
	resourceBrandsStock.setQtyTo(rbtnToBuy.isSelected());
	
	ExtendedInternalFrame resourceBrandsStockContainer = new ExtendedInternalFrame("Stock del Material: "+ resourcesRequestDetail.getResourceBrands().getResource().getName().toUpperCase());        
	resourceBrandsStockContainer.setDesktop(Environment.getActiveDesktop());
	resourceBrandsStockContainer.setCentralPanel(resourceBrandsStock);         
	resourceBrandsStockContainer.show();
    }

    private void rbtnToBuy_actionPerformed(ActionEvent e) {
	calculateTotal();
    }

    private void rbtnToDeliver_actionPerformed(ActionEvent e) {
	calculateTotal();
    }

}
