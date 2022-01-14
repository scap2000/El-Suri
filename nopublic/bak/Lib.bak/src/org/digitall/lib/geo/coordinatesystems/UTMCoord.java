package org.digitall.lib.geo.coordinatesystems;

public class UTMCoord {

    private double easting = 0.0;
    private double northing = 0.0;
    private int zone = -1;
    private int band = 0;
    //=-1 Sur, 1 Norte

    public UTMCoord() {
    }

    public UTMCoord(double _easting, double _northing, int _zone) {
	easting = _easting;
	northing = _northing;
	zone = _zone;
    }

    public void setEasting(double _easting) {
	easting = _easting;
    }

    public double getEasting() {
	return easting;
    }

    public void setNorthing(double _northing) {
	northing = _northing;
    }

    public double getNorthing() {
	return northing;
    }

    public void setZone(int _zone) {
	zone = _zone;
    }

    public int getZone() {
	return zone;
    }

    public void setBand(int _band) {
	band = _band;
    }

    public int getBand() {
	return band;
    }

}
