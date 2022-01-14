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
 * Grilla.java
 *
 * */
package org.digitall.projects.gdigitall.lib.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.digitall.lib.components.buttons.BackGridButton;
import org.digitall.lib.components.buttons.FirstGridButton;
import org.digitall.lib.components.buttons.LastGridButton;
import org.digitall.lib.components.buttons.NextGridButton;
import org.digitall.projects.gdigitall.lib.components.Barra;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class Grilla extends JPanel implements ActionListener
{
  /*private JButton bfin = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/fin.gif")));
  private JButton bsgte = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/siguiente.gif")));
  private JButton banter = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/anterior.gif")));
  private JButton bini = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/inicio.gif")));*/
  private NextGridButton bsgte = new NextGridButton();
  private BackGridButton banter = new BackGridButton();
  private FirstGridButton bini = new FirstGridButton();
  private LastGridButton bfin = new LastGridButton();
  
  private JLabel etiqueta = new JLabel();
  private JPanel jPanel3 = new JPanel();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private DefaultTableModel modelo = new DefaultTableModel();
  private boolean primeraVez = true;
  private boolean sort=false;
  private Vector ColumnaCheck = new Vector();
  private Vector cabecera = new Vector();
  
  private JTable table = new JTable(modelo)
    {
      public boolean isCellEditable(int row, int col) {
          //Note that the data/cell address is constant,
          //no matter where the cell appears onscreen.
          if (ColumnaCheck.contains(new Integer(col)))
          {
            return true;
          } else {
            return false;
          }
      }
      
      public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
      }
    };

  private String esquema="",tabla="",consulta="",consultacount="",filtro="";
  private int CR;   // DEFINIR JPANELS DE 780 DE ANCHO
  private int Liminf=0,creg=0;
  protected int[] tcol, tamcol;
  private Vector datos = new Vector();
  private Component form;
  private JDialog frm = null;
  private boolean checks = false;
  private boolean boton = false;
  
  public Grilla(int CantRegistros, int[] TCol, int[] TamCol, boolean _checks, boolean _boton)
  {
    try
    {      
//      this.getHeight() = this.getHeight()anel;
//     tventana = 315;
      CR = CantRegistros;
      tcol = TCol;
      tamcol = TamCol;
      jbInit();
      checks = _checks;
      boton = _boton;
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
    etiqueta.setToolTipText(etiqueta.getText());
    etiqueta.setBounds(new Rectangle(5, 273, 535, 20));
    etiqueta.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    bini.setBounds(new Rectangle(545, 273, 55, 20));
    banter.setBounds(new Rectangle(605, 273, 45, 20));
    bsgte.setBounds(new Rectangle(650, 273, 45, 20));
    bfin.setBounds(new Rectangle(700, 273, 55, 20));
    jScrollPane1.setBounds(new Rectangle(5, 5, 750, 265));
    jScrollPane1.getViewport().add(table, null);
    jPanel3.add(jScrollPane1, null);
    jPanel3.add(etiqueta, null);
    jPanel3.add(bini, null);
    jPanel3.add(banter, null);
    jPanel3.add(bsgte, null);
    jPanel3.add(bfin, null);
    this.add(jPanel3, null);
    
    bini.addActionListener(this);
    bfin.addActionListener(this);
    bsgte.addActionListener(this);
    banter.addActionListener(this);
    
    table.setColumnSelectionAllowed(false);
    table.setRowSelectionAllowed(true);
    
    table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
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
      
    table.addMouseListener(new MouseAdapter()
      {
        public void mouseClicked(MouseEvent e)
        {
          if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1)
          {
            if (frm !=null) 
            {
                                               OP_Proced.CentraVentana(frm);
              frm.setModal(true);    
              frm.setVisible(true);
              ActualizaTabla(form,consulta,consultacount,cabecera);
              frm.dispose();
            } else 
            {
//              System.out.println("NO Entro");
            }
          } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON3) 
          {
            for (int i = 0; i<table.getColumnCount(); i++) 
            {
              System.out.println("Columna " + i + ": " + table.getColumnModel().getColumn(i).getWidth());
            }
          }
        }
      });

    JTableHeader header = table.getTableHeader();
    header.addMouseListener(new ColumnHeaderListener());
      
    bini.setEnabled(false);
    bfin.setEnabled(false);
    bsgte.setEnabled(false);
    banter.setEnabled(false);
  }

  public void setFRM(JDialog FRM)
  {
    frm = FRM;
  }
  
  public String getCount()
  {
    return OP_Proced.getCampo(consultacount);
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
      ResultSet Resul1 = OP_Proced.exConsulta(consultacount);
      if (Resul1.next())
      {
      creg = Resul1.getInt(1);
      if (Liminf < 0) Liminf = 0;
      if (creg > CR) 
      {
        if (Liminf + CR < creg)
        {
          bsgte.setEnabled(true);
          bfin.setEnabled(true);
        } else 
        {
          bsgte.setEnabled(false);
          bfin.setEnabled(false);
        }
        if (Liminf > 0) 
        {
          banter.setEnabled(true);
          bini.setEnabled(true);
        } else 
        {
          banter.setEnabled(false);
          bini.setEnabled(false);
        }
      } else 
      {
        banter.setEnabled(false);
        bsgte.setEnabled(false);
        bini.setEnabled(false);
        bfin.setEnabled(false);
      }
      if (creg == 0) 
      {
        etiqueta.setText("No se han encontrado coincidencias");
      } else 
      {
        if (Liminf + CR > creg) 
        { 
          etiqueta.setText(" Registros: " + creg + " coincidencias, mostrando desde " + (Liminf + 1) + " hasta " + creg);
        } else 
        {
          etiqueta.setText(" Registros: " + creg + " coincidencias, mostrando desde " + (Liminf + 1) + " hasta " + (Liminf + CR));      
        }
      }
      
    etiqueta.setToolTipText(etiqueta.getText());
    }
  }



