package org.digitall.common.resourcesrequests.reports.purchaseorder;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLBasicBook;
import org.digitall.lib.xml.basic.XMLBasicSheet;

public class GeneralReportOfPurchaseOrders extends XMLBasicBook {

    private String params = "";
    private String costCentre = "";
    private String wordSearch = "";
    private double amount = 0;
    private String sheetName = "";
    private Vector<Vector> purchaseOrdersList = new Vector();

    public GeneralReportOfPurchaseOrders(String _params, String _costCentre, String _wordSearch) {
	bookName = "GeneralReportOfPurchaseOrder";
	params = _params;
	costCentre = _costCentre;
	wordSearch = _wordSearch;
	doReportBody();
	xmlFile = null;
	doReport();
    }

    public GeneralReportOfPurchaseOrders(String _xmlFileName) {
	bookName = "Accounts";
	doReportBody();
	xmlFile = xmlFileWriter(_xmlFileName);
	doReport();
    }

    private String mainColumnSizes() {
	StringBuffer mainColumnSizesString = new StringBuffer();
	mainColumnSizesString.append( "      <Column ss:AutoFitWidth=\"0\" ss:Width=\"130\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"60\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"50\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"50\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"120\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"60\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"70\" />\n");
	return mainColumnSizesString.toString();
    }

    private String mainHeader() {
	StringBuffer mainHeaderString = new StringBuffer();
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha de Impresión: " + Proced.setFormatDate(Environment.currentDate, true) + "</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Modulo: Ordenes de Compras</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Listado de Ordenes de Compras Existentes</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"s23\"><Data ss:Type=\"String\"></Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	return mainHeaderString.toString();
    }

    private String mainContentHeader() {
	StringBuffer mainContentHeaderString = new StringBuffer();
	mainContentHeaderString.append("         <Row>\n");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"s23\"><Data ss:Type=\"String\"></Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	return mainContentHeaderString.toString();
    }

