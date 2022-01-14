package jms;
import java.awt.Frame;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import jms.LayerPanel;
import java.awt.BorderLayout;

public class DialogLayer extends JDialog 
{
  private JTabbedPane tabbedpane = new JTabbedPane();
  private JButton jbNuevo = new JButton();
  private JButton jbPegarNuevo = new JButton();
  private JButton jbBorrarTodo = new JButton();
  private JButton jbRestaurarTodo = new JButton();
  private MapObject mapa = new MapObject();
  private JPanel panelbotones = new JPanel();
  private BorderLayout borderlayout = new BorderLayout();
  private JButton jbCancelar = new JButton();
  private JButton jbGrabar = new JButton();
  public DialogLayer()
  {
    this(null, "", false);
  }

  /**
   * 
   * @param parent
   * @param title
   * @param modal
   */
  public DialogLayer(Frame parent, String title, boolean modal)
  {
    super(parent, title, modal);
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
    this.setSize(new Dimension(761, 696));
    this.getContentPane().setLayout(borderlayout);
    jbNuevo.setText("Nuevo");
    jbNuevo.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbNuevo_actionPerformed(e);
        }
      });
    jbPegarNuevo.setText("Pegar en Nuevo");
    jbPegarNuevo.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbPegarNuevo_actionPerformed(e);
        }
      });
    jbBorrarTodo.setText("Borrar Todo");
    jbBorrarTodo.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbBorrarTodo_actionPerformed(e);
        }
      });
    jbRestaurarTodo.setText("Restaurar Todo");
    jbRestaurarTodo.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbRestaurarTodo_actionPerformed(e);
        }
      });
    jbCancelar.setText("Cancelar");
    jbCancelar.setMnemonic('C');
    jbCancelar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbCancelar_actionPerformed(e);
        }
      });
    jbGrabar.setText("Aceptar");
    jbGrabar.setMnemonic('a');
    jbGrabar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbAceptar_actionPerformed(e);
        }
      });
    panelbotones.add(jbNuevo, null);
    panelbotones.add(jbPegarNuevo, null);
    panelbotones.add(jbRestaurarTodo, null);
    panelbotones.add(jbBorrarTodo, null);
    panelbotones.add(jbGrabar, null);
    panelbotones.add(jbCancelar, null);
    this.getContentPane().add(panelbotones, BorderLayout.SOUTH);
    this.getContentPane().add(tabbedpane, BorderLayout.CENTER);
  }

  private void jbNuevo_actionPerformed(ActionEvent e)
  {
    LayerPanel layerpanel = new LayerPanel(mapa);
    tabbedpane.addTab("* Nuevo()", layerpanel);
    tabbedpane.setSelectedIndex(tabbedpane.getTabCount()-1);
  }

  private void jbPegarNuevo_actionPerformed(ActionEvent e)
  {
  }

  private void jbBorrarTodo_actionPerformed(ActionEvent e)
  {
    for (int i=0; i<tabbedpane.getTabCount();i++) 
    {
      LayerPanel layerpanel = (LayerPanel) tabbedpane.getComponentAt(i);
      layerpanel.borrarLayer();
    }
    tabbedpane.removeAll();
  }
  
  public JTabbedPane getTabbedPane() 
  {
    return this.tabbedpane;
  }
  
  public void setMapSource(MapObject map) 
  {
    this.mapa = map;   
  }

  private void jbRestaurarTodo_actionPerformed(ActionEvent e)
  {
    for (int i=0; i<tabbedpane.getTabCount();i++) 
    {
      LayerPanel layerpanel = (LayerPanel) tabbedpane.getComponentAt(i);
      layerpanel.restaurarLayer();
    }
  }

  private void jbCancelar_actionPerformed(ActionEvent e)
  {
    this.dispose();
  }

  private void jbAceptar_actionPerformed(ActionEvent e)
  {
    this.dispose();
 }
}