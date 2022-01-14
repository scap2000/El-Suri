package org.digitall.lib.geo.gaia.components;

import java.awt.geom.Rectangle2D;

import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.components.basic.BasicTabContainer;
import org.digitall.lib.geo.mapping.classes.Layer;

public class GaiaConfigPanel extends BasicPrimitivePanel {

    private Layer layer;
    private Object contentObject;
    private Rectangle2D.Double contentExtents;

    public GaiaConfigPanel() {
	super();
    }

    public void setLayer(Layer layer) {
	this.layer = layer;
    }

    public Layer getLayer() {
	return layer;
    }

    public void setContentObject(Object _contentObject) {
	contentObject = _contentObject;
    }

    public void setContentExtents(Rectangle2D.Double _contentExtents) {
	contentExtents = _contentExtents;
    }

    public Object getContentObject() {
	return contentObject;
    }
    
}
