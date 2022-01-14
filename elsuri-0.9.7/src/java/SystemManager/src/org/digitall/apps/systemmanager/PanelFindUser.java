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
 * PanelFindUser.java
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.Grilla;
import org.digitall.lib.components.JEntry;
import org.digitall.lib.components.basic.BasicContainerPanel;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicInternalFrame;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddUserButton;
import org.digitall.lib.components.buttons.CloseButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.FindButton;
import org.digitall.lib.sql.LibSQL;

//

public class PanelFindUser extends BasicContainerPanel {

    private BasicPanel panelFind = new BasicPanel();
    private BasicLabel lblLastName = new BasicLabel();
    private BasicLabel lblfirstName = new BasicLabel();
    private BasicLabel lblFinPanel = new BasicLabel();
    private BasicLabel lblResults = new BasicLabel();
    private FindButton bFind = new FindButton();
    private AcceptButton bUpdateData = new AcceptButton();
    private DeleteButton bDelUser = new DeleteButton();
    private CloseButton bCancel = new CloseButton();
    private AddUserButton btnNewUser = new AddUserButton();
    private JEntry tfLastName = new JEntry();
    private JEntry tfFirstName = new JEntry();
     
    private int Liminf = 0, CantReg1 = 200;
    private int[] tcol = { };
    private int[] tamcol = { 218, 150 };
    private Grilla jgUsuarios = new Grilla(CantReg1, tcol, tamcol, false, false);
    private Vector cabecera = new Vector();
    private Vector datos = new Vector();
    private String Consulta, ConsultaCount = "";
    private final int FRAME = 1;
    private final int INTERNALFRAME = 2;
    private final int DIALOG = 3;
    private int parentType = -1;
    private Component parent;
    private BasicPanel jPanel1 = new BasicPanel();

