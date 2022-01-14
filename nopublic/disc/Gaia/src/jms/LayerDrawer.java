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
 * LayerDrawer.java
 *
 * */
package jms;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.AttributedString;
//import javax.media.jai.GraphicsJAI;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import org.jibble.epsgraphics.*;

public class LayerDrawer extends JFrame implements MouseListener, MouseMotionListener, MouseWheelListener
{

  private JLabel label = new JLabel();
  private JLabel coords = new JLabel();
  Rectangle currentRect = null;
  Rectangle rectToDraw = null;  
  Rectangle previousRectDrawn = new Rectangle();

  double xmin=0;
  double xmax=0;
  double ymin=0;
  double ymax=0;
  double extX=0;
  double extY=0;
  double Factor = 0;
  double xOffset = 0;
  double yOffset = 0;
  DecimalFormat df = new DecimalFormat("0.0000");
  int fWidth=800,fHeight=600;
  int fNumPoints;
  double fFactor;
  Color color;
  public final static Font font=new Font("Courier",Font.BOLD,14);
  public final static Color DEFAULT_COLOR=Color.black;
  protected GeneralPath[] paths; 
  protected Polygon[] polys; 
  protected Color[] fills; 
  int numPaths;
  protected Point2D mSelectedPoint; 
  int[] selectedPath;
  int numSelPaths=0;
  LayerObject[] layer = new LayerObject[1];
  BufferedImage image = null;
  boolean plotear = false;
  boolean finDrawing = false;
//  private String ruta = "C:\\temp\\jms\\";
  private String ruta = "/tmp/jms/";
  private String archivo = "";
  private boolean loaded = false;
  private String Qwhere = "";
  private BufferedImage imagen = new BufferedImage(fWidth, fHeight, BufferedImage.TYPE_BYTE_INDEXED);
  private int numLayers=0;
  Extent extents = new Extent();
  private double ScaleX = 1, ScaleY = 1;
  private int centerx = 0, centery = 0;

  String status = "";
  int max = 0;
  int current = 0;
  final Barra progress = new Barra(max, current, this);
  
 
  public LayerDrawer()
  {
//    super("DragKing v1.0"); 
    setSize(800, 800); 
    center();
    setVisible(false);
//    doPolys();
    addMouseListener(this);
    addMouseMotionListener(this);
    addMouseWheelListener(this);
    repaint();
  }

  public LayerDrawer(LayerObject l)
  {
//    super("DragKing v1.0"); 
    setSize(800, 800); 
    center();
    setVisible(true);
    numLayers = 1;
    layer[0] = l;
    plotear = false;
    rundoImage();
    addMouseListener(this);
    addMouseMotionListener(this);
    addMouseWheelListener(this);
    label.setBounds(800,600,200,20);
    getContentPane().add(label);
    
    repaint();
  }

  public LayerDrawer(MapObject map, BufferedImage bfdImg)
  {
    xOffset = -1;
    yOffset = -1;
    setSize(800, 800); 
    center();
    setVisible(true);
//    imagen = new BufferedImage(bfdImg.getWidth(), bfdImg.getHeight(), BufferedImage.TYPE_BYTE_INDEXED);
    numLayers = map.getLayerCount();
    layer = new LayerObject[numLayers];
    for (int i=0; i<numLayers; i++) 
    {
      layer[i] = map.getLayerObject(i);
    }
    extents.setStrExtension(map.getExtent().getStrExtension());
    rundoImage();
    addMouseListener(this);
    addMouseMotionListener(this);
    addMouseWheelListener(this);
    repaint();
  }

/*  public BufferedImage drawLayers(MapObject map, BufferedImage bfdImg)
  {
    xOffset = -1;
    yOffset = -1;
    imagen = new BufferedImage(bfdImg.getWidth(), bfdImg.getHeight(), BufferedImage.TYPE_BYTE_INDEXED);
    numLayers = map.getLayerCount();
    layer = new LayerObject[numLayers];
    for (int i=0; i<numLayers; i++) 
    {
      layer[i] = map.getLayerObject(i);
    }
    extents = map.getExtent();
    rundoImage();
    return bfdImg;
  }*/

