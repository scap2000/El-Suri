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
 * PFUnit.java
 *
 * */
package org.digitall.lib.print;

/**
 * Class: PFUnit <p>
 *
 * <p>
 * Base class for the measurment system in the print framework. 
 * When adding a new measurement unit to the framework you must
 * extend this class and implement the <code>getPoint ()</code>.<p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see Cloneable
 * Created: Wed Sep 27 14:45:50 2000
 */


public abstract class PFUnit implements Cloneable {

   //--- Private instances declarations
   private double units = 0.0;
   private double points = 0.0;


   /**
    * Constructor: PFUnit <p>
    *
    */
   public PFUnit () {

   }


   /**
    * Constructor: PFUnit <p>
    *
    * @param parUnits a value of type double
    */
   public PFUnit (double parUnits) {

      units = parUnits;
   }


   /**
    * Method: setUnits <p>
    * 
    * Set the unit value, using the implemented unit of measurment.
    *
    * @param parValue a value of type double
    */
   public void setUnits (double parUnits) {

      units = parUnits;

   }


   /**
    * Method: getUnits <p>
    *
    * Get the units in the implemented measurement system.
    *
    * @return a value of type double
    */
   public double getUnits () {

      return (units);

   }
   
   
   /**
    * Method: getPoints <p>
    *
    * This method is in charge of converting the unit value in the
    * current measurement system to points. There is 72 points per inch.
    *
    * @return a value of type double
    */
   public abstract double getPoints ();
   

   /**
    * Method: setPoints <p>
    *
    * Set the value of this object in points a convert the
    * value to the current measurment system define by the
    * class.
    *
    * @param parPoints a value of type double
    */
   public abstract void setPoints (double parPoints);


   /**
    * Method: add <p>
    *
    * Add another measurement system value to this unit.
    *
    * @param parUnit a value of type PFUnit
    */
   public PFUnit add (PFUnit parUnits) {

      PFUnit tempUnit = (PFUnit) clone ();

      tempUnit.setUnits (tempUnit.getUnits () + parUnits.getUnits ());

      return (tempUnit);
   }


   /**
    * Method: add <p>
    *
    * Add a <code>double</code> value to this unit. Note
    * that the value passed in parameters is assumed to be
    * in the current units defined by this object.
    *
    * @param parUnits a value of type PFUnit
    */
   public PFUnit add (double parUnits) {

      PFUnit tempUnit = (PFUnit) clone ();

      tempUnit.setUnits (this.getUnits () + parUnits);

      return (tempUnit);
   }


   /**
    * Method: substract <p>
    *
    * Substract another measurement system value to this unit.
    *
    * @param parUnit a value of type PFUnit
    */
   public PFUnit substract (PFUnit parUnits) {

      PFUnit tempUnit = (PFUnit) clone ();

      tempUnit.setUnits (tempUnit.getUnits () - parUnits.getUnits ());

      return (tempUnit);
   }


   /**
    * Method: substract <p>
    *
    * Substract a <code>double</code> value to this unit. Note
    * that the value passed in parameters is assumed to be
    * in the current units defined by this object.
    *
    * @param parUnits a value of type PFUnit
    * @return a value of type PFUnit
    */
   public PFUnit substract (double parUnits) {

      PFUnit tempUnit = (PFUnit) clone ();

      tempUnit.setUnits (this.getUnits () - parUnits);

      return (tempUnit);
   }


   /**
    * Method: multiply <p>
    *
    * Multiply another measurement system value to this unit. 
    *
    * @param parUnits a value of type PFUnit
    */
   public PFUnit multiply (PFUnit parUnits) {

      PFUnit tempUnit = (PFUnit) clone ();

      tempUnit.setUnits (tempUnit.getUnits () * parUnits.getUnits ());

      return (tempUnit);
   }


   /**
    * Method: multiply <p>
    *
    * Multiply a <code>double</code> value to this unit. Note
    * that the value passed in parameters is assumed to be
    * in the current units defined by this object.
    *
    * @param parUnits a value of type double
    */
   public PFUnit multiply (double parUnits) {

      PFUnit tempUnit = (PFUnit) clone ();

      tempUnit.setUnits (this.getUnits () * parUnits);
      
      return (tempUnit);
   }


   /**
    * Method: divide <p>
    *
    * Divide another measurement system value to this unit.    
    *
    * Note: A denominator with a zero value will be trapped
    * and a <code>null</code> value will be returned.
    *
    * @param parUnits a value of type PFUnit
    */
   public PFUnit divide (PFUnit parUnits) {

      if (parUnits.getUnits () != 0) {
         PFUnit tempUnit = (PFUnit) clone ();
         tempUnit.setUnits (tempUnit.getUnits () / parUnits.getUnits ());
         return (tempUnit);
      }
      else
         return (null);
   }


   /**
    * Method: divide <p>
    *
    * Divide a <code>double</code> value to this unit. Note
    * that the value passed in parameters is assumed to be
    * in the current units defined by this object.
    *
    * Note: A denominator with a zero value will be trapped
    * and a <code>null</code> value will be returned.
    *
    * @param parUnits a value of type double
    */
   public PFUnit divide (double parUnits) {


      if (parUnits != 0) {
         PFUnit tempUnit = (PFUnit) clone ();
         tempUnit.setUnits (tempUnit.getUnits () / parUnits);
         return (tempUnit);
      }
      else
         return (null);

   }


   /**
    * Method: equal <p>
    *
    * @param parUnit a value of type Object
    * @return a value of type boolean
    */
   public boolean equals (Object parUnit) {

      if (parUnit instanceof PFUnit)
         if (((PFUnit) parUnit).getUnits () == this.getUnits ())
            return (true);

      return (false);

   }

   
   /**
    * Method: clone <p>
    *
    * @return a value of type Object
    */
   public Object clone () {

      try {
         return ((PFUnit) super.clone ());
      }
      catch (CloneNotSupportedException cnse) {
         cnse.printStackTrace ();
         return (null);
      }

   }


   /**
    * Method: toString <p>
    *
    * @return a value of type String
    */
   public String toString () {

      return (new String ().valueOf (units));

   }
}// PFUnit


















