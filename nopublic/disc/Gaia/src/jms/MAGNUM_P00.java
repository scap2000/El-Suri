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
 * MAGNUM_P00.java
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MAGNUM_P00 extends JFrame 
{
  private JPanel jPanel1 = new JPanel();
  private JPanel jPanel2 = new JPanel();
  private JLabel jLabel6 = new JLabel(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Logo_MAGNUM.png")));
  private JButton bCerrarSesion = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Usuario.png")).getImage().getScaledInstance(32,32,0)));
  private JButton bSalir = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Cerrar.png")).getImage().getScaledInstance(32,32,0)));
  private JButton bVecino = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Telefono.png")).getImage().getScaledInstance(48,48,0)));
  private JButton bPlanta = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Casco.png")).getImage().getScaledInstance(48,48,0)));
  private JButton bFiscal = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Fiscalizacion.png")).getImage().getScaledInstance(48,48,0)));
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JPopupMenu menuVecino = new JPopupMenu();
  private JMenuItem jMenuItem1 = new JMenuItem();
  private JMenuItem jMenuItem2 = new JMenuItem();
  private JMenuItem jMenuItem3 = new JMenuItem();
  private JPopupMenu menuPlanta = new JPopupMenu();
  private JPopupMenu menuFiscal = new JPopupMenu();
  private JMenuItem jMenuItem4 = new JMenuItem();
  private JMenuItem jMenuItem5 = new JMenuItem();
  private JMenuItem jMenuItem6 = new JMenuItem();
  private JMenu jMenu1 = new JMenu();
  private JMenuItem jMenuItem7 = new JMenuItem();
  private JMenuItem jMenuItem8 = new JMenuItem();
  private JMenuItem jMenuItem9 = new JMenuItem();
  private JMenuItem jMenuItem10 = new JMenuItem();
  private JMenuItem jMenuItem11 = new JMenuItem();
  private JMenuItem jMenuItem12 = new JMenuItem();
  private JMenuItem jMenuItem13 = new JMenuItem();
  private JMenuItem jMenuItem14 = new JMenuItem();
  private JMenu jMenu2 = new JMenu();
  private JMenuItem jMenuItem15 = new JMenuItem();
  private JMenuItem jMenuItem16 = new JMenuItem();
  private JMenuItem jMenuItem17 = new JMenuItem();
  private MapPanel mapa = new MapPanel();

  public MAGNUM_P00()
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
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(645, 807));
    this.setTitle("MAGNUM - Pantalla Principal");
    this.setBounds(new Rectangle(10, 10, 540, 387));
    jPanel1.setBounds(new Rectangle(0, 20, 170, 335));
    jPanel1.setBackground(new Color(155, 155, 255));
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    jPanel1.setLayout(null);
    jPanel2.setBounds(new Rectangle(170, 20, 360, 315));
    jPanel2.setLayout(null);
    jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jLabel6.setBounds(new Rectangle(28, 5, 115, 120));
    bCerrarSesion.setBounds(new Rectangle(10, 230, 155, 40));
    bCerrarSesion.setText("Cerrar sesión");
    bCerrarSesion.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bCerrarSesion_actionPerformed(e);
        }
      });
    bSalir.setBounds(new Rectangle(28, 280, 115, 40));
    bSalir.setText("  Salir");
    bSalir.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bSalir_actionPerformed(e);
        }
      });
    jLabel1.setText("<html><p align=center>Seleccione a su derecha la opción en la que desea trabajar</p></html>");
    jLabel1.setBounds(new Rectangle(40, 140, 90, 75));
    jLabel2.setText(" Bienvenido usuario ______________");
    jLabel3.setText(" Sesión iniciada en la terminal ______________ a Hs: ______");
    jLabel2.setBounds(new Rectangle(0, 0, 530, 20));
    jLabel3.setBounds(new Rectangle(170, 335, 360, 20));
    jLabel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jLabel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    menuVecino.setLabel("Menú Atención al Vecino");
    jMenuItem1.setText("Registrar reclamo");
    jMenuItem1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jMenuItem1_actionPerformed(e);
        }
      });
    jMenuItem2.setText("Consultar estado de un reclamo");
    jMenuItem3.setText("Generar informe diario");
    menuPlanta.setLabel("Menú Planta Hormigonera");
    menuFiscal.setLabel("Menú Fiscalización Urbana");
    jMenuItem4.setText("Emitir Orden de Trabajo");
    jMenuItem5.setText("Emitir Orden de Ejec. de Tareas");
    jMenuItem6.setText("Registrar Avance de Obras");
    jMenu1.setText("Informes");
    jMenuItem7.setText("Trabajos realizados");
    jMenuItem8.setText("Estado de obras");
    jMenuItem9.setText("Menú Atención al Vecino");
    jMenuItem9.setEnabled(false);
    jMenuItem10.setText("Menú Planta Hormigonera");
    jMenuItem10.setEnabled(false);
    jMenuItem11.setText("Menú Fiscalización Urbana");
    jMenuItem11.setEnabled(false);
    jMenuItem12.setText("Generar listado de catastros");
    jMenuItem13.setText("Registrar datos relevados");
    jMenuItem14.setText("Informe de estado de calzadas");
    jMenu2.setText("Mapas para relevamiento");
    jMenuItem15.setText("Calzada");
    jMenuItem16.setText("Cartelería y publicidad");
    jMenuItem17.setText("Baches");

    bVecino.setBounds(new Rectangle(10, 10, 245, 75));
    bVecino.setText("       Atención al Vecino");
    bVecino.addMouseListener(new MouseAdapter()
      {
        public void mousePressed(MouseEvent e)
        {
          menuVecino.show(e.getComponent(), e.getComponent().getLocation().x + e.getComponent().getWidth()-10, e.getY());
        }
      });
    bPlanta.setBounds(new Rectangle(10, 98, 245, 75));
    bPlanta.setText("     Planta Hormigonera");
    bPlanta.addMouseListener(new MouseAdapter()
      {
        public void mousePressed(MouseEvent e)
        {
          menuPlanta.show(e.getComponent(), e.getComponent().getLocation().x + e.getComponent().getWidth()-10, e.getY());
        }
      });
    bFiscal.setBounds(new Rectangle(10, 185, 245, 75));
    bFiscal.setText("     Fiscalización Urbana");
    bFiscal.addMouseListener(new MouseAdapter()
      {
        public void mousePressed(MouseEvent e)
        {
          menuFiscal.show(e.getComponent(), e.getComponent().getLocation().x + e.getComponent().getWidth()-10, e.getY());
        }
      });

    jPanel1.add(jLabel6, null);
    jPanel1.add(bCerrarSesion, null);
    jPanel1.add(bSalir, null);
    jPanel1.add(jLabel1, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jLabel3, null);
    jPanel2.add(bVecino, null);
    jPanel2.add(bPlanta, null);
    jPanel2.add(bFiscal, null);
    this.getContentPane().add(jPanel2, null);
    this.getContentPane().add(jPanel1, null);
    menuVecino.add(jMenuItem9);
    menuVecino.addSeparator();
    menuVecino.add(jMenuItem1);
    menuVecino.add(jMenuItem2);
    menuVecino.add(jMenuItem3);
    menuPlanta.add(jMenuItem10);
    menuPlanta.addSeparator();
    menuPlanta.add(jMenuItem4);
    menuPlanta.add(jMenuItem5);
    menuPlanta.add(jMenuItem6);
    jMenu1.add(jMenuItem7);
    jMenu1.add(jMenuItem8);
    menuPlanta.add(jMenu1);
    menuFiscal.add(jMenuItem11);
    menuFiscal.addSeparator();
    menuFiscal.add(jMenuItem12);
    menuFiscal.add(jMenuItem13);
    menuFiscal.add(jMenuItem14);
    jMenu2.add(jMenuItem15);
    jMenu2.add(jMenuItem16);
    jMenu2.add(jMenuItem17);
    menuFiscal.add(jMenu2);
  }

  private void jMenuItem1_actionPerformed(ActionEvent e)
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

  private void bCerrarSesion_actionPerformed(ActionEvent e)
  {
    Frame frame = new MAGNUM_P18();
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
    frame.addWindowListener(new WindowAdapter()
      {
        public void windowClosing(WindowEvent e)
        {
          System.exit(0);
        }
      });
    frame.setVisible(true);
    this.dispose();
  }

  private void bSalir_actionPerformed(ActionEvent e)
  {
    System.exit(0);
  }

}