  public void paint(Graphics g) 
  {
    Graphics2D g2d = (Graphics2D)g;
    if (!loaded) 
    {
      try
      {
        GeneralPath path = new GeneralPath();
        if ((finDrawing) && (!archivo.equals(""))) 
        {
          try {
              // Read from a file
              File file = new File(archivo);
              imagen = ImageIO.read(file);
              loaded = true;
              System.out.println("Imagen cargada...");
//              if (!isVisible()) setVisible(true);
              g2d.drawImage(imagen,0,0,(int)(fWidth*ScaleX),(int)(fHeight*ScaleX),null);
          } catch (IOException e) 
          {
            loaded = false;
          }
        }
      } catch (Exception x) 
      {
        x.printStackTrace();
      }
    } else 
    {
      if (!isVisible()) setVisible(true);
      if (ScaleX<1) 
      {
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,fWidth, fHeight);
      }
      g2d.drawImage(imagen,centerx,centery,(int)(fWidth*ScaleX),(int)(fHeight*ScaleX),null);
      getEscala(extX, extY, g2d);
      if (currentRect != null) {
        //Draw a rectangle on top of the image.
        g.setXORMode(Color.white); //Color of line varies
        //depending on image colors
        g.drawRect(rectToDraw.x, rectToDraw.y, rectToDraw.width - 1, rectToDraw.height - 1);
        updateLabel(rectToDraw);
      }       
    }
    //repaint();
  }

  private void drawPolygons(Graphics2D g2d, ResultSet Polygons, boolean withLabels, LayerObject myLayer)
  {
    numPaths = 0;
    try 
    {
      while (Polygons.next()) 
      {

        //Cantidad de puntos del polígono
        int fNumPoints = Polygons.getInt("npoints");
        //Puntos del polígono
        String Poly = Polygons.getString("the_geom");
        //Si es polígono cerrado, por ejemplo Manzana o Parcela
        Poly = Poly.substring(Poly.indexOf("(((")+3,Poly.length()-3);

        //Si es una polilínea, por ejemplo Calle o Río        
        //Poly = Poly.substring(Poly.indexOf("((")+2,Poly.length()-3);

        //Obtengo todos los puntos del registro
        //Podria usar la clase StringTokenizer???
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

        //Obtengo el polígono con los puntos
        polys[0] = getPolyFromPoints(xd, yd, fNumPoints);
        
        //Esta sentencia corre la evaluación del atributo 
        //"expression" de una clase del layer
        //Esto hay que revisar, pues es un poco rebuscado todavía
        MapColor fillColor = myLayer.getFillColorByClass(Polygons);
        if (fillColor == null) 
        {
          fills[0] = myLayer.getClassObject("color").getStyleObject().getOutLineColor();
        } else 
        {
          fills[0] = fillColor.getColor();
        }
        if (fills[0] != myLayer.getClassObject("color").getStyleObject().getOutLineColor()) 
        {
          //Pinto el polígono
          g2d.setColor(Color.white);
          //g2d.fill(polys[0]);
        }

        //Condición para dibujar los contornos >20 píxels de ancho y >20 píxels de alto
        if (((polys[0].getBounds2D().getWidth() > 20) && (polys[0].getBounds2D().getHeight() > 20)))
        {
          //Dibujo el contorno del polígono
          g2d.setColor(myLayer.getClassObject("color").getStyleObject().getOutLineColor());
          g2d.draw(polys[0]);
        }

        numPaths++;
        progress.setCurrent(numPaths);
        progress.setStatus("Dibujando el layer " + myLayer.getName() + ", objetos: " + numPaths + " de " + max);
      }
    } catch (SQLException x) 
    {
      System.out.println("Error dibujando poligonos: [drawPolygons(Graphics2D, ResultSet)]");
    }
  }

  private void drawPolylines(Graphics2D g2d, ResultSet Polylines, boolean withLabels, LayerObject myLayer)
  {
    numPaths = 0;
    try 
    {
      while (Polylines.next()) 
      {

        //Cantidad de puntos de la polilínea
        int fNumPoints = Polylines.getInt("npoints");
        //Puntos de la polilínea
        String Poly = Polylines.getString("the_geom");

        //Si es una polilínea, por ejemplo Calle o Río        
        Poly = Poly.substring(Poly.indexOf("((")+2,Poly.length()-3);

        //Obtengo todos los puntos del registro
        //Podria usar la clase StringTokenizer???
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

        //Obtengo la polilínea con los puntos
        int[] x = new int[fNumPoints];
        int[] y = new int[fNumPoints];
        for (int i=0; i < fNumPoints; i++) 
        {
          x[i] = (int)( (xd[i] - xOffset) * Factor);
          y[i] = (int)( fHeight - ((yd[i] - yOffset) * Factor));
        }
        
        //Esta sentencia corre la evaluación del atributo 
        //"expression" de una clase del layer
        //Esto hay que revisar, pues es un poco rebuscado todavía
        MapColor fillColor = myLayer.getFillColorByClass(Polylines);
        if (fillColor == null) 
        {
          fills[0] = myLayer.getClassObject("color").getStyleObject().getOutLineColor();
        } else 
        {
          fills[0] = fillColor.getColor();
        }
//        if (fills[0] != layer.getClassObject("color").getStyleObject().getOutLineColor()) 
        {
          //Pinto la polilínea
//          g2d.setColor(fills[0]);
//          g2d.fill(polys[0]);
        }

        //Condición para dibujar los contornos >20 píxels de ancho y >20 píxels de alto
//        if (((polys[0].getBounds2D().getWidth() > 20) && (polys[0].getBounds2D().getHeight() > 20)))
//        {
          //Dibujo la polilínea
          g2d.setColor(fills[0]);
          g2d.drawPolyline(x, y, fNumPoints);
//        }

        numPaths++;
        progress.setCurrent(numPaths);
        progress.setStatus("Dibujando el layer " + myLayer.getName() + ", objetos: " + numPaths + " de " + max);

        /************************** ETIQUETAS ********************************/
        polys[0] = getPolyFromPoints(xd, yd, fNumPoints);
        if ((withLabels) && (((polys[0].getBounds2D().getWidth() > 20) || (polys[0].getBounds2D().getHeight() > 20)) || myLayer.getClassObject("color").getLabelObject().getForce()))
        {
          try
          {
            String pLabel = Polylines.getString("label");
            String Centroid = Polylines.getString("centroid");
            Centroid = Centroid.substring(Centroid.indexOf("(")+1,Centroid.length()-1);
            double xc = Double.parseDouble(Centroid.substring(0,Centroid.indexOf(" ")).trim());
            double yc = Double.parseDouble(Centroid.substring(Centroid.indexOf(" ")));
            Point centro = toPixel(xc,yc);
            //System.out.println(xc + "-" + yc);
            double Angulo = 0;            
            for (int i=0; i < fNumPoints-1; i++) 
            {
/*            
              int j=i+1;
              System.out.println("xd[" + i + "]: " + xd[i]);
              System.out.println("xc: " + xc);
              System.out.println("xd[" + j + "]: " + xd[j]);
              System.out.println("yd[" + i + "]: " + yd[i]);
              System.out.println("yc: " + yc);
              System.out.println("yd[" + j + "]: " + yd[j]);
*/              
              if (((xd[i] <= xc && xd[i+1] >= xc) || (xd[i] >= xc && xd[i+1] <= xc)))// && ((yd[i] <= yc && yd[i+1] >= yc) || (yd[i] >= yc && yd[i+1] <= yc)))
              {
                //Cálculo del ángulo del label
                double Base = (xd[i] - xd[i+1]);
                double Altura = (yd[i] - yd[i+1]);
                Angulo = Math.atan(-Altura/Base);
//                System.out.println(pLabel + " - " + Angulo);
//                if (Angulo < 90) System.out.println(Math.toDegrees(Angulo));
//                  else if ((Angulo < 270) && (Angulo > 180)) System.out.println(Math.toDegrees(Angulo));
              }
            }


            FontMetrics fm = g2d.getFontMetrics(font);
            int lHeight = fm.getHeight();
            int lWidth = fm.stringWidth(pLabel);

            int xl = (int)centro.getX()-(int)lWidth/2;
            int yl = (int)centro.getY()+lHeight/2;
//            AttributedString str = new AttributedString(pLabel);
//            str.addAttribute(TextAttribute.BACKGROUND, Color.white);
//            str.addAttribute(TextAttribute.FOREGROUND, layer.getClassObject("color").getLabelObject().getColor());
//            str.addAttribute(TextAttribute.SIZE, "14");
//            g2d.setColor(Color.black);
//            g2d.drawOval((int)centro.getX()-11,(int)centro.getY()-11,22,22);
//            g2d.setColor(Color.white);
//            g2d.fillOval((int)centro.getX()-11,(int)centro.getY()-11,22,22);
            AffineTransform at = AffineTransform.getRotateInstance(Angulo, xl, yl);
            g2d.setTransform(at);
            g2d.setColor(myLayer.getClassObject("color").getLabelObject().getColor());
            g2d.drawString(pLabel,xl,yl);
            at = at.getRotateInstance(0, xl, yl);
            g2d.setTransform(at);
          } catch (NullPointerException e) 
          {
            e.printStackTrace();
          }
        }
      }
    } catch (SQLException x) 
    {
      System.out.println("Error dibujando poligonos: [drawPolylines(Graphics2D, ResultSet, WithLabels)]");
    }
  }

  public Graphics2D doImage(Graphics2D g2d, LayerObject myLayer) 
  {
    int tipoLayer = myLayer.getTypeInt();
    switch (tipoLayer)
    {
      case 0: Proc.Mensaje("Error en el tipo de Layer","Dibujar Layer"); return null;
      case 1: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); return null;
      case 4: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); return null;
      case 5: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); return null;
      case 6: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); return null;
      case 7: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); return null;
    }
    progress.setStatus("Inicializando...");

