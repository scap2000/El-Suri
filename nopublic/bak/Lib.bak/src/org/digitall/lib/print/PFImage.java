package org.digitall.lib.print;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class: PFImage <p>
 * 
 * Render an image on a <code>PFPage</code> object.
 * This class will render an image of type GIF or JPEG.
 * The image will be rendered using the specified height
 * and width
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFPrintObject
 */

/**
 * Class: PFImage <p>
 * 
 * Render an image on a <code>PFPage</code> object.
 * This class will render an image of type GIF or JPEG.
 * The image will be rendered using the specified height
 * and width
 *
 * @author Jean-Pierre Dube <jpdube@videotron.ca>
 * @version 1.0
 * @since 1.0
 * @see PFPrintObject
 */
public class PFImage extends PFPrintObject {

   //--- Private instances declarations
   private Canvas canvas = new Canvas ();
   private MediaTracker mt = new MediaTracker (canvas);
   private URL imageURL = null;
   private Image image;


   /**
    * Constructor: PFImage <p>
    *
    * Default constructor
    *
    */
   public PFImage () {
      
   }


   /**
    * Method: setURL <p>
    *
    * Set the <code>URL</code> and load the image
    * in an <code>Image</code> object
    *
    * @param parURL a value of type URL
    */
   public void setURL (String parURL) {

      try {
         
         imageURL = new URL (parURL);
      }
      catch (MalformedURLException me) {
         me.printStackTrace ();
      }

      //--- Load the image and wait for it to load
      image = Toolkit.getDefaultToolkit().getImage (imageURL);
      mt.addImage (image, 0);
      try {
         mt.waitForID (0);
      }
      catch (InterruptedException e) {
      } 
   }


   /**
    * Method: print <p>
    *
    * Render the image.
    * 
    * @param parG a value of type Graphics2D
    */
   public void print (Graphics2D parG) {

      //--- Compute the size and position of this object
      computePositionAndSize ();

      //--- Render the image on the sheet
      parG.drawImage (image,
                     (int) getDrawingOrigin ().getX ().getPoints (),
                     (int) getDrawingOrigin ().getY ().getPoints (),
                     (int) getDrawingSize ().getWidth ().getPoints (),
                     (int) getDrawingSize ().getHeight ().getPoints (),
                     canvas);

      //--- Draw childs
      printChilds (parG);
   }


   /**
    * Method: getBufferedImage <p>
    *
    * Return the loaded image as a <code>BufferedImage</code>.
    * Use this method when you need to process the image before 
    * it is rendered.
    *
    * @return a value of type BufferedImage
    */
   public BufferedImage getBufferedImage () {

      return ((BufferedImage) image);

   }



}// PFImage


























