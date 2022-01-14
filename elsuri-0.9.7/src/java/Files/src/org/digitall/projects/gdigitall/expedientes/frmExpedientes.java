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
 * frmExpedientes.java
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

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.digitall.lib.components.JIntEntry;
import org.digitall.lib.data.listeners.intListen;

import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.environment.Environment;
import org.digitall.projects.gdigitall.lib.components.Carga2Campo;
import org.digitall.projects.gdigitall.lib.components.CargaCampo;
import org.digitall.projects.gdigitall.lib.components.JEntry;
import org.digitall.projects.gdigitall.lib.components.JTFecha;
import org.digitall.projects.gdigitall.lib.components.Login;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

//???import org.opsalta.sistemas.SOPSalta.Profesionales;
//public class frmExpedientes extends JDialog implements ActionListener, KeyListener {
public class frmExpedientes extends BasicPrimitivePanel implements ActionListener, KeyListener {
    private JComboBox jctipoinst = new JComboBox();
    private JComboBox jctipo = new JComboBox();
    private JComboBox jctipoiniciador = new JComboBox();
    private JComboBox jcasunto = new JComboBox();
    private JComboBox jcestado = new JComboBox();
    private JComboBox jcprofesional = new JComboBox();
    private JComboBox jcorganismo = new JComboBox();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel9 = new JLabel();
    private JLabel jLabel10 = new JLabel();
    private JLabel jLabel11 = new JLabel();
    private JLabel jLabel12 = new JLabel();
    private JLabel jLabel13 = new JLabel();
    private JLabel jLabel14 = new JLabel();
    private JLabel lblProfesional = new JLabel();
    private JLabel jLabel16 = new JLabel();
    private JLabel jLabel17 = new JLabel();
    private JLabel jLabel18 = new JLabel();
    private JLabel jLabel19 = new JLabel();
    private JLabel jLabel20 = new JLabel();
    private JLabel jLabel22 = new JLabel();
    private JLabel jLabel24 = new JLabel();
    private JLabel lblBuscarProfesional = new JLabel();
    private JLabel jLabel27 = new JLabel();
    private JLabel jLabel110 = new JLabel();
    private JLabel jLabel111 = new JLabel();
    private JButton baddinst = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/add.gif")));
    private JButton bmodinst = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/mod.gif")));
    private JButton baddtipoiniciador = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/add.gif")));
    private JButton baddasunto = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/add.gif")));
    private JButton bprofesionales = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/buscar2.gif")));
    private JButton baceptar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
    private JButton bcancelar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    private JButton bmodtipoiniciador = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/mod.gif")));
    private JButton bmodasunto = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/mod.gif")));
    private JButton bplazo = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/buscar2.gif")));
    private JButton bhabilitar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/activar.gif")));
    private JEntry jtnroexp = new JEntry();
    private JEntry jtanio = new JEntry();
    private JEntry jtletra = new JEntry();
    private JEntry jtiniciante = new JEntry();
    private JEntry jtidtipoiniciador = new JEntry();
    private JEntry jtidasunto = new JEntry();
    private JIntEntry jtfolios = new JIntEntry();
    private JEntry jtcatastro = new JEntry();
    private JEntry jtidprofesional = new JEntry();
    private JEntry jtidorganismo = new JEntry();
    private JIntEntry jtplazo = new JIntEntry();
    private JIntEntry jtdia = new JIntEntry();
    private JTFecha jtfecha = new JTFecha();
    private JTextArea jtcontenido = new JTextArea();
    private JTextArea jtobservaciones1 = new JTextArea();
    private JTextArea jtcontenido1 = new JTextArea();
    private JIntEntry jtcuenta = new JIntEntry();
    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel4 = new JPanel();
    private JPanel jPanel6 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private String idinst = "", Query = "", idcatastro = "", idtipoinst = "", tipoexpte = "", idtipoexpte = "";
    private String idtipodoc = "0", sidorganismo = "0", sidtipoiniciador = "0", sidasunto = "0", sidprofesional = "0";
    private JCheckBox jchekgenerar = new JCheckBox();
    static String nroexp = "", anio = "", tipoinst = "";
    static boolean cancelar = true;
    private JPanel centralPanel = new JPanel();
    //********************************************
    private Expedientes parentMain;
    private CargaCampo tipo;
    private Plazos plazo;
    private JTextField jtBibliorato = new JTextField();
    private JLabel lblBibliorato = new JLabel();

