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
 * Polygon2Panel.java
 *
 * */
package jms;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.Format;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.postgis.*;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import oracle.jdeveloper.layout.VerticalFlowLayout;
//import javax.media.j3d.*;

class Polygon2Panel extends JPanel implements MouseListener, MouseMotionListener
{
  int fWidth,fHeight;
  int fNumPoints;
  double fFactor;
  private JLabel label = new JLabel();
  private JLabel coords = new JLabel();
  private VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  Rectangle currentRect = null;
  Rectangle rectToDraw = null;  
  Rectangle previousRectDrawn = new Rectangle();
  Image imagen;
  double Factor = 0;
  double xOffset = 0;
  double yOffset = 0;
  DecimalFormat df = new DecimalFormat("0.0000");
  private String Qwhere = "";
  double xmin=0;
  double xmax=0;
  double ymin=0;
  double ymax=0;
  double extX=0;
  double extY=0;
  String LabelItem = "";
  String Data = "";
  Color color;

  public final static Font font=new Font("Courier",Font.PLAIN,10);
  public final static Color DEFAULT_COLOR=Color.black;
 
  Polygon2Panel ()  {
    fNumPoints = 21;
    fWidth = 800;
    fHeight= 600;
    fFactor = 2.0 * Math.PI / (fNumPoints-1);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  Polygon2Panel (String etiqueta, String datos, Color colores)  {
    fNumPoints = 21;
    fWidth = 800;
    fHeight= 600;
    fFactor = 2.0 * Math.PI / (fNumPoints-1);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
    etiqueta += "";
    if (!etiqueta.trim().equals("")) 
    {
      LabelItem = etiqueta + " as label,";
    }
    Data = datos;
    color = colores;
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  Polygon2Panel (int width, int height, int numPoints)  {
    fNumPoints = numPoints;
    fWidth = width;
    fHeight= height;
    fFactor = 2.0 * Math.PI / (fNumPoints-1);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
  }

  public void paintComponent(Graphics g) {
    // First paint background
    super.paintComponent(g);
   if (currentRect != null) {
        //Draw a rectangle on top of the image.
        g.setXORMode(Color.white); //Color of line varies
        //depending on image colors
        g.drawRect(rectToDraw.x, rectToDraw.y, rectToDraw.width - 1,
            rectToDraw.height - 1);

        updateLabel(rectToDraw);
      }       
      g.drawImage(imagen,0,0,this);
    
  } // paintComponent

   private void updateDrawableRect(int compWidth, int compHeight) {
      int x = currentRect.x;
      int y = currentRect.y;
      int width = currentRect.width;
      int height = currentRect.height;

      //Make the width and height positive, if necessary.
      if (width < 0) {
        width = 0 - width;
        x = x - width + 1;
        if (x < 0) {
          width += x;
          x = 0;
        }
      }
      if (height < 0) {
        height = 0 - height;
        y = y - height + 1;
        if (y < 0) {
          height += y;
          y = 0;
        }
      }

      //The rectangle shouldn't extend past the drawing area.
      if ((x + width) > compWidth) {
        width = compWidth - x;
      }
      if ((y + height) > compHeight) {
        height = compHeight - y;
      }

      //Update rectToDraw after saving old value.
      if (rectToDraw != null) {
        previousRectDrawn.setBounds(rectToDraw.x, rectToDraw.y,
            rectToDraw.width, rectToDraw.height);
        rectToDraw.setBounds(x, y, width, height);
      } else {
        rectToDraw = new Rectangle(x, y, width, height);
      }
    }  
    
    public void mousePressed(MouseEvent event)
    {
      int mouseX = event.getX();
      int mouseY = event.getY();
    //      System.out.println("MouseX: " + mouseX + " - Mouse Y: " + mouseY);
      Shape shape = new Line2D.Float(mouseX, mouseY, mouseX, mouseY);
      JPanel p = (JPanel)(event.getSource());
      currentRect = new Rectangle(mouseX, mouseY, 0, 0);
      updateDrawableRect(getWidth(), getHeight());
      repaint();
    }
    
    public void mouseReleased(MouseEvent event)
    {
      if (currentRect != null)
      {
        Point2D.Double puntoOrigen = toSpace(currentRect.x,currentRect.y);
        Point2D.Double puntoDestino = toSpace(currentRect.x+currentRect.width,currentRect.y+currentRect.height);
        System.out.println("Coordenadas del Rectangulo final: " + puntoOrigen.x + ", " + puntoOrigen.y + ";" + 
            puntoDestino.x + ", " + puntoDestino.y);
        Qwhere = " where within(the_geom,geometryfromtext('BOX3D(" + 
        puntoOrigen.x + " " + puntoOrigen.y + " 0," + 
        puntoDestino.x + " " + puntoDestino.y + " 0)',-1)) ";
        Qwhere = Qwhere + " or intersects(the_geom,geometryfromtext('BOX3D(" + 
        puntoOrigen.x + " " + puntoOrigen.y + " 0," + 
        puntoDestino.x + " " + puntoDestino.y + " 0)',-1)) ";
//        Qwhere += " union select parcela as label, AsText(Centroid(the_geom)) as centroid, AsText(the_geom) as the_geom,npoints(the_geom) from gis.parcelas_st " + Qwhere;
        currentRect = null;
        imagen = CreateImage();
        repaint();
      }
    }
    public void mouseEntered(MouseEvent event)
    {
    
    }
    public void mouseExited(MouseEvent event)
    {
    
    }
    public void mouseClicked(MouseEvent event)
    {
    
    }
    
    public void mouseMoved(MouseEvent event)
    {
    /*      int mouseX = event.getX();
      int mouseY = event.getY();
      double x1 = mouseX/Factor + xOffset;
      double y1 = (fHeight-mouseY)/Factor + yOffset;*/
      Point2D.Double punto = toSpace(event.getX(),event.getY());
      coords.setText("X: " + df.format(punto.x) + ", Y: " + df.format(punto.y) );
    }
   public void mouseDragged(MouseEvent event)
   {
      updateSize(event);
      int mouseX = event.getX();
      int mouseY = event.getY();
//      System.out.println("MouseX: " + mouseX + " - Mouse Y: " + mouseY);
      Shape shape = new Line2D.Float(mouseX, mouseY, mouseX, mouseY);
      Point2D.Double punto = toSpace(event.getX(),event.getY());
      coords.setText("X: " + df.format(punto.x) + ", Y: " + df.format(punto.y) );
   }
   void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        currentRect.setSize(x - currentRect.x, y - currentRect.y);
        updateDrawableRect(getWidth(), getHeight());
        Rectangle totalRepaint = rectToDraw.union(previousRectDrawn);
        repaint(totalRepaint.x, totalRepaint.y, totalRepaint.width, totalRepaint.height);
      }
      
  public void updateLabel(Rectangle rect) 
  {
    int width = rect.width;
    int height = rect.height;

/*    //Make the coordinates look OK if a dimension is 0.
    if (width == 0) {
      width = 1;
    }
    if (height == 0) {
      height = 1;
    }
    double x1 = rect.x/Factor+xOffset;
    double y1 = rect.y/Factor+yOffset;
    double x2 = (rect.x+width)/Factor+xOffset;
    double y2 = (rect.y+height)/Factor+yOffset;
    Point2D.Double puntoOrigen = toSpace(rect.x,rect.y);
    Point2D.Double puntoDestino = toSpace(rect.x+width-1,rect.y+height-1);
*/
    Point2D.Double puntoOrigen = toSpace(rect.x,rect.y);
    Point2D.Double puntoDestino = toSpace(rect.x+width,rect.y+height);

    label.setText("Coordenadas del Rectangulo: " + df.format(puntoOrigen.x) + ", " + df.format(puntoOrigen.y) + ";" + 
        df.format(puntoDestino.x) + ", " + df.format(puntoDestino.y));
  }

  private void jbInit() throws Exception
  {
    this.setLayout(verticalFlowLayout1);
    label.setHorizontalAlignment(SwingConstants.LEFT);
    coords.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(label, null);
    this.add(coords,null);
    imagen = CreateImage();
    this.setBackground(Color.WHITE);
  }

  public Image CreateImage()
  {
    BufferedImage rendImage = CreateMap();
    
    // Write generated image to a file
    try {
        // Save as PNG
        File file = new File("/tmp/jms/0000.png");
        //ImageIO.write(rendImage, "png", file);
    
        // Save as JPEG
        file = new File("/tmp/jms/0000.jpg");
        ImageIO.write(rendImage, "jpg", file);
    } catch (IOException e) 
    {
    }

    Image image = null;
    try {
        // Read from a file
        File file = new File("/tmp/jms/0000.jpg");
        image = ImageIO.read(file);
    } catch (IOException e) {
    }    
    return image;
  }
    // Returns a generated image.
    public BufferedImage CreateMap() {
    
        // Create a buffered image in which to draw
        BufferedImage bufferedImage = new BufferedImage(fWidth, fHeight, BufferedImage.TYPE_INT_RGB);
    
        // Create a graphics contents on the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, fWidth, fHeight);    
        // Draw graphics
/*************************************************************************/
    try {
      Connection Con = Proc.CCon();
//      String Data = "the_geom from (select the_geom, manzana, oid from gis.manzanas_sa) as foo";
      Data = Data.trim();
      String geometry = Data.substring(0,Data.indexOf(" ")).trim();
      Data = Data.substring(Data.indexOf(" ")).trim();
//      String LabelItem = "manzana";
      String Query = "select " + LabelItem + " AsText(centroid(" + geometry + ")) as centroid, AsText(" + geometry + ") as the_geom,npoints(" + geometry + ") " + Data + Qwhere /*+
      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sb " + Qwhere /*+
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sc" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sd" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_se" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sf" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sg" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sh" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sj" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sk" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sl" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sm" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sn" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_so" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sp" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sq" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_sr" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_ss" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_st" +
                      " union select AsText(the_geom) as the_geom,npoints(the_geom) from gis.manzanas_su"*/;
      System.out.println(Query);
      ResultSet Polygons = Proc.exConsulta(Con,Query);
      
      if (Qwhere == Qwhere) 
      {
        ResultSet Extent = Proc.exConsulta(Con,"select xmin(extent(" + geometry + ")), " +
                            "xmax(extent(" + geometry + ")), " +
                            "ymin(extent(" + geometry + ")), " +
                            "ymax(extent(" + geometry + ")) " + Data );
  
        Extent.next();
        
        xmin = Extent.getDouble("xmin");
        xmax = Extent.getDouble("xmax");
        ymin = Extent.getDouble("ymin");
        ymax = Extent.getDouble("ymax");
        extX = xmax - xmin;
        extY = ymax - ymin;
      }
      if (extX > extY) 
      {
        Factor = (fWidth-20) / extX;
      } else 
      {
        Factor = (fHeight-20) / extY;
      }
      xOffset = xmin;
      yOffset = ymin;
      System.out.println("ExtX: " + extX + " ExtY: " + extY);
      System.out.println("xFactor: " + Factor + " yFactor: " + Factor);
      System.out.println("xOffset: " + xOffset + " yOffset: " + yOffset);
      
      while (Polygons.next()) 
      {

      
        /**Polígono*/  
        int fNumPoints = Polygons.getInt("npoints");
        String Poly = Polygons.getString("the_geom");
//        Poly = Poly.substring(Poly.indexOf("(((")+3,Poly.length()-3); //Si es polígono cerrado, por ejemplo Manzana o Parcela
        Poly = Poly.substring(Poly.indexOf("((")+2,Poly.length()-3);  //Si es una polilínea, por ejemplo Calle o Río
        double [] xd = new double[fNumPoints];
        double [] yd = new double[fNumPoints];
        for (int i=0; i < fNumPoints-1; i++) 
        {
          xd[i] = Double.parseDouble(Poly.substring(0,Poly.indexOf(" ")).trim());
          yd[i] = Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.indexOf(",")).trim());
          Poly = Poly.substring(Poly.indexOf(",")+1,Poly.length());
        }
        xd[fNumPoints-1] = Double.parseDouble(Poly.substring(0,Poly.indexOf(" ")).trim());
        yd[fNumPoints-1] = Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.length()).trim());
        int[] x = new int[fNumPoints];
        int[] y = new int[fNumPoints];
        
