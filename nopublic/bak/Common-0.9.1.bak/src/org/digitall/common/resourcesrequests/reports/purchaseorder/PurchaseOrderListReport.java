package org.digitall.common.resourcesrequests.reports.purchaseorder;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLBasicBook;
import org.digitall.lib.xml.basic.XMLBasicSheet;

public class PurchaseOrderListReport extends XMLBasicBook {

    private String params = "-1,'','',''";
    private String costCentre = "Todos";
    private String wordSearch = "";
    
    public PurchaseOrderListReport(String _params, String _costCentre, String _wordSearch) {
	bookName = "Ordenes de Compras ";
	params = _params;
	costCentre = _costCentre;
	wordSearch = _wordSearch;
	doReportBody();
	xmlFile = null;
	doReport();
    }
    
    public PurchaseOrderListReport(String _xmlFileName) {
	bookName = "Ordenes de Compras ";
	doReportBody();
	xmlFile = xmlFileWriter(_xmlFileName);
	doReport();
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
    
    private String mainColumnSizes() {
	StringBuffer mainColumnSizesString = new StringBuffer();
	mainColumnSizesString.append("      <Column ss:AutoFitWidth=\"0\" ss:Width=\"50\"/>\n");
	mainColumnSizesString.append("      <Column ss:Width=\"60\" />\n");
	mainColumnSizesString.append("      <Column ss:Width=\"60\"/>\n");
	mainColumnSizesString.append("      <Column ss:Width=\"60\" />\n");
	mainColumnSizesString.append("      <Column ss:Width=\"100\"/>\n");
	mainColumnSizesString.append("      <Column ss:Width=\"100\" />\n");
	mainColumnSizesString.append("      <Column ss:Width=\"130\"/>\n");
	mainColumnSizesString.append("      <Column ss:Width=\"170\"/>\n");
	return mainColumnSizesString.toString();
    }

    private String mainHeader() {
	StringBuffer mainHeaderString = new StringBuffer();
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha: " + Proced.setFormatDate(Environment.currentDate, true) + "</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Modulo: Ordenes de Compras</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Listado de Ordenes de Compras Existentes</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	if ( (!costCentre.equals("")) && (!wordSearch.equals("")) )  {
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Correspondientes al Centro de Costo '"+ costCentre +"' y filtrado por la palabra'"+ wordSearch +"'</Data></Cell>\n");
	} else if ( (!costCentre.equals("")) && (wordSearch.equals("")) )  {
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Correspondientes al Centro de Costo '"+ costCentre +"'</Data></Cell>\n");
	} else if ( (costCentre.equals("")) && (!wordSearch.equals("")) )  {
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Filtrado por la palabra '"+ wordSearch +"'</Data></Cell>\n");
	} else {
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\"></Data></Cell>\n");
	}
	mainHeaderString.append("         </Row>\n");
	return mainHeaderString.toString();
    }

    private String mainContentHeader() {
	StringBuffer mainContentHeaderString = new StringBuffer();
	mainContentHeaderString.append("         <Row ss:Index=\"6\">\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Nro. O.C.</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Nro. Pedido</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Fecha</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Costo total</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Proveedor</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Centro de Costos</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Estado</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Descripcion</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	return mainContentHeaderString.toString();
    }

    private String mainContent() {
	ResultSet PurchaseOrders = LibSQL.exFunction("resourcescontrol.xmlGetAllPurchaseOrders", params);
	StringBuffer mainContentString = new StringBuffer();
	try {
	    while (PurchaseOrders.next()) {
		mainContentString.append("         <Row>\n");
		mainContentString.append("		<Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + PurchaseOrders.getString("number") + "</Data></Cell>\n");
	        mainContentString.append("              <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + PurchaseOrders.getInt("resourcesrequestnumber") + "</Data></Cell>\n");
	        mainContentString.append("              <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + Proced.setFormatDate(PurchaseOrders.getString("date"),true) + "</Data></Cell>\n");
		mainContentString.append("		<Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + PurchaseOrders.getDouble("amount") + "</Data></Cell>\n");
		mainContentString.append("		<Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + PurchaseOrders.getString("provider") + "</Data></Cell>\n");
	        mainContentString.append("              <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + PurchaseOrders.getString("costscentre") + "</Data></Cell>\n");
	        mainContentString.append("              <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + PurchaseOrders.getString("requeststatus") + "</Data></Cell>\n");
	        mainContentString.append("              <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + PurchaseOrders.getString("description") + "</Data></Cell>\n");
		mainContentString.append("         </Row>\n");
	    }
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	    x.printStackTrace();
	}
	return mainContentString.toString();
    }

    
}