    /**
   * FORMULARIO PARA CARGAR O MODIFICAR UN DOCUMENTO
   * 
   * @param IdTipoInst: ME INDICA SI ES EXPEDIENTE, NOTA, ETC.
   * @param SQLQuery: ME INDICA SI SE ESTA POR MOFICAR O NO, CUANDO ESTE ES VACIO SE TRATA DE UN NUEVO DOCUMENTO, POR EL CONTRARIO ES PARA MODIFICAR EL DOC EN CUESTION
   */
    public frmExpedientes(String SQLQuery, String IdTipoInst) {
        try {
            Query = SQLQuery;
            idtipoinst = IdTipoInst;
            jbInit();
        } catch (SQLException e) {
            OP_Proced.Mensaje(e.getMessage().toUpperCase(), "Acceso a la Base de Datos no Autorizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(790, 550));
        //this.getContentPane().setLayout(null);
        this.setTitle("Agregar/Modificar un Documento");
        centralPanel.add(jLabel7, null);
        centralPanel.add(jLabel24, null);
        centralPanel.add(jLabel27, null);
        centralPanel.add(jPanel6, null);
        centralPanel.add(jPanel1, null);
        centralPanel.add(bcancelar, null);
        centralPanel.add(baceptar, null);
        centralPanel.add(bhabilitar, null);
        centralPanel.add(jPanel4, null);
        /*this.getContentPane().add(centralPanel, null);
        this.getContentPane().add(jtcontenido1, null);
        this.getContentPane().add(jtobservaciones1, null);
        */
        this.add(centralPanel, BorderLayout.CENTER);
        this.add(jtcontenido1, null);
        this.add(jtobservaciones1, null);
        jcorganismo.setBounds(new Rectangle(10, 60, 480, 20));
        jctipoinst.setBounds(new Rectangle(140, 20, 180, 20));
        jctipo.setBounds(new Rectangle(10, 105, 480, 20));
        jctipoiniciador.setBounds(new Rectangle(190, 80, 450, 20));
        jcasunto.setBounds(new Rectangle(190, 125, 450, 20));
        jcestado.setBounds(new Rectangle(565, 30, 105, 20));
        jcprofesional.setBounds(new Rectangle(425, 35, 285, 20));
        jtnroexp.setBounds(new Rectangle(105, 40, 95, 20));
	jtnroexp.addKeyListener(new intListen());
        jtnroexp.setFont(new Font("Dialog", 1, 15));
        jtnroexp.setForeground(Color.red);
        jtanio.setBounds(new Rectangle(215, 40, 38, 20));
	jtanio.addKeyListener(new intListen());
        jtanio.setFont(new Font("Dialog", 0, 13));
        jtanio.setForeground(Color.red);
        jtfecha.setBounds(new Rectangle(10, 30, 105, 20));
        jtfecha.setFont(new Font("Dialog", 1, 14));
        jtletra.setBounds(new Rectangle(60, 40, 43, 20));
        jtletra.setFont(new Font("Dialog", 0, 13));
        jtletra.setForeground(Color.red);
        jtletra.setEditable(false);
        jtletra.setBackground(Color.white);
        jtiniciante.setBounds(new Rectangle(130, 30, 360, 20));
        jtidtipoiniciador.setBounds(new Rectangle(10, 80, 140, 20));
        jtidtipoiniciador.setText("0");
        jtidasunto.setBounds(new Rectangle(10, 125, 140, 20));
        jtidasunto.setText("0");
        jtidprofesional.setBounds(new Rectangle(285, 35, 118, 20));
        jtidprofesional.setText("0");
        jtidorganismo.setBounds(new Rectangle(10, 40, 35, 20));
        jtidorganismo.setFont(new Font("Dialog", 0, 13));
        jtidorganismo.setForeground(Color.red);
        jtidorganismo.setEditable(false);
        jtidorganismo.setBackground(Color.white);
        jtfolios.setBounds(new Rectangle(500, 30, 60, 20));
	jtcatastro.addKeyListener(new intListen());
        jtcatastro.setBounds(new Rectangle(15, 35, 85, 20));
        jtcatastro.setText("0");
        jtcontenido.setLineWrap(true);
        jtcontenido.setMargin(new Insets(5, 5, 5, 5));
        jtcontenido.setWrapStyleWord(true);
        jtobservaciones1.setBounds(new Rectangle(0, 0, 0, 15));
        jtcontenido1.setBounds(new Rectangle(0, 0, 0, 15));
        jtplazo.setBounds(new Rectangle(680, 170, 85, 20));
        jtdia.setBounds(new Rectangle(680, 216, 30, 20));
        jtcuenta.setBounds(new Rectangle(138, 35, 110, 20));
        jLabel1.setText("NºCuenta:");
        jLabel1.setBounds(new Rectangle(138, 20, 62, 15));
        jLabel2.setText("Tipo de Documento:");
        jLabel2.setBounds(new Rectangle(10, 20, 130, 15));
        jLabel3.setText("Tipo/Letra:");
        jLabel3.setBounds(new Rectangle(10, 88, 69, 15));
        jLabel3.setToolTipText("null");
        jLabel4.setText("Numero:");
        jLabel4.setBounds(new Rectangle(60, 24, 60, 17));
        jLabel4.setFont(new Font("Dialog", 1, 14));
        jLabel5.setText("Año:");
        jLabel5.setBounds(new Rectangle(215, 25, 28, 15));
        jLabel6.setText("Fecha:");
        jLabel6.setBounds(new Rectangle(10, 14, 38, 15));
        jLabel7.setText(" Datos Principales: ");
        jLabel7.setBounds(new Rectangle(10, 160, 140, 15));
        jLabel7.setOpaque(true);
        jLabel7.setFont(new Font("Dialog", 1, 14));
        jLabel7.setForeground(Color.blue);
        jLabel8.setText("Iniciante:");
        jLabel8.setBounds(new Rectangle(130, 15, 56, 15));
        jLabel9.setText("Tipo Iniciador:");
        jLabel9.setBounds(new Rectangle(190, 63, 90, 15));
        jLabel10.setText("Asunto:");
        jLabel10.setBounds(new Rectangle(190, 111, 48, 15));
        jLabel11.setText("Buscar Tipo Iniciador:");
        jLabel11.setBounds(new Rectangle(10, 63, 135, 15));
        jLabel12.setText("Buscar Asunto:");
        jLabel12.setBounds(new Rectangle(10, 111, 93, 15));
        jLabel13.setText("Folios:");
        jLabel13.setBounds(new Rectangle(500, 15, 41, 15));
        jLabel14.setText("Estado:");
        jLabel14.setBounds(new Rectangle(565, 15, 45, 15));
        lblProfesional.setText("Profesional:");
        lblProfesional.setBounds(new Rectangle(425, 20, 73, 15));
        jLabel16.setText("-");
        jLabel16.setBounds(new Rectangle(200, 40, 15, 20));
        jLabel16.setFont(new Font("Dialog", 1, 20));
        jLabel17.setText("Contenido:");
        jLabel17.setBounds(new Rectangle(10, 155, 68, 15));
        jLabel18.setText("Tipo Plazo:");
        jLabel18.setBounds(new Rectangle(610, 173, 68, 15));
        jLabel19.setText("Organismo:");
        jLabel19.setBounds(new Rectangle(10, 45, 72, 15));
        jLabel20.setText("Org:");
        jLabel20.setBounds(new Rectangle(10, 25, 26, 15));
        jLabel22.setText("Nº Catastro:");
        jLabel22.setBounds(new Rectangle(15, 20, 75, 15));
        jLabel24.setText(" Datos del Catastro: ");
        jLabel24.setBounds(new Rectangle(15, 430, 150, 15));
        jLabel24.setFont(new Font("Dialog", 1, 14));
        jLabel24.setOpaque(true);
        lblBuscarProfesional.setText("Buscar Profesional:");
        lblBuscarProfesional.setBounds(new Rectangle(285, 20, 118, 15));
        jLabel27.setText(" Ingresar/Generar Nº del Documento: ");
        jLabel27.setBounds(new Rectangle(15, 5, 285, 20));
        jLabel27.setFont(new Font("Dialog", 1, 14));
        jLabel27.setOpaque(true);
        jLabel110.setText("-");
        jLabel110.setBounds(new Rectangle(45, 40, 15, 20));
        jLabel110.setFont(new Font("Dialog", 1, 20));
        jLabel111.setText("Dias:");
        jLabel111.setBounds(new Rectangle(645, 219, 31, 15));
        jPanel1.setLayout(null);
        jPanel1.setBounds(new Rectangle(5, 170, 770, 250));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
        jPanel2.setBounds(new Rectangle(510, 30, 260, 80));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel2.setLayout(null);
        jPanel4.setLayout(null);
        jPanel4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel4.setBounds(new Rectangle(5, 440, 775, 60));
        jPanel4.add(jtcuenta, null);
        jPanel4.add(jLabel1, null);
        jPanel4.add(lblBuscarProfesional, null);
        jPanel4.add(jtidprofesional, null);
        jPanel4.add(lblProfesional, null);
        jPanel4.add(jcprofesional, null);
        jPanel4.add(jtcatastro, null);
        jPanel4.add(jLabel22, null);
        jPanel4.add(bprofesionales, null);
        jPanel6.setBounds(new Rectangle(5, 15, 775, 130));
        jPanel6.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel6.setLayout(null);
        baddasunto.setBounds(new Rectangle(665, 124, 45, 23));
        baddasunto.setToolTipText("Agregar asunto");
        baddasunto.setText("+");
        baddtipoiniciador.setBounds(new Rectangle(665, 79, 45, 23));
        baddtipoiniciador.setToolTipText("Agregar iniciador");
        baddtipoiniciador.setText("+");
        bhabilitar.setMargin(new Insets(2, 5, 2, 14));
        baddinst.setBounds(new Rectangle(330, 19, 45, 23));
        bmodinst.setBounds(new Rectangle(380, 19, 45, 23));
        bmodtipoiniciador.setBounds(new Rectangle(720, 79, 45, 23));
        bmodtipoiniciador.setText("M");
        bmodtipoiniciador.setToolTipText("Modificar tipo iniciador");
        bmodasunto.setBounds(new Rectangle(720, 124, 45, 23));
        bmodasunto.setText("M");
        bmodasunto.setToolTipText("Modificar asunto");
        bprofesionales.setMnemonic('p');
        bprofesionales.setText("...");
        bprofesionales.setBounds(new Rectangle(720, 34, 45, 23));
        baceptar.setText("Aceptar");
        baceptar.setBounds(new Rectangle(555, 510, 100, 25));
        baceptar.setMnemonic('a');
        bcancelar.setText("Cancelar");
        bcancelar.setBounds(new Rectangle(675, 510, 105, 25));
        bcancelar.setMnemonic('c');
        bplazo.setText("...");
        bplazo.setToolTipText("Seleccionar Plazo");
        bplazo.setBounds(new Rectangle(720, 215, 45, 23));
        bhabilitar.setText("Habilitar");
        bhabilitar.setBounds(new Rectangle(5, 510, 95, 25));
        bhabilitar.setMnemonic('h');
        bhabilitar.setEnabled(false);
        jScrollPane1.setBounds(new Rectangle(10, 170, 585, 75));
        jchekgenerar.setText("Generar Nº Documento");
        jchekgenerar.setBounds(new Rectangle(520, 20, 205, 20));
        jchekgenerar.setFont(new Font("Dialog", 1, 14));
        jchekgenerar.setForeground(Color.blue);
        jPanel6.add(jctipo, null);
        jPanel6.add(jchekgenerar, null);
        jPanel6.add(jPanel2, null);
        jPanel6.add(bmodinst, null);
        jPanel6.add(baddinst, null);
        jPanel6.add(jctipoinst, null);
        jPanel6.add(jLabel3, null);
        jPanel6.add(jcorganismo, null);
        jPanel6.add(jLabel19, null);
        jPanel6.add(jLabel2, null);
        jPanel2.add(jLabel110, null);
        jPanel2.add(jtanio, null);
        jPanel2.add(jLabel5, null);
        jPanel2.add(jLabel16, null);
        jPanel2.add(jLabel4, null);
        jPanel2.add(jtnroexp, null);
        jPanel2.add(jtletra, null);
        jPanel2.add(jtidorganismo, null);
        jPanel2.add(jLabel20, null);
        jPanel1.add(lblBibliorato, null);
        jPanel1.add(jtBibliorato, null);
        jPanel1.add(jLabel111, null);
        jPanel1.add(jtdia, null);
        jPanel1.add(bplazo, null);
        jPanel1.add(jtplazo, null);
        jPanel1.add(jLabel18, null);
        jPanel1.add(bmodasunto, null);
        jPanel1.add(bmodtipoiniciador, null);
        jPanel1.add(jLabel17, null);
        jScrollPane1.getViewport().add(jtcontenido, null);
        jPanel1.add(jScrollPane1, null);
        jPanel1.add(jtidasunto, null);
        jPanel1.add(jcestado, null);
        jPanel1.add(jLabel14, null);
        jPanel1.add(jLabel13, null);
        jPanel1.add(jtfolios, null);
        jPanel1.add(baddasunto, null);
        jPanel1.add(jcasunto, null);
        jPanel1.add(jLabel10, null);
        jPanel1.add(jLabel12, null);
        jPanel1.add(jLabel11, null);
        jPanel1.add(jtidtipoiniciador, null);
        jPanel1.add(jLabel9, null);
        jPanel1.add(jctipoiniciador, null);
        jPanel1.add(baddtipoiniciador, null);
        jPanel1.add(jLabel8, null);
        jPanel1.add(jtiniciante, null);
        jPanel1.add(jtfecha, null);
        jPanel1.add(jLabel6, null);
        jtletra.addKeyListener(this);
        jtidtipoiniciador.addKeyListener(this);
        jtidasunto.addKeyListener(this);
        jtidprofesional.addKeyListener(this);
        baceptar.addActionListener(this);
        bcancelar.addActionListener(this);
        jchekgenerar.addActionListener(this);
        centralPanel.setBounds(new Rectangle(0, 0, 780, 540));
        centralPanel.setLayout(null);
        centralPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jtBibliorato.setBounds(new Rectangle(675, 30, 90, 20));
        lblBibliorato.setText("Bibliorato");
        lblBibliorato.setBounds(new Rectangle(675, 15, 90, 15));
        baddtipoiniciador.addActionListener(this);
        bmodtipoiniciador.addActionListener(this);
        baddasunto.addActionListener(this);
        bmodasunto.addActionListener(this);
        bprofesionales.addActionListener(this);
        baddinst.addActionListener(this);
        bmodinst.addActionListener(this);
        bplazo.addActionListener(this);
        //bdelinst.setText("-");
        //bdelinst.setBounds(new Rectangle(430, 20, 45, 20));
        bhabilitar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bhabilitar_actionPerformed(e);
                }
            }
        );
        //bdelinst.addActionListener(this);
        /**ESTA RUTINA TE DEVUELVE EL ID CORRESPONDIENTE A LA DESCRIPCION DE LA TABLA tipoinstlegal (TIPOS DE INSTRUMENTOS LEGALES)
     LOS ITEM'S DEL COMBO INDICAN DICHA DESCRIPCION*/
        jcorganismo.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        jtidorganismo.setText(OP_Proced.getCampo("Select idorganismo from files.tiposorganismo where (idorganismo||'-'||descripcion)='" + jcorganismo.getSelectedItem() + "'"));
                        sidorganismo = jtidorganismo.getText();
                        OP_Proced.CargaCombo(jctipo, "Select letra||'-'||descripcion from files.tiposletra where estado != '*' AND idorganismo=0" + sidorganismo, "GOP-GENERICO DE SECRETARIA DE OBRAS Y SERVICIOS PUBLICOS");
			String _defaultOffice = Environment.cfg.getProperty("defaultOffice");
			if (_defaultOffice != "defaultOffice") jctipo.setSelectedItem(_defaultOffice);
                    }
                }
            }
        );
        jctipoinst.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        idtipoinst = OP_Proced.getCampo("Select idtipo from files.tiposinstlegal where descripcion='" + jctipoinst.getSelectedItem() + "'");
                    }
                }
            }
        );
        jctipo.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        try {
                            String texto = OP_Proced.TransformaNull_Texto(jctipo.getSelectedItem().toString());
                            idtipodoc = OP_Proced.getCampo("Select idtipo from files.tiposletra where idorganismo=" + jtidorganismo.getText() + " and (letra||'-'||descripcion)='" + texto + "'");
                            jtletra.setText(OP_Proced.getCampo("Select letra from files.tiposletra where idtipo=" + idtipodoc + " and idorganismo=" + jtidorganismo.getText()));
                            if (jchekgenerar.isSelected()) {
                                jtnroexp.setText("");
                                jtanio.setText("");
                                jchekgenerar.setSelected(false);
                            }
                        } catch (Exception x) {
                            OP_Proced.Mensaje(x.getMessage(), "ERROR: ");
                        }
                    }
                }
            }
        );
        jctipoiniciador.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        jtidtipoiniciador.setText(OP_Proced.getCampo("Select idtipo from files.tiposiniciador where ('('||abrev||') - '||descripcion)='" + jctipoiniciador.getSelectedItem() + "'"));
                        sidtipoiniciador = jtidtipoiniciador.getText();
                    }
                }
            }
        );
        jcprofesional.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        jtidprofesional.setText(OP_Proced.getCampo("Select idprofesional from sopsalta.profesionales,sopsalta.tiposprofesiones " + " where profesionales.idtipoprof = tiposprofesiones.idtipo and (idprofesional||' - '||apellido||', '||nombre||' - ('||descripcion||')')='" + jcprofesional.getSelectedItem() + "'"));
                        sidprofesional = jtidprofesional.getText();
                    }
                }
            }
        );
        jcasunto.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        jtidasunto.setText(OP_Proced.getCampo("Select idtipo from files.tiposasunto where ('('||abrev||') - '||descripcion)='" + jcasunto.getSelectedItem() + "'"));
                        sidasunto = jtidasunto.getText();
                    }
                }
            }
        );
        /** ESTO SIGNIFICA QUE SE VA A MODIFICAR EL DOCUMENTO*/
        if (Query.length() > 0) {
            ResultSet Reg = OP_Proced.exConsulta(Query);
            if (Reg.next()) {
                idinst = Reg.getString(1);
                OP_Proced.CargaCombo(jctipoinst, "Select descripcion from files.tiposinstlegal where estado<>'*'", OP_Proced.getCampo("Select descripcion from files.tiposinstlegal where idtipo=" + Reg.getString(2)));
                jtidorganismo.setText(Reg.getString(3));
                sidorganismo = Reg.getString(3);
                OP_Proced.CargaCombo(jcorganismo, "Select idorganismo||'-'||descripcion from files.tiposorganismo", OP_Proced.getCampo("Select idorganismo||'-'||descripcion from files.tiposorganismo where idorganismo=" + Reg.getString(3)));
                OP_Proced.CargaCombo(jctipo, "Select letra||'-'||descripcion from files.tiposletra where idorganismo=" + jtidorganismo.getText(), OP_Proced.getCampo("Select letra||'-'||descripcion from files.tiposletra where idorganismo=" + jtidorganismo.getText() + " and idtipo=" + Reg.getString(4)));
		String _defaultOffice = Environment.cfg.getProperty("defaultOffice");
		if (_defaultOffice != "defaultOffice") jctipo.setSelectedItem(_defaultOffice);
                jtletra.setText(OP_Proced.getCampo("Select letra from files.tiposletra where idtipo=" + Reg.getString(4) + " and idorganismo=" + Reg.getString(3)));
                jtnroexp.setText(Reg.getString(5));
                jtanio.setText(Reg.getString(6));
                jtfecha.setText(OP_Proced.Fecha2(Reg.getString(7), true));
                jtidtipoiniciador.setText(Reg.getString(8));
                sidtipoiniciador = Reg.getString(8);
                OP_Proced.CargaCombo(jctipoiniciador, "Select '('||abrev||') - '||descripcion from files.tiposiniciador where idtipo=" + Reg.getString(8), "");
                jtidasunto.setText(Reg.getString(9));
                sidasunto = Reg.getString(9);
                OP_Proced.CargaCombo(jcasunto, "Select '('||abrev||') - '||descripcion from files.tiposasunto where idtipo=" + Reg.getString(9), "");
                jtiniciante.setText(Reg.getString(10));
                jtcontenido.setText(Reg.getString(11));
                jtfolios.setText(Reg.getString(13));
                OP_Proced.CargaCombo(jcestado, "Select descripcion from files.tablacombo where estado<>'*' and combo='jcestado'", OP_Proced.getCampo("Select descripcion from files.tablacombo where descripcion='" + Reg.getString(14) + "'"));
                idcatastro = Reg.getString(15);
                jtcatastro.setText(OP_Proced.getCampo("Select catastro from cepax.catastros where idcatastro=" + Reg.getString(15)));
                jtidprofesional.setText(Reg.getString(16));
                sidprofesional = Reg.getString(16);
                OP_Proced.CargaCombo(jcprofesional, "Select idprofesional||' - '||apellido||', '||nombre||' - ('||descripcion||')' " + " from sopsalta.profesionales,sopsalta.tiposprofesiones " + " where profesionales.idtipoprof=tiposprofesiones.idtipo and idprofesional=" + Reg.getString(16), "");
                jtBibliorato.setText(Reg.getString(20));
                ActivaControles(false);
            }
        } else /** ESTO SIGNIFICA QUE SE VA A AGREGAR UN DOCUMENTO NUEVO */
        {
            OP_Proced.CargaCombo(jctipoinst, "Select descripcion from files.tiposinstlegal where estado<>'*'", OP_Proced.getCampo("Select descripcion from files.tiposinstlegal Where idtipo=" + idtipoinst));
            jtfecha.setText(OP_Proced.FechaHora2(true, false));
            OP_Proced.CargaCombo(jcestado, "Select descripcion from files.tablacombo where estado<>'*' and combo='jcestado'", "");
            OP_Proced.CargaCombo(jcorganismo, "Select idorganismo||'-'||descripcion from files.tiposorganismo", OP_Proced.getCampo("Select idorganismo||'-'||descripcion from files.tiposorganismo where idorganismo=213")); // era el codigo: 82
        }
        //************
        baddinst.setVisible(false);
        bmodinst.setVisible(false);
        jtidprofesional.setVisible(false);
        jcprofesional.setVisible(false);
        bprofesionales.setVisible(false);
        lblBuscarProfesional.setVisible(false);
        lblProfesional.setVisible(false);
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("Agregar Expediente");
    }

    /** CUANDO SE MODIFICA UN DOCUMENTO ESTOS CAMPOS NO SE DEBEN TOCAR, SALVO Q SEA AUTORIZADO POR EL ADMINISTRADOR, PARA ESTO SE DEBERA SEGUIR
   * LA RUTINA DEL BOTON HABILITAR Y LUEGO DE SER AUTORIZADO POR EL LOGIN DEBERA LLAMAR A ESTE METODO CON EL PARAMETRO 'op = TRUE' */
    private void ActivaControles(boolean op) {
        jtidorganismo.setEditable(op);
        jcorganismo.setEnabled(op);
        jtletra.setEditable(op);
        jctipo.setEnabled(op);
        jtnroexp.setEditable(op);
        jtanio.setEditable(op);
        jchekgenerar.setEnabled(op);
        jctipoinst.setEnabled(op);
        baddinst.setEnabled(op);
        bmodinst.setEnabled(op); 
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == bcancelar) {
                cancelar = true;
                //this.dispose();
                 getParentInternalFrame().close();
            } else if (e.getSource() == baceptar) {
                if (jtcatastro.getText().equals("0") || jtcatastro.getTexto().equals("")) {
                    idcatastro = "0";
                } else {
		    idcatastro = OP_Proced.getCampo("Select idcatastro from cepax.catastros where catastro=" + jtcatastro.getText());
		    
                }
                if (idcatastro.length() > 0) {
                    if ( new Integer(jtfolios.getText()) > 0 ) { //&& (!jtfolios.getText().equals(""))) {
			System.out.println("query: "+ Query.length());
                        if (Query.length() > 0) {	/**SE GENERA LA CONSULTA P/MODIFICACION DE UN DOCUMENTO - UPDATE */
                            String Q =
                                /*+"', observaciones='"+ jtobservaciones.getText()*/
                                "Update files.instlegal set idtipoinst=" + idtipoinst + ", idorganismo=" + sidorganismo + ", idtipodoc=" + idtipodoc 
                                            + ", anio='" + jtanio.getText() + "', nroexp='" + jtnroexp.getText() + "', fechaexp='" + OP_Proced.Fecha2(jtfecha.getText(), false) 
                                            + "', idtipoiniciador=" + sidtipoiniciador + ", idtipoasunto = " + sidasunto + ", iniciante='" + jtiniciante.getText() 
                                            + "', extracto='" + jtcontenido.getText() + "', cantfolios = " + jtfolios.getText() + ",estadoexp='" + jcestado.getSelectedItem() 
                                            + "',idcatastro=" + idcatastro + ",idprofesional=" + sidprofesional + ", idtipoplazo = " + jtplazo.getText() 
                                            +",bibliorato = '"+ jtBibliorato.getText().trim() +"'"
                                + " Where idinst=" + idinst;
                            if (OP_Proced.exActualizar('m', Q)) {
                                cancelar = false;
                                //this.dispose();
                                parentMain.refresh();
                                getParentInternalFrame().close();
                            }
                        } else {		 /** SE GENERA LA CONSULTA P/AGREGAR UN DOCUMENTO, INSERT */
                            if (jtnroexp.getText().length() > 0) {
				if (jtanio.getTexto().length() > 0) {
				    idinst = OP_Proced.Max("files.instlegal", "idinst", "");
				    String Q = "Insert into files.instlegal values(" + idinst + "," + idtipoinst + "," + sidorganismo + "," + idtipodoc + "," + jtnroexp.getText() + ",'" + jtanio.getText() + "','" + OP_Proced.Fecha2(jtfecha.getText(), false) + "'," + sidtipoiniciador + "," + sidasunto + ",'" + jtiniciante.getText() + "','" + jtcontenido.getText() + "',''," + jtfolios.getText() + ",'" + jcestado.getSelectedItem() + "'," + idcatastro + "," + sidprofesional + "," + jtplazo.getText() + ",0,'','"+ jtBibliorato.getText().trim() +"')";
				    if (OP_Proced.exActualizar('a', Q)) {
				        Environment.cfg.setProperty("defaultOffice", jctipo.getSelectedItem());
					cancelar = false;
					nroexp = jtnroexp.getText();
					anio = jtanio.getText();
					tipoinst = idtipoinst;
					//codigo para registrar en la tabla estad_expte
					//String Q = "INSERT INTO files.estad_expte VALUES("+ idinst +", '"+ Proced.FechaHora(true,false) +"', horaini, '"+ Proced.FechaHora(false,true) +"',) ";
					//this.dispose();
					parentMain.refresh();
					getParentInternalFrame().close();
				    } else {
				        OP_Proced.Mensaje("Ocurrió un error al grabar los datos", "Error");
				    }
                                } else {
				    OP_Proced.Mensaje("Debe ingresar un Año", "Datos Incompletos");
				}
                            } else {
                                OP_Proced.Mensaje("Debe ingresar un Nº de " + jctipoinst.getSelectedItem(), "Datos Incompletos");
                            }
                        }
                    } else {
                        OP_Proced.Mensaje("La cantidad de Folio debe ser mayor que 0 (Cero)", "Dato no valido");
                    }
                } else {
                    OP_Proced.Errores(4);
                }
            }/** RUTINA QUE GENERA EL NUMERO DEL DOCUMENTO, VER CON MAS DETALLE E IMPLEMENTAR LA REGLA DE GENERACION. POR EL MOMENTO SOLO FUNCIONA PARA NOTAS */
            else if (e.getSource() == jchekgenerar) {
                if (jchekgenerar.isSelected()) {
                    if (idtipoinst.equals("2")) {			//--> NOTAS
                        if (jtanio.getTexto().length() > 0) {
                            jtnroexp.setText(OP_Proced.Max("files.instlegal", "nroexp", " where idtipoinst=" + idtipoinst + " and idorganismo=" + jtidorganismo.getText() + " and idtipodoc=" + idtipodoc + " and anio=" + jtanio.getTexto()));
                            //VER SI SE GENERA POR AÑO  
                        } else {
                            OP_Proced.Mensaje("Debe indicar el Año (el numero de nota se generará por año)", "Año no ingresado o invalido");
                            jchekgenerar.setSelected(false);
                        }
                    } else {
                        // jtnroexp.setText(Proced.Max("files.instlegal","nroexp"," where idorganismo="+ jtidorganismo.getText() +" and idtipodoc="+ idtipodoc));  //VER SI SE GENERA POR AÑO
                        // jtanio.setText(Proced.getCampo("select extract(year from current_date)"));
                    }
                } else {
                    jtnroexp.setText("");
                    jtanio.setText("");
                }
            } else if (e.getSource() == baddtipoiniciador) {
                Carga2Campo ini = new Carga2Campo("", "files.tiposiniciador", "idtipo", "descripcion", "abrev", "Tipo Iniciador:", "Abreviatura:", "Nuevo Iniciador");
                ini.setModal(true);
                ini.setVisible(true);
                OP_Proced.CargaCombo(jctipoiniciador, "Select '('||abrev||') - '||descripcion from files.tiposiniciador where estado<>'*'", "(" + ini.getCampo2().toUpperCase() + ") - " + ini.getCampo1().toUpperCase());
            } else if (e.getSource() == bmodtipoiniciador) {
                Carga2Campo ini = new Carga2Campo("Select idtipo,descripcion,abrev,estado from files.tiposiniciador where '('||abrev||') - '||descripcion='" + jctipoiniciador.getSelectedItem() + "'", "files.tiposiniciador", "idtipo", "descripcion", "abrev", "Tipo Iniciador:", "Abreviatura:", "Modificar Iniciador");
                ini.setModal(true);
                ini.setVisible(true);
                OP_Proced.CargaCombo(jctipoiniciador, "Select '('||abrev||') - '||descripcion from files.tiposiniciador where estado<>'*'", "(" + ini.getCampo2().toUpperCase() + ") - " + ini.getCampo1().toUpperCase());
            } else if (e.getSource() == baddasunto) {
                Carga2Campo asunto = new Carga2Campo("", "files.tiposasunto", "idtipo", "descripcion", "abrev", "Tipo Asunto:", "Abreviatura:", "Nuevo Asunto");
                asunto.setModal(true);
                asunto.setVisible(true);
                OP_Proced.CargaCombo(jcasunto, "Select '('||abrev||') - '||descripcion from files.tiposasunto where estado<>'*'", "(" + asunto.getCampo2().toUpperCase() + ") - " + asunto.getCampo1().toUpperCase());
            } else if (e.getSource() == bmodasunto) {
                Carga2Campo asunto = new Carga2Campo("Select idtipo,descripcion,abrev,estado from files.tiposasunto where '('||abrev||') - '||descripcion='" + jcasunto.getSelectedItem() + "'", "files.tiposiniciador", "idtipo", "descripcion", "abrev", "Tipo Asunto:", "Abreviatura:", "Modificar Asunto");
                asunto.setModal(true);
                asunto.setVisible(true);
                OP_Proced.CargaCombo(jcasunto, "Select '('||abrev||') - '||descripcion from files.tiposasunto where estado<>'*'", "(" + asunto.getCampo2().toUpperCase() + ") - " + asunto.getCampo1().toUpperCase());
            } else if (e.getSource() == bprofesionales) {
                //???Profesionales prof = new Profesionales();
                //???Proced.CentraVentana(prof);
                //???prof.setModal(true);
                //???prof.setVisible(true);
                //???Proced.CargaCombo(jcprofesional,"Select idprofesional||' - '||apellido||', '||nombre||' - ('||descripcion||')' "
                //???+" from sopsalta.profesionales,sopsalta.tiposprofesiones "
                //???+" where profesionales.idtipoprof=tiposprofesiones.idtipo and profesionales.estado<>'*' "
                //???+" and idprofesional="+ prof.getIDProfesional(),"");
                //???jtidprofesional.setText(prof.getIDProfesional());
            } else if (e.getSource() == baddinst) {
                tipo = new CargaCampo("", "files.tiposinst", "idtipo", "descripcion", "Nombre o Descripcion", "Nuevo Tipo de Documento");
                /*tipo.setParentMain(this);
                ExtendedInternalFrame tipoContainer = new ExtendedInternalFrame("Nuevo Expediente");
                tipoContainer.setCentralPanel(tipo);
                tipoContainer.show();*/
                OP_Proced.CentraVentana(tipo);
                tipo.setModal(true);
                tipo.setVisible(true);
                OP_Proced.CargaCombo(jctipoinst, "Select descripcion from files.tiposinstlegal where estado<>'*'", tipo.getCampo());
            } else if (e.getSource() == bmodinst) {
                CargaCampo tipo = new CargaCampo("Select * from files.tiposinstlegal where descripcion='" + jctipoinst.getSelectedItem() + "'", "files.tiposinst", "idtipo", "descripcion", "Nombre o Descripcion", "Modificar Tipo de Documento");
                OP_Proced.CentraVentana(tipo);
                tipo.setModal(true);
                tipo.setVisible(true);
                OP_Proced.CargaCombo(jctipoinst, "Select descripcion from files.tiposinstlegal where estado<>'*'", tipo.getCampo());
            } else if (e.getSource() == bplazo) {
                plazo = new Plazos();
                plazo.setParentMain(this);
                ExtendedInternalFrame plazoContainer = new ExtendedInternalFrame("Nuevo Expediente");
                plazoContainer.setCentralPanel(plazo);
                plazoContainer.show();
                
                /*OP_Proced.CentraVentana(plazo);
                plazo.setModal(true);
                plazo.setVisible(true);
                jtplazo.setText(plazo.idtipo);
                jtdia.setText(plazo.dia);*/
            }
        } catch (Exception x) {
	    x.printStackTrace();
	    System.out.println("Error");
        } 
    }

    public void keyReleased(KeyEvent k) {
    }

    public void keyTyped(KeyEvent k) {
    }

    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_ENTER) {
            if (k.getSource() == jtletra) {
                int id = 99999999;
                try {
                    id = Integer.parseInt(jtletra.getText().toString());
                } catch (NumberFormatException n) {
                    //            System.out.println(n.getMessage());           
                }
                OP_Proced.CargaCombo(jctipo, "Select descripcion from files.tiposorganismos where estado<>'*'" + " and idorganismo=" + id + " or upper(descripcion) LIKE upper('%" + jtletra.getText() + "%') order by descripcion", "");
		String _defaultOffice = Environment.cfg.getProperty("defaultOffice");
		if (_defaultOffice != "defaultOffice") jctipo.setSelectedItem(_defaultOffice);
            } else if (k.getSource() == jtidtipoiniciador) {
                int id = 99999999;
                try {
                    id = Integer.parseInt(jtidtipoiniciador.getText().toString());
                } catch (NumberFormatException n) {
                    //            System.out.println(n.getMessage());           
                }
                OP_Proced.CargaCombo(jctipoiniciador, "Select '('||abrev||') - '||descripcion from files.tiposiniciador " + " where estado<>'*' and (idtipo=" + id + " or upper(abrev) LIKE upper('%" + jtidtipoiniciador.getText() + "%') or" + " upper(descripcion) LIKE upper('%" + jtidtipoiniciador.getText() + "%')) order by abrev,descripcion", "");
            } else if (k.getSource() == jtidasunto) {
                int id = 99999999;
                try {
		    id = Integer.parseInt(jtidasunto.getText().toString());
                } catch (NumberFormatException n) {
                    //            System.out.println(n.getMessage());           
                }
                OP_Proced.CargaCombo(jcasunto, "Select '('||abrev||') - '||descripcion from files.tiposasunto " + " where estado<>'*' and (idtipo=" + id + " or upper(abrev) LIKE upper('%" + jtidasunto.getText() + "%') or" + " upper(descripcion) LIKE upper('%" + jtidasunto.getText() + "%')) order by abrev,descripcion", "");
		if (id == 0)  {
		    jcasunto.setSelectedItem("(S/N) - Sin Asignar");
		}
		
            } else if (k.getSource() == jtidprofesional) {
                int id = 99999999;
                try {
                    id = Integer.parseInt(jtidprofesional.getText().toString());
                } catch (NumberFormatException n) {
                    //            System.out.println(n.getMessage());           
                }
                OP_Proced.CargaCombo(jcprofesional, "Select idprofesional||' - '||apellido||', '||nombre||' - ('||descripcion||')' " + " from sopsalta.profesionales,sopsalta.tiposprofesiones " + " where profesionales.idtipoprof=tiposprofesiones.idtipo and profesionales.estado<>'*' " + " and (idprofesional=" + id + " or upper(apellido) LIKE upper('%" + jtidprofesional.getText() + "%')" + " or upper(nombre) LIKE upper('%" + jtidprofesional.getText() + "%')" + " or upper(descripcion) LIKE upper('%" + jtidprofesional.getText() + "%')) order by apellido,nombre,descripcion", "");
            }
        }
    }

    /** BOTON HABILITAR: METODO PARA AUTORIZAR LA HABILITACION DE LOS CAMPOS QUE SE INDICAN EN ActivaControles() */
    private void bhabilitar_actionPerformed(ActionEvent e) {
        String SQLUser = OP_Proced.getSQLUser();
        String SQLPass = OP_Proced.getSQLPass();
        Login inicio = new Login("Sistema General", "Secretaría de Obras y Servicios Públicos", false);
        inicio.setModal(true);
        inicio.setVisible(true);
        if (inicio.getValidado()) {
        }
        OP_Proced.setSQLUser(SQLUser);
        OP_Proced.setSQLPass(SQLPass);
    }

    public void setParentMain(Expedientes _parentMain) {
        parentMain = _parentMain;
    }
    
    public void setTipoPlazo(String _tipoPlazo) {
         jtplazo.setText(_tipoPlazo);
    }
    
    public void setDia(String _dia) {
        jtdia.setText(_dia);
    }
}
