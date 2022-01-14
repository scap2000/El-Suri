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
 * Pases.java
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.EmptyStackException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLEditorKit;

import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.projects.gdigitall.lib.components.CRegistros;
import org.digitall.projects.gdigitall.lib.html.HTMLBrowser;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

//************
//public class Pases extends JDialog implements ActionListener, KeyListener {
public class Pases extends BasicPrimitivePanel implements ActionListener, KeyListener {
    //private JPanel jPanel1 = new JPanel();
    private Vector datos1, datosx = new Vector();
    private Timer timer = new Timer();
    private int[] vcol = { 18, 17, 16, 15, 13, 12, 11, 9, 8, 7, 6, 4, 3, 0 };
    private int[] tcol = { 70, 70, 275, 275, 70 };
    private CRegistros jPanel1 = new CRegistros(30, "files.", "files.pases", vcol, tcol);
    private String Consulta = "", ConsultaCount = "", cfiltro = "", idinst = "", idoficrec = "0", idreceptor = "0";
    private JButton bregistrar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
    private JButton bcerrar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    private JButton beliminar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/eliminar.gif")));
    private JButton bmodificar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/modificar.gif")));
    private JButton bnuevo = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/nuevo.gif")));
    
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel4 = new JPanel();
    private JPanel jPanel5 = new JPanel();
    
    private JTextField jtexpte = new JTextField();
    private JTextField jtfechae = new JTextField();
    private JTextField jtfoliose = new JTextField();
    private JTextField jtiniciador = new JTextField();
    private JTextField jtobservaciones = new JTextField();
    private JTextField jtfechap = new JTextField();
    private JTextField jtfoliosp = new JTextField();
    private JTextField jtoficrec = new JTextField();
    private JTextField jthorap = new JTextField();
    private JTextField jtrec = new JTextField();
    private JTextField jtestado = new JTextField();
    private JTextField jtcatastro = new JTextField();
    private JTextArea jtcontenido = new JTextArea();
    private JTextField jttipoiniciador = new JTextField();
    private JTextField jtemisor = new JTextField();
    private JTextField jtobservrec = new JTextField();
    private JTextField jtreceptor = new JTextField();
    private JTextField jtcantfolio = new JTextField();
    private JTextField jtfecha_rec = new JTextField();
    private JTextField jthora_rec = new JTextField();
    
    private JLabel jLabel2 = new JLabel();
    private JLabel jtpalabra2 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JPanel jPanel3 = new JPanel();
    private JLabel jLabel9 = new JLabel();
    private JLabel jLabel10 = new JLabel();
    private JLabel jLabel11 = new JLabel();
    private JLabel jtpalabra3 = new JLabel();
    private JLabel jLabel12 = new JLabel();
    private JLabel jLabel13 = new JLabel();
    private JLabel jtpalabra5 = new JLabel();
    private JLabel jLabel14 = new JLabel();
    private JLabel jLabel15 = new JLabel();
    private JLabel jLabel16 = new JLabel();
    private JLabel jLabel18 = new JLabel();
    private JLabel jLabel19 = new JLabel();
    private JLabel jLabel111 = new JLabel();
    private JLabel jLabel112 = new JLabel();
    private JLabel jLabel113 = new JLabel();

    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    
    

    private JButton bimprimir = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/22x22/print.gif")));
    
    private JPopupMenu jPopupMenu1 = new JPopupMenu();
    private JMenuItem imprimirTexto = new JMenuItem();
    private JScrollPane jScrollPane3 = new JScrollPane();
    private JEditorPane jtobservemi = new JEditorPane();
    private JPanel centralPanel = new JPanel();
    //********************************************
    private Expedientes parentMain;
    private frmPases nuevo;

