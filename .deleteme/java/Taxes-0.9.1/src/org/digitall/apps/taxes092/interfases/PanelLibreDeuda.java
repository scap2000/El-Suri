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
 * PanelLibreDeuda.java
 *
 * */
package org.digitall.apps.taxes092.interfases;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.JDecEntry;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTextArea;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class PanelLibreDeuda extends BasicPanel{

    private BasicPanel panelAyuda = new BasicPanel();
    private BasicPanel panelCentral = new BasicPanel();
    private BasicPanel panelPeriodos = new BasicPanel();
    private BasicLabel lblTitulo = new BasicLabel();
    private BasicLabel lblInteres = new BasicLabel();
    private BasicLabel lblCapital = new BasicLabel();
    private BasicLabel lblTotalAPagar = new BasicLabel();
    private BasicLabel lblTotalAFinanciar = new BasicLabel();
    private BasicTextArea taAyuda = new BasicTextArea();
    private TFInput tfTipoContribucion = new TFInput(DataTypes.STRING,"Tipo Contribución", false);
    private TFInput tfAlicuotaAplicada = new TFInput(DataTypes.STRING,"Alícuota", false);
    private JDecEntry tfValorModulo = new JDecEntry();
    private JDecEntry tfMultiplicador = new JDecEntry();
    private JMoneyEntry tfTotalAPagar = new JMoneyEntry();
    private JMoneyEntry tfTotal = new JMoneyEntry();
    private JSeparator jSeparator1 = new JSeparator();
    private JButton btnGenerar = new JButton();

    public PanelLibreDeuda() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(845, 475));
	panelAyuda.setBounds(new Rectangle(0, 0, 215, 475));
	panelAyuda.setLayout(null);
	panelAyuda.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	panelAyuda.setSize(new Dimension(215, 470));
	lblTitulo.setText("Registrar Libre Deuda");
	lblTitulo.setBounds(new Rectangle(5, 5, 205, 20));
	lblTitulo.setFont(new Font("Dialog", 1, 11));
	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
	taAyuda.setBounds(new Rectangle(5, 30, 205, 440));
	panelCentral.setBounds(new Rectangle(215, 0, 630, 475));
	panelCentral.setLayout(null);
	panelCentral.setBorder(BorderFactory.createTitledBorder(""));
	panelPeriodos.setBounds(new Rectangle(5, 5, 620, 55));
	panelPeriodos.setBorder(BorderFactory.createTitledBorder(""));
	panelPeriodos.setLayout(null);
	lblInteres.setText("Multitplicador:");
	lblInteres.setBounds(new Rectangle(5, 170, 195, 30));
	lblInteres.setFont(new Font("Dialog", 0, 18));
	tfTipoContribucion.setBounds(new Rectangle(45, 10, 225, 35));
	tfTipoContribucion.setEditable(false);
	tfAlicuotaAplicada.setBounds(new Rectangle(360, 10, 225, 35));
	tfAlicuotaAplicada.setEditable(false);
	lblCapital.setText("Valor del Módulo:");
	lblCapital.setBounds(new Rectangle(5, 95, 165, 30));
	lblCapital.setFont(new Font("Dialog", 0, 18));
	tfValorModulo.setBounds(new Rectangle(320, 95, 140, 30));
	tfValorModulo.setEditable(false);
	tfValorModulo.setText("0.75");
	tfValorModulo.setFont(new Font("Dialog", 0, 20));
	tfValorModulo.setHorizontalAlignment(JTextField.RIGHT);
	tfValorModulo.setForeground(new Color(82, 132, 0));
	tfMultiplicador.setBounds(new Rectangle(320, 170, 140, 30));
	tfMultiplicador.setEditable(false);
	tfMultiplicador.setFont(new Font("Dialog", 0, 20));
	tfMultiplicador.setHorizontalAlignment(JTextField.RIGHT);
	tfMultiplicador.setText("17");
	tfMultiplicador.setForeground(new Color(82, 132, 0));
	tfTotalAPagar.setBounds(new Rectangle(365, 425, 255, 45));
	tfTotalAPagar.setEditable(false);
	tfTotalAPagar.setFont(new Font("Dialog", 1, 30));
	tfTotalAPagar.setHorizontalAlignment(JTextField.RIGHT);
	tfTotalAPagar.setText("$ 12.75");
	tfTotalAPagar.setForeground(Color.red);
	lblTotalAPagar.setText("Total a Pagar:");
	lblTotalAPagar.setBounds(new Rectangle(180, 425, 185, 45));
	lblTotalAPagar.setFont(new Font("Dialog", 1, 20));
	jSeparator1.setBounds(new Rectangle(5, 385, 615, 5));
	tfTotal.setBounds(new Rectangle(480, 245, 140, 30));
	tfTotal.setEditable(false);
	tfTotal.setFont(new Font("Dialog", 1, 21));
	tfTotal.setHorizontalAlignment(JTextField.RIGHT);
	tfTotal.setText("$ 12.75");
	tfTotal.setForeground(Color.red);
	lblTotalAFinanciar.setText("Total");
	lblTotalAFinanciar.setBounds(new Rectangle(5, 245, 200, 30));
	lblTotalAFinanciar.setFont(new Font("Dialog", 1, 18));
	btnGenerar.setText("Gen.");
	btnGenerar.setBounds(new Rectangle(85, 435, 65, 30));
	btnGenerar.setToolTipText("Generar Plan de Pago");
        btnGenerar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnGenerar_actionPerformed(e);
                }
            });
        panelAyuda.add(taAyuda, null);
	panelAyuda.add(lblTitulo, null);
	panelCentral.add(btnGenerar, null);
	panelCentral.add(lblTotalAFinanciar, null);
	panelCentral.add(tfTotal, null);
	panelCentral.add(jSeparator1, null);
	panelCentral.add(lblTotalAPagar, null);
	panelCentral.add(tfTotalAPagar, null);
	panelCentral.add(tfMultiplicador, null);
	panelCentral.add(tfValorModulo, null);
	panelCentral.add(lblCapital, null);
	panelCentral.add(lblInteres, null);
	panelCentral.add(panelPeriodos, null);
	panelPeriodos.add(tfAlicuotaAplicada, null);
	panelPeriodos.add(tfTipoContribucion, null);
	this.add(panelCentral, null);
	this.add(panelAyuda, null);
	taAyuda.setText("Ayuda");	
	taAyuda.setEditable(false);
        taAyuda.setEditable(false);
    }


    private void btnGenerar_actionPerformed(ActionEvent e) {
    }
}

