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
 * LayerDrawPolys.java
 *
 * */
package jms;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.event.MouseInputListener;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Enumeration;
import javax.media.j3d.*;

public class LayerDrawPolys extends JFrame //implements MouseListener, MouseMotionListener, MouseWheelListener
{
  Rectangle currentRect = null;
  Rectangle rectToDraw = null;  
  Rectangle previousRectDrawn = new Rectangle();
  CreaHTML reportero = new CreaHTML();
  
  double extX = 0;
  double extY = 0;
  double Factor = 0;
  double minFactor = 0;
  double xOffset = 0;
  double yOffset = 0;
  int catastro = 0;
  DecimalFormat d4 = new DecimalFormat("0.0000");
  DecimalFormat d2 = new DecimalFormat("0.00");

  int fWidth = 800,fHeight = 600;
  int topx = 0, topy = 0, prevx = 0, prevy = 0;

  int fNumPoints;
  double fFactor;
  Color color;
  public final static Font font=new Font("Courier",Font.BOLD,14);
  public final static Color DEFAULT_COLOR=Color.black;
  protected GeneralPath[] paths; 
  protected Polygon[] polys; 
  protected Color[] fills; 
  int numPaths;
  protected Point2D mSelectedPoint; 
  int[] selectedPath;
  int numSelPaths=0;
  LayerObject[] layer = new LayerObject[1];
  BufferedImage image = null;
  boolean panning = false;
  boolean plotear = false;
  boolean finDrawing = false;
  private String ruta = "/tmp/jms/";
//  private String ruta = "C:\\temp\\";
  private String archivo = "";
  private boolean loaded = false;
  private String Qwhere = "";
  private BufferedImage imagen = new BufferedImage(fWidth, fHeight, BufferedImage.TYPE_BYTE_INDEXED);
  private int numLayers=0;


  Extent extents = new Extent();
  Extent myExtent = new Extent();
  Extent origExt = new Extent();
  private double ScaleX = 1, scaleinc = 0.01;
  private int centerx = 0, centery = 0;
  Thread tarea;

  String status = "";
  int max = 0;
  int current = 0;
//  final Barra progress = new Barra(max, current, this);
  
  private int curraction = 0;


  private JPanel dibujo = new JPanel();
  private JLabel statusbar = new JLabel();
  private JLabel coordbar = new JLabel();
  private JLabel infobar = new JLabel();
  private JToolBar jToolBar1 = new JToolBar();
  private JButton zoominbtn = new JButton(new ImageIcon(LayerDraw.class.getResource("iconos/zoomin.png")));
  private JButton zoomoutbtn = new JButton(new ImageIcon(LayerDraw.class.getResource("iconos/zoomout.png")));
  private JButton zommextbtn = new JButton(new ImageIcon(LayerDraw.class.getResource("iconos/extents.png")));
  private JButton recenterbtn = new JButton(new ImageIcon(LayerDraw.class.getResource("iconos/consultar.png")));
  private JButton redrawbtn = new JButton(new ImageIcon(LayerDraw.class.getResource("iconos/redraw.png")));
  private JButton undobtn = new JButton(new ImageIcon(LayerDraw.class.getResource("iconos/undo.png")));
  private JButton redobtn = new JButton(new ImageIcon(LayerDraw.class.getResource("iconos/redo.png")));
  private JButton distancebtn = new JButton(new ImageIcon(LayerDraw.class.getResource("iconos/regla.png")));
  private JButton saveasbtn = new JButton(new ImageIcon(LayerDraw.class.getResource("iconos/grabarcomo.png")));
  private JButton printbtn = new JButton(new ImageIcon(LayerDraw.class.getResource("iconos/imprimir.png")));
  private JButton query = new JButton(new ImageIcon(LayerDraw.class.getResource("iconos/consultar.png")));
  private JButton statusbtn = new JButton();//new ImageIcon(LayerDraw.class.getResource("iconos/cancelar.png")));

  Point2D.Double[] distpoints = new Point2D.Double[100];
  Point[] polypoints = new Point[100];
  int cantdistpoints = 0;
  double distanciatotal = 0;
  boolean drawing = false;
  boolean osnap = false;
  private JProgressBar progresobar = new JProgressBar(max, current);
  private JButton Cancelar = new JButton();

  private boolean firstLoad = true;  

  protected Extent[] history = new Extent[50];
  private int acciones = 0;
  private int accionespos = 0;

//  PanelFeatures panel = new PanelFeatures(this);
  
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
          Qwhere = " where within(the_geom,geometryfromtext('BOX3D(" + 
          puntoOrigen.x + " " + puntoOrigen.y + " 0," + 
          puntoDestino.x + " " + puntoDestino.y + " 0)',-1)) ";
          Qwhere = Qwhere + " or intersects(the_geom,geometryfromtext('BOX3D(" + 
          puntoOrigen.x + " " + puntoOrigen.y + " 0," + 
          puntoDestino.x + " " + puntoDestino.y + " 0)',-1)) ";
          extents.setStrExtension(puntoOrigen.x + " " + puntoOrigen.y + " " + puntoDestino.x + " " + puntoDestino.y);
//          panel.setActualPoint(new Point(currentRect.x, currentRect.y), new Point(currentRect.x+currentRect.width,currentRect.y+currentRect.height), fWidth, fHeight);
          currentRect = null;
          finDrawing = false;
          loaded = false;
          image = null;
          history[accionespos] = new Extent(extents.getStrExtension());
          accionespos++;
          acciones = accionespos+1;
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
//      panel.setActualPoint(new Point(currentRect.x, currentRect.y), new Point(currentRect.x+currentRect.width,currentRect.y+currentRect.height), fWidth, fHeight);
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

