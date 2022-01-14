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
 * PFMacro.java
 *
 * */
package org.digitall.lib.print;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.CharacterIterator;

import java.util.LinkedList;
import java.util.Map;

/**
 * PFMacro.java
 *
 *
 * Created: Wed Dec 27 11:04:48 2000
 *
 * @author <a href="mailto: "</a>
 * @version
 */

/**
 * PFMacro.java
 *
 *
 * Created: Wed Dec 27 11:04:48 2000
 *
 * @author <a href="mailto: "</a>
 * @version
 */
public class PFMacro {


    //--- Public constants
    public final static String PAGE_NO = "<<[PAGE_NO]>>";
    public final static String PAGE_COUNT = "<<[PAGE_COUNT]>>";


    //--- Private instances declarations



    /**
     * Constructor: PFMacro <p>
     *
     */
    public PFMacro () {
	
    }


    public String expandMacro (String parInput) {

       

       return (new String ());
    }


    public AttributedString expandMacro (AttributedString parInput) {

       AttributedCharacterIterator iter;
       StringBuffer textBuffer = new StringBuffer ();
       String text;
       Map charAttributes;
       LinkedList textAttributes = new LinkedList ();

       //--- Get iterator 
       iter = parInput.getIterator ();


       //--- Extract the text in a StringBuffer
       for (char c = iter.first(); c != CharacterIterator.DONE; c = iter.next()) {
          textBuffer.append (c);

          //--- Save attributes
          charAttributes = iter.getAttributes (); 
          textAttributes.add (charAttributes);
       }

       //--- Convert the StrintBuffer to a String object
       text = textBuffer.toString ();

       //--- Expand macro


       //--- Recreate the AttributedString
       AttributedString returnString = new AttributedString (text);

       //--- Apply the attributes
       for (int i = 0; i < text.length (); i++) {
          returnString.addAttributes ((Map) textAttributes.get (i), i, i + 1);
       }


       return (returnString);
    }

   /*
   public AttributedAtring shiftAttributes (int parStartPosition, int parOffset) {

       AttributedCharacterIterator iter;
       StringBuffer textBuffer = new StringBuffer ();
       Map charAttributes;
       LinkedList textAttributes = new LinkedList ();

       //--- Get iterator 
       iter = parInput.getIterator ();


       //--- Extract the text in a StringBuffer
       for (char c = iter.first(); c != CharacterIterator.DONE; c = iter.next()) {
          textBuffer.append (c);

          //--- Save attributes
          charAttributes = iter.getAttributes (); 
          textAttributes.add (charAttributes);
       }

       //--- Recreate the AttributedString
       AttributedString returnString = new AttributedString (textBuffer.toString ());
       
       //--- Apply the attributes
       for (int i = 0; i < text.length (); i++) {

          if (i == parStartPosition) {
             if (parOffset < 0
             returnString.addAttributes ((Map) textAttributes.get (i), i, i + 1);

          }
       }
       
       return (returnString);
   }
   */

}// PFMacro

