/*    FileOutputStream outputStream;
    try 
    {
      // Save this document to example.eps
      outputStream = new FileOutputStream("/tmp/jms/example.eps");
      g2d = new EpsGraphics2D("Example", outputStream, 0, 0, fWidth, fHeight);
    } catch (Exception x) 
    {
      
    }*/
    // Create a new document with bounding box 0 <= x <= 100 and 0 <= y <= 100.
    // Create a graphics contents on the buffered image
  

    try {
      progress.setStatus("Conectando con la base de datos...");
      Connection Con = Proc.CCon();
      
      String Data = myLayer.getData();
      Data = Data.trim();
      String geometry = Data.substring(0,Data.indexOf(" ")).trim();
      Data = Data.substring(Data.indexOf(" ")).trim();

      //Preparo el campo label si existe
      String LabelItem = myLayer.getLabelItem();
      if (LabelItem.trim().length()>0)
      {
        LabelItem += " as label, ";
      }

      //La condicion por ahora es vacía
//      String Qwhere = "";
      
      //Preparo la consulta para solicitar los objetos a la base de datos      
      String Query = "select " + LabelItem + " AsText(centroid(" + geometry + ")) as centroid, AsText(" + geometry + ") as the_geom,npoints(" + geometry + ") " + myLayer.getExpressions() + Data + Qwhere;

      progress.setStatus("Contando objetos...");

      //Cuento los objetos
      ResultSet count = Proc.exConsulta(Con, "select count(*) from (" + Query + ") as foo");
      count.next();

      //max sirve para la barra de progreso
      max = count.getInt(1);
      progress.setMax(max);

//      paths = new GeneralPath[count.getInt(1)];
      polys = new Polygon[1];
//      selectedPath = new int[count.getInt(1)];

      fills = new Color[1];
      progress.setStatus("Cargando objetos...");
      
      //Solicito los objetos a la base de datos
      ResultSet Polygons = Proc.exConsulta(Con,Query);
      
      //Calculo la extensión máxima N-S-E-O
      if ((xOffset == 0.0) && (yOffset == 0.0)) 
      {
        ResultSet Extent = Proc.exConsulta(Con,"select xmin(extent(" + geometry + ")), " +
                            "xmax(extent(" + geometry + ")), " +
                            "ymin(extent(" + geometry + ")), " +
                            "ymax(extent(" + geometry + ")) " + Data + Qwhere );
  
        Extent.next();
        
        xmin = Extent.getDouble("xmin");
        xmax = Extent.getDouble("xmax");
        ymin = Extent.getDouble("ymin");
        ymax = Extent.getDouble("ymax");
        extX = xmax - xmin;
        extY = ymax - ymin;
      } else 
      {
        xmin = extents.getMinX();
        xmax = extents.getMaxX();
        ymin = extents.getMinY();
        ymax = extents.getMaxY();
        extX = xmax - xmin;
        extY = ymax - ymin;
      }
      //Calculo el factor para escalar los polígonos
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

      //Dibujo los objetos
      // Tipo de layer: 1: point - 2: line - 3: polygon - 4: circle - 5: annotation - 6: raster - 7: query 
      boolean withLabels;
      if (LabelItem.equals("")) 
      {
        withLabels = false; 
      } else {
        withLabels = true;
      }
      switch (tipoLayer)
      {
        case 0: Proc.Mensaje("Error en el tipo de Layer","Dibujar Layer"); break;
        case 1: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); break;
        case 2: drawPolylines(g2d, Polygons, withLabels, myLayer); break;
        case 3: drawPolygons(g2d, Polygons, withLabels, myLayer); break;
        case 4: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); break;
        case 5: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); break;
        case 6: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); break;
        case 7: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); break;
      }

        /************************** ETIQUETAS ********************************/

