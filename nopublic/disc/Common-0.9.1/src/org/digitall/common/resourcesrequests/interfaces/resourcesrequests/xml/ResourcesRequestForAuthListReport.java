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
 * ResourcesRequestForAuthListReport.java
 *
 * */
package org.digitall.common.resourcesrequests.interfaces.resourcesrequests.xml;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLBasicBook;
import org.digitall.lib.xml.basic.XMLBasicSheet;

public class ResourcesRequestForAuthListReport extends XMLBasicBook {

    private String params = "-1,'','','',2,-1,-1";
    private String costCentre = "Todos";
    private String status = "Todos";
    private String priority = "Todas";
    private int idCostCentre = -1;
    private int idStatus = -1;
    private int idPriority = -1;


    public ResourcesRequestForAuthListReport(String _params,int _idcostCentre ,String _costCentre , int _idStatus,String _status,int _idPriority ,String _priority) {
	params = _params;
	idCostCentre = _idcostCentre;
	costCentre = _costCentre;
	idStatus = _idStatus;
	status = _status;
	idPriority = _idPriority;
	priority = _priority;
	bookName = "Pedidos realizados - " + costCentre ;
	doReportBody();
	doReport();
    }

    public ResourcesRequestForAuthListReport(String _xmlFileName) {
	bookName = "Pedidos realizados - " + costCentre ;
	doReportBody();
	xmlFile = xmlFileWriter(_xmlFileName);
	doReport();
    }
    
    private String mainColumnSizes() {
	StringBuffer mainColumnSizesString = new StringBuffer();
	mainColumnSizesString.append( "      <Column ss:AutoFitWidth=\"0\" ss:Width=\"100\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"50\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"80\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"150\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"60\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"60\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"60\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"200\" />\n");
	return mainColumnSizesString.toString();
    }

    private String mainHeader() {
	StringBuffer mainHeaderString = new StringBuffer();
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha de Impresion: "+ Proced.setFormatDate(Environment.currentDate,true)  +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row >\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Modulo: Autorización de Pedido de Recursos</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row ss:Index=\"4\">\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Pedido de Recursos Realizados</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	if (idCostCentre == -1)  {
	    mainHeaderString.append("         <Row ss:Index=\"6\">\n");
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Reporte con todos los Centros de Costos</Data></Cell>\n");
	    mainHeaderString.append("         </Row>\n");
	} else {
	    mainHeaderString.append("         <Row ss:Index=\"6\">\n");
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Reporte del Centro de Costos: "+ costCentre +"</Data></Cell>\n");
	    mainHeaderString.append("         </Row>\n");
	}
	if (idStatus == -1)  {
	    mainHeaderString.append("         <Row ss:Index=\"7\">\n");
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">referido a todos los Estados</Data></Cell>\n");
	    mainHeaderString.append("         </Row>\n");
	} else {
	    mainHeaderString.append("         <Row ss:Index=\"7\">\n");
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Referido al Estado:  "+ status +"</Data></Cell>\n");
	    mainHeaderString.append("         </Row>\n");
	}
	if (idPriority == -1)  {
	    mainHeaderString.append("         <Row ss:Index=\"8\">\n");
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Y con todas las Prioridades</Data></Cell>\n");
	    mainHeaderString.append("         </Row>\n");
	} else {
	    mainHeaderString.append("         <Row ss:Index=\"8\">\n");
	    mainHeaderString.append("            <Cell ss:MergeAcross=\"7\" ss:StyleID=\"sDefaultLetterLeft\"><Data ss:Type=\"String\">Con la prioridad: "+ priority +"</Data></Cell>\n");
	    mainHeaderString.append("         </Row>\n");
	}
	
	return mainHeaderString.toString();
    }

    private String mainContentHeader() {
	StringBuffer mainContentHeaderString = new StringBuffer();
	mainContentHeaderString.append("         <Row ss:Index=\"10\">\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Centro de Costos</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Nro. de Pedido</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Fecha</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Solicitante</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">costo</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Estado</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Prioridad</Data></Cell>\n");
	mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Observaciones</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	return mainContentHeaderString.toString();
    }

    private String mainContent() {
	ResultSet resourcesRequestList = LibSQL.exFunction("resourcescontrol.xmlGetAllResourcesRequests",params);
	StringBuffer mainContentString = new StringBuffer();
	try {
	    while (resourcesRequestList.next()) {
		    mainContentString.append("         <Row>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + resourcesRequestList.getString("costscentre") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s37\"><Data ss:Type=\"Number\">" + resourcesRequestList.getInt("number") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + Proced.setFormatDate(resourcesRequestList.getString("date"),true) + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + resourcesRequestList.getString("solicitor") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"sBorderArgMoney\"><Data ss:Type=\"String\">" + resourcesRequestList.getDouble("estimateamount") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + resourcesRequestList.getString("requeststatus") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + resourcesRequestList.getString("priorityrequest") + "</Data></Cell>\n");
		    mainContentString.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">" + resourcesRequestList.getString("description") + "</Data></Cell>\n");
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
	_mainSheet.setSheetName("Pedidos Realizados");
        sheets.addElement(_mainSheet);
    }
}
