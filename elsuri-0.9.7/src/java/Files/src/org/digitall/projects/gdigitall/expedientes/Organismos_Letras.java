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
 * Organismos_Letras.java
 *
 * */
package org.digitall.projects.gdigitall.expedientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.projects.gdigitall.lib.components.CRegistros;
import org.digitall.projects.gdigitall.lib.components.CargaCampo;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

//public class Organismos_Letras extends JDialog implements ActionListener, KeyListener {
public class Organismos_Letras extends BasicPrimitivePanel implements ActionListener, KeyListener {
    private JPanel jPanel2 = new JPanel();
    private JButton bcerrar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    private JButton bseleccionar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/seleccionar.gif")));
    private JLabel buscar = new JLabel();
    private JLabel org = new JLabel();
    private JTextField jtpalabra = new JTextField();
    private JComboBox jcorganismo = new JComboBox();
    private Vector datos1, datosx = new Vector();
    private Timer timer = new Timer();
    private String Consulta = "", ConsultaCount = "", cfiltro = "", tabla = "";
    static String idorganismo = "", idtipoletra = "";
    private int[] vcol1 = { 2 };
    private int[] tcol1 = { 110, 500 };
    private int[] vcol2 = { 4 };
    private int[] tcol2 = { 70, 60, 60, 450 };
    private CRegistros jPanel1 = new CRegistros(30, "files.", "files.tiposorganismo", vcol1, tcol1);
    private CRegistros jPanel3 = new CRegistros(30, "files.", "files.tiposletra", vcol2, tcol2);
    private JButton bagregar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/agregar.gif")));
    private JButton bmodificar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/modificar.gif")));
    private JButton beliminar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/eliminar.gif")));
    private JPanel centralPanel = new JPanel();
    //******************************************
    private principal_simex parentMain;

