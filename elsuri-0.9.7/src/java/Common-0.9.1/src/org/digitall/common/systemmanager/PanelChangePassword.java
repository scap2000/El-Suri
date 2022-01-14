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
 * PanelChangePassword.java
 *
 * */
package org.digitall.common.systemmanager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        pfPassword.addKeyListener(new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if(e.getKeyChar()==KeyEvent.VK_ENTER){
                            pfRePassword.requestFocus();
                        } else if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                            dispose();
                        }
                    }
                                                                                                                                             
                    @Override
                    public void keyPressed(KeyEvent e) {
                        //Do Nothing
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        //Do Nothing
                    }

            });

        pfRePassword.addKeyListener(new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if(e.getKeyChar()==KeyEvent.VK_ENTER){
                            changePassword();
                        } else if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                            dispose();
                        }
                    }
                                                                                                                                             
                    @Override
                    public void keyPressed(KeyEvent e) {
                        //Do Nothing
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        //Do Nothing
                    }

            });

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
        this.setFocusable(true);
        this.addFocusListener(new FocusListener() {

                public void focusGained(FocusEvent focusEvent) {
                    pfPassword.requestFocus();
                }

                public void focusLost(FocusEvent focusEvent) {
                }
            });
	setData();
    }

    private void setData() {
	tfUserName.setText(userName);
        pfPassword.requestFocus();
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
        changePassword();
    }
    
    private void changePassword() {
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
