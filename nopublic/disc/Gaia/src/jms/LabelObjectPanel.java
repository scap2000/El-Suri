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
 * LabelObjectPanel.java
 *
 * */
package jms;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LabelObjectPanel extends JPanel 
{
  private JLabel labelangle = new JLabel();
  private JLabel labelantialias = new JLabel();
  private JLabel labelfont = new JLabel();
  private JLabel labelbuffer = new JLabel();
  private JLabel labeltype = new JLabel();
  private JLabel labelsize = new JLabel();
  private JTextField angle = new JTextField();
  private JComboBox antialias = new JComboBox();
  private JTextField font = new JTextField();
  private JTextField buffer = new JTextField();
  private JComboBox type = new JComboBox();
  private JTextField size = new JTextField();

  private LabelObject label = new LabelObject();
  private JLabel labelbackgroundcolor = new JLabel();
  private ColorSelectorPanel backgroundcolor = new ColorSelectorPanel();
  private JLabel labelbackgroundshadowcolor = new JLabel();
  private ColorSelectorPanel backgroundcolor1 = new ColorSelectorPanel();
  private JTextField minsize = new JTextField();
  private JLabel labelminsize = new JLabel();
  private JLabel labelminfeaturesize = new JLabel();
  private JTextField minfeaturesize = new JTextField();
  private JTextField maxsize = new JTextField();
  private JLabel labelmaxsize = new JLabel();
  private JLabel labelmindistance = new JLabel();
  private JTextField mindistance = new JTextField();
  private JTextField backgroundshadowwidth = new JTextField();
  private JLabel labelbackgroundshadowwidth = new JLabel();
  private JLabel labelbackgroundshadowheight = new JLabel();
  private JTextField backgroundshadowheight = new JTextField();
  private ColorSelectorPanel color = new ColorSelectorPanel();
  private JLabel labelcolor = new JLabel();
  private JComboBox force = new JComboBox();
  private JLabel labelforce = new JLabel();
  private JLabel labeloffsetheight = new JLabel();
  private JTextField offsetheight = new JTextField();
  private JComboBox partials = new JComboBox();
  private JLabel labelpartials = new JLabel();
  private JLabel labeloutlinecolor = new JLabel();
  private ColorSelectorPanel outlinecolor = new ColorSelectorPanel();
  private JLabel labelshadowcolor = new JLabel();
  private ColorSelectorPanel shadowcolor = new ColorSelectorPanel();
  private JComboBox position = new JComboBox();
  private JLabel labelposition = new JLabel();
  private JLabel labeloffsetwidth = new JLabel();
  private JTextField offsetwidth = new JTextField();
  private JTextField shadowheight = new JTextField();
  private JLabel labelshadowheight = new JLabel();
  private JTextField shadowwidth = new JTextField();
  private JLabel labelshadowwidth = new JLabel();

  
  public LabelObjectPanel()
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
    this.setSize(new Dimension(551, 660));
    this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    labelangle.setText("Angulo:");
    labelangle.setBounds(new Rectangle(15, 15, 55, 20));
    angle.setBounds(new Rectangle(75, 15, 70, 20));
    labelantialias.setText("Antialias:");
    labelantialias.setBounds(new Rectangle(160, 15, 65, 20));
    labelantialias.setToolTipText("null");
    antialias.setBounds(new Rectangle(225, 15, 70, 20));
    labelfont.setText("Fuente:");
    labelfont.setBounds(new Rectangle(280, 220, 45, 20));
    font.setBounds(new Rectangle(335, 220, 110, 20));
    labelbuffer.setText("Buffer:");
    labelbuffer.setBounds(new Rectangle(120, 220, 45, 20));
    buffer.setBounds(new Rectangle(165, 220, 60, 20));
    labeltype.setText("Tipo:");
    labeltype.setBounds(new Rectangle(180, 610, 35, 20));
    type.setBounds(new Rectangle(220, 610, 100, 20));
    size.setBounds(new Rectangle(95, 610, 60, 20));
    labelsize.setText("Tamaño:");
    labelsize.setBounds(new Rectangle(35, 610, 60, 20));
    labelbackgroundcolor.setText("Color de fondo del rectángulo de la etiqueta:");
    labelbackgroundcolor.setBounds(new Rectangle(130, 50, 280, 15));
    backgroundcolor.setBounds(new Rectangle(50, 70, 440, 40));
    labelbackgroundshadowcolor.setText("Color de la sombra del rectángulo de la etiqueta:");
    labelbackgroundshadowcolor.setBounds(new Rectangle(115, 120, 305, 15));
    backgroundcolor1.setBounds(new Rectangle(50, 140, 440, 40));
    minsize.setBounds(new Rectangle(205, 400, 85, 20));
    labelminsize.setText("Tamaño Mínimo:");
    labelminsize.setBounds(new Rectangle(190, 375, 110, 20));
    labelminfeaturesize.setText("Tamaño Mínimo (obj):");
    labelminfeaturesize.setBounds(new Rectangle(30, 375, 140, 20));
    minfeaturesize.setBounds(new Rectangle(60, 400, 85, 20));
    maxsize.setBounds(new Rectangle(60, 350, 85, 20));
    labelmaxsize.setText("Tamaño Máximo:");
    labelmaxsize.setBounds(new Rectangle(45, 325, 110, 20));
    labelmindistance.setText("Distancia Mínima:");
    labelmindistance.setBounds(new Rectangle(190, 325, 120, 20));
    mindistance.setBounds(new Rectangle(205, 350, 85, 20));
    backgroundshadowwidth.setBounds(new Rectangle(165, 190, 85, 20));
    labelbackgroundshadowwidth.setText("Offset Vertical (Sombra):");
    labelbackgroundshadowwidth.setBounds(new Rectangle(10, 190, 160, 20));
    labelbackgroundshadowheight.setText("Offset Horizontal (Sombra):");
    labelbackgroundshadowheight.setBounds(new Rectangle(275, 190, 175, 20));
    backgroundshadowheight.setBounds(new Rectangle(450, 190, 85, 20));
    color.setBounds(new Rectangle(50, 270, 440, 40));
    labelcolor.setText("Color de la etiqueta:");
    labelcolor.setBounds(new Rectangle(205, 250, 130, 15));
    force.setBounds(new Rectangle(370, 15, 70, 20));
    labelforce.setText("Forzar:");
    labelforce.setBounds(new Rectangle(320, 15, 45, 20));
    labelforce.setToolTipText("null");
    labeloffsetheight.setText("Offset Hor.:");
    labeloffsetheight.setBounds(new Rectangle(330, 325, 75, 20));
    offsetheight.setBounds(new Rectangle(325, 350, 85, 20));
    partials.setBounds(new Rectangle(335, 400, 70, 20));
    labelpartials.setText("Etiqueta Parcial:");
    labelpartials.setBounds(new Rectangle(320, 375, 100, 20));
    labelpartials.setToolTipText("null");
    labeloutlinecolor.setText("Color del contorno:");
    labeloutlinecolor.setBounds(new Rectangle(200, 435, 125, 15));
    outlinecolor.setBounds(new Rectangle(40, 455, 440, 40));
    labelshadowcolor.setText("Color de la sombra:");
    labelshadowcolor.setBounds(new Rectangle(195, 505, 125, 15));
    shadowcolor.setBounds(new Rectangle(40, 525, 440, 40));
    position.setBounds(new Rectangle(435, 400, 70, 20));
    labelposition.setText("Ubicación:");
    labelposition.setBounds(new Rectangle(440, 375, 65, 20));
    labelposition.setToolTipText("null");
    labeloffsetwidth.setText("Offset Ver.:");
    labeloffsetwidth.setBounds(new Rectangle(435, 325, 70, 20));
    offsetwidth.setBounds(new Rectangle(430, 350, 85, 20));
    shadowheight.setBounds(new Rectangle(455, 575, 85, 20));
    labelshadowheight.setText("Offset Horizontal (Sombra):");
    labelshadowheight.setBounds(new Rectangle(280, 575, 175, 20));
    shadowwidth.setBounds(new Rectangle(170, 575, 85, 20));
    labelshadowwidth.setText("Offset Vertical (Sombra):");
    labelshadowwidth.setBounds(new Rectangle(15, 575, 160, 20));
    this.add(labelshadowwidth, null);
    this.add(shadowwidth, null);
    this.add(labelshadowheight, null);
    this.add(shadowheight, null);
    this.add(offsetwidth, null);
    this.add(labeloffsetwidth, null);
    this.add(labelposition, null);
    this.add(position, null);
    this.add(shadowcolor, null);
    this.add(labelshadowcolor, null);
    this.add(outlinecolor, null);
    this.add(labeloutlinecolor, null);
    this.add(labelpartials, null);
    this.add(partials, null);
    this.add(offsetheight, null);
    this.add(labeloffsetheight, null);
    this.add(labelforce, null);
    this.add(force, null);
    this.add(color, null);
    this.add(labelcolor, null);
    this.add(backgroundshadowheight, null);
    this.add(labelbackgroundshadowheight, null);
    this.add(labelbackgroundshadowwidth, null);
    this.add(backgroundshadowwidth, null);
    this.add(mindistance, null);
    this.add(labelmindistance, null);
    this.add(labelmaxsize, null);
    this.add(maxsize, null);
    this.add(minfeaturesize, null);
    this.add(labelminfeaturesize, null);
    this.add(labelminsize, null);
    this.add(minsize, null);
    this.add(backgroundcolor1, null);
    this.add(labelbackgroundshadowcolor, null);
    this.add(backgroundcolor, null);
    this.add(labelbackgroundcolor, null);
    this.add(labelsize, null);
    this.add(size, null);
    this.add(type, null);
    this.add(labeltype, null);
    this.add(buffer, null);
    this.add(labelbuffer, null);
    this.add(font, null);
    this.add(labelfont, null);
    this.add(antialias, null);
    this.add(labelantialias, null);
    this.add(angle, null);
    this.add(labelangle, null);
    
    antialias.addItem("true");
    antialias.addItem("false");
    force.addItem("true");
    force.addItem("false");
    partials.addItem("true");
    partials.addItem("false");
    position.addItem("ul");
    position.addItem("uc");
    position.addItem("ur");
    position.addItem("cl");
    position.addItem("cc");
    position.addItem("cr");
    position.addItem("ll");
    position.addItem("lc");
    position.addItem("lr");
    position.addItem("auto");

    

    type.addItem("bitmap");
    type.addItem("truetype");


    
  }
  
  private void setLayer(LayerObject l) 
  {
/*//    this.layer = l;
    jmsConnection c = new jmsConnection();
    c.setDbName(dbname.getText());
    c.setHost(host.getText());
    c.setUser(user.getText());
    c.setPassword("");
    c.setPort("5432");
//    l.setConnection(c);
//    l.setConnectionType(connectiontype.getSelectedItem().toString()); //REVISAR
    l.setData(data.getText());
    if (dump.getSelectedIndex()>0) 
    {
      if (dump.getSelectedIndex() == 1) 
      {
        l.setDump(true);
      } else 
      {
        l.setDump(false);
      }
    }
    l.setFilter(font.getText());
    l.setFilterItem(filteritem.getText());
    l.setFooter(footer.getText());
    l.setGroup(group.getText());
    l.setHeader(header.getText());
    if (labelcache.getSelectedIndex()>0) 
    {
      if (labelcache.getSelectedIndex() == 1) 
      {
        l.setLabelCache(true);
      } else 
      {
        l.setLabelCache(false);
      }
    }
    l.setLabelItem(labelitem.getText());
    l.setLabelMaxScale(Double.parseDouble("0" + labelmaxscale.getText()));
    l.setLabelMinScale(Double.parseDouble("0" + labelminscale.getText()));
    l.setLabelRequires(labelrequires.getText());
    l.setLabelSizeItem(labelsizeitem.getText());
    l.setMaxScale(Double.parseDouble("0" + maxscale.getText()));
    l.setMinScale(Double.parseDouble("0" + minscale.getText()));
    l.setName(angle.getText());
    l.setOffSite(offsite.getColor());
    if (postlabelcache.getSelectedIndex()>0) 
    {
      if (postlabelcache.getSelectedIndex() == 1) 
      {
        l.setPostLabelCache(true);
      } else 
      {
        l.setPostLabelCache(false);
      }
    }
    if (sizeunits.getSelectedIndex() > -1) 
    {
      l.setSizeUnits(unitsv[sizeunits.getSelectedIndex()]);
    } else 
    {
      l.setSizeUnits(unitsv[0]);
    }
    if (antialias.getSelectedIndex() == 0) 
    {
      l.setStatus("On");
    } else if (antialias.getSelectedIndex() == 1) 
    {
      l.setStatus("Off");
    } else 
    {
      l.setStatus("Default");
    }
    l.setSymbolScale(Double.parseDouble("0" + buffer.getText()));
    l.setTemplate(template.getText());
    l.setTolerance(Double.parseDouble("0" + tolerance.getText()));
    l.setToleranceUnits(unitsv[toleranceunits.getSelectedIndex()]);
    if (transform.getSelectedIndex()>0) 
    {
      if (transform.getSelectedIndex() == 1) 
      {
        l.setTransform(true);
      } else 
      {
        l.setTransform(false);
      }
    }
    l.setTransparency(Integer.parseInt("0" + transparency.getText()));
    l.setType(types[type.getSelectedIndex()]);*/
  }  

  public void actPanel()
  {
/*    this.name.setText(layer.getName());
    this.group.setText(layer.getGroup());
    this.status.setSelectedItem(layer.getStatus()); //REVISAR
    this.type.setSelectedItem(layer.getType()); //REVISAR
    this.connectiontype.setSelectedItem(layer.getConnectionType()); //REVISAR
    this.data.setText(layer.getData());
    this.user.setText(layer.getConnection().getUser());
    this.dbname.setText(layer.getConnection().getDbName());
    this.host.setText(layer.getConnection().getHost());
//    this.dump.setSelectedItem(layer.getDump().toString()); //REVISAR
    this.filter.setText(layer.getFilter());
    this.filteritem.setText(layer.getFilterItem());
    this.footer.setText(layer.getFooter());
    this.header.setText(layer.getHeader());
//    this.labelcache.setSelectedItem(layer.getLabelCache().toString()); //REVISAR
    this.labelitem.setText(layer.getLabelItem());
    this.labelmaxscale.setText(layer.getLabelMaxScale());
    this.labelminscale.setText(layer.getLabelMinScale());
    this.labelrequires.setText(layer.getLabelRequires());
    this.labelsizeitem.setText(layer.getLabelSizeItem());
    this.minscale.setText(layer.getMinScale());
    this.maxscale.setText(layer.getMaxScale());
    this.offsite.setColor(layer.getOffsite());
//    this.postlabelcache.setSelectedItem(layer.getPostLabelCache().toString()); //REVISAR
    this.sizeunits.setSelectedItem(layer.getSizeUnits()); //REVISAR
    this.symbolscale.setText(layer.getSymbolScale());
    this.template.setText(layer.getTemplate());
    this.toleranceunits.setSelectedItem(layer.getToleranceUnits());
    this.tolerance.setText(layer.getTolerance());
    this.transparency.setText(layer.getTransparency());
//    this.transform.setSelectedItem(layer.getTransform().toString()); //REVISAR*/
  }

  public void setEmpty()
  {
/*    this.name.setText("");
    this.group.setText("");
    this.status.setSelectedIndex(0); //REVISAR
    this.type.setSelectedIndex(0); //REVISAR
    this.connectiontype.setSelectedIndex(0); //REVISAR
    this.data.setText("");
    this.user.setText("");
    this.dbname.setText("");
    this.host.setText("");
    this.dump.setSelectedIndex(0); //REVISAR
    this.filter.setText("");
    this.filteritem.setText("");
    this.footer.setText("");
    this.header.setText("");
    this.labelcache.setSelectedIndex(0); //REVISAR
    this.labelitem.setText("");
    this.labelmaxscale.setText("");
    this.labelminscale.setText("");
    this.labelrequires.setText("");
    this.labelsizeitem.setText("");
    this.minscale.setText("");
    this.maxscale.setText("");
    this.offsite.setColor(new Color(0,0,0));
    this.postlabelcache.setSelectedIndex(0); //REVISAR
    this.sizeunits.setSelectedIndex(0); //REVISAR
    this.symbolscale.setText("");
    this.template.setText("");
    this.toleranceunits.setSelectedIndex(0);
    this.tolerance.setText("");
    this.transparency.setText("");
    this.transform.setSelectedIndex(0); //REVISAR*/
  }  

  private void setLabel(LabelObject s) 
  {
/*    if (antialias.getSelectedIndex() > 0) 
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
    s.setOffSet(width.getText(),height.getText());*/
  }

  public void setLabelSource(LabelObject s) 
  {
    this.label = s;
    actPanel();
  }

  public LabelObject getLabel() //GRABA Y DEVUELVE
  {
    try 
    {
      setLabel(label);
      return label;
    } catch (NullPointerException x) 
    {
      this.label = new LabelObject();
      setLabel(label);
      return label;
    }
  } 
}
