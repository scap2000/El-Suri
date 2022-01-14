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













