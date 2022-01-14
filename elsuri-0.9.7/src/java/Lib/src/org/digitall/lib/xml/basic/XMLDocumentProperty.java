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
 * XMLDocumentProperty.java
 *
 * */
package org.digitall.lib.xml.basic;

public abstract class XMLDocumentProperty {
    
    public static String getXlsDocumentProperty(){
	StringBuffer seccion2 = new StringBuffer();
	seccion2.append("   <DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">\n");
	seccion2.append("      <Author>DIGITALL</Author>\n");
	seccion2.append("      <LastAuthor>DIGITALL</LastAuthor>\n");
	seccion2.append("      <Created>2007-05-07T08:12:27Z</Created>\n");
	seccion2.append("      <Company>Digitall</Company>\n");
	seccion2.append("      <Version>11.6360</Version>\n");
	seccion2.append("   </DocumentProperties>\n");
	seccion2.append("   <ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">\n");
	seccion2.append("      <WindowHeight>8700</WindowHeight>\n");
	seccion2.append("      <WindowWidth>11595</WindowWidth>\n");
	seccion2.append("      <WindowTopX>360</WindowTopX>\n");
	seccion2.append("      <WindowTopY>120</WindowTopY>\n");
	seccion2.append("      <ProtectStructure>False</ProtectStructure>\n");
	seccion2.append("      <ProtectWindows>False</ProtectWindows>\n");
	seccion2.append("   </ExcelWorkbook>\n");
	return seccion2.toString();
    }
    
    public static String getBasicWorkSheetOptions(){
	StringBuffer seccion4 = new StringBuffer();
	seccion4.append("  <WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">\n");
	seccion4.append("     <PageSetup>\n");
	seccion4.append("        <Header x:Margin=\"0\"/>\n");
	seccion4.append("        <Footer x:Margin=\"0\"/>\n");
	seccion4.append("        <PageMargins x:Bottom=\"0.98425196850393704\" x:Left=\"0.78740157480314965\" "); 
	seccion4.append("         x:Right=\"0.78740157480314965\" x:Top=\"0.98425196850393704\"/>\n");
	seccion4.append("     </PageSetup>\n");
	seccion4.append("     <Print>\n");
	seccion4.append("        <ValidPrinterInfo/>\n");
	seccion4.append("        <PaperSizeIndex>9</PaperSizeIndex>\n");
	seccion4.append("        <HorizontalResolution>300</HorizontalResolution>\n");
	seccion4.append("        <VerticalResolution>300</VerticalResolution>\n");
	seccion4.append("     </Print>\n");
	seccion4.append("        <Selected/>\n");
	seccion4.append("        <ProtectObjects>False</ProtectObjects>\n");
	seccion4.append("        <ProtectScenarios>False</ProtectScenarios>\n");
	seccion4.append("  </WorksheetOptions>\n");
	seccion4.append("  </Worksheet>\n");
	return seccion4.toString();
    }
    
    public static String getBasicWorkSheetOptionsFinal(){
	StringBuffer seccion4 = new StringBuffer();
	seccion4.append("  <WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">\n");
	seccion4.append("     <PageSetup>\n");
	seccion4.append("        <Header x:Margin=\"0\"/>\n");
	seccion4.append("        <Footer x:Margin=\"0\"/>\n");
	seccion4.append("        <PageMargins x:Bottom=\"0.98425196850393704\" x:Left=\"0.78740157480314965\" "); 
	seccion4.append("         x:Right=\"0.78740157480314965\" x:Top=\"0.98425196850393704\"/>\n");
	seccion4.append("     </PageSetup>\n");
	seccion4.append("     <Print>\n");
	seccion4.append("        <ValidPrinterInfo/>\n");
	seccion4.append("        <PaperSizeIndex>9</PaperSizeIndex>\n");
	seccion4.append("        <HorizontalResolution>300</HorizontalResolution>\n");
	seccion4.append("        <VerticalResolution>300</VerticalResolution>\n");
	seccion4.append("     </Print>\n");
	seccion4.append("        <Selected/>\n");
	seccion4.append("        <ProtectObjects>False</ProtectObjects>\n");
	seccion4.append("        <ProtectScenarios>False</ProtectScenarios>\n");
	seccion4.append("  </WorksheetOptions>\n");
	return seccion4.toString();
    }
    
    public static String getXlsDocument() {
	StringBuffer seccion1 = new StringBuffer();
	seccion1.append("<?xml version=\"1.0\"?>\n");
	seccion1.append("<?mso-application progid=\"Excel.Sheet\"?>\n");
	seccion1.append("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\" ");
	seccion1.append(" xmlns:o=\"urn:schemas-microsoft-com:office:office\" ");
	seccion1.append(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\" ");
	seccion1.append(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\" ");
	seccion1.append(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">\n");
	return seccion1.toString();
    }

}
