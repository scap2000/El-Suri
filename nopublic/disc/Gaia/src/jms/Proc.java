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
 * Proc.java
 *
 * */
package jms;
import java.util.Vector;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import javax.swing.JTree;
import javax.swing.tree.*;
import javax.swing.JSplitPane;
import java.util.Enumeration;
import javax.swing.JTextField;
import java.util.Timer;
import java.util.TimerTask;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Proc
{
/*  static String SQLDriver="com.mysql.jdbc.Driver";
  static String Database="jdbc:mysql://localhost/ccm";*/
  static String  SQLDriver="org.postgresql.Driver";
  static String Database="jdbc:postgresql://127.0.0.1/master";
  static String SQLUser="admin";
  static String SQLPass="0bra5publ1ca5";
  static String tipoconsulta,SelecCodBarrio,nomcentro,idcentro,nomcalle,idcalle,idbarrio,nombarrio,idpersona, idtema, tema, idreclamo, idpaso, sede, titulomenu,usuarioactual,sedeactual="2",organismo;
  static String separador = "";
  static Vector vectorcombo,contenidocombo;
  static JTextField jthora;
  
  public Proc()
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
  
  }


  static ResultSet exConsulta(Connection Conx, String Query) throws SQLException
  {
    Statement Stat = Conx.createStatement();
    ResultSet Resul1 = Stat.executeQuery(Query);
    return Resul1;
  }

  static Connection CCon() throws SQLException
  {
     try {Class.forName(SQLDriver);} catch (ClassNotFoundException x) {}
     try 
     {
       return DriverManager.getConnection(Database,SQLUser,SQLPass);       
     } catch (Exception x) 
     {
       Proc.Mensaje(x.getMessage(),"Error");       
       return DriverManager.getConnection(Database,SQLUser,SQLPass);       
     }
  }

  static boolean exActualizar(char ch, String Query)
  {
  //  try {Class.forName(SQLDriver);} catch (ClassNotFoundException x) {}
    boolean bol=true;
    String accion="";
    try 
    {
      Connection Conx=Proc.CCon(); 
      Statement Stat = Conx.createStatement();
      if (ch == 'a') 
      {
        accion = " insertar ";
        Stat.executeUpdate(Query);
      } else if (ch == 'b') 
      {
        int result = JOptionPane.showConfirmDialog((Component) null, "¿Está seguro que desea eliminar el registro?", "Eliminación", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION)  
        {
          accion = " eliminar ";        
          Stat.executeUpdate(Query);
        } else bol = false;
      } else if (ch == 'm') 
      {
        int result = JOptionPane.showConfirmDialog((Component) null, "¿Está seguro que desea guardar los cambios?", "Actualización",JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION)  
        {
          accion = " actualizar ";        
          Stat.executeUpdate(Query);
        } else bol=false;
      }
      Conx.close();
      return bol;
      
    } catch (SQLException x) 
    {
      String msg = "Ha ocurrido el siguiente error al" + accion + "el registro\n" + x.getMessage();
      JOptionPane.showMessageDialog((Component) null, msg, "Error",JOptionPane.OK_OPTION);
      System.out.println(msg);
      return false;
    }
    
  }

  static JTree CreaArbol(String tabla, String campoid, String campodescrip, String padre) throws Exception
  {
    Connection ConTmp=Proc.CCon();
    ResultSet Resul;
    Resul = exConsulta(ConTmp,"Select * from " + tabla +" where " + campoid + "=" + padre + " and estado<>'*' order by oid");
    Resul.next();
    DefaultMutableTreeNode arbol = new DefaultMutableTreeNode(Resul.getString(campoid) + " - " + Resul.getString(campodescrip));
    Resul = exConsulta(ConTmp,"Select * from " + tabla + " where padre=" + padre + " and estado<>'*' order by oid");
    CreaHijo(tabla,campoid,campodescrip,arbol,Resul);
    ConTmp.close();
    return new JTree(arbol);
  }

  static void CreaHijo(String tabla, String campoid, String campodescrip,DefaultMutableTreeNode padre, ResultSet rs) throws SQLException
  {
    while (rs.next()) 
    {
      DefaultMutableTreeNode hijos = new DefaultMutableTreeNode(rs.getString(campoid) + " - " + rs.getString(campodescrip));
      padre.add(hijos);
      Connection ConTmp=Proc.CCon();      
      ResultSet Resul = exConsulta(ConTmp,"Select * from " + tabla +" where estado<>'*' and padre=" + rs.getString(campoid) + " order by oid");
      CreaHijo(tabla,campoid,campodescrip,hijos,Resul);
      ConTmp.close();
    }
  }

  static Vector getNextRow(ResultSet rs,ResultSetMetaData rsmd)  //funcion que te devuelve un vector (registro)
  throws SQLException
  {
    Vector FilaActual = new Vector();
    try 
    {
      for (int i=1; i<=rsmd.getColumnCount(); ++i)
        switch (rsmd.getColumnType(i))
        {
          case Types.VARCHAR:
            FilaActual.addElement(rs.getString(i));
            break;
          case Types.CHAR:
            FilaActual.addElement(rs.getString(i));
            break;
          case Types.DATE:
            FilaActual.addElement(Fecha(rs.getString(i)));
            break;
          case Types.TIME:
            FilaActual.addElement(Hora(rs.getString(i)));
            break;
          case Types.LONGVARCHAR:
            FilaActual.addElement(rs.getString(i));
            break;
          case Types.INTEGER:
            FilaActual.addElement(new Long(rs.getLong(i)));
            break;
          case Types.DOUBLE:
            FilaActual.addElement(new Double(rs.getDouble(i)));
            break;
          case Types.DECIMAL:
            FilaActual.addElement(new Double(rs.getDouble(i)));
            break;
          default:
        } //end switch
    } catch (Exception x)
    {
      System.out.println(x.getMessage());
    }
    return FilaActual;  //vector resultante
  } 

  static String CantRegistro(String ConsultaCount,int CantReg,int Liminf, JButton siguiente, JButton anterior) throws SQLException
  {
      ResultSet Resul1;
      Connection Con1=Proc.CCon();
      String etiqueta;
      Resul1 = Proc.exConsulta(Con1,ConsultaCount);
      Resul1.next();
      int i = Resul1.getInt(1);
      if (Liminf < 0) Liminf = 0;
      if (i>CantReg) 
      {
        if (Liminf + CantReg < i)
        {
          siguiente.setEnabled(true);
        } else 
        {
          siguiente.setEnabled(false);
        }
        if (Liminf > 0) 
        {
          anterior.setEnabled(true);
        } else 
        {
          anterior.setEnabled(false);
        }
      } else 
      {
        anterior.setEnabled(false);
        siguiente.setEnabled(false);
      }
      if (Liminf + CantReg > i) 
      { 
        etiqueta = "  Registros: " + i + " coincidencias, mostrando de " + (Liminf + 1) + " a " + i;
      } else 
      {
        etiqueta = "  Registros: " + i + " coincidencias, mostrando de " + (Liminf + 1) + " a " + (Liminf + CantReg);      
      }
      return etiqueta;
  }


  static void RefreshTabla(String Tabla, String ConsultaTabla, int CantReg,int Liminf, DefaultTableModel jTableTmp,JTable jTabla,JScrollPane Panel) throws SQLException
  {
    String localSQLQuery = "Select columna from columnas where tabla='" + Tabla + "' order by idcolumna";
//    System.out.println(localSQLQuery);
    Connection ConTmp=Proc.CCon(); 
    ResultSet Resul = exConsulta(ConTmp,localSQLQuery);
    ResultSetMetaData ResulMD = Resul.getMetaData(); //ResulMD -- Obtiene la META-Informacion de la tabla que devuelve la consulta "SQLString"
    jTableTmp.setRowCount(0);
    jTableTmp.setColumnCount(0);
    while (Resul.next())  //mientras haya registros
    {
      jTableTmp.addColumn(getNextRow(Resul,ResulMD));
    } //end While
    ConTmp.close(); 
    ConTmp=Proc.CCon(); 
    Resul = exConsulta(ConTmp,ConsultaTabla);// + " LIMIT " + CantReg + " OFFSET " + Liminf); //Result1 -- Contiene la tabla que devuelve la consulta "SQLString"
    ResulMD = Resul.getMetaData(); //ResulMD -- Obtiene la META-Informacion de la tabla que devuelve la consulta "SQLString"
    while (Resul.next())  //mientras haya registros
    {
      jTableTmp.addRow(getNextRow(Resul,ResulMD));
    } //end While
    jTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jTabla.getTableHeader().setReorderingAllowed(false);
    jTabla.getTableHeader().setResizingAllowed(true);
    Panel.getViewport().remove(jTabla);
    Panel.getViewport().add(jTabla);
    ConTmp.close();
  }

  static String Max(String Tabla, String Campo, String Filtro) 
  {
    String maximo = "1";
    try 
    {
      Connection ConTmp=Proc.CCon();
      ResultSet Resulx = Proc.exConsulta(ConTmp,"Select max(" + Campo + ") + 1 from " + Tabla + Filtro);
      if (Resulx.next()) 
      {
        maximo = Resulx.getString(1);
        ConTmp.close();
        try 
        {
          if (maximo.equals(null))
          {
          }
        } catch (NullPointerException x) 
        {
          maximo="1";
        }
      }
    } catch (SQLException x) 
    {
    }
    return maximo;
  }

  static String getCampo(String SQLQuery) throws SQLException
  {
    Connection ConTmp=Proc.CCon();
    String campo ="";
    ResultSet Resul1 = exConsulta(ConTmp,SQLQuery);
    if (Resul1.next()) 
    {
      campo=Resul1.getString(1);
      ConTmp.close();
    }
    return campo;
  }

  static void CargaCombo(JComboBox combo, String ConsultaCombo, String filtro) throws SQLException 
  {
    Connection ConTmp=Proc.CCon();
    ResultSet Result1 = exConsulta(ConTmp,ConsultaCombo);
    combo.removeAllItems();
    int tamaniocombo =0;
    while (Result1.next())  //mientras haya registros
    {
      combo.addItem(Result1.getString(1));
      if (filtro.equals(Result1.getString(1))) combo.setSelectedIndex(tamaniocombo);
      tamaniocombo = tamaniocombo + 1;
    } //end While
    ConTmp.close();
  }
 
 static void RemueveCol(JTable jtabla, int[] vecindexcol)
 {
  //Los indices de cualquier tabla empiezan en 0, se debe especificar de atras hacia adelante
   for (int i=0; i<vecindexcol.length; ++i)
   {
//     System.out.println(vecindexcol[i]);
     jtabla.removeColumn(jtabla.getColumnModel().getColumn(vecindexcol[i]));     
   }
 }

  static String Fecha(String fecha) throws Exception
  {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = (Date)formatter.parse(fecha);
    String fecharet = formatter.format(date);
    formatter.applyPattern("dd/MM/yyyy");
    return formatter.format(date).toString();
  }
  
  static String Hora(String hora) throws Exception 
  {
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    Date date = (Date)formatter.parse(hora);
    String fecharet = formatter.format(date);
    formatter.applyPattern("HH:mm:ss");
    return formatter.format(date).toString();
  }

  static String FechaHora(boolean fecha) throws Exception //Si fecha es verdadero devuelve la fecha actual, si no devuelve la hora actual
  {
    String SQLQuery;
    if (fecha) 
    {
      return Fecha(getCampo("Select current_date as string"));
    } else 
    {
      return Hora(getCampo("Select current_time as string"));
    }
  }

  static void Mensaje(String mensaje, String titulo)
  {
     JOptionPane.showMessageDialog((Component) null, mensaje, titulo, JOptionPane.OK_OPTION);
//     System.out.println(mensaje);
  }

 static void ActivaBotones(JButton agregar, JButton modificar, JButton eliminar) 
 {
   if (tipoconsulta.equals("Consulta")) 
   {
     agregar.setEnabled(false);
     modificar.setEnabled(false);
     eliminar.setEnabled(false);
   } else if (tipoconsulta.equals("Carga de Datos")) 
   {
     agregar.setEnabled(true);
     modificar.setEnabled(false);
     eliminar.setEnabled(false);
     
   } else if (tipoconsulta.equals("Modificacion")) 
   {
     agregar.setEnabled(false);
     modificar.setEnabled(true);
     eliminar.setEnabled(false);
   } else if (tipoconsulta.equals("Eliminar")) 
   {
     agregar.setEnabled(false);
     modificar.setEnabled(false);
     eliminar.setEnabled(true);
   }
 }

  static void FechaHoraTpoReal(JTextField jtfecha, JTextField hora, Timer timer)
  {
      try
       {
          jthora = hora; 
          jtfecha.setText(Proc.FechaHora(true));
       } catch (Exception x)
       {
        System.out.println(x.getMessage());
       }
      
      int delay = 0;   // delay for 5 sec.
      int period = 1000;  // repeat every sec.

      timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
          try 
          {     
             jthora.setText(FechaHora(false));
          } catch (Exception x)
          {
             System.out.println(x.getMessage());
          }
        }
      }, delay, period);
  }
  
  static String getHost() 
  {
    try {
        InetAddress addr = InetAddress.getLocalHost();
        String hostname = addr.getHostName();
        return hostname + ".opsalta.ar";
        
    } catch (UnknownHostException e) {
        System.out.println(e.getMessage());
    }
    return "Error al obtener el nombre del host";
  }

 static void TamanioColumna(JTable tabla,int[] vecindextamcol)
 {
   tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
  //Los indices de cualquier tabla empiezan en 0, se debe especificar de atras hacia adelante
   for (int i=0; i < vecindextamcol.length; ++i)
   {
    TableColumn col = tabla.getColumnModel().getColumn(i);
    col.setPreferredWidth(vecindextamcol[i]);
   }
 }

 static void ExpandeSplit(JSplitPane jSP, boolean op, int tamanio)
 {
    jSP.getLeftComponent().setVisible(op);
    jSP.setDividerLocation(tamanio);
    jSP.setDividerSize(0);
 }
}
