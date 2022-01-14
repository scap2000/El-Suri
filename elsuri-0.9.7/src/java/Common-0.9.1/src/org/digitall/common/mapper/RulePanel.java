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
 * RulePanel.java
 *
 * */
package org.digitall.common.mapper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;

public class RulePanel extends JPanel {

    private BasicPanel jpRule = new BasicPanel();
    private BasicLabel lblRuleTitle = new BasicLabel();
    private BasicLabel lblPartialTitle = new BasicLabel();
    private BasicLabel lblTotalTitle = new BasicLabel();
    private BasicLabel lblAreaTitle = new BasicLabel();
    private BasicLabel lblAngleTitle = new BasicLabel();
    private BasicLabel lblPartial = new BasicLabel();
    private BasicLabel lblTotal = new BasicLabel();
    private BasicLabel lblArea = new BasicLabel();
    private BasicLabel lblAngle = new BasicLabel();

    public RulePanel() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(165, 107));
	jpRule.setBounds(new Rectangle(0, 0, 165, 105));
	jpRule.add(lblArea, null);
	jpRule.add(lblAngle, null);
	jpRule.add(lblTotal, null);
	jpRule.add(lblPartial, null);
	jpRule.add(lblAreaTitle, null);
	jpRule.add(lblAngleTitle, null);
	jpRule.add(lblTotalTitle, null);
	jpRule.add(lblPartialTitle, null);
	jpRule.add(lblRuleTitle, null);
	jpRule.setBackground(new Color(232, 229, 226));
	jpRule.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jpRule.setLayout(null);
	jpRule.setSize(new Dimension(165, 106));
	lblRuleTitle.setText(" Herramienta de Medición ");
	lblRuleTitle.setBounds(new Rectangle(0, 0, 165, 25));
	lblRuleTitle.setHorizontalAlignment(BasicLabel.RIGHT);
	lblRuleTitle.setForeground(Color.white);
	lblRuleTitle.setBackground(new Color(33, 33, 255));
	lblRuleTitle.setOpaque(true);
	lblRuleTitle.setFont(new Font("Dialog", Font.BOLD, 10));
	lblRuleTitle.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblPartialTitle.setText("Parcial: ");
	lblPartialTitle.setBounds(new Rectangle(1, 25, 55, 20));
	lblPartialTitle.setHorizontalAlignment(BasicLabel.RIGHT);
	lblPartialTitle.setForeground(Color.white);
	lblPartialTitle.setBackground(new Color(115, 198, 0));
	lblPartialTitle.setOpaque(true);
	lblPartialTitle.setFont(new Font("Dialog", Font.BOLD, 10));
	lblTotalTitle.setText("Total: ");
	lblTotalTitle.setBounds(new Rectangle(1, 45, 55, 20));
	lblTotalTitle.setHorizontalAlignment(BasicLabel.RIGHT);
	lblTotalTitle.setForeground(Color.white);
	lblTotalTitle.setBackground(new Color(99, 165, 0));
	lblTotalTitle.setOpaque(true);
	lblTotalTitle.setFont(new Font("Dialog", Font.BOLD, 10));
	lblAreaTitle.setText("Área: ");
	lblAreaTitle.setBounds(new Rectangle(1, 65, 55, 20));
	lblAreaTitle.setHorizontalAlignment(BasicLabel.RIGHT);
	lblAreaTitle.setForeground(Color.white);
	lblAreaTitle.setBackground(new Color(90, 148, 0));
	lblAreaTitle.setOpaque(true);
	lblAreaTitle.setFont(new Font("Dialog", Font.BOLD, 10));
	lblAngleTitle.setText("Ángulo: ");
	lblAngleTitle.setBounds(new Rectangle(1, 85, 55, 20));
	lblAngleTitle.setHorizontalAlignment(BasicLabel.RIGHT);
	lblAngleTitle.setForeground(Color.white);
	lblAngleTitle.setBackground(new Color(82, 132, 0));
	lblAngleTitle.setOpaque(true);
	lblAngleTitle.setFont(new Font("Dialog", Font.BOLD, 10));

	lblPartial.setText("");
	lblPartial.setBounds(new Rectangle(60, 25, 100, 20));
	lblPartial.setHorizontalAlignment(BasicLabel.LEFT);
	lblPartial.setForeground(Color.black);
	lblPartial.setFont(new Font("Dialog", Font.BOLD, 10));
	lblTotal.setText("");
	lblTotal.setBounds(new Rectangle(60, 45, 100, 20));
	lblTotal.setHorizontalAlignment(BasicLabel.LEFT);
	lblTotal.setForeground(Color.black);
	lblTotal.setFont(new Font("Dialog", Font.BOLD, 10));
	lblArea.setText("");
	lblArea.setBounds(new Rectangle(60, 65, 100, 20));
	lblArea.setHorizontalAlignment(BasicLabel.LEFT);
	lblArea.setForeground(Color.black);
	lblArea.setFont(new Font("Dialog", Font.BOLD, 10));
	lblAngle.setText("");
	lblAngle.setBounds(new Rectangle(60, 85, 100, 20));
	lblAngle.setHorizontalAlignment(BasicLabel.LEFT);
	lblAngle.setForeground(Color.black);
	lblAngle.setFont(new Font("Dialog", Font.BOLD, 10));
	this.add(jpRule, null);
    }

    public void setData(double _partial, double _total, double _area, double _angle) {
	lblPartial.setText((new DecimalFormat("0.00")).format(_partial) + "m");
	lblTotal.setText((new DecimalFormat("0.00")).format(_total) + "m");
	lblArea.setText((new DecimalFormat("0.00")).format(_area) + "m²");
	lblAngle.setText((new DecimalFormat("0.00")).format(_angle) + "º");
    }

}
