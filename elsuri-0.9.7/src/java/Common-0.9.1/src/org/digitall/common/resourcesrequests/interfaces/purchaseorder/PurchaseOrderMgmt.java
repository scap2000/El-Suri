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
 * PurchaseOrderMgmt.java
 *
 * */
package org.digitall.common.resourcesrequests.interfaces.purchaseorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.digitall.common.calendar.classes.News;
import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.reports.BasicReport;
import org.digitall.common.resourcescontrol.classes.Provider;
import org.digitall.common.resourcescontrol.classes.PurchaseOrder;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.PrintSaveButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PurchaseOrderMgmt extends BasicPrimitivePanel {

    private BasicPanel panel = new BasicPanel();
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private TFInput tfDate = new TFInput(DataTypes.SIMPLEDATE, "Date", true);
    private TFInput tfNumber = new TFInput(DataTypes.INTEGER, "Number", true);
    private TFInput tfTotalAmount = new TFInput(DataTypes.MONEY, "TotalCost", false);
    private TFInput tfProvider = new TFInput(DataTypes.STRING, "FindProvider", false);
    private CBInput cbCostsCentre = new CBInput(CachedCombo.COSTSCENTRE, "CostsCentre", false);
    private CBInput cbProvider = new CBInput(0, "Provider", false);
    private CBInput cbRequestStatus = new CBInput(CachedCombo.REQUESTSTATUS, "Status", false);
    private PrintSaveButton btnAccept = new PrintSaveButton();
    private CloseButton btnClose = new CloseButton();
    private PurchaseOrder purchaseOrder = new PurchaseOrder();
    private GeneratePurchaseOrder generatePurchaseOrder;
    private News notification;
    private int[] sizeColumnListAuth = { 165, 102, 90, 78, 80, 82 };
    private Vector detailDataRow = new Vector();
    private GridPanel detailPanel = new GridPanel(30, sizeColumnListAuth, "Materiales Autorizados", detailDataRow) {

	@Override
	public void calculate() {
	    calculateTotal();
	}
    };
    private String selectedIDs = "'-1'";

    public PurchaseOrderMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(651, 515));
	this.setPreferredSize(new Dimension(780, 381));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	panel.setLayout(null);
	panel.setPreferredSize(new Dimension(1, 150));
	panel.add(tfProvider, null);
	panel.add(cbCostsCentre, null);
	panel.add(tfDescription, null);
	panel.add(tfTotalAmount, null);
	panel.add(tfNumber, null);
	panel.add(tfDate, null);
	panel.add(cbProvider, null);
	this.addButton(btnClose);
	this.addButton(btnAccept);
	this.add(panel, BorderLayout.NORTH);
	this.add(detailPanel, BorderLayout.CENTER);
	tfTotalAmount.getTextField().setForegroundColor(Color.WHITE);
	tfTotalAmount.getTextField().setBackgroundColor(Color.RED);
	cbCostsCentre.setEnabled(false);
	tfProvider.getTextField().addKeyListener(new KeyAdapter() {

					      public void keyTyped(KeyEvent e) {
						  char caracter = e.getKeyChar();
						  if ((caracter == KeyEvent.VK_ENTER)) {
						      loadProviderCombo();
						  }
					      }

					  }
	);
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	setDetailHeaderList();
	cbRequestStatus.setEnabled(false);
        tfNumber.setEditable(false);
	tfNumber.setBounds(new Rectangle(15, 25, 85, 35));
	tfTotalAmount.setBounds(new Rectangle(530, 65, 105, 35));
	tfProvider.setBounds(new Rectangle(15, 65, 115, 35));
	btnClose.setToolTipText("Cancelar");
        btnAccept.setToolTipText("Grabar e imprimir Orden de Compra");
        panel.setBorder(BorderPanel.getBorderPanel("Nueva Orden de Compra"));
	tfDescription.setBounds(new Rectangle(15, 105, 620, 35));
	tfDate.setBounds(new Rectangle(110, 25, 100, 35));
	cbRequestStatus.setBounds(new Rectangle(440, 110, 195, 35));
	cbCostsCentre.setBounds(new Rectangle(220, 25, 415, 35));
	btnAccept.setBounds(new Rectangle(580, 172, 28, 30));
	cbProvider.setBounds(new Rectangle(140, 65, 380, 35));
	tfTotalAmount.setEditable(false);
	detailPanel.addEditableColumn(5, DataTypes.MONEY_EXTENDED);
	tfDate.setToday();
	tfNumber.setValue(purchaseOrder.getNewNumber());
	cbRequestStatus.setSelectedID("4");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Seleccione el proveedor, ajuste los precios para cada material y presione el botón \"Grabar e Imprimir\"");
    }

    private void loadProviderCombo() {
	cbProvider.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", tfProvider.getStringForSQL()));
    }

    
    private void btnAccept_actionPerformed(ActionEvent e) {
	if (validateData()) {
	    if (saveData()) {
		generatePurchaseOrder.refresh();
		getParentInternalFrame().close();
	    }
	}
    }

    public boolean saveData() {
	boolean returns = false;
	if (Advisor.question("Grabar Orden de Compra", "¿Está seguro de grabar la Orden de Compra por un monto de " + tfTotalAmount.getTextField().getText() + "?")) {
	    if (validateData()) {
		purchaseOrder.setNumber(String.valueOf(tfNumber.getInteger()));
		purchaseOrder.setDate(tfDate.getDate());
		purchaseOrder.setAmount(tfTotalAmount.getAmount());
		purchaseOrder.setDescription(tfDescription.getString());
		purchaseOrder.setCostsCentre(new CostsCentre(Integer.parseInt("" + cbCostsCentre.getSelectedValue())));
		purchaseOrder.setProvider(new Provider(Integer.parseInt("" + cbProvider.getSelectedValue())));
		purchaseOrder.setIdRequestStatus(Integer.parseInt("" + cbRequestStatus.getSelectedValue()));
    
		if (purchaseOrder.saveData() >= 0) {    // si se grabó el enc. ==> grabar el detalle de la Orden de Compra
		    int i = 0;
		    boolean _continue = true;
		    Vector _ids = detailPanel.getAllIDs();
		    while ((i < _ids.size()) && _continue )  {
			double _itemPrice = Double.parseDouble(detailPanel.getTable().getValueAt(i, 5).toString());
			if (LibSQL.getInt("resourcescontrol.generatePurchaseOrderDetail", _ids.elementAt(i) + "," + _itemPrice + "," + 
						    purchaseOrder.getIdPurchaseOrder() +","+ purchaseOrder.getCostsCentre().getIdCostCentre()) > 0)  {
			    i++;    
			} else{
			    Advisor.messageBox("Se produjo un error al grabar los datos del detalle de la Orden de Compra.", "Error");
			    _continue = false;
			}
		    }
		    
		    if (_continue) {
			/** registrar el movimiento y hacer las imputaciones */
			if (purchaseOrder.addBudgetMovementByPurchaseOrder() > 0) {
                                Advisor.messageBox("Se realizaron las notificaciones correspondientes,\nreferidas a la nueva Orden de Compra generada.", "Aviso");
				BasicReport report = new BasicReport(PurchaseOrderMgmt.class.getResource("xml/purchaseOrder.xml"));
				report.addTableModel("resourcescontrol.xmlGetPurchaseOrder", "" + purchaseOrder.getIdPurchaseOrder());
				report.addTableModel("resourcescontrol.xmlGetPurchaseOrderDetail", "" + purchaseOrder.getIdPurchaseOrder());
				report.setProperty("textamount",Format.NumberToText.numberToText(purchaseOrder.getAmount()) + ".-");
				report.doReport();
				returns = true;
			} else {
			    /** Borrar la Orden de Compra junto con su Detalle */
			    Advisor.messageBox("Se produjo un error al registrar los movimientos presupuestarios\nNo se pudo generar la Orden de Compra", "Error");
			    purchaseOrder.delPurchaseOrder();
			}
		    } else {
			purchaseOrder.delPurchaseOrder();
		    }
		} else {
		    Advisor.messageBox("Se produjo un error al grabar los datos del encabezado de la Orden de Compra", "Error");
		}
	    }
	}
	return returns;
    }
    
    private boolean validateData() {
	boolean result = false;
        if (tfTotalAmount.getAmount() <= 0)  {
	    Advisor.messageBox("El monto total debe ser mayor que cero", "Aviso");
        } else if (tfNumber.getInteger() <= 0) {
	    Advisor.messageBox("El Nº de la Orden de Compra no es válido", "Aviso");
        } else if (tfDate.getDate().length() <= 0) {
	    Advisor.messageBox("Debe ingresar una fecha", "Aviso");
        } else if (cbProvider.getSelectedIndex() == -1) {
	    Advisor.messageBox("Debe seleccionar un Proveedor", "Aviso");
        } else if (existenMismosRecursosConDistintosPrecios()) {
	    Advisor.messageBox("Los precios de un mismo Recurso deben ser iguales", "Aviso");
        }else {
            result = true;
        }
	return result;
    }
    
    private boolean existenMismosRecursosConDistintosPrecios(){
        boolean _hayPreciosDistintos = false;
        int i = 0;
        Vector idsResources = detailPanel.getValuesAt(2);
        Vector idsBrands = detailPanel.getValuesAt(4);
        Vector prices = detailPanel.getValuesAt(9);
        while ( i < (idsResources.size() - 1) && !_hayPreciosDistintos) {
            int _IdResourcesActual = Integer.parseInt(idsResources.elementAt(i).toString());
            int _IdBrandActual = Integer.parseInt(idsBrands.elementAt(i).toString());
            double _priceActual = Double.parseDouble(prices.elementAt(i).toString());
            int j = i + 1;
            boolean _hayRecursoConPrecioDistinto = false;
            while (!_hayRecursoConPrecioDistinto && j < idsResources.size()) {
                if ( ( _IdResourcesActual == Integer.parseInt(idsResources.elementAt(j).toString()) ) 
                       && (_IdBrandActual == Integer.parseInt(idsBrands.elementAt(j).toString()) ) 
                       && (_priceActual != Double.parseDouble(prices.elementAt(j).toString()) )) {
                    _hayRecursoConPrecioDistinto = true;
                } else {
                    j++;
                }
            }
            if (_hayRecursoConPrecioDistinto) {
                _hayPreciosDistintos = true;
            } else {
                i++;
            }
        }
        return _hayPreciosDistintos;
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setParent(GeneratePurchaseOrder _generatePurchaseOrder) {
	this.generatePurchaseOrder = _generatePurchaseOrder;
    }

    private void setDetailHeaderList() {
	Vector detailHeaderList = new Vector();
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement(Environment.lang.getProperty("Resource"));
	detailHeaderList.addElement("*");
	detailHeaderList.addElement(Environment.lang.getProperty("Brand"));
	detailHeaderList.addElement(Environment.lang.getProperty("Quantity"));
	detailHeaderList.addElement("*");
	detailHeaderList.addElement(Environment.lang.getProperty("Unit"));
	detailHeaderList.addElement(Environment.lang.getProperty("Price"));
	detailHeaderList.addElement(Environment.lang.getProperty("Cost"));
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
	detailHeaderList.addElement("*");
        detailHeaderList.addElement("*");
        detailHeaderList.addElement("*");
	detailPanel.setParams("resourcescontrol.getAllResourcesRequestDetailAuthForPurchaseOrder", "'-1'", detailHeaderList);
    }
    
    private void calculateTotal() {
	double _totalAmount = 0.0;
	for (int i = 0; i < detailPanel.getTable().getRowCount(); i++) {
	    double _price = Double.parseDouble(detailPanel.getTable().getValueAt(i, 5).toString());
	    double _qty = Double.parseDouble(detailPanel.getTable().getValueAt(i, 3).toString());
	    double _amount = _qty * _price;
	    detailPanel.getTable().setValueAt(_amount, i, 6);
	    _totalAmount += _amount;
	}
	tfTotalAmount.setValue(_totalAmount);
    }

    public void setSelectedIDs(String _selectedIDs) {
	this.selectedIDs = _selectedIDs;
	//tfTotalAmount.setValue(resourcesRequest.getEstimatedAmountAuth());
	tfTotalAmount.setValue(LibSQL.getDouble("resourcescontrol.getresourcesrequestauthdetailtotalamount", "'" + selectedIDs + "'"));
	detailPanel.refresh("'" + selectedIDs + "'");
    }
    
    public void setIDCostsCentre(int _idCostsCentre) {
	cbCostsCentre.setSelectedID(_idCostsCentre);
    }
    
}
