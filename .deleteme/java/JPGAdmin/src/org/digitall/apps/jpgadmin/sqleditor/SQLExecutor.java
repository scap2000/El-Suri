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
 * SQLExecutor.java
 *
 * */
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
