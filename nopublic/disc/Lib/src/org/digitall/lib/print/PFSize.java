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
 * PFSize.java
 *
 * */
package org.digitall.lib.print;

import java.awt.Dimension;

/**
 * Class: PFSize <p>
 * 
 * 
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 */

/**
 * Class: PFSize <p>
 * 
 * 
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 */
public class PFSize implements Cloneable {

   //--- Private instances declarations
   private PFUnit width = new PFInchUnit (0.0);
   private PFUnit height = new PFInchUnit (0.0);


   /**
    * Constructor: PFSize <p>
    *
    */
   public PFSize (PFUnit parWidth, PFUnit parHeight) {
      
      width = parWidth;
      height = parHeight;

   }


   /**
    * Method: setWidth <p>
    *
    * @param parWidth a value of type PFUnit
    */
   public void setWidth (PFUnit parWidth) {

      width = parWidth;

   }


   /**
    * Method: getWidth <p>
    *
    * @return a value of type PFUnit
    */
   public PFUnit getWidth () {

      return (width);

   }


   /**
    * Method: setHeight <p>
    *
    * @param parHeight a value of type PFUnit
    */
   public void setHeight (PFUnit parHeight) {

      height = parHeight;

   }


   /**
    * Method: getHeight <p>
    *
    * @return a value of type PFUnit
    */
   public PFUnit getHeight () {

      return (height);

   }


   /**
    * Method: getDimension <p>
    *
    * @return a value of type Dimension
    */
   public Dimension getDimension () {

      Dimension dimension = new Dimension ((int) width.getPoints (), (int) height.getPoints ());

      return (dimension);

   }


   /**
    * Method: scale <p>
    *
    * Scale will multiply the actual width and height with
    * the passed width and height, thus scaling the size.
    * You can use a value < 0 to reduced the size and values
    * > 0 to enlarge.
    *
    * @param parWidth a value of type PFUnit
    * @param parHeight a value of type PFUnit
    */
   public void scale (PFUnit parWidth, PFUnit parHeight) {

      width = width.multiply (parWidth);
      height = height.multiply (parHeight);

   }


   /**
    * Method: equals <p>
    *
    * @param parObject a value of type Object
    * @return a value of type boolean
    */
   public boolean equals (Object parObject) {

      if (parObject instanceof PFSize) {
         PFSize testObject = (PFSize) parObject;
         if (width.equals (testObject.getWidth ()) && height.equals (testObject.getHeight ()))
            return (true);
      }

      return (false);
   }


   /**
    * Method: toString <p>
    *
    * @return a value of type String
    */
   public String toString () {

      return ("Width=" + width + " : Height=" + height);

   }


   /**
    * Method: clone <p>
    *
    * @return a value of type Object
    */
   public Object clone () {

      try {
         return ((PFSize) super.clone ());
      }
      catch (CloneNotSupportedException cnse) {
         cnse.printStackTrace ();
         return (null);
      }

   }


}// PFSize













