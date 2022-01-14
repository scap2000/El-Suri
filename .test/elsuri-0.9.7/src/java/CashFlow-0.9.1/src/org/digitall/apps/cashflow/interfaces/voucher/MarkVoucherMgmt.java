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
 * MarkVoucherMgmt.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.voucher;

import java.awt.BorderLayout;
import java.awt.Color;
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

import java.math.BigDecimal;

import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class MarkVoucherMgmt extends BasicPrimitivePanel {

    private BasicPanel container = new BasicPanel();
    private BasicPanel dataPanel = new BasicPanel();
    private BasicPanel jpImputations = new BasicPanel();
    private BasicPanel detailPartialAmountPanel = new BasicPanel();
    
    private CloseButton btnClose = new CloseButton();
    private SaveDataButton btnPrintSave = new SaveDataButton();
    private DeleteButton btnDeleteDeduction = new DeleteButton();
    
    private int[] sizeColumnList = { 185, 83, 103, 132 , 119 };
    private Vector dataRowDetail = new Vector();
    private GridPanel listDetailPanel = new GridPanel(30, sizeColumnList, "", dataRowDetail){
	public void calculate() {
	    calcularSubTotal();
	}
    };
    private Vector headerListDetail = new Vector();
    
    private TFInput tfFindProvider = new TFInput(DataTypes.STRING, "FindProvider", false);
    private TFInput tfFaltaImputar = new TFInput(DataTypes.MONEY, "Saldo O.P. seleccionada", false);
    
    private CBInput cbProvider = new CBInput(0, "Provider", false);
    private CBInput cbPaymentOrder = new CBInput(0, "Ordenes de Pago adelantadas", false);

    private BasicLabel lblTotalAImputar = new BasicLabel();
    private BasicLabel lbSubTotal = new BasicLabel();

    private JMoneyEntry tfAmountOwed = new JMoneyEntry();
    private JMoneyEntry tfDetailPartialAmount = new JMoneyEntry();
    
    private BorderLayout borderLayout2 = new BorderLayout();
    private BorderLayout borderLayout1 = new BorderLayout();

    public MarkVoucherMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(673, 513));
	this.setPreferredSize(new Dimension(590, 500));
	container.setLayout(borderLayout1);
	dataPanel.setLayout(null);
	dataPanel.setPreferredSize(new Dimension(675, 110));
	jpImputations.setLayout(borderLayout2);
	detailPartialAmountPanel.setLayout(null);
	detailPartialAmountPanel.setPreferredSize(new Dimension(1, 40));
	tfDetailPartialAmount.setBounds(new Rectangle(535, 10, 115, 20));
	lbSubTotal.setText(Environment.lang.getProperty("PartialAmount") + ":");
	lbSubTotal.setBounds(new Rectangle(450, 10, 84, 20));
	lbSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
	lbSubTotal.setSize(new Dimension(84, 20));
	tfFindProvider.setBounds(new Rectangle(10, 20, 120, 35));
	cbProvider.setBounds(new Rectangle(140, 20, 355, 35));
	cbProvider.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					    if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cbProvider.getSelectedIndex() > -1) {
						    loadSaldoProveedor();
						    loadVouchers();
						    loadComboOrdenesPago();
						}
					    }
					}

				    }
	);
        cbPaymentOrder.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent e) {
                                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                                if (cbPaymentOrder.getSelectedIndex() > -1) {
                                                    //loadComboOrdenesPago();
                                                    loadSaldoOrdenDePago();
						    calcularSubTotal();
                                                }
                                            }
                                        }

                                    }
        );
        
	detailPartialAmountPanel.add(lbSubTotal, null);
	detailPartialAmountPanel.add(tfDetailPartialAmount, null);
	jpImputations.add(detailPartialAmountPanel, BorderLayout.SOUTH);
	jpImputations.add(listDetailPanel, BorderLayout.CENTER);
	dataPanel.add(tfFaltaImputar, null);
	dataPanel.add(cbPaymentOrder, null);
	dataPanel.add(lblTotalAImputar, null);
	dataPanel.add(tfAmountOwed, null);
	dataPanel.add(cbProvider, null);
	dataPanel.add(tfFindProvider, null);
	cbProvider.autoSize();
	tfDetailPartialAmount.setEnabled(false);
	btnDeleteDeduction.setBounds(new Rectangle(655, 25, 20, 20));
	tfFaltaImputar.setBounds(new Rectangle(510, 60, 145, 35));
        tfFaltaImputar.setEditable(false);
        cbPaymentOrder.setBounds(new Rectangle(10, 60, 485, 35));
	lblTotalAImputar.setText("Total Imputable");
	lblTotalAImputar.setBounds(new Rectangle(510, 20, 115, 15));
	tfAmountOwed.setBounds(new Rectangle(510, 35, 145, 20));
	tfDetailPartialAmount.setValue(0.0);
	tfDetailPartialAmount.setSize(new Dimension(115, 20));
	btnClose.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnClose_actionPerformed(e);
		}
	    }
	);
	btnPrintSave.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnPrintSave_actionPerformed(e);
		}
	    }
	);
	tfFindProvider.getTextField().addKeyListener(new KeyAdapter() {

						  public void keyTyped(KeyEvent e) {
						      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							  loadComboProveedor();
							  if (cbProvider.getCombo().getItemCount() > 0) {
							      loadVouchers();
							  }
						      }
						  }

					      }
	);
	container.add(jpImputations, BorderLayout.CENTER);
	container.add(dataPanel, BorderLayout.NORTH);
	this.add(container, BorderLayout.CENTER);
	addButton(btnClose);
	addButton(btnPrintSave);
	setHeaderListDetail();
	jpImputations.setPreferredSize(new Dimension(720, 365));
	detailPartialAmountPanel.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("")));
	dataPanel.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("MainData")));
	listDetailPanel.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("Facturas")));
	listDetailPanel.setSortable(false);
	tfAmountOwed.setEnabled(false);
	tfAmountOwed.setEditable(false);
	tfAmountOwed.setSize(new Dimension(140, 20));
	btnPrintSave.setEnabled(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Listado de Facturas impagas por Proveedor");
    }

    /**2010-02-08(moraless)*/
    private void loadComboProveedor() {
	String param = "'" + tfFindProvider.getString() + "'";
	//cbProvider.loadJCombo(LibSQL.exFunction("org.getAllProviderFromPaymentOrderManualByFilter", param));
	cbProvider.loadJCombo(LibSQL.exFunction("org.getallproveedoresconfacturasyoppendientes", param));
	if (cbProvider.getSelectedIndex() != -1) {
	    loadSaldoProveedor();
	    loadComboOrdenesPago();
	}
    }
    
    /** 2010-02-08 (moraless) -- 2010-02-11 (cesar)*/
    private void loadComboOrdenesPago(){
	cbPaymentOrder.loadJCombo(LibSQL.exFunction("cashflow.getallordenespagosinvincular",""+cbProvider.getSelectedValue()));
	if(cbPaymentOrder.getCombo().getModel().getSize() == 0){
	    btnPrintSave.setEnabled(false);
	}else{
	    btnPrintSave.setEnabled(true);
	}
        loadSaldoOrdenDePago();
    }
    
    /** 2010-02-11 (cesar)*/
    private void loadSaldoOrdenDePago() {
        double saldo = LibSQL.getDouble("cashflow.getSaldoOrdenDePagoAdelantada", ""+ cbPaymentOrder.getSelectedValue());
        tfFaltaImputar.setValue(saldo);
    }
    
    
    private void loadSaldoProveedor(){
	double monto = LibSQL.getDouble("org.getsaldoproveedor",cbProvider.getSelectedValue());
	tfAmountOwed.setValue(monto);
	if(monto <= 0){
	    tfAmountOwed.setBackground(Color.lightGray);
	    btnPrintSave.setEnabled(false);
	}else{
	    tfAmountOwed.setBackground(Color.yellow);
	    btnPrintSave.setEnabled(true);
	}
    }

    private void setHeaderListDetail() {
	headerListDetail.removeAllElements();
	headerListDetail.addElement("*");
	headerListDetail.addElement("Comprobante");
	headerListDetail.addElement("Fecha");
	headerListDetail.addElement(Environment.lang.getProperty("Amount"));
	headerListDetail.addElement("($) Monto Cancelado");
	headerListDetail.addElement("Saldo");
	listDetailPanel.getTable().addMouseListener(new MouseAdapter() {
						 public void mouseClicked(MouseEvent e) {
						    calcularSubTotal();
						     if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) {
							 int index = listDetailPanel.getTable().rowAtPoint(e.getPoint());
							 listDetailPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
							 
						     }
						 }

					     }
	);
	String params = "" + cbProvider.getSelectedValue() + ",-1";
	listDetailPanel.setParams("cashflow.getAllVouchersNotPaid", params, headerListDetail);
    }

    //metodo que obtiene el subtotal correspondiente a la suma de las facturas seleccionadas
    private void calcularSubTotal() {
	BigDecimal subTotal = new BigDecimal("0");
	Vector filasSeleccionadas = listDetailPanel.getSelectedsVector();
	for(int i=0;i< filasSeleccionadas.size();i++){
	    Vector fila = (Vector)filasSeleccionadas.elementAt(i);
	    subTotal = subTotal.add(new BigDecimal(fila.elementAt(5).toString()));
	}
	tfDetailPartialAmount.setValue(subTotal.doubleValue());
	if(subTotal.doubleValue() > Double.valueOf(tfFaltaImputar.getValue().toString())){
	    tfDetailPartialAmount.setBackground(Color.RED);
	}else{
	    tfDetailPartialAmount.setBackground(Color.LIGHT_GRAY);
	}
    }
    
    //metodo que carga las facturas correspondientes a un proveedor
    private void loadVouchers() {
	String params = ""+cbProvider.getSelectedValue()+",-1";
	listDetailPanel.refresh(params);
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().setIcon(true);
    }

    private void btnPrintSave_actionPerformed(ActionEvent e) {
	if(control()){
	    if (Advisor.question("Aviso", "¿Está seguro de marcar como pagadas las facturas seleccionadas, \n por un Monto Total de "+tfDetailPartialAmount.getText()+"?")) {
		saveDataPaid();
	    }
	}
    }
    
    /**deprecated*/
