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