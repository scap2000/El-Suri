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
 * Personas.java
 *
 * */
package org.digitall.projects.gdigitall.expedientes;

import java.awt.Frame;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.Vector;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.digitall.projects.gdigitall.lib.components.CRegistros;
import org.digitall.projects.gdigitall.lib.components.JCombo;
import org.digitall.projects.gdigitall.lib.components.JEntry;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class Personas extends JDialog implements ActionListener, KeyListener
{

 // private JPanel jPanel1 = new JPanel();
//  private JPanel jPanel2 = new JPanel();
  private JButton bmodificar = new JButton();
  private JButton beliminar = new JButton();
  private JButton bcerrar = new JButton();
  private JButton bnuevo = new JButton();
  
  private int Liminf = 0, CantReg=30;
  private Vector datos1,datosx = new Vector();
  //private String idpedido="",cfiltro1="",cfiltro2="";
  private boolean nuevo;
  private Timer timer1 = new Timer();  
  private Timer timer2 = new Timer();  
  private int[] vc2 = {14,13,12,10,9,8,7,1};
  private int[] tc2={70,50,90,150,150,150,100};
  private String ConsultaCount="",Consulta="",idtipo="0",cfiltro="";
  static String idpersona="0";
  private CRegistros jPanel2 = new CRegistros(CantReg,"","personas",vc2,tc2);
  private JPanel jPanel3 = new JPanel();
  private JPanel jPanel4 = new JPanel();
  private JEntry jtbapellido = new JEntry();
  private JLabel jLabel9 = new JLabel();
  private JEntry jtidpersona = new JEntry();
  private JLabel jLabel18 = new JLabel();
  private JButton bcargar = new JButton();
  private JCombo jctipo = new JCombo();
  //private JEntry jtnro1 = new JEntry();
  private JEntry jtnro = new JEntry();
  private JLabel jLabel19 = new JLabel();
  private JEntry jtapellido = new JEntry();
  private JLabel jLabel110 = new JLabel();
  private JEntry jtnombre = new JEntry();
  private JLabel jLabel111 = new JLabel();
  private JLabel jLabel112 = new JLabel();
  private JButton bseleccionar = new JButton();
  private JCheckBox jchektodos = new JCheckBox();
  private JEntry jtnum_calle = new JEntry();
  private JLabel jLabel16 = new JLabel();
  private JCombo jccalle = new JCombo();
  private JLabel jLabel113 = new JLabel();
  private JEntry jtcalle = new JEntry();
  private JLabel jLabel10 = new JLabel();
  private JPanel jPanel1 = new JPanel();
  private JTabbedPane jTabbedPane1 = new JTabbedPane();
  private JLabel jLabel1 = new JLabel();
  private JTextField jtdomadicional = new JTextField();
  private JTextField jtemail = new JTextField();
  private JTextField jttel = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel11 = new JLabel();
  private JLabel jLabel12 = new JLabel();
  private JEntry jtbnombre = new JEntry();
  private JLabel jLabel13 = new JLabel();
  private JEntry jtbnro = new JEntry();
  private JLabel jLabel4 = new JLabel();
  private JTextField jtalias = new JTextField();

  public Personas() {
        try {
            jbInit();
        } catch (SQLException e) {
            OP_Proced.Mensaje(e.getMessage().toUpperCase(), "Acceso a la Base de Datos no Autorizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  private void jbInit() throws Exception  {
    this.setSize(new Dimension(800, 570));
    this.getContentPane().setLayout(null);
    this.setTitle("Listado General de Personas");
    jPanel2.setBounds(new Rectangle(5, 80, 775, 265));
    jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    jPanel2.setLayout(null);
    bmodificar.setText("Modificar");
    bmodificar.setBounds(new Rectangle(421, 510, 92, 25));
    bmodificar.setMnemonic('m');
    beliminar.setText("Eliminar");
    beliminar.setBounds(new Rectangle(522, 510, 85, 25));
    beliminar.setMnemonic('e');
    bcerrar.setText("Cerrar");
    bcerrar.setBounds(new Rectangle(708, 510, 72, 25));
    bcerrar.setMnemonic('c');
    bnuevo.setText("Nuevo");
    bnuevo.setBounds(new Rectangle(340, 510, 73, 25));
    bnuevo.setMnemonic('n');
    jPanel3.add(jtalias, null);
    jPanel3.add(jLabel4, null);
    jPanel3.add(jLabel3, null);
    jPanel3.add(jLabel2, null);
    jPanel3.add(jttel, null);
    jPanel3.add(jtemail, null);
    jPanel3.add(jLabel112, null);
    jPanel3.add(jLabel111, null);
    jPanel3.add(jtnombre, null);
    jPanel3.add(jLabel110, null);
    jPanel3.add(jtapellido, null);
    jPanel3.add(jLabel19, null);
    jPanel3.add(jtnro, null);
    jPanel3.add(jctipo, null);
    jPanel3.add(jLabel18, null);
    jPanel3.add(jtidpersona, null);
    jPanel4.add(jtbnro, null);
    jPanel4.add(jLabel13, null);
    jPanel4.add(jtbnombre, null);
    jPanel4.add(jLabel12, null);
    jPanel4.add(jLabel11, null);
    jPanel4.add(jchektodos, null);
    jPanel4.add(jtbapellido, null);
    jPanel1.add(jtdomadicional, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jLabel10, null);
    jPanel1.add(jLabel113, null);
    jPanel1.add(jccalle, null);
    jPanel1.add(jLabel16, null);
    jPanel1.add(jtnum_calle, null);
    jPanel1.add(jtcalle, null);
    jTabbedPane1.addTab("Datos Principales", jPanel3);
    jTabbedPane1.addTab("Datos del Domicilio", jPanel1);
    this.getContentPane().add(jTabbedPane1, null);
    this.getContentPane().add(bseleccionar, null);
    this.getContentPane().add(bcargar, null);
    this.getContentPane().add(bnuevo, null);
    this.getContentPane().add(bcerrar, null);
    this.getContentPane().add(beliminar, null);
    this.getContentPane().add(bmodificar, null);
    this.getContentPane().add(jPanel2, null);
    this.getContentPane().add(jLabel9, null);
    this.getContentPane().add(jPanel4, null);
    jPanel3.setLayout(null);
    jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    bnuevo.addActionListener(this);
    bcerrar.addActionListener(this);
    bmodificar.addActionListener(this);
    beliminar.addActionListener(this);
    jLabel9.setBounds(new Rectangle(10, 0, 105, 15));
    jLabel9.setText("Buscar Personas:");
    jtbapellido.setBounds(new Rectangle(20, 30, 135, 20));
    jPanel4.setLayout(null);
    jPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    jPanel4.setBounds(new Rectangle(5, 10, 775, 60));
    jPanel2.Redimensiona();    
    bcargar.setMnemonic('a');
    bcargar.setBounds(new Rectangle(615, 510, 85, 25));
    bcargar.setText("Cargar");
    jLabel18.setText("ID:");
    jLabel18.setBounds(new Rectangle(15, 15, 17, 15));
    jtidpersona.setBounds(new Rectangle(15, 30, 70, 20));
    jtbapellido.addKeyListener(this);
    datos1 = jPanel2.VDatos(); 
    jtalias.setBounds(new Rectangle(640, 85, 120, 20));
    jtalias.setEditable(false);
    jLabel4.setText("Usuario/Red:");
    jLabel4.setBounds(new Rectangle(640, 70, 81, 15));
    jtbnro.setBounds(new Rectangle(420, 30, 165, 20));
    jLabel13.setBounds(new Rectangle(420, 15, 90, 15));
    jLabel13.setText("NºDocumento:");
    jLabel13.setOpaque(true);
    jtbnombre.setBounds(new Rectangle(210, 30, 135, 20));
    jLabel12.setBounds(new Rectangle(210, 15, 52, 15));
    jLabel12.setText("Nombre:");
    jLabel12.setOpaque(true);
    jLabel11.setOpaque(true);
    jLabel11.setText("Apellido:");
    jLabel11.setBounds(new Rectangle(20, 15, 55, 15));
    jLabel9.setOpaque(true);
    jttel.setEditable(false);
    jtemail.setEditable(false);
    jLabel3.setBounds(new Rectangle(400, 70, 72, 15));
    jLabel3.setText("Telefono/s:");
    jLabel2.setBounds(new Rectangle(15, 70, 44, 15));
    jLabel2.setText("E-Mail:");
    jttel.setBounds(new Rectangle(400, 85, 160, 20));
    jtemail.setBounds(new Rectangle(15, 85, 320, 20));
    jtdomadicional.setEditable(false);
    jtdomadicional.setBounds(new Rectangle(140, 65, 450, 20));
    jLabel1.setBounds(new Rectangle(10, 70, 125, 15));
    jLabel1.setText("Domicilio Adicional:");
    jTabbedPane1.setBounds(new Rectangle(5, 355, 775, 145));
    jPanel1.setLayout(null);
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    jLabel10.setText("Buscar Calle:");
    jLabel10.setBounds(new Rectangle(10, 23, 80, 15));
    jtcalle.setEditable(false);
    jtcalle.setBounds(new Rectangle(90, 20, 215, 20));
    jLabel113.setBounds(new Rectangle(340, 23, 35, 15));
    jLabel113.setText("Calle:");   
    jccalle.setBounds(new Rectangle(380, 20, 365, 20));
    jLabel16.setBounds(new Rectangle(645, 70, 20, 15));
    jLabel16.setText("Nº:");
    jtnum_calle.setBounds(new Rectangle(670, 65, 80, 20));
    jtnum_calle.setEditable(false);
    jchektodos.setMnemonic('t');
    jchektodos.setBounds(new Rectangle(670, 15, 95, 25));
    jchektodos.setText("Ver Todos");
    jctipo.setEnabled(false);
    jtidpersona.setEditable(false);
    jtnro.setEditable(false);
    jtapellido.setEditable(false);
    jtnombre.setEditable(false);
    bseleccionar.setMnemonic('s');
    bseleccionar.setBounds(new Rectangle(5, 510, 103, 25));
    bseleccionar.setText("Seleccionar");
    jLabel112.setText("Tipo:");
    jLabel112.setBounds(new Rectangle(135, 15, 31, 15));
    jLabel111.setBounds(new Rectangle(575, 15, 52, 15));
    jLabel111.setText("Nombre:");
    jtnombre.setBounds(new Rectangle(575, 30, 185, 20));
    jLabel110.setBounds(new Rectangle(400, 15, 55, 15));
    jLabel110.setText("Apellido:");
    jtapellido.setBounds(new Rectangle(400, 30, 160, 20));
    jLabel19.setBounds(new Rectangle(215, 15, 28, 15));
    jLabel19.setText("Nro.:");
    jtnro.setBounds(new Rectangle(215, 30, 140, 20));    
    //jtnro1.setBounds(new Rectangle(650, 25, 100, 20));
    bseleccionar.addActionListener(this);
    jchektodos.addActionListener(this);
    bcargar.addActionListener(this);
    jtcalle.addKeyListener(this);
    jtbnombre.addKeyListener(this);
    jtbnro.addKeyListener(this);
    
    jccalle.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    try {
                        jtcalle.setText(OP_Proced.getCampo("SELECT idcalle FROM calles WHERE nombre ='" + jccalle.getItemTexto() + "'"));
                    } catch (Exception x) {
                        //Proced.Mensaje(x.getMessage(), "ERROR:");
                    }
                }
            }
        );

    
    jctipo.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    try {
                        //System.out.println("Select idtipo from tiposid where descripcion='" + jctipo.getItemTexto().toString() + "'");
                        idtipo = OP_Proced.getCampo("SELECT idtipo FROM tiposid WHERE descripcion='" + jctipo.getItemTexto() + "'");
                    } catch (Exception x) {
                        //Proced.Mensaje(x.getMessage(), "ERROR: ");
                    }
                }
            }
        );

    jctipo.setBounds(new Rectangle(135, 30, 70, 20));
    bcargar.setEnabled(false);
    timer2.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    datosx = jPanel2.VDatos();
                    if (!datos1.equals(datosx)) {
                        datos1 = datosx;
                        System.out.println(datos1);
                        jtidpersona.setText(datos1.elementAt(0).toString());
                        OP_Proced.CargaCombo(jctipo, "SELECT descripcion FROM tiposid WHERE estado<>'*'", datos1.elementAt(2).toString());
                        jtnro.setText(datos1.elementAt(3).toString());
                        jtapellido.setText(datos1.elementAt(4).toString());
                        jtnombre.setText(datos1.elementAt(5).toString());
                        jtemail.setText(datos1.elementAt(6).toString());
                        jtcalle.setText(datos1.elementAt(7).toString());
                        OP_Proced.CargaCombo(jccalle, "SELECT nombre FROM calles WHERE idcalle=" + datos1.elementAt(7).toString(), "");
                        jtnum_calle.setText(datos1.elementAt(9).toString());
                        jtdomadicional.setText(datos1.elementAt(10).toString());
                        jttel.setText(datos1.elementAt(11).toString());
                    }
                } catch (Exception x) {
                    x.printStackTrace();
                    //Proced.Mensaje(x.getMessage(),"ERROR 01:");
                }
            }
        }
        , 0, 500);
      
     jchektodos.setSelected(true);
     ActualizaTabla();
  }
  
  public void LimpiaCampo() {
        jtidpersona.setText("");
        jctipo.removeAllItems();
        jtnro.setText("");
        jtapellido.setText("");
        jtnombre.setText("");
        jtcalle.setText("0");
        jccalle.removeAllItems();
        jtnum_calle.setText("");
        jtdomadicional.setText("");
        jttel.setText("");
        jtemail.setText("");
        jtalias.setText("");
    }

    public void ActivaComp(boolean op1) {
        jctipo.setEnabled(op1);
        jtnro.setEditable(op1);
        jtapellido.setEditable(op1);
        jtnombre.setEditable(op1);
        bcargar.setEnabled(op1);
        jtcalle.setEditable(op1);
        jtnum_calle.setEditable(op1);
        jtdomadicional.setEditable(op1);
        jttel.setEditable(op1);
        jtemail.setEditable(op1);
        jtalias.setEditable(op1);
    }

    public void ActualizaTabla() {
        Consulta = "SELECT idpersona,tipoid,tiposid.descripcion,dni,apellido,personas.nombre,email,idcuadra," 
                + "calles.nombre,nro,domadicional,telefono,alias,clave,personas.estado" 
                + " FROM personas,calles,tiposid" 
                + " WHERE personas.idcuadra=calles.idcalle" 
                + " AND personas.tipoid=tiposid.idtipo " + cfiltro;
        System.out.println(Consulta);
        ConsultaCount = "SELECT COUNT(*) FROM personas WHERE estado <> '*' " + cfiltro;
        jPanel2.ActualizaTabla(this, Consulta, ConsultaCount);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == bcerrar) {
                this.dispose();
            } else if (e.getSource() == bnuevo) {
                ActivaComp(true);
                LimpiaCampo();
                jtidpersona.setText(OP_Proced.Max("personas", "idpersona", ""));
                OP_Proced.CargaCombo(jctipo, "SELECT descripcion FROM tiposid WHERE estado <> '*'", "S/N");
                nuevo = true;
            } else if (e.getSource() == bmodificar) {
                ActivaComp(true);
                nuevo = false;
            } else if (e.getSource() == bcargar) {
                if (nuevo) {
                    String Q = "INSERT INTO personas values(" + jtidpersona.getTexto() + ",'" + jtnro.getTexto() + "'," + idtipo 
                                                            + ",'" + jtapellido.getTexto().toUpperCase() + "','" + jtnombre.getTexto() 
                                                            + "','" + jtemail.getText() + "'," + jtcalle.getTexto() + ",'" + jtnum_calle.getTexto() 
                                                            + "','" + jtdomadicional.getText() + "','" + jttel.getText() + "','" + jtalias.getText() + "','','')";
                    OP_Proced.exActualizar('a', Q);
                } else {
                    String Q = "UPDATE personas SET tipoid=" + idtipo + ", apellido='" + jtapellido.getTexto().toUpperCase() + "', nombre='" + jtnombre.getTexto() + "', dni='" + jtnro.getTexto() + "', idcuadra=" + jtcalle.getTexto() + ", nro='" + jtnum_calle.getTexto() + "',domadicional='" + jtdomadicional.getText() + "', email='" + jtemail.getText() + "', telefono='" + jttel.getText() + "', alias='" + jtalias.getText() + "' WHERE idpersona=" + datos1.elementAt(0).toString();
                    OP_Proced.exActualizar('m', Q);
                }
                ActivaComp(false);
                LimpiaCampo();
                ActualizaTabla();
            } else if (e.getSource() == beliminar) {
                if (!datos1.isEmpty()) {
                    String Q = "UPDATE personas SET estado='*' WHERE idpersona=" + datos1.elementAt(0).toString();
                    //System.out.println(Q);
                    OP_Proced.exActualizar('b', Q);
                    //      cfiltro=" ";      
                    ActualizaTabla();
                }
            } else if (e.getSource() == bseleccionar) {
                idpersona = datos1.elementAt(0).toString();
                this.dispose();
            } else if (e.getSource() == jchektodos) {
                if (jchektodos.isSelected()) {
                    cfiltro = "";
                    ActualizaTabla();
                }
            }
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "ERROR... ");
        }
    }

    public void keyReleased(KeyEvent k) {
    }

    public void keyTyped(KeyEvent k) {
    }

    public void keyPressed(KeyEvent k) {
        /*try {*/
        if (k.getKeyCode() == KeyEvent.VK_ENTER) {
            if (k.getSource() == jtbapellido | k.getSource() == jtbnombre | k.getSource() == jtbnro) {
                Buscar();
            } else if (k.getSource() == jtcalle) {
                int idcalle = 99999;
                try {
                    idcalle = Integer.parseInt(jtcalle.getTexto().toString());
                } catch (NumberFormatException n) {
                    //System.out.println(n.getMessage());           
                }
                OP_Proced.CargaCombo(jccalle, "SELECT nombre FROM calles WHERE estado<>'*'" + " AND idcalle=" + idcalle + " OR upper(nombre) LIKE upper('%" + jtcalle.getTexto() + "%') order by nombre", "");
            }
        }
        /*} catch (SQLException x)    {
      System.out.println(x.getMessage());
    }*/
    }

    private void Buscar() {
        String SQLApellido, SQLNombre, SQLNro;
        if (jtbapellido.getText().length() > 0) {
            SQLApellido = " AND UPPER(apellido) LIKE UPPER('%" + jtbapellido.getTexto() + "%')";
        } else {
            SQLApellido = "";
        }
        if (jtbnombre.getText().length() > 0) {
            SQLNombre = " AND UPPER(personas.nombre) LIKE UPPER('%" + jtbnombre.getTexto() + "%')";
        } else {
            SQLNombre = "";
        }
        if (jtbnro.getText().length() > 0) {
            SQLNro = " AND nro='" + jtbnro.getTexto() + "'";
        } else {
            SQLNro = "";
        }
        cfiltro = SQLApellido + SQLNombre + SQLNro;
        ActualizaTabla();
    }
}