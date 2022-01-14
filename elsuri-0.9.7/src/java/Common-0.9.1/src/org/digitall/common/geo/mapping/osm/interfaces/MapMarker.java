package org.digitall.common.geo.mapping.osm.interfaces;

//License: GPL. Copyright 2008 by Jan Peter Stotz

import java.awt.Graphics;
import java.awt.Point;

import org.digitall.common.geo.mapping.osm.JMapViewer;

/**
 * Interface to be implemented by all one dimensional elements that can be displayed on the map.
 *
 * @author Jan Peter Stotz
 * @see org.digitall.common.geo.mapping.osm.JMapViewer#addMapMarker(MapMarker)
 * @see org.digitall.common.geo.mapping.osm.JMapViewer#getMapMarkerList()
 */
public interface MapMarker {

    /**
     * @return Latitude of the map marker position
     */
    public double getLat();

    /**
     * @return Longitude of the map marker position
     */
    public double getLon();

    /**
     * Paints the map marker on the map. The <code>position</code> specifies the
     * coordinates within <code>g</code>
     *
     * @param g
     * @param position
     */
    public void paint(Graphics g, Point position);
}
