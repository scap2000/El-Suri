package org.digitall.lib.xml.basic;

import org.digitall.lib.xml.basic.XMLBasicSheet;

public class XMLCoverSheet extends XMLBasicSheet {

private String title = "";

    public XMLCoverSheet(String _title) {
	setSheetName("Portada");
	title = _title;
    }


    public String tableBody() {
	StringBuffer mainContentHeaderString = new StringBuffer();
	mainContentHeaderString.append("         <Row>\n");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"s30\"/>\n");
	mainContentHeaderString.append("         </Row>\n");
	appendEmptyRows(mainContentHeaderString,1);
	mainContentHeaderString.append("         <Row>\n");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"m26445296\"><Data ss:Type=\"String\">LOS DATOS QUE SE ENCUENTRAN EN ESTE REPORTE NO SON REALES</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	appendEmptyRows(mainContentHeaderString,6);
	mainContentHeaderString.append("         <Row ss:Height=\"42\">\n");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"m26444922\"><Data ss:Type=\"String\">DIGITALL DESKTOP</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	appendEmptyRows(mainContentHeaderString,1);
	mainContentHeaderString.append("         <Row ss:Height=\"20.25\">\n");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"m26444932\"><Data ss:Type=\"String\">INFORME DE EJEMPLO:</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	appendEmptyRows(mainContentHeaderString,1);
	mainContentHeaderString.append("         <Row ss:Height=\"18.75\">\n");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"m26444942\" ss:HRef=\"#'"+ title +"'!A1\"><Data ss:Type=\"String\">"+ title +"</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	appendEmptyRows(mainContentHeaderString,15);
	mainContentHeaderString.append("         <Row>\n");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"m26444902\"><Data ss:Type=\"String\">Copyrigth (c) 2008 | DIGITALL S.H. | Salta | Argentina</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	mainContentHeaderString.append("         <Row>\n");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"m26444892\"><Data ss:Type=\"String\">Tel: +54 387 431 3379 | info@digitallsh.com.ar</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	appendEmptyRows(mainContentHeaderString,16);
	mainContentHeaderString.append("         <Row>\n");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"m26444912\"><Data ss:Type=\"String\">LOS DATOS QUE SE ENCUENTRAN EN ESTE REPORTE NO SON REALES</Data></Cell>\n");
	mainContentHeaderString.append("         </Row>\n");
	appendEmptyRows(mainContentHeaderString,1);
	mainContentHeaderString.append("         <Row>\n");
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"s27\"/>\n");
	mainContentHeaderString.append("         </Row>\n");	
	return mainContentHeaderString.toString();
    }
    
    public void appendEmptyRows(StringBuffer _str, int _qty){
	for (int i = 0 ; i < _qty ; i++)  {
	    _str.append("         <Row>\n");
	    _str.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"s28\"/>\n");
	    _str.append("         </Row>\n");
	}
	
    }
}

