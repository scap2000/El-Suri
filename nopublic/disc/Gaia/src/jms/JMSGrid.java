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
 * JMSGrid.java
 *
 * */
package jms;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class JMSGrid extends JFrame
{

  private boolean conectado = false;
  //private String host = "192.168.10.220";
  private String host = "magnumgis.dnsalias.org";
  private BufferedReader reader;
  private PrintWriter writer;
      
  Rectangle currentRect = null;
  Rectangle rectToDraw = null;  
  Rectangle previousRectDrawn = new Rectangle();
  boolean finDrawing = false;
  private boolean loaded = false;
  int fWidth = 800,fHeight = 600;
  BufferedImage image = null;
  private BufferedImage imagen = new BufferedImage(fWidth, fHeight, BufferedImage.TYPE_BYTE_INDEXED);
  DecimalFormat d4 = new DecimalFormat("0.0000");
  DecimalFormat d2 = new DecimalFormat("0.00");

  private JPanel dibujo = new JPanel();
  private JLabel statusbar = new JLabel();
  private JLabel coordbar = new JLabel();
  private JLabel infobar = new JLabel();
  private JToolBar jToolBar1 = new JToolBar();
  private JButton zoominbtn = new JButton(new ImageIcon(JMSApplet.class.getResource("iconos/zoomin.png")));
  private JButton zoomoutbtn = new JButton(new ImageIcon(JMSApplet.class.getResource("iconos/zoomout.png")));
  private JButton zommextbtn = new JButton(new ImageIcon(JMSApplet.class.getResource("iconos/extents.png")));
  private JButton recenterbtn = new JButton(new ImageIcon(JMSApplet.class.getResource("iconos/consultar.png")));
  private JButton redrawbtn = new JButton(new ImageIcon(JMSApplet.class.getResource("iconos/redraw.png")));
  private JButton distancebtn = new JButton(new ImageIcon(JMSApplet.class.getResource("iconos/regla.png")));
  private JButton saveasbtn = new JButton(new ImageIcon(JMSApplet.class.getResource("iconos/grabarcomo.png")));
  private JButton statusbtn = new JButton();

  private double ScaleX = 1;
  
  double Factor = 0;
  double xOffset = 0;
  double yOffset = 0;
  double extX = 0;
  double extY = 0;
  
  int topx = 0, topy = 0, prevx = 0, prevy = 0;

  private String archivo = "";
  Thread tarea;

  String status = "";
  int max = 0;
  int current = 0;
  
  Point2D.Double[] distpoints = new Point2D.Double[100];
  Point[] polypoints = new Point[100];
  int cantdistpoints = 0;
  double distanciatotal = 0;
  boolean drawing = false;
  boolean osnap = false;
  private JProgressBar progresobar = new JProgressBar(max, current);
  private JButton Cancelar = new JButton();
  private JTextField ubicacion;
  private boolean firstLoad = true;  
  private Extent extents = new Extent();
  private Extent origExt = new Extent();

  protected MouseListener eraseListener;
  protected MouseMotionListener eraseMotionListener;
  protected MouseWheelListener eraseWheelListener;

  protected MouseListener ZoomInListener = new MouseListener() 
  {
    public void mouseReleased(MouseEvent event)
    {
      if (currentRect != null)
      {
        if (Math.abs(currentRect.width)>3 && Math.abs(currentRect.height)>3)
        {
          Point2D.Double puntoOrigen = toSpace(currentRect.x,currentRect.y);
          Point2D.Double puntoDestino = toSpace(currentRect.x+currentRect.width,currentRect.y+currentRect.height);
          extents.setStrExtension(puntoOrigen.x + " " + puntoOrigen.y + " " + puntoDestino.x + " " + puntoDestino.y);
          currentRect = null;
          finDrawing = false;
          loaded = false;
          image = null;
          rundoImage();
          repaint();
        } else 
        {
//          zoom(2);
        }
      }
    }
    public void mousePressed(MouseEvent event)
    {
      if (event.getButton() == MouseEvent.BUTTON1)
      {
        int mouseX = event.getX();
        int mouseY = event.getY();
        Shape shape = new Line2D.Float(mouseX, mouseY, mouseX, mouseY);
        currentRect = new Rectangle(mouseX, mouseY, 0, 0);
        updateDrawableRect(getWidth(), getHeight());
        repaint();
      }
    }
    public void mouseClicked(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {}        
    public void mouseExited(MouseEvent event) {}      
    
  };

  protected MouseMotionListener ZoomInMotionListener = new MouseMotionListener() 
  {
    public void mouseMoved(MouseEvent event)
    {
      Point2D.Double punto = toSpace(event.getX(),event.getY());
      coordbar.setText("X: " + d4.format(punto.x) + ", Y: " + d4.format(punto.y) + " - Zoom: " + d2.format(((ScaleX-1)*100)+100) + "%");
    }
    
    public void mouseDragged(MouseEvent event)
    {
      updateSize(event);
      int mouseX = event.getX();
      int mouseY = event.getY();
      Shape shape = new Line2D.Float(mouseX, mouseY, mouseX, mouseY);
      Point2D.Double punto = toSpace(event.getX(),event.getY());
      coordbar.setText("X: " + d4.format(punto.x) + ", Y: " + d4.format(punto.y) + " - Zoom: " + d2.format(((ScaleX-1)*100)+100) + "%");
    }
  };            
  
  protected MouseListener DistanceListener = new MouseListener() 
  {
    public void mousePressed(MouseEvent event)
    {
      if (event.getButton() == MouseEvent.BUTTON1)
      {
        int mouseX = event.getX();
        int mouseY = event.getY();
        Shape shape = new Line2D.Float(mouseX, mouseY, mouseX, mouseY);
        currentRect = new Rectangle(mouseX, mouseY, 0, 0);
        updateDrawableRect(getWidth(), getHeight());
        repaint();
      }
    }
    public void mouseClicked(MouseEvent event) 
    {
      if (event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 1)
      {
        distance(event.getX(), event.getY(), true);
        drawing = true;
      }
    }

    public void mouseReleased(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {}        
    public void mouseExited(MouseEvent event) {}      
  };

  protected MouseMotionListener DistanceMotionListener = new MouseMotionListener() 
  {
    public void mouseMoved(MouseEvent event) 
    {
     if (cantdistpoints>0) 
      {
        polypoints[cantdistpoints] = new Point(event.getX(), event.getY());
        distance(event.getX(), event.getY(), false);
        osnap = false;
        for (int i=0; i<cantdistpoints; i++) 
        {
          if (Math.abs(event.getX()-polypoints[i].x)<10 && Math.abs(event.getY()-polypoints[i].y)<10) 
          {
            polypoints[cantdistpoints] = new Point(polypoints[i].x, polypoints[i].y);
            distance(polypoints[i].x, polypoints[i].y, false);
            osnap = true;
          }
        }
        repaint();
      }
    }
    public void mouseDragged(MouseEvent event) {}
  };

  protected MouseMotionListener QueryMotionListener = new MouseMotionListener() 
  {
    public void mouseMoved(MouseEvent event)
    {
      Point2D.Double punto = toSpace(event.getX(),event.getY());
      coordbar.setText("X: " + d4.format(punto.x) + ", Y: " + d4.format(punto.y) + " - Zoom: " + d2.format(((ScaleX-1)*100)+100) + "%");
    }
    public void mouseDragged(MouseEvent event)
    {
      updateSize(event);
      int mouseX = event.getX();
      int mouseY = event.getY();
      Shape shape = new Line2D.Float(mouseX, mouseY, mouseX, mouseY);
      Point2D.Double punto = toSpace(event.getX(),event.getY());
      coordbar.setText("X: " + d4.format(punto.x) + ", Y: " + d4.format(punto.y) + " - Zoom: " + d2.format(((ScaleX-1)*100)+100) + "%");
    }
  };
   
////////////////////// ------------------------- PARA LA DEMO ----------------------------- ///////////////////////

  protected MouseListener CaptureListener = new MouseListener() 
  {
    public void mouseReleased(MouseEvent event) {}
    public void mousePressed(MouseEvent event) {}
    public void mouseClicked(MouseEvent event) 
    {
      Point2D captura = toSpace(event.getX(),event.getY());
      ubicacion.setText(String.valueOf(captura.getX()) + "," + String.valueOf(captura.getY()));
    }
    public void mouseEntered(MouseEvent event) {}        
    public void mouseExited(MouseEvent event) {}      
  };
  
  protected MouseMotionListener CaptureMotionListener = new MouseMotionListener() 
  {
    public void mouseMoved(MouseEvent event)
    {
      Point2D.Double punto = toSpace(event.getX(),event.getY());
      coordbar.setText("X: " + d4.format(punto.x) + ", Y: " + d4.format(punto.y) + " - Zoom: " + d2.format(((ScaleX-1)*100)+100) + "%");
    }
    public void mouseDragged(MouseEvent event)
    {
      updateSize(event);
      int mouseX = event.getX();
      int mouseY = event.getY();
      Shape shape = new Line2D.Float(mouseX, mouseY, mouseX, mouseY);
      Point2D.Double punto = toSpace(event.getX(),event.getY());
      coordbar.setText("X: " + d4.format(punto.x) + ", Y: " + d4.format(punto.y) + " - Zoom: " + d2.format(((ScaleX-1)*100)+100) + "%");
    }
  };


////////////////////// ------------------------- PARA LA DEMO ----------------------------- ///////////////////////

  protected MouseListener RecenterListener = new MouseListener() 
  {
    public void mouseClicked(MouseEvent event) 
    {
      if (event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 1)
      {
        System.out.println("Recenter");
      }
    }
    public void mouseReleased(MouseEvent event) {}
    public void mousePressed(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {}        
    public void mouseExited(MouseEvent event) {}      
  };

  protected MouseMotionListener RecenterMotionListener = new MouseMotionListener()  
  {
    public void mouseMoved(MouseEvent event) {}
    public void mouseDragged(MouseEvent event) {}
  };
  private JComboBox comboBarrios = new JComboBox();
  private JButton goBarrio = new JButton();

  public static void main(String[] args)
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    new JMSGrid();
  }
  
  public JMSGrid()
  {
    init();
  }

  public void init()
  {
    try
    {
      jbInit();
      //rundoImage();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception
  {
    dibujo = new JPanel() { 
              public void paint(Graphics g) 
              {
                Graphics2D g2d = (Graphics2D)g;
                if (!loaded) 
                {
                  try
                  {
                    GeneralPath path = new GeneralPath();
                    if ((conectado) && (!archivo.equals(""))) 
                    {
                      try {
                          URL direccion = new URL("http://" + host + archivo);
                          System.out.println("Intentando leer la imagen desde " + direccion);
                          imagen = ImageIO.read(direccion);
                          loaded = true;
                          g2d.drawImage(imagen, topx, topy, (int)(fWidth*ScaleX), (int)(fHeight*ScaleX), null);
                          //getEscala(extX, extY, g2d);
                      } catch (IOException e) 
                      {
                        e.printStackTrace();
                        loaded = false;
                      }
                    }
                  } catch (Exception x) 
                  {
                    x.printStackTrace();
                  }
                } else 
                {
                  if (!isVisible()) setVisible(true);
                  g2d.drawImage(imagen, topx, topy, (int)(fWidth*ScaleX), (int)(fHeight*ScaleX), null);
                  //getEscala(extX, extY, g2d);
                  if (currentRect != null) 
                  {
                    g.setXORMode(Color.white);
                    g.drawRect(rectToDraw.x, rectToDraw.y, rectToDraw.width - 1, rectToDraw.height - 1);
                    updateLabel(rectToDraw);
                  }       
                  if (cantdistpoints>0) 
                  {
                    g.setColor(Color.black);
                    for (int i=0; i<cantdistpoints; i++) 
                    {
                      try 
                      {
                        g.drawLine(polypoints[i].x, polypoints[i].y, polypoints[i+1].x, polypoints[i+1].y);
                      } catch (NullPointerException x) 
                      {
                        
                      }
                    }
                    g.setColor(Color.red);
                    int side = 8;
                    if (osnap) g.fillRect(polypoints[cantdistpoints].x - side / 2, polypoints[cantdistpoints].y - side / 2, side, side);
                  }
                }
              }
    };

    getContentPane().setLayout(null);
    setSize(new Dimension(856, 1043));
    
//    setBounds(new Rectangle(10, 10, 856, 828));
//    center();
    setVisible(true);
    
    comboBarrios.setBounds(new Rectangle(90, 700, 550, 25));
    goBarrio.setText("Ir");
    goBarrio.setBounds(new Rectangle(0, 700, 88, 25));
    goBarrio.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          goBarrio_actionPerformed(e);
        }
      });
    
    dibujo.setBounds(new Rectangle(0, 0, 800, 600));
    dibujo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    dibujo.setBackground(new Color(165, 187, 204));
    statusbtn.setBounds(new Rectangle(755, 600, 45, 45));
    statusbtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    statusbtn.setBackground(new Color(165, 187, 204));
    statusbtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          statusbtn_actionPerformed(e);
        }
      });
    Cancelar.setText("Cancelar");
    Cancelar.setBounds(new Rectangle(680, 625, 75, 20));
    Cancelar.setMnemonic('C');
    Cancelar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    Cancelar.setFont(new Font("Dialog", 1, 11));
    Cancelar.setBackground(new Color(165, 187, 204));
    Cancelar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Cancelar_actionPerformed(e);
        }
      });
    progresobar.setBounds(new Rectangle(0, 625, 680, 20));
    progresobar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    progresobar.setBackground(new Color(165, 187, 204));
    statusbar.setBounds(new Rectangle(0, 670, 800, 25));
    statusbar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    statusbar.setBackground(new Color(165, 187, 204));
    statusbar.setOpaque(true);
    coordbar.setBounds(new Rectangle(0, 645, 800, 25));
    coordbar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    coordbar.setBackground(new Color(165, 187, 204));
    coordbar.setOpaque(true);
    infobar.setBounds(new Rectangle(0, 600, 755, 25));
    infobar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    infobar.setBackground(new Color(165, 187, 204));
    infobar.setOpaque(true);
    jToolBar1.setBounds(new Rectangle(800, 0, 45, 695));
    jToolBar1.setFloatable(false);
    jToolBar1.setOrientation(1);
    jToolBar1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jToolBar1.setPreferredSize(new Dimension(45, 695));
    jToolBar1.setMaximumSize(new Dimension(45, 695));
    jToolBar1.setMinimumSize(new Dimension(45, 695));
    jToolBar1.setBackground(new Color(165, 187, 204));
    zoominbtn.setBounds(new Rectangle(2, 2, 40, 40));
    zoominbtn.setPreferredSize(new Dimension(40, 40));
    zoominbtn.setMaximumSize(new Dimension(40, 40));
    zoominbtn.setMinimumSize(new Dimension(40, 40));
    zoominbtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    zoominbtn.setBackground(new Color(165, 187, 204));
    zoominbtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          zoominbtn_actionPerformed(e);
        }
      });
    zoomoutbtn.setSize(new Dimension(40, 40));
    zoomoutbtn.setPreferredSize(new Dimension(40, 40));
    zoomoutbtn.setMinimumSize(new Dimension(40, 40));
    zoomoutbtn.setMaximumSize(new Dimension(40, 40));
    zoomoutbtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    zoomoutbtn.setBackground(new Color(165, 187, 204));
    zoomoutbtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          zoomoutbtn_actionPerformed(e);
        }
      });
    zommextbtn.setSize(new Dimension(40, 40));
    zommextbtn.setPreferredSize(new Dimension(40, 40));
    zommextbtn.setMinimumSize(new Dimension(40, 40));
    zommextbtn.setMaximumSize(new Dimension(40, 40));
    zommextbtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    zommextbtn.setBackground(new Color(165, 187, 204));
    zommextbtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          zommextbtn_actionPerformed(e);
        }
      });
    redrawbtn.setSize(new Dimension(40, 40));
    redrawbtn.setPreferredSize(new Dimension(40, 40));
    redrawbtn.setMinimumSize(new Dimension(40, 40));
    redrawbtn.setMaximumSize(new Dimension(40, 40));
    redrawbtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    redrawbtn.setBackground(new Color(165, 187, 204));
    redrawbtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          redrawbtn_actionPerformed(e);
        }
      });
    recenterbtn.setSize(new Dimension(40, 40));
    recenterbtn.setPreferredSize(new Dimension(40, 40));
    recenterbtn.setMinimumSize(new Dimension(40, 40));
    recenterbtn.setMaximumSize(new Dimension(40, 40));
    recenterbtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    recenterbtn.setBackground(new Color(165, 187, 204));
    recenterbtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          recenterbtn_actionPerformed(e);
        }
      });
    distancebtn.setSize(new Dimension(40, 40));
    distancebtn.setPreferredSize(new Dimension(40, 40));
    distancebtn.setMinimumSize(new Dimension(40, 40));
    distancebtn.setMaximumSize(new Dimension(40, 40));
    distancebtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    distancebtn.setBackground(new Color(165, 187, 204));
    distancebtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          distancebtn_actionPerformed(e);
        }
      });
    saveasbtn.setSize(new Dimension(40, 40));
    saveasbtn.setPreferredSize(new Dimension(40, 40));
    saveasbtn.setMinimumSize(new Dimension(40, 40));
    saveasbtn.setMaximumSize(new Dimension(40, 40));
    saveasbtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    saveasbtn.setBackground(new Color(165, 187, 204));
    saveasbtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          saveasbtn_actionPerformed(e);
        }
      });
    jToolBar1.add(zoominbtn, null);
    jToolBar1.add(zoomoutbtn, null);
    jToolBar1.add(zommextbtn, null);
    jToolBar1.add(redrawbtn, null);
    jToolBar1.add(recenterbtn, null);
    jToolBar1.add(distancebtn, null);
    jToolBar1.add(saveasbtn, null);
    Cancelar.setEnabled(false);
    this.getContentPane().add(goBarrio, null);
    this.getContentPane().add(comboBarrios, null);
    this.getContentPane().add(statusbtn, null);
    this.getContentPane().add(Cancelar, null);
    this.getContentPane().add(progresobar, null);
    getContentPane().add(jToolBar1, null);
    getContentPane().add(infobar, null);
    this.getContentPane().add(coordbar, null);
    this.getContentPane().add(statusbar, null);
    getContentPane().add(dibujo, null);
   // initMap();
  }

   private void updateDrawableRect(int compWidth, int compHeight) 
   {
      int x = currentRect.x;
      int y = currentRect.y;
      int width = currentRect.width;
      int height = currentRect.height;

      //Make the width and height positive, if necessary.
      if (width < 0) {
        width = 0 - width;
        x = x - width + 1;
        if (x < 0) {
          width += x;
          x = 0;
        }
      }
      if (height < 0) {
        height = 0 - height;
        y = y - height + 1;
        if (y < 0) {
          height += y;
          y = 0;
        }
      }

      //The rectangle shouldn't extend past the drawing area.
      if ((x + width) > compWidth) {
        width = compWidth - x;
      }
      if ((y + height) > compHeight) {
        height = compHeight - y;
      }

      //Update rectToDraw after saving old value.
      if (rectToDraw != null) {
        previousRectDrawn.setBounds(rectToDraw.x, rectToDraw.y,
            rectToDraw.width, rectToDraw.height);
        rectToDraw.setBounds(x, y, width, height);
      } else {
        rectToDraw = new Rectangle(x, y, width, height);
      }
    }  
    
  void updateSize(MouseEvent e) 
  {
    int x = e.getX();
    int y = e.getY();
    currentRect.setSize(x - currentRect.x, y - currentRect.y);
    updateDrawableRect(getWidth(), getHeight());
    repaint();
   }
      
  public void updateLabel(Rectangle rect) 
  {
    int width = rect.width;
    int height = rect.height;

    Point2D.Double puntoOrigen = toSpace(rect.x,rect.y);
    Point2D.Double puntoDestino = toSpace(rect.x+width,rect.y+height);

    statusbar.setText("Coordenadas del Rectangulo: " + d4.format(puntoOrigen.x) + ", " + d4.format(puntoOrigen.y) + ";" + 
        d4.format(puntoDestino.x) + ", " + d4.format(puntoDestino.y));
  }
 
  public void center() 
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = getSize();
    setLocation((screenSize.width - frameSize.width)/2,(screenSize.height - frameSize.height)/2);
  }
  
  private Point toPixel(double xd, double yd) 
  {
    int x = (int)( (xd - xOffset) * Factor);
    int y = (int)( fHeight - ((yd - yOffset) * Factor));
    Point xy = new Point(x,y);
    return xy;
  }
  
  private Point2D.Double toSpace(int x, int y) 
  {
    double xd = x/Factor + xOffset;
    double yd = (fHeight-y)/Factor + yOffset;
    Point2D.Double xy = new Point2D.Double(xd,yd);
    return xy;
  }

  private void zoominbtn_actionPerformed(ActionEvent e)
  {
    setAction(1);
  }

  private void zoomoutbtn_actionPerformed(ActionEvent e)
  {
    setAction(2);
    //zoom(2);
  }

  private void zommextbtn_actionPerformed(ActionEvent e)
  {
    setAction(3);
    xOffset = -1.0;
    yOffset = -1.0;
    //Qwhere = "";
    extents.setStrExtension(origExt.getStrExtension());
    rundoImage();
  }

  private void redrawbtn_actionPerformed(ActionEvent e)
  {
    setAction(4);
    //SETEAR
    rundoImage();
  }

  private void recenterbtn_actionPerformed(ActionEvent e)
  {
    setAction(41);
    //SETEAR
    rundoImage();
  }

  private void distancebtn_actionPerformed(ActionEvent e)
  {
    distanciatotal = 0;
    cantdistpoints = 0;
    setAction(7);
    repaint();
  }

  private void saveasbtn_actionPerformed(ActionEvent e)
  {
    setAction(8);
  }

  private void printbtn_actionPerformed(ActionEvent e)
  {
    setAction(9);
  }
  

  private void query_actionPerformed(ActionEvent e)
  {
    setAction(10);
  }

  private void capture_actionPerformed(ActionEvent e)
  {
    setAction(11);
  }

  public void setAction(int accion) 
  {
    dibujo.removeMouseListener(eraseListener);
    dibujo.removeMouseMotionListener(eraseMotionListener);
    dibujo.removeMouseWheelListener(eraseWheelListener);

    switch (accion)
    {
      case 1: 
          eraseListener = ZoomInListener;
          eraseMotionListener = ZoomInMotionListener;
          break;
      case 2:
          break;
      case 3:
          break;
      case 4:
          break;
      case 41:
          eraseListener = RecenterListener;
          eraseMotionListener = RecenterMotionListener;
          break;
      case 5:
          break;
      case 6:
          break;
      case 7:
          eraseListener = DistanceListener;
          eraseMotionListener = DistanceMotionListener;
          break;
      case 8:
          break;
      case 9:
          break;
      case 11:
          eraseListener = CaptureListener;
          eraseMotionListener = CaptureMotionListener;
          break;
    }
    
    dibujo.addMouseListener(eraseListener);
    dibujo.addMouseMotionListener(eraseMotionListener);
  }

  public void distance(int x, int y, boolean nuevo)
  {
    double distancia = 0;
    double area = 0;
    if (cantdistpoints<99) 
    {
      if (osnap) 
      {
        distpoints[cantdistpoints] = toSpace(polypoints[cantdistpoints].x, polypoints[cantdistpoints].y);
      } else 
      {
        polypoints[cantdistpoints] = new Point(x,y);
        distpoints[cantdistpoints] = toSpace(x,y);
      }
      if (nuevo) 
      {
        cantdistpoints++;
      }
      if (cantdistpoints == 1)
      {
        if (!nuevo) distancia = distance(distpoints[cantdistpoints-1], distpoints[cantdistpoints]);
      }
      if (cantdistpoints>1) 
      {
        if (nuevo) distancia = distance(distpoints[cantdistpoints-2], distpoints[cantdistpoints-1]);
          else distancia = distance(distpoints[cantdistpoints-1], distpoints[cantdistpoints]);
        if (nuevo) distanciatotal += distancia;
        if (cantdistpoints>2) 
        {
          area = calcArea(distpoints);
        }
      }      
      infobar.setText("Distancia parcial: " + d2.format(distancia) + "m - Distancia total: " + d2.format(distanciatotal) + "m - Area: " + d2.format(area) + "m2");
    } else 
    {
      Proc.Mensaje("Máxima cantidad de puntos alcanzada, \nno debe superar los 100 puntos", "Error");
    }
  }

  public double distance(Point2D.Double x0, Point2D.Double x1) 
  {
    double catetox = x1.x - x0.x;
    double catetoy = x1.y - x0.y;
    double cats = catetox*catetox + catetoy*catetoy;
    double dist = Math.sqrt(cats);
    return dist;
  }

  public double calcArea(Point2D.Double[] poly) 
  {
//    Area = 1/2 de Sumatoria(Xi*Yi+1 - Yi*Xi+1)
    double area = 0;
    for (int i = 0; i<cantdistpoints-1; i++) 
    {
      area += (poly[i+1].x * poly[i].y) - (poly[i].x * poly[i+1].y);
    }
    area += (poly[0].x * poly[cantdistpoints-1].y) - (poly[cantdistpoints-1].x * poly[0].y);
    return Math.abs(area/2);
  }
  
  public void setCurrent(int current) 
  {
    progresobar.setValue(current);
    progresobar.setString(porcentaje(current, progresobar.getMaximum()));
  }
  
  public void setMax(int max) 
  {
    if (max <= 0 ) max = 1;
    progresobar.setMaximum(max);
    if (max <= 1) indeterminar(true); else indeterminar(false);
  }
  
  private String porcentaje(int current, int max)
  {
    double actual = (double)current;
    double maximo = (double)max;
    double porcentaje = actual*100/maximo;
    DecimalFormat df = new DecimalFormat("0.00");
    return String.valueOf(df.format(porcentaje) + "%");
  }
  
  private void indeterminar(boolean indeterminate) 
  {
    if (indeterminate) 
    {
      progresobar.setStringPainted(false);
    } else 
    {
      progresobar.setStringPainted(true);
      setCurrent(0);
    }
    progresobar.setIndeterminate(indeterminate);
  }
 
  public void setStatus(String status) 
  {
    infobar.setText(status);
  }

  private void Cancelar_actionPerformed(ActionEvent e)
  {
    setStatus("Operación cancelada");
    statusbtn.setIcon(new ImageIcon(JMSApplet.class.getResource("iconos/cancelar.png")));
    tarea.stop();
    indeterminar(false);
    progresobar.setStringPainted(false);
    Cancelar.setEnabled(false);
  }

  public void actualizar() 
  {
    rundoImage();
  }
  
  private void goBarrio_actionPerformed(ActionEvent e)
  {
  }

  private void statusbtn_actionPerformed(ActionEvent e)
  {
  
  }
  
  private void initMap()
  {
    if (!conectado) 
    {
      infobar.setText("Intentando conectar a " + host);
      String welcome, response;
      Client client;
      try 
      {
        client = new Client (host, 5433); //la clase Client Implementa el socket cliente al que se le pasa el argumento 0 que contendrá la dirección o nombre del servidor
        conectado = true;
        infobar.setText("Conectado a " + host);
        //creamos los canales de entrada/salida para comunicarnos con el servidor
        reader = new BufferedReader (new InputStreamReader (client.in));
        writer = new PrintWriter (new OutputStreamWriter (client.out), true);
        //leemos la bienvenida que nos da el servidor
        welcome = reader.readLine();
        Thread.sleep(1000);
        enviarComando("AYUDA");

        String coordenadas = enviarComando("GETCOORDS");
        origExt.setStrExtension(coordenadas);
        extents.setStrExtension(origExt.getStrExtension());

        rundoImage();

      } catch (IOException io) {
        conectado = false;
        infobar.setText("Error en la conexión a " + host);
        System.out.println ("IOException en client.in.readln()");
        System.out.println(io);
      } catch (InterruptedException ie) 
      {
        ie.printStackTrace();
      }
    }
  }

  private void rundoImage() 
  {
    if (!enviarComando("SETCOORDS:"+extents.getStrExtension()).equalsIgnoreCase("ERROR")) 
    {
      String newArchivo = enviarComando("GETIMAGE");
      if (!newArchivo.equalsIgnoreCase("ERROR")) 
      {
        archivo = newArchivo;
        loaded = false;
        
        xOffset = extents.getMinX();
        yOffset = extents.getMinY();

        extX = extents.getMaxX() - extents.getMinX();
        extY = extents.getMaxY() - extents.getMinY();
    
        //Calculo el factor para escalar los polígonos
        if (extX > extY) 
        {
          Factor = (fWidth-20) / extX;
        } else 
        {
          Factor = (fHeight-20) / extY;
        }
        repaint();
      }
    }
  }
  
  private String enviarComando(String comando) 
  {
    try {
      System.out.println("Enviando comando " + comando);
      infobar.setText("Enviando comando " + comando);
      writer.println(comando);
      return reader.readLine();
    } catch (IOException io) {
      conectado = false;
      infobar.setText("Error en la conexión a " + host);
      System.out.println ("IOException en client.in.readln()");
      System.out.println(io);
      return "ERROR";
    }
  }
}