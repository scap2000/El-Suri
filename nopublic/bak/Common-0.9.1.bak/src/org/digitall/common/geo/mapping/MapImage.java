package org.digitall.common.geo.mapping;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class MapImage extends BufferedImage {

    private Rectangle2D bounds;
    private boolean loaded = false;

    public MapImage(int _width, int _height, int _type) {
	super(_width, _height, _type);
    }

    public void setBounds(Rectangle2D bounds) {
	this.bounds = bounds;
    }

    public Rectangle2D getBounds() {
	return bounds;
    }

    public void setLoaded(boolean loaded) {
	this.loaded = loaded;
    }

    public boolean isLoaded() {
	return loaded;
    }
    
    public double getX() {
	return bounds.getX();
    }

    public double getY() {
	return bounds.getY();
    }

}
