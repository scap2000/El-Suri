package org.digitall.apps.cashflow.reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;

import org.digitall.common.cashflow.classes.Voucher;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.common.ConfigFile;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;
import org.digitall.lib.xml.basic.XMLDocumentProperty;
import org.digitall.lib.xml.basic.XMLSheetStyles;

public class XMLSheetDespatchNoteList {

	private FileWriter xmlFile;
	private BufferedWriter log;
	private String EncabezadoReporte = "";
	private String xmlPath = "";
	private ConfigFile cfg = new ConfigFile("ddesktop.conf");
	
	private String params = "";
	private Voucher voucher;
	
	 public XMLSheetDespatchNoteList(Voucher _voucher, String _params) {
	    voucher = _voucher;
	    params = _params + ",0,0";
	    
	     /*******************************************************************/
	     // Abro el Archivo
	     xmlPath = cfg.getProperty("DespatchNote") + File.separator;
	     if (AbreArchivo(xmlPath)) {
	         write(Seccion1());
	         write(Seccion2());
	         write(Seccion3());
	         writeEncabezadoPie();
	         write(Cuerpo());
	         write(FinCuerpo());
	         write(FinSeccion1());
	         if (CierraArchivo()) {
	             Advisor.messagePopupWindow("<html><p align=center>Reporte generado con éxito<br><a href=>Click aquí para verlo</a></p></html>", xmlPath);
	         }
	     }
	     }

	private boolean AbreArchivo(String _path) {
	    JFileChooser chooser = new JFileChooser(_path);
	    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    int returnVal = chooser.showSaveDialog(chooser.getParent());
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		// IF File Selected
		try {
		    String path = chooser.getSelectedFile().getAbsolutePath();
		    if (!path.endsWith(".xls")) {
			path += ".xls";
		    }
		    cfg.setProperty("DespatchNote", chooser.getSelectedFile().getParent());
		    xmlFile = new FileWriter(path, false);
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
	    // Seccion 1: Tipo de Documento y Esquemas
	     return XMLDocumentProperty.getXlsDocument();
	}

	private String FinSeccion1() {
	    String finseccion1 = "</Workbook>\n";
	    return finseccion1;
	}

	private String Seccion2() {
	    // Seccion 2: Propiedades
	    return XMLDocumentProperty.getXlsDocumentProperty();
	    
	}

	private String Seccion3() {
	    return XMLSheetStyles.getStyles();
	}

	
	private void writeEncabezadoPie() {
	    //  Encabezado y Pie de Pagina
	    writeln("<!-- Inicio del Encabezado y Pie -->");
	    writeln("<!-- Fin del Encabezado y Pie -->");
	}

	
	private String InicioCuerpoSecundario() {
	    StringBuffer inicioCuerpo = new StringBuffer();
	    inicioCuerpo.append("<!-- Inicio del cuerpo -->");
	    inicioCuerpo.append("<Worksheet ss:Name=\"Remitos\">");
	    return inicioCuerpo.toString();
	}

	private String Seccion4Principal() {
	    //  Seccion 4: Propiedades del Documento de Excel
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
	    //  Supuestamente una tabla tiene 8644 unidades de ancho, por ende habria que hacer ancho_columna = 8644/cantidad_columnas
	    StringBuffer tituloMasInicioTabla = new StringBuffer();
	    tituloMasInicioTabla.append("<!-- Inicio de la tabla -->");
	    tituloMasInicioTabla.append(" <Table>");
	    tituloMasInicioTabla.append( "      <Column ss:AutoFitWidth=\"0\" ss:Width=\"130\"/>");
	    tituloMasInicioTabla.append( "      <Column ss:Width=\"130\" />");
	    tituloMasInicioTabla.append( "      <Column ss:Width=\"80\"/>");
	    tituloMasInicioTabla.append( "      <Column ss:Width=\"50\"/>");
	    return tituloMasInicioTabla.toString();
	}

	
	private String EncabezadoTabla() {
	    
	    StringBuffer filaTabla = new StringBuffer();
	    filaTabla.append("         <Row>");
	    filaTabla.append("            <Cell ss:MergeAcross=\"3\" ss:StyleID=\"fecha\"><Data ss:Type=\"String\">Fecha: "+ Proced.setFormatDate(Environment.currentDate,true)  +"</Data></Cell>");
	    filaTabla.append("         </Row>");
	    filaTabla.append("         <Row>");
	    filaTabla.append("            <Cell ss:MergeAcross=\"3\" ss:StyleID=\"s20\"><Data ss:Type=\"String\">Detalle del Remito Nro. "+ voucher.getNumber() +"</Data></Cell>");
	    filaTabla.append("         </Row>");
	    filaTabla.append("         <Row>");
	    filaTabla.append("            <Cell ss:MergeAcross=\"3\" ss:StyleID=\"s20\"><Data ss:Type=\"String\">Fecha del Remito: "+ Proced.setFormatDate(voucher.getDate(),true) +"</Data></Cell>");
	    filaTabla.append("         </Row>");
	    filaTabla.append("         <Row ss:Index=\"5\">");
	    filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Tipo de Gasto</Data></Cell>");
	    filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Recurso</Data></Cell>");
	    filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Marca</Data></Cell>");
	    filaTabla.append("            <Cell ss:StyleID=\"encabezado\"><Data ss:Type=\"String\">Cantidad</Data></Cell>");
	    filaTabla.append("         </Row>");
	    return filaTabla.toString();
	}

	
	private void writeCuerpoTabla() {
	    ResultSet DepatchNotes = LibSQL.exFunction("cashflow.getAllVoucherDetail",params);
	    StringBuffer filaTabla = new StringBuffer();
	    try {
		while (DepatchNotes.next()) {
		    filaTabla.append("         <Row>");
		    filaTabla.append("    	  <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + DepatchNotes.getString("name") + "</Data></Cell>");
		    filaTabla.append("            <Cell ss:StyleID=\"totalNumber\"><Data ss:Type=\"String\">" + DepatchNotes.getString("resource") + "</Data></Cell>");
		    filaTabla.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"String\">" + DepatchNotes.getString("brand") + "</Data></Cell>");
		    filaTabla.append("            <Cell ss:StyleID=\"s35\"><Data ss:Type=\"Number\">" + DepatchNotes.getDouble("finalqty") + "</Data></Cell>");
		    filaTabla.append("         </Row>");
		}
	        
		write(filaTabla.toString());
	    } catch (SQLException x) {
		org.digitall.lib.components.Advisor.messageBox(x.getMessage(), "Error");
		x.printStackTrace();
	    }
	}

	private String FinTabla() {
	    StringBuffer finTabla = new StringBuffer();
	    finTabla.append("  </Table>");
	    finTabla.append("  <!-- Fin de la tabla -->");
	    return finTabla.toString();
	}

	public String getEncabezadoReporte() {
	    return EncabezadoReporte;
	}

	public void setEncabezadoReporte(String _EncabezadoReporte) {
	    EncabezadoReporte = _EncabezadoReporte;
	}

}



