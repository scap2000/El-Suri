package org.digitall.lib.geo.esri;

import java.awt.geom.Point2D;

public class ESRIMultiPoint implements Cloneable {

    private int numPoints = -1;
    private ESRIPoint[] points;

    public ESRIMultiPoint() {
    }

    public ESRIMultiPoint(ESRIPoint[] _points) {
	points = _points;
	numPoints = points.length;
    }

    public ESRIMultiPoint(ESRIPoint _point) {
	points = new ESRIPoint[1];
	points[0] = new ESRIPoint(_point);
	numPoints = points.length;
    }

    public ESRIMultiPoint(Point2D[] _points) {
	points = new ESRIPoint[_points.length];
	for (int i = 0; i < _points.length; i++) {
	    points[i] = new ESRIPoint(_points[i]);
	}
	numPoints = points.length;
    }

    public ESRIMultiPoint(ESRIPolygon _polygon) {
	ESRIPoint[] _points = _polygon.getPoints(); 
	points = new ESRIPoint[_points.length];
	for (int i = 0; i < _points.length; i++) {
	    points[i] = new ESRIPoint(_points[i]);
	}
	numPoints = points.length;
    }

    public ESRIMultiPoint(ESRIPolyline _polyline) {
	ESRIPoint[] _points = _polyline.getPoints(); 
	points = new ESRIPoint[_points.length];
	for (int i = 0; i < _points.length; i++) {
	    points[i] = new ESRIPoint(_points[i]);
	}
	numPoints = points.length;
    }

    public ESRIPoint[] getPoints() {
	return points;
    }

    public void setPoints(ESRIPoint[] _points) {
	points = _points;
	numPoints = points.length;
    }

    public int getNumPoints() {
	return numPoints;
    }

    public Object clone() {
	return new ESRIMultiPoint(points);
    }

}
