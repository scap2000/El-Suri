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
 * ESRIPolygon.java
 *
 * */
package org.digitall.lib.geo.esri;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.io.Serializable;

/*
 * $Id: Polygon2D.java,v 1.9 2001/07/22 22:02:08 johnr Exp $
 *
 * Copyright (c) 1998-2001 The Regents of the University of California.
 * All rights reserved. See the file COPYRIGHT for details.
 *
 */

/** A polygon shape. This class implements <b>java.awt.Shape</b>, and
 * consists of a series of straight-line segments. This class
 * should be used instead of GeneralPath for shapes that consist
 * only of straight-line segments and are always closed. It is
 * more efficient than GeneralPath, and allows the coordinates of
 * vertices to be modified.
 *
 * Following the convention set by the Java2D shape classes,
 * the Polygon class is an abstract class, which contains
 * two concrete inner classes, one storing floats and one
 * storing doubles.
 *
 * @version	$Revision: 1.9 $
 * @author 	John Reekie
 */
public abstract class ESRIPolygon implements Shape, Serializable {

    /** Identificador del poligono
     */
    protected int idPolygon = -1;
    private int symbol = -1;
    private double area = 0.0;
    private ESRIPoint centroid;
    private boolean visible = false;
    /** The current number of coordinates
     */
    protected int coordCount = 0;
    /** The flag that says the the polygon has been closed
     */
    protected boolean closed = false;

    /** Close the polygon. No further segments can be added.
     * If this method not called, then the path iterators will
     * treat the polygon as thought it were closed, and implicitly
     * join the most recently added vertex to the first one. However,
     * this method should generally be called, as if the last vertex
     * is the same as the first vertex, then it merges them.
     */
    public void closePath() {
	if (getX(getVertexCount() - 1) == getX(0) && getY(getVertexCount() - 1) == getY(0)) {
	    coordCount -= 2;
	}
	closed = true;
    }

    /** Return true if the given point is inside the polygon.
     * This method uses a straight-forward algorithm, where a point
     * is taken to be inside the polygon if a horizontal line
     * extended from the point to infinity intersects an odd number
     * of segments.
     */
    public boolean contains(double x, double y) {
	int crossings = 0;
	if (coordCount == 0) {
	    return false;
	}
	// Iterate over all vertices
	int i = 1;
	for (; i < getVertexCount(); ) {
	    double x1 = getX(i - 1);
	    double x2 = getX(i);
	    double y1 = getY(i - 1);
	    double y2 = getY(i);
	    // Crossing if lines intersect
	    if (x < x1 || x < x2) {
		if (y == y2) {
		    crossings++;
		} else if (y == y1) {
		    // do nothing, so that two adjacent segments
		    // don't both get counted
		} else if (Line2D.linesIntersect(x, y, Math.max(x1, x2), y, x1, y1, x2, y2)) {
		    crossings++;
		}
	    }
	    i++;
	}
	// Final segment
	double x1 = getX(i - 1);
	double y1 = getY(i - 1);
	double x2 = getX(0);
	double y2 = getY(0);
	// Crossing if lines intersect
	if (x < x1 || x < x2) {
	    if (Line2D.linesIntersect(x, y, Math.max(x1, x2), y, x1, y1, x2, y2) && (y != y1)) {
		crossings++;
	    }
	}
	// True if odd number of crossings
	return crossings % 2 == 1;
    }

    /** Return true if the given point is inside the polygon.
     */
    public boolean contains(Point2D p) {
	return contains(p.getX(), p.getY());
    }

    /** Return true if the given rectangle is entirely inside
     * the polygon. (Currently, this algorithm can be fooled by
     * supplying a rectangle that has all four corners inside the
     * polygon, but which intersects some edges.)
     */
    public boolean contains(Rectangle2D r) {
	return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /** Return true if the given rectangle is entirely inside
     * the polygon. (Currently, this algorithm can be fooled by
     * supplying a rectangle that has all four corners inside the
     * polygon, but which intersects some edges.)
     */
    public boolean contains(double x1, double y1, double w, double h) {
	double x2 = x1 + w;
	double y2 = y1 + h;
	return contains(x1, y1) && contains(x1, y2) && contains(x2, y1) && contains(x2, y2);
    }

    /** Get the integer bounds of the polygon.
     */
    public Rectangle getBounds() {
	return getBounds2D().getBounds();
    }

    /** Get the floating-point bounds of the polygon.
     */
    public abstract Rectangle2D getBounds2D();

    /** Get a path iterator over the object.
     */
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
	return getPathIterator(at);
    }

