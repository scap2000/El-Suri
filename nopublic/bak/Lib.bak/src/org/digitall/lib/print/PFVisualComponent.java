package org.digitall.lib.print;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class: PFVisualComponent <p>
 *
 * Print an AWT/Swing components.
 *
 * Using this class will be able to render any AWT/Swing
 * components.
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFPrintObject
 */

/**
 * Class: PFVisualComponent <p>
 *
 * Print an AWT/Swing components.
 *
 * Using this class will be able to render any AWT/Swing
 * components.
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFPrintObject
 */
public class PFVisualComponent extends PFPrintObject{

   //--- Private instances declarations
   private Component component = null;


   /**
    * Constructor: PFVisualComponent <p>
    *
    * Default constructor
    *
    */
   public PFVisualComponent () {
      
   }


   /**
    * Method: setComponent <p>
    *
    * Set the visual component to be printed.
    * the component can be an AWT or Swing 
    * component
    *
    * @param parComponent a value of type Component
    */
   public void setComponent (Component parComponent) {

      component = parComponent;

   }


   /**
    * Method: print <p>
    *
    * Print an AWT/Swing component on a <code>PFPage</code> object.
    *
    * To be able to position and size a visual component, an image
    * of the component is created. This image is then rendered on
    * the page.
    *
    * @param parG a value of type Graphics2D
    */
   public void print (Graphics2D parG) {


      //--- Compute position and size
      computePositionAndSize ();

      //--- Do we have a valid component to render
      if (component != null) {
         //--- Set the size of the component
         component.setSize (getDrawingSize ().getDimension ());

         //--- Create our double buffer image to render the component on
         BufferedImage doubleBuffer = new BufferedImage ((int) getDrawingSize ().getWidth ().getPoints (),
                                                         (int) getDrawingSize ().getHeight ().getPoints (),
                                                         BufferedImage.TYPE_INT_RGB);

         //--- Ask the component to paint itself on the double buffer
         component.paint (doubleBuffer.getGraphics ());
      
         //--- If we have a valid doubleBuffer then
         //--- draw the image of the component on the page
         if (doubleBuffer != null)
            parG.drawImage (doubleBuffer,
                            (int) getDrawingOrigin ().getX ().getPoints (),
                            (int) getDrawingOrigin ().getY ().getPoints (),
                            (int) getDrawingSize ().getWidth ().getPoints (),
                            (int) getDrawingSize ().getHeight ().getPoints (),
                            new Canvas ());
      }      

      //--- Print childs
      printChilds (parG);
   }
}// PFVisualComponent
















