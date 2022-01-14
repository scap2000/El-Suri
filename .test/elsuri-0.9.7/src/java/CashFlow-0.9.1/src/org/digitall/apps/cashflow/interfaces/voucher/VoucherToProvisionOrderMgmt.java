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
 * VoucherToProvisionOrderMgmt.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.voucher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.Types;

import java.util.Vector;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.cashflow.classes.VoucherDetail;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Brands;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.ResourceBrands;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTable;
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

public class VoucherToProvisionOrderMgmt extends BasicPrimitivePanel {

    private BasicPanel contentPanel = new BasicPanel();
    private TFInput tfProvider = new TFInput(DataTypes.STRING, "Provider", false);
    private TFInput tfDate = new TFInput(DataTypes.SIMPLEDATE, "Date", false);
    private TFInput tfAmount = new TFInput(DataTypes.MONEY, "TotalAmount", true);
    private TFInput tfNumber = new TFInput("Number", true, "####-########");
    private TFInput tfPartialWOTaxes = new TFInput(DataTypes.MONEY, "PartialWOTaxes", false);
    private TFInput tfTaxes = new TFInput(DataTypes.MONEY, "Taxes", false);
    private TFInput tfPartialWTaxes = new TFInput(DataTypes.MONEY, "PartialWTaxes", false);
    private TFInput tfVATAmount = new TFInput(DataTypes.MONEY, "VATAmount", false);
    private CBInput cbVoucherType = new CBInput(CachedCombo.VOUCHERTYPE_PAYABLE, "VoucherType");
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private BasicPanel headerPanel = new BasicPanel("Datos de la Factura");
    private int[] sizeColumnList = { 64, 170, 100, 85, 85, 55, 70, 95, 55, 75, 95 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Detalle de la Factura", dataRow) {

	public void calculate() {
	    calculateTotal();
	}

    };
    private BasicPanel footerPanel = new BasicPanel();
    private String provisionOrdersID;
    private VoucherToProvisionOrderMain parentMain;
    private GoButton btnCalculate = new GoButton();
    private Provider provider;

