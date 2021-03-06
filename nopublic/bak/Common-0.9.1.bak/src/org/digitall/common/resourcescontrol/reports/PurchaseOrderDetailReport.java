package org.digitall.common.resourcescontrol.reports;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.common.resourcescontrol.classes.PurchaseOrder;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLBasicBook;
import org.digitall.lib.xml.basic.XMLBasicSheet;

public class PurchaseOrderDetailReport extends XMLBasicBook {

    private double totalNeto = 0;
    private String street = "";
    private int streetNumber = 0;
    private String phone = "";
    //OBJECTS
    private PurchaseOrder purchaseOrder;


    public PurchaseOrderDetailReport(PurchaseOrder _purchaseOrder) {
	purchaseOrder = _purchaseOrder;
	bookName = "O.C. Nº " + purchaseOrder.getNumber();
	String params = String.valueOf(purchaseOrder.getProvider().getIdProvider());
	ResultSet address = LibSQL.exFunction("org.getCompanyAddressByDefault",params);
	try  {
	    if (address.next())  {
		street = address.getString("street");
		streetNumber = address.getInt("number");
		phone = address.getString("phone");
	    }
		    
	} catch (Exception ex)  {
	    ex.printStackTrace();
	} 
	doReportBody();
	doReport();
    }
    
    private String mainColumnSizes() {
	StringBuffer mainColumnSizesString = new StringBuffer();
	mainColumnSizesString.append( "      <Column ss:AutoFitWidth=\"0\" ss:Width=\"180\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"50\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"120\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"75\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"75\"/>\n");
	return mainColumnSizesString.toString();
    }

    private String mainHeader() {
	StringBuffer mainHeaderString = new StringBuffer();
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"4\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha: "+ Proced.setFormatDate(purchaseOrder.getDate(),true) +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"4\" ss:StyleID=\"sDefaultLetterRight\"><Data ss:Type=\"String\">Módulo: Orden de Compra</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"4\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Orden de Compra Nro: " + purchaseOrder.getNumber() + "</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row ss:Index=\"4\">\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"4\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Prooveedor: "+ purchaseOrder.getProvider().getName() +"</Data></Cell>\n"); 
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	if (streetNumber == 0)  {
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Direccion: " + street + "</Data></Cell>\n");
	} else{
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Direccion: " + street + " Nº " + streetNumber + "</Data></Cell>\n");        
	}
	mainHeaderString.append("                <Cell ss:MergeAcross=\"1\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Telefono: " + phone + "</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	
	return mainHeaderString.toString();
    }

    private String mainContentHeader() {
	StringBuffer mainContentHeaderString = new StringBuffer();
	mainContentHeaderString.append("         <Row ss:Index=\"7\">\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Recurso</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Cantidad</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Marca</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">($) Precio Unitario</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">($) Costo</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	return mainContentHeaderString.toString();
    }

    private String mainContent() {
	 totalNeto = 0;
	 String params = String.valueOf(purchaseOrder.getIdPurchaseOrder());
	 ResultSet purchaseOrderDetail = LibSQL.exFunction("resourcescontrol.xmlGetPurchaseOrderDetail",params);
	 StringBuffer mainContentString = new StringBuffer();
	 try {
	     while (purchaseOrderDetail.next()) {
	         mainContentString.append("         <Row>\n");
	         mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + purchaseOrderDetail.getString("resourcename") + "</Data></Cell>\n");
	         mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + purchaseOrderDetail.getDouble("approvedqty") + "</Data></Cell>\n");
	         mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + purchaseOrderDetail.getString("brandname") + "</Data></Cell>\n");
	         mainContentString.append("            <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + purchaseOrderDetail.getDouble("price") + "</Data></Cell>\n");
	         mainContentString.append("            <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + purchaseOrderDetail.getDouble("amount") + "</Data></Cell>\n");
	         mainContentString.append("         </Row>\n");
	         totalNeto = totalNeto + purchaseOrderDetail.getDouble("amount");
	     }
	     mainContentString.append("         <Row>\n");
	     mainContentString.append("            <Cell ss:StyleID=\"totalText\" ss:MergeAcross=\"3\" ><Data ss:Type=\"String\">Total</Data></Cell>\n");
	     mainContentString.append("            <Cell ss:StyleID=\"totalNumberYellow\"><Data ss:Type=\"Number\">" + totalNeto + "</Data></Cell>\n");
	     mainContentString.append("         </Row>\n");
	     mainContentString.append("         <Row>\n");
	     mainContentString.append("         </Row>\n");
	     mainContentString.append("         <Row>\n");
	     mainContentString.append("         </Row>\n");
	     mainContentString.append("         <Row>\n");
	     mainContentString.append("         </Row>\n");
	     mainContentString.append("         <Row>\n");
	     mainContentString.append("            <Cell ss:StyleID=\"firma\"><Data ss:Type=\"String\">Firma</Data></Cell>\n");
	     mainContentString.append("         </Row>\n");
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
	_mainSheet.setSheetName("Orden de Compra");
	sheets.addElement(_mainSheet);
    }
}