/*    private void saveDataPaid(){
	String facturasMarcadas = "Las facturas marcadas como pagadas son: \n";
	Vector filasSeleccionadas = listDetailPanel.getSelectedsVector();
	boolean continuarPagando = true;
	int i = 0;
	//BigDecimal saldo = new BigDecimal(tfAmountOwed.getValue().toString());
	BigDecimal saldo = new BigDecimal(tfFaltaImputar.getValue().toString());
	BigDecimal montoTotalCancelado = new BigDecimal("0");
	String paramsOrdenPago = ""+ cbPaymentOrder.getSelectedValue()+",";
	while( (continuarPagando) && (i < filasSeleccionadas.size()) ){
	    paramsOrdenPago = ""+ cbPaymentOrder.getSelectedValue()+",";
	    String montoPagado = "";
	    String params = "";
	    Vector fila = (Vector)filasSeleccionadas.elementAt(i);
	    BigDecimal monto = new BigDecimal(fila.elementAt(5).toString());
	    String idVoucher = fila.elementAt(0).toString();
	    if (saldo.subtract(monto).doubleValue() >= 0) {
		//se paga el 100 % del monto de la factura
		params = idVoucher +","+monto;
		montoPagado = ""+ monto.doubleValue();
		montoTotalCancelado = montoTotalCancelado.add(monto);
		saldo = saldo.subtract(monto);
		facturasMarcadas += " "+ fila.elementAt(1) +"\n";
	    } else {
		//se paga un % del monto de la factura
	        params = idVoucher +","+ saldo;
		montoPagado = ""+ saldo.doubleValue();
		continuarPagando = false;
	        montoTotalCancelado = montoTotalCancelado.add(saldo);
		BigDecimal porcentaje = new BigDecimal("0");
	        porcentaje = (saldo.multiply(new BigDecimal("100")));
		porcentaje = porcentaje.divide(monto,BigDecimal.ROUND_CEILING);
	        facturasMarcadas += " La "+ fila.elementAt(1) +" se pagó en un "+ porcentaje.doubleValue()+" % \n";
	    }
	    
	    //ahora se guarda la factura pagada
	    //se actualizan las lineas antiguas de la orden de pago
	    //se insertan las nuevas lineas en la orden de pago correspondientes a las facturas seleccionadas
	    if (!(LibSQL.getInt("cashflow.setvoucherpaid", params) > 0)) {
		Advisor.messageBox("Se produjo un error al guardar los datos", "Error1");
	    } else {
		/*if (!(LibSQL.getInt("cashflow.delpaymentorderdetail",""+ cbPaymentOrder.getSelectedValue()) > 0)){
		    Advisor.messageBox("Se produjo un error al guardar los datos","Error2");
		} else {*/
		    //cargar lineas en el detalle de la orden de pago
