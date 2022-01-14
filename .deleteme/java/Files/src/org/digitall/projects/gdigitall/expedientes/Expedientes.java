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
 * Expedientes.java
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

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.projects.gdigitall.lib.classes.ValidaDato;
import org.digitall.projects.gdigitall.lib.components.Grilla;
import org.digitall.projects.gdigitall.lib.components.JEntry;
import org.digitall.projects.gdigitall.lib.components.SelectorFecha;
import org.digitall.projects.gdigitall.lib.html.CreaHTML;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;
import org.digitall.common.reports.BasicReport;
import org.digitall.lib.components.JTFecha;
import org.digitall.lib.icons.IconTypes;

//public class Expedientes extends JDialog implements ActionListener, KeyListener {
public class Expedientes extends BasicPrimitivePanel implements ActionListener, KeyListener {

    private JButton bagregar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/agregar.gif")));
    private JButton bcerrar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    private JButton beliminar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/eliminar.gif")));
    private JButton bmodificar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/modificar.gif")));
    private JButton bbuscar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/buscar.gif")));
    private JButton bprocesos = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/procesos.gif")));
    private JButton bimprimir = new JButton(IconTypes.print_16x16); //new ImageIcon(OP_Proced.class.getResource("iconos/22x22/print.gif")));
    private JButton bseleccionar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/seleccionar.gif")));
    private JButton bAgregarSeleccionados = new JButton();
    private JButton bVaciarLista = new JButton();
    private JButton bBorrarSeleccionados = new JButton();
    private JButton bverSeguimiento = new JButton();
    
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel4 = new JPanel();
    private JPanel jPanel5 = new JPanel();
    private JPanel jPanel6 = new JPanel();
    private JPanel jPanel7 = new JPanel();
    private JPanel jPanel8 = new JPanel();
    
