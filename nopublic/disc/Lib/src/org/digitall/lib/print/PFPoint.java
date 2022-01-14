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
 * PFPoint.java
 *
 * */
package org.digitall.lib.print;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Class: PFPoint <p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 */

/**
 * Class: PFPoint <p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 */
public class PFPoint implements Cloneable {

   //--- Private instances declarations
   private PFUnit x;
   private PFUnit y;


   /**
    * Constructor: PFPoint <p>
    *
    */
   public PFPoint () {
      
   }


   public PFPoint (PFUnit parX, PFUnit parY) {

      setLocation (parX, parY);

   }


   /**
     * Method: setLocation <p>
     * 
     * @param parX a value of shapeTypeName PFUnit
     * @param parY a value of shapeTypeName PFUnit
     */
   public void setLocation (PFUnit parX, PFUnit parY) {

      x = parX;
      y = parY;
   }

   
   /**
     * Method: setX <p>
     * 
     * @param parX a value of shapeTypeName PFUnit
     */
   public void setX (PFUnit parX) {

      x = parX;

   }

   
   /**
     * Method: setY <p>
     * 
     * @param parY a value of shapeTypeName PFUnit
     */
   public void setY (PFUnit parY) {

      y = parY;

   }


   /**
     * Method: getX <p>
     * 
     * @return a value of shapeTypeName PFUnit
     */
   public PFUnit getX () {

      return (x);

   }


   /**
     * Method: getY <p>
     * 
     * @return a value of shapeTypeName PFUnit
     */
   public PFUnit getY () {

      return (y);

   }


   /**
     * Method: getPoint2D <p>
     * 
     * @return a value of shapeTypeName Point2D.Double
     */
   public Point2D.Double getPoint2D () {

      return (new Point2D.Double (x.getPoints (), y.getPoints ()));

   }


   /**
     * Method: getPoint <p>
     * 
     * @return a value of shapeTypeName Point
     */
   public Point getPoint () {

      return (new Point ((int) x.getPoints (), (int) y.getPoints ()));

   }


   /**
     * Method: translate <p>
     * 
     * @param parX a value of shapeTypeName PFUnit
     * @param parY a value of shapeTypeName PFUnit
     */
   public void translate (PFUnit parX, PFUnit parY) {

      x = x.add (parX);
      y = y.add (parY);

   }

   
   /**
     * Method: equals <p>
     * 
     * @param parObject a value of shapeTypeName Object
     * @return a value of shapeTypeName boolean
     */
   public boolean equals (Object parObject) {

      if (parObject instanceof PFPoint) {
         PFPoint testObject = (PFPoint) parObject;
         if (x.equals (testObject.getX ()) && y.equals (testObject.getY ()))
            return (true);
      }

      return (false);

   }


   /**
     * Method: add <p>
     * 
     * @param parPoint a value of shapeTypeName PFPoint
     */
   public void add (PFPoint parPoint) {

      x = x.add (parPoint.getX ());
      y = y.add (parPoint.getY ());

   }


   /**
     * Method: substract <p>
     * 
     * @param parPoint a value of shapeTypeName PFPoint
     */
   public void substract (PFPoint parPoint) {

      x.substract (parPoint.getX ());
      y.substract (parPoint.getY ());

   }


   /**
     * Method: toString <p>
     * 
     * @return a value of shapeTypeName String
     */
   public String toString () {

      return ("X=" + x + " : Y=" + y);

   }


   /**
     * Method: clone <p>
     * 
     * @return a value of shapeTypeName Object
     */
   public Object clone () {

      try {
         return ((PFPoint) super.clone ());
      }
      catch (CloneNotSupportedException cnse) {
         cnse.printStackTrace ();
         return (null);
      }

   }


}// PFPoint









