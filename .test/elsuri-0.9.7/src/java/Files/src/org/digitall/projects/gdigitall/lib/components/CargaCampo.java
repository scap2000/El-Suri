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
 * CargaCampo.java
 *
 * */
package org.digitall.projects.gdigitall.lib.components;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class CargaCampo extends JDialog implements ActionListener {
    private JTextField jtcampo = new JTextField();
    private JButton baceptar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
    private JButton bcancelar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cancelar.gif")));
    private JLabel titulo1 = new JLabel();
    private JLabel titulo2 = new JLabel();
    private JTextField jtcodigo = new JTextField();
    //Variable
    private String LocalQuery, tabla, id, nombre, titulo, tituloventana, esquema;
    private String campo = "", codigo = "";
    //*******************************************
    //private frmExpedientes parentMain;

    public CargaCampo(String Query, String Tabla, String NomCampoID, String CampoDescripcion, String Titulo, String TituloVentana) {
        try {
            id = NomCampoID;
            nombre = CampoDescripcion;
            tabla = Tabla;
            LocalQuery = Query;
            titulo = Titulo;
            tituloventana = TituloVentana;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        baceptar.setMargin(new Insets(2, 5, 2, 14));
        //   Proced.CentrarVentana(this);
        bcancelar.setMargin(new Insets(2, 5, 2, 14));
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(411, 140));
        this.setTitle(tituloventana);
        jtcampo.setBounds(new Rectangle(105, 35, 285, 20));
        baceptar.setText("Aceptar");
        baceptar.setBounds(new Rectangle(185, 75, 92, 25));
        baceptar.setMnemonic('A');
        bcancelar.setText("Cancelar");
        bcancelar.setBounds(new Rectangle(290, 75, 98, 25));
        bcancelar.setMnemonic('C');
        titulo1.setText(titulo);
        titulo1.setBounds(new Rectangle(105, 15, 285, 15));
        titulo2.setText("Codigo:");
        titulo2.setBounds(new Rectangle(15, 15, 48, 15));
        jtcodigo.setBounds(new Rectangle(15, 35, 80, 20));
        jtcodigo.setEditable(false);
        this.getContentPane().add(jtcodigo, null);
        this.getContentPane().add(titulo2, null);
        this.getContentPane().add(titulo1, null);
        this.getContentPane().add(bcancelar, null);
        this.getContentPane().add(baceptar, null);
        this.getContentPane().add(jtcampo, null);
        baceptar.addActionListener(this);
        bcancelar.addActionListener(this);
        if (LocalQuery.length() > 0) {
            //System.out.println(LocalQuery); 
            ResultSet Resul1 = OP_Proced.exConsulta(LocalQuery);
            if (Resul1.next()) {
                jtcodigo.setText(Resul1.getString(1).toString());
                jtcampo.setText(Resul1.getString(2).toString());
            }
        } else {
            //jtcodigo.setText(Proced.Max(tabla,id,""));
        }
    }

    public void actionPerformed(ActionEvent e) {
        /*try 
   {*/
        //Connection Con1=Proced.CCon();
        if (e.getSource() == baceptar) {
            if (jtcampo.getText().trim().length() > 0) {
                campo = jtcampo.getText();
                codigo = jtcodigo.getText();
                if (LocalQuery.length() == 0) {
                    codigo = OP_Proced.Max(tabla, id, "");
                    String Q = "Insert into " + tabla + " values(" + codigo + ",upper('" + jtcampo.getText() + "'),'')";
                    //System.out.println(Q);
                    if (OP_Proced.exActualizar('a', Q))
                        this.dispose();
                } else {
                    String Q = "Update " + tabla + " set " + nombre + "=upper('" + jtcampo.getText() + "') where " + id + "=" + jtcodigo.getText();
                    // System.out.println(Q);
                    if (OP_Proced.exActualizar('m', Q))
                        this.dispose();
                }
            }
        } else if (e.getSource() == bcancelar) {
            campo = "";
            this.dispose();
        }
        /*} catch (SQLException x)
   {
     System.out.println(x.getMessage());
   }*/
    }

    public String getCampo() {
        return campo;
    }

    public String getCodigo() {
        return codigo;
    }
}
