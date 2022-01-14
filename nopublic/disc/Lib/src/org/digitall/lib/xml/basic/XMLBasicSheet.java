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
 * XMLBasicSheet.java
 *
 * */
package org.digitall.lib.xml.basic;

import java.util.Vector;

import org.digitall.lib.xml.basic.XMLDocumentProperty;

public class XMLBasicSheet {

    public Vector<Integer> rowBreaks = new Vector();
    private String sheetName = "unnamed";

    public String writeSheet() {
	rowBreaks.removeAllElements();
	StringBuffer bodyLines = new StringBuffer();
	bodyLines.append("<!-- Inicio del cuerpo -->");
	bodyLines.append("<Worksheet ss:Name=\"" + sheetName + "\">");
	bodyLines.append(tableInit());
	bodyLines.append(tableColumnSizes());
	bodyLines.append(sheetHeader());
	bodyLines.append(tableHeader());
	bodyLines.append(tableBody());
	bodyLines.append(tableTail());
	bodyLines.append(docProperties());
	bodyLines.append(pageBreaks());
	bodyLines.append("  </Worksheet>");
	return bodyLines.toString();
    }

    public String tableInit() {
	StringBuffer tableInitString = new StringBuffer();
	tableInitString.append("<!-- Inicio de la tabla -->");
	tableInitString.append(" <Table>");
	return tableInitString.toString();
    }

    //Anchos de las columnas
    public String tableColumnSizes() {
	return "";
    }

    //Encabezado de la hoja
     /**
      * Ejemplo:
      *  append("         <Row>");
      *  append("            <Cell ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Reporte Basico</Data></Cell>");
      *  append("         </Row>");
      * */
      public String sheetHeader() {
	return "";
    }

    //Encabezado de la tabla
    /**
     * Ejemplo:
     * 	append("         <Row>");
     *  append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Recurso</Data></Cell>");
     *  append("         </Row>");
     * */
    public String tableHeader() {
	return "";
    }

    //Cuerpo de la hoja
    /**
     * Ejemplo:
     * ResultSet resul = LibSQL.exFunction("function",params);
     * try {
     * while (resul.next()) {
     * append("          <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + resul.getString("column") + "</Data></Cell>");
     * }
     * catch (SQLException minX) {
     * x.printStackTrace();
     * }
     */
    public String tableBody() {
	return "";
    }

    public String tableTail() {
	StringBuffer tableTailString = new StringBuffer();
	tableTailString.append("  </Table>");
	tableTailString.append("  <!-- Fin de la tabla -->");
	return tableTailString.toString();
    }

    public String docProperties() {
	return XMLDocumentProperty.getBasicWorkSheetOptionsFinal();
    }

    public String pageBreaks() {
	StringBuffer breaks = new StringBuffer();
	if (rowBreaks.size() > 0) {
	    breaks.append("<PageBreaks xmlns=\"urn:schemas-microsoft-com:office:excel\">");
	    breaks.append("     <RowBreaks>");
	    for (int i = 0; i < rowBreaks.size(); i++) {
		breaks.append("         <RowBreak>");
		breaks.append("                 <Row>" + (rowBreaks.get(i) - 2) + "</Row>");
		//Hay que restarle 2 filas para que el salto de Paginas quede correcto!!
		breaks.append("         </RowBreak>");
	    }
	    breaks.append("     </RowBreaks>");
	    breaks.append("</PageBreaks>");
	}
	return breaks.toString();
    }


    public String writeBodyTable2() {
	StringBuffer filaTabla = new StringBuffer();
	filaTabla.append("         <Row>");
	filaTabla.append("            <Cell ss:StyleID=\"s38\"><Data ss:Type=\"String\">data</Data></Cell>");
	filaTabla.append("         </Row>");
	return filaTabla.toString();
    }

    public void setSheetName(String sheetName) {
	this.sheetName = sheetName;
    }

    public String getSheetName() {
	return sheetName;
    }

}
