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
 * PaymentOrderCheckList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.account;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Vector;

import org.digitall.common.cashflow.classes.BankAccount;
import org.digitall.common.cashflow.classes.Check;
import org.digitall.common.cashflow.classes.PaymentOrder;
import org.digitall.common.cashflow.classes.PaymentOrderCheck;
import org.digitall.common.cashflow.interfaces.account.ChecksMgmt;
import org.digitall.common.cashflow.interfaces.account.PaymentsOrdersMain;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.AssignButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PaymentOrderCheckList extends BasicPrimitivePanel{
    private int[] sizeColumnList = {99, 92, 90, 81, 131, 107};
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Cheques", dataRow);
    private Vector headerList = new Vector();
    private CBInput cbChecks = new CBInput(0, "Checks");
    private PaymentOrderCheck paymentOrderCheck;
    private TFInput tfFindCheck = new TFInput(DataTypes.STRING, "FindCheck", false);
    private ChecksMgmt checksMgmt;
    private double amountChecks;
    private BasicPanel checkPanel = new BasicPanel("Cheques Asociados");
    private AssignButton btnAssign = new AssignButton(true);
    private PaymentOrder paymentOrder;
    private PaymentsOrdersMain parentMain;
    
    public PaymentOrderCheckList(PaymentsOrdersMain _parentMain) {
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
	setTitle("Cheques Asociados");
	checkPanel.setBounds(new Rectangle(3, 160, 645, 290));
	checkPanel.setLayout(null);
	checkPanel.setPreferredSize(new Dimension(866, 80));
	cbChecks.setBounds(new Rectangle(245, 25, 255, 35));
	listPanel.setBounds(new Rectangle(5, 95, 650, 220));
	tfFindCheck.setBounds(new Rectangle(120, 25, 110, 35));	
	btnAssign.setBounds(new Rectangle(510, 35, 30, 25));
	btnAssign.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAssign_actionPerformed(e);
				 }

			     }
	);
	checkPanel.add(tfFindCheck, null);
	checkPanel.add(btnAssign, null);
	checkPanel.add(cbChecks, null);
	tfFindCheck.getTextField().addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
					    if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
						loadComboCheck();
					    }
					}
				    }
	);
	this.add(checkPanel, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	
	cbChecks.autoSize();
	setHeaderList();
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Number"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement(Environment.lang.getProperty("ExpirationDate"));
	headerList.addElement(Environment.lang.getProperty("Amount"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Recipient"));
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("AccountNumber"));
	headerList.addElement("*");
	
	
	listPanel.getTable().addMouseListener(new MouseAdapter(){
	    public void mouseClicked(MouseEvent me) {
		loadObject();
		if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 2) {
		    int index = listPanel.getTable().rowAtPoint(me.getPoint());
		    listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
		    
		} else if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 2) {
		    loadCheckMgmt();
		}
	    }
	
	});
	
	    listPanel.setParams("cashflow.getAllPaymentOrderChecks", "-1", headerList);
    }

    public void refresh() {
	String params = ""+ paymentOrder.getIdPaymentOrder();
	listPanel.refresh(params);
	loadComboCheck();
    }
    
    private void loadObject(){
	if (!dataRow.isEmpty()) {
	    paymentOrderCheck = new PaymentOrderCheck();
	    paymentOrderCheck.setPaymentOrder(new PaymentOrder(Integer.parseInt(""+ dataRow.elementAt(0))));
	    cbChecks.setSelectedID(dataRow.elementAt(1));
	    
	    Check check = new Check(Integer.parseInt(""+ dataRow.elementAt(1)));
	    check.setLetter(""+ dataRow.elementAt(2));
	    check.setNumber(Integer.parseInt(""+ dataRow.elementAt(3)));    
	    check.setAccount(new BankAccount(Integer.parseInt(""+ dataRow.elementAt(5))));
	    check.setDate(Proced.setFormatDate(""+ dataRow.elementAt(6), false));
	    check.setExpiredDate(Proced.setFormatDate(""+ dataRow.elementAt(7), false));
	    check.setAmount(Double.parseDouble(""+ dataRow.elementAt(8)));
	    check.setIdCheckType(Integer.parseInt(""+ dataRow.elementAt(9)));
	    check.setIdEntity(Integer.parseInt(""+ dataRow.elementAt(11)));
	    check.setIdEntitytype(Integer.parseInt(""+ dataRow.elementAt(13)));
	    check.setIdCheckStatus(Integer.parseInt(""+ dataRow.elementAt(15)));
	    check.setDescription(""+ dataRow.elementAt(17));
	    
	    paymentOrderCheck.setCheck(check);
	    
	}
    }
    
    private void loadComboCheck(){
	try {
	    String param = "'" + tfFindCheck.getString() + "',0";
	    if (paymentOrder != null){
		param += paymentOrder.getIdPaymentOrder();
	    }
	    ResultSet rs = LibSQL.exFunction("cashflow.getAllChecksByFilter", param);
	    rs.last();
	    if(rs.getRow()>0){
		rs.first();
		cbChecks.loadJCombo(rs);    
	    } else {
		Advisor.messageBox("No existen cheques disponibles", "No existen cheques disponibles");
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    private void loadCheckMgmt(){
	checksMgmt = new ChecksMgmt();
	checksMgmt.setPaymentOrderCheckList(this);	
	checksMgmt.setCheck(paymentOrderCheck.getCheck());
	checksMgmt.setAvailableAmount(amountChecks + Double.parseDouble(""+ dataRow.elementAt(8)));
	
	ExtendedInternalFrame checksMgmtContainer = new ExtendedInternalFrame("Modificar datos del Cheque Nº"+ paymentOrderCheck.getCheck().getNumber());
	checksMgmtContainer.setCentralPanel(checksMgmt);
	checksMgmtContainer.show();
    }
    
    private void btnAssign_actionPerformed(ActionEvent e) {
	if (!cbChecks.getSelectedValue().equals("-1") && (Double.parseDouble(""+ cbChecks.getSelectedValueRef()) <= amountChecks)){
	    if (paymentOrderCheck == null){
		paymentOrderCheck = new PaymentOrderCheck();
	    }
	    paymentOrderCheck.setPaymentOrder(paymentOrder);
	    Check check = new Check(Integer.parseInt(""+ cbChecks.getSelectedValue()));
	    check.retrieveData();
	    paymentOrderCheck.setCheck(check);
	    
	    try {
		boolean valid = true;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (check.getExpiredDate() != null){
		    Date checkExpiredDate = dateFormat.parse(check.getExpiredDate());
		    if ((checkExpiredDate.before(dateFormat.parse(Environment.currentDate)))){
			valid = false;
		    }
		}
		
		if (valid){
		    if (paymentOrderCheck.getCheck().getAccount().getAmount() >= paymentOrderCheck.getCheck().getAmount()){
			if (paymentOrderCheck.saveData()>= 0){
				amountChecks -= Double.parseDouble(""+ cbChecks.getSelectedValueRef());
				Date checkDate = dateFormat.parse(check.getDate());
				if (String.valueOf(checkDate.getMonth()+1).equals(Environment.currentMonth)){
				    paymentOrderCheck.getCheck().updateStatus(Check.TO_DEBIT);
				    paymentOrderCheck.getCheck().getAccount().updateAmount(paymentOrderCheck.getCheck().getAmount());
				} else {
				    paymentOrderCheck.getCheck().updateStatus(Check.HANDED);
				}
				refresh();  
			} else {
			    Advisor.messageBox("Este cheque ya esta asociado", "Cheque no válido");    
			}
		    } else {    
			Advisor.messageBox("Cta. Cte. con Saldo insuficiente para emitir este cheque.", "Cheque no válido");
		    }
		    
		} else {
		    Advisor.messageBox("Cheque vencido.", "Cheque no válido");
		}
	    }
	    catch (ParseException ex) {
		ex.printStackTrace();
	    }
	} else {
	    if(cbChecks.getSelectedValue().equals("-1")){
		Advisor.messageBox("No existen cheques disponibles","No existen cheques disponibles");
	    } else {
		if ((paymentOrder.getAmount() - amountChecks) == 0){
		    Advisor.messageBox("El monto de pago ya se cubrio en su totalidad","Imposible agregar cheques");
		} else {
		    Advisor.messageBox("El importe del cheque debe ser menor o igual a "+ Format.money(amountChecks),"Validacion");       
		}
	    }
	}
    }
    
    public void updateAmountChecks(double _newAmountChecks) {
	amountChecks = amountChecks + Double.parseDouble(""+ dataRow.elementAt(8)) - _newAmountChecks;
    }
    
    public void setAmountChecks(double amountChecks) {
	this.amountChecks = amountChecks;
    }

    public void setPaymentOrder(PaymentOrder paymentOrder) {
	this.paymentOrder = paymentOrder;
	refresh();
    }
    
   
}
