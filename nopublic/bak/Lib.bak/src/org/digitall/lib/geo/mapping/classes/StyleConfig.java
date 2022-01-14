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
