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















