package org.digitall.lib.geo.shapefile;

import java.awt.geom.Point2D;

import org.digitall.lib.geo.coordinatesystems.CoordinateSystems;
import org.digitall.lib.geo.coordinatesystems.CoordinateSystems.BadCoordinatesException;
import org.digitall.lib.geo.coordinatesystems.GKCoord;
import org.digitall.lib.geo.coordinatesystems.LatLongCoord;
import org.digitall.lib.geo.coordinatesystems.UTMCoord;

public class SHPPoint {

    private int shapeType;
    //Always 5 for Polygon Type
    private double x;
    //X Coordinate
    private double y;
    //Y Coordinate
    private UTMCoord utm = new UTMCoord();
    private GKCoord gk = new GKCoord();
    private LatLongCoord ll = new LatLongCoord();

    public SHPPoint() {

    }

    public void setShapeType(int _shapeType) {
	if (_shapeType == ShapeTypes.POINT) {
	    this.shapeType = _shapeType;
	} else {
	    System.err.println("Tipo de archivo incorrecto, no es del tipo POINT");
	}
    }

    public int getShapeType() {
	return shapeType;
    }

    public void showInfo() {
	System.out.println("Show Point Info: ");
	System.out.println("[X]: " + x);
	System.out.println("[Y]: " + y);
    }

    public String getSQLString(String _scheme, String _table) {
	String sql = "INSERT INTO \"";
	if (!_scheme.equals("")) {
	    sql += _scheme + "\".\"";
	}
	sql += _table + "\" " + "(\"mapkey\",the_geom) VALUES ('0',GeometryFromText('POINT(";
	sql += x + " " + y;
	sql += ")',-1) );";
	return sql;
    }

    public String getSQLString(String _scheme, String _table, String _columnaID, String _ID) {
	String sql = "INSERT INTO \"";
	if (!_scheme.equals("")) {
	    sql += _scheme + "\".\"";
	}
	sql += _table + "\" " + "(\"mapkey\", " + _columnaID + ", the_geom) VALUES ('0', " + _ID + ", GeometryFromText('POINT(";
	sql += x + " " + y;
	sql += ")',-1) );";
	return sql;
    }

    public void setX(double _x) {
	x = _x;
    }

    public double getX() {
	return x;
    }

    public void setY(double _y) {
	y = _y;
    }

    public double getY() {
	return y;
    }

    public void convertCoords(int _from, int _to, int _zone, int _faja) {
	Point2D.Double xy = new Point2D.Double(x, y);
	if ((_from == CoordinateSystems.LL) && (_to == CoordinateSystems.UTM)) {
	    utm = CoordinateSystems.geo2utm(y, x);
	} else if ((_from == CoordinateSystems.LL) && (_to == CoordinateSystems.GK)) {
	    gk = CoordinateSystems.geo2gk(y, x);
	} else if ((_from == CoordinateSystems.UTM) && (_to == CoordinateSystems.LL)) {
	    ll = CoordinateSystems.utm2geo(x, y, _zone);
	} else if ((_from == CoordinateSystems.UTM) && (_to == CoordinateSystems.GK)) {
	    gk = CoordinateSystems.utm2gk(x, y, _zone);
	} else if ((_from == CoordinateSystems.GK) && (_to == CoordinateSystems.LL)) {
            try {
                ll = CoordinateSystems.gk2geo(x, y, _faja);
            } catch (BadCoordinatesException bce) {
                // TODO: Add catch code
                bce.printStackTrace();
            }
	} else if ((_from == CoordinateSystems.GK) && (_to == CoordinateSystems.UTM)) {
	    utm = CoordinateSystems.gk2utm(x, y, _faja);
	}
	x = xy.getX();
	y = xy.getY();
    }

}
