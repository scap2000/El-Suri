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
 * DespatchNotesList.java
 *
 * */
package org.digitall.apps.resourcesrequests.interfaces.resourcesmovements;

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
import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class DespatchNotesList extends BasicPrimitivePanel {

    private BasicPanel findPanel = new BasicPanel();
    private int[] sizeColumnList = { 80, 95, 230, 265 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Remitos", dataRow);
    private Vector headerList = new Vector();
    private FindButton btnFind = new FindButton();
    private TFInput tfStartDate = new TFInput(DataTypes.DATE, "From", false);
    private TFInput tfEndDate = new TFInput(DataTypes.DATE, "To", false);
    private TFInput tfSearchProvider = new TFInput(DataTypes.STRING, "SearchProvider", false);
    private CBInput cbProviders = new CBInput(0, "Provider", false);
    private Voucher voucherSelected = new Voucher(-1);
    private String entityName;
    private PrintButton btnReport = new PrintButton();
    private PrintButton btnDetailReport = new PrintButton();
    private int[] sizeColumnDetailList = { 300, 200, 85, 85 };
    private Vector detailDataRow = new Vector();
    private GridPanel detailListPanel = new GridPanel(30, sizeColumnDetailList, "Detalle", detailDataRow);
    private Vector detailList = new Vector();
    private JPanel content = new JPanel();
    private GridLayout gridLayout1 = new GridLayout();

    public DespatchNotesList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setBounds(new Rectangle(10, 10, 725, 455));
	this.setSize(new Dimension(725, 497));
	findPanel.setLayout(null);
	findPanel.setBorder(BorderPanel.getBorderPanel("Buscar Remitos"));
	findPanel.setMinimumSize(new Dimension(1, 100));
	findPanel.setPreferredSize(new Dimension(1, 70));
	listPanel.setMinimumSize(new Dimension(173, 300));
	listPanel.setPreferredSize(new Dimension(400, 200));
	tfStartDate.setBounds(new Rectangle(25, 20, 90, 35));
	cbProviders.setBounds(new Rectangle(415, 20, 245, 35));
	btnReport.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnReport_actionPerformed(e);
				 }

			     }
	);
	tfEndDate.setBounds(new Rectangle(135, 20, 90, 35));
	btnFind.setBounds(new Rectangle(670, 30, 40, 25));
	btnFind.setSize(new Dimension(40, 25));
	btnFind.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   btnFind_actionPerformed(e);
			       }

			   }
	);
	this.addButton(btnReport);
	this.addButton(btnDetailReport);
	tfSearchProvider.setBounds(new Rectangle(255, 20, 150, 35));
	this.add(findPanel, BorderLayout.NORTH);
	content.add(listPanel);
	content.add(detailListPanel);
	this.add(content, BorderLayout.CENTER);
	findPanel.add(cbProviders, null);
	findPanel.add(tfSearchProvider, null);
	findPanel.add(btnFind, null);
	findPanel.add(tfEndDate, null);
	findPanel.add(tfStartDate, null);
	cbProviders.autoSize();
	setHeaderList();
	setDetailList();
	tfSearchProvider.getTextField().addKeyListener(new KeyAdapter() {

						    public void keyTyped(KeyEvent e) {
							if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
							    loadComboProviders();
							    if (!cbProviders.getSelectedValue().toString().equals("0")) {
								//loadFroms();
							    }
							}
						    }

						}
	);
	cbProviders.getCombo().addItem("Todos", "-1");
	btnDetailReport.addActionListener(new ActionListener() {

				       public void actionPerformed(ActionEvent e) {
					   btnDetailReport_actionPerformed(e);
				       }

				   }
	);
	detailListPanel.setPreferredSize(new Dimension(400, 200));
	content.setLayout(gridLayout1);
	gridLayout1.setRows(2);
	btnDetailReport.setToolTipText("Imprimir el Remito seleccionado");
	btnReport.setToolTipText("Listado General de Remitos");
	btnDetailReport.setEnabled(false);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("DespatchNoteNumber"));
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Provider"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Description"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   loadObjectSelected();
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
					       }
					   }

				       }
	);
	String params = "'" + Proced.setFormatDate(tfStartDate.getString(), false) + "','" + Proced.setFormatDate(tfEndDate.getString(), false) + "'," + cbProviders.getSelectedValue();
	listPanel.setParams("cashflow.getAllDespatchNotesByFilter", params, headerList);
    }

    private void setDetailList() {
	detailList.removeAllElements();
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Resource"));
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Brand"));
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement("*");
	detailList.addElement(Environment.lang.getProperty("Quantity"));
	detailList.addElement(Environment.lang.getProperty("Unit"));
	detailList.addElement("*");
	detailList.addElement("*");
	detailListPanel.getTable().addMouseListener(new MouseAdapter() {

						 public void mouseClicked(MouseEvent e) {
						     if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {

						     } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

						     }
						 }

					     }
	);
	detailListPanel.setParams("cashflow.getAllDespatchNoteDetail", "-1", detailList);
    }

    private void loadComboProviders() {
	String param = "'" + tfSearchProvider.getString() + "'";
	cbProviders.loadJCombo((LibSQL.exFunction("org.getAllProvidersByFilter", param)));
	cbProviders.getCombo().addItem("Todos", "-1");
    }

    public void refresh() {
	String params = "'" + Proced.setFormatDate(tfStartDate.getString(), false) + "','" + Proced.setFormatDate(tfEndDate.getString(), false) + "'," + cbProviders.getSelectedValue();
	listPanel.refresh(params);
	btnDetailReport.setToolTipText("Imprimir el Remito seleccionado");
	btnDetailReport.setEnabled(false);
	voucherSelected.setIdVoucher(-1);
	refreshDetail();
    }

    public void refreshDetail() {
	String params = "" + voucherSelected.getIdVoucher();
	detailListPanel.refresh(params);
    }

    private void loadObjectSelected() {
	if (!dataRow.isEmpty()) {
	    voucherSelected = new Voucher(Integer.parseInt("" + dataRow.elementAt(0)));
	    voucherSelected.setIdVoucherType(Integer.parseInt("" + dataRow.elementAt(1)));
	    voucherSelected.setNumber(Long.parseLong("0"+ dataRow.elementAt(2).toString()));
	    voucherSelected.setDate(Proced.setFormatDate("" + dataRow.elementAt(3), false));
	    voucherSelected.setIdEntity(Integer.parseInt("" + dataRow.elementAt(4)));
	    entityName = "" + dataRow.elementAt(5);
	    voucherSelected.setIdEntitytype(Integer.parseInt("" + dataRow.elementAt(6)));
	    voucherSelected.setPartialWOTaxes(Double.parseDouble("" + dataRow.elementAt(7)));
	    voucherSelected.setTaxes(Double.parseDouble("" + dataRow.elementAt(8)));
	    voucherSelected.setPartialWTaxes(Double.parseDouble("" + dataRow.elementAt(9)));
	    voucherSelected.setVATAmount(Double.parseDouble("" + dataRow.elementAt(10)));
	    voucherSelected.setVATp(Double.parseDouble("" + dataRow.elementAt(11)));
	    voucherSelected.setAmount(Double.parseDouble("" + dataRow.elementAt(12)));
	    voucherSelected.setWithDetail((dataRow.elementAt(13).toString() == "--" ? false : true));
	    voucherSelected.setDescription("" + dataRow.elementAt(14));
	    voucherSelected.setCostsCentre(new CostsCentre(Integer.parseInt("" + dataRow.elementAt(16))));
	    voucherSelected.setClosed((dataRow.elementAt(17).toString() == "--" ? false : true));
	    refreshDetail();
	    btnDetailReport.setToolTipText("<html><center><u>Imprimir Remito</u><br>Nº " + voucherSelected.getNumber() + " - " + entityName +"</center></html>");
	    btnDetailReport.setEnabled(true);
	} else {
	    btnDetailReport.setEnabled(false);
	    btnDetailReport.setToolTipText("Imprimir el Remito seleccionado");
	}
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnReport_actionPerformed(ActionEvent e) {
	String provider = "";
	if (Integer.parseInt(cbProviders.getSelectedValue().toString()) != 0) {
	    provider = cbProviders.getSelectedItem().toString();
	}
	String params = "'" + Proced.setFormatDate(tfStartDate.getString(), false) + "','" + Proced.setFormatDate(tfEndDate.getString(), false) + "'," + cbProviders.getSelectedValue();
        BasicReport report = new BasicReport(DespatchNotesList.class.getResource("xml/DespatchNotesListReport.xml"));
        report.addTableModel("cashflow.xmlDespatchNotesList",params);
        report.setProperty("startdate",tfStartDate.getString().equals("")?"--":tfStartDate.getString());
        report.setProperty("enddate",tfEndDate.getString().equals("")?"--":tfEndDate.getString());
        report.setProperty("provider",provider);
        report.doReport();
    }

    private void btnDetailReport_actionPerformed(ActionEvent e) {
        if (detailListPanel.getTable().getRowCount() > 0) {
            String params = "" + voucherSelected.getIdVoucher();
            BasicReport report = new BasicReport(DespatchNotesList.class.getResource("xml/DespatchNotesDetailReport.xml"));
            report.addTableModel("cashflow.xmlDespatchNoteDetail",params);
            report.setProperty("provider",dataRow.get(5).toString());
            report.setProperty("number",voucherSelected.getNumber());
            report.setProperty("voucherdate",Proced.setFormatDate(voucherSelected.getDate(),true));
            report.doReport();
        }
        else {
            Advisor.messageBox("No se pudo generar el Reporte porque no hay datos en la Grilla inferior","Mensaje");
        }
    }

    public void setParentInternalFrame(ExtendedInternalFrame _) {
	super.setParentInternalFrame(_);
	getParentInternalFrame().setInfo("Puede buscar Remitos por rangos de fechas y/o por Proveedor, y con un click ver su detalle");
    }

}
