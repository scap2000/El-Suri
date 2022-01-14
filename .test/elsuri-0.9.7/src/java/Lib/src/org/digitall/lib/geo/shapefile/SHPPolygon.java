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
 * SHPPolygon.java
 *
 * */
package org.digitall.lib.geo.shapefile;

import java.awt.geom.Point2D;

import java.util.Vector;

import org.digitall.lib.geo.coordinatesystems.CoordinateSystems;
import org.digitall.lib.geo.coordinatesystems.CoordinateSystems.BadCoordinatesException;
import org.digitall.lib.geo.coordinatesystems.GKCoord;
import org.digitall.lib.geo.coordinatesystems.LatLongCoord;
import org.digitall.lib.geo.coordinatesystems.UTMCoord;

public class SHPPolygon {

    private int shapeType;
    //Always 5 for Polygon Type
    private double[] box = new double[4];
    //Bounding box
    private int numParts;
    //Number of parts;
    private int numPoints;
    //Total number of points
    private int[] parts;
    //Index to first point in part
    private int[] partPoints;
    //Points for each part
    private Point2D[] points;
    //Points for all parts;
    private UTMCoord utm = new UTMCoord();
    private GKCoord gk = new GKCoord();
    private LatLongCoord ll = new LatLongCoord();

    public SHPPolygon() {
    }

    public void setBox(double[] _box) {
	box = _box;
    }

    public double[] getBox() {
	return box;
    }

    public void setNumParts(int _numParts) {
	numParts = _numParts;
	parts = new int[numParts];
	partPoints = new int[numParts];
    }

    public int getNumParts() {
	return numParts;
    }

    public void setNumPoints(int _numPoints) {
	numPoints = _numPoints;
	points = new Point2D.Double[numPoints];
    }

    public int getNumPoints() {
	return numPoints;
    }

    public void setParts(int[] _parts) {
	parts = _parts;
    }

    public int[] getParts() {
	return parts;
    }

    public void setPoints(Point2D[] _points) {
	points = _points;
	if (!hasClosedRings()) {
	    //Proced.Mensaje("El polÃ­gono no estÃ¡ cerrado", "Error");
	}
    }

    public Point2D[] getPoints() {
	return points;
    }

    public void setShapeType(int _shapeType) {
	if (_shapeType == ShapeTypes.POLYGON) {
	    shapeType = _shapeType;
	} else {
	    System.err.println("Tipo de archivo incorrecto, no es del tipo POLGYON");
	}
    }

    public int getShapeType() {
	return shapeType;
    }

    public void setPartPoints(int _numPart, int _partPoints) {
	partPoints[_numPart] = _partPoints;
    }

    public int getPartPoints(int _numPart) {
	return partPoints[_numPart];
    }

    public void showInfo() {
	//System.out.println("Show Poly Info: " + getNumPoints() + " puntos");
	for (int j = 0; j < getNumPoints(); j++) {
	    System.out.println("[" + j + "]: " + points[j]);
	}
    }

    public String getSQLString(String _scheme, String _table) {
	String sql = "INSERT INTO \"";
	if (!_scheme.equals("")) {
	    sql += _scheme + "\".\"";
	}
	sql += _table + "\" " + "(\"mapkey\",the_geom) VALUES ('0',GeometryFromText('POLYGON((";
	for (int j = 0; j < getNumPoints() - 1; j++) {
	    sql += points[j].getX() + " " + points[j].getY() + ",";
	}
	sql += points[getNumPoints() - 1].getX() + " " + points[getNumPoints() - 1].getY();
	sql += "))',-1) );";
	return sql;
    }

    public String getSQLString(String _scheme, String _table, String _columnaID, int _ID) {
	Vector sqlStr = new Vector();
	String sql = "INSERT INTO \"";
	if (!_scheme.equals("")) {
	    sql += _scheme + "\".\"";
	}
	sql += _table + "\" " + "(\"mapkey\", " + _columnaID + ", the_geom) VALUES ('0', " + _ID + ", GeometryFromText('POLYGON((";
	sqlStr.addElement(sql);
	sql = "";
	for (int j = 0; j < getNumPoints() - 1; j++) {
	    sql += points[j].getX() + " " + points[j].getY() + ",";
	    if (j % 100 == 0) {
		System.out.println("Punto " + j + " de " + getNumPoints());
		sqlStr.addElement(sql);
		sql = "";
	    }
	}
	sql += points[getNumPoints() - 1].getX() + " " + points[getNumPoints() - 1].getY();
	sql += "))',-1) );";
	sqlStr.addElement(sql);
	sql = "";
	for (int j = 0; j < sqlStr.size(); j++) {
	    sql += sqlStr.elementAt(j).toString();
	}
	return sql;
    }

    public void convertCoords(int _from, int _to, int _zone, int _faja) {
	Point2D.Double xy = new Point2D.Double();
	xy = convertPoint(_from, _to, box[0], box[1], _zone, _faja);
	box[0] = xy.getX();
	box[1] = xy.getY();
	xy = convertPoint(_from, _to, box[2], box[3], _zone, _faja);
	box[2] = xy.getX();
	box[3] = xy.getY();
	for (int i = 0; i < getNumPoints() - 1; i++) {
	    points[i] = convertPoint(_from, _to, points[i].getX(), points[i].getY(), _zone, _faja);
	}
	points[getNumPoints() - 1] = points[0];
    }

    public Point2D.Double convertPoint(int _from, int _to, double x, double y, int _zone, int _faja) {
	Point2D.Double xy = new Point2D.Double(x, y);
	if ((_from == CoordinateSystems.LL) && (_to == CoordinateSystems.UTM)) {
	    UTMCoord _utm = CoordinateSystems.geo2utm(y, x);
	    xy.setLocation(_utm.getEasting(), _utm.getNorthing());
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
	return xy;
    }

    public boolean hasClosedRings() {
	if (points[0].distance(points[getNumPoints() - 1]) == 0) {
	    return true;
	} else {
	    return false;
	}
    }

    public void closeRings() {
	Point2D[] _points;
	_points = new Point2D[getNumPoints() + 1];
	for (int i = 0; i < getNumPoints(); i++) {
	    _points[i] = points[i];
	}
	setNumPoints(getNumPoints() + 1);
	_points[getNumPoints() - 1] = _points[0];
	setPoints(_points);
    }

}
