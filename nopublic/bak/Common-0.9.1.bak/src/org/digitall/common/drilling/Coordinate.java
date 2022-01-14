package org.digitall.common.drilling;
import java.awt.geom.Point2D;

public abstract class Coordinate extends Point2D {

    public boolean isNull;

    public void setNull(boolean _isNull) {
	isNull = _isNull;
    }

    public boolean isNull() {
	return isNull;
    }

    public static class Double extends Coordinate {

	public double x;
	public double y;

	public Double(double _x, double _y) {
	    x = _x;
	    y = _y;
	    setNull(false);
	}

	public Double() {
	    setNull(true);
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
	    setNull(false);
	}

    }

}
