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
 * ResourcesMovementsListReport.java
 *
 * */
package org.digitall.common.resourcesrequests.reports;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.common.resourcesrequests.classes.ResourceMovements;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLBasicBook;
import org.digitall.lib.xml.basic.XMLBasicSheet;

public class ResourcesMovementsListReport extends XMLBasicBook {

    private String provider= "";
    private ResourceMovements resourceMovements;

    public ResourcesMovementsListReport(ResourceMovements _resourceMovements, String _provider) {
	resourceMovements = _resourceMovements;
	provider = _provider;
	bookName = "Remitos Externos";
	doReportBody();
	doReport();
    }
    
    private String mainColumnSizes() {
	StringBuffer mainColumnSizesString = new StringBuffer();
	mainColumnSizesString.append( "      <Column ss:AutoFitWidth=\"0\" ss:Width=\"40\"/>\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"160\" />\n");
	mainColumnSizesString.append( "      <Column ss:Width=\"160\" />\n");
	mainColumnSizesString.append( "      <Column ss:Index=\"4\" ss:Width=\"80\"/>\n");
	return mainColumnSizesString.toString();
    }

    private String mainHeader() {
	StringBuffer mainHeaderString = new StringBuffer();
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha - Hora: "+ Proced.setFormatDate(Environment.currentDate,true) + " - " + Environment.currentTime.substring(0,8) + "</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Modulo: Registrar Remito</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row ss:Index=\"3\">\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s20\"><Data ss:Type=\"String\">Movimiento: "+ resourceMovements.getMovementtype() +" - Nro: "+ resourceMovements.getIdmovement() +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row ss:Index=\"4\">\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s20\"><Data ss:Type=\"String\">Deposito: "+ resourceMovements.getDepot() +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row ss:Index=\"6\">\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Proveedor: "+ provider +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row ss:Index=\"8\">\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"Default\"><Data ss:Type=\"String\">Remitente: "+ resourceMovements.getDispatcher() +"</Data></Cell>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"Default\"><Data ss:Type=\"String\">Destinatario: "+ resourceMovements.getRecipient() +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row ss:Index=\"9\">\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"Default\"><Data ss:Type=\"String\">Entregado por: "+ resourceMovements.getDelivery() +"</Data></Cell>\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"Default\"><Data ss:Type=\"String\">Recibido por: "+ resourceMovements.getReceiver() +"</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row ss:Index=\"11\">\n");
	mainHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"sLetterCenter\"><Data ss:Type=\"String\">Listado de Remitos</Data></Cell>\n");
	mainHeaderString.append("         </Row>\n");
	mainHeaderString.append("         <Row>\n");
	mainHeaderString.append("         </Row>\n");
	return mainHeaderString.toString();
    }

    private String mainContentHeader() {
	StringBuffer mainContentHeaderString = new StringBuffer();
	String despatchNotesParams = "" + resourceMovements.getIdmovement();
	System.out.println("SELECT resourcescontrol.getallresourcesmovementdetailbymovementnumber("+ despatchNotesParams +")");
	ResultSet movementsDetail = LibSQL.exFunction("resourcescontrol.getallresourcesmovementdetailbymovementnumber",despatchNotesParams);
	try  {
	    while (movementsDetail.next())  {
		mainContentHeaderString.append("         <Row>\n");
		mainContentHeaderString.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Remito Nro. " + movementsDetail.getString("number") + "</Data></Cell>\n");
		mainContentHeaderString.append("         </Row>\n");
		mainContentHeaderString.append("         <Row>\n");
		mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Ind.</Data></Cell>\n");
		mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Recurso</Data></Cell>\n");
		mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Marca</Data></Cell>\n");
		mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Cantidad</Data></Cell>\n");
		mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Unidad</Data></Cell>\n");
		mainContentHeaderString.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Ord. Cpra.</Data></Cell>\n");
		mainContentHeaderString.append("         </Row>\n");
		String despatchNotesDetailParams = ""+ movementsDetail.getInt("idvoucher");
		System.out.println("SELECT cashflow.getAllDespatchNoteDetailByNumber("+ despatchNotesDetailParams +")");
		ResultSet despatchNotesDetail = LibSQL.exFunction("cashflow.getAllDespatchNoteDetailByNumber",despatchNotesDetailParams);
		int index = 0;
		try {
		    while (despatchNotesDetail.next()) {
			index++;
			mainContentHeaderString.append("         <Row>\n");
			mainContentHeaderString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + index + "</Data></Cell>\n");
			mainContentHeaderString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + despatchNotesDetail.getString("recurso") + "</Data></Cell>\n");
			mainContentHeaderString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + despatchNotesDetail.getString("marca") + "</Data></Cell>\n");
			mainContentHeaderString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + despatchNotesDetail.getDouble("cantidad") + "</Data></Cell>\n");
			mainContentHeaderString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + despatchNotesDetail.getString("unidad") + "</Data></Cell>\n");
			mainContentHeaderString.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + despatchNotesDetail.getInt("oc") + "</Data></Cell>\n");
			mainContentHeaderString.append("         </Row>\n");
		    }
		    mainContentHeaderString.append("         <Row>\n");
		    mainContentHeaderString.append("         </Row>\n");
		} catch (SQLException x) {
		    org.digitall.lib.components.Advisor.messageBox(x.getMessage(), "Error");
		    x.printStackTrace();
		}           
	    }
	} catch (Exception ex)  {
	    ex.printStackTrace();
	}
	return mainContentHeaderString.toString();
    }

    private String mainContent() {
	return "";
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
    _mainSheet.setSheetName("Detalle del Movimiento");
    sheets.addElement(_mainSheet);
    }
}


