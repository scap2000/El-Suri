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
 * Anexos_InstLegal.java
 *
 * */
package org.digitall.projects.gdigitall.expedientes;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.projects.gdigitall.lib.classes.ValidaDato;
import org.digitall.projects.gdigitall.lib.components.CRegistros;
import org.digitall.projects.gdigitall.lib.components.SelectorFecha;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

//public class Anexos_InstLegal extends JDialog implements ActionListener, KeyListener {
public class Anexos_InstLegal extends BasicPrimitivePanel implements ActionListener, KeyListener {
    private JTextArea jtcontenido = new JTextArea();
    private JTextArea jtobservaciones = new JTextArea();
    private JTextArea jtextracto = new JTextArea();
    private JTextArea jtobservaciones1 = new JTextArea();
    private JTextField jthasta = new JTextField();
    private JTextField jtdesde = new JTextField();
    private JTextField jtpalabra = new JTextField();
    private JTextField jtidorganismo = new JTextField();
    private JTextField jtletra = new JTextField();
    private JTextField jtnroexp = new JTextField();
    private JTextField jtanio = new JTextField();
    private JTextField jtprofesional = new JTextField();
    private JTextField jttipoiniciador = new JTextField();
    private JTextField jtfolio = new JTextField();
    private JTextField jtasunto = new JTextField();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel4 = new JPanel();
    // private JPanel jPanel5 = new JPanel();
    private JPanel jPanel7 = new JPanel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel9 = new JLabel();
    private JLabel jLabel11 = new JLabel();
    private JLabel jLabel12 = new JLabel();
    private JLabel jLabel13 = new JLabel();
    private JLabel jLabel14 = new JLabel();
    private JLabel jLabel15 = new JLabel();
    private JLabel jLabel16 = new JLabel();
    private JLabel jLabel17 = new JLabel();
    private JLabel jLabel18 = new JLabel();
    private JLabel jLabel20 = new JLabel();
    private JLabel jLabel110 = new JLabel();
    private JLabel jtpalabra3 = new JLabel();
    private JLabel jtpalabra5 = new JLabel();
    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private JComboBox jctipoinst = new JComboBox();
    private JCheckBox jchekbuscar = new JCheckBox();
    private JButton bagregar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/agregar.gif")));
    private JButton bquitar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/quitar.gif")));
    private JButton bcerrar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    private JButton bbuscar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/buscar.gif")));
    private JButton bmodificar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/modificar.gif")));
    private String idtiporef = "";
    private String Consulta1 = "", ConsultaCount1 = "", cfiltro1 = "", idtipoinst = "", expte = "";
    private String Consulta2 = "", ConsultaCount2 = "", cfiltro2 = "", idinst = "";
    private Vector datos1, datosx, datos2, datosy = new Vector();
    private Timer timer1 = new Timer();
    private Timer timer2 = new Timer();
    private int[] vcol1 = { 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 8, 4, 3, 1, 0 };
    private int[] tcol1 = { 80, 150, 90, 70, 80 };
    private int[] vcol2 = { 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 11, 10, 9, 8, 7, 6, 5, 2, 1 };
    private int[] tcol2 = { 75, 150, 80, 250 };
    private CRegistros jPanel6 = new CRegistros(30, "files.", "files.instxinst", vcol1, tcol1);
    private CRegistros jPanel5 = new CRegistros(30, "files.", "files.instlegal", vcol2, tcol2);
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JScrollPane jScrollPane3 = new JScrollPane();
    //******************************************************
    private Expedientes parentMain;
    private JPanel jPanel1 = new JPanel();

