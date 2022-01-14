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
 * CRegistros.java
 *
 * */
package ObrasPublicas2;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.security.Principal;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ProgressMonitor;

public class CRegistros extends JPanel implements ActionListener
{
  private JButton bfin = new JButton(new ImageIcon(CRegistros.class.getResource("iconos/16x16/fin.gif")));
  private JButton bsgte = new JButton(new ImageIcon(CRegistros.class.getResource("iconos/16x16/siguiente.gif")));
  private JButton banter = new JButton(new ImageIcon(CRegistros.class.getResource("iconos/16x16/anterior.gif")));
  private JButton bini = new JButton(new ImageIcon(CRegistros.class.getResource("iconos/16x16/inicio.gif")));
  private JLabel etiqueta = new JLabel();
  private JPanel jPanel3 = new JPanel();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private DefaultTableModel modelo = new DefaultTableModel();
  private JTable jTable1 = new JTable(modelo)
    {
      public boolean isCellEditable(int rowIndex, int vColIndex) 
      {
        return false;
      }
    };
 
  private String esquema="",tabla="",consulta="",consultacount="",filtro="";
  private int CR;   // DEFINIR JPANELS DE 780 DE ANCHO
  private int Liminf=0,creg=0;
  protected int[] tcol, tamcol;
  private Vector datos = new Vector();
  private Component form;
  private JDialog frm = null;    
  
  public CRegistros(int CantRegistros, String Esquema,String Tabla, int[] TCol, int[] TamCol )
  {
    try
    {      
//      this.getHeight() = this.getHeight()anel;
//     tventana = 315;
      CR = CantRegistros;
      esquema = Esquema;
      tabla = Tabla;
      tcol = TCol;
      tamcol = TamCol;
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
    this.setSize(new Dimension(760, 296));
    jPanel3.setBounds(new Rectangle(0, 0, 760, 295)); 
    jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    jPanel3.setLayout(null);
    etiqueta.setText(" Registros");
    etiqueta.setBounds(new Rectangle(5, 273, 535, 20));
    etiqueta.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    bini.setBounds(new Rectangle(545, 273, 55, 20));
    banter.setBounds(new Rectangle(605, 273, 45, 20));
    bsgte.setBounds(new Rectangle(650, 273, 45, 20));
    bfin.setBounds(new Rectangle(700, 273, 55, 20));
    jScrollPane1.setBounds(new Rectangle(5, 5, 750, 265));
    jScrollPane1.getViewport().add(jTable1, null);
    jPanel3.add(jScrollPane1, null);
    jPanel3.add(etiqueta, null);
    jPanel3.add(bini, null);
    jPanel3.add(banter, null);
    jPanel3.add(bsgte, null);
    jPanel3.add(bfin, null);
    this.add(jPanel3, null);
    bsgte.addActionListener(this);
    banter.addActionListener(this);
    bfin.addActionListener(this);
    bini.addActionListener(this);

  //  jTable1.setCellSelectionEnabled(false);
    
    jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener()
      {
        public void valueChanged(ListSelectionEvent e) 
        {
          ListSelectionModel tmodelo = (DefaultListSelectionModel)e.getSource();
          if (!tmodelo.isSelectionEmpty())
          {
            int fila = tmodelo.getMinSelectionIndex();  //indice del vector de vectores
            datos = (Vector)modelo.getDataVector().elementAt(fila);
          }
        }
      });
      
    jTable1.addMouseListener(new java.awt.event.MouseAdapter()
      {
        public void mouseClicked(MouseEvent e)
        {
          if (e.getClickCount() == 2)
          {
            if (!frm.equals(null)) 
            {
              System.out.println("Entro");
              Proced.CentraVentana(frm);
              frm.setModal(true);    
              frm.show();
            } else 
            {
              System.out.println("NO Entro");
            }
          }
        }
      });
      


  }

  public void setFRM(JDialog FRM)
  {
    frm = FRM;
  }
  
  public void setPanelDato(Object Panel)
  {
    //Panel.setDatos();
  }
  
