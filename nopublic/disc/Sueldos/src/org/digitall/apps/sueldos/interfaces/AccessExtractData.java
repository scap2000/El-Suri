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
 * AccessExtractData.java
 *
 * */
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
