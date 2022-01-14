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
 * LibSQLMini.java
 *
 * */
package org.digitall.lib.sql;

import java.io.File;
import java.io.FileInputStream;

import java.net.BindException;
import java.net.InetAddress;

import java.net.UnknownHostException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Types;

import java.util.Date;
import java.util.Vector;

import javax.swing.Timer;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.ssl.SSLConfig;

import org.digitall.lib.ssl.SSLRelayClient;

import org.postgresql.util.PSQLException;

public class LibSQLMini {

    private String SQLDriver = "org.postgresql.Driver";
    private String database = "";
    private String SQLUser = "";
    private String SQLPass = "";
    private Connection pgCon = null;
    private int tries = 0;
    private long connectionLatency = 0;
    private long lastConnectionTime = System.currentTimeMillis();
    private boolean verbose = true;
    private int isBusy = 0;
    private boolean connected = false;
    private SSLRelayClient logClient = null;

    public LibSQLMini() {

    }

    public LibSQLMini(String _serverURL, String _database, String _sqlUser, String _sqlPass) {
	String _localhost = "localhost";
	try {
	    _localhost = InetAddress.getLocalHost().getHostAddress().toString();
	} catch (UnknownHostException e) {
	    e.printStackTrace();   
	}
	database = "jdbc:postgresql://" + _localhost + ":" + SSLConfig.SQLMINI_CLIENT_PORT + "/" + _database;
	setSQLUser(_sqlUser);
	setSQLPass(_sqlPass);
	if (_serverURL != null) {
	    if (startOwnClient(_serverURL)) {
	        if (!isConnected()) {
	            Advisor.messageBox("Error al intentar iniciar la conexión LibSQLMini\nRevise Host, Nombre de Usuario y Contraseña", "Error de conexión");
	        }
	    }
	}
    }

    private boolean startOwnClient(String _serverURL) {
	int tries = 0;
	connected = false;
	if (logClient != null) {
	    try {
	        logClient.close();
	    } catch (Exception x) {
		//ignore
	    }
	}
	while (tries < 3 && logClient == null) {
	    try {
		logClient = new SSLRelayClient(SSLConfig.SQLMINI_SERVER_SECURED_PORT, SSLConfig.SQLMINI_CLIENT_PORT, _serverURL);
		connected = true;
	    } catch (BindException x) {
		SSLConfig.setSQLMINI_CLIENT_PORT((int)(Math.random() * 10000));
		System.out.println("SQLMINI Port already open, trying on port: " + SSLConfig.SQLMINI_CLIENT_PORT);
		tries++;
		continue;
	    }
	}
	return isConnected();
    }

    public Connection CreateConnection() throws SQLException {
	isBusy = 0;
	Connection _returns = null;
	try {
	    Class.forName(SQLDriver);
	} catch (ClassNotFoundException x) {
	    printStackTrace(x);
	}
	if (!SQLUser.equals("") && !SQLPass.equals("")) {
	    _returns = DriverManager.getConnection(database, SQLUser, SQLPass);
	}
	return _returns;
    }

    public boolean isConnected() {
	setBusy(true);
	tries = 0;
	boolean connected = false;
	while (tries < 10 && !connected) {
	    try {
		if (pgCon == null) {

		    try {
			pgCon = CreateConnection();
			connected = true;
			try {
			    pgCon.createStatement();
			    //setDateTime();
			    connected = true;
			} catch (SQLException x) {
			    tries++;
			    System.out.println("Error en la conexión, intento: " + tries);
			    pgCon = null;
			    connected = false;
			}
		    } catch (SQLException x) {
			tries++;
			printStackTrace(x);
			System.out.println("Error en la conexión, intento: " + tries);
			pgCon = null;
			connected = false;
		    }

		} else {
		    try {
			Statement tmp = pgCon.createStatement();
			ResultSet _results = tmp.executeQuery("SELECT now()");
			_results.next();
			connected = true;
		    } catch (SQLException x) {
			tries++;
			System.out.println("Error en la conexión, intento: " + tries);
			pgCon = null;
			connected = false;
		    }
		}
	    } catch (Exception e) {
		// TODO: Add catch code
		connected = false;
		tries++;
		System.out.println("ERROR:" + e.getMessage() + "\nReintentando...");
		continue;
	    }
	}
	if (!connected) {
	    messageBox("No se pudo conectar a la base de datos", "Error");
	    setSQLPass("");
	} else {
	    lastConnectionTime = System.currentTimeMillis();
	}
	setBusy(false);
	return connected;
    }

