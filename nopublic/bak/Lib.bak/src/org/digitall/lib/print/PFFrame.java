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





