    private String mainContent() {
	ResultSet PurchaseOrders = LibSQL.exFunction("resourcescontrol.xmlGetAllPurchaseOrders", params);
	StringBuffer mainContentString = new StringBuffer();
	try {
	    while (PurchaseOrders.next()) {
	        mainContentString.append("         <Row>\n");
	        mainContentString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Orden de Compra Nro. "+ PurchaseOrders.getInt("number") +"</Data></Cell>\n");
	        mainContentString.append("         </Row>\n");
		mainContentString.append("         <Row>\n");
	        mainContentString.append("              <Cell ss:MergeAcross=\"2\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Centro de Costos: " + PurchaseOrders.getString("costscentre") + "</Data></Cell>\n");
	        mainContentString.append("              <Cell ss:MergeAcross=\"3\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha de la O.C.: " + Proced.setFormatDate(PurchaseOrders.getString("date"),true) + "</Data></Cell>\n");
	        mainContentString.append("         </Row>\n");
	        mainContentString.append("         <Row>\n");
	        mainContentString.append("              <Cell ss:MergeAcross=\"2\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Proveedor: " + PurchaseOrders.getString("provider") + "</Data></Cell>\n");
		mainContentString.append("              <Cell ss:MergeAcross=\"3\" ss:StyleID=\"sDefaultLetterRight\"><Data ss:Type=\"String\">Estado: " + PurchaseOrders.getString("requeststatus") + "</Data></Cell>\n");		
	        mainContentString.append("         </Row>\n");
	        mainContentString.append("         <Row>\n");
		mainContentString.append("              <Cell ss:MergeAcross=\"6\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Descripción: " + PurchaseOrders.getString("description") + "</Data></Cell>\n");
		mainContentString.append("         </Row>\n");
	        mainContentString.append("         <Row>\n");
	        mainContentString.append("              <Cell ss:MergeAcross=\"6\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\"></Data></Cell>\n");
	        mainContentString.append("         </Row>\n");
	        mainContentString.append("         <Row>\n");
	        mainContentString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Recurso</Data></Cell>\n");
	        mainContentString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Marca</Data></Cell>\n");
	        mainContentString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Cantidad</Data></Cell>\n");
	        mainContentString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Unidad</Data></Cell>\n");
	        mainContentString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Descripción</Data></Cell>\n");
	        mainContentString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">($) Precio</Data></Cell>\n");
	        mainContentString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">($) Costo</Data></Cell>\n");
	        mainContentString.append("         </Row>\n");
	        amount = PurchaseOrders.getDouble("amount");
	        String detailParams = "" + PurchaseOrders.getInt("idpurchaseorder");
	        ResultSet purchaseOrderDetail = LibSQL.exFunction("resourcescontrol.xmlgetpurchaseorderdetail",detailParams);
	        try {
	            while (purchaseOrderDetail.next()) {
	                    mainContentString.append("         <Row>\n");
	                    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + purchaseOrderDetail.getString("resourcename") + "</Data></Cell>\n");
	                    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + purchaseOrderDetail.getString("brandname") + "</Data></Cell>\n");
	                    mainContentString.append("            <Cell ss:StyleID=\"s37\"><Data ss:Type=\"Number\">" + purchaseOrderDetail.getDouble("approvedqty") + "</Data></Cell>\n");
	                    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + purchaseOrderDetail.getString("unitname") + "</Data></Cell>\n");
	                    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + purchaseOrderDetail.getString("description") + "</Data></Cell>\n");
	                    mainContentString.append("            <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + purchaseOrderDetail.getString("price") + "</Data></Cell>\n");
	                    mainContentString.append("            <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + purchaseOrderDetail.getString("amount") + "</Data></Cell>\n");
	                    mainContentString.append("         </Row>\n");
	            }
	            mainContentString.append("         <Row>\n");
	            mainContentString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"sDefaultLetterRight\"><Data ss:Type=\"String\">Total:</Data></Cell>\n");
	            mainContentString.append("            <Cell ss:StyleID=\"totalNumberYellow\"><Data ss:Type=\"Number\">"+ amount +"</Data></Cell>\n");
	            mainContentString.append("         </Row>\n");
	            mainContentString.append("         <Row>\n");
	            mainContentString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"sDefaultLetterRight\"><Data ss:Type=\"String\"></Data></Cell>\n");
	            mainContentString.append("         </Row>\n");
	            mainContentString.append("         <Row>\n");
	            mainContentString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"sDefaultLetterRight\"><Data ss:Type=\"String\"></Data></Cell>\n");
	            mainContentString.append("         </Row>\n");
	        } catch (SQLException x) {
	            org.digitall.lib.components.Advisor.messageBox(x.getMessage(), "Error");
	            x.printStackTrace();
	        }
	    }
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
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
	_mainSheet.setSheetName("Ordenes de Compras");
	sheets.addElement(_mainSheet);
	for (int i = 0 ; i < 2  ; i++ )  {
	    if (i == 1)  {
	        sheetName = "Centros de Costos";
	    } else  {
	        sheetName = "Proveedores";
	    }
	    
	    XMLBasicSheet _sheet = new XMLBasicSheet() {
    
		    public String tableColumnSizes() {
			return columnSizes();
		    }
    
		    public String sheetHeader() {
			return header();
		    }
    
		    public String tableHeader() {
			return contentHeader();
		    }
    
		    public String tableBody() {
			return content(-1, -1);
		    }
    
		}
	    ;
	    _sheet.setSheetName("accnumber");
	    sheets.addElement(_sheet);
	}
    }

    private String columnSizes() {
	//  Supuestamente una tabla tiene 8644 unidades de ancho, por ende habria que hacer ancho_columna = 8644/cantidad_columnas
	StringBuffer columnSizesString = new StringBuffer();
	columnSizesString.append("      <Column ss:AutoFitWidth=\"0\" ss:Width=\"60\"/>");
	columnSizesString.append("      <Column ss:Width=\"100\"/>");
	columnSizesString.append("      <Column ss:Width=\"60\" />");
	columnSizesString.append("      <Column ss:Width=\"80\"/>");
	columnSizesString.append("      <Column ss:Width=\"180\" />");
	columnSizesString.append("      <Column ss:Width=\"80\"/>");
	return columnSizesString.toString();
    }

    private String header() {
	StringBuffer headerString = new StringBuffer();
	headerString.append("         <Row>");
	headerString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha de Impresion: " + Proced.setFormatDate(Environment.currentDate, true) + "</Data></Cell>");
	headerString.append("         </Row>");
	headerString.append("         <Row>");
	headerString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Modulo: Cuentas Bancarias</Data></Cell>");
	headerString.append("         </Row>");
	headerString.append("         <Row>");
	headerString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Ordenes de Compras existentes, agrupados por "+ sheetName +"</Data></Cell>");
	headerString.append("         </Row>");
	headerString.append("         <Row>");
	headerString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s23\"><Data ss:Type=\"String\"></Data></Cell>");
	headerString.append("         </Row>");
	return headerString.toString();
    }

    private String contentHeader() {
	StringBuffer contentHeaderString = new StringBuffer();
	contentHeaderString.append("         <Row ss:Index=\"5\">");
	contentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Centro de Costos</Data></Cell>");
	contentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">(#) O.C.</Data></Cell>");
	contentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">($) Monto</Data></Cell>");
	contentHeaderString.append("         </Row>");  
	params = "";
	double totalAmount = 0;
	ResultSet purchaseOrders = LibSQL.exFunction("resourcescontrol.xmlGetPurchaseOrdersByCostsCentres", params);
	try {
	    while (purchaseOrders.next()) {
	        contentHeaderString.append("         <Row ss:Index=\"5\">");
	        contentHeaderString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">"+ purchaseOrders.getString("costscentre") +"</Data></Cell>");
	        contentHeaderString.append("            <Cell ss:StyleID=\"s37\"><Data ss:Type=\"String\">"+ purchaseOrders.getInt("qty") +"</Data></Cell>");
	        contentHeaderString.append("            <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"String\">"+ purchaseOrders.getDouble("amount") +"</Data></Cell>");
	        contentHeaderString.append("         </Row>");	
		totalAmount = totalAmount + purchaseOrders.getDouble("amount");
		Vector po = new Vector();
		po.addElement(purchaseOrders.getInt("idcostscentre"));
	        po.addElement(purchaseOrders.getString("costscentre"));
	        po.addElement(purchaseOrders.getInt("qty"));
	        po.addElement(purchaseOrders.getInt("amount"));
		purchaseOrdersList.add(po);
	    }
	    contentHeaderString.append("         <Row ss:Index=\"5\">");
	    contentHeaderString.append("            <Cell ss:StyleID=\"totalText\"><Data ss:Type=\"String\">Total</Data></Cell>");
	    contentHeaderString.append("            <Cell ss:StyleID=\"totalNumberYellow\"><Data ss:Type=\"Number\">"+ totalAmount +"</Data></Cell>");
	    contentHeaderString.append("         </Row>");  
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	    x.printStackTrace();
	}    
	contentHeaderString.append("         <Row ss:Index=\"5\">");
	contentHeaderString.append("            <Cell ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\"></Data></Cell>");
	contentHeaderString.append("         </Row>");  
	return contentHeaderString.toString();
    }

    private String content(int _idAccount, int _idmoney) {
	
	//Ciclo
		
	params = "" + _idAccount;
	ResultSet checkList = LibSQL.exFunction("cashflow.xmlGetAllChecksByBankAccount", params);
	StringBuffer filaTabla = new StringBuffer();
	try {
	    while (checkList.next()) {
		if (checkList.getDouble("amount") != 0) {
		    //Si el monto es <> 0, entonces lo inserto en el Reporte
		    filaTabla.append("         <Row>");
		    filaTabla.append("            <Cell ss:StyleID=\"s37\"><Data ss:Type=\"Number\">" + checkList.getInt("number") + "</Data></Cell>");
		    if (checkList.getString("expireddate") != null) {
			filaTabla.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + Proced.setFormatDate(checkList.getString("expireddate"), true) + "</Data></Cell>");
		    } else {
			filaTabla.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">--</Data></Cell>");
		    }
		    if (_idmoney == 1) {
			filaTabla.append("            <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + checkList.getDouble("amount") + "</Data></Cell>");
		    } else {
			filaTabla.append("            <Cell ss:StyleID=\"sBorderUsaMoney\"><Data ss:Type=\"Number\">" + checkList.getDouble("amount") + "</Data></Cell>");
		    }
		    filaTabla.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + checkList.getString("checktype") + "</Data></Cell>");
		    filaTabla.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + checkList.getString("entity") + "</Data></Cell>");
		    filaTabla.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + checkList.getString("checkstatus") + "</Data></Cell>");
		    filaTabla.append("         </Row>");
		}
	    }
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	    x.printStackTrace();
	}
	return filaTabla.toString();
    }

}
