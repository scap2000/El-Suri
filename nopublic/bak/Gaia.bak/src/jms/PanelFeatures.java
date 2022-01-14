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

