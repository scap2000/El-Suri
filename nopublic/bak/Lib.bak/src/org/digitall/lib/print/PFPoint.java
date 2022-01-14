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