    /**
     * @param _functionName
     * @param _paramsQuery
     * @param _limit
     * @param _offset
     * @return null if got an error
     */
    public ResultSet exFunction(String _functionName, String _paramsQuery, int _limit, int _offset) {
	setBusy(true);
	ResultSet results = null;
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _paramsQuery + _limit + "," + _offset + ") }");
	if (isConnected()) {
	    try {
		if (_paramsQuery.length() > 0) {
		    _paramsQuery = _paramsQuery + ",";
		}
		/**
	     * Hago lo siguiente para ver si logro parchar el @#$%&@! error del "unnamed portal"
	     * */
		Connection _pgCon = CreateConnection();
		_pgCon.setAutoCommit(false);
		CallableStatement proc = _pgCon.prepareCall("{ ? = call " + _functionName + "(" + _paramsQuery + _limit + "," + _offset + ") }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		proc.registerOutParameter(1, Types.OTHER);
		try {
		    proc.execute();
		    results = (ResultSet)proc.getObject(1);
		    proc.close();
		    _pgCon.setAutoCommit(true);
		    _pgCon.close();
		} catch (PSQLException x) {
		    Advisor.printException(x);
		    printStackTrace(x);
		    proc.close();
		    _pgCon.setAutoCommit(true);
		    _pgCon.close();
		} catch (SQLException x) {
		    Advisor.printException(x);
		    printStackTrace(x);
		    proc.close();
		    _pgCon.setAutoCommit(true);
		    _pgCon.close();
		}
	    } catch (SQLException e) {
		printStackTrace(e);
	    }
	}
	setBusy(false);
	return results;
    }

