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
 * PFVisualComponent.java
 *
 * */
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
















