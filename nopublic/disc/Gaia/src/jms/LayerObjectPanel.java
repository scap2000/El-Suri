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
 * LayerObjectPanel.java
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

public class LayerObjectPanel extends JPanel 
{
  private JLabel labelname = new JLabel();
  private JLabel labelgroup = new JLabel();
  private JLabel labelstatus = new JLabel();
  private JLabel labeltype = new JLabel();
  private JLabel labelconnection = new JLabel();
  private JLabel labeldata = new JLabel();
  private JLabel labeluser = new JLabel();
  private JLabel labeldbname = new JLabel();
  private JLabel labelhost = new JLabel();
  private JLabel labeldump = new JLabel();
  private JLabel labelfilter = new JLabel();
  private JLabel labelfilteritem = new JLabel();
  private JLabel labelfooter = new JLabel();
  private JLabel labelheader = new JLabel();
  private JLabel labellabelcache = new JLabel();
  private JLabel labellabelitem = new JLabel();
  private JLabel labellabelmaxscale = new JLabel();
  private JLabel labellabelminscale = new JLabel();
  private JLabel labellabelrequires = new JLabel();
  private JLabel labellabelsizeitem = new JLabel();
  private JLabel llabelminscale = new JLabel();
  private JLabel llabelmaxscale = new JLabel();
  private JLabel labeloffsite = new JLabel();
  private JLabel labelpostlabelcache = new JLabel();
  private JLabel labelsizeunits = new JLabel();
  private JLabel labelsymbolscale = new JLabel();
  private JLabel labeltemplate = new JLabel();
  private JLabel labeltoleranceunits = new JLabel();
  private JLabel labeltolerance = new JLabel();
  private JLabel labeltransparency = new JLabel();
  private JLabel labeltransform = new JLabel();
  private JTextField name = new JTextField();
  private JTextField group = new JTextField();
  private JComboBox status = new JComboBox();
  private JComboBox type = new JComboBox();
  private JComboBox connectiontype = new JComboBox();
  private JTextField data = new JTextField();
  private JTextField user = new JTextField();
  private JTextField dbname = new JTextField();
  private JTextField host = new JTextField();
  private JComboBox dump = new JComboBox();
  private JTextField filter = new JTextField();
  private JTextField filteritem = new JTextField();
  private JTextField footer = new JTextField();
  private JTextField header = new JTextField();
  private JComboBox labelcache = new JComboBox();
  private JTextField labelitem = new JTextField();
  private JTextField labelmaxscale = new JTextField();
  private JTextField labelminscale = new JTextField();
  private JTextField labelrequires = new JTextField();
  private JTextField labelsizeitem = new JTextField();
  private JTextField minscale = new JTextField();
  private JTextField maxscale = new JTextField();
  private ColorSelectorPanel offsite = new ColorSelectorPanel();
  private JComboBox postlabelcache = new JComboBox();
  private JComboBox sizeunits = new JComboBox();
  private JTextField symbolscale = new JTextField();
  private JTextField template = new JTextField();
  private JComboBox toleranceunits = new JComboBox();
  private JTextField tolerance = new JTextField();
  private JTextField transparency = new JTextField();
  private JComboBox transform = new JComboBox();
  private LayerObject layer;
  
