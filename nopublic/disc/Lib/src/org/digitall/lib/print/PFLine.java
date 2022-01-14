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
 * PFLine.java
 *
 * */
package org.digitall.lib.print;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * Class: PFLine <p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFPrintObject
 */

/**
 * Class: PFLine <p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFPrintObject
 */
public class PFLine extends PFPrintObject {
   
   //--- Private instances declarations
   private double tickness = 0.0;
   private PFPoint endPoint;
   private Color lineColor = Color.black;
   private int cap = BasicStroke.CAP_SQUARE;
   private int miter = BasicStroke.JOIN_MITER;
   private float miterLimit = 10.0f;


   /**
    * Constructor: PFLine <p>
    *
    */
   public PFLine () {
      
   }


   /**
    * Method: setCap <p>
    *
    * @param parCap a value of type int
    */
   public void setCap (int parCap) {

      cap = parCap;

   }

   
   /**
    * Method: getCap <p>
    *
    * @return a value of type int
    */
   public int getCap () {

      return (cap);

   }


   /**
    * Method: setMiter <p>
    *
    * @param parMitter a value of type int
    */
   public void setMiter (int parMiter) {

      miter = parMiter;

   }


   /**
    * Method: getMiter <p>
    *
    * @return a value of type int
    */
   public int getMiter () {

      return (miter);

   }


   /**
    * Method: setMiterLimit <p>
    *
    * @param parLimit a value of type float
    */
   public void setMiterLimit (float parLimit) {

      miterLimit = parLimit;

   }


   /**
    * Method: getMiterLimit <p>
    *
    * @return a value of type float
    */
   public float getMiterLimit () {

      return (miterLimit);

   }


   /**
    * Method: setTickness <p>
    *
    * @param parTickness a value of type double
    */
   public void setTickness (double parTickness) {

      tickness = parTickness;

   }


   /**
    * Method: getTickness <p>
    *
    * @return a value of type double
    */
   public double getTickness () {

      return (tickness);

   }


   /**
    * Method: setLineColor <p>
    *
    * @param parLineColor a value of type Color
    */
   public void setLineColor (Color parLineColor) {

      lineColor = parLineColor;

   }


   /**
    * Method: getLineColor <p>
    *
    * @return a value of type Color
    */
   public Color getLineColor () {

      return (lineColor);

   }


   /**
    * Method: setEndPoint <p>
    *
    * @param parPoint a value of type PFPoint
    */
   public void setEndPoint (PFPoint parPoint) {

      endPoint = parPoint;

      //--- Since all object works on the basis of x, y, w h
      //--- so we have to convert the end point
      setWidth (parPoint.getX ());
      setHeight (parPoint.getY ());
   }


   /**
    * Method: print <p>
    *
    * @param parG a value of type Graphics2D
    */
   public void print (Graphics2D parG) {

      //--- Compute the position and size of the line
      computePositionAndSize ();
      
      //--- Save Graphics parameters
      Color savedColor = parG.getColor ();

      //--- Set color
      parG.setColor (getLineColor ());

      //--- Draw line 
      Line2D.Double line = new Line2D.Double ();
      line.setLine (getDrawingOrigin ().getX ().getPoints (),
                    getDrawingOrigin ().getY ().getPoints (),
                    getDrawingSize ().getWidth ().getPoints (),
                    getDrawingSize ().getHeight ().getPoints ());

      BasicStroke stroke = new BasicStroke ((float) tickness, cap, miter, miterLimit);
      parG.setStroke (stroke);

      parG.draw (line);

      //--- Restore saved parameters
      parG.setColor (savedColor);

      //--- Paint childs
      printChilds (parG);
   }

}// PFLine















