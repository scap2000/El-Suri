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
 * Vtos.java
 *
 * */
package org.digitall.projects.gdigitall.expedientes;

import java.awt.Color;
import java.awt.Cursor;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.digitall.common.reports.BasicReport;
import org.digitall.deprecatedlibs.Proced;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.projects.gdigitall.lib.classes.ValidaDato;
import org.digitall.projects.gdigitall.lib.components.CRegistros;
import org.digitall.projects.gdigitall.lib.components.SelectorFecha;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class Vtos extends BasicPrimitivePanel implements KeyListener, ActionListener {

    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel4 = new JPanel();
    private JPanel jPanel5 = new JPanel();
    
    private JTextField jttipoplazo = new JTextField();
    private JTextField jtidorganismo = new JTextField();
    private JTextField jtletra = new JTextField();
    private JTextField jtnroexp = new JTextField();
    private JTextField jtanio = new JTextField();
    private JTextField jthasta = new JTextField();
    private JTextField jtcatastro = new JTextField();
    private JTextField jtdesde = new JTextField();
    private JTextField jtdias = new JTextField();
    private JTextField jtprofesional = new JTextField();
    private JTextField jttipoiniciador = new JTextField();
    private JTextField jtfolio = new JTextField();
    private JTextField jtasunto = new JTextField();
    private JTextField jtplazo = new JTextField();
    
    private JComboBox jctipoinst = new JComboBox();
    private JComboBox jcPlazo = new JComboBox();
    
    private JButton bimprimir = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/22x22/print.gif")));
    private JButton bcerrar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    private JButton bplazo = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/reloj.gif")));
    private JButton bbuscar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/buscar.gif")));
    
    private JLabel jLabel1 = new JLabel();    
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel(); 
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel9 = new JLabel();
    private JLabel jLabel10 = new JLabel();
    private JLabel jLabel11 = new JLabel();
    private JLabel jLabel12 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel13 = new JLabel();
    private JLabel jLabel14 = new JLabel();
    private JLabel jLabel15 = new JLabel();
    private JLabel jLabel16 = new JLabel();
    private JLabel jLabel17 = new JLabel();
    private JLabel jLabel18 = new JLabel();
    private JLabel jLabel19 = new JLabel();
    
    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    
    private JTextArea jtextracto = new JTextArea();
    private JTextArea jtobservaciones = new JTextArea();
    
    private Vector datos1, datosx = new Vector();
    private Timer timer = new Timer();
    private int[] vcol = { 23, 22, 20, 19, 18, 16, 15, 14, 12, 11, 10, 9, 8, 7, 2, 1, 0 };
    private int[] tcol = { 150, 70, 75, 90, 220, 80, 70 };
    private CRegistros jPanel2 = new CRegistros(30, "files.", "files.expvencidos", vcol, tcol);
    private String Consulta = "", ConsultaCount = "", cfiltro = "", idtipoinst = "";

    private JTextField jtfecha = new JTextField();
    private JPopupMenu jPopupMenu1 = new JPopupMenu();
    private JMenuItem menuGral = new JMenuItem();
    //private JMenuItem menuDetallado = new JMenuItem();
    private JLabel jLabel20 = new JLabel();
    private JPanel centralPanel = new JPanel();
    //************************************************
    private String idTipoPlazo = "-1";
    private String dia = "-1";
    private principal_simex parentMain;
    private Plazos plazo;

    /**
   * FORMULARIO PARA VISUALIZAR LOS DOCUMENTOS Q SE VENCIERON SEGUN EL TIPO DE PLAZO Y FECHA
   */
    public Vtos() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        jtdias.setEditable(false);
        jtplazo.setEditable(false);
        this.setSize(new Dimension(800, 555));
        //this.getContentPane().setLayout(null);
        this.setTitle("Listado de Documentos Vencidos (por Tipo de Plazo)");
        jPanel1.setBounds(new Rectangle(5, 15, 780, 125));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        jPanel2.setBounds(new Rectangle(5, 160, 780, 220));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel2.setLayout(null);
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel3.setLayout(null);
        jLabel1.setText(" Buscar por... ");
        jLabel1.setBounds(new Rectangle(10, 5, 85, 15));
        jLabel1.setOpaque(true);
        jttipoplazo.setBounds(new Rectangle(85, 65, 45, 20));
        jtidorganismo.setBounds(new Rectangle(15, 30, 35, 19));
        jtidorganismo.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        jtletra.setBounds(new Rectangle(65, 30, 45, 19));
        jtletra.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        jtnroexp.setBounds(new Rectangle(112, 30, 93, 19));
        jtanio.setBounds(new Rectangle(220, 30, 40, 19));
        jthasta.setBounds(new Rectangle(641, 66, 69, 19));
        jtcatastro.setBounds(new Rectangle(299, 29, 100, 20));
        jtdesde.setBounds(new Rectangle(475, 66, 69, 19));
        jctipoinst.setBounds(new Rectangle(438, 29, 210, 20));
        bimprimir.setText("Imprimir");
        bimprimir.setBounds(new Rectangle(5, 510, 105, 30));
        bimprimir.setMnemonic('i');
        bcerrar.setText("Cerrar");
        bcerrar.setBounds(new Rectangle(695, 510, 90, 25));
        bcerrar.setMnemonic('c');
        jLabel2.setText("Tipo Plazo:");
        jLabel2.setBounds(new Rectangle(15, 68, 68, 15));
        jLabel4.setText("Tipo Documento:");
        jLabel4.setBounds(new Rectangle(438, 15, 107, 15));
        jLabel5.setText("Nº Catastro:");
        jLabel5.setBounds(new Rectangle(299, 15, 75, 15));
        jLabel6.setText("Fecha Hasta:");
        jLabel6.setBounds(new Rectangle(560, 68, 77, 15));
        jLabel7.setText("Fecha Desde:");
        jLabel7.setBounds(new Rectangle(395, 68, 81, 15));
        jLabel8.setText("Año:");
        jLabel8.setBounds(new Rectangle(225, 15, 28, 15));
        jLabel9.setText("-");
        jLabel9.setBounds(new Rectangle(205, 32, 13, 15));
        jLabel9.setFont(new Font("Dialog", 1, 20));
        jLabel10.setText("Numero:");
        jLabel10.setBounds(new Rectangle(85, 15, 52, 15));
        jLabel11.setText("Org:");
        jLabel11.setBounds(new Rectangle(15, 15, 26, 15));
        jLabel12.setText("-");
        jLabel12.setBounds(new Rectangle(50, 32, 13, 15));
        jLabel12.setFont(new Font("Dialog", 1, 20));
        jLabel3.setText("Días:");
        jLabel3.setBounds(new Rectangle(145, 68, 35, 15));
        jtdias.setBounds(new Rectangle(180, 65, 45, 20));
        bplazo.setText("...");
        bplazo.setBounds(new Rectangle(725, 93, 50, 25));
        bplazo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bplazo_actionPerformed(e);
                }
            }
        );
        jLabel13.setText(" Listado de Documentos con plazo vencido: ");
        jLabel13.setBounds(new Rectangle(20, 150, 320, 15));
        jLabel13.setFont(new Font("Dialog", 1, 14));
        jLabel13.setForeground(Color.blue);
        jLabel13.setOpaque(true);
        jTabbedPane1.setBounds(new Rectangle(5, 390, 780, 110));
        jPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel4.setLayout(null);
        jPanel5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel5.setLayout(null);
        jScrollPane1.getViewport().add(jtextracto, null);
        jPanel4.add(jScrollPane1, null);
        jTabbedPane1.addTab("Extracto o Contenido", jPanel4);
        jScrollPane2.getViewport().add(jtobservaciones, null);
        jPanel5.add(jScrollPane2, null);
        jTabbedPane1.addTab("Observaciones", jPanel5);
        jTabbedPane1.addTab("Otros Datos", jPanel3);
        jPanel3.add(jLabel17, null);
        jPanel3.add(jLabel16, null);
        jPanel3.add(jLabel15, null);
        jPanel3.add(jLabel14, null);
        jPanel3.add(jtasunto, null);
        jPanel3.add(jtfolio, null);
        jPanel3.add(jttipoiniciador, null);
        jPanel3.add(jtprofesional, null);
        centralPanel.add(jLabel13, null);
        centralPanel.add(jLabel20, null);
        centralPanel.add(jLabel1, null);
        centralPanel.add(jPanel1, null);
        centralPanel.add(jPanel2, null);
        centralPanel.add(jTabbedPane1, null);
        centralPanel.add(bcerrar, null);
        centralPanel.add(bimprimir, null);
        jPanel1.add(jcPlazo, null);
        jPanel1.add(jtfecha, null);
        jPanel1.add(jLabel19, null);
        jPanel1.add(bbuscar, null);
        jPanel1.add(jLabel12, null);
        jPanel1.add(jLabel11, null);
        jPanel1.add(jLabel10, null);
        jPanel1.add(jLabel9, null);
        jPanel1.add(jLabel8, null);
        jPanel1.add(jLabel7, null);
        jPanel1.add(jLabel6, null);
        jPanel1.add(jLabel5, null);
        jPanel1.add(jLabel4, null);
        jPanel1.add(jctipoinst, null);
        jPanel1.add(jtdesde, null);
        jPanel1.add(jtcatastro, null);
        jPanel1.add(jthasta, null);
        jPanel1.add(jtanio, null);
        jPanel1.add(jtnroexp, null);
        jPanel1.add(jtletra, null);
        jPanel1.add(jtidorganismo, null);
        jPanel1.add(bplazo, null);
        jPanel1.add(jLabel2, null);
        jPanel1.add(jttipoplazo, null);
        jPanel1.add(jtdias, null);
        jPanel1.add(jLabel3, null);
        jPanel1.add(jtplazo, null);
        jPanel1.add(jLabel18, null);
        jPopupMenu1.add(menuGral);
        //jPopupMenu1.add(menuDetallado);
        //this.getContentPane().add(centralPanel, null);
        this.add(centralPanel, null);
        jPanel2.Redimensiona();
        datos1 = jPanel2.VDatos();
        jtobservaciones.setWrapStyleWord(true);
        jtobservaciones.setLineWrap(true);
        jtobservaciones.setMargin(new Insets(5, 5, 5, 5));
        jtextracto.setWrapStyleWord(true);
        jtextracto.setLineWrap(true);
        jtextracto.setMargin(new Insets(5, 5, 5, 5));
        jLabel20.setForeground(Color.red);
        jLabel20.setOpaque(true);
        jLabel20.setBounds(new Rectangle(590, 5, 185, 15));
        jLabel20.setText(" Presione F10 p/Seleccionar... ");
        centralPanel.setBounds(new Rectangle(0, 0, 800, 550));
        centralPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        centralPanel.setLayout(null);
        jcPlazo.setBounds(new Rectangle(85, 95, 625, 20));
        //menuDetallado.setMnemonic('d');
        //menuDetallado.setText("Informe Detallado por Día (lento)");
        menuGral.setMnemonic('g');
        menuGral.setText("Informe Gral.");
        jPopupMenu1.setLabel("jPopupMenu1");
        jtfecha.setBounds(new Rectangle(295, 66, 69, 19));
        jLabel19.setBounds(new Rectangle(265, 68, 27, 15));
        jLabel19.setText("Vto.:");
        bimprimir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bimprimir_actionPerformed(e);
                }
            }
        );
        bbuscar.setMargin(new Insets(2, 5, 2, 14));
        bcerrar.setMargin(new Insets(2, 5, 2, 14));
        bimprimir.setMargin(new Insets(2, 5, 2, 14));
        jtdias.setBackground(Color.white);
        jtplazo.setBackground(Color.white);
        bbuscar.setMnemonic('b');
        jtplazo.setBounds(new Rectangle(85, 95, 140, 20));
        jLabel18.setBounds(new Rectangle(47, 98, 36, 15));
        jLabel18.setText("Plazo:");
        bbuscar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bbuscar_actionPerformed(e);
                }
            }
        );
        bbuscar.setBounds(new Rectangle(687, 27, 88, 25));
        bbuscar.setText("Buscar");
        bcerrar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bcerrar_actionPerformed(e);
                }
            }
        );
        jtprofesional.setBackground(Color.white);
        jttipoiniciador.setBackground(Color.white);
        jtasunto.setBackground(Color.white);
        jtfolio.setBackground(Color.white);
        jtprofesional.setEditable(false);
        jttipoiniciador.setEditable(false);
        jtasunto.setEditable(false);
        jtfolio.setEditable(false);
        jLabel17.setBounds(new Rectangle(387, 58, 48, 15));
        jLabel17.setText("Asunto:");
        jLabel16.setBounds(new Rectangle(365, 18, 70, 15));
        jLabel16.setText("Cant. Folio:");
        jLabel15.setBounds(new Rectangle(10, 58, 89, 15));
        jLabel15.setText("Tipo Iniciador:");
        jLabel14.setBounds(new Rectangle(26, 18, 73, 15));
        jLabel14.setText("Profesional:");
        jtasunto.setBounds(new Rectangle(440, 55, 330, 20));
        jtfolio.setBounds(new Rectangle(440, 16, 69, 19));
        jttipoiniciador.setBounds(new Rectangle(100, 55, 265, 20));
        jtprofesional.setBounds(new Rectangle(100, 15, 245, 20));
        jtobservaciones.setEditable(false);
        jtextracto.setEditable(false);
        jScrollPane2.setBounds(new Rectangle(5, 5, 765, 75));
        jScrollPane1.setBounds(new Rectangle(5, 5, 765, 75));
        jtidorganismo.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtletra.setDocument(new ValidaDato(ValidaDato.MAYUSCULA));
        jtnroexp.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtanio.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtcatastro.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtnroexp.addKeyListener(this);
        jtdesde.addKeyListener(this);
        jthasta.addKeyListener(this);
        //    jtdesde.addKeyListener(new dateListen(true));
        //    jthasta.addKeyListener(new dateListen(true));
        //    jtfecha.addKeyListener(new dateListen(true));
        jtdesde.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                        JTextField kk = new JTextField(((JTextField)e.getSource()).getText());
                        SelectorFecha c = new SelectorFecha(kk);
                        OP_Proced.CentraVentana(c);
                        c.setModal(true);
                        c.show();
                        jtdesde.setText(kk.getText());
                        {
                        }
                    }
                }
            }
        );
        jthasta.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                        JTextField kk = new JTextField(((JTextField)e.getSource()).getText());
                        SelectorFecha c = new SelectorFecha(kk);
                        OP_Proced.CentraVentana(c);
                        c.setModal(true);
                        c.show();
                        jthasta.setText(kk.getText());
                        {
                        }
                    }
                }
            }
        );
        jtfecha.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
                        JTextField kk = new JTextField(((JTextField)e.getSource()).getText());
                        SelectorFecha c = new SelectorFecha(kk);
                        OP_Proced.CentraVentana(c);
                        c.setModal(true);
                        c.show();
                        jtfecha.setText(kk.getText());
                        {
                        }
                    }
                }
            }
        );
        jtfecha.addKeyListener(this);
        jtanio.addKeyListener(this);
        jtletra.addKeyListener(this);
        jtidorganismo.addKeyListener(this);
        jttipoplazo.addKeyListener(this);
        menuGral.addActionListener(this);
        //menuDetallado.addActionListener(this);
        jctipoinst.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        idtipoinst = OP_Proced.getCampo("SELECT idtipo FROM files.tiposinstlegal WHERE descripcion='" + jctipoinst.getSelectedItem() + "'");
                    }
                }
            }
        );
        jcPlazo.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        /*idTipoPlazo = OP_Proced.getCampo("SELECT idtipo FROM files.tiposplazo WHERE descripcion = '" + jcPlazo.getSelectedItem() + "'");
                        dia = OP_Proced.getCampo("SELECT plazo FROM files.tiposplazo WHERE descripcion = '" + jcPlazo.getSelectedItem() + "'");
                        jttipoplazo.setText(idTipoPlazo);
                        jtdias.setText(dia);*/
                        try {
                            ResultSet Reg = OP_Proced.exConsulta("SELECT idtipo,descripcion,plazo FROM files.tiposplazo WHERE descripcion = '" + jcPlazo.getSelectedItem() + "'");
                            if (Reg.next()) {
                                idTipoPlazo = Reg.getString("idtipo");
                                jttipoplazo.setText(idTipoPlazo);
                                dia = Reg.getString("plazo");
                                jtdias.setText(dia);
                            }
                        } catch (SQLException x) {
                        }
                    }
                }
            }
        );
        timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        datosx = jPanel2.VDatos();
                        //System.out.println(datosx);
                        if (!datos1.equals(datosx)) {
                            datos1 = datosx;
                            jttipoiniciador.setText(OP_Proced.getCampo("SELECT descripcion||' - ('||abrev||')' FROM files.tiposiniciador WHERE idtipo=" + datos1.elementAt(9).toString()));
                            jtasunto.setText(OP_Proced.getCampo("SELECT descripcion||' - ('||abrev||')' FROM files.tiposasunto WHERE idtipo=" + datos1.elementAt(10).toString()));
                            jtextracto.setText(datos1.elementAt(14).toString());
                            jtobservaciones.setText(datos1.elementAt(15).toString());
                            jtfolio.setText(datos1.elementAt(16).toString());
                            jtprofesional.setText(datos1.elementAt(20).toString());
                        }
                    } catch (Exception x) {
                        x.printStackTrace();
                    }
                }
            }
            , 0, 500);
        bimprimir.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        );
        OP_Proced.CargaCombo(jctipoinst, "SELECT descripcion FROM files.tiposinstlegal WHERE estado<>'*'", "EXPEDIENTE");
        OP_Proced.CargaCombo(jcPlazo, "SELECT descripcion FROM files.tiposplazo WHERE estado <> '*'", "PLAZO");
        jtfecha.setText(OP_Proced.FechaHora(true, false));
        jtplazo.setVisible(false);
        bplazo.setVisible(false);
    }

    private void bplazo_actionPerformed(ActionEvent e) {
        /*plazo = new Plazos();
        OP_Proced.CentraVentana(plazo);
        plazo.setModal(true);
        plazo.setVisible(true);
        
        jttipoplazo.setText(plazo.idtipo);
        jtplazo.setText(OP_Proced.getCampo("SELECT descripcion FROM files.tiposplazo WHERE idtipo=" + plazo.idtipo));
        jtdias.setText(plazo.dia);*/
    }

    public void ActualizaTabla() {
        try {
            Consulta = "SELECT idinst,idtipoinst,instlegal.idtipodoc,(instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio) as expte," + "fechaexp,(date '" + OP_Proced.Fecha2(jtfecha.getText(), false) + "' - fechaexp) as diasvenc,catastros.catastro,anio," + "nroexp,idtipoiniciador,idtipoasunto,tiposiniciador.abrev as iniciador,tiposasunto.abrev as asunto," + "iniciante,extracto,instlegal.observaciones,cantfolios,estadoexp,instlegal.idcatastro," + "instlegal.idprofesional,apellido||', '||nombre as profesional,instlegal.cantanexo,instlegal.estado,tiposletra.letra" + " FROM files.instlegal,files.tiposorganismo,files.tiposiniciador,files.tiposasunto,cepax.catastros,sopsalta.profesionales,files.tiposletra,files.tiposplazo" + " WHERE instlegal.estado<>'*' AND (date '" + OP_Proced.Fecha2(jtfecha.getText(), false) + "' - fechaexp) > plazo " + cfiltro + " AND instlegal.idtipoplazo=tiposplazo.idtipo " + " AND instlegal.idtipodoc=tiposletra.idtipo " + " AND instlegal.idorganismo=tiposletra.idorganismo " + " AND instlegal.idorganismo=tiposorganismo.idorganismo " + " AND instlegal.idtipoiniciador=tiposiniciador.idtipo  " + " AND instlegal.idtipoasunto=tiposasunto.idtipo " + " AND instlegal.idcatastro=catastros.idcatastro " + " AND instlegal.idprofesional=profesionales.idprofesional " + " order by nroexp,anio,fechaexp";
            System.out.println(Consulta);
            ConsultaCount = "SELECT count(*) FROM files.instlegal,files.tiposorganismo,files.tiposiniciador,files.tiposasunto,cepax.catastros,sopsalta.profesionales,files.tiposletra,files.tiposplazo" + " WHERE instlegal.estado<>'*' AND (date '" + OP_Proced.Fecha2(OP_Proced.FechaHora(true, false), false) + "' - fechaexp) > plazo " + cfiltro + " AND instlegal.idtipoplazo=tiposplazo.idtipo " + " AND instlegal.idtipodoc=tiposletra.idtipo " + " AND instlegal.idorganismo=tiposletra.idorganismo " + " AND instlegal.idorganismo=tiposorganismo.idorganismo " + " AND instlegal.idtipoiniciador=tiposiniciador.idtipo  " + " AND instlegal.idtipoasunto=tiposasunto.idtipo " + " AND instlegal.idcatastro=catastros.idcatastro " + " AND instlegal.idprofesional=profesionales.idprofesional";
            //System.out.println(ConsultaCount);
            jPanel2.ActualizaTabla(this, Consulta, ConsultaCount);
            datos1.clear();
        } catch (Exception x) {
        }
    }

    private void Buscar() {
        String SQLIdInst = "", SQLIdOrganismo = "", SQLLetra = "", SQLExpte = "", SQLPalabra = "", SQLFecha = "", SQLAnio = "", SQLCatastro = "", SQLPlazo = "";
        try {
            if (jttipoplazo.getText().length() > 0) {
                SQLPlazo = " AND idtipoplazo=" + jttipoplazo.getText();
            } else {
                SQLPlazo = "";
            }
            if (jtnroexp.getText().length() > 0) {
                SQLExpte = " AND nroexp LIKE '%" + jtnroexp.getText() + "%'";
            } else {
                SQLExpte = "";
            }
            if (jtcatastro.getText().length() > 0) {
                SQLCatastro = " AND catastros.catastro=" + jtcatastro.getText();
            } else {
                SQLCatastro = "";
            }
            if (jtanio.getText().length() > 0) {
                SQLAnio = " AND anio=" + jtanio.getText();
            } else {
                SQLAnio = "";
            }
            if (jtdesde.getText().length() > 0 & jthasta.getText().length() > 0) {
                SQLFecha = " AND fechaexp Between '" + OP_Proced.Fecha2(jtdesde.getText(), false) + "' AND '" + OP_Proced.Fecha2(jthasta.getText(), false) + "'";
            } else {
                SQLFecha = "";
            }
            if (jtidorganismo.getText().length() > 0) {
                SQLIdOrganismo = " AND instlegal.idorganismo=" + jtidorganismo.getText();
            } else {
                SQLIdOrganismo = "";
            }
            if (jtletra.getText().length() > 0) {
                SQLLetra = " AND tiposletra.letra='" + jtletra.getText().toUpperCase() + "'";
            } else {
                SQLLetra = "";
            }
            /*      if (jtidinst.getText().length()>0)
        {
          SQLIdInst=" AND idinst="+ jtidinst.getText();
        } else 
        {
          SQLIdInst="";
        }*/
            cfiltro = " AND idtipoinst=" + idtipoinst + SQLIdInst + SQLIdOrganismo + SQLLetra + SQLExpte + SQLAnio + SQLPalabra + SQLFecha + SQLCatastro + SQLPlazo;
            jttipoiniciador.setText("");
            jtasunto.setText("");
            jtextracto.setText("");
            jtobservaciones.setText("");
            jtfolio.setText("0");
            jtprofesional.setText("");
            if (jttipoplazo.getText().length() > 0) {
                if (jtfecha.getText().length() > 0) {
                    ActualizaTabla();
                    
                } else {
                    OP_Proced.Mensaje("Debe indicar la Fecha de Vencimiento", "Datos insuficientes");
                }
            } else {
                OP_Proced.Mensaje("Debe indicar el Tipo de Plazo", "Datos insuficientes");
            }
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "ERROR: ");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (cfiltro.length() > 0) {
            Cursor esp = new Cursor(Cursor.WAIT_CURSOR);
            Cursor def = new Cursor(Cursor.DEFAULT_CURSOR);
            this.setCursor(esp);
            if (e.getSource() == menuGral) {
                try {
                    String cadena = "";
                    if (jtplazo.getText().trim().equals(""))  {
                        cadena = "(" + jtdias.getText() + " días)";
                    } else  {
                        cadena = jtplazo.getText() + " - (" + jtdias.getText() + " días)";
                    }
                    /*ExpteHTML.HTMLExpVdosGral(cfiltro, jtplazo.getText() + " - (" + jtdias.getText() + " Dias)", jtfecha.getText());
                    this.setCursor(def);*/
                    BasicReport report = new BasicReport(Vtos.class.getResource("xml/ExpiredFiles.xml"));
                    report.addTableModel("files.xmlGetExpiredFiles", "'"+ cfiltro +"','"+ OP_Proced.Fecha2(jtfecha.getText(), false) +"'");
                    report.setProperty("tipoplazo",cadena);
                    report.doReport(); 
                }catch (Exception x) {
                }
            } /*else if (e.getSource() == menuDetallado) {
                System.out.println("filtro: " + cfiltro);
                System.out.println("plazo: " + jtplazo.getText() + " - (" + jtdias.getText() + " Dias)");
                System.out.println("fecha: " + jtfecha.getText());
                ExpteHTML.HTMLExpVdosDetallado(cfiltro, jtplazo.getText() + " - (" + jtdias.getText() + " Dias)", jtfecha.getText());
                this.setCursor(def);
            }*/
        } else {
            OP_Proced.Mensaje("No existen datos para imprimir", "Impresion no valida");
        }
    }

    public void keyReleased(KeyEvent k) {
    }

    public void keyTyped(KeyEvent k) {
    }

    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_F10)//F10
        {
            if (k.getSource() == jtidorganismo) {
                Organismos_Letras org = new Organismos_Letras("files.tiposorganismo");
                /*OP_Proced.CentraVentana(org);
                org.setModal(true);*/
                org.setVisible(true);
                if (org.idorganismo.length() > 0) {
                    jtletra.setText("");
                    jtidorganismo.setText(org.idorganismo);
                }
            } else {
                Organismos_Letras org = new Organismos_Letras("files.tiposletra");
                /*OP_Proced.CentraVentana(org);
                org.setModal(true);*/
                org.setVisible(true);
                if (org.idorganismo.length() > 0) {
                    jtidorganismo.setText(org.idorganismo);
                    jtletra.setText(org.idtipoletra);
                }
            }
        }
        if (k.getKeyCode() == KeyEvent.VK_ENTER) {
            if (k.getSource() == jtidorganismo || k.getSource() == jtletra || k.getSource() == jtnroexp || k.getSource() == jtanio || k.getSource() == jthasta || k.getSource() == jtfecha) {
                Buscar();
            } else if (k.getSource() == jttipoplazo) {
                try {
                    ResultSet Reg = OP_Proced.exConsulta("SELECT descripcion,plazo FROM files.tiposplazo WHERE idtipo=" + jttipoplazo.getText());
                    if (Reg.next()) {
                        jtplazo.setText(Reg.getString(1));
                        jtdias.setText(Reg.getString(2));
                    }
                } catch (SQLException x) {
                }
            }
        }
    }

    private void bcerrar_actionPerformed(ActionEvent e) {
        //this.dispose();
         getParentInternalFrame().close();
    }

    private void bbuscar_actionPerformed(ActionEvent e) {
        Buscar();
    }

    private void bimprimir_actionPerformed(ActionEvent e) {
    }

    public void setParentMain(principal_simex _parentMain) {
        parentMain = _parentMain;
    }
}

