package jms;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ColorSelectorPanel extends JPanel 
{

  private JPanel colorseleccionado = new JPanel();
  private JTextField textred = new JTextField();
  private JTextField textgreen = new JTextField();
  private JTextField textblue = new JTextField();
  private JLabel labelred = new JLabel();
  private JLabel labelgreen = new JLabel();
  private JLabel labelblue = new JLabel();
  private JButton pickcolor = new JButton();
  private int red, green, blue;
  private JButton delcolor = new JButton();

  public ColorSelectorPanel()
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
    this.setLayout(null);
    this.setSize(new Dimension(497, 42));
    this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    colorseleccionado.setBounds(new Rectangle(280, 10, 40, 20));
    colorseleccionado.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    textred.setBounds(new Rectangle(45, 10, 40, 19));
    textred.setEditable(false);
    textred.setBackground(Color.white);
    textgreen.setBounds(new Rectangle(140, 10, 40, 20));
    textgreen.setEditable(false);
    textgreen.setBackground(Color.white);
    textblue.setBounds(new Rectangle(225, 10, 40, 20));
    textblue.setEditable(false);
    textblue.setBackground(Color.white);
    labelred.setText("Rojo");
    labelred.setBounds(new Rectangle(10, 10, 35, 20));
    labelgreen.setText("Verde");
    labelgreen.setBounds(new Rectangle(95, 10, 35, 20));
    labelblue.setText("Azul");
    labelblue.setBounds(new Rectangle(190, 10, 30, 20));
    pickcolor.setText("?");
    pickcolor.setBounds(new Rectangle(335, 10, 40, 20));
    pickcolor.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          pickcolor_actionPerformed(e);
        }
      });
    delcolor.setText("x");
    delcolor.setBounds(new Rectangle(390, 10, 40, 20));
    delcolor.setSize(new Dimension(41, 20));
    delcolor.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          delcolor_actionPerformed(e);
        }
      });
    this.add(delcolor, null);
    this.add(pickcolor, null);
    this.add(labelblue, null);
    this.add(labelgreen, null);
    this.add(labelred, null);
    this.add(textblue, null);
    this.add(textgreen, null);
    this.add(textred, null);
    this.add(colorseleccionado, null);
  }

  private void pickcolor_actionPerformed(ActionEvent e)
  {
    /** Abre el Selector de Colores */
    Color initialColor = Color.WHITE;
    Color newColor = JColorChooser.showDialog(this, "Seleccione color", initialColor);
    try 
    {
      textred.setText("" + newColor.getRed() + "");
      textgreen.setText("" + newColor.getGreen() + "");
      textblue.setText("" + newColor.getBlue() + "");
      actColor();
    } catch (NullPointerException x) 
    {
      
    }
 
  }

  private void actColor() 
  {
    red = Integer.parseInt("0" + textred.getText());
    green = Integer.parseInt("0" + textgreen.getText());
    blue = Integer.parseInt("0" + textblue.getText());
    colorseleccionado.setBackground(new Color(red, green, blue));
  }

  public void setColor(Color c) 
  {
    red = c.getRed();
    green = c.getGreen();
    blue = c.getBlue();
    textred.setText(String.valueOf(red));
    textgreen.setText(String.valueOf(green));
    textblue.setText(String.valueOf(blue));
    colorseleccionado.setBackground(new Color(red, green, blue));
  }
  
  public Color getColor()
  {
    return new Color(red,green,blue);
  }

  public int getRed()
  {
    return red;
  }

  public int getGreen()
  {
    return green;
  }

  public int getBlue()
  {
    return blue;
  }

  private void delcolor_actionPerformed(ActionEvent e)
  {
    this.red = 0; this.green = 0; this.blue = 0;
    textred.setText("");
    textgreen.setText("");
    textblue.setText("");
  }
  
}