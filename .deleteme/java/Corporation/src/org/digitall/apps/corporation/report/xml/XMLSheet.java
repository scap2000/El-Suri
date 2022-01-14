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
 * XMLSheet.java
 *
 * */
package org.digitall.apps.corporation.report.xml;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.Vector;

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

public class XMLSheet 
{
  private FileWriter xmlFile;
  private BufferedWriter log;
  private int anchocolumna = 0;
  private String EncabezadoReporte = "";
  private String[] querys;
  private int columnas = 0;
  private Vector cabecera;
  private String xmlPath = "";
 
/*  public XMLSheet(Vector _cabecera, String[] _querys)
  {
    querys = _querys;
    cabecera = _cabecera;
    columnas = cabecera.size();

      // Abro el Archivo
    System.out.println("Ruta: " + Proced.getRuta());
    //xmlPath = Proced.ruta + Proced.separador + "Informe de "+ params[0][0].replaceAll("/","-") +" - "+ Proced.FechaHora2(false,true) +".xls";
    xmlPath = Proced.getRuta() + Proced.getSeparador() + "informes" + Proced.getSeparador() + "Informe de "+ cabecera.elementAt(0).toString().replaceAll("/","-")  +".xls";
    //System.out.println("ruta: "+Proced.ruta + Proced.separador + "Informe de "+ params[0][0].replaceAll("/","-") +" - "+ Proced.FechaHora2(false,true) +".doc");           
    
    if (AbreArchivo(xmlPath))
    {
      write(Seccion1());
        write(Seccion2());
        write(Seccion3());
        writeEncabezadoPie();
        write(InicioCuerpo());
        write(Cuerpo());
        write(FinCuerpo());
        write(Seccion4());
      write(FinSeccion1());
      String Fecha = Proced.FechaHora2(true,false);
      if (CierraArchivo())
      {
        System.out.println("Archivo XML generado con éxito");
//        Proced.MensajePopupWindow("<html>Reporte generado con éxito en<br><a href=file:///" + xmlPath + ">" + xmlPath + "</a></html>");
//        Proced.MensajePopupWindow("Reporte generado con éxito");
        Proced.MensajePopupWindow("<html><p align=center>Reporte generado con éxito<br><a href=>Click aquí para verlo</a></p></html>", xmlPath);
      }
    } else 
    {
      Proced.Mensaje("Ha ocurrido un error al crear el archivo, el reporte no se generará", "Error");
    }
  }
  
  private boolean AbreArchivo(String _path)
  {
    try 
    {
      xmlFile = new FileWriter(_path,false);
      log = new BufferedWriter(xmlFile);
      return true;
    } catch (IOException x) 
    {
      Proced.Mensaje("Error de E/S", "Error");
      x.printStackTrace();
      return false;
    }
  }

  private boolean CierraArchivo()
  {
    try 
    {
      xmlFile.close();
      return true;
    } catch (IOException x)
    {
      Proced.Mensaje("Error de E/S", "Error");
      x.printStackTrace();
      return false;
    }
  }

  private void write(String _cadena)
  {
    try 
    {
      xmlFile.write(_cadena);
    } catch (IOException x) 
    {
      Proced.Mensaje("Error de E/S", "Error");
      x.printStackTrace();
    }
  }

  private void writeln(String _cadena) 
  {
    try 
    {
      xmlFile.write(_cadena + "\n");
    } catch (IOException x) 
    {
      Proced.Mensaje("Error de E/S", "Error");
      x.printStackTrace();
    }
  }

  private void writeln() 
  {
    try 
    {
      xmlFile.write("<w:p/>\n");
    } catch (IOException x) 
    {
      Proced.Mensaje("Error de E/S", "Error");
      x.printStackTrace();
    }
  }
  
  private String Seccion1() 
  {
    // Seccion 1: Tipo de Documento y Esquemas
    String seccion1 = 
      "<?xml version=\"1.0\" encoding=\"iso-8859-1\" standalone=\"yes\"?>\n" +
      "<?mso-application progid=\"Excel.Sheet\"?>\n" +
      "<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"\n" +
      " xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
      " xmlns:x=\"urn:schemas-microsoft-com:office:excel\"\n" +
      " xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"\n" +
      " xmlns:html=\"http://www.w3.org/TR/REC-html40\">\n";
   return seccion1;
  }

  private String FinSeccion1()
  {
    String finseccion1 = 
      "</Workbook>\n";
    return finseccion1;
  }
  
  private String Seccion2() 
  {
    // Seccion 2: Propiedades
    String seccion2 = 
      " <DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">\n" +
      "  <Author>usuario</Author>\n" +
      "  <LastAuthor>usuario</LastAuthor>\n" +
      "  <Created>2006-08-30T08:12:27Z</Created>\n" +
      "  <Company>Secretaría de Obras y Servicios Públicos</Company>\n" +
      "  <Version>11.6568</Version>\n" +
      " </DocumentProperties>\n" +
      " <ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">\n" +
      "  <WindowHeight>9210</WindowHeight>\n" +
      "  <WindowWidth>11595</WindowWidth>\n" +
      "  <WindowTopX>360</WindowTopX>\n" +
      "  <WindowTopY>90</WindowTopY>\n" +
      "  <ProtectStructure>False</ProtectStructure>\n" +
      "  <ProtectWindows>False</ProtectWindows>\n" +
      " </ExcelWorkbook>\n";
    return seccion2;
  }
  
  private String Seccion3() 
  {
    // Seccion 3: Fonts y Styles
    String seccion3 = 
      " <Styles>\n" +
      "  <Style ss:ID=\"Default\" ss:Name=\"Normal\">\n" +
      "   <Alignment ss:Vertical=\"Bottom\"/>\n" +
      "   <Borders/>\n" +
      "   <Font/>\n" +
      "   <Interior/>\n" +
      "   <NumberFormat/>\n" +
      "   <Protection/>\n" +
      "  </Style>\n" +
      "  <Style ss:ID=\"s22\">\n" +
      "   <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Top\" ss:WrapText=\"1\"/>\n" +
      "   <Borders>\n" +
      "    <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "    <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "    <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "    <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "   </Borders>\n" +
      "   <Font ss:FontName=\"Times New Roman\" x:Family=\"Roman\" ss:Size=\"12\" ss:Bold=\"1\"/>\n" +
      "  </Style>\n" +
      "  <Style ss:ID=\"s24\">\n" +
      "   <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Top\" ss:WrapText=\"1\"/>\n" +
      "   <Borders>\n" +
      "    <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "    <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "    <Border ss:Position=\"Top\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "   </Borders>\n" +
      "   <Font ss:FontName=\"Times New Roman\" x:Family=\"Roman\" ss:Size=\"12\" ss:Bold=\"1\"/>\n" +
      "  </Style>\n" +
      "  <Style ss:ID=\"s26\">\n" +
      "   <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Top\" ss:WrapText=\"1\"/>\n" +
      "   <Borders>\n" +
      "    <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "    <Border ss:Position=\"Left\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "    <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "   </Borders>\n" +
      "   <Font ss:FontName=\"Times New Roman\" x:Family=\"Roman\" ss:Size=\"12\"/>\n" +
      "  </Style>\n" +
      "  <Style ss:ID=\"s28\">\n" +
      "   <Alignment ss:Horizontal=\"Center\" ss:Vertical=\"Top\" ss:WrapText=\"1\"/>\n" +
      "   <Borders>\n" +
      "    <Border ss:Position=\"Bottom\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "    <Border ss:Position=\"Right\" ss:LineStyle=\"Continuous\" ss:Weight=\"2\"/>\n" +
      "   </Borders>\n" +
      "   <Font ss:FontName=\"Times New Roman\" x:Family=\"Roman\" ss:Size=\"12\"/>\n" +
      "  </Style>\n" +
      " </Styles>\n";
    return seccion3;
  }
  
  private String Seccion4() 
  {
    //  Seccion 4: Propiedades del Documento de Word
    String seccion4 = 
      "  <WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">\n" +
      "   <PageSetup>\n" +
      "    <Header x:Margin=\"0\"/>\n" +
      "    <Footer x:Margin=\"0\"/>\n" +
      "    <PageMargins x:Bottom=\"0.984251969\" x:Left=\"0.78740157499999996\"\n" +
      "     x:Right=\"0.78740157499999996\" x:Top=\"0.984251969\"/>\n" +
      "   </PageSetup>\n" +
      "   <Selected/>\n" +
      "   <Panes>\n" +
      "    <Pane>\n" +
      "     <Number>3</Number>\n" +
      "     <ActiveRow>5</ActiveRow>\n" +
      "    </Pane>\n" +
      "   </Panes>\n" +
      "   <ProtectObjects>False</ProtectObjects>\n" +
      "   <ProtectScenarios>False</ProtectScenarios>\n" +
      "  </WorksheetOptions>\n" +
      "  <AutoFilter x:Range=\"R1C1:R1C" + columnas + "\" xmlns=\"urn:schemas-microsoft-com:office:excel\">\n" +
      "  </AutoFilter>\n" +
      "</Worksheet>\n";
    return seccion4;
  }
  
  private String writeFoto(int _idfoto, double _width, double _height)
  {
    try 
    {
      //ResultSet rs = ps.executeQuery();
      ResultSet rs = Proced.exConsulta("SELECT foto, ancho, alto from fotos where idfoto = " + _idfoto);
      if (rs != null) 
      {
        write("<w:pict><w:binData w:name=\"wordml://" + _idfoto + ".jpg\">");
        
        while(rs.next()) 
        {
          byte[] imgBytes =rs.getBytes(1);
          write(new String(Base64Coder.encode(imgBytes)));
        }
        double anchoimagen = rs.getDouble("ancho");
        double altoimagen = rs.getDouble("alto");
        double factor1 = _width/anchoimagen;
        double factor2 = _height/altoimagen;
        double factor = 1;
        if (factor1>factor2) factor = factor1; else factor = factor2;
        double width = anchoimagen * factor;
        double height = altoimagen * factor;
        write("</w:binData><v:shape id=\"_x0000_i1025\" type=\"#_x0000_t75\" style=\"width:"
        + width + "pt" + ";height:" + height + "pt\">" +
        "<v:imagedata src=\"wordml://" + _idfoto + ".jpg\" o:title=\"Foto\"/>" +
        "</v:shape></w:pict>");
        rs.close();
      }
    } catch (Exception x) 
    {
      Proced.Mensaje("Error al obtener la Foto","Error");
      x.printStackTrace();
    }
    return "";
  }

  private void writeEncabezadoPie() 
  {
    //  Encabezado y Pie de Página
    writeln("<!-- Inicio del Encabezado y Pie -->");

//Encabezado
//    writeFoto(1,20,30);

//Pie de Página
    writeln("<!-- Fin del Encabezado y Pie -->");
  }
  
  private String InicioCuerpo() 
  {
    String iniciocuerpo = 
      "<!-- Inicio del cuerpo -->\n" + 
        " <Worksheet ss:Name=\"Hoja1\">\n" + 
        "   <Names>\n" + 
        "     <NamedRange ss:Name=\"_FilterDatabase\" ss:RefersTo=\"=Hoja1!R1C1:R1C" + columnas + "\" ss:Hidden=\"1\"/>\n" + 
        "   </Names>\n";
    return iniciocuerpo;
  }
  
  private String Cuerpo() 
  {
    write(InicioTabla());
    write(EncabezadoTabla());
    for (int i = 0; i<querys.length; i++) 
    {
      writeCuerpoTabla(i);
    }
    write(FinTabla());
    return "";
  }
  
  private String FinCuerpo() 
  {
    String fincuerpo = "";
    return fincuerpo;
  }
  
  private String InicioTabla()
  {
    //  Supuestamente una tabla tiene 8644 unidades de ancho,
    //  por ende habrá que hacer ancho_columna = 8644/cantidad_columnas
    String titulotabla = "";
    String iniciotabla = 
            "<!-- Inicio de la tabla -->\n" + 
            "  <Table ss:ExpandedColumnCount=\"" + columnas + "\" x:FullColumns=\"1\"\n" + 
            "   x:FullRows=\"1\" ss:DefaultColumnWidth=\"216\">\n";
            for (int i=0; i<columnas; i++) iniciotabla +="       <Column ss:Width=\"" + 216 + "\"/>\n";
    return titulotabla+iniciotabla;
  }
  
  private String EncabezadoTabla() 
  {
    String filatabla = "   <Row ss:Height=\"16.5\">\n";
    for (int i=0; i<columnas; i++) filatabla +=
      "    <Cell ss:StyleID=\"s22\"><Data ss:Type=\"String\">" + 
      cabecera.elementAt(i).toString() + 
      "</Data><NamedCell ss:Name=\"_FilterDatabase\"/></Cell>\n";
    filatabla += "   </Row>\n";
    return filatabla;
  }

  private void writeCuerpoTabla(int indice) 
  {
    String fila = "";
    try
    {
      //System.out.println(querys[indice]);
      ResultSet Resulx = Proced.exConsulta(querys[indice]);
      while (Resulx.next()) 
      {
//        fila = "   <Row ss:AutoFitHeight=\"0\" ss:Height=\"16.5\">\n";
        fila = "   <Row ss:Height=\"16.5\">\n";
        for (int i=0; i<columnas; i++) fila +=
          "    <Cell ss:StyleID=\"s26\"><Data ss:Type=\"String\">"+ Resulx.getString(i+1) + "</Data></Cell>\n";
        fila += "   </Row>\n";
        write(fila);
      }
    } catch (SQLException x) 
    {
      Proced.Mensaje(x.getMessage(), "Error");
      x.printStackTrace();
    }
  }
  
  private String FinTabla()
  {
    String fintabla = 
      "  </Table>\n" +
      "<!-- Fin de la tabla -->\n";
    return fintabla;
  }
 
  public String getEncabezadoReporte()
  {
    return EncabezadoReporte;
  }

  public void setEncabezadoReporte(String _EncabezadoReporte)
  {
    EncabezadoReporte = _EncabezadoReporte;
  }


  private String writeFoto(int indice)
  {
    try 
    {
      ResultSet rs = Proced.exConsulta("SELECT foto, ancho, alto from fotos where idfoto = " + (indice+1));
      if (rs != null) 
      {
        write("<w:pict><w:binData w:name=\"wordml://" + indice + ".jpg\">");
        
        while(rs.next()) 
        {
          byte[] imgBytes =rs.getBytes(1);
          write(new String(Base64Coder.encode(imgBytes)));
        }

        write("</w:binData><v:shape id=\"_x0000_i1025\" type=\"#_x0000_t75\" style=\"width:"
        + rs.getString(2) + "pt" + ";height:" + rs.getString(3) + "pt\">" +
        "<v:imagedata src=\"wordml://" + indice + ".jpg\" o:title=\"Foto\"/>" +
        "</v:shape></w:pict>");
        rs.close();
      }
    } catch (Exception x) 
    {
      Proced.Mensaje("Error al obtener la Foto","Error");
      x.printStackTrace();
    }
    return "";
  }
 */  
}