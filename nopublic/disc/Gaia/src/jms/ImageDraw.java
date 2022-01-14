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
 * ImageDraw.java
 *
 * */
package jms;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;

public class ImageDraw// extends BufferedImage
{

  Extent extents = new Extent();
  private String Qwhere = "";
  int max = 0;
  double xOffset = 0;
  double yOffset = 0;
  int fWidth = 800,fHeight = 600;
  double Factor = 0;
  protected Polygon[] polys; 
  protected Color[] fills; 
  Extent myExtent = new Extent();
  private boolean firstLoad = true;  
  double extX = 0;
  double extY = 0;
  Extent origExt = new Extent();
  double minFactor = 0;
  int numPaths;
  public final static Font font=new Font("Courier",Font.BOLD,14);

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
    //progress.
//    setStatus("Inicializando...");
    
//    statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/reloj.gif")));
    

    try {
      //progress.
//      statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/connecting.png")));
//      setStatus("Conectando con la base de datos...");

      Connection Con = Proc.CCon();
//      statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/connected.png")));
      
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
      String Query = "select " + LabelItem + " astext(force_2d(intersection(" + geometry + ", " + extents.getBox3D() + "))) as intersection, npoints(intersection(" + geometry + ", " + extents.getBox3D() + ")) as npointsintersection, astext(force_2d(centroid(intersection(" + geometry + ", " + extents.getBox3D() + ")))) as centroidintersection, AsText(centroid(" + geometry + ")) as centroid, AsText(" + geometry + ") as the_geom, npoints(" + geometry + ") " + myLayer.getExpressions() + Data + Qwhere;

      //progress.
//      setStatus("Contando objetos...");
//      statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/lupa.gif")));

      //Cuento los objetos
      ResultSet count = Proc.exConsulta(Con, "select count(*) from (" + Query + ") as foo");
      count.next();

      //max sirve para la barra de progreso
      max = count.getInt(1);
      //progress.
//      setMax(max);

//      paths = new GeneralPath[count.getInt(1)];
      polys = new Polygon[1];
//      selectedPath = new int[count.getInt(1)];

      fills = new Color[1];
      //progress.
//      setStatus("Cargando objetos...");
//      statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/working.gif")));
      
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
        myExtent.setExtension(Extent.getDouble("xmin"), Extent.getDouble("xmax"), Extent.getDouble("ymin"), Extent.getDouble("ymax"));
      } else 
      {
        myExtent.setExtension(extents.getMinX(), extents.getMaxX(), extents.getMinY(), extents.getMaxY());
      }
      if (firstLoad) 
      {
        origExt.setStrExtension(myExtent.getStrExtension());
        firstLoad = false;
      }

      extX = myExtent.getMaxX() - myExtent.getMinX();
      extY = myExtent.getMaxY() - myExtent.getMinY();

      //Calculo el factor para escalar los polígonos
      if (extX > extY) 
      {
        Factor = (fWidth-20) / extX;
      } else 
      {
        Factor = (fHeight-20) / extY;
      }
      if (minFactor == 0) 
      {
        minFactor = Factor;
      }
      xOffset = myExtent.getMinX();
      yOffset = myExtent.getMinY();
      System.out.println("ExtX: " + extX + " ExtY: " + extY);
      System.out.println("Factor: " + Factor + " minFactor: " + minFactor);
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
    } catch (SQLException x) 
    {
      Proc.Mensaje(x.getErrorCode() + ": " + x.getMessage(), "Error en la consulta SQL");
    }
    //progress.
//    setMax(0);
    //progress.
//    setStatus("Imagen generada... grabando");
//    statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/grabar.png")));
    return g2d;
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
          g2d.setColor(fills[0]);//REVISAR!!!//REVISAR!!!
//          if (fills[0] != Color.black) g2d.fill(polys[0]); //REVISAR!!!//REVISAR!!!
        }

        //Condición para dibujar los contornos >20 píxels de ancho y >20 píxels de alto
        if (((polys[0].getBounds2D().getWidth() > 20) && (polys[0].getBounds2D().getHeight() > 20)))
        {
          //Dibujo el contorno del polígono
          g2d.setColor(myLayer.getClassObject("color").getStyleObject().getOutLineColor());
          g2d.draw(polys[0]);
        }

        numPaths++;
        //progress.
