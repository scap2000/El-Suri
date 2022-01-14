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
 * InvoiceField.java
 *
 * */
package org.digitall.lib.print;

import java.awt.Color;

import java.text.AttributedString;

public class InvoiceField extends PFParagraph {

    private String name = "S/N";
    private String text = "-";
    private Color foreground = Color.black;
    private Color background = Color.white;
    private PFSize size = new PFSize(new PFmmUnit(0.0), new PFmmUnit(0.0));
    private double x = 0.0;
    private double y = 0.0;
    private double width = 0.0;
    private double height = 0.0;
    private double xOffset = 0.0;
    private double yOffset = 0.0;
    private AttributedString attributedString = new AttributedString(text);

    public InvoiceField(String _name) {
	setName(_name);
	setSize(size);
	setText(new PFMacro().expandMacro(attributedString));
	setHorizontalAlignment(PFParagraph.BOTH_CENTER_JUSTIFIED);
    }

    public void setDimension(double _x, double _y, double _width, double _height) {
	x = _x;
	y = _y;
	width = _width;
	height = _height;
	setSize(new PFSize(new PFmmUnit(width), new PFmmUnit(height)));
	setOffset(false);
    }

    public void setUniverse(double _x, double _y, double _width, double _height, double _xOffset, double _yOffset) {
	xOffset = _xOffset;
	yOffset = _yOffset;
	setDimension(_x, _y, _width, _height);
    }

    public void setOffset(double _xOffset, double _yOffset) {
	xOffset = _xOffset;
	yOffset = _yOffset;
	setDimension(getPosition().getX().getPoints(), getPosition().getY().getPoints(), getSize().getWidth().getPoints(), getSize().getHeight().getPoints());
    }
    
    public void setOffset(boolean offset) {
	setPosition(new PFPoint(new PFmmUnit(x + (offset?xOffset:0.0)), new PFmmUnit(y + (offset?yOffset:0.0))));
	setCenter(new PFPoint(new PFmmUnit(width / 2.0 + x + (offset?xOffset:0.0)), new PFmmUnit(height / 2.0 + y + (offset?yOffset:0.0))));
    }

    public void setName(String _name) {
	name = _name;
    }

    public String getName() {
	return name;
    }

    public void setText(String _text) {
	text = _text.length()<1?"-":_text;
	super.setText(text);
    }

    public String getText() {
	return text;
    }

    public void setForeground(Color _foreground) {
	foreground = _foreground;
    }

    public Color getForeground() {
	return foreground;
    }

    public void setBackground(Color _background) {
	background = _background;
    }

    public Color getBackground() {
	return background;
    }

}
