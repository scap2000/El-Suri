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
 * Direcciones.java
 *
 * */
package org.digitall.projects.gdigitall.expedientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class Direcciones extends JDialog implements ActionListener, KeyListener {
//public class Direcciones extends BasicPrimitivePanel implements ActionListener, KeyListener {

    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    
    private JTextField jtidorganismo = new JTextField();
    private JTextField jtdireccion = new JTextField();
    private JTextField jtidarea = new JTextField();
    private JTextField jtiddireccion = new JTextField();
    
    private JComboBox jcorganismo = new JComboBox();
    private JComboBox jcarea = new JComboBox();
    
    private JButton baceptar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
    private JButton bcancelar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    private JButton baddarea = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/add.gif")));
    private JButton bmodarea = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/mod.gif")));
    private JButton borg = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/buscar.gif")));
    
    private String LocalQuery = "", sidorganismo = "", sidarea = "";
    protected String idorganismo = "", idarea = "", iddireccion = "";
    private BasicPanel centralPanel = new BasicPanel();

    /**
   * FORMULARIO PARA CARGAR Y MODIFICAR LAS DIRECCIONES, SEGUN EL ORGANISMO Y AREA
   * 
   * @param IdArea
   * @param IdOrganismo
   * @param Query
   */
    public Direcciones(String Query, String IdOrganismo, String IdArea) {
        try {
            String ConsultaReg = "";
            LocalQuery = Query;
            idarea = IdArea;
            idorganismo = IdOrganismo;
            jbInit();
        } catch (SQLException e) {
            OP_Proced.Mensaje(e.getMessage().toUpperCase(), "Acceso a la Base de Datos no Autorizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(734, 226));
        this.setTitle("Agregar/Modificar una Direccion");
        centralPanel.add(jcorganismo, null);
        centralPanel.add(bcancelar, null);
        centralPanel.add(baceptar, null);
        centralPanel.add(jtdireccion, null);
        centralPanel.add(borg, null);
        centralPanel.add(bmodarea, null);
        centralPanel.add(baddarea, null);
        centralPanel.add(jLabel4, null);
        centralPanel.add(jLabel6, null);
        centralPanel.add(jcarea, null);
        centralPanel.add(jLabel2, null);
        centralPanel.add(jtiddireccion, null);
        centralPanel.add(jLabel3, null);
        centralPanel.add(jtidarea, null);
        centralPanel.add(jLabel5, null);
        centralPanel.add(jtidorganismo, null);
        centralPanel.add(jLabel1, null);
        this.add(centralPanel, BorderLayout.CENTER);
        baceptar.setText("Aceptar");
        baceptar.setBounds(new Rectangle(495, 165, 100, 25));
        baceptar.setMnemonic('a');
        bcancelar.setText("Cancelar");
        bcancelar.setBounds(new Rectangle(615, 165, 105, 25));
        bcancelar.setMnemonic('c');
        borg.setBounds(new Rectangle(675, 20, 45, 25));
        borg.addActionListener(new Direcciones_borg_actionAdapter(this));
        centralPanel.setBounds(new Rectangle(5, 5, 730, 200));
        centralPanel.setLayout(null);
        baddarea.setBounds(new Rectangle(625, 70, 45, 25));
        bmodarea.setBounds(new Rectangle(675, 70, 45, 25));
        baceptar.addActionListener(this);
        bcancelar.addActionListener(this);
        baddarea.addActionListener(this);
        bmodarea.addActionListener(this);
        jLabel1.setBounds(new Rectangle(5, 5, 125, 15));
        jLabel1.setText("Buscar Organismo:");
        jLabel2.setText("Direccion:");
        jLabel2.setBounds(new Rectangle(165, 120, 65, 15));
        jLabel3.setText("NºOrden:");
        jLabel3.setBounds(new Rectangle(5, 120, 60, 15));
        jLabel4.setText("Organismo:");
        jLabel4.setBounds(new Rectangle(145, 5, 90, 15));
        jLabel5.setText("Buscar Area:");
        jLabel5.setBounds(new Rectangle(5, 55, 105, 15));
        jLabel6.setText("Area:");
        jLabel6.setBounds(new Rectangle(145, 55, 70, 15));
        jcarea.setBounds(new Rectangle(145, 70, 475, 20));
        jcorganismo.setBounds(new Rectangle(145, 20, 525, 20));
        jtidorganismo.setBounds(new Rectangle(5, 20, 120, 20));
        jtdireccion.setBounds(new Rectangle(235, 115, 485, 20));
        jtiddireccion.setBounds(new Rectangle(70, 115, 75, 20));
        jtiddireccion.setBackground(Color.white);
        jtiddireccion.setEditable(false);
        jtidorganismo.addKeyListener(this);
        jtidarea.addKeyListener(this);
        jtidarea.setBounds(new Rectangle(5, 70, 120, 20));
        jtidarea.setBackground(Color.white);
        jcorganismo.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        jtidorganismo.setText(OP_Proced.getCampo("Select idorganismo from files.tiposorganismo where descripcion='" + jcorganismo.getSelectedItem() + "'"));
                        sidorganismo = jtidorganismo.getText();
                    }
                }
            }
        );
        jcarea.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        jtidarea.setText(OP_Proced.getCampo("Select idarea from files.tiposarea where idorganismo=" + jtidorganismo.getText() + " and descripcion='" + jcarea.getSelectedItem() + "'"));
                        sidarea = jtidarea.getText();
                    }
                }
            }
        );
        if (LocalQuery.length() > 0) {
            ResultSet Reg = OP_Proced.exConsulta(LocalQuery);
            if (Reg.next()) {
                idorganismo = Reg.getString(1);
                jtidorganismo.setText(idorganismo);
                OP_Proced.CargaCombo(jcorganismo, "Select descripcion from files.tiposorganismo where idorganismo=" + Reg.getString(1), "");
                idarea = Reg.getString(2);
                jtidarea.setText(idarea);
                OP_Proced.CargaCombo(jcarea, "Select descripcion from files.tiposarea where estado<>'*'", OP_Proced.getCampo("Select descripcion From files.tiposarea Where idarea=" + idarea + " And idorganismo=" + idorganismo));
                iddireccion = Reg.getString(3);
                jtiddireccion.setText(iddireccion);
                jtdireccion.setText(Reg.getString(4));
            }
        } else {
            OP_Proced.CargaCombo(jcorganismo, "Select descripcion from files.tiposorganismo where idorganismo=" + idorganismo, "");
            jtidorganismo.setText(idorganismo);
            OP_Proced.CargaCombo(jcarea, "Select descripcion from files.tiposarea where idorganismo=" + idorganismo + " And idarea=" + idarea, "");
            jtidarea.setText(idarea);
        }
        
        
        baddarea.setVisible(false);
        bmodarea.setVisible(false);
        borg.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bcancelar) {
            idorganismo = "";
            this.dispose();
        } else if (e.getSource() == baceptar) {
            if (jtdireccion.getText().trim().length() > 0) {
                if (LocalQuery.length() > 0) {
                    String Q = "Update files.tiposdireccion set idorganismo =" + sidorganismo + ", idarea=" + sidarea + ",descripcion='" + jtdireccion.getText().toUpperCase() + "' Where iddireccion=" + iddireccion + " and idorganismo=" + idorganismo + " and idarea=" + idarea;
                    iddireccion = jtiddireccion.getText();
                    if (OP_Proced.exActualizar('m', Q))
                        this.dispose();
                         System.out.println("Aca hay que arreglar");
                } else {
                    iddireccion = OP_Proced.Max("files.tiposdireccion", "iddireccion", " Where idarea=" + jtidarea.getText() + " AND idorganismo=" + jtidorganismo.getText());
                    String Q = "Insert into files.tiposdireccion values(" + jtidorganismo.getText() + "," + jtidarea.getText() + "," + iddireccion + ",'" + jtdireccion.getText().toUpperCase() + "','')";
                    if (OP_Proced.exActualizar('a', Q))
                        this.dispose();
                        System.out.println("Aca hay que arreglar");
                }
                idorganismo = sidorganismo;
                idarea = sidarea;
            }
        } else if (e.getSource() == baddarea) {
            ExptesAreas area = new ExptesAreas("", sidorganismo);
            /*OP_Proced.CentraVentana(area);
            area.setModal(true);*/
            area.setVisible(true);
            if (area.idorganismo.length() > 0) {
                OP_Proced.CargaCombo(jcorganismo, "Select descripcion from files.tiposorganismo where idorganismo=" + area.idorganismo, "");
                jtidorganismo.setText(area.idorganismo);
                OP_Proced.CargaCombo(jcarea, "Select descripcion from files.tiposarea where idorganismo=" + area.idorganismo + " and idarea=" + area.idarea, "");
                jtidarea.setText(area.idarea);
            }
        } else if (e.getSource() == bmodarea) {
            ExptesAreas area = new ExptesAreas("Select * from files.tiposarea where idorganismo=" + sidorganismo + " and descripcion='" + jcarea.getSelectedItem().toString() + "'", "");
            /*OP_Proced.CentraVentana(area);
            area.setModal(true);*/
            area.setVisible(true);
            if (area.idorganismo.length() > 0) {
                OP_Proced.CargaCombo(jcorganismo, "Select descripcion from files.tiposorganismo where idorganismo=" + area.idorganismo, "");
                jtidorganismo.setText(area.idorganismo);
                OP_Proced.CargaCombo(jcarea, "Select descripcion from files.tiposarea where idorganismo=" + area.idorganismo + " and idarea=" + area.idarea, "");
                jtidarea.setText(area.idarea);
            }
        }
    }

    public void keyReleased(KeyEvent k) {
    }

    public void keyTyped(KeyEvent k) {
    }

    public void keyPressed(KeyEvent k) {
        int id = 99999999;
        String texto = "", Q = "";
        if (k.getKeyCode() == KeyEvent.VK_ENTER) {
            if (k.getSource() == jtidorganismo) {
                try {
                    id = Integer.parseInt(jtidorganismo.getText());
                } catch (NumberFormatException n) {
                    //            System.out.println(n.getMessage());           
                }
                Q = "Select descripcion from files.tiposorganismo where estado<>'*' and (idorganismo=" + id + " or upper(descripcion) LIKE upper('%" + jtidorganismo.getText() + "%')) order by descripcion";
                OP_Proced.CargaCombo(jcorganismo, Q, "");
            } else if (k.getSource() == jtidarea) {
                try {
                    id = Integer.parseInt(jtidarea.getText());
                } catch (NumberFormatException n) {
                    //            System.out.println(n.getMessage());           
                }
                Q = "Select descripcion from files.tiposarea where estado<>'*' and idorganismo=" + jtidorganismo.getText() + " and (idarea=" + id + " or upper(descripcion) LIKE upper('%" + jtidarea.getText() + "%')) order by descripcion";
                System.out.println(Q);
                OP_Proced.CargaCombo(jcarea, Q, "");
            }
        }
    }

    void borg_actionPerformed(ActionEvent e) {
        Organismos_Letras org = new Organismos_Letras("files.tiposorganismo");
        /*OP_Proced.CentraVentana(org);
        org.setModal(true);*/
        org.setVisible(true);
        if (org.idorganismo.length() > 0) {
            OP_Proced.CargaCombo(jcorganismo, "Select descripcion from files.tiposorganismo where idorganismo=" + org.idorganismo, "");
            jtidorganismo.setText(org.idorganismo);
        }
    }
}
class Direcciones_borg_actionAdapter implements ActionListener {
    Direcciones adaptee;

    Direcciones_borg_actionAdapter(Direcciones adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.borg_actionPerformed(e);
    }
}