//        setCurrent(numPaths);
        //progress.
//        setStatus("Dibujando el layer " + myLayer.getName() + ", objetos: " + numPaths + " de " + max);
//        statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/dibuja.png")));
        /************************** ETIQUETAS ********************************/
        polys[0] = getPolyFromPoints(xd, yd, fNumPoints);
        if ((withLabels) && (((polys[0].getBounds2D().getWidth() > 20) || (polys[0].getBounds2D().getHeight() > 20)) || myLayer.getClassObject("color").getLabelObject().getForce()))
        {
          try
          {
            String pLabel = Polygons.getString("label");
            String Centroid = Polygons.getString("centroid");
            Centroid = Centroid.substring(Centroid.indexOf("(")+1,Centroid.length()-1);
            double xc = Double.parseDouble(Centroid.substring(0,Centroid.indexOf(" ")).trim());
            double yc = Double.parseDouble(Centroid.substring(Centroid.indexOf(" ")));
            Point centro = toPixel(xc,yc);
            FontMetrics fm = g2d.getFontMetrics(font);
            int lHeight = fm.getHeight();
            int lWidth = fm.stringWidth(pLabel);
//            int xl = (int)centro.getX();
//            int yl = (int)centro.getY();
            int xl = (int)centro.getX()-(int)lWidth/2;
            int yl = (int)centro.getY()+lHeight/2;
            g2d.setColor(myLayer.getClassObject("color").getLabelObject().getColor());
            g2d.drawString(pLabel,xl,yl);
          } catch (NullPointerException e) 
          {
            e.printStackTrace();
          }
        }
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
        int fNumPoints = Polylines.getInt("npointsintersection");
        //Puntos de la polilínea

        String Poly = Polylines.getString("intersection");
        while (Poly.indexOf("(")>0) 
        {
          Poly = Poly.substring(Poly.indexOf("(")+1,Poly.length()-1);
        }
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
        //progress.
//        setCurrent(numPaths);
        //progress.
//        setStatus("Dibujando el layer " + myLayer.getName() + ", objetos: " + numPaths + " de " + max);
//        statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/dibuja.png")));

        /************************** ETIQUETAS ********************************/
        polys[0] = getPolyFromPoints(xd, yd, fNumPoints);
        if ((withLabels) && (((polys[0].getBounds2D().getWidth() > 20) || (polys[0].getBounds2D().getHeight() > 20)) || myLayer.getClassObject("color").getLabelObject().getForce()))
        {
          try
          {
            String pLabel = Polylines.getString("label");
            String Centroid = Polylines.getString("centroidintersection");
            Centroid = Centroid.substring(Centroid.indexOf("(")+1,Centroid.length()-1);
            double xc = Double.parseDouble(Centroid.substring(0,Centroid.indexOf(" ")).trim());
            double yc = Double.parseDouble(Centroid.substring(Centroid.indexOf(" ")));
            Point centro = toPixel(xc,yc);
            //System.out.println(xc + "-" + yc);
            double Angulo = 0;            
            for (int i=0; i < fNumPoints-1; i++) 
            {
              if (((xd[i] <= xc && xd[i+1] >= xc) || (xd[i] >= xc && xd[i+1] <= xc)) && ((yd[i] <= yc && yd[i+1] >= yc) || (yd[i] >= yc && yd[i+1] <= yc)))
              {
                //Cálculo del ángulo del label
                double Base = (xd[i] - xd[i+1]);
                double Altura = (yd[i] - yd[i+1]);
                Angulo = Math.atan(-Altura/Base);
              }
            }


            FontMetrics fm = g2d.getFontMetrics(font);
            int lHeight = fm.getHeight();
            int lWidth = fm.stringWidth(pLabel);

            int xl = (int)centro.getX();
            int yl = (int)centro.getY();

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
    } catch (Exception x) 
    {
      System.out.println("Error dibujando poligonos: [drawPolylines(Graphics2D, ResultSet, WithLabels)]");
//      System.out.println(Poly);
      x.printStackTrace();
    }
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
  

}