///------------------
  public void ActualizaTabla(final Component Formulario,final String Consulta, final String ConsultaCount, final Vector _cabecera)
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
               if (!consulta.equals(Consulta))
               {
                 consulta = Consulta;
                 consultacount = ConsultaCount;
                 Liminf = 0;
               }
               OP_Proced.CargaGrilla(_cabecera, consulta + filtro, CR, Liminf, modelo, table, jScrollPane1, checks, boton);
               cabecera = _cabecera;
               if (primeraVez)
               {
                 RemueveCol();
                 TamanioColumna();
                 primeraVez = false;
               }
               CantRegistro();
               barra.disposeme();
             } catch (SQLException x)
             {
                OP_Proced.Mensaje(x.getMessage(), "Error de Consulta");
             } catch (Exception x)
             {
               System.out.println("ERROR de ActualizaTabla");
               //System.out.println(consulta);
               x.printStackTrace();
             } 
             
          }
        });
       barra.Inicia(Tarea);
                        OP_Proced.CentraVentana(barra);
       barra.setModal(true);
       barra.show();
    
       if (barra.getCancelar())
       {
          etiqueta.setText(" Registros: 0 coincidencias, Busqueda Cancelada...");         
          etiqueta.setToolTipText(etiqueta.getText());
       }
      }
    };
        SwingUtilities.invokeLater(query);
    //query.run();
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
    ActualizaTabla(form,consulta,consultacount, cabecera);
    } catch (Exception x)
    {
      System.out.println("daleee");
    }
  }

  public Vector VDatos()
  {
     return datos;
  }

  void bini_actionPerformed(ActionEvent e)
  {
  }

    // Regardless of sort order (ascending or descending), null values always appear last.
    // colIndex specifies a column in model.
    public void sortAllRowsBy(DefaultTableModel model, int colIndex, boolean ascending) {
        Vector data = model.getDataVector();
        Collections.sort(data, new ColumnSorter(colIndex, ascending));
        model.fireTableStructureChanged();
        RemueveCol();
        TamanioColumna();
    }
    
    // This comparator is used to sort vectors of data
    public class ColumnSorter implements Comparator {
        int colIndex;
        boolean ascending;
        ColumnSorter(int colIndex, boolean ascending) {
            this.colIndex = colIndex;
            this.ascending = ascending;
        }
        public int compare(Object a, Object b) {
            Vector v1 = (Vector)a;
            Vector v2 = (Vector)b;
            Object o1 = v1.get(colIndex);
            Object o2 = v2.get(colIndex);
    
            // Treat empty strains like nulls
            if (o1 instanceof String && ((String)o1).length() == 0) {
                o1 = null;
            }
            if (o2 instanceof String && ((String)o2).length() == 0) {
                o2 = null;
            }
    
            // Sort nulls so they appear last, regardless
            // of sort order
            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 == null) {
                return 1;
            } else if (o2 == null) {
                return -1;
            } else if (o1 instanceof Comparable) {
                if (ascending) {
                    return ((Comparable)o1).compareTo(o2);
                } else {
                    return ((Comparable)o2).compareTo(o1);
                }
            } else {
                if (ascending) {
                    return o1.toString().compareTo(o2.toString());
                } else {
                    return o2.toString().compareTo(o1.toString());
                }
            }
        }
    }  
    
    
    
    
    
    /***************************************/
    
    public class ColumnHeaderListener extends MouseAdapter {
        public void mouseClicked(MouseEvent evt) {
            JTable table = ((JTableHeader)evt.getSource()).getTable();
            TableColumnModel colModel = table.getColumnModel();

            // The index of the column whose header was clicked
            int vColIndex = colModel.getColumnIndexAtX(evt.getX());
            int mColIndex = table.convertColumnIndexToModel(vColIndex);
    
            // Return if not clicked on any column header
            if (vColIndex == -1) {
                return;
            }
    
            // Determine if mouse was clicked between column heads
            Rectangle headerRect = table.getTableHeader().getHeaderRect(vColIndex);
            if (vColIndex == 0) {
                headerRect.width -= 3;    // Hard-coded constant
            } else {
                headerRect.grow(-3, 0);   // Hard-coded constant
            }
            if (!headerRect.contains(evt.getX(), evt.getY())) {
                // Mouse was clicked between column heads
                // vColIndex is the column head closest to the click
    
                // vLeftColIndex is the column head to the left of the click
                int vLeftColIndex = vColIndex;
                if (evt.getX() < headerRect.x) {
                    vLeftColIndex--;
                }
            } else 
            {
              sort = !sort;
              sortAllRowsBy(modelo, mColIndex, sort);
            }
        }
    }    

  private void TamanioColumna()
  {
    try
    {
      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      for (int i=0; i < tamcol.length; ++i)
      {
          TableColumn col = table.getColumnModel().getColumn(i);
        if (tamcol[i] == 0) 
        {
          col.setResizable(false);
        }
        col.setPreferredWidth(tamcol[i]);
      }
      if (checks) 
      {
        table.getColumnModel().getColumn(table.getColumnCount()-1).setPreferredWidth(34);
        table.getColumnModel().getColumn(table.getColumnCount()-1).setResizable(false);
      }
    } catch (NullPointerException x)
    {
      //System.out.println("ERROR TamCol");
    }
  }

  public void RemueveCol()
  {
    try
    {
    //Los indices de cualquier tabla empiezan en 0, se debe especificar de atras hacia adelante
    //for (int i=0; i<tcol.length; ++i)
    for (int i=cabecera.size()-1; i>=0; i--)
     {
       //table.removeColumn(table.getColumnModel().getColumn(tcol[i]));
       if (cabecera.elementAt(i).equals("*")) 
       {
         table.removeColumn(table.getColumnModel().getColumn(i));
       }
     }
    } catch (NullPointerException x)
    {
      //x.printStackTrace();
    }
  }

  public JTable getTable()
  {
    return table;
  }
  
  public void setDragEnabled(boolean _dragEnabled) 
  {
    table.setDragEnabled(_dragEnabled);
  }
  
  public String getSelectedID() 
  {
    int[] rows = table.getSelectedRows();
    return table.getValueAt(rows[0], 0).toString();
  }
  
