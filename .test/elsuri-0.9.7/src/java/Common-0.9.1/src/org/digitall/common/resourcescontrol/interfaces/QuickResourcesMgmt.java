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
 * QuickResourcesMgmt.java
 *
 * */
package org.digitall.common.resourcescontrol.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.digitall.common.cashflow.classes.ExpenditureAccount;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.common.resourcescontrol.classes.Resource;
import org.digitall.common.resourcescontrol.classes.Skills;
import org.digitall.common.resourcescontrol.classes.Units;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.SaveDataButton;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;
import org.digitall.lib.sql.LibSQL;

public class QuickResourcesMgmt extends BasicPrimitivePanel {

    private TFInput tfResource = new TFInput(DataTypes.STRING, "Nombre del Recurso", false);
    private TFInput tfFindAccount = new TFInput(DataTypes.STRING, "FindAccounting", false);
    private CBInput cbAccounting = new CBInput(0, "Accounting", false);
    private BasicPanel content = new BasicPanel();
    private SaveDataButton btnSave = new SaveDataButton();
    private CloseButton btnClose = new CloseButton();

    public QuickResourcesMgmt() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.add(content, BorderLayout.CENTER);
	this.setSize(new Dimension(582, 133));
	tfResource.setBounds(new Rectangle(5, 5, 570, 35));
	tfFindAccount.setBounds(new Rectangle(5, 50, 110, 35));
	cbAccounting.setBounds(new Rectangle(125, 50, 450, 35));
	content.add(tfResource);
	content.add(tfFindAccount);
	content.add(cbAccounting);
	content.setLayout(null);
	btnSave.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnSave_actionPerformed(e);
		    }

		});
	btnClose.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnClose_actionPerformed(e);
		    }

		});
	cbAccounting.autoSize();
	addButton(btnClose);
	addButton(btnSave);
	tfFindAccount.getTextField().addKeyListener(new KeyAdapter() {

		    public void keyReleased(KeyEvent e) {
			if ((e.getKeyChar() == KeyEvent.VK_ENTER)) {
			    loadComboAccounting();
			}
		    }

		});
    }

    private void loadComboAccounting() {
	String param = "5000,'" + tfFindAccount.getString() + "'";
	cbAccounting.loadJCombo(LibSQL.exFunction("accounting.getAllAccountsByFilter", param));
    }

    private void btnSave_actionPerformed(ActionEvent e) {
	if (valid()) {
	    try {
		Resource resource = new Resource();
		resource.setName(tfResource.getString());
		resource.setUnit(new Units(0, ""));
		resource.setSkillToProvider(new Skills(0));
		resource.setExpenditureAccount(new ExpenditureAccount(Integer.parseInt(cbAccounting.getSelectedValue().toString())));
		if ((resource.saveData() > 0)) {
		    tfResource.setValue("");
		} else {
		    Advisor.messageBox("Error al intentar grabar el recurso", "Error");
		}
	    } catch (Exception x) {
		Advisor.messageBox("Error al intentar grabar el recurso", "Error");
	    }
	} else {
	    Advisor.messageBox("Debe escribir un nombre y seleccionar una cuenta", "Error");
	}
    }

    private void btnClose_actionPerformed(ActionEvent e) {
	getParentInternalFrame().close();
    }

    private boolean valid() {
	boolean returns = false;
	try {
	    returns = (cbAccounting.getSelectedIndex() != -1 && tfResource.getString().length() > 0);
	} catch (Exception x) {
	    //ignore
	}
	return returns;
    }

}
