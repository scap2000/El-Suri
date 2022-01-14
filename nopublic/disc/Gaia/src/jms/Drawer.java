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
 * Drawer.java
 *
 * */
package jms;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Drawer
{

  private String Data;
  private String LabelItem;
  private Color color;
  private String Qwhere;
  private int fWidth,fHeight;
  int fNumPoints;
  double fFactor;
  double Factor = 0;
  double xOffset = 0;
  double yOffset = 0;
  double xmin = 0;
  double xmax = 0;
  double ymin = 0;
  double ymax = 0;
  double extX = 0;
  double extY = 0;
  public final static Font font=new Font("Courier",Font.PLAIN,10);
  public final static Color DEFAULT_COLOR=Color.black;
  
  public Drawer()
  {
        BufferedImage bufferedImage = new BufferedImage(fWidth, fHeight, BufferedImage.TYPE_INT_RGB);
        // Create a graphics contents on the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, fWidth, fHeight);    
        // Draw graphics
/*************************************************************************/
    try {
      Connection Con = Proc.CCon();
      Data = Data.trim();
      String geometry = Data.substring(0,Data.indexOf(" ")).trim();
      Data = Data.substring(Data.indexOf(" ")).trim();
      String Query = "select " + LabelItem + " as label, AsText(centroid(" + geometry + ")) as centroid, AsText(" + geometry + ") as the_geom,npoints(" + geometry + ") " + Data + Qwhere;
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
        Poly = Poly.substring(Poly.indexOf("(((")+3,Poly.length()-3);
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
            g2d.drawPolygon(poly);
        
            // Change the line color and draw the x-y axes
//            g.setColor(Color.green);

        try
        {
          g2d.setColor(new Color(0,0,255));
          String pLabel = Polygons.getString("label");
          String Centroid = Polygons.getString("centroid");
          Centroid = Centroid.substring(Centroid.indexOf("(")+1,Centroid.length()-1);
          double xc = Double.parseDouble(Centroid.substring(0,Centroid.indexOf(" ")).trim());
          double yc = Double.parseDouble(Centroid.substring(Centroid.indexOf(" ")));
          Point centro = toPixel(xc,yc);
          
          FontMetrics fm = g2d.getFontMetrics(font);
          int lHeight = fm.getHeight();
          int lWidth = fm.stringWidth(pLabel);
          g2d.drawString(pLabel,(int)centro.getX()-(int)lWidth/2,(int)centro.getY()+lHeight/2);
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
    
       // return bufferedImage;
       // this = bufferedImage;
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
  
}