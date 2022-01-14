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
 * Plazos.java
 *
 * */
package org.digitall.projects.gdigitall.expedientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JPanel;

import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.projects.gdigitall.lib.components.CRegistros;
import org.digitall.projects.gdigitall.lib.misc.OP_Proced;

//public class Plazos extends JDialog implements ActionListener {
public class Plazos extends BasicPrimitivePanel implements ActionListener {
    //private JPanel jPanel1 = new JPanel();
    private Vector datos1, datosx = new Vector();
    private Timer timer = new Timer();
    private int[] vcol = { 3 };
    private int[] tcol = { 30, 680, 40 };
    private CRegistros jPanel1 = new CRegistros(30, "files.", "files.plazos", vcol, tcol);
    private String Consulta = "", ConsultaCount = "", cfiltro = "", idtipoinst = "";
    private JButton bseleccionar = new JButton();//new ImageIcon(OP_Proced.class.getResource("iconos/16x16/seleccionar.gif")));
    private JButton bcerrar = new JButton();//new ImageIcon(OP_Proced.class.getResource("iconos/16x16/cerrar.gif")));
    static String dia = "", idtipo = "";
    private JPanel centralPanel = new JPanel();
    //*************************************
    private frmExpedientes parentMain;
    private principal_simex parent;

    /**
   * FORMULARIO PARA VISUALIZAR LOS DIFERENTES TIPOS DE PLAZO
   */
    public Plazos() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(789, 294));
        //this.getContentPane().setLayout(null);
        this.setTitle("Listado de Tipos de Plazos");
        jPanel1.setBounds(new Rectangle(5, 5, 775, 215));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jPanel1.setLayout(null);
        bseleccionar.setText("Seleccionar");
        bseleccionar.setBounds(new Rectangle(5, 235, 130, 25));
        bseleccionar.setMnemonic('s');
        bcerrar.setText("Cerrar");
        bcerrar.setBounds(new Rectangle(685, 235, 95, 25));
        bcerrar.setMnemonic('c');
        centralPanel.add(bcerrar, null);
        centralPanel.add(bseleccionar, null);
        centralPanel.add(jPanel1, null);
        //this.getContentPane().add(centralPanel, null);
        this.add(centralPanel, null);
        jPanel1.Redimensiona();
        datos1 = jPanel1.VDatos();
        bcerrar.addActionListener(this);
        centralPanel.setBounds(new Rectangle(0, 0, 785, 270));
        centralPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        centralPanel.setLayout(null);
        bseleccionar.addActionListener(this);
        timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        datosx = jPanel1.VDatos();
                        if (!datos1.equals(datosx)) {
                            datos1 = datosx;
                            // System.out.println(datos1);
                        }
                    } catch (Exception x) {
                        OP_Proced.Mensaje(x.getMessage(), "ERROR:");
                    }
                }
            }
            , 0, 500);
        ActualizaTabla();
    }

    /*public void setParentInternalFrame(ExtendedInternalFrame _e) {
        super.setParentInternalFrame(_e);
        getParentInternalFrame().setInfo("");
    }*/

    public void ActualizaTabla() {
        Consulta = "SELECT * FROM files.tiposplazo ORDER BY descripcion";
        ConsultaCount = "SELECT count(*) FROM files.tiposplazo";
        jPanel1.ActualizaTabla(this, Consulta, ConsultaCount);
        datos1.clear();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bcerrar) {
            //this.dispose();
            getParentInternalFrame().close();
        } else if (e.getSource() == bseleccionar) {
            dia = datos1.elementAt(2).toString();
            idtipo = datos1.elementAt(0).toString();
            //this.dispose();
            parentMain.setTipoPlazo(idtipo);
            parentMain.setDia(dia);
            getParentInternalFrame().close();
        }
    }

    public void setParentMain(frmExpedientes _parentMain) {
        parentMain = _parentMain;
    }
    
    public void setParent(principal_simex _parent) {
        parent = _parent;
    }

    public String getDia() {
        return dia;
    }

    public String getIdtipo() {
        return idtipo;
    }
}
