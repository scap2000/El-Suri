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
 * UserList.java
 *
 * */
package org.digitall.apps.projecttask.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;

import org.digitall.apps.projecttask.classes.Person;
import org.digitall.lib.org.User;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.common.components.combos.CachedCombo;
import org.digitall.common.components.inputpanels.CBInput;
import org.digitall.lib.components.inputpanels.TFInput;
import org.digitall.lib.data.DataTypes;

public class UserList extends BasicContainerPanel {

    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private BasicPanel dataPanel = new BasicPanel();
    private TFInput tfEmployeName = new TFInput(DataTypes.STRING, "Employee", false);
    private CBInput cbUsers = new CBInput(CachedCombo.USERS, "Users", false);
    private CloseButton btnColse = new CloseButton();
    private AcceptButton btnAccept = new AcceptButton();
     
    private Person person;
    private User user;

    public UserList() {
	try {
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public UserList(BasicInternalFrame _parent) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public UserList(BasicDialog _parent) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public UserList(JFrame _parent) {
	try {
	    parent = _parent;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(525, 140));
	//lblPriority.setSize(new Dimension(70, 14));
	dataPanel.setBounds(new Rectangle(15, 5, 495, 70));
	dataPanel.setLayout(null);
	dataPanel.setBorder(BorderPanel.getBorderPanel("Seleccione usuario", Color.BLUE, Color.BLACK));
	tfEmployeName.setBounds(new Rectangle(215, 20, 255, 35));
	tfEmployeName.setSize(new Dimension(265, 35));
	cbUsers.setBounds(new Rectangle(10, 20, 190, 40));
	cbUsers.autoSize();
	cbUsers.getCombo().addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent evt) {
					    if (evt.getStateChange() == ItemEvent.SELECTED) {
						tfEmployeName.setValue(cbUsers.toString());
						loadData(Integer.parseInt(cbUsers.getSelectedValue().toString()));
					    }
					}

				    }
	);
	btnColse.setBounds(new Rectangle(470, 90, 40, 25));
	btnColse.setSize(new Dimension(40, 25));
	btnColse.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnColse_actionPerformed(e);
				}

			    }
	);
	btnAccept.setBounds(new Rectangle(405, 90, 40, 25));
	btnAccept.setSize(new Dimension(40, 25));
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	//new Rectangle(0, 80, 525, 10));
	dataPanel.add(tfEmployeName, null);
	dataPanel.add(cbUsers, null);
	 
	this.add(btnAccept, null);
	this.add(btnColse, null);
	this.add(dataPanel, null);
	user = new User(Integer.parseInt(cbUsers.getSelectedValue().toString()));
	person = new Person();
	person.loadDataAdmin(user.getIdperson());
	tfEmployeName.setValue(person.getLastName() + ", " + person.getName());
    }

    private void btnExit_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void dispose() {
	switch (parentType) {
	    case DIALOG :
		((BasicDialog)parent).dispose();
		break;
	    case INTERNALFRAME :
		((BasicInternalFrame)parent).setVisible(false);
		((BasicInternalFrame)parent).hide();
		break;
	    case FRAME :
		((JFrame)parent).dispose();
		break;
	}
    }

    private void btnColse_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void loadData(int _idPerson) {
	user.loadData(_idPerson);
	person.loadDataAdmin(user.getIdperson());
	tfEmployeName.setValue(person.getLastName() + ", " + person.getName());
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	String name = person.getLastName() + ", " + person.getName();
	TaskByEmployeeJDialog taskByEmployeeBasicDialog = new TaskByEmployeeJDialog(name, user);
	taskByEmployeeBasicDialog.setModal(true);
	taskByEmployeeBasicDialog.setVisible(true);
    }

}
