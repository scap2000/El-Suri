package org.digitall.apps.taxes092.classes;

import org.digitall.apps.taxes.interfases.feesadmin.TaxPayerList;
import org.digitall.common.reports.BasicReport;

public class Printer {

    public Printer() {
    
    }
    
    public void imprimirTodosPlanesDePagos(){
	String params = "";
	BasicReport report = new BasicReport(TaxPayerList.class.getResource("xml/PaymentPlanList.xml"));
	report.addTableModel("taxes.xmlGetAllPaymentPlan", params);
	report.doReport();	
    }
}
