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
 * PanelCreateUser.java
 *
 * */
package org.digitall.apps.systemmanager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCheckBox;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPasswordField;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.sql.LibSQL;

//

public class PanelCreateUser extends BasicContainerPanel {

    private BasicLabel lblSurname = new BasicLabel();
    private BasicLabel lblName = new BasicLabel();
    private BasicLabel lblUser = new BasicLabel();
    private BasicLabel jLabel1 = new BasicLabel();
    private BasicLabel lblReTypePass = new BasicLabel();
    private JEntry tfLastName = new JEntry();
    private JEntry tfFirstName = new JEntry();
    private JEntry tfUser = new JEntry();
    private BasicPasswordField pfPassword = new BasicPasswordField();
    private BasicPasswordField pfRePassword = new BasicPasswordField();
    private BasicButton bMoreData = new BasicButton();
    private AcceptButton btnAccept = new AcceptButton();
    private CloseButton bCancel = new CloseButton();
     
    private BasicCheckBox cbLoginAllowed = new BasicCheckBox();
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private int error = 0;
    private String group, insertUser, insertUserToTable, idUser, userName;
    private int tecla = 0;
    private char[] letrasPerm = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    static Set teclasPermitidas = new HashSet();
    private Set letrasPermitidas = new HashSet();
    static Set teclas = new HashSet();
    private Vector v = new Vector();

