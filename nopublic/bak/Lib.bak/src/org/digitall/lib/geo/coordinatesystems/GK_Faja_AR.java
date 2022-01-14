package org.digitall.lib.geo.coordinatesystems;

public class GK_Faja_AR {

    private int faja = -1;
    private LatLongCoord centralMeridian = new LatLongCoord();
    private LatLongCoord upLimit = new LatLongCoord();
    private LatLongCoord loLimit = new LatLongCoord();

    public GK_Faja_AR() {
    }

    public GK_Faja_AR(int _faja) {
	setFaja(_faja);
    }

    public void setFaja(int _faja) {
	faja = _faja;
	centralMeridian.setLongitude(-75.0 + faja * 3.0);
	upLimit.setLongDMS(centralMeridian.getLongitude() + 1.0, 30.0, 0.0);
	loLimit.setLongDMS(centralMeridian.getLongitude() - 2.0, 30.0, 0.0);
    }

}
