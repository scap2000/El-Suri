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
 * ResourcesRequestDetailMgmt.java
 *
 * */
package org.digitall.apps.resourcesrequests.interfaces.resourcesrequests;

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

import javax.swing.JOptionPane;

import org.digitall.apps.resourcesrequests.interfaces.resourcesrequests.classes.errclasses.ErrorsResourcesRequestsMgmt;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Destinations;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.classes.ResourcesRequestDetail;
import org.digitall.common.resourcesrequests.classes.ResourcesRequest;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AddComboButton;
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

public class ResourcesRequestDetailMgmt extends BasicPrimitivePanel {

    private BasicPanel jpData = new BasicPanel();
    private BasicPanel jpHeader = new BasicPanel();
    private BasicPanel jpNorth = new BasicPanel();
    
    private TFInput tfRequestedQty = new TFInput(DataTypes.DOUBLE, "Cantidad", true);
    private TFInput tfPrice = new TFInput(DataTypes.MONEY_EXTENDED, "Precio", false);
    private TFInput tfCost = new TFInput(DataTypes.MONEY, "Costo (aprox.)", false);
    private TFInput tfUnit = new TFInput(DataTypes.STRING, "Unit", false);
    private TFInput tfFindResource = new TFInput(DataTypes.STRING, "SearchButton", false);
    private TFInput tfNewResource = new TFInput(DataTypes.STRING, "Nuevo Material", false);
    private TFInput tfHeaderDescription = new TFInput(DataTypes.STRING, "Motivo del pedido", false);
    private TFInput tfDate = new TFInput(DataTypes.SIMPLEDATE, "Date", true);
    private TFInput tfNumber = new TFInput(DataTypes.INTEGER, "Number", true);
    private TFInput tfFindSolicitor = new TFInput(DataTypes.STRING, "SearchButton", false);
    
    private AssignButton btnAddItem = new AssignButton(true);
    private AddButton btnNewItem = new AddButton();
    private DeleteButton btnDeleteItem = new DeleteButton();
    private PrintSaveButton btnSavePrint = new PrintSaveButton();
    private CloseButton btnCancelClose = new CloseButton();
    private AssignButton btnSaveHeader = new AssignButton(true);
    private AddButton btnNewHeader = new AddButton();
    private FindButton btnFindSolicitor = new FindButton();
    private AddComboButton btnAddResource = new AddComboButton();
    
    private CBInput cbBrand = new CBInput(CachedCombo.BRANDS, "BrandSuggested", false);
    private CBInput cbResources = new CBInput(0, "Material seleccionado", false);
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre", false);
    private CBInput cbSolicitor = new CBInput(0, "Solicitor", false);

    private int[] sizeColumnList = { 388, 202, 125 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Materiales Solicitados", dataRow) {

	@Override
	public void finishLoading() {
	    btnSavePrint.setEnabled(hasItems());
	}
    };
    private GridLayout layoutJPNorth = new GridLayout();
    private CBInput cbActivity = new CBInput(CachedCombo.ACTIVITY, "Activities", true);

    private ResourcesRequestDetail resourcesRequestDetail;
    private ResourcesRequestDetail selectedResourcesRequestDetail;
    private ResourcesRequest resourcesRequest;

