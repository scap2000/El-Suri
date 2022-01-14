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
 * MapPanel.java
 *
 * */
package jms;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MapPanel extends JPanel 
{
  private MapObject map = new MapObject();
  private MapObjectPanel mapobjectpanel = new MapObjectPanel(map);
  private JList layerlist = new JList();
  private JLabel labellayerlist = new JLabel();
  private JScrollPane scrolllayerlist = new JScrollPane();
  private JScrollPane scrollclasslist = new JScrollPane();
  private JLabel labelclasslist = new JLabel();
  private JList classlist = new JList();
  private DialogLayer dialoglayer = new DialogLayer();
  private DialogClass dialogclass = new DialogClass();
  private DialogStyle dialogstyle = new DialogStyle();
  private JButton Estilo = new JButton();
  private JButton verMapaButton = new JButton();
  private JButton Ficha = new JButton();

  public MapPanel()
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
    this.setSize(new Dimension(677, 525));
    this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    mapobjectpanel.setBounds(new Rectangle(5, 5, 500, 425));
    layerlist.addListSelectionListener(new ListSelectionListener()
      {
        public void valueChanged(ListSelectionEvent e)
        {
          layerlist_valueChanged(e);
        }
      });
    classlist.addMouseListener(new MouseAdapter()
      {
        public void mouseClicked(MouseEvent e)
        {
          classlist_mouseClicked(e);
        }
      });
    scrollclasslist.getViewport();
    this.add(Ficha, null);
    this.add(verMapaButton, null);
    this.add(Estilo, null);
    this.add(labelclasslist, null);
    this.add(labellayerlist, null);
    scrollclasslist.getViewport().add(classlist, null);
    this.add(scrollclasslist, null);
    scrolllayerlist.getViewport().add(layerlist, null);
    this.add(scrolllayerlist, null);
    this.add(mapobjectpanel, null);
    labelclasslist.setText("Clases del Layer");
    labelclasslist.setBounds(new Rectangle(510, 155, 160, 20));
    labelclasslist.setHorizontalAlignment(SwingConstants.CENTER);
    scrollclasslist.setBounds(new Rectangle(510, 180, 160, 110));
    scrollclasslist.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    labellayerlist.setHorizontalAlignment(SwingConstants.CENTER);
    scrolllayerlist.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    scrolllayerlist.setBounds(new Rectangle(510, 35, 160, 110));
    labellayerlist.setBounds(new Rectangle(510, 10, 160, 20));
    labellayerlist.setText("Layers del Mapa");
    layerlist.addMouseListener(new java.awt.event.MouseAdapter()
      {
        public void mouseClicked(MouseEvent e)
        {
          layerlist_mouseClicked(e);
        }
      });

    map = map.ParseMe(map);
    Ficha.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Ficha_actionPerformed(e);
        }
      });
    Ficha.setMnemonic('F');
    Ficha.setBounds(new Rectangle(545, 440, 95, 25));
    Ficha.setText("Ver Ficha");
    verMapaButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          verMapaButton_actionPerformed(e);
        }
      });
    verMapaButton.setMnemonic('M');
    verMapaButton.setBounds(new Rectangle(545, 405, 95, 25));
    verMapaButton.setText("Ver Mapa");
    Estilo.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Estilo_actionPerformed(e);
        }
      });
    Estilo.setMnemonic('E');
    Estilo.setBounds(new Rectangle(510, 295, 160, 25));
    Estilo.setText("Estilo de Clase");
    actPanel();
  }

  private void layerlist_mouseClicked(MouseEvent e)
  {
    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1)
    {
      JTabbedPane tabbedpane = dialoglayer.getTabbedPane();
      tabbedpane.setSelectedIndex(layerlist.getSelectedIndex());
      dialoglayer.setTitle("Mapa: " + map.getName());
      dialoglayer.setMapSource(map);
//      dialoglayer.setModal(true);
      dialoglayer.setVisible(true);
      actPanel();
    }
    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) 
    {
      String[] classnames = new String[map.getLayerObject(layerlist.getSelectedIndex()).getClassCount()];
      for (int i=0; i<classnames.length; i++) 
      {
        classnames[i] = map.getLayerObject(layerlist.getSelectedIndex()).getClassObject(i).getName();
      }
      classlist.setListData(classnames);
    }
  }
  
  private void actPanel() 
  {
    JTabbedPane tabbedpane = dialoglayer.getTabbedPane();
    tabbedpane.removeAll();
    String[] layernames = new String[map.getLayerCount()];
    dialoglayer.getContentPane().add(tabbedpane, BorderLayout.CENTER);
    for (int i=0; i<layernames.length; i++) 
    {
      layernames[i] = map.getLayerObject(i).getName();
      LayerPanel layerpanel = new LayerPanel(this.map);
      layerpanel.setLayerSource(map.getLayerObject(i));
      String title = layernames[i];
      tabbedpane.addTab(title,layerpanel);
    }
    layerlist.setListData(layernames);
    mapobjectpanel.actPanel();
  }

  private void layerlist_valueChanged(ListSelectionEvent e)
  {
    if (layerlist.getSelectedIndex() > -1) 
    {
      JTabbedPane tabbedpane = dialogclass.getTabbedPane();
      tabbedpane.removeAll();
      String[] classnames = new String[map.getLayerObject(layerlist.getSelectedIndex()).getClassCount()];
      LayerObject temp = map.getLayerObject(layerlist.getSelectedIndex());
      for (int i=0; i<classnames.length; i++) 
      {
        classnames[i] = temp.getClassObject(i).getName();
        ClassPanel classpanel = new ClassPanel(temp);
        classpanel.setClassSource(temp.getClassObject(i));
        String title = classnames[i];
        tabbedpane.addTab(title,classpanel);
      }
      classlist.setListData(classnames);
    }
  }

  private void classlist_mouseClicked(MouseEvent e)
  {
    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1)
    {
      if (layerlist.getSelectedIndex() > -1 && classlist.getSelectedIndex() > -1) 
      {
        JTabbedPane tabbedpane = dialogclass.getTabbedPane();
        tabbedpane.setSelectedIndex(classlist.getSelectedIndex());
        dialogclass.setTitle("Layer: " + map.getLayerObject(layerlist.getSelectedIndex()).getName());
        dialogclass.setLayerSource(map.getLayerObject(layerlist.getSelectedIndex()));
        dialogclass.setModal(true);
        dialogclass.setVisible(true);
        actPanel();
      }
    }
  }

  private void Estilo_actionPerformed(ActionEvent e)
  {
    if (layerlist.getSelectedIndex() > -1 && classlist.getSelectedIndex() > -1) 
    {
      dialogstyle.setTitle("Clase: " + map.getLayerObject(layerlist.getSelectedIndex()).getClassObject(classlist.getSelectedIndex()).getName());
      dialogstyle.setClassSource(map.getLayerObject(layerlist.getSelectedIndex()).getClassObject(classlist.getSelectedIndex()));
      dialogstyle.setModal(true);
      dialogstyle.show();
    }
  }

  private void verMapaButton_actionPerformed(ActionEvent e)
  {
    BufferedImage rendImage = new BufferedImage(800, 600, BufferedImage.TYPE_BYTE_INDEXED);
//    LayerDrawPolys layer = new LayerDrawPolys(map.getLayerObject(0));
    LayerDraw layer = new LayerDraw(map, rendImage);
  }

  private void Ficha_actionPerformed(ActionEvent e)
  {
    BufferedImage rendImage = new BufferedImage(800, 600, BufferedImage.TYPE_BYTE_INDEXED);
    LayerDraw layer = new LayerDraw(map, rendImage, 32325);
  }

  public void verMapa(JTextField _ubicacion)
  {
    BufferedImage rendImage = new BufferedImage(800, 600, BufferedImage.TYPE_BYTE_INDEXED);
    LayerDraw layer = new LayerDraw(map, rendImage, _ubicacion);
  }
  
  public void verMapa() 
  {
    BufferedImage rendImage = new BufferedImage(800, 600, BufferedImage.TYPE_BYTE_INDEXED);
    LayerDraw layer = new LayerDraw(map, rendImage);
  }

}