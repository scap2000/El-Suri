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
 * LatLongCoord.java
 *
 * */
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
