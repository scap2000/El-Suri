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