/*        if (((polys[0].getBounds2D().getWidth() > 20) && (polys[0].getBounds2D().getHeight() > 20)) || layer.getClassObject("color").getLabelObject().getForce())
        {
          try
          {
            String pLabel = Polygons.getString("label");
            String Centroid = Polygons.getString("centroid");
            Centroid = Centroid.substring(Centroid.indexOf("(")+1,Centroid.length()-1);
            double xc = Double.parseDouble(Centroid.substring(0,Centroid.indexOf(" ")).trim());
            double yc = Double.parseDouble(Centroid.substring(Centroid.indexOf(" ")));
            Point centro = toPixel(xc,yc);
            //System.out.println(xc + "-" + yc);
            double Angulo = 0;            
            for (int i=0; i < fNumPoints-1; i++) 
            {
/*            
              int j=i+1;
              System.out.println("xd[" + i + "]: " + xd[i]);
              System.out.println("xc: " + xc);
              System.out.println("xd[" + j + "]: " + xd[j]);
              System.out.println("yd[" + i + "]: " + yd[i]);
              System.out.println("yc: " + yc);
              System.out.println("yd[" + j + "]: " + yd[j]);
*/              
/*              if (((xd[i] <= xc && xd[i+1] >= xc) || (xd[i] >= xc && xd[i+1] <= xc)))// && ((yd[i] <= yc && yd[i+1] >= yc) || (yd[i] >= yc && yd[i+1] <= yc)))
              {
                //Cálculo del ángulo del label
                double Base = Math.abs(xd[i] - xd[i+1]);
                double Altura = Math.abs(yd[i] - yd[i+1]);
//                Angulo = Math.atan(Altura/Base);
//                if (Angulo < 90) System.out.println(Math.toDegrees(Angulo));
//                  else if ((Angulo < 270) && (Angulo > 180)) System.out.println(Math.toDegrees(Angulo));
              }
            }


            FontMetrics fm = g2d.getFontMetrics(font);
            int lHeight = fm.getHeight();
            int lWidth = fm.stringWidth(pLabel);

            int xl = (int)centro.getX()-(int)lWidth/2;
            int yl = (int)centro.getY()+lHeight/2;
//            AttributedString str = new AttributedString(pLabel);
//            str.addAttribute(TextAttribute.BACKGROUND, Color.white);
//            str.addAttribute(TextAttribute.FOREGROUND, layer.getClassObject("color").getLabelObject().getColor());
//            str.addAttribute(TextAttribute.SIZE, "14");
//            g2d.setColor(Color.black);
//            g2d.drawOval((int)centro.getX()-11,(int)centro.getY()-11,22,22);
//            g2d.setColor(Color.white);
//            g2d.fillOval((int)centro.getX()-11,(int)centro.getY()-11,22,22);
            AffineTransform at = AffineTransform.getRotateInstance(Angulo, xl, yl);
            g2d.setTransform(at);
            g2d.setColor(layer.getClassObject("color").getLabelObject().getColor());
            g2d.drawString(pLabel,xl,yl);
            at = at.getRotateInstance(0, xl, yl);
            g2d.setTransform(at);
          } catch (NullPointerException e) 
          {
            e.printStackTrace();
          }
        }*/
      //}
    } catch (SQLException x) 
    {
      Proc.Mensaje(x.getErrorCode() + ": " + x.getMessage(), "Error en la consulta SQL");
    }
    progress.setMax(0);
    progress.setStatus("Imagen generada... grabando");
