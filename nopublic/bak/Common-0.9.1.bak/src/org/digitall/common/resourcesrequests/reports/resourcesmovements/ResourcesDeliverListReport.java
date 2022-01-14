package org.digitall.common.resourcesrequests.reports.resourcesmovements;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.common.resourcesrequests.classes.ResourceMovements;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLBasicBook;
import org.digitall.lib.xml.basic.XMLBasicSheet;

public class ResourcesDeliverListReport extends XMLBasicBook {

    private Vector<Vector> vectors;
    private Voucher voucher;
    private Vector<Integer> rowBreaks = new Vector();
    private int rowIndex = 1;
    private ResourceMovements resourceMovements;

    public ResourcesDeliverListReport(Vector<Vector> _vectors, ResourceMovements _resourceMovements) {
	bookName = "Remitos Internos";
	vectors = _vectors;
	resourceMovements = _resourceMovements;
	doReportBody();
	doReport();
    }
	
    private String mainColumnSizes() {
	StringBuffer mainColumnSizesString = new StringBuffer();
	mainColumnSizesString.append( "      <Column ss:AutoFitWidth=\"0\" ss:Width=\"170\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"110\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"100\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"100\"/>\n");
	return mainColumnSizesString.toString();
    }

    private String mainHeader() {
	return "";
    }

    private String mainContentHeader() {
	return "";
    }

    private String mainContent() {
	StringBuffer mainContentString = new StringBuffer();
	for (int i = 0 ; i < vectors.size() ; i++ )  {
	    Vector vectorSelected = vectors.elementAt(i);
	    voucher = new Voucher(Integer.parseInt(vectorSelected.elementAt(0).toString()));
	    voucher.retrieveData();
	    //-----------
	     String headerParams = "" + voucher.getIdVoucher();
	     System.out.println("SELECT cashflow.xmlGetAllVoucherDelivery("+ headerParams +")");
	     ResultSet voucherHeader = LibSQL.exFunction("cashflow.xmlGetAllVoucherDelivery",headerParams);
	     try  {
	         if (voucherHeader.next())  {
	             mainContentString.append("         <Row ss:Index=\""+ rowIndex +"\">\n");
	             mainContentString.append("            <Cell ss:MergeAcross=\"3\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha de Impresion: "+ Proced.setFormatDate(Environment.currentDate,true)  +"</Data></Cell>\n");
	             mainContentString.append("         </Row>\n");
	             mainContentString.append("         <Row >\n");
	             mainContentString.append("            <Cell ss:MergeAcross=\"3\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Modulo: Entrega de Recursos Internos</Data></Cell>\n");
	             mainContentString.append("         </Row>\n");
	             rowIndex = rowIndex + 3;
	             mainContentString.append("         <Row ss:Index=\""+ rowIndex +"\">\n");
	             mainContentString.append("            <Cell ss:MergeAcross=\"3\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Remito Interno Nro. "+ voucherHeader.getInt("number") +"  </Data></Cell>\n");
	             mainContentString.append("         </Row>\n");
	             rowIndex = rowIndex + 2;
	             mainContentString.append("         <Row ss:Index=\""+ rowIndex +"\">\n");
	             mainContentString.append("            <Cell ss:MergeAcross=\"1\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Centro de Costos: "+ voucherHeader.getString("name") +"</Data></Cell>\n");
	             mainContentString.append("            <Cell ss:MergeAcross=\"1\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Fecha del Remito: " + Proced.setFormatDate(voucherHeader.getString("date"),true) +"</Data></Cell>\n");
	             mainContentString.append("         </Row>\n");
	             rowIndex = rowIndex + 1;
	             mainContentString.append("         <Row ss:Index=\""+ rowIndex +"\">\n");
	             mainContentString.append("            <Cell ss:MergeAcross=\"1\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Remitente: "+ resourceMovements.getDispatcher() +"</Data></Cell>\n");
	             mainContentString.append("            <Cell ss:MergeAcross=\"1\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Destinatario: " + resourceMovements.getRecipient() +"</Data></Cell>\n");
	             mainContentString.append("         </Row>\n");
	             rowIndex = rowIndex + 1;
	             mainContentString.append("         <Row ss:Index=\""+ rowIndex +"\">\n");
	             mainContentString.append("            <Cell ss:MergeAcross=\"1\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Enviado por: "+ resourceMovements.getDelivery() +"</Data></Cell>\n");
	             mainContentString.append("            <Cell ss:MergeAcross=\"1\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Recibido por: " + resourceMovements.getReceiver() +"</Data></Cell>\n");
	             mainContentString.append("         </Row>\n");
	             mainContentString.append("         <Row>\n");
	             mainContentString.append("            <Cell ss:MergeAcross=\"3\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Observaciones: "+ voucherHeader.getString("description") +"</Data></Cell>\n");
	             mainContentString.append("         </Row>\n");
	             rowIndex = rowIndex + 3;
	         }
	     } catch (Exception ex)  {
	         ex.printStackTrace();
	     }
	    //---------

	    mainContentString.append("         <Row ss:Index=\""+ rowIndex +"\">\n");
	    mainContentString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Recurso</Data></Cell>\n");
	    mainContentString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Marca</Data></Cell>\n");
	    mainContentString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Cantidad</Data></Cell>\n");
	    mainContentString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Unidad</Data></Cell>\n");
	    mainContentString.append("         </Row>\n");
	    rowIndex++;
	    String bodyParams = "" + voucher.getIdVoucher();
	    System.out.println("SELECT cashflow.xmlGetAllVoucherDetailDelivery("+ bodyParams +")");
	    ResultSet voucherDetail = LibSQL.exFunction("cashflow.xmlGetAllVoucherDetailDelivery",bodyParams);
	    try {
		while (voucherDetail.next()) {
			mainContentString.append("         <Row>\n");
			mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + voucherDetail.getString("resource") + "</Data></Cell>\n");
			mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + voucherDetail.getString("brand") + "</Data></Cell>\n");
			mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + voucherDetail.getDouble("finalqty") + "</Data></Cell>\n");
			mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + voucherDetail.getString("unit") + "</Data></Cell>\n");
			mainContentString.append("         </Row>\n");
			rowIndex++;
		}
		rowIndex = rowIndex +5;
		rowBreaks.add(rowIndex);
	    } catch (SQLException x) {
		Advisor.messageBox(x.getMessage(), "Error");
		x.printStackTrace();
	    }
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
