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
 * Login.java
 *
 * */
package org.digitall.lib.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;

import java.net.InetAddress;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicCombo;
import org.digitall.lib.components.basic.BasicContainer;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPasswordField;
import org.digitall.lib.components.basic.BasicTitleLabel;
import org.digitall.lib.components.buttons.AddButton;
import org.digitall.lib.components.buttons.ModifyButton;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.network.NetworkConfig;
import org.digitall.lib.resources.ResourcesManager;
import org.digitall.lib.sql.LibSQL;

public class Login extends JDialog {

    private BasicLabel lblBackground = new BasicLabel(IconTypes.login_background);
    private JEntry jtUser = new JEntry();
    private BasicPasswordField jtPassword = new BasicPasswordField();
    private BasicButton btnAccept = new BasicButton();
    private BasicButton btnCancel = new BasicButton();
    private String titulo, dep;
    private String _sqlIntruderUser = "";
    private String _sqlIntruderPassword = "";
    public static boolean validado = false;
    private boolean inicio = false;
    private int intentos = 0;
    private BasicCombo cbServerList = new BasicCombo();
    //private ConfigFile commonCfg = new ConfigFile(ResourcesManager.class.getResourceAsStream("common/default.conf"));
    private boolean logo = true;
    private BasicTitleLabel lblLanguage = new BasicTitleLabel("Idioma", IconTypes.login_language);
    private BasicTitleLabel  lblOptions = new BasicTitleLabel ("Opciones", IconTypes.login_options);
    private BasicTitleLabel lblUser = new BasicTitleLabel();
    private BasicTitleLabel lblPwd = new BasicTitleLabel();
    private BasicTitleLabel lblServerList = new BasicTitleLabel();
    private Vector<String> serverURLs = new Vector();
    private Vector<String> serverDBs = new Vector();
    private int client_module_err_counter = 0;
    private BasicTitleLabel lblVersion = new BasicTitleLabel("V. " + Environment.SYSTEM_VERSION);
    
    private AddButton btnAddServer = new AddButton();
    private ModifyButton btnModifyServer = new ModifyButton();
    
    private ItemListener cbItemListener;

