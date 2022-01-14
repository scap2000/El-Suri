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

















