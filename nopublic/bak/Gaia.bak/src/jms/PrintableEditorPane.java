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