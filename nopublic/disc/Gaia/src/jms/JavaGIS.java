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
 * JavaGIS.java
 *
 * */
package jms;
import java.sql.*;
import java.util.*;
import java.lang.*;
import org.postgis.*;
import org.postgresql.*;

public class JavaGIS
{

	public static void main(String[] args)
	{
		java.sql.Connection conn;

		String dbname = "master";
		String dbuser = "consulta";
		String dbpass = "consulta";
		String dbhost = "172.16.4.6";
		String dbport = "5432";

		String dbtable = "gis.parcelas_sa";

		String dropSQL = "drop table " + dbtable;
		String createSQL = "create table " + dbtable + " (geom geometry, id int4)";
		String insertPointSQL = "insert into " + dbtable + " values ('POINT (10 10 10)',1)";
		String insertPolygonSQL = "insert into " + dbtable + " values ('POLYGON ((0 0 0,0 10 0,10 10 0,10 0 0,0 0 0))',2)";

		try {

			System.out.println("Creating JDBC connection...");
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://" + dbhost + ":" + dbport + "/" + dbname;
			conn = DriverManager.getConnection(url, dbuser, dbpass);
			System.out.println("Adding geometric type entries...");
//			((org.postgresql.Connection)conn).addDataType("geometry","org.postgis.PGgeometry");
//			((org.postgresql.Connection)conn).addDataType("box3d","org.postgis.PGbox3d");
			java.sql.Statement s = conn.createStatement();
			System.out.println("Creating table with geometric types...");
			//table might not yet exist
			try {
//			    s.execute(dropSQL);
			} catch(Exception e) {
			    e.printStackTrace();
			}
//			s.execute(createSQL);
			System.out.println("Inserting point...");
//			s.execute(insertPointSQL);
			System.out.println("Inserting polygon...");
//			s.execute(insertPolygonSQL);
			System.out.println("Done.");
			s = conn.createStatement();
			System.out.println("Querying table...");
			java.sql.ResultSet r = s.executeQuery("select asText(the_geom),idparcela from " + dbtable + " limit 2");
			while( r.next() )
			{
				int id = r.getInt(2);
//        PGgeometry geom = (PGgeometry)r.getObject(1);
        Object geom = r.getObject(1);
				System.out.println("Row " + id + ":");
        System.out.println(geom.toString());
        PGgeometry kk = new PGgeometry(geom.toString());
        Polygon pl = new Polygon(geom.toString());
			}
			s.close();
			conn.close();
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
	}
}