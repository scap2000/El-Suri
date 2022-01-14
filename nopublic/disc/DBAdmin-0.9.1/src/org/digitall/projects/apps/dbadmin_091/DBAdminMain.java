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
 * DBAdminMain.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.digitall.common.components.combos.JCombo;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.BorderPanel;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.basic.BasicButton;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.BasicTabbedPane;
import org.digitall.lib.sql.LibSQL;
import org.digitall.projects.apps.dbadmin_091.classes.DBControl;
import org.digitall.projects.apps.dbadmin_091.interfases.DBAdminPanel;
import org.digitall.projects.apps.dbadmin_091.interfases.DBRole;
import org.digitall.projects.apps.dbadmin_091.interfases.FunctionManager;
import org.digitall.projects.apps.dbadmin_091.interfases.GroupFunctionsManager;
import org.digitall.projects.apps.dbadmin_091.interfases.GroupManager;
import org.digitall.projects.apps.dbadmin_091.interfases.GroupTableManager;
import org.digitall.projects.apps.dbadmin_091.interfases.OptionsMgmt;
import org.digitall.projects.apps.dbadmin_091.interfases.PublicAccessManager;
import org.digitall.projects.apps.dbadmin_091.interfases.StoredBySystemFunctionsManager;
import org.digitall.projects.apps.dbadmin_091.interfases.SystemFunctionUsersManager;
import org.digitall.projects.apps.dbadmin_091.interfases.SystemFunctionsByStoredManager;
import org.digitall.projects.apps.dbadmin_091.interfases.SystemFunctionsGroupsManager;
import org.digitall.projects.apps.dbadmin_091.interfases.TableManager;
import org.digitall.projects.apps.dbadmin_091.interfases.UserManager;
import org.digitall.projects.apps.dbadmin_091.interfases.UsersSystemFunctionsManager;

public class DBAdminMain extends JFrame {

    private DBAdminPanel dbAdminPanel = new DBAdminPanel();

    public DBAdminMain() {
	try {
	    jbInit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void jbInit() throws Exception {
	getContentPane().setLayout(null);
	setSize(new Dimension(675, 615));
	setTitle("DataBase Administrator");
	this.setBackground(Color.black);
        dbAdminPanel.setBounds(new Rectangle(1, 1, 675, 590));
        this.getContentPane().add(dbAdminPanel, null);
    }
}

