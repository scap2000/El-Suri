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

public class DialogStyle extends JDialog 
{
  private JButton jbAceptar = new JButton();
  private JButton jbCancelar = new JButton();
  private MapObject mapa = new MapObject();
  private StyleObjectPanel styleobjectpanel = new StyleObjectPanel();
  private JPanel panelbotones = new JPanel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private ClassObject clase = new ClassObject();
  public DialogStyle()
  {
    this(null, "", false);
  }

  /**
   * 
   * @param parent
   * @param title
   * @param modal
   */
  public DialogStyle(Frame parent, String title, boolean modal)
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
    this.setSize(new Dimension(463, 485));
    this.getContentPane().setLayout(borderLayout1);
    jbAceptar.setText("Aceptar");
    jbAceptar.setMnemonic('A');
    jbAceptar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbAceptar_actionPerformed(e);
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
    panelbotones.add(jbAceptar, null);
    panelbotones.add(jbCancelar, null);
    this.getContentPane().add(panelbotones, BorderLayout.SOUTH);
    this.getContentPane().add(styleobjectpanel, BorderLayout.CENTER);
  }

  private void jbAceptar_actionPerformed(ActionEvent e)
  {
    clase.setStyleObject(styleobjectpanel.getStyle());
    this.dispose();
//    layer.addClassObject(clase);
//    JTabbedPane pane = (JTabbedPane) this.getParent();
//    pane.setTitleAt(pane.getSelectedIndex(),clase.getName());
  }

  private void jbCancelar_actionPerformed(ActionEvent e)
  {
    this.dispose();
  }
  
  public void setMapSource(MapObject map) 
  {
    this.mapa = map;   
  }
  
  public void setClassSource(ClassObject c) 
  {
    this.clase = c;
    styleobjectpanel.setStyleSource(clase.getStyleObject());
   
  }
  
}