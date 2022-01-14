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
 * VoucherToInvoiceList.java
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

import java.util.Vector;

import org.digitall.common.components.combos.JCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.common.resourcesrequests.interfaces.resourcesmovements.ResourcesReceiveList;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class VoucherToInvoiceList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 88, 88, 102, 570 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Notas de Recepción p/Facturar", dataRow);
    private Vector headerList = new Vector();
    private AssignButton btnAssign = new AssignButton();
    private BasicPanel content = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel();
    private CBInput cbProvider = new CBInput(0, "Provider", false);
    private FindButton btnFin = new FindButton();
    private VoucherToInvoiceMain parentMain;
    private BorderLayout borderLayout1 = new BorderLayout();
    private PrintButton btnPrint = new PrintButton();
    private TFInput tfSearchProvider = new TFInput(DataTypes.STRING,"FindProvider",false);

    public VoucherToInvoiceList(VoucherToInvoiceMain _parentMain) {
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
	this.setTitle("Notas de Recepción p/Facturar");
	listPanel.setSize(new Dimension(860, 400));
	btnAssign.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	content.setLayout(borderLayout1);
	findPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(860, 75));
	cbProvider.setBounds(new Rectangle(355, 25, 285, 35));
	btnFin.setBounds(new Rectangle(665, 35, 30, 25));
	btnFin.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnFin_actionPerformed(e);
			      }

			  }
	);
	btnPrint.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnPrint_actionPerformed(e);
				}

			    }
	);
        findPanel.add(tfSearchProvider, null);
        findPanel.add(btnFin, null);
        findPanel.add(cbProvider, null);
        content.add(findPanel, BorderLayout.NORTH);
	content.add(listPanel, BorderLayout.CENTER);
	this.add(content, null);
	addButton(btnAssign);
	addButton(btnPrint);
	cbProvider.autoSize();
	//loadComboEntity();
	setHeaderList();
	btnAssign.setEnabled(false);
	btnAssign.setToolTipText("Facturar la Nota de Recepción seleccionada");
	btnPrint.setToolTipText("Imprimir la Nota de Recepción seleccionada");
        tfSearchProvider.setBounds(new Rectangle(215, 25, 125, 35));
        tfSearchProvider.getTextField().addKeyListener(new KeyAdapter() {

                                                   public void keyTyped(KeyEvent e) {
                                                       if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
                                                           loadComboEntity();
                                                       }
                                                   }

                                               }
        );
        loadComboEntity();
        findPanel.setBorder(BorderPanel.getBorderPanel("Buscar Notas de Recepción para Facturar"));
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("DespatchNoteNumber"));
	headerList.addElement(Environment.lang.getProperty("ReceptionNoteNumber"));
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Provider"));
	headerList.addElement("*");
	headerList.addElement("*");
	//No muestro el centro de costos porque confunde el concepto
	//pues una Nota de Recepción o una Factura afectan a uno o varios Centros de Costos
	//headerList.addElement(Environment.lang.getProperty("CostsCentre")); 
	headerList.addElement("*");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
						   btnAssign.setEnabled(true);
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
						   loadResourcesToBill();
					       }
					   }

				       }
	);
	listPanel.setParams("cashflow.getallvouchertoinvoice", "-1", headerList);
    }

    public void refresh() {
	listPanel.refresh("" + cbProvider.getSelectedValue());
    }

    private void loadComboEntity() {
        String param = "'" + tfSearchProvider.getString() + "'";
        cbProvider.loadJCombo(LibSQL.exFunction("org.getAllProviderByBilling", param));
    }

    public void loadResourcesToBill() {
	if (dataRow.size() != 0) {
	    Vector values = dataRow;
	    String ids = "";
	    String idCC = "";
	    ids = values.elementAt(0).toString();
	    if (!values.elementAt(7).toString().equals(idCC) && !idCC.equals("")) {
		ids = "-1";
	    } else {
		idCC = values.elementAt(7).toString();
	    }
	    if (!ids.equals("-1")) {
		parentMain.getVoucherToInvoiceMgmt().setSelectedIdVoucher(ids);
		Provider provider = new Provider();
		provider.setName("" + cbProvider.getSelectedItem());
		provider.setIdProvider(Integer.parseInt("" + cbProvider.getSelectedValue()));
		parentMain.getVoucherToInvoiceMgmt().setProvider(provider);
		parentMain.setSelectedTab(1);
	    } else {
		Advisor.messageBox("Para registrar una factura externa\ndebe seleccionar las notas de recepción\nde un mismo Centro de Costos.", "Imposible registrar Factura");
	    }
	} else {
	    Advisor.messageBox("No seleccionó ninguna Nota de Recepción", "Error");
	}
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	loadResourcesToBill();
    }

    private void btnFin_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void btnPrint_actionPerformed(ActionEvent e) {
	if (dataRow.size() != 0) {
	    BasicReport report = new BasicReport(ResourcesReceiveList.class.getResource("xml/receptionNote.xml"));
	    int idvoucher = LibSQL.getInt("cashflow.getIdVoucher", "" + dataRow.elementAt(3));
	    report.addTableModel("cashflow.getVoucherToReceptionNote", "" + idvoucher + ",'" + cbProvider.getSelectedItem() + "',"+ cbProvider.getSelectedValue());
	    report.addTableModel("cashflow.getallvoucherdetailtoreceivenote", idvoucher + ",0,0"); 
	    report.doReport();
	} else {
	    Advisor.messageBox("Debe seleccionar una Nota de Recepción de la grilla", "Aviso");
	}
    }

}
