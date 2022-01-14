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
 * SelectorFecha.java
 *
 * */
package org.digitall.projects.gdigitall.lib.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;
import java.util.Timer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class SelectorFecha extends JDialog 
{
  private SelectorFechaPanel calendar = new SelectorFechaPanel(Calendar.MONDAY,true,1940,2099, this);
  
  private JButton bseleccionar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/seleccionar.gif")));
  private JButton bcerrar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
  
  
  private JLabel jLabel2 = new JLabel();
  private JPanel jPanel1 = new JPanel();
  
  private JTextField jtfechaselec = new JTextField();
  
  //private JPanel jPanel2 = new JPanel();
  private String Consulta="",ConsultaCount="",cfiltro="";
  private Vector datos1,datosx = new Vector();
  private int[] vcol = {2};
  private int[] tcol={80,320};
  private String fechax="",mesx="",aniox="";
  static String fechaselec,lafecha = "";
  static String band = "";
  
  private Timer timer1 = new Timer();  
  
  private JTextField jtfecha = new JTextField();
  
  /**
   * FORMULARIO PARA LLEVAR UN CALENDARIO LABORAL, SE DEBERA CARGAR LOS DIAS QUE NO SON LABORALES.
   * YA SEA POR SABADO Y DOMINGO, FERIADOS U OTROS.
   */
  public SelectorFecha(JTextField _jtfecha)
  {
    jtfecha = _jtfecha;
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
    //this.setIconImage(Toolkit.getDefaultToolkit().getImage(Paso2.class.getResource("iconos/16x16/calendario.gif")));
    
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(353, 285));
    this.setTitle("Calendario");
    this.setBackground(new Color(112, 145, 204));
    calendar.setBounds(new Rectangle(10, 10, 320, 210));
    calendar.setBackground(Color.white);
    bseleccionar.setText("Seleccionar");
    bseleccionar.setBounds(new Rectangle(25, 360, 129, 25));
    bseleccionar.setMnemonic('s');
    bseleccionar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bseleccionar_actionPerformed(e);
        }
      });
    bcerrar.setText("Cerrar");
    bcerrar.setBounds(new Rectangle(240, 225, 92, 25));
    bcerrar.setMnemonic('c');    
    jPanel1.setBackground(new Color(112, 145, 204));
    jtfechaselec.setEditable(false);
    jtfechaselec.setDisabledTextColor(Color.red);
    jtfechaselec.setEnabled(false);
    jtfechaselec.setBackground(Color.white);
    calendar.initializeCalendar();
    this.getContentPane().add(jLabel2, null);
    jPanel1.add(jtfechaselec, null);
    this.getContentPane().add(jPanel1, null);
    this.getContentPane().add(bcerrar, null);
    this.getContentPane().add(bseleccionar, null);
    this.getContentPane().add(calendar, null);
    
    jtfechaselec.setText("00/00/0000");  //aca va 00/00/0000
    jLabel2.setForeground(Color.blue);
    jLabel2.setFont(new Font("Dialog", 1, 13));
    jLabel2.setOpaque(true);
    jPanel1.setLayout(null);
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
    jPanel1.setBounds(new Rectangle(20, 310, 325, 45));
    
    jtfechaselec.setForeground(Color.red);
    jtfechaselec.setFont(new Font("Dialog", 1, 15));
    jLabel2.setBounds(new Rectangle(30, 300, 138, 15));
    jLabel2.setText(" Fecha Seleccionada ");
    jtfechaselec.setBounds(new Rectangle(105, 10, 100, 25));
    ((JPanel)this.getContentPane()).setBackground(new Color(112, 145, 204));
   
    bcerrar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bcerrar_actionPerformed(e);
        }
      });
      
  }

  
  private void bcerrar_actionPerformed(ActionEvent e)
  {
    this.dispose();
  }
  
  /**
   * SELECCIONA UNA FECHA DETERMINADA Y LA DEVUELVE AL FORMULARIO QUE LA SOLICITO
   */
  private void bseleccionar_actionPerformed(ActionEvent e)
  {
    jtfecha.setText(jtfechaselec.getText());
    this.dispose();
  }

  public void setFechaX(String _fechax)
  {
    fechax = _fechax;
    try 
    {
      jtfecha.setText(OP_Proced.Fecha2(fechax,true));    
      dispose();
    } catch (Exception x) 
    {
      x.printStackTrace();
            OP_Proced.Mensaje("Error al asignar la fecha","Error");
    }

  }
}
