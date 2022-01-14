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
 * StyleObjectPanel.java
 *
 * */
package jms;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

public class StyleObjectPanel extends JPanel 
{
  private JLabel labelsymbol = new JLabel();
  private JLabel labelmaxsize1 = new JLabel();
  private JLabel labeloutlinecolor = new JLabel();
  private JLabel labelmaxsize = new JLabel();
  private JLabel labelcolor = new JLabel();
  private JLabel labelbackgroundcolor = new JLabel();
  private JLabel labelwidth = new JLabel();
  private JLabel labelantialias = new JLabel();
  private JLabel labelminsize = new JLabel();
  private JLabel labelheight = new JLabel();

  private JComboBox symbol = new JComboBox();
  private JTextField size = new JTextField();
  private ColorSelectorPanel outlinecolor = new ColorSelectorPanel();
  private JTextField maxsize = new JTextField();
  private ColorSelectorPanel color = new ColorSelectorPanel();
  private ColorSelectorPanel backgroundcolor = new ColorSelectorPanel();
  private JTextField width = new JTextField();
  private JComboBox antialias = new JComboBox();
  private JTextField minsize = new JTextField();
  private JTextField height = new JTextField();

  private StyleObject style = new StyleObject();

  public StyleObjectPanel()
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
    this.setLayout(null);
    this.setSize(new Dimension(458, 417));
    this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    labelsymbol.setText("Símbolo:");
    labelsymbol.setBounds(new Rectangle(30, 390, 60, 20));
    labelmaxsize1.setText("Tamaño:");
    labelmaxsize1.setBounds(new Rectangle(50, 270, 60, 20));
    labeloutlinecolor.setText("Color de los contornos:");
    labeloutlinecolor.setBounds(new Rectangle(155, 195, 150, 15));
    labelmaxsize.setText("Tamaño Máximo:");
    labelmaxsize.setBounds(new Rectangle(150, 270, 110, 20));
    labelcolor.setText("Color de los dibujos:");
    labelcolor.setBounds(new Rectangle(165, 125, 130, 15));
    labelbackgroundcolor.setText("Color de los símbolos no transparentes:");
    labelbackgroundcolor.setBounds(new Rectangle(103, 55, 255, 15));
    labelwidth.setText("Offset Vertical:");
    labelwidth.setBounds(new Rectangle(55, 330, 105, 20));
    symbol.setBounds(new Rectangle(90, 390, 305, 20));
    size.setBounds(new Rectangle(25, 295, 105, 20));
    outlinecolor.setBounds(new Rectangle(10, 215, 440, 40));
    maxsize.setBounds(new Rectangle(155, 295, 105, 20));
    color.setBounds(new Rectangle(10, 145, 440, 40));
    backgroundcolor.setBounds(new Rectangle(10, 75, 440, 40));
    width.setBounds(new Rectangle(60, 355, 85, 20));
    labelantialias.setText("Antialias:");
    labelantialias.setBounds(new Rectangle(25, 20, 60, 20));
    antialias.setBounds(new Rectangle(90, 20, 305, 20));
    labelminsize.setText("Tamaño Minimo:");
    labelminsize.setBounds(new Rectangle(280, 270, 110, 20));
    minsize.setBounds(new Rectangle(285, 295, 105, 20));
    labelheight.setText("Offset Horizontal:");
    labelheight.setBounds(new Rectangle(190, 330, 120, 20));
    height.setBounds(new Rectangle(205, 355, 85, 20));
    symbol.addItem("0 - Sin símbolo");
    symbol.addItem("1 - Ángulo");
    symbol.addItem("2 - Estrella");
    symbol.addItem("3 - Triángulo");
    symbol.addItem("4 - Cuadrado");
    symbol.addItem("5 - \"+\"");
    symbol.addItem("6 - \"X\"");
    symbol.addItem("7 - Círculo");
    symbol.addItem("8 - \"_\"");
    symbol.addItem("9 - \" \"");
    symbol.addItem("10 - \"\\\"");
    symbol.addItem("11 - \"/\"");
    symbol.addItem("12 - \"X\"");
    symbol.addItem("13 - Círculo");
    antialias.addItem("");
    antialias.addItem("Sí");
    antialias.addItem("No");
    this.add(height, null);
    this.add(labelheight, null);
    this.add(minsize, null);
    this.add(labelminsize, null);
    this.add(antialias, null);
    this.add(labelantialias, null);
    this.add(backgroundcolor, null);
    this.add(color, null);
    this.add(outlinecolor, null);
    this.add(width, null);
    this.add(maxsize, null);
    this.add(size, null);
    this.add(symbol, null);
    this.add(labelwidth, null);
    this.add(labelbackgroundcolor, null);
    this.add(labelcolor, null);
    this.add(labelmaxsize, null);
    this.add(labeloutlinecolor, null);
    this.add(labelmaxsize1, null);
    this.add(labelsymbol, null);
  }


  private void setStyle(StyleObject s) 
  {
    if (antialias.getSelectedIndex() > 0) 
    {
      if (antialias.getSelectedIndex() == 1) 
      {
        s.setAntiAlias("TRUE");
      } else {
        s.setAntiAlias("FALSE");
      }
    } else 
    {
      s.setAntiAlias("");
    }
    s.setBackGroundColor(backgroundcolor.getColor());
    s.setColor(color.getColor());
    s.setOutLineColor(outlinecolor.getColor());
    s.setMaxSize(maxsize.getText());
    s.setMinSize(minsize.getText());
    s.setSize(size.getText());
    s.setSymbol(symbol.getSelectedIndex());
    s.setOffSet(width.getText(),height.getText());
  }
 
  public void actPanel() 
  {
    this.antialias.setSelectedItem(style.getAntiAlias());
    this.symbol.setSelectedItem(style.getSymbol());
    this.size.setText(style.getSize());
    this.outlinecolor.setColor(style.getOutLineColor());
    this.maxsize.setText(style.getMaxSize());
    this.minsize.setText(style.getMinSize());
    this.width.setText(String.valueOf(style.getOffSet().getWidth()));
    this.height.setText(String.valueOf(style.getOffSet().getHeight()));
    this.color.setColor(style.getColor());
    this.backgroundcolor.setColor(style.getBackGroundColor());
  }
  
  public void setStyleSource(StyleObject s) 
  {
    this.style = s;
    actPanel();
  }

  public StyleObject getStyle() //GRABA Y DEVUELVE
  {
    try 
    {
      setStyle(style);
      return style;
    } catch (NullPointerException x) 
    {
      this.style = new StyleObject();
      setStyle(style);
      return style;
    }
  }  

}