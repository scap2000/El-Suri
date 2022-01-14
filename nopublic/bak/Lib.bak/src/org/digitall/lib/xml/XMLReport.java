package org.digitall.lib.xml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.data.Base64Coder;
import org.digitall.lib.sql.LibSQL;

//

/** FORMATOS DE TEXTO EN XML
TAMAÑO DE LETRA (48 en XML es 24 en Word, 32 en XML es 16 en Word):
  <w:r><w:rPr><w:sz w:val=\"48\"/><w:sz-cs w:val=\"48\"/></w:rPr><w:t> TEXTO </w:t></w:r>
SUBRAYADO SIMPLE:
  <w:r><w:rPr><w:u w:val=\"single\"/></w:rPr><w:t> TEXTO </w:t></w:r>
NEGRITA:
  <w:r><w:rPr><w:b/></w:rPr><w:t> TEXTO </w:t></w:r>
PARRAFO CENTRADO:
  <w:p><w:pPr><w:jc w:val=\"center\"/></w:pPr></w:p>
 */
public class XMLReport {

    private FileWriter xmlFile;
    private BufferedWriter log;
    private int anchocolumna = 0;
    private String EncabezadoReporte = "";
    private String[][] params;
    private int columnas = 0;
    private Vector EncabezadoTabla;
    private String xmlPath = "";

    private Vector getEncabezadoTabla(String _encabezado) {
	Vector encabezado = new Vector();
	while (_encabezado.length() > 0) {
	    int indice = _encabezado.indexOf("|");
	    if (indice >= 0) {
		encabezado.add(_encabezado.substring(0, indice));
		_encabezado = _encabezado.substring(indice + 1);
	    }
	}
	return encabezado;
    }

    public XMLReport(String[][] _params) {
	params = _params;
	/*
 * params = new String[2][4];
    params[0][0] = "Calles por Tipo";
    params[0][1] = "#Calle|Nombre|Tipo|";
    params[0][2] = "Select idcalle, nombre, tiposcalle.nombre as tipo from calles where upper(nombre) like 'A%' and tiposcalle.idtipo = tipo";
    params[0][3] = "Select count(*) from (" + params[0][2] + ") as foo";
    params[1][0] = "Personal de Obras Públicas";
    params[1][1] = "DNI|Apellido|Nombre|Alias|";
    params[1][2] = "select dni,apellido,nombre,alias from personas";
    params[1][3] = "Select count(*) from (" + params[1][2] + ") as foo";
*/
	// Abro el Archivo
	//xmlPath = Proced.getRuta() + File.separator + "informes" + File.separator + "Informe de " + params[0][0].replaceAll("/", "-") + ".doc";
	/** ARREGLAR LA RUTA EN LA LINEA DE ARRIBA! */
	if (AbreArchivo(xmlPath)) {
	    write(Seccion1());
	    write(Seccion2());
	    write(Seccion3());
	    write(Seccion4());
	    writeEncabezadoPie();
	    write(InicioCuerpo());
	    write(Cuerpo());
	    for (int i = 0; i < params.length; i++) {
		EncabezadoTabla = getEncabezadoTabla(params[i][1]);
		columnas = EncabezadoTabla.size();
		anchocolumna = (int)(8644 / columnas);
		writeTabla(i);
		writeln();
	    }
	    write(FinCuerpo());
	    write(FinSeccion1());
	    String Fecha = Proced.FechaHora2(true, false);
	    if (CierraArchivo()) {
		System.out.println("Archivo XML generado con éxito");
		//        Advisor.messageBoxPopupWindow("<html>Reporte generado con éxito en<br><a href=file:///" + xmlPath + ">" + xmlPath + "</a></html>");
		//        Advisor.messageBoxPopupWindow("Reporte generado con éxito");
		Advisor.messagePopupWindow("<html><p align=center>Reporte generado con éxito<br><a href=>Click aquí para verlo</a></p></html>", xmlPath);
	    }
	} else {
	    Advisor.messageBox("Ha ocurrido un error al crear el archivo, el reporte no se generará", "Error");
	}
    }