    private JLabel jtpalabra2 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel3 = new JLabel(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/buscar.gif")));
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
    private JLabel lbloficemi = new JLabel();
    private JLabel lblpersonaemi = new JLabel();
    private JEntry jtpalabra = new JEntry();
    private JTFecha jtdesde = new JTFecha();
    private JTFecha jthasta = new JTFecha();
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
    private JCheckBox bverEnSeguimiento = new JCheckBox();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JScrollPane jScrollPane3 = new JScrollPane();
    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private JTextArea jtextracto = new JTextArea();
    private JTextArea jtobservaciones = new JTextArea();
    private JTextArea jtfechap = new JTextArea();
    private JTextArea jthorap = new JTextArea();
    private JTextArea jtfoliosp = new JTextArea();
    private JTextArea jtobservacion = new JTextArea();
    private JTextArea jtestado = new JTextArea();
    private JTextArea jtoficrec = new JTextArea();
    private JTextArea jtrec = new JTextArea();
    private JTextArea jtoficemi = new JTextArea();
    private JTextArea jtemi = new JTextArea();
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
    private int[] tcol = { 0, 138, 70, 367, 86, 49, 50};
    private Grilla jPanel1 = new Grilla(1000, vcol, tcol, true, false);
    private String idoficemi = "0", idemisor = "0";
    private String idoficrec = "0", idreceptor = "0";
    private String Consulta = "", ConsultaCount = "", cfiltro = "", idtipoinst = "";
    private String cfiltroPrev = "";
    protected String idinst = "0";
    private Vector cabecera = new Vector();
    private Vector seleccionados = new Vector();
    private Vector idSeleccionados = new Vector();
    private JList listaSeguimiento = new JList();
    private JButton bBorrarSeguimiento = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/eliminar.gif")));
    private JButton bAgregarSeguim = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/buscar2.gif")));
    private boolean primeravez = true;
    private JPanel centralPanel = new JPanel();
    
    //*********************************
    private principal_simex parentMain;
    private Seguimiento seg;
    private frmExpedientes nuevo;
    private Pases pases;
    private Anexos_InstLegal anexos;
    private Anexos_InstLegal adjuntos;

    /**
   * NOTA: se debe tratar todos como DOCUMENTOS (EXPEDIENTES, NOTAS, ETC.), NO deben tratarse como INSTRUMENTOS LEGALES esta mal expresado
   * FORMULARIO DE DOCUMENTOS
   */
    public Expedientes() {
        try {
            jbInit();
        } catch (SQLException e) {
            OP_Proced.Mensaje(e.getMessage().toUpperCase(), "Acceso a la Base de Datos no Autorizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(796, 585));
        this.setTitle("Listado General de Documentos");
        centralPanel.add(bprocesos, null);
        centralPanel.add(bcerrar, null);
        centralPanel.add(beliminar, null);
        centralPanel.add(bmodificar, null);
        centralPanel.add(bagregar, null);
        centralPanel.add(bimprimir, null);
        centralPanel.add(bseleccionar, null);
        jPanel6.add(barra, null);
        centralPanel.add(jPanel6, null);
        centralPanel.add(jTabbedPane1, null);
        centralPanel.add(jPanel1, null);
        centralPanel.add(jLabel6, null);
        centralPanel.add(jPanel2, null);
        centralPanel.add(jLabel1, null);
        centralPanel.add(jLabel3, null);
        //this.setDefaultCloseOperation(1);
        bseleccionar.setMargin(new Insets(2, 3, 2, 14));
        bagregar.setText("Agregar");
        bagregar.setBounds(new Rectangle(395, 545, 95, 25));
        bagregar.setMnemonic('a');
        bcerrar.setText("Cerrar");
        bcerrar.setBounds(new Rectangle(700, 545, 85, 25));
        bcerrar.setMnemonic('c');
        beliminar.setText("Eliminar");
        beliminar.setBounds(new Rectangle(600, 545, 95, 25));
        beliminar.setMnemonic('e');
        bmodificar.setText("Modificar");
        bmodificar.setBounds(new Rectangle(495, 545, 100, 25));
        bmodificar.setMnemonic('m');
        bbuscar.setText("Buscar");
        bbuscar.setBounds(new Rectangle(681, 63, 89, 25));
        bbuscar.setMnemonic('b');
        bagregar.addActionListener(this);
        bmodificar.addActionListener(this);
        beliminar.addActionListener(this);
        bcerrar.addActionListener(this);
        bbuscar.addActionListener(this);
        bimprimir.addActionListener(this);
        bimprimir.setToolTipText("Imprimir");
        bimprimir.setBounds(new Rectangle(290, 545, 50, 30));
        bseleccionar.addActionListener(this);
        bprocesos.setMnemonic('o');
        bprocesos.setBounds(new Rectangle(130, 545, 155, 25));
        bprocesos.setText("Procesos p/Doc...");
        bverEnSeguimiento.setBounds(new Rectangle(550, 35, 190, 25));
        bverEnSeguimiento.setText("Ver sólo en seguimiento");
        bverSeguimiento.setBounds(new Rectangle(535, 5, 210, 25));
        bverSeguimiento.setText("Ver Exptes. en Seguimiento");
        bBorrarSeleccionados.setText("Borrar Selecc.");
        bBorrarSeleccionados.setBounds(new Rectangle(390, 38, 140, 20));
        bVaciarLista.setBounds(new Rectangle(390, 70, 140, 20));
        bVaciarLista.setText("Vaciar Lista");
        bAgregarSeleccionados.setBounds(new Rectangle(390, 5, 140, 20));
        bAgregarSeleccionados.setText("Agregar Todos");
        barra.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        barra.setForeground(Color.blue);
        barra.setBounds(new Rectangle(5, 3, 770, 17));
        barra.setText(" Barra de Mensajes:");
        bseleccionar.setMnemonic('s');
        bseleccionar.setBounds(new Rectangle(5, 545, 115, 25));
        bseleccionar.setText("Seleccionar");
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
        impExptePases.setMnemonic('x');
        impExpte.setMnemonic('e');
        impBusqueda.setMnemonic('b');
        impExptePases.setText("Doc. Seleccionado c/Pases");
        impExpte.setText("Doc. Seleccionado");
        impBusqueda.setText("Rtdo. de la Busqueda");
        impBusqueda.addActionListener(this);
        impExpte.addActionListener(this);
        impExptePases.addActionListener(this);
        //impBusqueda.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/imprimir.gif")));
        //impExpte.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/imprimir.gif")));
        //impExptePases.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/imprimir.gif")));
        jctipoinst.setBounds(new Rectangle(430, 30, 185, 20));
        jtpalabra2.setText("Palabra Clave:");
        jtpalabra2.setBounds(new Rectangle(15, 65, 86, 20));
        jtpalabra.setBounds(new Rectangle(105, 65, 225, 20));
        jtdesde.setBounds(new Rectangle(415, 65, 90, 20));
        jthasta.setBounds(new Rectangle(580, 65, 90, 20));
        jtnroexp.addKeyListener(this);
        jtpalabra.addKeyListener(this);
        jtidinst.addKeyListener(this);
        jtanio.addKeyListener(this);
        jtletra.addKeyListener(this);
        jtidorganismo.addKeyListener(this);
        jtidinst.setBounds(new Rectangle(670, 30, 100, 20));
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
        jtfolio.setBounds(new Rectangle(710, 10, 50, 20));
        jttipoiniciador.setBounds(new Rectangle(110, 50, 280, 20));
        jtprofesional.setBounds(new Rectangle(110, 10, 455, 20));
        jtfoliosp.setToolTipText("null");
        jtemi.setBounds(new Rectangle(555, 40, 215, 20));
        jtemi.setEditable(false);
        jtoficemi.setBounds(new Rectangle(120, 40, 310, 20));
        jtoficemi.setEditable(false);
        jtfechap.setEditable(false);
        jthorap.setEditable(false);
        jtfoliosp.setEditable(false);
        jtoficrec.setEditable(false);
        jtrec.setEditable(false);
        jtobservacion.setEditable(false);
        jtestado.setEditable(false);
        jtrec.setBounds(new Rectangle(555, 70, 215, 18));
        jtoficrec.setBounds(new Rectangle(120, 70, 310, 20));
        jtestado.setBounds(new Rectangle(680, 15, 90, 20));
        jtobservacion.setBounds(new Rectangle(255, 15, 415, 20));
        jtfoliosp.setBounds(new Rectangle(195, 15, 50, 18));
        jthorap.setBounds(new Rectangle(105, 15, 75, 18));
        jtfechap.setBounds(new Rectangle(15, 15, 75, 18));
        jtobservaciones.setWrapStyleWord(true);
        jtobservaciones.setLineWrap(true);
        jtobservaciones.setMargin(new Insets(5, 5, 5, 5));
        jtextracto.setWrapStyleWord(true);
        jtextracto.setLineWrap(true);
        jtextracto.setMargin(new Insets(5, 5, 5, 5));
        jtletra.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        jtidorganismo.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        jtletra.setToolTipText("Pres. F10 p/Buscar un Tipo de Letra");
        jtidorganismo.setToolTipText("Pres. F10 p/Buscar un Organismo");
        jtcatastro.setBounds(new Rectangle(295, 30, 100, 20));
        jtidorganismo.setForeground(Color.red);
        jtidorganismo.setFont(new Font("Dialog", 0, 13));
        jtidorganismo.setBounds(new Rectangle(15, 30, 35, 20));
        jtletra.setForeground(Color.red);
        jtletra.setFont(new Font("Dialog", 0, 13));
        jtletra.setBounds(new Rectangle(65, 30, 45, 20));
        jtnroexp.setForeground(Color.red);
        jtnroexp.setFont(new Font("Dialog", 1, 15));
        jtnroexp.setBounds(new Rectangle(112, 30, 93, 20));
        jtanio.setForeground(Color.red);
        jtanio.setFont(new Font("Dialog", 0, 13));
        jtanio.setBounds(new Rectangle(220, 30, 40, 20));
        jtidinst.addKeyListener(this);
        jtcatastro.addKeyListener(this);
        jtidorganismo.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtletra.setDocument(new ValidaDato(ValidaDato.MAYUSCULA));
        jtnroexp.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtanio.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtcatastro.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jtidinst.setDocument(new ValidaDato(ValidaDato.ENTERO));
        jLabel1.setForeground(Color.red);
        jLabel1.setOpaque(true);
        jLabel1.setBounds(new Rectangle(585, 5, 185, 15));
        jLabel1.setText(" Presione F10 p/Seleccionar... ");
        jLabel2.setBounds(new Rectangle(430, 15, 130, 15));
        jLabel2.setText("Tipo de Documento:");
        jLabel3.setText(" Busqueda de Documentos: ");
        jLabel3.setBounds(new Rectangle(5, 5, 245, 15));
        jLabel3.setFont(new Font("Dialog", 1, 14));
        jLabel3.setOpaque(true);
        jLabel4.setText("F. Desde:");
        jLabel4.setBounds(new Rectangle(360, 65, 56, 20));
        jLabel5.setText("F. Hasta:");
        jLabel5.setBounds(new Rectangle(525, 65, 52, 20));
        jLabel6.setText(" Documentos encontrados: ");
        jLabel6.setBounds(new Rectangle(5, 130, 205, 20));
        jLabel6.setFont(new Font("Dialog", 1, 14));
        jLabel6.setOpaque(true);
        jLabel16.setFont(new Font("Dialog", 1, 20));
        jLabel16.setBounds(new Rectangle(205, 30, 15, 20));
        jLabel16.setText("-");
        jLabel7.setBounds(new Rectangle(670, 15, 60, 15));
        jLabel7.setText("Nº Orden:");
        jLabel8.setBounds(new Rectangle(220, 15, 30, 15));
        jLabel8.setText("Año:");
        jLabel9.setText("Nº Catastro:");
        jLabel9.setBounds(new Rectangle(295, 15, 75, 15));
        jLabel11.setText("Profesional:");
        jLabel11.setBounds(new Rectangle(30, 15, 73, 15));
        jLabel12.setBounds(new Rectangle(15, 55, 89, 15));
        jLabel12.setText("Tipo Iniciador:");
        jLabel13.setBounds(new Rectangle(630, 15, 77, 15));
        jLabel13.setText("Cant. Folios:");
        jLabel14.setBounds(new Rectangle(395, 53, 48, 15));
        jLabel14.setText("Asunto:");
        jLabel20.setBounds(new Rectangle(15, 15, 26, 15));
        jLabel20.setText("Org:");
        jLabel110.setFont(new Font("Dialog", 1, 20));
        jLabel110.setBounds(new Rectangle(50, 30, 15, 20));
        jLabel110.setText("-");
        jchekbuscar.setText("Numero:");
        jchekbuscar.setBounds(new Rectangle(65, 15, 80, 15));
        jchekbuscar.setSelected(true);
        jchekbuscar.addActionListener(this);
        jPanel1.setBounds(new Rectangle(5, 150, 780, 220));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        jPanel1.Redimensiona();
        jPanel2.setBounds(new Rectangle(5, 25, 775, 100));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel2.setLayout(null);
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
        jPanel3.setLayout(null);
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel4.setLayout(null);
        jPanel5.add(jtasunto, null);
        jPanel5.add(jLabel14, null);
        jPanel5.add(jtfolio, null);
        jPanel5.add(jLabel13, null);
        jPanel5.add(jttipoiniciador, null);
        jPanel5.add(jLabel12, null);
        jPanel5.add(jLabel11, null);
        jPanel5.add(jtprofesional, null);
        jPanel5.setLayout(null);
        jPanel5.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel6.setLayout(null);
        jPanel6.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        jPanel6.setBounds(new Rectangle(5, 510, 780, 20));
        jPanel7.add(lblpersonaemi, null);
        jPanel7.add(jtemi, null);
        jPanel7.add(jtoficemi, null);
        jPanel7.add(lbloficemi, null);
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
        jPanel7.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel7.setLayout(null);
        jPanel8.add(bAgregarSeguim, null);
        jPanel8.add(bBorrarSeguimiento, null);
        jPanel8.add(bverEnSeguimiento, null);
        jPanel8.add(bverSeguimiento, null);
        jPanel8.add(bBorrarSeleccionados, null);
        jPanel8.add(bVaciarLista, null);
        jPanel8.add(bAgregarSeleccionados, null);
        jScrollPane3.getViewport().add(listaSeguimiento, null);
        jPanel8.add(jScrollPane3, null);
        jPanel8.setLayout(null);
        jScrollPane1.setBounds(new Rectangle(5, 5, 760, 90));
        jScrollPane2.getViewport();
        jScrollPane2.setBounds(new Rectangle(10, 10, 765, 85));
        jScrollPane3.setBounds(new Rectangle(120, 5, 265, 85));
        jScrollPane1.getViewport().add(jtextracto, null);
        jPanel3.add(jScrollPane1, null);
        jTabbedPane1.addTab("Extracto o Contenido", jPanel3);
        jScrollPane2.getViewport().add(jtobservaciones, null);
        jPanel4.add(jScrollPane2, null);
        jTabbedPane1.addTab("Observaciones", jPanel4);
        jTabbedPane1.addTab("Otros Datos", jPanel5);
        jTabbedPane1.addTab("Ultimo Pase", jPanel7); 
        /** SEGUIMIENTO NO DISPONIBLE POR AHORA */
        //jTabbedPane1.addTab("Seguimiento", jPanel8);
        jTabbedPane1.setSelectedComponent(jPanel7);
        jTabbedPane1.setBounds(new Rectangle(5, 380, 775, 125));
        jPopupMenu1.add(impBusqueda);
        jPopupMenu1.add(impExpte);
        jPopupMenu1.add(impExptePases);
        jPopupMenu1.setLabel("jPopupMenu1");
        lblpersonarec.setHorizontalAlignment(SwingConstants.RIGHT);
        lblpersonaemi.setHorizontalAlignment(SwingConstants.RIGHT);
        lbloficrec.setHorizontalAlignment(SwingConstants.RIGHT);
        lbloficemi.setHorizontalAlignment(SwingConstants.RIGHT);
        lblpersonaemi.setText("Persona Emisora");
        lblpersonaemi.setBounds(new Rectangle(435, 45, 115, 15));
        lbloficemi.setBounds(new Rectangle(0, 40, 115, 15));
        lbloficemi.setText("Oficina Emisora");
        lblpersonarec.setBounds(new Rectangle(435, 70, 115, 15));
        lblpersonarec.setText("Persona Receptora");
        lbloficrec.setText("Oficina Receptora");
        lbloficrec.setBounds(new Rectangle(0, 70, 115, 15));
        lblestado.setText("Estado");
        lblestado.setBounds(new Rectangle(680, 0, 45, 15));
        lblobservacion.setText("Observaciones");
        lblobservacion.setBounds(new Rectangle(255, 0, 95, 15));
        lblfolios.setBounds(new Rectangle(195, 0, 45, 15));
        lblfolios.setText("Folios");
        lblhora.setText("Hora");
        lblhora.setBounds(new Rectangle(105, 0, 40, 15));
        lblfechap.setBounds(new Rectangle(15, 0, 40, 15));
        lblfechap.setText("Fecha");
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
        menuPases.addActionListener(this);
        menuAnexos.addActionListener(this);
        menuAdjuntos.addActionListener(this);
        menuPlazos.setEnabled(false);
        menuPromulgada.setEnabled(false);
        menuReferencias.setEnabled(false);
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
        menuAdjuntos.setMnemonic('d');
        menuPases.setMnemonic('p');
        menuAnexos.setMnemonic('a');
        menuAdjuntos.setText("Adjuntos");
        menuAnexos.setText("Anexos");
        menuPases.setText("Pases");
        menuAcciones.setLabel("jPopupMenu2");
        /*menuPases.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/pases.gif")));
        menuAnexos.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/anexo.gif")));
        menuAdjuntos.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/adjunto.gif")));
        menuReferencias.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/sanciones.gif")));
        menuPromulgada.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/promulgada.gif")));
        menuPlazos.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/reloj.gif")));*/
        datos1 = jPanel1.VDatos();
        bAgregarSeleccionados.setEnabled(false);
        bBorrarSeleccionados.setEnabled(false);
        bVaciarLista.setEnabled(false);
        bBorrarSeguimiento.setMnemonic('Q');
        bBorrarSeguimiento.setHorizontalTextPosition(SwingConstants.RIGHT);
        bBorrarSeguimiento.setHorizontalAlignment(SwingConstants.LEFT);
        bAgregarSeguim.setHorizontalAlignment(SwingConstants.LEFT);
        bAgregarSeguim.setHorizontalTextPosition(SwingConstants.RIGHT);
        bAgregarSeguim.setIconTextGap(3);
        bAgregarSeguim.setMargin(new Insets(2, 4, 2, 14));
        bAgregarSeguim.setMnemonic('a');
        bAgregarSeguim.setBounds(new Rectangle(15, 10, 95, 25));
        bAgregarSeguim.setText("Agregar");
        bBorrarSeguimiento.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bBorrarSeguimiento_actionPerformed(e);
                }
            }
        );
        bAgregarSeguim.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bAgregarSeguim_actionPerformed(e);
                }
            }
        );
        //centralPanel.setBounds(new Rectangle(5, 0, 790, 585));
        centralPanel.setLayout(null);
        bBorrarSeguimiento.setText("Quitar");
        bBorrarSeguimiento.setBounds(new Rectangle(15, 40, 95, 25));
        jtobservaciones.setBounds(new Rectangle(0, 0, 750, 82));
        bverEnSeguimiento.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bverEnSeguimiento_actionPerformed(e);
                }
            }
        );
        bprocesos.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    if (bprocesos.isEnabled())
                        menuAcciones.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        );
        bverSeguimiento.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bverSeguimiento_actionPerformed(e);
                }
            }
        );
        bAgregarSeleccionados.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bAgregarSeleccionados_actionPerformed(e);
                }
            }
        );
        bBorrarSeleccionados.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bBorrarSeleccionados_actionPerformed(e);
                }
            }
        );
        bVaciarLista.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bVaciarLista_actionPerformed(e);
                }
            }
        );
        timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
				       datosx = jPanel1.VDatos();
				       if (!datos1.equals(datosx)) {
					   datos1 = datosx;
					   jttipoiniciador.setText(OP_Proced.getCampo("SELECT descripcion||' - ('||abrev||')' from files.tiposiniciador where idtipo=" + datos1.elementAt(8).toString()));
					   jtasunto.setText(OP_Proced.getCampo("SELECT descripcion||' - ('||abrev||')' from files.tiposasunto where idtipo=" + datos1.elementAt(9).toString()));
					   jtextracto.setText(datos1.elementAt(13).toString());
					   jtobservaciones.setText(datos1.elementAt(14).toString());
					   jtfolio.setText(datos1.elementAt(15).toString());
					   jtprofesional.setText(datos1.elementAt(19).toString());
					   barra.setText(" Barra de Mensajes:");
					   /** EN EL CASO DE QUE SE ESTE SELECCIONANDO UN DOCUMENTO, EL CUAL SEA UN ADJUNTO O ANEXO A OTRO, ESTO ME INDICA EL 
                  * ,
                 O SEA EL DOCUEMTO AL CUAL FUE ADJUNTADO O ANEXADO. EL R DE ESTO ES INDICA EN JLABEL barra */
					   String idinst = OP_Proced.getCampo("SELECT idinst from files.instxinst where estado<>'*' AND idinstref=" + datos1.elementAt(0).toString());
					   if (idinst.length() > 0) {
					       menuAdjuntos.setEnabled(false);
					       barra.setText(" Adjuntos/Anexos: Este corre adjunto/anexo al documento (o expediente) '" + OP_Proced.getCampo("SELECT instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio FROM files.instlegal,files.tiposletra " + " Where idinst=" + idinst + " and instlegal.idtipodoc = tiposletra.idtipo and instlegal.idorganismo = tiposletra.idorganismo") + "'.");
					   } else /** ESTO ME INDICA EL NUMERO DE DOCUMENTOS ADJUNTADOS O ANEXADOS AL DOCUMENTO SELECCIONADO*/
					   {
					       menuAdjuntos.setEnabled(true);
					       String cantadjunto = OP_Proced.getCampo("SELECT count(*) from files.instxinst where estado<>'*' AND idinst=" + datos1.elementAt(0).toString());
					       if (!cantadjunto.equals("0"))
						   barra.setText(" Documento Cabecera: TIENE " + cantadjunto + " DOCUMENTOS O EXPEDIENTES ADJUNTADOS/ANEXADOS");
					   }
					   /** CADA DOC DEBE PERMANECER 'x' DIAS EN UNA OFICINA O DEPENDENCIA, ESTARA EN FUNCION DEL TIPO DE PLAZO Q SE LE HAYA INDICADO.
                  ENTONCES AL SELECCIONAR ESTE CALCULO LOS DIAS QUE TRANSCURRIERON AL DIA DE LA FECHA Y SE LOS SUMO AL ULTIMO PASE.
                  ESTA RUTINA NO ESTA ACTIVA, PARA ESTO DES-COMENTAR LA LINEA Q DICE ACTIVAR!!!!!! */
					   if (!OP_Proced.getCampo("SELECT count(*) from files.pases where estado<>'*' and idinst=" + datos1.elementAt(0).toString()).equals("0")) {
					       UltimoPase();
					       ResultSet Reg = OP_Proced.exConsulta("SELECT max(fechapase||' '||horapase) from files.pases where estado<>'*' and idinst=" + datos1.elementAt(0).toString());
					       if (Reg.next()) {
						   String FechaRecepcion = OP_Proced.TransformaNull_Texto(OP_Proced.getCampo("SELECT fecha_rec FROM files.pases  WHERE estado<>'*' and idinst=" + datos1.elementAt(0).toString() + " and fechapase||' '||horapase='" + Reg.getString(1) + "'"));
						   if (!FechaRecepcion.equals("0")) {
						       String FechaActual = OP_Proced.Fecha2(OP_Proced.FechaHora(true, false), false);
						       int CantDia = Integer.parseInt(OP_Proced.ObtieneCantDia(FechaRecepcion, FechaActual));
						       int CantDiaNoLaborables = Integer.parseInt(OP_Proced.getCampo("SELECT count(*) FROM calendario WHERE fecha BETWEEN '" + FechaRecepcion + "' AND '" + FechaActual + "'"));
						       String Q = "Update files.pases set plazo=" + String.valueOf(CantDia - CantDiaNoLaborables) + " WHERE idinst=" + datos1.elementAt(0).toString() + " and fechapase||' '||horapase='" + Reg.getString(1) + "'";
						       if (!OP_Proced.getCampo("SELECT idoficrec from files.pases where idinst=" + datos1.elementAt(0).toString() + " and fechapase||' '||horapase='" + Reg.getString(1) + "'").equals("82"))// (82) Oficina ARCHIVO GENERAL!!!!!!!!!!!!!!!!
						       {
							   /**ACTIVARRRRR!!!!!!!!!!!!!!!    **/
							   OP_Proced.exActualizar('a',Q);     
							   int Plazo = Integer.parseInt(OP_Proced.getCampo("SELECT plazo from files.tiposplazo where idtipo=(SELECT idtipoplazo from files.instlegal where idinst=" + datos1.elementAt(0).toString() + ")"));
							   int vencido = CantDia - CantDiaNoLaborables - Plazo;
							   if ((Plazo < CantDia - CantDiaNoLaborables) && (vencido >= 90)) {
							       Advisor.messageBox("<html>El Documento " + datos1.elementAt(3) + "<br>ha superado el plazo por " + vencido + " dias</html>", "Plazo vencido");
							   }
						       }
						   }
					       }
					   } else {
					       jtfechap.setText("");
					       jthorap.setText("");
					       jtoficemi.setText("");
					       jtemi.setText("");
					       jtoficrec.setText("");
					       jtrec.setText("");
					       jtfoliosp.setText("");
					       jtestado.setText("");
					       jtobservacion.setText("");
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
                        //idtipoinst = OP_Proced.getCampo("SELECT idtipo from files.tiposinstlegal where descripcion='" + jctipoinst.getSelectedItem() + "'");
                        idtipoinst = OP_Proced.getCampo("SELECT idtipo FROM files.tiposinstlegal WHERE descripcion='" + jctipoinst.getSelectedItem() + "'");
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
        //OP_Proced.CargaCombo(jctipoinst, "SELECT descripcion from files.tiposinstlegal where estado<>'*'", "EXPEDIENTE");
        OP_Proced.CargaCombo(jctipoinst, "SELECT descripcion FROM files.tiposinstlegal WHERE estado<>'*'", "EXPEDIENTE");
        bseleccionar.setEnabled(false);
        bprocesos.setEnabled(false);
        bimprimir.setEnabled(false);
        bmodificar.setEnabled(false);
        beliminar.setEnabled(false);
        jtletra.transferFocus();
        //cargarListaSeguimiento(); 
        setearCabecera();
        //bimprimir.setText("Imp.");
        
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Seleccione el Expediente con el que desea trabajar");
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
        cabecera.addElement("Anexo/Adj.");
        cabecera.addElement("Bibl.");
    }

    public void ActualizaTabla() {
        if (!primeravez) {
            bseleccionar.setEnabled(false);
            bprocesos.setEnabled(false);
            bmodificar.setEnabled(false);
            beliminar.setEnabled(false);
            bimprimir.setEnabled(true);
            if (bverEnSeguimiento.isSelected()) {
                //cfiltro += " and idinst in (SELECT idinst from files.seguimiento where usuario = '" + OP_Proced.getSQLUser() + "')";
                cfiltro += " AND idinst IN (SELECT idinst FROM files.seguimiento WHERE usuario = '" + OP_Proced.getSQLUser() + "')";
            }
            Consulta = "SELECT idinst,idtipoinst,instlegal.idtipodoc,(instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio) as expte," 
                        + "catastros.catastro,anio,nroexp,fechaexp,idtipoiniciador,idtipoasunto,tiposiniciador.abrev as iniciador,tiposasunto.abrev as asunto," 
                        + "iniciante,extracto,instlegal.observaciones,cantfolios,estadoexp,instlegal.idcatastro,"
                        + "instlegal.idprofesional,apellido||', '||nombre as profesional,instlegal.estado,tiposletra.letra,instlegal.cantanexo,instlegal.bibliorato "
                        + " FROM files.instlegal,files.tiposorganismo,files.tiposiniciador,files.tiposasunto,cepax.catastros,sopsalta.profesionales,files.tiposletra"
                        + " WHERE instlegal.estado<>'*' " + cfiltro + " and instlegal.idtipodoc=tiposletra.idtipo "
                        + " and instlegal.idorganismo=tiposletra.idorganismo "
                        + " and instlegal.idorganismo=tiposorganismo.idorganismo "
                        + " and instlegal.idtipoiniciador=tiposiniciador.idtipo  "
                        + " and instlegal.idtipoasunto=tiposasunto.idtipo "
                        + " and instlegal.idcatastro=catastros.idcatastro "
                        + " AND instlegal.idprofesional=profesionales.idprofesional ";
            Consulta += " ORDER BY nroexp,anio,fechaexp";
            System.out.println(Consulta);         
            ConsultaCount = "SELECT count(*)  FROM files.instlegal,files.tiposorganismo,files.tiposiniciador,files.tiposasunto,cepax.catastros,sopsalta.profesionales,files.tiposletra " + " WHERE instlegal.estado<>'*'" + cfiltro + " and instlegal.idtipodoc=tiposletra.idtipo " + " and instlegal.idorganismo=tiposletra.idorganismo  " + " and instlegal.idorganismo=tiposorganismo.idorganismo  " + " and instlegal.idtipoiniciador=tiposiniciador.idtipo   " + " and instlegal.idtipoasunto=tiposasunto.idtipo  " + " and instlegal.idcatastro=catastros.idcatastro  " + " and instlegal.idprofesional=profesionales.idprofesional";
            //System.out.println(ConsultaCount);
            //jPanel1.ActualizaTabla((JDialog)this,Consulta,ConsultaCount);
            //ESTO SE DEBE HACER SI O SI ANTES DEL ActualizaTabla, para guardar los que han sido seleccionados
            //System.out.println(Consulta);
            jPanel1.ActualizaTabla(this, Consulta, ConsultaCount, cabecera);
            jPanel1.setColumnaEditable(6);
            datos1.clear();
        }
    }

    private void Buscar() {
        String SQLIdInst = "", SQLIdOrganismo = "", SQLLetra = "", SQLExpte = "", SQLPalabra = "", SQLFecha = "", SQLAnio = "", SQLCatastro = "";
        primeravez = false;
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
                SQLAnio = " AND instlegal.anio=" + jtanio.getText();
            } else {
                SQLAnio = "";
            }
            if (jtpalabra.getText().length() > 0) {
                SQLPalabra = " AND (upper(extracto) LIKE upper('%" + jtpalabra.getText() + "%')" + " or upper(instlegal.observaciones) LIKE upper('%" + jtpalabra.getText() + "%')" + " or upper(iniciante) LIKE upper('%" + jtpalabra.getText() + "%'))";
            } else {
                SQLPalabra = "";
            }
            if (jtdesde.getText().length() > 0 && jthasta.getText().length() > 0) {
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
                getParentInternalFrame().close();
            } else if (e.getSource() == bagregar) {
                nuevo = new frmExpedientes("", idtipoinst);
                nuevo.setParentMain(this);
                ExtendedInternalFrame nuevoContainer = new ExtendedInternalFrame("Nuevo Expediente");
                nuevoContainer.setCentralPanel(nuevo);
                nuevoContainer.show();
                /*OP_Proced.CentraVentana(nuevo);
                nuevo.setModal(true);
                nuevo.setVisible(true);*/
                
                if (!nuevo.cancelar) {
                    OP_Proced.CargaCombo(jctipoinst, "SELECT descripcion from files.tiposinstlegal where estado<>'*'", OP_Proced.getCampo("SELECT descripcion from files.tiposinstlegal where idtipo=" + nuevo.tipoinst));
                    cfiltro = " AND idtipoinst=" + nuevo.tipoinst + " AND nroexp=" + nuevo.nroexp + " AND instlegal.anio=" + nuevo.anio;
                    ActualizaTabla();
                }
            } else if (e.getSource() == bmodificar) {
                boolean cancelar = false;
                if (!datos1.isEmpty()) {
                    /** CUANDO EL CAMPO 'estado' DE TODAS LAS TABLAS ES IGUAL 'a' QUIERE DECIR Q ESTA SIENDO ACCEDIDO POR OTRO USUARIO */
                    if (!datos1.elementAt(20).toString().equals("a")) {
                        if (OP_Proced.exActualizar('a', "Update files.instlegal set estado='a' where idinst=" + datos1.elementAt(0).toString())) {
                            nuevo = new frmExpedientes("SELECT * from files.instlegal where idinst=" + datos1.elementAt(0).toString(), "");
                            nuevo.setParentMain(this);
                            ExtendedInternalFrame nuevoContainer = new ExtendedInternalFrame("Nuevo Expedientes");
                            nuevoContainer.setCentralPanel(nuevo);
                            nuevoContainer.show();
                            /*OP_Proced.CentraVentana(nuevo);
                            nuevo.setModal(true);
                            nuevo.setVisible(true);*/
                            cancelar = nuevo.cancelar;
                            OP_Proced.exActualizar('a', "Update files.instlegal set estado='' where idinst=" + datos1.elementAt(0).toString());
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
                    String Q = "Update files.instlegal set estado='*' where idinst=" + datos1.elementAt(0).toString();
                    if (OP_Proced.exActualizar('b', Q))
                        ActualizaTabla();
                } else {
                    OP_Proced.Errores(2);
                }
            } else if (e.getSource() == bbuscar) {
                Buscar();
            } else if (e.getSource() == menuPases) {
                if (!datos1.isEmpty()) {
                    pases = new Pases(datos1.elementAt(0).toString());
                    pases.setParentMain(this);
                    ExtendedInternalFrame pasesContainer = new ExtendedInternalFrame("Pases");
                    pasesContainer.setCentralPanel(pases);
                    pasesContainer.show();
                    /*OP_Proced.CentraVentana(pases);
                    pases.setModal(true);
                    pases.setVisible(true);*/
                } else {
                    OP_Proced.Errores(2);
                }
            } else if (e.getSource() == menuAnexos) {
                if (!datos1.isEmpty()) {
                    anexos = new Anexos_InstLegal("2", datos1.elementAt(0).toString(), datos1.elementAt(3).toString());
                    anexos.setParentMain(this);
                    ExtendedInternalFrame anexosContainer = new ExtendedInternalFrame("Anexos");
                    anexosContainer.setCentralPanel(anexos);
                    anexosContainer.show();
                    /*OP_Proced.CentraVentana(anexos);
                    anexos.setModal(true);
                    anexos.setVisible(true);*/
                    ActualizaTabla();
                } else {
                    OP_Proced.Errores(2);
                }
            } else if (e.getSource() == menuAdjuntos) {
                if (!datos1.isEmpty()) {
                    adjuntos = new Anexos_InstLegal("4", datos1.elementAt(0).toString(), datos1.elementAt(3).toString());
                    adjuntos.setParentMain(this);
                    ExtendedInternalFrame adjuntosContainer = new ExtendedInternalFrame("Anexos");
                    adjuntosContainer.setCentralPanel(adjuntos);
                    adjuntosContainer.show();
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
                 getParentInternalFrame().close();
            } else if (e.getSource() == impBusqueda) {
                //CreaHTML.HTMLListadoExptes(cfiltro, "ListadoExptes");
                BasicReport report = new BasicReport(Expedientes.class.getResource("xml/FilesList.xml"));
                report.addTableModel("files.xmlGetAllFiles", "'"+ cfiltro +"'");
                report.doReport();
            } else if (e.getSource() == impExpte) {
                //CreaHTML.HTMLExpteSeleccionado(datos1.elementAt(0).toString(), "ExpteSeleccionado", false);
                //System.out.println("datos1.elementAt(0).toString(): " + datos1.elementAt(0).toString());
                BasicReport report = new BasicReport(Expedientes.class.getResource("xml/FileSelected.xml"));
                report.addTableModel("files.xmlGetFileSelected", "'"+ datos1.elementAt(0).toString() +"'"); 
                report.doReport();
            } else if (e.getSource() == impExptePases) {
                //CreaHTML.HTMLExpteSeleccionado(datos1.elementAt(0).toString(), "ExpteSelecCPases", true);
                BasicReport report = new BasicReport(Expedientes.class.getResource("xml/FileSelectedWithPasses.xml"));
                report.addTableModel("files.xmlGetFileSelected", "'"+ datos1.elementAt(0).toString() +"'"); 
                report.addTableModel("files.xmlGetPassesByFileSelected", "'"+ datos1.elementAt(0).toString() +"'");
                report.doReport(); 
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
            if (k.getSource() == jtidorganismo || k.getSource() == jtletra || k.getSource() == jtnroexp || k.getSource() == jtpalabra || k.getSource() == jtanio || k.getSource() == jthasta || k.getSource() == jtidinst || k.getSource() == jtcatastro) {
                //JEntry texto = (JEntry)k.getSource();
                Buscar();
            }
        }
    }

    private void UltimoPase() {
        try {
            //System.out.println("SELECT MAX(fechapase||' '||horapase) FROM files.pases WHERE estado<>'*' AND idinst="+ datos1.elementAt(0).toString());
            ResultSet Reg10 = OP_Proced.exConsulta("SELECT MAX(fechapase||' '||horapase) FROM files.pases WHERE estado<>'*' AND idinst=" + datos1.elementAt(0).toString());
            if (Reg10.next()) {
                ResultSet Reg11 = OP_Proced.exConsulta("SELECT * FROM files.pases WHERE estado<>'*' AND idinst=" + datos1.elementAt(0).toString() + " AND fechapase||' '||horapase='" + Reg10.getString(1) + "'");
                if (Reg11.next()) {
                    //System.out.println("Consulta: "+Reg11);
                    //System.out.println("Consulta: "+ "SELECT * FROM files.pases WHERE estado<>'*' AND idinst="+ datos1.elementAt(0).toString()
                    //                              +" AND fechapase||' '||horapase='"+ Reg10.getString(1) +"'");  
                    /*System.out.println("Reg (5): "+Reg11.getString(5));
		    System.out.println("Reg (6): "+Reg11.getString(6));*/
                    jtfechap.setText(OP_Proced.Fecha2(Reg11.getString(2), true));
                    jthorap.setText(OP_Proced.Hora(Reg11.getString(3), true, false));
                    /////agregó Germán
                    //System.out.println("Estadopase (13): "+Reg11.getString(13));
                    idoficemi = Reg11.getString(4);
                    jtoficemi.setText(OP_Proced.getCampo("SELECT descripcion FROM files.tiposoficina WHERE idtipo=" + Reg11.getString(4)));
                    jtemi.setText(OP_Proced.getCampo("SELECT apellido||', '||nombre FROM personas WHERE idpersona=" + Reg11.getString(5)));
                    idemisor = Reg11.getString(5);
                    ////
                    idoficrec = Reg11.getString(7);
                    jtoficrec.setText(OP_Proced.getCampo("SELECT descripcion FROM files.tiposoficina WHERE idtipo=" + Reg11.getString(7)));
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
    /*  private void AgregarSeleccionados() 
  {
    Vector idSelecteds = jPanel1.getSelecteds(0);
    Vector SELECTeds = jPanel1.getSelecteds(1);
    for (int i = 0; i < selecteds.size(); i++) 
    {
      if (!seleccionados.contains(selecteds.elementAt(i))) 
      {
        idSeleccionados.add(idSelecteds.elementAt(i));
        seleccionados.add(selecteds.elementAt(i));
      }
    }
    AgregarSeleccionadosALista();
  }*/
    /*  private void AgregarSeleccionadosALista() 
  {
    listaSeguimiento.setListData(seleccionados);
  }*/

    private void bAgregarSeleccionados_actionPerformed(ActionEvent e) {
        for (int i = 0; i < seleccionados.size(); i++) {
            //Para una estructura (idinst, usuario, monto, observaciones, estado)
            OP_Proced.exActualizar('a', "INSERT INTO files.seguimiento values(" + idSeleccionados.elementAt(i).toString() + ", '" + OP_Proced.getSQLUser() + "', 0.0, '','')");
        }
    }

    private void bBorrarSeleccionados_actionPerformed(ActionEvent e) {
        for (int i = listaSeguimiento.getSelectedIndices().length - 1; i >= 0; i--) {
            seleccionados.removeElementAt((listaSeguimiento.getSelectedIndices()[i]));
            idSeleccionados.removeElementAt((listaSeguimiento.getSelectedIndices()[i]));
        }
        listaSeguimiento.setListData(seleccionados);
    }

    private void bVaciarLista_actionPerformed(ActionEvent e) {
        listaSeguimiento.setListData(new Vector());
    }

    private void cargarListaSeguimiento() {
        ResultSet res = OP_Proced.exConsulta("SELECT instlegal.idorganismo||'-'||tiposletra.letra||nroexp||'-'||anio as expte, idinst FROM files.instlegal,files.tiposletra " + " Where instlegal.idtipodoc = tiposletra.idtipo and instlegal.idorganismo = tiposletra.idorganismo and idinst in (SELECT idinst from files.seguimiento where usuario = '" + OP_Proced.getSQLUser() + "')");
        try {
            while (res.next()) {
                seleccionados.add(res.getString("expte"));
                idSeleccionados.add(res.getString("idinst"));
            }
        } catch (SQLException x) {
            OP_Proced.Mensaje(x.getMessage(), "Error");
        }
        listaSeguimiento.setListData(seleccionados);
    }

    void bverSeguimiento_actionPerformed(ActionEvent e) {
        seg = new Seguimiento();
        seg.setParentMain(this);
        ExtendedInternalFrame segContainer = new ExtendedInternalFrame("Seguimiento");
        segContainer.setCentralPanel(seg);
        segContainer.show();
        /*OP_Proced.CentraVentana(seg);
        seg.setModal(true);
        seg.setVisible(true);*/
    }

    void bverEnSeguimiento_actionPerformed(ActionEvent e) {
        if (bverEnSeguimiento.isSelected()) {
            cfiltroPrev = cfiltro;
            cfiltro = "";
            ActualizaTabla();
        } else {
            cfiltro = cfiltroPrev;
        }
    }

    void bBorrarSeguimiento_actionPerformed(ActionEvent e) {
        boolean act = false;
        for (int i = listaSeguimiento.getSelectedIndices().length - 1; i >= 0; i--) {
            if (OP_Proced.exActualizar('b', "delete from files.seguimiento where idinst = " + idSeleccionados.elementAt(i).toString() + " and usuario = '" + OP_Proced.getSQLUser() + "'")) {
                seleccionados.removeElementAt((listaSeguimiento.getSelectedIndices()[i]));
                idSeleccionados.removeElementAt((listaSeguimiento.getSelectedIndices()[i]));
                act = true;
            }
        }
        listaSeguimiento.setListData(seleccionados);
        if (act)
            ActualizaTabla();
    }

    void bAgregarSeguim_actionPerformed(ActionEvent e) {
        Vector idSelecteds = jPanel1.getSelecteds(0);
        Vector selecteds = jPanel1.getSelecteds(1);
        for (int i = 0; i < selecteds.size(); i++) {
            if (!seleccionados.contains(selecteds.elementAt(i))) {
                idSeleccionados.add(idSelecteds.elementAt(i));
                seleccionados.add(selecteds.elementAt(i));
                OP_Proced.exActualizar('a', "INSERT INTO files.seguimiento values(" + idSeleccionados.elementAt(i).toString() + ", '" + OP_Proced.getSQLUser() + "', 0.0, '','')");
            }
        }
        /*for (int i = 0; i<seleccionados.size(); i++) 
    {
      //Para una estructura (idinst, usuario, monto, observaciones, estado)
      Proced.exActualizar('a', "INSERT INTO files.seguimiento values(" + idSeleccionados.elementAt(i).toString() + ", '"
       + Proced.getSQLUser() + "', 0.0, '','')");
    }*/
        listaSeguimiento.setListData(seleccionados);
    }

    public void setParentMain(principal_simex _parentMain) {
        parentMain = _parentMain;
    }
}