        for (int i=0; i < fNumPoints; i++) 
        {
          x[i] = (int)( (xd[i] - xOffset) * Factor);
          y[i] = (int)( fHeight - ((yd[i] - yOffset) * Factor));
        }
                  Polygon poly = new Polygon(x,y,fNumPoints);
        
            // Then pass the polygon object for drawing
            g2d.setColor(color);
//            g2d.drawPolygon(poly); //Si es polígono cerrado
            g2d.drawPolyline(x,y,fNumPoints); //Si es una polilínea
        
            // Change the line color and draw the x-y axes
//            g.setColor(Color.green);

        try
        {
          String pLabel = Polygons.getString("label");
          if (pLabel.equalsIgnoreCase("Acceso Norte")) 
          {
            String Centroid = Polygons.getString("centroid");
            Centroid = Centroid.substring(Centroid.indexOf("(")+1,Centroid.length()-1);
            double xc = Double.parseDouble(Centroid.substring(0,Centroid.indexOf(" ")).trim());
            double yc = Double.parseDouble(Centroid.substring(Centroid.indexOf(" ")));
            Point centro = toPixel(xc,yc);
            //System.out.println(xc + "-" + yc);
            double Angulo = 0;            
            for (int i=0; i < fNumPoints-1; i++) 
            {
/*              int j=i+1;
              System.out.println("xd[" + i + "]: " + xd[i]);
              System.out.println("xc: " + xc);
              System.out.println("xd[" + j + "]: " + xd[j]);
              System.out.println("yd[" + i + "]: " + yd[i]);
              System.out.println("yc: " + yc);
              System.out.println("yd[" + j + "]: " + yd[j]);
*/              
              if (((xd[i] <= xc && xd[i+1] >= xc) || (xd[i] >= xc && xd[i+1] <= xc)) && ((yd[i] <= yc && yd[i+1] >= yc) || (yd[i] >= yc && yd[i+1] <= yc)))
              {
                //Cálculo del ángulo del label
                double Base = Math.abs(xd[i] - xd[i+1]);
                double Altura = Math.abs(yd[i] - yd[i+1]);
                Angulo = Math.atan(Altura/Base);
                System.out.println(Math.toDegrees(Angulo));
              }
            }


            FontMetrics fm = g2d.getFontMetrics(font);
            int lHeight = fm.getHeight();
            int lWidth = fm.stringWidth(pLabel);
  
            int xl = (int)centro.getX()-(int)lWidth/2;
            int yl = (int)centro.getY()+lHeight/2;
            AffineTransform at = AffineTransform.getRotateInstance(Angulo, xl, yl);
            g2d.setTransform(at);
            g2d.drawString(pLabel,xl,yl);
            at = at.getRotateInstance(0, xl, yl);
            g2d.setTransform(at);
          }
        } catch (NullPointerException e) 
        {
          
        }

      }
    } catch (SQLException x) 
    {
      Proc.Mensaje(x.getErrorCode() + ": " + x.getMessage(), "Error en la consulta SQL");
    }
/***********************************************************************************/
    
        // Graphics context no longer needed so dispose it
        g2d.dispose();
    
        return bufferedImage;
    }
    
    private Point toPixel(double xd, double yd) 
    {
      int x = (int)( (xd - xOffset) * Factor);
      int y = (int)( fHeight - ((yd - yOffset) * Factor));
      Point xy = new Point(x,y);
      return xy;
    }
    
    private Point2D.Double toSpace(int x, int y) 
    {
      double xd = x/Factor + xOffset;
      double yd = (fHeight-y)/Factor + yOffset;
      Point2D.Double xy = new Point2D.Double(xd,yd);
      return xy;
    }
} // class Polygon2Panel