//    System.out.println("EPS POSTSCRIPT!!!:");
//    System.out.println(g2d.toString());
    // Flush and close the document (don't forget to do this!)
    try 
    {
//      g2d.flush();
//      g2d.close();
    } catch (Exception x) 
    {
    }
    return g2d;
/***********************************************************************************/
  }

  
  public Graphics2D getEscala(double x, double y, Graphics2D g2d)
  {
    int divisiones = 4;
    double mtsxpixel = x/fWidth;
    int size = (int)((fWidth/divisiones)*mtsxpixel);
    String unidad = "";
    if (x>10000) //Kilómetros
    {
      size = (size/1000);
      unidad = "Km";
    } else //Metros
    {
      unidad = "m";      
    }
    g2d.setColor(Color.magenta);
    g2d.fillRect(0, fHeight-10, (fWidth/divisiones), 5);
    g2d.fillRect(2*(fWidth/divisiones), fHeight-10, (fWidth/divisiones), 5);
    g2d.setColor(Color.cyan);
    g2d.fillRect((fWidth/divisiones), fHeight-10, (fWidth/divisiones), 5);
    g2d.fillRect(3*(fWidth/divisiones), fHeight-10, (fWidth/divisiones), 5);
    g2d.setColor(Color.black);
    g2d.drawLine(0*(fWidth/divisiones)+2,fHeight, 0*(fWidth/divisiones)+2, fHeight-15);
    g2d.drawLine(1*(fWidth/divisiones),fHeight, 1*(fWidth/divisiones), fHeight-15);
    g2d.drawLine(2*(fWidth/divisiones),fHeight, 2*(fWidth/divisiones), fHeight-15);
    g2d.drawLine(3*(fWidth/divisiones),fHeight, 3*(fWidth/divisiones), fHeight-15);
    g2d.drawLine(4*(fWidth/divisiones)-2, fHeight, 4*(fWidth/divisiones)-2, fHeight-15);
    g2d.drawString(String.valueOf(0*size) + unidad,0*(fWidth/divisiones)+2, fHeight-15);
    g2d.drawString(String.valueOf(1*size) + unidad,1*(fWidth/divisiones)+2, fHeight-15);
    g2d.drawString(String.valueOf(2*size) + unidad,2*(fWidth/divisiones)+2, fHeight-15);
    g2d.drawString(String.valueOf(3*size) + unidad,3*(fWidth/divisiones)+2, fHeight-15);
    g2d.drawString(String.valueOf(4*size) + unidad,4*(fWidth/divisiones)-30, fHeight-15);
// 
    return g2d;
  }

  public GeneralPath getPathFromPoints(double[] xp, double[] yp, int cantidad) 
  {
    GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
    int[] x = new int[cantidad];
    int[] y = new int[cantidad];
    for (int i=0; i < cantidad; i++) 
    {
      x[i] = (int)( (xp[i] - xOffset) * Factor);
      y[i] = (int)( fHeight - ((yp[i] - yOffset) * Factor));
      if (i == 0) 
      {  
        path.moveTo(x[i],y[i]); 
      } else {
        if (i == fNumPoints-1) 
        {
          path.closePath(); 
        } else {
          path.lineTo(x[i],y[i]);
        }
      }
    }
    return path;
  } 

  public Polygon getPolyFromPoints(double[] xp, double[] yp, int cantidad) 
  {
    int[] x = new int[cantidad];
    int[] y = new int[cantidad];
    for (int i=0; i < cantidad; i++) 
    {
      x[i] = (int)( (xp[i] - xOffset) * Factor);
      y[i] = (int)( fHeight - ((yp[i] - yOffset) * Factor));
    }
    Polygon poly = new Polygon(x, y, cantidad);
    return poly;
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

  protected Shape getControlPoint(Point2D p) 
  { 
    // Create a small square around the given point. 
    int side = 4; 
    return new Rectangle2D.Double( p.getX() - side / 2, p.getY() - side / 2, side, side); 
  } 
  
/*  public void mouseClicked(MouseEvent me) {} 
  
  public void mousePressed(MouseEvent me) 
  { 
    for (int i=0; i<paths.length; i++) 
    {
      if (paths[i].contains(me.getPoint())) 
//      if (paths[i].intersects(me.getPoint()))
      {
        Graphics2D g2d = (Graphics2D)this.getGraphics();
        GeneralPath path = new GeneralPath();
        g2d.setColor(Color.red);
        g2d.fill(polys[i]);
        g2d.setColor(Color.black);
        g2d.draw(polys[i]);
        g2d.dispose();
        selectedPath[numSelPaths] = i;
        numSelPaths++;
      }
    }
  } 
  
  public void mouseReleased(MouseEvent me) {} 

  public void mouseMoved(MouseEvent me) {} 
  
  public void mouseDragged(MouseEvent me) 
  { 
    if (mSelectedPoint != null) 
    { 
      mSelectedPoint.setLocation(me.getPoint()); 
      repaint(); 
    } 
  }
  
  public void mouseEntered(MouseEvent me) {} 
  
  public void mouseExited(MouseEvent me) {} 
*/
  public void drawLabel(String label, Polygon poly) 
  {
    
  }

  synchronized public void rundoImage() 
  {
    try
    {
      Runnable work = new Runnable()
      {
        public void run()
        {
          Thread tarea = new Thread(new Runnable() {
            public void run() 
            {
              BufferedImage bufferedImage = imagen;
              //Si es para plotear, el tamaño de la imagen será mucho mayor
              fWidth = bufferedImage.getWidth();
              fHeight = bufferedImage.getHeight();
              if (plotear) 
              {
                fWidth = fWidth * 7;
                fHeight = fHeight * 7;
              }
              Graphics2D g2d = bufferedImage.createGraphics();
              //Color de Fondo
              g2d.setColor(Color.white);
              //Relleno la imagen con el color de fondo
              g2d.fillRect(0, 0, fWidth, fHeight);
              for (int i=0; i<numLayers; i++) 
              {
                progress.setMax(0);
                doImage(g2d, layer[i]);
              }
              g2d.dispose();

              // Write generated image to a file
              try 
              {
                // Save as PNG
//                DecimalFormat dxf = new DecimalFormat("0.0000");
//                archivo = ruta + "l-" + dxf.format(Math.random()*10000) + ".png";
                archivo = ruta + "l-" + Math.random()*10000 + ".png";
                File file = new File(archivo);
                ImageIO.write(imagen, "png", file);
                progress.setStatus("Imagen grabada... Cargando imagen...");
              } catch (IOException e) 
              {
                e.printStackTrace();
              }    
              finDrawing = true;
              progress.setModal(false);
              progress.disposeme();
              repaint();
            }
          });
          progress.show();
          progress.iniciar(tarea);
          progress.setModal(true);
          if (progress.cancelar) 
          {
           progress.dispose();
           repaint();
          }
        }
      };
      work.run();
    } catch (Exception x)
    {
      this.setEnabled(true);
      System.out.println("Error");
      x.printStackTrace();
    }
    repaint();
  }                  
 
/*   public void paintComponent(Graphics g) {
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
    
  } // paintComponent*/

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
//      JPanel p = (JPanel)(event.getSource());
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
        extents.setStrExtension(puntoOrigen.x + " " + puntoOrigen.y + " " + puntoDestino.x + " " + puntoDestino.y);
        currentRect = null;
        
//        imagen = CreateImage();
        finDrawing = false;
        loaded = false;
        image = null;
//        .setStrExtension();
        rundoImage();
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
 
  public void center() 
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = getSize();
    setLocation((screenSize.width - frameSize.width)/2,(screenSize.height - frameSize.height)/2);
  }
  
  public void mouseWheelMoved(MouseWheelEvent e) 
  {
    if (loaded) 
    {
    ScaleX += -.1*e.getWheelRotation();
    repaint();
/*    int x = e.getX();
    int y = e.getY();
    centerx = x/2;
    centery = y/2;*/
    }
  }
  
}