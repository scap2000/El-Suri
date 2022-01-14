package org.digitall.common.geo.mapping;

import java.awt.BasicStroke;

import java.util.Vector;

import org.digitall.common.geo.mapping.panels.StrokeSamples;
import org.digitall.lib.components.basic.BasicPrimitivePanel;
import org.digitall.lib.geo.mapping.classes.BasicDrawEngineConfig;
import org.digitall.lib.geo.mapping.classes.GeometrySet;
import org.digitall.lib.geo.mapping.classes.Layer;
import org.digitall.lib.geo.mapping.classes.StyleConfig;

public class DrawEnginePanel extends BasicPrimitivePanel {
    
    public Vector getLayerGroups() {
	return null;
    }
    
    public void addLayer(Layer _layer) {
    }

    public void addGeometrySet(GeometrySet _geometrySet) {
    }

    public BasicDrawEngineConfig getEngineConfig() {
	return null;
    }

    protected BasicStroke getStyleConfigStroke(StyleConfig _styleConfig) {
	if (_styleConfig.getStrokeStyle() == -1) {
	    return new BasicStroke(_styleConfig.getLineWidth());
	} else {
	    BasicStroke _stroke = (BasicStroke)StrokeSamples.strokeSamples[_styleConfig.getStrokeStyle()].getStroke();
	    return new BasicStroke(_styleConfig.getLineWidth(), _stroke.getEndCap(), _stroke.getLineJoin(), _stroke.getMiterLimit(), _stroke.getDashArray(), _stroke.getDashPhase());
	}
    }

}
