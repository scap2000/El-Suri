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


















