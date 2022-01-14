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
 * Systems.java
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

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.Grilla;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicScrollPane;
import org.digitall.lib.components.buttons.AcceptButton;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.AddUserButton;
import org.digitall.lib.components.buttons.DeleteButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class Systems extends BasicDialog {

    private BasicPanel jPanel1 = new BasicPanel();
    private BasicPanel PanelSistemas = new BasicPanel();
    private BasicLabel lblGroups = new BasicLabel();
    private BasicLabel lblUsers = new BasicLabel();
    private BasicLabel lblSystems = new BasicLabel();
    private BasicLabel lblGroups1 = new BasicLabel();
    private AddUserButton bAddGroup = new AddUserButton();
    private AddUserButton bNewUser = new AddUserButton();
    private AcceptButton bCancel = new AcceptButton();
    private ModifyButton bAddUserData = new ModifyButton();
    private AddUserButton bAddUser = new AddUserButton();
    private DeleteButton bDelGroup = new DeleteButton();
    private DeleteButton bDelUser = new DeleteButton();
    private AddButton bAddTables = new AddButton();
    private BasicScrollPane spGroups = new BasicScrollPane();
    private BasicScrollPane spUsers = new BasicScrollPane();
    private JList listGroups = new JList();
    private JList listUsers = new JList();
    private Vector vectorGroups = new Vector();
    private Vector vectorUsers = new Vector();
     
    private int Liminf = 0, CantReg1 = 200;
    private int[] tcol = { };
    private int[] tamcol = { 100, 350 };
    private Grilla jgSistemas = new Grilla(CantReg1, tcol, tamcol, false, false);
    private Vector cabecera = new Vector();
    private Vector datos = new Vector();
    private String Consulta = "", ConsultaCount = "", ConsultaInicial = "";
    private String systemName = "";
    private final String TEXTO = "Systems";
    private BasicButton btnUsage = new BasicButton();

    public Systems() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	this.setSize(new Dimension(553, 552));
	bDelGroup.setBounds(new Rectangle(65, 160, 40, 29));
	bDelGroup.setSize(new Dimension(40, 29));
	bDelUser.setBounds(new Rectangle(330, 160, 40, 29));
	bDelUser.setSize(new Dimension(40, 29));
	bDelGroup.setEnabled(false);
	bAddUserData.setBounds(new Rectangle(380, 160, 40, 29));
	bAddUserData.setSize(new Dimension(40, 29));
	bAddGroup.setBounds(new Rectangle(15, 160, 40, 29));
	bAddGroup.setSize(new Dimension(40, 29));
	jPanel1.add(bDelGroup, null);
	jPanel1.add(bAddUserData, null);
	jPanel1.add(bDelUser, null);
	jPanel1.add(lblUsers, null);
	jPanel1.add(lblGroups, null);
	jPanel1.add(bAddUser, null);
	jPanel1.add(bNewUser, null);
	jPanel1.add(bAddGroup, null);
	spUsers.getViewport().add(listUsers, null);
	jPanel1.add(spUsers, null);
	spGroups.getViewport().add(listGroups, null);
	jPanel1.add(spGroups, null);
	this.getContentPane().add(lblGroups1, null);
	this.getContentPane().add(jPanel1, null);
	this.getContentPane().add(lblSystems, null);
	this.getContentPane().add(bCancel, null);
	this.getContentPane().setLayout(null);
	this.setTitle("Systems");
	PanelSistemas.setBounds(new Rectangle(10, 50, 525, 195));
	PanelSistemas.setLayout(null);
	PanelSistemas.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	//
	this.getContentPane().add(PanelSistemas, null);
	listUsers.setSize(new Dimension(165, 120));
	listUsers.addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			listUsers_mouseClicked(e);
		    }

		});
	PanelSistemas.add(btnUsage, null);
	PanelSistemas.add(bAddTables, null);
	PanelSistemas.add(jgSistemas, null);
	jgSistemas.setBounds(new Rectangle(7, 10, 510, 145));
	bAddTables.setBounds(new Rectangle(465, 160, 45, 29));
	bAddTables.setToolTipText("Add tables to System");
	bAddTables.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAddTables_actionPerformed(e);
		    }

		});
	bAddUserData.setEnabled(false);
	bAddUserData.setToolTipText("Add user data");
	bAddUserData.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAddUserData_actionPerformed(e);
		    }

		});
	bDelGroup.setMnemonic('D');
	bDelGroup.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bDelGroup_actionPerformed(e);
		    }

		});
	bDelUser.setMnemonic('D');
	bDelUser.setEnabled(false);
	bDelUser.setToolTipText("Remove user");
	bDelUser.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bDelUser_actionPerformed(e);
		    }

		});
	spGroups.setBounds(new Rectangle(15, 35, 225, 120));
	spGroups.getViewport().setLayout(null);
	listGroups.setBounds(new Rectangle(0, 0, 225, 120));
	listGroups.setSize(new Dimension(230, 120));
	listGroups.addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			listGroups_mouseClicked(e);
		    }

		});
	listGroups.addKeyListener(new KeyAdapter() {

		    public void keyPressed(KeyEvent e) {
			listGroups_keyTyped(e);
		    }

		    public void keyReleased(KeyEvent e) {
			listGroups_keyTyped(e);
		    }

		    public void keyTyped(KeyEvent e) {
			listGroups_keyTyped(e);
		    }

		});
	spUsers.setBounds(new Rectangle(280, 35, 230, 120));
	bAddGroup.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAddGroup_actionPerformed(e);
		    }

		});
	bNewUser.setBounds(new Rectangle(280, 160, 40, 29));
	bNewUser.setEnabled(false);
	bNewUser.setMnemonic('N');
	bNewUser.setToolTipText("Create new User");
	bNewUser.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bNewUser_actionPerformed(e);
		    }

		});
	bAddUser.setBounds(new Rectangle(115, 160, 40, 29));
	bAddUser.setEnabled(false);
	bAddUser.setToolTipText("Add Users");
	bAddUser.setMnemonic('A');
	bAddUser.setSize(new Dimension(40, 29));
	bAddUser.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bAddUser_actionPerformed(e);
		    }

		});
	//new Rectangle(0, 485, 555, 5));
	lblGroups.setText("Groups Name");
	lblGroups.setBounds(new Rectangle(15, 20, 225, 15));
	lblGroups.setFont(new Font("Dialog", 1, 11));
	lblGroups.setSize(new Dimension(75, 15));
	lblUsers.setText("Users Name");
	lblUsers.setBounds(new Rectangle(280, 20, 230, 15));
	lblUsers.setFont(new Font("Dialog", 1, 11));
	lblUsers.setSize(new Dimension(66, 15));
	lblSystems.setText(" Systems List ");
	lblSystems.setBounds(new Rectangle(25, 40, 85, 15));
	lblSystems.setFont(new Font("Dialog", 1, 11));
	lblSystems.setSize(new Dimension(77, 16));
	lblSystems.setOpaque(true);
	bCancel.setBounds(new Rectangle(490, 495, 45, 25));
	bCancel.setMnemonic('C');
	bCancel.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			bCancel_actionPerformed(e);
		    }

		});
	jgSistemas.Redimensiona();
	jgSistemas.getTable().addKeyListener(new KeyAdapter() {

		    public void keyPressed(KeyEvent e) {
			jgSistemas_keyTyped(e);
		    }

		    public void keyReleased(KeyEvent e) {
			jgSistemas_keyTyped(e);
		    }

		    public void keyTyped(KeyEvent e) {
			jgSistemas_keyTyped(e);
		    }

		});
	/**
        * Accion que se dispara cuando se hace "UN CLICK" en la grilla
        * */
	jgSistemas.getTable().addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1 && e.getButton() == e.BUTTON1) {
			    if (datos != jgSistemas.VDatos()) {
				datos = jgSistemas.VDatos();
				habilitaBotones(false);
				bAddTables.setEnabled(true);
				bAddGroup.setEnabled(true);
				setearGroups();
			    }
			}
		    }

		});
	/**
        * Accion que se dispara cuando se hace "DOBLE CLICK" en la grilla
        * */
	jgSistemas.getTable().addMouseListener(new MouseAdapter() {

		    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2 && e.getButton() == e.BUTTON1) {
			    datos = jgSistemas.VDatos();
			}
		    }

		});
	org.digitall.lib.components.ComponentsManager.centerWindow(this);
	jgSistemas.setSpanishLanguage(false);
	btnUsage.setBounds(new Rectangle(10, 160, 105, 30));
	btnUsage.setText("Grant Usage");
	btnUsage.setFont(new Font("Dialog", 1, 10));
	btnUsage.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
			btnUsage_actionPerformed(e);
		    }

		});
	jPanel1.setBounds(new Rectangle(10, 270, 525, 195));
	jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	jPanel1.setLayout(null);
	lblGroups1.setText(" System Groups & Users ");
	lblGroups1.setBounds(new Rectangle(25, 260, 140, 15));
	lblGroups1.setFont(new Font("Dialog", 1, 11));
	lblGroups1.setSize(new Dimension(138, 16));
	lblGroups1.setOpaque(true);
	habilitaBotones(false);
	bAddTables.setEnabled(false);
	bAddGroup.setEnabled(false);
	bAddTables.setSize(new Dimension(45, 29));
	setearCabecera();
	actualizaTabla();
	loadData();
    }

    private void bCancel_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    private void habilitaBotones(boolean _valor) {
	//bAddGroup.setEnabled(_valor);
	bNewUser.setEnabled(_valor);
	bAddUser.setEnabled(_valor);
	bDelUser.setEnabled(_valor);
	//bDelGroup.setEnabled(_valor);
    }

    private void setearCabecera() {
	cabecera.removeAllElements();
	cabecera.addElement("*");
	cabecera.addElement("System");
	cabecera.addElement("Description");
	//cabecera.addElement("Date");
    }

    private void actualizaTabla() {
	armarConsulta();
	ConsultaCount = "SELECT count(*) FROM (" + Consulta + ") as mat";
	jgSistemas.ActualizaTabla((BasicDialog)this, Consulta, ConsultaCount, cabecera);
	//System.out.println("Consulta Count --> "+ConsultaCount);
	//System.out.println("Consulta FrmFrenteObras --> "+Consulta);
    }

    private void armarConsulta() {
	ConsultaInicial = "SELECT idsist,nombre,descripcion FROM admin.systems WHERE idsist > 0 AND estado <> '*'";
	String filtroName = "", filtroFecha = "";
	String OrderBy = " ORDER BY idsist";
	Consulta = ConsultaInicial + filtroName + filtroFecha + OrderBy;
	//System.out.println("Consulta --> "+Consulta);
    }

    private void setearGroups() {
	String consulta = "SELECT nombre FROM admin.systems WHERE idsist = " + datos.elementAt(0).toString();
	systemName = org.digitall.lib.sql.LibSQL.getCampo(consulta);
	consulta = "Select rolname From pg_catalog.pg_roles Where rolname like '" + systemName + "%'";
	ResultSet resul = org.digitall.lib.sql.LibSQL.exQuery(consulta);
	vectorGroups.removeAllElements();
	try {
	    while (resul.next()) {
		vectorGroups.addElement(resul.getString("rolname"));
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	listGroups.setListData(vectorGroups);
	setearUsers();
    }

    private void setearUsers() {
	vectorUsers.removeAllElements();
	listUsers.setListData(vectorUsers);
    }

    private void jgSistemas_keyTyped(KeyEvent e) {
	try {
	    if (datos != jgSistemas.VDatos()) {
		datos = jgSistemas.VDatos();
		setearGroups();
		//bNewUser.setEnabled(false);
		habilitaBotones(false);
		bAddTables.setEnabled(true);
		bAddGroup.setEnabled(true);
	    }
	} catch (ArrayIndexOutOfBoundsException x) {
	    e.consume();
	}
    }

    private void listGroups_mouseClicked(MouseEvent e) {
	if ((e.getClickCount() == 2) && (listGroups.getSelectedIndex() >= 0)) {
	    String idsist = datos.elementAt(0).toString();
	    //System.out.println("grupo: "+vectorGroups.elementAt(listGroups.getSelectedIndex()).toString());
	    GroupProperties groupProperties = new GroupProperties(idsist, systemName, vectorGroups.elementAt(listGroups.getSelectedIndex()).toString());
	    groupProperties.setModal(true);
	    groupProperties.setVisible(true);
	} else if ((e.getClickCount() == 1) && (listGroups.getSelectedIndex() >= 0)) {
	    setUsers();
	    control();
	}
    }

    private void listGroups_keyTyped(KeyEvent e) {
	setUsers();
	control();
    }

    private void setUsers() {
	vectorUsers.removeAllElements();
	listUsers.setListData(vectorUsers);
	String consultaUsers = "SELECT rolname FROM pg_catalog.pg_roles R, pg_auth_members M WHERE" + " R.oid=M.member AND admin_option='f' AND roleid IN (SELECT oid FROM" + " pg_catalog.pg_roles WHERE rolname='" + vectorGroups.elementAt(listGroups.getSelectedIndex()).toString() + "') ORDER BY rolname ";
	ResultSet resulUsers = org.digitall.lib.sql.LibSQL.exQuery(consultaUsers);
	try {
	    while (resulUsers.next()) {
		vectorUsers.addElement(resulUsers.getString("rolname"));
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	listUsers.setListData(vectorUsers);
	habilitaBotones(true);
	bDelUser.setEnabled(false);
	bAddUserData.setEnabled(false);
    }

    private void listUsers_mouseClicked(MouseEvent e) {
	if ((e.getClickCount() == 1) && (listUsers.getSelectedIndex() >= 0)) {
	    bDelUser.setEnabled(true);
	    bAddUserData.setEnabled(true);
	}
	if ((e.getClickCount() == 2) && (listUsers.getSelectedIndex() >= 0)) {
	    String userSelected = listUsers.getSelectedValue().toString();
	    /**
	     * a partir del dia 32-01-2008, se va a reemplazr la tabla employees por la tabla employees2.
	     * Es decir la tabla employees2 pasará a ser la tabla employees.
	     * */
	    //String idUserSelected = org.digitall.lib.sql.LibSQL.getCampo("Select idpeople From org.employees Where user_name = '" + userSelected + "' ");
	    String idUserSelected ="";
	    PersonalData datosPersonales = new PersonalData(idUserSelected);
	    datosPersonales.setModal(true);
	    datosPersonales.setVisible(true);
	}
    }

    private void bAddGroup_actionPerformed(ActionEvent e) {
	/*NewGroup newgroup = new NewGroup(idsist, systemName);
        newgroup.setModal(true);
        newgroup.setVisible(true);
        setearGroups();
        habilitaBotones(false);*/
	String idsist = datos.elementAt(0).toString();
	AddGroup agregarGrupo = new AddGroup(idsist);
	agregarGrupo.setModal(true);
	agregarGrupo.setVisible(true);
	setearGroups();
	habilitaBotones(false);
    }

    private void bAddUserData_actionPerformed(ActionEvent e) {
	String userSelected = listUsers.getSelectedValue().toString();
	/**
	 * a partir del dia 32-01-2008, se va a reemplazr la tabla employees por la tabla employees2.
	 * Es decir la tabla employees2 pasará a ser la tabla employees.
	 * */
	String idUserSelected = "";
	//String idUserSelected = org.digitall.lib.sql.LibSQL.getCampo("Select idpeople From org.employees Where user_name = '" + userSelected + "' ");
	PersonalData datosPersonales = new PersonalData(idUserSelected);
	datosPersonales.setModal(true);
	datosPersonales.setVisible(true);
    }

    private void bNewUser_actionPerformed(ActionEvent e) {
	if (listGroups.getSelectedIndex() > -1) {
	    String grupo = listGroups.getSelectedValue().toString();
	    CreateUser crearusuario = new CreateUser(grupo);
	    crearusuario.setModal(true);
	    crearusuario.setVisible(true);
	    setUsers();
	}
    }

    private void bAddUser_actionPerformed(ActionEvent e) {
	if (listGroups.getSelectedIndex() > -1) {
	    String grupo2 = listGroups.getSelectedValue().toString();
	    SelectUsers user = new SelectUsers(grupo2);
	    user.setModal(true);
	    user.setVisible(true);
	    setUsers();
	}
    }

    private void bDelUser_actionPerformed(ActionEvent e) {
	if (listUsers.getSelectedIndex() > -1) {
	    String groupSelected = listGroups.getSelectedValue().toString();
	    String userSelected = listUsers.getSelectedValue().toString();
	    if (Advisor.question("Warning!", "Are you sure to remove the user " + userSelected + " from group " + groupSelected + "?")) {
		String revoke = "REVOKE " + groupSelected + " FROM " + userSelected;
		if (LibSQL.exActualizar('a', revoke)) {
		    setUsers();
		}
	    }
	}
    }

    private void control() {
	String groupName = listGroups.getSelectedValue().toString();
	String grupo = groupName.substring(groupName.indexOf("_") + 1, groupName.length());
	bDelGroup.setEnabled(false);
	if (grupo.equals("admin") || grupo.equals("user") || grupo.equals("query")) {
	    //
	} else {
	    bDelGroup.setEnabled(true);
	}
    }

    private void bDelGroup_actionPerformed(ActionEvent e) {
	if (listGroups.getSelectedIndex() > -1) {
	    String idsist = datos.elementAt(0).toString();
	    String groupName = listGroups.getSelectedValue().toString();
	    int result = JOptionPane.showConfirmDialog((Component)null, "Are you sure of delete this group?", "Warning!", JOptionPane.YES_NO_OPTION);
	    if (result == JOptionPane.YES_OPTION) {
		boolean band = false;
		String consulta = "Select idtabla as id, tables.esquema||'.'||tables.tabla as dato" + " From admin.tables WHere idtabla in (Select idtabla From admin.syst_tables," + " admin.systems Where syst_tables.idsist = systems.idsist AND systems.idsist = " + idsist + ")";
		ResultSet tablas = org.digitall.lib.sql.LibSQL.exQuery(consulta);
		consulta = "SELECT distinct(esquema) FROM admin.tables " + " WHere idtabla in (Select idtabla From admin.syst_tables, " + " admin.systems Where syst_tables.idsist = systems.idsist AND systems.idsist = " + idsist + " )";
		ResultSet esquemas = org.digitall.lib.sql.LibSQL.exQuery(consulta);
		try {
		    while (tablas.next()) {
			consulta = "REVOKE SELECT ON " + tablas.getString("dato") + " FROM GROUP " + groupName;
			LibSQL.exActualizar('a', consulta);
			consulta = "REVOKE INSERT ON " + tablas.getString("dato") + " FROM GROUP " + groupName;
			LibSQL.exActualizar('a', consulta);
			consulta = "REVOKE UPDATE ON " + tablas.getString("dato") + " FROM GROUP " + groupName;
			LibSQL.exActualizar('a', consulta);
			band = true;
		    }
		    while (esquemas.next()) {
			String revoke = "REVOKE ALL PRIVILEGES ON SCHEMA " + esquemas.getString("esquema") + " FROM " + groupName;
			LibSQL.exActualizar('a', revoke);
			band = true;
		    }
		    if (band) {
			String borrarGrupo = "DROP GROUP " + groupName;
			if (LibSQL.exActualizar('a', borrarGrupo)) {
			    org.digitall.lib.components.Advisor.messageBox("The group has been deleted", "Success");
			}
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
	    }
	}
    }

    private void bAddTables_actionPerformed(ActionEvent e) {
	String idsist = datos.elementAt(0).toString();
	AddTablesToSystem addTables = new AddTablesToSystem(idsist);
	addTables.setModal(true);
	addTables.setVisible(true);
	actualizaTabla();
    }

    private void btnUsage_actionPerformed(ActionEvent e) {
	boolean band = false;
	String consultaSistemas = "SELECT idsist,nombre FROM admin.systems WHERE estado <> '*'";
	ResultSet sistemas = org.digitall.lib.sql.LibSQL.exQuery(consultaSistemas);
	try {
	    while (sistemas.next()) {
		String consultaGrupos = "Select rolname From pg_catalog.pg_roles Where rolname like '" + sistemas.getString("nombre") + "%'";
		ResultSet grupos = org.digitall.lib.sql.LibSQL.exQuery(consultaGrupos);
		while (grupos.next()) {
		    String cadena = "SELECT DISTINCT(esquema), idtabla FROM admin.tables WHERE idtabla IN" + " (Select idtabla From admin.syst_tables, admin.systems Where" + " syst_tables.idsist = systems.idsist AND systems.idsist = " + sistemas.getInt("idsist") + ")";
		    ResultSet esquemas = org.digitall.lib.sql.LibSQL.exQuery(cadena);
		    while (esquemas.next()) {
			String usageSchema = "GRANT USAGE ON SCHEMA " + esquemas.getString("esquema") + " TO " + grupos.getString("rolname");
			//System.out.println("Usage Schema: "+usageSchema);
			if (LibSQL.exActualizar('a', usageSchema)) {
			    band = true;
			} else
			    band = false;
		    }
		}
	    }
	    if (band) {
		org.digitall.lib.components.Advisor.messageBox("Update Succes!", "Message");
	    } else
		org.digitall.lib.components.Advisor.messageBox("It happened an error when updating the data", "Error");
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
    }

    private void loadData() {
	if (Environment.sessionUser.equals("admin")) {
	    btnUsage.setVisible(true);
	} else
	    btnUsage.setVisible(false);
    }

}
