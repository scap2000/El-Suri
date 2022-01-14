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
 * Barra.java
 *
 * */
package jms;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.DebugGraphics;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Barra extends JDialog 
{
  private JProgressBar jbarra = new JProgressBar();
  Component componente = null;
  private JLabel imagen2 = new JLabel();//new JLabel(new ImageIcon(Principal.class.getResource("iconos/rueda03.gif")));
  private JLabel jLabel1 = new JLabel();
  private JButton bcancelar = new JButton();//new JButton(new ImageIcon(Principal.class.getResource("iconos/16x16/cancelar.gif")));
  private Thread tarea;
  protected boolean cancelar = false;
  private JPanel jPanel1 = new JPanel();


/* )
  {
     try 
    {
      jbInit();
    } catch (Exception x)
    {
      x.printStackTrace();
    }
  }
  
  public void jbInit(*/  
  public Barra(int max, int current, Component jd) {
    componente = jd;
    componente.setEnabled(false);
    setMax(max);
    setCurrent(current);
    imagen2.setBounds(new Rectangle(13, 5, 40, 35));
    jLabel1.setText("Progreso...");
    jLabel1.setBounds(new Rectangle(10, 10, 450, 15));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
    bcancelar.setText("Cancelar");
    bcancelar.setBounds(new Rectangle(187, 80, 96, 25));
    bcancelar.setMargin(new Insets(2, 3, 2, 14));
    bcancelar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bcancelar_actionPerformed(e);
        }
      });
    jPanel1.setBounds(new Rectangle(10, 40, 450, 35));
    jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    jPanel1.setLayout(null);
    //x.setVisible(false);
    setSize(new Dimension(478, 153));
    getContentPane().setLayout(null);
    setTitle("Trabajando...");
    jbarra.setBounds(new Rectangle(5, 5, 440, 25));
    jbarra.setForeground(new Color(88, 146, 219));
    jbarra.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    jbarra.setBackground(Color.white);
    jbarra.setToolTipText("Trabajando....");
    jbarra.setFont(new Font("Dialog", 3, 13));
    jPanel1.add(jbarra, null);
    getContentPane().add(jPanel1, null);
    getContentPane().add(bcancelar, null);
    getContentPane().add(jLabel1, null);
    getContentPane().add(imagen2, null);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = getSize();
    setLocation((screenSize.width - frameSize.width)/2,(screenSize.height - frameSize.height)/2);
    
   // tarea.start();
  }
  
  public void disposeme() 
  {
    componente.setEnabled(true);
    //x.setVisible(true);
    this.dispose();
  }
  
  public void iniciar(Thread Tarea)
  {
    tarea = Tarea;
    tarea.start();
  }

  private void bcancelar_actionPerformed(ActionEvent e)
  {
    int result = JOptionPane.showConfirmDialog((Component) null, "¿Está seguro de cancelar la tarea actual?", "Cancelar", JOptionPane.YES_NO_OPTION);
    if (result == JOptionPane.YES_OPTION)  
    {
      tarea.stop();
      cancelar = true;
      disposeme();  
    }
  }
  
  public void setStatus(String status) 
  {
    jLabel1.setText(status);
  }
  
  public void setCurrent(int current) 
  {
    jbarra.setValue(current);
    jbarra.setString(porcentaje(current, jbarra.getMaximum()));
  }
  
  public void setMax(int max) 
  {
    if (max <= 0 ) max = 1;
    jbarra.setMaximum(max);
    if (max <= 1) indeterminar(true); else indeterminar(false);
  }
  
  private String porcentaje(int current, int max)
  {
    double actual = (double)current;
    double maximo = (double)max;
    double porcentaje = actual*100/maximo;
    DecimalFormat df = new DecimalFormat("0.00");
    return String.valueOf(df.format(porcentaje) + "%");
  }
  
  private void indeterminar(boolean indeterminate) 
  {
    if (indeterminate) 
    {
      jbarra.setStringPainted(false);
    } else 
    {
      jbarra.setStringPainted(true);
      setCurrent(0);
    }
    jbarra.setIndeterminate(indeterminate);
  }
}

