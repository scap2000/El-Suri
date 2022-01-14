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
 * PrintableEditorPane.java
 *
 * */
package jms;
import javax.swing.JEditorPane;
import javax.swing.RepaintManager;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.awt.print.Printable;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.Serializable;
import java.net.URL;
import java.io.IOException;

/** Codigo obtenido en 
http://www.experts-exchange.com/Programming/Programming_Languages/Java/Q_20317393.html
Autor: Kylar
*/
public class PrintableEditorPane extends JEditorPane 
  implements Printable, Serializable {
    public PrintableEditorPane(URL url) throws IOException{
      super(url);
    }
  /**
  * The method @print@ must be implemented for @Printable@ interface.
  * Parameters are supplied by system.
  */
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException 
    {
      Graphics2D g2 = (Graphics2D)g;
      g2.setColor(Color.black);  //set default foreground color to black

      RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);
      Dimension d = this.getSize();  //get size of document
      double panelWidth = d.width;  //width in pixels
      double panelHeight = d.height;  //height in pixels
      double pageHeight = pf.getImageableHeight();  //height of printer page
      double pageWidth = pf.getImageableWidth();  //width of printer page
      double scale = pageWidth/panelWidth;
      int totalNumPages = (int)Math.ceil(scale * panelHeight / pageHeight);

      // Make sure not print empty pages
      if(pageIndex >= totalNumPages) 
      {
        return Printable.NO_SUCH_PAGE;
      }

      // Shift Graphic to line up with beginning of print-imageable region
      g2.translate(pf.getImageableX(), pf.getImageableY());
      // Shift Graphic to line up with beginning of next page to print
      g2.translate(0f, -pageIndex*pageHeight);
      // Scale the page so the width fits...
      g2.scale(scale, scale);
      this.paint(g2);  //repaint the page for printing
      return Printable.PAGE_EXISTS;
    }
         
    public void doPrintActions()
    {
      PrinterJob job=PrinterJob.getPrinterJob();
      job.setPrintable(this);
      if (job.printDialog())
      try
      {
        job.print();
      } catch (PrinterException x) 
      {
        Proc.Mensaje(x.getMessage(),"");
      }
    }
  }