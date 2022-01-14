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
 * CashFlowToolBar.java
 *
 * */
package org.digitall.apps.cashflow.interfaces;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import org.digitall.common.cashflow.interfaces.account.BankAccountsList;
import org.digitall.common.cashflow.interfaces.account.PaymentsOrdersList;
import org.digitall.common.cashflow.interfaces.budget.BudgetList;
import org.digitall.common.cashflow.interfaces.cashmovement.CashMovementTypesTree;
import org.digitall.common.cashflow.interfaces.cashmovement.CashMovementsList;
import org.digitall.common.cashflow.interfaces.costscentre.CCList;
import org.digitall.common.cashflow.interfaces.expenditureaccounts.ExpenditureAccountsTree;
import org.digitall.common.cashflow.interfaces.voucher.VouchersList;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDesktop;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicToolBar;
import org.digitall.lib.icons.IconTypes;

//

public class CashFlowToolBar extends BasicToolBar {

    private BasicButton btnBudgets = new BasicButton();
    private BasicButton btnCCList = new BasicButton();
    private BasicDesktop desktop;
    private BasicLabel lblCashFlow = new BasicLabel();
    private final String title = "Herramientas del Módulo COMPyS";
    //private BasicButton btnExpenditureAccounts = new BasicButton();
    private BasicButton btnAccount = new BasicButton();
    private BasicButton btnCashMovementTypes = new BasicButton();
    private CFFrameContainer cashMovementTypesTreeContainer = new CFFrameContainer("Tipos de Movimientos de Fondos");
    private CashMovementTypesTree cashMovementTypesTree;
    private BasicButton btnCashFlow = new BasicButton();
    private CFFrameContainer cashMovementsListContainer = new CFFrameContainer("Movimientos de Fondos");
    private CashMovementsList cashMovementsList;
    private CFFrameContainer voucherListContainer = new CFFrameContainer("Comprobantes");
    private VouchersList voucherList;
    private BasicButton btnVouchers = new BasicButton();
    private CCList ccList;
    private CFFrameContainer ccListContainer = new CFFrameContainer("Centros de Costos");
    private BudgetList budgetList;
    private CFFrameContainer budgetListContainer = new CFFrameContainer("Partidas Presupuestarias");
    private BankAccountsList accountsList;
    private CFFrameContainer accountsListContainer = new CFFrameContainer("Cuentas Bancarias");
    private BasicButton btnPaymentsOrders = new BasicButton();

    public CashFlowToolBar(BasicDesktop _desktop) {
	try {
	    desktop = _desktop;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	btnBudgets.setToolTipText("Partidas Presupuestarias");
	btnBudgets.setMaximumSize(new Dimension(150, 22));
	btnBudgets.setPreferredSize(new Dimension(35, 35));
	btnBudgets.setText("Partidas Presupuestarias");
	btnBudgets.setHorizontalTextPosition(SwingConstants.CENTER);
	btnBudgets.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnBudgetItems_actionPerformed(e);
		    }

		});
	btnCCList.setToolTipText("Centros de Costos");
	btnCCList.setText("Centros de Costos");
	btnCCList.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnCCList_actionPerformed(e);
		    }

		});
	btnCCList.setCursor(new Cursor(Cursor.HAND_CURSOR));
	lblCashFlow.setText("CashFlow");
	lblCashFlow.setIcon(IconTypes.systemIcon_16x16);
	lblCashFlow.setForeground(new Color(0, 0, 82));
	lblCashFlow.setBounds(new Rectangle(1, 100, 77, 22));
	/*btnExpenditureAccounts.setText("Tipos de Gastos");
	btnExpenditureAccounts.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnExpenditureAccounts_actionPerformed(e);
		    }

		});
	*/
	btnAccount.setText("Cuentas de Bco.");
	btnAccount.addActionListener(new ActionListener() {

				  public void actionPerformed(ActionEvent e) {
				      btnAccount_actionPerformed(e);
				  }

			      }
	);
	btnCashMovementTypes.setText("Tipos de Movimientos de Fondos");
	btnCashMovementTypes.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					    btnCashFlowTypes_actionPerformed(e);
					}

				    }
	);
	btnCashFlow.setText("Movimientos de Fondos");
	btnCashFlow.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnCashFlow_actionPerformed(e);
				   }

			       }
	);
	btnVouchers.setText("Comprobantes");
	btnVouchers.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       btnVouchers_actionPerformed(e);
				   }

			       }
	);
	btnPaymentsOrders.setText("Ordenes de Pagos");
	btnPaymentsOrders.addActionListener(new ActionListener() {

					 public void actionPerformed(ActionEvent e) {
					     btnPaymentsOrders_actionPerformed(e);
					 }

				     }
	);
	this.setRollover(true);
	this.setToolTipText(title);
	this.setBounds(new Rectangle(0, 0, 1006, 38));
	this.add(lblCashFlow, null);
	this.add(btnCashFlow, null);
	this.add(btnVouchers, null);
	this.add(btnPaymentsOrders, null);
    }

    private void btnBudgetItems_actionPerformed(ActionEvent e) {
	budgetList = new BudgetList();
	budgetListContainer.setDesktop(desktop);
	budgetListContainer.show();
    }

    private void btnCCList_actionPerformed(ActionEvent e) {
	ccList = new CCList();
	ccListContainer.setDesktop(desktop);
	ccListContainer.show();
    }

    /*private void btnExpenditureAccounts_actionPerformed(ActionEvent e) {
	expenditureAccountsTree = new ExpenditureAccountsTree();
	expenditureAccountsTreeContainer.setDesktop(desktop);
	expenditureAccountsTreeContainer.show();
    }*/

    private void btnAccount_actionPerformed(ActionEvent e) {
	accountsList = new BankAccountsList();
	accountsListContainer.setDesktop(desktop);
	accountsListContainer.show();
    }

    private void btnCashFlowTypes_actionPerformed(ActionEvent e) {
	cashMovementTypesTree = new CashMovementTypesTree();
	cashMovementTypesTreeContainer.setDesktop(desktop);
	cashMovementTypesTreeContainer.show();
    }

    private void btnCashFlow_actionPerformed(ActionEvent e) {
	cashMovementsList = new CashMovementsList();
	cashMovementsListContainer.setDesktop(desktop);
	cashMovementsListContainer.show();
    }

    private void btnVouchers_actionPerformed(ActionEvent e) {
	voucherList = new VouchersList();
	voucherListContainer.setDesktop(desktop);
	voucherListContainer.show();
    }

    private void btnPaymentsOrders_actionPerformed(ActionEvent e) {
	
    }

}
