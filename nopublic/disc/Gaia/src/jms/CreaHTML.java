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
 * CreaHTML.java
 *
 * */
package jms;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.lang.*;

public class CreaHTML extends JFrame 
{

  public CreaHTML() 
  {
    
  }
  
  public boolean FichaCatastral(int catastro, String archivo)
  {
    String Fecha="";
    
    try
    {

    /** Abro el Archivo */
      String htmlPath = "/tmp/jms/ficha.html";
//      String htmlPath = "C:\\temp\\ficha.html";
      FileWriter htmlFile = new FileWriter(htmlPath,false);
      BufferedWriter log = new BufferedWriter(htmlFile);

    /** Leo el Registro */
      Fecha = Proc.FechaHora(true);
      Connection Conx = Proc.CCon();
      Statement Statx = Conx.createStatement();

      htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
      htmlFile.write("<HTML>\n");
      htmlFile.write("<HEAD>\n");
      htmlFile.write("<TITLE>Informe</TITLE>\n");
      htmlFile.write("</HEAD>\n");
      htmlFile.write("<BODY>\n");
      htmlFile.write("<TABLE align=center WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
      htmlFile.write("<TR>\n");

      /** Titulo */
      htmlFile.write("<TD WIDTH=10%><img src='"+ CreaHTML.class.getResource("iconos/logo.jpg") +"' width=60></TD>\n");
      htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=6 FACE='Arial'><B>Ficha Catastral</B></FONT></P></TD>\n");
      htmlFile.write("<TD WIDTH=10%><img src='"+ CreaHTML.class.getResource("iconos/logo.jpg") +"' width=60></TD>\n");

      htmlFile.write("</TR>\n");
      htmlFile.write("</TABLE><BR><BR>\n");

      htmlFile.write("<TABLE align=center WIDTH=60% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
      htmlFile.write("<TR>\n");
      htmlFile.write("<TD WIDTH=60% ALIGN=CENTER><FONT size=6 FACE='Arial'><B>Croquis</B></FONT></P></TD>\n");
      htmlFile.write("</TR>\n");
      htmlFile.write("<TR>\n");
      htmlFile.write("<TD WIDTH=60%><img src='"+ archivo +"'></TD>\n");
      htmlFile.write("</TR>\n");
      htmlFile.write("</TABLE><BR><BR>\n");
      
      String Query = "select idseccion as seccion, nmanzana||lmanzana as manzana, nparcela||lparcela as parcela," +
      "categoria, impinmm, barrios.nombre as barrio, calles.nombre as calle, " +
      "numero, idcatastro, catastros.* from cepax.parcelas, cepax.catastros " +
      "where cepax.parcelas.idparcela = cepax.catastros.idparcela " +
      "and public.barrios.idbarrio = cepax.parcelas.idbarrio " + 
      "and public.calles.idcalle = cepax.catastros.idcalle " +
      " and cepax.catastros.catastro = " + catastro;

      ResultSet Resulx = Statx.executeQuery(Query);
      
  //  Parte Prima
      while (Resulx.next())
      {

        htmlFile.write("<P ALIGN=CENTER><FONT size=4 FACE='Arial'><i><u>Catastro Nro.: "+ catastro +"</i></u></FONT></P>\n");
        htmlFile.write("<TABLE align=center WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
        htmlFile.write("<TR>\n");
          htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Sección</B></FONT></P></TD>\n");
          htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Manzana</B></FONT></P></TD>\n");
          htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Parcela</B></FONT></P></TD>\n");
          htmlFile.write("<TD WIDTH=20% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Calle</B></FONT></P></TD>\n");
          htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Número</B></FONT></P></TD>\n");
          htmlFile.write("<TD WIDTH=20% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Barrio</B></FONT></P></TD>\n");
          htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>TGI</B></FONT></P></TD>\n");
          htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>ImpInm</B></FONT></P></TD>\n");
        htmlFile.write("</TR>\n");
        
        htmlFile.write("<TR>\n");      
          htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(1) +"</FONT></P></TD>\n");
          htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(2) +"</FONT></P></TD>\n");
          htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(3) +"</FONT></P></TD>\n");
          htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(7) +"</FONT></P></TD>\n");       
          htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(8) +"</FONT></P></TD>\n");                    
          htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(6) +"</FONT></P></TD>\n");          
          htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(4) +"</FONT></P></TD>\n");                    
          htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(5) +"</FONT></P></TD>\n");          
        htmlFile.write("</TR>\n");               
        htmlFile.write("</TABLE><BR>\n");
      }
      
  //  Parte Seconda
      Query = "select propietarios.nombre, tiposid.descripcion, documento from cepax.propietarios where " +
              "propietarios.idpropietario = cepax.catsxprops.idpropietario " + 
              "and idtipodoc = public.tiposid.idtipo " + 
              "and cepax.catsxprops.idcatastro = "+ Resulx.getString(9) + "order by nombre";

      Statement Staty = Conx.createStatement();        
      Resulx = Staty.executeQuery(Query);

      htmlFile.write("<P ALIGN=CENTER><FONT SIZE=4><U>Datos del/los propietario/s</U></FONT></P>\n");
      htmlFile.write("<TABLE align=center WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
      htmlFile.write("<TR>\n");
        htmlFile.write("<TD WIDTH=10%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Nombre</B></FONT></P></TD>\n");
        htmlFile.write("<TD WIDTH=2%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Tipo Doc.</B></FONT></P></TD>\n");          
        htmlFile.write("<TD WIDTH=5%><P ALIGN=CENTER><FONT size=2 FACE='Arial'><B>Documento</B></FONT></P></TD>\n");
      htmlFile.write("</TR>\n");
      
      while (Resulx.next())
      {
        htmlFile.write("<TR>\n");
            htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(1) +"</FONT></P></TD>\n");
            htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(2) +"</FONT></P></TD>\n");
            htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(3) +"</FONT></P></TD>\n");
        htmlFile.write("</TR>\n");       

      }
      htmlFile.write("</TABLE>\n");
      htmlFile.write("<BR><HR><BR>\n");
      htmlFile.write("</BODY>\n");
      htmlFile.write("</HTML>\n");
      htmlFile.close();

      HTMLBrowser temp = new HTMLBrowser(htmlPath);
      temp.setModal(true);
      temp.setVisible(true);
      return true;
    } catch (Exception x) 
    {
      x.printStackTrace();
      return false;
    }
  }

  public boolean Reporte(String layer, String consulta, FileWriter htmlFile)
  {
    String Fecha="";
    
    try
    {

    /** Abro el Archivo */
//      BufferedWriter log = new BufferedWriter(htmlFile);

    /** Leo el Registro */
      Fecha = Proc.FechaHora(true);

      Connection Conx = Proc.CCon();
      Statement Statx = Conx.createStatement();
      ResultSet Resulx = Statx.executeQuery(consulta);

  // Encabezado
      htmlFile.write("<P ALIGN=CENTER><FONT size=4 FACE='Arial'><i><u>Informe del Layer: <B>" + layer + "</B></i></u></FONT></P>\n");
      htmlFile.write("<TABLE align=center WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
      htmlFile.write("<TR>\n");
      //Titulos
      int columnas = Resulx.getMetaData().getColumnCount();
      String[] colnames = new String[columnas];
      for (int i = 0; i<columnas; i++) 
      {
        colnames[i] = Resulx.getMetaData().getColumnName(i+1);
        htmlFile.write("<TD WIDTH=10% ALIGN=CENTER><FONT size=2 FACE='Arial'><B>" + colnames[i] + "</B></FONT></P></TD>\n");
      }
      htmlFile.write("</TR>\n");
      
  //  Parte Prima
      while (Resulx.next())
      {
        htmlFile.write("<TR>\n");      
        for (int i = 0; i<columnas; i++) 
        {
          htmlFile.write("<TD ><P ALIGN=CENTER><FONT size=2 FACE='Arial'>"+ Resulx.getString(i+1) +"</FONT></P></TD>\n");          
        }
        htmlFile.write("</TR>\n");               
      }
      htmlFile.write("</TABLE><BR>\n");
      htmlFile.write("</TABLE>\n");
      htmlFile.write("<BR><HR><BR>\n");
      htmlFile.write("</BODY>\n");
      htmlFile.write("</HTML>\n");
      htmlFile.close();
      return true;
    } catch (Exception x) 
    {
      x.printStackTrace();
      return false;
    }
  }
}