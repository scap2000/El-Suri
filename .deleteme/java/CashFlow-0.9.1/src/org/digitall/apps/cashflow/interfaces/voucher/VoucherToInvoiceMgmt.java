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
 * VoucherToInvoiceMgmt.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.voucher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Types;

import java.util.Vector;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.VoucherDetail;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.GoButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class VoucherToInvoiceMgmt extends BasicPrimitivePanel {

    private BasicPanel content = new BasicPanel();
    private TFInput tfProvider = new TFInput(DataTypes.STRING, "Provider", false);
    private TFInput tfDate = new TFInput(DataTypes.SIMPLEDATE, "Date", false);
    private TFInput tfAmount = new TFInput(DataTypes.MONEY, "TotalAmount", false);
    private TFInput tfNumber = new TFInput("Number", true, "####-########");
    private TFInput tfPartialWOTaxes = new TFInput(DataTypes.MONEY, "PartialWOTaxes", false);
    private TFInput tfTaxes = new TFInput(DataTypes.MONEY, "Taxes", false);
    private TFInput tfPartialWTaxes = new TFInput(DataTypes.MONEY, "PartialWTaxes", false);
    private TFInput tfVATAmount = new TFInput(DataTypes.MONEY, "VATAmount", false);
    private CBInput cbVoucherType = new CBInput(CachedCombo.VOUCHERTYPE_PAYABLE, "VoucherType");
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private BasicPanel headerPanel = new BasicPanel();
    private int[] sizeColumnList = { 170, 100, 85, 85, 55, 70, 95, 55, 75, 95 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Detalle de la Factura", dataRow) {

	    public void calculate() {
		//System.out.println("[selected,3]--> " + Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 3)));
		//System.out.println("[selected,4]--> " + Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 4)));
		//System.out.println("[selected,6]--> " + Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 6)));
		if (table.getSelectedRow() > -1) {
		    double valor = (Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 3).toString()) - Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 4))) * Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 6));
		    double iva = Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 8));
		    if (Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 3)) >= Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 4))) {
			table.setValueAt(String.valueOf(valor), table.getSelectedRow(), 7);
			valor = Double.parseDouble("" + table.getValueAt(table.getSelectedRow(), 7)) * iva / 100;
			table.setValueAt(String.valueOf(valor), table.getSelectedRow(), 9);
			valor = Double.parseDouble("" + table.getValueAt(table.getSelectedRow(), 7)) + Double.parseDouble("" + table.getValueAt(table.getSelectedRow(), 9));
			table.setValueAt(String.valueOf(valor), table.getSelectedRow(), 10);
		    }
		}
	    }

	}
    ;
    private Vector headerList = new Vector();
    private BasicPanel footerPanel = new BasicPanel();
    private String selectedIdVoucher;
    private VoucherToInvoiceMain parentMain;
    private GoButton btnCalculate = new GoButton();
    private Provider provider;
    private BorderLayout borderLayout1 = new BorderLayout();
    
    private int error = 0;
    
    public VoucherToInvoiceMgmt(VoucherToInvoiceMain _parentMain) {
	try {
	    parentMain = _parentMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(900, 500));
	this.setPreferredSize(new Dimension(900, 500));
	this.setTitle("Nueva Factura");
	content.setBounds(new Rectangle(5, 0, 695, 700));
	content.setLayout(borderLayout1);
	content.setSize(new Dimension(870, 548));
	tfProvider.setBounds(new Rectangle(395, 25, 185, 35));
	tfDate.setBounds(new Rectangle(285, 25, 85, 35));
	tfAmount.setBounds(new Rectangle(498, 15, 125, 35));
	cbVoucherType.setBounds(new Rectangle(5, 25, 130, 35));
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
	tfDescription.setBounds(new Rectangle(590, 25, 300, 35));
	headerPanel.setLayout(null);
	headerPanel.setPreferredSize(new Dimension(970, 70));
	listPanel.setSize(new Dimension(970, 315));
	footerPanel.setLayout(null);
	footerPanel.setPreferredSize(new Dimension(970, 60));
	btnCalculate.setBounds(new Rectangle(640, 25, 30, 25));
	btnCalculate.setSize(new Dimension(30, 25));
	btnCalculate.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
					btnCalculate_actionPerformed(e);
				    }

				}
	);
	/*btnBonus.addActionListener(new ActionListener() {
				
								public void actionPerformed(ActionEvent e) {
							btnHelp_actionPerformed(e);
								}
				
						}
				);
				
				btnBonus.setBounds(new Rectangle(710, 15, 35, 25));*/
	tfNumber.setBounds(new Rectangle(150, 25, 125, 35));
	tfPartialWOTaxes.setBounds(new Rectangle(10, 15, 125, 35));
	tfTaxes.setBounds(new Rectangle(152, 15, 95, 35));
	tfPartialWTaxes.setBounds(new Rectangle(264, 15, 125, 35));
	tfVATAmount.setBounds(new Rectangle(406, 15, 75, 35));
	/*btnBonus.setText(Environment.lang.getProperty("Bonus"));
				footerPanel.add(btnBonus, null);*/
	footerPanel.add(tfPartialWOTaxes, null);
	footerPanel.add(tfVATAmount, null);
	footerPanel.add(tfPartialWTaxes, null);
	footerPanel.add(tfTaxes, null);
	footerPanel.add(tfAmount, null);
	footerPanel.add(btnCalculate, null);
	headerPanel.add(cbVoucherType, null);
	headerPanel.add(tfNumber, null);
	headerPanel.add(tfDescription, null);
	headerPanel.add(tfDate, null);
	headerPanel.add(tfProvider, null);
	content.add(headerPanel, BorderLayout.NORTH);
	content.add(footerPanel, BorderLayout.SOUTH);
	content.add(listPanel, BorderLayout.CENTER);
	this.add(content, null);
	this.addButton(btnClose);
	this.addButton(btnSave);
	cbVoucherType.autoSize();
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfProvider.setEnabled(false);
	setHeaderList();
	listPanel.setCellEditor(Types.DOUBLE, 3);
	listPanel.setCellEditor(Types.DOUBLE, 5);
	listPanel.setCellEditor(Types.DOUBLE, 7);
	tfTaxes.getTextField().addKeyListener(new KeyAdapter() {

					   public void keyTyped(KeyEvent e) {
					       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
						   calculateTotal();
					       }
					   }

				       }
	);
	listPanel.getTable().addKeyListener(new KeyAdapter() {

					 public void keyReleased(KeyEvent e) {
					    if (listPanel.getTable().getSelectedRow() != -1) {
					     double Qty = Double.parseDouble(listPanel.getTable().getValueAt(listPanel.getTable().getSelectedRow(), 3).toString());
					     double bonusQty = Double.parseDouble(listPanel.getTable().getValueAt(listPanel.getTable().getSelectedRow(), 4).toString());
					     if (bonusQty > Qty) {
						 Advisor.messageBox("No está permitido bonificar mas cantidad de la que se recibió", "Error");
						 listPanel.getTable().setValueAt(0, listPanel.getTable().getSelectedRow(), 4);
					     }}
					     calculateTotal();
					 }

				     }
	);
	tfAmount.setEnabled(false);
	tfPartialWOTaxes.setEnabled(false);
	tfPartialWTaxes.setEnabled(false);
	tfVATAmount.setEnabled(false);
	btnSave.setEnabled(false);
	btnClose.setEnabled(false);
	btnCalculate.setToolTipText("Recalcular totales");
	btnSave.setToolTipText("Grabar Factura");
	btnClose.setToolTipText("Cancelar Factura");
        headerPanel.setBorder(BorderPanel.getBorderPanel(("Datos de la Factura")));
	tfNumber.setValue("0000-00000000");
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Resource"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Brand"));
	headerList.addElement(Environment.lang.getProperty("Quantity"));
	headerList.addElement(Environment.lang.getProperty("Bonus"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Unit"));
	headerList.addElement(Environment.lang.getProperty("Price")); //precio del recurso en la Factura
	headerList.addElement(Environment.lang.getProperty("PartialWOTaxes"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("VATp")); //IVA 
	headerList.addElement(Environment.lang.getProperty("VATAmount"));
	headerList.addElement(Environment.lang.getProperty("TotalAmount"));
	headerList.addElement("*"); //resources.idaccount
	headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("*"); //precio del recurso en la orden de compra
        headerList.addElement("*");//id del voucherdetail
        
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   calculateTotal();
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
					       }
					   }

				       }
	);
	listPanel.setParams("cashflow.getallvoucherdetailtoinvoiceselected", "'-1',"+ OrganizationInfo.getIva(), headerList);
                             
	listPanel.selectAllItems(true);
    }

    public void refresh() {
	String params = "'" + selectedIdVoucher + "'," + OrganizationInfo.getIva();
	listPanel.refresh(params);
	listPanel.selectAllItems(true);
	tfPartialWOTaxes.setValue(0.0);
	tfTaxes.setValue(0.0);
	tfPartialWTaxes.setValue(0.0);
	tfVATAmount.setValue(0.0);
	tfAmount.setValue(0.0);
	tfNumber.setValue("0000-00000000");
    }
    
    private void btnSave_actionPerformed(ActionEvent e) {
        //calculateTotal();
        saveData();
    }
    
    public boolean saveData() {
        boolean returns = false;
        calculateTotal();
        if ( control() ) {
            if (Advisor.question("Grabar factura", "¿Está seguro de grabar la factura por un monto de " + tfAmount.getTextField().getText() + "?")) {
                if (saveInvoice())  {
                    Advisor.messageBox("Factura registrada con éxito.", "Factura registrada");
                    selectedIdVoucher = "-1";
		    tfNumber.setValue("0000-00000000");
                    refresh();
                    parentMain.getVoucherToInvoiceList().refresh();
                    parentMain.setSelectedTab(0);
                    btnSave.setEnabled(false);
                    btnClose.setEnabled(false);
                    returns = true;
                } else  {
                    switch (error)  {
                        case 10: Advisor.messageBox("Ocurrió un error al grabar el encabezado de la Factura.", "Error");
                                // ¿borrar encabezado?
                            break;
                        case 11: Advisor.messageBox("Ocurrió un error al grabar el detalle de la Factura.", "Error");
                                /** borrar detalle y encabezado */
                                 
                            break;
                        case 12: Advisor.messageBox("Ocurrió un error al registrar las imputaciones y los asientos.", "Error");
                                /** borrar detalle y encabezado */
                            break;
                    }
                }
            }
        } else {
            switch (error)  {
                case 1: Advisor.messageBox("Ingrese un Número de Factura.", "Aviso");
                    break;
                case 2: Advisor.messageBox("El monto de la Factura no puede ser menor o igual a cero", "Aviso");
                    break;
                case 3: Advisor.messageBox("El Nº de Factura ya está registrado", "Aviso");
                    break;
                case 4: Advisor.messageBox("No hay datos en la Tabla", "Aviso");
                    break;
                case 5: Advisor.messageBox("El prefijo del Nº de comprobante debe ser mayor que cero (0)", "Aviso");
                    break;
                case 6: Advisor.messageBox("El sufijo del Nº de comprobante debe ser mayor que cero (0)", "Aviso");
                    break;
            }
        }
        return returns;
    }

    private boolean control() {
        boolean result = true;
        String voucherNumber = tfNumber.getValue().toString();
        String prefijo = voucherNumber.substring(0,voucherNumber.indexOf("-"));
        String sufijo = voucherNumber.substring(voucherNumber.indexOf("-")+1,voucherNumber.length());
        if (Integer.parseInt(prefijo) == 0) {
            result = false;
            error = 5;
        } else if (Integer.parseInt(sufijo) == 0) {
            result = false;
            error = 6;
	} else if (tfNumber.getValue().equals("") || tfNumber.getValue().equals("0000-00000000") || tfNumber.getValue().equals("0"))  {
            error = 1;
            result = false;
        } else if (tfAmount.getAmount() <= 0) {
            error = 2;
            result = false;
        } else if ( LibSQL.getBoolean("cashflow.payableVoucherExists", cbVoucherType.getSelectedValue() +","+ tfNumber.getValue() + "," + provider.getIdProvider()) ){
            error = 3;
            result = false;
        } else if ( listPanel.getTable().getRowCount() == 0 ) {
            error = 4;
            result = false;
        }
        return result;
    }

    public boolean saveInvoice() {
        boolean returns = false;
        Vector<Vector> values = listPanel.getAllValues();
        if (values.size() > 0) {
            Voucher voucher = new Voucher();
            voucher.setAmount(Format.toDouble("0" + tfAmount.getString()).doubleValue());
            voucher.setDate(Proced.setFormatDate(tfDate.getString(), false));
            voucher.setDescription(tfDescription.getString());
            voucher.setIdEntitytype(EntityTypes.PROVIDER);
            voucher.setIdEntity(provider.getIdProvider());
            voucher.setIdVoucherType(Integer.parseInt("" + cbVoucherType.getSelectedValue()));
            voucher.setPartialWOTaxes(tfPartialWOTaxes.getAmount());
            voucher.setPartialWTaxes(tfPartialWTaxes.getAmount());
            voucher.setTaxes(tfTaxes.getAmount());
            voucher.setVATAmount(tfVATAmount.getAmount());
            voucher.setVATp(0);
            voucher.setNumber(0);
            voucher.setNroFactura(tfNumber.getString());
            voucher.setWithDetail(true);
            voucher.setCostsCentre(new CostsCentre(Integer.parseInt("" + (listPanel.getSelectedsValuesAt(16)).elementAt(0))));
            voucher.setClosed(true);
            boolean flag = true;
            try { 
                if ( voucher.addData() >= 0)  {    /** Si se grabó correctamente el encabezado ==> grabo el detalle */
                    int i = 0;
                    while ( i < values.size() && flag) {
                        values.elementAt(i).remove(0);
                        //grabar detalle
                        VoucherDetail voucherDetail = new VoucherDetail();
                        voucherDetail.setVoucher(voucher);
                        voucherDetail.setResourceBrand(new ResourceBrands(new Resource(EntityTypes.RESOURCE, Integer.parseInt("" + (values.elementAt(i)).elementAt(0))), new Brands(Integer.parseInt("" + (values.elementAt(i)).elementAt(2)))));
                        voucherDetail.setOriginalQty(Double.parseDouble("" + (values.elementAt(i)).elementAt(4)));
                        voucherDetail.setFinalQty(Double.parseDouble("" + (values.elementAt(i)).elementAt(4)));
                        voucherDetail.setPrice(Double.parseDouble("" + (values.elementAt(i)).elementAt(8)));
                        voucherDetail.setPartialWOTaxes(Double.parseDouble("" + (values.elementAt(i)).elementAt(9)));
                        voucherDetail.setTaxes(0);
                        voucherDetail.setPartialWTaxes(0); 
                        voucherDetail.setVATp(Double.parseDouble("" + (values.elementAt(i)).elementAt(12)));
                        voucherDetail.setVATAmount(Double.parseDouble("" + (values.elementAt(i)).elementAt(13)));
                        voucherDetail.setAmount(Double.parseDouble("" + (values.elementAt(i)).elementAt(14)));
                        voucherDetail.setExpenditureAccount(new ExpenditureAccount.CCItem(Integer.parseInt("" + (values.elementAt(i)).elementAt(15))));
                        voucherDetail.setDescription("");
                        voucherDetail.setIdVoucherDetailRef(Integer.parseInt("" + (values.elementAt(i)).elementAt(19)));
                        voucherDetail.setBonusQty(Double.parseDouble("" + (values.elementAt(i)).elementAt(5)));
                        voucherDetail.setOriginalPrice(Double.parseDouble("" + (values.elementAt(i)).elementAt(18)));
                        voucherDetail.setIdCostCentre(Integer.parseInt("" + (values.elementAt(i)).elementAt(16)));
                        if ( voucherDetail.addDataByInvoice() < 0 ) {
                            flag = false;
                        }
                        i++;
                    }
                } else {
                    flag = false;
                }
            } catch (Exception x) {
                voucher.delete();
            }
                // flag = true ==> Significa que el detalle se registro correctamente, entonces pude registrar las imputaciones correspondientes 
                if (flag)  {    
                    if (LibSQL.getInt("cashflow.addBudgetMovementByInvoice","" + voucher.getIdVoucher() + ",'" + selectedIdVoucher +"'") > 0)  {
                        returns = true;
                    } else  {
                        // Borrar el voucher con su detalle 
                        voucher.delete();
                        error = 12;
                        returns = false;
                    }
                } else  {
                    // Borrar el voucher con su detalle 
                    voucher.delete();
                    error = 11;
                    returns = false;
                }
            } else  {
                error = 10;
                returns = false;
            }
        return returns;    
    }

  
    private void btnClose_actionPerformed(ActionEvent e) {
	//getParentInternalFrame().close();
	if (Advisor.question("Cancelar factura", "¿Está seguro de cancelar la factura?")) {
	    parentMain.setSelectedTab(0);
	    selectedIdVoucher = "-1";
	    btnSave.setEnabled(false);
	    btnClose.setEnabled(false);
	    refresh();
	}
    }

    public void setSelectedIdVoucher(String _selectedIdVoucher) {
	selectedIdVoucher = _selectedIdVoucher;
	listPanel.selectAllItems(true);
	refresh();
    }

    private void calculateTotal() {
	listPanel.selectAllItems(true);
	listPanel.calculate();
	Vector<Vector> values = listPanel.getSelectedsVector();
	if (values.size() > 0) {
	    double amount = 0;
	    double partialWOTaxes = 0;
	    double taxes = tfTaxes.getAmount();
	    double vatAmount = 0;
	    for (int i = 0; i < values.size(); i++) {
		partialWOTaxes += Double.parseDouble("" + (values.elementAt(i)).elementAt(9));
		vatAmount += Double.parseDouble("" + (values.elementAt(i)).elementAt(13));
		amount += Double.parseDouble("" + (values.elementAt(i)).elementAt(14));
	    }
	    tfPartialWOTaxes.setValue(partialWOTaxes);
	    tfPartialWTaxes.setValue(partialWOTaxes + taxes);
	    tfVATAmount.setValue(vatAmount);
	    tfAmount.setValue(amount + taxes);
	} else {
	    tfPartialWOTaxes.setValue(0.0);
	    tfTaxes.setValue(0.0);
	    tfPartialWTaxes.setValue(0.0);
	    tfVATAmount.setValue(0.0);
	    tfAmount.setValue(0.0);
	}
    }

    private void btnCalculate_actionPerformed(ActionEvent e) {
	calculateTotal();
    }

    public void setProvider(Provider provider) {
	this.provider = provider;
	tfProvider.setValue(provider.getName());
        provider.retrieveData();
	btnSave.setEnabled(true);
	btnClose.setEnabled(true);
    }
 
}
