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
 * PaymentOrderList.java
 *
 * */
package org.digitall.apps.cashflow.interfaces.paymentorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JPanel;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.PaymentOrder;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;


public class PaymentOrderList  extends BasicPrimitivePanel{

    private BasicPanel jPanel1 = new BasicPanel();
    private TFInput tfDate = new TFInput(DataTypes.DATE,"Date",false);
    private TFInput tfNumber = new TFInput(DataTypes.STRING,"Number",false);
    private FindButton btnFind = new FindButton();
    private CBInput cbProvider = new CBInput(0,"Provider",false);
    private int[] sizeColumnList = {68, 85, 236, 96, 98, 96};
    private Vector dataRow = new Vector();
    private GridPanel listPaymentOrderPanel = new GridPanel(30, sizeColumnList, "Órdenes de Pago", dataRow);
    private Vector headerList = new Vector();
    private int[] sizeColumnDetail = {230 , 236 , 102, 110};
    private Vector dataRowDetail = new Vector();
    private GridPanel listPaymentOrderDetailPanel = new GridPanel(30, sizeColumnDetail, "Asiento de la Orden de Pago seleccionada", dataRowDetail);
    private Vector headerDetail = new Vector();
    private PaymentOrder paymentOrder;
    private TFInput tfFindProvider = new TFInput(DataTypes.STRING,"FindProvider",false);
    private PrintButton btnPrint = new PrintButton();
    private DeleteButton btnDelete = new DeleteButton();
    private DeleteButton btnClearProvider = new DeleteButton();
    private PrintButton btnGeneralPrint = new PrintButton();
    private PrintButton btnListadoOPAdelantadas = new PrintButton();
    private JPanel jPanel2 = new JPanel();
    private GridLayout gridLayout1 = new GridLayout();