  protected MouseListener QueryListener = new MouseListener() 
  {
    public void mouseReleased(MouseEvent event)
    {
      if (currentRect != null)
      {
        if (Math.abs(currentRect.width)>3 && Math.abs(currentRect.height)>3)
        {
          Point2D.Double puntoOrigen = toSpace(currentRect.x,currentRect.y);
          Point2D.Double puntoDestino = toSpace(currentRect.x+currentRect.width,currentRect.y+currentRect.height);
          Qwhere = " where within(the_geom,geometryfromtext('BOX3D(" + 
          puntoOrigen.x + " " + puntoOrigen.y + " 0," + 
          puntoDestino.x + " " + puntoDestino.y + " 0)',-1)) ";
          Qwhere = Qwhere + " or intersects(the_geom,geometryfromtext('BOX3D(" + 
          puntoOrigen.x + " " + puntoOrigen.y + " 0," + 
          puntoDestino.x + " " + puntoDestino.y + " 0)',-1)) ";
          currentRect = null;
          repaint();
          rundoQuery();
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
//      panel.setActualPoint(new Point(currentRect.x, currentRect.y), new Point(currentRect.x+currentRect.width,currentRect.y+currentRect.height), fWidth, fHeight);
    }
  };
   
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


  public LayerDrawPolys(LayerObject l)
  {
    numLayers = 1;
    layer[0] = l;
    plotear = false;
    try
    {
      jbInit();
      rundoImage();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  public LayerDrawPolys(MapObject map, BufferedImage bfdImg)
  {
    xOffset = -1;
    yOffset = -1;
    numLayers = map.getLayerCount();
    layer = new LayerObject[numLayers];
    for (int i=0; i<numLayers; i++) 
    {
      layer[i] = map.getLayerObject(i);
    }
    origExt.setStrExtension(map.getExtent().getStrExtension());
    extents.setStrExtension(map.getExtent().getStrExtension());
    extX = origExt.getMaxX() - origExt.getMinX();
    extY = origExt.getMaxY() - origExt.getMinY();

    //Calculo el factor para escalar los polígonos
    if (extX > extY) 
    {
      Factor = (fWidth-20) / extX;
    } else 
    {
      Factor = (fHeight-20) / extY;
    }
    if (minFactor == 0) 
    {
      minFactor = Factor;
    }
    firstLoad = false;
    try
    {
      jbInit();
      rundoImage();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  public LayerDrawPolys(MapObject map, BufferedImage bfdImg, int numcatastro)
  {
    xOffset = -1;
    yOffset = -1;
    catastro = numcatastro;
    numLayers = map.getLayerCount();
    layer = new LayerObject[numLayers];
    for (int i=0; i<numLayers; i++) 
    {
      layer[i] = map.getLayerObject(i);
    }
    origExt.setStrExtension(map.getExtent().getStrExtension());

    extX = origExt.getMaxX() - origExt.getMinX();
    extY = origExt.getMaxY() - origExt.getMinY();

    //Calculo el factor para escalar los polígonos
    if (extX > extY) 
    {
      Factor = (fWidth-20) / extX;
    } else 
    {
      Factor = (fHeight-20) / extY;
    }
    if (minFactor == 0) 
    {
      minFactor = Factor;
    }
    firstLoad = false;
    try
    {
      jbInit();
      drawCatastro(catastro);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  public LayerDrawPolys()
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
    dibujo = new JPanel()/* { 
              public void paint(Graphics g) 
              {
                Graphics2D g2d = (Graphics2D)g;
                if (!loaded) 
                {
                  try
                  {
                    GeneralPath path = new GeneralPath();
                    if ((finDrawing) && (!archivo.equals(""))) 
                    {
                      try {
                          // Read from a file
                          File file = new File(archivo);
                          imagen = ImageIO.read(file);
                          loaded = true;
//                          System.out.println("Imagen cargada...");
            //              if (!isVisible()) setVisible(true);
                          g2d.drawImage(imagen, topx, topy, (int)(fWidth*ScaleX), (int)(fHeight*ScaleX), null);
                          getEscala(extX, extY, g2d);
                      } catch (IOException e) 
                      {
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
                  getEscala(extX, extY, g2d);
                  if (currentRect != null) 
                  {
                    //Draw a rectangle on top of the image.
                    g.setXORMode(Color.white); //Color of line varies
                    //depending on image colors
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
                  //g2d.drawImage(imagen,centerx,centery,(int)(fWidth*ScaleX),(int)(fHeight*ScaleX),null);
                }
                //repaint();
              }
    }*/;

//    dibujo.addMouseListener(eraseListener);
//    dibujo.addMouseMotionListener(eraseMotionListener);
/*    dibujo.addMouseWheelListener(this);*/
    
    undobtn.setEnabled(false);
    redobtn.setEnabled(false);
    printbtn.setEnabled(false);
    getContentPane().setLayout(null);
    setSize(new Dimension(856, 1043));
    setBounds(new Rectangle(10, 10, 856, 828));
    center();
    setTitle("Ventana de Dibujo");
    setResizable(false);
    setVisible(true);
    
//    panel = new PanelFeatures(this);
//    panel.setRefMap(ruta + "refmap.png");
//    panel.setLayers(numLayers, layer);
//    panel.show();
    
    dibujo.setBounds(new Rectangle(0, 0, 800, 600));
    dibujo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    query.setBounds(new Rectangle(2, 362, 40, 40));
    query.setSize(new Dimension(40, 40));
    query.setPreferredSize(new Dimension(40, 40));
    query.setMaximumSize(new Dimension(40, 40));
    query.setMinimumSize(new Dimension(40, 40));
    query.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          query_actionPerformed(e);
        }
      });
    statusbtn.setBounds(new Rectangle(755, 600, 45, 45));
    statusbtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    Cancelar.setText("Cancelar");
    Cancelar.setBounds(new Rectangle(680, 625, 75, 20));
    Cancelar.setMnemonic('C');
    Cancelar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    Cancelar.setFont(new Font("Dialog", 1, 11));
    Cancelar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Cancelar_actionPerformed(e);
        }
      });
    progresobar.setBounds(new Rectangle(0, 625, 680, 20));
    progresobar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    statusbar.setBounds(new Rectangle(0, 670, 800, 25));
    statusbar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    coordbar.setBounds(new Rectangle(0, 645, 800, 25));
    coordbar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    infobar.setBounds(new Rectangle(0, 600, 755, 25));
    infobar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jToolBar1.setBounds(new Rectangle(800, 0, 45, 695));
    jToolBar1.setFloatable(false);
    jToolBar1.setOrientation(1);
    jToolBar1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jToolBar1.setPreferredSize(new Dimension(45, 695));
    jToolBar1.setMaximumSize(new Dimension(45, 695));
    jToolBar1.setMinimumSize(new Dimension(45, 695));
    zoominbtn.setBounds(new Rectangle(2, 2, 40, 40));
    zoominbtn.setPreferredSize(new Dimension(40, 40));
    zoominbtn.setMaximumSize(new Dimension(40, 40));
    zoominbtn.setMinimumSize(new Dimension(40, 40));
    zoominbtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
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
    recenterbtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          recenterbtn_actionPerformed(e);
        }
      });
    undobtn.setSize(new Dimension(40, 40));
    undobtn.setPreferredSize(new Dimension(40, 40));
    undobtn.setMinimumSize(new Dimension(40, 40));
    undobtn.setMaximumSize(new Dimension(40, 40));
    undobtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    undobtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          undobtn_actionPerformed(e);
        }
      });
    redobtn.setSize(new Dimension(40, 40));
    redobtn.setPreferredSize(new Dimension(40, 40));
    redobtn.setMinimumSize(new Dimension(40, 40));
    redobtn.setMaximumSize(new Dimension(40, 40));
    redobtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    redobtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          redobtn_actionPerformed(e);
        }
      });
    distancebtn.setSize(new Dimension(40, 40));
    distancebtn.setPreferredSize(new Dimension(40, 40));
    distancebtn.setMinimumSize(new Dimension(40, 40));
    distancebtn.setMaximumSize(new Dimension(40, 40));
    distancebtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
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
    saveasbtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          saveasbtn_actionPerformed(e);
        }
      });
    printbtn.setSize(new Dimension(40, 40));
    printbtn.setPreferredSize(new Dimension(40, 40));
    printbtn.setMinimumSize(new Dimension(40, 40));
    printbtn.setMaximumSize(new Dimension(40, 40));
    printbtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    printbtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          printbtn_actionPerformed(e);
        }
      });
    jToolBar1.add(zoominbtn, null);
    jToolBar1.add(zoomoutbtn, null);
    jToolBar1.add(zommextbtn, null);
    jToolBar1.add(redrawbtn, null);
    jToolBar1.add(recenterbtn, null);
    jToolBar1.add(undobtn, null);
    jToolBar1.add(redobtn, null);
    jToolBar1.add(distancebtn, null);
    jToolBar1.add(saveasbtn, null);
    jToolBar1.add(printbtn, null);
    jToolBar1.add(query, null);
    Cancelar.setEnabled(false);
    this.getContentPane().add(statusbtn, null);
    this.getContentPane().add(Cancelar, null);
    this.getContentPane().add(progresobar, null);
    getContentPane().add(jToolBar1, null);
    getContentPane().add(infobar, null);
    this.getContentPane().add(coordbar, null);
    this.getContentPane().add(statusbar, null);
    getContentPane().add(dibujo, null);
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
    Rectangle totalRepaint = rectToDraw.union(previousRectDrawn);
    repaint();//totalRepaint.x, totalRepaint.y, totalRepaint.width, totalRepaint.height);
   }
      
  public void updateLabel(Rectangle rect) 
  {
    int width = rect.width;
    int height = rect.height;

/*    //Make the coordinates look OK if a dimension is 0.
    if (width == 0) {
      width = 1;
    }
    if (height == 0) {
      height = 1;
    }
    double x1 = rect.x/Factor+xOffset;
    double y1 = rect.y/Factor+yOffset;
    double x2 = (rect.x+width)/Factor+xOffset;
    double y2 = (rect.y+height)/Factor+yOffset;
    Point2D.Double puntoOrigen = toSpace(rect.x,rect.y);
    Point2D.Double puntoDestino = toSpace(rect.x+width-1,rect.y+height-1);
*/
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

  synchronized public void rundoImage() 
  {
    tarea = new Thread(new Runnable() {
      public void run() 
      {
        Graphics2D g2d = (Graphics2D)dibujo.getRootPane().getGlassPane().getGraphics();
        //Color de Fondo
        g2d.setColor(Color.white);
        //Relleno la imagen con el color de fondo
        g2d.fillRect(0, 0, fWidth, fHeight);

        for (int i=0; i<numLayers; i++) 
        {
          //progress.
          setMax(0);
//          panel.actualizar();
          if (layer[i].getonoff()) doImage(g2d, layer[i]);
        }
        g2d.dispose();
  
        finDrawing = true;
//                progress.setModal(false);
//                progress.disposeme();
        Cancelar.setEnabled(false);
        indeterminar(false);
        progresobar.setStringPainted(false);
        setStatus("Operación terminada");
        ImageIcon icono = new ImageIcon(LayerDraw.class.getResource("iconos/ok.png"));
        statusbtn.setIcon(icono);
        repaint();
      }
    });
    Cancelar.setEnabled(true);
    tarea.start();
  }                  

  public Graphics2D doImage(Graphics2D g2d, LayerObject myLayer) 
  {
    dibujo.setEnabled(false);
    int tipoLayer = myLayer.getTypeInt();
    switch (tipoLayer)
    {
      case 0: Proc.Mensaje("Error en el tipo de Layer","Dibujar Layer"); return null;
      case 1: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); return null;
      case 4: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); return null;
      case 5: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); return null;
      case 6: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); return null;
      case 7: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); return null;
    }
    //progress.
    setStatus("Inicializando...");
    
    statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/reloj.gif")));
   

