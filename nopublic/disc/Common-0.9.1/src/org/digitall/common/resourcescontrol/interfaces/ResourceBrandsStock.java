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
 * ResourceBrandsStock.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Types;

import java.util.Vector;

import javax.swing.ButtonGroup;

import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.ExpenditureAccount.CCItem;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.ResourcesRequestDetail;
import org.digitall.common.resourcesrequests.interfaces.resourcesrequests.ResourcesRequestsAuthList;
import org.digitall.common.resourcesrequests.interfaces.resourcesrequests.ResourcesRequestsAuthMgmt;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicRadioButton;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.GoButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ResourceBrandsStock extends BasicPrimitivePanel {

    private int[] sizeColumnList = {160, 75, 55, 63};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Marcas del Recursos", dataRow);
    private Vector headerList = new Vector();
    private BasicRadioButton rbtnToBuy = new BasicRadioButton();
    private BasicRadioButton rbtnToDeliver = new BasicRadioButton();
    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton btnClose = new CloseButton();
    private BasicPanel budgetPanel = new BasicPanel("Partida Presupuestaria");
    private TFInput tfNewAvailableAmount = new TFInput(DataTypes.MONEY,"NewAvailableAmount",false);
    private TFInput tfCostsCentre = new TFInput(DataTypes.STRING, "CostsCentre", false);
    private TFInput tfExpenditureAccount = new TFInput(DataTypes.STRING, "ExpenditureAccount", false);
    private TFInput tfAssignedAmount = new TFInput(DataTypes.STRING,"AssignedAmount",false);
    private TFInput tfSpentAmount = new TFInput(DataTypes.STRING,"SpentAmount",false);
    private TFInput tfAvailableAmount = new TFInput(DataTypes.MONEY,"AvailableAmount",false);
    private GoButton btnCalculate = new GoButton();
    private ResourcesRequestDetail resourcesRequestAuthDetail;
    private ResourcesRequestDetail resourcesRequestDetail;
    private ResourcesRequestsAuthList parentList;
    private ResourcesRequestsAuthMgmt parentMgmt;
    private double totalAuthQty;
    private TFInput tfWithoutQtyAssign = new TFInput(DataTypes.DOUBLE,"WithoutQuantityAssign",false);
    private BasicPanel content = new BasicPanel();
    private ExpenditureAccount.CCItem ccExpenditureAccount;

    public ResourceBrandsStock() {
	try {
	    jbInit();
	} catch (Exception x) {
	    x.printStackTrace();
	}
    }
	
    private void jbInit() throws Exception {
	this.setSize(new Dimension(410, 525));
	this.setPreferredSize(new Dimension(410, 525));
	budgetPanel.setBounds(new Rectangle(5, 0, 400, 205));
	budgetPanel.setLayout(null);
	tfCostsCentre.setBounds(new Rectangle(15, 25, 370, 35));
	tfExpenditureAccount.setBounds(new Rectangle(15, 70, 370, 35));
	tfAssignedAmount.setBounds(new Rectangle(15, 115, 115, 35));
	tfSpentAmount.setBounds(new Rectangle(143, 115, 115, 35));
	tfAvailableAmount.setBounds(new Rectangle(270, 115, 115, 35));
	tfNewAvailableAmount.setBounds(new Rectangle(15, 160, 115, 35));
	listPanel.setBounds(new Rectangle(5, 245, 400, 235));
	rbtnToBuy.setText("Cantidad p/Comprar");
	rbtnToBuy.setBounds(new Rectangle(250, 215, 155, 18));
	rbtnToBuy.setSize(new Dimension(155, 18));
	rbtnToDeliver.setText("Cantidad p/Entregar");
	rbtnToDeliver.setBounds(new Rectangle(5, 215, 155, 20));
	btnAccept.setBounds(new Rectangle(340, 490, 30, 25));
	btnAccept.addActionListener(new ActionListener() {

				     public void actionPerformed(ActionEvent e) {
					 btnAccept_actionPerformed(e);
				     }

				 }
	);
	btnClose.setBounds(new Rectangle(375, 490, 30, 25));
	btnCalculate.setBounds(new Rectangle(135, 170, 30, 25));
	btnCalculate.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnCalculate_actionPerformed(e);
				    }

				}
	);
	tfWithoutQtyAssign.setBounds(new Rectangle(270, 160, 115, 35));
	btnClose.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnClose_actionPerformed(e);
				    }

				}
	);
	budgetPanel.add(tfWithoutQtyAssign, null);
	budgetPanel.add(btnCalculate, null);
	budgetPanel.add(tfNewAvailableAmount, null);
	budgetPanel.add(tfAvailableAmount, null);
	budgetPanel.add(tfSpentAmount, null);
	budgetPanel.add(tfAssignedAmount, null);
	budgetPanel.add(tfExpenditureAccount, null);
	budgetPanel.add(tfCostsCentre, null);
	content.add(budgetPanel, null);
	content.add(rbtnToDeliver, null);
	content.add(rbtnToBuy, null);
	content.add(listPanel, null);
	content.setLayout(null);
	this.add(content, null);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	
	tfCostsCentre.setEnabled(false);
	tfExpenditureAccount.setEnabled(false);
	tfAssignedAmount.setEnabled(false);
	tfSpentAmount.setEnabled(false);
	tfAvailableAmount.setEnabled(false);	
	tfNewAvailableAmount.setEnabled(false);
	tfWithoutQtyAssign.setEnabled(false);

	ButtonGroup btn = new ButtonGroup();
	btn.add(rbtnToBuy);
	btn.add(rbtnToDeliver);
	
    	setHeaderList();
	listPanel.setCellEditor(Types.DOUBLE, 3);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Brand"));
	headerList.addElement(Environment.lang.getProperty("Price"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Stock"));
	headerList.addElement(Environment.lang.getProperty("Quantity"));
	headerList.addElement("*");
	
	/*listPanel.getTable().addMouseListener(new MouseAdapter() {
		 public void mouseClicked(MouseEvent e) {
		     if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			 loadObjectSelected();
		     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			 
		     }
		 }
    
	     }
	);*/
	listPanel.setParams("resourcescontrol.getAllResourceBrandsStock", "-1", headerList);
    }
    
    public void loadList() {
	String params = ""+ resourcesRequestDetail.getResourceBrands().getResource().getIdResource();
	listPanel.refresh(params);
    }
    
    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	
	} 
    }

    public boolean saveData(){
	boolean result = false;
	if (Calculate()){
	    Vector selectedValues = listPanel.getSelectedsVector();
	    for (int i = 0; i < selectedValues.size(); i++) {
		Vector selectedDataRow = (Vector)selectedValues.elementAt(i);
		
		ResourceBrands resourceBrand = new ResourceBrands(Integer.parseInt(""+ selectedDataRow.elementAt(0)));
		resourceBrand.setResource(new Resource(EntityTypes.RESOURCE, Integer.parseInt(""+ selectedDataRow.elementAt(1))));
		resourceBrand.setBrands(new Brands(Integer.parseInt(""+ selectedDataRow.elementAt(2))));
		
		resourcesRequestAuthDetail = new ResourcesRequestDetail();
		resourcesRequestAuthDetail.setResourcesRequest(resourcesRequestDetail.getResourcesRequest());
		resourcesRequestAuthDetail.setResourceBrands(resourceBrand);
		resourcesRequestAuthDetail.setDestinations(resourcesRequestDetail.getDestinations());    
		resourcesRequestAuthDetail.setActualStock(Double.parseDouble(""+ selectedDataRow.elementAt(6)));
		resourcesRequestAuthDetail.setQty(Double.parseDouble(""+ selectedDataRow.elementAt(7)));
		resourcesRequestAuthDetail.setActualPrice(Double.parseDouble(""+ selectedDataRow.elementAt(4)));        
		resourcesRequestAuthDetail.setAmount(Double.parseDouble(""+ selectedDataRow.elementAt(7)) * Double.parseDouble(""+ selectedDataRow.elementAt(4)));        
		resourcesRequestAuthDetail.setToBuy(rbtnToBuy.isSelected());
		
	        if (resourcesRequestAuthDetail.getIdResourcesRequestDetail() == -1){
	            resourcesRequestAuthDetail.setIdResourcesRequestDetailRef(resourcesRequestDetail.getIdResourcesRequestDetail());
	        }

	        if (resourcesRequestAuthDetail.saveData() >= 0)  {
	            if (ccExpenditureAccount.getIdCostCentreDetail() == 0){
	                String params = ccExpenditureAccount.getCostCentre().getIdCostCentre() +","+ ccExpenditureAccount.getIDExpenditureAccount() +", 0, 0, 0, 0, 0, 0";
	                //int idCostCentreDetail = LibSQL.getInt("cashflow.addCostsCentreAmountDetail", params);
	                //ccExpenditureAccount.setIdCostCentreDetail(idCostCentreDetail);
	            }
	            //ccExpenditureAccount.updateSpentAmount(resourcesRequestAuthDetail.getAmount());
		    
	            result = true;
	        } else{
	            Advisor.messageBox("Recurso y Marca existentes", "Recurso y Marca no válido");
	            result = false;
	            break;
	        }
	    }
	}    
	if (result){
	    parentList.refreshLists();
	    parentMgmt.getParentInternalFrame().close();
	    getParentInternalFrame().close();
	}
	
	return result;
    }
    
    private void btnAccept_actionPerformed(ActionEvent e) {
	saveData();
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private boolean Calculate(){
	Vector selectedValues = listPanel.getSelectedsVector();
	if (selectedValues.size() != 0){
	    double totalQty = 0;
	    for (int i = 0; i < selectedValues.size(); i++) {
		Vector selectedDataRow = (Vector)selectedValues.elementAt(i);
		double cost = Double.parseDouble(""+ selectedDataRow.elementAt(4)) + Double.parseDouble(""+ selectedDataRow.elementAt(7));
		totalQty += Double.parseDouble(""+ selectedDataRow.elementAt(7));
		tfNewAvailableAmount.setValue(tfAvailableAmount.getAmount()- cost);
	    }
	    if ((totalAuthQty - totalQty) >= 0){
		tfWithoutQtyAssign.setValue(totalAuthQty - totalQty);
		return true;
	    } else {
		Advisor.messageBox("La cantidad total para Autorizar\ndebe ser menor o igual a "+ totalAuthQty,"Cantidad no válida");
		return false;
	    }
	} else { 
	    Advisor.messageBox("Debe seleccionar un Item", "Seleccionar Item");
	    return false;
	}
    }
    
    private void loadData(){
	totalAuthQty = resourcesRequestDetail.getQty() - resourcesRequestDetail.getAuthorizatedQty();
	tfWithoutQtyAssign.setValue(totalAuthQty);
	tfCostsCentre.setValue(resourcesRequestDetail.getResourcesRequest().getCostsCentre().getName());
	tfExpenditureAccount.setValue(resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getName());
	tfAssignedAmount.setValue(resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getInitialAmount());
	tfSpentAmount.setValue(resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getSpentAmount());
	tfAvailableAmount.setValue(resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getAvailableAmount());
    }
    
    private void btnCalculate_actionPerformed(ActionEvent e) {
	Calculate();
    }

    public void setResourcesRequestDetail(ResourcesRequestDetail resourcesRequestDetail) {
	this.resourcesRequestDetail = resourcesRequestDetail;
	if (ccExpenditureAccount == null){
	    ccExpenditureAccount = new ExpenditureAccount.CCItem(resourcesRequestDetail.getResourcesRequest().getCostsCentre());
	    ccExpenditureAccount.setIDExpenditureAccount(resourcesRequestDetail.getResourceBrands().getResource().getExpenditureAccount().getIDExpenditureAccount());
	}
	
	loadList();
	loadData();
    }

    public void setParentList(ResourcesRequestsAuthList _parentList) {
	parentList = _parentList;
    }

    public void setParentMgmt(ResourcesRequestsAuthMgmt parentMgmt) {
	this.parentMgmt = parentMgmt;
    }

    public void setQtyTo(boolean toBuy) {
	rbtnToBuy.setSelected(toBuy);
	rbtnToDeliver.setSelected(!toBuy);
    }
}
