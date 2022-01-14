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
 * MAGNUM_P01.java
 *
 * */
package jms;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import oracle.jdeveloper.layout.PaneLayout;
import oracle.jdeveloper.layout.PaneConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MAGNUM_P01 extends JFrame 
{
  private JPanel jPanel1 = new JPanel();
  private JPanel jPanel2 = new JPanel();
  private JLabel jLabel6 = new JLabel(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Logo_MAGNUM.png")));
  private JButton bCerrar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Cerrar.png")).getImage().getScaledInstance(32,32,0)));
  private JLabel jLabel1 = new JLabel();
  private JButton animbtn = new JButton();
  private JLabel jLabel7 = new JLabel(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Vecinos.png")).getImage().getScaledInstance(48, 48, 0)));
  private JPanel jPanel3 = new JPanel();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JButton bBuscar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Buscar.png")).getImage().getScaledInstance(32, 32, 0)));
  private JPanel jPanel4 = new JPanel();
  private PaneLayout paneLayout1 = new PaneLayout();
  private JPanel jPanel5 = new JPanel();
  private JTextField dni = new JTextField();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JTextField apellido = new JTextField();
  private JTextField telefono = new JTextField();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JTextField direccion = new JTextField();
  private JButton bEliminar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Eliminar.png")).getImage().getScaledInstance(32, 32, 0)));
  private JButton bModificar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Modificar.png")).getImage().getScaledInstance(32, 32, 0)));
  private JButton bAgregar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Agregar.png")).getImage().getScaledInstance(32, 32, 0)));
  private JButton bSeleccionar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Seleccionar.png")).getImage().getScaledInstance(32, 32, 0)));
  MapPanel mapa;

  DefaultTableModel tmp = new DefaultTableModel();
  private JTable jTable1 = new JTable(tmp);
  private JScrollPane jScrollPane1 = new JScrollPane(jTable1);
  private int nroVecino = 64557;
  private Vector datos1 = new Vector();

  public MAGNUM_P01(MapPanel _mapa)
  {
    try
    {
      mapa = _mapa;
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception
  {
    bCerrar.setFont(new Font("Dialog", 0, 10));
    dni.setEnabled(false);
    apellido.setEnabled(false);
    telefono.setEnabled(false);
    direccion.setEnabled(false);
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(540, 457));
    this.setTitle("MAGNUM - Atención al Vecino");
    this.setBounds(new Rectangle(10, 10, 840, 457));
    jPanel1.setBounds(new Rectangle(0, 0, 170, 425));
    jPanel1.setBackground(new Color(155, 155, 255));
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    jPanel1.setLayout(null);
    jPanel2.setBounds(new Rectangle(170, 0, 650, 375));
    jPanel2.setLayout(null);
    jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jLabel6.setBounds(new Rectangle(28, 5, 115, 120));
    bCerrar.setBounds(new Rectangle(705, 380, 115, 40));
    bCerrar.setText("  Cerrar");
    bCerrar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bCerrar_actionPerformed(e);
        }
      });
    jLabel1.setText("<html><p align=right>Administración de Vecinos</p></html>");
    jLabel1.setBounds(new Rectangle(30, 135, 125, 40));
    jLabel1.setFont(new Font("Dialog", 1, 16));
    animbtn.setBounds(new Rectangle(345, 75, 50, 50));
    animbtn.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    animbtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          animbtn_actionPerformed(e);
        }
      });
    jLabel7.setSize(new Dimension(48, 48));
    jLabel7.setBounds(new Rectangle(15, 155, 45, 50));
    jPanel3.setBounds(new Rectangle(10, 10, 630, 80));
    jPanel3.setBorder(BorderFactory.createTitledBorder(" Panel de Búsqueda "));
    jPanel3.setLayout(null);
    jLabel2.setText("Apellido y Nombres");
    jLabel2.setBounds(new Rectangle(20, 20, 130, 25));
    jTextField1.setBounds(new Rectangle(20, 45, 300, 25));
    jTextField2.setBounds(new Rectangle(345, 45, 125, 25));
    jLabel3.setText("DNI");
    jLabel3.setBounds(new Rectangle(345, 20, 30, 25));
    bBuscar.setBounds(new Rectangle(500, 25, 120, 45));
    bBuscar.setText("  Buscar");
    bBuscar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bBuscar_actionPerformed(e);
        }
      });
    jPanel4.setBounds(new Rectangle(10, 95, 630, 125));
    jPanel4.setBorder(BorderFactory.createTitledBorder(" Resultados de la Búsqueda "));
    jPanel4.setLayout(paneLayout1);
    bAgregar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bAgregar_actionPerformed(e);
        }
      });
    bSeleccionar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bSeleccionar_actionPerformed(e);
        }
      });
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


    jPanel1.add(jLabel6, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jLabel7, null);
    jPanel3.add(jLabel3, null);
    jPanel3.add(jTextField2, null);
    jPanel3.add(jTextField1, null);
    jPanel3.add(jLabel2, null);
    jPanel3.add(bBuscar, null);
    jPanel4.add(jScrollPane1,null);
    bSeleccionar.setFont(new Font("Dialog", 0, 10));
    bAgregar.setFont(new Font("Dialog", 0, 10));
    bModificar.setFont(new Font("Dialog", 0, 10));
    bEliminar.setFont(new Font("Dialog", 0, 10));
    bSeleccionar.setText("  Selecc.");
    bSeleccionar.setBounds(new Rectangle(175, 380, 115, 40));
    bAgregar.setText("  Agregar");
    bAgregar.setBounds(new Rectangle(330, 380, 115, 40));
    bModificar.setText("  Modif.");
    bModificar.setBounds(new Rectangle(455, 380, 115, 40));
    bEliminar.setText("  Eliminar");
    bEliminar.setBounds(new Rectangle(580, 380, 115, 40));
    direccion.setBounds(new Rectangle(20, 100, 300, 25));
    jLabel9.setBounds(new Rectangle(20, 75, 65, 25));
    jLabel9.setText("Dirección");
    jLabel8.setBounds(new Rectangle(490, 20, 60, 25));
    jLabel8.setText("Teléfono");
    telefono.setBounds(new Rectangle(490, 45, 125, 25));
    apellido.setBounds(new Rectangle(20, 45, 300, 25));
    jLabel5.setBounds(new Rectangle(20, 20, 130, 25));
    jLabel5.setText("Apellido y Nombres");
    jLabel4.setBounds(new Rectangle(345, 20, 30, 25));
    jLabel4.setText("DNI");
    dni.setBounds(new Rectangle(345, 45, 125, 25));
    jPanel5.setLayout(null);
    jPanel5.setBorder(BorderFactory.createTitledBorder(" Datos Personales "));
    jPanel5.setBounds(new Rectangle(10, 225, 630, 140));
    jPanel5.add(direccion, null);
    jPanel5.add(jLabel9, null);
    jPanel5.add(jLabel8, null);
    jPanel5.add(telefono, null);
    jPanel5.add(apellido, null);
    jPanel5.add(jLabel5, null);
    jPanel5.add(jLabel4, null);
    jPanel5.add(dni, null);
    jPanel5.add(animbtn, null);
    
    jPanel2.add(jPanel5, null);
    jPanel2.add(jPanel4, null);
    jPanel2.add(jPanel3, null);
    this.getContentPane().add(bSeleccionar, null);
    this.getContentPane().add(bAgregar, null);
    this.getContentPane().add(bModificar, null);
    this.getContentPane().add(bEliminar, null);
    this.getContentPane().add(jPanel2, null);
    this.getContentPane().add(jPanel1, null);
    this.getContentPane().add(bCerrar, null);
    animbtn.setIcon(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/anims/Earth3.gif")).getImage().getScaledInstance(48,48,0)));

    jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) 
        {
          ListSelectionModel modelo = (ListSelectionModel)e.getSource();
          if (!modelo.isSelectionEmpty())
          {
            int fila = modelo.getMinSelectionIndex();  //indice del vector de vectores
            datos1 = (Vector)tmp.getDataVector().elementAt(fila);
            apellido.setText(datos1.elementAt(1).toString());
            dni.setText(datos1.elementAt(2).toString());
            direccion.setText(datos1.elementAt(3).toString());
            telefono.setText(datos1.elementAt(4).toString());
          }
        }
    });


  }

  private void bCerrar_actionPerformed(ActionEvent e)
  {
    Frame frame = new MAGNUM_P00();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height)
    {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width)
    {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
    this.dispose();
  }

  private void bAgregar_actionPerformed(ActionEvent e)
  {
//    nroVecino++;
//    tmp.addRow(new Object[]{String.valueOf(nroVecino),apellido.getText(),dni.getText(),direccion.getText()});
  }

  private void bSeleccionar_actionPerformed(ActionEvent e)
  {
    Frame frame = new MAGNUM_P02(mapa);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height)
    {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width)
    {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
    this.dispose();
  }

  private void bBuscar_actionPerformed(ActionEvent e)
  {
    tmp.setColumnCount(0);
    tmp.addColumn("ID");
    tmp.addColumn("Apellido y Nombres");
    tmp.addColumn("DNI");
    tmp.addColumn("Dirección");
    tmp.addColumn("Teléfono");
    tmp.addRow(new Object[]{"1231","Santiago Cassina","27034668","3563403.911593166,7259418.567382667","428-3506"});
    tmp.addRow(new Object[]{"5568","Raúl Cassini","3450697","3536596.2275026417,7259179.5982626","439-9558"});
  }

  private void animbtn_actionPerformed(ActionEvent e)
  {
    mapa.verMapa(direccion);
  }

}