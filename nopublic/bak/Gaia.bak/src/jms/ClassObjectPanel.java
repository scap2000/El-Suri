package jms;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClassObjectPanel extends JPanel 
{
  private JLabel labelname = new JLabel();
  private JLabel labelexpression = new JLabel();
  private JLabel labelbackgroundcolor = new JLabel();
  private JLabel labelcolor = new JLabel();
  private JLabel labelmaxsize = new JLabel();
  private JLabel labelminsize = new JLabel();
  private JLabel labeloutlinecolor = new JLabel();
  private JLabel labelmaxsize1 = new JLabel();
  private JLabel labelsymbol = new JLabel();
  private JLabel labeltemplate = new JLabel();
  private JLabel labeltext = new JLabel();

  private JTextField name = new JTextField();
  private ColorSelectorPanel backgroundcolor = new ColorSelectorPanel();
  private ColorSelectorPanel color = new ColorSelectorPanel();
  private JTextField maxsize = new JTextField();
  private JTextField minsize = new JTextField();
  private ColorSelectorPanel outlinecolor = new ColorSelectorPanel();
  private JTextField expression = new JTextField();
  private JTextField size = new JTextField();
  private JTextField template = new JTextField();
  private JTextField text = new JTextField();
  private JComboBox symbol = new JComboBox();
  
  private ClassObject clase;

  public ClassObjectPanel()
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
    this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    this.setSize(new Dimension(462, 446));
    labelname.setText("Nombre:");
    labelname.setBounds(new Rectangle(15, 10, 55, 20));
    name.setBounds(new Rectangle(75, 10, 260, 20));
    expression.setBounds(new Rectangle(140, 275, 260, 20));
    labelexpression.setText("Expresión:");
    labelexpression.setBounds(new Rectangle(65, 275, 70, 20));
    labelbackgroundcolor.setText("Color de los símbolos no transparentes:");
    labelbackgroundcolor.setBounds(new Rectangle(103, 55, 255, 15));
    backgroundcolor.setBounds(new Rectangle(10, 75, 440, 40));
    labelcolor.setText("Color de los dibujos:");
    labelcolor.setBounds(new Rectangle(165, 125, 130, 15));
    color.setBounds(new Rectangle(10, 145, 440, 40));
    labelmaxsize.setText("Tamaño Máximo:");
    labelmaxsize.setBounds(new Rectangle(170, 305, 110, 20));
    maxsize.setBounds(new Rectangle(175, 330, 105, 20));
    labelminsize.setText("Tamaño Minimo:");
    labelminsize.setBounds(new Rectangle(300, 305, 110, 20));
    minsize.setBounds(new Rectangle(305, 330, 105, 20));
    labeloutlinecolor.setText("Color de los contornos:");
    labeloutlinecolor.setBounds(new Rectangle(155, 195, 150, 15));
    outlinecolor.setBounds(new Rectangle(10, 215, 440, 40));
    size.setBounds(new Rectangle(45, 330, 105, 20));
    labelmaxsize1.setText("Tamaño:");
    labelmaxsize1.setBounds(new Rectangle(70, 305, 60, 20));
    labelsymbol.setText("Símbolo:");
    labelsymbol.setBounds(new Rectangle(45, 360, 60, 20));
    labeltemplate.setText("Plantilla:");
    labeltemplate.setBounds(new Rectangle(45, 390, 60, 20));
    template.setBounds(new Rectangle(110, 390, 300, 20));
    labeltext.setText("Texto:");
    labeltext.setBounds(new Rectangle(45, 420, 45, 20));
    text.setBounds(new Rectangle(95, 420, 315, 20));
    symbol.setBounds(new Rectangle(105, 360, 305, 20));
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
    this.add(labeltext, null);
    this.add(labeltemplate, null);
    this.add(labelsymbol, null);
    this.add(labelmaxsize1, null);
    this.add(labeloutlinecolor, null);
    this.add(labelminsize, null);
    this.add(labelmaxsize, null);
    this.add(labelcolor, null);
    this.add(labelbackgroundcolor, null);
    this.add(labelexpression, null);
    this.add(labelname, null);
    this.add(symbol, null);
    this.add(text, null);
    this.add(template, null);
    this.add(size, null);
    this.add(outlinecolor, null);
    this.add(minsize, null);
    this.add(maxsize, null);
    this.add(color, null);
    this.add(backgroundcolor, null);
    this.add(expression, null);
    this.add(name, null);
  }

  private void Aceptar_actionPerformed(ActionEvent e)
  {
    setClase(clase);
    System.out.println(clase.generaClase());
  }
  
  private void setClase(ClassObject c) 
  {
    c.setBackGroundColor(backgroundcolor.getColor());
    c.setColor(color.getColor());
    c.setOutLineColor(outlinecolor.getColor());
    c.setExpression(expression.getText());
    c.setMaxSize(maxsize.getText());
    c.setMinSize(minsize.getText());
    c.setName(name.getText());
    c.setSize(size.getText());
    c.setSymbol(symbol.getSelectedIndex());
    c.setTemplate(template.getText());
    c.setText(text.getText());
  }
  
  public void actPanel() 
  {
    this.name.setText(clase.getName());
    this.backgroundcolor.setColor(clase.getBackGroundColor());
    this.color.setColor(clase.getColor());
    this.maxsize.setText(clase.getMaxSize());
    this.minsize.setText(clase.getMinSize());
    this.outlinecolor.setColor(clase.getOutLineColor());
    this.expression.setText(clase.getExpression().getText());
    this.size.setText(clase.getSize());
    this.template.setText(clase.getTemplate());
    this.text.setText(clase.getText());
    this.symbol.setSelectedItem(clase.getSymbol());
  }

  public void setEmpty()
  {
    this.name.setText("");
    this.backgroundcolor.setColor(new Color(0,0,0));
    this.color.setColor(new Color(0,0,0));
    this.maxsize.setText("");
    this.minsize.setText("");
    this.outlinecolor.setColor(new Color(0,0,0));
    this.expression.setText("");
    this.size.setText("");
    this.template.setText("");
    this.text.setText("");
    this.symbol.setSelectedIndex(0);
  }
  
  
  public void setClassSource(ClassObject c) 
  {
    this.clase = c;
    actPanel();
  }
 
  public ClassObject getClase() //GRABA Y DEVUELVE
  {
    try 
    {
      setClase(clase);
      return clase;
    } catch (NullPointerException x) 
    {
      this.clase = new ClassObject();
      setClase(clase);
      return clase;
    }
  }  
}