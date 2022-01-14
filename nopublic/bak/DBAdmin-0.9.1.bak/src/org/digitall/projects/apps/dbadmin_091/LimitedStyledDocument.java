package org.digitall.projects.apps.dbadmin_091;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

public class LimitedStyledDocument extends DefaultStyledDocument {

    int maxCharacters;

    public LimitedStyledDocument(int maxChars) {
        maxCharacters = maxChars;
    }

    public void insertString(int offs, String str, AttributeSet a) 
        throws BadLocationException {

        //This rejects the entire insertion if it would make
        //the contents too long. Another option would be
        //to truncate the inserted string so the contents
        //would be exactly maxCharacters in length.
        if ((getLength() + str.length()) <= maxCharacters)
            super.insertString(offs, str, a);
        else
            Toolkit.getDefaultToolkit().beep();
    }
}