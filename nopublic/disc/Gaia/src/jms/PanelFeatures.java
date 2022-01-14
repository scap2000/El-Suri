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
 * PanelFeatures.java
 *
 * */
package jms;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
//import oracle.jdeveloper.layout.VerticalFlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import oracle.jdeveloper.layout.VerticalFlowLayout;

public class PanelFeatures extends JFrame 
{
  private JPanel RefMap = new JPanel();
  private JScrollPane LayerList = new JScrollPane();
  private BufferedImage imagen;
  private String archivo = "";
  private Point x1 = new Point(0,0);
  private Point x2 = new Point(0,0);
  protected JCheckBox[] layeronoff = new JCheckBox[50];
  private JPanel LayerListPanel = new JPanel();
//  private VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JButton Redibujar = new JButton();
  private int numLayers = 0;
  private LayerObject[] layers;
  private LayerDraw padre;
  private VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  final static int SERVER_PORT = 5433; // puerto de escucha de nuestro servidor

  private String clientRequest;
  private BufferedReader reader;
  private PrintWriter writer;
  private Thread servidor;
  
  public PanelFeatures() 
  {
    try 
    {
      jbInit();
    } catch (Exception x) 
    {
      x.printStackTrace();
    }
  }
  
  public PanelFeatures(LayerDraw layerdraw)
  {
    padre = layerdraw;
    try 
    {
      jbInit();
    } catch (Exception x) 
    {
      x.printStackTrace();
    }
  }
  
  public void jbInit() 
  {
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(163, 536));
    this.setTitle("Herramientas");
    this.setResizable(false);
    RefMap = new JPanel() {
              public void paint(Graphics g) 
              {
                Graphics2D g2d = (Graphics2D)g;
                      try {
                          // Read from a file
                          File file = new File(archivo);
                          imagen = ImageIO.read(file);
//                          System.out.println("Imagen cargada...");
            //              if (!isVisible()) setVisible(true);
                          setBounds(new Rectangle(0, 0, imagen.getWidth(), imagen.getHeight()));
                          g2d.drawImage(imagen, 0, 0, imagen.getWidth(), imagen.getHeight(), null);
                          g2d.setColor(Color.red);
                          g2d.drawRect(x1.x, x1.y, x2.x, x2.y);
                          g2d.dispose();
                      } catch (IOException e) 
                      {

                      }
              }
    };
    RefMap.setBounds(new Rectangle(0, 25, 155, 115));
    RefMap.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    Redibujar.setText("Redibujar");
    Redibujar.setBounds(new Rectangle(30, 475, 93, 25));
    Redibujar.setMnemonic('R');
    Redibujar.setSize(new Dimension(93, 25));
    Redibujar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    Redibujar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Redibujar_actionPerformed(e);
        }
      });
    jLabel2.setText("Mapa de Referencia");
    jLabel2.setBounds(new Rectangle(20, 5, 120, 20));
    jLabel2.setSize(new Dimension(120, 20));
    jLabel1.setText("Listado de Capas");
    jLabel1.setBounds(new Rectangle(25, 155, 107, 20));
    jLabel1.setSize(new Dimension(107, 20));
    LayerList.setBounds(new Rectangle(0, 175, 155, 295));
    LayerList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    LayerListPanel.setLayout(verticalFlowLayout1);
//    LayerListPanel.setLayout(verticalFlowLayout1);
    this.getContentPane().add(Redibujar, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jLabel1, null);
    LayerList.getViewport().add(LayerListPanel, null);
    this.getContentPane().add(LayerList, null);
    this.getContentPane().add(RefMap, null);

  }
  
  public void setRefMap(String refmap) 
  {
    archivo = refmap;
    repaint();
  }
  
  public void setActualPoint(Point p1, Point p2, int fWidth, int fHeight) 
  {
    try {
    x1.x = imagen.getWidth()*p1.x/fWidth;
    x2.x = imagen.getWidth()*p2.x/fWidth;
    x2.y = imagen.getHeight()*p1.y/fHeight;
    x2.y = imagen.getHeight()*p2.y/fHeight;
    System.out.println(p1.x + " - " + x1.x);
    System.out.println(p2.x + " - " + x2.x);
    System.out.println(p1.y + " - " + x1.y);
    System.out.println(p2.y + " - " + x2.y);
    repaint();
    } catch (NullPointerException x) 
    {
      
    }
  }
  
  public void setLayers(int cantidad, LayerObject[] layer) 
  {
//    layeronoff = new JCheckBox[numLayers];
    layers = layer;
    numLayers = cantidad;
    for (int i = 0; i<numLayers; i++) 
    {
      layeronoff[i] = new JCheckBox(layer[i].getName(), layer[i].getonoff());
      LayerListPanel.add(layeronoff[i], null);
    }
  }

  private void Redibujar_actionPerformed(ActionEvent e)
  {
    actualizar();
    padre.actualizar();
  }
  
  public void actualizar() 
  {
    for (int i = 0; i<numLayers; i++) 
    {
      layers[i].setonoff(layeronoff[i].isSelected());
    }
  }
}