/*
    public XMLSheetResourcesMovementsList(ResourceMovements _resourceMovements, String _provider) {
	resourceMovements = _resourceMovements;
	provider = _provider;
	bookName += getFileName();	

	// Abro el Archivo
	xmlPath = Environment.cfg.getProperty(bookName) + File.separator;

	// Abro el Archivo
	if (AbreArchivo(xmlPath)) {
	 write(Seccion1());
	   write(Seccion2());
	     write(Seccion3());
	     writeEncabezadoPie();
	     write(Cuerpo());
	   write(FinCuerpo());
	 write(FinSeccion1());
	 if (CierraArchivo()) {
	     Advisor.messagePopupWindow("<html><p align=center>Informe generado con éxito<br><a href=>Click aquí para verlo</a></p></html>", xmlPath);
	 }
	}
    }

    private String getFileName() {
	String today = Proced.setFormatDate(Environment.currentDate,true);
	today = today.replaceAll("/","-");
	String name = " ("+ today +")";
	return name;
    }

	private boolean AbreArchivo(String _path) {
	    JFileChooser chooser = new JFileChooser(_path);
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    chooser.setSelectedFile(new File(bookName + ".xls"));
	    int returnVal = chooser.showSaveDialog(chooser.getParent());
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		// IF File Selected
		try {
		    String path = chooser.getSelectedFile().getAbsolutePath();
		    if (!path.endsWith(".xls")) {
			path += ".xls";
		    }
		    xmlFile = new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8");
		    log = new BufferedWriter(xmlFile);
		    return true;
		} catch (IOException x) {
		    org.digitall.lib.components.Advisor.messageBox("Error de E/S", "Error XMLWorkBook");
		    x.printStackTrace();
		    return false;
		}
	    } else {
		return false;
	    }
	}

	private boolean CierraArchivo() {
	    try {
		xmlFile.close();
		return true;
	    } catch (IOException x) {
		org.digitall.lib.components.Advisor.messageBox("Error de E/S", "Error XMLProjectReport");
		x.printStackTrace();
		return false;
	    }
	}

	private void write(String _cadena) {
	    try {
		xmlFile.write(_cadena);
	    } catch (IOException x) {
		org.digitall.lib.components.Advisor.messageBox("Error de E/S", "Error");
		x.printStackTrace();
	    }
	}

	private void writeln(String _cadena) {
	    try {
		xmlFile.write(_cadena + "\n");
	    } catch (IOException x) {
		org.digitall.lib.components.Advisor.messageBox("Error de E/S", "Error");
		x.printStackTrace();
	    }
	}

	private String Seccion1() {
	    return XMLDocumentProperty.getXlsDocument();
	}

	private String FinSeccion1() {
	    String finseccion1 = "</Workbook>\n";
	    return finseccion1;
	}

	private String Seccion2() {
	    return XMLDocumentProperty.getXlsDocumentProperty();
	}

	private String Seccion3() {
	    return XMLSheetStyles.getStyles();
	}

	
	private void writeEncabezadoPie() {
	    writeln("<!-- Inicio del Encabezado y Pie -->\n");
	    writeln("<!-- Fin del Encabezado y Pie -->\n");
	}

	private String InicioCuerpoSecundario() {
	    StringBuffer inicioCuerpo = new StringBuffer();
	    inicioCuerpo.append("<!-- Inicio del cuerpo -->\n");
	    inicioCuerpo.append("<Worksheet ss:Name=\"Detalle del Movimiento\">\n");
	    return inicioCuerpo.toString();
	}

	private String Seccion4Principal() {
	    return XMLDocumentProperty.getBasicWorkSheetOptions();
	}

	private String Cuerpo() {
	    write(InicioCuerpoSecundario());
	    write(InicioTabla());
	    write(EncabezadoTabla());
	    writeCuerpoTabla();
	    write(FinTabla());
	    write(Seccion4Principal());
	    return "";
	}

	private String FinCuerpo() {
	    String fincuerpo = "";
	    return fincuerpo;
	}

	private String InicioTabla() {
	    StringBuffer tituloMasInicioTabla = new StringBuffer();
	    tituloMasInicioTabla.append("<!-- Inicio de la tabla -->\n");
	    tituloMasInicioTabla.append(" <Table>\n");
	    tituloMasInicioTabla.append( "      <Column ss:AutoFitWidth=\"0\" ss:Width=\"40\"/>\n");
	    tituloMasInicioTabla.append( "      <Column ss:Width=\"160\" />\n");
	    tituloMasInicioTabla.append( "      <Column ss:Width=\"160\" />\n");
	    tituloMasInicioTabla.append( "      <Column ss:Index=\"4\" ss:Width=\"80\"/>\n");
	    return tituloMasInicioTabla.toString();
	}

	
	private String EncabezadoTabla() {
	    
	    StringBuffer filaTabla = new StringBuffer();
	    filaTabla.append("         <Row>\n");
	    filaTabla.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha - Hora: "+ Proced.setFormatDate(Environment.currentDate,true) + " - " + Environment.currentTime.substring(0,8) + "</Data></Cell>\n");
	    filaTabla.append("         </Row>\n");
	    filaTabla.append("         <Row>\n");
	    filaTabla.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Modulo: Registrar Remito</Data></Cell>\n");
	    filaTabla.append("         </Row>\n");
	    filaTabla.append("         <Row ss:Index=\"3\">\n");
	    filaTabla.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s20\"><Data ss:Type=\"String\">Movimiento: "+ resourceMovements.getMovementtype() +" - Nro: "+ resourceMovements.getIdmovement() +"</Data></Cell>\n");
	    filaTabla.append("         </Row>\n");
	    filaTabla.append("         <Row ss:Index=\"4\">\n");
	    filaTabla.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s20\"><Data ss:Type=\"String\">Deposito: "+ resourceMovements.getDepot() +"</Data></Cell>\n");
	    filaTabla.append("         </Row>\n");
	    filaTabla.append("         <Row ss:Index=\"6\">\n");
	    filaTabla.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"s23\"><Data ss:Type=\"String\">Proveedor: "+ provider +"</Data></Cell>\n");
	    filaTabla.append("         </Row>\n");
	    filaTabla.append("         <Row ss:Index=\"8\">\n");
	    filaTabla.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"Default\"><Data ss:Type=\"String\">Remitente: "+ resourceMovements.getDispatcher() +"</Data></Cell>\n");
	    filaTabla.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"Default\"><Data ss:Type=\"String\">Destinatario: "+ resourceMovements.getRecipient() +"</Data></Cell>\n");
	    filaTabla.append("         </Row>\n");
	    filaTabla.append("         <Row ss:Index=\"9\">\n");
	    filaTabla.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"Default\"><Data ss:Type=\"String\">Entregado por: "+ resourceMovements.getDelivery() +"</Data></Cell>\n");
	    filaTabla.append("            <Cell ss:MergeAcross=\"2\" ss:StyleID=\"Default\"><Data ss:Type=\"String\">Recibido por: "+ resourceMovements.getReceiver() +"</Data></Cell>\n");
	    filaTabla.append("         </Row>\n");
	    filaTabla.append("         <Row ss:Index=\"11\">\n");
	    filaTabla.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"sLetterCenter\"><Data ss:Type=\"String\">Listado de Remitos</Data></Cell>\n");
	    filaTabla.append("         </Row>\n");
	    filaTabla.append("         <Row>\n");
	    filaTabla.append("         </Row>\n");
	    return filaTabla.toString();
	}

	
	private void writeCuerpoTabla() {
	    String despatchNotesParams = "" + resourceMovements.getIdmovement();
	    ResultSet movementsDetail = LibSQL.exFunction("resourcescontrol.getallresourcesmovementdetailbymovementnumber",despatchNotesParams);
	    StringBuffer filaTabla = new StringBuffer();
	    try  {
		while (movementsDetail.next())  {
		    filaTabla.append("         <Row>\n");
		    filaTabla.append("            <Cell ss:MergeAcross=\"5\" ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Remito Nro. " + movementsDetail.getString("number") + "</Data></Cell>\n");
		    filaTabla.append("         </Row>\n");
		    filaTabla.append("         <Row>\n");
		    filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Ind.</Data></Cell>\n");
		    filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Recurso</Data></Cell>\n");
		    filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Marca</Data></Cell>\n");
		    filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Cantidad</Data></Cell>\n");
		    filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Unidad</Data></Cell>\n");
		    filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Ord. Cpra.</Data></Cell>\n");
		    filaTabla.append("         </Row>\n");
		    String despatchNotesDetailParams = ""+ movementsDetail.getInt("idvoucher");
		    ResultSet despatchNotesDetail = LibSQL.exFunction("cashflow.getAllDespatchNoteDetailByNumber",despatchNotesDetailParams);
		    int index = 0;
		    try {
		        while (despatchNotesDetail.next()) {
			    index++;
		            filaTabla.append("         <Row>\n");
		            filaTabla.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + index + "</Data></Cell>\n");
		            filaTabla.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + despatchNotesDetail.getString("recurso") + "</Data></Cell>\n");
		            filaTabla.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + despatchNotesDetail.getString("marca") + "</Data></Cell>\n");
		            filaTabla.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + despatchNotesDetail.getDouble("cantidad") + "</Data></Cell>\n");
		            filaTabla.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + despatchNotesDetail.getString("unidad") + "</Data></Cell>\n");
		            filaTabla.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + despatchNotesDetail.getInt("oc") + "</Data></Cell>\n");
		            filaTabla.append("         </Row>\n");
		        }
		        filaTabla.append("         <Row>\n");
		        filaTabla.append("         </Row>\n");
		    } catch (SQLException x) {
		        org.digitall.lib.components.Advisor.messageBox(x.getMessage(), "Error");
		        x.printStackTrace();
		    }           
		}
	        write(filaTabla.toString());
	    } catch (Exception ex)  {
		ex.printStackTrace();
	    }
	        
	}

	private String FinTabla() {
	    StringBuffer finTabla = new StringBuffer();
	    finTabla.append("  </Table>\n");
	    finTabla.append("  <!-- Fin de la tabla -->\n");
	    return finTabla.toString();
	}

	public String getEncabezadoReporte() {
	    return EncabezadoReporte;
	}

	public void setEncabezadoReporte(String _EncabezadoReporte) {
	    EncabezadoReporte = _EncabezadoReporte;
	}

}

*/

