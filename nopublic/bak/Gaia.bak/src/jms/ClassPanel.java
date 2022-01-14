package jms;
import java.awt.Component;
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

public class ClassPanel extends JPanel 
{
  private ClassObject clase = new ClassObject();
  private LayerObject layer = new LayerObject();
  private ClassObjectPanel classobjectpanel = new ClassObjectPanel();
  private JLabel labelclasslist = new JLabel();
  private JScrollPane scrollclasslist = new JScrollPane();
  private JList classlist = new JList();
  private JButton jbVaciar = new JButton();
  private JButton jbRestaurar = new JButton();
  private JButton jbBorrar = new JButton();
  private JButton jbGrabar = new JButton();
  private JButton jbCopiar = new JButton();
  private JButton jbPegar = new JButton();
  private JButton jbEstilo = new JButton();
  private DialogStyle dialog = new DialogStyle();

  public ClassPanel(LayerObject param)
  {
    layer = param;
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
    this.setSize(new Dimension(727, 460));
    classobjectpanel.setBounds(new Rectangle(5, 5, 550, 450));
    classobjectpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    labelclasslist.setText("Clases del Layer");
    labelclasslist.setBounds(new Rectangle(560, 5, 160, 20));
    labelclasslist.setHorizontalAlignment(SwingConstants.CENTER);
    scrollclasslist.setBounds(new Rectangle(560, 30, 160, 110));
    scrollclasslist.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    classlist.setBounds(new Rectangle(50, -145, 0, 0));
    jbVaciar.setText("Vaciar");
    jbVaciar.setBounds(new Rectangle(575, 210, 100, 25));
    jbVaciar.setMnemonic('V');
    jbVaciar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbVaciar_actionPerformed(e);
        }
      });
    jbRestaurar.setText("Restaurar");
    jbRestaurar.setBounds(new Rectangle(575, 245, 100, 25));
    jbRestaurar.setMnemonic('R');
    jbRestaurar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbRestaurar_actionPerformed(e);
        }
      });
    jbBorrar.setText("Borrar");
    jbBorrar.setBounds(new Rectangle(575, 310, 100, 25));
    jbBorrar.setMnemonic('B');
    jbBorrar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbBorrar_actionPerformed(e);
        }
      });
    jbGrabar.setText("Grabar");
    jbGrabar.setBounds(new Rectangle(575, 280, 100, 25));
    jbGrabar.setMnemonic('G');
    jbGrabar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbGrabar_actionPerformed(e);
        }
      });
    jbCopiar.setText("Copiar");
    jbCopiar.setBounds(new Rectangle(575, 345, 100, 25));
    jbCopiar.setMnemonic('C');
    jbPegar.setText("Pegar");
    jbPegar.setBounds(new Rectangle(575, 380, 100, 25));
    jbPegar.setMnemonic('P');
    jbEstilo.setText("Estilo");
    jbEstilo.setBounds(new Rectangle(575, 175, 100, 25));
    jbEstilo.setMnemonic('E');
    jbEstilo.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jbEstilo_actionPerformed(e);
        }
      });
    scrollclasslist.getViewport().add(classlist, null);
    this.add(jbEstilo, null);
    this.add(jbPegar, null);
    this.add(jbCopiar, null);
    this.add(jbGrabar, null);
    this.add(jbBorrar, null);
    this.add(jbRestaurar, null);
    this.add(jbVaciar, null);
//    this.add(scrollclasslist, null);
//    this.add(labelclasslist, null);
    this.add(classobjectpanel, null);
    
  }
  
  private void actPanel() 
  {
    String[] classnames = new String[layer.getClassCount()];
    for (int i=0; i<classnames.length; i++) 
    {
      classnames[i] = layer.getClassObject(i).getName();
    }
    classlist.setListData(classnames);
//    layerobjectpanel.actPanel();
  }
  
  public void setClassSource(ClassObject c) 
  {
    this.clase = c;
    classobjectpanel.setClassSource(c);
    actPanel();
  }

  private void jbVaciar_actionPerformed(ActionEvent e)
  {
    classobjectpanel.setEmpty();
  }

  private void jbRestaurar_actionPerformed(ActionEvent e)
  {
    setClassSource(clase);
  }

  private void jbGrabar_actionPerformed(ActionEvent e)
  {
    clase = classobjectpanel.getClase();
    layer.addClassObject(clase);
    JTabbedPane pane = (JTabbedPane) this.getParent();
    pane.setTitleAt(pane.getSelectedIndex(),clase.getName());
    setClassSource(clase);
  }

  private void jbBorrar_actionPerformed(ActionEvent e)
  {
    int answer = JOptionPane.showConfirmDialog((Component) null, "¿Está seguro de borrar la clase actual?","Borrar Clase",JOptionPane.YES_NO_OPTION);
    if (answer == JOptionPane.YES_OPTION) {
        // User clicked YES.
        this.clase.deleteMe();
        JTabbedPane pane = (JTabbedPane) this.getParent();
        pane.remove(pane.getSelectedIndex());
    } else if (answer == JOptionPane.NO_OPTION) {
        // User clicked NO.
    }
  }
  
  public void borrarClass() 
  {
    this.clase.deleteMe();
  }
  
  public void restaurarClass() 
  {
    setClassSource(clase);
  }

  private void jbEstilo_actionPerformed(ActionEvent e)
  {
    dialog.setTitle("Clase: " + clase.getName());
    dialog.setClassSource(clase);
    dialog.setModal(true);
    dialog.show();
  }

}