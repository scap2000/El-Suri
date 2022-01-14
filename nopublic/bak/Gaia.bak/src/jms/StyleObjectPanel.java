package jms;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

public class StyleObjectPanel extends JPanel 
{
  private JLabel labelsymbol = new JLabel();
  private JLabel labelmaxsize1 = new JLabel();
  private JLabel labeloutlinecolor = new JLabel();
  private JLabel labelmaxsize = new JLabel();
  private JLabel labelcolor = new JLabel();
  private JLabel labelbackgroundcolor = new JLabel();
  private JLabel labelwidth = new JLabel();
  private JLabel labelantialias = new JLabel();
  private JLabel labelminsize = new JLabel();
  private JLabel labelheight = new JLabel();

  private JComboBox symbol = new JComboBox();
  private JTextField size = new JTextField();
  private ColorSelectorPanel outlinecolor = new ColorSelectorPanel();
  private JTextField maxsize = new JTextField();
  private ColorSelectorPanel color = new ColorSelectorPanel();
  private ColorSelectorPanel backgroundcolor = new ColorSelectorPanel();
  private JTextField width = new JTextField();
  private JComboBox antialias = new JComboBox();
  private JTextField minsize = new JTextField();
  private JTextField height = new JTextField();

  private StyleObject style = new StyleObject();

  public StyleObjectPanel()
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
    this.setSize(new Dimension(458, 417));
    this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    labelsymbol.setText("Símbolo:");
    labelsymbol.setBounds(new Rectangle(30, 390, 60, 20));
    labelmaxsize1.setText("Tamaño:");
    labelmaxsize1.setBounds(new Rectangle(50, 270, 60, 20));
    labeloutlinecolor.setText("Color de los contornos:");
    labeloutlinecolor.setBounds(new Rectangle(155, 195, 150, 15));
    labelmaxsize.setText("Tamaño Máximo:");
    labelmaxsize.setBounds(new Rectangle(150, 270, 110, 20));
    labelcolor.setText("Color de los dibujos:");
    labelcolor.setBounds(new Rectangle(165, 125, 130, 15));
    labelbackgroundcolor.setText("Color de los símbolos no transparentes:");
    labelbackgroundcolor.setBounds(new Rectangle(103, 55, 255, 15));
    labelwidth.setText("Offset Vertical:");
    labelwidth.setBounds(new Rectangle(55, 330, 105, 20));
    symbol.setBounds(new Rectangle(90, 390, 305, 20));
    size.setBounds(new Rectangle(25, 295, 105, 20));
    outlinecolor.setBounds(new Rectangle(10, 215, 440, 40));
    maxsize.setBounds(new Rectangle(155, 295, 105, 20));
    color.setBounds(new Rectangle(10, 145, 440, 40));
    backgroundcolor.setBounds(new Rectangle(10, 75, 440, 40));
    width.setBounds(new Rectangle(60, 355, 85, 20));
    labelantialias.setText("Antialias:");
    labelantialias.setBounds(new Rectangle(25, 20, 60, 20));
    antialias.setBounds(new Rectangle(90, 20, 305, 20));
    labelminsize.setText("Tamaño Minimo:");
    labelminsize.setBounds(new Rectangle(280, 270, 110, 20));
    minsize.setBounds(new Rectangle(285, 295, 105, 20));
    labelheight.setText("Offset Horizontal:");
    labelheight.setBounds(new Rectangle(190, 330, 120, 20));
    height.setBounds(new Rectangle(205, 355, 85, 20));
    symbol.addItem("0 - Sin símbolo");
    symbol.addItem("1 - Ángulo");
    symbol.addItem("2 - Estrella");
    symbol.addItem("3 - Triángulo");
    symbol.addItem("4 - Cuadrado");
    symbol.addItem("5 - \"+\"");
    symbol.addItem("6 - \"X\"");
    symbol.addItem("7 - Círculo");
    symbol.addItem("8 - \"_\"");
    symbol.addItem("9 - \" \"");
    symbol.addItem("10 - \"\\\"");
    symbol.addItem("11 - \"/\"");
    symbol.addItem("12 - \"X\"");
    symbol.addItem("13 - Círculo");
    antialias.addItem("");
    antialias.addItem("Sí");
    antialias.addItem("No");
    this.add(height, null);
    this.add(labelheight, null);
    this.add(minsize, null);
    this.add(labelminsize, null);
    this.add(antialias, null);
    this.add(labelantialias, null);
    this.add(backgroundcolor, null);
    this.add(color, null);
    this.add(outlinecolor, null);
    this.add(width, null);
    this.add(maxsize, null);
    this.add(size, null);
    this.add(symbol, null);
    this.add(labelwidth, null);
    this.add(labelbackgroundcolor, null);
    this.add(labelcolor, null);
    this.add(labelmaxsize, null);
    this.add(labeloutlinecolor, null);
    this.add(labelmaxsize1, null);
    this.add(labelsymbol, null);
  }


  private void setStyle(StyleObject s) 
  {
    if (antialias.getSelectedIndex() > 0) 
    {
      if (antialias.getSelectedIndex() == 1) 
      {
        s.setAntiAlias("TRUE");
      } else {
        s.setAntiAlias("FALSE");
      }
    } else 
    {
      s.setAntiAlias("");
    }
    s.setBackGroundColor(backgroundcolor.getColor());
    s.setColor(color.getColor());
    s.setOutLineColor(outlinecolor.getColor());
    s.setMaxSize(maxsize.getText());
    s.setMinSize(minsize.getText());
    s.setSize(size.getText());
    s.setSymbol(symbol.getSelectedIndex());
    s.setOffSet(width.getText(),height.getText());
  }
 
  public void actPanel() 
  {
    this.antialias.setSelectedItem(style.getAntiAlias());
    this.symbol.setSelectedItem(style.getSymbol());
    this.size.setText(style.getSize());
    this.outlinecolor.setColor(style.getOutLineColor());
    this.maxsize.setText(style.getMaxSize());
    this.minsize.setText(style.getMinSize());
    this.width.setText(String.valueOf(style.getOffSet().getWidth()));
    this.height.setText(String.valueOf(style.getOffSet().getHeight()));
    this.color.setColor(style.getColor());
    this.backgroundcolor.setColor(style.getBackGroundColor());
  }
  
  public void setStyleSource(StyleObject s) 
  {
    this.style = s;
    actPanel();
  }

  public StyleObject getStyle() //GRABA Y DEVUELVE
  {
    try 
    {
      setStyle(style);
      return style;
    } catch (NullPointerException x) 
    {
      this.style = new StyleObject();
      setStyle(style);
      return style;
    }
  }  

}