    /**
     * Formulario para cargar los Adjuntos minY Anexos a un Expediente
     * (Adjuntos: cuando se agregan al expediente en cuestion, otros expedientes)
     * (Anexos: cuando se agregan al expediente en cuestion, cualquier otro documento o nota, que no sean expedientes)
     *
     * @param Expte: indica el numero de expediente al cual se va a adjuntar o anexar otros documentos
     * @param IdInst: clave que representa al expediente en cuestion
     * @param IdTipoRef: me indica el tipo de documento (Anexo o Adjunto), segun lo que sea cargo el combo jctipoinst --> Tipo Documento
     */
    public Anexos_InstLegal(String IdTipoRef, String IdInst, String Expte) {
        try {
            idtiporef = IdTipoRef;
            idinst = IdInst;
            expte = Expte;
            jbInit();
        } catch (SQLException e) {
            OP_Proced.Mensaje(e.getMessage().toUpperCase(), "Acceso a la Base de Datos no Autorizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(791, 526));
        //this.getContentPane().setLayout(null);
        jPanel1.add(jPanel7, null);
        jPanel1.add(jTabbedPane1, null);
        jPanel1.add(bquitar, null);
        jPanel1.add(bcerrar, null);
        jPanel1.add(bmodificar, null);
        jPanel1.add(bagregar, null);
        this.add(jPanel1, null);
        this.add(jtcontenido, null);
        jctipoinst.setBounds(new Rectangle(10, 25, 245, 20));
        jchekbuscar.setText("Numero:");
        jchekbuscar.setBounds(new Rectangle(60, 65, 80, 15));
        jchekbuscar.setSelected(true);
        jchekbuscar.addActionListener(this);
        jtcontenido.setBounds(new Rectangle(0, 0, 0, 15));
        jthasta.setBounds(new Rectangle(95, 190, 70, 20));
        jtdesde.setBounds(new Rectangle(10, 190, 70, 20));
        jtpalabra.setBounds(new Rectangle(10, 135, 245, 20));
        jtpalabra3.setText("Palabra Clave:");
        jtpalabra3.setBounds(new Rectangle(10, 120, 86, 15));
        jtpalabra5.setText("Tipo Documento:");
        jtpalabra5.setBounds(new Rectangle(10, 10, 107, 15));
        jtidorganismo.setForeground(Color.red);
        jtidorganismo.setFont(new Font("Dialog", 0, 13));
        jtidorganismo.setBounds(new Rectangle(10, 80, 35, 20));
        jtletra.setForeground(Color.red);
        jtletra.setFont(new Font("Dialog", 0, 13));
        jtletra.setBounds(new Rectangle(60, 80, 45, 20));
        jtnroexp.setForeground(Color.red);
        jtnroexp.setFont(new Font("Dialog", 1, 15));
        jtnroexp.setBounds(new Rectangle(107, 80, 95, 20));
        jtanio.setForeground(Color.red);
        jtanio.setFont(new Font("Dialog", 0, 13));
        jtanio.setBounds(new Rectangle(215, 80, 40, 20));
        jtnroexp.addKeyListener(this);
        jtpalabra.addKeyListener(this);
        jtdesde.addKeyListener(this);
        jthasta.addKeyListener(this);
        jtanio.addKeyListener(this);
        jtletra.addKeyListener(this);
        jtidorganismo.addKeyListener(this);
        jtidorganismo.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtletra.setDocument(new ValidaDato(ValidaDato.MAYUSCULA));
        jtnroexp.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtanio.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtextracto.setWrapStyleWord(true);
        jtobservaciones.setWrapStyleWord(true);
        jtextracto.setLineWrap(true);
        jtobservaciones.setLineWrap(true);
        jtextracto.setMargin(new Insets(5, 5, 5, 5));
        jtobservaciones.setMargin(new Insets(5, 5, 5, 5));
        jtobservaciones1.setWrapStyleWord(true);
        jtobservaciones1.setMargin(new Insets(5, 5, 5, 5));
        jtobservaciones1.setLineWrap(true);
        jtidorganismo.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        jtletra.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        jtasunto.setBackground(Color.white);
        jtasunto.setEditable(false);
        jtasunto.setBounds(new Rectangle(450, 50, 310, 20));
        jtfolio.setBackground(Color.white);
        jtfolio.setEditable(false);
        jtfolio.setBounds(new Rectangle(555, 15, 50, 20));
        jttipoiniciador.setBackground(Color.white);
        jttipoiniciador.setEditable(false);
        jttipoiniciador.setBounds(new Rectangle(110, 50, 255, 20));
        jtprofesional.setBackground(Color.white);
        jtprofesional.setEditable(false);
        jtprofesional.setBounds(new Rectangle(95, 15, 270, 20));
        jPanel2.setBounds(new Rectangle(5, 5, 260, 220));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel2.setLayout(null);
        jPanel2.add(jtpalabra5, null);
        jPanel2.add(jctipoinst, null);
        jPanel2.add(jthasta, null);
        jPanel2.add(jLabel6, null);
        jPanel2.add(jtdesde, null);
        jPanel2.add(jLabel9, null);
        jPanel2.add(jtpalabra, null);
        jPanel2.add(jtpalabra3, null);
        jPanel2.add(jtidorganismo, null);
        jPanel2.add(jLabel20, null);
        jPanel2.add(jtletra, null);
        jPanel2.add(jtnroexp, null);
        jPanel2.add(jLabel16, null);
        jPanel2.add(jLabel8, null);
        jPanel2.add(jtanio, null);
        jPanel2.add(jLabel110, null);
        jPanel2.add(jchekbuscar, null);
        jPanel2.add(bbuscar, null);
        jPanel3.add(jLabel17, null);
        jPanel3.add(jLabel15, null);
        jScrollPane2.getViewport().add(jtobservaciones, null);
        jPanel3.add(jScrollPane2, null);
        jScrollPane1.getViewport().add(jtextracto, null);
        jPanel3.add(jScrollPane1, null);
        jPanel3.add(jtprofesional, null);
        jPanel3.add(jLabel11, null);
        jPanel3.add(jLabel12, null);
        jPanel3.add(jttipoiniciador, null);
        jPanel3.add(jLabel13, null);
        jPanel3.add(jtfolio, null);
        jPanel3.add(jLabel14, null);
        jPanel3.add(jtasunto, null);
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel3.setLayout(null);
        jPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel4.setLayout(null);
        jPanel4.add(jPanel2, null);
        jPanel4.add(jPanel5, null);
        jPanel5.setBounds(new Rectangle(275, 5, 495, 220));
        jPanel5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel5.setLayout(null);
        jPanel5.Redimensiona();
        jPanel6.setLayout(null);
        jPanel6.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel6.setBounds(new Rectangle(0, 0, 490, 220));
        jPanel6.Redimensiona();
        jLabel6.setText("F. Hasta:");
        jLabel6.setBounds(new Rectangle(95, 175, 52, 15));
        jScrollPane3.getViewport().add(jtobservaciones1, null);
        jPanel7.add(jScrollPane3, null);
        jPanel7.add(jLabel18, null);
        jPanel7.add(jPanel6, null);
        jPanel7.setBounds(new Rectangle(5, 5, 780, 220));
        jPanel7.setLayout(null);
        jPanel7.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jLabel8.setBounds(new Rectangle(215, 65, 30, 15));
        jLabel8.setText("Año:");
        jLabel9.setText("F. Desde:");
        jLabel9.setBounds(new Rectangle(10, 175, 56, 15));
        jLabel11.setText("Profesional:");
        jLabel11.setBounds(new Rectangle(15, 20, 75, 15));
        jLabel12.setBounds(new Rectangle(15, 55, 90, 15));
        jLabel12.setText("Tipo Iniciador:");
        jLabel13.setBounds(new Rectangle(430, 20, 120, 15));
        jLabel13.setText("Cant. Folios inicial:");
        jLabel14.setBounds(new Rectangle(395, 55, 50, 15));
        jLabel14.setText("Asunto:");
        jLabel15.setText("Extracto:");
        jLabel15.setBounds(new Rectangle(15, 90, 53, 15));
        jLabel16.setFont(new Font("Dialog", 1, 20));
        jLabel16.setBounds(new Rectangle(200, 80, 15, 20));
        jLabel16.setText("-");
        jLabel17.setText("Observaciones:");
        jLabel17.setBounds(new Rectangle(395, 90, 94, 15));
        jLabel18.setBounds(new Rectangle(500, 10, 95, 15));
        jLabel18.setText("Observaciones:");
        jLabel20.setBounds(new Rectangle(10, 65, 26, 15));
        jLabel20.setText("Org:");
        jLabel110.setFont(new Font("Dialog", 1, 20));
        jLabel110.setBounds(new Rectangle(45, 80, 15, 20));
        jLabel110.setText("-");
        jScrollPane1.setBounds(new Rectangle(15, 105, 360, 115));
        jScrollPane2.setBounds(new Rectangle(395, 105, 360, 115));
        jScrollPane3.setBounds(new Rectangle(500, 25, 275, 190));
        jPanel1.setBounds(new Rectangle(0, 0, 790, 525));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        
        jTabbedPane1.setBounds(new Rectangle(5, 230, 780, 255));
        jTabbedPane1.addTab("Buscar Documentos/Expedientes", jPanel4);
        jTabbedPane1.addTab("Mas Datos del Documento (seleccionado)", jPanel3);
        bagregar.setText("Agregar");
        bagregar.setBounds(new Rectangle(5, 490, 105, 25));
        bagregar.setMnemonic('a');
        bquitar.setText("Quitar");
        bquitar.setBounds(new Rectangle(115, 490, 95, 25));
        bquitar.setMnemonic('q');
        bcerrar.setText("Cerrar");
        bcerrar.setBounds(new Rectangle(695, 490, 90, 25));
        bcerrar.setMnemonic('c');
        bbuscar.setMnemonic('b');
        bbuscar.setBounds(new Rectangle(200, 189, 55, 23));
        bbuscar.setText("...");
        bmodificar.setMnemonic('m');
        bmodificar.setBounds(new Rectangle(215, 490, 110, 25));
        bmodificar.setText("Modificar");
        bagregar.addActionListener(this);
        bquitar.addActionListener(this);
        bcerrar.addActionListener(this);
        bbuscar.addActionListener(this);
        bmodificar.addActionListener(this);
        bquitar.setMargin(new Insets(2, 3, 2, 14));
        bmodificar.setMargin(new Insets(2, 3, 2, 14));
        bagregar.setMargin(new Insets(2, 3, 2, 14));
        datos1 = jPanel6.VDatos();
        datos2 = jPanel5.VDatos();
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
        // COMBO DEL TIPO DE DOCUMENTO (EXPEDIENTES, NOTAS, ETC.)
        jctipoinst.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        idtipoinst = OP_Proced.getCampo("SELECT idtipo FROM files.tiposinstlegal WHERE descripcion='" + jctipoinst.getSelectedItem() + "'");
                    }
                }
            }
        );
        // TEMPORIZADOR QUE ME REFRESCA EL VECTOR DATOS, EL CUAL CONTIENE LOS DATOS DE UNA TUPLA (LA SELECCIONADA)
        timer1.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        datosx = jPanel6.VDatos();
                        // CADA VEZ QUE SE REALIZA UN CLICK SOBRE LA TABLA SUPERIOR, VISUALIZA EL CAMPO OBSERVACION EN jtobservaciones1
                        if (!datos1.equals(datosx)) {
                            datos1 = datosx;
                            jtobservaciones1.setText(datos1.elementAt(8).toString());
                        }
                    } catch (Exception x) {
                        OP_Proced.Mensaje(x.getMessage(), "ERROR:");
                    }
                }
            }
            , 0, 500);
        /* CADA VEZ QUE SE REALIZA UN CLICK SOBRE LA TABLA INFERIOR,
      * VISUALIZA LOS CAMPOS SOBRE LA PESTAÑA --> 'Mas Datos del Documento (seleccionado)'*/
        timer2.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        datosy = jPanel5.VDatos();
                        if (!datos2.equals(datosy)) {
                            datos2 = datosy;
                            // System.out.println(datos1);
                            jttipoiniciador.setText(OP_Proced.getCampo("SELECT descripcion||' - ('||abrev||')' FROM files.tiposiniciador WHERE idtipo=" + datos2.elementAt(8).toString()));
                            jtasunto.setText(OP_Proced.getCampo("SELECT descripcion||' - ('||abrev||')' FROM files.tiposasunto WHERE idtipo=" + datos2.elementAt(9).toString()));
                            jtextracto.setText(datos2.elementAt(13).toString());
                            jtobservaciones.setText(datos2.elementAt(14).toString());
                            jtfolio.setText(datos2.elementAt(15).toString());
                            jtprofesional.setText(datos2.elementAt(19).toString());
                        }
                    } catch (Exception x) {
                        OP_Proced.Mensaje(x.getMessage(), "ERROR:");
                    }
                }
            }
            , 0, 500);
        /* TIPOREF=4 ME INDICA ADJUNTO, ENTONCES CARGO ESTE COMBO CON SOLAMENTE EL TIPO DE DOCUMENTO. 
    POR LO CONTRARIO LO CARGO CON EL RESTO DE LOS TIPOS DE DOCUMENTOS*/
        if (idtiporef.equals("4")) {
            OP_Proced.CargaCombo(jctipoinst, "SELECT descripcion FROM files.tiposinstlegal WHERE estado<>'*' AND descripcion='EXPEDIENTE'", "");
            this.setTitle("Listado de Expedientes Adjuntos  -->  al Doc.: ' " + expte + " '");
        } else {
            OP_Proced.CargaCombo(jctipoinst, "SELECT descripcion FROM files.tiposinstlegal WHERE estado<>'*' AND descripcion<>'EXPEDIENTE'", "");
            this.setTitle("Listado de Documentos Anexados   -->  al Doc.: ' " + expte + " '");
        }
        ActualizaTabla1();
    }
    
    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Anexos");
    }

    public void ActualizaTabla1() {
        Consulta1 = "SELECT instxinst.idinst,idtiporef,idinstref,idtipoinst,instlegal.idtipodoc," 
                    + "(instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio) as expte," 
                    + "catastros.catastro,instxinst.fecha,instxinst.observaciones,estadoanexo,anio,nroexp,fechaexp," 
                    + "idtipoiniciador,idtipoasunto,tiposiniciador.abrev as iniciador," 
                    + "tiposasunto.abrev as asunto,iniciante,extracto,instlegal.observaciones,cantfolios,estadoexp," 
                    + "instlegal.idcatastro,instlegal.idprofesional,apellido||', '||nombre as profesional," 
                    + "instxinst.estado,tiposletra.letra" 
                    + " FROM files.instlegal,files.instxinst,files.tiposorganismo,files.tiposiniciador," 
                    + "files.tiposasunto,cepax.catastros,sopsalta.profesionales,files.tiposletra" 
                    + " WHERE instxinst.estado<>'*' " 
                    + " AND instxinst.idinst=" + idinst + " AND idtiporef=" + idtiporef + " AND instlegal.idinst=instxinst.idinstref" 
                    + " AND instlegal.idtipodoc=tiposletra.idtipo" + " AND instlegal.idorganismo=tiposletra.idorganismo" 
                    + " AND instlegal.idorganismo=tiposorganismo.idorganismo" 
                    + " AND instlegal.idtipoiniciador=tiposiniciador.idtipo" 
                    + " AND instlegal.idtipoasunto=tiposasunto.idtipo" 
                    + " AND instlegal.idcatastro=catastros.idcatastro" 
                    + " AND instlegal.idprofesional=profesionales.idprofesional" 
                    + " order by instxinst.fecha,nroexp,anio,fechaexp";
        System.out.println(Consulta1);        
        ConsultaCount1 = "SELECT count(*) FROM files.instxinst WHERE instxinst.estado<>'*' " + " AND instxinst.idinst=" + idinst + " AND idtiporef=" + idtiporef + cfiltro1;
        //System.out.println(ConsultaCount1);         
        jPanel6.ActualizaTabla(this, Consulta1, ConsultaCount1);
        datos1.clear();
    }

    public void ActualizaTabla2() {
        Consulta2 = "SELECT idinst,idtipoinst,instlegal.idtipodoc,(instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio) as expte," 
                    + "catastros.catastro,anio,nroexp, fechaexp, idtipoiniciador,idtipoasunto,tiposiniciador.abrev as iniciador,tiposasunto.abrev as asunto," 
                    + "iniciante,extracto,instlegal.observaciones,cantfolios,estadoexp,instlegal.idcatastro," 
                    + "instlegal.idprofesional,apellido||', '||nombre as profesional,instlegal.estado,tiposletra.letra,cantfolios " 
                    + " FROM files.instlegal,files.tiposorganismo,files.tiposiniciador,files.tiposasunto,cepax.catastros,sopsalta.profesionales,files.tiposletra" 
                    + " WHERE instlegal.estado<>'*' " 
                    + " AND idtipoinst=" + idtipoinst + cfiltro2 + " AND idinst<>" + idinst + " AND idinst NOT IN (SELECT idinstref FROM files.instxinst" 
                    + " WHERE instxinst.estado<>'*' " + " AND instxinst.idinst=" + idinst + " AND idtiporef=" + idtiporef + ")" 
                    + " AND instlegal.idtipodoc=tiposletra.idtipo " 
                    + " AND instlegal.idorganismo=tiposletra.idorganismo " 
                    + " AND instlegal.idorganismo=tiposorganismo.idorganismo " 
                    + " AND instlegal.idtipoiniciador=tiposiniciador.idtipo  " 
                    + " AND instlegal.idtipoasunto=tiposasunto.idtipo " 
                    + " AND instlegal.idcatastro=catastros.idcatastro " 
                    + " AND instlegal.idprofesional=profesionales.idprofesional " 
                    + " order by nroexp,anio,fechaexp";
        //System.out.println(Consulta2);        
        ConsultaCount2 = "SELECT count(*) FROM files.instlegal,cepax.catastros,files.tiposletra" + " WHERE instlegal.estado<>'*' " + " AND idtipoinst=" + idtipoinst + cfiltro2 + " AND instlegal.idcatastro=catastros.idcatastro " + " AND instlegal.idtipodoc=tiposletra.idtipo" + " AND instlegal.idorganismo=tiposletra.idorganismo";
        System.out.println(ConsultaCount2);
        jPanel5.ActualizaTabla(this, Consulta2, ConsultaCount2);
        datos2.clear();
    }

    private void Buscar() {
        String SQLIdOrganismo = "", SQLLetra = "", SQLExpte = "", SQLPalabra = "", SQLFecha = "", SQLAnio = "", SQLCatastro = "";
        try {
            if (jtnroexp.getText().length() > 0) {
                if (jchekbuscar.isSelected()) {
                    SQLExpte = " AND nroexp=" + jtnroexp.getText();
                } else {
                    SQLExpte = " AND nroexp LIKE '%" + jtnroexp.getText() + "%'";
                }
            } else {
                SQLExpte = "";
            }
            /*if (jtcatastro.getText().length()>0)
        {
          SQLCatastro=" AND catastros.catastro="+ jtcatastro.getText();
        } else 
        {
          SQLCatastro="";
        }*/
            if (jtanio.getText().length() > 0) {
                SQLAnio = " AND anio=" + jtanio.getText();
            } else {
                SQLAnio = "";
            }
            if (jtpalabra.getText().length() > 0) {
                SQLPalabra = " AND (UPPER(extracto) LIKE UPPER('%" + jtpalabra.getText() + "%')" + " or UPPER(instlegal.observaciones) LIKE UPPER('%" + jtpalabra.getText() + "%')" + " or UPPER(iniciante) LIKE UPPER('%" + jtpalabra.getText() + "%'))";
            } else {
                SQLPalabra = "";
            }
            if (jtdesde.getText().length() > 0 & jthasta.getText().length() > 0) {
                SQLFecha = " AND fechaexp BETWEEN '" + OP_Proced.Fecha2(jtdesde.getText(), false) + "' AND '" + OP_Proced.Fecha2(jthasta.getText(), false) + "'";
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
            cfiltro2 = SQLIdOrganismo + SQLLetra + SQLExpte + SQLAnio + SQLPalabra + SQLFecha + SQLCatastro;
            ActualizaTabla2();
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "ERROR: ");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bcerrar) {
            String cantanexo = OP_Proced.getCampo("SELECT count(*) FROM files.instxinst WHERE idinst=" + idinst);
            if (!cantanexo.equals("0"))
                OP_Proced.exActualizar('a', "UPDATE files.instlegal SET cantanexo=" + cantanexo + "  WHERE idinst=" + idinst);
            //this.dispose();
             getParentInternalFrame().close();
        } else if (e.getSource() == bagregar) {
            if (!datos2.isEmpty()) {
                if (!datos2.elementAt(18).toString().equals("a")) {
                    OP_Proced.exActualizar('a', "UPDATE files.instlegal SET estado='a' WHERE idinst=" + datos2.elementAt(0).toString());
                    frmAnexos_InstLegal nuevo = new frmAnexos_InstLegal("", datos2.elementAt(3).toString(), datos2.elementAt(4).toString(), idinst, datos2.elementAt(0).toString(), OP_Proced.getCampo("SELECT descripcion FROM files.tiposreferencia WHERE idtipo=" + idtiporef));
                    OP_Proced.CentraVentana(nuevo);
                    nuevo.setModal(true);
                    nuevo.setVisible(true);
                    ActualizaTabla1();
                    ActualizaTabla2();
                    OP_Proced.exActualizar('a', "UPDATE files.instlegal SET estado='' WHERE idinst=" + datos2.elementAt(0).toString());
                } else {
                    OP_Proced.Errores(0);
                }
            } else {
                OP_Proced.Errores(2);
            }
        } else if (e.getSource() == bmodificar) {
            if (!datos1.isEmpty()) {
                if (!datos1.elementAt(25).toString().equals("a")) {
                    if (OP_Proced.exActualizar('a', "UPDATE files.instxinst SET estado='a' WHERE idinst=" + datos1.elementAt(0).toString() + " and idinstref=" + datos1.elementAt(2).toString())) {
                        frmAnexos_InstLegal modificar = new frmAnexos_InstLegal("SELECT * FROM files.instxinst WHERE idinst=" + datos1.elementAt(0).toString() + " and idinstref=" + datos1.elementAt(2).toString(), datos1.elementAt(5).toString(), datos1.elementAt(6).toString(), "", "", "");
                        OP_Proced.CentraVentana(modificar);
                        modificar.setModal(true);
                        modificar.setVisible(true);
                        ActualizaTabla1();
                        jtobservaciones.setText("");
                        OP_Proced.exActualizar('a', "UPDATE files.instxinst SET estado='' WHERE idinst=" + datos1.elementAt(0).toString() + " and idinstref=" + datos1.elementAt(2).toString());
                    } else {
                        OP_Proced.Errores(0);
                    }
                } else {
                    OP_Proced.Errores(0);
                }
            } else {
                OP_Proced.Errores(2);
            }
        } else if (e.getSource() == bquitar) {
            if (!datos1.isEmpty()) {
                String Q = "UPDATE files.instxinst SET estado='*' WHERE idinst=" + datos1.elementAt(0).toString() + " and idinstref=" + datos1.elementAt(2).toString();
                OP_Proced.exActualizar('b', Q);
                ActualizaTabla1();
            } else {
                OP_Proced.Errores(2);
            }
        } else if (e.getSource() == bbuscar) {
            Buscar();
        }
    }

    public void keyReleased(KeyEvent k) {
    }

    public void keyTyped(KeyEvent k) {
    }

    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_ENTER) {
            if /*|| k.getSource() == jtcatastro*/(k.getSource() == jtidorganismo || k.getSource() == jtletra || k.getSource() == jtnroexp || k.getSource() == jtpalabra || k.getSource() == jtanio || k.getSource() == jthasta) {
                Buscar();
            }
        }
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
    }

    public void setParentMain(Expedientes _parentMain) {
        parentMain = _parentMain;
    }
}