/*    FileOutputStream outputStream;
    try 
    {
      // Save this document to example.eps
      outputStream = new FileOutputStream("/tmp/jms/example.eps");
      g2d = new EpsGraphics2D("Example", outputStream, 0, 0, fWidth, fHeight);
    } catch (Exception x) 
    {
      
    }*/
    // Create a new document with bounding box 0 <= x <= 100 and 0 <= y <= 100.
    // Create a graphics contents on the buffered image
  

    try {
      //progress.
      
      statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/connecting.png")));
      setStatus("Conectando con la base de datos...");
      Connection Con = Proc.CCon();
      statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/connected.png")));
      
      String Data = myLayer.getData();
      Data = Data.trim();
      String geometry = Data.substring(0,Data.indexOf(" ")).trim();
      Data = Data.substring(Data.indexOf(" ")).trim();

      //Preparo el campo label si existe
      String LabelItem = myLayer.getLabelItem();
      if (LabelItem.trim().length()>0)
      {
        LabelItem += " as label, ";
      }

      //La condicion por ahora es vacía
//      String Qwhere = "";
      
      //Preparo la consulta para solicitar los objetos a la base de datos      
      String Query = "select " + LabelItem + " astext(force_2d(intersection(" + geometry + ", " + extents.getBox3D() + "))) " +  
              " as intersection, npoints(intersection(" + geometry + ", " + extents.getBox3D() + ")) as npointsintersection, astext(force_2d(centroid(intersection(" + geometry + ", " + extents.getBox3D() + ")))) " + 
              " as centroidintersection, AsText(centroid(" + geometry + ")) as centroid, AsText(" + geometry + ") as the_geom, npoints(" + geometry + ") " + myLayer.getExpressions() + Data + Qwhere;

      //progress.
      setStatus("Contando objetos...");
      statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/lupa.gif")));

      //Cuento los objetos
      ResultSet count = Proc.exConsulta(Con, "select count(*) from (" + Query + ") as foo");
      count.next();

      //max sirve para la barra de progreso
      max = count.getInt(1);
      //progress.
      setMax(max);

//      paths = new GeneralPath[count.getInt(1)];
      polys = new Polygon[1];
//      selectedPath = new int[count.getInt(1)];

      fills = new Color[1];
      //progress.
      setStatus("Cargando objetos...");
      statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/working.gif")));
      
      //Solicito los objetos a la base de datos
      ResultSet Polygons = Proc.exConsulta(Con,Query);
      
      //Calculo la extensión máxima N-S-E-O
      if ((xOffset == 0.0) && (yOffset == 0.0)) 
      {
        ResultSet Extent = Proc.exConsulta(Con,"select xmin(extent(" + geometry + ")), " +
                            "xmax(extent(" + geometry + ")), " +
                            "ymin(extent(" + geometry + ")), " +
                            "ymax(extent(" + geometry + ")) " + Data + Qwhere );
  
        Extent.next();
        myExtent.setExtension(Extent.getDouble("xmin"), Extent.getDouble("xmax"), Extent.getDouble("ymin"), Extent.getDouble("ymax"));
      } else 
      {
        myExtent.setExtension(extents.getMinX(), extents.getMaxX(), extents.getMinY(), extents.getMaxY());
      }
      if (firstLoad) 
      {
        origExt.setStrExtension(myExtent.getStrExtension());
        history[accionespos] = new Extent(myExtent.getStrExtension());
        accionespos++;
        acciones = accionespos+1;
        firstLoad = false;
      }

      extX = myExtent.getMaxX() - myExtent.getMinX();
      extY = myExtent.getMaxY() - myExtent.getMinY();

      //Calculo el factor para escalar los polígonos
      if (extX > extY) 
      {
        Factor = (fWidth-20) / extX;
      } else 
      {
        Factor = (fHeight-20) / extY;
      }
      if (minFactor == 0) 
      {
        minFactor = Factor;
      }
      xOffset = myExtent.getMinX();
      yOffset = myExtent.getMinY();
      System.out.println("ExtX: " + extX + " ExtY: " + extY);
      System.out.println("Factor: " + Factor + " minFactor: " + minFactor);
      System.out.println("xOffset: " + xOffset + " yOffset: " + yOffset);

      //Dibujo los objetos
      // Tipo de layer: 1: point - 2: line - 3: polygon - 4: circle - 5: annotation - 6: raster - 7: query 
      boolean withLabels;
      if (LabelItem.equals("")) 
      {
        withLabels = false; 
      } else {
        withLabels = true;
      }
      switch (tipoLayer)
      {
        case 0: Proc.Mensaje("Error en el tipo de Layer","Dibujar Layer"); break;
        case 1: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); break;
        case 2: drawPolylines(g2d, Polygons, withLabels, myLayer); break;
        case 3: drawPolygons(g2d, Polygons, withLabels, myLayer); break;
        case 4: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); break;
        case 5: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); break;
        case 6: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); break;
        case 7: Proc.Mensaje("Tipo de Layer aún no soportado","Dibujar Layer"); break;
      }
    } catch (SQLException x) 
    {
      Proc.Mensaje(x.getErrorCode() + ": " + x.getMessage(), "Error en la consulta SQL");
    }
    //progress.
    setMax(0);
    //progress.
    setStatus("Imagen generada... grabando");
    statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/grabar.png")));