    /** Get a path iterator over the object.
     */
    public PathIterator getPathIterator(AffineTransform at) {
	return new ESRIPolygonIterator(this, at);
    }

    /** Get the number of vertices
     */
    public int getVertexCount() {
	return coordCount / 2;
    }

    /** Get the given X-coordinate
     *
     * @throws IndexOutOfBoundsException The index is out of bounds.
     */
    public abstract double getX(int index);

    /** Get the given Y-coordinate
     *
     * @throws IndexOutOfBoundsException The index is out of bounds.
     */
    public abstract double getY(int index);

    public double[] getXCoords() {
	double[] returns = new double[getVertexCount()];
	for (int i = 0; i < getVertexCount(); i++) {
	    returns[i] = getX(i);
	}
	return returns;
    }

    public double[] getYCoords() {
	double[] returns = new double[getVertexCount()];
	for (int i = 0; i < getVertexCount(); i++) {
	    returns[i] = getY(i);
	}
	return returns;
    }

    /** Test if the polygon is intersected by the given
     * rectangle. (Currently, this algorithm can be fooled by
     * supplying a rectangle that has no corners inside the
     * polygon, and does not contain any vertex of the polygon,
     * but which intersects some edges.)
     */
    public boolean intersects(Rectangle2D r) {
	return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /** Test if the polygon is intersected by the given
     * rectangle.
     */
    public boolean intersects(double x1, double y1, double w, double h) {
	double x2 = x1 + w;
	double y2 = y1 + h;
	if (coordCount == 0) {
	    return false;
	}
	// If the bounds don't intersect, then return false.
	Rectangle2D rect = new Rectangle2D.Double(x1, y1, w, h);
	if (!getBounds().intersects(rect))
	    return false;
	// return true if the polygon contains any vertex of the rectangle.
	if (contains(x1, y1) || contains(x1, y2) || contains(x2, y1) || contains(x2, y2))
	    return true;
	// return true if the rectangle contains any vertex of the polygon
	for (int i = 0; i < getVertexCount(); i++) {
	    if (rect.contains(getX(i), getY(i)))
		return true;
	}
	// return true if any line segment of the polygon crosses a line
	// segment of the rectangle.
	// This is rather long, I wonder if it could be optimized.
	// Iterate over all vertices
	for (int i = 1; i < getVertexCount(); i++) {
	    double vx1 = getX(i - 1);
	    double vx2 = getX(i);
	    double vy1 = getY(i - 1);
	    double vy2 = getY(i);
	    if (Line2D.linesIntersect(x1, y1, x1, y2, vx1, vy1, vx2, vy2)) {
		return true;
	    }
	    if (Line2D.linesIntersect(x1, y2, x2, y2, vx1, vy1, vx2, vy2)) {
		return true;
	    }
	    if (Line2D.linesIntersect(x2, y2, x2, y1, vx1, vy1, vx2, vy2)) {
		return true;
	    }
	    if (Line2D.linesIntersect(x2, y1, x1, y1, vx1, vy1, vx2, vy2)) {
		return true;
	    }
	}
	return false;
    }

    /** Add a new vertex to the end of the polygon.
     * Throw an exception of the polygon has already been closed.
     */
    public abstract void lineTo(double x, double y);

    /** Move the start point of the vertex to the given position.
     * Throw an exception if the line already contains any vertices.
     */
    public abstract void moveTo(double x, double y);

    /** Reset the polygon back to empty.
     */
    public void reset() {
	coordCount = 0;
	closed = false;
    }

    /** Set the given X-coordinate.
     *
     * @throws IndexOutOfBoundsException The index is out of bounds.
     */
    public abstract void setX(int index, double x);

    /** Set the given Y-coordinate
     *
     * @throws IndexOutOfBoundsException The index is out of bounds.
     */
    public abstract void setY(int index, double y);

    /** Transform the polygon with the given transform.
     */
    public abstract void transform(AffineTransform at);

    /** Translate the polygon the given distance.
     */
    public abstract void translate(double x, double y);

    /** Return a string representation of the polygon.
     */
    public String toString() {
	String out = getClass().getName() + "[\n";
	for (int i = 0; i < getVertexCount(); i++) {
	    out = out + "\t" + getX(i) + ", " + getY(i) + "\n";
	}
	out = out + "]";
	return out;
    }

    public String asPostGISPolygonString() {
	//POLYGON((3559879.68168291 7262683.31462082,3559883.59677587 7262670.50782531,3559845.34052621 7262672.81638019,3559879.68168291 7262683.31462082))
	StringBuilder out = new StringBuilder("POLYGON((");
	for (int i = 0; i < getVertexCount()-1; i++) {
	    out.append(getX(i) + " " + getY(i) + ", ");
	}
	out.append(getX(getVertexCount()-1) + " " + getY(getVertexCount()-1));
	if (getX(0) != getX(getVertexCount()-1) || getY(0) != getY(getVertexCount()-1)) {
	    out.append(", " + getX(0) + " " + getY(0) + "))");
	} else {
	    out.append("))");
	}
	return out.toString();
    }

    public String asPostGISPolylineString() {
	//LINESTRING(3557113.78158373 7257414.70372425,3556889.06141494 7257345.57717192)
	StringBuilder out = new StringBuilder("LINESTRING(");
	for (int i = 0; i < getVertexCount()-1; i++) {
	    out.append(getX(i) + " " + getY(i) + ", ");
	}
	out.append(getX(getVertexCount()-1) + " " + getY(getVertexCount()-1));
	out.append(")");
	return out.toString();
    }

    public ESRIPoint[] getPoints() {
	ESRIPoint[] returns = new ESRIPoint[getVertexCount()];
	for (int i = 0; i < getVertexCount(); i++) {
	    returns[i] = new ESRIPoint(getX(i), getY(i));
	}
	return returns;
    }

    public void setVisible(boolean visible) {
	this.visible = visible;
    }

    public boolean isVisible() {
	return visible;
    }

    ///////////////////////////////////////////////////////////////////
    //// Float

    /** The concrete Polygon class that stores coordinates internally
     * as floats.
     */
    public static class Float extends ESRIPolygon {

	/** The coordinates
	 */
	float _coords[];

	/** Create a new polygon with space for the
	 * given number of vertices.
	 */
	public Float(int size) {
	    _coords = new float[2 * size];
	}

	/** Create a new polygon with space for the
	 * given number of vertices.
	 */
	public Float(float[] coords) {
	    _coords = coords;
	    coordCount = coords.length;
	}

	/** Create a new polygon with no vertices.
	 */
	public Float() {
	    this(2);
	}

	/** Create a new polygon with a single start point
	 */
	public Float(float x, float y) {
	    this(2);
	    _coords[0] = x;
	    _coords[1] = y;
	    coordCount = 2;
	}

	/** Get the floating-point bounds of the polygon.
	 */
	public Rectangle2D getBounds2D() {
	    if (coordCount <= 1) {
		return new Rectangle2D.Float();
	    }
	    float x1 = _coords[0];
	    float y1 = _coords[1];
	    float x2 = x1;
	    float y2 = y1;
	    for (int i = 2; i < coordCount; ) {
		if (_coords[i] < x1) {
		    x1 = _coords[i];
		} else if (_coords[i] > x2) {
		    x2 = _coords[i];
		}
		i++;
		if (_coords[i] < y1) {
		    y1 = _coords[i];
		} else if (_coords[i] > y2) {
		    y2 = _coords[i];
		}
		i++;
	    }
	    return new Rectangle2D.Float(x1, y1, x2 - x1, y2 - y1);
	}

	/** Get the given X-coordinate
	 *
	 * @throws IndexOutOfBoundsException The index is out of bounds.
	 */
	public double getX(int index) {
	    return _coords[index * 2];
	}

	/** Get the given Y-coordinate
	 *
	 * @throws IndexOutOfBoundsException The index is out of bounds.
	 */
	public double getY(int index) {
	    return _coords[index * 2 + 1];
	}

	public double[] getXCoords() {
	    double[] returns = new double[_coords.length];
	    for (int i = 0; i < _coords.length / 2; i++) {
		returns[i] = _coords[i * 2];
	    }
	    return returns;
	}

	public double[] getYCoords() {
	    double[] returns = new double[_coords.length];
	    for (int i = 0; i < _coords.length / 2; i++) {
		returns[i] = _coords[i * 2 + 1];
	    }
	    return returns;
	}

	/** Add a new vertex to the end of the line.
	 */
	public void lineTo(double x, double y) {
	    if (closed) {
		throw new UnsupportedOperationException("This polygon has already been closed");
	    }
	    if (coordCount == _coords.length) {
		float temp[] = new float[coordCount * 2];
		System.arraycopy(_coords, 0, temp, 0, coordCount);
		_coords = temp;
	    }
	    _coords[coordCount++] = (float)x;
	    _coords[coordCount++] = (float)y;
	}

	/** Move the start point of the vertex to the given position.
	 *
	 * @throws UnsupportedOperationException The polygon already
	 * has vertices
	 */
	public void moveTo(double x, double y) {
	    if (coordCount > 0) {
		throw new UnsupportedOperationException("This polygon already has vertices");
	    }
	    _coords[0] = (float)x;
	    _coords[1] = (float)y;
	    coordCount = 2;
	}

	/** Set the given X-coordinate.
	 *
	 * @throws IndexOutOfBoundsException The index is out of bounds.
	 */
	public void setX(int index, double x) {
	    _coords[index * 2] = (float)x;
	}

	/** Set the given Y-coordinate
	 *
	 * @throws IndexOutOfBoundsException The index is out of bounds.
	 */
	public void setY(int index, double y) {
	    _coords[index * 2 + 1] = (float)y;
	}

	/** Transform the polygon with the given transform.
	 */
	public void transform(AffineTransform at) {
	    at.transform(_coords, 0, _coords, 0, coordCount / 2);
	}

	/** Translate the polygon the given distance.
	 */
	public void translate(double x, double y) {
	    float fx = (float)x;
	    float fy = (float)y;
	    for (int i = 0; i < coordCount; ) {
		_coords[i++] += fx;
		_coords[i++] += fy;
	    }
	}

    }
    ///////////////////////////////////////////////////////////////////
    //// Double

    /** The concrete Polygon class that stores coordinates internally
     * as doubles.
     */
    public static class Double extends ESRIPolygon {

	/** The coordinates
	 */
	double _coords[];

	/** Create a new polygon with space for the
	 * given number of vertices.
	 */
	public Double(int size) {
	    _coords = new double[2 * size];
	}

	/** Create a new polygon with
	 * a given rectangle
	 */
	public Double(Rectangle2D.Double _rectangle) {
	    double[] coords = new double[10];
	    coords[0] = _rectangle.getMinX();
	    coords[1] = _rectangle.getMinY();
	    coords[2] = _rectangle.getMinX();
	    coords[3] = _rectangle.getMaxY();
	    coords[4] = _rectangle.getMaxX();
	    coords[5] = _rectangle.getMaxY();
	    coords[6] = _rectangle.getMaxX();
	    coords[7] = _rectangle.getMinY();
	    coords[8] = _rectangle.getMinX();
	    coords[9] = _rectangle.getMinY();
	    _coords = coords;
	    coordCount = coords.length;
	}

	/** Create a new polygon with the
	 * given vertices, in the format
	 * [x0, y0, x1, y1, ... ].
	 */
	public Double(double[] coords) {
	    _coords = coords;
	    coordCount = coords.length;
	}

	/** Create a new polygon with no coordinates
	 */
	public Double() {
	    this(2);
	}

	/** Create a new polygon with a single start point
	 */
	public Double(double x, double y) {
	    this(2);
	    _coords[0] = x;
	    _coords[1] = y;
	    coordCount = 2;
	}

	/** Get the floating-point bounds of the polygon.
	 */
	public Rectangle2D getBounds2D() {
	    if (coordCount <= 0) {
		return new Rectangle2D.Double();
	    }
	    double x1 = _coords[0];
	    double y1 = _coords[1];
	    double x2 = x1;
	    double y2 = y1;
	    for (int i = 2; i < coordCount; ) {
		if (_coords[i] < x1) {
		    x1 = _coords[i];
		} else if (_coords[i] > x2) {
		    x2 = _coords[i];
		}
		i++;
		if (_coords[i] < y1) {
		    y1 = _coords[i];
		} else if (_coords[i] > y2) {
		    y2 = _coords[i];
		}
		i++;
	    }
	    return new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1);
	}

	/** Get the number of vertices
	 */
	public int getVertexCount() {
	    return coordCount / 2;
	}

	/** Get the given X-coordinate
	 *
	 * @throws IndexOutOfBoundsException The index is out of bounds.
	 */
	public double getX(int index) {
	    return _coords[index * 2];
	}

	/** Get the given Y-coordinate
	 *
	 * @throws IndexOutOfBoundsException The index is out of bounds.
	 */
	public double getY(int index) {
	    return _coords[index * 2 + 1];
	}

	public double[] getXCoords() {
	    double[] returns = new double[_coords.length];
	    for (int i = 0; i < _coords.length / 2; i++) {
		returns[i] = _coords[i * 2];
	    }
	    return returns;
	}

	public double[] getYCoords() {
	    double[] returns = new double[_coords.length];
	    for (int i = 0; i < _coords.length / 2; i++) {
		returns[i] = _coords[i * 2 + 1];
	    }
	    return returns;
	}

	/** Add a new vertex to the end of the line.
	 */
	public void lineTo(double x, double y) {
	    if (coordCount == _coords.length) {
		double temp[] = new double[coordCount * 2];
		System.arraycopy(_coords, 0, temp, 0, coordCount);
		_coords = temp;
	    }
	    _coords[coordCount++] = x;
	    _coords[coordCount++] = y;
	}

	/** Move the start point of the vertex to the given position.
	 *
	 * @throws UnsupportedOperationException The polygon already
	 * has vertices
	 */
	public void moveTo(double x, double y) {
	    if (coordCount > 0) {
		throw new UnsupportedOperationException("This polygon already has vertices");
	    }
	    _coords[0] = x;
	    _coords[1] = y;
	    coordCount = 2;
	}

	/** Set the given X-coordinate.
	 *
	 * @throws IndexOutOfBoundsException The index is out of bounds.
	 */
	public void setX(int index, double x) {
	    _coords[index * 2] = x;
	}

	/** Set the given Y-coordinate
	 *
	 * @throws IndexOutOfBoundsException The index is out of bounds.
	 */
	public void setY(int index, double y) {
	    _coords[index * 2 + 1] = y;
	}

	/** Transform the polygon with the given transform.
	 */
	public void transform(AffineTransform at) {
	    at.transform(_coords, 0, _coords, 0, coordCount / 2);
	}

	/** Translate the polygon the given distance.
	 */
	public void translate(double x, double y) {
	    for (int i = 0; i < coordCount; ) {
		_coords[i++] += x;
		_coords[i++] += y;
	    }
	}

    }

    public void setCentroid(ESRIPoint _centroid) {
	centroid = _centroid;
    }

    public ESRIPoint getCentroid() {
	return centroid;
    }

    public void setSymbol(int _symbol) {
	symbol = _symbol;
    }

    public int getSymbol() {
	return symbol;
    }

    public void setIdPolygon(int _idPolygon) {
	idPolygon = _idPolygon;
    }

    public int getIdPolygon() {
	return idPolygon;
    }
    
    public void setArea(double _area) {
	area = _area;
    }
    
    public double getArea() {
	return area;
    }

    public static ESRIPolygon constructPolygon(double[] xp, double[] yp) {
	int cantidad = xp.length;
	if (cantidad > 0) {
	    double[] xy = new double[cantidad * 2];
	    for (int i = 0; i < cantidad * 2; i += 2) {
		xy[i] = xp[i / 2];
		xy[i + 1] = yp[i / 2];
	    }
	    ESRIPolygon poly = new ESRIPolygon.Double(xy);
	    return poly;
	} else {
	    return new ESRIPolygon.Double(0);
	}
    }



}
