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
 * PFImage.java
 *
 * */
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


























