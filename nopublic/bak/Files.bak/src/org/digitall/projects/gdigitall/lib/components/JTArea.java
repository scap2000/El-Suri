package org.digitall.projects.gdigitall.lib.components;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextArea;

public class JTArea extends JTextArea
{
   
  public JTArea()
  {
        try
    {

      jbInit();
    }
    catch(Exception e)
    {
     e.printStackTrace();
   }
  }

  private void jbInit() throws Exception
  {


  this.addFocusListener(new FocusAdapter() {
        public void focusGained(FocusEvent evt) {
          JTextArea jt = (JTextArea)evt.getSource();
          jt.setSelectionStart(0);
          jt.setSelectionEnd(255);
        }
        public void focusLost(FocusEvent evt) {           
        }
        });
  }
    
 public String getTexto()
 {
   return this.getText().replaceAll("\'","\\\\'");
 }

  
}
  