    private boolean AbreArchivo(String _path) {
	try {
	    xmlFile = new FileWriter(_path, false);
	    log = new BufferedWriter(xmlFile);
	    return true;
	} catch (IOException x) {
	    org.digitall.lib.components.Advisor.messageBox("Error de E/S", "Error");
	    x.printStackTrace();
	    return false;
	}
    }

    private boolean CierraArchivo() {
	try {
	    xmlFile.close();
	    return true;
	} catch (IOException x) {
	    org.digitall.lib.components.Advisor.messageBox("Error de E/S", "Error");
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

    private void writeln() {
	try {
	    xmlFile.write("<w:p/>\n");
	} catch (IOException x) {
	    org.digitall.lib.components.Advisor.messageBox("Error de E/S", "Error");
	    x.printStackTrace();
	}
    }

    private String Seccion1() {
	// Seccion 1: Tipo de Documento y Esquemas
	String seccion1 = "<?xml version=\"1.0\" encoding=\"iso-8859-1\" standalone=\"yes\"?>\n" + "<?mso-application progid=\"Word.Document\"?>\n" + "<w:wordDocument xmlns:w=\"http://schemas.microsoft.com/office/word/2003/wordml\"\n" + "  xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\"\n" + "  xmlns:sl=\"http://schemas.microsoft.com/schemaLibrary/2003/core\" xmlns:aml=\"http://schemas.microsoft.com/aml/2001/core\" " + "  xmlns:wx=\"http://schemas.microsoft.com/office/word/2003/auxHint\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" " + "  xmlns:dt=\"uuid:C2F41010-65B3-11d1-A29F-00AA00C14882\" w:macrosPresent=\"no\" w:embeddedObjPresent=\"no\"\n" + "  w:ocxPresent=\"no\" xml:space=\"preserve\">";
	return seccion1;
    }

    private String FinSeccion1() {
	String finseccion1 = "</w:wordDocument>\n";
	return finseccion1;
    }

    private String Seccion2() {
	// Seccion 2: Propiedades
	String seccion2 = "<o:DocumentProperties>\n" + "  <o:Title>Reporte</o:Title>\n" + "  <o:Author>Sistema General</o:Author>\n" + "  <o:Created>yyyy-MM-ddTHH:mm:ssZ</o:Created>\n" + "  <o:Company>Secretaría de Obras y Servicios Públicos</o:Company>\n" + "  <o:Version>0</o:Version>\n" + "</o:DocumentProperties>\n";
	return seccion2;
    }

    private String Seccion3() {
	// Seccion 3: Fonts y Styles
	String seccion3 = "<w:fonts>\n" + "  <w:defaultFonts w:ascii=\"Times New Roman\" w:fareast=\"Times New Roman\" w:h-ansi=\"Times New Roman\" w:cs=\"Times New Roman\"/>\n" + "</w:fonts>\n" + "<!--**********************************************************************************************************-->\n" + "<w:styles>\n" + "  <w:versionOfBuiltInStylenames w:val=\"4\"/>\n" + "  <w:latentStyles w:defLockedState=\"off\" w:latentStyleCount=\"156\"/>\n" + "  <w:style w:type=\"paragraph\" w:default=\"on\" w:styleId=\"Normal\">\n" + "    <w:name w:val=\"Normal\"/>\n" + "    <w:rPr><wx:font wx:val=\"Times New Roman\"/>\n" + "      <w:sz w:val=\"24\"/>\n" + "      <w:sz-cs w:val=\"24\"/>\n" + "      <w:lang w:val=\"ES\" w:fareast=\"ES\" w:bidi=\"AR-SA\"/>\n" + "    </w:rPr>\n" + "  </w:style>\n" + "  <w:style w:type=\"character\" w:default=\"on\" w:styleId=\"Fuentedeprrafopredeter\">\n" + "    <w:name w:val=\"Default Paragraph Font\"/>\n" + "    <wx:uiName wx:val=\"Fuente de pÃ¡rrafo predeter.\"/>\n" + "    <w:semiHidden/>\n" + "  </w:style>\n" + "  <w:style w:type=\"table\" w:default=\"on\" w:styleId=\"Tablanormal\">\n" + "    <w:name w:val=\"Normal Table\"/>\n" + "    <wx:uiName wx:val=\"Tabla normal\"/>\n" + "    <w:semiHidden/>\n" + "    <w:rPr>\n" + "      <wx:font wx:val=\"Times New Roman\"/>\n" + "    </w:rPr>\n" + "    <w:tblPr>\n" + "      <w:tblInd w:w=\"0\" w:type=\"dxa\"/>\n" + "      <w:tblCellMar><w:top w:w=\"0\" w:type=\"dxa\"/>\n" + "        <w:left w:w=\"108\" w:type=\"dxa\"/>\n" + "        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" + "        <w:right w:w=\"108\" w:type=\"dxa\"/>\n" + "      </w:tblCellMar>\n" + "    </w:tblPr>\n" + "  </w:style>\n" + "  <w:style w:type=\"list\" w:default=\"on\" w:styleId=\"Sinlista\">\n" + "    <w:name w:val=\"No List\"/>\n" + "    <wx:uiName wx:val=\"Sin lista\"/>\n" + "    <w:semiHidden/>\n" + "  </w:style>\n" + "  <w:style w:type=\"table\" w:styleId=\"Tablaconcuadrcula\">\n" + "    <w:name w:val=\"Table Grid\"/>\n" + "    <wx:uiName wx:val=\"Tabla con cuadrÃ­cula\"/>\n" + "    <w:basedOn w:val=\"Tablanormal\"/>\n" + "    <w:rsid w:val=\"00771DCE\"/>\n" + "    <w:rPr>\n" + "      <wx:font wx:val=\"Times New Roman\"/>\n" + "    </w:rPr>\n" + "    <w:tblPr>\n" + "      <w:tblInd w:w=\"0\" w:type=\"dxa\"/>\n" + "      <w:tblBorders>\n" + "        <w:top w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"auto\"/>\n" + "        <w:left w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"auto\"/>\n" + "        <w:bottom w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"auto\"/>\n" + "        <w:right w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"auto\"/>\n" + "        <w:insideH w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"auto\"/>\n" + "        <w:insideV w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"auto\"/>\n" + "      </w:tblBorders>\n" + "      <w:tblCellMar>\n" + "        <w:top w:w=\"0\" w:type=\"dxa\"/>\n" + "        <w:left w:w=\"108\" w:type=\"dxa\"/>\n" + "        <w:bottom w:w=\"0\" w:type=\"dxa\"/>\n" + "        <w:right w:w=\"108\" w:type=\"dxa\"/>\n" + "      </w:tblCellMar>\n" + "    </w:tblPr>\n" + "  </w:style>\n" + "  <w:style w:type=\"paragraph\" w:styleId=\"Encabezado\">\n" + "    <w:name w:val=\"header\"/>\n" + "    <wx:uiName wx:val=\"Encabezado\"/>\n" + "    <w:basedOn w:val=\"Normal\"/>\n" + "    <w:rsid w:val=\"00771DCE\"/>\n" + "    <w:pPr>\n" + "      <w:pStyle w:val=\"Encabezado\"/>\n" + "      <w:tabs>\n" + "        <w:tab w:val=\"center\" w:pos=\"4252\"/>\n" + "        <w:tab w:val=\"right\" w:pos=\"8504\"/>\n" + "      </w:tabs>\n" + "    </w:pPr>\n" + "    <w:rPr>\n" + "      <wx:font wx:val=\"Times New Roman\"/>\n" + "    </w:rPr>\n" + "  </w:style>\n" + "  <w:style w:type=\"paragraph\" w:styleId=\"Piedepgina\">\n" + "    <w:name w:val=\"footer\"/>\n" + "    <wx:uiName wx:val=\"Pie de pÃ¡gina\"/>\n" + "    <w:basedOn w:val=\"Normal\"/>\n" + "    <w:rsid w:val=\"00771DCE\"/>\n" + "    <w:pPr>\n" + "      <w:pStyle w:val=\"Piedepgina\"/>\n" + "      <w:tabs>\n" + "        <w:tab w:val=\"center\" w:pos=\"4252\"/>\n" + "        <w:tab w:val=\"right\" w:pos=\"8504\"/>\n" + "      </w:tabs>\n" + "    </w:pPr>\n" + "    <w:rPr>\n" + "      <wx:font wx:val=\"Times New Roman\"/>\n" + "    </w:rPr>\n" + "  </w:style>\n" + "</w:styles>\n";
	return seccion3;
    }

    private String Seccion4() {
	//  Seccion 4: Propiedades del Documento de Word
	String seccion4 = "<w:docPr>\n" + "  <w:view w:val=\"print\"/>\n" + "  <w:zoom w:percent=\"100\"/>\n" + "  <w:doNotEmbedSystemFonts/>\n" + "  <w:attachedTemplate w:val=\"\"/>\n" + "  <w:defaultTabStop w:val=\"708\"/>\n" + "  <w:hyphenationZone w:val=\"425\"/>\n" + "  <w:punctuationKerning/>\n" + "  <w:characterSpacingControl w:val=\"DontCompress\"/>\n" + "  <w:optimizeForBrowser/>\n" + "  <w:validateAgainstSchema/>\n" + "  <w:saveInvalidXML w:val=\"off\"/>\n" + "  <w:ignoreMixedContent w:val=\"off\"/>\n" + "  <w:alwaysShowPlaceholderText w:val=\"off\"/>\n" + "  <w:footnotePr>\n" + "    <w:footnote w:type=\"separator\">\n" + "      <w:p>\n" + "        <w:r>\n" + "          <w:separator/>\n" + "        </w:r>\n" + "      </w:p>\n" + "    </w:footnote>\n" + "    <w:footnote w:type=\"continuation-separator\">\n" + "      <w:p>\n" + "        <w:r>\n" + "          <w:continuationSeparator/>\n" + "        </w:r>\n" + "      </w:p>\n" + "    </w:footnote>\n" + "  </w:footnotePr>\n" + "  <w:endnotePr>\n" + "    <w:endnote w:type=\"separator\">\n" + "      <w:p>\n" + "        <w:r>\n" + "          <w:separator/>\n" + "        </w:r>\n" + "      </w:p>\n" + "    </w:endnote>\n" + "    <w:endnote w:type=\"continuation-separator\">\n" + "      <w:p>\n" + "        <w:r>\n" + "          <w:continuationSeparator/>\n" + "        </w:r>\n" + "      </w:p>\n" + "    </w:endnote>\n" + "  </w:endnotePr>\n" + "  <w:compat>\n" + "    <w:breakWrappedTables/>\n" + "    <w:snapToGridInCell/>\n" + "    <w:wrapTextWithPunct/>\n" + "    <w:useAsianBreakRules/>\n" + "    <w:dontGrowAutofit/>\n" + "  </w:compat>\n" + "</w:docPr>\n";
	return seccion4;
    }

    private String writeFoto(int _idfoto, double _width, double _height) {
	try {
	    //ResultSet rs = ps.executeQuery();
	    ResultSet rs = LibSQL.exQuery("SELECT foto, ancho, alto from fotos where idfoto = " + _idfoto);
	    if (rs != null) {
		write("<w:pict><w:binData w:name=\"wordml://" + _idfoto + ".jpg\">");
		while (rs.next()) {
		    byte[] imgBytes = rs.getBytes(1);
		    write(new String(Base64Coder.encode(imgBytes)));
		}
		double anchoimagen = rs.getDouble("ancho");
		double altoimagen = rs.getDouble("alto");
		double factor1 = _width / anchoimagen;
		double factor2 = _height / altoimagen;
		double factor = 1;
		if (factor1 > factor2)
		    factor = factor1;
		else
		    factor = factor2;
		double width = anchoimagen * factor;
		double height = altoimagen * factor;
		write("</w:binData><v:shape id=\"_x0000_i1025\" type=\"#_x0000_t75\" style=\"width:" + width + "pt" + ";height:" + height + "pt\">" + "<v:imagedata src=\"wordml://" + _idfoto + ".jpg\" o:title=\"Foto\"/>" + "</v:shape></w:pict>");
		rs.close();
	    }
	} catch (Exception x) {
	    org.digitall.lib.components.Advisor.messageBox("Error al obtener la Foto", "Error");
	    x.printStackTrace();
	}
	return "";
    }

    private void writeEncabezadoPie() {
	//  Encabezado y Pie de Página
	writeln("<!-- Inicio del Encabezado y Pie -->");
	//Encabezado
	writeln("<w:sectPr>");
	writeln("<w:hdr w:type=\"odd\">");
	writeln("<w:pStyle w:val=\"Encabezado\"/>");
	writeln("<w:tbl><w:tblPr><w:tblStyle w:val=\"Tablaconcuadrcula\"/><w:tblW w:w=\"0\" w:type=\"auto\"/>");
	writeln("<w:tblBorders><w:top w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/><w:left w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/>");
	writeln("<w:bottom w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/><w:right w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/>");
	writeln("<w:insideH w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/><w:insideV w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/>");
	writeln("</w:tblBorders>");
	writeln("<w:tblLook w:val=\"01E0\"/></w:tblPr><w:tblGrid><w:gridCol w:w=\"2268\"/><w:gridCol w:w=\"3780\"/><w:gridCol w:w=\"2596\"/>");
	writeln("</w:tblGrid>");
	writeln("<w:tr><w:tc><w:tcPr><w:tcW w:w=\"2268\" w:type=\"dxa\"/></w:tcPr><w:p><w:pPr><w:pStyle w:val=\"Encabezado\"/><w:jc w:val=\"center\"/></w:pPr>");
	writeln("<w:r>");
	writeFoto(1, 20, 30);
	writeln("</w:r></w:p></w:tc><w:tc><w:tcPr><w:tcW w:w=\"3780\" w:type=\"dxa\"/></w:tcPr><w:p><w:pPr>");
	writeln("<w:pStyle w:val=\"Encabezado\"/></w:pPr></w:p></w:tc><w:tc><w:tcPr><w:tcW w:w=\"2596\" w:type=\"dxa\"/></w:tcPr>");
	writeln("<w:p><w:pPr><w:pStyle w:val=\"Encabezado\"/></w:pPr></w:p></w:tc></w:tr><w:tr><w:tc>");
	writeln("<w:tcPr><w:tcW w:w=\"2268\" w:type=\"dxa\"/></w:tcPr><w:p><w:pPr><w:pStyle w:val=\"Encabezado\"/>");
	writeln("<w:jc w:val=\"center\"/></w:pPr><w:r><w:t>Secretaría de Obras</w:t></w:r></w:p><w:p><w:pPr>");
	writeln("<w:pStyle w:val=\"Encabezado\"/></w:pPr><w:r><w:t>y Servicios Públicos</w:t></w:r></w:p>");
	writeln("</w:tc><w:tc><w:tcPr><w:tcW w:w=\"3780\" w:type=\"dxa\"/></w:tcPr><w:p><w:pPr>");
	writeln("<w:pStyle w:val=\"Encabezado\"/></w:pPr></w:p></w:tc><w:tc><w:tcPr><w:tcW w:w=\"2596\" w:type=\"dxa\"/>");
	writeln("<w:vAlign w:val=\"bottom\"/></w:tcPr><w:p><w:pPr><w:pStyle w:val=\"Encabezado\"/>");
	writeln("<w:jc w:val=\"right\"/></w:pPr><w:r><w:t>Fecha: " + Proced.FechaHora2(true, false) + "</w:t></w:r></w:p><w:p><w:pPr>");
	writeln("<w:pStyle w:val=\"Encabezado\"/><w:jc w:val=\"right\"/></w:pPr>");
	writeln("<w:r><w:t>Hora: " + Proced.FechaHora2(false, false) + "</w:t></w:r></w:p></w:tc></w:tr></w:tbl>");
	writeln("<w:p><w:pPr><w:pStyle w:val=\"Encabezado\"/><w:pBdr>");
	writeln("<w:bottom w:val=\"single\" w:sz=\"12\" wx:bdrwidth=\"30\" w:space=\"1\" w:color=\"auto\"/></w:pBdr></w:pPr></w:p>");
	writeln();
	writeln("</w:hdr>");
	//Pie de Página
	writeln("<w:ftr w:type=\"odd\"><wx:pBdrGroup><wx:borders>");
	writeln("<wx:bottom wx:val=\"solid\" wx:bdrwidth=\"30\" wx:space=\"1\" wx:color=\"auto\"/>");
	writeln("</wx:borders><w:p><w:pPr><w:pBdr><w:bottom w:val=\"single\" w:sz=\"12\" wx:bdrwidth=\"30\" w:space=\"1\" w:color=\"auto\"/>");
	writeln("</w:pBdr></w:pPr></w:p></wx:pBdrGroup><w:p/><w:tbl><w:tblPr><w:tblStyle w:val=\"Tablaconcuadrcula\"/>");
	writeln("<w:tblW w:w=\"0\" w:type=\"auto\"/><w:tblBorders>");
	writeln("<w:top w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/>");
	writeln("<w:left w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/>");
	writeln("<w:bottom w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/>");
	writeln("<w:right w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/>");
	writeln("<w:insideH w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/>");
	writeln("<w:insideV w:val=\"none\" w:sz=\"0\" wx:bdrwidth=\"0\" w:space=\"0\" w:color=\"auto\"/>");
	writeln("</w:tblBorders><w:tblLook w:val=\"01E0\"/></w:tblPr><w:tblGrid><w:gridCol w:w=\"6948\"/>");
	writeln("<w:gridCol w:w=\"1696\"/></w:tblGrid><w:tr><w:tc><w:tcPr><w:tcW w:w=\"6948\" w:type=\"dxa\"/>");
	writeln("<w:vmerge w:val=\"restart\"/></w:tcPr><w:p><w:pPr><w:pStyle w:val=\"Piedepgina\"/><w:jc w:val=\"center\"/>");
	writeln("</w:pPr><w:r><w:t>Informe Generado con XMLReport </w:t></w:r></w:p><w:p><w:pPr>");
	writeln("<w:pStyle w:val=\"Piedepgina\"/><w:jc w:val=\"center\"/></w:pPr><w:r>");
	writeln("<w:t>Sistema General de Obras y Servicios Públicos</w:t></w:r></w:p></w:tc><w:tc><w:tcPr>");
	writeln("<w:tcW w:w=\"1296\" w:type=\"dxa\"/></w:tcPr><w:p><w:pPr><w:pStyle w:val=\"Piedepgina\"/>");
	writeln("</w:pPr></w:p></w:tc></w:tr><w:tr><w:tc><w:tcPr><w:tcW w:w=\"6948\" w:type=\"dxa\"/><w:vmerge/>");
	writeln("</w:tcPr><w:p><w:pPr><w:pStyle w:val=\"Piedepgina\"/></w:pPr></w:p></w:tc><w:tc><w:tcPr>");
	writeln("<w:tcW w:w=\"2096\" w:type=\"dxa\"/></w:tcPr><w:p><w:pPr><w:pStyle w:val=\"Piedepgina\"/>");
	writeln("<w:jc w:val=\"right\"/></w:pPr><w:r>");
	writeln("<w:t>Página </w:t></w:r><w:r><w:fldChar w:fldCharType=\"begin\"/>");
	writeln("</w:r><w:r><w:instrText> PAGE </w:instrText></w:r><w:r><w:fldChar w:fldCharType=\"separate\"/></w:r><w:r><w:rPr>");
	writeln("<w:noProof/></w:rPr>");
	writeln("<w:t></w:t>");
	writeln("</w:r><w:r><w:fldChar w:fldCharType=\"end\"/></w:r><w:r>");
	writeln("<w:t> de </w:t></w:r><w:r>");
	writeln("<w:fldChar w:fldCharType=\"begin\"/></w:r><w:r><w:instrText> NUMPAGES </w:instrText></w:r><w:r>");
	writeln("<w:fldChar w:fldCharType=\"separate\"/></w:r><w:r><w:rPr><w:noProof/></w:rPr>");
	writeln("<w:t></w:t>");
	writeln("</w:r><w:r><w:fldChar w:fldCharType=\"end\"/></w:r></w:p></w:tc></w:tr></w:tbl><w:p><w:pPr><w:pStyle w:val=\"Piedepgina\"/>");
	writeln("</w:pPr></w:p></w:ftr>");
	writeln("</w:sectPr>");
	writeln("<!-- Fin del Encabezado y Pie -->");
    }

    private String InicioCuerpo() {
	String iniciocuerpo = "<!-- Inicio del cuerpo -->\n" + "<w:body>\n" + "  <wx:sect>\n";
	return iniciocuerpo;
    }

    private String Cuerpo() {
	return "";
    }

    private String FinCuerpo() {
	String fincuerpo = "  </wx:sect>\n" + "</w:body>\n" + "<!-- Fin del Cuerpo -->\n";
	return fincuerpo;
    }

    private String InicioTabla(int indice) {
	//  Supuestamente una tabla tiene 8644 unidades de ancho,
	//  por ende habrá que hacer ancho_columna = 8644/cantidad_columnas
	String titulotabla = "<!-- Titulo de la tabla -->\n" + "<w:p>\n" + "  <w:pPr>\n" + "    <w:jc w:val=\"center\"/>\n" + "    <w:rPr>\n" + "      <w:b/>\n" + "    </w:rPr>\n" + "  </w:pPr>\n" + "  <w:r>\n" + "    <w:rPr>" + "      <w:sz w:val=\"32\"/><w:sz-cs w:val=\"32\"/><w:b/>" + "    </w:rPr>" + "    <w:t>" + params[indice][0] + "</w:t>\n" + "  </w:r>\n" + "</w:p>\n";
	String iniciotabla = "<!-- Inicio de la tabla -->\n" + "<w:tbl>\n" + "  <w:tblPr>\n" + "    <w:tblStyle w:val=\"Tablaconcuadrcula\"/>\n" + "    <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" + "    <w:tblLook w:val=\"01E0\"/>\n" + "  </w:tblPr>\n" + "  <w:tblGrid>\n";
	for (int i = 0; i < columnas; i++)
	    iniciotabla += "    <w:gridCol w:w=\"" + anchocolumna + "\"/>\n";
	iniciotabla += "  </w:tblGrid>\n";
	return titulotabla + iniciotabla;
    }

    private void writeTabla(int indice) {
	write(InicioTabla(indice));
	writeln();
	write(EncabezadoTabla(indice));
	writeCuerpoTabla(indice);
	write(FinTabla(indice));
	writeln();
    }

    private String EncabezadoTabla(int indice) {
	String filatabla = "    <w:tr>\n";
	for (int i = 0; i < columnas; i++)
	    filatabla += "      <w:tc>\n" + "        <w:tcPr>\n" + "          <w:tcW w:w=\"" + anchocolumna + "\" w:type=\"dxa\"/>\n" + "        </w:tcPr>\n" + "        <w:p>\n" + "          <w:pPr>\n" + "            <w:jc w:val=\"center\"/>\n" + "          </w:pPr>\n" + "          <w:r><w:rPr><w:b/></w:rPr>\n" + "            <w:t>" + EncabezadoTabla.elementAt(i).toString() + "</w:t>\n" + "          </w:r>\n" + "        </w:p>\n" + "      </w:tc>\n";
	filatabla += "    </w:tr>\n";
	return filatabla;
    }

    private void writeCuerpoTabla(int indice) {
	String fila = "";
	try {
	    //System.out.println(params[indice][2]);
	    ResultSet Resulx = org.digitall.lib.sql.LibSQL.exQuery(params[indice][2]);
	    while (Resulx.next()) {
		fila = "    <w:tr>\n";
		for (int i = 0; i < columnas; i++)
		    fila += "      <w:tc>\n" + "        <w:tcPr>\n" + "          <w:tcW w:w=\"" + anchocolumna + "\" w:type=\"dxa\"/>\n" + "        </w:tcPr>\n" + "        <w:p>\n" + "          <w:pPr>\n" + "            <w:jc w:val=\"center\"/>\n" + "          </w:pPr>\n" + "          <w:r>\n" + "            <w:t>" + Resulx.getString(i + 1) + "</w:t>\n" + "          </w:r>\n" + "        </w:p>\n" + "      </w:tc>\n";
		fila += "    </w:tr>\n";
		write(fila);
	    }
	} catch (SQLException x) {
	    org.digitall.lib.components.Advisor.messageBox(x.getMessage(), "Error");
	    x.printStackTrace();
	}
    }

    private String FinTabla(int indice) {
	String total = "";
	total = org.digitall.lib.sql.LibSQL.getCampo(params[indice][3]);
	String fintabla = //writeFoto(indice) +
	    "<w:tr>\n" + "  <w:tc>\n" + "    <w:tcPr>\n" + "      <w:tcW w:w=\"" + anchocolumna * (columnas - 1) + "\" w:type=\"dxa\"/>\n" + "      <w:gridSpan w:val=\"" + (columnas - 1) + "\"/>\n" + "      <w:tcBorders>\n" + "        <w:top w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"auto\"/>\n" + "        <w:left w:val=\"nil\"/>\n" + "        <w:bottom w:val=\"nil\"/>\n" + "        <w:right w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"auto\"/>\n" + "      </w:tcBorders>\n" + "    </w:tcPr>\n" + "    <w:p>\n" + "      <w:pPr>\n" + "        <w:jc w:val=\"right\"/>\n" + "      </w:pPr>\n" + "      <w:r>\n" + "        <w:t>TOTAL</w:t>\n" + "      </w:r>\n" + "    </w:p>\n" + "  </w:tc>\n" + "  <w:tc>\n" + "    <w:tcPr>\n" + "      <w:tcW w:w=\"" + anchocolumna + "\" w:type=\"dxa\"/>\n" + "      <w:tcBorders>\n" + "        <w:top w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"auto\"/>\n" + "        <w:left w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"auto\"/>\n" + "      </w:tcBorders>\n" + "    </w:tcPr>\n" + "    <w:p>\n" + "      <w:pPr>\n" + "        <w:jc w:val=\"center\"/>\n" + "      </w:pPr>\n" + "      <w:r>\n" + "        <w:t>" + total + "</w:t>\n" + "      </w:r>\n" + "    </w:p>\n" + "  </w:tc>\n" + "</w:tr>\n" + "</w:tbl>\n" + "<!-- Fin de la tabla -->\n";
	return fintabla;
    }
    /*  private String formatear(String _cadena)
  {
    return _cadena.replaceAll("á","a").replaceAll("é","e").replaceAll("í","i").replaceAll("ó","o").replaceAll("ú","u")
                  .replaceAll("Á","A").replaceAll("É","E").replaceAll("Í","I").replaceAll("Ó","O").replaceAll("Ú","U")
                  .replaceAll("ñ","n").replaceAll("Ñ","N").replaceAll("Ü","U").replaceAll("ü","u")
                  .replaceAll("º","o").replaceAll("ª","a");
  }
*/

    public String getEncabezadoReporte() {
	return EncabezadoReporte;
    }

    public void setEncabezadoReporte(String _EncabezadoReporte) {
	EncabezadoReporte = _EncabezadoReporte;
    }

    private String writeFoto(int indice) {
	try {
	    ResultSet rs = org.digitall.lib.sql.LibSQL.exQuery("SELECT foto, ancho, alto from fotos where idfoto = " + (indice + 1));
	    if (rs != null) {
		write("<w:pict><w:binData w:name=\"wordml://" + indice + ".jpg\">");
		while (rs.next()) {
		    byte[] imgBytes = rs.getBytes(1);
		    write(new String(Base64Coder.encode(imgBytes)));
		}
		write("</w:binData><v:shape id=\"_x0000_i1025\" type=\"#_x0000_t75\" style=\"width:" + rs.getString(2) + "pt" + ";height:" + rs.getString(3) + "pt\">" + "<v:imagedata src=\"wordml://" + indice + ".jpg\" o:title=\"Foto\"/>" + "</v:shape></w:pict>");
		rs.close();
	    }
	} catch (Exception x) {
	    org.digitall.lib.components.Advisor.messageBox("Error al obtener la Foto", "Error");
	    x.printStackTrace();
	}
	return "";
    }

}
