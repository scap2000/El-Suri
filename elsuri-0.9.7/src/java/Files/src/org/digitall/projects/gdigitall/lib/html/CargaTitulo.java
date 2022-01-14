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
 * CargaTitulo.java
 *
 * */
package org.digitall.projects.gdigitall.lib.html;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.digitall.projects.gdigitall.lib.components.SelectorFecha;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;


public class CargaTitulo extends JDialog implements ActionListener {
    protected JTextField jtcampo = new JTextField();

    private JButton bcancelar = new JButton();

    private JLabel titulo1 = new JLabel();

    private JTextField jtfecha = new JTextField();

    private JLabel jlfecha = new JLabel();

    private JButton baceptar = new JButton();

    //Variable

    private String titulo, tituloventana, textocampo;

    private static String campo, resaltar;

    private boolean activafecha, activachek;

    private JCheckBox jchekreparado = new JCheckBox();

    private JPanel jPanel1 = new JPanel();

    public CargaTitulo(String Titulo, String TituloVentana, String TextoCampo,
                       boolean ActivaFecha, boolean ActivarCheck) {
        try {
            titulo = Titulo;
            textocampo = TextoCampo;
            activafecha = ActivaFecha;
            tituloventana = TituloVentana;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        OP_Proced.IniciaTeclas();
        //Proced.CentrarVentana(this);
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(455, 172));
        this.setTitle(tituloventana);
        jtcampo.setBounds(new Rectangle(15, 35, 330, 20));
        bcancelar.setText("Cancelar");
        bcancelar.setBounds(new Rectangle(345, 110, 87, 25));
        bcancelar.setMnemonic('C');
        titulo1.setText(titulo);
        titulo1.setBounds(new Rectangle(15, 15, 285, 15));
        jtfecha.setBounds(new Rectangle(360, 35, 70, 20));
        jlfecha.setText("Fecha:");
        jlfecha.setBounds(new Rectangle(360, 15, 38, 15));
        baceptar.setText("Aceptar");
        baceptar.setBounds(new Rectangle(235, 110, 90, 25));
        baceptar.setMnemonic('A');
        jchekreparado.setText("Resaltar los Baches Reparados");
        jchekreparado.setBounds(new Rectangle(99, 3, 214, 15));
        jchekreparado
        .setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setBounds(new Rectangle(15, 70, 415, 20));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        jPanel1.add(jchekreparado, null);
        this.getContentPane().add(jPanel1, null);
        this.getContentPane().add(baceptar, null);
        this.getContentPane().add(jlfecha, null);
        this.getContentPane().add(jtfecha, null);
        this.getContentPane().add(titulo1, null);
        this.getContentPane().add(bcancelar, null);
        this.getContentPane().add(jtcampo, null);
        bcancelar.addActionListener(this);
        baceptar.addActionListener(this);
        jtfecha.addMouseListener(new MouseAdapter() {
                                     public void mouseClicked(MouseEvent e) {
                                         if (e.getClickCount() == 1 &&
                                             e.getButton() == e.BUTTON1) {
                                             JTextField kk = new JTextField();
                                             SelectorFecha c =
                                                 new SelectorFecha(kk);
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
        //    jtfecha.addKeyListener(new dateListen(true));
        jtfecha.setText(OP_Proced.FechaHora2(true, false));

        jlfecha.setVisible(activafecha);
        jtfecha.setVisible(activafecha);
        jtcampo.setText(textocampo);
        jchekreparado.setVisible(activachek);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == baceptar) {
            if (activafecha) {
                campo = jtcampo.getText() + " - " + jtfecha.getText();
                resaltar = OP_Proced.CharCheckBox(jchekreparado);
            } else {
                campo = jtcampo.getText();
            }
            this.dispose();
        }
    }

    public void setCampo(String _campo) {
        campo = _campo;
    }

    public static String getCampo() {
        return campo;
    }

    public static String getResaltar() {
        return resaltar;
    }
}
