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
 * CashMovementsListReport.java
 *
 * */
package org.digitall.common.cashflow.reports.cashmovement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLBasicBook;
import org.digitall.lib.xml.basic.XMLBasicSheet;

public class CashMovementsListReport extends XMLBasicBook {

    private String params = "";
    private String startDate = "";
    private String endDate = "";
    private String cashMovement = "";
    private String costCentre = "";

    public CashMovementsListReport(String _params, String _startDate, String _endDate, String _cashMovement, String _costCentre) {
	params = _params;
	startDate = _startDate;
	endDate = _endDate;
	cashMovement = _cashMovement;
	costCentre = _costCentre;
	bookName = "Movimiento de Fondos - "+ costCentre;
	doReportBody();
	doReport();
    }
    
    private String mainColumnSizes() {
	StringBuffer mainColumnSizesString = new StringBuffer();
	mainColumnSizesString.append( "      <Column ss:AutoFitWidth=\"0\" ss:Width=\"180\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"80\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"100\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"60\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"50\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"50\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"50\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"50\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"50\"/>\n");
	return mainColumnSizesString.toString();
    }

    private String mainHeader() {
	StringBuffer mainHeaderString = new StringBuffer();
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"8\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha de Impresión: "+ Proced.setFormatDate(Environment.currentDate,true)  +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"8\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Modulo: "+ cashMovement +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"8\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Listado de Movimiento del tipo: "+ cashMovement +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row ss:Index=\"5\">\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"8\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Centro de Costos:  "+ costCentre +" </Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Fecha de inicio:  "+ startDate +" </Data></Cell>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Fecha de Fin:  "+ endDate +" </Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	return mainHeaderString.toString();
    }

    private String mainContentHeader() {
	StringBuffer mainContentHeaderString = new StringBuffer();
	mainContentHeaderString.append("         <Row ss:Index=\"8\">\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Tipo</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Centro de Costo</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Comprobante</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Fecha</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">($) Monto</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">%</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">($) Monto (proy.)</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">%</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Desvio</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	return mainContentHeaderString.toString();
    }

    private String mainContent() {
	StringBuffer mainContentString = new StringBuffer();
	ResultSet cashMovementsList = LibSQL.exFunction("cashflow.getAllCashMovements",params + ",0,0");
	try {
	    while (cashMovementsList.next()) {
		    mainContentString.append("         <Row>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + cashMovementsList.getString("cashmovementtype") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + cashMovementsList.getString("costscentre") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + cashMovementsList.getString("vouchernumber") + "</Data></Cell>\n");
		    if (cashMovementsList.getString("date") != null)  {
			mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + Proced.setFormatDate(cashMovementsList.getString("date"),true) + "</Data></Cell>\n");
		    } else  {
			mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">--</Data></Cell>\n");
		    }
		    mainContentString.append("            <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + cashMovementsList.getDouble("amount") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s37\"><Data ss:Type=\"Number\">" + cashMovementsList.getDouble("amountp") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"Number\">" + cashMovementsList.getDouble("projamount") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s37\"><Data ss:Type=\"Number\">" + cashMovementsList.getDouble("projamountp") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s37\"><Data ss:Type=\"Number\">" + cashMovementsList.getDouble("desviationamount") + "</Data></Cell>\n");
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
    _mainSheet.setSheetName("Movimientos de Fondos");
    sheets.addElement(_mainSheet);
    }
}
