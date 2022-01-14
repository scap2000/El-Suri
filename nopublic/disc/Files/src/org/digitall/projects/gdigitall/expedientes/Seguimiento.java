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
 * Seguimiento.java
 *
 * */
package org.digitall.projects.gdigitall.expedientes;

import java.awt.BorderLayout;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.projects.gdigitall.lib.classes.ValidaDato;
import org.digitall.projects.gdigitall.lib.components.Grilla;
import org.digitall.projects.gdigitall.lib.components.JEntry;
import org.digitall.projects.gdigitall.lib.components.SelectorFecha;
import org.digitall.projects.gdigitall.lib.html.CreaHTML;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

//public class Seguimiento extends JDialog implements ActionListener, KeyListener {
public class Seguimiento extends BasicPrimitivePanel implements ActionListener, KeyListener {
    private JButton bagregar = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/agregar.gif")));
    private JButton bcerrar = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    private JButton beliminar = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/eliminar.gif")));
    private JButton bmodificar = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/modificar.gif")));
    private JButton bbuscar = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/buscar.gif")));
    private JButton bprocesos = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/procesos.gif")));
    private JButton bimprimir = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/22x22/print.gif")));
    private JButton bseleccionar = new JButton(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/seleccionar.gif")));
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel4 = new JPanel();
    private JPanel jPanel5 = new JPanel();
    private JPanel jPanel6 = new JPanel();
    private JPanel jPanel7 = new JPanel();
    private JLabel jtpalabra2 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel3 = new JLabel(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/buscar.gif")));
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel11 = new JLabel();
    private JLabel jLabel12 = new JLabel();
    private JLabel jLabel13 = new JLabel();
    private JLabel jLabel14 = new JLabel();
    private JLabel jLabel110 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel16 = new JLabel();
    private JLabel jLabel20 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel9 = new JLabel();
    private JLabel jLabel1 = new JLabel();
    private JLabel barra = new JLabel();
    private JLabel lblfechap = new JLabel();
    private JLabel lblhora = new JLabel();
    private JLabel lblfolios = new JLabel();
    private JLabel lblobservacion = new JLabel();
    private JLabel lblestado = new JLabel();
    private JLabel lbloficrec = new JLabel();
    private JLabel lblpersonarec = new JLabel();
    private JEntry jtpalabra = new JEntry();
    private JEntry jtdesde = new JEntry();
    private JEntry jthasta = new JEntry();
    private JEntry jtidinst = new JEntry();
    private JEntry jtprofesional = new JEntry();
    private JEntry jttipoiniciador = new JEntry();
    private JEntry jtfolio = new JEntry();
    private JEntry jtasunto = new JEntry();
    private JEntry jtanio = new JEntry();
    private JEntry jtnroexp = new JEntry();
    private JEntry jtletra = new JEntry();
    private JEntry jtidorganismo = new JEntry();
    private JEntry jtcatastro = new JEntry();
    private JCheckBox jchekbuscar = new JCheckBox();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JTextArea jtextracto = new JTextArea();
    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private JTextArea jtobservaciones = new JTextArea();
    private JTextArea jtfechap = new JTextArea();
    private JTextArea jthorap = new JTextArea();
    private JTextArea jtfoliosp = new JTextArea();
    private JTextArea jtobservacion = new JTextArea();
    private JTextArea jtestado = new JTextArea();
    private JTextArea jtoficrec = new JTextArea();
    private JTextArea jtrec = new JTextArea();
    private JComboBox jctipoinst = new JComboBox();
    private Cursor wait = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor def = new Cursor(Cursor.DEFAULT_CURSOR);
    private JPopupMenu jPopupMenu1 = new JPopupMenu();
    private JPopupMenu menuAcciones = new JPopupMenu();
    private JMenuItem impBusqueda = new JMenuItem();
    private JMenuItem impExpte = new JMenuItem();
    private JMenuItem impExptePases = new JMenuItem();
    private JMenuItem menuPases = new JMenuItem();
    private JMenuItem menuAnexos = new JMenuItem();
    private JMenuItem menuAdjuntos = new JMenuItem();
    private JMenuItem menuReferencias = new JMenuItem();
    private JMenuItem menuPlazos = new JMenuItem();
    private JMenuItem menuPromulgada = new JMenuItem();
    //private JPanel jPanel1 = new JPanel();
    private Vector datos1, datosx = new Vector();
    private Timer timer = new Timer();
    private int[] vcol = { 21, 20, 19, 18, 17, 15, 14, 13, 11, 10, 9, 8, 6, 5, 2, 1 };
    private int[] tcol = { 0, 138, 70, 367, 86 };
    private Grilla jPanel1 = new Grilla(30, vcol, tcol, false, false);
    private String idoficrec = "0", idreceptor = "0";
    private String Consulta = "", ConsultaCount = "", cfiltro = "", idtipoinst = "";
    protected String idinst = "0";
    private Vector cabecera = new Vector();
    private Vector seleccionados = new Vector();
    private Vector idSeleccionados = new Vector();
    private JPanel jPanel8 = new JPanel();
    private JList listaSeguimiento = new JList();
    private JScrollPane jScrollPane3 = new JScrollPane();
    private JButton bAgregarSeleccionados = new JButton();
    private JButton bVaciarLista = new JButton();
    private JButton bBorrarSeleccionados = new JButton();
    private JButton bverSeguimiento = new JButton();
    private JButton bBorrarSeguimiento = new JButton();
    private JPanel centralPanel = new JPanel();
    //**********************************************
    private Expedientes parentMain;

    /**
   * NOTA: se debe tratar todos como DOCUMENTOS (EXPEDIENTES, NOTAS, ETC.), NO deben tratarse como INSTRUMENTOS LEGALES esta mal expresado
   * FORMULARIO DE DOCUMENTOS
   */
    public Seguimiento() {
        try {
            jbInit();
        } catch (SQLException e) {
            OP_Proced.Mensaje(e.getMessage().toUpperCase(), "Acceso a la Base de Datos no Autorizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        bseleccionar.setMargin(new Insets(2, 3, 2, 14));
        this.setSize(new Dimension(800, 558));
        //this.getContentPane().setLayout(null);
        this.setTitle("Listado General de Documentos");
        jPanel1.setBounds(new Rectangle(5, 130, 780, 220));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        bagregar.setText("Agregar");
        bagregar.setBounds(new Rectangle(405, 515, 95, 25));
        bagregar.setMnemonic('a');
        bcerrar.setText("Cerrar");
        bcerrar.setBounds(new Rectangle(705, 515, 80, 25));
        bcerrar.setMnemonic('c');
        beliminar.setText("Eliminar");
        beliminar.setBounds(new Rectangle(605, 515, 95, 25));
        beliminar.setMnemonic('e');
        bmodificar.setText("Modificar");
        bmodificar.setBounds(new Rectangle(500, 515, 105, 25));
        bmodificar.setMnemonic('m');
        jPanel2.setBounds(new Rectangle(5, 15, 780, 95));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel2.setLayout(null);
        jtpalabra2.setText("Palabra Clave:");
        jtpalabra2.setBounds(new Rectangle(15, 65, 86, 15));
        jtpalabra.setBounds(new Rectangle(105, 65, 225, 20));
        jLabel4.setText("F. Desde:");
        jLabel4.setBounds(new Rectangle(370, 68, 56, 15));
        jtdesde.setBounds(new Rectangle(430, 65, 70, 20));
        jLabel5.setText("F. Hasta:");
        jLabel5.setBounds(new Rectangle(525, 68, 52, 15));
        jthasta.setBounds(new Rectangle(580, 65, 70, 20));
        jLabel3.setText(" Busqueda de Documentos: ");
        jLabel3.setBounds(new Rectangle(15, 5, 220, 15));
        jLabel3.setFont(new Font("Dialog", 1, 14));
        jLabel3.setOpaque(true);
        jLabel6.setText(" Documentos encontrados: ");
        jLabel6.setBounds(new Rectangle(10, 120, 195, 20));
        jLabel6.setFont(new Font("Dialog", 1, 14));
        jLabel6.setOpaque(true);
        jchekbuscar.setText("Numero:");
        jchekbuscar.setBounds(new Rectangle(65, 15, 80, 15));
        jchekbuscar.setSelected(true);
        bbuscar.setText("Buscar");
        bbuscar.setBounds(new Rectangle(681, 63, 89, 25));
        bbuscar.setMnemonic('b');
        jPanel5.add(jtasunto, null);
        jPanel5.add(jLabel14, null);
        jPanel5.add(jtfolio, null);
        jPanel5.add(jLabel13, null);
        jPanel5.add(jttipoiniciador, null);
        jPanel5.add(jLabel12, null);
        jPanel5.add(jLabel11, null);
        jPanel5.add(jtprofesional, null);
        jScrollPane2.getViewport();
        jPanel2.add(jtcatastro, null);
        jPanel2.add(jLabel9, null);
        jPanel2.add(jLabel2, null);
        jPanel2.add(jctipoinst, null);
        jPanel2.add(jLabel20, null);
        jPanel2.add(jtidorganismo, null);
        jPanel2.add(jtletra, null);
        jPanel2.add(jtnroexp, null);
        jPanel2.add(jLabel16, null);
        jPanel2.add(jLabel8, null);
        jPanel2.add(jtanio, null);
        jPanel2.add(jLabel110, null);
        jPanel2.add(jthasta, null);
        jPanel2.add(jLabel5, null);
        jPanel2.add(jtdesde, null);
        jPanel2.add(jLabel4, null);
        jPanel2.add(jtpalabra, null);
        jPanel2.add(jtpalabra2, null);
        jPanel2.add(jchekbuscar, null);
        jPanel2.add(bbuscar, null);
        jPanel2.add(jtidinst, null);
        jPanel2.add(jLabel7, null);
        jScrollPane1.getViewport().add(jtextracto, null);
        jPanel3.add(jScrollPane1, null);
        jTabbedPane1.addTab("Extracto o Contenido", jPanel3);
        jScrollPane2.getViewport().add(jtobservaciones, null);
        jPanel4.add(jScrollPane2, null);
        jTabbedPane1.addTab("Observaciones", jPanel4);
        jTabbedPane1.addTab("Otros Datos", jPanel5);
        jTabbedPane1.addTab("Ultimo Pase", jPanel7);
        jTabbedPane1.addTab("Seguimiento", jPanel8);
        jPanel7.add(lblpersonarec, null);
        jPanel7.add(lbloficrec, null);
        jPanel7.add(lblestado, null);
        jPanel7.add(lblobservacion, null);
        jPanel7.add(lblfolios, null);
        jPanel7.add(lblhora, null);
        jPanel7.add(lblfechap, null);
        jPanel7.add(jtrec, null);
        jPanel7.add(jtoficrec, null);
        jPanel7.add(jtestado, null);
        jPanel7.add(jtobservacion, null);
        jPanel7.add(jtfoliosp, null);
        jPanel7.add(jthorap, null);
        jPanel7.add(jtfechap, null);
        jPanel8.add(bBorrarSeguimiento, null);
        jPanel8.add(bverSeguimiento, null);
        jPanel8.add(bBorrarSeleccionados, null);
        jPanel8.add(bVaciarLista, null);
        jPanel8.add(bAgregarSeleccionados, null);
        jScrollPane3.getViewport().add(listaSeguimiento, null);
        jPanel8.add(jScrollPane3, null);
        jTabbedPane1.setSelectedComponent(jPanel7);
        centralPanel.add(jLabel6, null);
        centralPanel.add(jLabel1, null);
        centralPanel.add(jLabel3, null);
        centralPanel.add(jPanel2, null);
        centralPanel.add(jPanel1, null);
        centralPanel.add(jTabbedPane1, null);
        jPanel6.add(barra, null);
        centralPanel.add(jPanel6, null);
        centralPanel.add(bagregar, null);
        centralPanel.add(bcerrar, null);
        centralPanel.add(beliminar, null);
        centralPanel.add(bmodificar, null);
        centralPanel.add(bimprimir, null);
        centralPanel.add(bprocesos, null);
        centralPanel.add(bseleccionar, null);
        jPopupMenu1.add(impBusqueda);
        jPopupMenu1.add(impExpte);
        jPopupMenu1.add(impExptePases);
        menuAcciones.add(menuPases);
        menuAcciones.add(menuAnexos);
        menuAcciones.add(menuAdjuntos);
        menuAcciones.addSeparator();
        menuAcciones.add(menuReferencias);
        menuAcciones.add(menuPromulgada);
        menuAcciones.addSeparator();
        menuAcciones.add(menuPlazos);
        //this.getContentPane().add(centralPanel, null);
        this.add(centralPanel, BorderLayout.CENTER);
        bagregar.addActionListener(this);
        bmodificar.addActionListener(this);
        beliminar.addActionListener(this);
        bcerrar.addActionListener(this);
        jtnroexp.addKeyListener(this);
        jtpalabra.addKeyListener(this);
        jtdesde.addKeyListener(this);
        jthasta.addKeyListener(this);
        //    jtdesde.addKeyListener(new dateListen(true));
        //    jthasta.addKeyListener(new dateListen(true));
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
        jchekbuscar.addActionListener(this);
        bbuscar.addActionListener(this);
        menuPases.addActionListener(this);
        jtidinst.addKeyListener(this);
        jtanio.addKeyListener(this);
        jtletra.addKeyListener(this);
        jtidorganismo.addKeyListener(this);
        menuAnexos.addActionListener(this);
        menuAdjuntos.addActionListener(this);
        bimprimir.addActionListener(this);
        bseleccionar.addActionListener(this);
        jLabel7.setBounds(new Rectangle(670, 15, 60, 15));
        jLabel7.setText("Nº Orden:");
        jtidinst.setBounds(new Rectangle(670, 30, 100, 20));
        bprocesos.setMnemonic('o');
        bprocesos.setBounds(new Rectangle(130, 520, 155, 25));
        bprocesos.setText("Procesos p/Doc...");
        jtprofesional.setBackground(Color.white);
        jtextracto.setBackground(Color.white);
        jtobservaciones.setBackground(Color.white);
        jttipoiniciador.setBackground(Color.white);
        jtasunto.setBackground(Color.white);
        jtfolio.setBackground(Color.white);
        jtprofesional.setEditable(false);
        jtextracto.setEditable(false);
        jtobservaciones.setEditable(false);
        jttipoiniciador.setEditable(false);
        jtasunto.setEditable(false);
        jtfolio.setEditable(false);
        jtasunto.setBounds(new Rectangle(450, 50, 315, 20));
        jLabel14.setBounds(new Rectangle(395, 53, 48, 15));
        jLabel14.setText("Asunto:");
        jtfolio.setBounds(new Rectangle(510, 15, 50, 20));
        jLabel13.setBounds(new Rectangle(430, 18, 77, 15));
        jLabel13.setText("Cant. Folios:");
        jttipoiniciador.setBounds(new Rectangle(110, 50, 255, 20));
        jLabel12.setBounds(new Rectangle(15, 53, 89, 15));
        jLabel12.setText("Tipo Iniciador:");
        jtprofesional.setBounds(new Rectangle(95, 15, 270, 20));
        jLabel11.setText("Profesional:");
        jLabel11.setBounds(new Rectangle(15, 18, 73, 15));
        jPanel5.setLayout(null);
        jPanel5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jScrollPane2.setBounds(new Rectangle(10, 10, 765, 65));
        jPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel4.setLayout(null);
        jTabbedPane1.setBounds(new Rectangle(5, 360, 780, 110));
        jScrollPane1.setBounds(new Rectangle(10, 10, 755, 65));
        jPanel3.setLayout(null);
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.Redimensiona();
        datos1 = jPanel1.VDatos();
        bBorrarSeguimiento.setBounds(new Rectangle(560, 55, 210, 25));
        bBorrarSeguimiento.setText("Quitar Seguimiento");
        bBorrarSeguimiento.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bBorrarSeguimiento_actionPerformed(e);
                }
            }
        );
        centralPanel.setBounds(new Rectangle(0, 0, 790, 555));
        centralPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        centralPanel.setLayout(null);
        bverSeguimiento.setBounds(new Rectangle(560, 10, 210, 25));
        bverSeguimiento.setText("Ver Exptes. en Seguimiento");
        bBorrarSeleccionados.setText("Borrar Selecc.");
        bBorrarSeleccionados.setBounds(new Rectangle(390, 32, 140, 20));
        bVaciarLista.setBounds(new Rectangle(390, 60, 140, 20));
        bVaciarLista.setText("Vaciar Lista");
        bAgregarSeleccionados.setBounds(new Rectangle(390, 5, 140, 20));
        bAgregarSeleccionados.setText("Agregar Todos");
        jScrollPane3.setBounds(new Rectangle(120, 5, 265, 75));
        jPanel8.setLayout(null);
        jtfechap.setEditable(false);
        jthorap.setEditable(false);
        jtfoliosp.setEditable(false);
        jtoficrec.setEditable(false);
        jtrec.setEditable(false);
        jtobservacion.setEditable(false);
        jtestado.setEditable(false);
        lblpersonarec.setBounds(new Rectangle(300, 43, 115, 15));
        lblpersonarec.setText("Persona Receptora");
        lbloficrec.setText("Oficina Receptora");
        lbloficrec.setBounds(new Rectangle(15, 43, 115, 15));
        lblestado.setText("Estado");
        lblestado.setBounds(new Rectangle(680, 5, 45, 15));
        lblobservacion.setText("Observaciones");
        lblobservacion.setBounds(new Rectangle(300, 5, 95, 15));
        lblfolios.setBounds(new Rectangle(195, 5, 45, 15));
        lblfolios.setText("Folios");
        lblhora.setText("Hora");
        lblhora.setBounds(new Rectangle(105, 5, 40, 15));
        lblfechap.setBounds(new Rectangle(15, 5, 40, 15));
        lblfechap.setText("Fecha");
        jtrec.setBounds(new Rectangle(300, 58, 360, 18));
        jtoficrec.setBounds(new Rectangle(15, 58, 255, 18));
        jtestado.setBounds(new Rectangle(680, 20, 75, 18));
        jtobservacion.setBounds(new Rectangle(295, 20, 360, 18));
        jtfoliosp.setBounds(new Rectangle(195, 20, 50, 18));
        jthorap.setBounds(new Rectangle(105, 20, 75, 18));
        jtfechap.setBounds(new Rectangle(15, 20, 75, 18));
        jPanel7.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel7.setLayout(null);
        jtobservaciones.setWrapStyleWord(true);
        jtobservaciones.setLineWrap(true);
        jtobservaciones.setMargin(new Insets(5, 5, 5, 5));
        jtextracto.setWrapStyleWord(true);
        jtextracto.setLineWrap(true);
        jtextracto.setMargin(new Insets(5, 5, 5, 5));
        menuPlazos.setEnabled(false);
        menuPromulgada.setEnabled(false);
        menuReferencias.setEnabled(false);
        //this.setDefaultCloseOperation(1);
        barra.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        barra.setForeground(Color.blue);
        barra.setBounds(new Rectangle(5, 3, 770, 17));
        barra.setText(" Barra de Mensajes:");
        jPanel6.setLayout(null);
        jPanel6.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        jPanel6.setBounds(new Rectangle(5, 480, 780, 20));
        jtletra.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        jtidorganismo.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        jLabel1.setForeground(Color.red);
        jLabel1.setOpaque(true);
        jLabel1.setBounds(new Rectangle(595, 5, 185, 15));
        jLabel1.setText(" Presione F10 p/Seleccionar... ");
        menuAnexos.setMargin(new Insets(2, 8, 2, 2));
        menuAdjuntos.setMargin(new Insets(2, 8, 2, 2));
        menuReferencias.setMargin(new Insets(2, 8, 2, 2));
        menuPromulgada.setMargin(new Insets(2, 8, 2, 2));
        menuPlazos.setMargin(new Insets(2, 8, 2, 2));
        menuPases.setMargin(new Insets(2, 8, 2, 2));
        menuPromulgada.setMnemonic('o');
        menuPromulgada.setText("Promulgada por...");
        menuPlazos.setMnemonic('z');
        menuReferencias.setMnemonic('r');
        menuPlazos.setText("Plazos");
        menuReferencias.setText("Referencias");
        bimprimir.setToolTipText("Imprimir");
        bseleccionar.setMnemonic('s');
        bseleccionar.setBounds(new Rectangle(5, 520, 120, 25));
        bseleccionar.setText("Seleccionar");
        menuAdjuntos.setMnemonic('d');
        menuPases.setMnemonic('p');
        menuAnexos.setMnemonic('a');
        menuAdjuntos.setText("Adjuntos");
        menuAnexos.setText("Anexos");
        menuPases.setText("Pases");
        menuAcciones.setLabel("jPopupMenu2");
        bbuscar.setMargin(new Insets(2, 8, 2, 14));
        bbuscar.setHorizontalAlignment(SwingConstants.LEFT);
        bprocesos.setHorizontalAlignment(SwingConstants.LEFT);
        bprocesos.setMargin(new Insets(2, 3, 2, 14));
        bcerrar.setMargin(new Insets(2, 4, 2, 14));
        beliminar.setMargin(new Insets(2, 4, 2, 14));
        bmodificar.setMargin(new Insets(2, 4, 2, 14));
        bagregar.setMargin(new Insets(2, 4, 2, 14));
        bagregar.setIconTextGap(3);
        bagregar.setHorizontalTextPosition(SwingConstants.RIGHT);
        bagregar.setHorizontalAlignment(SwingConstants.LEFT);
        jtletra.setToolTipText("Pres. F10 p/Buscar un Tipo de Letra");
        jtidorganismo.setToolTipText("Pres. F10 p/Buscar un Organismo");
        jtcatastro.setBounds(new Rectangle(295, 30, 100, 20));
        jLabel9.setText("Nº Catastro:");
        jLabel9.setBounds(new Rectangle(295, 15, 75, 15));
        impExptePases.setMnemonic('x');
        impExpte.setMnemonic('e');
        impBusqueda.setMnemonic('b');
        impExptePases.setText("Doc. Seleccionado c/Pases");
        impExpte.setText("Doc. Seleccionado");
        impBusqueda.setText("Rtdo. de la Busqueda");
        jPopupMenu1.setLabel("jPopupMenu1");
        bimprimir.setBounds(new Rectangle(290, 515, 50, 30));
        jLabel2.setBounds(new Rectangle(430, 15, 130, 15));
        jLabel2.setText("Tipo de Documento:");
        jctipoinst.setBounds(new Rectangle(430, 30, 185, 20));
        jLabel20.setBounds(new Rectangle(15, 15, 26, 15));
        jLabel20.setText("Org:");
        jtidorganismo.setForeground(Color.red);
        jtidorganismo.setFont(new Font("Dialog", 0, 13));
        jtidorganismo.setBounds(new Rectangle(15, 30, 35, 20));
        jtletra.setForeground(Color.red);
        jtletra.setFont(new Font("Dialog", 0, 13));
        jtletra.setBounds(new Rectangle(65, 30, 45, 20));
        jtnroexp.setForeground(Color.red);
        jtnroexp.setFont(new Font("Dialog", 1, 15));
        jtnroexp.setBounds(new Rectangle(112, 30, 93, 20));
        jLabel16.setFont(new Font("Dialog", 1, 20));
        jLabel16.setBounds(new Rectangle(205, 30, 15, 20));
        jLabel16.setText("-");
        jLabel8.setBounds(new Rectangle(220, 15, 30, 15));
        jLabel8.setText("Año:");
        jtanio.setForeground(Color.red);
        jtanio.setFont(new Font("Dialog", 0, 13));
        jtanio.setBounds(new Rectangle(220, 30, 40, 20));
        jLabel110.setFont(new Font("Dialog", 1, 20));
        jLabel110.setBounds(new Rectangle(50, 30, 15, 20));
        jLabel110.setText("-");
        impBusqueda.addActionListener(this);
        impExpte.addActionListener(this);
        impExptePases.addActionListener(this);
        jtidinst.addKeyListener(this);
        jtcatastro.addKeyListener(this);
        jtidorganismo.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtletra.setDocument(new ValidaDato(ValidaDato.MAYUSCULA));
        jtnroexp.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtanio.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtcatastro.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtidinst.setDocument(new ValidaDato(ValidaDato.ENTERO));
        ActualizaTabla();
        menuPases.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/pases.gif")));
        menuAnexos.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/anexo.gif")));
        menuAdjuntos.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/adjunto.gif")));
        menuReferencias.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/sanciones.gif")));
        menuPromulgada.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/promulgada.gif")));
        menuPlazos.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/reloj.gif")));
        impBusqueda.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/imprimir.gif")));
        impExpte.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/imprimir.gif")));
        impExptePases.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/imprimir.gif")));
        bprocesos.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    if (bprocesos.isEnabled())
                        menuAcciones.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        );
        timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        datosx = jPanel1.VDatos();
                        if (!datos1.equals(datosx)) {
                            datos1 = datosx;
                            jttipoiniciador.setText(OP_Proced.getCampo("Select descripcion||' - ('||abrev||')' from sim.tiposiniciador where idtipo=" + datos1.elementAt(8).toString()));
                            jtasunto.setText(OP_Proced.getCampo("Select descripcion||' - ('||abrev||')' from sim.tiposasunto where idtipo=" + datos1.elementAt(9).toString()));
                            jtextracto.setText(datos1.elementAt(13).toString());
                            jtobservaciones.setText(datos1.elementAt(14).toString());
                            jtfolio.setText(datos1.elementAt(15).toString());
                            jtprofesional.setText(datos1.elementAt(19).toString());
                            barra.setText(" Barra de Mensajes:");
                            /** EN EL CASO DE QUE SE ESTE SELECCIONANDO UN DOCUMENTO, EL CUAL SEA UN ADJUNTO O ANEXO A OTRO, ESTO ME INDICA EL 
                  * ,
                 O SEA EL DOCUEMTO AL CUAL FUE ADJUNTADO O ANEXADO. EL RESULTADO DE ESTO ES INDICA EN JLABEL barra */
                            String idinst = OP_Proced.getCampo("Select idinst from sim.instxinst where estado<>'*' AND idinstref=" + datos1.elementAt(0).toString());
                            if (idinst.length() > 0) {
                                menuAdjuntos.setEnabled(false);
                                barra.setText(" Adjuntos/Anexos: Este corre adjunto/anexo al documento (o expediente) '" + OP_Proced.getCampo("Select instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio FROM sim.instlegal,sim.tiposletra " + " Where idinst=" + idinst + " and instlegal.idtipodoc = tiposletra.idtipo and instlegal.idorganismo = tiposletra.idorganismo") + "'.");
                            } else /** ESTO ME INDICA EL NUMERO DE DOCUMENTOS ADJUNTADOS O ANEXADOS AL DOCUMENTO SELECCIONADO*/
                            {
                                menuAdjuntos.setEnabled(true);
                                String cantadjunto = OP_Proced.getCampo("Select count(*) from sim.instxinst where estado<>'*' AND idinst=" + datos1.elementAt(0).toString());
                                if (!cantadjunto.equals("0"))
                                    barra.setText(" Documento Cabecera: TIENE " + cantadjunto + " DOCUMENTOS O EXPEDIENTES ADJUNTADOS/ANEXADOS");
                            }
                            /** CADA DOC DEBE PERMANECER 'x' DIAS EN UNA OFICINA O DEPENDENCIA, ESTARA EN FUNCION DEL TIPO DE PLAZO Q SE LE HAYA INDICADO.
                  ENTONCES AL SELECCIONAR ESTE CALCULO LOS DIAS QUE TRANSCURRIERON AL DIA DE LA FECHA Y SE LOS SUMO AL ULTIMO PASE.
                  ESTA RUTINA NO ESTA ACTIVA, PARA ESTO DES-COMENTAR LA LINEA Q DICE ACTIVAR!!!!!! */
                            if (!OP_Proced.getCampo("Select count(*) from sim.pases where estado<>'*' and idinst=" + datos1.elementAt(0).toString()).equals("0")) {
                                UltimoPase();
                                ResultSet Reg = OP_Proced.exConsulta("Select max(fechapase||' '||horapase) from sim.pases where estado<>'*' and idinst=" + datos1.elementAt(0).toString());
                                if (Reg.next()) {
                                    String FechaRecepcion = OP_Proced.TransformaNull_Texto(OP_Proced.getCampo("SELECT fecha_rec FROM sim.pases  WHERE estado<>'*' and idinst=" + datos1.elementAt(0).toString() + " and fechapase||' '||horapase='" + Reg.getString(1) + "'"));
                                    if (!FechaRecepcion.equals("0")) {
                                        String FechaActual = OP_Proced.Fecha2(OP_Proced.FechaHora(true, false), false);
                                        int CantDia = Integer.parseInt(OP_Proced.ObtieneCantDia(FechaRecepcion, FechaActual));
                                        int CantDiaNoLaborables = Integer.parseInt(OP_Proced.getCampo("SELECT count(*) FROM calendario WHERE fecha BETWEEN '" + FechaRecepcion + "' AND '" + FechaActual + "'"));
                                        String Q = "Update sim.pases set plazo=" + String.valueOf(CantDia - CantDiaNoLaborables) + " WHERE idinst=" + datos1.elementAt(0).toString() + " and fechapase||' '||horapase='" + Reg.getString(1) + "'";
                                        if (!OP_Proced.getCampo("Select idoficrec from sim.pases where idinst=" + datos1.elementAt(0).toString() + " and fechapase||' '||horapase='" + Reg.getString(1) + "'").equals("82"))// (82) Oficina ARCHIVO GENERAL!!!!!!!!!!!!!!!!
                                        {
                                            /**ACTIVARRRRR!!!!!!!!!!!!!!!    **/
                                            //     Proced.exActualizar('a',Q);     
                                            int Plazo = Integer.parseInt(OP_Proced.getCampo("Select plazo from sim.tiposplazo where idtipo=(Select idtipoplazo from sim.instlegal where idinst=" + datos1.elementAt(0).toString() + ")"));
                                            if (Plazo < CantDia - CantDiaNoLaborables) {
                                                OP_Proced.MensajePopupWindow("<html>El Documento " + datos1.elementAt(3) + "<br>ha superado el plazo por " + String.valueOf(CantDia - CantDiaNoLaborables - Plazo) + " dias</html>");
                                            }
                                        }
                                    }
                                }
                            } else {
                                barra.setText(barra.getText() + " - (No Posee Pases)");
                            }
                            // FIN*---
                            beliminar.setEnabled(true);
                            bmodificar.setEnabled(true);
                            bprocesos.setEnabled(true);
                            bseleccionar.setEnabled(true);
                            bimprimir.setEnabled(true);
                        }
                    } catch (Exception x) {
                        OP_Proced.Mensaje(x.getMessage(), "ERROR: ");
                    }
                }
            }
            , 0, 500);
        jctipoinst.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        idtipoinst = OP_Proced.getCampo("Select idtipo from sim.tiposinstlegal where descripcion='" + jctipoinst.getSelectedItem() + "'");
                    }
                }
            }
        );
        bimprimir.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        );
        OP_Proced.CargaCombo(jctipoinst, "Select descripcion from sim.tiposinstlegal where estado<>'*'", "EXPEDIENTE");
        bseleccionar.setEnabled(false);
        bprocesos.setEnabled(false);
        bimprimir.setEnabled(false);
        bmodificar.setEnabled(false);
        beliminar.setEnabled(false);
        jtletra.transferFocus();
    }

    private void setearCabecera() {
        cabecera.removeAllElements();
        cabecera.addElement("");
        //("Nº Orden");
        cabecera.addElement("*");
        cabecera.addElement("*");
        cabecera.addElement("Nº Expediente/Nota");
        cabecera.addElement("*");
        //cabecera.addElement("Nº Catastro");
        cabecera.addElement("*");
        cabecera.addElement("*");
        cabecera.addElement("Fecha");
        cabecera.addElement("*");
        cabecera.addElement("*");
        cabecera.addElement("*");
        cabecera.addElement("*");
        cabecera.addElement("Iniciante");
        cabecera.addElement("*");
        cabecera.addElement("*");
        cabecera.addElement("*");
        cabecera.addElement("Estado");
        cabecera.addElement("*");
        cabecera.addElement("*");
        cabecera.addElement("*");
        cabecera.addElement("*");
        cabecera.addElement("*");
        cabecera.addElement("*");
    }

    public void ActualizaTabla() {
        setearCabecera();
        bseleccionar.setEnabled(false);
        bprocesos.setEnabled(false);
        bmodificar.setEnabled(false);
        beliminar.setEnabled(false);
        bimprimir.setEnabled(true);
        Consulta = "SELECT idinst,idtipoinst,instlegal.idtipodoc,(instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio) as expte," + "catastros.catastro,anio,nroexp,fechaexp,idtipoiniciador,idtipoasunto,tiposiniciador.abrev as iniciador,tiposasunto.abrev as asunto," + "iniciante,extracto,instlegal.observaciones,cantfolios,estadoexp,instlegal.idcatastro," + "instlegal.idprofesional,apellido||', '||nombre as profesional,instlegal.estado,tiposletra.letra,instlegal.cantanexo " + " FROM sim.instlegal,sim.tiposorganismo,sim.tiposiniciador,sim.tiposasunto,cepax.catastros,sopsalta.profesionales,sim.tiposletra" + " WHERE instlegal.estado<>'*' " + cfiltro + " and instlegal.idtipodoc=tiposletra.idtipo " + " and instlegal.idorganismo=tiposletra.idorganismo " + " and instlegal.idorganismo=tiposorganismo.idorganismo " + " and instlegal.idtipoiniciador=tiposiniciador.idtipo  " + " and instlegal.idtipoasunto=tiposasunto.idtipo " + " and instlegal.idcatastro=catastros.idcatastro " + " and instlegal.idprofesional=profesionales.idprofesional " + " and idinst in (select idinst from sim.seguimiento where usuario = '" + OP_Proced.getSQLUser() + "')" + " order by nroexp,anio,fechaexp";
        //System.out.println(Consulta);         
        ConsultaCount = "SELECT count(*)  FROM sim.instlegal,sim.tiposorganismo,sim.tiposiniciador,sim.tiposasunto,cepax.catastros,sopsalta.profesionales,sim.tiposletra " + " WHERE instlegal.estado<>'*'" + cfiltro + " and instlegal.idtipodoc=tiposletra.idtipo " + " and instlegal.idorganismo=tiposletra.idorganismo  " + " and instlegal.idorganismo=tiposorganismo.idorganismo  " + " and instlegal.idtipoiniciador=tiposiniciador.idtipo   " + " and instlegal.idtipoasunto=tiposasunto.idtipo  " + " and instlegal.idcatastro=catastros.idcatastro  " + " and instlegal.idprofesional=profesionales.idprofesional" + " and idinst in (select idinst from sim.seguimiento where usuario = '" + OP_Proced.getSQLUser() + "')";
        //ESTO SE DEBE HACER SI O SI ANTES DEL ActualizaTabla, para guardar los que han sido seleccionados
        jPanel1.ActualizaTabla(this, Consulta, ConsultaCount, cabecera);
        datos1.clear();
    }

    private void Buscar() {
        String SQLIdInst = "", SQLIdOrganismo = "", SQLLetra = "", SQLExpte = "", SQLPalabra = "", SQLFecha = "", SQLAnio = "", SQLCatastro = "";
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
            if (jtpalabra.getText().length() > 0) {
                SQLPalabra = " AND (upper(extracto) LIKE upper('%" + jtpalabra.getText() + "%')" + " or upper(instlegal.observaciones) LIKE upper('%" + jtpalabra.getText() + "%')" + " or upper(iniciante) LIKE upper('%" + jtpalabra.getText() + "%'))";
            } else {
                SQLPalabra = "";
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
            if (jtidinst.getText().length() > 0) {
                SQLIdInst = " AND idinst=" + jtidinst.getText();
            } else {
                SQLIdInst = "";
            }
            cfiltro = " AND idtipoinst=" + idtipoinst + SQLIdInst + SQLIdOrganismo + SQLLetra + SQLExpte + SQLAnio + SQLPalabra + SQLFecha + SQLCatastro;
            jttipoiniciador.setText("");
            jtasunto.setText("");
            jtextracto.setText("");
            jtobservaciones.setText("");
            jtfolio.setText("0");
            jtprofesional.setText("");
            ActualizaTabla();
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "ERROR: ");
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == bcerrar) {
                //this.dispose();
            } else if (e.getSource() == bagregar) {
                frmExpedientes nuevo = new frmExpedientes("", idtipoinst);
                /*OP_Proced.CentraVentana(nuevo);
                nuevo.setModal(true);*/
                nuevo.setVisible(true);
                if (!nuevo.cancelar) {
                    OP_Proced.CargaCombo(jctipoinst, "Select descripcion from sim.tiposinstlegal where estado<>'*'", OP_Proced.getCampo("Select descripcion from sim.tiposinstlegal where idtipo=" + nuevo.tipoinst));
                    cfiltro = " AND idtipoinst=" + nuevo.tipoinst + " AND nroexp=" + nuevo.nroexp + " AND anio=" + nuevo.anio;
                    ActualizaTabla();
                }
            } else if (e.getSource() == bmodificar) {
                boolean cancelar = false;
                if (!datos1.isEmpty()) {
                    /** CUANDO EL CAMPO 'estado' DE TODAS LAS TABLAS ES IGUAL 'a' QUIERE DECIR Q ESTA SIENDO ACCEDIDO POR OTRO USUARIO */
                    if (!datos1.elementAt(20).toString().equals("a")) {
                        if (OP_Proced.exActualizar('a', "Update sim.instlegal set estado='a' where idinst=" + datos1.elementAt(0).toString())) {
                            frmExpedientes nuevo = new frmExpedientes("Select * from sim.instlegal where idinst=" + datos1.elementAt(0).toString(), "");
                            /*OP_Proced.CentraVentana(nuevo);
                            nuevo.setModal(true);*/
                            nuevo.setVisible(true);
                            cancelar = nuevo.cancelar;
                            OP_Proced.exActualizar('a', "Update sim.instlegal set estado='' where idinst=" + datos1.elementAt(0).toString());
                        } else {
                            OP_Proced.Errores(5);
                        }
                    } else {
                        OP_Proced.Errores(0);
                    }
                    if (!cancelar) {
                        ActualizaTabla();
                    }
                } else {
                    OP_Proced.Errores(2);
                }
            } else if (e.getSource() == beliminar) {
                if (!datos1.isEmpty()) {
                    String Q = "Update sim.instlegal set estado='*' where idinst=" + datos1.elementAt(0).toString();
                    if (OP_Proced.exActualizar('b', Q))
                        ActualizaTabla();
                } else {
                    OP_Proced.Errores(2);
                }
            } else if (e.getSource() == bbuscar) {
                Buscar();
            } else if (e.getSource() == menuPases) {
                if (!datos1.isEmpty()) {
                    Pases pases = new Pases(datos1.elementAt(0).toString());
                    /*OP_Proced.CentraVentana(pases);
                    pases.setModal(true);*/
                    pases.setVisible(true);
                } else {
                    OP_Proced.Errores(2);
                }
            } else if (e.getSource() == menuAnexos) {
                if (!datos1.isEmpty()) {
                    Anexos_InstLegal anexos = new Anexos_InstLegal("2", datos1.elementAt(0).toString(), datos1.elementAt(3).toString());
                    /*OP_Proced.CentraVentana(anexos);
                    anexos.setModal(true);
                    anexos.setVisible(true);*/
                    ActualizaTabla();
                } else {
                    OP_Proced.Errores(2);
                }
            } else if (e.getSource() == menuAdjuntos) {
                if (!datos1.isEmpty()) {
                    Anexos_InstLegal adjuntos = new Anexos_InstLegal("4", datos1.elementAt(0).toString(), datos1.elementAt(3).toString());
                    /*OP_Proced.CentraVentana(adjuntos);
                    adjuntos.setModal(true);
                    adjuntos.setVisible(true);*/
                    ActualizaTabla();
                } else {
                    OP_Proced.Errores(2);
                }
            } else if (e.getSource() == bseleccionar) {
                idinst = datos1.elementAt(0).toString();
                //this.dispose();
            } else if (e.getSource() == impBusqueda) {
                CreaHTML.HTMLListadoExptes(cfiltro, "ListadoExptes");
            } else if (e.getSource() == impExpte) {
                CreaHTML.HTMLExpteSeleccionado(datos1.elementAt(0).toString(), "ExpteSeleccionado", false);
            } else if (e.getSource() == impExptePases) {
                CreaHTML.HTMLExpteSeleccionado(datos1.elementAt(0).toString(), "ExpteSelecCPases", true);
            }
        } catch (Exception x) {
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
                Organismos_Letras org = new Organismos_Letras("sim.tiposorganismo");
                /*OP_Proced.CentraVentana(org);
                org.setModal(true);*/
                org.setVisible(true);
                if (org.idorganismo.length() > 0) {
                    jtletra.setText("");
                    jtidorganismo.setText(org.idorganismo);
                }
            } else {
                Organismos_Letras org = new Organismos_Letras("sim.tiposletra");
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
            if (k.getSource() == jtidorganismo || k.getSource() == jtletra || k.getSource() == jtnroexp || k.getSource() == jtpalabra || k.getSource() == jtanio || k.getSource() == jthasta || k.getSource() == jtidinst || k.getSource() == jtcatastro) {
                //JEntry texto = (JEntry)k.getSource();
                Buscar();
            }
        }
    }

    private void UltimoPase() {
        try {
            //      System.out.println("SELECT MAX(fechapase||' '||horapase) FROM sim.pases WHERE estado<>'*' AND idinst="+ datos1.elementAt(0).toString());
            ResultSet Reg10 = OP_Proced.exConsulta("SELECT MAX(fechapase||' '||horapase) FROM sim.pases WHERE estado<>'*' AND idinst=" + datos1.elementAt(0).toString());
            if (Reg10.next()) {
                ResultSet Reg11 = OP_Proced.exConsulta("SELECT * FROM sim.pases WHERE estado<>'*' AND idinst=" + datos1.elementAt(0).toString() + " AND fechapase||' '||horapase='" + Reg10.getString(1) + "'");
                if (Reg11.next()) {
                    //System.out.println(Reg11);
                    jtfechap.setText(OP_Proced.Fecha2(Reg11.getString(2), true));
                    jthorap.setText(OP_Proced.Hora(Reg11.getString(3), true, true));
                    idoficrec = Reg11.getString(7);
                    jtoficrec.setText(OP_Proced.getCampo("SELECT descripcion FROM sim.tiposoficina WHERE idtipo=" + Reg11.getString(7)));
                    jtrec.setText(OP_Proced.getCampo("SELECT apellido||', '||nombre FROM personas WHERE idpersona=" + Reg11.getString(8)));
                    idreceptor = Reg11.getString(8);
                    jtobservacion.setText(Reg11.getString(9));
                    jtfoliosp.setText(Reg11.getString(10));
                    jtestado.setText(Reg11.getString(13));
                }
            }
        } catch (Exception x) {
            OP_Proced.Mensaje(x.getMessage(), "ERROR: ");
        }
    }

    public String getIDInst() {
        return idinst;
    }

    void bBorrarSeguimiento_actionPerformed(ActionEvent e) {
        for (int i = listaSeguimiento.getSelectedIndices().length - 1; i >= 0; i--) {
            OP_Proced.exActualizar('b', "delete from sim.seguimiento where idinst = " + idSeleccionados.elementAt(i).toString() + " and usuario = '" + OP_Proced.getSQLUser() + "'");
        }
    }

    public void setParentMain(Expedientes _parentMain) {
        parentMain = _parentMain;
    }
}
