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
 * ESRIPoint.java
 *
 * */
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
