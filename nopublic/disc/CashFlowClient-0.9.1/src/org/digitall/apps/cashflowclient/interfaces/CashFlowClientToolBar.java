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
 * CashFlowClientToolBar.java
 *
 * */
package org.digitall.apps.cashflowclient.interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.digitall.common.cashflow.classes.CashFlowEnvironment;
import org.digitall.common.cashflow.interfaces.costscentre.CCToolBar;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDesktop;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicToolBar;

public class CashFlowClientToolBar extends BasicToolBar {

    private BasicLabel jLabel1 = new BasicLabel();
    private BasicDesktop desktop;
    private BasicButton btnAmount = new BasicButton();
    private AmountsExpenditureAccountsTree amountsTree;
    private CFCFrameContainer containerAmounts = new CFCFrameContainer("Tipos de Gastos del Centro de Costos");
    private CFCFrameContainer containerCC = new CFCFrameContainer();
    private CCToolBar ccToolBar;

    public CashFlowClientToolBar(BasicDesktop _desktop) {
	try {
	    desktop = _desktop;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(830, 38));
	jLabel1.setText("CashFlow-Client");
	jLabel1.setMaximumSize(new Dimension(100, 14));
	jLabel1.setFont(new Font("Default", 0, 11));
	//setOrientation(SwingConstants.VERTICAL);
	//setMaximumSize(new Dimension(5, 32767));
	btnAmount.setText("Montos");
	btnAmount.setFont(new Font("Default", 0, 11));
	btnAmount.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAmount_actionPerformed(e);
				 }

			     }
	);
	this.add(jLabel1, null);
	this.add(btnAmount, null);
	containerAmounts.setVisible(false);
	containerCC.setVisible(false);
	desktop.add(containerAmounts);
	desktop.add(containerCC);
    }

    private void loadTotalToolBar() {
	if (containerCC.isClosed()) {
	    containerCC = new CFCFrameContainer();
	    desktop.add(containerCC);
	}
	ccToolBar.setCostscentre(CashFlowEnvironment.getCostsCentre());
	containerCC.setTitle(CashFlowEnvironment.getCostsCentre().getName());
	containerCC.setPanel(ccToolBar);
	containerCC.setVisible(true);
    }

    private void loadAmountTree() {
	if (containerAmounts.isClosed()) {
	    containerAmounts = new CFCFrameContainer("Tipos de Gastos del Centro de Costos");
	    desktop.add(containerAmounts);
	}
	amountsTree.setCostsCentre(CashFlowEnvironment.getCostsCentre());
	containerAmounts.setPanel(amountsTree);
	containerAmounts.setVisible(true);
    }

    private void btnAmount_actionPerformed(ActionEvent e) {
	loadAmountTree();
	loadTotalToolBar();
    }

}
