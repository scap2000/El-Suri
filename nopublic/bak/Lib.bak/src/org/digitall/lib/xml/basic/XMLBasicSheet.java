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