/*
		    paramsOrdenPago += idVoucher + "," + cbProvider.getSelectedValueRef().toString().split("#")[1]+","+montoPagado + ",'"+fila.elementAt(1).toString()+"'";
		    if (!(LibSQL.getInt("cashflow.addPaymentOrderDetailManual",paramsOrdenPago) > 0)){
			Advisor.messageBox("Se produjo un error al guardar los datos","Error3");
		    }
		//}
	    }
	    i++;
	}
	if (montoTotalCancelado.doubleValue() > 0) {
	    String paramsCompanies = ""+ cbProvider.getSelectedValue() +","+ montoTotalCancelado;
	    if (LibSQL.getInt("cashflow.setCancelAMount", paramsCompanies) > 0) {
	        if (!(LibSQL.getInt("cashflow.delpaymentorderdetail", "" + cbPaymentOrder.getSelectedValue()) > 0)) {
		    Advisor.messageBox("Se produjo un error al guardar los datos", "Error2");
		}else{
		    Advisor.messageBox(facturasMarcadas, "Mensaje");
		    loadComboProveedor();
		    loadComboOrdenesPago();
		}
	    } else {
		Advisor.messageBox("Se produjo un error al guardar los datos", "Error");
	    }
	}
	loadSaldoProveedor();
	loadVouchers();
    }*/
    
    
    //metodo que permite pagar las facturas marcadas y asociarlas con una orden de pago
    private void saveDataPaid(){
	String facturasMarcadas = "Las facturas marcadas como pagadas son: \n";
	Vector filasSeleccionadas = listDetailPanel.getSelectedsVector();
	Vector idSelectedsVouchers = new Vector();
	//obtengo id de facturas seleccionadas 
	for(int i = 0; i< filasSeleccionadas.size();i++){
	    Vector fila = (Vector)filasSeleccionadas.elementAt(i);
	    idSelectedsVouchers.add(fila.elementAt(0).toString());
	    facturasMarcadas += " "+ fila.elementAt(1) +"\n";
	}
	//antes de enviar el monto total correspondiente a las facturas marcadas, se debe calcular nuevamente dicho monto para asegurarse que el monto enviado es el correcto.
	calcularSubTotal();
	String params = ""+cbPaymentOrder.getSelectedValue()+",'" + vectorToParams(idSelectedsVouchers)+"',"+cbProvider.getSelectedValue()+","+cbProvider.getSelectedValueRef().toString().split("#")[1]+","+tfDetailPartialAmount.getValue();
	if(!LibSQL.getBoolean("cashflow.markVoucher",params)){
	    Advisor.messageBox("Se produjo un error al guardar los datos","Error");
	}else{
	    Advisor.messageBox(facturasMarcadas, "Mensaje");
	    loadSaldoProveedor();
	    loadComboOrdenesPago();
	    loadVouchers();
	}
    }
    
    private String vectorToParams(Vector _vector){
	String resultado = "";
	int tam = _vector.size();
	for(int i = 0 ; i < tam; i++){
	    resultado += _vector.elementAt(i).toString() + ",";  
	}
	resultado = resultado.substring(0,resultado.length() - 1);
	return resultado;
    }
    
    
    private boolean control(){
	boolean retorno = false;
	if((listDetailPanel.getSelectedsVector().size() > 0) && ( Double.parseDouble(tfDetailPartialAmount.getValue().toString()) <= Double.parseDouble(tfFaltaImputar.getValue().toString()))){
	    retorno = true;
	}else{
	    if(listDetailPanel.getSelectedsVector().size() > 0){
	        Advisor.messageBox("El monto total de las facturas seleccionadas debe ser menor o igual\n que saldo a imputar de la Orden de Pago", "Error");
	    }else{
	        Advisor.messageBox("Debe seleccionar al menos una factura", "Error");
	    }
	}
	return retorno;
    }
}