    public PanelFindUser(BasicInternalFrame _parent) {
	try {
	    parent = _parent;
	    parentType = INTERNALFRAME;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelFindUser(BasicDialog _parent) {
	try {
	    parent = _parent;
	    parentType = DIALOG;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public PanelFindUser(JFrame _parent) {
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
	this.setSize(new Dimension(453, 311));
	panelFind.setBounds(new Rectangle(5, 10, 440, 55));
	panelFind.setLayout(null);
	panelFind.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	bFind.setBounds(new Rectangle(380, 15, 40, 25));
	bFind.setMnemonic('F');
	bFind.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bFind_actionPerformed(e);
		    }

		});
	tfLastName.setBounds(new Rectangle(25, 25, 130, 15));
	tfLastName.setFont(new Font("Dialog", 1, 11));
	tfLastName.setForeground(Color.blue);
	tfFirstName.setBounds(new Rectangle(190, 25, 160, 15));
	tfFirstName.setFont(new Font("Dialog", 1, 11));
	tfFirstName.setForeground(Color.blue);
	jgUsuarios.setBounds(new Rectangle(3, 5, 435, 170));
	btnNewUser.setBounds(new Rectangle(305, 280, 40, 25));
	btnNewUser.setMnemonic('N');
	btnNewUser.setFont(new Font("Dialog", 1, 11));
	btnNewUser.setToolTipText("Create New User");
	btnNewUser.setSize(new Dimension(40, 25));
	btnNewUser.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnNewUser_actionPerformed(e);
		    }

		});
	lblResults.setText(" Results");
	lblResults.setBounds(new Rectangle(15, 75, 50, 10));
	lblResults.setFont(new Font("Dialog", 1, 11));
	lblResults.setOpaque(true);
	//new Rectangle(0, 270, 450, 5));
	lblFinPanel.setText(" Find panel");
	lblFinPanel.setBounds(new Rectangle(15, 5, 70, 10));
	lblFinPanel.setFont(new Font("Dialog", 1, 11));
	lblFinPanel.setOpaque(true);
	bUpdateData.setBounds(new Rectangle(355, 280, 40, 25));
	bUpdateData.setMnemonic('U');
	bUpdateData.setFont(new Font("Dialog", 1, 11));
	bUpdateData.setToolTipText("Update personal data");
	bUpdateData.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bUpdateData_actionPerformed(e);
		    }

		});
	bCancel.setBounds(new Rectangle(5, 280, 40, 25));
	bCancel.setMnemonic('C');
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	lblLastName.setText("Last Name:");
	lblLastName.setBounds(new Rectangle(25, 15, 130, 10));
	lblLastName.setFont(new Font("Dialog", 1, 11));
	lblfirstName.setText("First Name:");
	lblfirstName.setBounds(new Rectangle(190, 15, 160, 10));
	lblfirstName.setFont(new Font("Dialog", 1, 11));
	panelFind.add(lblLastName, null);
	panelFind.add(tfFirstName, null);
	panelFind.add(tfLastName, null);
	panelFind.add(bFind, null);
	panelFind.add(lblfirstName, null);
	this.add(bDelUser, null);
	this.add(lblResults, null);
	jPanel1.add(jgUsuarios, null);
	this.add(jPanel1, null);
	this.add(lblFinPanel, null);
	this.add(btnNewUser, null);
	 
	this.add(bUpdateData, null);
	this.add(panelFind, null);
	this.add(bCancel, null);
	jgUsuarios.Redimensiona();
	jgUsuarios.getTable().addKeyListener(new KeyAdapter() {

		    public void keyPressed(KeyEvent e) {
			jgUsuarios_keyTyped(e);
		    }

		    public void keyReleased(KeyEvent e) {
			jgUsuarios_keyTyped(e);
		    }

		    public void keyTyped(KeyEvent e) {
			jgUsuarios_keyTyped(e);
		    }

		});
	/**
        * Accion que se dispara cuando se hace "UN CLICK" en la grilla
        * */
	jgUsuarios.getTable().addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			    if (datos != jgUsuarios.VDatos()) {
				datos = jgUsuarios.VDatos();
				habilitaBotones(true);
				//setearGroups();
			    }
			}
		    }

		});
	/**
        * Accion que se dispara cuando se hace "DOBLE CLICK" en la grilla
        * */
	jgUsuarios.getTable().addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    datos = jgUsuarios.VDatos();
			    PersonalData datosPersonales = new PersonalData(datos.elementAt(0).toString());
			    datosPersonales.setModal(true);
			    datosPersonales.setVisible(true);
			}
		    }

		});
	jgUsuarios.setSpanishLanguage(false);
	jPanel1.setBounds(new Rectangle(5, 80, 440, 180));
	jPanel1.setLayout(null);
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	bDelUser.setBounds(new Rectangle(405, 280, 40, 25));
	bDelUser.setMnemonic('U');
	bDelUser.setFont(new Font("Dialog", 1, 11));
	bDelUser.setToolTipText("Update personal data");
	bDelUser.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bDelUser_actionPerformed(e);
		    }

		});
	setearCabecera();
	actualizaTabla();
	habilitaBotones(false);
    }

    private void habilitaBotones(boolean _valor) {
	bUpdateData.setEnabled(_valor);
	bDelUser.setEnabled(_valor);
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

    private void setearCabecera() {
	cabecera.removeAllElements();
	cabecera.addElement("*");
	cabecera.addElement("Name");
	cabecera.addElement("User Name");
	//cabecera.addElement("Date");
    }

    private void actualizaTabla() {
	armarConsulta();
	ConsultaCount = "SELECT count(*) FROM (" + Consulta + ") as mat";
	jgUsuarios.ActualizaTabla((BasicPanel)this, Consulta, ConsultaCount, cabecera);
	habilitaBotones(false);
	//System.out.println("Consulta Count --> "+ConsultaCount);
	//System.out.println("Consulta FrmFrenteObras --> "+Consulta);
    }

    private void armarConsulta() {
	
	/**
	 * a partir del dia 32-01-2008, se va a reemplazr la tabla employees por la tabla employees2.
	 * Es decir la tabla employees2 pasará a ser la tabla employees.
	 * */
	 String ConsultaInicial = "";
	//String ConsultaInicial = "SELECT idpeople, (initcap(last_name) || ',' || initcap(first_name)) as nombre, user_name FROM org.employees WHERE idpeople > 0 AND estado <> '*' ";
	 
	String filtroLastName = "";
	String filtroFirstName = "";
	if (!tfLastName.getText().trim().equals("")) {
	    filtroLastName = "AND lower(last_name) like '%" + tfLastName.getText().trim().toLowerCase() + "%' ";
	} else
	    filtroLastName = "";
	if (tfFirstName.getText().trim().equals("")) {
	    filtroFirstName = "AND lower(first_name) like '%" + tfFirstName.getText().trim().toLowerCase() + "%' ";
	} else
	    filtroFirstName = "";
	String OrderBy = " ORDER BY nombre";
	Consulta = ConsultaInicial + filtroLastName + filtroFirstName + OrderBy;
	//System.out.println("Consulta --> "+Consulta);
    }

    private void jgUsuarios_keyTyped(KeyEvent e) {
	try {
	    if (datos != jgUsuarios.VDatos()) {
		datos = jgUsuarios.VDatos();
		habilitaBotones(true);
	    }
	} catch (ArrayIndexOutOfBoundsException x) {
	    e.consume();
	}
    }

    private void bFind_actionPerformed(ActionEvent e) {
	actualizaTabla();
    }

    private void bUpdateData_actionPerformed(ActionEvent e) {
	PersonalData datosPersonales = new PersonalData(datos.elementAt(0).toString());
	datosPersonales.setModal(true);
	datosPersonales.setVisible(true);
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	dispose();
    }

    private void btnNewUser_actionPerformed(ActionEvent e) {
	CreateUser createUser = new CreateUser("");
	createUser.setModal(true);
	createUser.setVisible(true);
	actualizaTabla();
    }

    private void bDelUser_actionPerformed(ActionEvent e) {
	String user = datos.elementAt(1).toString();
	if (Advisor.question("Warning!", "Are you sure to delete the user \"" + user + "\" permanently from the whole system?")) {
	    if (Advisor.question("Warning!", "Are you really sure about what are you doing? This action is irreversible!")) {
		org.digitall.lib.components.Advisor.messageBox("Ok, I will delete the user \"" + user + "\" from the database engine", "Operation confirmed");
		String consulta = "DELETE from org.employees WHERE idpeople = " + datos.elementAt(0).toString() + ";";
		consulta += "DROP USER " + datos.elementAt(2).toString();
		if (LibSQL.exActualizar('a', consulta)) {
		    org.digitall.lib.components.Advisor.messageBox("The user \"" + user + "\" was totally deleted from the database engine", "Successful!");
		} else {
		    org.digitall.lib.components.Advisor.messageBox("Error:  I can't delete \"" + user + "\", maybe other users depends on him... Sorry", "Error");
		}
	    }
	}
	actualizaTabla();
    }

}
