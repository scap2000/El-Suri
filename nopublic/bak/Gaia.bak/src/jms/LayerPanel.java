package jms;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class LayerPanel extends JPanel 
{
  private LayerObject layer = new LayerObject();
  private MapObject mapa = new MapObject();
  private LayerObjectPanel layerobjectpanel = new LayerObjectPanel();
  private JLabel labelclasslist = new JLabel();
  private JScrollPane scrollclasslist = new JScrollPane();
  private JList classlist = new JList();
  private JButton jbVaciar = new JButton();
  private JButton jbRestaurar = new JButton();
  private JButton jbBorrar = new JButton();
  private JButton jbGrabar = new JButton();
  private JButton jbCopiar = new JButton();
  private JButton jbPegar = new JButton();
  private DialogClass dialog = new DialogClass();
  private JButton jbPrevisualizar = new JButton();

  public LayerPanel(MapObject param)
  {
    super();
    mapa = param;
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
    this.setSize(new Dimension(727, 542));
    layerobjectpanel.setBounds(new Rectangle(5, 5, 550, 530));
    layerobjectpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    labelclasslist.setText("Clases del Layer");
    labelclasslist.setBounds(new Rectangle(560, 5, 160, 20));
    labelclasslist.setHorizontalAlignment(SwingConstants.CENTER);
    scrollclasslist.setBounds(new Rectangle(560, 30, 160, 110));
    scrollclasslist.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    classlist.setBounds(new Rectangle(50, -145, 0, 0));
    classlist.addMouseListener(new java.awt.event.MouseAdapter()
      {
        public void mouseClicked(MouseEvent e)
        {
          classlist_mouseClicked(e);
        }
      });
    jbVaciar.setText("Vaciar");
    jbVaciar.setBounds(new Rectangle(575, 175, 100, 25));
    jbVaciar.setMnemonic('V');
    jbVaciar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbVaciar_actionPerformed(e);
        }
      });
    jbRestaurar.setText("Restaurar");
    jbRestaurar.setBounds(new Rectangle(575, 209, 100, 25));
    jbRestaurar.setMnemonic('R');
    jbRestaurar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbRestaurar_actionPerformed(e);
        }
      });
    jbBorrar.setText("Borrar");
    jbBorrar.setBounds(new Rectangle(575, 277, 100, 25));
    jbBorrar.setMnemonic('B');
    jbBorrar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbBorrar_actionPerformed(e);
        }
      });
    jbGrabar.setText("Grabar");
    jbGrabar.setBounds(new Rectangle(575, 243, 100, 25));
    jbGrabar.setMnemonic('G');
    jbGrabar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbGrabar_actionPerformed(e);
        }
      });
    jbCopiar.setText("Copiar");
    jbCopiar.setBounds(new Rectangle(575, 311, 100, 25));
    jbCopiar.setMnemonic('C');
    jbPegar.setText("Pegar");
    jbPegar.setBounds(new Rectangle(575, 345, 100, 25));
    jbPegar.setMnemonic('P');
    jbPrevisualizar.setText("Previsualizar");
    jbPrevisualizar.setBounds(new Rectangle(575, 380, 100, 25));
    jbPrevisualizar.setMnemonic('P');
    jbPrevisualizar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbPrevisualizar_actionPerformed(e);
        }
      });
    scrollclasslist.getViewport().add(classlist, null);
    this.add(jbPrevisualizar, null);
    this.add(jbPegar, null);
    this.add(jbCopiar, null);
    this.add(jbGrabar, null);
    this.add(jbBorrar, null);
    this.add(jbRestaurar, null);
    this.add(jbVaciar, null);
    this.add(scrollclasslist, null);
    this.add(labelclasslist, null);
    this.add(layerobjectpanel, null);
    
  }
  
  private void actPanel() 
  {
    JTabbedPane tabbedpane = dialog.getTabbedPane();
    tabbedpane.removeAll();
    String[] classnames = new String[layer.getClassCount()];
    dialog.getContentPane().add(tabbedpane, BorderLayout.CENTER);
    for (int i=0; i<classnames.length; i++) 
    {
      classnames[i] = layer.getClassObject(i).getName();
      ClassPanel classpanel = new ClassPanel(this.layer);
      classpanel.setClassSource(layer.getClassObject(i));
      String title = classnames[i];
      tabbedpane.addTab(title,classpanel);
    }
    classlist.setListData(classnames);
    layerobjectpanel.actPanel();
  }
  
  public void setLayerSource(LayerObject l) 
  {
    this.layer = l;
    layerobjectpanel.setLayerSource(l);
    actPanel();
  }

  private void jbVaciar_actionPerformed(ActionEvent e)
  {
    layerobjectpanel.setEmpty();
  }

  private void jbRestaurar_actionPerformed(ActionEvent e)
  {
    setLayerSource(layer);
  }

  private void jbGrabar_actionPerformed(ActionEvent e)
  {
    layer = layerobjectpanel.getLayer();
    mapa.addLayerObject(layer);
    JTabbedPane pane = (JTabbedPane) this.getParent();
    pane.setTitleAt(pane.getSelectedIndex(),layer.getName());
    setLayerSource(layer);
  }

  private void jbBorrar_actionPerformed(ActionEvent e)
  {
    int answer = JOptionPane.showConfirmDialog((Component) null, "¿Está seguro de borrar el layer actual?","Borrar Layer",JOptionPane.YES_NO_OPTION);
    if (answer == JOptionPane.YES_OPTION) {
        // User clicked YES.
        this.layer.deleteMe();
        JTabbedPane pane = (JTabbedPane) this.getParent();
        pane.remove(pane.getSelectedIndex());
    } else if (answer == JOptionPane.NO_OPTION) {
        // User clicked NO.
    }  
  }
  
  public void borrarLayer() 
  {
    this.layer.deleteMe();
  }
  
  public void setMapSource(MapObject map) 
  {
    this.mapa = map;
  }
  
  public void restaurarLayer() 
  {
    setLayerSource(layer);
  }

  private void classlist_mouseClicked(MouseEvent e)
  {
    if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1)
    {
      JTabbedPane tabbedpane = dialog.getTabbedPane();
      tabbedpane.setSelectedIndex(classlist.getSelectedIndex());
      dialog.setTitle("Layer: " + layer.getName());
      dialog.setLayerSource(layer);
      dialog.setModal(true);
      dialog.setVisible(true);
      actPanel();
    }
  }

  private void jbPrevisualizar_actionPerformed(ActionEvent e)
  {
//    JFrame Prev = new JFrame();
//    Polygon2Panel panel = new Polygon2Panel(layer.getLabelItem(), layer.getData(),layer.getClassObject("color").getStyleObject().getOutLineColor());
//    Prev.getContentPane().add(panel);
//    Prev.setSize(800,600);
//    Prev.show();
    LayerDraw test = new LayerDraw(layer);
  }

}