/** @deprecated
 * nunca funcionï¿½ pero estarï¿½ bueno poder implementarlo
 */
  public void setMultiSelect(boolean multi)
  {
    if (multi) 
    {
      table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    } else 
    {
      table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
  }
  
  public Vector getSelecteds()
  {
    Vector seleccionados = new Vector();
    for (int i=0; i<table.getRowCount(); i++)  
    {
      String selected = table.getValueAt(i,table.getColumnCount()-1).toString();
      if (selected.equalsIgnoreCase("true")) 
      {
        seleccionados.addElement(table.getValueAt(i,0));
//        return table.getValueAt(rows[0], 0).toString();
      }
    }
    return seleccionados;
  }

  public Vector getSelecteds(int _columna)
  {
    Vector seleccionados = new Vector();
    if (_columna < table.getColumnCount()) 
    {
      for (int i=0; i<table.getRowCount(); i++)  
      {
        String selected = table.getValueAt(i,table.getColumnCount()-1).toString();
        if (selected.equalsIgnoreCase("true")) 
        {
          seleccionados.addElement(table.getValueAt(i,_columna));
  //        return table.getValueAt(rows[0], 0).toString();
        }
      }
    } else 
    {
      System.out.println("La columna indicada supera la cantidad de columnas de la Grilla");
    }
    return seleccionados;
  }
  
  public void setEditorColumna(int _columna, JComboBox _combobox)
  {
    TableColumn column = table.getColumnModel().getColumn(_columna);
    column.setCellEditor(new DefaultCellEditor(_combobox));
    ColumnaCheck.addElement(new Integer(_columna));
  }
  
  public void setEditorColumna(int _columna, JTextField _textfield)
  {
    TableColumn column = table.getColumnModel().getColumn(_columna);
    column.setCellEditor(new DefaultCellEditor(_textfield));
    ColumnaCheck.addElement(new Integer(_columna));
  }
  
  public void setColumnaEditable(int _columna) 
  {
    ColumnaCheck.addElement(new Integer(_columna));
  }

  public void unsetColumnaEditable(int _columna) 
  {
    ColumnaCheck.removeElement(new Integer(_columna));
  }

  public void getRegistros()
  {
    for (int i = 0; i < table.getRowCount(); i++) 
    {
      for (int j = 0; j < table.getColumnCount(); j++) 
      {
        System.out.print(table.getValueAt(i,j).getClass() + ": " + table.getValueAt(i,j) + ", ");
      }
      System.out.println();
    }
  }
  
  public void addButtonColumn() 
  {
    ButtonColumn buttonColumn = new ButtonColumn(table, table.getModel().getColumnCount()-1);
  }

  class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener
  {
      JTable table;
      JButton renderButton;
      JButton editButton;
      String text;

      public ButtonColumn(JTable table, int column)
      {
          super();
          this.table = table;
          renderButton = new JButton();

          editButton = new JButton();
          editButton.setFocusPainted( false );
          editButton.addActionListener( this );

          TableColumnModel columnModel = table.getColumnModel();
          columnModel.getColumn(column).setCellRenderer( this );
          columnModel.getColumn(column).setCellEditor( this );
      }

      public Component getTableCellRendererComponent(
          JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
      {
          if (hasFocus)
          {
              renderButton.setForeground(table.getForeground());
              renderButton.setBackground(UIManager.getColor("Button.background"));
          }
          else if (isSelected)
          {
              renderButton.setForeground(table.getSelectionForeground());
               renderButton.setBackground(table.getSelectionBackground());
          }
          else
          {
              renderButton.setForeground(table.getForeground());
              renderButton.setBackground(UIManager.getColor("Button.background"));
          }

          renderButton.setText( (value == null) ? "" : value.toString() );
          renderButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));//.createEtchedBorder(EtchedBorder.RAISED));
          return renderButton;
      }

      public Component getTableCellEditorComponent(
          JTable table, Object value, boolean isSelected, int row, int column)
      {
          text = (value == null) ? "" : value.toString();
          editButton.setText( text );
          return editButton;
      }

      public Object getCellEditorValue()
      {
          return text;
      }

      public void actionPerformed(ActionEvent e)
      {
          fireEditingStopped();
          System.out.println( e.getActionCommand() + " : " + table.getSelectedRow());
      }
  }

  public void setPrimeraVez(boolean _primeravez) 
  {
    primeraVez = _primeravez;
  }
  
}

