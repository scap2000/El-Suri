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
 * Oficinas.java
 *
 * */
package org.digitall.projects.gdigitall.expedientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

//public class Oficinas extends JDialog implements ActionListener, KeyListener {
public class Oficinas extends BasicPrimitivePanel implements ActionListener, KeyListener {
    
    private JTextField jtidorganismo = new JTextField();
    private JTextField jtidtipo = new JTextField();
    private JTextField jtoficina = new JTextField();
    private JTextField jtiddireccion = new JTextField();
    private JTextField jtidarea = new JTextField();
    
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JLabel jLabel10 = new JLabel();
    private JLabel jLabel13 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel5 = new JLabel();

    private JButton bmoddireccion = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/mod.gif")));
    private JButton bnuevo = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/nuevo.gif")));
    private JButton bmodificar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/modificar.gif")));
    private JButton bcargar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
    private JButton beliminar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/eliminar.gif")));
    private JButton bcerrar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    private JButton bseleccionar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/seleccionar.gif")));
    private JButton badddireccion = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/add.gif")));
    private JButton bmodarea = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/mod.gif")));
    private JButton baddarea = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/add.gif")));
    private JButton borg = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/buscar.gif"))); 
    
    //private JPanel jPanel1 = new JPanel();
    private JComboBox jcorganismo = new JComboBox();
    private JComboBox jcdireccion = new JComboBox();
    private JComboBox jcarea = new JComboBox();
    
    private String idarea = "", idorganismo = "0";
    private Vector datos1, datosx = new Vector();
    private Timer timer = new Timer();
    private int[] vcol = { 7, 6, 4, 3, 2, 1 };
    private int[] tcol = { 100, 630 };
    private CRegistros jPanel1 = new CRegistros(30, "files.", "files.tiposoficina", vcol, tcol);
    private String Consulta = "", ConsultaCount = "", cfiltro = "", idtipo = "0", iddireccion = "";
    private boolean nuevo = true;
    static String idorg, idofic;

    private JPanel centralPanel = new JPanel();
    //*********************************************
    private principal_simex parentMain;

