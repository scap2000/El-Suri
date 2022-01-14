package org.digitall.apps.jpgadmin.sqleditor;

import java.awt.BorderLayout;

import java.sql.ResultSet;

import javax.swing.JDialog;

import javax.swing.JPanel;

import org.digitall.lib.components.JArea;
import org.digitall.lib.components.basic.BasicDialog;
import org.digitall.lib.sql.LibSQL;

public abstract class SQLExecutor {

    private static StringBuffer sqlLog = new StringBuffer();

    private static void append(String _query) {
	sqlLog.append(_query + "\n");
    }

    public static String getLog() {
	return sqlLog.toString();
    }

    public static void showLog() {
	System.out.println("Log: " + getLog());
	JArea taSQLLog = new JArea();
	BasicDialog sqlDialog = new BasicDialog();
	sqlDialog.setTitle("Log de ejecución SQL");
	sqlDialog.setLayout(new BorderLayout());
	sqlDialog.setSize(600, 600);
	sqlDialog.getContentPane().add(taSQLLog, BorderLayout.CENTER);
	taSQLLog.setText(getLog());
	sqlDialog.setVisible(true);
	sqlDialog.show();
    }

    public static void setDataBase(String _dataBase) {
	LibSQL.setDataBase(_dataBase);
    }

    public static String getDataBase() {
	return LibSQL.getDataBase();
    }
    public static void closeConnection() {
	LibSQL.closeConnection();
    }

    public static boolean isConnected() {
	return LibSQL.isConnected();
    }

    /*public static void setDateTime() {
	LibSQL.setDateTime();
    }*/

    public static boolean execute(String _query, boolean _log) {
	if (_log) {
	    append(_query);
	}
	return LibSQL.executeQuery(_query);
    }

    public static boolean exActualizar(char c, String _query, boolean _log) {
	if (_log) {
	    append(_query);
	}
	return LibSQL.exActualizar(c, _query);
    }

    public static ResultSet exQuery(String _query, boolean _log) {
	if (_log) {
	    append(_query);
	}
	return LibSQL.exQuery(_query);
    }

    public static ResultSet exFunction(String _query, String _params, boolean _log) {
	if (_log) {
	    append(_query + "(" + _params + ")");
	}
	return LibSQL.exFunction(_query, _params);
    }

    public static Object getBoolean(String _query, String _params, boolean _log) {
	if (_log) {
	    append(_query + "(" + _params + ")");
	}
	return LibSQL.getBoolean(_query, _params);
    }

    public static Object getInt(String _query, String _params, boolean _log) {
	if (_log) {
	    append(_query + "(" + _params + ")");
	}
	return LibSQL.getInt(_query, _params);
    }

    public static Object getDouble(String _query, String _params, boolean _log) {
	if (_log) {
	    append(_query + "(" + _params + ")");
	}
	return LibSQL.getDouble(_query, _params);
    }

    public static Object getDate(String _query, String _params, boolean _log) {
	if (_log) {
	    append(_query + "(" + _params + ")");
	}
	return LibSQL.getDate(_query, _params);
    }

}