    public PanelCreateUser(BasicInternalFrame _parent) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelCreateUser(BasicDialog _parent) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelCreateUser(JFrame _parent) {
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
	this.setSize(new Dimension(392, 158));
	lblSurname.setText("Last Name:");
	lblSurname.setBounds(new Rectangle(10, 5, 145, 10));
	lblSurname.setFont(new Font("Dialog", 1, 11));
	tfLastName.setBounds(new Rectangle(10, 15, 145, 15));
	tfLastName.setFont(new Font("Dialog", 1, 11));
	tfLastName.addKeyListener(new KeyAdapter() {

		    public void keyTyped(KeyEvent e) {
			tfLastName_keyTyped(e);
		    }

		    public void keyReleased(KeyEvent e) {
			tfLastName_keyReleased(e);
		    }

		});
	tfFirstName.setBounds(new Rectangle(165, 15, 215, 15));
	tfFirstName.addKeyListener(new KeyAdapter() {

		    public void keyTyped(KeyEvent e) {
			tfFirstName_keyTyped(e);
		    }

		    public void keyReleased(KeyEvent e) {
			tfFirstName_keyReleased(e);
		    }

		});
	tfUser.setBounds(new Rectangle(10, 50, 145, 15));
	pfPassword.setBounds(new Rectangle(10, 90, 145, 15));
	pfRePassword.setBounds(new Rectangle(165, 90, 140, 15));
	lblName.setText("First Name:");
	lblName.setBounds(new Rectangle(165, 5, 215, 10));
	lblName.setFont(new Font("Dialog", 1, 11));
	lblUser.setText("User:");
	lblUser.setBounds(new Rectangle(10, 40, 140, 10));
	lblUser.setFont(new Font("Dialog", 1, 11));
	jLabel1.setText("Password:");
	jLabel1.setBounds(new Rectangle(10, 80, 140, 10));
	jLabel1.setFont(new Font("Dialog", 1, 11));
	lblReTypePass.setText("Re-type Password:");
	lblReTypePass.setBounds(new Rectangle(165, 80, 140, 10));
	lblReTypePass.setFont(new Font("Dialog", 1, 11));
	bMoreData.setBounds(new Rectangle(146, 120, 100, 20));
	bMoreData.setText("More data");
	bMoreData.setMnemonic('M');
	bMoreData.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bMoreData_actionPerformed(e);
		    }

		});
	bMoreData.setVisible(false);
	//new Rectangle(0, 115, 390, 5));
	cbLoginAllowed.setText("Login allowed");
	cbLoginAllowed.setBounds(new Rectangle(165, 50, 145, 15));
	cbLoginAllowed.setFont(new Font("Dialog", 1, 11));
	btnAccept.setBounds(new Rectangle(340, 125, 40, 25));
	btnAccept.setSize(new Dimension(40, 25));
	btnAccept.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnAccept_actionPerformed(e);
		    }

		});
	bCancel.setBounds(new Rectangle(10, 125, 40, 25));
	bCancel.setMnemonic('C');
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	this.add(bCancel, null);
	this.add(btnAccept, null);
	this.add(cbLoginAllowed, null);
	 
	this.add(bMoreData, null);
	this.add(lblReTypePass, null);
	this.add(jLabel1, null);
	this.add(lblUser, null);
	this.add(lblName, null);
	this.add(pfRePassword, null);
	this.add(pfPassword, null);
	this.add(tfUser, null);
	this.add(tfFirstName, null);
	this.add(tfLastName, null);
	this.add(lblSurname, null);
	cbLoginAllowed.setSelected(true);
	setearTeclasMinusculas();
    }

    private void setearTeclasMinusculas() {
	for (int i = 0; i < letrasPerm.length; i++) {
	    letrasPermitidas.add("" + letrasPerm[i]);
	}
    }

    public void setGroup(String _group) {
	group = _group;
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

    private void bCancel_actionPerformed(ActionEvent e) {
	dispose();
    }

    private String makeInsertUser() {
	String filtroLogin, filtroGrupo = "";
	if (cbLoginAllowed.isSelected()) {
	    filtroLogin = " LOGIN ";
	} else {
	    filtroLogin = " NOLOGIN ";
	}
	if (group.equals("")) {
	    filtroGrupo = "";
	} else {
	    filtroGrupo = " ; GRANT " + group + " TO " + tfUser.getText().trim() + " ;";
	}
	return "CREATE USER " + tfUser.getText().trim() + filtroLogin + " ENCRYPTED PASSWORD '" + new String(pfPassword.getPassword()) + "' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE " + filtroGrupo;
	//        System.out.println("insertUser --> "+insertUser);
    }

    private void makeInsertUserToTable() {
	//--> arreglado: antes estaba como: From org.addresses2
	String insertAddress = "INSERT INTO org.addresses VALUES((Select max(idaddress) + 1 From org.addresses),0,0,0,0,0,00,'','',00,'','00','','00','','','','','',0,'00:00:00',0,1,'',null,'')";
	LibSQL.exActualizar('a', insertAddress);
	LibSQL.exActualizar('a', insertAddress);
	String consultaIdUser = "SELECT usesysid FROM pg_user WHERE usename = '" + tfUser.getText().trim() + "' ";
	idUser = org.digitall.lib.sql.LibSQL.getCampo(consultaIdUser);
	String fechaRegistro = org.digitall.lib.sql.LibSQL.getCampo("select current_timestamp");
	/** 
	 * Cesar: 	
	 * 	hay que dar de alta al usuario en la tabla Personas, y luego relacionar
	 * 	las direcciones que se dieron de alta en la tabla PersonAddresses, para
	 *      finalmente agregarlo en la tabla employees (Tener en cuenta que la insercion 
	 *      en la tabla employees hay que arreglarla para que funcione con la nueva estructura
	 *      de la tabla employees
	 * */
	//insertUserToTable = "INSERT INTO org.employees  VALUES(" + idUser + ",0,0,0,'" + tfLastName.getText().trim() + "', '" + tfFirstName.getText().trim() + "'," + " '" + tfUser.getText() + "', 'email',0,0,1000000,0,0,0,0,NULL,'" + fechaRegistro + "',NULL,NULL,0,0" + idAddress1 + ",0" + idAddress2 + ",NULL,'','',0,'')";
	//         System.out.println("insertUserToTable --> "+insertUserToTable);
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	//System.out.println("tamanio: " + control());
	if (control()) {
	    String insertUser = makeInsertUser();
	    if (LibSQL.exActualizar('a', insertUser)) {
		makeInsertUserToTable();
		if (LibSQL.exActualizar('a', insertUserToTable)) {
		    if (Advisor.question("Message", "Do you want to add personal data for this user?")) {
			PersonalData datosPersonales = new PersonalData(idUser);
			datosPersonales.setModal(true);
			datosPersonales.setVisible(true);
		    }
		    if (Advisor.question("Message", "Do you want to select groups for this user?")) {
			UserToGroup usuarioGrupo = new UserToGroup(idUser);
			usuarioGrupo.setModal(true);
			usuarioGrupo.setVisible(true);
		    }
		    dispose();
		}
	    }
	} else {
	    errores();
	}
    }

    private boolean control() {
	boolean valido = true;
	String consulta = "Select count(*) from pg_user where usename = '" + tfUser.getText().trim() + "'";
	String id = org.digitall.lib.sql.LibSQL.getCampo(consulta);
	if (tfLastName.getText().trim().equals("")) {
	    error = 1;
	    return false;
	} else if (tfFirstName.getText().trim().equals("")) {
	    error = 2;
	    return false;
	} else if (tfUser.getText().trim().equals("")) {
	    error = 3;
	    return false;
	} else if (!id.equals("0")) {
	    error = 4;
	    return false;
	} else if (!comparaPassword()) {
	    return false;
	} else
	    return true;
	//System.out.println("pws[1].length--> "+ pws.length);
	//System.out.println("re-pws.length --> "+ repws.length);
    }

    private boolean comparaPassword() {
	String pwd = new String(pfPassword.getPassword());
	String repwd = new String(pfRePassword.getPassword());
	if (pwd.trim().length() < 6) {
	    error = 5;
	    return false;
	} else if (!pwd.equals(repwd)) {
	    error = 6;
	    return false;
	} else
	    return true;
    }

    private void bMoreData_actionPerformed(ActionEvent e) {
	UserToGroup usuarioGrupo = new UserToGroup("195377");
	usuarioGrupo.setModal(true);
	usuarioGrupo.setVisible(true);
    }

    private void errores() {
	switch (error) {
	    case 1:
		org.digitall.lib.components.Advisor.messageBox("Last name required", "Error");
		break;
	    case 2:
		org.digitall.lib.components.Advisor.messageBox("First name required", "Error");
		break;
	    case 3:
		org.digitall.lib.components.Advisor.messageBox("User name required", "Error");
		break;
	    case 4:
		org.digitall.lib.components.Advisor.messageBox("User name already exists, please type other", "Error");
		break;
	    case 5:
		org.digitall.lib.components.Advisor.messageBox("Password must be greather than 5 characters", "Error");
		break;
	    case 6:
		org.digitall.lib.components.Advisor.messageBox("Passwords don't match", "Error");
		break;
	}
    }

    private void sugiereNombre() {
	String apellido = tfLastName.getText().trim().toLowerCase().replaceAll(" ", "");
	apellido = apellido.replaceAll("\'", "").replaceAll("'", "");
	apellido = apellido.replaceAll("ï¿½", "a").replaceAll("ï¿½", "e").replaceAll("ï¿½", "i").replaceAll("ï¿½", "o").replaceAll("ï¿½", "u").replaceAll("ï¿½", "n").replaceAll("ï¿½", "u");
	char[] apel = apellido.toCharArray();
	apellido = "";
	for (int i = 0; i < apel.length; i++) {
	    if (letrasPermitidas.contains("" + apel[i])) {
		apellido += apel[i];
	    }
	}
	String nombre = tfFirstName.getText().trim().toLowerCase();
	if (nombre.length() > 1) {
	    nombre = nombre.substring(0, 1);
	}
	tfUser.setText(apellido + nombre);
    }

    private void tfLastName_keyTyped(KeyEvent e) {
	//sugiereNombre();
    }

    private void tfFirstName_keyTyped(KeyEvent e) {
	//sugiereNombre();
    }

    private void tfLastName_keyReleased(KeyEvent e) {
	sugiereNombre();
    }

    private void tfFirstName_keyReleased(KeyEvent e) {
	sugiereNombre();
    }

}
