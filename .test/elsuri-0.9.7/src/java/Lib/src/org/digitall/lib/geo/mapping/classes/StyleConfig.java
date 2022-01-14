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
 * StyleConfig.java
 *
 * */
package org.digitall.lib.geo.mapping.classes;

import java.awt.Color;

import java.awt.Font;

import java.io.Serializable;

import org.digitall.lib.common.ClassConfigFile;
import org.digitall.lib.common.OrganizationInfo;

public class StyleConfig implements Serializable {

    private static final long serialVersionUID = 10000095L;
    
    private String name = "";
    private Color fillColor;
    private Color outlineColor;
    private int fillPatern = -1;
    private int strokeStyle = -1;
    private int lineWidth = 1;
    private Color mouseOverColor;
    private Color mouseOverOutlineColor;
    private Color selectedColor;
    private Color selectedOutlineColor;
    private Font labelFont = new Font("Arial", Font.PLAIN, 6);
    private Color labelColor = Color.black;
    private int transparency = 100;
    private int symbol = -1;

    public StyleConfig(String _name) {
	name = _name;
    }

    public void setFillColor(Color geometryColor) {
	this.fillColor = geometryColor;
    }

    public Color getFillColor() {
	return getAlphaColor(fillColor);
    }

    public void setOutlineColor(Color outlineColor) {
	this.outlineColor = outlineColor;
    }

    public Color getOutlineColor() {
	return outlineColor;
    }

    public void setFillPatern(int fillPatern) {
	this.fillPatern = fillPatern;
    }

    public int getFillPatern() {
	return fillPatern;
    }

    public void setStrokeStyle(int strokeStyle) {
	this.strokeStyle = strokeStyle;
    }

    public int getStrokeStyle() {
	return strokeStyle;
    }

    public void setLineWidth(int lineWidth) {
	this.lineWidth = lineWidth;
    }

    public int getLineWidth() {
	return lineWidth;
    }

    public void setMouseOverColor(Color mouseOverColor) {
	this.mouseOverColor = mouseOverColor;
    }

    public Color getMouseOverColor() {
	return mouseOverColor;
    }

    public void setMouseOverOutlineColor(Color mouseOverOutlineColor) {
	this.mouseOverOutlineColor = mouseOverOutlineColor;
    }

    public Color getMouseOverOutlineColor() {
	return mouseOverOutlineColor;
    }

    public void setSelectedColor(Color selectedColor) {
	this.selectedColor = selectedColor;
    }

    public Color getSelectedColor() {
	return selectedColor;
    }

    public void setSelectedOutlineColor(Color selectedOutlineColor) {
	this.selectedOutlineColor = selectedOutlineColor;
    }

    public Color getSelectedOutlineColor() {
	return selectedOutlineColor;
    }

    public void setLabelFont(Font labelFont) {
	this.labelFont = labelFont;
    }

    public Font getLabelFont() {
	return labelFont;
    }

    public void setLabelColor(Color labelColor) {
	this.labelColor = labelColor;
    }

    public Color getLabelColor() {
	return labelColor;
    }

    public void setTransparency(int transparency) {
	this.transparency = transparency;
    }

    public int getTransparency() {
	return transparency;
    }

    private Color getAlphaColor(Color _color) {
	return _color!=null?new Color(_color.getRed(), _color.getGreen(), _color.getBlue(), transparency*255/100):null;
    }

    public void setSymbol(int _symbol) {
	symbol = _symbol;
    }

    public int getSymbol() {
	return symbol;
    }

}
