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
 * PaymentOrderDetailList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.account;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.PaymentOrder;
import org.digitall.common.cashflow.classes.PaymentOrderDetail;
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.cashflow.reports.account.PaymentOrderDetailListReport;
import org.digitall.common.cashflow.interfaces.account.PaymentsOrdersMain;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.ReportButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PaymentOrderDetailList extends BasicPrimitivePanel {

    private int[] sizeColumnList = {150, 85, 270, 90};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Detalle", dataRow);
    private Vector headerList = new Vector();
    private BasicPanel dataPanel = new BasicPanel("Agregar Factura p/pagar");
    private CBInput cbVoucher = new CBInput(0,"Invoices",false);
    private TFInput tfProvider = new TFInput(DataTypes.INTEGER, "Provider", false);
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private PaymentOrder paymentOrder;
    private PaymentOrderDetail paymentOrderDetail;
    private AssignButton btnAdd = new AssignButton(true);
    private ReportButton btnReport = new ReportButton();
    private PaymentsOrdersMain parentMain;
    
    
    public PaymentOrderDetailList(PaymentsOrdersMain _parentMain) {
	try {
	    parentMain = _parentMain;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(650, 555));
	this.setPreferredSize(new Dimension(650, 555));
	setTitle("Detalle");
	listPanel.setBounds(new Rectangle(5, 95, 650, 220));
	dataPanel.setLayout(null);
	dataPanel.setBounds(new Rectangle(5, 10, 650, 75));
	dataPanel.setPreferredSize(new Dimension(650, 120));
	tfProvider.setBounds(new Rectangle(10, 30, 190, 35));
	cbVoucher.setBounds(new Rectangle(355, 30, 285, 35));
	tfDescription.setBounds(new Rectangle(10, 70, 575, 35));
	btnAdd.setBounds(new Rectangle(612, 80, 28, 30));
	btnAdd.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnAdd_actionPerformed(e);
			      }

			  }
	);
	dataPanel.add(btnAdd, null);
	dataPanel.add(tfDescription, null);
	dataPanel.add(cbVoucher, null);
	dataPanel.add(tfProvider, null);
	this.add(dataPanel, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	btnReport.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnReport_actionPerformed(e);
			      }

			  }
	);
	cbVoucher.autoSize();
	tfProvider.setEnabled(false);
	this.addButton(btnReport);
	setHeaderList();
	
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }
    
    private void loadComboVoucher(){
	String param = "'',"+ paymentOrder.getIdEntity();
	cbVoucher.loadJCombo(LibSQL.exFunction("cashflow.getAllVouchersNotPaid", param));	
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("VoucherNumber"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("CostsCentre"));
	headerList.addElement(Environment.lang.getProperty("TotalAmount"));
	headerList.addElement("*");
	headerList.addElement("*");

	listPanel.getTable().addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
	        loadObjectSelected();
		if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    
		} else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1){
		    
		} 
	    }
	}
	);
	
	listPanel.setParams("cashflow.getAllPaymentOrderDetail","-1", headerList); 
    }
    
    public void refresh() {
	String params = ""+ paymentOrder.getIdPaymentOrder();
	listPanel.refresh(params);
    }

    private void loadObjectSelected(){
	if (!dataRow.isEmpty()) {
	    paymentOrderDetail = new PaymentOrderDetail(Integer.parseInt(""+ dataRow.elementAt(0)));
	    paymentOrderDetail.setPaymentOrder(new PaymentOrder(Integer.parseInt(""+ dataRow.elementAt(1))));
	    paymentOrderDetail.setVoucher(new Voucher(Integer.parseInt(""+ dataRow.elementAt(2))));
	    paymentOrderDetail.setDescription(""+ dataRow.elementAt(14));
	    
	    tfDescription.setValue(""+ dataRow.elementAt(14));
	}
    }

    public void setPaymentOrder(PaymentOrder paymentOrder) {
	this.paymentOrder = paymentOrder;	
	paymentOrderDetail = null;
	loadComboVoucher();
	refresh();
    }

    public boolean saveData(){
	if (!cbVoucher.getSelectedValue().equals("-1")){
	    if (paymentOrderDetail == null){
		paymentOrderDetail = new PaymentOrderDetail();
	    }
	    
	    paymentOrderDetail.setPaymentOrder(paymentOrder);
	    paymentOrderDetail.setVoucher(new Voucher(Integer.parseInt(""+ cbVoucher.getSelectedValue())));
	    paymentOrderDetail.setDescription(tfDescription.getString());
	    
	    if (paymentOrderDetail.saveData_old() >= 0){
	        if (paymentOrder.setVoucherPaid() == 0){
		    refresh();
		    loadComboVoucher();
		    paymentOrderDetail.setIdPaymentOrderDetail(-1);
		    paymentOrder.updateAmount();
		    parentMain.getPaymentsOrdersList().refresh();
		    parentMain.getPaymentsOrdersList().setHeaderTitle();
		    return true;
		} else {
		    Advisor.messageBox("Ocurrio un error al registrar la factura como pagada,\nreferida al Orden de Pago Nº"+ paymentOrder.getNumber(),"Error");
		    return false;
		}
	    } else {
		return false;
	    }
	} else {
	    Advisor.messageBox("No hay Factura para Pagar","No hay Factura");
	    return false;
	}
    }

    private void btnAdd_actionPerformed(ActionEvent e) {
	saveData();
    }

    public void setProviderName(String _provider){
	tfProvider.setValue(_provider);
    }
    
    private void btnReport_actionPerformed(ActionEvent e) {
	if (listPanel.getTable().getRowCount() != 0)  {
		 new PaymentOrderDetailListReport(paymentOrder);
	} else  {
	    Advisor.messageBox("No se generó el Informe, porque\nno hay Ordenes de Pago en la grilla","Informe");
	}
    }
}
