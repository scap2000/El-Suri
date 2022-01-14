package org.digitall.common.cashflow.reports.account;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.common.cashflow.classes.PaymentOrder;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLBasicBook;
import org.digitall.lib.xml.basic.XMLBasicSheet;

public class PaymentOrderDetailListReport extends XMLBasicBook {

    private String params = "";
    private PaymentOrder paymentOrder;

    /*public PaymentOrderCheckListReport() {
	this(null);
    }*/

    public PaymentOrderDetailListReport(PaymentOrder _paymentOrder) {
	paymentOrder = _paymentOrder;
	bookName = "Ordenes de Pago Nº "+ paymentOrder.getNumber();
	doReportBody();
	//xmlFile = xmlFileWriter(_xmlFileName);
	doReport();
    }

    private String mainColumnSizes() {
	StringBuffer tituloMasInicioTabla = new StringBuffer();
	tituloMasInicioTabla.append("      <Column ss:AutoFitWidth=\"0\" ss:Width=\"100\"/>");
	tituloMasInicioTabla.append("      <Column ss:Width=\"60\" />");
	tituloMasInicioTabla.append("      <Column ss:Width=\"200\"/>");
	tituloMasInicioTabla.append("      <Column ss:Width=\"80\"/>");
	return tituloMasInicioTabla.toString();
    }

    private String mainHeader() {
	StringBuffer mainHeaderString = new StringBuffer();
	mainHeaderString.append("         <Row>");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha de Impresión: " + Proced.setFormatDate(Environment.currentDate, true) + "</Data></Cell>");
	mainHeaderString.append("         </Row>");
	mainHeaderString.append("         <Row>");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Modulo: Orden de Pago</Data></Cell>");
	mainHeaderString.append("         </Row>");
	mainHeaderString.append("         <Row ss:Index=\"4\">");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Orden de Pago Nro. "+ paymentOrder.getNumber() +"</Data></Cell>");
	mainHeaderString.append("         </Row>");
	return mainHeaderString.toString();
    }

    private String mainContentHeader() {
	StringBuffer mainContentHeaderString = new StringBuffer();
	mainContentHeaderString.append("         <Row ss:Index=\"6\">");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Facturas</Data></Cell>");
	mainContentHeaderString.append("         </Row>");
	mainContentHeaderString.append("         <Row ss:Index=\"7\">");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Nro. Comprobante</Data></Cell>");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Fecha</Data></Cell>");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"1\" ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Centro de Costos</Data></Cell>");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"1\" ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">($) Total</Data></Cell>");
	mainContentHeaderString.append("         </Row>");
	return mainContentHeaderString.toString();
    }

    private String mainContent() {
	params = ""+ paymentOrder.getIdPaymentOrder() +",0,0";
	ResultSet PaymentOrderDetailString = LibSQL.exFunction("cashflow.getAllPaymentOrderDetail",params);
	StringBuffer mainContentString = new StringBuffer();
	try {
	    while (PaymentOrderDetailString.next()) {
		mainContentString.append("        <Row>");
	        mainContentString.append("           <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + PaymentOrderDetailString.getString("number") + "</Data></Cell>");
		mainContentString.append("    	     <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + Proced.setFormatDate(PaymentOrderDetailString.getString("date"),true) + "</Data></Cell>");
	        mainContentString.append("           <Cell ss:MergeAcross=\"1\" ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + PaymentOrderDetailString.getString("entity") + "</Data></Cell>");
		mainContentString.append("           <Cell ss:MergeAcross=\"1\" ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + PaymentOrderDetailString.getDouble("amount") + "</Data></Cell>");
		mainContentString.append("        </Row>");
	    }
	    mainContentString.append("        <Row>");
	    mainContentString.append("        </Row>");
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	    x.printStackTrace();
	}
	
	mainContentString.append(getCheckList());
	
	return mainContentString.toString();
    }

    private void doReportBody() {

	XMLBasicSheet _mainSheet = new XMLBasicSheet() {

		public String tableColumnSizes() {
		    return mainColumnSizes();
		}

		public String sheetHeader() {
		    return mainHeader();
		}

		public String tableHeader() {
		    return mainContentHeader();
		}

		public String tableBody() {
		    return mainContent();
		}

	    }
	;
	_mainSheet.setSheetName("Ordenes de Pago");
	sheets.addElement(_mainSheet);
    }

    private String getCheckList() {
	StringBuffer checkListString = new StringBuffer();
	checkListString.append("        <Row>");
	checkListString.append("        </Row>");
	checkListString.append("        <Row>");
	checkListString.append("           <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Cheques Asociados</Data></Cell>");
	checkListString.append("        </Row>");
	checkListString.append("        <Row>");
	checkListString.append("           <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Nro.</Data></Cell>");
	checkListString.append("           <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Fecha</Data></Cell>");
	checkListString.append("           <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Destinatario</Data></Cell>");
	checkListString.append("           <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Fecha vto.</Data></Cell>");
	checkListString.append("           <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">($) Monto</Data></Cell>");
	checkListString.append("           <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Nro. cuenta</Data></Cell>");
	checkListString.append("        </Row>");

	params = ""+ paymentOrder.getIdPaymentOrder() +",0,0";
	ResultSet checkList = LibSQL.exFunction("cashflow.getAllPaymentOrderChecks",params);
	try {
	    while (checkList.next()) {
		checkListString.append("        <Row>");
		checkListString.append("           <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + checkList.getString("checknumber") + "</Data></Cell>");
		checkListString.append("           <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + Proced.setFormatDate(checkList.getString("date"),true) + "</Data></Cell>");
	        checkListString.append("           <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">"+ checkList.getString("entity") +"</Data></Cell>");
		checkListString.append("           <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + Proced.setFormatDate(checkList.getString("expireddate"),true) + "</Data></Cell>");
	        checkListString.append("           <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + checkList.getDouble("amount") + "</Data></Cell>");
	        checkListString.append("           <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + checkList.getInt("accountnumber") + "</Data></Cell>");
		checkListString.append("        </Row>");
	    }
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	    x.printStackTrace();
	}
	return checkListString.toString();
    }
    

}
