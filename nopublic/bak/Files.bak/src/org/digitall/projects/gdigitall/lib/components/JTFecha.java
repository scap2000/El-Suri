package org.digitall.projects.gdigitall.lib.components;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class JTFecha extends JTextField
{
  public JTFecha()
  {
    setEditable(false);
    setBackground(new Color(255,255,255));
    setBorder(BorderFactory.createLineBorder(new Color(196, 50, 50), 2));
    setHorizontalAlignment(CENTER);
    addMouseListener(new MouseAdapter()
      {
        public void mouseClicked(MouseEvent e)
        {
          if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1)
          {
            JTextField kk = new JTextField(((JTextField)e.getSource()).getText());
            SelectorFecha c = new SelectorFecha(kk);
                                     OP_Proced.CentraVentana(c);
            c.setModal(true);
            c.show();
            setText(kk.getText());
          }
        }
      });
  }
}