    public PaymentOrderList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(731, 497));
	jPanel1.setPreferredSize(new Dimension(10, 70));
	jPanel1.setLayout(null);
	tfDate.setBounds(new Rectangle(10, 20, 95, 35));
	tfNumber.setBounds(new Rectangle(125, 20, 90, 35));
	btnFind.setBounds(new Rectangle(645, 35, 20, 20));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	cbProvider.setBounds(new Rectangle(360, 20, 275, 35));
	tfFindProvider.setBounds(new Rectangle(235, 20, 120, 35));
	btnPrint.setBounds(new Rectangle(525, 5, 20, 20));
	btnPrint.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnPrint_actionPerformed(e);
				 }

			     }
	);
        btnGeneralPrint.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnGeneralPrint_actionPerformed(e);
                                 }

                             }
        );
        btnListadoOPAdelantadas.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {
                                     btnListadoOPAdelantadas_actionPerformed(e);
                                 }

                             }
        );
	btnDelete.setBounds(new Rectangle(240, 20, 28, 33));
	btnDelete.setSize(new Dimension(20, 20));
	btnDelete.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnDelete_actionPerformed(e);
				 }

			     }
	);
	addButton(btnDelete);
	addButton(btnPrint);
        addButton(btnGeneralPrint);
        addButton(btnListadoOPAdelantadas);
	//jPanel1.add(btnClearProvider, null);
	jPanel1.add(tfFindProvider, null);
	jPanel1.add(cbProvider, null);
	jPanel1.add(btnFind, null);
	jPanel1.add(tfDate, null);
	jPanel1.add(tfNumber, null);
	this.add(jPanel1, BorderLayout.NORTH);
	jPanel2.add(listPaymentOrderPanel, null);
	jPanel2.add(listPaymentOrderDetailPanel, null);
	this.add(jPanel2, BorderLayout.CENTER);
	cbProvider.autoSize();
        cbProvider.setGeneralItem(true);
	tfFindProvider.getTextField().addKeyListener(new KeyAdapter() {

						  public void keyTyped(KeyEvent e) {
						      if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							  loadComboProvider();
						      }
						  }

					      }
	);
	
	setHeaderList();
	setHeaderDetail();
	btnDelete.setEnabled(false);
	btnClearProvider.setBounds(new Rectangle(640, 35, 20, 20));
	btnClearProvider.setSize(new Dimension(20, 20));
	btnClearProvider.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnClearProvider_actionPerformed(e);
					}

				    }
	);
	btnPrint.setEnabled(false);
	btnGeneralPrint.setToolTipText("Imprimir listado de las Órdenes de Pago");
        btnListadoOPAdelantadas.setToolTipText("Imprimir listado de las Órdenes de Pago Adelantadas");
	jPanel2.setLayout(gridLayout1);
	gridLayout1.setRows(2);
        jPanel1.setBorder(BorderPanel.getBorderPanel(Environment.lang.getProperty("FindPaymentOrder")));
        //btnListadoOPAdelantadas.setVisible(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Listado de las Órdenes de Pago y sus correspondientes Asientos");
    }
    
    private void loadComboProvider(){
	String param = "'" + tfFindProvider.getString() + "'";
	cbProvider.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", param));
    }
    
    private void setHeaderList(){
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Number"));
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Provider"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("DebitAmount"));
	headerList.addElement(Environment.lang.getProperty("DeductionAmount"));
	headerList.addElement(Environment.lang.getProperty("Amount"));
	headerList.addElement("*");
	headerList.addElement("*");
	
				
	listPaymentOrderPanel.getTable().addMouseListener(new MouseAdapter() {
	     public void mouseClicked(MouseEvent e) {
		 loadObjectSelected();
	     }
	 }
	);
	
	listPaymentOrderPanel.setParams("cashflow.getAllPaymentOrders", "'', -1, -1",headerList);    
    }
    
    public void refresh() {
	String params = "'"+ Proced.setFormatDate(tfDate.getDate(), false) 
			+"', "+ (tfNumber.getString().equals("")? "-1": tfNumber.getString()) 
			+" ,"+ (cbProvider.getSelectedValue().equals("-1")? "-1": cbProvider.getSelectedValue());
	listPaymentOrderPanel.refresh(params);
        listPaymentOrderDetailPanel.refresh("-1");
	btnDelete.setEnabled(false);
	btnPrint.setEnabled(false);
	btnDelete.setToolTipText("Borrar la Orden de Pago seleccionada");
	btnPrint.setToolTipText("Imprimir la Orden de Pago seleccionada");
    }
    
    private void loadObjectSelected(){
	if (!dataRow.isEmpty()){
	    paymentOrder = new PaymentOrder();
	    paymentOrder.setIdPaymentOrder(Integer.parseInt(""+ dataRow.elementAt(0)));
	    paymentOrder.setNumber(Integer.parseInt(""+ dataRow.elementAt(1)));
	    paymentOrder.setDate(Proced.setFormatDate(""+ dataRow.elementAt(2), false));
	    paymentOrder.setProvider(new Provider(Integer.parseInt(""+ dataRow.elementAt(3))));
	    paymentOrder.setCostCentre(new CostsCentre(Integer.parseInt(""+ dataRow.elementAt(5))));
	    paymentOrder.setDetailAmount(Double.parseDouble(""+ dataRow.elementAt(6)));
	    paymentOrder.setDeductionAmount(Double.parseDouble(""+ dataRow.elementAt(7)));
	    paymentOrder.setAmount(Double.parseDouble(""+ dataRow.elementAt(8)));
	    paymentOrder.setObservations(""+ dataRow.elementAt(9));
	    
	    btnDelete.setEnabled(true);
	    btnPrint.setEnabled(true);
	    btnPrint.setToolTipText("<html><center><u>Imprimir Orden de Pago</u><br>Nº " + paymentOrder.getNumber() + ": " + paymentOrder.getObservations() + "</center></html>");
	    btnDelete.setToolTipText("<html><center><u>Borrar Orden de Pago</u><br>Nº " + paymentOrder.getNumber() + ": " + paymentOrder.getObservations() + "</center></html>");
	    refreshDetail();
	} else {
	    btnDelete.setEnabled(false);
	    btnPrint.setEnabled(false);
	    btnPrint.setToolTipText("Imprimir la Orden de Pago seleccionada");
	    btnDelete.setToolTipText("Borrar la Orden de Pago seleccionada");
	}
    }
    
    private void setHeaderDetail(){
	headerDetail.removeAllElements();
	headerDetail.addElement("*");
	headerDetail.addElement("*");
	headerDetail.addElement("*");
	headerDetail.addElement("*");
	headerDetail.addElement("*");
        headerDetail.addElement(Environment.lang.getProperty("Accounting"));
	headerDetail.addElement(Environment.lang.getProperty("Concept"));
	headerDetail.addElement(Environment.lang.getProperty("Debit"));
	headerDetail.addElement(Environment.lang.getProperty("Credit"));
	
	listPaymentOrderDetailPanel.getTable().addMouseListener(new MouseAdapter() {
	     public void mouseClicked(MouseEvent e) {
		 loadObjectDetailSelected();
		 if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
		     
		 } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
		    
		 } else if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
		    int index = listPaymentOrderDetailPanel.getTable().rowAtPoint(e.getPoint());
		    listPaymentOrderDetailPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
		} 
	     }
	 }
	);
	
	listPaymentOrderDetailPanel.setParams("cashflow.getAllPaymentOrderBookKeepingEntry", "-1",headerDetail);    
    }
    
    public void refreshDetail() {
	if (paymentOrder != null){
	    String params = ""+ paymentOrder.getIdPaymentOrder();
	    listPaymentOrderDetailPanel.refresh(params);
	}
    }
    
    private void loadObjectDetailSelected(){
	
    }
    
    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }
    
    private void btnPrint_actionPerformed(ActionEvent e) {
	if (paymentOrder != null){
	    BasicReport report = new BasicReport(PaymentOrderMgmt.class.getResource("xml/paymentorder.xml"));
	    report.addTableModel("cashflow.getPaymentOrders", ""+ paymentOrder.getIdPaymentOrder());
	    report.addTableModel("cashflow.xmlGetAllPaymentOrderBookKeepingEntry", paymentOrder.getIdPaymentOrder());
	    report.setProperty("totalAmount",paymentOrder.getAmount());
	    report.setProperty("textamount",Format.NumberToText.numberToText(paymentOrder.getAmount()) + ".-");
	    report.doReport();
            /** ANTES DE CONTINUAR, SE DEBE ARREGLAR LAS ORDENES DE PAGO QUE FUERON CARGAAS SIN EL COMPROBANTE DE RETENCIÓN */
            /*if (LibSQL.getBoolean("cashflow.existPaymentOrderDeductionByPaymentOrder",""+ paymentOrder.getIdPaymentOrder())) {
                if (Advisor.question("Constancia de Retención DGR", "¿Desea imprimir la Constancia de Retención DGR?")) {
                    BasicReport deductionReport = new BasicReport(PaymentOrderList.class.getResource("xml/DeductionTaxVoucher.xml"));
                    deductionReport.addTableModel("cashflow.xmlGetDeductionTaxVoucher", "" + paymentOrder.getIdPaymentOrder());
                    //deductionReport.setProperty("textamount", Format.NumberToText.numberToText(tfDeductionPartialAmount.getAmount()) + ".-");
                    deductionReport.doReport();
                }
            }*/
            
	}
    }

    private void btnGeneralPrint_actionPerformed(ActionEvent e) {
        if ( listPaymentOrderPanel.getTable().getRowCount() != 0 ) {
            String startDate = "";
            String endDate = "";
            if (startDate.equals(""))  {
                startDate = "2008-01-01";
            } else {
                startDate = "" + Proced.setFormatDate(startDate,false);
            }
            if (endDate.equals(""))  {
                endDate = Environment.currentDate;
            } else {
                endDate = Proced.setFormatDate(endDate,false);
            }
            String params = "'"+ startDate +"','" + endDate
                            +"' ,"+ cbProvider.getSelectedValue();
            BasicReport report = new BasicReport(PaymentOrderMgmt.class.getResource("xml/PaymentOrderListReport.xml"));
            report.addTableModel("cashflow.xmlGetAllPaymentOrders", params);
            report.setProperty("startdate","" + Proced.setFormatDate(startDate,true));
            report.setProperty("enddate","" + Proced.setFormatDate(endDate,true));
            report.doReport();
        }
    }


    private void btnDelete_actionPerformed(ActionEvent e) {
	if (Advisor.question("Anular Orden de Pago", "¿Desea anular la Orden de Pago Nº"+ paymentOrder.getNumber())){
	    if (paymentOrder.cancel()){
		refresh();
	    }
	}
    }

    private void btnClearProvider_actionPerformed(ActionEvent e) {
	cbProvider.removeAllItems();
	tfFindProvider.setValue("");
    }
    
    private void btnListadoOPAdelantadas_actionPerformed(ActionEvent e) {
        BasicReport report = new BasicReport(PaymentOrderMgmt.class.getResource("xml/ListadoOrdenesDePagoAdelantadas.xml"));
        report.addTableModel("cashflow.xmlGetAllOrdenesDePagoAdelantadas", "");
        report.addTableModel("cashflow.xmlGetAllOrdenesDePagoPorProveedor","");
        report.doReport();
        
    }

}
