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
 * ProvisionOrderMgmt.java
 *
 * */
package org.digitall.common.resourcesrequests.interfaces.provisionorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.common.resourcescontrol.classes.PurchaseOrder;
import org.digitall.common.resourcescontrol.classes.PurchaseOrderDetail;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.ResourcesRequestDetail;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintSaveButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class ProvisionOrderMgmt extends BasicPrimitivePanel {

    private BasicPanel jpData = new BasicPanel();
    private BasicPanel jpHeader = new BasicPanel();
    private BasicPanel jpNorth = new BasicPanel();
    
    private TFInput tfRequestedQty = new TFInput(DataTypes.DOUBLE_EXTENDED, "Cantidad", true);
    private TFInput tfPrice = new TFInput(DataTypes.MONEY_EXTENDED, "Precio", false);
    private TFInput tfCost = new TFInput(DataTypes.MONEY_EXTENDED, "Costo (aprox.)", false);
    private TFInput tfUnit = new TFInput(DataTypes.STRING, "Unit", false);
    private TFInput tfFindResource = new TFInput(DataTypes.STRING, "SearchButton", false);
    private TFInput tfNewResource = new TFInput(DataTypes.STRING, "Nuevio Material", false);
    private TFInput tfHeaderDescription = new TFInput(DataTypes.STRING, "Motivo de la Orden de Provisión", false);
    private TFInput tfDate = new TFInput(DataTypes.SIMPLEDATE, "Date", true);
    private TFInput tfNumber = new TFInput(DataTypes.INTEGER, "Number", true);
    private TFInput tfFindProvider = new TFInput(DataTypes.STRING, "SearchButton", false);
    
    private AssignButton btnAddItem = new AssignButton(true);
    private AddButton btnNewItem = new AddButton();
    private DeleteButton btnDeleteItem = new DeleteButton();
    private PrintSaveButton btnSavePrint = new PrintSaveButton();
    private CloseButton btnCancelClose = new CloseButton();
    private AssignButton btnSaveHeader = new AssignButton(true);
    private AddButton btnNewHeader = new AddButton();
    private FindButton btnFindProvider = new FindButton();

    private CBInput cbBrand = new CBInput(CachedCombo.BRANDS, "BrandSuggested", false);
    private CBInput cbResources = new CBInput(0, "Material seleccionado", false);
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre", false);
    private CBInput cbProvider = new CBInput(0, "Provider", false);

    private int[] sizeColumnList = { 180, 125, 105, 75, 230 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Materiales Solicitados", dataRow) {

	@Override
	public void finishLoading() {
	    btnSavePrint.setEnabled(hasItems());
	}
    };
    private GridLayout layoutJPNorth = new GridLayout();
    private CBInput cbActivity = new CBInput(CachedCombo.ACTIVITY, "Activities", true);
    
    private PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
    private PurchaseOrderDetail selectedPurchaseOrderDetail = new PurchaseOrderDetail();
    private PurchaseOrder purchaseOrder = new PurchaseOrder();
    
    public ProvisionOrderMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setPreferredSize(new Dimension(760, 195));
	this.setSize(new Dimension(760, 512));
	cbResources.setBounds(new Rectangle(405, 20, 285, 35));
	tfFindResource.setEditable(true);
	tfPrice.setEditable(false);
	tfCost.setEditable(false);
	btnAddItem.setBounds(new Rectangle(720, 75, 30, 25));
	btnAddItem.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAddItem_actionPerformed(e);
			      }

			  });
	jpData.setLayout(null);
	cbBrand.setBounds(new Rectangle(10, 65, 285, 35));
	tfNewResource.setBounds(new Rectangle(610, 20, 105, 35));
	btnNewItem.setBounds(new Rectangle(690, 30, 30, 25));
	btnNewItem.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnNewItem_actionPerformed(e);
			      }

			  });
	jpData.add(btnNewItem, null);
	jpData.add(tfRequestedQty, null);
	jpData.add(tfPrice, null);
	jpData.add(tfCost, null);
	jpData.add(tfUnit, null);
	jpData.add(cbBrand, null);
	jpData.add(tfFindResource, null);
	jpData.add(btnDeleteItem, null);
	jpData.add(cbResources, null);
	jpData.add(btnAddItem, null);
	jpData.add(cbActivity, null);
	jpNorth.add(jpHeader, BorderLayout.CENTER);
	jpNorth.add(jpData, BorderLayout.SOUTH);
	tfFindResource.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadResourcesCombo();
			loadResource();
		    }
		}

	    });
	tfRequestedQty.getTextField().addKeyListener(new KeyAdapter() {

		public void keyReleased(KeyEvent e) {
		    calculateTotal();
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			saveDetailData();
		    }
		}

	    });
	cbResources.addItemListener(new ItemListener() {

		 public void itemStateChanged(ItemEvent evt) {
		     if (evt.getStateChange() == ItemEvent.SELECTED) {
			 loadResource();
		     }
		 }
    
	     });
	cbBrand.getCombo().addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent evt) {
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
			loadBrand();
		    }
		}
    
	    });

	btnSaveHeader.setBounds(new Rectangle(710, 80, 30, 25));
	btnSaveHeader.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnSaveHeader_actionPerformed(e);
				    }

				});
	btnNewHeader.setBounds(new Rectangle(670, 80, 30, 25));
	btnNewHeader.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnNewHeader_actionPerformed(e);
				    }

				});
	cbProvider.setBounds(new Rectangle(335, 25, 210, 35));
	btnFindProvider.setBounds(new Rectangle(515, 40, 30, 25));
	jpNorth.setLayout(layoutJPNorth);
	jpNorth.setMinimumSize(new Dimension(1, 220));
	jpNorth.setPreferredSize(new Dimension(1, 220));
	tfRequestedQty.setBounds(new Rectangle(380, 65, 85, 35));
	tfPrice.setBounds(new Rectangle(300, 65, 75, 35));
	tfCost.setBounds(new Rectangle(600, 65, 90, 35));
	tfUnit.setBounds(new Rectangle(470, 65, 125, 35));
	tfFindResource.setBounds(new Rectangle(330, 20, 70, 35));
	tfDate.setBounds(new Rectangle(115, 25, 100, 35));
	tfFindProvider.setBounds(new Rectangle(225, 25, 105, 35));
	layoutJPNorth.setRows(2);
	cbActivity.setBounds(new Rectangle(10, 20, 315, 35));
	tfNumber.setBounds(new Rectangle(10, 25, 95, 35));
	cbCostsCentre.setBounds(new Rectangle(550, 25, 200, 35));
	tfHeaderDescription.setBounds(new Rectangle(10, 65, 650, 35));
	jpHeader.add(cbProvider, null);
	jpHeader.add(tfFindProvider, null);
	jpHeader.add(cbCostsCentre, null);
        jpHeader.add(tfHeaderDescription, null);
	jpHeader.add(tfNumber, null);
	jpHeader.add(tfDate, null);
	jpHeader.add(btnNewHeader, null);
        jpHeader.add(btnSaveHeader, null);
        jpHeader.setLayout(null);
	tfFindProvider.getTextField().addKeyListener(new KeyAdapter() {

	       public void keyTyped(KeyEvent e) {
		   if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
		       loadProvidersCombo();
		   }
	       }
    
	   });
	
	this.add(jpNorth, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	setHeaderList();

	btnDeleteItem.setBounds(new Rectangle(715, 30, 40, 25));
	btnDeleteItem.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDeleteItem_actionPerformed(e);
				 }

			     });
	addButton(btnCancelClose);
	addButton(btnSavePrint);
	btnCancelClose.setToolTipText("Cancelar la Orden de Provisión");
	btnSavePrint.setToolTipText("Grabar e imprimir la Orden de provisión");
	btnSavePrint.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnSavePrint_actionPerformed(e);
				    }

				});
	btnCancelClose.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancelClose_actionPerformed(e);
				 }

			     });
	btnNewHeader.setToolTipText("Limpiar campos");
	btnSaveHeader.setToolTipText("Agregar Orden de Provisión");
	btnNewItem.setToolTipText("Limpiar campos");
	btnAddItem.setToolTipText("Agregar el material seleccionado");
	btnDeleteItem.setToolTipText("Quitar el material seleccionado");
        jpHeader.setBorder(BorderPanel.getBorderPanel("Datos de la Orden de Provisión"));
        jpData.setBorder(BorderPanel.getBorderPanel("Agregar Material"));
	tfUnit.setHorizontalAlignment(TFInput.CENTER);
	btnSavePrint.setEnabled(false);
	tfUnit.setEditable(false);
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadSelectedObject();
					       }
					   }
				       }
	);
	restart();
    }

    private void loadResourcesCombo() {
	cbResources.loadJCombo(LibSQL.exFunction("resourcescontrol.getAllResourcesByFilter", tfFindResource.getStringForSQL() + ",0"));
    }
    
    private void loadProvidersCombo() {
	cbProvider.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", tfFindProvider.getStringForSQL()));
    }


    private void loadResource() {
	ResourceBrands resourceBrands = new ResourceBrands();
	if (cbResources.getCombo().getValuesVector().size() > 0) {
	    resourceBrands.setResource(new Resource(EntityTypes.RESOURCE, Integer.parseInt(cbResources.getSelectedValue().toString())));
	    resourceBrands.getResource().setName("" + cbResources.getSelectedItem());
	    resourceBrands.setBrands(new Brands(Integer.parseInt(cbBrand.getSelectedValue().toString()), cbBrand.getCombo().getItemTexto()));
	    purchaseOrderDetail.setResourceBrand(resourceBrands);
	    purchaseOrderDetail.getResourceBrand().getResource().retrieveData();
	}
	loadBrand();
    }
    
    private void loadBrand() {
        System.out.println("--> "+ purchaseOrderDetail.getResourceBrand().getResource() );
	ResourceBrands resourceBrands = new ResourceBrands(purchaseOrderDetail.getResourceBrand().getResource());
	resourceBrands.setBrands(new Brands(Integer.parseInt(cbBrand.getSelectedValue().toString()), cbBrand.getCombo().getItemTexto()));
	if (resourceBrands.getResource() != null) {
	    if (resourceBrands.getBrands() != null) {
		resourceBrands.retrieveData();
		tfUnit.setValue(resourceBrands.getResource().getUnit().getName());
		tfPrice.setValue(resourceBrands.getPrice());
		calculateTotal();
	    }
	}
    }

    private void btnAddItem_actionPerformed(ActionEvent e) {
	saveDetailData();
    }

    private boolean saveDetailData() {
	boolean _returns = false;
	if (validateDetailData()) {
	    purchaseOrderDetail = new PurchaseOrderDetail();
	    purchaseOrderDetail.setPurchaseOrder(purchaseOrder);
	    purchaseOrderDetail.setApprovedQty(tfRequestedQty.getDouble());
	    purchaseOrderDetail.setDescription(cbActivity.getSelectedItem().toString());
	    purchaseOrderDetail.setResourcesRequestAuthDetailRef(new ResourcesRequestDetail());

	    ResourceBrands resourceBrands = new ResourceBrands();
	    resourceBrands.setBrands(new Brands(Integer.parseInt("" + cbBrand.getSelectedValue())));
	    resourceBrands.setResource(new Resource(EntityTypes.RESOURCE, Integer.parseInt("" + cbResources.getSelectedValue())));

	    purchaseOrderDetail.setResourceBrand(resourceBrands);
	    purchaseOrderDetail.setPrice(tfPrice.getAmount());
	    purchaseOrderDetail.setAmount(tfCost.getAmount());

	    double amount = purchaseOrderDetail.getResourceBrand().getPrice() * purchaseOrderDetail.getApprovedQty();
	    purchaseOrderDetail.setAmount(amount);
	    int result = purchaseOrderDetail.saveDataByProvisionOrder();
	    if (result >= 0) {
		refresh();
		newItem();
		_returns = true;
	    } else if (result == -2) { //==> no tiene suficiente dinero en la P.P.  para solicitar el recurso
		Advisor.messageBox("No dispone de suficiente dinero en la Partida Presupestaria\npara el material seleccionado", "Error");
	    }
	}
	return _returns;
    }

    private void newItem() {
	purchaseOrderDetail = new PurchaseOrderDetail();
	tfFindResource.setValue("");
	tfNewResource.setValue("");
	tfRequestedQty.setValue(0.0);
	calculateTotal();
    }
    
    private boolean validateDetailData() {
	boolean result = false;
	if (cbResources.getSelectedIndex() < 0) {
	    Advisor.messageBox("No se ha seleccionado ningún Material", "Error");
	} else if (cbBrand.getSelectedIndex() < 0) {
	    Advisor.messageBox("No se ha seleccionado una Marca", "Error");
	} else if (tfRequestedQty.getDouble() <= 0) {
	    Advisor.messageBox("La cantidad debe ser mayor que cero", "Error");
	} else {
	    result = true;
	}
	return result;
    }

    private void btnNewItem_actionPerformed(ActionEvent e) {
	newItem();
    }
    
    private void setHeaderTitle() {
	purchaseOrder.retrieveData();
	loadHeaderData();
    }

    private void btnSaveHeader_actionPerformed(ActionEvent e) {
	if (saveHeaderData()) {
	    enableHeaderPanel(false);
	    purchaseOrderDetail = new PurchaseOrderDetail();
	    Environment.addUnfinishedTask(this.getParentInternalFrame());
	}
    }
    
    private boolean saveHeaderData() {
	boolean returns = false;
	/* Las ordenes de provision se registran en la tabla de Ordenes de Compras con el campo provisionNote = true*/
	if (validateHeaderData()) {
	    purchaseOrder.setIdPurchaseOrder(-1);
	    purchaseOrder.setIsProvisionOrder(true);
	    purchaseOrder.setNumber("0");
	    purchaseOrder.setDate(Proced.setFormatDate(tfDate.getDate(), false));
	    purchaseOrder.setAmount(0);
	    purchaseOrder.setDescription(cbActivity.getSelectedItem().toString());
	    purchaseOrder.setCostsCentre(new CostsCentre(Integer.parseInt(cbCostsCentre.getSelectedValue().toString())));
	    purchaseOrder.setProvider(new Provider(Integer.parseInt(cbProvider.getSelectedValue().toString())));
	    purchaseOrder.setIdRequestStatus(4); /** IdRequestStatus = 4 --> Autorizado para comprar */
	    if (purchaseOrder.saveProvisionOrder() >= 0) {
		purchaseOrder.retrieveData();
	        tfNumber.setValue(purchaseOrder.getNumber());
	        tfNumber.setVisible(true);
	        refresh();
	        enableDetailPanel(true);
	        setHeaderTitle();
	        returns = true;
	    } else {
	        Advisor.messageBox("Se produjo un error al grabar los datos", "Atención");
	    }
	}
	return returns;
    }

    private boolean validateHeaderData() {
	boolean result = false;
	if (tfNumber.getString().equals(""))  {
	    Advisor.messageBox("El campo \"Nº\" no debe estar vacío","Error");
	} else if (tfDate.getDate().equals("")) {
	    Advisor.messageBox("El campo \"Fecha\" no debe estar vacío","Error");
	} else if ((Proced.compareDates(Proced.setFormatDate(tfDate.getDate(), false), Environment.currentDate)) == 2 ) {
	    Advisor.messageBox("La Fecha de Solicitud no debe ser superior a la Fecha Actual","Error");
	} else if (cbProvider.getSelectedIndex() == -1) {
	    Advisor.messageBox("No se ha seleccionado un Proveedor","Error");
	} else if (cbCostsCentre.getSelectedIndex() == -1) {
	    Advisor.messageBox("No se ha seleccionado un Centro de Costos","Error");
	} else {
	    result = true;
	}
	return result;
    }

    private void btnNewHeader_actionPerformed(ActionEvent e) {
	restart();
    }

    private void loadHeaderData() {
	tfNumber.setValue(purchaseOrder.getNumber());
	tfDate.setValue(Proced.setFormatDate(purchaseOrder.getDate(), true));
	cbCostsCentre.setSelectedID(purchaseOrder.getCostsCentre().getIdCostCentre());
	cbActivity.setSelectedValue(purchaseOrder.getDescription());
	tfFindProvider.setValue("");
	btnAddItem.setEnabled(true);
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Material"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Brand"));
	headerList.addElement(Environment.lang.getProperty("Quantity"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Unit"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Description"));
	headerList.addElement("*");
	listPanel.setParams("resourcescontrol.getAllPurchaseOrderDetailByProvisionOrder", "-1", headerList);
    }
 
    public void refresh() {
	if (purchaseOrder != null) {
	    listPanel.refresh(purchaseOrder.getIdPurchaseOrder());
	    selectedPurchaseOrderDetail = new PurchaseOrderDetail();
	}
    }

    private void loadSelectedObject() {
	if (!dataRow.isEmpty()) {
	    selectedPurchaseOrderDetail.setIdPurchaseOrderDetail(Integer.parseInt("" + dataRow.elementAt(0)));
	}
    }

    private void restart() {
	Environment.removeUnfinishedTask(this.getParentInternalFrame());
	enableDetailPanel(false);
	enableHeaderPanel(true);
	tfNumber.setEditable(false);
	tfNumber.setValue(0);
	tfNumber.setVisible(false);
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfFindProvider.setValue("");
	tfHeaderDescription.setValue("");
	tfRequestedQty.setValue(0.0);
	purchaseOrder = new PurchaseOrder();
	purchaseOrderDetail = new PurchaseOrderDetail();
	tfDate.requestFocus();
	refresh();
    }

    private void enableDetailPanel(boolean _valor) {
	tfFindResource.setEnabled(_valor);
	cbBrand.setEnabled(_valor);
	cbResources.setEnabled(_valor);
	tfRequestedQty.setEnabled(_valor);
	tfNewResource.setEnabled(_valor);
	cbActivity.setEnabled(_valor);
	btnNewItem.setEnabled(_valor);
	btnAddItem.setEnabled(_valor);
	btnDeleteItem.setEnabled(_valor);
	tfNumber.setVisible(_valor);
	tfPrice.setEnabled(_valor);
	tfCost.setEnabled(_valor);
	tfUnit.setEnabled(_valor);
    }

    private void enableHeaderPanel(boolean _valor) {
	tfNumber.setVisible(!_valor);
	tfDate.setEnabled(_valor);
	tfFindProvider.setEnabled(_valor);
	cbProvider.setEnabled(_valor);
	btnFindProvider.setEnabled(_valor);
	cbCostsCentre.setEnabled(_valor);
	tfHeaderDescription.setEnabled(_valor);
	btnSaveHeader.setEnabled(_valor);
	btnNewHeader.setEnabled(_valor);
    }

    private void btnDeleteItem_actionPerformed(ActionEvent e) {
	if (purchaseOrderDetail != null) {
	    if (selectedPurchaseOrderDetail.getIdPurchaseOrderDetail() != -1) {
		if (Advisor.question("Aviso", "¿Está seguro de querer quitar el material?")) {
		    selectedPurchaseOrderDetail.delete();
		    refresh();
		}
	    } else {
	        Advisor.messageBox("Debe seleccionar un material para quitar", "Error");
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar un material para quitar", "Error");
	}
    }

    private void btnSavePrint_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    restart();
	}
    }
    
    public boolean saveData() {
	boolean _returns = false;
	if (Advisor.question("Aviso", "¿Está seguro de grabar la Orden de Provisión?")) {
	    if (listPanel.hasItems())  {
		if (purchaseOrder.getIdPurchaseOrder() != -1) {
		    if (purchaseOrder.addBudgetMovementByProvisionOrder() > 0) {
		        Environment.removeUnfinishedTask(this.getParentInternalFrame());
			BasicReport report = new BasicReport(ProvisionOrderMgmt.class.getResource("xml/ProvisionOrder.xml"));
			report.addTableModel("resourcescontrol.xmlGetProvisionOrder", "" + purchaseOrder.getIdPurchaseOrder());
			report.addTableModel("resourcescontrol.xmlGetAllProvisionOrderDetail", "" + purchaseOrder.getIdPurchaseOrder());
			report.doReport();
			_returns = true;
		    } else {
			Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
		    }
		} else {
		    Advisor.messageBox("No se puede grabar una Orden de Provisión sin detalle", "Error");
		}
	    } else  {
	        Advisor.messageBox("No se puede grabar una Orden de Provisión sin detalle", "Error");
	    }
	}
	return _returns;
    }

    private void btnCancelClose_actionPerformed(ActionEvent e) {
	if (purchaseOrder.getIdPurchaseOrder() != -1)  {
	    if (Advisor.question("Aviso", "¿Está seguro de cancelar la Orden de Provisión?")) {
		purchaseOrder.delProvisionOrder();
	        restart();
	        getParentInternalFrame().setIcon(true);
	    }
	} else {
	    getParentInternalFrame().setIcon(true);
	}
    }

    private void calculateTotal() {
	tfCost.setValue(tfRequestedQty.getAmount() * tfPrice.getAmount());
    }
    
}