    public VoucherToProvisionOrderMgmt(VoucherToProvisionOrderMain _parentMain) {
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
	this.setTitle("Facturar Órdenes de Provisión");
	contentPanel.setBounds(new Rectangle(5, 0, 695, 700));
	contentPanel.setLayout(new BorderLayout());
	contentPanel.setSize(new Dimension(870, 548));
	tfProvider.setBounds(new Rectangle(395, 25, 185, 35));
	tfDate.setBounds(new Rectangle(285, 25, 85, 35));
	tfAmount.setBounds(new Rectangle(360, 15, 100, 35));
	cbVoucherType.setBounds(new Rectangle(5, 25, 130, 35));
	btnSave.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnSave_actionPerformed(e);
		}

	    });
	btnClose.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClose_actionPerformed(e);
		}

	    });
	tfDescription.setBounds(new Rectangle(590, 25, 300, 35));
	headerPanel.setLayout(null);
	headerPanel.setPreferredSize(new Dimension(970, 70));
	listPanel.setSize(new Dimension(970, 315));
	footerPanel.setLayout(null);
	footerPanel.setPreferredSize(new Dimension(970, 60));
	btnCalculate.setBounds(new Rectangle(465, 25, 30, 25));
	btnCalculate.setSize(new Dimension(30, 25));
	btnCalculate.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnCalculate_actionPerformed(e);
		}

	    });
	tfNumber.setBounds(new Rectangle(150, 25, 125, 35));
	tfPartialWOTaxes.setBounds(new Rectangle(10, 15, 90, 35));
	tfTaxes.setBounds(new Rectangle(105, 15, 85, 35));
	tfPartialWTaxes.setBounds(new Rectangle(195, 15, 80, 35));
	tfVATAmount.setBounds(new Rectangle(280, 15, 75, 35));
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
	contentPanel.add(headerPanel, BorderLayout.NORTH);
	contentPanel.add(footerPanel, BorderLayout.SOUTH);
	contentPanel.add(listPanel, BorderLayout.CENTER);
	setContent(contentPanel);
	this.addButton(btnClose);
	this.addButton(btnSave);
	cbVoucherType.autoSize();
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfProvider.setEnabled(false);
	setHeaderList();
	listPanel.setCellEditor(Types.DOUBLE, 4);
	listPanel.setCellEditor(DataTypes.DOUBLE_EXTENDED, 6);
	listPanel.setCellEditor(Types.DOUBLE, 8);
	tfTaxes.getTextField().addKeyListener(new KeyAdapter() {

		public void keyReleased(KeyEvent e) {
		    calculateTotal();
		}

	    });
	listPanel.getTable().addKeyListener(new KeyAdapter() {

		public void keyReleased(KeyEvent e) {
		    if (listPanel.getTable().getSelectedRow() != -1) {
			double Qty = Double.parseDouble(listPanel.getTable().getValueAt(listPanel.getTable().getSelectedRow(), 4).toString());
			double bonusQty = Double.parseDouble(listPanel.getTable().getValueAt(listPanel.getTable().getSelectedRow(), 5).toString());
			if (bonusQty > Qty) {
			    Advisor.messageBox("No está permitido bonificar más cantidad de la que se recibió", "Error");
			    listPanel.getTable().setValueAt(0, listPanel.getTable().getSelectedRow(), 5);
			}
		    }
		    calculateTotal();
		}

	    });
	tfAmount.setEditable(false);
	tfPartialWOTaxes.setEditable(false);
	tfPartialWTaxes.setEditable(false);
	tfVATAmount.setEditable(false);
	btnSave.setEnabled(false);
	btnClose.setEnabled(false);
	btnCalculate.setToolTipText("Recalcular totales");
	btnSave.setToolTipText("Grabar Factura");
	btnClose.setToolTipText("Cancelar Factura");
	tfNumber.setValue("0000-00000000");
	tfAmount.getTextField().setUneditableBackgroundColor(Color.red);
	tfAmount.getTextField().setUneditableForegroundColor(Color.white);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.removeAllElements();
	headerList.addElement("*"); // idpurchaseorderdetail
	headerList.addElement("*"); // idpurchaseorder
	headerList.addElement(Environment.lang.getProperty("# O.Prov.")); // Nro. de Orden de Provisión
	headerList.addElement("*"); // idresource
	headerList.addElement(Environment.lang.getProperty("Material")); //resource
	headerList.addElement("*"); // idbrand
	headerList.addElement(Environment.lang.getProperty("Brand")); //brand
	headerList.addElement(Environment.lang.getProperty("Quantity")); //qty
	headerList.addElement(Environment.lang.getProperty("Bonus")); //bonusqty
	headerList.addElement("*"); // idunit
	headerList.addElement(Environment.lang.getProperty("Unit")); // unit
	headerList.addElement(Environment.lang.getProperty("Price")); //precio 10
	headerList.addElement(Environment.lang.getProperty("PartialWOTaxes")); //partialwotaxes
	headerList.addElement("*"); //taxes
	headerList.addElement("*"); //partialwtaxes
	headerList.addElement(Environment.lang.getProperty("VATp")); // vatp (14)
	headerList.addElement(Environment.lang.getProperty("VATAmount")); // vatamount
	headerList.addElement(Environment.lang.getProperty("TotalAmount")); // amount
	headerList.addElement("*"); // resources.idaccount
	headerList.addElement("*"); // idcostcentre //headerList.addElement(Environment.lang.getProperty("@ CC"));     // idcostcentre
	headerList.addElement("*"); // provisionorder.price

	listPanel.setParams("cashflow.getAllProvisionOrderDetailToInvoiceSelected", "'-1'", headerList);
    }

    public void refresh() {
	tfPartialWOTaxes.setValue(0.0);
	tfTaxes.setValue(0.0);
	tfPartialWTaxes.setValue(0.0);
	tfVATAmount.setValue(0.0);
	tfAmount.setValue(0.0);
	tfNumber.setValue("0000-00000000");
	listPanel.refresh("'" + provisionOrdersID + "'");
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	if (saveData()) {
	    parentMain.getVoucherToProvisionOrderList().refresh();
	    parentMain.getTabbedPane().setEnabledAt(1, false);
	    parentMain.setSelectedTab(0);
	}
    }

    public boolean saveData() {
	boolean returns = false;
	calculateTotal();
	if (control()) {
	    if (Advisor.question("Grabar factura", "¿Está seguro de grabar la factura por un monto de " + tfAmount.getTextField().getText() + "?")) {
		if (saveInvoice()) {
		    Advisor.messageBox("Factura registrada con éxito", "Factura registrada");
		    provisionOrdersID = "-1";
		    btnSave.setEnabled(false);
		    btnClose.setEnabled(false);
		    refresh();
		    returns = true;
		}
	    }
	}
	return returns;
    }

    private boolean control() {
	boolean _returns = false;
	if (tfNumber.getString().equals("0000-00000000") || !(tfNumber.getString().length() > 0)) {
	    Advisor.messageBox("Ingrese un Número de Factura", "Aviso");
	} else if (tfAmount.getAmount() <= 0) {
	    Advisor.messageBox("El monto de la Factura debe ser mayor que cero", "Aviso");
	} else if (LibSQL.getBoolean("cashflow.payableVoucherExists", cbVoucherType.getSelectedValue() + "," + tfNumber.getValue() + "," + provider.getIdProvider())) {
	    Advisor.messageBox("El Nº de Factura ya está registrado", "Aviso");
	} else if (!listPanel.hasItems()) {
	    Advisor.messageBox("No hay datos para grabar", "Aviso");
	} else {
	    _returns = true;
	}
	return _returns;
    }

    public boolean saveInvoice() {
	boolean _returns = false;
	Vector<Vector> values = listPanel.getAllValues();
	if (values.size() > 0) {
	    Voucher voucher = new Voucher();
	    voucher.setAmount(Format.toDouble("0" + tfAmount.getString()).doubleValue());
	    voucher.setDate(Proced.setFormatDate(tfDate.getDate(), false));
	    voucher.setDescription(tfDescription.getString());
	    voucher.setIdEntitytype(EntityTypes.PROVIDER);
	    voucher.setIdEntity(provider.getIdProvider());
	    voucher.setIdVoucherType(Integer.parseInt(cbVoucherType.getSelectedValue().toString()));
	    voucher.setPartialWOTaxes(tfPartialWOTaxes.getAmount());
	    voucher.setPartialWTaxes(tfPartialWTaxes.getAmount());
	    voucher.setTaxes(tfTaxes.getAmount());
	    voucher.setVATAmount(tfVATAmount.getAmount());
	    voucher.setVATp(0);
	    voucher.setNumber(0);
	    voucher.setNroFactura(tfNumber.getString());
	    voucher.setWithDetail(true);
	    voucher.setCostsCentre(new CostsCentre(-1));
	    voucher.setClosed(true);
	    boolean _continuar = true;
	    try {
		if (voucher.addData() >= 0) { /** Si se grabó correctamente el encabezado ==> grabo el detalle */
		    int i = 0;
		    while (i < values.size() && _continuar) {
			values.elementAt(i).remove(0);
			//grabar detalle
			VoucherDetail voucherDetail = new VoucherDetail();
			voucherDetail.setVoucher(voucher);
			voucherDetail.setResourceBrand(new ResourceBrands(new Resource(EntityTypes.RESOURCE, Integer.parseInt("" + (values.elementAt(i)).elementAt(3))), new Brands(Integer.parseInt("" + (values.elementAt(i)).elementAt(5)))));
			voucherDetail.setOriginalQty(Double.parseDouble("" + (values.elementAt(i)).elementAt(7)));
			voucherDetail.setFinalQty(Double.parseDouble("" + (values.elementAt(i)).elementAt(7)));
			voucherDetail.setPrice(Double.parseDouble("" + (values.elementAt(i)).elementAt(11)));
			voucherDetail.setPartialWOTaxes(Double.parseDouble("" + (values.elementAt(i)).elementAt(12)));
			voucherDetail.setTaxes(0);
			voucherDetail.setPartialWTaxes(0);
			voucherDetail.setVATp(Double.parseDouble("" + (values.elementAt(i)).elementAt(15)));
			voucherDetail.setVATAmount(Double.parseDouble("" + (values.elementAt(i)).elementAt(16)));
			voucherDetail.setAmount(Double.parseDouble("" + (values.elementAt(i)).elementAt(17)));
			voucherDetail.setExpenditureAccount(new ExpenditureAccount.CCItem(Integer.parseInt("" + (values.elementAt(i)).elementAt(18))));
			voucherDetail.setDescription("");
			voucherDetail.setIdVoucherDetailRef(Integer.parseInt("" + (values.elementAt(i)).elementAt(0)));
			voucherDetail.setBonusQty(Double.parseDouble("" + (values.elementAt(i)).elementAt(8)));
			voucherDetail.setOriginalPrice(Double.parseDouble("" + (values.elementAt(i)).elementAt(20)));
			voucherDetail.setIdCostCentre(Integer.parseInt("" + (values.elementAt(i)).elementAt(19)));
			//voucherDetail.setIdCostCentre(-1 /*OBTENER CENTRO DE COSTOS*/); //OJO con el CENTRO DE COSTOS
			if (voucherDetail.addDataInvoiceByProvisionOrder() < 0) {
			    Advisor.messageBox("No es posible cargar el Material porque no tiene\ndisponible Efectivo en la Partida Presupuestaria para este Material", "Error");
			    _continuar = false;
			}
			i++;
		    }
		} else {
		    Advisor.messageBox("Ocurrió un error al grabar el detalle de la Factura", "Error");
		    _continuar = false;
		}
	    } catch (Exception x) {
	        _continuar = false;
	    }
	    // _continuar = true ==> Significa que el detalle se registro correctamente, entonces pude registrar las imputaciones correspondientes
	    if (_continuar) {
                    if (LibSQL.getInt("cashflow.addBudgetMovementByInvoiceToProvisionOrder", "" + voucher.getIdVoucher() + ",'" + provisionOrdersID + "'") > 0) {
                    //Aqui creo que deberia marcar a las ordenes de provision como facturadas
		    _returns = true;
		} else {
		    // Borrar el voucher con su detalle
		    voucher.delete();
		    Advisor.messageBox("Ocurrió un error al registrar las imputaciones y los asientos", "Error");
		    _returns = false;
		}
	    } else {
		// Borrar el voucher con su detalle
		voucher.delete();
		_returns = false;
	    }
	} else {
	    Advisor.messageBox("Ocurrió un error al grabar el encabezado de la Factura", "Error");
	    _returns = false;
	}
	return _returns;
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	if (Advisor.question("Cancelar factura", "¿Está seguro de cancelar la factura?")) {
	    provisionOrdersID = "-1";
	    btnSave.setEnabled(false);
	    btnClose.setEnabled(false);
	    parentMain.getTabbedPane().setEnabledAt(1, false);
	    parentMain.setSelectedTab(0);
	    refresh();
	}
    }

    public void setProvisionOrdersID(String _provisionOrdersID) {
	provisionOrdersID = _provisionOrdersID;
	refresh();
    }

    private void calculateTotal() {
	BasicTable table = listPanel.getTable();
	if (table.getSelectedRow() > -1) {
	    double valor = (Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 4).toString()) - Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 5))) * Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 7));
	    double iva = Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 9));
	    if (Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 4)) >= Double.parseDouble("0" + table.getValueAt(table.getSelectedRow(), 5))) {
		table.setValueAt(String.valueOf(valor), table.getSelectedRow(), 8);
		valor = Double.parseDouble("" + table.getValueAt(table.getSelectedRow(), 8)) * iva / 100;
		table.setValueAt(String.valueOf(valor), table.getSelectedRow(), 10);
		valor = Double.parseDouble("" + table.getValueAt(table.getSelectedRow(), 8)) + Double.parseDouble("" + table.getValueAt(table.getSelectedRow(), 10));
		table.setValueAt(String.valueOf(valor), table.getSelectedRow(), 11);
	    }
	}
	Vector<Vector> values = listPanel.getAllValues();
	if (values.size() > 0) {
	    double amount = 0;
	    double partialWOTaxes = 0;
	    double taxes = tfTaxes.getAmount();
	    double vatAmount = 0;
	    for (int i = 0; i < values.size(); i++) {
		partialWOTaxes += Double.parseDouble("" + (values.elementAt(i)).elementAt(13));
		vatAmount += Double.parseDouble("" + (values.elementAt(i)).elementAt(17));
		amount += Double.parseDouble("" + (values.elementAt(i)).elementAt(18));
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
	provider.retrieveData();
	tfProvider.setValue(provider.getName());
	btnSave.setEnabled(true);
	btnClose.setEnabled(true);
    }

}
