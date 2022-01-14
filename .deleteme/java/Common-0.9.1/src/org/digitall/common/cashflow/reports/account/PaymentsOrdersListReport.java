/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
/**
 * PaymentsOrdersListReport.java
 *
 * */
package org.digitall.common.cashflow.reports.account;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLBasicBook;
import org.digitall.lib.xml.basic.XMLBasicSheet;

public class PaymentsOrdersListReport extends XMLBasicBook {

    private String params = "";

    public PaymentsOrdersListReport() {
	this(null);
    }

    public PaymentsOrdersListReport(String _xmlFileName) {
	bookName = "Ordenes de Pago";
	doReportBody();
	xmlFile = xmlFileWriter(_xmlFileName);
	doReport();
    }

    private String mainColumnSizes() {
	StringBuffer tituloMasInicioTabla = new StringBuffer();
	tituloMasInicioTabla.append("      <Column ss:AutoFitWidth=\"0\" ss:Width=\"60\"/>");
	tituloMasInicioTabla.append("      <Column ss:Width=\"60\" />");
	tituloMasInicioTabla.append("      <Column ss:Width=\"200\"/>");
	tituloMasInicioTabla.append("      <Column ss:Width=\"100\"/>");
	tituloMasInicioTabla.append("      <Column ss:Width=\"60\"/>");
	return tituloMasInicioTabla.toString();
    }

    private String mainHeader() {
	StringBuffer filaTabla = new StringBuffer();
	filaTabla.append("         <Row>");
	filaTabla.append("            <Cell ss:MergeAcross=\"4\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha de Impresión: " + Proced.setFormatDate(Environment.currentDate, true) + "</Data></Cell>");
	filaTabla.append("         </Row>");
	filaTabla.append("         <Row>");
	filaTabla.append("            <Cell ss:MergeAcross=\"4\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Modulo: Orden de Pago</Data></Cell>");
	filaTabla.append("         </Row>");
	filaTabla.append("         <Row>");
	filaTabla.append("            <Cell ss:MergeAcross=\"4\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Ordenes de Pago</Data></Cell>");
	filaTabla.append("         </Row>");
	return filaTabla.toString();
    }

    private String mainContentHeader() {
	StringBuffer filaTabla = new StringBuffer();
	filaTabla.append("         <Row ss:Index=\"5\">");
	filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Fecha</Data></Cell>");
	filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Nro. Orden de Pago</Data></Cell>");
	filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Destinatario</Data></Cell>");
	filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Tipo</Data></Cell>");
	filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">($) Monto</Data></Cell>");
	
	filaTabla.append("         </Row>");
	return filaTabla.toString();
    }

    private String mainContent() {
	ResultSet PaymentsOrders = LibSQL.exFunction("cashflow.getallPaymentsOrders","0,0");
	StringBuffer filaTabla = new StringBuffer();
	try {
	    while (PaymentsOrders.next()) {
		filaTabla.append("        <Row>");
		filaTabla.append("    	     <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + Proced.setFormatDate(PaymentsOrders.getString("date"),true) + "</Data></Cell>");
	        filaTabla.append("           <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + PaymentsOrders.getString("number") + "</Data></Cell>");
	        filaTabla.append("           <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + PaymentsOrders.getString("entity") + "</Data></Cell>");
	        filaTabla.append("           <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + PaymentsOrders.getString("entitytype") + "</Data></Cell>");
		filaTabla.append("           <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + PaymentsOrders.getDouble("amount") + "</Data></Cell>");
		filaTabla.append("        </Row>");
	    }
	} catch (SQLException x) {
	    Advisor.messageBox(x.getMessage(), "Error");
	    x.printStackTrace();
	}
	return filaTabla.toString();
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

}
