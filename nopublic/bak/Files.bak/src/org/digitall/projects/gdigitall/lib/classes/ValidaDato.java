package org.digitall.projects.gdigitall.lib.classes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ValidaDato extends PlainDocument 
{
   /**
    EJEMPLO de utilizacion: 
            jttiempo.setDocument(new ValidaDato(ValidaDato.DECIMAL));
            jttiempo.setText("0");
    */
      
   public static final String MINUSCULA  =
        "abcdefghijklmnñopqrstuvwxyzáéíóú ";
   public static final String MAYUSCULA  =
        "ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ ";
   public static final String SIGNOS  =
        "ºª!|@#$%&/()=?'¡¿{}[]*+<>,;.:-_";     
   public static final String ALPHA   = 
        MINUSCULA + MAYUSCULA + SIGNOS;
   public static final String ENTERO = 
        "0123456789";
   public static final String DECIMAL = 
        ENTERO + ".";
   public static final String ALPHA_NUMERIC = 
        ALPHA + DECIMAL;

   public static final String ALPHA_NUMERIC_SIG = 
        ALPHA + DECIMAL + SIGNOS;
   
   protected String acceptedChars = null;
   protected boolean negativeAccepted = false;
   
   public ValidaDato() 
   {
     this(ALPHA_NUMERIC);
   }
   
   public ValidaDato(String acceptedchars) 
   {
     acceptedChars = acceptedchars;
   }
  
   public void setNegativeAccepted(boolean negativeaccepted) 
   {
     if (acceptedChars.equals(ENTERO) || acceptedChars.equals(DECIMAL) || acceptedChars.equals(ALPHA_NUMERIC))
     {
         negativeAccepted = negativeaccepted;
        acceptedChars += "-";
     }
   }

   public void insertString(int offset, String  str, AttributeSet attr) throws BadLocationException 
   {
     if (str == null) return;

     if (acceptedChars.equals(MAYUSCULA))
        str = str.toUpperCase();
     else if (acceptedChars.equals(MINUSCULA))
        str = str.toLowerCase();

     for (int i=0; i < str.length(); i++) 
     {
       if (acceptedChars.indexOf(str.valueOf(str.charAt(i))) == -1) return;
     }

     if (acceptedChars.equals(DECIMAL) || (acceptedChars.equals(DECIMAL + "-") && negativeAccepted)) 
     {
        if (str.indexOf(".") != -1) 
        {
           if (getText(0, getLength()).indexOf(".") != -1) return;
        }
     }

     if (negativeAccepted && str.indexOf("-") != -1) 
     {
        if (str.indexOf("-") != 0 || offset != 0 ) return;
     }

     super.insertString(offset, str, attr);
   }
 }