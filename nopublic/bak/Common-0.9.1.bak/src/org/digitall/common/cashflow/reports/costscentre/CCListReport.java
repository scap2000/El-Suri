package org.digitall.common.cashflow.reports.costscentre;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLBasicBook;
import org.digitall.lib.xml.basic.XMLBasicSheet;

public class CCListReport extends XMLBasicBook {

    public CCListReport() {
	bookName = "Centros de Costos";
	doReportBody();
	doReport();
    }

    public CCListReport(String _xmlFileName) {
	bookName = "Centros de Costos";
	doReportBody();
	xmlFile = xmlFileWriter(_xmlFileName);
	doReport();
    }
    
    private String mainColumnSizes() {
	StringBuffer mainColumnSizesString = new StringBuffer();
	mainColumnSizesString.append( "      <Column ss:AutoFitWidth=\"0\" ss:Width=\"80\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"130\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"250\" />\n");
	return mainColumnSizesString.toString();
    }

    private String mainHeader() {
	StringBuffer mainHeaderString = new StringBuffer();
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha de impresión: "+ Proced.setFormatDate(Environment.currentDate,true)  +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Modulo: Centros de Costos</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Centros de Costos</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	
	return mainHeaderString.toString();
    }

    private String mainContentHeader() {
	StringBuffer mainContentHeaderString = new StringBuffer();
	mainContentHeaderString.append("         <Row ss:Index=\"5\">\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Cod</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Centro de Costo</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Descripción</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	return mainContentHeaderString.toString();
    }

    private String mainContent() {
	StringBuffer mainContentString = new StringBuffer();
	ResultSet costsCentresList = LibSQL.exFunction("cashflow.xmlGetAllCostsCentres","");
	try {
	    while (costsCentresList.next()) {
		    mainContentString.append("         <Row>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + costsCentresList.getString("code") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + costsCentresList.getString("name") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + costsCentresList.getString("description") + "</Data></Cell>\n");
		    mainContentString.append("         </Row>\n");    
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
    _mainSheet.setSheetName("Centros de Costos");
    sheets.addElement(_mainSheet);
    }
}
