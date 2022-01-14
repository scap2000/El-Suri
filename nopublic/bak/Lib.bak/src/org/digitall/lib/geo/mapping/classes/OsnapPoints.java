package org.digitall.lib.geo.mapping.classes;

import java.util.Vector;

import org.digitall.lib.geo.esri.ESRIPoint;
import org.digitall.lib.geo.esri.ESRIPolygon;
import org.digitall.lib.geo.esri.ESRIPolyline;

public class OsnapPoints extends Vector<ESRIPoint> {

    private final static int sizeLimit = 40;

    public OsnapPoints() {
	super();
    }

    public boolean addOsnapPoint(ESRIPoint _point) {
	if (size() < sizeLimit*5) {
	    boolean found = false;
	    int i = 0;
	    while ((i < size()) && (!found)) {
		ESRIPoint point = (ESRIPoint)elementAt(i);
		if ((_point.getX() == point.getX()) && (_point.getY() == point.getY())) {
		    found = true;
		}
		i++;
	    }
	    if (!found) {
		return super.add(_point);
	    } else {
		return false;
	    }
	} else {
	    System.out.println("Límite OSNAP excedido");
	    return false;
	}
    }

    public void addOsnapPoints(ESRIPolygon _polygon) {
	for (int i = 0; i < _polygon.getVertexCount() - 1; i++) {
	    addOsnapPoint(new ESRIPoint(_polygon.getX(i), _polygon.getY(i)));
	}
    }

    public void addOsnapPoints(ESRIPolyline _polyline) {
	for (int i = 0; i < _polyline.getVertexCount(); i++) {
	    addOsnapPoint(new ESRIPoint(_polyline.getX(i), _polyline.getY(i)));
	}
    }

   /* public boolean add(Object _object) {
	boolean _added = true;
	if (ESRIPolygon.class.isInstance(_object)) {
	    addOsnapPoints((ESRIPolygon)_object);
	} else if (ESRIPoint.class.isInstance(_object)) {
	    _added = addOsnapPoint((ESRIPoint)_object);
	} else if (ESRIPolyline.class.isInstance(_object)) {
	    addOsnapPoints((ESRIPolyline)_object);
	} else {
	    _added = false;
	}
	return _added;
    }*/

    public void addOsnapPointsVector(OsnapPoints _osnapPoints) {
	for (int i = 0; i < _osnapPoints.size(); i++)  {
	    addOsnapPoint((ESRIPoint)_osnapPoints.elementAt(i));
	}
    }
    
    /*public boolean add(ESRIPoint _point) {
	return addOsnapPoint(_point);
    }*/

}
