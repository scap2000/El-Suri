package org.digitall.lib.geo.esri;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class ESRIPolyline extends ESRIPolygon {

    public Rectangle2D getBounds2D() {
	return null;
    }

    public double getX(int index) {
	return 0.0;
    }

    public double getY(int index) {
	return 0.0;
    }

    public void lineTo(double x, double y) {

    }

    public void moveTo(double x, double y) {

    }

    public void setX(int index, double x) {

    }

    public void setY(int index, double y) {

    }

    public void transform(AffineTransform at) {

    }

    public void translate(double x, double y) {

    }

    public double[] getXCoords() {
	return null;
    }

    public double[] getYCoords() {
	return null;
    }

    public int getIdPolyLine() {
	return super.getIdPolygon();
    }

}
