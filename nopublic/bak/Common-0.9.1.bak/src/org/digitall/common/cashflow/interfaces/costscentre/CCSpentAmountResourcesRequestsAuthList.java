package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.PrintButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.environment.Environment;

public class CCSpentAmountResourcesRequestsAuthList extends BasicPrimitivePanel {

    private int sizeColumnList[] = new int[] { 93, 147, 166, 93, 276 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Pedidos de Recursos Autorizados", dataRow);
    private Vector headerList = new Vector();
    private ExpenditureAccount.CCItem ccExpenditureAccount;
    private PrintButton btnReport = new PrintButton();

    public CCSpentAmountResourcesRequestsAuthList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	setSize(new Dimension(830, 355));
	setTitle("Pedidos de Recursos Autorizados");
	listPanel.setBounds(new Rectangle(5, 0, 820, 350));
	add(listPanel, null);
	btnReport.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
		btnReport_actionPerformed(e);
	    }
	}
	);
	this.addButton(btnReport);
	setHeaderList();
	btnReport.setToolTipText("Pedidos autorizados con detalle de gastos");
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Date"));
	headerList.addElement(Environment.lang.getProperty("ResourcesRequestNumber"));
	headerList.addElement("*");
	headerList.addElement(Environment.lang.getProperty("Solicitor"));
	headerList.addElement(Environment.lang.getProperty("TotalAmount"));
	headerList.addElement(Environment.lang.getProperty("Observations"));
	headerList.addElement("*");
	
	listPanel.setParams("cashflow.getAllCostsCentreResourcesRequestAuth", "-1, -1", headerList);
    }

    public void refresh() {
	listPanel.refresh((new StringBuilder()).append(ccExpenditureAccount.getCostCentre().getIdCostCentre()).append(",").append(ccExpenditureAccount.getIDExpenditureAccount()).toString());
    }

    public void setCCExpenditureAccount(ExpenditureAccount.CCItem ccExpenditureAccount) {
	this.ccExpenditureAccount = ccExpenditureAccount;
	btnReport.setToolTipText("<html><center><u>Pedidos autorizados con detalle de gastos</u><br>Centro de Costos: " + ccExpenditureAccount.getCostCentre().getName() + "</center></html>");
	refresh();
    }
    public void btnReport_actionPerformed(ActionEvent e){
        if (listPanel.getTable().getRowCount() != 0)  {
            BasicReport report = new BasicReport(CCSpentAmountResourcesRequestsAuthList.class.getResource("xml/ResourceRequestAuthListByCostCentre.xml"));
            String params = ""+ ccExpenditureAccount.getCostCentre().getIdCostCentre() +","+ ccExpenditureAccount.getIDExpenditureAccount();
            report.addTableModel("cashflow.xmlGetAllCostsCentreResourcesRequestAuth", params);
            report.setProperty("costcentre",ccExpenditureAccount.getCostCentre().getName());
            report.setProperty("account",ccExpenditureAccount.getName()); 
            report.doReport();
        } else  {
            Advisor.messageBox("El Reporte no se generó\nporque no hay datos en la grilla","Aviso");
        }
        
    }
}
