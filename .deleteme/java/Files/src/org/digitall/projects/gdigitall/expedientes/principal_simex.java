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
 * principal_simex.java
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import javax.swing.plaf.ColorUIResource;

import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;
import org.digitall.projects.gdigitall.lib.components.Login;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

//public class principal_simex extends JFrame implements ActionListener {
public class principal_simex extends BasicPrimitivePanel implements ActionListener {
    private JButton bdoc = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/22x22/expte01.gif")));
    private JButton bvencimientos = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/22x22/rurbano01.gif")));
    private JButton bprincipal = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/22x22/principal03.gif")));
    private JButton bsalir = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/22x22/salir001.gif")));
    private JButton bsesion = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/22x22/sesion01.gif")));
    private JLabel logo = new JLabel(IconTypes.desktop_bottom_left); //new ImageIcon(OP_Proced.class.getResource("iconos/escudo.gif")));
    //private JLabel fecha = new JLabel(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/calendario.gif")));
    //private JLabel hora = new JLabel();
    //private JLabel lhora = new JLabel(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/reloj.gif")));
    private JSeparator jSeparator1 = new JSeparator();
    private JLabel barraestado = new JLabel(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/tux.gif")));
    private JPopupMenu jPopupMenu1 = new JPopupMenu();
    private JMenuItem menuOrg = new JMenuItem();
    private JMenuItem menuOficina = new JMenuItem();
    private JMenuItem menuTipos = new JMenuItem();
    protected String Horas = "";
    private JPanel jPanel1 = new JPanel();
    private JPanel centralPanel = new JPanel();
    //*************************
    private Expedientes exp;
    private Vtos expvto;
    private Organismos_Letras org;
    private Oficinas ofic;
    private Organismos_Letras letra;

    public principal_simex() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        centralPanel.setLayout(null);
        bvencimientos.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bvencimientos_actionPerformed(e);
                }
            }
        );
        //this.setSize(new Dimension(645, 410));
        //this.getContentPane().setLayout(null);
        this.setTitle("SIMEx (Sistema Informatico Municipal de Expedientes) v 1.2");
        this.setSize(new Dimension(634, 377));
        bdoc.setText("Documentos (o Expedientes)");
        bdoc.setBounds(new Rectangle(30, 25, 230, 30));
        bdoc.setMnemonic('d');
        bdoc.setMargin(new Insets(2, 5, 2, 14));
        bdoc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bdoc_actionPerformed(e);
                }
            }
        );
        bvencimientos.setText("Vencimientos");
        bvencimientos.setBounds(new Rectangle(30, 75, 230, 30));
        bvencimientos.setMnemonic('e');
        bvencimientos.setMargin(new Insets(2, 5, 2, 14));
        bvencimientos.setHorizontalAlignment(SwingConstants.LEFT);
        bprincipal.setText("Opciones Principales");
        bprincipal.setBounds(new Rectangle(30, 125, 230, 30));
        bprincipal.setMnemonic('o');
        bprincipal.setMargin(new Insets(2, 5, 2, 14));
        bprincipal.setHorizontalAlignment(SwingConstants.LEFT);
        bsalir.setText("Salir");
        bsalir.setBounds(new Rectangle(30, 250, 90, 30));
        bsalir.setMnemonic('s');
        bsalir.setMargin(new Insets(2, 5, 2, 14));
        bsalir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bsalir_actionPerformed(e);
                }
            }
        );
        bsesion.setText("Cerrar Sesion");
        bsesion.setBounds(new Rectangle(125, 250, 135, 30));
        bsesion.setMnemonic('c');
        bsesion.setMargin(new Insets(2, 5, 2, 14));
        bsesion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bsesion_actionPerformed(e);
                }
            }
        );
        logo.setBounds(new Rectangle(360, 25, 215, 235));
        logo.setBackground(new Color(17,27,56));
        logo.setOpaque(true);
        /*fecha.setText("G.I.S.");
        fecha.setBounds(new Rectangle(10, 5, 300, 20));
        fecha.setFont(new Font("Dialog", 0, 12));
        fecha.setText("SALTA, Miercoles 24 de Noviembre de 2004.-    Horas:");
        //fecha.setText("SALTA, " + OP_Proced.ObtieneDiaSemana(OP_Proced.FechaHora(true, false)) + " " + OP_Proced.getCampo("SELECT EXTRACT(Day FROM current_date)") + " de " + OP_Proced.ObtieneMes(OP_Proced.FechaHora(true, false)) + " de " + OP_Proced.getCampo("SELECT EXTRACT(year FROM current_date)") + ".- ");
        hora.setText("G.I.S.");
        hora.setBounds(new Rectangle(565, 10, 60, 15));
        hora.setText("00:00");
        lhora.setText("00:00");
        lhora.setFont(new Font("Dialog", 0, 12));
        lhora.setBounds(new Rectangle(505, 10, 55, 15));
        lhora.setText("Hora:");*/
        jSeparator1.setBounds(new Rectangle(5, 30, 615, 2));
        barraestado.setHorizontalAlignment(SwingConstants.LEFT);
        barraestado.setText("Barra de Estado");
        barraestado.setBounds(new Rectangle(10, 340, 615, 25));
        barraestado.setFont(new Font("Dialog", 1, 13));
        barraestado.setText(" Bienvenido '" + Environment.sessionUser + "' - Sesión iniciada a hs.: " + OP_Proced.FechaHora(false, false) + ", Terminal '" + OP_Proced.ObtieneHost() + "'");
        jPopupMenu1.setLabel("jPopupMenu1");
        menuOrg.setText("  Organismos");
        menuOrg.setMnemonic('o');
        menuOficina.setText("  Oficinas");
        menuOficina.setMnemonic('f');
        menuTipos.setText("  Tipos Letras");
        menuTipos.setMnemonic('t');
        jPanel1.add(bsesion, null);
        jPanel1.add(logo, null);
        jPanel1.add(bsalir, null);
        jPanel1.add(bprincipal, null);
        jPanel1.add(bvencimientos, null);
        jPanel1.add(bdoc, null);
        centralPanel.add(barraestado, null);
        centralPanel.add(jPanel1, null);
        //centralPanel.add(lhora, null);
        //centralPanel.add(fecha, null);
        centralPanel.add(jSeparator1, null);
        //centralPanel.add(hora, null);
        jPopupMenu1.add(menuOrg);
        jPopupMenu1.add(menuOficina);
        jPopupMenu1.add(menuTipos);
        this.add(centralPanel, BorderLayout.CENTER);
        menuOrg.addActionListener(this);
        menuOficina.addActionListener(this);
        menuTipos.addActionListener(this);
        Horas = OP_Proced.FechaHora(false, true);
        //---------HORA---------
        //int delay = 0;
        // delay for 5 sec.
        //int period = 1000;
        // repeat every sec.
        //Timer timer = new Timer();
        barraestado.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        //jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setBounds(new Rectangle(10, 40, 615, 290));
        centralPanel.setBounds(new Rectangle(3, 0, 635, 375));
        
        /*timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        Horas = OP_Proced.Reloj(Horas);
                        hora.setText(Horas);
                        //System.out.println("Reloj: "+ Horas);
                    } catch (Exception x) {
                        System.out.println(x.getMessage());
                    }
                }
            }
            , delay, period);*/
        //-------------
        bprincipal.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        );
        //menuOrg.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/organismos.gif")));
        //menuOficina.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/oficinas.gif")));
        //menuTipos.setIcon(new ImageIcon(OP_Proced.class.getResource("iconos/16x16/letras.gif")));
        bsalir.setEnabled(false);
        bsalir.setVisible(false);
        bsesion.setEnabled(false);
        bsesion.setVisible(false);
        
    }

    private void bdoc_actionPerformed(ActionEvent e) {
        exp = new Expedientes();
        exp.setParentMain(this);
        ExtendedInternalFrame expContainer = new ExtendedInternalFrame("Expedientes");
        expContainer.setCentralPanel(exp);
        expContainer.show();
        /*OP_Proced.CentraVentana(exp);
        exp.setModal(true);*/
        //exp.show();
    }

    private void bvencimientos_actionPerformed(ActionEvent e) { 
        expvto = new Vtos();
        expvto.setParentMain(this);
        ExtendedInternalFrame expVtoContainer = new ExtendedInternalFrame("Vencimientos");
        expVtoContainer.setCentralPanel(expvto);
        expVtoContainer.show();
        /*OP_Proced.CentraVentana(expvto);
        expvto.setModal(true);
        expvto.show();*/
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuOrg) {
            //Organismos_Letras org = new Organismos_Letras("sim.tiposorganismo");
            org = new Organismos_Letras("files.tiposorganismo");
            org.setParentMain(this);
            ExtendedInternalFrame orgContainer = new ExtendedInternalFrame("Organismos");
            orgContainer.setCentralPanel(org);
            orgContainer.show();
            /*OP_Proced.CentraVentana(org);
            org.setModal(true);*/
            //org.setVisible(true);
        } else if (e.getSource() == menuOficina) {
            ofic = new Oficinas();
            ofic.setParentMain(this);
            ExtendedInternalFrame oficContainer = new ExtendedInternalFrame("Oficinas");
            oficContainer.setCentralPanel(ofic);
            oficContainer.show();
            /*OP_Proced.CentraVentana(ofic);
            ofic.setModal(true);
            ofic.setVisible(true);*/
        } else if (e.getSource() == menuTipos) {
            letra = new Organismos_Letras("files.tiposletra");
            letra.setParentMain(this);
            ExtendedInternalFrame letraContainer = new ExtendedInternalFrame("Tipos Letras");
            letraContainer.setCentralPanel(letra);
            letraContainer.show();
            /*OP_Proced.CentraVentana(letra);
            letra.setModal(true);
            letra.setVisible(true);*/
        }
    }

    private void bsalir_actionPerformed(ActionEvent e) {
        //System.exit(0);
    }

    private void bsesion_actionPerformed(ActionEvent e) {
        /*try {
            Login inicio = new Login("Sistema General", "Secretaría de Obras y Servicios Públicos", true);
            OP_Proced.CentraVentana(inicio);
            inicio.setResizable(false);
            inicio.setModal(true);
            inicio.show();
            barraestado.setText(" Bienvenido '" + OP_Proced.getSQLUser() + "' - Sesión iniciada a hs.: " + OP_Proced.FechaHora(false, false) + ", Terminal '" + OP_Proced.ObtieneHost() + "'");
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }*/
    }
}
