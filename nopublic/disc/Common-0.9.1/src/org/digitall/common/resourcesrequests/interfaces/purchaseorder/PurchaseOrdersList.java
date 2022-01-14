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
 * PurchaseOrdersList.java
 *
 * */
package org.digitall.common.resourcesrequests.interfaces.purchaseorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.sql.ResultSet;

import java.util.Vector;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.interfaces.account.PaymentsOrdersMain;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.common.resourcescontrol.classes.PurchaseOrder;
import org.digitall.common.resourcescontrol.reports.PurchaseOrderDetailReport;
import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesReceiveMain;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.OrganizationInfo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.buttons.ReportButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PurchaseOrdersList extends BasicPrimitivePanel {

    private BasicPanel findPanel = new BasicPanel();
    private BasicPanel northPanel = new BasicPanel();
    
    private int[] sizeColumnList = { 59, 73, 87, 101, 277, 243, 363 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Órdenes de Compra", dataRow) {

	@Override
	public void finishLoading() {
	    btnReport.setEnabled(false);
	    btnGeneralReport.setEnabled(hasItems());
	    btnReport.setToolTipText("Imprimir la Orden de Compra seleccionada");
	    selectedPurchaseOrder.setIdPurchaseOrder(-1);
	    detailListPanel.refresh(-1);
	}
    };

    private TFInput tfStartDate = new TFInput(DataTypes.SIMPLEDATE, "StartDate", false);
    private TFInput tfEndDate = new TFInput(DataTypes.SIMPLEDATE, "EndDate", false);
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre");
    private int[] sizeColumnDetailList = { 232, 101, 82, 72, 75, 84, 79 };
    private Vector detailDataRow = new Vector();
    private GridPanel detailListPanel = new GridPanel(30, sizeColumnDetailList, "Detalle de la Orden de Compra", detailDataRow);
    
    private PrintButton btnGeneralReport = new PrintButton();
    private FindButton btnFind = new FindButton();
    private PrintButton btnReport = new PrintButton();

    private PurchaseOrder selectedPurchaseOrder = new PurchaseOrder();

    public PurchaseOrdersList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(780, 512));
	this.setPreferredSize(new Dimension(780, 400));
	setTitle("Órdenes de Compra existentes");
	listPanel.setSize(new Dimension(780, 200));
	listPanel.setMinimumSize(new Dimension(173, 200));
	listPanel.setPreferredSize(new Dimension(400, 200));
	findPanel.setLayout(null);
	findPanel.setBounds(new Rectangle(0, 0, 750, 10));
	findPanel.setPreferredSize(new Dimension(1, 70));
	findPanel.setSize(new Dimension(780, 96));
	cbCostsCentre.setBounds(new Rectangle(345, 25, 335, 35));
	btnFind.setBounds(new Rectangle(695, 35, 30, 25));
	btnFind.setSize(new Dimension(30, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	northPanel.setBounds(new Rectangle(0, 0, 780, 400));
	northPanel.setLayout(new BorderLayout());
	northPanel.setSize(new Dimension(780, 400));
	northPanel.setPreferredSize(new Dimension(400, 270));
	findPanel.add(btnFind, null);
	findPanel.add(cbCostsCentre, null);
	findPanel.add(tfStartDate, null);
	findPanel.add(tfEndDate, null);
	northPanel.add(findPanel, BorderLayout.NORTH);
	northPanel.add(listPanel, BorderLayout.CENTER);
	this.add(northPanel, BorderLayout.NORTH);
	cbCostsCentre.autoSize();
	cbCostsCentre.getCombo().addItem("Todos", "-1");
	cbCostsCentre.setSelectedID("-1");
	detailListPanel.setBounds(new Rectangle(5, 5, 770, 390));
	detailListPanel.setSize(new Dimension(780, 100));
	this.add(detailListPanel, BorderLayout.CENTER);
	setHeaderList();
	btnGeneralReport.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnGeneralReport_actionPerformed(e);
					}

				    }
	);
	btnGeneralReport.setToolTipText("Reporte con Informe");
	this.addButton(btnReport);
	this.addButton(btnGeneralReport);
	btnReport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReport_actionPerformed(e);
				 }

			     }
	);
	setDetailList();
	btnReport.setToolTipText("Imprimir la Orden de Compra seleccionada");
	btnGeneralReport.setToolTipText("Listado General de Órdenes de Compra");
	tfStartDate.setBounds(new Rectangle(95, 25, 95, 35));
	tfEndDate.setBounds(new Rectangle(215, 25, 95, 35));
	tfStartDate.setFirstDayOfYear();
	tfEndDate.setToday();
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar Órdenes de Compra"));
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       loadSelectedObject();
					       if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

					       } else if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
					       
					       }
					   }

				       }
	);
	btnReport.setEnabled(false);
	btnGeneralReport.setEnabled(false);
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("PurchaseOrderNumber"));
	headerList.addElement(Environment.lang.getProperty("RequestNumber"));
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement(Environment.lang.getProperty("TotalCost"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Provider"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("CostsCentre"));
	headerList.addElement("*");
	headerList.addElement("*");//Environment.lang.getProperty("Status"));
	headerList.addElement(Environment.lang.getProperty("Description"));
	headerList.addElement("*");
	listPanel.setParams("resourcescontrol.getAllPurchaseOrders", "-1,'','',''", headerList);
    }

    public void refresh() {
	listPanel.refresh(cbCostsCentre.getSelectedValue() + ",''," + tfStartDate.getDateForSQL() + "," + tfEndDate.getDateForSQL());
    }

    private void loadSelectedObject() {
	if (!dataRow.isEmpty()) {
	    selectedPurchaseOrder.setIdPurchaseOrder(Integer.parseInt("" + dataRow.elementAt(0)));
	    selectedPurchaseOrder.setNumber(dataRow.elementAt(1).toString());
	    selectedPurchaseOrder.setDate(Proced.setFormatDate(dataRow.elementAt(3).toString(), false));
	    selectedPurchaseOrder.setAmount(Double.parseDouble("" + dataRow.elementAt(4)));
	    selectedPurchaseOrder.setDescription(dataRow.elementAt(7).toString());
	    selectedPurchaseOrder.setProvider(new Provider(Integer.parseInt("" + dataRow.elementAt(5)), dataRow.elementAt(6).toString()));
	    selectedPurchaseOrder.setIdRequestStatus(Integer.parseInt("" + dataRow.elementAt(7)));
	    selectedPurchaseOrder.setDescription("" + dataRow.elementAt(9));
	    btnReport.setEnabled(true);
	    btnReport.setToolTipText("<html><center><u>Imprimir la Orden de Compra</u><br>Nº " + selectedPurchaseOrder.getNumber() + "</center></html>");
	    detailListPanel.refresh(selectedPurchaseOrder.getIdPurchaseOrder());
	} else{
	    btnReport.setEnabled(false);
	    btnReport.setToolTipText("Imprimir la Orden de Compra seleccionada");
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }
    private void btnGeneralReport_actionPerformed(ActionEvent e) {
	if (listPanel.hasItems()) {
	    String param = "" + cbCostsCentre.getSelectedValue() + "," + tfStartDate.getDateForSQL() + "," + tfEndDate.getDateForSQL() + ",-1";
	    BasicReport report = new BasicReport(PurchaseOrdersList.class.getResource("xml/purchaseOrdersListReport.xml"));
	    report.addTableModel("resourcescontrol.xmlGetAllPurchaseOrders", "" + param);
	    report.setProperty("startdate", tfStartDate.getDate());
	    report.setProperty("enddate", tfEndDate.getDate());
	    report.doReport();
	} else {
	    Advisor.messageBox("No se encontraron resultados para generar un reporte", "Aviso");
	}
    }

    private void setDetailList() {
	Vector detailList = new Vector();
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Resource"));
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Brand"));
	detailList.addElement(Environment.lang.getProperty("Quantity"));
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Unit"));
	detailList.addElement(Environment.lang.getProperty("Price"));
	detailList.addElement(Environment.lang.getProperty("Cost"));
	detailList.addElement(Environment.lang.getProperty("Recibido"));
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");//Environment.lang.getProperty("Status"));
	detailList.addElement("*");
	detailList.addElement("*");
	detailListPanel.setParams("resourcescontrol.getAllPurchaseOrderDetail", "-1", detailList);
    }

    private void btnReport_actionPerformed(ActionEvent e) {
	BasicReport report = new BasicReport(PurchaseOrdersMgmt.class.getResource("xml/purchaseOrder.xml"));
	report.addTableModel("resourcescontrol.xmlGetPurchaseOrder", "" + selectedPurchaseOrder.getIdPurchaseOrder());
	report.addTableModel("resourcescontrol.xmlGetPurchaseOrderDetail", "" + selectedPurchaseOrder.getIdPurchaseOrder());
        report.setProperty("textamount", Format.NumberToText.numberToText(selectedPurchaseOrder.getAmount()) + ".-");
	report.doReport();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede buscar Órdenes de Compra por rangos de fechas y/o por Centro de Costos, ver su detalle y reimprimirlas");
    }

}
