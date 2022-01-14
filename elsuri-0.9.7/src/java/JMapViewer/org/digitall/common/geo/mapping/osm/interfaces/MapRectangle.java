package org.digitall.common.geo.mapping.osm.interfaces;

//License: GPL. Copyright 2009 by Stefan Zeller

import java.awt.Graphics;
import java.awt.Point;

import org.digitall.common.geo.mapping.osm.Coordinate;
import org.digitall.common.geo.mapping.osm.JMapViewer;

/**
 * Interface to be implemented by rectangles that can be displayed on the map.
 *
 * @author Stefan Zeller
 * @see org.digitall.common.geo.mapping.osm.JMapViewer#addMapRectangle(MapRectangle)
 * @see org.digitall.common.geo.mapping.osm.JMapViewer#getMapRectangleList()
 * @date 21.06.2009S
 */
public interface MapRectangle {

    /**
     * @return Latitude/Longitude of top left of rectangle
     */
    public Coordinate getTopLeft();

    /**
     * @return Latitude/Longitude of bottom right of rectangle
     */
    public Coordinate getBottomRight();

    /**
     * Paints the map rectangle on the map. The <code>topLeft</code> and
     * <code>bottomRight</code> are specifying the coordinates within <code>g</code>
     *
     * @param g
     * @param position
     */
    public void paint(Graphics g, Point topLeft, Point bottomRight);
}
