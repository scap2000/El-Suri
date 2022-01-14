package org.digitall.common.systemmanager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JOutry;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicPasswordField;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PanelChangePassword extends BasicPanel {

    private BasicPasswordField pfRePassword = new BasicPasswordField();
    private BasicPasswordField pfPassword = new BasicPasswordField();
    private BasicLabel lblPass = new BasicLabel();
    private BasicLabel lblUser = new BasicLabel();
    private BasicLabel lblReTypePass = new BasicLabel();
    private JOutry tfUserName = new JOutry();
    private AcceptButton bAccept = new AcceptButton();
    private CloseButton bCancel = new CloseButton();
     
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private final int PWDMATCH = 1;
    private final int PWDSIZE = 2;
    private final int pwdMinSize = 6;
    private int localError = 0;
    private String userName = "";

    public PanelChangePassword(BasicInternalFrame _parent, String _userName) {
	try {
	    parent = _parent;
	    userName = _userName;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelChangePassword(BasicDialog _parent, String _userName) {
	try {
	    parent = _parent;
	    userName = _userName;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelChangePassword(JFrame _parent, String _userName) {
	try {
	    parent = _parent;
	    userName = _userName;
	    parentType = FRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setLayout(null);
	this.setSize(new Dimension(312, 124));
	pfRePassword.setBounds(new Rectangle(165, 50, 140, 20));
	pfPassword.setBounds(new Rectangle(10, 50, 145, 20));
	lblPass.setText("Clave:");
	lblPass.setBounds(new Rectangle(10, 40, 140, 10));
	lblPass.setFont(new Font("Dialog", 1, 11));
	lblUser.setText("Usuario:");
	lblUser.setBounds(new Rectangle(85, 5, 145, 10));
	lblUser.setFont(new Font("Dialog", 1, 11));
	lblReTypePass.setText("Confirmación:");
	lblReTypePass.setBounds(new Rectangle(165, 40, 140, 10));
	lblReTypePass.setFont(new Font("Dialog", 1, 11));
	tfUserName.setBounds(new Rectangle(85, 15, 145, 20));
	bAccept.setBounds(new Rectangle(265, 85, 40, 25));
	bAccept.setSize(new Dimension(40, 25));
	bAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAccept_actionPerformed(e);
		    }

		});
	bCancel.setBounds(new Rectangle(5, 85, 40, 25));
	bCancel.setMnemonic('C');
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	 
	this.add(bCancel, null);
	this.add(bAccept, null);
	this.add(tfUserName, null);
	this.add(pfRePassword, null);
	this.add(pfPassword, null);
	this.add(lblPass, null);
	this.add(lblUser, null);
	this.add(lblReTypePass, null);
	this.add(lblUser, null);
	setData();
    }

    private void setData() {
	tfUserName.setText(userName);
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

    private void bAccept_actionPerformed(ActionEvent e) {
	if (comparePassword()) {
	    //String passUpdate = "ALTER USER " + tfUserName.getText().trim() + " WITH ENCRYPTED PASSWORD '" + LibSQL.getMD5(new String(pfPassword.getPassword())) + "';";
	    String passUpdate = "";
	    if (tfUserName.getText().trim().equals(Environment.sessionUser)) {
		if (Environment.sessionUser.equals("admin")) {
		    passUpdate = "SELECT setpassword('" + new String(pfPassword.getPassword()) + "');";
		} else {
		    passUpdate = "SELECT setpassword('" + new String(pfPassword.getPassword()) + "');";
		}
	    } else {
		passUpdate = "SELECT setpassword('" + tfUserName.getText() + "','" + new String(pfPassword.getPassword()) + "');";
	    }
	    ResultSet chgPasswd = LibSQL.exQuery(passUpdate);
	    try {
		if (chgPasswd.next()) {
		    if (chgPasswd.getBoolean("setpassword")) {
			Advisor.messageBox("Contraseña cambiada", "Message");
			dispose();
		    } else {
			Advisor.messageBox("<html><u>Error</u>: Contraseña no cambiada</html>", "Error");
		    }
		}
	    } catch (SQLException f) {
		// TODO
		f.printStackTrace();
	    }
	} else {
	    showError();
	}
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	dispose();
    }

    private boolean comparePassword() {
	String pwd = new String(pfPassword.getPassword());
	String repwd = new String(pfRePassword.getPassword());
	if (pwd.trim().length() < pwdMinSize) {
	    localError = PWDSIZE;
	    return false;
	} else if (!pwd.equals(repwd)) {
	    localError = PWDMATCH;
	    return false;
	} else
	    return true;
    }

    private void showError() {
	switch (localError) {
	    case PWDSIZE:
		Advisor.messageBox("La contraseña debe tener " + pwdMinSize + " o más caracteres", "Error");
		break;
	    case PWDMATCH:
		Advisor.messageBox("Las contraseñas no coinciden", "Error");
		break;
	}
    }

}
