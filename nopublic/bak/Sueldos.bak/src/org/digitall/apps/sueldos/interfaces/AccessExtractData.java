package org.digitall.apps.sueldos.interfaces;

import java.sql.*;

import java.io.*;

public class AccessExtractData {
    private static Connection connSUPE = null;
    private static Connection connPERS = null;
    public static void main(String[] args) {
        
    }

    /** Prints badly ResultSet to PrintStream */
    public static void printResultSet(PrintStream p, ResultSet rs,
                                      String title) {
        int count = 0;
        if (rs != null) {
            try {
                ResultSetMetaData metaData = rs.getMetaData();
                int cols = metaData.getColumnCount();
                p.println("\n--------------------------\n" +
                        title + "\n--------------------------");
                //Muestra el nombre de las columnas devueltas por el resultset
                for (int i = 1; i <= cols; i++) {
                    p.print(metaData.getColumnLabel(i) + "\t");
                }
                p.println("\n--------------------------");
                while (rs.next()) {
                    for (int i = 1; i <= cols; i++) {
                        p.print(rs.getString(i) + "\t");
                    }
                    p.println("\n--------------------------");
                    count++;
                }
            } catch (SQLException e) {

            }
            p.println("Filas: " + count);
        } else {
            p.println("\n ES NULO");
        }
    }

    public static ResultSet extraerFichadas(String _fechaDesde, String _fechaHasta, Connection _conn){
          ResultSet rs = AccessJDBCUtil.exQuery("SELECT FICHADA.FIC_ID,LEGAJO.LEG_LEGAJO,FICHADA.FIC_TARJETA,FICHADA.FIC_FECHA,FICHADA.FIC_HORA,FICHADA.FIC_ENTSAL,FICHADA.FIC_RELOJ,FICHADA.FIC_ORIGEN,FICHADA.FIC_NOVEDAD FROM FICHADA LEFT JOIN LEGAJO ON LEGAJO.LEG_TARJETA = FICHADA.FIC_TARJETA  WHERE  cdate(FICHADA.FIC_FECHA) >= cdate('"+_fechaDesde+"') AND cdate(FICHADA.FIC_FECHA) <= cdate('"+_fechaHasta+"') ORDER BY FICHADA.FIC_ID",_conn);
          return rs;
          
        }
        
        public static ResultSet extraerTarjetasSinLegajos(Connection _conn){
          ResultSet rs = AccessJDBCUtil.exQuery("SELECT DISTINCT( FICHADA.FIC_TARJETA) FROM FICHADA  WHERE FIC_TARJETA NOT IN (SELECT LEG_TARJETA FROM LEGAJO)",_conn);
          return rs;
        }
        
        public static ResultSet extraerTarjetasConLegajosUltimoDia(Connection _conn){
          ResultSet rs = AccessJDBCUtil.exQuery("SELECT DISTINCT( FICHADA.FIC_TARJETA) FROM FICHADA  WHERE FIC_TARJETA IN (SELECT LEG_TARJETA FROM LEGAJO) AND cdate(FICHADA.FIC_FECHA) >= cdate('2010-03-01')",_conn);
          return rs;
        }
        
        public static ResultSet extraerTarjetasSinLegajosUltimoDia(Connection _conn){
          ResultSet rs = AccessJDBCUtil.exQuery("SELECT  FICHADA.FIC_TARJETA,FICHADA.FIC_FECHA,FICHADA.FIC_HORA FROM FICHADA  WHERE LEG_LEGAJO IS NULL AND cdate(FICHADA.FIC_FECHA) >= cdate('2010-03-29')",_conn);
          return rs;
        }
        
        public static ResultSet extraerTarjetasSinLegajos2(Connection _conn){
          ResultSet rs = AccessJDBCUtil.exQuery("SELECT DISTINCT(FICHADA.FIC_TARJETA) FROM FICHADA  WHERE LEG_LEGAJO IS NULL AND cdate(FICHADA.FIC_FECHA) >= cdate('2010-01-01')",_conn);
          return rs;
        }
        
        public static ResultSet extraerFichadaTarjetasSinLegajo(int _tarjeta, Connection _conn){
          ResultSet rs = AccessJDBCUtil.exQuery("SELECT FICHADA.FIC_TARJETA, FICHADA.FIC_FECHA FROM FICHADA  WHERE FIC_TARJETA = " + _tarjeta + " ORDER BY FICHADA.FIC_FECHA",_conn);
          return rs;
        }
        
        public static ResultSet extraerTarjetas(Connection _conn){
          ResultSet rs = AccessJDBCUtil.exQuery("SELECT LEGAJO.LEG_TARJETA,LEGAJO.LEG_LEGAJO FROM LEGAJO ",_conn);
          return rs;
        }
    
    public static boolean conectarDB1() {
        boolean conected = false;
        try {
            connSUPE = AccessJDBCUtil.conectAccessDB("ClockCard","Master","Masterclk");
            conected = true;
        } catch (SQLException e) {
            connSUPE = null;
        } catch (NullPointerException e) {
            connSUPE = null;
        }
        return conected;
    }
    
    public static boolean conectarDB2() {
        boolean conected = false;
        try {
            connPERS = AccessJDBCUtil.conectAccessDB("ClockCard1","Master","Masterclk");
            conected = true;
        } catch (SQLException e) {
            connPERS = null;
        } catch (NullPointerException e) {
            connSUPE = null;
        }
        return conected;
    }

    public static Connection getConnSUPE() {
        return connSUPE;
    }

    public static Connection getConnPERS() {
        return connPERS;
    }
}
