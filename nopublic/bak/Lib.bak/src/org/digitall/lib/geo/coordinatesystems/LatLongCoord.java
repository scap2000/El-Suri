package org.digitall.lib.geo.coordinatesystems;

public class LatLongCoord {

    private double latitude = -1;
    private double longitude = -1;
    private double latD = -1;
    private double latM = -1;
    private double latS = -1;
    private double longD = -1;
    private double longM = -1;
    private double longS = -1;

    public LatLongCoord() {
    }

    public LatLongCoord(double _latitude, double _longitude) {
	latitude = _latitude;
	longitude = _longitude;
	LatLongCoord _temp = dec2gms(latitude, true);
	setLatDMS(_temp.getLatD(), _temp.getLatM(), _temp.getLatS());
	_temp = dec2gms(longitude, false);
	setLongDMS(_temp.getLongD(), _temp.getLongM(), _temp.getLongS());
    }

    public void setLatDMS(double _latD, double _latM, double _latS) {
	latD = _latD;
	latM = _latM;
	latS = _latS;
    }

    public void setLongDMS(double _longD, double _longM, double _longS) {
	longD = _longD;
	longM = _longM;
	longS = _longS;
    }

    public LatLongCoord(double _latD, double _latM, double _latS, double _longD, double _longM, double _longS) {
	latD = _latD;
	latM = _latM;
	latS = _latS;
	longD = _longD;
	longM = _longM;
	longS = _longS;
	latitude = gms2dec(latD, latM, latS);
	longitude = gms2dec(longD, longM, longS);
    }

    public void setLatitude(double _latitude) {
	latitude = _latitude;
	LatLongCoord _temp = dec2gms(latitude, true);
	setLatDMS(_temp.getLatD(), _temp.getLatM(), _temp.getLatS());
    }

    public double getLatitude() {
	return latitude;
    }

    public void setLongitude(double _longitude) {
	longitude = _longitude;
	LatLongCoord _temp = dec2gms(longitude, false);
	setLongDMS(_temp.getLongD(), _temp.getLongM(), _temp.getLongS());
    }

    public double getLongitude() {
	return longitude;
    }

    public void setLatD(double _latD) {
	latD = _latD;
    }

    public double getLatD() {
	return latD;
    }

    public void setLatM(double _latM) {
	latM = _latM;
    }

    public double getLatM() {
	return latM;
    }

    public void setLatS(double _latS) {
	latS = _latS;
    }

    public double getLatS() {
	return latS;
    }

    public void setLongD(double _longD) {
	longD = _longD;
    }

    public double getLongD() {
	return longD;
    }

    public void setLongM(double _longM) {
	longM = _longM;
    }

    public double getLongM() {
	return longM;
    }

    public void setLongS(double _longS) {
	longS = _longS;
    }

    public double getLongS() {
	return longS;
    }

    public double signo(double _num) {
	if (_num < 0) {
	    return -1.0;
	} else {
	    return 1.0;
	}
    }

    private LatLongCoord dec2gms(double _value, boolean _latitude) {
	double _g = Math.floor(Math.abs(_value)) * signo(_value);
	double _m = Math.floor(Math.abs(_value - _g) * 60.0);
	double _s = (Math.abs(_value) - Math.abs(_g) - _m / 60.0) * 3600.0;
	if (_latitude) {
	    return new LatLongCoord(_g, _m, _s, 0, 0, 0);
	} else {
	    return new LatLongCoord(0, 0, 0, _g, _m, _s);
	}
    }

    private double gms2dec(double _g, double _m, double _s) {
	double g = Math.abs(_g) + _m / 60.0 + _s / 3600.0;
	return (g * signo(_g));
    }

}