    public ResourcesRequestDetailMgmt() {
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
	btnNewItem.setBounds(new Rectangle(690, 75, 30, 25));
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
        jpData.add(btnAddResource, null);
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
	cbSolicitor.setBounds(new Rectangle(335, 25, 210, 35));
	btnFindSolicitor.setBounds(new Rectangle(515, 40, 30, 25));
	jpNorth.setLayout(layoutJPNorth);
	jpNorth.setMinimumSize(new Dimension(1, 220));
	jpNorth.setPreferredSize(new Dimension(1, 220));
	tfRequestedQty.setBounds(new Rectangle(380, 65, 85, 35));
	tfPrice.setBounds(new Rectangle(300, 65, 75, 35));
	tfCost.setBounds(new Rectangle(600, 65, 90, 35));
	tfUnit.setBounds(new Rectangle(470, 65, 125, 35));
	tfFindResource.setBounds(new Rectangle(330, 20, 70, 35));
	tfDate.setBounds(new Rectangle(115, 25, 100, 35));
	tfFindSolicitor.setBounds(new Rectangle(225, 25, 105, 35));
	layoutJPNorth.setRows(2);
	cbActivity.setBounds(new Rectangle(10, 20, 315, 35));
	tfNumber.setBounds(new Rectangle(10, 25, 95, 35));
	cbCostsCentre.setBounds(new Rectangle(550, 25, 200, 35));
	tfHeaderDescription.setBounds(new Rectangle(10, 65, 650, 35));
	jpHeader.add(cbSolicitor, null);
	jpHeader.add(tfFindSolicitor, null);
	jpHeader.add(cbCostsCentre, null);
	jpHeader.add(tfHeaderDescription, null);
	jpHeader.add(tfNumber, null);
	jpHeader.add(tfDate, null);
	jpHeader.add(btnNewHeader, null);
	jpHeader.add(btnSaveHeader, null);
	jpHeader.setLayout(null);
	tfFindSolicitor.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadSolicitorCombo();
		    }
		}

	    });

	this.add(jpNorth, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	setHeaderList();

	btnDeleteItem.setBounds(new Rectangle(720, 30, 30, 25));
        btnAddResource.setBounds(new Rectangle(690, 30, 30, 25));
        btnAddResource.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnAddResource_actionPerformed(e);
                }
            });
        btnDeleteItem.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDeleteItem_actionPerformed(e);
				 }

			     });
	addButton(btnCancelClose);
	addButton(btnSavePrint);
	btnCancelClose.setToolTipText("Cancelar el Pedido de Materiales");
	btnSavePrint.setToolTipText("Grabar e imprimir el Pedido de Materiales");
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
	btnSaveHeader.setToolTipText("Agregar Pedido de Materiales");
	btnNewItem.setToolTipText("Limpiar campos");
        btnAddResource.setToolTipText("Agregar Material");
	btnAddItem.setToolTipText("Agregar el material seleccionado");
	btnDeleteItem.setToolTipText("Quitar el material seleccionado");
        jpHeader.setBorder(BorderPanel.getBorderPanel("Datos del Pedido de Materiales"));
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

    private void loadResource() {
	ResourceBrands resourceBrands = new ResourceBrands();
	if (cbResources.getCombo().getValuesVector().size() > 0) {
	    resourceBrands.setResource(new Resource(EntityTypes.RESOURCE, Integer.parseInt(cbResources.getSelectedValue().toString())));
	    resourceBrands.getResource().setName("" + cbResources.getSelectedItem());
	    resourceBrands.setBrands(new Brands(Integer.parseInt(cbBrand.getSelectedValue().toString()), cbBrand.getCombo().getItemTexto()));
	    resourcesRequestDetail.setResourceBrands(resourceBrands);
	    resourcesRequestDetail.getResourceBrands().getResource().retrieveData();
	}
	loadBrand();
    }

    private void loadBrand() {
	ResourceBrands resourceBrands = new ResourceBrands(resourcesRequestDetail.getResourceBrands().getResource());
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
	    resourcesRequestDetail = new ResourcesRequestDetail();
	    resourcesRequestDetail.setQty(tfRequestedQty.getDouble());
	    resourcesRequestDetail.setDescription(cbActivity.getSelectedItem().toString());
	    ResourceBrands resourceBrands = new ResourceBrands();
	    resourceBrands.setBrands(new Brands(Integer.parseInt("" + cbBrand.getSelectedValue())));
	    resourceBrands.setResource(new Resource(EntityTypes.RESOURCE, Integer.parseInt("" + cbResources.getSelectedValue())));
	    resourcesRequestDetail.setDestinations(new Destinations());
	    resourcesRequestDetail.setEstado("p");
	    resourcesRequestDetail.setResourceBrands(resourceBrands);
	    resourcesRequestDetail.setResourcesRequest(resourcesRequest);
	    resourcesRequestDetail.setActualPrice(tfPrice.getAmount());
	    resourcesRequestDetail.setAmount(tfCost.getAmount());
	    resourcesRequestDetail.setIdRequestStatus(2);
	    int result = resourcesRequestDetail.saveRequestData();
	    if (result >= 0) {
		refresh();
		newItem();
		_returns = true;
	    } else {
	    }
	}
	return _returns;
    }

    private void newItem() {
	resourcesRequestDetail = new ResourcesRequestDetail();
	tfFindResource.setValue("");
	tfNewResource.setValue("");
	tfRequestedQty.setValue(0.0);
	calculateTotal();
    }

    private boolean validateDetailData() {
	boolean result = false;
	if (cbResources.getSelectedIndex() < 0) {
	    ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.EMPTY_RESOURCE);
	} else if (cbBrand.getSelectedIndex() < 0) {
	    ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.EMPTY_BRAND);
	} else if (tfRequestedQty.getDouble() <= 0) {
	    ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.INVALID_QTY);
	} else {
	    result = true;
	}
	return result;
    }

    private void btnNewItem_actionPerformed(ActionEvent e) {
	newItem();
    }

    private void loadSolicitorCombo() {
	cbSolicitor.loadJCombo(LibSQL.exFunction("org.getAllPersonsByFilter", tfFindSolicitor.getStringForSQL()));
    }

    private void setHeaderTitle() {
	resourcesRequest.retrieveData();
	loadHeaderData();
    }

    private void btnSaveHeader_actionPerformed(ActionEvent e) {
	if (saveHeaderData()) {
	    enableHeaderPanel(false);
	    resourcesRequestDetail = new ResourcesRequestDetail();
	    Environment.addUnfinishedTask(this.getParentInternalFrame());
	}
    }
    
    private boolean saveHeaderData() {
	boolean returns = false;
	if (validateHeaderData()) {
	    resourcesRequest = new ResourcesRequest();
	    resourcesRequest.setNumber(0);
	    resourcesRequest.setDate(Proced.setFormatDate(tfDate.getDate(), false));
	    resourcesRequest.setIdSolicitor(Integer.parseInt("" + cbSolicitor.getSelectedValue()));
	    resourcesRequest.setDescription(tfHeaderDescription.getString());
	    resourcesRequest.setEstado("p");
	    resourcesRequest.setIdRequestStatus(2);
	    resourcesRequest.setCostsCentre(new CostsCentre(Integer.parseInt("" + cbCostsCentre.getSelectedValue())));
	    if (resourcesRequest.addData() >= 0) {
		resourcesRequest.retrieveData();
		tfNumber.setValue(resourcesRequest.getNumber());
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
	    ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.EMPTY_NUMBER);
	} else if (tfDate.getDate().equals("")) {
	    ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.EMPTY_DATE);
	} else if ((Proced.compareDates(Proced.setFormatDate(tfDate.getDate(), false), Environment.currentDate)) == 2 ) {
	    ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.INVALID_DATE);
	} else if (cbSolicitor.getSelectedIndex() == -1) {
	    ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.EMPTY_SOLICITOR);
	} else if (cbCostsCentre.getSelectedIndex() == -1) {
	    ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.EMPTY_COSTSCENTRE);
	} else {
	    result = true;
	}
	return result;
    }

    private void btnNewHeader_actionPerformed(ActionEvent e) {
	restart();
    }

    private void loadHeaderData() {
	tfNumber.setValue(resourcesRequest.getNumber());
	tfDate.setValue(Proced.setFormatDate(resourcesRequest.getDate(), true));
	cbCostsCentre.setSelectedID(resourcesRequest.getCostsCentre().getIdCostCentre());
	cbActivity.setSelectedValue(resourcesRequest.getDescription());
	tfFindSolicitor.setValue("");
	/* 2010-07-30: CHECKPOINT ¿Qué es idRequestStatus? */
	if (resourcesRequest.getIdRequestStatus() > 2) {
	    btnAddItem.setEnabled(false);
	} else {
	    btnAddItem.setEnabled(true);
	}
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
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");//Environment.lang.getProperty("AuthorizedQuantity"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");//Environment.lang.getProperty("Status"));
	headerList.addElement("*");
	headerList.addElement("*");//Environment.lang.getProperty("Priority"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	listPanel.setParams("resourcescontrol.getAllResourcesRequestDetail", "-1", headerList);
    }

    public void refresh() {
	if (resourcesRequest != null) {
	    listPanel.refresh(resourcesRequest.getIdResourcesRequest());
	    selectedResourcesRequestDetail = new ResourcesRequestDetail(ResourcesRequest.REQUESTED, resourcesRequest);
	}
    }

    private void loadSelectedObject() {
	if (!dataRow.isEmpty()) {
	    selectedResourcesRequestDetail = new ResourcesRequestDetail(ResourcesRequest.REQUESTED, new ResourcesRequest(Integer.parseInt("" + dataRow.elementAt(1))));
	    selectedResourcesRequestDetail.setIdResourcesRequestDetail(Integer.parseInt("" + dataRow.elementAt(0)));
	    /*selectedResourcesRequestDetail.setResourceBrands(new ResourceBrands());
	    selectedResourcesRequestDetail.getResourceBrands().setResource(new Resource(EntityTypes.RESOURCE, Integer.parseInt("" + dataRow.elementAt(2))));
	    selectedResourcesRequestDetail.getResourceBrands().getResource().setName("" + dataRow.elementAt(3));
	    selectedResourcesRequestDetail.getResourceBrands().setBrands(new Brands(Integer.parseInt("" + dataRow.elementAt(4)), "" + dataRow.elementAt(5)));
	    selectedResourcesRequestDetail.getResourceBrands().getResource().setUnit(new Units(Integer.parseInt("" + dataRow.elementAt(7)), "" + dataRow.elementAt(8)));
	    selectedResourcesRequestDetail.getResourceBrands().setPrice(Double.parseDouble("" + dataRow.elementAt(9)));
	    selectedResourcesRequestDetail.setQty(Double.parseDouble("" + dataRow.elementAt(6)));
	    selectedResourcesRequestDetail.setActualPrice(Double.parseDouble("" + dataRow.elementAt(9)));
	    selectedResourcesRequestDetail.setAmount(Double.parseDouble("" + dataRow.elementAt(10)));
	    selectedResourcesRequestDetail.setAuthorizatedQty(Double.parseDouble("" + dataRow.elementAt(11)));
	    selectedResourcesRequestDetail.setDestinations(new Destinations(Integer.parseInt("" + dataRow.elementAt(12))));
	    selectedResourcesRequestDetail.setIdRequestStatus(Integer.parseInt("" + dataRow.elementAt(14).toString()));
	    selectedResourcesRequestDetail.setIdPriorityRequest(Integer.parseInt("" + dataRow.elementAt(16).toString()));
	    selectedResourcesRequestDetail.setDescription(dataRow.elementAt(18).toString());
	    selectedResourcesRequestDetail.setActualStock(Double.parseDouble("" + dataRow.elementAt(20)));*/
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
	tfFindSolicitor.setValue("");
	/*if (Environment.person.getIdPerson() != -1) {
	    cbSolicitor.getCombo().addItem(Environment.person.getFullName(), "" + Environment.person.getIdPerson());
	}*/
	tfHeaderDescription.setValue("");
        tfRequestedQty.setValue(0.0);
	resourcesRequest = new ResourcesRequest();
	resourcesRequestDetail = new ResourcesRequestDetail();
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
        btnAddResource.setEnabled(_valor);
    }

    private void enableHeaderPanel(boolean _valor) {
	tfNumber.setVisible(!_valor);
	tfDate.setEnabled(_valor);
	tfFindSolicitor.setEnabled(_valor);
	cbSolicitor.setEnabled(_valor);
	btnFindSolicitor.setEnabled(_valor);
	cbCostsCentre.setEnabled(_valor);
	tfHeaderDescription.setEnabled(_valor);
	btnSaveHeader.setEnabled(_valor);
	btnNewHeader.setEnabled(_valor);
    }

    private void btnDeleteItem_actionPerformed(ActionEvent e) {
	if (resourcesRequestDetail != null) {
	    if ((selectedResourcesRequestDetail.getIdResourcesRequestDetail() != -1) && (selectedResourcesRequestDetail.isDelete())) {
		if (Advisor.question("Aviso", "¿Está seguro de querer quitar el material?")) {
		    selectedResourcesRequestDetail.delData();
		    refresh();
		}
	    } else {
	        ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.EMPTY_ITEM);
	    }
	} else {
	    ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.EMPTY_ITEM);
	}
    }

    private void btnSavePrint_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    restart();
	}
    }
    
    public boolean saveData() {
	boolean _returns = false;
	if (Advisor.question("Aviso", "¿Está seguro de grabar el Pedido de Materiales?")) {
	    if (listPanel.hasItems())  {
		if (resourcesRequest.getIdResourcesRequest() != -1) {
		    if (resourcesRequest.toRecord() >= 0) {
		        Environment.removeUnfinishedTask(this.getParentInternalFrame());
			BasicReport report = new BasicReport(ResourcesRequestsMain.class.getResource("xml/resourcesRequest.xml"));
			report.addTableModel("resourcescontrol.xmlGetResourcesRequest", "" + resourcesRequest.getIdResourcesRequest());
			report.addTableModel("resourcescontrol.xmlGetAllResourcesRequestDetail", "" + resourcesRequest.getIdResourcesRequest());
			report.doReport();
			_returns = true;
		    } else {
		        ErrorsResourcesRequestsMgmt.showErrorMessage(-1);
		    }
		} else {
		    ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.EMPTY_DETAIL);
		}
	    } else  {
	        ErrorsResourcesRequestsMgmt.showErrorMessage(ErrorsResourcesRequestsMgmt.EMPTY_DETAIL);
	    }
	}
	return _returns;
    }

    private void btnCancelClose_actionPerformed(ActionEvent e) {
	if (resourcesRequest.getIdResourcesRequest() != -1) {
	    if (Advisor.question("Aviso", "¿Está seguro de cancelar el Pedido de Materiales?")) {
		resourcesRequest.delData();
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

    private void btnAddResource_actionPerformed(ActionEvent e) {
        agregarRecurso();
    }
    
    private void agregarRecurso(){
        String _resource = JOptionPane.showInternalInputDialog(Environment.getActiveDesktop(), "Nombre del Material", "Agregar Material", JOptionPane.QUESTION_MESSAGE);
        if (_resource != null) {
            _resource = _resource.trim();
            if (_resource.length() > 3) {
                if (! LibSQL.getBoolean("resourcescontrol.existResource", "'" + _resource + "'")){
                    if (LibSQL.getBoolean("resourcescontrol.addResourceByName", "'" + _resource + "'")){
                        cbResources.loadJCombo(LibSQL.exFunction("resourcescontrol.getAllResourcesByFilter", "'" + _resource + "',0"));
                    } else {
                        Advisor.messageBox("Se produjo un error al grabar los datos", "Error");
                    }
                } else {
                    Advisor.messageBox("El material \" "+ _resource.toUpperCase() +"\" ya existe", "Aviso");
                }
            } else {
                Advisor.messageBox("El nombre del material debe tener al menos 4 letras", "Aviso");
            }
        } else {
            Advisor.messageBox("Debe ingresar un nombre de material","Aviso");
        }
    }
}
