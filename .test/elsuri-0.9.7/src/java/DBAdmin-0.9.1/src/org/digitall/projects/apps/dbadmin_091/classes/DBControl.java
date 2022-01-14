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
 * DBControl.java
 *
 * */
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
