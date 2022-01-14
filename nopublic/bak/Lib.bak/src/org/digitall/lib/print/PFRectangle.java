package org.digitall.lib.print;


/**
 * Class: PFRectangle <p>
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFPrintObject
 */


public class PFRectangle {

   //--- Private instances declarations
   private PFPoint location = new PFPoint (new PFInchUnit (0.0), new PFInchUnit (0.0));
   private PFSize size = new PFSize (new PFInchUnit (0.0), new PFInchUnit (0.0));


   /**
    * Constructor: PFRectangle <p>
    *
    */
   public PFRectangle () {
      
   }


   /**
    * Method: setX <p>
    *
    * @param parX a value of type PFUnit
    */
   public void setX (PFUnit parX) {

      location.setX (parX);
   }


   /**
    * Method: setY <p>
    *
    * @param parY a value of type PFUnit
    */
   public void setY (PFUnit parY) {

      location.setY (parY);

   }


   /**
    * Method: setLocation <p>
    *
    * @param parPoint a value of type PFPoint
    */
   public void setLocation (PFPoint parPoint) {

      location = parPoint;

   }


   /**
    * Method: setWidth <p>
    *
    * @param parWidth a value of type PFUnit
    */
   public void setWidth (PFUnit parWidth) {

      size.setWidth (parWidth);

   }


   /**
    * Method: setHeight <p>
    *
    * @param parHeight a value of type PFUnit
    */
   public void setHeight (PFUnit parHeight) {

      size.setHeight (parHeight);

   }


   /**
    * Method: setSize <p>
    *
    * @param parSize a value of type PFSize
    */
   public void setSize (PFSize parSize) {

      size = parSize;
   }

}// PFRectangle




















