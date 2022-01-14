package org.digitall.lib.geo.esri;

import java.awt.geom.Point2D;

import java.io.Serializable;

public class ESRIPoint extends Point2D implements Serializable {

    private double x;
    private double y;
    private int idPoint = -1;
    private int symbol = -1;

    public ESRIPoint(double _x, double _y) {
	x = _x;
	y = _y;
    }

    public ESRIPoint(ESRIPoint _point) {
	this(_point.getX(), _point.getY());
    }

    public ESRIPoint(Point2D _point) {
	this(_point.getX(), _point.getY());
    }

    public double getX() {
	return x;
    }

    public double getY() {
	return y;
    }

    public void setLocation(double _x, double _y) {
	x = _x;
	y = _y;
    }

    public Object clone() {
	return new ESRIPoint(x, y);
    }

    public void setSymbol(int _symbol) {
	symbol = _symbol;
    }

    public int getSymbol() {
	return symbol;
    }

    public void setIdPoint(int _idPoint) {
	idPoint = _idPoint;
    }

    public int getIdPoint() {
	return idPoint;
    }

    public String asPostGISPointString() {
	//POINT(3558274.6481411 7260167.74930834)
	return "POINT(" + getX() + " " + getY() + ")";
    }

}