    public ResultSet exFunction(String _functionName) {
	setBusy(true);
	ResultSet results = null;
	System.out.println("{ Executing SQL call = " + _functionName + "() }");
	if (isConnected()) {
	    try {
		pgCon.setAutoCommit(false);
		CallableStatement proc = pgCon.prepareCall("{ ? = call " + _functionName + " }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		proc.registerOutParameter(1, Types.OTHER);
		try {
		    proc.execute();
		    results = (ResultSet)proc.getObject(1);
		    proc.close();
		    pgCon.setAutoCommit(true);
		} catch (PSQLException x) {
		    messageBox(x.getMessage(), "Error");
		    printStackTrace(x);
		    proc.close();
		    pgCon.setAutoCommit(true);
		} catch (SQLException x) {
		    messageBox(x.getMessage(), "Error");
		    printStackTrace(x);
		    proc.close();
		    pgCon.setAutoCommit(true);
		}
	    } catch (SQLException e) {
		printStackTrace(e);
	    }
	}
	setBusy(false);
	return results;
    }

    /**
     * @param _functionName
     * @param _params
     * @return null if got an error
     */
    public ResultSet exFunction(String _functionName, Object _params) {
	setBusy(true);
	ResultSet results = null;
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + ") }");
	if (isConnected()) {
	    try {
		pgCon.setAutoCommit(false);
		CallableStatement proc = pgCon.prepareCall("{ ? = call " + _functionName + "(" + _params + ") }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		proc.registerOutParameter(1, Types.OTHER);
		try {
		    proc.execute();
		    results = (ResultSet)proc.getObject(1);
		    proc.close();
		    pgCon.setAutoCommit(true);
		} catch (PSQLException x) {
		    Advisor.printException(x);
		    printStackTrace(x);
		    proc.close();
		    pgCon.setAutoCommit(true);
		} catch (SQLException x) {
		    Advisor.printException(x);
		    printStackTrace(x);
		    proc.close();
		    pgCon.setAutoCommit(true);
		}
	    } catch (SQLException e) {
		Advisor.printException(e);
	    }
	}
	setBusy(false);
	return results;
    }

    public ResultSet exQuery(String _sqlStat) {
	setBusy(true);
	ResultSet results = null;
	System.out.println("{ Executing SQL Statement = " + _sqlStat + " }");
	try {
	    if (isConnected()) {
		Statement _stat = pgCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		results = _stat.executeQuery(_sqlStat);
	    }
	} catch (PSQLException x) {
	    Advisor.printException(x);
	} catch (SQLException x) {
	    Advisor.printException(x);
	}
	setBusy(false);
	return results;
    }

    public boolean execute(String _sqlStat) {
	setBusy(true);
	boolean results = false;
	System.out.println("{ Executing SQL Statement = " + _sqlStat + " }");
	try {
	    if (isConnected()) {
		Statement _stat = pgCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		results = _stat.execute(_sqlStat);
	    }
	} catch (PSQLException x) {
	    printStackTrace(x);
	} catch (SQLException x) {
	    printStackTrace(x);
	}
	setBusy(false);
	return results;
    }

    /**
     * @deprecated
     * @param _sqlStat
     * @return
     */
    public String insertQuery(String _sqlStat) {
	setBusy(true);
	ResultSet results = null;
	System.out.println("{ Executing SQL Statement = " + _sqlStat + " }");
	String returnValue = "";
	try {
	    results = exQuery(_sqlStat);
	    if (results.next()) {
		returnValue = results.getString(1);
	    }
	    if (String.valueOf(returnValue).equals("null")) {
		returnValue = "0";
	    }
	} catch (SQLException x) {
	    printStackTrace(x);
	}
	setBusy(false);
	return returnValue;
    }

    private boolean doExecution(CallableStatement _proc) {
	setBusy(true);
	boolean _results = false;
	try {
	    //execute() devuelve --> true if the first result is a ResultSet object;
            //false if the first result is an update count or there is no result 
	    _proc.execute();
	    _results = true;
	} catch (PSQLException x) {
	    /*if (isConnected()) {
		try {
		    results = _proc.execute();
		} catch (PSQLException e) {
		    messageBox(e.getMessage(), "Error");
		    printStackTrace(e);
		} catch (SQLException e) {
		    messageBox(e.getMessage(), "Error");
		    printStackTrace(e);
		}
	    }*/
	    Advisor.printException(x);
	    printStackTrace(x);
	} catch (SQLException x) {
	    if (isConnected()) {
		try {
		    _results = _proc.execute();
		    _results = true;
		} catch (PSQLException e) {
		    messageBox(e.getMessage(), "Error");
		    printStackTrace(e);
		} catch (SQLException e) {
		    messageBox(e.getMessage(), "Error");
		    printStackTrace(e);
		}
	    }
	    printStackTrace(x);
	}
	//Santiago: quito esto para eliminar el mensaje, ya que
	//los getDate y otros generan este error
	/*if (!_success) {
	    System.err.println("Error al intentar ejecutar la consulta");
	}*/
	setBusy(false);
	return _results;
    }

    public boolean getBoolean(String _functionName, Object _params) {
	setBusy(true);
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + ") }");
	boolean results = false;
	if (isConnected()) {
	    try {
		pgCon.setAutoCommit(false);
		CallableStatement proc = pgCon.prepareCall("{ ? = call " + _functionName + "(" + _params + ") }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		proc.registerOutParameter(1, -7);
		if (doExecution(proc)) {
		    results = proc.getBoolean(1);
		}
		SQLWarning sqlWarning = proc.getWarnings();
		if (sqlWarning != null) {
		    messageBox(sqlWarning.getMessage(), "Mensaje del motor");
		}
		proc.close();
		pgCon.setAutoCommit(true);
	    } catch (PSQLException x) {
		Advisor.printException(x);
		printStackTrace(x);
	    } catch (SQLException x) {
	        Advisor.printException(x);
		printStackTrace(x);
	    }
	}
	setBusy(false);
	return results;
    }

    public String getString(String _functionName, Object _params) {
	setBusy(true);
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + ") }");
	String results = "";
	if (isConnected()) {
	    try {
		pgCon.setAutoCommit(false);
		CallableStatement proc = pgCon.prepareCall("{ ? = call " + _functionName + "(" + _params + ") }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		proc.registerOutParameter(1, Types.VARCHAR);
                proc.execute();
                results = proc.getString(1);
		SQLWarning sqlWarning = proc.getWarnings();
		if (sqlWarning != null) {
		    messageBox(sqlWarning.getMessage(), "Mensaje del motor");
		}
		proc.close();
		pgCon.setAutoCommit(true);
	    } catch (SQLException x) {
		System.out.println("Error en " + _functionName + "(" + _params + ")");
		printStackTrace(x);
	    }
	}
	setBusy(false);
	return results;
    }

    public Date getDate(String _functionName, Object _params) {
	setBusy(true);
	Date results = null;
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + ") }");
	if (isConnected()) {
	    try {
		pgCon.setAutoCommit(false);
		CallableStatement proc = pgCon.prepareCall("{ ? = call " + _functionName + "(" + _params + ") }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		proc.registerOutParameter(1, Types.DATE);
		//REVISAR SI ES MEJOR LO SIGUIENTE:
		/*
		 if (doExecution(proc)) {
		    results = proc.getDate(1);
		 }
		 * 
		 */
		 //En lugar de las dos líneas siguientes (fijarse en getBoolean)
		 //public boolean getBoolean(String _functionName, Object _params) {
		proc.execute();
		results = proc.getDate(1);
		proc.close();
		pgCon.setAutoCommit(true);
	    } catch (SQLException x) {
		messageBox(x.getMessage(), "Error");
		printStackTrace(x);
	    }
	}
	setBusy(false);
	return results;
    }

    public int getInt(String _functionName, Object _params) {
	setBusy(true);
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + ") }");
	int _result = -1;
	if (isConnected()) {
	    try {
		pgCon.setAutoCommit(false);
		CallableStatement proc = pgCon.prepareCall("{ ? = call " + _functionName + "(" + _params + ") }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		proc.registerOutParameter(1, Types.INTEGER);
		try {
		    proc.execute();
		    _result = proc.getInt(1);
		    pgCon.commit();
		} catch (PSQLException x) {
		    Advisor.printException(x);
		    printStackTrace(x);
		} catch (SQLException e) {
		    Advisor.printException(e);
		    printStackTrace(e);
		}
		proc.close();
		pgCon.setAutoCommit(true);
	    } catch (SQLException x) {
	        Advisor.printException(x);
		printStackTrace(x);
	    }
	}
	setBusy(false);
	return _result;
    }

    public double getDouble(String _functionName, Object _params) {
	setBusy(true);
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + ") }");
	double _result = -1;
	if (isConnected()) {
	    try {
		pgCon.setAutoCommit(false);
		CallableStatement proc = pgCon.prepareCall("{ ? = call " + _functionName + "(" + _params + ") }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		proc.registerOutParameter(1, Types.DOUBLE);
		try {
		    proc.execute();
		    _result = proc.getDouble(1);
		    pgCon.commit();
		} catch (SQLException e) {
		    Advisor.printException(e);
		    printStackTrace(e);
		}
		proc.close();
		pgCon.setAutoCommit(true);
	    } catch (SQLException x) {
		printStackTrace(x);
	    }
	}
	setBusy(false);
	return _result;
    }

    /**
     * @deprecated
     * @param _sqlStat
     * @return
     */
    public String updateQuery(String _sqlStat) {
	setBusy(true);
	String returnValue = "";
	System.out.println("{ Executing SQL stat " + _sqlStat + ") }");
	try {
	    //System.out.println(_sqlStat);
	    ResultSet rs = exQuery(_sqlStat);
	    if (rs.next()) {
		returnValue = rs.getString(1);
	    }
	    if (String.valueOf(returnValue).equals("null")) {
		returnValue = "0";
	    }
	} catch (SQLException x) {
	    messageBox(x.getMessage(), "Error");
	    printStackTrace(x);
	}
	setBusy(false);
	return returnValue;
    }

    public int deleteQuery(String _functionName, Object _params) {
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + ") }");
	int returnValue = -1;
	if (isConnected()) {
            /*
	    int result = JOptionPane.showConfirmDialog((Component)null, "Eliminar este registro?", "Eliminar", JOptionPane.YES_NO_OPTION);
	    if (result == JOptionPane.YES_OPTION) {
		setBusy(true);
		returnValue = getInt(_functionName, _params);
		setBusy(false);
	    }
            */
	}
	return returnValue;
    }

