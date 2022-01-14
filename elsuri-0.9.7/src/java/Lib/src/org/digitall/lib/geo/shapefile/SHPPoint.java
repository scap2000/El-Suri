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
 * SHPPoint.java
 *
 * */
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
