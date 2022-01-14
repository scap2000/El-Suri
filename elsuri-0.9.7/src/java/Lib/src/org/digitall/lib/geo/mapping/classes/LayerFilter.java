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
 * LayerFilter.java
 *
 * */
package org.digitall.lib.geo.mapping.classes;

import java.awt.Color;

import java.io.Serializable;

public class LayerFilter implements Serializable {

    private static final long serialVersionUID = 10000095L;

    private int id;    
    private String name;
    private String column;
    private String expression;
    private String toolTipValue;
    private int matches;
    private boolean active = true;
    private String tableName = "";

    private StyleConfig styleConfig;

    public LayerFilter(String _name, String _column, String _expression, String _toolTipValue) {
	this(_name, _column, _expression, null, null, 1, _toolTipValue);
    }
    
    public LayerFilter(String _name, String _column, String _expression, Color _outLineColor, int _lineWidth, String _toolTipValue) {
	this(_name, _column, _expression, _outLineColor, null, _lineWidth, _toolTipValue);
    }

    public LayerFilter(String _name, String _column, String _expression, Color _outLineColor, Color _fillColor, String _toolTipValue) {
	this(_name, _column, _expression, _outLineColor, _fillColor, 1, _toolTipValue);
    }

    public LayerFilter(String _name, String _column, String _expression, Color _outLineColor, Color _fillColor, int _lineWidth, String _toolTipValue) {
	name = _name;
	column = _column;
	styleConfig = new StyleConfig("[Filter] - " + _name + " - " + _toolTipValue);
	expression = _expression;
	toolTipValue = _toolTipValue;
	styleConfig.setOutlineColor(_outLineColor);
	styleConfig.setFillColor(_fillColor);
	styleConfig.setLineWidth(_lineWidth);
    }

    public void setExpression(String _expression) {
	expression = _expression;
    }

    public String getExpression() {
	return expression;
    }

    public StyleConfig getStyleConfig() {
	return styleConfig;
    }

    public void setToolTipValue(String _toolTipValue) {
	toolTipValue = _toolTipValue;
    }

    public String getToolTipValue() {
	return toolTipValue;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

    public boolean isActive() {
	return active;
    }

    public void setColumn(String column) {
	this.column = column;
    }

    public String getColumn() {
	return column;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void incrementMatch() {
	matches++;
    }

    public int getMatches() {
	return matches;
    }
    
    public void restartMatches() {
	matches = 0;
    }

    public void setID(int _id) {
        id = _id;
    }

    public int getID() {
        return id;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
