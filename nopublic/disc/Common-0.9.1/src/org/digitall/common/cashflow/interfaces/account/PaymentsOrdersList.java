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
 * PaymentsOrdersList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.account;

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

import org.digitall.common.cashflow.classes.EntityTypes;
import org.digitall.common.cashflow.classes.PaymentOrder;
import org.digitall.common.cashflow.reports.account.PaymentsOrdersListReport;
import org.digitall.common.cashflow.interfaces.account.PaymentsOrdersMain;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.components.buttons.ReportButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PaymentsOrdersList extends BasicPrimitivePanel {

    private int[] sizeColumnList = {94, 111, 182, 69, 142};
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Ordenes de Pagos", dataRow);
    private AddButton btnNew = new AddButton();
    private PaymentOrder paymentOrder;
    private BasicPanel dataPanel = new BasicPanel("Nueva Orden de Pago");
    private TFInput tfFindEntity = new TFInput(DataTypes.STRING, "FindEntity", false);
    private TFInput tfDate = new TFInput(DataTypes.DATE, "Date", false);
    private TFInput tfAmount = new TFInput(DataTypes.STRING, "TotalAmount", false);
    private TFInput tfNumber = new TFInput(DataTypes.INTEGER, "Number", false);
    private CBInput cbEntityType = new CBInput(CachedCombo.ENTITYTYPE, "EntityType");
    private AssignButton btnAdd = new AssignButton(true);
    private CBInput cbEntity = new CBInput(0,"Entity",false);
    private TFInput tfDescription = new TFInput(DataTypes.STRING, "Description", false);
    private FindButton btnFindEntity = new FindButton();
    private PaymentsOrdersMain parentMain;
    private ReportButton btnReport = new ReportButton();
    private CloseButton btnClose = new CloseButton();

    public PaymentsOrdersList(PaymentsOrdersMain _parentMain) {
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
	setTitle("Ordenes de Pagos");
	listPanel.setBounds(new Rectangle(105, 95, 640, 220));
	dataPanel.setLayout(null);
	dataPanel.setPreferredSize(new Dimension(640, 160));
	btnNew.setBounds(new Rectangle(565, 125, 30, 25));
	btnNew.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnNew_actionPerformed(e);
			      }

			  }
	);
	btnReport.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnReport_actionPerformed(e);
			      }

			  }
	);
	btnClose.addActionListener(new ActionListener() {

			      public void actionPerformed(ActionEvent e) {
				  btnClose_actionPerformed(e);
			      }

			  }
	);
	tfFindEntity.setBounds(new Rectangle(150, 70, 130, 35));
	tfDate.setBounds(new Rectangle(195, 30, 85, 35));
	tfAmount.setBounds(new Rectangle(525, 30, 105, 35));
	cbEntityType.setBounds(new Rectangle(15, 70, 125, 35));
	listPanel.setSize(new Dimension(640, 220));
	listPanel.setPreferredSize(new Dimension(640, 270));
	btnAdd.setBounds(new Rectangle(600, 125, 30, 25));
	btnAdd.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAdd_actionPerformed(e);
				 }

			     }
	);
	cbEntity.setBounds(new Rectangle(290, 70, 295, 35));
	tfDescription.setBounds(new Rectangle(15, 110, 510, 35));
	btnFindEntity.setBounds(new Rectangle(600, 80, 30, 25));
	dataPanel.add(tfNumber, null);
	dataPanel.add(btnFindEntity, null);
	dataPanel.add(tfDescription, null);
	dataPanel.add(cbEntity, null);
	dataPanel.add(cbEntityType, null);
	dataPanel.add(tfAmount, null);
	dataPanel.add(tfDate, null);
	dataPanel.add(tfFindEntity, null);
	dataPanel.add(btnAdd, null);
	dataPanel.add(btnNew, null);
	cbEntityType.autoSize();
	cbEntity.autoSize();
	
	setEnabledEntity(false);
	tfAmount.setEnabled(false);
	
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate, true));
	tfFindEntity.getTextField().addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
					    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
						loadComboEntity();
					    }
					}
				    }
	);
	cbEntityType.getCombo().addItemListener(new ItemListener() {
		    public void itemStateChanged (ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
			    if (cbEntityType.getSelectedValue().equals("-1")){
				setEnabledEntity(false);
				cbEntity.getCombo().removeAllItems();
			    } else {
				setEnabledEntity(true);                         
				cbEntity.getCombo().removeAllItems();
				if (paymentOrder != null){
				    if (cbEntityType.getSelectedValue().equals(""+ paymentOrder.getIdEntityType())){
				       cbEntity.getCombo().addItem(dataRow.elementAt(4).toString(), ""+ paymentOrder.getIdEntity()); 
				    }
				}
			    }
			}
		    }
		});
	this.add(dataPanel, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	this.addButton(btnClose);
	this.addButton(btnReport);
	setHeaderList();
	tfNumber.setEnabled(false);
	tfNumber.setValue("0");
	tfNumber.setBounds(new Rectangle(15, 30, 125, 35));
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Fecha");
	headerList.addElement("Nº Orden");
	headerList.addElement("*");
	headerList.addElement("Destinatario");
	headerList.addElement("*");
	headerList.addElement("Tipo");
	headerList.addElement("($) Monto");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
				 
	listPanel.getTable().addMouseListener(new MouseAdapter(){
	    public void mouseClicked(MouseEvent me) {
	        loadObject();
		if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 2) {
		    int index = listPanel.getTable().rowAtPoint(me.getPoint());
		    listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
		} else if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
		    parentMain.setSelectedTab(1);
		}
	    }
	});
	listPanel.setParams("cashflow.getallPaymentsOrders", "",headerList);
    }

    public void refresh() {
	String params = "";
	listPanel.refresh(params);
    }

    private void loadObject() {
	if (!dataRow.isEmpty()) {           
	    paymentOrder = new PaymentOrder();
	    paymentOrder.setIdPaymentOrder(Integer.parseInt(""+ dataRow.elementAt(0)));
	    paymentOrder.setDate(Proced.setFormatDate(""+ dataRow.elementAt(1), false));
	    paymentOrder.setNumber(Integer.parseInt(""+ dataRow.elementAt(2)));
	    paymentOrder.setIdEntity(Integer.parseInt(""+ dataRow.elementAt(3)));
	    paymentOrder.setIdEntityType(Integer.parseInt(""+ dataRow.elementAt(5)));
	    paymentOrder.setAmount(Double.parseDouble(""+ dataRow.elementAt(7)));
	    paymentOrder.setObservations(""+ dataRow.elementAt(8));
	    
	    loadData();
	    parentMain.getPaymentOrderDetailList().setPaymentOrder(paymentOrder);
	    parentMain.getPaymentOrderDetailList().setProviderName(dataRow.elementAt(4).toString());
	    parentMain.getPaymentsCheckList().setPaymentOrder(paymentOrder);
	    parentMain.getPaymentsCheckList().setAmountChecks(paymentOrder.getAmount() - Double.parseDouble(""+ dataRow.elementAt(10)));
	    
	    setHeaderTitle();
	}  
    }
    
    public void setHeaderTitle(){
	String title = Environment.lang.getProperty("PaymentOrderNumber") +": "+ paymentOrder.getNumber() +" - "+ Format.money(paymentOrder.getAmount());
	parentMain.setTitle(title);
    }
    
    private void setEnabledEntity(boolean _enabled){
	tfFindEntity.setEnabled(_enabled);
	cbEntity.setEnabled(_enabled);
	btnFindEntity.setEnabled(_enabled);     
    }
    
    private void loadComboEntity(){
	String param = "'" + tfFindEntity.getString() + "'";
	switch (Integer.parseInt(""+ cbEntityType.getSelectedValue()))  {
	    case EntityTypes.PERSON: 
		cbEntity.loadJCombo(LibSQL.exFunction("org.getAllPersonsByFilter", param));    
		break;
	    case EntityTypes.PROVIDER: 
		cbEntity.loadJCombo(LibSQL.exFunction("org.getAllProvidersByFilter", param));
		break;
	    default:
		break;
	}
    }
    
    private void loadData(){
	dataPanel.setTitle("Modificar Orden de Pago");
	tfAmount.setValue((paymentOrder.getAmount()));
	tfDate.setValue(Proced.setFormatDate(paymentOrder.getDate(), true));
	tfNumber.setValue(""+ paymentOrder.getNumber());
	tfDescription.setValue(paymentOrder.getObservations());
	cbEntityType.setSelectedID(""+ paymentOrder.getIdEntityType());
    }
    
    public boolean saveData(){
	if (!cbEntityType.getSelectedValue().equals("-1")){
	    if (paymentOrder == null){
		paymentOrder = new PaymentOrder();
	    }
	    paymentOrder.setDate(Proced.setFormatDate(tfDate.getString(), false));
	    paymentOrder.setObservations(tfDescription.getString());
	    paymentOrder.setIdEntity(Integer.parseInt("0"+ cbEntity.getSelectedValue()));
	    paymentOrder.setIdEntityType(Integer.parseInt("0"+ cbEntityType.getSelectedValue()));
	    paymentOrder.setNumber(Integer.parseInt("0"+ tfNumber.getString()));
	    
	    int result = -1;
	    if (paymentOrder.getIdPaymentOrder() == -1){
		result = paymentOrder.addData();
	    } else {
		result = paymentOrder.setData();
	    }
    
	    if (result>= 0){
		refresh();
		return true;
	    } else {
		return false;
	    }
	} else {
	    Advisor.messageBox("Indicar Proveedor o Persona.","Destinatario requerido");
	    return false;
	}    
    }
    
    private void btnAdd_actionPerformed(ActionEvent e) {
	saveData();
    }
    
    private void btnNew_actionPerformed(ActionEvent e) {
	dataPanel.setTitle("Nueva Orden de Pago");
	tfAmount.setValue("0");
	tfDate.setValue(Proced.setFormatDate(Environment.currentDate,true));
	tfNumber.setValue("0");
	tfDescription.setValue("");
	cbEntityType.setSelectedID("0");
	paymentOrder = null;
    }

    private void btnReport_actionPerformed(ActionEvent e) {
	if (listPanel.getTable().getRowCount() != 0)  {
	    Vector<Vector> vectorsSelected = new Vector();
	    vectorsSelected = listPanel.getSelectedsVector();
	    if (vectorsSelected.size() != 0)  {
		//Advisor.messageBox("Generando Informe de la Orden de Pago Nº"+ paymentOrder.getNumber(),"Orden de Pago");
		 new PaymentsOrdersListReport();
	    } else  {
	        Advisor.messageBox("Para poder generar el Informe, debe\nseleccionar por lo menos una Orden de Pago","Informe");
	    }
	} else  {
	    Advisor.messageBox("No se generó el Informe, porque\nno hay Ordenes de Pago en la grilla","Informe");
	}
	
    }
    
    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }    
}
