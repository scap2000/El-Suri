package org.digitall.lib.geo.coordinatesystems;

public class GKCoord {

    private double x = 0.0;
    private double y = 0.0;
    private int faja = -1;

    public GKCoord() {
    }

    public GKCoord(double _x, double _y, int _faja) {
	x = _x;
	y = _y;
	faja = _faja;
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

    public void setFaja(int _faja) {
	faja = _faja;
    }

    public int getFaja() {
	return faja;
    }

}
