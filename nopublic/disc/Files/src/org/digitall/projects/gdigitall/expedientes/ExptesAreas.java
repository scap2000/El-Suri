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
 * ExptesAreas.java
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
import org.digitall.projects.gdigitall.lib.components.CargaCampo;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

public class ExptesAreas extends JDialog implements KeyListener {
//public class ExptesAreas extends BasicPrimitivePanel implements KeyListener {
    private JComboBox jcorganismo = new JComboBox();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JTextField jtidorganismo = new JTextField();
    private JTextField jtidarea = new JTextField();
    private JTextField jtarea = new JTextField();
    private JButton baceptar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));
    private JButton bcancelar = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cancelar.gif")));
    private JButton baddorganismo = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));new ImageIcon(OP_Proced.class.getResource("iconos/16x16/add.gif")));
    private JButton bmodorganismo = new JButton(); //new ImageIcon(OP_Proced.class.getResource("iconos/16x16/aceptar.gif")));new ImageIcon(OP_Proced.class.getResource("iconos/16x16/mod.gif")));
    private String Query = "", sidorganismo = "";
    protected String idorganismo = "", idarea = "";
    private JPanel centralPanel = new JPanel();

    /**
     * NOTA: EL ARBOL DE DEPENDENCIAS UTILIZADO PARA ESTE SISTEMA ES --> ORGANISMOS -> AREA -> DIRECCION -> OFICINA
     * Formulario para Modificar minY Agregar AREAS
     * @param IdOrganismo: ME INDICA DE QUE ORGANISMOS SON LAS AREAS EN CUESTION
     * @param SQLQuery: CONSULTA PARA MODIFICAR LOS DATOS DE UNA AREA
     */
    public ExptesAreas(String SQLQuery, String IdOrganismo) {
        try {
            Query = SQLQuery;
            idorganismo = IdOrganismo;
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(734, 174));
        //this.getContentPane().setLayout(null);
        this.setTitle("Agregar/Modificar un Area");
        centralPanel.add(jcorganismo, null);
        centralPanel.add(bcancelar, null);
        centralPanel.add(baceptar, null);
        centralPanel.add(bmodorganismo, null);
        centralPanel.add(baddorganismo, null);
        centralPanel.add(jLabel1, null);
        centralPanel.add(jLabel4, null);
        centralPanel.add(jtarea, null);
        centralPanel.add(jtidarea, null);
        centralPanel.add(jLabel3, null);
        centralPanel.add(jtidorganismo, null);
        centralPanel.add(jLabel2, null);
        //this.getContentPane().add(centralPanel, null);
        this.add(centralPanel, null);
        jcorganismo.setBounds(new Rectangle(160, 20, 455, 20));
        jLabel1.setText("Organismo:");
        jLabel1.setBounds(new Rectangle(160, 5, 75, 15));
        jLabel2.setText("Buscar Organismo:");
        jLabel2.setBounds(new Rectangle(5, 5, 120, 15));
        jLabel3.setText("NºOrden:");
        jLabel3.setBounds(new Rectangle(5, 55, 60, 15));
        jLabel4.setText("Area:");
        jLabel4.setBounds(new Rectangle(160, 55, 35, 15));
        jtidorganismo.setBounds(new Rectangle(5, 20, 120, 15));
        jtidorganismo.addKeyListener(this);
        jtidarea.setBounds(new Rectangle(5, 70, 70, 15));
        jtidarea.setEditable(false);
        jtidarea.setBackground(Color.white);
        jtarea.setBounds(new Rectangle(160, 70, 555, 20));
        baceptar.setText("Aceptar");
        baceptar.setBounds(new Rectangle(460, 110, 110, 25));
        baceptar.setMnemonic('a');
        baceptar.setMargin(new Insets(2, 5, 2, 14));
        baceptar.addActionListener(new ExptesAreas_baceptar_actionAdapter(this));
        baddorganismo.setBounds(new Rectangle(630, 15, 40, 25));
        baddorganismo.setMargin(new Insets(2, 5, 2, 14));
        baddorganismo.addActionListener(new ExptesAreas_baddorganismo_actionAdapter(this));
        bcancelar.setText("Cancelar");
        bcancelar.setBounds(new Rectangle(605, 110, 110, 25));
        bcancelar.setMnemonic('c');
        bcancelar.setMargin(new Insets(2, 5, 2, 14));
        bcancelar.addActionListener(new ExptesAreas_bcancelar_actionAdapter(this));
        bmodorganismo.setBounds(new Rectangle(675, 15, 40, 25));
        bmodorganismo.setMargin(new Insets(2, 5, 2, 14));
        bmodorganismo.addActionListener(new ExptesAreas_bmodorganismo_actionAdapter(this));
        centralPanel.setBounds(new Rectangle(0, 0, 725, 145));
        centralPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        centralPanel.setLayout(null);
        jcorganismo.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent evt) {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        jtidorganismo.setText(OP_Proced.getCampo("SELECT idorganismo FROM files.tiposorganismo WHERE descripcion='" + jcorganismo.getSelectedItem() + "'"));
                        sidorganismo = jtidorganismo.getText();
                    }
                }
            }
        );
        if (Query.length() > 0) {
            ResultSet Reg = OP_Proced.exConsulta(Query);
            if (Reg.next()) {
                idorganismo = Reg.getString(1);
                jtidorganismo.setText(idorganismo);
                OP_Proced.CargaCombo(jcorganismo, "SELECT descripcion FROM files.tiposorganismo WHERE idorganismo=" + idorganismo, "");
                jtidarea.setText(Reg.getString(2));
                jtarea.setText(Reg.getString(3));
            }
        } else {
            jtidorganismo.setText(idorganismo);
            OP_Proced.CargaCombo(jcorganismo, "SELECT descripcion FROM files.tiposorganismo WHERE idorganismo=" + idorganismo, "");
        }
        
        baddorganismo.setVisible(false);
        bmodorganismo.setVisible(false);
    }

    void baddorganismo_actionPerformed(ActionEvent e) {
        CargaCampo organ = new CargaCampo("", "files.tiposorganismo", "idorganismo", "descripcion", "Nombre o Descripcion", "Nuevo Organismo");
        OP_Proced.CentraVentana(organ);
        organ.setModal(true);
        organ.setVisible(true);
        if (organ.getCampo().length() > 0) {
            OP_Proced.CargaCombo(jcorganismo, "SELECT descripcion FROM files.tiposorganismo WHERE upper(descripcion)='" + organ.getCampo().toUpperCase() + "'", "");
        }
    }

    void bmodorganismo_actionPerformed(ActionEvent e) {
        idorganismo = sidorganismo;
        if (!OP_Proced.getCampo("SELECT estado FROM files.tiposorganismo WHERE idorganismo=" + idorganismo).equals("a")) {
            OP_Proced.exActualizar('a', "UPDATE files.tiposorganismo set estado='a' WHERE idorganismo=" + idorganismo);
            CargaCampo organ = new CargaCampo("SELECT * FROM files.tiposorganismo WHERE idorganismo=" + idorganismo, "files.tiposorganismo", "idorganismo", "descripcion", "Nombre o Descripcion", "Modificar Organismo");
            OP_Proced.CentraVentana(organ);
            organ.setModal(true);
            organ.setVisible(true);
            if (organ.getCampo().length() > 0) {
                idorganismo = organ.getCodigo();
                OP_Proced.CargaCombo(jcorganismo, "SELECT descripcion FROM files.tiposorganismo WHERE idorganismo=" + idorganismo, "");
            }
            OP_Proced.exActualizar('a', "UPDATE files.tiposorganismo set estado='' WHERE idorganismo=" + idorganismo);
        }
    }

    void baceptar_actionPerformed(ActionEvent e) {
        if (Query.length() > 0) {
            idarea = jtidarea.getText();
            String Q = "UPDATE files.tiposarea set idorganismo=" + sidorganismo + ", descripcion='" + jtarea.getText().toUpperCase() + "' WHERE idarea=" + idarea + " and idorganismo=" + idorganismo;
            if (OP_Proced.exActualizar('m', Q)) {
                this.dispose();
            }
        } else {
            idarea = OP_Proced.Max("files.tiposarea", "idarea", " WHERE idorganismo=" + jtidorganismo.getText());
            String Q = "Insert into files.tiposarea values(" + idorganismo + "," + idarea + ",'" + jtarea.getText().toUpperCase() + "','')";
            System.out.println("consulta: "+ Q);
            if (OP_Proced.exActualizar('a', Q)) {
                this.dispose();
            }
        }
        idorganismo = sidorganismo;
    }

    void bcancelar_actionPerformed(ActionEvent e) {
        idorganismo = "";
        this.dispose();
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
                Q = "SELECT descripcion FROM files.tiposorganismo WHERE estado<>'*' and (idorganismo=" + id + " or upper(descripcion) LIKE upper('%" + jtidorganismo.getText() + "%')) order by descripcion";
                OP_Proced.CargaCombo(jcorganismo, Q, "");
            }
        }
    }
}
class ExptesAreas_baddorganismo_actionAdapter implements ActionListener {
    ExptesAreas adaptee;

    ExptesAreas_baddorganismo_actionAdapter(ExptesAreas adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.baddorganismo_actionPerformed(e);
    }
}
class ExptesAreas_bmodorganismo_actionAdapter implements ActionListener {
    ExptesAreas adaptee;

    ExptesAreas_bmodorganismo_actionAdapter(ExptesAreas adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.bmodorganismo_actionPerformed(e);
    }
}
class ExptesAreas_baceptar_actionAdapter implements ActionListener {
    ExptesAreas adaptee;

    ExptesAreas_baceptar_actionAdapter(ExptesAreas adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.baceptar_actionPerformed(e);
    }
}
class ExptesAreas_bcancelar_actionAdapter implements ActionListener {
    ExptesAreas adaptee;

    ExptesAreas_bcancelar_actionAdapter(ExptesAreas adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.bcancelar_actionPerformed(e);
    }
}
