package org.digitall.common.legalprocs.manager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JList;

import org.digitall.lib.components.List;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddUserButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.icons.IconTypes;

//

public class ContactsPanel extends BasicContainerPanel {

    private BasicPanel jpContacts = new BasicPanel();
    private BasicScrollPane jspUsers = new BasicScrollPane();
    private BasicScrollPane jspUserSelected = new BasicScrollPane();
    private DeleteButton bRemoveUser = new DeleteButton();
    private AddUserButton bAddUser = new AddUserButton();
    private BasicLabel jLabel1 = new BasicLabel();
    private BasicLabel jLabel2 = new BasicLabel();
    private CloseButton btnClose = new CloseButton();
    private AcceptButton btnAccept = new AcceptButton();
    private JList listUsers = new JList();
    private JList listUsersSelected = new JList();
    private List usersList = new List();
    private List usersSelectedList = new List();
    private String grupo = "";
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private String appName = "";
    private BasicLabel lblTitulo = new BasicLabel();
    private BasicPanel jPanel1 = new BasicPanel();
    private BasicLabel lblTitulo1 = new BasicLabel();
    private BasicLabel lblFile;
    private int color = 0;

    public ContactsPanel(BasicDialog _parent, String _appName, int _color) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    appName = _appName;
	    color = _color;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(408, 334));
	jpContacts.setBounds(new Rectangle(5, 65, 395, 225));
	jpContacts.setLayout(null);
	jpContacts.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jspUsers.setBounds(new Rectangle(10, 30, 160, 180));
	jspUsers.setSize(new Dimension(160, 180));
	jspUsers.getViewport().setLayout(null);
	jspUserSelected.setBounds(new Rectangle(225, 30, 160, 180));
	listUsers.setBounds(new Rectangle(0, 0, 160, 180));
	bRemoveUser.setBounds(new Rectangle(180, 120, 35, 30));
	bRemoveUser.setToolTipText("Remove User");
	bRemoveUser.addActionListener(new ActionListener() {

				   public void actionPerformed(ActionEvent e) {
				       bRemoveUser_actionPerformed(e);
				   }

			       }
	);
	bAddUser.setBounds(new Rectangle(180, 65, 35, 30));
	bAddUser.setToolTipText("Add User");
	bAddUser.setMnemonic('A');
	bAddUser.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    bAddUser_actionPerformed(e);
				}

			    }
	);
	jLabel1.setText("Usuarios");
	jLabel1.setBounds(new Rectangle(10, 15, 155, 15));
	jLabel1.setFont(new Font("Default", 1, 11));
	jLabel2.setText("Usuarios Seleccionados");
	jLabel2.setBounds(new Rectangle(225, 15, 160, 15));
	jLabel2.setFont(new Font("Default", 1, 11));
	btnClose.setBounds(new Rectangle(10, 295, 40, 25));
	btnClose.setSize(new Dimension(40, 25));
	btnClose.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
				    btnClose_actionPerformed(e);
				}

			    }
	);
	btnAccept.setBounds(new Rectangle(360, 295, 40, 25));
	btnAccept.setSize(new Dimension(40, 25));
	jpContacts.add(jLabel1, null);
	jspUserSelected.getViewport().add(listUsersSelected, null);
	jpContacts.add(jspUserSelected, null);
	jspUsers.getViewport().add(listUsers, null);
	jpContacts.add(jspUsers, null);
	jpContacts.add(bAddUser, null);
	jpContacts.add(bRemoveUser, null);
	jpContacts.add(jLabel2, null);
	jPanel1.add(lblTitulo1, null);
	jPanel1.add(lblTitulo, null);
	this.add(jPanel1, null);
	this.add(btnAccept, null);
	this.add(btnClose, null);
	this.add(jpContacts, null);
	listUsers.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
				    listUsers_mouseClicked(e);
				}

			    }
	);
	listUsersSelected.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent e) {
					    listUsersSelected_mouseClicked(e);
					}

				    }
	);
	lblTitulo.setBounds(new Rectangle(10, 0, 380, 15));
	lblTitulo.setText("Evento:" + appName);
	lblTitulo.setFont(new Font("Default", 1, 11));
	jPanel1.setBounds(new Rectangle(5, 5, 395, 50));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	lblTitulo1.setBounds(new Rectangle(10, 25, 70, 15));
	lblTitulo1.setText("Prioridad: ");
	lblTitulo1.setFont(new Font("Default", 1, 11));
	if (color == 1) {
	    lblFile = new BasicLabel(IconTypes.CRFileBlack);
	} else if (color == 2) {
	    lblFile = new BasicLabel(IconTypes.CRFileRed);
	} else if (color == 3) {
	    lblFile = new BasicLabel(IconTypes.CRFileYellow);
	} else if (color == 4) {
	    lblFile = new BasicLabel(IconTypes.CRFileGreen);
	} else if (color == 5) {
	    lblFile = new BasicLabel(IconTypes.CRFileBlue);
	}
	lblFile.setBounds(new Rectangle(85, 20, 25, 25));
	jPanel1.add(lblFile, null);
	cargaUsers();
    }

    private void dispose() {
	switch (parentType) {
	    case DIALOG :
		((BasicDialog)parent).dispose();
		break;
	}
    }

    private void cargaUsers() {
	String Q = " SELECT idpeople as id, user_name as dato FROM org.employees WHERE idpeople NOT IN " + " ( SELECT idpeople as id from pg_group, org.employees where idpeople = any(grolist) " + " AND groname = '" + grupo + "') AND employees.estado <> '*' ";
	//System.out.println("Q 1 --> "+ Q);
	usersList.getListFromQuery(Q);
	listUsers.setListData(usersList.getNombres());
	listUsers.setSelectedIndex(0);
	Q = "SELECT idpeople as id, user_name as dato from pg_group,org.employees where idpeople = any(grolist) " + " AND groname = '" + grupo + "' AND employees.estado <> '*'";
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

    private void btnClose_actionPerformed(ActionEvent e) {
	dispose();
    }

}