  public LayerObjectPanel()
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
    this.setSize(new Dimension(551, 529));
    this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    labelname.setText("Nombre:");
    labelname.setBounds(new Rectangle(15, 15, 55, 20));
    name.setBounds(new Rectangle(75, 15, 195, 20));
    labelgroup.setText("Grupo:");
    labelgroup.setBounds(new Rectangle(290, 15, 50, 20));
    group.setBounds(new Rectangle(340, 15, 195, 20));
    labelstatus.setText("Activo:");
    labelstatus.setBounds(new Rectangle(15, 45, 55, 20));
    status.setBounds(new Rectangle(65, 45, 70, 20));
    labeltype.setText("Tipo:");
    labeltype.setBounds(new Rectangle(145, 45, 45, 20));
    type.setBounds(new Rectangle(180, 45, 100, 20));
    labelconnection.setText("Conexión:");
    labelconnection.setBounds(new Rectangle(295, 45, 65, 20));
    connectiontype.setBounds(new Rectangle(360, 45, 175, 20));
    labeldata.setText("Data:");
    labeldata.setBounds(new Rectangle(15, 105, 40, 20));
    data.setBounds(new Rectangle(55, 105, 480, 20));
    labeluser.setText("Usuario:");
    labeluser.setBounds(new Rectangle(175, 75, 55, 20));
    user.setBounds(new Rectangle(235, 75, 95, 20));
    labeldbname.setText("Base de Datos:");
    labeldbname.setBounds(new Rectangle(340, 75, 95, 20));
    dbname.setBounds(new Rectangle(440, 75, 95, 20));
    labelhost.setText("Host:");
    labelhost.setBounds(new Rectangle(15, 75, 35, 20));
    host.setBounds(new Rectangle(55, 75, 110, 20));
    labeldump.setText("Dump:");
    labeldump.setBounds(new Rectangle(15, 130, 50, 20));
    dump.setBounds(new Rectangle(65, 130, 100, 20));
    labelfilter.setText("Filtro:");
    labelfilter.setBounds(new Rectangle(185, 130, 45, 20));
    filter.setBounds(new Rectangle(230, 130, 110, 20));
    labelfilteritem.setText("FilterItem:");
    labelfilteritem.setBounds(new Rectangle(355, 130, 65, 20));
    filteritem.setBounds(new Rectangle(425, 130, 110, 20));
    labelfooter.setText("Pie:");
    labelfooter.setBounds(new Rectangle(195, 165, 25, 20));
    footer.setBounds(new Rectangle(225, 165, 110, 20));
    labelheader.setText("Cabecera:");
    labelheader.setBounds(new Rectangle(15, 165, 60, 20));
    header.setBounds(new Rectangle(80, 165, 110, 20));
    labellabelcache.setText("Label Cache:");
    labellabelcache.setBounds(new Rectangle(375, 165, 80, 20));
    labelcache.setBounds(new Rectangle(460, 165, 70, 20));
    labelitem.setBounds(new Rectangle(80, 195, 110, 20));
    labellabelitem.setText("LabelItem:");
    labellabelitem.setBounds(new Rectangle(10, 195, 65, 20));
    labelmaxscale.setBounds(new Rectangle(105, 225, 60, 20));
    labellabelmaxscale.setText("LabelMaxScale:");
    labellabelmaxscale.setBounds(new Rectangle(10, 225, 95, 20));
    labelminscale.setBounds(new Rectangle(270, 225, 60, 20));
    labellabelminscale.setText("LabelMinScale:");
    labellabelminscale.setBounds(new Rectangle(175, 225, 90, 20));
    labelrequires.setBounds(new Rectangle(435, 225, 110, 20));
    labellabelrequires.setText("LabelRequires:");
    labellabelrequires.setBounds(new Rectangle(335, 225, 90, 20));
    labelsizeitem.setBounds(new Rectangle(290, 195, 110, 20));
    labellabelsizeitem.setText("LabelSizeItem:");
    labellabelsizeitem.setBounds(new Rectangle(195, 195, 90, 20));
    minscale.setBounds(new Rectangle(235, 260, 60, 20));
    llabelminscale.setText("Escala Mín.:");
    llabelminscale.setBounds(new Rectangle(160, 260, 75, 20));
    maxscale.setBounds(new Rectangle(90, 260, 60, 20));
    llabelmaxscale.setText("Escala Máx.:");
    llabelmaxscale.setBounds(new Rectangle(10, 260, 80, 20));
    labeloffsite.setText("Color transparente (sólo en layers RASTER):");
    labeloffsite.setBounds(new Rectangle(135, 300, 280, 15));
    offsite.setBounds(new Rectangle(55, 320, 440, 40));
    labelpostlabelcache.setText("PostLabelCache:");
    labelpostlabelcache.setBounds(new Rectangle(10, 375, 100, 20));
    postlabelcache.setBounds(new Rectangle(120, 375, 100, 20));
    labelsizeunits.setText("Unidades del Class:");
    labelsizeunits.setBounds(new Rectangle(235, 375, 130, 20));
    sizeunits.setBounds(new Rectangle(365, 375, 100, 20));
    labelsymbolscale.setText("Escala del Symbol:");
    labelsymbolscale.setBounds(new Rectangle(235, 405, 120, 20));
    symbolscale.setBounds(new Rectangle(360, 405, 60, 20));
    labeltemplate.setText("Plantilla:");
    labeltemplate.setBounds(new Rectangle(15, 435, 60, 20));
    template.setBounds(new Rectangle(80, 435, 110, 20));
    labeltoleranceunits.setText("Unidades de Tolerancia:");
    labeltoleranceunits.setBounds(new Rectangle(145, 465, 150, 20));
    toleranceunits.setBounds(new Rectangle(300, 465, 100, 20));
    tolerance.setBounds(new Rectangle(80, 465, 60, 20));
    labeltolerance.setText("Tolerancia:");
    labeltolerance.setBounds(new Rectangle(10, 465, 75, 20));
    labeltransparency.setText("Transparencia:");
    labeltransparency.setBounds(new Rectangle(20, 500, 95, 20));
    transparency.setBounds(new Rectangle(115, 500, 110, 20));
    transform.setBounds(new Rectangle(340, 500, 100, 20));
    labeltransform.setText("Transformación:");
    labeltransform.setBounds(new Rectangle(235, 500, 110, 20));
    this.add(labeltransform, null);
    this.add(transform, null);
    this.add(transparency, null);
    this.add(labeltransparency, null);
    this.add(labeltolerance, null);
    this.add(tolerance, null);
    this.add(toleranceunits, null);
    this.add(labeltoleranceunits, null);
    this.add(template, null);
    this.add(labeltemplate, null);
    this.add(symbolscale, null);
    this.add(labelsymbolscale, null);
    this.add(sizeunits, null);
    this.add(labelsizeunits, null);
    this.add(postlabelcache, null);
    this.add(labelpostlabelcache, null);
    this.add(offsite, null);
    this.add(labeloffsite, null);
    this.add(llabelmaxscale, null);
    this.add(maxscale, null);
    this.add(llabelminscale, null);
    this.add(minscale, null);
    this.add(labellabelsizeitem, null);
    this.add(labelsizeitem, null);
    this.add(labellabelrequires, null);
    this.add(labelrequires, null);
    this.add(labellabelminscale, null);
    this.add(labelminscale, null);
    this.add(labellabelmaxscale, null);
    this.add(labelmaxscale, null);
    this.add(labellabelitem, null);
    this.add(labelitem, null);
    this.add(labelcache, null);
    this.add(labellabelcache, null);
    this.add(header, null);
    this.add(labelheader, null);
    this.add(footer, null);
    this.add(labelfooter, null);
    this.add(filteritem, null);
    this.add(labelfilteritem, null);
    this.add(filter, null);
    this.add(labelfilter, null);
    this.add(dump, null);
    this.add(labeldump, null);
    this.add(host, null);
    this.add(labelhost, null);
    this.add(dbname, null);
    this.add(labeldbname, null);
    this.add(user, null);
    this.add(labeluser, null);
    this.add(data, null);
    this.add(labeldata, null);
    this.add(connectiontype, null);
    this.add(labelconnection, null);
    this.add(type, null);
    this.add(labeltype, null);
    this.add(status, null);
    this.add(labelstatus, null);
    this.add(group, null);
    this.add(labelgroup, null);
    this.add(name, null);
    this.add(labelname, null);
    