    public void setSQLUser(String _user) {
	SQLUser = _user;
    }

    public void setSQLPass(String _pass) {
	SQLPass = _pass;
    }

    public void setDataBase(String _database) {
	String _localhost = "localhost";
	try {
	    _localhost = InetAddress.getLocalHost().getHostAddress().toString();
	} catch (UnknownHostException e) {
	    e.printStackTrace();   
	}
	database = "jdbc:postgresql://" + _localhost + ":" + SSLConfig.PG_CLIENT_PORT + "/" + _database;
	System.out.println(database);
    }

    public void setDataBaseString(String _database) {
	database = _database;
    }

    public boolean tryToConnect(String _SQLUser, String _SQLPass) {
	try {
	    Class.forName(SQLDriver);
	    long start = System.currentTimeMillis();
	    Connection tmp = DriverManager.getConnection(database, _SQLUser, _SQLPass);
	    long end = System.currentTimeMillis();
	    connectionLatency = end - start;
	    tmp.close();
	    return true;
	} catch (ClassNotFoundException x) {
	    System.out.println("El controlador de la base de datos no está instalado correctamente");
	    return false;
	} catch (SQLException x) {
	    printStackTrace(x);
	    return false;
	}
    }

    public void closeConnection() {
	try {
	    if (pgCon != null) {
		pgCon.close();
	    }
	} catch (SQLException x) {
	    printStackTrace(x);
	}
	isBusy = 0;
	//setSQLPass("");
	//Codigo comentado - para eliminación
	//Environment.jpStatusBar.setAction("Desconectado :(");
	pgCon = null;
    }

