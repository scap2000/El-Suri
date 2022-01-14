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
 * CCAssignedAmountList.java
 *
 * */
package org.digitall.common.cashflow.interfaces.costscentre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.lib.components.JMoneyEntry;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTextField;
import org.digitall.lib.components.basic.ExtendedInternalFrame;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.grid.GridPanel;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.data.Format;

public class CCAssignedAmountList extends BasicPrimitivePanel {

    private int[] sizeColumnList = { 204, 103, 50, 114, 50, 107, 50 };
    private Vector dataRow = new Vector();
    private GridPanel listPanel = new GridPanel(30, sizeColumnList, "Partidas Presupuestarias", dataRow);
    private Vector headerList = new Vector();
    private CloseButton btnClose = new CloseButton();
    private ExpenditureAccount.CCItem ccExpenditureAccount;
    private TFInput tfExpenditureAccount = new TFInput(DataTypes.STRING, "ExpenditureAccount", false);
    private BasicLabel jLabel1 = new BasicLabel();
    private JMoneyEntry tfTotal = new JMoneyEntry();
    private BasicPanel northPanel = new BasicPanel();
    private BasicPanel southPanel = new BasicPanel();

    public CCAssignedAmountList() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(560, 329));
	listPanel.setBounds(new Rectangle(0, 50, 560, 245));
	btnClose.setBounds(new Rectangle(525, 450, 30, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	tfExpenditureAccount.setBounds(new Rectangle(63, 0, 435, 35));
	jLabel1.setText("Total Asignado:");
	jLabel1.setBounds(new Rectangle(310, 5, 95, 20));
	tfTotal.setBounds(new Rectangle(410, 5, 130, 20));
	//this.addButton(btnClose);
	southPanel.add(jLabel1, null);
	southPanel.add(tfTotal, null);
	northPanel.add(tfExpenditureAccount, null);
	this.add(northPanel, BorderLayout.NORTH);
	this.add(southPanel, BorderLayout.SOUTH);
	this.add(listPanel, BorderLayout.CENTER);
	tfExpenditureAccount.setEnabled(false);
	tfTotal.setEnabled(false);
	tfTotal.setEditable(false);
	northPanel.setBounds(new Rectangle(0, 0, 560, 325));
	northPanel.setLayout(null);
	northPanel.setMinimumSize(new Dimension(560, 325));
	northPanel.setPreferredSize(new Dimension(560, 40));
	southPanel.setToolTipText("null");
	southPanel.setLayout(null);
	southPanel.setPreferredSize(new Dimension(1, 30));
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setClosable(true);
	getParentInternalFrame().setInfo("Detalle de los Montos Asignados por Tipos de Gastos");
    }

    private void loadData() {
	if (ccExpenditureAccount != null) {
	    setHeaderList();
	    refresh();
	    tfExpenditureAccount.setValue(ccExpenditureAccount.getName());
	    tfTotal.setText(Format.money(ccExpenditureAccount.getInitialAmount()));
	}
    }

    private void setHeaderList() {
	headerList.removeAllElements();
	headerList.addElement("*");
	headerList.addElement("Partida Presupuestaria");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("*");
	headerList.addElement("($) Inicial");
	headerList.addElement("%");
	headerList.addElement("($) Ejecutado");
	headerList.addElement("%");
	headerList.addElement("($) Saldo");
	headerList.addElement("%");
	headerList.addElement("*");
	headerList.addElement("*");
	listPanel.getTable().addMouseListener(new MouseAdapter() {

					   public void mouseClicked(MouseEvent e) {
					       if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
					       } else if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {

					       }
					   }

				       }
	);
	listPanel.setParams("cashflow.getAllCostsCentreAssignedAmount", "-1,-1", headerList);
    }

    public void refresh() {
	String params = ccExpenditureAccount.getCostCentre().getIdCostCentre() + "," + ccExpenditureAccount.getIDExpenditureAccount();
	listPanel.refresh(params);
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    public void setCcExpenditureAccount(ExpenditureAccount.CCItem _ccExpenditureAccount) {
	this.ccExpenditureAccount = _ccExpenditureAccount;
	loadData();
    }

}
