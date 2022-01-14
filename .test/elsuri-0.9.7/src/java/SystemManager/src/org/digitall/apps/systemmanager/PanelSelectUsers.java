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
 * PanelSelectUsers.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;

import org.digitall.lib.components.List;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddUserButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.sql.LibSQL;

//

public class PanelSelectUsers extends BasicContainerPanel {

    private BasicLabel lblUsers1 = new BasicLabel();
    private BasicLabel lblUsers = new BasicLabel();
    private BasicScrollPane jScrollPane1 = new BasicScrollPane();
    private BasicScrollPane jScrollPane2 = new BasicScrollPane();
    private JList listUsers = new JList();
    private JList listUsersSelected = new JList();
    private List usersList = new List();
    private List usersSelectedList = new List();
    private AcceptButton bAccept = new AcceptButton();
    private CloseButton bCancel = new CloseButton();
    private DeleteButton bRemoveUser = new DeleteButton();
    private AddUserButton bAddUser = new AddUserButton();
     
    private String grupo = "";
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;

    public PanelSelectUsers(BasicInternalFrame _parent, String _grupo) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    grupo = _grupo;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelSelectUsers(BasicDialog _parent, String _grupo) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    grupo = _grupo;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelSelectUsers(JFrame _parent, String _grupo) {
	try {
	    parent = _parent;
	    parentType = FRAME;
	    grupo = _grupo;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(326, 258));
	bRemoveUser.setBounds(new Rectangle(145, 115, 35, 30));
	bRemoveUser.setToolTipText("Remove User");
	bRemoveUser.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bRemoveUser_actionPerformed(e);
		    }

		});
	bAddUser.setBounds(new Rectangle(145, 60, 35, 30));
	bAddUser.setToolTipText("Add User");
	bAddUser.setMnemonic('A');
	bAddUser.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAddUser_actionPerformed(e);
		    }

		});
	lblUsers1.setText("Users Selected:");
	lblUsers1.setBounds(new Rectangle(185, 10, 130, 10));
	lblUsers1.setFont(new Font("Dialog", 1, 11));
	lblUsers.setText("Users:");
	lblUsers.setBounds(new Rectangle(10, 10, 130, 10));
	lblUsers.setFont(new Font("Dialog", 1, 11));
	jScrollPane1.setBounds(new Rectangle(10, 20, 130, 175));
	jScrollPane2.setBounds(new Rectangle(185, 20, 130, 175));
	listUsers.setBounds(new Rectangle(0, 0, 130, 175));
	listUsersSelected.setBounds(new Rectangle(0, 0, 130, 175));
	bAccept.setBounds(new Rectangle(285, 220, 35, 30));
	bAccept.setToolTipText("Remove User");
	bAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAccept_actionPerformed(e);
		    }

		});
	//new Rectangle(0, 210, 325, 5));
	bCancel.setBounds(new Rectangle(5, 220, 35, 30));
	bCancel.setToolTipText("Add User");
	bCancel.setMnemonic('A');
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	jScrollPane2.getViewport().add(listUsersSelected, null);
	this.add(bCancel, null);
	 
	this.add(bAccept, null);
	this.add(jScrollPane2, null);
	jScrollPane1.getViewport().add(listUsers, null);
	this.add(jScrollPane1, null);
	this.add(lblUsers, null);
	this.add(lblUsers1, null);
	this.add(bAddUser, null);
	this.add(bRemoveUser, null);
	listUsers.addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			listUsers_mouseClicked(e);
		    }

		});
	listUsersSelected.addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			listUsersSelected_mouseClicked(e);
		    }

		});
	cargaUsers();
    }

    private void cargaUsers() {
	/**
	 * a partir del dia 32-01-2008, se va a reemplazr la tabla employees por la tabla employees2.
	 * Es decir la tabla employees2 pasará a ser la tabla employees.
	 * */
	String Q = "";
	//String Q = " SELECT idpeople as id, user_name as dato FROM org.employees WHERE idpeople NOT IN " + " ( SELECT idpeople as id from pg_group, org.employees where idpeople = any(grolist) " + " AND groname = '" + grupo + "') AND employees.estado <> '*' ";
	//System.out.println("Q 1 --> "+ Q);
	usersList.getListFromQuery(Q);
	listUsers.setListData(usersList.getNombres());
	listUsers.setSelectedIndex(0);
	Q = "";
	//Q = "SELECT idpeople as id, user_name as dato from pg_group,org.employees where idpeople = any(grolist) " + " AND groname = '" + grupo + "' AND employees.estado <> '*'";
	//System.out.println("Q 2 --> "+ Q);
	usersSelectedList.getListFromQuery(Q);
	listUsersSelected.setListData(usersSelectedList.getNombres());
	listUsersSelected.setSelectedIndex(0);
    }

    private void listUsers_mouseClicked(MouseEvent e) {
	if ((e.getClickCount() == 2) && (listUsers.getSelectedIndex() >= 0)) {
	    usersList.swapItem(usersSelectedList, usersList.getIndexFromString(listUsers.getSelectedValue().toString()));
	    listUsers.setListData(usersList.getNombres());
	    listUsersSelected.setListData(usersSelectedList.getNombres());
	}
    }

    private void listUsersSelected_mouseClicked(MouseEvent e) {
	if ((e.getClickCount() == 2) && (listUsersSelected.getSelectedIndex() >= 0)) {
	    usersSelectedList.swapItem(usersList, usersSelectedList.getIndexFromString(listUsersSelected.getSelectedValue().toString()));
	    listUsersSelected.setListData(usersSelectedList.getNombres());
	    listUsers.setListData(usersList.getNombres());
	}
    }

    private void bAddUser_actionPerformed(ActionEvent e) {
	if (listUsers.getSelectedIndex() != -1) {
	    usersList.swapItem(usersSelectedList, usersList.getIndexFromString(listUsers.getSelectedValue().toString()));
	    listUsers.setListData(usersList.getNombres());
	    listUsersSelected.setListData(usersSelectedList.getNombres());
	}
    }

    private void bRemoveUser_actionPerformed(ActionEvent e) {
	if (listUsersSelected.getSelectedIndex() != -1) {
	    usersSelectedList.swapItem(usersList, usersSelectedList.getIndexFromString(listUsersSelected.getSelectedValue().toString()));
	    listUsers.setListData(usersList.getNombres());
	    listUsersSelected.setListData(usersSelectedList.getNombres());
	}
    }

    private void bAccept_actionPerformed(ActionEvent e) {
	Vector users = new Vector();
	users = usersSelectedList.getNombres();
	String consulta = "";
	if (users.size() != 0) {
	    /*** consulta para dar de alta a un usuario en el grupo seleccionado *********/
	    for (int i = 0; i < users.size(); i++) {
		//System.out.println("usuario: "+ users.get(i).toString());
		consulta = "GRANT " + grupo + " TO " + users.get(i).toString();
		LibSQL.exActualizar('a', consulta);
	    }
	    dispose();
	}
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void dispose() {
	switch (parentType) {
	    case DIALOG:
		((BasicDialog)parent).dispose();
		break;
	    case INTERNALFRAME:
		((BasicInternalFrame)parent).dispose();
		break;
	    case FRAME:
		((JFrame)parent).dispose();
		break;
	}
    }

}