    /** @deprecated
     * @since 05-09-2007
     * */
    public boolean exActualizar(char ch, String _sqlStat) {
        
	setBusy(true);
	System.out.println("{ Executing SQL stat " + _sqlStat + ") }");
	boolean bol = true;
	String accion = "";
	//codigo comentado - para eliminación
        if (isConnected()) {
	    try {
		Statement Stat = pgCon.createStatement();
		if (ch == 'a') {
		    accion = " insertar ";
		    Stat.executeUpdate(_sqlStat);
		} 
	    } catch (SQLException x) {
		messageBox(x.getMessage(), "Error" + x.getErrorCode());
	    }
	} else {
	    messageBox("Error, el servidor de Bases de Datos está desconectado\nIntente nuevamente más tarde", "Error de Conexión");
	}
        
	setBusy(false);
	return bol;
    }

    /** @deprecated
     * @since 05-09-2007
     * */
    public String getCampo(String SQLQuery) {
	setBusy(true);
	String campo = "";
	try {
	    //System.out.println(SQLQuery);
	    ResultSet Resul1 = exQuery(SQLQuery);
	    if (Resul1.next()) {
		campo = Resul1.getString(1);
	    }
	    if (String.valueOf(campo).equals("null")) {
		campo = "0";
	    }
	} catch (SQLException x) {
	    messageBox(x.getMessage(), "Error");
	}
	setBusy(false);
	return campo;
    }

    /** @deprecated
     * @since 05-09-2007
     * */
    public String getCampo(String _SQLQuery, String _columna) {
	setBusy(true);
	String campo = "";
	try {
	    ResultSet Resul1 = exQuery(_SQLQuery);
	    if (Resul1.next()) {
		campo = Resul1.getString(_columna);
	    }
	    if (String.valueOf(campo).equals("null")) {
		campo = "0";
	    }
	} catch (SQLException x) {
	    messageBox(x.getMessage(), "Error");
	}
	setBusy(false);
	return campo;
    }

    public void GuardaImagen(File _imgFile, String _query) {
	setBusy(true);
	if (_imgFile != null)
	    try {
		FileInputStream fis = new FileInputStream(_imgFile);
		PreparedStatement ps = pgCon.prepareStatement(_query);
		ps.setBinaryStream(1, fis, (int)_imgFile.length());
		ps.executeUpdate();
		ps.close();
		fis.close();
	    } catch (Exception x) {
		messageBox(x.getMessage(), "Error");
		printStackTrace(x);
	    }
	setBusy(false);
    }

