package org.digitall.apps.sueldos.interfaces;

import java.sql.*;

public class AccessJDBCUtil {

    private static final String accessDBURLPrefix = "jdbc:odbc:";
    private static final String accessDBURLSuffix = "";
    private static java.sql.Connection conn = null;

    static {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("JdbcOdbc Bridge Driver not found!");
        }
    }

    /** Creates a Connection to a Access Database */
     public static Connection conectAccessDB(String filename,String usr,String pass) throws SQLException {
           filename = filename.replace('\\', '/').trim();
           String databaseURL = accessDBURLPrefix + filename + accessDBURLSuffix;
           conn = DriverManager.getConnection(databaseURL, usr, pass);
           return conn;
         }

    public static ResultSet exQuery(String _query, Connection _conn) {
        ResultSet result = null;
        conn = _conn;
        try {
            Statement stmt = conn.createStatement();
            if (stmt.execute(_query)) {
                result = stmt.getResultSet();
            } else {
                result = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
