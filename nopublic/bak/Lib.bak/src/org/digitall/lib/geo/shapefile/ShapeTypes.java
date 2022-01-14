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
