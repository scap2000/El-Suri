package org.digitall.common.cashflow.interfaces.account;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.BankAccount;
import org.digitall.common.cashflow.interfaces.account.TransactionsMgmt;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.grid.GridPanel;

//

public class TransactionsList extends BasicPrimitivePanel {

    private int[] sizeColumnList = {92,101, 89, 112, 198};
    private Vector headerList = new Vector();
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(50000, sizeColumnList, "Transacciones", dataRow);
    private BasicPanel panelTransaction = new BasicPanel();
    private TransactionsMgmt transactionsPanel = new TransactionsMgmt(null,TransactionsMgmt.TRANSACTIONLIST);
    private BankAccount account;

    public TransactionsList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(645, 425));
	panelTransaction.setLayout(null);
	transactionsPanel.setBounds(new Rectangle(10, 25, 615, 95));
	panelTransaction.setBorder(BorderPanel.getBorderPanel("Nueva Transacción", Color.red, Color.black));
	panelTransaction.setSize(new Dimension(635, 125));
	panelTransaction.setMinimumSize(new Dimension(1, 100));
	panelTransaction.setPreferredSize(new Dimension(1, 130));
	panelTransaction.add(transactionsPanel, null);
	this.add(panelTransaction, BorderLayout.NORTH);
	this.add(listPanel, BorderLayout.CENTER);
	setHeaderList();
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setClosable(true);
	getParentInternalFrame().setInfo("Las transacciones modifican directamente el saldo de la Cuenta Bancaria");
    }
    
    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Fecha");
	headerList.addElement("Nº Transacción");
	headerList.addElement("Tipo");
	headerList.addElement("($) Monto");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("Concepto");
				 
	listPanel.getTable().addMouseListener(new MouseAdapter(){
	    public void mouseClicked(MouseEvent me) {
	        
		if (me.getButton() == MouseEvent.BUTTON3 && me.getClickCount() == 2) {
		    int index = listPanel.getTable().rowAtPoint(me.getPoint());
		    listPanel.getTable().getSelectionModel().setSelectionInterval(index, index);
		    
		}
	    }
	});
	
	listPanel.setParams("cashflow.getAllTransactionByAccount", "-1,''", headerList);
    }

    public void loadList() {
	String params = account.getIDBankAccount() +  ",''";
	listPanel.refresh(params);
    }

    private void btnSearch_actionPerformed(ActionEvent e) {

    }

    public void setAccount(BankAccount account) {
	this.account = account;
	transactionsPanel.setBankAccount(account);
	transactionsPanel.setParentList(this);	
	loadList();
    }

}
