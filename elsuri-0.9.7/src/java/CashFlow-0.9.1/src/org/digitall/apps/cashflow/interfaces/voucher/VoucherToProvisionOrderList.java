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
 * VoucherToProvisionOrderList.java
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

import java.util.Vector;

import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.common.resourcescontrol.classes.PurchaseOrder;
import org.digitall.common.resourcesrequests.interfaces.provisionorder.ProvisionOrderMain;
import org.digitall.lib.components.Advisor;
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

public class VoucherToProvisionOrderList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 90, 90, 85, 340, 245 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Órdenes de Provisión no facturadas", dataRow) {

	@Override
	public void finishLoading() {
	    btnAssign.setEnabled(false);
	    loadSelectedObject();
	}
    };
    private AssignButton btnAssign = new AssignButton();
    private BasicPanel contentPanel = new BasicPanel();
    private BasicPanel findPanel = new BasicPanel("Buscar Órdenes de Provisión para facturar");
    private CBInput cbProvider = new CBInput(0, "Provider", false);
    private FindButton btnFind = new FindButton();
    private VoucherToProvisionOrderMain parentMain;
    private BorderLayout borderLayout1 = new BorderLayout();
    private PrintButton btnPrint = new PrintButton();
    private TFInput tfSearchProvider = new TFInput(DataTypes.STRING,"FindProvider",false);
    private PurchaseOrder selectedProvisionOrder;
    
    public VoucherToProvisionOrderList(VoucherToProvisionOrderMain _parentMain) {
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
	this.setTitle("Órdenes de Provisión no facturadas");
	listPanel.setSize(new Dimension(860, 400));
	btnAssign.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAssign_actionPerformed(e);
				 }

			     }
	);
	contentPanel.setLayout(borderLayout1);
	findPanel.setLayout(null);
	findPanel.setPreferredSize(new Dimension(860, 75));
	cbProvider.setBounds(new Rectangle(360, 25, 285, 35));
	btnFind.setBounds(new Rectangle(655, 35, 30, 25));
	btnFind.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnFind_actionPerformed(e);
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
        findPanel.add(btnFind, null);
        findPanel.add(cbProvider, null);
        contentPanel.add(findPanel, BorderLayout.NORTH);
	contentPanel.add(listPanel, BorderLayout.CENTER);
	setContent(contentPanel);
	addButton(btnAssign);
	addButton(btnPrint);
	setHeaderList();
	btnAssign.setToolTipText("Facturar las Órdenes de Provisión seleccionadas");
	btnAssign.setEnabled(false);
	btnPrint.setToolTipText("Imprimir la Orden de Provisión seleccionada");
	btnPrint.setEnabled(false);
        tfSearchProvider.setBounds(new Rectangle(215, 25, 125, 35));
	tfSearchProvider.getTextField().addKeyListener(new KeyAdapter() {

		public void keyTyped(KeyEvent e) {
		    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			loadProvidersCombo();
		    }
		}

	    });
	
        loadProvidersCombo();

	listPanel.getTable().addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			loadSelectedObject();
		    }
		}
	    });
    }

    private void setHeaderList() {
	Vector headerList = new Vector();
	headerList.removeAllElements();
	headerList.addElement("*");
        headerList.addElement(Environment.lang.getProperty("ProvisionOrderNumber"));
        headerList.addElement(Environment.lang.getProperty("Date"));
        headerList.addElement(Environment.lang.getProperty("Amount"));
	headerList.addElement("*");
	headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement(Environment.lang.getProperty("Provider"));
	headerList.addElement("*");
        headerList.addElement(Environment.lang.getProperty("CostsCentre"));
	headerList.addElement("*");
        headerList.addElement("*");
        headerList.addElement("*");
	listPanel.setParams("cashflow.getAllVoucherToProvisionOrder", "-1", headerList);
    }

    public void refresh() {
	btnAssign.setEnabled(false);
	btnPrint.setEnabled(false);
	listPanel.refresh(cbProvider.getSelectedValue());
    }

    private void loadProvidersCombo() {
        cbProvider.loadJCombo(LibSQL.exFunction("org.getAllProviderByProvisionOrders", tfSearchProvider.getStringForSQL()));
    }

    private void facturarOrdenesdeProvision() {
	String _selectedIDs = listPanel.getSelectedsIDSeparatedByComma();
	if (_selectedIDs.length() > 0) {
	    parentMain.getVoucherToProvisionOrderMgmt().setProvisionOrdersID(_selectedIDs);
	    Provider provider = new Provider();
	    provider.setIdProvider(Integer.parseInt(cbProvider.getSelectedValue().toString()));
	    parentMain.getVoucherToProvisionOrderMgmt().setProvider(provider);
	    parentMain.setSelectedTab(1);
	    parentMain.getTabbedPane().setEnabledAt(1, true);
	} else {
	    Advisor.messageBox("No seleccionó ninguna Orden de Provisión", "Error");
	}
    }

    private void btnAssign_actionPerformed(ActionEvent e) {
	facturarOrdenesdeProvision();
    }

    private void btnFind_actionPerformed(ActionEvent e) {
	refresh();
    }

    private void loadSelectedObject() {
	selectedProvisionOrder = new PurchaseOrder();
	if (!dataRow.isEmpty()) {
	    selectedProvisionOrder.setIdPurchaseOrder(Integer.parseInt(dataRow.elementAt(0).toString()));
	    selectedProvisionOrder.setNumber(dataRow.elementAt(1).toString());
	    btnPrint.setToolTipText("<html><center><u>Imprimir Orden de Provisión</u><br>Nº " + selectedProvisionOrder.getNumber() + "</center></html>");
	    btnPrint.setEnabled(true);
	} else {
	    btnPrint.setToolTipText("Imprimir la Orden de Provisión seleccionada");
	    btnPrint.setEnabled(false);
	}
	btnAssign.setEnabled(listPanel.getSelectedsID().size() > 0);
    }

    private void btnPrint_actionPerformed(ActionEvent e) {
	BasicReport report = new BasicReport(ProvisionOrderMain.class.getResource("xml/ProvisionOrder.xml"));
	report.addTableModel("resourcescontrol.xmlGetProvisionOrder", selectedProvisionOrder.getIdPurchaseOrder());
	report.addTableModel("resourcescontrol.xmlGetAllProvisionOrderDetail", selectedProvisionOrder.getIdPurchaseOrder());
	report.doReport();
    }

}