    /**
   * FORMULARIO PARA VISUALIZAR LOS PASES DE UN DOCUMENTO
   * @param IDInst
   */
    public Pases(String IDInst) {
        try {
            idinst = IDInst;
            jbInit();
        } catch (SQLException e) {
            OP_Proced.Mensaje(e.getMessage().toUpperCase(), "Acceso a la Base de Datos no Autorizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(800, 568));
        //this.getContentPane().setLayout(null);
        String t = "SELECT (instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio) FROM files.instlegal,files.tiposletra" + " WHERE idinst=" + idinst + " AND instlegal.idtipodoc=tiposletra.idtipo ";
        this.setTitle("Listado de Pases del Documento Nº " + OP_Proced.getCampo(t));
        jPanel1.setBounds(new Rectangle(5, 130, 780, 200));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        bregistrar.setText("Registrar");
        bregistrar.setBounds(new Rectangle(350, 505, 110, 25));
        bregistrar.setMnemonic('r');
        bcerrar.setText("Cerrar");
        bcerrar.setBounds(new Rectangle(695, 505, 90, 25));
        bcerrar.setMnemonic('c');
        beliminar.setText("Eliminar");
        beliminar.setBounds(new Rectangle(585, 505, 105, 25));
        beliminar.setMnemonic('e');
        bmodificar.setText("Modificar");
        bmodificar.setBounds(new Rectangle(465, 505, 110, 25));
        bmodificar.setMnemonic('m');
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel2.setLayout(null);
        jtexpte.setBounds(new Rectangle(100, 15, 185, 20));
        jLabel2.setText("Fecha:");
        jLabel2.setBounds(new Rectangle(535, 15, 38, 20));
        jtfechae.setBounds(new Rectangle(580, 15, 72, 20));
        jtpalabra2.setText("Folios:");
        jtpalabra2.setBounds(new Rectangle(685, 15, 41, 20));
        jtfoliose.setBounds(new Rectangle(730, 15, 36, 20));
        jLabel4.setText("Tipo Iniciador:");
        jLabel4.setBounds(new Rectangle(10, 58, 89, 15));
        jtiniciador.setBounds(new Rectangle(100, 95, 250, 20));
        jLabel5.setText("Contenido:");
        jLabel5.setBounds(new Rectangle(386, 50, 68, 15));
        jLabel6.setText(" Pases Registrados: ");
        jLabel6.setBounds(new Rectangle(15, 120, 145, 20));
        jLabel6.setFont(new Font("Dialog", 1, 14));
        jLabel6.setOpaque(true);
        jPanel3.add(jtpalabra5, null);
        jPanel3.add(jtestado, null);
        jPanel3.add(jtrec, null);
        jPanel3.add(jLabel13, null);
        jPanel3.add(jLabel9, null);
        jPanel3.add(jtobservaciones, null);
        jPanel3.add(jLabel10, null);
        jPanel3.add(jtoficrec, null);
        jPanel3.add(jLabel11, null);
        jPanel3.add(jtfoliosp, null);
        jPanel3.add(jtpalabra3, null);
        jPanel3.add(jtfechap, null);
        jPanel3.add(jLabel12, null);
        jPanel3.add(jthorap, null);
        jPanel2.add(jLabel15, null);
        jScrollPane1.getViewport().add(jtcontenido, null);
        jPanel2.add(jScrollPane1, null);
        jPanel2.add(jLabel14, null);
        jPanel2.add(jtcatastro, null);
        jPanel2.add(jLabel7, null);
        jPanel2.add(jLabel5, null);
        jPanel2.add(jtiniciador, null);
        jPanel2.add(jLabel4, null);
        jPanel2.add(jtfoliose, null);
        jPanel2.add(jtpalabra2, null);
        jPanel2.add(jtfechae, null);
        jPanel2.add(jLabel2, null);
        jPanel2.add(jtexpte, null);
        jPanel2.add(jttipoiniciador, null);
        jPanel4.add(jLabel113, null);
        jPanel4.add(jthora_rec, null);
        jPanel4.add(jLabel112, null);
        jPanel4.add(jtfecha_rec, null);
        jPanel4.add(jtcantfolio, null);
        jPanel4.add(jLabel111, null);
        jPanel4.add(jtreceptor, null);
        jPanel4.add(jLabel19, null);
        jPanel4.add(jLabel18, null);
        jPanel4.add(jLabel16, null);
        jPanel4.add(jtemisor, null);
        jPanel4.add(jtobservrec, null);
        jTabbedPane1.addTab("Datos del Pase (seleccionado)", jPanel4);
        jScrollPane3.getViewport().add(jtobservemi, null);
        jPanel5.add(jScrollPane3, null);
        jTabbedPane1.addTab("Texto del Pase", jPanel5);
        jTabbedPane1.addTab("Datos del Documento (Expte.)", jPanel2);
        centralPanel.add(jLabel6, null);
        centralPanel.add(jLabel8, null);
        centralPanel.add(jPanel3, null);
        centralPanel.add(jPanel1, null);
        centralPanel.add(jTabbedPane1, null);
        centralPanel.add(bregistrar, null);
        centralPanel.add(bcerrar, null);
        centralPanel.add(beliminar, null);
        centralPanel.add(bmodificar, null);
        centralPanel.add(bnuevo, null);
        centralPanel.add(bimprimir, null);
        jPopupMenu1.add(imprimirTexto);
        //this.getContentPane().add(centralPanel, null);
        this.add(centralPanel, BorderLayout.CENTER);
        jPanel1.Redimensiona();
        //    jtiniciador.addKeyListener(new dateListen(true));
        datos1 = jPanel1.VDatos();
        jLabel6.setForeground(new Color(5, 25, 180));
        jtfechap.setBackground(Color.white);
        jtoficrec.setBackground(Color.white);
        jthorap.setBackground(Color.white);
        jtfoliosp.setBackground(Color.white);
        jtobservaciones.setBackground(Color.white);
        jtrec.setBackground(Color.white);
        jttipoiniciador.setBackground(Color.white);
        jtiniciador.setBackground(Color.white);
        jtcatastro.setBackground(Color.white);
        jtfechae.setBackground(Color.white);
        jtfoliose.setBackground(Color.white);
        jtexpte.setBackground(new Color(255, 255, 220));
        jtemisor.setBackground(Color.white);
        jtreceptor.setBackground(Color.white);
        jtcantfolio.setBackground(Color.white);
        jtfecha_rec.setBackground(Color.white);
        jtobservrec.setBackground(Color.white);
        jthora_rec.setBackground(Color.white);
        jLabel8.setForeground(new Color(5, 25, 135));
        jLabel113.setBounds(new Rectangle(595, 103, 98, 15));
        jLabel113.setText("Hora Recepcion:");
        jthora_rec.setBounds(new Rectangle(700, 100, 65, 20));
        jthora_rec.setEditable(false);
        jLabel112.setBounds(new Rectangle(370, 103, 104, 15));
        jLabel112.setText("Fecha Recepcion:");
        jtfecha_rec.setBounds(new Rectangle(480, 100, 80, 20));
        jtfecha_rec.setEditable(false);
        jtcantfolio.setEditable(false);
        jtcantfolio.setBounds(new Rectangle(90, 100, 55, 20));
        jLabel111.setText("Cant. Folios:");
        jLabel111.setBounds(new Rectangle(10, 103, 77, 15));
        jtreceptor.setEditable(false);
        jtreceptor.setBounds(new Rectangle(10, 70, 270, 20));
        jLabel19.setText("Persona Receptora:");
        jLabel19.setBounds(new Rectangle(10, 55, 117, 15));
        jtobservrec.setEditable(false);
        jtobservrec.setBounds(new Rectangle(315, 70, 450, 20));
        jLabel18.setText("Observ. Receptor:");
        jLabel18.setBounds(new Rectangle(315, 55, 108, 15));
        jLabel16.setBounds(new Rectangle(10, 10, 106, 15));
        jLabel16.setText("Persona Emisora:");
        jtemisor.setBounds(new Rectangle(10, 25, 270, 20));
        jtemisor.setEditable(false);
        jPanel4.setLayout(null);
        jPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jTabbedPane1.setBounds(new Rectangle(5, 335, 780, 155));
        jLabel15.setBounds(new Rectangle(42, 98, 57, 15));
        jLabel15.setText("Iniciador:");
        jttipoiniciador.setEditable(false);
        jttipoiniciador.setBounds(new Rectangle(100, 55, 250, 20));
        jScrollPane1.setBounds(new Rectangle(386, 65, 380, 50));
        jtexpte.setEditable(false);
        jtiniciador.setEditable(false);
        jtfechap.setEditable(false);
        jtoficrec.setEditable(false);
        jthorap.setEditable(false);
        jtfoliosp.setEditable(false);
        jtobservaciones.setEditable(false);
        jtrec.setEditable(false);
        jtcatastro.setEditable(false);
        jtfechae.setEditable(false);
        jtestado.setEditable(false);
        jtfoliose.setEditable(false);
        jLabel14.setBounds(new Rectangle(310, 15, 75, 20));
        jLabel14.setText("Nº Catastro:");
        jtcatastro.setBounds(new Rectangle(390, 15, 110, 20));
        jtpalabra5.setText("Estado:");
        jtpalabra5.setBounds(new Rectangle(707, 10, 45, 15));
        jtpalabra5.setToolTipText("null");
        jtestado.setBounds(new Rectangle(707, 25, 68, 20));
        jtrec.setBounds(new Rectangle(410, 70, 365, 20));
        jLabel13.setText("Persona Receptora:");
        jLabel13.setBounds(new Rectangle(410, 55, 117, 15));
        jthorap.setBounds(new Rectangle(105, 25, 63, 20));
        jLabel12.setBounds(new Rectangle(10, 10, 38, 15));
        jLabel12.setText("Fecha:");
        jtfechap.setBounds(new Rectangle(10, 25, 90, 20));
        jtpalabra3.setToolTipText("null");
        jtpalabra3.setBounds(new Rectangle(190, 10, 41, 15));
        jtpalabra3.setText("Folios:");
        jtfoliosp.setBounds(new Rectangle(190, 25, 41, 20));
        jLabel11.setBounds(new Rectangle(10, 55, 112, 15));
        jLabel11.setText("Oficina Receptora:");
        jtoficrec.setBounds(new Rectangle(10, 70, 365, 20));
        jLabel10.setToolTipText("null");
        jLabel10.setBounds(new Rectangle(265, 10, 80, 15));
        jLabel10.setText("Observacion:");
        jtobservaciones.setBounds(new Rectangle(265, 25, 415, 20));
        jLabel9.setText("Hora:");
        jLabel9.setBounds(new Rectangle(105, 10, 32, 15));
        jPanel3.setLayout(null);
        jPanel3.setBorder(BorderFactory.createLineBorder(new Color(5, 25, 135), 1));
        jPanel3.setBounds(new Rectangle(5, 15, 780, 95));
        jLabel8.setOpaque(true);
        jLabel8.setFont(new Font("Dialog", 1, 14));
        jLabel8.setBounds(new Rectangle(15, 5, 175, 15));
        jLabel8.setText(" Datos del Ultimo Pase: ");
        jLabel5.setToolTipText("null");
        jtpalabra2.setToolTipText("null");
        jLabel7.setBounds(new Rectangle(5, 15, 94, 20));
        jLabel7.setText("Nº Documento:");
        bnuevo.setMnemonic('n');
        bnuevo.setBounds(new Rectangle(250, 505, 90, 25));
        bnuevo.setText("Nuevo");
        imprimirTexto.setMnemonic('t');
        imprimirTexto.setText("Imprimir Texto del Pase");
        jPopupMenu1.setLabel("jPopupMenu1");
        jPanel5.setLayout(null);
        jPanel5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        bimprimir.setBounds(new Rectangle(5, 500, 45, 30));
        bimprimir.setMnemonic('i');
        bregistrar.addActionListener(this);
        bmodificar.addActionListener(this);
        bnuevo.addActionListener(this);
        beliminar.addActionListener(this);
        bcerrar.addActionListener(this);
        jtexpte.addKeyListener(this);
        jtfechae.addKeyListener(this);
        jtpalabra2.addKeyListener(this);
        jtiniciador.addKeyListener(this);
        imprimirTexto.addActionListener(this);
        timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        datosx = jPanel1.VDatos();
                        if (!datos1.equals(datosx)) {
                            datos1 = datosx;
                               System.out.println(datos1);
                            jtemisor.setText(datos1.elementAt(6).toString());
                            jtobservemi.setText(datos1.elementAt(7).toString());
                            jtreceptor.setText(datos1.elementAt(11).toString());
                            jtobservrec.setText(datos1.elementAt(12).toString());
                            jtfecha_rec.setText(OP_Proced.Fecha2(datos1.elementAt(15).toString(), true)); //  datos1.elementAt(15).toString());
                            jthora_rec.setText(datos1.elementAt(16).toString());
                            jtcantfolio.setText(datos1.elementAt(13).toString());
                            if (datos1.elementAt(7).toString().length() > 110) {
                                bimprimir.setEnabled(true);
                            } else
                                bimprimir.setEnabled(false);
                        }
                    } catch (Exception x) {
                        OP_Proced.Mensaje(x.getMessage(), "ERROR:");
                    }
                }
            }
            , 0, 500);
        if (idinst.length() > 0) {
            //System.out.println("SELECT * FROM files.instlegal WHERE idinst="+ idinst);
            ResultSet Reg = OP_Proced.exConsulta("SELECT * FROM files.instlegal WHERE idinst=" + idinst);
            if (Reg.next()) {
                jtexpte.setText(Reg.getString(3) + "-" + OP_Proced.getCampo("SELECT letra FROM files.tiposletra WHERE idorganismo=" + Reg.getString(3) + " AND idtipo=" + Reg.getString(4)) + Reg.getString(5) + "-" + Reg.getString(6));
                jtfechae.setText(OP_Proced.Fecha2(Reg.getString(7), true));
                jttipoiniciador.setText(OP_Proced.getCampo("SELECT '( '||abrev||' )'||' - '||descripcion FROM files.tiposiniciador WHERE idtipo=" + Reg.getString(8)));
                jtiniciador.setText(Reg.getString(10));
                jtcontenido.setText(Reg.getString(11));
                jtfoliose.setText(Reg.getString(13));
                jtcatastro.setText(OP_Proced.getCampo("SELECT catastro FROM cepax.catastros WHERE idcatastro=" + Reg.getString(15)));
            }
        }
        ActualizaTabla();
        String idinst2 = OP_Proced.getCampo("SELECT idinst FROM files.instxinst WHERE estado<>'*' AND idinstref=" + idinst);
        jtcontenido.setWrapStyleWord(true);
        jtcontenido.setLineWrap(true);
        jtcontenido.setMargin(new Insets(5, 5, 5, 5));
        jtobservemi.setEditable(false);
        jtobservemi.setEditorKit(new HTMLEditorKit());
        centralPanel.setBounds(new Rectangle(0, 0, 800, 545));
        centralPanel.setLayout(null);
        jScrollPane3.setBounds(new Rectangle(5, 5, 765, 120));
        if (idinst2.length() > 0) {
            OP_Proced.Mensaje("Este documento (o expediente) corre adjunto/anexo al documento '" + OP_Proced.getCampo("SELECT instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio FROM files.instlegal,files.tiposletra " + " WHERE idinst=" + idinst2 + " AND instlegal.idtipodoc = tiposletra.idtipo AND instlegal.idorganismo = tiposletra.idorganismo") + "'.\nLos pases deberan registrarse en el documento (o expediente) cabecera", "Documento Adjuntado/Anexado");
            ActivaBotones(false);
        } else {
            //ActivaBotones(true);
        }
        bimprimir.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        );
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Pases");
    }


    private void ActivaBotones(boolean op) {
        bnuevo.setEnabled(op);
        bregistrar.setEnabled(op);
        bmodificar.setEnabled(op);
        beliminar.setEnabled(op);
    }

    private void UltimoPase() {
        try {
            //  System.out.println("SELECT MAX(fechapase||' '||horapase) FROM files.pases WHERE estado<>'*' AND idinst="+ idinst);
            ResultSet Reg = OP_Proced.exConsulta("SELECT MAX(fechapase||' '||horapase) FROM files.pases WHERE estado<>'*' AND idinst=" + idinst);
            if (Reg.next()) {
                ResultSet Reg2 = OP_Proced.exConsulta("SELECT * FROM files.pases WHERE estado<>'*' AND idinst=" + idinst + " AND fechapase||' '||horapase='" + Reg.getString(1) + "'");
                if (Reg2.next()) {
                    jtfechap.setText(OP_Proced.Fecha2(Reg2.getString(2), true));
                    jthorap.setText(OP_Proced.Hora(Reg2.getString(3), true, false));
                    idoficrec = Reg2.getString(7);
                    jtoficrec.setText(OP_Proced.getCampo("SELECT descripcion FROM files.tiposoficina WHERE idtipo=" + Reg2.getString(7)));
                    jtrec.setText(OP_Proced.getCampo("SELECT apellido||', '||nombre FROM personas WHERE idpersona=" + Reg2.getString(8)));
                    idreceptor = Reg2.getString(8);
                    jtobservaciones.setText(Reg2.getString(9));
                    jtfoliosp.setText(Reg2.getString(10));
                    jtestado.setText(Reg2.getString(13));
                    if (jtestado.getText().equals("Enviado")) {
                        bnuevo.setEnabled(false);
                        bregistrar.setEnabled(true);
                    } else if (jtestado.getText().equals("Inactivo")) {
                        //  System.out.println("Inactivo");
                        bnuevo.setEnabled(false);
                        bregistrar.setEnabled(false);
                    } else {
                        bnuevo.setEnabled(true);
                        bregistrar.setEnabled(false);
                    }
                }
            }
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "ERROR: ");
        }
    }

    public void ActualizaTabla() {
        Consulta = "SELECT idinst,fechapase,horapase,idoficemi,idemisor," + "(SELECT descripcion FROM files.tiposoficina WHERE idtipo=idoficemi) as ofic_emi," 
                    + "public.isnull( (SELECT apellido||', '||nombre FROM personas WHERE idpersona=idemisor),'')::character varying as emisor," 
                    + "observemi,idoficrec,idreceptor,(SELECT descripcion FROM files.tiposoficina WHERE idtipo=idoficrec) as ofic_rec," 
                    + "(SELECT apellido||', '||nombre FROM personas WHERE idpersona=idreceptor) as receptor," 
                    + "observrec,cfoliorec,estadopase,public.isnull(fecha_rec::character varying,''::character varying)::character varying as fecha_rec,public.isnull(hora_rec::character varying,''::character varying)::character varying as hora_rec,estado" 
                    + " FROM files.pases " 
                    + " WHERE estado<>'*' AND idinst=" + idinst + " ORDER BY fechapase,horapase";
        System.out.println(Consulta);         
        ConsultaCount = "SELECT count(*) FROM files.pases WHERE estado<>'*' AND idinst=" + idinst;
        jPanel1.ActualizaTabla(this, Consulta, ConsultaCount);
        UltimoPase();
        datos1.clear();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == bcerrar) {
                //this.dispose();
                getParentInternalFrame().close();
            } else if (e.getSource() == bnuevo) {
                nuevo = new frmPases("", idinst, jtexpte.getText(), jtcatastro.getText(), "", jtfoliosp.getText(), idoficrec, idreceptor);
                nuevo.setParentMain(this);
                ExtendedInternalFrame nuevoContainer = new ExtendedInternalFrame("Nuevo Pase");
                nuevoContainer.setCentralPanel(nuevo);
                nuevoContainer.show();
                /*OP_Proced.CentraVentana(nuevo);
                nuevo.setModal(true);
                nuevo.setVisible(true);
                ActualizaTabla();*/
            } else if (e.getSource() == bmodificar) {
		System.out.println("DAtos 1: "+ datos1.isEmpty());
		System.out.println("DAtos 1: "+ !datos1.isEmpty());
		System.out.println("DAtos 1 size : "+ datos1.size());
                if (!datos1.isEmpty()) {
                    //if (!datos1.elementAt(17).toString().equals("a")) {
                        OP_Proced.exActualizar('a', "UPDATE files.pases set estado='a' WHERE idinst=" + datos1.elementAt(0).toString() + " AND fechapase='" + OP_Proced.Fecha2(datos1.elementAt(1).toString(), false) + "' AND horapase='" + datos1.elementAt(2).toString() + "'");
                        frmPases nuevo = new frmPases("SELECT * FROM files.pases WHERE idinst=" + datos1.elementAt(0).toString() + " AND fechapase='" + OP_Proced.Fecha2(datos1.elementAt(1).toString(), false) + "' AND horapase='" + datos1.elementAt(2).toString() + "'", idinst, jtexpte.getText(), jtcatastro.getText(), "", "", "", "");
                        nuevo.setParentMain(this);
                        ExtendedInternalFrame nuevoContainer = new ExtendedInternalFrame("Modificar Pase");
                        nuevoContainer.setCentralPanel(nuevo);
                        nuevoContainer.show();
                        /*OP_Proced.CentraVentana(nuevo);
                        nuevo.setModal(true);
                        nuevo.setVisible(true);*/
                        System.out.println("UPDATE files.pases set estado='' WHERE idinst=" + datos1.elementAt(0).toString() + " AND fechapase='" + OP_Proced.Fecha2(datos1.elementAt(1).toString(), false) + "' AND horapase='" + datos1.elementAt(2).toString() + "'");
                        //OP_Proced.exActualizar('a', "UPDATE files.pases set estado='' WHERE idinst=" + datos1.elementAt(0).toString() + " AND fechapase='" + OP_Proced.Fecha2(datos1.elementAt(1).toString(), false) + "' AND horapase='" + datos1.elementAt(2).toString() + "'");
                    /*} else {
                        OP_Proced.Errores(0);
                    }*/
                    ActualizaTabla();
                } else {
                    OP_Proced.Errores(2);
                }
            } else if (e.getSource() == bregistrar) {
                if (!OP_Proced.getCampo("SELECT estado FROM files.pases WHERE idinst=" + idinst + " AND fechapase='" + OP_Proced.Fecha2(jtfechap.getText(), false) + "' AND horapase='" + jthorap.getText() + "'").equals("a")) {
                    OP_Proced.exActualizar('a', "UPDATE files.pases set estado='a' WHERE idinst=" + idinst + " AND fechapase='" + OP_Proced.Fecha2(jtfechap.getText(), false) + "' AND horapase='" + jthorap.getText() + "'");
                    // System.out.println("SELECT * FROM files.pases WHERE idinst="+ idinst +" AND fechapase='"+ Proced.Fecha(jtfechap.getText(),false) +"' AND horapase='"+ jthorap.getText() +"'");
                    frmPases nuevo = new frmPases("SELECT * FROM files.pases WHERE idinst=" + idinst + " AND fechapase='" + OP_Proced.Fecha2(jtfechap.getText(), false) + "' AND horapase='" + jthorap.getText() + "'", idinst, jtexpte.getText(), jtcatastro.getText(), "REGISTRAR", "", "", "");
                    /*OP_Proced.CentraVentana(nuevo);
                    nuevo.setModal(true);*/
                    nuevo.setVisible(true);
                    OP_Proced.exActualizar('a', "UPDATE files.pases set estado='' WHERE idinst=" + idinst + " AND fechapase='" + OP_Proced.Fecha2(jtfechap.getText(), false) + "' AND horapase='" + jthorap.getText() + "'");
                } else {
                    OP_Proced.Errores(0);
                }
             //   ActualizaTabla();
            } else if (e.getSource() == beliminar) {
                if (!datos1.isEmpty()) {
                    String Q = "UPDATE files.pases set estado='*' WHERE idinst=" + datos1.elementAt(0).toString() + " AND fechapase='" + OP_Proced.Fecha2(datos1.elementAt(1).toString(), false) + "' AND horapase='" + datos1.elementAt(2).toString() + "'";
                    OP_Proced.exActualizar('b', Q);
                    ActualizaTabla();
                } else {
                    OP_Proced.Errores(2);
                }
            } else if (e.getSource() == imprimirTexto) {
                if (!datos1.isEmpty()) {
                    //System.out.println("tamaño..."+datos1.elementAt(7).toString().length());
                    /*BasicReport report = new BasicReport(Pases.class.getResource("xml/TextPassSelected.xml"));
		    report.addTableModel(new DefaultTableModel());
                    report.setProperty("textopase",datos1.elementAt(7).toString());
                    report.doReport();*/
                    HTMLTextoPase(false);
                    try {
                        HTMLBrowser navegador = new HTMLBrowser(OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + "texto_pase" + idinst + ".html");
                        navegador.setModal(true);
                        navegador.setVisible(true);
                    } catch (FileNotFoundException f) {
                        f.printStackTrace();
                    }
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void keyReleased(KeyEvent k) {
    }

    public void keyTyped(KeyEvent k) {
    }

    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_ENTER) {
        }
    }

    private void HTMLTextoPase(boolean nuevo) {
        String archivo = "texto_pase" + idinst;
        try {
            String htmlPath = OP_Proced.getRutaInforme() + OP_Proced.getSeparador() + archivo + ".html";
            FileWriter htmlFile = new FileWriter(htmlPath, false);
            BufferedWriter log = new BufferedWriter(htmlFile);
            if (nuevo) {
                NuevoDoc(htmlFile);
            } else {
                //System.out.println("A:" + panel.getDocumentBody().length());      
                /*if (panel.getDocumentBody().length() < 30)
        {
            System.out.println("Generar nuevo");
            NuevoDoc(htmlFile);
        } else 
        {*/
                // htmlFile.write(panel.getDocumentBody());  /** cuerpo del HTML **/  
                // NuevoDoc(htmlFile); 
                htmlFile.write(jtobservemi.getText());
                //}
            }
            htmlFile.write("</BODY>\n");
            htmlFile.write("</HTML>\n");
            htmlFile.close();
        } catch (EmptyStackException e) {
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
        }
    }

    /**
   * METODO QUE GENERA UN DOCUMENTOEN BLANCO
   * @param htmlFile: ARCHIVO DONDE SE GENERARA EL DOCUMENTO
   */
    private void NuevoDoc(FileWriter htmlFile) {
        String titulo = "Texto del Pase";
        String subtitulo = "Correspondiente al Documento Nº " + jtexpte.getText();
        boolean encabezado = true;
        String archivo = "texto_pase" + idinst;
        boolean nuevo = false;
        String textopase = jtobservemi.getText();
        try {
            if (OP_Proced.getSeparador().equals("\\")) {
                OP_Proced.setSeparador("\\\\");
            }
            htmlFile.write("<HTML>\n");
            htmlFile.write("<HEAD>\n");
            htmlFile.write("<TITLE>" + titulo + "</TITLE>\n");
            htmlFile.write("</HEAD>\n");
            htmlFile.write("<BODY>\n");
            // ENCABEZADO
            if (encabezado) {
                htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg' height=40 width=40></TD>\n");
                htmlFile.write("<TD WIDTH=80% ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='" + OP_Proced.getColorAzul() + "'><B>" + titulo + "</B></FONT><BR>\n");
                htmlFile.write("<TD WIDTH=10%><img src='" + OP_Proced.getRutaIcono() + OP_Proced.getSeparador() + "logo.jpg' height=40 width=40></TD>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("</TABLE>\n");
            }
            // SUBTITULO
            if (subtitulo.length() > 0) {
                htmlFile.write("<TABLE align=center WIDTH=100% BORDER=0 STYLE='BORDER-COLLAPSE:COLLAPSE' CELLPADDING=0 CELLSPACING=0>\n");
                htmlFile.write("<TR>\n");
                htmlFile.write("<TD ALIGN=CENTER><FONT size=4 FACE='Arial' COLOR='" + OP_Proced.getColorAzul() + "'><B>" + subtitulo + "</B></FONT><BR>\n");
                htmlFile.write("</TR>\n");
                htmlFile.write("</TABLE><BR>\n");
            }
        } catch (EmptyStackException e) {
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "");
        }
    }

    public void setParentMain(Expedientes _parentMain) {
        parentMain = _parentMain;
    }
}