//    System.out.println("EPS POSTSCRIPT!!!:");
//    System.out.println(g2d.toString());
    // Flush and close the document (don't forget to do this!)
    try 
    {
//      g2d.flush();
//      g2d.close();
    } catch (Exception x) 
    {
    }
    dibujo.setEnabled(true);
    return g2d;
  }

  private void drawPolygons(Graphics2D g2d, ResultSet Polygons, boolean withLabels, LayerObject myLayer)
  {
    numPaths = 0;
    try 
    {
      while (Polygons.next()) 
      {

        //Cantidad de puntos del polígono
        int fNumPoints = Polygons.getInt("npoints");
        //Puntos del polígono
        String Poly = Polygons.getString("the_geom");
        //Si es polígono cerrado, por ejemplo Manzana o Parcela
        Poly = Poly.substring(Poly.indexOf("(((")+3,Poly.length()-3);

        //Si es una polilínea, por ejemplo Calle o Río        
        //Poly = Poly.substring(Poly.indexOf("((")+2,Poly.length()-3);

        //Obtengo todos los puntos del registro
        //Podria usar la clase StringTokenizer???
        double [] xd = new double[fNumPoints];
        double [] yd = new double[fNumPoints];
        for (int i=0; i < fNumPoints-1; i++) 
        {
          xd[i] = Double.parseDouble(Poly.substring(0,Poly.indexOf(" ")).trim());
          yd[i] = Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.indexOf(",")).trim());
          Poly = Poly.substring(Poly.indexOf(",")+1,Poly.length());
        }
        xd[fNumPoints-1] = Double.parseDouble(Poly.substring(0,Poly.indexOf(" ")).trim());
        yd[fNumPoints-1] = Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.length()).trim());

        //Obtengo el polígono con los puntos
        polys[0] = getPolyFromPoints(xd, yd, fNumPoints);
        
        //Esta sentencia corre la evaluación del atributo 
        //"expression" de una clase del layer
        //Esto hay que revisar, pues es un poco rebuscado todavía
        MapColor fillColor = myLayer.getFillColorByClass(Polygons);
        if (fillColor == null) 
        {
          fills[0] = myLayer.getClassObject("color").getStyleObject().getOutLineColor();
        } else 
        {
          fills[0] = fillColor.getColor();
        }
        if (fills[0] != myLayer.getClassObject("color").getStyleObject().getOutLineColor()) 
        {
          //Pinto el polígono
          g2d.setColor(fills[0]);//REVISAR!!!//REVISAR!!!
//          if (fills[0] != Color.black) g2d.fill(polys[0]); //REVISAR!!!//REVISAR!!!
        }

        //Condición para dibujar los contornos >20 píxels de ancho y >20 píxels de alto
        if (((polys[0].getBounds2D().getWidth() > 20) && (polys[0].getBounds2D().getHeight() > 20)))
        {
          //Dibujo el contorno del polígono
          g2d.setColor(myLayer.getClassObject("color").getStyleObject().getOutLineColor());
          g2d.draw(polys[0]);
        }

        numPaths++;
        //progress.
        setCurrent(numPaths);
        //progress.
        setStatus("Dibujando el layer " + myLayer.getName() + ", objetos: " + numPaths + " de " + max);
        statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/dibuja.png")));
        /************************** ETIQUETAS ********************************/
        polys[0] = getPolyFromPoints(xd, yd, fNumPoints);
        if ((withLabels) && (((polys[0].getBounds2D().getWidth() > 20) || (polys[0].getBounds2D().getHeight() > 20)) || myLayer.getClassObject("color").getLabelObject().getForce()))
        {
          try
          {
            String pLabel = Polygons.getString("label");
            String Centroid = Polygons.getString("centroid");
            Centroid = Centroid.substring(Centroid.indexOf("(")+1,Centroid.length()-1);
            double xc = Double.parseDouble(Centroid.substring(0,Centroid.indexOf(" ")).trim());
            double yc = Double.parseDouble(Centroid.substring(Centroid.indexOf(" ")));
            Point centro = toPixel(xc,yc);
            FontMetrics fm = g2d.getFontMetrics(font);
            int lHeight = fm.getHeight();
            int lWidth = fm.stringWidth(pLabel);
//            int xl = (int)centro.getX();
//            int yl = (int)centro.getY();
            int xl = (int)centro.getX()-(int)lWidth/2;
            int yl = (int)centro.getY()+lHeight/2;
            g2d.setColor(myLayer.getClassObject("color").getLabelObject().getColor());
            g2d.drawString(pLabel,xl,yl);
          } catch (NullPointerException e) 
          {
            e.printStackTrace();
          }
        }
      }
    } catch (SQLException x) 
    {
      System.out.println("Error dibujando poligonos: [drawPolygons(Graphics2D, ResultSet)]");
    }
  }

  private void drawPolylines(Graphics2D g2d, ResultSet Polylines, boolean withLabels, LayerObject myLayer)
  {
    numPaths = 0;
    try 
    {
      while (Polylines.next()) 
      {

        //Cantidad de puntos de la polilínea
        int fNumPoints = Polylines.getInt("npointsintersection");
        //Puntos de la polilínea

        String Poly = Polylines.getString("intersection");
        while (Poly.indexOf("(")>0) 
        {
          Poly = Poly.substring(Poly.indexOf("(")+1,Poly.length()-1);
        }
        double [] xd = new double[fNumPoints];
        double [] yd = new double[fNumPoints];
        for (int i=0; i < fNumPoints-1; i++) 
        {
          xd[i] = Double.parseDouble(Poly.substring(0,Poly.indexOf(" ")).trim());
          yd[i] = Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.indexOf(",")).trim());
          Poly = Poly.substring(Poly.indexOf(",")+1,Poly.length());
        }
        xd[fNumPoints-1] = Double.parseDouble(Poly.substring(0,Poly.indexOf(" ")).trim());
        yd[fNumPoints-1] = Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.length()).trim());

        //Obtengo la polilínea con los puntos
        int[] x = new int[fNumPoints];
        int[] y = new int[fNumPoints];
        for (int i=0; i < fNumPoints; i++) 
        {
          x[i] = (int)( (xd[i] - xOffset) * Factor);
          y[i] = (int)( fHeight - ((yd[i] - yOffset) * Factor));
        }
        
        //Esta sentencia corre la evaluación del atributo 
        //"expression" de una clase del layer
        //Esto hay que revisar, pues es un poco rebuscado todavía
        MapColor fillColor = myLayer.getFillColorByClass(Polylines);
        if (fillColor == null) 
        {
          fills[0] = myLayer.getClassObject("color").getStyleObject().getOutLineColor();
        } else 
        {
          fills[0] = fillColor.getColor();
        }
