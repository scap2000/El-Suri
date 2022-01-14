package org.digitall.lib.sql;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Types;

import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.Timer;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicLabel;
import org.digitall.lib.environment.Environment;
import org.digitall.lib.icons.IconTypes;
import org.digitall.lib.image.LibIMG;
import org.digitall.lib.network.NetworkConfig;
import org.digitall.lib.org.Persons;
import org.digitall.lib.ssl.SSLConfig;

import org.postgresql.util.PSQLException;

public abstract class LibSQL {

    private static String SQLDriver = "org.postgresql.Driver";
    private static String DataBase = "";
    private static String SQLUser = "";
    private static String SQLPass = "";
    private static Connection pgCon = null;
    private static int tries = 0;
    private static long connectionLatency = 0;
    private static long lastConnectionTime = System.currentTimeMillis();
    private static Timer calendarTimer = null;
    //ten minute timeout
    public static int timeout = 10;
    private static Savepoint savePoint = null;
    private static boolean verbose = true;
    private static BasicLabel busyLabel = new BasicLabel(IconTypes.busy);
    private static int isBusy = 0;

    public static Connection CreateConnection() throws SQLException {
	isBusy = 0;
	Connection _returns = null;
	try {
	    Class.forName(SQLDriver);
	} catch (ClassNotFoundException x) {
	    printStackTrace(x);
	}
	if (!SQLUser.equals("") && !SQLPass.equals("")) {
	    _returns = DriverManager.getConnection(DataBase, SQLUser, SQLPass);
	    if (_returns != null) {
		Environment.jpStatusBar.setAction("Conectado a " + DataBase.split("//")[1].split("/")[1] + "!");
	        lastConnectionTime = System.currentTimeMillis();
	        if (calendarTimer == null) {
	            String _strTimeout = Environment.cfg.getProperty("DBTimeOut");
		    if (_strTimeout.equalsIgnoreCase("DBTimeOut")) {
			Environment.cfg.setProperty("DBTimeOut","10");
		    } else {
			try {
			    timeout = Integer.parseInt(_strTimeout);
			}
			catch (Exception e) {
			    timeout = 10;
			}
		    }
		    if (timeout == 0) {
			timeout = 10;
		    }
		    final int _timeout = timeout * 60 * 1000;
	            calendarTimer = new Timer(10000, new ActionListener() {

	                                   public void actionPerformed(ActionEvent e) {
	                                       if ((System.currentTimeMillis() - lastConnectionTime) > _timeout && (isBusy == 0)) {
	                                           System.out.println("Closing SQL connection due to timeout");
						    setSQLPass("");
						    closeConnection();
						    calendarTimer.stop();
						    calendarTimer = null;
	                                       } else {
	                                           //System.out.println("SQL connection still active, ETA: " + (_timeout - (System.currentTimeMillis() - lastConnectionTime)) / 1000.0 + " seconds to close");
	                                       }
	                                   }

	                               }
	                );
	        }
	        if (timeout > 0) {
	            calendarTimer.start();
	        }
	    }
	} else {

	    if (!SQLUser.equals("")) {
	        String _password = "";
	        JPasswordField _jtPassword = new JPasswordField();
	        Object[] obj = {"Contraseña para " + Environment.sessionUser + "@" + NetworkConfig.getServerIP(), _jtPassword};
	        Object stringArray[] = {"Aceptar","Cancelar"};
	        if (JOptionPane.showOptionDialog(Environment.mainFrame, obj, "Ingrese la contraseña",
	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION)
		{
		    _password = new String(_jtPassword.getPassword());
		    if (!_password.equals("")) {
		        if (tryToConnect(SQLUser, _password)) {
		            setSQLPass(_password);
		            _returns = CreateConnection();
		        } else {
		            Advisor.messageBox("No ha ingresado una contraseña válida", "Error de autenticación");
		        }
		    }
		} else {
		    if (Advisor.question("Salir del sistema", "¿Desea salir de " + Environment.SYSTEM_VERSION + "?")) {
		        Environment.cfg.setProperty("SelectedTab", String.valueOf(Environment.mainTabbedPane.getSelectedIndex()));
		        if (Environment.saveAll()) {
		            System.exit(0);
		        } else {
		            messageBox("No se puede salir del programa hasta que no se hayan completado o cancelado las operaciones pendientes", "Aviso");
		        }
		    } else {
		        _returns = CreateConnection();
		    }
		}
	    }
	}
	return _returns;
    }

    public static boolean isConnected() {
	setBusy(true);
	tries = 0;
	boolean connected = false;
	while (tries < 10 && !connected) {
	    if (pgCon == null) {
		try {
		    pgCon = CreateConnection();
		    connected = true;
		    try {
			pgCon.createStatement();
			setDateTime();
			connected = true;
		    } catch (NullPointerException x) {
		        tries++;
		        System.out.println("Error en la conexión, intento: " + tries);
		        pgCon = null;
		        connected = false;
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
	}
	if (!connected) {
	    messageBox("No se pudo conectar a la base de datos.\nRevise su nombre de usuario y contraseña.", "Error");
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
     * @return null on error
     */
    public static ResultSet exFunction(String _functionName, Object _paramsQuery, int _limit, int _offset) {
	setBusy(true);
	ResultSet results = null;
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _paramsQuery + ", " + _limit + ", " + _offset + "); }");
	if (isConnected()) {
	    try {
		if (_paramsQuery.toString().length() > 0) {
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
		    printException(x);
		    printStackTrace(x);
		    proc.close();
		    _pgCon.setAutoCommit(true);
		    _pgCon.close();
		} catch (SQLException x) {
		    printException(x);
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

    public static ResultSet exFunction(String _functionName) {
	setBusy(true);
	ResultSet results = null;
	System.out.println("{ Executing SQL call = " + _functionName + "(); }");
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
    public static ResultSet exFunction(String _functionName, Object _params) {
	setBusy(true);
	ResultSet results = null;
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + "); }");
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
		    printException(x);
		    printStackTrace(x);
		    proc.close();
		    pgCon.setAutoCommit(true);
		} catch (SQLException x) {
		    printException(x);
		    printStackTrace(x);
		    proc.close();
		    pgCon.setAutoCommit(true);
		}
	    } catch (SQLException e) {
		printException(e);
	    }
	}
	setBusy(false);
	return results;
    }

    public static ResultSet exQuery(String _sqlStat) {
	setBusy(true);
	ResultSet results = null;
	System.out.println("{ Executing SQL Statement = " + _sqlStat + "; }");
	try {
	    if (isConnected()) {
		Statement _stat = pgCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		results = _stat.executeQuery(_sqlStat);
	    }
	} catch (PSQLException x) {
	    printException(x);
	} catch (SQLException x) {
	    printException(x);
	}
	setBusy(false);
	return results;
    }

    public static boolean executeQuery(String _sqlStat) {
	//Devuelve: true si se pudo ejecutar
	//false en caso contrario
	boolean results = false;
	setBusy(true);
	System.out.println("{ Executing SQL Statement = " + _sqlStat + "; }");
	try {
	    if (isConnected()) {
		Statement _stat = pgCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		_stat.execute(_sqlStat);
		results = true;
	    }
	} catch (PSQLException x) {
	    Advisor.printException(x);
	} catch (SQLException x) {
	    Advisor.printException(x);
	}
	setBusy(false);
	return results;
    }

    /**
     * @deprecated
     * @param _sqlStat
     * @return
     */
    public static String insertQuery(String _sqlStat) {
	setBusy(true);
	ResultSet results = null;
	System.out.println("{ Executing SQL Statement = " + _sqlStat + "; }");
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

    private static boolean doExecution(CallableStatement _proc) {
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
	    printException(x);
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

    public static boolean getBoolean(String _functionName, Object _params) {
	setBusy(true);
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + "); }");
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
		printException(x);
		printStackTrace(x);
	    } catch (SQLException x) {
	        printException(x);
		printStackTrace(x);
	    }
	}
	setBusy(false);
	return results;
    }

    public static String getString(String _functionName, Object _params) {
	setBusy(true);
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + "); }");
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

    public static Date getDate(String _functionName, Object _params) {
	setBusy(true);
	Date results = null;
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + "); }");
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
		 //public static boolean getBoolean(String _functionName, Object _params) {
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

    public static int getInt(String _functionName, Object _params) {
	setBusy(true);
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + "); }");
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
		    printException(x);
		    printStackTrace(x);
		} catch (SQLException e) {
		    printException(e);
		    printStackTrace(e);
		}
		proc.close();
		pgCon.setAutoCommit(true);
	    } catch (SQLException x) {
	        printException(x);
		printStackTrace(x);
	    }
	}
	setBusy(false);
	return _result;
    }

    public static double getDouble(String _functionName, Object _params) {
	setBusy(true);
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + "); }");
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
		    printException(e);
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
    public static String updateQuery(String _sqlStat) {
	setBusy(true);
	String returnValue = "";
	System.out.println("{ Executing SQL stat " + _sqlStat + "); }");
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

    public static int deleteQuery(String _functionName, Object _params) {
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + "); }");
	int returnValue = -1;
	if (isConnected()) {
	    int result = JOptionPane.showConfirmDialog((Component)null, "Eliminar este registro?", "Eliminar", JOptionPane.YES_NO_OPTION);
	    if (result == JOptionPane.YES_OPTION) {
		setBusy(true);
		returnValue = getInt(_functionName, _params);
		setBusy(false);
	    }
	}
	return returnValue;
    }

    public static void setSQLUser(String _user) {
	SQLUser = _user;
    }

    public static void setSQLPass(String _pass) {
	SQLPass = _pass;
    }

    public static void setDataBase(String _database) {
	String _localhost = "localhost";
	try {
	    _localhost = InetAddress.getLocalHost().getHostAddress().toString();
	} catch (UnknownHostException e) {
	    e.printStackTrace();   
	}
	DataBase = "jdbc:postgresql://" + _localhost + ":" + SSLConfig.PG_CLIENT_PORT + "/" + _database;
	System.out.println(DataBase);
    }

    public static void setDataBaseString(String _database) {
	DataBase = _database;
    }

    public static boolean tryToConnect(String _SQLUser, String _SQLPass) {
	try {
	    try {
		Class.forName(SQLDriver);
	    } catch (ClassNotFoundException x) {
		System.out.println("El controlador de la base de datos no está instalado correctamente");
	    }
	    long start = System.currentTimeMillis();
	    Connection tmp = DriverManager.getConnection(DataBase, _SQLUser, _SQLPass);
	    long end = System.currentTimeMillis();
	    connectionLatency = end - start;
	    tmp.close();
	    return true;
	} catch (SQLException x) {
	    printStackTrace(x);
	    return false;
	}
    }

    public static void closeConnection() {
	try {
	    if (pgCon != null) {
		pgCon.close();
	    }
	} catch (SQLException x) {
	    printStackTrace(x);
	}
	isBusy = 0;
	setSQLPass("");
	Environment.jpStatusBar.setAction("Desconectado :(");
	pgCon = null;
    }

    /** @deprecated
     * @since 05-09-2007
     * */
    public static boolean exActualizar(char ch, String _sqlStat) {
	setBusy(true);
	System.out.println("{ Executing SQL stat " + _sqlStat + "); }");
	boolean bol = true;
	String accion = "";
	if (isConnected()) {
	    try {
		Statement Stat = pgCon.createStatement();
		if (ch == 'a') {
		    accion = " insertar ";
		    Stat.executeUpdate(_sqlStat);
		} else if (ch == 'b') {
		    int result = JOptionPane.showConfirmDialog((Component)null, "¿Está seguro de que desea eliminar el registro?", "Eliminación", JOptionPane.YES_NO_OPTION);
		    if (result == JOptionPane.YES_OPTION) {
			accion = " eliminar ";
			Stat.executeUpdate(_sqlStat);
		    } else
			bol = false;
		} else if (ch == 'm') {
		    //int result = JOptionPane.showConfirmDialog((Component) null, "EstÃ¡ seguro que desea guardar los cambios?", "Actualización",JOptionPane.YES_NO_OPTION);
		    int result = JOptionPane.showConfirmDialog((Component)null, "¿Está seguro de que desea guardar los cambios?", "Actualización", JOptionPane.YES_NO_OPTION);
		    if (result == JOptionPane.YES_OPTION) {
			accion = " actualizar ";
			Stat.executeUpdate(_sqlStat);
		    } else
			bol = false;
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
    public static String getCampo(String SQLQuery) {
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
    public static String getCampo(String _SQLQuery, String _columna) {
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

    public static void GuardaImagen(File _imgFile, String _query) {
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
     * @param condition     String: condicion WHERE SQL para un registro especófico
     */
    public static boolean saveImageSQL(File imgFile, String table, String campoImagen, String condition) {
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

    /**
     *
     * @param image       byte[] Imagen
     * @param table       String: esquema.tabla
     * @param imageField  String: campo donde se almacena la imagen
     * @param condition   String: condicion WHERE SQL para un registro específico
     */
    public static boolean saveImage(BufferedImage image, String table, String imageField, String condition) {
	setBusy(true);
	boolean returns = false;
	if (isConnected()) {
	    if (image != null) {
		try {
		    System.out.println("Saving image to " + table + " at field " + imageField + " " + condition);
		    byte[] imageBytes = LibIMG.getBytesFromImage(image);
		    ByteArrayInputStream fis = new ByteArrayInputStream(imageBytes);
		    PreparedStatement ps = pgCon.prepareStatement("UPDATE " + table + " SET " + imageField + " =? " + condition);
		    ps.setBinaryStream(1, fis, (int)imageBytes.length);
		    if (ps.executeUpdate() != 0) {
			fis.close();
			returns = true;
		    } else {
			fis.close();
		    }
		} catch (Exception x) {
		    printException(x);
		}
	    } else {
		messageBox("No se pudo leer el archivo", "Error");
	    }
	} else {
	    messageBox("No se pudo grabar la imagen", "Error");
	}
	setBusy(false);
	return returns;
    }

    public static long getConnectionLatency() {
	return connectionLatency;
    }

    private static void setDateTime() {
	setBusy(true);
	ResultSet rsSessionData = exFunction("org.getSessionData", "");
	try {
	    if (rsSessionData.next()) {
		Environment.currentDate = rsSessionData.getString("date");
		Environment.currentTime = rsSessionData.getString("time");
		Environment.currentDay = rsSessionData.getString("day");
		Environment.currentMonth = rsSessionData.getString("month");
		Environment.currentYear = rsSessionData.getString("year");
		Environment.currentDayNumber = rsSessionData.getString("daynumber");
		Environment.currentDayName = rsSessionData.getString("dayname");
		Environment.currentDayNumberOfYear = rsSessionData.getString("daynumberofyear");
		Environment.currentWeek = rsSessionData.getString("week");
		Environment.firstDayOfYear = rsSessionData.getString("year") + "-01-01";
		Environment.setUniversalTime();
		Environment.idUser = rsSessionData.getInt("iduser");
	    }
	} catch (SQLException x) {
	    printStackTrace(x);
	}
	ResultSet rsUserData = exFunction("org.getUserData", "");
	try {
	    if (rsUserData.next()) {
		Persons person = new Persons(rsUserData.getInt("idperson"));
		person.setIdIdentificationType(rsUserData.getInt("ididentificationtype"));
		person.setIdentificationNumber(rsUserData.getString("identificationnumber"));
		person.setName(rsUserData.getString("name"));
		person.setLastName(rsUserData.getString("lastname"));
		//person.setUser(new User(rsUserData.getInt("iduser")));
		Environment.person = person;
	    } else {
		messageBox("El usuario no existe en la Base de Datos", "Error fatal");
		System.exit(-1);
	    }
	} catch (SQLException x) {
	    printStackTrace(x);
	}
	/*ResultSet rsSessionData = exFunction("org.getDataSession", "");
	try {
	    if (rsSessionData.next()) {
		Environment.currentDate = rsSessionData.getString("date");
		Environment.currentTime = rsSessionData.getString("time");
		Environment.currentDay = rsSessionData.getString("day");
		Environment.currentMonth = rsSessionData.getString("month");
		Environment.currentYear = rsSessionData.getString("year");
		Environment.currentDayNumber = rsSessionData.getString("daynumber");
		Environment.currentDayName = rsSessionData.getString("dayname");
		Environment.currentDayNumberOfYear = rsSessionData.getString("daynumberofyear");
		Environment.currentWeek = rsSessionData.getString("week");
		System.out.println("--> "+rsSessionData.getInt("idperson"));
		Persons person = new Persons(rsSessionData.getInt("idperson"));
		person.setIdIdentificationType(rsSessionData.getInt("ididentificationtype"));
		person.setIdentificationNumber(rsSessionData.getLong("identificationnumber"));
		person.setName(rsSessionData.getString("name"));
		person.setLastName(rsSessionData.getString("lastname"));
		person.setUser(new User(rsSessionData.getInt("iduser")));
		Environment.person = person;
		Environment.setUniversalTime();
	    } else {
		messageBox("El usuario no existe en la Base de Datos","Error fatal");
		System.exit(-1);
	    }
	} catch (SQLException x) {
	    printStackTrace(x);
	}*/
	setBusy(false);
    }

    public static Connection getConnection() {
	return pgCon;
    }

    public static String getDataBase() {
	return DataBase;
    }

    public static void beginTransaction() {
	try {
	    pgCon.setAutoCommit(false);
	    savePoint = pgCon.setSavepoint();
	} catch (SQLException x) {
	    messageBox(x.getMessage(), "Error");
	}
    }

    public static void commitTransaction() {
	if (savePoint != null) {
	    try {
		pgCon.releaseSavepoint(savePoint);
	    } catch (SQLException x) {
		messageBox(x.getMessage(), "Error");
	    }
	}
    }

    public static void rollbackTransaction() {
	if (savePoint != null) {
	    try {
		pgCon.rollback(savePoint);
	    } catch (SQLException x) {
		messageBox(x.getMessage(), "Error");
	    }
	}
    }

    public static BufferedImage getImage(String table, String imageField, String condition) {
	setBusy(true);
	System.out.println("Getting image from " + table + " at field " + imageField + " " + condition);
	byte[] _result = null;
	if (isConnected()) {
	    try {
		pgCon.setAutoCommit(false);
		Statement ps = pgCon.createStatement();
		ResultSet rs = ps.executeQuery("SELECT " + imageField + " FROM " + table + " " + condition);
		try {
		    if (rs.next()) {
			_result = rs.getBytes(imageField);
		    }
		} catch (SQLException e) {
		    printException(e);
		}
		ps.close();
		pgCon.setAutoCommit(true);
	    } catch (SQLException e) {
		printException(e);
	    }
	}
	setBusy(false);
	return LibIMG.getImageFromBytes(_result);
    }

    public static void setVerboseMode(boolean _verbose) {
	verbose = _verbose;
    }

    private static void printException(Exception _e) {
	if (verbose) {
	    Advisor.printException(_e);
	}
    }

    private static void printStackTrace(Exception _e) {
	if (verbose) {
	    _e.printStackTrace();
	}
    }

    private static void messageBox(String _message, String _title) {
	if (verbose) {
	    Advisor.messageBox(_message, _title);
	}
    }

    private static void setBusy(boolean _busy) {
	try {
	    if (_busy) {
		isBusy++;
		if (isBusy == 1) {
		    busyLabel.setBounds(Environment.getActiveDesktop().getBounds());
		    Environment.getActiveDesktop().add(busyLabel, 0);
		}
	    } else {
		isBusy--;
		if (isBusy < 1) {
		    isBusy = 0;
		    Environment.getActiveDesktop().remove(busyLabel);
		}
	    }
	    Environment.getActiveDesktop().repaint();
	} catch (Exception x) {
	    x.toString();//ignore
	}
    }

    public static Vector getVector(String _sqlStat) {
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

    public static Vector getVector(String _functionName, Object _params) {
	setBusy(true);
	Vector _returns = new Vector();
	System.out.println("{ Executing SQL call = " + _functionName + "(" + _params + "); }");
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
		    printException(e);
		    printStackTrace(e);
	        } catch (NullPointerException e) {
	            printException(e);
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
