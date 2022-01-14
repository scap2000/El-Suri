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
 * AccountsMain.java
 *
 * */
package org.digitall.common.cashflow.interfaces.accounting;

import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.digitall.common.cashflow.classes.Account;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

public class AccountsMain extends BasicTabContainer {

    private AccountsList accountsList = new AccountsList(this);
    private AccountsMgmt accountsMgmt = new AccountsMgmt(this);
    private Account account;
    private BasicPrimitivePanel parentPanel;

    public AccountsMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(550, 530));
	this.setPreferredSize(new Dimension(550, 530));
	addTab("Listado de Cuentas", accountsList);
	addTab("Nueva Cuenta", accountsMgmt);
    }

    public void changeSelectedTab() {
	if (getTabbedPane().getSelectedIndex() == 1 && !accountsMgmt.isModifying()) {
	    newAccount();
	} else if (getTabbedPane().getSelectedIndex() == 1 && accountsMgmt.isModifying()) {
	    newAccount();
	} else if (getTabbedPane().getSelectedIndex() == 0 && accountsMgmt.isModifying()) {
	    if (Advisor.question("Datos de la cuenta", "¿Desea guardar los cambios?")) {
		accountsMgmt.saveData();
	    }
	}
    }

    public void setParentInternalFrame(ExtendedInternalFrame _e) {
	super.setParentInternalFrame(_e);
	getParentInternalFrame().setInfo("Puede ver las cuentas como un listado o árbol");
    }

    public void newAccount() {
	accountsMgmt.setModifying(false);
	accountsMgmt.clearData();
	setTitleAt(1, "Nueva Cuenta");
	if (accountsList.getAccount() != null) {
	    accountsMgmt.setParentAccount(accountsList.getAccount());
	} else {
	    if (Advisor.question("Nueva Cuenta", "<html><center>No seleccionó una Cuenta superior,\n" + "la nueva cuenta se agregará a la raiz.\n¿Desea continuar?</center></html>")) {
		accountsMgmt.setParentAccount(accountsList.getParentAccount());
	    } else {
		setSelectedTab(0);
	    }
	}
    }

    public AccountsList getAccountsList() {
	return accountsList;
    }

    public AccountsMgmt getAccountMgmt() {
	return accountsMgmt;
    }

    public void setAccount(Account account) {
	this.account = account;
	accountsList.setAccount(account);
    }

    public void setParentPanel(BasicPrimitivePanel parentPanel) {
	this.parentPanel = parentPanel;
	accountsList.setParentPanel(parentPanel);
    }

}
