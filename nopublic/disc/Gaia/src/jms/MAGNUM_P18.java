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
 * MAGNUM_P18.java
 *
 * */
package jms;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MAGNUM_P18 extends JFrame 
{
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JPasswordField jPasswordField1 = new JPasswordField();
  private JPanel jPanel1 = new JPanel();
  private JPanel jPanel2 = new JPanel();
  private JLabel jLabel6 = new JLabel(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Logo_MAGNUM.png")));
  private JLabel jLabel7 = new JLabel(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Usuario.png")).getImage().getScaledInstance(48,48,0)));
  private JLabel jLabel8 = new JLabel(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Clave.png")).getImage().getScaledInstance(51,62,0)));
  private JButton bAceptar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Aceptar.png")).getImage().getScaledInstance(16,16,0)));
  private JButton bCancelar = new JButton(new ImageIcon(new ImageIcon(MAGNUM_DEMO.class.getResource("iconos/MAGNUM/Cancelar.png")).getImage().getScaledInstance(16,16,0)));

  public MAGNUM_P18()
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
    jLabel7.setSize(new Dimension(48, 48));
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(496, 307));
    this.setTitle("MAGNUM - Validación de Usuario");
    this.setBounds(new Rectangle(10, 10, 438, 307));
    jLabel1.setText("BIENVENIDO a MAGNUM");
    jLabel1.setBounds(new Rectangle(7, 20, 270, 15));
    jLabel1.setFont(new Font("Dialog", 1, 20));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel2.setText("Se requiere autenticar");
    jLabel2.setBounds(new Rectangle(20, 70, 240, 15));
    jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel2.setFont(new Font("Dialog", 1, 15));
    jLabel3.setText("sus datos para continuar");
    jLabel3.setBounds(new Rectangle(20, 90, 240, 15));
    jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel3.setFont(new Font("Dialog", 1, 15));
    jLabel4.setText("Usuario:");
    jLabel4.setBounds(new Rectangle(20, 145, 70, 20));
    jLabel4.setHorizontalAlignment(SwingConstants.LEFT);
    jLabel5.setText("Clave:");
    jLabel5.setBounds(new Rectangle(20, 210, 70, 20));
    jLabel5.setHorizontalAlignment(SwingConstants.LEFT);
    jTextField1.setBounds(new Rectangle(95, 145, 160, 20));
    jPasswordField1.setBounds(new Rectangle(95, 210, 160, 20));
    jPanel1.setBounds(new Rectangle(0, 0, 145, 275));
    jPanel1.setBackground(new Color(155, 155, 255));
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    jPanel1.setLayout(null);
    jPanel2.setBounds(new Rectangle(145, 0, 285, 275));
    jPanel2.setLayout(null);
    jLabel6.setBounds(new Rectangle(15, 5, 115, 120));

    jLabel7.setBounds(new Rectangle(48, 135, 48, 48));
    jLabel8.setBounds(new Rectangle(48, 195, 51, 62));
    jLabel8.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel8.setVerticalTextPosition(SwingConstants.BOTTOM);
    bAceptar.setBounds(new Rectangle(180, 245, 40, 25));
    bAceptar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bAceptar_actionPerformed(e);
        }
      });
    bCancelar.setBounds(new Rectangle(235, 245, 40, 25));
    bCancelar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bCancelar_actionPerformed(e);
        }
      });
    jPanel2.add(bAceptar, null);
    jPanel2.add(bCancelar, null);
    jPanel2.add(jLabel1, null);
    jPanel2.add(jLabel2, null);
    jPanel2.add(jLabel3, null);
    jPanel2.add(jTextField1, null);
    jPanel2.add(jLabel4, null);
    jPanel2.add(jLabel5, null);
    jPanel2.add(jPasswordField1, null);
    jPanel1.add(jLabel7, null);
    jPanel1.add(jLabel6, null);
    jPanel1.add(jLabel8, null);
    this.getContentPane().add(jPanel2, null);
    this.getContentPane().add(jPanel1, null);
  }

  private void bAceptar_actionPerformed(ActionEvent e)
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

  private void bCancelar_actionPerformed(ActionEvent e)
  {
    System.exit(0);
  }
}