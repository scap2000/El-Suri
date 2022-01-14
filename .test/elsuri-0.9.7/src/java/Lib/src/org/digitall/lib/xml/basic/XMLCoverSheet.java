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
 * XMLCoverSheet.java
 *
 * */
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
	mainContentHeaderString.append("            <Cell ss:MergeAcross=\"6\" ss:StyleID=\"m26444902\"><Data ss:Type=\"String\">MARCELO D\'AMBROSIO E CASSINA S SOC DE HECHO | Salta | Argentina</Data></Cell>\n");
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

