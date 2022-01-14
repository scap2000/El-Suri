package jms;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class JMSMain extends JFrame 
{

  static private MyGlassPane myGlassPane;
  private JMenuItem menuHelpAbout = new JMenuItem();
  private JMenu menuHelp = new JMenu();
  private JMenuItem menuFileExit = new JMenuItem();
  private JMenu menuFile = new JMenu();
  private JMenuBar menuBar = new JMenuBar();
//  private MapPanel jPanel1 = new MapPanel();
//  private Polygon2Panel editorPane = new Polygon2Panel();
  private MapObject mapa = new MapObject();
  private MapPanel editorPane = new MapPanel();
  int i = 0;
  private JScrollPane jScrollPane1 = new JScrollPane();
  private BorderLayout borderLayout1 = new BorderLayout();
  
  public JMSMain()
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
    this.setJMenuBar(menuBar);
    this.getContentPane().setLayout(borderLayout1);
    this.setSize(new Dimension(793, 693));
    this.setTitle("Java Map Server 1 (Beta)");
    menuFile.setText("File");
    menuFileExit.setText("Exit");
    menuFileExit.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ae)
        {
          fileExit_ActionPerformed(ae);
        }
      });
    menuHelp.setText("Help");
    menuHelpAbout.setText("About");
    menuHelpAbout.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ae)
        {
          helpAbout_ActionPerformed(ae);
        }
      });
    menuFile.add(menuFileExit);
    menuBar.add(menuFile);
    menuHelp.add(menuHelpAbout);
    menuBar.add(menuHelp);
    jScrollPane1.getViewport().add(editorPane, null);
    this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
    myGlassPane = new MyGlassPane();
    this.setGlassPane(myGlassPane);
    //this.getGlassPane().setVisible(true);
  }



  void fileExit_ActionPerformed(ActionEvent e)
  {
    System.exit(0);
  }

  void helpAbout_ActionPerformed(ActionEvent e)
  {
    JOptionPane.showMessageDialog(this, new JMSMain_AboutBoxPanel1(), "About", JOptionPane.PLAIN_MESSAGE);   
  }

}

class MyGlassPane extends JComponent {
    Point point;

    public void paint(Graphics g) 
    {
    }

    public void setPoint(Point p) {
        point = p;
    }

    public MyGlassPane() {
        CBListener listener = new CBListener(this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }
}

class CBListener extends MouseInputAdapter {
    Toolkit toolkit;
    Component liveButton;
    JMenuBar menuBar;
    MyGlassPane glassPane;
    Container contentPane;
    boolean inDrag = false;
    int startX;
    int startY;

    public CBListener(MyGlassPane glassPane) {
        toolkit = Toolkit.getDefaultToolkit();
        this.glassPane = glassPane;
    }


   
    public void mouseMoved(MouseEvent e) {
//        redispatchMouseEvent(e, false);
    }

    public void mousePressed(MouseEvent event)
    {
      startX = event.getX();
      startY = event.getY();
//      redispatchMouseEvent(event, false);
    }
    
    public void mouseReleased(MouseEvent event)
    {

    }

    public void mouseDragged(MouseEvent e) 
    {
      int currentX = e.getX();
      int currentY = e.getY();
      int beginX = Math.min(startX, currentX);
      int beginY = Math.min(startY, currentY);
      int width = Math.abs(currentX - startX);
      int height = Math.abs(currentY - startY);
      glassPane.getGraphics().drawRect(beginX, beginY, width, height);
      glassPane.repaint();
    }

    public void mouseClicked(MouseEvent e) {
//        redispatchMouseEvent(e, false);
    }

    public void mouseEntered(MouseEvent e) {
//        redispatchMouseEvent(e, false);
    }

    public void mouseExited(MouseEvent e) {
//        redispatchMouseEvent(e, false);
    }

}