    status.addItem("on");
    status.addItem("off");
    status.addItem("default");
    dump.addItem("true");
    dump.addItem("false");
    labelcache.addItem("on");
    labelcache.addItem("off");
    
    type.addItem("point");
    type.addItem("line");
    type.addItem("polygon");
    type.addItem("circle");
    type.addItem("annotation");
    type.addItem("raster");
    type.addItem("query");

    connectiontype.addItem("");
    connectiontype.addItem("local");
    connectiontype.addItem("sde");
    connectiontype.addItem("ogr");
    connectiontype.addItem("postgis");
    connectiontype.addItem("oraclespatial");
    connectiontype.addItem("wms");
    
    postlabelcache.addItem("true");
    postlabelcache.addItem("false");
    
    sizeunits.addItem("pixels");
    sizeunits.addItem("feet");
    sizeunits.addItem("inches");
    sizeunits.addItem("kilometers");
    sizeunits.addItem("meters");
    sizeunits.addItem("miles");

    toleranceunits.addItem("pixels");
    toleranceunits.addItem("feet");
    toleranceunits.addItem("inches");
    toleranceunits.addItem("kilometers");
    toleranceunits.addItem("meters");
    toleranceunits.addItem("miles");

    transform.addItem("true");
    transform.addItem("false");
  }
  
  private void setLayer(LayerObject l) 
  {
//    this.layer = l;
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
    l.setFilter(filter.getText());
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
    l.setName(name.getText());
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
    l.setSizeUnits(sizeunits.getSelectedItem().toString());
    l.setStatus(status.getSelectedItem().toString());
    l.setSymbolScale(Double.parseDouble("0" + symbolscale.getText()));
    l.setTemplate(template.getText());
    l.setTolerance(Double.parseDouble("0" + tolerance.getText()));
    l.setToleranceUnits(toleranceunits.getSelectedItem().toString());
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
    l.setType(type.getSelectedItem().toString());
  }

  private void AdmStyles_actionPerformed(ActionEvent e)
  {
    layer.addClassObject();
  }

  private void Cancelar_actionPerformed(ActionEvent e)
  {
  }

  private void Aceptar_actionPerformed(ActionEvent e)
  {
    setLayer(layer);
    System.out.println(layer.generaClase());
  }
  
  public void actPanel()
  {
    this.name.setText(layer.getName());
    this.group.setText(layer.getGroup());
    this.status.setSelectedItem(layer.getStatus()); //REVISAR
    this.type.setSelectedItem(layer.getType().toLowerCase());
    this.connectiontype.setSelectedItem(layer.getConnectionType().toLowerCase());
    this.data.setText(layer.getData());
    this.user.setText(layer.getConnection().getUser());
    this.dbname.setText(layer.getConnection().getDbName());
    this.host.setText(layer.getConnection().getHost());
    if (layer.getDump()) 
    {
      this.dump.setSelectedItem("true"); //REVISAR
    } else 
    {
      this.dump.setSelectedItem("false"); //REVISAR
    }
    this.filter.setText(layer.getFilter());
    this.filteritem.setText(layer.getFilterItem());
    this.footer.setText(layer.getFooter());
    this.header.setText(layer.getHeader());
    if (layer.getLabelCache()) 
    {
      this.labelcache.setSelectedItem("on"); //REVISAR
    } else 
    {
      this.labelcache.setSelectedItem("off"); //REVISAR
    }
    this.labelitem.setText(layer.getLabelItem());
    this.labelmaxscale.setText(layer.getLabelMaxScale());
    this.labelminscale.setText(layer.getLabelMinScale());
    this.labelrequires.setText(layer.getLabelRequires());
    this.labelsizeitem.setText(layer.getLabelSizeItem());
    this.minscale.setText(layer.getMinScale());
    this.maxscale.setText(layer.getMaxScale());
    this.offsite.setColor(layer.getOffsite());
    if (layer.getPostLabelCache()) 
    {
      this.postlabelcache.setSelectedItem("true"); //REVISAR
    } else 
    {
      this.postlabelcache.setSelectedItem("false"); //REVISAR
    }
    this.sizeunits.setSelectedItem(layer.getSizeUnits()); //REVISAR
    this.symbolscale.setText(layer.getSymbolScale());
    this.template.setText(layer.getTemplate());
    this.toleranceunits.setSelectedItem(layer.getToleranceUnits());
    this.tolerance.setText(layer.getTolerance());
    this.transparency.setText(layer.getTransparency());
    if (layer.getTransform())
    {
      this.transform.setSelectedItem("true"); //REVISAR
    } else 
    {
      this.transform.setSelectedItem("false"); //REVISAR
    }
  }

  public void setEmpty()
  {
    this.name.setText("");
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
    this.transform.setSelectedIndex(0); //REVISAR
  }  

  public void setLayerSource(LayerObject l) 
  {
    this.layer = l;
    actPanel();
  }
  
  public LayerObject getLayer() //GRABA Y DEVUELVE
  {
    try 
    {
      setLayer(layer);
      return layer;
    } catch (NullPointerException x) 
    {
      this.layer = new LayerObject();
      setLayer(layer);
      return layer;
    }
  }
}