  public void Redimensiona()
  {
    this.setSize(new Dimension(this.getWidth(), this.getHeight()));
    jPanel3.setBounds(new Rectangle(5, 10, this.getWidth() - 8, this.getHeight() - 15));
    jScrollPane1.setBounds(new Rectangle(5, 5, this.getWidth() - 18, this.getHeight() - 55));
    etiqueta.setBounds(new Rectangle(5,this.getHeight() - 42 , this.getWidth() - 233, 20));
    bini.setBounds(new Rectangle(this.getWidth() - 223, this.getHeight() - 45, 55, 25));
    banter.setBounds(new Rectangle(this.getWidth() - 163, this.getHeight() - 45, 45, 25));
    bsgte.setBounds(new Rectangle(this.getWidth() - 118, this.getHeight() - 45, 45, 25));
    bfin.setBounds(new Rectangle(this.getWidth() - 68, this.getHeight() - 45, 55, 25));
  }


  private void CantRegistro() throws SQLException
  {

      Connection Con1=Proced.CCon();
      ResultSet Resul1 = Proced.exConsulta(Con1,consultacount);
      Resul1.next();
      creg = Resul1.getInt(1);
      Con1.close();
      if (Liminf < 0) Liminf = 0;
      if (creg > CR) 
      {
        if (Liminf + CR < creg)
        {
          bsgte.setEnabled(true);
        } else 
        {
          bsgte.setEnabled(false);
        }
        if (Liminf > 0) 
        {
          banter.setEnabled(true);
        } else 
        {
          banter.setEnabled(false);
        }
      } else 
      {
        banter.setEnabled(false);
        bsgte.setEnabled(false);
      }
      if (Liminf + CR > creg) 
      { 
        etiqueta.setText(" Registros: " + creg + " coincidencias, mostrando de " + (Liminf + 1) + " a " + creg);
      } else 
      {
        etiqueta.setText(" Registros: " + creg + " coincidencias, mostrando de " + (Liminf + 1) + " a " + (Liminf + CR));      
      }
  }



///------------------
  public void ActualizaTabla(final Component Formulario,final String Consulta, final String ConsultaCount)
  {
    try
    {
    form = Formulario;
    Runnable query = new Runnable() 
    {
      public void run() 
      {
        final Barra barra = new Barra(form);
        Thread Tarea = new Thread(new Runnable() {
          public void run() 
          {
             try
             {
               consulta = Consulta;
               consultacount = ConsultaCount;
               Proced.RefreshTabla(esquema,tabla,consulta + filtro,CR,Liminf,modelo,jTable1,jScrollPane1);
               Proced.RemueveCol(jTable1,tcol);
               Proced.TamanioColumna(jTable1,tamcol);
               CantRegistro();
               barra.disposeme();
             } catch (SQLException x)
             {
               Proced.Mensaje(x.getMessage(),"Error de Consulta");
             } catch (Exception x)
             {
               System.out.println("ERROR de ActualizaTabla");
               x.printStackTrace();
             } 
             
          }
        });
       barra.Inicia(Tarea);
       Proced.CentraVentana(barra);
       barra.setModal(true);
       barra.show();
    
       if (barra.cancelar) 
       {
          etiqueta.setText(" Registros: 0 coincidencias, Busqueda Cancelada...");         
       }
      }
    };
    query.run();
    } catch (Exception x)
    {
     System.out.println("ERROR de ActualizaTabla");
     x.printStackTrace();
    }
                   

  }

//-------------------- /

  public void actionPerformed(ActionEvent e)
  {
    try
    {
    if (e.getSource() == bsgte)
    {
      Liminf=Liminf + CR;
    } else if (e.getSource() == banter)
    {
      Liminf=Liminf - CR;
    } else if (e.getSource() == bfin)
    {
      if (creg > CR)
      {
        Liminf=creg - CR;
       // System.out.println(Liminf);      
      }
    } else if (e.getSource() == bini)
    {
      Liminf=0;
    } 
    ActualizaTabla(form,consulta,consultacount);
    } catch (Exception x)
    {
      System.out.println("daleee");
    }
  }

  public Vector VDatos()
  {
     return datos;
  }

  public void LlenaV(JPanel jPanel)
  {
    System.out.println(jPanel.getComponentCount());
  }
}