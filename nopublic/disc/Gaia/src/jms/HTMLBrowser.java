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
 * HTMLBrowser.java
 *
 * */
package jms;
import java.util.EmptyStackException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.net.URL;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HTMLBrowser extends JDialog
{
  private PrintableEditorPane htmled;
  private JScrollPane htmlpanel = new JScrollPane();
  private JPanel panelCenter = new JPanel();
  private BorderLayout layoutMain = new BorderLayout();
  private JButton bimprimir = new JButton();//new ImageIcon(HTMLBrowser.class.getResource("iconos/16x16/imprimir.gif")));
  private JButton bcerrar = new JButton();//new ImageIcon(HTMLBrowser.class.getResource("iconos/16x16/cerrar.gif")));
  private String command="",Archivo="";
  private JButton bvisorexterno = new JButton();//new ImageIcon(HTMLBrowser.class.getResource("iconos/16x16/visor_externo.gif")));
  
  public HTMLBrowser(String file)
  {
    try
    {
      Archivo=file;
//      System.out.println("HTMLBrowser: " + Archivo);
      jbInit();
    } catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception
  {
//    htmled = new PrintableEditorPane(new URL("http","192.168.2.1",""));
    htmled = new PrintableEditorPane(new URL("file","",Archivo));    
    htmled.setEditable(false);
    this.setSize(new Dimension(800, 575));
    this.getContentPane().setLayout(null);
    this.setTitle("Visor de Informes");
    htmled.setText("");
    htmlpanel.setBounds(new Rectangle(5, 5, 780, 500));
    bimprimir.setText("Imprimir");
    bimprimir.setBounds(new Rectangle(435, 515, 108, 25));
    bimprimir.setMnemonic('I');  
    bcerrar.setText("Cerrar");
    bcerrar.setBounds(new Rectangle(693, 515, 92, 25));
    bcerrar.setMnemonic('C');
    bvisorexterno.setText("Visor Externo");
    bvisorexterno.setBounds(new Rectangle(550, 515, 137, 25));
    bvisorexterno.setMnemonic('V');
    htmlpanel.getViewport().add(htmled, null);
    this.getContentPane().add(bvisorexterno, null);
    this.getContentPane().add(bcerrar, null);
    this.getContentPane().add(bimprimir, null);
    this.getContentPane().add(htmlpanel, null);
    
    htmled.addHyperlinkListener(new HyperlinkListener() {
    public void hyperlinkUpdate(HyperlinkEvent link)
      {
        if (link.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
        {
          try
          {
            htmled.setPage(link.getURL());
          } catch (EmptyStackException e)
    {
     System.out.println("ErrorSTack01"); 
    } catch (IOException x)
          {
            Proc.Mensaje("Error: no se pudo abrir el archivo " +
              link.getURL().toExternalForm() + "\n" + x.getMessage(), "true");
          } 
        }
      }
    });
    
    bimprimir.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bimprimir_actionPerformed(e);
        }
      });
      
    bcerrar.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bcerrar_actionPerformed(e);
        }
      });
      
    bvisorexterno.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          bvisorexterno_actionPerformed(e);
        }
      });
    
    
  //  panelCenter.add(htmlpanel, null);
  //  this.getContentPane().add(panelCenter, BorderLayout.NORTH);

  }

  private void benviar_actionPerformed(ActionEvent e)
  {
  
    this.dispose();
  }
  
  private void bcerrar_actionPerformed(ActionEvent e)
  {
    this.dispose();
  }

  private void bimprimir_actionPerformed(ActionEvent e)
  {
    htmled.doPrintActions();
  }

  private void bvisorexterno_actionPerformed(ActionEvent e)
  {
    try 
    {
      if (System.getProperty("os.name").equals("Linux"))
      {
        command = "konqueror " + Archivo;
      }else 
      {
        command = "c:"+ Proc.separador +"archivos de programa"+ Proc.separador +"internet explorer"+ Proc.separador +"iexplore " + Archivo;
      }
//      System.out.println(command);
      Process child = Runtime.getRuntime().exec(command);
    } catch (EmptyStackException x)
    {
     System.out.println("ErrorSTack02"); 
    } catch (IOException x) 
    {
      Proc.Mensaje("Error al intentar utilizar el visor externo","true");      
    } 
  }
  
}