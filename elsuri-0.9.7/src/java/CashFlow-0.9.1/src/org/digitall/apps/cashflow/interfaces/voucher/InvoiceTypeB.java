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
 * InvoiceTypeB.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.voucher;

import java.awt.BorderLayout;
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.math.BigDecimal;

import java.util.Vector;

import org.digitall.apps.cashflow.classes.ErrInvoiceTypeB;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.VoucherDetail;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.common.resourcescontrol.interfaces.QuickResourcesMgmt;
import org.digitall.common.resourcesrequests.classes.ResourcesRequest;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintSaveButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class InvoiceTypeB extends BasicPrimitivePanel {

    private BasicPanel jpNorth = new BasicPanel();
    private BasicPanel jpCenter = new BasicPanel();
    private BasicPanel jpTotals = new BasicPanel();
    private BasicPanel headerPanel = new BasicPanel(); //("Datos del Comprobante");
    private BasicPanel dataPanel = new BasicPanel(); //("Agregar Recurso");
    private BorderLayout borderLayout1 = new BorderLayout();
    private BorderLayout borderLayout2 = new BorderLayout();
    
    private CBInput cbProviders = new CBInput(0, "Provider", false);
    private CBInput cbVoucherType = new CBInput(CachedCombo.VOUCHERTYPE_PAYABLE, "VoucherType");
    //private CBInput cbBrand = new CBInput(CachedCombo.BRANDS, "Brand", false);
    private CBInput cbBrand = new CBInput(0, "Brand", false);
    private CBInput cbResources = new CBInput(0, "Resources", false);
    private CBInput cbCostsCentres = new CBInput(0, "CostsCentre", false);
    private CBInput cbActivity = new CBInput(CachedCombo.ACTIVITY, "Activities", true);
    
    private TFInput tfFindProvider = new TFInput(DataTypes.STRING, "FindEntity", false);
    private TFInput tfHeaderDescription = new TFInput(DataTypes.STRING, "Observations", false);
    private TFInput tfNumber = new TFInput("Number", true, "####-########");
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", true);
    private TFInput tfAmount = new TFInput(DataTypes.MONEY_EXTENDED, "TotalAmount", false);
    private TFInput tfVATp = new TFInput(DataTypes.PERCENT, "VATp", false);
    private TFInput tfUnitPrice = new TFInput(DataTypes.MONEY_EXTENDED, "Price", false);
    private TFInput tfRequestedQty = new TFInput(DataTypes.DOUBLE_EXTENDED, "Qty", true);
    private TFInput tfUnit = new TFInput(DataTypes.STRING, "Unit", false);
    private TFInput tfFindResource = new TFInput(DataTypes.STRING, "SearchButton", false);
    private TFInput tfNewResource = new TFInput(DataTypes.STRING, "NewResource", false);
    private TFInput tfBuscarMarca = new TFInput(DataTypes.STRING, "Buscar", false);
    private TFInput tfFooterPartialWOTaxes = new TFInput(DataTypes.MONEY, "PartialWOTaxes", false);
    private TFInput tfFooterTaxes = new TFInput(DataTypes.MONEY, "Taxes", false);
    private TFInput tfFooterPartialWTaxes = new TFInput(DataTypes.MONEY, "PartialWTaxes", false);
    private TFInput tfFooterVATAmount = new TFInput(DataTypes.PERCENT, "VATAmount", false);
    private TFInput tfFooterAmount = new TFInput(DataTypes.MONEY, "TotalAmount", false);
    private TFInput tfCuenta = new TFInput(DataTypes.STRING, "(Código) Cuenta", false);

    private AddButton btnHeaderNew = new AddButton();
    private AssignButton btnAddInvoiceHeader = new AssignButton(true);
    private AddButton btnClearResourceFields = new AddButton();
    private AddButton btnAddResource = new AddButton();
    private DeleteButton btnDeleteItem = new DeleteButton();
    private AssignButton btnAddItem = new AssignButton(true);
    private AcceptButton acceptButton1 = new AcceptButton();
    private FindButton btnFindProvider = new FindButton();
    private PrintSaveButton btnSavePrint = new PrintSaveButton();
    private CloseButton btnCancel = new CloseButton();
    
    private int[] sizeColumnList = { 150, 95, 85, 60, 75, 56 , 88 , 150};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Detalle del Comprobante", dataRow) {
        public void calculate() {
            footerCalculateTotal();
        }
    };
    private Vector headerList = new Vector();
    
    private ResourcesRequest resourcesRequest;
    private ResourceBrands resourceBrands;
    
    private Voucher voucher = new Voucher();
    private VoucherDetail voucherDetail;
    private VoucherDetail selectedVoucherDetail;

    private int idBudget = -1;
    
    private ErrInvoiceTypeB errForm = new ErrInvoiceTypeB();
    private BasicCheckBox chkCAI = new BasicCheckBox();
    
    public InvoiceTypeB() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(760, 589));
	jpNorth.setLayout(borderLayout1);
	jpNorth.setPreferredSize(new Dimension(1, 280));
        jpNorth.setSize(new Dimension(760, 260));
        jpCenter.setLayout(borderLayout2);
        jpCenter.setSize(new Dimension(760, 260));
        jpTotals.setLayout(null);
	jpTotals.setPreferredSize(new Dimension(1, 40));
        jpNorth.add(headerPanel, BorderLayout.NORTH);
        jpNorth.add(dataPanel, BorderLayout.CENTER);
        cbProviders.setBounds(new Rectangle(150, 60, 515, 35));
	cbProviders.autoSize();
	cbActivity.setBounds(new Rectangle(425, 60, 235, 35));
	cbActivity.autoSize();
	tfFindProvider.setBounds(new Rectangle(10, 60, 130, 35));
	tfFindProvider.setSize(new Dimension(130, 35));
	cbVoucherType.setBounds(new Rectangle(10, 25, 135, 35));
	cbVoucherType.autoSize();
	cbVoucherType.setSelectedValue("Factura B");
	//cbVoucherType.setEnabled(false);
	tfHeaderDescription.setBounds(new Rectangle(10, 95, 655, 35));
	tfNumber.setBounds(new Rectangle(150, 25, 150, 35));
	tfDate.setBounds(new Rectangle(320, 25, 105, 35));
	btnHeaderNew.setBounds(new Rectangle(675, 110, 30, 25));
	btnHeaderNew.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnHeaderNew_actionPerformed(e);
				    }

				}
	);
	btnAddInvoiceHeader.setBounds(new Rectangle(710, 110, 30, 25));
	btnAddInvoiceHeader.addActionListener(new ActionListener() {

					   public void actionPerformed(ActionEvent e) {
					       btnAddInvoiceHeader_actionPerformed(e);
					   }

				       }
	);
        headerPanel.add(chkCAI, null);
        headerPanel.add(cbProviders, null);
        headerPanel.add(tfFindProvider, null);
        headerPanel.add(cbVoucherType, null);
        headerPanel.add(tfHeaderDescription, null);
        headerPanel.add(tfNumber, null);
        headerPanel.add(tfDate, null);
        headerPanel.add(btnHeaderNew, null);
        headerPanel.add(btnAddInvoiceHeader, null);
        headerPanel.setLayout(null);
	headerPanel.setPreferredSize(new Dimension(1, 140));
	tfAmount.setBounds(new Rectangle(615, 100, 105, 35));
	tfVATp.setBounds(new Rectangle(530, 100, 80, 35));
	tfUnitPrice.setBounds(new Rectangle(425, 100, 100, 35));
	btnAddResource.setBounds(new Rectangle(710, 30, 30, 25));
	btnAddResource.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAddResource_actionPerformed(e);
			      }

			  }
	);

	btnClearResourceFields.setBounds(new Rectangle(675, 75, 30, 25));
	btnClearResourceFields.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnClearResourceFields_actionPerformed(e);
			      }

			  }
	);
	tfRequestedQty.setBounds(new Rectangle(280, 100, 85, 35));
	tfUnit.setBounds(new Rectangle(370, 100, 50, 35));
	tfUnit.setEnabled(false);
	tfUnit.setValue("N/A");
	cbBrand.setBounds(new Rectangle(85, 60, 265, 35));
	cbBrand.autoSize();
        loadComboMarcas();
	cbBrand.getCombo().addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent evt) {
					    if (evt.getStateChange() == ItemEvent.SELECTED) {
						loadBrand();
					    }
					}

				    }
	);
	tfFindResource.setBounds(new Rectangle(10, 20, 70, 35));
	tfFindResource.getTextField().addKeyListener(new KeyAdapter() {

						  public void keyTyped(KeyEvent e) {
						      char caracter = e.getKeyChar();
						      if ((caracter == KeyEvent.VK_ENTER)) {
							  loadComboResource();
						      }
						  }

					      }
	);
        
        tfBuscarMarca.getTextField().addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (caracter == KeyEvent.VK_ENTER) {
                      loadComboMarcas();      
                        }
            }
        } );
        
	btnDeleteItem.setBounds(new Rectangle(705, 75, 40, 25));
	btnDeleteItem.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDeleteItem_actionPerformed(e);
				 }

			     }
	);
	cbResources.setBounds(new Rectangle(85, 20, 265, 35));
        cbCostsCentres.setBounds(new Rectangle(10, 100, 265, 35));
	cbResources.autoSize();
	cbCostsCentres.autoSize();
	cbResources.addItemListener(new ItemListener() {

				 public void itemStateChanged(ItemEvent evt) {
				     if (evt.getStateChange() == ItemEvent.SELECTED) {
					 setCuenta();
				     }
				 }

			     }
	);
        cbCostsCentres.addItemListener(new ItemListener() {

                                 public void itemStateChanged(ItemEvent evt) {
                                     if (evt.getStateChange() == ItemEvent.SELECTED) {
                                         
                                     }
                                 }

                             }
        );
	btnAddItem.setBounds(new Rectangle(725, 115, 30, 25));
	btnAddItem.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAddItem_actionPerformed(e);
			      }

			  }
	);
	addKeyCalculate(tfRequestedQty);
	addKeyCalculate(tfUnitPrice);
	addKeyCalculate(tfVATp);
	dataPanel.setLayout(null);
	dataPanel.setPreferredSize(new Dimension(1, 140));
        dataPanel.add(tfCuenta, null);
        dataPanel.add(tfBuscarMarca, null);
        dataPanel.add(tfAmount, null);
        dataPanel.add(tfVATp, null);
        dataPanel.add(tfUnitPrice, null);
        dataPanel.add(btnAddResource, null);
        dataPanel.add(btnClearResourceFields, null);
        dataPanel.add(tfRequestedQty, null);
        dataPanel.add(tfUnit, null);
        dataPanel.add(cbBrand, null);
        dataPanel.add(tfFindResource, null);
        dataPanel.add(btnDeleteItem, null);
        dataPanel.add(cbResources, null);
        dataPanel.add(cbCostsCentres, null);
        dataPanel.add(btnAddItem, null);
        dataPanel.add(cbActivity, null);
        this.add(jpNorth, BorderLayout.NORTH);
	jpCenter.add(listPanel, BorderLayout.CENTER);
	jpCenter.add(jpTotals, BorderLayout.SOUTH);
	this.add(jpCenter, BorderLayout.CENTER);
	tfFindProvider.getTextField().addKeyListener(new KeyAdapter() {

						  public void keyTyped(KeyEvent e) {
						      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							  loadComboProviders();
						      }
						  }

					      }
	);
	acceptButton1.setBounds(new Rectangle(560, 5, 28, 33));
	acceptButton1.setVisible(false);
	tfFooterAmount.setBounds(new Rectangle(440, 0, 105, 35));
	tfFooterVATAmount.setBounds(new Rectangle(350, 0, 75, 35));
	tfFooterPartialWTaxes.setBounds(new Rectangle(230, 0, 105, 35));
	tfFooterTaxes.setBounds(new Rectangle(130, 0, 85, 35));
	tfFooterPartialWOTaxes.setBounds(new Rectangle(10, 0, 105, 35));
        jpTotals.add(acceptButton1, null);
	jpTotals.add(tfFooterAmount, null);
	jpTotals.add(tfFooterVATAmount, null);
	jpTotals.add(tfFooterPartialWTaxes, null);
	jpTotals.add(tfFooterTaxes, null);
	jpTotals.add(tfFooterPartialWOTaxes, null);
	/* Revisar si va lo siguiente */
	btnFindProvider.setBounds(new Rectangle(515, 40, 30, 25));
	tfNewResource.setBounds(new Rectangle(610, 20, 105, 35));
	
	/* Hasta aquí */
	addButton(btnCancel);
	addButton(btnSavePrint);
	btnCancel.setToolTipText("Cancelar el registro del Comprobante");
	btnSavePrint.setToolTipText("Grabar e imprimir el Comprobante");
	btnSavePrint.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnSavePrint_actionPerformed(e);
				    }

				}
	);
        
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);

        chkCAI.setText("Verif. CAI");
        chkCAI.setBounds(new Rectangle(665, 35, 90, 25));
        ActionListener calculateListen = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    calculateTotal();
                }

            }
        ;
        tfVATp.getTextField().addActionListener(calculateListen);
        tfUnitPrice.getTextField().addActionListener(calculateListen);
	tfRequestedQty.getTextField().addActionListener(calculateListen);
        btnHeaderNew.setToolTipText("Limpiar campos");
        btnAddInvoiceHeader.setToolTipText("Agregar Comprobante");
        btnDeleteItem.setToolTipText("Borrar el ítem seleccionado del Comprobante");
	btnAddResource.setToolTipText("Agregar un recurso inexistente en la lista");
	btnClearResourceFields.setToolTipText("Limpiar campos");
        btnAddItem.setToolTipText("Agregar el ítem al Comprobante");
        
        chkCAI.addActionListener(new ActionListener() {

                                   public void actionPerformed(ActionEvent e) {
                                       chkCAI_actionPerformed(e);
                                   }

                               }
        );
        
        //loadBrand();
        tfBuscarMarca.setBounds(new Rectangle(10, 60, 70, 35));
        setHeaderList();
	setEnabledRRPanel(false);
	setEnabledRRDPanel(false);
        setDisableFooterPanel();
        tfAmount.setEnabled(false);
        btnDeleteItem.setEnabled(false);
        tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
        loadComboCostsCentres();
        headerPanel.setBorder(BorderPanel.getBorderPanel("Datos del Comprobante"));
        dataPanel.setBorder(BorderPanel.getBorderPanel("Agregar Recurso"));
        dataPanel.setSize(new Dimension(760, 160));
        tfCuenta.setBounds(new Rectangle(370, 20, 335, 35));
	tfNumber.setValue("0000-00000000");
        tfCuenta.setEditable(false);
        cbActivity.setSelectedValue("Sin asignar");
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Complete los datos y presione el boton \"Grabar e imprimir\" para registrar el Comprobante");
    }

    private void chkCAI_actionPerformed(ActionEvent e) {
        if (chkCAI.isSelected()) {
            setEnabledRRPanel(true);
        } else {
            setEnabledRRPanel(false);
        }
    }
    

    private void loadComboProviders() {
	String param = "'" + tfFindProvider.getString() + "'";
	cbProviders.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", param));
	cbProviders.getCombo().updateUI();
    }

    private void btnHeaderNew_actionPerformed(ActionEvent e) {
	newHeaderItem();
    }

    private void newHeaderItem() {
	setEnabledRRPanel(true);
	resourcesRequest = new ResourcesRequest();
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfNumber.setValue("0000-00000000");
	tfHeaderDescription.setValue("");
	tfFindProvider.setValue("");
	cbProviders.getCombo().removeAllItems();
	setEnabledRRDPanel(false);
    }

    private void btnAddInvoiceHeader_actionPerformed(ActionEvent e) {
        if (saveHeaderData()) {
            refresh();
            setEnabledRRPanel(false);
            chkCAI.setEnabled(false);
            setEnabledRRDPanel(true);
            //footerCalculateTotal();
        }
    }

    private void setEnabledRRPanel(boolean _valor) {
        cbVoucherType.setEnabled(_valor);
	tfNumber.setEnabled(_valor);
	tfDate.setEnabled(_valor);
	tfFindProvider.setEnabled(_valor);
	cbProviders.setEnabled(_valor);
	tfHeaderDescription.setEnabled(_valor);
	btnHeaderNew.setEnabled(_valor);
        btnAddInvoiceHeader.setEnabled(_valor);
    }

    private void setEnabledRRDPanel(boolean _valor) {
	tfFindResource.setEnabled(_valor);
        tfRequestedQty.setEnabled(_valor);
        tfNewResource.setEnabled(_valor);
        tfUnitPrice.setEnabled(_valor);
        tfVATp.setEnabled(_valor);
        cbResources.setEnabled(_valor);
        tfCuenta.setEnabled(_valor);
        tfBuscarMarca.setEnabled(_valor);
	cbBrand.setEnabled(_valor);
	cbActivity.setEnabled(_valor);
        cbCostsCentres.setEnabled(_valor);
	btnAddResource.setEnabled(_valor);
	btnClearResourceFields.setEnabled(_valor);
	btnAddItem.setEnabled(_valor);
    }
    
    private void setDisableFooterPanel() {
        tfFooterPartialWOTaxes.setEnabled(false);
        tfFooterPartialWTaxes.setEnabled(false);
        tfFooterTaxes.setEnabled(false);
        tfFooterAmount.setEnabled(false);
        tfFooterVATAmount.setEnabled(false);
    }

    private void btnClearResourceFields_actionPerformed(ActionEvent e) {
	newItem();
    }

    private void btnAddResource_actionPerformed(ActionEvent e) {
	ExtendedInternalFrame newResourceIFrame = new ExtendedInternalFrame("Agregar nuevo recurso");
	QuickResourcesMgmt quickResource = new QuickResourcesMgmt();
	newResourceIFrame.setCentralPanel(quickResource);
	newResourceIFrame.show();
    }

    private void btnDeleteItem_actionPerformed(ActionEvent e) {
	if (voucherDetail != null) {
	    if ( selectedVoucherDetail.getIdVoucherDetail() != -1) {
		if (Advisor.question("Aviso", "¿Está seguro de querer borrar el ítem seleccionado?")) {
                    selectedVoucherDetail.deletevoucherDetail();
		    refresh();
                    footerCalculateTotal();
		}
	    } else {
		Advisor.messageBox("Debe seleccionar un ítem para borrar", "Aviso");
	    }
	} else {
	    Advisor.messageBox("Debe seleccionar un ítem para borrar", "Aviso");
	}
    }

    private void btnAddItem_actionPerformed(ActionEvent e) {
	saveData();
    }

    private void loadBrand() {
	if (resourceBrands == null) {
	    resourceBrands = new ResourceBrands();
	} 
	resourceBrands.setBrands(new Brands(Integer.parseInt(cbBrand.getSelectedValue().toString()), cbBrand.getCombo().getItemTexto()));
    }

    private void newItem() {
        voucherDetail = new VoucherDetail();
        tfRequestedQty.setValue(0);
        tfUnitPrice.setValue(0);
        tfVATp.setValue(0);
        tfAmount.setValue(0);
    }


    private void loadComboResource() {
	String param = "'" + tfFindResource.getString() + "',0";
	cbResources.loadJCombo(LibSQL.exFunction("resourcescontrol.getAllRecursosByFilter", param));
        tfCuenta.setValue(cbResources.getSelectedValueRef());
        setCuenta();
	footerCalculateTotal();	
    }

    /**2009-09-25(moraless)*/
    private boolean saveHeaderData() {
	//if (validateHeaderData()) {
	if (controlEncabezado()) {
                voucher.setIdVoucherType(Integer.parseInt(cbVoucherType.getSelectedValue().toString()));
                //voucher.setNumber(tfNumber.getValue().toString());
                voucher.setNumber(0);
                voucher.setNroFactura(tfNumber.getValue().toString());
                voucher.setDate(Proced.setFormatDate(tfDate.getString(),false));
                voucher.setIdEntitytype(67); //67: Empresa
                voucher.setIdEntity(Integer.parseInt(cbProviders.getSelectedValue().toString()));
                voucher.setDescription(tfHeaderDescription.getString().trim());
                voucher.setCostsCentre(new CostsCentre());
                int result = -1;
                if (voucher.getIdVoucher() == -1) {	
                        result = voucher.addData();
                        voucher.retrieveData();
                        //tfNumber.setValue(voucher.getNumber());
                        tfNumber.setValue(voucher.getNroFactura());
                } else {
                    result = voucher.setData();
                }
                if (result >= 0) {
                    /*refresh();
                    setEnabledRRPanel(false);
                    setEnabledRRDPanel(true);
                    footerCalculateTotal();*/
                    return true;
                } else {
                    Advisor.messageBox("Se produjo un error al grabar los datos\n puede que ya exista el comprobante que desea ingresar", "Atención");
                    return false;
                }
	} else {
	    //Advisor.messageBox("No puede haber campos vacios", "Atención");
	    errForm.showMessage();
	    return false;
	}
    }
    
    /**2009-09-25(moraless)*/
     private boolean controlEncabezado() {
	 errForm = new ErrInvoiceTypeB();
	 boolean valor = false;
         String voucherNumber = tfNumber.getValue().toString();
         String prefijo = voucherNumber.substring(0,voucherNumber.indexOf("-"));
         String sufijo = voucherNumber.substring(voucherNumber.indexOf("-")+1,voucherNumber.length());
         if (Integer.parseInt(prefijo) == 0) {
	    errForm.setError(errForm.voucherPrefijo);
         } else if (Integer.parseInt(sufijo) == 0) {
             errForm.setError(errForm.voucherSufijo);
         } else if (tfNumber.getValue().equals("") || tfNumber.getValue().equals("0000-00000000") || tfNumber.getValue().equals("0"))  {
	    errForm.setError(errForm.voucherNumber);
         } else if(tfDate.getString().equals("")){
	     errForm.setError(errForm.voucherDate);
         } else if(cbProviders.getSelectedIndex() == -1) {
	    errForm.setError(errForm.voucherProvider);
         } else if(!chkCAI.isSelected()) {
             errForm.setError(errForm.voucherCAI);
	 } else {
	     valor = true;
	 }
	 return valor;
     }

    private boolean validateHeaderData() {
	boolean result = false;
	if ((!tfNumber.getString().equals("")) && (!tfDate.getString().equals("")) && (cbProviders.getSelectedIndex() != -1)) {
	    result = true;
	}
	return result;
    }

    /**2009-09-25(moraless)*/
    public boolean saveData() {
	//if (validateData()) {
	if(controlDetalle()){
	    if (voucherDetail == null) {
	        voucherDetail = new VoucherDetail();
	    }
	    if (resourceBrands == null) {
	        resourceBrands = new ResourceBrands();
	        resourceBrands.setResource(new Resource());
	    }
	    
	    voucherDetail.setVoucher(voucher);

	    resourceBrands.setBrands(new Brands(Integer.parseInt("" + cbBrand.getSelectedValue())));
	    resourceBrands.setResource(new Resource(EntityTypes.RESOURCE, Integer.parseInt("" + cbResources.getSelectedValue())));
	    resourceBrands.getResource().retrieveData();
            
	    voucherDetail.setResourceBrand(resourceBrands);
            voucherDetail.setExpenditureAccount(new ExpenditureAccount.CCItem(resourceBrands.getResource().getExpenditureAccount().getIDExpenditureAccount()));
            voucherDetail.setOriginalQty(tfRequestedQty.getDouble());
	    voucherDetail.setFinalQty(tfRequestedQty.getDouble());
	    voucherDetail.setPrice(tfUnitPrice.getAmount());
	    voucherDetail.setPartialWOTaxes(tfUnitPrice.getAmount() * tfRequestedQty.getDouble());
	    voucherDetail.setTaxes(0);
            voucherDetail.setPartialWTaxes(tfUnitPrice.getAmount() * tfRequestedQty.getDouble());
	    voucherDetail.setVATp(tfVATp.getAmount());
	    voucherDetail.setDescription(cbActivity.getSelectedItem().toString());
            voucherDetail.setIdCostCentre(Integer.parseInt(cbCostsCentres.getSelectedValue().toString()));
            
	    if (voucherDetail.addData() >= 0) {
		refresh();
	        footerCalculateTotal();
		newItem();
                idBudget = Integer.parseInt(cbCostsCentres.getSelectedValueRef().toString());
                loadComboCostsCentres();
                return true;
	    } else {
		Advisor.messageBox("El Recurso ya se encuentra en el Detalle", "Atención");
		return false;
	    }
	} else {
	    errForm.showMessage();
	    return false;
	}
    }

    private boolean validateData() {
	boolean result = false;
	if (cbResources.getSelectedIndex() == -1) {
	    result = false;
	} else if (tfRequestedQty.getDouble() == 0) {
	    result = false;
	} else if (cbBrand.getSelectedIndex() == -1) {
	    result = false;
	} else if (cbCostsCentres.getSelectedIndex() == -1) {
	    result = false;
	} else if (tfUnitPrice.getAmount() <= 0) {
	    result = false;
	} else if (tfAmount.getAmount() <= 0) {
	    result = false;
	} else {
	    result = true;
	}
	return result;
    }
    
    /**2009-09-25(moraless)*/
    private boolean controlDetalle(){
	boolean result = true;
	errForm = new ErrInvoiceTypeB();
	if(cbResources.getSelectedIndex() == -1){
	    result = false;
	    errForm.setError(errForm.resource);
	} else {
	    if(tfRequestedQty.getDouble()<=0){
	        result = false;
	        errForm.setError(errForm.requestedQty);
	    }else{
	        if(tfUnitPrice.getAmount()<=0){
	            result = false;
	            errForm.setError(errForm.unitPrice);
	        }/* else {
	            if( (tfVATp.getAmount() <= 0) && (Integer.parseInt(cbVoucherType.getSelectedValue().toString()) != 2)) {
	                result = false;
	                errForm.setError(errForm.vatp);
	            }
	        }*/
	    }    
	}
	return result;
    }


    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Resource"));
        headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Brand"));
        headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Quantity"));
        headerList.addElement("*");
        headerList.addElement(Environment.lang.getProperty("Unit"));
        headerList.addElement(Environment.lang.getProperty("Price"));
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("(%) IVA");
	headerList.addElement(Environment.lang.getProperty("Amount"));
        headerList.addElement("Actividad/CC");
	headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement(Environment.lang.getProperty("CostsCentre"));
     
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadObjectSelected();
                                                   btnDeleteItem.setEnabled(true);
					       }
					   }

				       }
	);
	listPanel.setParams("cashflow.getAllVoucherDetailToInvoiceB", "-1", headerList);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
            selectedVoucherDetail = new VoucherDetail();
            selectedVoucherDetail.setIdVoucherDetail(Integer.parseInt("" + dataRow.elementAt(0)));
	    selectedVoucherDetail.setVoucher(voucher);
	}
    }

    public void refresh() {
	String params = "" + voucher.getIdVoucher();
	listPanel.refresh(params);
	btnDeleteItem.setEnabled(false);
    }

    private void btnSavePrint_actionPerformed(ActionEvent e) {
        if (listPanel.getTable().getRowCount() > 0) {
            footerCalculateTotal();
            if (tfFooterAmount.getAmount() > 0) {
                if (Advisor.question("Grabar Comprobante", "¿Está seguro de grabar el Comprobante por un monto de " + tfFooterAmount.getTextField().getText() + "?")) {
                    boolean closevoucher = true;
                    boolean isResourcesMovement = true;
                    boolean isCashMovement = false;
                     String params = "" + voucher.getIdVoucher() + "," + tfFooterPartialWOTaxes.getAmount() + "," 
                                     + tfFooterTaxes.getAmount() +"," + tfFooterPartialWTaxes.getAmount() + "," 
                                     + tfFooterVATAmount.getAmount() + "," + 0 + "," +tfFooterAmount.getAmount() +","
                                     + closevoucher;
		    
		    //String params2 = "" + voucher.getIdVoucher() + "," + isResourcesMovement + "," + isCashMovement +",67,-1";
		    
		    String params2 = "" + voucher.getIdVoucher() + "," + isResourcesMovement + "," + isCashMovement + ",67,"+ cbProviders.getSelectedValue(); 
                    if ( (LibSQL.getInt("cashflow.setVoucherByInvoiceB",params) != -1) && (LibSQL.getInt("cashflow.addVoucherByInvoiceB","" + params2 ) > 0) ) {
                    
                         BasicReport report = new BasicReport(InvoiceTypeB.class.getResource("xml/InvoiceTypeB.xml"));
                         report.addTableModel("cashflow.xmlGetInvoiceTypeB", "" + voucher.getIdVoucher());
                         report.addTableModel("cashflow.xmlGetInvoiceTypeBDetail", "" + voucher.getIdVoucher());
                         report.setProperty("textamount",Format.NumberToText.numberToText(tfFooterAmount.getAmount()) + ".-");
                         report.doReport();
                         clearData();
                         //getParentInternalFrame().setIcon(true);
                    } else {
                        Advisor.messageBox("Ocurrió un error al grabar los datos", "Error");
                    }
                } else {
                    Advisor.messageBox("Operación cancelada por el usuario", "Aviso");
                }
            } else {
                Advisor.messageBox("El monto del Comprobante debe ser mayor que cero", "Aviso");
            }    
        } else {
            Advisor.messageBox("Debe cargar al menos un ítem", "Aviso");
        }
	    
    }

    private void btnCancel_actionPerformed(ActionEvent e) {
	if ( voucher.getIdVoucher() != -1 ) {
	    if (Advisor.question("Aviso", "¿Está seguro de cancelar el Registro del Comprobante?")) {
                voucher.delete();
                voucher = new Voucher();
                voucherDetail = new VoucherDetail();
                clearFooterPanel();
                clearResourcesPanel();
                clearHeaderPanel();
                refresh();
	        footerCalculateTotal();
	        //getParentInternalFrame().setIcon(true);
                idBudget = -1;
                loadComboCostsCentres();
	    }
	}else{
	    Advisor.messageBox("No se ha generado un comprobante", "No se puede cancelar");
	}
    }
    
    /**2009-09-25(moraless)*/
    private void calculateTotal() {
        try {
	    BigDecimal amount = tfUnitPrice.getBigDecimal().multiply(tfRequestedQty.getBigDecimal());
	    amount = tfVATp.addPercentage(amount);
            tfAmount.setValue(amount);
        } catch (Exception ex) {
	    ex.printStackTrace();
            tfUnitPrice.setValue(0.0);
            tfVATp.setValue(0.0);
            tfAmount.setValue(0.0);
        }
    }

    /**2009-09-25(moraless)*/
    private void footerCalculateTotal() {
        Vector<Vector> values = listPanel.getAllValues();
        if (values.size() > 0) {
	    BigDecimal amount = new BigDecimal("0");
	    BigDecimal partialWOTaxes = new BigDecimal("0");
	    BigDecimal vatAmount = new BigDecimal("0");
            BigDecimal taxes = tfFooterTaxes.getBigDecimal();
            for (int i = 0; i < values.size(); i++) {
		partialWOTaxes = partialWOTaxes.add(new BigDecimal(values.elementAt(i).elementAt(14).toString()));
		vatAmount = vatAmount.add(new BigDecimal(values.elementAt(i).elementAt(17).toString()));
		amount = amount.add(new BigDecimal(values.elementAt(i).elementAt(19).toString()));
            }
            tfFooterPartialWOTaxes.setValue(partialWOTaxes);
            tfFooterPartialWTaxes.setValue(partialWOTaxes.add(taxes));
            tfFooterVATAmount.setValue(vatAmount);
            tfFooterAmount.setValue(amount.add(taxes));
        } else {
            tfFooterPartialWOTaxes.setValue(0.0);
            tfFooterTaxes.setValue(0.0);
            tfFooterPartialWTaxes.setValue(0.0);
            tfFooterVATAmount.setValue(0.0);
            tfFooterAmount.setValue(0.0);
        }
    }

    private void clearFooterPanel() {
        tfFooterPartialWOTaxes.setValue(0);
        tfFooterPartialWTaxes.setValue(0);
        tfFooterTaxes.setValue(0);
        tfFooterAmount.setValue(0);
        tfFooterVATAmount.setValue(0);
    }
    private void clearResourcesPanel() {
        tfRequestedQty.setValue(0);
        tfUnitPrice.setValue(0);
        tfVATp.setValue(0);
        tfAmount.setValue(0);
        setEnabledRRDPanel(false);
    }

    private void clearHeaderPanel() {
        tfNumber.setValue("");
        tfDate.setValue(Proced.setFormatDate(Environment.currentDate,true));
        tfHeaderDescription.setValue("");
        setEnabledRRPanel(false);
        chkCAI.setSelected(false);
        chkCAI.setEnabled(true);
	tfNumber.setValue("0000-00000000");
    }

    private void clearData() {
        clearFooterPanel();
        clearHeaderPanel();
        clearResourcesPanel();
        voucher = new Voucher();
        voucherDetail = new VoucherDetail();
        refresh();
        idBudget = -1;
        loadComboCostsCentres();
    }

    private void loadComboCostsCentres() {
        cbCostsCentres.getCombo().loadJCombo("cashflow.getCostsCentresByBudgets",""+ idBudget);
    }

    
    private void addKeyCalculate(TFInput _tf){

	_tf.getTextField().addPropertyChangeListener(new PropertyChangeListener() {

		public void propertyChange(PropertyChangeEvent e) {
		    calculateTotal();
		}
	    }
	);
    }
    
    private void loadComboMarcas() {
        String param = "'" + tfBuscarMarca.getString() + "'";
        cbBrand.loadJCombo(LibSQL.exFunction("resourcescontrol.getAllMarcasByFilter", param));
        cbBrand.setSelectedValue("N/A");
    }
    
    private void setCuenta() {
        tfCuenta.setValue(cbResources.getSelectedValueRef());
    }
}
