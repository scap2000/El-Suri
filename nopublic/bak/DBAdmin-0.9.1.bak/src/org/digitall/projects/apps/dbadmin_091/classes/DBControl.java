package org.digitall.projects.apps.dbadmin_091.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.digitall.lib.sql.LibSQL;

public class DBControl{

    public DBControl(){
    
    }
    
    public static boolean existFunction(String _nombreFuncion,String _params){
	String nombreFuncion = _nombreFuncion.substring(_nombreFuncion.indexOf(".")+1,_nombreFuncion.length());
	ResultSet result = LibSQL.exQuery("SELECT proname FROM pg_proc WHERE proname = '" + nombreFuncion +"'");
	boolean retorno = false;
	try {
	    if(result.next()){
		/*ResultSet r = LibSQL.exFunction(_nombreFuncion,_params);
		if(r.next()){*/
		    retorno = true;
		//}
	    }
	} catch (SQLException e) {
	    // TODO
	    retorno = false;
	}
	return retorno;
    }
    
    /**2009-12-07(moraless)*/
    public static boolean existFunction(String _esquema, String _nombreFuncion,String _params){
	String nombreFuncion = _nombreFuncion + "("+_params+")";
	ResultSet result = LibSQL.exQuery("SELECT proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')' as declaration FROM pg_catalog.pg_proc p INNER JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace  INNER JOIN pg_catalog.pg_language pl ON pl.oid = p.prolang WHERE NOT p.proisagg  AND ((lanname = 'plpgsql') OR (lanname = 'sql')) AND n.nspname = '" +
	_esquema + "' AND ( (proname || '(' || pg_catalog.oidvectortypes(p.proargtypes) ||  ')')= '" + nombreFuncion + "') ORDER BY p.proname");
	//
	boolean retorno = false;
	try {
	    if(result.next()){
		/*ResultSet r = LibSQL.exFunction(_nombreFuncion,_params);
		if(r.next()){*/
		    retorno = true;
		//}
	    }
	} catch (SQLException e) {
	    // TODO
	    retorno = false;
	}
	return retorno;
    }
    
    public static boolean existTable(String _nombreEsquema,String _nombreTabla){
	boolean retorno = false;
	String consulta ="SELECT relname FROM pg_class WHERE (SELECT oid FROM pg_namespace ns WHERE '" + _nombreEsquema + "' = ns.nspname) = pg_class.relnamespace AND relname = '" + _nombreTabla +"'";
	//SELECT relname FROM pg_class bc ,pg_namespace ns WHERE bc.relname = 'publicaccess' AND bc.relnamespace = ns.oid
	ResultSet result = LibSQL.exQuery(consulta);
	try {
	    if(result.next()){
		retorno = true;
	    }
	} catch (SQLException e) {
	    // TODO
	}
	return retorno;
    }
    
    public static boolean existScheme(String _nombreEsquema){
	boolean retorno = false;
	String consulta ="SELECT nspname FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' AND nspname = '" +	_nombreEsquema + "';";
	ResultSet result = LibSQL.exQuery(consulta);
	try {
	    if(result.next()){
		retorno = true;
	    }
	} catch (SQLException e) {
	    // TODO
	}
	return retorno;
    }
}
