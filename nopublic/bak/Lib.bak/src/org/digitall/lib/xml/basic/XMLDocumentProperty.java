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
