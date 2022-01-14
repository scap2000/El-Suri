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
 * PFFrame.java
 *
 * */
package org.digitall.lib.print;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

/**
 * Class: PFFrame <p>
 *
 * Render a rectangle on a <code>PFPage</code>. This class
 * will render a rectangle with the following attributes: 
 * Line color, fill color and line tickness.<p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFPrintObject
 */

/**
 * Class: PFFrame <p>
 *
 * Render a rectangle on a <code>PFPage</code>. This class
 * will render a rectangle with the following attributes: 
 * Line color, fill color and line tickness.<p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFPrintObject
 */
public class PFFrame extends PFPrintObject {

   //--- Private instances declarations
   private double tickness = 1.0;
   private Color lineColor = Color.black;
   private Color fillColor = null;
   private Rectangle2D.Double rectangle;


   /**
    * Constructor: PFFrame <p>
    *
    * Default constructor
    *
    */
   public PFFrame () {
      
   }


   /**
    * Method: setTickness <p>
    *
    * Set the line tickness in pixels.
    *
    * @param parTickness a value of type float
    */
   public void setTickness (double parTickness) {

      tickness = parTickness;

   }


   /**
    * Method: getTickness <p>
    *
    * Get the line tickness in pixels
    *
    * @return a value of type float
    */
   public double getTickness () {

      return (tickness);

   }


   /**
    * Method: setLineColor <p>
    *
    * Set the line color usin the java.awt.Color object.
    *
    * @param parLineColor a value of type Color
    */
   public void setLineColor (Color parLineColor) {

      lineColor = parLineColor;

   }


   /**
    * Method: getLineColor <p>
    *
    * Get the line color for the rectangle
    *
    * @return a value of type Color
    */
   public Color getLineColor () {

      return (lineColor);

   }


   /**
    * Method: setFillColor <p>
    *
    * Set the fill color usin the java.awt.Color object.
    *
    * @param parFillColor a value of type Color
    */
   public void setFillColor (Color parFillColor) {

      fillColor = parFillColor;

   }


   /**
    * Method: getFillColor <p>
    *
    * Return the fill color
    *
    * @return a value of type Color
    */
   public Color getFillColor () {

      return (fillColor);

   }


   /**
    * Method: print <p>
    *
    * Render this rectangle using the line color, fill color
    * and line tickness specified by the user.
    *
    * @param parG a value of type Graphics2D
    */
   public void print (Graphics2D parG) {


      Color saveForeground = parG.getColor ();


      //--- Print the child objects
      computePositionAndSize ();

      
      rectangle = new Rectangle2D.Double (getDrawingOrigin ().getX ().getPoints (),
                                          getDrawingOrigin ().getY ().getPoints (),
                                          getDrawingSize ().getWidth ().getPoints (),
                                          getDrawingSize ().getHeight ().getPoints ());

      //--- Draw the rectangle 
      if (fillColor != null) {
         parG.setColor (fillColor);
         parG.fill (rectangle);
      }
      
      //--- Set the line tickness
      Stroke lineStroke = new BasicStroke ((float) tickness);
      parG.setStroke (lineStroke);
      parG.setColor (lineColor);
      parG.draw (rectangle);
      
      //--- Restore colors
      parG.setColor (saveForeground);

      //--- Render child objects
      printChilds (parG);
   }

}// PFFrame





















