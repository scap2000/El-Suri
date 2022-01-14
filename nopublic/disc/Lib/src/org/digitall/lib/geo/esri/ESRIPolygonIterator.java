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
 * ESRIPolygonIterator.java
 *
 * */
package org.digitall.lib.geo.esri;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

/*
 * $Id: PolygonIterator.java,v 1.3 2001/07/22 22:02:08 johnr Exp $
 *
 * Copyright (c) 1998-2001 The Regents of the University of California.
 * All rights reserved. See the file COPYRIGHT for details.
 *
 */

/** An iterator over Polygon2D. This class is private
 * to this package.
 *
 * @version	$Revision: 1.3 $
 * @author 	John Reekie
 */
public class ESRIPolygonIterator implements PathIterator {

    /** The transformed coordinates being iterated.
     */
    private double[] _coords;

    /** The current coordinate index.
     */
    private int _index = 0;

    /** The flag saying we are already done
     */
    private boolean _done = false;

    /** Create a new iterator over the given polygon and
     * with the given transform. If the transform is null,
     * that is taken to be the same as a unit Transform.
     */
    public ESRIPolygonIterator(ESRIPolygon pl, AffineTransform at) {
	int count = pl.getVertexCount() * 2;
	_coords = new double[count];
	if (pl instanceof ESRIPolygon.Float) {
	    ESRIPolygon.Float f = (ESRIPolygon.Float)pl;
	    if (at == null || at.isIdentity()) {
		for (int i = 0; i < count; i++) {
		    _coords[i] = (double)f._coords[i];
		}
	    } else {
		at.transform(f._coords, 0, _coords, 0, count / 2);
	    }
	} else {
	    ESRIPolygon.Double d = (ESRIPolygon.Double)pl;
	    if (at == null || at.isIdentity()) {
		System.arraycopy(d._coords, 0, _coords, 0, count);
	    } else {
		at.transform(d._coords, 0, _coords, 0, count / 2);
	    }
	}
    }

    /** Get the current segment
     */
    public int currentSegment(double[] coords) {
	if (_index == _coords.length) {
	    if (_done) {
		return PathIterator.SEG_CLOSE;
	    } else {
		coords[0] = this._coords[0];
		coords[1] = this._coords[1];
		return PathIterator.SEG_LINETO;
	    }
	} else {
	    coords[0] = this._coords[_index];
	    coords[1] = this._coords[_index + 1];
	    if (_index == 0) {
		return PathIterator.SEG_MOVETO;
	    } else {
		return PathIterator.SEG_LINETO;
	    }
	}
    }

    /** Get the current segment
     */
    public int currentSegment(float[] coords) {
	if (_index == _coords.length) {
	    if (_done) {
		return PathIterator.SEG_CLOSE;
	    } else {
		coords[0] = (float)this._coords[0];
		coords[1] = (float)this._coords[1];
		return PathIterator.SEG_LINETO;
	    }
	} else {
	    coords[0] = (float)this._coords[_index];
	    coords[1] = (float)this._coords[_index + 1];
	    if (_index == 0) {
		return PathIterator.SEG_MOVETO;
	    } else {
		return PathIterator.SEG_LINETO;
	    }
	}
    }

    /** Return the winding rule. This is WIND_NON_ZERO.
     */
    public int getWindingRule() {
	return PathIterator.WIND_NON_ZERO;
    }

    /** Test if the iterator is done.
     */
    public boolean isDone() {
	return _done;
    }

    /** Move the iterator along by one point.
     */
    public void next() {
	if (_index == _coords.length) {
	    _done = true;
	} else {
	    _index += 2;
	}
    }

}
