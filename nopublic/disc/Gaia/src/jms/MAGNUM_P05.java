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
 * MAGNUM_P05.java
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
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdeveloper.layout.PaneLayout;
import oracle.jdeveloper.layout.PaneConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class MAGNUM_P05 extends JDialog 
{
  private JPanel jPanel1 = new JPanel();
  private JPanel jPanel2 = new JPanel();
  private JLabel jLabel6 = new JLabel(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Logo_MAGNUM.png")));
  private JLabel jLabel1 = new JLabel();
  private JButton animbtn = new JButton();
  private JLabel jLabel7 = new JLabel(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Vecinos.png")).getImage().getScaledInstance(48, 48, 0)));
  private JLabel jLabel2 = new JLabel();
  private JPanel jPanel5 = new JPanel();
  private JTextField fecha = new JTextField();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JTextField hora = new JTextField();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JTextField ubicacion = new JTextField();
  private MapPanel mapa;
  DefaultTableModel tmp = new DefaultTableModel();
  private JTable jTable1 = new JTable(tmp);
  private JLabel jLabel11 = new JLabel();
  private JLabel jLabel12 = new JLabel();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JLabel jLabel13 = new JLabel();
  private JTextArea descripcion = new JTextArea();
  private JButton bCancelar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Cancelar.png")).getImage().getScaledInstance(32, 32, 0)));
  private JButton bAceptar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Aceptar.png")).getImage().getScaledInstance(32, 32, 0)));
  private JComboBox tipo = new JComboBox();
  private JComboBox prioridad = new JComboBox();
  private JComboBox estado = new JComboBox();
  private Object[] reclamo;
  private int nroReclamo = 0;
  private MAGNUM_P02 parent;
  
  public MAGNUM_P05(MAGNUM_P02 _parent, MapPanel _mapa)
  {
    try
    {
      parent = _parent;
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
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(840, 555));
    this.setTitle("MAGNUM - Atención al Vecino");
    this.setBounds(new Rectangle(10, 10, 820, 362));
    jPanel1.setBounds(new Rectangle(0, 0, 170, 330));
    jPanel1.setBackground(new Color(155, 155, 255));
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    jPanel1.setLayout(null);
    jPanel2.setBounds(new Rectangle(170, 0, 640, 270));
    jPanel2.setLayout(null);
    jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jLabel6.setBounds(new Rectangle(28, 5, 115, 120));
    jLabel1.setText("<html><p align=right>Agregar/Modificar Reclamo</p></html>");
    jLabel1.setBounds(new Rectangle(10, 135, 155, 40));
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
    jLabel2.setText("Reclamo Nro: X");
    jLabel2.setBounds(new Rectangle(168, 10, 315, 25));
    jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
    ubicacion.setEnabled(false);
    jLabel11.setBounds(new Rectangle(490, 75, 60, 25));
    jLabel11.setText("Estado");
    jLabel12.setBounds(new Rectangle(345, 75, 90, 25));
    jLabel12.setText("Prioridad");
    jScrollPane2.setBounds(new Rectangle(20, 155, 600, 60));
    jLabel13.setBounds(new Rectangle(20, 130, 140, 25));
    jLabel13.setText("Descripción");
    jLabel13.setToolTipText("null");
    bCancelar.setBounds(new Rectangle(505, 280, 60, 40));
    bCancelar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bCancelar_actionPerformed(e);
        }
      });
    bAceptar.setBounds(new Rectangle(430, 280, 60, 40));
    bAceptar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bAceptar_actionPerformed(e);
        }
      });
    tipo.setBounds(new Rectangle(20, 45, 255, 25));
    prioridad.setBounds(new Rectangle(345, 100, 125, 25));
    estado.setBounds(new Rectangle(490, 100, 125, 25));


    jPanel1.add(jLabel6, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jLabel7, null);
    ubicacion.setBounds(new Rectangle(20, 100, 255, 25));
    jLabel9.setBounds(new Rectangle(20, 75, 140, 25));
    jLabel9.setText("Ubicación Geográfica");
    jLabel8.setBounds(new Rectangle(490, 20, 60, 25));
    jLabel8.setText("Hora");
    hora.setBounds(new Rectangle(490, 45, 125, 25));
    jLabel5.setBounds(new Rectangle(20, 20, 35, 25));
    jLabel5.setText("Tipo");
    jLabel4.setBounds(new Rectangle(345, 20, 45, 25));
    jLabel4.setText("Fecha");
    fecha.setBounds(new Rectangle(345, 45, 125, 25));
    jPanel5.setLayout(null);
    jPanel5.setBorder(BorderFactory.createTitledBorder(" Datos del Reclamo "));
    jPanel5.setBounds(new Rectangle(5, 40, 630, 225));
    jPanel5.add(estado, null);
    jPanel5.add(prioridad, null);
    jPanel5.add(tipo, null);
    jPanel5.add(jLabel13, null);
    jScrollPane2.getViewport().add(descripcion, null);
    jPanel5.add(jScrollPane2, null);
    jPanel5.add(jLabel12, null);
    jPanel5.add(jLabel11, null);
    jPanel5.add(ubicacion, null);
    jPanel5.add(jLabel9, null);
    jPanel5.add(jLabel8, null);
    jPanel5.add(hora, null);
    jPanel5.add(jLabel5, null);
    jPanel5.add(jLabel4, null);
    jPanel5.add(fecha, null);
    jPanel5.add(animbtn, null);
    
    jPanel2.add(jPanel5, null);
    jPanel2.add(jLabel2, null);
    this.getContentPane().add(bAceptar, null);
    this.getContentPane().add(bCancelar, null);
    this.getContentPane().add(jPanel2, null);
    this.getContentPane().add(jPanel1, null);
    animbtn.setIcon(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/anims/Earth3.gif")).getImage().getScaledInstance(48,48,0)));

    tmp.setColumnCount(0);
    tmp.addColumn("Nro");
    tmp.addColumn("Fecha");
    tmp.addColumn("Descripción");
    tmp.addColumn("Estado");
    tmp.addRow(new Object[]{"918","18-12-2006","Luminaria averiada","Iniciado"});
    
    tipo.addItem("Luminarias");
    tipo.addItem("Bacheo");
    tipo.addItem("Microbasural");
    tipo.addItem("Espacios Verdes");

    prioridad.addItem("Alta");
    prioridad.addItem("Media");
    prioridad.addItem("Baja");

    estado.addItem("Iniciado");
    estado.addItem("En trámite");
    estado.addItem("Finalizado");
    
    fecha.setText(Proc.FechaHora(true));
    hora.setText(Proc.FechaHora(false));

  }

  private void bCancelar_actionPerformed(ActionEvent e)
  {
    this.dispose();  
  }

  private void bAceptar_actionPerformed(ActionEvent e)
  {
    reclamo = new Object[]{String.valueOf(nroReclamo),tipo.getSelectedItem(), fecha.getText(), hora.getText(), 
      ubicacion.getText(), prioridad.getSelectedItem(), estado.getSelectedItem(), descripcion.getText()};
    parent.addReclamo(reclamo);
    this.dispose();  
  }

  private void animbtn_actionPerformed(ActionEvent e)
  {
    mapa.verMapa(ubicacion);
  }

}