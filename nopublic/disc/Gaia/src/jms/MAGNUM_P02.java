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
 * MAGNUM_P02.java
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
import javax.swing.JDialog;
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

public class MAGNUM_P02 extends JFrame 
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
  private JTextField fecha = new JTextField();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JTextField tipo = new JTextField();
  private JTextField hora = new JTextField();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JTextField ubicacion = new JTextField();
  private JButton bModificar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Modificar.png")).getImage().getScaledInstance(32, 32, 0)));
  private JButton bAgregar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Agregar.png")).getImage().getScaledInstance(32, 32, 0)));
  private MapPanel mapa;
  private DefaultTableModel tmp = new DefaultTableModel();
  private JTable jTable1 = new JTable(tmp);
  private JScrollPane jScrollPane1 = new JScrollPane(jTable1);
  private JLabel jLabel10 = new JLabel();
  private JTextField estado = new JTextField();
  private JTextField prioridad = new JTextField();
  private JLabel jLabel11 = new JLabel();
  private JLabel jLabel12 = new JLabel();
  private Vector datos1 = new Vector();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JLabel jLabel13 = new JLabel();
  private JTextArea descripcion = new JTextArea();
  private int nroReclamo = 0;
  
  public MAGNUM_P02(MapPanel _mapa)
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
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(840, 555));
    this.setTitle("MAGNUM - Atención al Vecino");
    this.setBounds(new Rectangle(10, 10, 840, 582));
    jPanel1.setBounds(new Rectangle(0, 0, 170, 550));
    jPanel1.setBackground(new Color(155, 155, 255));
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    jPanel1.setLayout(null);
    jPanel2.setBounds(new Rectangle(170, 0, 650, 500));
    jPanel2.setLayout(null);
    jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jLabel6.setBounds(new Rectangle(28, 5, 115, 120));
    bCerrar.setBounds(new Rectangle(710, 505, 115, 40));
    bCerrar.setText("  Cerrar");
    bCerrar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bCerrar_actionPerformed(e);
        }
      });
    jLabel1.setText("<html><p align=right>Administración de Reclamos</p></html>");
    jLabel1.setBounds(new Rectangle(30, 135, 125, 40));
    jLabel1.setFont(new Font("Dialog", 1, 16));
    animbtn.setBounds(new Rectangle(285, 75, 50, 50));
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
    jPanel3.setBounds(new Rectangle(10, 45, 630, 80));
    jPanel3.setBorder(BorderFactory.createTitledBorder(" Panel de Búsqueda "));
    jPanel3.setLayout(null);
    jLabel2.setText("Reclamo Nro");
    jLabel2.setBounds(new Rectangle(20, 20, 130, 25));
    jTextField1.setBounds(new Rectangle(175, 45, 300, 25));
    jTextField2.setBounds(new Rectangle(20, 45, 125, 25));
    jLabel3.setText("Palabras clave");
    jLabel3.setBounds(new Rectangle(175, 20, 105, 25));
    bBuscar.setBounds(new Rectangle(500, 25, 120, 45));
    bBuscar.setText("  Buscar");
    jPanel4.setBounds(new Rectangle(10, 130, 630, 125));
    jPanel4.setBorder(BorderFactory.createTitledBorder(" Listado de reclamos "));
    jPanel4.setLayout(paneLayout1);
    fecha.setEnabled(false);
    tipo.setEnabled(false);
    hora.setEnabled(false);
    ubicacion.setEnabled(false);
    bAgregar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bAgregar_actionPerformed(e);
        }
      });
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jLabel10.setText(nroReclamo + " Reclamo(s) registrado(s) de Santiago Cassina");
    jLabel10.setBounds(new Rectangle(10, 15, 630, 25));
    jLabel10.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
    estado.setBounds(new Rectangle(490, 100, 125, 25));
    estado.setEnabled(false);
    prioridad.setBounds(new Rectangle(345, 100, 125, 25));
    prioridad.setEnabled(false);
    jLabel11.setBounds(new Rectangle(490, 75, 60, 25));
    jLabel11.setText("Estado");
    jLabel12.setBounds(new Rectangle(345, 75, 90, 25));
    jLabel12.setText("Prioridad");
    jScrollPane2.setBounds(new Rectangle(20, 155, 600, 60));
    jLabel13.setBounds(new Rectangle(20, 130, 140, 25));
    jLabel13.setText("Descripción");
    jLabel13.setToolTipText("null");
    descripcion.setEnabled(false);

    jPanel1.add(jLabel6, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jLabel7, null);
    jPanel3.add(jLabel3, null);
    jPanel3.add(jTextField2, null);
    jPanel3.add(jLabel2, null);
    jPanel3.add(bBuscar, null);
    jPanel3.add(jTextField1, null);
    bAgregar.setFont(new Font("Dialog", 0, 10));
    bModificar.setFont(new Font("Dialog", 0, 10));
    bAgregar.setText("  Agregar");
    bAgregar.setBounds(new Rectangle(465, 505, 115, 40));
    bModificar.setText("  Modif.");
    bModificar.setBounds(new Rectangle(588, 505, 115, 40));
    ubicacion.setBounds(new Rectangle(20, 100, 255, 25));
    jLabel9.setBounds(new Rectangle(20, 75, 140, 25));
    jLabel9.setText("Ubicación Geográfica");
    jLabel8.setBounds(new Rectangle(490, 20, 60, 25));
    jLabel8.setText("Hora");
    hora.setBounds(new Rectangle(490, 45, 125, 25));
    tipo.setBounds(new Rectangle(20, 45, 255, 25));
    jLabel5.setBounds(new Rectangle(20, 20, 35, 25));
    jLabel5.setText("Tipo");
    jLabel4.setBounds(new Rectangle(345, 20, 45, 25));
    jLabel4.setText("Fecha");
    fecha.setBounds(new Rectangle(345, 45, 125, 25));
    jPanel5.setLayout(null);
    jPanel5.setBorder(BorderFactory.createTitledBorder(" Datos del Reclamo "));
    jPanel5.setBounds(new Rectangle(10, 265, 630, 225));
    jPanel5.add(jLabel13, null);
    jScrollPane2.getViewport().add(descripcion, null);
    jPanel5.add(jScrollPane2, null);
    jPanel5.add(jLabel12, null);
    jPanel5.add(jLabel11, null);
    jPanel5.add(prioridad, null);
    jPanel5.add(estado, null);
    jPanel5.add(ubicacion, null);
    jPanel5.add(jLabel9, null);
    jPanel5.add(jLabel8, null);
    jPanel5.add(hora, null);
    jPanel5.add(tipo, null);
    jPanel5.add(jLabel5, null);
    jPanel5.add(jLabel4, null);
    jPanel5.add(fecha, null);
    jPanel5.add(animbtn, null);
    
    jPanel2.add(jLabel10, null);
    jPanel2.add(jPanel5, null);
    jPanel4.add(jScrollPane1, null);
    jPanel2.add(jPanel4, null);
    jPanel2.add(jPanel3, null);
    this.getContentPane().add(bAgregar, null);
    this.getContentPane().add(bModificar, null);
    this.getContentPane().add(jPanel2, null);
    this.getContentPane().add(jPanel1, null);
    this.getContentPane().add(bCerrar, null);

    jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) 
        {
          ListSelectionModel modelo = (ListSelectionModel)e.getSource();
          if (!modelo.isSelectionEmpty())
          {
            int fila = modelo.getMinSelectionIndex();  //indice del vector de vectores
            datos1 = (Vector)tmp.getDataVector().elementAt(fila);
            tipo.setText(datos1.elementAt(1).toString());
            fecha.setText(datos1.elementAt(2).toString());
            hora.setText(datos1.elementAt(3).toString());
            ubicacion.setText(datos1.elementAt(4).toString());
            prioridad.setText(datos1.elementAt(5).toString());
            estado.setText(datos1.elementAt(6).toString());
            descripcion.setText(datos1.elementAt(7).toString());
            
          }
        }
    });
    
    animbtn.setIcon(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/anims/Earth3.gif")).getImage().getScaledInstance(48,48,0)));

    tmp.setColumnCount(0);
    tmp.addColumn("Nro");
    tmp.addColumn("Tipo");
    tmp.addColumn("Fecha");
    tmp.addColumn("Hora");
    tmp.addColumn("Ubicación");
    tmp.addColumn("Prioridad");
    tmp.addColumn("Estado");
    tmp.addColumn("Descripción");
//    tmp.addRow(new Object[]{"918","18-12-2006","Luminaria averiada","Iniciado"});
  }

  private void bCerrar_actionPerformed(ActionEvent e)
  {
    Frame frame = new MAGNUM_P01(mapa);
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
    nroReclamo++;
//    tmp.addRow(new Object[]{String.valueOf(nroReclamo),fecha.getText(),descripcion.getText(),estado.getText()});
    Object[] reclamo = new Object[8];
    JDialog frame = new MAGNUM_P05(this, mapa);
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
    frame.setModal(true);
  }

  public void addReclamo(Object[] _reclamo) 
  {
    tmp.addRow(_reclamo);
  }

  private void animbtn_actionPerformed(ActionEvent e)
  {
    mapa.verMapa(ubicacion);
  }

}