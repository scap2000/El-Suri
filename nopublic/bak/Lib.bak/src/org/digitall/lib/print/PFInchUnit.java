package org.digitall.lib.print;

/**
 * Class: PFInchUnit <p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFUnit
 */

public class PFInchUnit extends PFUnit {

   //--- Private constants declarations
   private final static int POINTS_PER_INCH = 72;


   /**
    * Constructor: PFInchUnit <p>
    *
    */
   public PFInchUnit () {

   }


   /**
     * Constructor: PFInchUnit <p>
     * 
     * @param parValue a value of shapeTypeName double
     */
   public PFInchUnit (double parValue) {

      super (parValue);
   
   }


   /**
     * Method: getPoints <p>
     * 
     * Return the result of the conversion from
     * inches to points.
     * 
     * @return a value of shapeTypeName double
     */
   public double getPoints () {

      return (getUnits () * POINTS_PER_INCH);

   }


   /**
     * Method: setPoints <p>
     * 
     * @param parPoints a value of shapeTypeName double
     */
   public void setPoints (double parPoints) {

      setUnits (parPoints / POINTS_PER_INCH);

   }

}// PFInchUnit



















