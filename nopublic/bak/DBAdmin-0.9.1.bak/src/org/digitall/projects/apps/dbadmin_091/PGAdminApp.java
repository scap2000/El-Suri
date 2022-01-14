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
