package jms;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MapObjectPanel extends JPanel 
{
  private JLabel labelname = new JLabel();
  private JLabel labeldatapattern = new JLabel();
  private JLabel labeldebug = new JLabel();
  private JLabel labelextent = new JLabel();
  private JLabel labelminx = new JLabel();
  private JLabel labelminx1 = new JLabel();
  private JLabel labelmaxx = new JLabel();
  private JLabel labelmaxy = new JLabel();
  private JLabel labelfontset = new JLabel();
  private JLabel labelimagecolor = new JLabel();
  private JLabel labelimagetype = new JLabel();
  private JLabel labelresolution = new JLabel();
  private JLabel labelscale = new JLabel();
  private JLabel labelsize = new JLabel();
  private JLabel labelshapepath = new JLabel();
  private JLabel labelstatus = new JLabel();
  private JLabel labeltemplatepattern = new JLabel();
  private JLabel labelunits = new JLabel();
  private JLabel labelsymbolset = new JLabel();

  private JTextField name = new JTextField();
  private JTextField datapattern = new JTextField();
  private JTextField debug = new JTextField();
  private JTextField minx = new JTextField();
  private JTextField miny = new JTextField();
  private JTextField maxx = new JTextField();
  private JTextField maxy = new JTextField();
  private JTextField fontset = new JTextField();
  private ColorSelectorPanel imagecolor = new ColorSelectorPanel();
  private JComboBox imagetype = new JComboBox();
  private JTextField resolution = new JTextField();
  private JTextField scale = new JTextField();
  private JTextField size = new JTextField();
  private JTextField shapepath = new JTextField();
  private JComboBox status = new JComboBox();
  private JTextField templatepattern = new JTextField();
  private JComboBox units = new JComboBox();
  private JTextField symbolset = new JTextField();


  private MapObject map = new MapObject();

  public MapObjectPanel(MapObject param)
  {
    try
    {
      map = param;
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
    this.setSize(new Dimension(506, 426));
    name.setBounds(new Rectangle(75, 15, 195, 20));
    labelname.setText("Nombre:");
    labelname.setBounds(new Rectangle(15, 15, 55, 20));
    datapattern.setBounds(new Rectangle(100, 45, 195, 20));
    labeldatapattern.setText("Datapattern:");
    labeldatapattern.setBounds(new Rectangle(15, 45, 80, 20));
    debug.setBounds(new Rectangle(65, 75, 195, 20));
    labeldebug.setText("Debug:");
    labeldebug.setBounds(new Rectangle(15, 75, 50, 20));
    labelextent.setText("Extensión:");
    labelextent.setBounds(new Rectangle(210, 105, 65, 20));
    minx.setBounds(new Rectangle(55, 135, 165, 20));
    labelminx.setText("MinX:");
    labelminx.setBounds(new Rectangle(15, 135, 35, 20));
    labelminx1.setText("MinY:");
    labelminx1.setBounds(new Rectangle(240, 135, 35, 20));
    miny.setBounds(new Rectangle(280, 135, 165, 20));
    labelmaxx.setText("MaxX:");
    labelmaxx.setBounds(new Rectangle(15, 165, 40, 20));
    maxx.setBounds(new Rectangle(55, 165, 165, 20));
    labelmaxy.setText("MaxY:");
    labelmaxy.setBounds(new Rectangle(240, 165, 40, 20));
    maxy.setBounds(new Rectangle(280, 165, 165, 20));
    fontset.setBounds(new Rectangle(105, 195, 195, 20));
    labelfontset.setText("Archivo Font:");
    labelfontset.setBounds(new Rectangle(15, 195, 85, 20));
    labelimagecolor.setText("Color de Fondo:");
    labelimagecolor.setBounds(new Rectangle(200, 230, 100, 15));
    imagecolor.setBounds(new Rectangle(30, 250, 440, 40));
    labelimagetype.setText("Tipo de Imagen:");
    labelimagetype.setBounds(new Rectangle(15, 305, 100, 20));
    imagetype.setBounds(new Rectangle(120, 305, 100, 20));
    resolution.setBounds(new Rectangle(330, 305, 60, 20));
    labelresolution.setText("Resolución:");
    labelresolution.setBounds(new Rectangle(250, 305, 75, 20));
    scale.setBounds(new Rectangle(60, 335, 60, 20));
    labelscale.setText("Escala:");
    labelscale.setBounds(new Rectangle(15, 335, 45, 20));
    size.setBounds(new Rectangle(75, 365, 60, 20));
    labelsize.setText("Tamaño:");
    labelsize.setBounds(new Rectangle(15, 365, 60, 20));
    shapepath.setBounds(new Rectangle(240, 335, 195, 20));
    labelshapepath.setText("Ruta de Shapes:");
    labelshapepath.setBounds(new Rectangle(140, 335, 100, 20));
    labelstatus.setText("Activo:");
    labelstatus.setBounds(new Rectangle(290, 15, 45, 20));
    status.setBounds(new Rectangle(340, 15, 70, 20));
    templatepattern.setBounds(new Rectangle(295, 365, 195, 20));
    labeltemplatepattern.setText("Patrón de Plantilla:");
    labeltemplatepattern.setBounds(new Rectangle(170, 365, 120, 20));
    labelunits.setText("Unidades:");
    labelunits.setBounds(new Rectangle(295, 395, 65, 20));
    units.setBounds(new Rectangle(360, 395, 100, 20));
    symbolset.setBounds(new Rectangle(90, 395, 195, 20));
    labelsymbolset.setText("SymbolSet:");
    labelsymbolset.setBounds(new Rectangle(15, 395, 75, 20));
    imagetype.addItem("gif");
    imagetype.addItem("png");
    imagetype.addItem("jpeg");
    imagetype.addItem("wbmp");
    imagetype.addItem("gtiff");
    imagetype.addItem("swf");
    imagetype.addItem("userdefined");
    status.addItem("On");
    status.addItem("Off");
    units.addItem("feet");
    units.addItem("inches");
    units.addItem("kilometers");
    units.addItem("meters");
    units.addItem("miles");
    units.addItem("dd");
    this.add(labelsymbolset, null);
    this.add(symbolset, null);
    this.add(units, null);
    this.add(labelunits, null);
    this.add(labeltemplatepattern, null);
    this.add(templatepattern, null);
    this.add(status, null);
    this.add(labelstatus, null);
    this.add(labelshapepath, null);
    this.add(shapepath, null);
    this.add(labelsize, null);
    this.add(size, null);
    this.add(labelscale, null);
    this.add(scale, null);
    this.add(labelresolution, null);
    this.add(resolution, null);
    this.add(imagetype, null);
    this.add(labelimagetype, null);
    this.add(imagecolor, null);
    this.add(labelimagecolor, null);
    this.add(labelfontset, null);
    this.add(fontset, null);
    this.add(maxy, null);
    this.add(labelmaxy, null);
    this.add(maxx, null);
    this.add(labelmaxx, null);
    this.add(miny, null);
    this.add(labelminx1, null);
    this.add(labelminx, null);
    this.add(minx, null);
    this.add(labelextent, null);
    this.add(labeldebug, null);
    this.add(debug, null);
    this.add(labeldatapattern, null);
    this.add(datapattern, null);
    this.add(labelname, null);
    this.add(name, null);
    
  }

  private void setMap(MapObject m) 
  {
    this.map = m;
    m.setDataPattern(datapattern.getText());
    m.setDebug(debug.getText());
    m.setExtent(minx.getText() + " " + miny.getText() + " " + maxx.getText() + " " + maxy.getText());
    m.setFontSet(fontset.getText());
    m.setImageColor(imagecolor.getColor());
    if (imagetype.getSelectedIndex() == 0) 
    {
      m.setImageType(imagetype.getSelectedItem().toString());
    }
    if (status.getSelectedIndex() == 0) 
    {
      m.setStatus("On");
    } else if (status.getSelectedIndex() == 1) 
    {
      m.setStatus("Off");
    }
    m.setName(name.getText());
    m.setResolution(resolution.getText());
    m.setScale(scale.getText());
    m.setShapePath(shapepath.getText());
    m.setSize(size.getText());
    m.setSymbolSet(symbolset.getText());
    m.setTemplatePattern(templatepattern.getText());
  }

  private void AdmStyles_actionPerformed(ActionEvent e)
  {
    map.addLayerObject();
  }

  private void Cancelar_actionPerformed(ActionEvent e)
  {
  }

  private void Aceptar_actionPerformed(ActionEvent e)
  {
    setMap(map);
    System.out.println(map.generaClase());
  }

  public void actPanel() 
  {
    this.name.setText(map.getName());
    this.datapattern.setText(map.getDataPattern());
    this.debug.setText(map.getDebug());
    this.minx.setText(String.valueOf(map.getExtent().getMinX()));
    this.maxx.setText(String.valueOf(map.getExtent().getMaxX()));
    this.miny.setText(String.valueOf(map.getExtent().getMinY()));
    this.maxy.setText(String.valueOf(map.getExtent().getMaxY()));
    this.fontset.setText(map.getFontSet());
    this.imagecolor.setColor(map.getImageColor());
    this.imagetype.setSelectedItem(map.getImageType().toLowerCase()); //REVISAR
    this.resolution.setText(String.valueOf(map.getResolution()));
    this.scale.setText(String.valueOf(map.getScale()));
    this.size.setText(String.valueOf(map.getSize()));
    this.shapepath.setText(map.getShapePath());
    if (map.getStatus()) 
    {
      this.status.setSelectedItem("On"); //REVISAR
    } else 
    {
      this.status.setSelectedItem("Off"); //REVISAR
    }
    this.templatepattern.setText(map.getTemplatePattern());
    this.units.setSelectedItem(map.getUnits().toLowerCase()); //REVISAR
    this.symbolset.setText(map.getSymbolSet());
  }
 
  public void setMapSource(MapObject m) 
  {
    this.setMapSource(m);
    actPanel();
  }
}