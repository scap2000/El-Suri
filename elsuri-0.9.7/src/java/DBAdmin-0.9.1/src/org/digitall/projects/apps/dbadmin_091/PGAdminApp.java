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
 * PGAdminApp.java
 *
 * */
package org.digitall.projects.apps.dbadmin_091;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.ComponentsManager;
import org.digitall.lib.components.Login;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.sql.LibSQL;

public class PGAdminApp {

    public PGAdminApp() {
	try {
	    ResultSet _result = LibSQL.exQuery("SELECT rolsuper FROM pg_roles WHERE rolname = session_user");
	    if (_result.next()) {
		if (_result.getBoolean("rolsuper")) {
		    JFrame frame = new DBAdminMain();
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    Dimension frameSize = frame.getSize();
		    if (frameSize.height > screenSize.height) {
		        frameSize.height = screenSize.height;
		    }
		    if (frameSize.width > screenSize.width) {
		        frameSize.width = screenSize.width;
		    }
		    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setVisible(true);
		} else {
		    Advisor.messageBox("No tiene el nivel de acceso suficiente para esta aplicación", "Acceso denegado");
		}
	    }
	} catch (Exception e) {
	    // TODO: Add catch code
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	Environment.mainClass = PGAdminApp.class;
	if (args.length != 3) {
	    System.out.println("Run with the following parameters\ndatabase_connection_string database_user password\nE.G.:./run.sh jdbc:postgresql://127.0.01:5432/template1 admin adminpassword");
	} else {
	    try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] devices = env.getScreenDevices();
	    Environment.graphicsDevice = devices[0];
	    try {
		/* Here you must set the database connection params as plain text
		 * either just implement any other login system as you want to
		 * */
		LibSQL.setDataBaseString(args[0]);
		LibSQL.setSQLUser(args[1]);
		LibSQL.setSQLPass(args[2]);
		ResultSet _result = LibSQL.exQuery("SELECT rolsuper FROM pg_roles WHERE rolname = session_user");
		if (_result.next()) {
		    if (_result.getBoolean("rolsuper")) {
			new PGAdminApp();
		    } else {
			Advisor.messageBox("No tiene el nivel de acceso suficiente para esta aplicación", "Acceso denegado");
			System.exit(0);
		    }
		}
	    } catch (Exception e) {
		// TODO: Add catch code
		e.printStackTrace();
	    }
	}
    }

}
