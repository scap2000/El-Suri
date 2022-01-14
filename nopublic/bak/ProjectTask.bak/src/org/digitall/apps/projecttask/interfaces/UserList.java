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