    /**
   * FORMULARIO PARA CARGAR OFICINAS
   */
    public Oficinas() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(795, 538));
        //this.getContentPane().setLayout(null);
        this.setTitle("Agregar/Modificar Oficinas");
        jcorganismo.setBounds(new Rectangle(155, 25, 565, 20));
        jtidorganismo.setBounds(new Rectangle(10, 25, 117, 20));
        jLabel1.setText("Buscar Organismo:");
        jLabel1.setBounds(new Rectangle(10, 10, 117, 15));
        jLabel4.setText("Organismo:");
        jLabel4.setBounds(new Rectangle(155, 10, 72, 15));
        jLabel7.setText(" Listado de Oficinas: ");
        jLabel7.setBounds(new Rectangle(20, 155, 155, 20));
        jLabel7.setFont(new Font("Dialog", 1, 14));
        jLabel7.setOpaque(true);
        jPanel2.setBounds(new Rectangle(5, 5, 780, 140));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel2.setLayout(null);
        bmoddireccion.setText("M");
        bmoddireccion.setBounds(new Rectangle(725, 108, 45, 25));
        jPanel3.setBounds(new Rectangle(5, 410, 780, 55));
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel3.setLayout(null);
        jLabel10.setText("Nombre o Descripcion:");
        jLabel10.setBounds(new Rectangle(155, 10, 141, 15));
        jLabel13.setText("Nº Orden:");
        jLabel13.setBounds(new Rectangle(10, 10, 60, 15));
        jtidtipo.setBounds(new Rectangle(10, 25, 80, 20));
        jtoficina.setBounds(new Rectangle(155, 25, 610, 20));
        bnuevo.setText("Nuevo");
        bnuevo.setBounds(new Rectangle(310, 480, 85, 25));
        bnuevo.setMnemonic('n');
        bmodificar.setText("Modificar");
        bmodificar.setBounds(new Rectangle(400, 480, 105, 25));
        bmodificar.setMnemonic('m');
        bcargar.setText("Cargar");
        bcargar.setBounds(new Rectangle(510, 480, 85, 25));
        bcargar.setMnemonic('a');
        beliminar.setText("Eliminar");
        beliminar.setBounds(new Rectangle(600, 480, 95, 25));
        beliminar.setMnemonic('e');
        bcerrar.setText("Cerrar");
        bcerrar.setBounds(new Rectangle(700, 480, 85, 25));
        bcerrar.setMnemonic('c');
        bseleccionar.setText("Seleccionar");
        bseleccionar.setBounds(new Rectangle(5, 480, 120, 25));
        bseleccionar.setMnemonic('s');
        jPanel1.setBounds(new Rectangle(5, 170, 780, 230));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        jLabel5.setText("Direccion:");
        jLabel5.setBounds(new Rectangle(155, 95, 61, 15));
        jcdireccion.setBounds(new Rectangle(155, 110, 510, 20));
        badddireccion.setText("+");
        badddireccion.setBounds(new Rectangle(675, 108, 45, 25));
        badddireccion.setActionCommand("+");
        jPanel3.add(jtoficina, null);
        jPanel3.add(jLabel10, null);
        jPanel3.add(jLabel13, null);
        jPanel3.add(jtidtipo, null);
        centralPanel.add(jPanel2, null);
        centralPanel.add(jLabel7, null);
        centralPanel.add(jPanel1, null);
        centralPanel.add(jPanel3, null);
        centralPanel.add(bcargar, null);
        centralPanel.add(bcerrar, null);
        centralPanel.add(beliminar, null);
        centralPanel.add(bmodificar, null);
        centralPanel.add(bnuevo, null);
        centralPanel.add(bseleccionar, null);
        jPanel2.add(borg, null);
        jPanel2.add(baddarea, null);
        jPanel2.add(bmodarea, null);
        jPanel2.add(jLabel6, null);
        jPanel2.add(jcarea, null);
        jPanel2.add(jtidarea, null);
        jPanel2.add(jLabel3, null);
        jPanel2.add(jLabel4, null);
        jPanel2.add(jLabel1, null);
        jPanel2.add(jtidorganismo, null);
        jPanel2.add(jcorganismo, null);
        jPanel2.add(jcdireccion, null);
        jPanel2.add(jLabel2, null);
        jPanel2.add(jtiddireccion, null);
        jPanel2.add(jLabel5, null);
        jPanel2.add(bmoddireccion, null);
        jPanel2.add(badddireccion, null);
        //this.getContentPane().add(centralPanel, null);
        this.add(centralPanel, BorderLayout.CENTER);
        jtidorganismo.addKeyListener(this);
        jtiddireccion.addKeyListener(this);
        badddireccion.addActionListener(this);
        bmoddireccion.addActionListener(this);
        bnuevo.addActionListener(this);
        bmodificar.addActionListener(this);
        bcargar.addActionListener(this);
        beliminar.addActionListener(this);
        bcerrar.addActionListener(this);
        bseleccionar.addActionListener(this);
        jPanel1.Redimensiona();
        datos1 = jPanel1.VDatos();
        bcargar.setEnabled(false);
        borg.addActionListener(new Oficinas_borg_actionAdapter(this));
        borg.setText("M");
        borg.setBounds(new Rectangle(725, 23, 45, 25));
        centralPanel.setBounds(new Rectangle(0, 0, 790, 515));
        centralPanel.setLayout(null);
        baddarea.setActionCommand("+");
        baddarea.setBounds(new Rectangle(675, 65, 45, 25));
        baddarea.setText("+");
        bmodarea.setBounds(new Rectangle(725, 65, 45, 25));
        bmodarea.setText("M");
        jLabel6.setBounds(new Rectangle(155, 55, 31, 15));
        jLabel6.setText("Area:");
        jcarea.setBounds(new Rectangle(155, 70, 510, 20));
        jtidarea.setBounds(new Rectangle(10, 70, 115, 20));
        jLabel3.setText("Buscar Area:");
        jLabel3.setBounds(new Rectangle(10, 55, 76, 15));
        jtidorganismo.setText("82");
        jLabel2.setBounds(new Rectangle(10, 95, 106, 15));
        jLabel2.setText("Buscar Direccion:");
        jtiddireccion.setBounds(new Rectangle(10, 110, 115, 20));
        bnuevo.setMargin(new Insets(2, 5, 2, 14));
        bmodificar.setMargin(new Insets(2, 5, 2, 14));
        bcargar.setMargin(new Insets(2, 5, 2, 14));
        beliminar.setMargin(new Insets(2, 5, 2, 14));
        bcerrar.setMargin(new Insets(2, 5, 2, 14));
        bseleccionar.setMargin(new Insets(2, 5, 2, 14));
        jtoficina.setBackground(Color.white);
        jtidtipo.setEditable(false);
        jtoficina.setEditable(false);
        jtidarea.addKeyListener(this);
        baddarea.addActionListener(this);
        bmodarea.addActionListener(this);
        timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        datosx = jPanel1.VDatos();
                        if (!datos1.equals(datosx)) {
                            datos1 = datosx;
                            jtidtipo.setText(datos1.elementAt(0).toString());
                            jtoficina.setText(datos1.elementAt(5).toString());
                            bseleccionar.setEnabled(true);
                            bmodificar.setEnabled(true);
                            beliminar.setEnabled(true);
                        }
                        //System.out.println(datos1);
                    } catch (Exception x) {
                        OP_Proced.Mensaje(x.getMessage(), "ERROR:");
                    }
                }
            }
            , 0, 500);
        jcarea.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        if (jtidorganismo.getText().length() > 0) {
                            idarea = OP_Proced.getCampo("SELECT idarea FROM files.tiposarea WHERE estado<>'*' AND idorganismo=" + idorganismo + " AND descripcion='" + jcarea.getSelectedItem() + "'");
                            if (idarea.length() > 0) {
                                jtidarea.setText(idarea);
                                if (!nuevo) {
                                    LimpiaCampo();
                                }
                            }
                        } else {
                            OP_Proced.Mensaje("Debe indicar un Organismo", "Datos insuficientes");
                        }
                    }
                }
            }
        );
        jcorganismo.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        jtidorganismo.setText(OP_Proced.getCampo("SELECT idorganismo FROM files.tiposorganismo WHERE descripcion='" + jcorganismo.getSelectedItem() + "'"));
                        idorganismo = jtidorganismo.getText();
                    }
                }
            }
        );
        jcdireccion.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        if (jtidarea.getText().length() > 0) {
                            iddireccion = OP_Proced.getCampo("SELECT iddireccion FROM files.tiposdireccion WHERE estado<>'*' AND idorganismo=" + idorganismo + " AND idarea=" + idarea + " AND descripcion='" + jcdireccion.getSelectedItem() + "'");
                            if (iddireccion.length() > 0) {
                                jtiddireccion.setText(iddireccion);
                                if (!nuevo) {
                                    LimpiaCampo();
                                }
                                ActualizaTabla();
                            }
                        } else {
                            OP_Proced.Mensaje("Debe indicar una Area", "Datos insuficientes");
                        }
                    }
                }
            }
        );
        OP_Proced.CargaCombo(jcorganismo, "SELECT descripcion FROM files.tiposorganismo WHERE idorganismo=213", "");
        bseleccionar.setEnabled(false);
        bmodificar.setEnabled(false);
        beliminar.setEnabled(false);
        
        /** Estan dehabilitados porque darian conflicto!!! */
        borg.setVisible(false);
        baddarea.setVisible(true);
        bmodarea.setVisible(true);
        /*badddireccion.setVisible(false);
        bmoddireccion.setVisible(false);*/
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("");
    }

    private void ActualizaTabla() {
        bseleccionar.setEnabled(false);
        bmodificar.setEnabled(false);
        beliminar.setEnabled(false);
        Consulta = "SELECT * FROM files.tiposoficina " + " WHERE estado<>'*' AND idorganismo=" + idorganismo + " AND iddireccion=" + iddireccion + " AND idarea=" + idarea + " ORDER BY descripcion";
        System.out.println(Consulta);
        ConsultaCount = "SELECT count(*) FROM files.tiposoficina " + " WHERE estado<>'*' AND idorganismo=" + idorganismo + " AND iddireccion=" + iddireccion + " AND idarea=" + idarea;
        jPanel1.ActualizaTabla(this, Consulta, ConsultaCount);
        
        datos1.clear();
    }

    public void LimpiaCampo() {
        jtoficina.setText("");
        jtidtipo.setText("");
    }

    public void ActivaCampo(boolean op) {
        jtoficina.setEditable(op);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bcerrar) {
            idorg = "";
            idofic = "";
            //this.dispose();
            getParentInternalFrame().close();
        } else if (e.getSource() == bnuevo) {
            LimpiaCampo();
            ActivaCampo(true);
            bcargar.setEnabled(true);
            nuevo = true;
            bmodificar.setEnabled(false);
            beliminar.setEnabled(false);
            bnuevo.setEnabled(false);
            bseleccionar.setEnabled(false);
        } else if (e.getSource() == bcargar) {
            if (jtoficina.getText().trim().length() > 0) {
                String Q = "";
                if (nuevo) {
                    Q = "INSERT INTO files.tiposoficina VALUES(" + OP_Proced.Max("files.tiposoficina", "idtipo", "") + "," + idorganismo + "," + idarea + "," + iddireccion + ",0,'" + jtoficina.getText().toUpperCase() + "','')";
                    System.out.println(Q);
                    if (OP_Proced.exActualizar('a', Q))
                        ActualizaTabla();
                } else {
                    if (!datos1.isEmpty() | jtidtipo.getText().length() > 0) {
                        Q = "UPDATE files.tiposoficina SET descripcion='" + jtoficina.getText().toUpperCase() + "', idarea=" + idarea + ", idorganismo=" + idorganismo + ",iddireccion=" + iddireccion + " WHERE idtipo=" + idtipo;
                        System.out.println(Q);
                        if (OP_Proced.exActualizar('m', Q)) {
                            OP_Proced.exActualizar('a', "UPDATE files.tiposoficina SET estado='' WHERE idtipo=" + datos1.elementAt(0).toString());
                            ActualizaTabla();
                        }
                    } else {
                        OP_Proced.Errores(2);
                    }
                    nuevo = true;
                }
                ActivaCampo(false);
                bcargar.setEnabled(false);
		bnuevo.setEnabled(true);
                LimpiaCampo();
            }
        } else if (e.getSource() == bmodificar) {
            if (!datos1.elementAt(6).toString().equals("a")) {
                nuevo = false;
                OP_Proced.exActualizar('a', "UPDATE files.tiposoficina SET estado='a' WHERE idtipo=" + datos1.elementAt(0).toString());
                ActivaCampo(true);
                bcargar.setEnabled(true);
                idtipo = jtidtipo.getText();
                bnuevo.setEnabled(false);
                bseleccionar.setEnabled(false);
                bmodificar.setEnabled(false);
                beliminar.setEnabled(false);
            } else {
                OP_Proced.Errores(0);
            }
        } else if (e.getSource() == beliminar) {
            if (!datos1.isEmpty()) {
                if (OP_Proced.exActualizar('a', "UPDATE files.tiposoficina SET estado='*' WHERE idtipo=" + datos1.elementAt(0).toString()))
                    ActualizaTabla();
                LimpiaCampo();
            } else {
                OP_Proced.Errores(2);
            }
        } else if (e.getSource() == badddireccion) {
            Direcciones nuevo = new Direcciones("", jtidorganismo.getText(), jtidarea.getText());
            OP_Proced.CentraVentana(nuevo);
            nuevo.setModal(true);
            nuevo.setVisible(true);
            if (nuevo.idorganismo.length() > 0) {
                String Q = "SELECT descripcion FROM files.tiposdireccion WHERE estado<>'*' AND idorganismo=" + nuevo.idorganismo + " AND idarea=" + nuevo.idarea + " AND iddireccion=" + nuevo.iddireccion + " ORDER BY descripcion";
                System.out.println(Q);    
                OP_Proced.CargaCombo(jcdireccion, Q, "");
                jtiddireccion.setText(nuevo.iddireccion);
                OP_Proced.CargaCombo(jcorganismo, "SELECT descripcion FROM files.tiposorganismo WHERE idorganismo=" + nuevo.idorganismo, "");
                jtidorganismo.setText(nuevo.idorganismo);
            }
        } else if (e.getSource() == bmoddireccion) {
            String idorgan = jtidorganismo.getText(), area = jtidarea.getText();
            Direcciones nuevo = new Direcciones("SELECT * FROM files.tiposdireccion WHERE idorganismo=" + idorgan + " AND iddireccion=" + jtiddireccion.getText() + " AND idarea=" + idarea, idorgan, jtidarea.getText());
            /*OP_Proced.CentraVentana(nuevo);
            nuevo.setModal(true);*/
            nuevo.setVisible(true);
            if (nuevo.idorganismo.length() > 0) {
                idorgan = nuevo.idorganismo;
                iddireccion = nuevo.iddireccion;
                String Q = "SELECT descripcion FROM files.tiposdireccion WHERE estado<>'*' AND idorganismo=" + nuevo.idorganismo + " AND idarea=" + nuevo.idarea + " AND iddireccion=" + nuevo.iddireccion + " ORDER BY descripcion";
                OP_Proced.CargaCombo(jcdireccion, Q, "");
                jtiddireccion.setText(nuevo.iddireccion);
                OP_Proced.CargaCombo(jcorganismo, "SELECT descripcion FROM files.tiposorganismo WHERE idorganismo=" + nuevo.idorganismo, "");
                jtidorganismo.setText(nuevo.idorganismo);
            }
        } else if (e.getSource() == baddarea) {
            if (jtidorganismo.getText().length() > 0) {
                ExptesAreas area = new ExptesAreas("", jtidorganismo.getText());
                OP_Proced.CentraVentana(area);
                area.setModal(true);
                area.setVisible(true);
                if (area.idarea.length() > 0) {
                    jtidorganismo.setText(area.idorganismo);
                    OP_Proced.CargaCombo(jcorganismo, "SELECT descripcion FROM files.tiposorganismo WHERE idorganismo=" + area.idorganismo, "");
                    jtidarea.setText(area.idarea);
                    OP_Proced.CargaCombo(jcarea, "SELECT descripcion FROM files.tiposarea WHERE idorganismo=" + area.idorganismo + " AND idarea=" + area.idarea, "");
                }
            } else {
                OP_Proced.Mensaje("Debe indicar un Organismo", "Datos insuficientes");
            }
        } else if (e.getSource() == bmodarea) {
            if (jtidarea.getText().length() > 0) {
                ExptesAreas area = new ExptesAreas("SELECT * FROM files.tiposarea WHERE idarea=" + idarea + " AND idorganismo=" + idorganismo, idorganismo);
                OP_Proced.CentraVentana(area);
                area.setModal(true);
                area.setVisible(true);
                if (area.idarea.length() > 0) {
                    jtidorganismo.setText(area.idorganismo);
                    OP_Proced.CargaCombo(jcorganismo, "SELECT descripcion FROM files.tiposorganismo WHERE idorganismo=" + area.idorganismo, "");
                    jtidarea.setText(area.idarea);
                    OP_Proced.CargaCombo(jcarea, "SELECT descripcion FROM files.tiposarea WHERE idorganismo=" + area.idorganismo + " AND idarea=" + area.idarea, "");
                }
            } else {
                OP_Proced.Mensaje("Debe indicar una Area", "Datos insuficientes");
            }
        } else if (e.getSource() == bseleccionar) {
            idorg = datos1.elementAt(1).toString();
            idofic = datos1.elementAt(0).toString();
            //this.dispose();
             getParentInternalFrame().close();
        }
    }

    public void keyReleased(KeyEvent k) {
    }

    public void keyTyped(KeyEvent k) {
    }

    public void keyPressed(KeyEvent k) {
        int id = 99999999;
        String texto = "", Q = "";
        if (k.getKeyCode() == KeyEvent.VK_ENTER) {
            if (k.getSource() == jtidorganismo) {
                try {
                    id = Integer.parseInt(jtidorganismo.getText());
                } catch (NumberFormatException n) {
                    //            System.out.println(n.getMessage());           
                }
                Q = "SELECT descripcion FROM files.tiposorganismo WHERE estado<>'*' AND (idorganismo=" + id + " or upper(descripcion) LIKE upper('%" + jtidorganismo.getText() + "%')) ORDER BY descripcion";
                OP_Proced.CargaCombo(jcorganismo, Q, "");
            } else if (k.getSource() == jtiddireccion) {
                if (jtidarea.getText().length() > 0) {
                    try {
                        id = Integer.parseInt(jtiddireccion.getText());
                    } catch (NumberFormatException n) {
                        //            System.out.println(n.getMessage());           
                    }
                    Q = "SELECT descripcion FROM files.tiposdireccion WHERE estado<>'*' AND idorganismo=" + idorganismo + " AND idarea=" + idarea + " AND (iddireccion=" + id + " OR upper(descripcion) LIKE upper('%" + jtiddireccion.getText() + "%')) ORDER BY descripcion";
                    OP_Proced.CargaCombo(jcdireccion, Q, "");
                } else {
                    OP_Proced.Mensaje("Debe indicar una Area", "Datos insuficientes");
                }
            } else if (k.getSource() == jtidarea) {
                if (jtidorganismo.getText().length() > 0) {
                    try {
                        id = Integer.parseInt(jtidarea.getText());
                    } catch (NumberFormatException n) {
                        //            System.out.println(n.getMessage());           
                    }
                    Q = "SELECT descripcion FROM files.tiposarea WHERE estado<>'*' AND idorganismo=" + idorganismo + " AND (idarea =" + id + " OR upper(descripcion) LIKE upper('%" + jtidarea.getText() + "%')) ORDER BY descripcion";
                    OP_Proced.CargaCombo(jcarea, Q, "");
                } else {
                    OP_Proced.Mensaje("Debe indicar un Organismo", "Datos insuficientes");
                }
            }
        }
    }

    void borg_actionPerformed(ActionEvent e) {
        Organismos_Letras org = new Organismos_Letras("files.tiposorganismo");
        /*OP_Proced.CentraVentana(org);
        org.setModal(true);*/
        org.setVisible(true);
        if (org.idorganismo.length() > 0) {
            OP_Proced.CargaCombo(jcorganismo, "SELECT descripcion FROM files.tiposorganismo WHERE idorganismo=" + org.idorganismo, "");
            jtidorganismo.setText(org.idorganismo);
        }
    }

    public String getIDOfic() {
        return idofic;
    }

    public String getIDOrg() {
        return idorg;
    }

    public void setParentMain(principal_simex _parentMain) {
        parentMain = _parentMain;
    }
}
class Oficinas_borg_actionAdapter implements ActionListener {
    Oficinas adaptee;

    Oficinas_borg_actionAdapter(Oficinas adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.borg_actionPerformed(e);
    }
}
