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
 * ShapeTypes.java
 *
 * */
package org.digitall.lib.geo.shapefile;

public class ShapeTypes {

    //Shape types
    public static final int UNKNOWN = -1;
    public static final int NULLSHAPE = 0;
    public static final int POINT = 1;
    public static final int POLYLINE = 3;
    public static final int MULTIPOLYLINE = 4;
    public static final int POLYGON = 5;
    public static final int MULTIPOINT = 8;
    public static final int POINTZ = 11;
    public static final int POLYLINEZ = 13;
    public static final int POLYGONZ = 15;
    public static final int MULTIPOINTZ = 18;
    public static final int POINTM = 21;
    public static final int POLYLINEM = 23;
    public static final int POLYGONM = 25;
    public static final int MULTIPOINTM = 28;
    public static final int MULTIPATCH = 31;
    public static final int MULTIPOLYGON = 101;
    private int shapeType = -1;

    public ShapeTypes(int _type) {
	shapeType = _type;
	switch (shapeType) {
	    case NULLSHAPE :
		System.err.println("Null Shape SHP");
		break;
	    case POINT :
		SHPPoint _point = new SHPPoint();
		break;
	    case POLYLINE :
		System.err.println("SE DEBE CREAR UNA POLYLINE");
		break;
	    case MULTIPOLYLINE :
	        System.err.println("SE DEBE CREAR UNA MULTIPOLYLINE");
	        break;
	    case POLYGON :
		SHPPolygon _polygon = new SHPPolygon();
		break;
	    case MULTIPOINT :
		System.err.println("SE DEBE CREAR UN MULTIPOINT");
		break;
	    case POINTZ :
		System.err.println("SE DEBE CREAR UN POINTZ");
		break;
	    case POLYLINEZ :
		System.err.println("SE DEBE CREAR UNA POLYLINEZ");
		break;
	    case POLYGONZ :
		System.err.println("SE DEBE CREAR UN POLYGONZ");
		break;
	    case MULTIPOINTZ :
		System.err.println("SE DEBE CREAR UN MULTIPOINTZ");
		break;
	    default :
		System.err.println("Tipo de Geometría no soportado");
		break;
	}
    }

    public String getSQLAddGeometryColumn(String _scheme, String _table) {
	String _query = "";
	switch (shapeType) {
	    case POLYGON :
		_query = "SELECT AddGeometryColumn('" + _scheme + "','" + _table + "','the_geom','-1','POLYGON',2);";
		break;
	    case POINT :
		_query = "SELECT AddGeometryColumn('" + _scheme + "','" + _table + "','the_geom','-1','POINT',2);";
		break;
	    default :
		_query = "";
	}
	return _query;
    }

    public String getSQLCreateTable(String _scheme, String _table, String _idColumn) {
	String _query = "";
	switch (shapeType) {
	    case POLYGON :
		_query = "CREATE TABLE \"" + _scheme + "\".\"" + _table + "_polygons\" (gid serial PRIMARY KEY, " + "\"mapkey\" varchar(128), " + _idColumn + " integer NOT NULL) WITH OIDS;\n";
		_query += "SELECT AddGeometryColumn('" + _scheme + "','" + _table + "_polygons','the_geom','-1','POLYGON',2);";
		break;
	    case POINT :
		_query = "CREATE TABLE \"" + _scheme + "\".\"" + _table + "_points\" (gid serial PRIMARY KEY, " + "\"mapkey\" varchar(128), " + _idColumn + " integer NOT NULL) WITH OIDS;\n";
		_query += "SELECT AddGeometryColumn('" + _scheme + "','" + _table + "_points','the_geom','-1','POINT',2);";
		break;
	    default :
		break;
	}
	return _query;
    }

    public static String getShapeType(int _shapeType) {
	String _result = "";
	switch (_shapeType) {
	    case NULLSHAPE :
		_result = "NULLSHAPE";
		break;
	    case POINT :
		_result = "POINT";
		break;
	    case POLYLINE :
	        _result = "POLYLINE";
	        break;
	    case MULTIPOLYLINE :
	        _result = "MULTIPOLYLINE";
	        break;
	    case POLYGON :
		_result = "POLYGON";
		break;
	    case MULTIPOINT :
		_result = "MULTIPOINT";
		break;
	    case POINTZ :
		_result = "POINTZ";
		break;
	    case POLYLINEZ :
		_result = "POLYLINEZ";
		break;
	    case POLYGONZ :
		_result = "POLYGONZ";
		break;
	    case MULTIPOINTZ :
		_result = "MULTIPOINTZ";
		break;
	    case POINTM :
		_result = "POINTM";
		break;
	    case POLYLINEM :
		_result = "POLYLINEM";
		break;
	    case POLYGONM :
		_result = "POLYGONM";
		break;
	    case MULTIPOINTM :
		_result = "MULTIPOINTM";
		break;
	    case MULTIPATCH :
		_result = "MULTIPATCH";
		break;
	    case MULTIPOLYGON :
		_result = "MULTIPOLYGON";
		break;
	    case UNKNOWN :
	        _result = "UNKNOWN";
	        break;
	}
	return _result;
    }

}
