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
 * frmLetras.java
 *
 * */
package org.digitall.projects.gdigitall.expedientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.projects.gdigitall.lib.classes.ValidaDato;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class frmLetras extends JDialog {
//public class frmLetras extends BasicPrimitivePanel {
    private JButton baceptar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
    private JButton bcancelar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cancelar.gif")));
    private JComboBox jcorg = new JComboBox();
    private JTextField jtidtipo = new JTextField();
    private JTextField jtletra = new JTextField();
    private JTextField jtdescripcion = new JTextField();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private String org = "", Query = "", sidorganismo = "0";
    protected String idorganismo = "";
    private JPanel centralPanel = new JPanel();

    /**
   * FORMULARIO PARA CARGAR LAS LETRAS (INICIALES) REPRESENTATIVA DE CADA DEPENDENCIA, pej. GEH:GENERICO DE ECONOMIA Y HACIENDA - 
   * GOP:GENERICO DE SECRETARIA DE OBRAS Y SERVICIOS PUBLICOS
   * 
   * @param SQLQuery: INDICA MODIFICACION
   * @param Org: EL ORGANISMO AL CUAL PERTENECERA LA DEPENDENCIA REPRESENTADA POR LA LETRA INDICADA
   */
    public frmLetras(String Org, String SQLQuery) {
        try {
            org = Org;
            Query = SQLQuery;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(612, 180));
        //this.getContentPane().setLayout(null);
        this.setTitle("Agregar/Modificar Tipo Letra");
        baceptar.setText("Aceptar");
        baceptar.setBounds(new Rectangle(395, 120, 95, 25));
        baceptar.setMnemonic('a');
        baceptar.setMargin(new Insets(2, 5, 2, 14));
        baceptar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    baceptar_actionPerformed(e);
                }
            }
        );
        bcancelar.setText("Cancelar");
        bcancelar.setBounds(new Rectangle(505, 120, 95, 25));
        bcancelar.setMnemonic('c');
        bcancelar.setMargin(new Insets(2, 5, 2, 14));
        bcancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    bcancelar_actionPerformed(e);
                }
            }
        );
        jcorg.setBounds(new Rectangle(5, 20, 595, 20));
        jtidtipo.setBounds(new Rectangle(5, 75, 55, 20));
        jtidtipo.setEditable(false);
        jtidtipo.setBackground(Color.white);
        jtletra.setBounds(new Rectangle(100, 75, 55, 20));
        jtdescripcion.setBounds(new Rectangle(195, 75, 405, 20));
        jLabel1.setText("ID Letra:");
        jLabel1.setBounds(new Rectangle(5, 60, 55, 15));
        jLabel2.setText("Letra:");
        jLabel2.setBounds(new Rectangle(100, 60, 35, 15));
        jLabel3.setText("Descripcion:");
        jLabel3.setBounds(new Rectangle(195, 60, 400, 15));
        jLabel4.setText("Organismo:");
        jLabel4.setBounds(new Rectangle(5, 5, 75, 15));
        centralPanel.setBounds(new Rectangle(0, 0, 610, 155));
        centralPanel.setLayout(null);
        centralPanel.add(jcorg, null);
        centralPanel.add(bcancelar, null);
        centralPanel.add(baceptar, null);
        centralPanel.add(jtdescripcion, null);
        centralPanel.add(jLabel3, null);
        centralPanel.add(jtletra, null);
        centralPanel.add(jLabel2, null);
        centralPanel.add(jtidtipo, null);
        centralPanel.add(jLabel1, null);
        centralPanel.add(jLabel4, null);
        //this.getContentPane().add(centralPanel, null);
        this.add(centralPanel, null);
        jtletra.setDocument(new ValidaDato(ValidaDato.MAYUSCULA));
        jtdescripcion.setDocument(new ValidaDato(ValidaDato.MAYUSCULA));
        jcorg.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        sidorganismo = OP_Proced.getCampo("SELECT idorganismo FROM files.tiposorganismo WHERE (idorganismo||'-'||descripcion)='" + jcorg.getSelectedItem() + "'");
                    }
                }
            }
        );
        OP_Proced.CargaCombo(jcorg, "SELECT idorganismo||'-'||descripcion FROM files.tiposorganismo WHERE estado <> '*'", org);
        if (Query.length() > 0) {
            ResultSet Reg = OP_Proced.exConsulta(Query);
            if (Reg.next()) {
                jtidtipo.setText(Reg.getString(1));
                idorganismo = Reg.getString(2);
                jtletra.setText(Reg.getString(3));
                jtdescripcion.setText(Reg.getString(4));
            }
        }
        jcorg.setEnabled(false);
    }

    private void baceptar_actionPerformed(ActionEvent e) {
        if (Query.length() > 0) {
            String Q = "UPDATE files.tiposletra SET letra=upper('" + jtletra.getText() + "'), descripcion=upper('" + jtdescripcion.getText() + "') WHERE idtipo = " + jtidtipo.getText() +" AND idorganismo = " + sidorganismo;
            if (OP_Proced.exActualizar('m', Q))
                this.dispose();
                //System.out.println(" ACA HAY QUE ARREGLAR !!!!!!!");
        } else {
            String Q = "INSERT into files.tiposletra VALUES(" + OP_Proced.Max("files.tiposletra", "idtipo", " WHERE idorganismo=" + sidorganismo) + "," + sidorganismo + ",upper('" + jtletra.getText() + "'), upper('" + jtdescripcion.getText() + "'),'')";
            if (OP_Proced.exActualizar('a', Q))
                this.dispose();
                // System.out.println(" ACA HAY QUE ARREGLAR !!!!!!!");
        }
    }

    private void bcancelar_actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
