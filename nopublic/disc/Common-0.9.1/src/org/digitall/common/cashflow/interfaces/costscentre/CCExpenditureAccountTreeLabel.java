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
 * CCExpenditureAccountTreeLabel.java
 *
 * */
package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JToggleButton;

import org.digitall.common.cashflow.classes.CostsCentre;
import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.data.Format;
import org.digitall.common.cashflow.classes.ExpenditureAccount.CCItem;

public class CCExpenditureAccountTreeLabel extends BasicContainerPanel {
    private BasicPanel jPanel1 = new BasicPanel();
    private GridLayout gridLayout1 = new GridLayout();
    private JToggleButton tbtnName = new JToggleButton();
    private BasicPanel jPanel2 = new BasicPanel();
    private GridLayout gridLayout2 = new GridLayout();
    private BasicLabel lblAssignedAmount = new BasicLabel();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();
    
    private BasicPanel jPanel3 = new BasicPanel();
    private BasicLabel lblSpentAmount = new BasicLabel();
    private GridLayout gridLayout3 = new GridLayout();
    private BasicPanel jPanel4 = new BasicPanel();
    private BasicLabel lblAvailableAmount = new BasicLabel();
    private GridLayout gridLayout4 = new GridLayout();
    private ExpenditureAccount.CCItem objItem = new ExpenditureAccount.CCItem();
    
    
    public CCExpenditureAccountTreeLabel(ExpenditureAccount.CCItem _objItem) {
	try {
	    objItem = _objItem;
	    setData();
	    jbInit();
	} catch (Exception e) {
	e.printStackTrace();
	}
    }
    
    public CCExpenditureAccountTreeLabel(CostsCentre _costsCentre) {
	try {
	    setCostsCentre(_costsCentre);
	    jbInit();
	} catch (Exception e) {
	e.printStackTrace();
	}
    }
    
    private void jbInit() throws Exception {
	this.setLayout(gridBagLayout1);
	this.setBounds(new Rectangle(10, 10, 500, 20));
	jPanel1.setLayout(gridLayout1);
	jPanel1.setOpaque(false);
	jPanel2.setLayout(gridLayout2);
	jPanel2.setOpaque(false);
	jPanel3.setLayout(gridLayout3);
	jPanel3.setOpaque(false);
	jPanel4.setLayout(gridLayout4);
	jPanel4.setOpaque(false);
	tbtnName.setBorderPainted(false);
	tbtnName.setMargin(new Insets(1, 1, 1, 1));
	tbtnName.setFont(new Font("Default", 0, 12));
	tbtnName.setOpaque(false);
	tbtnName.setContentAreaFilled(false);
	jPanel1.add(tbtnName, null);
	this.add(jPanel1, new GridBagConstraints(1, 0, 1, 16, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
	jPanel2.add(lblAssignedAmount, null);
	this.add(jPanel2, new GridBagConstraints(2, 0, 1, 16, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
	jPanel3.add(lblSpentAmount, null);
	this.add(jPanel3, new GridBagConstraints(3, 0, 1, 16, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
	jPanel4.add(lblAvailableAmount, null);
	this.add(jPanel4, new GridBagConstraints(4, 0, 1, 16, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
    }

    public void setData() {
	lblAssignedAmount.setText("A: "+ Format.money(objItem.getInitialAmount()));
	lblAssignedAmount.setForeground(Color.BLUE);
	
	lblSpentAmount.setText(" | E: "+ Format.money(objItem.getSpentAmount()));
	lblSpentAmount.setForeground(Color.RED);
	
	lblAvailableAmount.setText(" | S: "+ Format.money(objItem.getAvailableCCAmount()));
	if (objItem.getAvailableCCAmount() > 0){
	    lblAvailableAmount.setForeground(new Color(30,170,30));
	} else {
	    lblAvailableAmount.setForeground(Color.RED);
	}
	
	tbtnName.setText(objItem.getName());
    }
    
    public void setCostsCentre(CostsCentre _costsCentre) {
	objItem = null;
	
	tbtnName.setText("("+ _costsCentre.getCode() +") "+ _costsCentre.getName());
	tbtnName.setForeground(Color.BLUE);
    }
}