    public Login(String Titulo, String Dependencia, boolean Inicio, boolean _logo) {
	try {
	    titulo = Titulo;
	    dep = Dependencia;
	    inicio =  Inicio;
	    logo = _logo;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public Login(BasicDialog _parent, String Titulo, String Dependencia, boolean Inicio) {
	super(_parent);
	try {
	    titulo = Titulo;
	    dep = Dependencia;
	    inicio = Inicio;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public Login(JFrame _parent, String Titulo, String Dependencia, boolean Inicio) {
	super(_parent);
	try {
	    titulo = Titulo;
	    dep = Dependencia;
	    inicio = Inicio;
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	ResourcesManager.initConfig();
	addWindowListener(new WindowAdapter() {

		       public void windowClosing(WindowEvent e) {
			   System.exit(0);
		       }

		   }
	);
	setUndecorated(true);
	//jtUser.setOpaque(false);
	//jtPassword.setOpaque(false);
	//jtUser.setEnabled(false);
	//jtUser.setText("admin");
	//cbServerList.setOpaque(false);
	btnAccept.setIcon(IconTypes.login_accept);
	btnAccept.setRolloverIcon(IconTypes.login_accept_ro);
	btnAccept.setPressedIcon(IconTypes.login_accept_pr);
	btnCancel.setIcon(IconTypes.login_cancel);
	btnCancel.setRolloverIcon(IconTypes.login_cancel_ro);
	btnCancel.setPressedIcon(IconTypes.login_cancel_pr);
	this.setContentPane(new BasicContainer());
	this.setTitle(titulo + " - Authorization");
	this.setLayout(new BorderLayout());
	lblBackground.add(lblServerList, null);
	lblBackground.add(lblPwd, null);
	lblBackground.add(lblVersion, null);
	lblBackground.add(lblUser, null);
	lblBackground.add(lblOptions, null);
	lblBackground.add(lblLanguage, null);
	lblBackground.add(cbServerList, null);
	lblBackground.add(btnAddServer, null);
	lblBackground.add(btnModifyServer, null);
	lblBackground.add(jtUser, null);
	lblBackground.add(jtPassword, null);
	lblBackground.add(btnCancel, null);
	lblBackground.add(btnAccept, null);
	add(lblBackground, BorderLayout.CENTER);
	this.toFront();
	this.setSize(new Dimension(443,366));
	jtUser.setBounds(new Rectangle(80, 20, 155, 35));
	jtUser.setFont(new Font("Dialog", 0, 15));
	jtUser.setNextFocusableComponent(jtPassword);
	jtUser.setBounds(new Rectangle(175, 165, 130, 22));
	//jtPassword.setBounds(new Rectangle(130, 65, 130, 25));
	jtPassword.setFont(new Font("Dialog", 0, 15));
	jtPassword.setNextFocusableComponent(cbServerList);
	jtPassword.setBounds(new Rectangle(175, 200, 130, 22));
	btnAccept.setNextFocusableComponent(btnCancel);
	btnAccept.setHorizontalAlignment(SwingConstants.LEFT);
	btnAccept.setHorizontalTextPosition(SwingConstants.RIGHT);
	btnAccept.setBounds(new Rectangle(230, 325, 72, 40));
	btnCancel.setNextFocusableComponent(btnAccept);
	btnCancel.setBounds(new Rectangle(310, 325, 72, 40));
	//cbServerList.setBounds(new Rectangle(130, 94, 130, 25));
	cbServerList.setNextFocusableComponent(btnAccept);
	cbServerList.setBounds(new Rectangle(175, 235, 130, 22));
	btnAddServer.setBounds(new Rectangle(310, 235, 25, 25));
	btnAddServer.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnAddServer_actionPerformed(e);
		}
	    });
	btnModifyServer.setBounds(new Rectangle(340, 235, 25, 25));
	btnModifyServer.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    btnModifyServer_actionPerformed(e);
		}
	    });
	lblLanguage.setBounds(new Rectangle(125, 275, 75, 20));
	lblOptions.setBounds(new Rectangle(235, 280, 75, 14));
	lblUser.setText("Usuario");
	lblUser.setBounds(new Rectangle(65, 170, 100, 14));
	lblUser.setFont(new Font("Lucida Sans", 1, 12));
	lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
	lblPwd.setText("Contraseña");
	lblPwd.setBounds(new Rectangle(65, 205, 100, 15));
	lblPwd.setFont(new Font("Lucida Sans", 1, 12));
	lblPwd.setHorizontalAlignment(SwingConstants.RIGHT);
	lblVersion.setBounds(new Rectangle(5, 350, 220, 15));
	lblVersion.setFont(new Font("Lucida Sans", 1, 11));
	lblServerList.setText("Servidor");
	lblServerList.setBounds(new Rectangle(65, 240, 100, 14));
	lblServerList.setFont(new Font("Lucida Sans", 1, 12));
	lblServerList.setHorizontalAlignment(SwingConstants.RIGHT);
	btnAccept.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnAccept_actionPerformed(e);
				 }

			     }
	);
	//btnCancel.addKeyListener(this);
	btnCancel.addActionListener(new ActionListener() {

				 public void actionPerformed(ActionEvent e) {
				     btnCancel_actionPerformed(e);
				 }

			     }
	);
	btnModifyServer.setEnabled(false);
	
	btnAddServer.setToolTipText("Agregar un servidor nuevo");
	btnModifyServer.setToolTipText("Modificar el servidor seleccionado");
	loadServerList();
	cbServerList.transferFocus();
	lblBackground.setBounds(new Rectangle(0, 0, 443, 366));
	lblBackground.setSize(new Dimension(443, 366));
	jtPassword.addKeyListener(new KeyAdapter() {

						  public void keyReleased(KeyEvent key) {
						      if (key.getKeyCode() == KeyEvent.VK_ENTER) {
							  tryToGainAccess();
						      }
						  }

					      }
	);
	
    }

    private void tryToGainAccess() {
	String _sqlUser = "", _sqlPassword = "";
	_sqlUser = jtUser.getText();
	_sqlIntruderUser += _sqlUser + " ";
	_sqlIntruderPassword += new String(jtPassword.getPassword()) + " ";
	_sqlPassword = new String(jtPassword.getPassword());
	if (jtUser.getText().equals("admin")) {
	    _sqlPassword = new String(jtPassword.getPassword());
	} else {
	    _sqlPassword = new String(jtPassword.getPassword());
	}
	tryToConnect(_sqlUser, _sqlPassword);
    }

    private void tryToConnect(String _user, String _password) {
	validado = false;
	boolean _isServerReachable = true;
	try {
	    InetAddress _test = InetAddress.getByName(serverURLs.elementAt(cbServerList.getSelectedIndex()));
	    System.out.println("HostName: " + _test.getHostName());
	    System.out.println("HostAddress: " + _test.getHostAddress());
	} catch (IOException e) {
	    // TODO
	    System.err.println("Error en la conexión: " + e.getMessage());
	    _isServerReachable = false;
	}
	if (_isServerReachable) {
	    NetworkConfig.setServerURL(serverURLs.elementAt(cbServerList.getSelectedIndex()));
	    if (NetworkConfig.startClient()) {
		LibSQL.setDataBase(serverDBs.elementAt(cbServerList.getSelectedIndex()));
		validado = LibSQL.tryToConnect(_user, _password);
		cbServerList.setEnabled(false);
		if (validado) {
		    LibSQL.setSQLUser(_user);
		    LibSQL.setSQLPass(_password);
		    if (LibSQL.isConnected()) {
			Environment.defaultCfg.setProperty("DefaultServer",String.valueOf(cbServerList.getSelectedIndex()));
			Environment.sessionUser = _user;
			/*************/
			client_module_err_counter = 0;
			this.dispose();
		    } else {
			Advisor.messageBox("Acceso denegado, Usuario o Contraseña incorrecta", "Error de acceso mediante LIBSQL");
		    }
		} else {
		    Advisor.messageBox("Acceso denegado, Usuario o Contraseña incorrecta", "Error de acceso");
		    jtPassword.setText("");
		    intentos++;
		    if (intentos == 3) {
			try {
			    LibSQL.setSQLUser("md5");
			    LibSQL.setSQLPass("pa55w0rd");
			    //String Query = "INSERT INTO auditorias.erroresdeacceso values('" + org.digitall.lib.sql.LibSQL.getCampo("SELECT current_date") + "','" + Proced.Hora(org.digitall.lib.sql.LibSQL.getCampo("SELECT current_time"), false, false) + "','" + Proced.ObtieneHost() + "','" + _sqlIntruderUser + "','" + _sqlIntruderPassword + "')";
			    //LibSQL.exActualizar('a', Query);
			    Advisor.messageBox("Su intrusión será reportada al Administrador del Sistema", "Advertencia Legal");
			} catch (Exception x) {
			    x.printStackTrace();
			}
			System.exit(0);
		    }
		}
	    } else {
		System.err.println("Error, no se pudo iniciar el módulo cliente");
		client_module_err_counter++;
		if (client_module_err_counter < 3) {
		    if (Advisor.question("Error al iniciar el módulo cliente", "Ha ocurrido un error al iniciar el módulo cliente\n¿Desea reintentar?")) {
			tryToConnect(_user, _password);
		    } else {
			Advisor.messageBox("Por favor, presione \"Aceptar\" e ingrese nuevamente sus datos", "Error al iniciar el módulo cliente");
		    }
		} else {
		    Advisor.messageBox("Por favor, revise su conexión de red e intente nuevamente", "Error al iniciar el módulo cliente");
		    client_module_err_counter = 0;
		}
	    }
	} else {
	    Advisor.messageBox("Por favor, revise su conexión a la red (Internet) e intente nuevamente", "No hay conexión con el servidor " + serverURLs.elementAt(cbServerList.getSelectedIndex()));
	}
    }

    public boolean getValidado() {
	return validado;
    }

    public void setValidado(boolean _validado) {
	validado = _validado;
    }

    public String getUsuario() {
	return jtUser.getText();
    }

    private void loadServerList() {
	cbServerList.removeItemListener(cbItemListener);
	cbItemListener = new ItemListener() {

				 public void itemStateChanged(ItemEvent evt) {
				     if (evt.getStateChange() == ItemEvent.SELECTED) {
					 btnModifyServer.setEnabled(false);
					 if (cbServerList.getSelectedIndex() != -1) {
					     btnModifyServer.setEnabled(true);
					     cbServerList.setToolTipText(
						serverDBs.elementAt(cbServerList.getSelectedIndex()) + "@" +
						serverURLs.elementAt(cbServerList.getSelectedIndex())
						 );
					 }
				     }
				 }

			     };
	cbServerList.removeAllItems();
	serverDBs.removeAllElements();
	serverURLs.removeAllElements();
	boolean found = true;
	int i = 0;
	while (found && i < 1000) {
	    if (!Environment.defaultCfg.hasProperty("srv["+i+"]")) {
		found = false;
	    } else {
		cbServerList.addItem(Environment.defaultCfg.getProperty("srv["+i+"]"));
		serverURLs.add(Environment.defaultCfg.getProperty("url["+i+"]"));
		serverDBs.add(Environment.defaultCfg.getProperty("db["+i+"]"));
	    }
	    i++;
	}
	
	cbServerList.addItemListener(cbItemListener);
	
	if (!Environment.defaultCfg.hasProperty("DefaultServer")) {
	    cbServerList.setSelectedIndex(0);
	} else {
	    cbServerList.setSelectedIndex(Integer.parseInt(Environment.defaultCfg.getProperty("DefaultServer")));
	}
	if (cbServerList.getItemCount() > 0) {
	    btnModifyServer.setEnabled(true);
	}
    }

    private void btnAccept_actionPerformed(ActionEvent e) {
	tryToGainAccess();
    }

    private void btnCancel_actionPerformed(ActionEvent e) {
	if (inicio) {
	    //ComponentPrinter.printComponent(lblBackground);
	    System.exit(0);
	} else {
	    this.dispose();
	}
    }

    private void btnAddServer_actionPerformed(ActionEvent e) {
	int _index = cbServerList.getItemCount();
	String _serverName = (String)JOptionPane.showInputDialog(this, "Ingrese el Nombre de esta configuración", "Nombre de la configuración", JOptionPane.QUESTION_MESSAGE, null, null, "");
	if (_serverName != null) {
	    String _serverURL = (String)JOptionPane.showInputDialog(this, "Ingrese el URL o Nombre del Servidor", "Nombre del Servidor", JOptionPane.QUESTION_MESSAGE, null, null, "");
	    if (_serverURL != null) {
		String _database = (String)JOptionPane.showInputDialog(this, "Ingrese el nombre de la Base de Datos", "Base de Datos", JOptionPane.QUESTION_MESSAGE, null, null, "");
		if (_database != null) {
		    Environment.defaultCfg.setProperty("srv[" + _index + "]", _serverName);
		    Environment.defaultCfg.setProperty("url[" + _index + "]", _serverURL);
		    Environment.defaultCfg.setProperty("db[" + _index + "]", _database);
		    loadServerList();
		    cbServerList.setSelectedIndex(_index);
		}
	    }
	}
    }

    private void btnModifyServer_actionPerformed(ActionEvent e) {
	int _index = cbServerList.getSelectedIndex();
	if (_index != -1) {
	    String _serverName = (String)JOptionPane.showInputDialog(this, "Ingrese el Nombre de esta configuración", "Nombre de la configuración", JOptionPane.QUESTION_MESSAGE, null, null, cbServerList.getSelectedItem());
	    if (_serverName != null) {
		String _serverURL = (String)JOptionPane.showInputDialog(this, "Ingrese el URL o Nombre del Servidor", "Nombre del Servidor", JOptionPane.QUESTION_MESSAGE, null, null, serverURLs.elementAt(_index)  );
		if (_serverURL != null) {
		    String _database = (String)JOptionPane.showInputDialog(this, "Ingrese el nombre de la Base de Datos", "Base de Datos", JOptionPane.QUESTION_MESSAGE, null, null, serverDBs.elementAt(_index));
		    if (_database != null) {
		        Environment.defaultCfg.setProperty("srv[" + _index + "]", _serverName);
		        Environment.defaultCfg.setProperty("url[" + _index + "]", _serverURL);
		        Environment.defaultCfg.setProperty("db[" + _index + "]", _database);
		        loadServerList();
		        cbServerList.setSelectedIndex(_index);
		    }
		}
	    }
	}
    }
}
