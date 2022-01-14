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
 * PanelUserToGroup.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.List;
import org.digitall.lib.components.basic.BasicButton;
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

public class PanelUserToGroup extends BasicContainerPanel {

    private BasicLabel lblUsers = new BasicLabel();
    private BasicLabel lblUsers1 = new BasicLabel();
    private BasicLabel lblUser = new BasicLabel();
    private BasicLabel lblUsuario = new BasicLabel();
    private BasicScrollPane spUsers = new BasicScrollPane();
    private BasicScrollPane spUsersSelected = new BasicScrollPane();
    private JList listGroups = new JList();
    private JList listGroupsSelected = new JList();
    private List groupsList = new List();
    private List groupsSelectedList = new List();
    private AddUserButton bAddUser = new AddUserButton();
    private DeleteButton bRemoveUser = new DeleteButton();
    private BasicButton bNewUser = new BasicButton();
    private AcceptButton bAceptar = new AcceptButton();
    private CloseButton bCancel = new CloseButton();
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private String iduser, usuario = "";

    public PanelUserToGroup(BasicInternalFrame _parent, String _iduser) {
	try {
	    iduser = _iduser;
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelUserToGroup(BasicDialog _parent, String _iduser) {
	try {
	    iduser = _iduser;
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelUserToGroup(JFrame _parent, String _iduser) {
	try {
	    iduser = _iduser;
	    parent = _parent;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(326, 273));
	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	spUsers.setBounds(new Rectangle(5, 45, 135, 175));
	//spUsers.getViewport().setLayout(null);
	listGroups.setBounds(new Rectangle(0, 0, 135, 175));
	listGroups.setSize(new Dimension(135, 175));
	listGroupsSelected.setBounds(new Rectangle(0, 0, 135, 175));
	lblUsers.setText("Groups:");
	lblUsers.setBounds(new Rectangle(5, 30, 135, 15));
	lblUsers.setFont(new Font("Dialog", 1, 11));
	spUsersSelected.setBounds(new Rectangle(185, 45, 135, 175));
	spUsersSelected.getViewport().setLayout(null);
	lblUsers1.setText("Groups Selected:");
	lblUsers1.setBounds(new Rectangle(185, 30, 135, 15));
	lblUsers1.setFont(new Font("Dialog", 1, 11));
	bAddUser.setBounds(new Rectangle(145, 85, 35, 30));
	bAddUser.setToolTipText("Add User");
	bAddUser.setMnemonic('A');
	bAddUser.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bAddUser_actionPerformed(e);
				}

			    }
	);
	bRemoveUser.setBounds(new Rectangle(145, 140, 35, 30));
	bRemoveUser.setToolTipText("Remove User");
	bRemoveUser.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       bRemoveUser_actionPerformed(e);
				   }

			       }
	);
	bNewUser.setBounds(new Rectangle(143, 240, 40, 25));
	bNewUser.setToolTipText("New User");
	bNewUser.setMnemonic('N');
	bNewUser.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bNewUser_actionPerformed(e);
				}

			    }
	);
	bAceptar.setBounds(new Rectangle(280, 240, 40, 25));
	bAceptar.setToolTipText("Add Users");
	bAceptar.setSize(new Dimension(40, 25));
	bAceptar.setMnemonic('A');
	bAceptar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bAceptar_actionPerformed(e);
				}

			    }
	);
	bCancel.setBounds(new Rectangle(5, 240, 40, 25));
	bCancel.setToolTipText("Cancel");
	bCancel.setMnemonic('C');
	bCancel.addActionListener(new ActionListener() {

			       public void actionPerformed(ActionEvent e) {
				   bCancel_actionPerformed(e);
			       }

			   }
	);
	//new Rectangle(5, 230, 320, 2));
	//setBounds(new Rectangle(5, 25, 320, 2));
	lblUser.setText("User:");
	lblUser.setBounds(new Rectangle(35, 5, 85, 15));
	lblUser.setFont(new Font("Dialog", 1, 13));
	lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
	lblUsuario.setBounds(new Rectangle(125, 5, 135, 15));
	lblUsuario.setFont(new Font("Dialog", 1, 13));
	lblUsuario.setForeground(Color.blue);
	this.add(lblUsuario, null);
	this.add(lblUser, null);
	//or2, null);
	this.add(bCancel, null);
	this.add(bAceptar, null);
	this.add(bNewUser, null);
	this.add(bRemoveUser, null);
	this.add(bAddUser, null);
	this.add(lblUsers1, null);
	spUsersSelected.getViewport().add(listGroupsSelected, null);
	this.add(spUsersSelected, null);
	this.add(lblUsers, null);
	spUsers.getViewport().add(listGroups, null);
	this.add(spUsers, null);
	listGroups.addMouseListener(new MouseAdapter() {

				 public void mouseClicked(MouseEvent e) {
				     listGroups_mouseClicked(e);
				 }

			     }
	);
	listGroupsSelected.addMouseListener(new MouseAdapter() {

					 public void mouseClicked(MouseEvent e) {
					     listGroupsSelected_mouseClicked(e);
					 }

				     }
	);
	loadData();
	lblUsuario.setText(usuario);
	cargaGroups();
    }

    private void cargaGroups() {
	String Q = "Select grosysid as id, groname as dato, grolist as lista From pg_group where " + " grosysid not in (Select grosysid From pg_group where " + iduser + " = any(grolist)) " + " and groname like 'cr%'";
	groupsList.getListFromQuery(Q);
	listGroups.setListData(groupsList.getNombres());
	listGroups.setSelectedIndex(0);
	Q = "SELECT grosysid as id, groname as dato FROM pg_group WHERE groname like 'cr%' " + " AND " + iduser + " = any(grolist)";
	groupsSelectedList.getListFromQuery(Q);
	listGroupsSelected.setListData(groupsSelectedList.getNombres());
	listGroupsSelected.setSelectedIndex(0);
    }

    private void listGroups_mouseClicked(MouseEvent e) {
	if ((e.getClickCount() == 2) && (listGroups.getSelectedIndex() >= 0)) {
	    groupsList.swapItem(groupsSelectedList, groupsList.getIndexFromString(listGroups.getSelectedValue().toString()));
	    listGroups.setListData(groupsList.getNombres());
	    listGroupsSelected.setListData(groupsSelectedList.getNombres());
	}
    }

    private void listGroupsSelected_mouseClicked(MouseEvent e) {
	if ((e.getClickCount() == 2) && (listGroupsSelected.getSelectedIndex() >= 0)) {
	    groupsSelectedList.swapItem(groupsList, groupsSelectedList.getIndexFromString(listGroupsSelected.getSelectedValue().toString()));
	    listGroupsSelected.setListData(groupsSelectedList.getNombres());
	    listGroups.setListData(groupsList.getNombres());
	}
    }

    private void bAddUser_actionPerformed(ActionEvent e) {
	if (listGroups.getSelectedIndex() != -1) {
	    groupsList.swapItem(groupsSelectedList, groupsList.getIndexFromString(listGroups.getSelectedValue().toString()));
	    listGroups.setListData(groupsList.getNombres());
	    listGroupsSelected.setListData(groupsSelectedList.getNombres());
	}
    }

    private void bRemoveUser_actionPerformed(ActionEvent e) {
	if (listGroupsSelected.getSelectedIndex() != -1) {
	    groupsSelectedList.swapItem(groupsList, groupsSelectedList.getIndexFromString(listGroupsSelected.getSelectedValue().toString()));
	    listGroups.setListData(groupsList.getNombres());
	    listGroupsSelected.setListData(groupsSelectedList.getNombres());
	}
    }

    public Vector getVector() {
	Vector vec = new Vector();
	vec = groupsSelectedList.getNombres();
	return vec;
    }

    private void bNewUser_actionPerformed(ActionEvent e) {
	Advisor.messageBox("Sorry, button under construction", "Message");
    }

    private void bAceptar_actionPerformed(ActionEvent e) {
	Vector vectorGrupos = new Vector();
	vectorGrupos = groupsSelectedList.getNombres();
	;
	String consulta = "";
	if (vectorGrupos.size() != 0) {
	    /*** consulta para dar de alta a un usuario en los grupos seleccionados *********/
	    boolean band = true;
	    int i = 0;
	    while ((i < vectorGrupos.size()) && (band)) {
		consulta = "GRANT " + vectorGrupos.elementAt(i).toString() + " TO " + usuario;
		//System.out.println("consulta: "+ consulta);
		if (LibSQL.exActualizar('a', consulta)) {
		    i++;
		} else
		    band = false;
	    }
	    if (band) {
		Advisor.messageBox("El usuario fue agregado a los grupos seleccionados", "Aviso");
		dispose();
	    } else
		Advisor.messageBox("No se pudo agregar el usuario a los grupos seleccionados", "Error");
	    /*for (int i=0; i < vectorGrupos.size();i++){
                consulta = "GRANT "+ vectorGrupos.elementAt(i).toString()  +" TO "+ usuario;
                //System.out.println("consulta: "+ consulta);
                LibSQL.exActualizar('a',consulta);
            }*/
	    //this.dispose();
	}
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void dispose() {
	switch (parentType) {
	    case DIALOG :
		((BasicDialog)parent).dispose();
		break;
	    case INTERNALFRAME :
		((BasicInternalFrame)parent).dispose();
		break;
	    case FRAME :
		((JFrame)parent).dispose();
		break;
	}
    }

    private void loadData() {
	usuario = LibSQL.getCampo("SELECT usename FROM pg_user WHERE usesysid = " + iduser);
    }

}
