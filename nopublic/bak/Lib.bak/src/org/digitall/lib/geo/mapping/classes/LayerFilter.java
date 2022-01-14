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