    /**
   * FORMULARIO PARA VISUALIZAR LOS ORGANISMO Y LAS LETRAS, SEGUN LA VARIABLE tabla
   * @param Tabla
   */
    public Organismos_Letras(String Tabla) {
        try {
            tabla = Tabla;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(672, 362));
        //this.getContentPane().setLayout(null);
        jPanel1.setBounds(new Rectangle(5, 75, 655, 215));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        jPanel3.setBounds(new Rectangle(5, 75, 655, 215));
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel3.setLayout(null);
        bagregar.setText("Agregar");
        bagregar.setBounds(new Rectangle(255, 305, 95, 25));
        bagregar.setMnemonic('a');
        bagregar.setMargin(new Insets(2, 5, 2, 14));
        bagregar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bagregar_actionPerformed(e);
                }
            }
        );
        bmodificar.setText("Modificar");
        bmodificar.setBounds(new Rectangle(355, 305, 105, 25));
        bmodificar.setMnemonic('m');
        bmodificar.setMargin(new Insets(2, 5, 2, 14));
        bmodificar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bmodificar_actionPerformed(e);
                }
            }
        );
        beliminar.setText("Eliminar");
        beliminar.setBounds(new Rectangle(465, 305, 100, 25));
        beliminar.setMnemonic('e');
        beliminar.setMargin(new Insets(2, 5, 2, 14));
        beliminar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    beliminar_actionPerformed(e);
                }
            }
        );
        centralPanel.setBounds(new Rectangle(0, 0, 670, 340));
        centralPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        centralPanel.setLayout(null);
        jPanel2.setBounds(new Rectangle(5, 5, 655, 60));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel2.setLayout(null);
        bcerrar.setText("Cerrar");
        bcerrar.setBounds(new Rectangle(570, 305, 90, 25));
        bcerrar.setMnemonic('c');
        bcerrar.setMargin(new Insets(2, 5, 2, 14));
        bseleccionar.setText("Seleccionar");
        bseleccionar.setBounds(new Rectangle(5, 305, 120, 25));
        bseleccionar.setMnemonic('s');
        bseleccionar.setMargin(new Insets(2, 5, 2, 14));
        buscar.setText("jLabel1");
        buscar.setBounds(new Rectangle(10, 15, 155, 15));
        org.setText("Organismo:");
        org.setBounds(new Rectangle(195, 15, 72, 15));
        jtpalabra.setBounds(new Rectangle(10, 30, 155, 20));
        jcorganismo.setBounds(new Rectangle(195, 30, 455, 20));
        centralPanel.add(jPanel2, null);
        centralPanel.add(beliminar, null);
        centralPanel.add(bmodificar, null);
        centralPanel.add(bagregar, null);
        centralPanel.add(jPanel3, null);
        centralPanel.add(bseleccionar, null);
        centralPanel.add(bcerrar, null);
        centralPanel.add(jPanel1, null);
        jPanel2.add(jcorganismo, null);
        jPanel2.add(jtpalabra, null);
        jPanel2.add(org, null);
        jPanel2.add(buscar, null);
        //this.getContentPane().add(centralPanel, null);
        this.add(centralPanel, null);
        jPanel1.Redimensiona();
        jPanel3.Redimensiona();
        bcerrar.addActionListener(this);
        bseleccionar.addActionListener(this);
        jtpalabra.addKeyListener(this);
        if (tabla.equals("files.tiposorganismo")) {
            jPanel1.setVisible(true);
            jPanel3.setVisible(false);
            jcorganismo.setVisible(false);
            org.setVisible(false);
            buscar.setText("Buscar Organismo:");
            this.setTitle("Listado General de Organismos");
            datos1 = jPanel1.VDatos();
        } else {
            jPanel3.setVisible(true);
            jPanel1.setVisible(false);
            jcorganismo.setVisible(true);
            org.setVisible(true);
            buscar.setText("Buscar Tipo/Letra:");
            this.setTitle("Listado General de Tipos/Letras");
            idorganismo = "213";
            OP_Proced.CargaCombo(jcorganismo, "SELECT idorganismo||'-'||descripcion FROM files.tiposorganismo", OP_Proced.getCampo("SELECT idorganismo||'-'||descripcion FROM files.tiposorganismo WHERE idorganismo=213"));
            datos1 = jPanel3.VDatos();
        }
        timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        if (tabla.equals("files.tiposorganismo")) {
                            datosx = jPanel1.VDatos();
                        } else {
                            datosx = jPanel3.VDatos();
                        }
                        if (!datos1.equals(datosx)) {
                            datos1 = datosx;
                            // System.out.println(datos1);
                        }
                    } catch (Exception x) {
                        OP_Proced.Mensaje(x.getMessage(), "ERROR:");
                    }
                }
            }
            , 0, 500);
        jcorganismo.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        idorganismo = OP_Proced.getCampo("SELECT idorganismo FROM files.tiposorganismo WHERE (idorganismo||'-'||descripcion)='" + jcorganismo.getSelectedItem() + "'");
                        ActualizaTabla();
                    }
                }
            }
        );
        ActualizaTabla();

    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("");
    }


    public void ActualizaTabla() {
        if (tabla.equals("files.tiposorganismo")) {
            Consulta = "SELECT * FROM files.tiposorganismo WHERE estado<>'*'" + cfiltro + " ORDER BY idorganismo,descripcion";
            ConsultaCount = "SELECT count(*) FROM files.tiposorganismo WHERE estado<>'*'" + cfiltro;
            jPanel1.ActualizaTabla(this, Consulta, ConsultaCount);
        } else {
            Consulta = "SELECT * FROM files.tiposletra WHERE estado <> '*' AND idorganismo=" + idorganismo + cfiltro + " ORDER BY letra,descripcion";
            ConsultaCount = "SELECT count(*) FROM files.tiposletra WHERE estado<>'*' AND idorganismo=" + idorganismo + cfiltro;
            jPanel3.ActualizaTabla(this, Consulta, ConsultaCount);
        }
        datos1.clear();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bcerrar) {
            idorganismo = "";
            idtipoletra = "";
            //this.dispose();
             getParentInternalFrame().close();
        } else if (e.getSource() == bseleccionar) {
            if (tabla.equals("files.tiposorganismo")) {
                idorganismo = datos1.elementAt(0).toString();
            } else {
                idtipoletra = datos1.elementAt(2).toString();
            }
            //this.dispose();
             getParentInternalFrame().close();
        }
    }

    public void keyReleased(KeyEvent k) {
    }

    public void keyTyped(KeyEvent k) {
    }

    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_ENTER) {
            if (k.getSource() == jtpalabra) {
                cfiltro = " AND UPPER(descripcion) LIKE UPPER('%" + jtpalabra.getText() + "%') ";
                ActualizaTabla();
            }
        }
    }

    private void bagregar_actionPerformed(ActionEvent e) {
        if (tabla.equals("files.tiposorganismo")) {
            CargaCampo org = new CargaCampo("", tabla, "idorganismo", "descripcion", "Organismo", "Nuevo Organismo");
            OP_Proced.CentraVentana(org);
            org.setModal(true);
            org.setVisible(true);
        } else {
            frmLetras letra = new frmLetras(jcorganismo.getSelectedItem().toString(), "");
            OP_Proced.CentraVentana(letra);
            letra.setModal(true);
            letra.setVisible(true);
        }
        ActualizaTabla();
    }

    private void bmodificar_actionPerformed(ActionEvent e) {
        if (tabla.equals("files.tiposorganismo")) {
            CargaCampo org = new CargaCampo("SELECT * FROM files.tiposorganismo WHERE idorganismo=" + datos1.elementAt(0).toString(), tabla, "idorganismo", "descripcion", "Organismo", "Nuevo Organismo");
            OP_Proced.CentraVentana(org);
            org.setModal(true);
            org.setVisible(true);
        } else {
            frmLetras letra = new frmLetras(jcorganismo.getSelectedItem().toString(), "SELECT * FROM files.tiposletra WHERE idtipo=" + datos1.elementAt(0).toString() +" AND idorganismo = "+ datos1.elementAt(1).toString());
            OP_Proced.CentraVentana(letra);
            letra.setModal(true);
            letra.setVisible(true);
        }
        ActualizaTabla();
    }

    private void beliminar_actionPerformed(ActionEvent e) {
        if (tabla.equals("files.tiposorganismo")) {
            String Q = "UPDATE files.tiposorganismo SET estado='*' WHERE idorganismo=" + datos1.elementAt(0).toString();
            OP_Proced.exActualizar('b', Q);
        } else {
            String Q = "UPDATE files.tiposletra SET estado='*' WHERE idtipo=" + datos1.elementAt(0).toString();
            OP_Proced.exActualizar('b', Q);
        }
        ActualizaTabla();
    }

    public String getIDOrganismo() {
        return idorganismo;
    }

    public String getIDTipoLetra() {
        return idtipoletra;
    }

    public void setParentMain(principal_simex _parentMain) {
        parentMain = _parentMain;
    }
}
