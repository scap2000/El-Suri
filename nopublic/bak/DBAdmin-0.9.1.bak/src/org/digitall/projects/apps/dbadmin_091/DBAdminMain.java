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

