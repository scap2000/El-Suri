package org.digitall.common.cashflow.reports.account;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.common.cashflow.classes.BankAccount;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLBasicBook;
import org.digitall.lib.xml.basic.XMLBasicSheet;

public class CheckListReport extends XMLBasicBook {

    private BankAccount account;
    private String params = "";
    private String title = "";

    public CheckListReport(BankAccount _account,String _title) {
	account = _account;
	title = _title;
	params ="";
	bookName = "Listado de Cheques";
	doReportBody();
	doReport();
    }
    
    private String mainColumnSizes() {
	StringBuffer mainColumnSizesString = new StringBuffer();
	mainColumnSizesString.append( "      <Column ss:AutoFitWidth=\"0\" ss:Width=\"60\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"100\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"60\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"80\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"180\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"80\"/>\n");
	return mainColumnSizesString.toString();
    }

    private String mainHeader() {
	StringBuffer mainHeaderString = new StringBuffer();
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha de Impresión: "+ Proced.setFormatDate(Environment.currentDate,true)  +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Módulo: Cuentas Bancarias</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row ss:Index=\"4\">\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">"+ title +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	
	return mainHeaderString.toString();
    }

    private String mainContentHeader() {
	StringBuffer mainContentHeaderString = new StringBuffer();
	mainContentHeaderString.append("         <Row ss:Index=\"6\">\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Nro. Cheque</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Fecha Vto.</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Importe ($)</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Tipo</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Destinatario</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Estado</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	return mainContentHeaderString.toString();
    }

    private String mainContent() {
	StringBuffer mainContentString = new StringBuffer();
	params = ""+ account.getIDBankAccount();
	ResultSet checkList = LibSQL.exFunction("cashflow.xmlGetAllChecksByBankAccount",params);
	try {
	    while (checkList.next()) {
		if (checkList.getDouble("amount") != 0)  {      //Si el monto es <> 0, entonces lo inserto en el reporte
		    mainContentString.append("         <Row>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + checkList.getInt("number") + "</Data></Cell>\n");
		    if (checkList.getString("expireddate") != null)  {
			mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + Proced.setFormatDate(checkList.getString("expireddate"),true) + "</Data></Cell>\n");
		    } else  {
			mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">--</Data></Cell>\n");
		    }
		    if (account.getIdMoneyType() == 1)  {
			mainContentString.append("            <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + checkList.getDouble("amount") + "</Data></Cell>\n");        
		    } else  {
			mainContentString.append("            <Cell ss:StyleID=\"sBorderUsaMoney\"><Data ss:Type=\"Number\">" + checkList.getDouble("amount") + "</Data></Cell>\n");        
		    }
		    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + checkList.getString("checktype") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + checkList.getString("entity") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + checkList.getString("checkstatus") + "</Data></Cell>\n");
		    mainContentString.append("         </Row>\n");    
		}
	    }
	} catch (SQLException x) {
	    org.digitall.lib.components.Advisor.messageBox(x.getMessage(), "Error");
	    x.printStackTrace();
	}
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
    _mainSheet.setSheetName(bookName);
    sheets.addElement(_mainSheet);
    }
}