    /**
     *
     * @param imgFile       Archivo de Imagen
     * @param table         String: esquema.tabla
     * @param campoImagen   String: campo donde se almacena la imagen
     * @param condition     String: condicion WHERE SQL para un registro específico
     */
    public boolean saveImageSQL(File imgFile, String table, String campoImagen, String condition) {
	setBusy(true);
	boolean returns = false;
	if (isConnected()) {
	    if (imgFile != null) {
		try {
		    System.out.println("Saving image " + imgFile.getName() + " to " + table + " at field " + campoImagen + " " + condition);
		    FileInputStream fis = new FileInputStream(imgFile);
		    PreparedStatement ps = pgCon.prepareStatement("UPDATE " + table + " SET " + campoImagen + " =? " + condition);
		    ps.setBinaryStream(1, fis, (int)imgFile.length());
		    if (ps.executeUpdate() != 0) {
			fis.close();
			returns = true;
		    } else {
			fis.close();
		    }
		} catch (Exception x) {
		    messageBox(x.getMessage(), "Error");
		    printStackTrace(x);
		}
	    } else {
		messageBox("No se pudo leer el archivo", "Error");
	    }
	}
	setBusy(false);
	return returns;
    }

    public long getConnectionLatency() {
	return connectionLatency;
    }

    public Connection getConnection() {
	return pgCon;
    }

    public String getDataBase() {
	return database;
    }

    public void setVerboseMode(boolean _verbose) {
	verbose = _verbose;
    }

    
    private void printException(Exception _e) {
	if (verbose) {
	    //codigo comentado - para eliminación
	    _e.printStackTrace();
	}
    }

    private void printStackTrace(Exception _e) {
	if (verbose) {
	    _e.printStackTrace();
	}
    }

    private void messageBox(String _message, String _title) {
	if (verbose) {
	System.out.println("MessageBox " + _title + ": " + _message);
	    //codigo comentado - para eliminación
	    //Advisor.messageBox(_message, _title);
	}
    }

    private void setBusy(boolean _busy) {
	try {
	    if (_busy) {
		isBusy++;
		if (isBusy == 1) {
                    /*
                    //codigo comentado - para eliminación
		    busyLabel.setBounds(Environment.getActiveDesktop().getBounds());
		    Environment.getActiveDesktop().add(busyLabel, 0);
                    */
		}
	    } else {
		isBusy--;
		if (isBusy < 1) {
		    isBusy = 0;
		    //codigo comentado - para eliminación
		    //Environment.getActiveDesktop().remove(busyLabel);
		}
	    }
	    //Codigo comentado - para eliminación
	    //Environment.getActiveDesktop().repaint();
	} catch (Exception x) {
	    x.toString();//ignore
	}
    }

    public Vector getVector(String _sqlStat) {
	ResultSet _results = exQuery(_sqlStat);
	try {
	    Vector _returns = new Vector();
	    while (_results.next()) {
		_returns.add(_results.getString(1));
	    }
	    return _returns;
	} catch (SQLException e) {
	    return new Vector();
	}
    }

    public Vector getVector(String _functionName, Object _params) {
	setBusy(true);
	Vector _returns = new Vector();
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + ") }");
	if (isConnected()) {
	    try {
		pgCon.setAutoCommit(false);
		CallableStatement proc = pgCon.prepareCall("{ ? = call " + _functionName + "(" + _params + ") }", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		proc.registerOutParameter(1, Types.OTHER);
		try {
		    proc.execute();
		    ResultSet _results = (ResultSet)proc.getObject(1);
		    while (_results.next()) {
			_returns.add(_results.getString(1));
		    }
		    pgCon.commit();
		} catch (SQLException e) {
		    Advisor.printException(e);
		    printStackTrace(e);
	        } catch (NullPointerException e) {
	            Advisor.printException(e);
	        }
		proc.close();
		pgCon.setAutoCommit(true);
	    } catch (SQLException x) {
		printStackTrace(x);
	    }
	}
	setBusy(false);
	return _returns;
    }
    
}
