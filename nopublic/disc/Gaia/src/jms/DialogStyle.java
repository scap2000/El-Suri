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
 * DialogStyle.java
 *
 * */
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