//        if (fills[0] != layer.getClassObject("color").getStyleObject().getOutLineColor()) 
        {
          //Pinto la polilínea
//          g2d.setColor(fills[0]);
//          g2d.fill(polys[0]);
        }

        //Condición para dibujar los contornos >20 píxels de ancho y >20 píxels de alto
//        if (((polys[0].getBounds2D().getWidth() > 20) && (polys[0].getBounds2D().getHeight() > 20)))
//        {
          //Dibujo la polilínea
          g2d.setColor(fills[0]);
          g2d.drawPolyline(x, y, fNumPoints);
//        }

        numPaths++;
        //progress.
        setCurrent(numPaths);
        //progress.
        setStatus("Dibujando el layer " + myLayer.getName() + ", objetos: " + numPaths + " de " + max);
        statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/dibuja.png")));

        /************************** ETIQUETAS ********************************/
        polys[0] = getPolyFromPoints(xd, yd, fNumPoints);
        if ((withLabels) && (((polys[0].getBounds2D().getWidth() > 20) || (polys[0].getBounds2D().getHeight() > 20)) || myLayer.getClassObject("color").getLabelObject().getForce()))
        {
          try
          {
            String pLabel = Polylines.getString("label");
            String Centroid = Polylines.getString("centroidintersection");
            Centroid = Centroid.substring(Centroid.indexOf("(")+1,Centroid.length()-1);
            double xc = Double.parseDouble(Centroid.substring(0,Centroid.indexOf(" ")).trim());
            double yc = Double.parseDouble(Centroid.substring(Centroid.indexOf(" ")));
            Point centro = toPixel(xc,yc);
            //System.out.println(xc + "-" + yc);
            double Angulo = 0;            
            for (int i=0; i < fNumPoints-1; i++) 
            {
              if (((xd[i] <= xc && xd[i+1] >= xc) || (xd[i] >= xc && xd[i+1] <= xc)) && ((yd[i] <= yc && yd[i+1] >= yc) || (yd[i] >= yc && yd[i+1] <= yc)))
              {
                //Cálculo del ángulo del label
                double Base = (xd[i] - xd[i+1]);
                double Altura = (yd[i] - yd[i+1]);
                Angulo = Math.atan(-Altura/Base);
//                System.out.println("Centro: " + xc + ", " + yc);
//                System.out.println("Base: " + xd[i] + " - " + xd[i+1]);
//                System.out.println("Altura: " + yd[i] + " - " + yd[i+1]);
//                System.out.println("Angulo: " + Angulo);
//                System.out.println("Calle: " + pLabel);

//                if (Angulo < 90) System.out.println(Math.toDegrees(Angulo));
//                  else if ((Angulo < 270) && (Angulo > 180)) System.out.println(Math.toDegrees(Angulo));
              }
            }


            FontMetrics fm = g2d.getFontMetrics(font);
            int lHeight = fm.getHeight();
            int lWidth = fm.stringWidth(pLabel);

//            int xl = (int)centro.getX()-(int)lWidth/2;
//            int yl = (int)centro.getY()+lHeight/2;

            int xl = (int)centro.getX();
            int yl = (int)centro.getY();

//            AttributedString str = new AttributedString(pLabel);
//            str.addAttribute(TextAttribute.BACKGROUND, Color.white);
//            str.addAttribute(TextAttribute.FOREGROUND, layer.getClassObject("color").getLabelObject().getColor());
//            str.addAttribute(TextAttribute.SIZE, "14");
//            g2d.setColor(Color.black);
//            g2d.drawOval((int)centro.getX()-11,(int)centro.getY()-11,22,22);
//            g2d.setColor(Color.white);
//            g2d.fillOval((int)centro.getX()-11,(int)centro.getY()-11,22,22);
            AffineTransform at = AffineTransform.getRotateInstance(Angulo, xl, yl);
            g2d.setTransform(at);
            g2d.setColor(myLayer.getClassObject("color").getLabelObject().getColor());
            g2d.drawString(pLabel,xl,yl);
            at = at.getRotateInstance(0, xl, yl);
            g2d.setTransform(at);
          } catch (NullPointerException e) 
          {
            e.printStackTrace();
          }
        }
      }
    } catch (Exception x) 
    {
      System.out.println("Error dibujando poligonos: [drawPolylines(Graphics2D, ResultSet, WithLabels)]");
//      System.out.println(Poly);
      x.printStackTrace();
    }
  }

  public void getEscala(double x, double y, Graphics2D g2d)
  {
    int divisiones = 4;
    double mtsxpixel = x/(fWidth*ScaleX);
    double size = ((fWidth/divisiones)*mtsxpixel);
    size = distance(toSpace(0,0), toSpace(200,0));
    String unidad = "";
/*    if (x>1000) //Kilómetros
    {
      size = (size/1000);
      unidad = "Km";
    } else //Metrosr
    {
      unidad = "m";      
    }
*/
    String Size0 = "0" + unidad;
    String Size1 = "";
    String Size2 = "";
    String Size3 = "";
    String Size4 = "";
    
    if (1*size < 1000) Size1 = d2.format(1*size) + "m"; else Size1 = d2.format(1*size/1000) + "Km";
    if (2*size < 1000) Size2 = d2.format(2*size) + "m"; else Size2 = d2.format(2*size/1000) + "Km";
    if (3*size < 1000) Size3 = d2.format(3*size) + "m"; else Size3 = d2.format(3*size/1000) + "Km";
    if (4*size < 1000) Size4 = d2.format(4*size) + "m"; else Size4 = d2.format(4*size/1000) + "Km";
    g2d.setColor(Color.magenta);
    g2d.fillRect(0, fHeight-10, (fWidth/divisiones), 5);
    g2d.fillRect(2*(fWidth/divisiones), fHeight-10, (fWidth/divisiones), 5);
    g2d.setColor(Color.cyan);
    g2d.fillRect((fWidth/divisiones), fHeight-10, (fWidth/divisiones), 5);
    g2d.fillRect(3*(fWidth/divisiones), fHeight-10, (fWidth/divisiones), 5);
    g2d.setColor(Color.black);
    g2d.drawLine(0*(fWidth/divisiones), fHeight, 0*(fWidth/divisiones), fHeight-15);
    g2d.drawLine(1*(fWidth/divisiones), fHeight, 1*(fWidth/divisiones), fHeight-15);
    g2d.drawLine(2*(fWidth/divisiones), fHeight, 2*(fWidth/divisiones), fHeight-15);
    g2d.drawLine(3*(fWidth/divisiones), fHeight, 3*(fWidth/divisiones), fHeight-15);
    g2d.drawLine(4*(fWidth/divisiones), fHeight, 4*(fWidth/divisiones), fHeight-15);
//    Size0 = String.valueOf(mtsxpixel);
    g2d.drawString(Size0, 0*(fWidth/divisiones), fHeight-15);

    FontMetrics fm = g2d.getFontMetrics(font);
    int lWidth = fm.stringWidth(Size1);
    int lHeight = fm.getHeight();
    int xl = (1*fWidth/divisiones)-(lWidth/2);
    int yl = fHeight-15;
    g2d.drawString(Size1, xl, yl);

    lWidth = fm.stringWidth(Size2);
    lHeight = fm.getHeight();
    xl = (2*fWidth/divisiones)-(lWidth/2);
    g2d.drawString(Size2, xl, yl);

    lWidth = fm.stringWidth(Size3);
    lHeight = fm.getHeight();
    xl = (3*fWidth/divisiones)-(lWidth/2);
    g2d.drawString(Size3, xl, yl);

    lWidth = fm.stringWidth(Size3);
    lHeight = fm.getHeight();
    xl = (4*fWidth/divisiones)-(lWidth);
    g2d.drawString(Size4, xl, yl);
    
//    return scaleimg;
 }

  public Polygon getPolyFromPoints(double[] xp, double[] yp, int cantidad) 
  {
    int[] x = new int[cantidad];
    int[] y = new int[cantidad];
    for (int i=0; i < cantidad; i++) 
    {
      x[i] = (int)( (xp[i] - xOffset) * Factor);
      y[i] = (int)( fHeight - ((yp[i] - yOffset) * Factor));
    }
    Polygon poly = new Polygon(x, y, cantidad);
    return poly;
  } 

  private void zoominbtn_actionPerformed(ActionEvent e)
  {
    setAction(1);
  }

  private void zoomoutbtn_actionPerformed(ActionEvent e)
  {
    setAction(2);
    zoom(2);
  }

  private void zommextbtn_actionPerformed(ActionEvent e)
  {
    setAction(3);
    xOffset = -1.0;
    yOffset = -1.0;
    Qwhere = "";
    extents.setStrExtension(origExt.getStrExtension());
    history[accionespos] = new Extent(extents.getStrExtension());
    accionespos++;
    acciones = accionespos+1;
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

  private void undobtn_actionPerformed(ActionEvent e)
  {
    setAction(5);
    if (accionespos>1)
    {
      xOffset = -1;
      yOffset = -1;
      accionespos--;
      extents.setStrExtension(history[accionespos-1].getStrExtension());
      Qwhere = " where within(the_geom,geometryfromtext('BOX3D(" + 
      extents.getMinX() + " " + extents.getMinY() + " 0," + 
      extents.getMaxX() + " " + extents.getMaxY() + " 0)',-1)) ";
      Qwhere = Qwhere + " or intersects(the_geom,geometryfromtext('BOX3D(" + 
      extents.getMinX() + " " + extents.getMinY() + " 0," + 
      extents.getMaxX() + " " + extents.getMaxY() + " 0)',-1)) ";
      rundoImage();
    }
  }

  private void redobtn_actionPerformed(ActionEvent e)
  {
    setAction(6);
    if (accionespos<acciones-1)
    {
      xOffset = -1;
      yOffset = -1;
      extents.setStrExtension(history[accionespos].getStrExtension());
      accionespos++;
      Qwhere = " where within(the_geom,geometryfromtext('BOX3D(" + 
      extents.getMinX() + " " + extents.getMinY() + " 0," + 
      extents.getMaxX() + " " + extents.getMaxY() + " 0)',-1)) ";
      Qwhere = Qwhere + " or intersects(the_geom,geometryfromtext('BOX3D(" + 
      extents.getMinX() + " " + extents.getMinY() + " 0," + 
      extents.getMaxX() + " " + extents.getMaxY() + " 0)',-1)) ";
      rundoImage();
    }
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

  public void setAction(int accion) 
  {
    curraction = accion;
 //   for (int i=0; i<dibujo.getMouseListeners().length; i++) dibujo.removeMouseListener(dibujo.getMouseListeners()[0]);
 //   for (int i=0; i<dibujo.getMouseMotionListeners().length; i++) dibujo.removeMouseMotionListener(dibujo.getMouseMotionListeners()[0]);
 //   for (int i=0; i<dibujo.getMouseWheelListeners().length; i++) dibujo.removeMouseWheelListener(dibujo.getMouseWheelListeners()[0]);
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
      case 10:
          eraseListener = QueryListener;
          eraseMotionListener = QueryMotionListener;
          break;
    }
    
    dibujo.addMouseListener(eraseListener);
    dibujo.addMouseMotionListener(eraseMotionListener);
//    dibujo.updateUI();
  }

/**  public void mouseReleased(MouseEvent event)
  {
    if (currentRect != null)
    {
      switch (curraction)
      {
        case 10: //Query
          if (Math.abs(currentRect.width)>3 && Math.abs(currentRect.height)>3)
          {
            Point2D.Double puntoOrigen = toSpace(currentRect.x,currentRect.y);
            Point2D.Double puntoDestino = toSpace(currentRect.x+currentRect.width,currentRect.y+currentRect.height);
            Qwhere = " where within(the_geom,geometryfromtext('BOX3D(" + 
            puntoOrigen.x + " " + puntoOrigen.y + " 0," + 
            puntoDestino.x + " " + puntoDestino.y + " 0)',-1)) ";
            Qwhere = Qwhere + " or intersects(the_geom,geometryfromtext('BOX3D(" + 
            puntoOrigen.x + " " + puntoOrigen.y + " 0," + 
            puntoDestino.x + " " + puntoDestino.y + " 0)',-1)) ";
            currentRect = null;
            repaint();
            rundoQuery();
          }
      }
    }
  }

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
    Point2D.Double punto = toSpace(event.getX(),event.getY());
    coordbar.setText("X: " + d4.format(punto.x) + ", Y: " + d4.format(punto.y) + " - Zoom: " + d2.format(((ScaleX-1)*100)+100) + "%");
  }
 
  public void mouseDragged(MouseEvent event)
  {
    if (panning) 
    {
      if (ScaleX>1) 
      {
        int x = event.getX();
        int y = event.getY();
//        if ( ((fWidth*ScaleX)-(x-prevx) > fWidth) && ((fHeight*ScaleX)-(y-prevy) > fHeight) )
        if (((x-prevx)<=0) && (fWidth*ScaleX - (prevx-x) >= fWidth))
        {
          topx = x - prevx;
        }
        if (((y-prevy)<=0) && (fHeight*ScaleX - (prevy-y) >= fHeight))
        {
          topy = y - prevy;
        }

        repaint();
      }
    }
  }

  public void mouseWheelMoved(MouseWheelEvent event)
  {
    if (loaded) 
    {
      if ((ScaleX>1)) ScaleX += -scaleinc*event.getWheelRotation(); 
      else if ((ScaleX == 1) && (event.getWheelRotation()<0)) ScaleX += -scaleinc*event.getWheelRotation(); 
      Point2D.Double punto = toSpace(event.getX(),event.getY());
      coordbar.setText("X: " + d4.format(punto.x) + ", Y: " + d4.format(punto.y) + " - Zoom: " + d2.format(((ScaleX-1)*100)+100) + "%");
      if (ScaleX >= 1) repaint();
    }
  }*/

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
    statusbtn.setIcon(new ImageIcon(LayerDraw.class.getResource("iconos/cancelar.png")));
    tarea.stop();
    indeterminar(false);
    progresobar.setStringPainted(false);
    Cancelar.setEnabled(false);
  }

  public void zoom(double zoom)
  {
    xOffset = -1.0;
    yOffset = -1.0;
    Factor = Factor * zoom;
    double Diffx = Math.abs(extX - (fWidth-20)/Factor);
    double Diffy = Math.abs(extY - (fHeight-20)/Factor);

    extents.setExtension(myExtent.getMinX()-Diffx, myExtent.getMaxX()+Diffx, myExtent.getMinY()-Diffy, myExtent.getMaxY()+Diffy);

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
    if (Factor > minFactor)
    {
      Qwhere = " where within(the_geom,geometryfromtext('BOX3D(" + 
      extents.getMinX() + " " + extents.getMinY() + " 0," + 
      extents.getMaxX() + " " + extents.getMaxY() + " 0)',-1)) ";
      Qwhere = Qwhere + " or intersects(the_geom,geometryfromtext('BOX3D(" + 
      extents.getMinX() + " " + extents.getMinY() + " 0," + 
      extents.getMaxX() + " " + extents.getMaxY() + " 0)',-1)) ";
    } else 
    {
      xOffset = -1.0;
      yOffset = -1.0;
      Qwhere = "";
      extents.setStrExtension(origExt.getStrExtension());
    }
    history[accionespos] = new Extent(extents.getStrExtension());
    accionespos++;
    acciones = accionespos+1;
    rundoImage();
  }
  
  public void drawCatastro(int catastro) 
  {
    String Data = "";
    String geometry = "";
    try 
    {
      Connection Con = Proc.CCon();
      ResultSet cats = Proc.exConsulta(Con, "select idseccion, idparcela from cepax.parcelas where idparcela = cepax.catastros.idparcela and catastros.catastro = " + catastro);
      if (cats.next()) 
      {
        String seccion = cats.getString("idseccion");
        String idparcela = cats.getString("idparcela");
        Data = "from gis.parcelas_s" + seccion.toLowerCase();
        String Cwhere = " where idparcela = " + idparcela;
        geometry = "the_geom"; 
        ResultSet Extent = Proc.exConsulta(Con,"select xmin(extent(" + geometry + ")), " +
                            "xmax(extent(" + geometry + ")), " +
                            "ymin(extent(" + geometry + ")), " +
                            "ymax(extent(" + geometry + ")) " + Data + Cwhere );
  
        if (Extent.next()) 
        {
          extents.setExtension(Extent.getDouble("xmin")-50, Extent.getDouble("xmax")+50, Extent.getDouble("ymin")-50, Extent.getDouble("ymax")+50);
          Qwhere = " where within(the_geom,geometryfromtext('BOX3D(" + 
          extents.getMinX() + " " + extents.getMinY() + " 0," + 
          extents.getMaxX() + " " + extents.getMaxY() + " 0)',-1)) ";
          Qwhere = Qwhere + " or intersects(the_geom,geometryfromtext('BOX3D(" + 
          extents.getMinX() + " " + extents.getMinY() + " 0," + 
          extents.getMaxX() + " " + extents.getMaxY() + " 0)',-1)) ";
          System.out.println(Qwhere);
          rundoImage();
        } else 
        {
          Proc.Mensaje("Catastro no existente en el dibujo", "Catastros");
        }
      }
    } catch (SQLException x)
    {
      x.printStackTrace();
    }
  }
  
  public void actualizar() 
  {
    rundoImage();
  }
  
  synchronized public void rundoQuery() 
  {
    tarea = new Thread(new Runnable() {
      public void run() 
      {
        String htmlPath = "/tmp/jms/reporte.html";
//        String htmlPath = "C:\\temp\\reporte.html";
        try 
        {
          FileWriter htmlFile = new FileWriter(htmlPath,false);
          BufferedWriter log = new BufferedWriter(htmlFile);
    
          htmlFile.write("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 3.2//EN>\n");
          htmlFile.write("<HTML>\n");
          htmlFile.write("<HEAD>\n");
          htmlFile.write("<TITLE>Informe</TITLE>\n");
          htmlFile.write("</HEAD>\n");
          htmlFile.write("<BODY>\n");
          htmlFile.write("<TABLE align=center WIDTH=100% BORDER=1 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
          htmlFile.write("<TR>\n");
    
          /** Titulo */
          htmlFile.write("<TD WIDTH=10%><img src='"+ CreaHTML.class.getResource("iconos/logo.jpg") +"' width=60></TD>\n");
          htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=6 FACE='Arial'><B>Informe</B></FONT></P></TD>\n");
          htmlFile.write("<TD WIDTH=10%><img src='"+ CreaHTML.class.getResource("iconos/logo.jpg") +"' width=60></TD>\n");
    
          htmlFile.write("</TR>\n");
          htmlFile.write("</TABLE><BR><BR>\n");
          for (int i=0; i<numLayers; i++) 
          {
            //progress.
            setMax(0);
//            panel.actualizar();
            String consulta = layer[i].getQuery() + " where " + layer[i].getKeyRef() + " in (select " + layer[i].getKeyRef() + " from(select " + layer[i].getKeyRef() + ", " + layer[i].getData() + Qwhere + ") as foo)";
            System.out.println(consulta);            
            if (!layer[i].getQuery().equals("")) reportero.Reporte(layer[i].getName(), consulta, htmlFile);
          }
          HTMLBrowser temp = new HTMLBrowser(htmlPath);
//          temp.setModal(true);
          temp.setVisible(true);
        } catch (IOException x) 
        {
          x.printStackTrace();
        }
        Cancelar.setEnabled(false);
        indeterminar(false);
        progresobar.setStringPainted(false);
        setStatus("Operación terminada");
        ImageIcon icono = new ImageIcon(LayerDraw.class.getResource("iconos/ok.png"));
        statusbtn.setIcon(icono);
      }
    });
    Cancelar.setEnabled(true);
    tarea.start();
  }

}